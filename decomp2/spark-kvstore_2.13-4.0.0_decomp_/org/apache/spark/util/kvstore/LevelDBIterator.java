package org.apache.spark.util.kvstore;

import java.io.IOException;
import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.iq80.leveldb.DBIterator;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Throwables;

class LevelDBIterator implements KVStoreIterator {
   private static final Cleaner CLEANER = Cleaner.create();
   private final LevelDB db;
   private final boolean ascending;
   private final DBIterator it;
   private final Class type;
   private final LevelDBTypeInfo ti;
   private final LevelDBTypeInfo.Index index;
   private final byte[] indexKeyPrefix;
   private final byte[] end;
   private final long max;
   private final ResourceCleaner resourceCleaner;
   private final Cleaner.Cleanable cleanable;
   private boolean checkedNext;
   private byte[] next;
   private boolean closed;
   private long count;

   LevelDBIterator(Class type, LevelDB db, KVStoreView params) throws Exception {
      this.db = db;
      this.ascending = params.ascending;
      this.it = db.db().iterator();
      this.type = type;
      this.ti = db.getTypeInfo(type);
      this.index = this.ti.index(params.index);
      this.max = params.max;
      this.resourceCleaner = new ResourceCleaner(this.it, db);
      this.cleanable = CLEANER.register(this, this.resourceCleaner);
      Preconditions.checkArgument(!this.index.isChild() || params.parent != null, "Cannot iterate over child index %s without parent value.", params.index);
      byte[] parent = this.index.isChild() ? this.index.parent().childPrefix(params.parent) : null;
      this.indexKeyPrefix = this.index.keyPrefix(parent);
      byte[] firstKey;
      if (params.first != null) {
         if (this.ascending) {
            firstKey = this.index.start(parent, params.first);
         } else {
            firstKey = this.index.end(parent, params.first);
         }
      } else if (this.ascending) {
         firstKey = this.index.keyPrefix(parent);
      } else {
         firstKey = this.index.end(parent);
      }

      this.it.seek(firstKey);
      byte[] end = null;
      if (this.ascending) {
         if (params.last != null) {
            end = this.index.end(parent, params.last);
         } else {
            end = this.index.end(parent);
         }
      } else {
         if (params.last != null) {
            end = this.index.start(parent, params.last);
         }

         if (this.it.hasNext()) {
            byte[] nextKey = (byte[])this.it.peekNext().getKey();
            if (compare(nextKey, this.indexKeyPrefix) <= 0) {
               this.it.next();
            }
         }
      }

      this.end = end;
      if (params.skip > 0L) {
         this.skip(params.skip);
      }

   }

   public boolean hasNext() {
      if (!this.checkedNext && !this.closed) {
         this.next = this.loadNext();
         this.checkedNext = true;
      }

      if (!this.closed && this.next == null) {
         try {
            this.close();
         } catch (IOException ioe) {
            throw new RuntimeException(ioe);
         }
      }

      return this.next != null;
   }

   public Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         this.checkedNext = false;

         try {
            T ret;
            if (this.index != null && !this.index.isCopy()) {
               byte[] key = this.ti.buildKey(false, this.ti.naturalIndex().keyPrefix((byte[])null), this.next);
               ret = (T)this.db.get(key, this.type);
            } else {
               ret = (T)this.db.serializer.deserialize(this.next, this.type);
            }

            this.next = null;
            return ret;
         } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
         }
      }
   }

   public List next(int max) {
      List<T> list = new ArrayList(max);

      while(this.hasNext() && list.size() < max) {
         list.add(this.next());
      }

      return list;
   }

   public boolean skip(long n) {
      if (this.closed) {
         return false;
      } else {
         long skipped = 0L;

         while(skipped < n) {
            if (this.next != null) {
               this.checkedNext = false;
               this.next = null;
               ++skipped;
            } else {
               boolean hasNext = this.ascending ? this.it.hasNext() : this.it.hasPrev();
               if (!hasNext) {
                  this.checkedNext = true;
                  return false;
               }

               Map.Entry<byte[], byte[]> e = this.ascending ? (Map.Entry)this.it.next() : this.it.prev();
               if (!this.isEndMarker((byte[])e.getKey())) {
                  ++skipped;
               }
            }
         }

         return this.hasNext();
      }
   }

   public synchronized void close() throws IOException {
      this.db.notifyIteratorClosed(this.it);
      if (!this.closed) {
         try {
            this.it.close();
         } finally {
            this.closed = true;
            this.next = null;
            this.cancelResourceClean();
         }
      }

   }

   private void cancelResourceClean() {
      this.resourceCleaner.setStartedToFalse();
      this.cleanable.clean();
   }

   DBIterator internalIterator() {
      return this.it;
   }

   @VisibleForTesting
   ResourceCleaner getResourceCleaner() {
      return this.resourceCleaner;
   }

   private byte[] loadNext() {
      if (this.count >= this.max) {
         return null;
      } else {
         Map.Entry<byte[], byte[]> nextEntry;
         byte[] nextKey;
         do {
            boolean hasNext = this.ascending ? this.it.hasNext() : this.it.hasPrev();
            if (!hasNext) {
               return null;
            }

            try {
               nextEntry = this.ascending ? (Map.Entry)this.it.next() : this.it.prev();
            } catch (NoSuchElementException var5) {
               return null;
            }

            nextKey = (byte[])nextEntry.getKey();
            if (!startsWith(nextKey, this.indexKeyPrefix)) {
               return null;
            }
         } while(this.isEndMarker(nextKey));

         if (this.end != null) {
            int comp = compare(nextKey, this.end) * (this.ascending ? 1 : -1);
            if (comp > 0) {
               return null;
            }
         }

         ++this.count;
         return (byte[])nextEntry.getValue();
      }
   }

   @VisibleForTesting
   static boolean startsWith(byte[] key, byte[] prefix) {
      if (key.length < prefix.length) {
         return false;
      } else {
         for(int i = 0; i < prefix.length; ++i) {
            if (key[i] != prefix[i]) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean isEndMarker(byte[] key) {
      return key.length > 2 && key[key.length - 2] == 0 && key[key.length - 1] == LevelDBTypeInfo.END_MARKER[0];
   }

   static int compare(byte[] a, byte[] b) {
      int diff = 0;
      int minLen = Math.min(a.length, b.length);

      for(int i = 0; i < minLen; ++i) {
         diff += a[i] - b[i];
         if (diff != 0) {
            return diff;
         }
      }

      return a.length - b.length;
   }

   static class ResourceCleaner implements Runnable {
      private static final SparkLogger LOG = SparkLoggerFactory.getLogger(ResourceCleaner.class);
      private final DBIterator dbIterator;
      private final LevelDB levelDB;
      private final AtomicBoolean started = new AtomicBoolean(true);

      ResourceCleaner(DBIterator dbIterator, LevelDB levelDB) {
         this.dbIterator = dbIterator;
         this.levelDB = levelDB;
      }

      public void run() {
         if (this.started.compareAndSet(true, false)) {
            try {
               this.levelDB.closeIterator(this.dbIterator);
            } catch (IOException e) {
               LOG.warn("Failed to close iterator", e);
            }
         }

      }

      void setStartedToFalse() {
         this.started.set(false);
      }

      @VisibleForTesting
      boolean isCompleted() {
         return !this.started.get();
      }
   }
}

package org.fusesource.leveldbjni.internal;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBException;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Range;
import org.iq80.leveldb.ReadOptions;
import org.iq80.leveldb.Snapshot;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.WriteOptions;

public class JniDB implements DB {
   private NativeDB db;
   private NativeCache cache;
   private NativeComparator comparator;
   private NativeLogger logger;

   public JniDB(NativeDB db, NativeCache cache, NativeComparator comparator, NativeLogger logger) {
      this.db = db;
      this.cache = cache;
      this.comparator = comparator;
      this.logger = logger;
   }

   public void close() {
      if (this.db != null) {
         this.db.delete();
         this.db = null;
         if (this.cache != null) {
            this.cache.delete();
            this.cache = null;
         }

         if (this.comparator != null) {
            this.comparator.delete();
            this.comparator = null;
         }

         if (this.logger != null) {
            this.logger.delete();
            this.logger = null;
         }
      }

   }

   public byte[] get(byte[] key) throws DBException {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         return this.get(key, new ReadOptions());
      }
   }

   public byte[] get(byte[] key, ReadOptions options) throws DBException {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         try {
            return this.db.get(this.convert(options), key);
         } catch (NativeDB.DBException e) {
            if (e.isNotFound()) {
               return null;
            } else {
               throw new DBException(e.getMessage(), e);
            }
         }
      }
   }

   public DBIterator iterator() {
      return this.iterator(new ReadOptions());
   }

   public DBIterator iterator(ReadOptions options) {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         return new JniDBIterator(this.db.iterator(this.convert(options)));
      }
   }

   public void put(byte[] key, byte[] value) throws DBException {
      this.put(key, value, new WriteOptions());
   }

   public void delete(byte[] key) throws DBException {
      this.delete(key, new WriteOptions());
   }

   public void write(WriteBatch updates) throws DBException {
      this.write(updates, new WriteOptions());
   }

   public WriteBatch createWriteBatch() {
      return new JniWriteBatch(new NativeWriteBatch());
   }

   public Snapshot put(byte[] key, byte[] value, WriteOptions options) throws DBException {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         try {
            this.db.put(this.convert(options), key, value);
            return null;
         } catch (NativeDB.DBException e) {
            throw new DBException(e.getMessage(), e);
         }
      }
   }

   public Snapshot delete(byte[] key, WriteOptions options) throws DBException {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         try {
            this.db.delete(this.convert(options), key);
            return null;
         } catch (NativeDB.DBException e) {
            throw new DBException(e.getMessage(), e);
         }
      }
   }

   public Snapshot write(WriteBatch updates, WriteOptions options) throws DBException {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         try {
            this.db.write(this.convert(options), ((JniWriteBatch)updates).writeBatch());
            return null;
         } catch (NativeDB.DBException e) {
            throw new DBException(e.getMessage(), e);
         }
      }
   }

   public Snapshot getSnapshot() {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         return new JniSnapshot(this.db, this.db.getSnapshot());
      }
   }

   public long[] getApproximateSizes(Range... ranges) {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         NativeRange[] args = new NativeRange[ranges.length];

         for(int i = 0; i < args.length; ++i) {
            args[i] = new NativeRange(ranges[i].start(), ranges[i].limit());
         }

         return this.db.getApproximateSizes(args);
      }
   }

   public String getProperty(String name) {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         return this.db.getProperty(name);
      }
   }

   private NativeReadOptions convert(ReadOptions options) {
      if (options == null) {
         return null;
      } else {
         NativeReadOptions rc = new NativeReadOptions();
         rc.fillCache(options.fillCache());
         rc.verifyChecksums(options.verifyChecksums());
         if (options.snapshot() != null) {
            rc.snapshot(((JniSnapshot)options.snapshot()).snapshot());
         }

         return rc;
      }
   }

   private NativeWriteOptions convert(WriteOptions options) {
      if (options == null) {
         return null;
      } else {
         NativeWriteOptions rc = new NativeWriteOptions();
         rc.sync(options.sync());
         if (options.snapshot()) {
            throw new UnsupportedOperationException("WriteOptions snapshot not supported");
         } else {
            return rc;
         }
      }
   }

   public void compactRange(byte[] begin, byte[] end) throws DBException {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         this.db.compactRange(begin, end);
      }
   }

   public void suspendCompactions() throws InterruptedException {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         this.db.suspendCompactions();
      }
   }

   public void resumeCompactions() {
      if (this.db == null) {
         throw new DBException("Closed");
      } else {
         this.db.resumeCompactions();
      }
   }
}

package org.apache.spark.util.kvstore;

import java.io.File;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import org.apache.spark.annotation.Private;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Throwables;

@Private
public class LevelDB implements KVStore {
   @VisibleForTesting
   static final long STORE_VERSION = 1L;
   @VisibleForTesting
   static final byte[] STORE_VERSION_KEY;
   private static final byte[] METADATA_KEY;
   private static final byte[] TYPE_ALIASES_KEY;
   final AtomicReference _db;
   final KVStoreSerializer serializer;
   private final ConcurrentMap typeAliases;
   private final ConcurrentMap types;
   private final ConcurrentLinkedQueue iteratorTracker;

   public LevelDB(File path) throws Exception {
      this(path, new KVStoreSerializer());
   }

   public LevelDB(File path, KVStoreSerializer serializer) throws Exception {
      this.serializer = serializer;
      this.types = new ConcurrentHashMap();
      Options options = new Options();
      options.createIfMissing(true);
      this._db = new AtomicReference(JniDBFactory.factory.open(path, options));
      byte[] versionData = this.db().get(STORE_VERSION_KEY);
      if (versionData != null) {
         long version = serializer.deserializeLong(versionData);
         if (version != 1L) {
            this.close();
            throw new UnsupportedStoreVersionException();
         }
      } else {
         this.db().put(STORE_VERSION_KEY, serializer.serialize(1L));
      }

      Map<String, byte[]> aliases;
      try {
         aliases = ((TypeAliases)this.get(TYPE_ALIASES_KEY, TypeAliases.class)).aliases;
      } catch (NoSuchElementException var7) {
         aliases = new HashMap();
      }

      this.typeAliases = new ConcurrentHashMap(aliases);
      this.iteratorTracker = new ConcurrentLinkedQueue();
   }

   public Object getMetadata(Class klass) throws Exception {
      try {
         return this.get(METADATA_KEY, klass);
      } catch (NoSuchElementException var3) {
         return null;
      }
   }

   public void setMetadata(Object value) throws Exception {
      if (value != null) {
         this.put(METADATA_KEY, value);
      } else {
         this.db().delete(METADATA_KEY);
      }

   }

   Object get(byte[] key, Class klass) throws Exception {
      byte[] data = this.db().get(key);
      if (data == null) {
         throw new NoSuchElementException(new String(key, StandardCharsets.UTF_8));
      } else {
         return this.serializer.deserialize(data, klass);
      }
   }

   private void put(byte[] key, Object value) throws Exception {
      Preconditions.checkArgument(value != null, "Null values are not allowed.");
      this.db().put(key, this.serializer.serialize(value));
   }

   public Object read(Class klass, Object naturalKey) throws Exception {
      Preconditions.checkArgument(naturalKey != null, "Null keys are not allowed.");
      byte[] key = this.getTypeInfo(klass).naturalIndex().start((byte[])null, naturalKey);
      return this.get(key, klass);
   }

   public void write(Object value) throws Exception {
      Preconditions.checkArgument(value != null, "Null values are not allowed.");
      LevelDBTypeInfo ti = this.getTypeInfo(value.getClass());
      WriteBatch batch = this.db().createWriteBatch();

      try {
         byte[] data = this.serializer.serialize(value);
         synchronized(ti) {
            this.updateBatch(batch, value, data, value.getClass(), ti.naturalIndex(), ti.indices());
            this.db().write(batch);
         }
      } catch (Throwable var9) {
         if (batch != null) {
            try {
               batch.close();
            } catch (Throwable var7) {
               var9.addSuppressed(var7);
            }
         }

         throw var9;
      }

      if (batch != null) {
         batch.close();
      }

   }

   public void writeAll(List values) throws Exception {
      Preconditions.checkArgument(values != null && !values.isEmpty(), "Non-empty values required.");

      for(Map.Entry entry : ((Map)values.stream().collect(Collectors.groupingBy(Object::getClass))).entrySet()) {
         Iterator<?> valueIter = ((List)entry.getValue()).iterator();
         List<byte[]> list = new ArrayList(((List)entry.getValue()).size());

         for(Object value : (List)entry.getValue()) {
            list.add(this.serializer.serialize(value));
         }

         Iterator<byte[]> serializedValueIter = list.iterator();
         Class<?> klass = (Class)entry.getKey();
         LevelDBTypeInfo ti = this.getTypeInfo(klass);
         synchronized(ti) {
            LevelDBTypeInfo.Index naturalIndex = ti.naturalIndex();
            Collection<LevelDBTypeInfo.Index> indices = ti.indices();
            WriteBatch batch = this.db().createWriteBatch();

            try {
               while(valueIter.hasNext()) {
                  assert serializedValueIter.hasNext();

                  this.updateBatch(batch, valueIter.next(), (byte[])serializedValueIter.next(), klass, naturalIndex, indices);
               }

               this.db().write(batch);
            } catch (Throwable var17) {
               if (batch != null) {
                  try {
                     batch.close();
                  } catch (Throwable var16) {
                     var17.addSuppressed(var16);
                  }
               }

               throw var17;
            }

            if (batch != null) {
               batch.close();
            }
         }
      }

   }

   private void updateBatch(WriteBatch batch, Object value, byte[] data, Class klass, LevelDBTypeInfo.Index naturalIndex, Collection indices) throws Exception {
      Object existing;
      try {
         existing = this.get(naturalIndex.entityKey((byte[])null, value), klass);
      } catch (NoSuchElementException var13) {
         existing = null;
      }

      PrefixCache cache = new PrefixCache(value);
      byte[] naturalKey = naturalIndex.toKey(naturalIndex.getValue(value));

      for(LevelDBTypeInfo.Index idx : indices) {
         byte[] prefix = cache.getPrefix(idx);
         idx.add(batch, value, existing, data, naturalKey, prefix);
      }

   }

   public void delete(Class type, Object naturalKey) throws Exception {
      Preconditions.checkArgument(naturalKey != null, "Null keys are not allowed.");

      try {
         WriteBatch batch = this.db().createWriteBatch();

         try {
            LevelDBTypeInfo ti = this.getTypeInfo(type);
            byte[] key = ti.naturalIndex().start((byte[])null, naturalKey);
            synchronized(ti) {
               byte[] data = this.db().get(key);
               if (data != null) {
                  Object existing = this.serializer.deserialize(data, type);
                  PrefixCache cache = new PrefixCache(existing);
                  byte[] keyBytes = ti.naturalIndex().toKey(ti.naturalIndex().getValue(existing));

                  for(LevelDBTypeInfo.Index idx : ti.indices()) {
                     idx.remove(batch, existing, keyBytes, cache.getPrefix(idx));
                  }

                  this.db().write(batch);
               }
            }
         } catch (Throwable var16) {
            if (batch != null) {
               try {
                  batch.close();
               } catch (Throwable var14) {
                  var16.addSuppressed(var14);
               }
            }

            throw var16;
         }

         if (batch != null) {
            batch.close();
         }
      } catch (NoSuchElementException var17) {
      }

   }

   public KVStoreView view(final Class type) throws Exception {
      return new KVStoreView() {
         public Iterator iterator() {
            try {
               LevelDBIterator<T> it = new LevelDBIterator(type, LevelDB.this, this);
               LevelDB.this.iteratorTracker.add(new WeakReference(it));
               return it;
            } catch (Exception e) {
               Throwables.throwIfUnchecked(e);
               throw new RuntimeException(e);
            }
         }
      };
   }

   public boolean removeAllByIndexValues(Class klass, String index, Collection indexValues) throws Exception {
      LevelDBTypeInfo.Index naturalIndex = this.getTypeInfo(klass).naturalIndex();
      boolean removed = false;
      KVStoreView<T> view = this.view(klass).index(index);

      for(Object indexValue : indexValues) {
         KVStoreIterator<T> iterator = view.first(indexValue).last(indexValue).closeableIterator();

         try {
            while(iterator.hasNext()) {
               T value = (T)iterator.next();
               Object itemKey = naturalIndex.getValue(value);
               this.delete(klass, itemKey);
               removed = true;
            }
         } catch (Throwable var13) {
            if (iterator != null) {
               try {
                  iterator.close();
               } catch (Throwable var12) {
                  var13.addSuppressed(var12);
               }
            }

            throw var13;
         }

         if (iterator != null) {
            iterator.close();
         }
      }

      return removed;
   }

   public long count(Class type) throws Exception {
      LevelDBTypeInfo.Index idx = this.getTypeInfo(type).naturalIndex();
      return idx.getCount(idx.end((byte[])null));
   }

   public long count(Class type, String index, Object indexedValue) throws Exception {
      LevelDBTypeInfo.Index idx = this.getTypeInfo(type).index(index);
      return idx.getCount(idx.end((byte[])null, indexedValue));
   }

   public void close() throws IOException {
      synchronized(this._db) {
         DB _db = (DB)this._db.getAndSet((Object)null);
         if (_db != null) {
            try {
               if (this.iteratorTracker != null) {
                  for(Reference ref : this.iteratorTracker) {
                     LevelDBIterator<?> it = (LevelDBIterator)ref.get();
                     if (it != null) {
                        it.close();
                     }
                  }
               }

               _db.close();
            } catch (IOException ioe) {
               throw ioe;
            } catch (Exception e) {
               throw new IOException(e.getMessage(), e);
            }

         }
      }
   }

   void closeIterator(DBIterator it) throws IOException {
      this.notifyIteratorClosed(it);
      synchronized(this._db) {
         DB _db = (DB)this._db.get();
         if (_db != null) {
            it.close();
         }

      }
   }

   void notifyIteratorClosed(DBIterator dbIterator) {
      this.iteratorTracker.removeIf((ref) -> {
         LevelDBIterator<?> it = (LevelDBIterator)ref.get();
         return it != null && dbIterator.equals(it.internalIterator());
      });
   }

   LevelDBTypeInfo getTypeInfo(Class type) throws Exception {
      LevelDBTypeInfo ti = (LevelDBTypeInfo)this.types.get(type);
      if (ti == null) {
         LevelDBTypeInfo tmp = new LevelDBTypeInfo(this, type, this.getTypeAlias(type));
         ti = (LevelDBTypeInfo)this.types.putIfAbsent(type, tmp);
         if (ti == null) {
            ti = tmp;
         }
      }

      return ti;
   }

   DB db() {
      DB _db = (DB)this._db.get();
      if (_db == null) {
         throw new IllegalStateException("DB is closed.");
      } else {
         return _db;
      }
   }

   private byte[] getTypeAlias(Class klass) throws Exception {
      byte[] alias = (byte[])this.typeAliases.get(klass.getName());
      if (alias == null) {
         synchronized(this.typeAliases) {
            byte[] tmp = String.valueOf(this.typeAliases.size()).getBytes(StandardCharsets.UTF_8);
            alias = (byte[])this.typeAliases.putIfAbsent(klass.getName(), tmp);
            if (alias == null) {
               alias = tmp;
               this.put(TYPE_ALIASES_KEY, new TypeAliases(this.typeAliases));
            }
         }
      }

      return alias;
   }

   static {
      STORE_VERSION_KEY = "__version__".getBytes(StandardCharsets.UTF_8);
      METADATA_KEY = "__meta__".getBytes(StandardCharsets.UTF_8);
      TYPE_ALIASES_KEY = "__types__".getBytes(StandardCharsets.UTF_8);
   }

   public static class TypeAliases {
      public Map aliases;

      TypeAliases(Map aliases) {
         this.aliases = aliases;
      }

      TypeAliases() {
         this((Map)null);
      }
   }

   private static class PrefixCache {
      private final Object entity;
      private final Map prefixes;

      PrefixCache(Object entity) {
         this.entity = entity;
         this.prefixes = new HashMap();
      }

      byte[] getPrefix(LevelDBTypeInfo.Index idx) throws Exception {
         byte[] prefix = null;
         if (idx.isChild()) {
            prefix = (byte[])this.prefixes.get(idx.parent());
            if (prefix == null) {
               prefix = idx.parent().childPrefix(idx.parent().getValue(this.entity));
               this.prefixes.put(idx.parent(), prefix);
            }
         }

         return prefix;
      }
   }
}

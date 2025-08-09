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
import org.rocksdb.BlockBasedTableConfig;
import org.rocksdb.BloomFilter;
import org.rocksdb.CompressionType;
import org.rocksdb.Options;
import org.rocksdb.RocksIterator;
import org.rocksdb.WriteBatch;
import org.rocksdb.WriteOptions;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Throwables;

@Private
public class RocksDB implements KVStore {
   @VisibleForTesting
   static final long STORE_VERSION = 1L;
   @VisibleForTesting
   static final byte[] STORE_VERSION_KEY;
   private static final byte[] METADATA_KEY;
   private static final byte[] TYPE_ALIASES_KEY;
   private static final BloomFilter fullFilter;
   private static final BlockBasedTableConfig tableFormatConfig;
   private static final Options dbOptions;
   private static final WriteOptions writeOptions;
   private final AtomicReference _db;
   final KVStoreSerializer serializer;
   private final ConcurrentMap typeAliases;
   private final ConcurrentMap types;
   private final ConcurrentLinkedQueue iteratorTracker;

   public RocksDB(File path) throws Exception {
      this(path, new KVStoreSerializer());
   }

   public RocksDB(File path, KVStoreSerializer serializer) throws Exception {
      this.serializer = serializer;
      this.types = new ConcurrentHashMap();
      this._db = new AtomicReference(org.rocksdb.RocksDB.open(dbOptions, path.toString()));
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
      } catch (NoSuchElementException var6) {
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
      RocksDBTypeInfo ti = this.getTypeInfo(value.getClass());
      byte[] data = this.serializer.serialize(value);
      synchronized(ti) {
         WriteBatch writeBatch = new WriteBatch();

         try {
            this.updateBatch(writeBatch, value, data, value.getClass(), ti.naturalIndex(), ti.indices());
            this.db().write(writeOptions, writeBatch);
         } catch (Throwable var10) {
            try {
               writeBatch.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }

            throw var10;
         }

         writeBatch.close();
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
         RocksDBTypeInfo ti = this.getTypeInfo(klass);
         synchronized(ti) {
            RocksDBTypeInfo.Index naturalIndex = ti.naturalIndex();
            Collection<RocksDBTypeInfo.Index> indices = ti.indices();
            WriteBatch writeBatch = new WriteBatch();

            try {
               while(valueIter.hasNext()) {
                  assert serializedValueIter.hasNext();

                  this.updateBatch(writeBatch, valueIter.next(), (byte[])serializedValueIter.next(), klass, naturalIndex, indices);
               }

               this.db().write(writeOptions, writeBatch);
            } catch (Throwable var17) {
               try {
                  writeBatch.close();
               } catch (Throwable var16) {
                  var17.addSuppressed(var16);
               }

               throw var17;
            }

            writeBatch.close();
         }
      }

   }

   private void updateBatch(WriteBatch batch, Object value, byte[] data, Class klass, RocksDBTypeInfo.Index naturalIndex, Collection indices) throws Exception {
      Object existing;
      try {
         existing = this.get(naturalIndex.entityKey((byte[])null, value), klass);
      } catch (NoSuchElementException var13) {
         existing = null;
      }

      PrefixCache cache = new PrefixCache(value);
      byte[] naturalKey = naturalIndex.toKey(naturalIndex.getValue(value));

      for(RocksDBTypeInfo.Index idx : indices) {
         byte[] prefix = cache.getPrefix(idx);
         idx.add(batch, value, existing, data, naturalKey, prefix);
      }

   }

   public void delete(Class type, Object naturalKey) throws Exception {
      Preconditions.checkArgument(naturalKey != null, "Null keys are not allowed.");

      try {
         WriteBatch writeBatch = new WriteBatch();

         try {
            RocksDBTypeInfo ti = this.getTypeInfo(type);
            byte[] key = ti.naturalIndex().start((byte[])null, naturalKey);
            synchronized(ti) {
               byte[] data = this.db().get(key);
               if (data != null) {
                  Object existing = this.serializer.deserialize(data, type);
                  PrefixCache cache = new PrefixCache(existing);
                  byte[] keyBytes = ti.naturalIndex().toKey(ti.naturalIndex().getValue(existing));

                  for(RocksDBTypeInfo.Index idx : ti.indices()) {
                     idx.remove(writeBatch, existing, keyBytes, cache.getPrefix(idx));
                  }

                  this.db().write(writeOptions, writeBatch);
               }
            }
         } catch (Throwable var16) {
            try {
               writeBatch.close();
            } catch (Throwable var14) {
               var16.addSuppressed(var14);
            }

            throw var16;
         }

         writeBatch.close();
      } catch (NoSuchElementException var17) {
      }

   }

   public KVStoreView view(final Class type) throws Exception {
      return new KVStoreView() {
         public Iterator iterator() {
            try {
               RocksDBIterator<T> it = new RocksDBIterator(type, RocksDB.this, this);
               RocksDB.this.iteratorTracker.add(new WeakReference(it));
               return it;
            } catch (Exception e) {
               Throwables.throwIfUnchecked(e);
               throw new RuntimeException(e);
            }
         }
      };
   }

   public boolean removeAllByIndexValues(Class klass, String index, Collection indexValues) throws Exception {
      RocksDBTypeInfo.Index naturalIndex = this.getTypeInfo(klass).naturalIndex();
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
      RocksDBTypeInfo.Index idx = this.getTypeInfo(type).naturalIndex();
      return idx.getCount(idx.end((byte[])null));
   }

   public long count(Class type, String index, Object indexedValue) throws Exception {
      RocksDBTypeInfo.Index idx = this.getTypeInfo(type).index(index);
      return idx.getCount(idx.end((byte[])null, indexedValue));
   }

   public void close() throws IOException {
      synchronized(this._db) {
         org.rocksdb.RocksDB _db = (org.rocksdb.RocksDB)this._db.getAndSet((Object)null);
         if (_db != null) {
            try {
               if (this.iteratorTracker != null) {
                  for(Reference ref : this.iteratorTracker) {
                     RocksDBIterator<?> it = (RocksDBIterator)ref.get();
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

   void closeIterator(RocksIterator it) {
      this.notifyIteratorClosed(it);
      synchronized(this._db) {
         org.rocksdb.RocksDB _db = (org.rocksdb.RocksDB)this._db.get();
         if (_db != null) {
            it.close();
         }

      }
   }

   void notifyIteratorClosed(RocksIterator rocksIterator) {
      this.iteratorTracker.removeIf((ref) -> {
         RocksDBIterator<?> rocksDBIterator = (RocksDBIterator)ref.get();
         return rocksDBIterator != null && rocksIterator.equals(rocksDBIterator.internalIterator());
      });
   }

   RocksDBTypeInfo getTypeInfo(Class type) throws Exception {
      RocksDBTypeInfo ti = (RocksDBTypeInfo)this.types.get(type);
      if (ti == null) {
         RocksDBTypeInfo tmp = new RocksDBTypeInfo(this, type, this.getTypeAlias(type));
         ti = (RocksDBTypeInfo)this.types.putIfAbsent(type, tmp);
         if (ti == null) {
            ti = tmp;
         }
      }

      return ti;
   }

   org.rocksdb.RocksDB db() {
      org.rocksdb.RocksDB _db = (org.rocksdb.RocksDB)this._db.get();
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
      org.rocksdb.RocksDB.loadLibrary();
      STORE_VERSION_KEY = "__version__".getBytes(StandardCharsets.UTF_8);
      METADATA_KEY = "__meta__".getBytes(StandardCharsets.UTF_8);
      TYPE_ALIASES_KEY = "__types__".getBytes(StandardCharsets.UTF_8);
      fullFilter = new BloomFilter((double)10.0F, false);
      tableFormatConfig = (new BlockBasedTableConfig()).setFilterPolicy(fullFilter).setEnableIndexCompression(false).setIndexBlockRestartInterval(8).setFormatVersion(5);
      dbOptions = (new Options()).setCreateIfMissing(true).setBottommostCompressionType(CompressionType.ZSTD_COMPRESSION).setCompressionType(CompressionType.LZ4_COMPRESSION).setTableFormatConfig(tableFormatConfig);
      writeOptions = (new WriteOptions()).setSync(false);
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

      byte[] getPrefix(RocksDBTypeInfo.Index idx) throws Exception {
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

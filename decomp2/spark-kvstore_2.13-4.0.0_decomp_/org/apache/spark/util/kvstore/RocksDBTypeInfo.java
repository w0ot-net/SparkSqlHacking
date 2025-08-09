package org.apache.spark.util.kvstore;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.rocksdb.RocksDBException;
import org.rocksdb.WriteBatch;
import org.sparkproject.guava.base.Preconditions;

class RocksDBTypeInfo {
   static final byte[] END_MARKER = new byte[]{45};
   static final byte ENTRY_PREFIX = 43;
   static final byte KEY_SEPARATOR = 0;
   static byte TRUE = 49;
   static byte FALSE = 48;
   private static final byte SECONDARY_IDX_PREFIX = 46;
   private static final byte POSITIVE_MARKER = 61;
   private static final byte NEGATIVE_MARKER = 42;
   private static final byte[] HEX_BYTES = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
   private final RocksDB db;
   private final Class type;
   private final Map indices;
   private final byte[] typePrefix;

   RocksDBTypeInfo(RocksDB db, Class type, byte[] alias) throws Exception {
      this.db = db;
      this.type = type;
      this.indices = new HashMap();
      KVTypeInfo ti = new KVTypeInfo(type);
      ti.indices().forEach((idx) -> {
         if (idx.parent().isEmpty() || idx.value().equals("__main__")) {
            this.indices.put(idx.value(), new Index(idx, ti.getAccessor(idx.value()), (Index)null));
         }

      });
      ti.indices().forEach((idx) -> {
         if (!idx.parent().isEmpty() && !idx.value().equals("__main__")) {
            this.indices.put(idx.value(), new Index(idx, ti.getAccessor(idx.value()), (Index)this.indices.get(idx.parent())));
         }

      });
      this.typePrefix = alias;
   }

   Class type() {
      return this.type;
   }

   byte[] keyPrefix() {
      return this.typePrefix;
   }

   Index naturalIndex() {
      return this.index("__main__");
   }

   Index index(String name) {
      Index i = (Index)this.indices.get(name);
      Preconditions.checkArgument(i != null, "Index %s does not exist for type %s.", name, this.type.getName());
      return i;
   }

   Collection indices() {
      return this.indices.values();
   }

   byte[] buildKey(byte[]... components) {
      return this.buildKey(true, components);
   }

   byte[] buildKey(boolean addTypePrefix, byte[]... components) {
      int len = 0;
      if (addTypePrefix) {
         len += this.typePrefix.length + 1;
      }

      for(byte[] comp : components) {
         len += comp.length;
      }

      len += components.length - 1;
      byte[] dest = new byte[len];
      int written = 0;
      if (addTypePrefix) {
         System.arraycopy(this.typePrefix, 0, dest, 0, this.typePrefix.length);
         dest[this.typePrefix.length] = 0;
         written += this.typePrefix.length + 1;
      }

      for(byte[] comp : components) {
         System.arraycopy(comp, 0, dest, written, comp.length);
         written += comp.length;
         if (written < dest.length) {
            dest[written] = 0;
            ++written;
         }
      }

      return dest;
   }

   class Index {
      private final boolean copy;
      private final boolean isNatural;
      private final byte[] name;
      private final KVTypeInfo.Accessor accessor;
      private final Index parent;

      private Index(KVIndex self, KVTypeInfo.Accessor accessor, Index parent) {
         byte[] name = self.value().getBytes(StandardCharsets.UTF_8);
         if (parent != null) {
            byte[] child = new byte[name.length + 1];
            child[0] = 46;
            System.arraycopy(name, 0, child, 1, name.length);
         }

         this.name = name;
         this.isNatural = self.value().equals("__main__");
         this.copy = this.isNatural || self.copy();
         this.accessor = accessor;
         this.parent = parent;
      }

      boolean isCopy() {
         return this.copy;
      }

      boolean isChild() {
         return this.parent != null;
      }

      Index parent() {
         return this.parent;
      }

      byte[] childPrefix(Object value) {
         Preconditions.checkState(this.parent == null, "Not a parent index.");
         return RocksDBTypeInfo.this.buildKey(this.name, this.toParentKey(value));
      }

      Object getValue(Object entity) throws Exception {
         return this.accessor.get(entity);
      }

      private void checkParent(byte[] prefix) {
         if (prefix != null) {
            Preconditions.checkState(this.parent != null, "Parent prefix provided for parent index.");
         } else {
            Preconditions.checkState(this.parent == null, "Parent prefix missing for child index.");
         }

      }

      byte[] keyPrefix(byte[] prefix) {
         this.checkParent(prefix);
         return this.parent != null ? RocksDBTypeInfo.this.buildKey(false, prefix, this.name) : RocksDBTypeInfo.this.buildKey(this.name);
      }

      byte[] start(byte[] prefix, Object value) {
         this.checkParent(prefix);
         return this.parent != null ? RocksDBTypeInfo.this.buildKey(false, prefix, this.name, this.toKey(value)) : RocksDBTypeInfo.this.buildKey(this.name, this.toKey(value));
      }

      byte[] end(byte[] prefix) {
         this.checkParent(prefix);
         return this.parent != null ? RocksDBTypeInfo.this.buildKey(false, prefix, this.name, RocksDBTypeInfo.END_MARKER) : RocksDBTypeInfo.this.buildKey(this.name, RocksDBTypeInfo.END_MARKER);
      }

      byte[] end(byte[] prefix, Object value) {
         this.checkParent(prefix);
         return this.parent != null ? RocksDBTypeInfo.this.buildKey(false, prefix, this.name, this.toKey(value), RocksDBTypeInfo.END_MARKER) : RocksDBTypeInfo.this.buildKey(this.name, this.toKey(value), RocksDBTypeInfo.END_MARKER);
      }

      byte[] entityKey(byte[] prefix, Object entity) throws Exception {
         Object indexValue = this.getValue(entity);
         Preconditions.checkNotNull(indexValue, "Null index value for %s in type %s.", this.name, RocksDBTypeInfo.this.type.getName());
         byte[] entityKey = this.start(prefix, indexValue);
         if (!this.isNatural) {
            entityKey = RocksDBTypeInfo.this.buildKey(false, entityKey, this.toKey(RocksDBTypeInfo.this.naturalIndex().getValue(entity)));
         }

         return entityKey;
      }

      private void updateCount(WriteBatch batch, byte[] key, long delta) throws RocksDBException {
         long updated = this.getCount(key) + delta;
         if (updated > 0L) {
            batch.put(key, RocksDBTypeInfo.this.db.serializer.serialize(updated));
         } else {
            batch.delete(key);
         }

      }

      private void addOrRemove(WriteBatch batch, Object entity, Object existing, byte[] data, byte[] naturalKey, byte[] prefix) throws Exception {
         Object indexValue = this.getValue(entity);
         Preconditions.checkNotNull(indexValue, "Null index value for %s in type %s.", this.name, RocksDBTypeInfo.this.type.getName());
         byte[] entityKey = this.start(prefix, indexValue);
         if (!this.isNatural) {
            entityKey = RocksDBTypeInfo.this.buildKey(false, entityKey, naturalKey);
         }

         boolean needCountUpdate = existing == null;
         if (existing != null && !this.isNatural) {
            byte[] oldPrefix = null;
            Object oldIndexedValue = this.getValue(existing);
            boolean removeExisting = !indexValue.equals(oldIndexedValue);
            if (!removeExisting && this.isChild()) {
               oldPrefix = this.parent().childPrefix(this.parent().getValue(existing));
               removeExisting = RocksDBIterator.compare(prefix, oldPrefix) != 0;
            }

            if (removeExisting) {
               if (oldPrefix == null && this.isChild()) {
                  oldPrefix = this.parent().childPrefix(this.parent().getValue(existing));
               }

               byte[] oldKey = this.entityKey(oldPrefix, existing);
               batch.delete(oldKey);
               if (!this.isChild()) {
                  byte[] oldCountKey = this.end((byte[])null, oldIndexedValue);
                  this.updateCount(batch, oldCountKey, -1L);
                  needCountUpdate = true;
               }
            }
         }

         if (data != null) {
            byte[] stored = this.copy ? data : naturalKey;
            batch.put(entityKey, stored);
         } else {
            batch.delete(entityKey);
         }

         if (needCountUpdate && !this.isChild()) {
            long delta = data != null ? 1L : -1L;
            byte[] countKey = this.isNatural ? this.end(prefix) : this.end(prefix, indexValue);
            this.updateCount(batch, countKey, delta);
         }

      }

      void add(WriteBatch batch, Object entity, Object existing, byte[] data, byte[] naturalKey, byte[] prefix) throws Exception {
         this.addOrRemove(batch, entity, existing, data, naturalKey, prefix);
      }

      void remove(WriteBatch batch, Object entity, byte[] naturalKey, byte[] prefix) throws Exception {
         this.addOrRemove(batch, entity, (Object)null, (byte[])null, naturalKey, prefix);
      }

      long getCount(byte[] key) throws RocksDBException {
         byte[] data = RocksDBTypeInfo.this.db.db().get(key);
         return data != null ? RocksDBTypeInfo.this.db.serializer.deserializeLong(data) : 0L;
      }

      byte[] toParentKey(Object value) {
         return this.toKey(value, (byte)46);
      }

      byte[] toKey(Object value) {
         return this.toKey(value, (byte)43);
      }

      byte[] toKey(Object value, byte prefix) {
         byte[] result;
         if (value instanceof String str) {
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            result = new byte[bytes.length + 1];
            result[0] = prefix;
            System.arraycopy(bytes, 0, result, 1, bytes.length);
         } else if (value instanceof Boolean bool) {
            result = new byte[]{prefix, bool ? RocksDBTypeInfo.TRUE : RocksDBTypeInfo.FALSE};
         } else if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            byte[][] components = new byte[length][];

            for(int i = 0; i < length; ++i) {
               components[i] = this.toKey(Array.get(value, i));
            }

            result = RocksDBTypeInfo.this.buildKey(false, components);
         } else {
            int bytes;
            if (value instanceof Integer) {
               bytes = 32;
            } else if (value instanceof Long) {
               bytes = 64;
            } else if (value instanceof Short) {
               bytes = 16;
            } else {
               if (!(value instanceof Byte)) {
                  throw new IllegalArgumentException(String.format("Type %s not allowed as key.", value.getClass().getName()));
               }

               bytes = 8;
            }

            bytes /= 8;
            byte[] key = new byte[bytes * 2 + 2];
            long longValue = ((Number)value).longValue();
            key[0] = prefix;
            key[1] = (byte)(longValue >= 0L ? 61 : 42);

            for(int i = 0; i < key.length - 2; ++i) {
               int masked = (int)(longValue >>> 4 * i & 15L);
               key[key.length - i - 1] = RocksDBTypeInfo.HEX_BYTES[masked];
            }

            result = key;
         }

         return result;
      }
   }
}

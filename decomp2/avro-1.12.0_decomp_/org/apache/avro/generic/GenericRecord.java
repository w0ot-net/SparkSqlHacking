package org.apache.avro.generic;

public interface GenericRecord extends IndexedRecord {
   void put(String key, Object v);

   Object get(String key);

   default boolean hasField(String key) {
      return this.getSchema().getField(key) != null;
   }
}

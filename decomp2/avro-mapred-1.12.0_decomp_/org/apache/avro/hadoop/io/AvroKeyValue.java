package org.apache.avro.hadoop.io;

import java.util.Arrays;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

public class AvroKeyValue {
   public static final String KEY_VALUE_PAIR_RECORD_NAME = "KeyValuePair";
   public static final String KEY_VALUE_PAIR_RECORD_NAMESPACE = "org.apache.avro.mapreduce";
   public static final String KEY_FIELD = "key";
   public static final String VALUE_FIELD = "value";
   private final GenericRecord mKeyValueRecord;

   public AvroKeyValue(GenericRecord keyValueRecord) {
      this.mKeyValueRecord = keyValueRecord;
   }

   public GenericRecord get() {
      return this.mKeyValueRecord;
   }

   public Object getKey() {
      return this.mKeyValueRecord.get("key");
   }

   public Object getValue() {
      return this.mKeyValueRecord.get("value");
   }

   public void setKey(Object key) {
      this.mKeyValueRecord.put("key", key);
   }

   public void setValue(Object value) {
      this.mKeyValueRecord.put("value", value);
   }

   public static Schema getSchema(Schema keySchema, Schema valueSchema) {
      Schema schema = Schema.createRecord("KeyValuePair", "A key/value pair", "org.apache.avro.mapreduce", false);
      schema.setFields(Arrays.asList(new Schema.Field("key", keySchema, "The key", (Object)null), new Schema.Field("value", valueSchema, "The value", (Object)null)));
      return schema;
   }

   public static class Iterator implements java.util.Iterator {
      private final java.util.Iterator mGenericIterator;

      public Iterator(java.util.Iterator genericIterator) {
         this.mGenericIterator = genericIterator;
      }

      public boolean hasNext() {
         return this.mGenericIterator.hasNext();
      }

      public AvroKeyValue next() {
         GenericRecord genericRecord = (GenericRecord)this.mGenericIterator.next();
         return null == genericRecord ? null : new AvroKeyValue(genericRecord);
      }

      public void remove() {
         this.mGenericIterator.remove();
      }
   }
}

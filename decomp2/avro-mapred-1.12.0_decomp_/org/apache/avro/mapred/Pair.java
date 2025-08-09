package org.apache.avro.mapred;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.Schema.Field.Order;
import org.apache.avro.generic.GenericContainer;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.specific.SpecificData;

public class Pair implements IndexedRecord, Comparable, SpecificData.SchemaConstructable {
   private static final String PAIR = Pair.class.getName();
   private static final String KEY = "key";
   private static final String VALUE = "value";
   private Schema schema;
   private Object key;
   private Object value;
   private static final Map SCHEMA_CACHE = new WeakHashMap();
   private static final Schema STRING_SCHEMA;
   private static final Schema BYTES_SCHEMA;
   private static final Schema INT_SCHEMA;
   private static final Schema LONG_SCHEMA;
   private static final Schema FLOAT_SCHEMA;
   private static final Schema DOUBLE_SCHEMA;
   private static final Schema NULL_SCHEMA;

   public Pair(Schema schema) {
      checkIsPairSchema(schema);
      this.schema = schema;
   }

   public Pair(Object key, Schema keySchema, Object value, Schema valueSchema) {
      this.schema = getPairSchema(keySchema, valueSchema);
      this.key = key;
      this.value = value;
   }

   private static void checkIsPairSchema(Schema schema) {
      if (!PAIR.equals(schema.getFullName())) {
         throw new IllegalArgumentException("Not a Pair schema: " + schema);
      }
   }

   public static Schema getKeySchema(Schema pair) {
      checkIsPairSchema(pair);
      return pair.getField("key").schema();
   }

   public static Schema getValueSchema(Schema pair) {
      checkIsPairSchema(pair);
      return pair.getField("value").schema();
   }

   public static Schema getPairSchema(Schema key, Schema value) {
      synchronized(SCHEMA_CACHE) {
         Map<Schema, Schema> valueSchemas = (Map)SCHEMA_CACHE.computeIfAbsent(key, (k) -> new WeakHashMap());
         Schema result = (Schema)valueSchemas.get(value);
         if (result == null) {
            result = makePairSchema(key, value);
            valueSchemas.put(value, result);
         }

         return result;
      }
   }

   private static Schema makePairSchema(Schema key, Schema value) {
      Schema pair = Schema.createRecord(PAIR, (String)null, (String)null, false);
      List<Schema.Field> fields = Arrays.asList(new Schema.Field("key", key, "", (Object)null), new Schema.Field("value", value, "", (Object)null, Order.IGNORE));
      pair.setFields(fields);
      return pair;
   }

   public Schema getSchema() {
      return this.schema;
   }

   public Object key() {
      return this.key;
   }

   public void key(Object key) {
      this.key = key;
   }

   public Object value() {
      return this.value;
   }

   public void value(Object value) {
      this.value = value;
   }

   public void set(Object key, Object value) {
      this.key = key;
      this.value = value;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Pair)) {
         return false;
      } else {
         Pair that = (Pair)o;
         if (!this.schema.equals(that.schema)) {
            return false;
         } else {
            return this.compareTo(that) == 0;
         }
      }
   }

   public int hashCode() {
      return GenericData.get().hashCode(this, this.schema);
   }

   public int compareTo(Pair that) {
      return GenericData.get().compare(this, that, this.schema);
   }

   public String toString() {
      return GenericData.get().toString(this);
   }

   public Object get(int i) {
      switch (i) {
         case 0:
            return this.key;
         case 1:
            return this.value;
         default:
            throw new AvroRuntimeException("Bad index: " + i);
      }
   }

   public void put(int i, Object o) {
      switch (i) {
         case 0:
            this.key = o;
            break;
         case 1:
            this.value = o;
            break;
         default:
            throw new AvroRuntimeException("Bad index: " + i);
      }

   }

   public Pair(Object key, Object value) {
      this(key, getSchema(key), value, getSchema(value));
   }

   public Pair(Object key, GenericContainer value) {
      this(key, getSchema(key), value, value.getSchema());
   }

   public Pair(Object key, CharSequence value) {
      this(key, getSchema(key), value, STRING_SCHEMA);
   }

   public Pair(Object key, ByteBuffer value) {
      this(key, getSchema(key), value, BYTES_SCHEMA);
   }

   public Pair(Object key, Integer value) {
      this(key, getSchema(key), value, INT_SCHEMA);
   }

   public Pair(Object key, Long value) {
      this(key, getSchema(key), value, LONG_SCHEMA);
   }

   public Pair(Object key, Float value) {
      this(key, getSchema(key), value, FLOAT_SCHEMA);
   }

   public Pair(Object key, Double value) {
      this(key, getSchema(key), value, DOUBLE_SCHEMA);
   }

   public Pair(Object key, Void value) {
      this(key, getSchema(key), value, NULL_SCHEMA);
   }

   public Pair(GenericContainer key, Object value) {
      this(key, key.getSchema(), value, getSchema(value));
   }

   public Pair(GenericContainer key, GenericContainer value) {
      this(key, key.getSchema(), value, value.getSchema());
   }

   public Pair(GenericContainer key, CharSequence value) {
      this(key, key.getSchema(), value, STRING_SCHEMA);
   }

   public Pair(GenericContainer key, ByteBuffer value) {
      this(key, key.getSchema(), value, BYTES_SCHEMA);
   }

   public Pair(GenericContainer key, Integer value) {
      this(key, key.getSchema(), value, INT_SCHEMA);
   }

   public Pair(GenericContainer key, Long value) {
      this(key, key.getSchema(), value, LONG_SCHEMA);
   }

   public Pair(GenericContainer key, Float value) {
      this(key, key.getSchema(), value, FLOAT_SCHEMA);
   }

   public Pair(GenericContainer key, Double value) {
      this(key, key.getSchema(), value, DOUBLE_SCHEMA);
   }

   public Pair(GenericContainer key, Void value) {
      this(key, key.getSchema(), value, NULL_SCHEMA);
   }

   public Pair(CharSequence key, Object value) {
      this(key, STRING_SCHEMA, value, getSchema(value));
   }

   public Pair(CharSequence key, GenericContainer value) {
      this(key, STRING_SCHEMA, value, value.getSchema());
   }

   public Pair(CharSequence key, CharSequence value) {
      this(key, STRING_SCHEMA, value, STRING_SCHEMA);
   }

   public Pair(CharSequence key, ByteBuffer value) {
      this(key, STRING_SCHEMA, value, BYTES_SCHEMA);
   }

   public Pair(CharSequence key, Integer value) {
      this(key, STRING_SCHEMA, value, INT_SCHEMA);
   }

   public Pair(CharSequence key, Long value) {
      this(key, STRING_SCHEMA, value, LONG_SCHEMA);
   }

   public Pair(CharSequence key, Float value) {
      this(key, STRING_SCHEMA, value, FLOAT_SCHEMA);
   }

   public Pair(CharSequence key, Double value) {
      this(key, STRING_SCHEMA, value, DOUBLE_SCHEMA);
   }

   public Pair(CharSequence key, Void value) {
      this(key, STRING_SCHEMA, value, NULL_SCHEMA);
   }

   public Pair(ByteBuffer key, Object value) {
      this(key, BYTES_SCHEMA, value, getSchema(value));
   }

   public Pair(ByteBuffer key, GenericContainer value) {
      this(key, BYTES_SCHEMA, value, value.getSchema());
   }

   public Pair(ByteBuffer key, CharSequence value) {
      this(key, BYTES_SCHEMA, value, STRING_SCHEMA);
   }

   public Pair(ByteBuffer key, ByteBuffer value) {
      this(key, BYTES_SCHEMA, value, BYTES_SCHEMA);
   }

   public Pair(ByteBuffer key, Integer value) {
      this(key, BYTES_SCHEMA, value, INT_SCHEMA);
   }

   public Pair(ByteBuffer key, Long value) {
      this(key, BYTES_SCHEMA, value, LONG_SCHEMA);
   }

   public Pair(ByteBuffer key, Float value) {
      this(key, BYTES_SCHEMA, value, FLOAT_SCHEMA);
   }

   public Pair(ByteBuffer key, Double value) {
      this(key, BYTES_SCHEMA, value, DOUBLE_SCHEMA);
   }

   public Pair(ByteBuffer key, Void value) {
      this(key, BYTES_SCHEMA, value, NULL_SCHEMA);
   }

   public Pair(Integer key, Object value) {
      this(key, INT_SCHEMA, value, getSchema(value));
   }

   public Pair(Integer key, GenericContainer value) {
      this(key, INT_SCHEMA, value, value.getSchema());
   }

   public Pair(Integer key, CharSequence value) {
      this(key, INT_SCHEMA, value, STRING_SCHEMA);
   }

   public Pair(Integer key, ByteBuffer value) {
      this(key, INT_SCHEMA, value, BYTES_SCHEMA);
   }

   public Pair(Integer key, Integer value) {
      this(key, INT_SCHEMA, value, INT_SCHEMA);
   }

   public Pair(Integer key, Long value) {
      this(key, INT_SCHEMA, value, LONG_SCHEMA);
   }

   public Pair(Integer key, Float value) {
      this(key, INT_SCHEMA, value, FLOAT_SCHEMA);
   }

   public Pair(Integer key, Double value) {
      this(key, INT_SCHEMA, value, DOUBLE_SCHEMA);
   }

   public Pair(Integer key, Void value) {
      this(key, INT_SCHEMA, value, NULL_SCHEMA);
   }

   public Pair(Long key, Object value) {
      this(key, LONG_SCHEMA, value, getSchema(value));
   }

   public Pair(Long key, GenericContainer value) {
      this(key, LONG_SCHEMA, value, value.getSchema());
   }

   public Pair(Long key, CharSequence value) {
      this(key, LONG_SCHEMA, value, STRING_SCHEMA);
   }

   public Pair(Long key, ByteBuffer value) {
      this(key, LONG_SCHEMA, value, BYTES_SCHEMA);
   }

   public Pair(Long key, Integer value) {
      this(key, LONG_SCHEMA, value, INT_SCHEMA);
   }

   public Pair(Long key, Long value) {
      this(key, LONG_SCHEMA, value, LONG_SCHEMA);
   }

   public Pair(Long key, Float value) {
      this(key, LONG_SCHEMA, value, FLOAT_SCHEMA);
   }

   public Pair(Long key, Double value) {
      this(key, LONG_SCHEMA, value, DOUBLE_SCHEMA);
   }

   public Pair(Long key, Void value) {
      this(key, LONG_SCHEMA, value, NULL_SCHEMA);
   }

   public Pair(Float key, Object value) {
      this(key, FLOAT_SCHEMA, value, getSchema(value));
   }

   public Pair(Float key, GenericContainer value) {
      this(key, FLOAT_SCHEMA, value, value.getSchema());
   }

   public Pair(Float key, CharSequence value) {
      this(key, FLOAT_SCHEMA, value, STRING_SCHEMA);
   }

   public Pair(Float key, ByteBuffer value) {
      this(key, FLOAT_SCHEMA, value, BYTES_SCHEMA);
   }

   public Pair(Float key, Integer value) {
      this(key, FLOAT_SCHEMA, value, INT_SCHEMA);
   }

   public Pair(Float key, Long value) {
      this(key, FLOAT_SCHEMA, value, LONG_SCHEMA);
   }

   public Pair(Float key, Float value) {
      this(key, FLOAT_SCHEMA, value, FLOAT_SCHEMA);
   }

   public Pair(Float key, Double value) {
      this(key, FLOAT_SCHEMA, value, DOUBLE_SCHEMA);
   }

   public Pair(Float key, Void value) {
      this(key, FLOAT_SCHEMA, value, NULL_SCHEMA);
   }

   public Pair(Double key, Object value) {
      this(key, DOUBLE_SCHEMA, value, getSchema(value));
   }

   public Pair(Double key, GenericContainer value) {
      this(key, DOUBLE_SCHEMA, value, value.getSchema());
   }

   public Pair(Double key, CharSequence value) {
      this(key, DOUBLE_SCHEMA, value, STRING_SCHEMA);
   }

   public Pair(Double key, ByteBuffer value) {
      this(key, DOUBLE_SCHEMA, value, BYTES_SCHEMA);
   }

   public Pair(Double key, Integer value) {
      this(key, DOUBLE_SCHEMA, value, INT_SCHEMA);
   }

   public Pair(Double key, Long value) {
      this(key, DOUBLE_SCHEMA, value, LONG_SCHEMA);
   }

   public Pair(Double key, Float value) {
      this(key, DOUBLE_SCHEMA, value, FLOAT_SCHEMA);
   }

   public Pair(Double key, Double value) {
      this(key, DOUBLE_SCHEMA, value, DOUBLE_SCHEMA);
   }

   public Pair(Double key, Void value) {
      this(key, DOUBLE_SCHEMA, value, NULL_SCHEMA);
   }

   public Pair(Void key, Object value) {
      this(key, NULL_SCHEMA, value, getSchema(value));
   }

   public Pair(Void key, GenericContainer value) {
      this(key, NULL_SCHEMA, value, value.getSchema());
   }

   public Pair(Void key, CharSequence value) {
      this(key, NULL_SCHEMA, value, STRING_SCHEMA);
   }

   public Pair(Void key, ByteBuffer value) {
      this(key, NULL_SCHEMA, value, BYTES_SCHEMA);
   }

   public Pair(Void key, Integer value) {
      this(key, NULL_SCHEMA, value, INT_SCHEMA);
   }

   public Pair(Void key, Long value) {
      this(key, NULL_SCHEMA, value, LONG_SCHEMA);
   }

   public Pair(Void key, Float value) {
      this(key, NULL_SCHEMA, value, FLOAT_SCHEMA);
   }

   public Pair(Void key, Double value) {
      this(key, NULL_SCHEMA, value, DOUBLE_SCHEMA);
   }

   public Pair(Void key, Void value) {
      this(key, NULL_SCHEMA, value, NULL_SCHEMA);
   }

   private static Schema getSchema(Object o) {
      try {
         return ReflectData.get().getSchema(o.getClass());
      } catch (AvroRuntimeException e) {
         throw new AvroRuntimeException("Cannot infer schema for : " + o.getClass() + ".  Must create Pair with explicit key and value schemas.", e);
      }
   }

   static {
      STRING_SCHEMA = Schema.create(Type.STRING);
      BYTES_SCHEMA = Schema.create(Type.BYTES);
      INT_SCHEMA = Schema.create(Type.INT);
      LONG_SCHEMA = Schema.create(Type.LONG);
      FLOAT_SCHEMA = Schema.create(Type.FLOAT);
      DOUBLE_SCHEMA = Schema.create(Type.DOUBLE);
      NULL_SCHEMA = Schema.create(Type.NULL);
   }
}

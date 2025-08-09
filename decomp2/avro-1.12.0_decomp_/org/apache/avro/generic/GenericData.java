package org.apache.avro.generic;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.temporal.Temporal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import org.apache.avro.AvroMissingFieldException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Conversion;
import org.apache.avro.Conversions;
import org.apache.avro.JsonProperties;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;
import org.apache.avro.UnresolvedUnionException;
import org.apache.avro.io.BinaryData;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.FastReaderBuilder;
import org.apache.avro.util.Utf8;
import org.apache.avro.util.internal.Accessor;
import org.apache.avro.util.springframework.ConcurrentReferenceHashMap;

public class GenericData {
   private static final GenericData INSTANCE = new GenericData();
   private static final Map PRIMITIVE_DATUM_TYPES = new IdentityHashMap();
   public static final String STRING_PROP = "avro.java.string";
   protected static final String STRING_TYPE_STRING = "String";
   private final ClassLoader classLoader;
   private Map conversions;
   private Map conversionsByClass;
   public static final String FAST_READER_PROP = "org.apache.avro.fastread";
   private boolean fastReaderEnabled;
   private FastReaderBuilder fastReaderBuilder;
   private static final String TOSTRING_CIRCULAR_REFERENCE_ERROR_TEXT = " \">>> CIRCULAR REFERENCE CANNOT BE PUT IN JSON STRING, ABORTING RECURSION <<<\" ";
   private final ConcurrentMap defaultValueCache;
   private static final Schema STRINGS;

   public static void setStringType(Schema s, StringType stringType) {
      if (stringType == GenericData.StringType.String) {
         s.addProp("avro.java.string", "String");
      }

   }

   public static GenericData get() {
      return INSTANCE;
   }

   public GenericData() {
      this((ClassLoader)null);
   }

   public GenericData(ClassLoader classLoader) {
      this.conversions = new HashMap();
      this.conversionsByClass = new IdentityHashMap();
      this.fastReaderEnabled = "true".equalsIgnoreCase(System.getProperty("org.apache.avro.fastread"));
      this.fastReaderBuilder = null;
      this.defaultValueCache = new ConcurrentReferenceHashMap(128, ConcurrentReferenceHashMap.ReferenceType.WEAK);
      this.classLoader = classLoader != null ? classLoader : this.getClass().getClassLoader();
      this.loadConversions();
   }

   public ClassLoader getClassLoader() {
      return this.classLoader;
   }

   private void loadConversions() {
      for(Conversion conversion : ServiceLoader.load(Conversion.class, this.classLoader)) {
         this.addLogicalTypeConversion(conversion);
      }

   }

   public Collection getConversions() {
      return this.conversions.values();
   }

   public void addLogicalTypeConversion(Conversion conversion) {
      this.conversions.put(conversion.getLogicalTypeName(), conversion);
      Class<?> type = conversion.getConvertedType();
      Map<String, Conversion<?>> conversionsForClass = (Map)this.conversionsByClass.computeIfAbsent(type, (k) -> new LinkedHashMap());
      conversionsForClass.put(conversion.getLogicalTypeName(), conversion);
   }

   public Conversion getConversionByClass(Class datumClass) {
      Map<String, Conversion<?>> conversions = (Map)this.conversionsByClass.get(datumClass);
      return conversions != null ? (Conversion)conversions.values().iterator().next() : null;
   }

   public Conversion getConversionByClass(Class datumClass, LogicalType logicalType) {
      Map<String, Conversion<?>> conversions = (Map)this.conversionsByClass.get(datumClass);
      return conversions != null ? (Conversion)conversions.get(logicalType.getName()) : null;
   }

   public Conversion getConversionFor(LogicalType logicalType) {
      return logicalType == null ? null : (Conversion)this.conversions.get(logicalType.getName());
   }

   public GenericData setFastReaderEnabled(boolean flag) {
      this.fastReaderEnabled = flag;
      return this;
   }

   public boolean isFastReaderEnabled() {
      return this.fastReaderEnabled && FastReaderBuilder.isSupportedData(this);
   }

   public FastReaderBuilder getFastReaderBuilder() {
      if (this.fastReaderBuilder == null) {
         this.fastReaderBuilder = new FastReaderBuilder(this);
      }

      return this.fastReaderBuilder;
   }

   public DatumReader createDatumReader(Schema schema) {
      return this.createDatumReader(schema, schema);
   }

   public DatumReader createDatumReader(Schema writer, Schema reader) {
      return new GenericDatumReader(writer, reader, this);
   }

   public DatumWriter createDatumWriter(Schema schema) {
      return new GenericDatumWriter(schema, this);
   }

   public boolean validate(Schema schema, Object datum) {
      switch (schema.getType()) {
         case RECORD:
            if (!this.isRecord(datum)) {
               return false;
            } else {
               for(Schema.Field f : schema.getFields()) {
                  if (!this.validate(f.schema(), this.getField(datum, f.name(), f.pos()))) {
                     return false;
                  }
               }

               return true;
            }
         case ENUM:
            if (!this.isEnum(datum)) {
               return false;
            }

            return schema.getEnumSymbols().contains(datum.toString());
         case ARRAY:
            if (!this.isArray(datum)) {
               return false;
            } else {
               for(Object element : this.getArrayAsCollection(datum)) {
                  if (!this.validate(schema.getElementType(), element)) {
                     return false;
                  }
               }

               return true;
            }
         case MAP:
            if (!this.isMap(datum)) {
               return false;
            } else {
               Map<Object, Object> map = (Map)datum;

               for(Map.Entry entry : map.entrySet()) {
                  if (!this.validate(schema.getValueType(), entry.getValue())) {
                     return false;
                  }
               }

               return true;
            }
         case UNION:
            try {
               int i = this.resolveUnion(schema, datum);
               return this.validate((Schema)schema.getTypes().get(i), datum);
            } catch (UnresolvedUnionException var6) {
               return false;
            }
         case FIXED:
            return datum instanceof GenericFixed && ((GenericFixed)datum).bytes().length == schema.getFixedSize();
         case STRING:
            return this.isString(datum);
         case BYTES:
            return this.isBytes(datum);
         case INT:
            return this.isInteger(datum);
         case LONG:
            return this.isLong(datum);
         case FLOAT:
            return this.isFloat(datum);
         case DOUBLE:
            return this.isDouble(datum);
         case BOOLEAN:
            return this.isBoolean(datum);
         case NULL:
            return datum == null;
         default:
            return false;
      }
   }

   public String toString(Object datum) {
      StringBuilder buffer = new StringBuilder();
      this.toString(datum, buffer, new IdentityHashMap(128));
      return buffer.toString();
   }

   protected void toString(Object datum, StringBuilder buffer, IdentityHashMap seenObjects) {
      if (this.isRecord(datum)) {
         if (seenObjects.containsKey(datum)) {
            buffer.append(" \">>> CIRCULAR REFERENCE CANNOT BE PUT IN JSON STRING, ABORTING RECURSION <<<\" ");
            return;
         }

         seenObjects.put(datum, datum);
         buffer.append("{");
         int count = 0;
         Schema schema = this.getRecordSchema(datum);

         for(Schema.Field f : schema.getFields()) {
            this.toString(f.name(), buffer, seenObjects);
            buffer.append(": ");
            this.toString(this.getField(datum, f.name(), f.pos()), buffer, seenObjects);
            ++count;
            if (count < schema.getFields().size()) {
               buffer.append(", ");
            }
         }

         buffer.append("}");
         seenObjects.remove(datum);
      } else if (this.isArray(datum)) {
         if (seenObjects.containsKey(datum)) {
            buffer.append(" \">>> CIRCULAR REFERENCE CANNOT BE PUT IN JSON STRING, ABORTING RECURSION <<<\" ");
            return;
         }

         seenObjects.put(datum, datum);
         Collection<?> array = this.getArrayAsCollection(datum);
         buffer.append("[");
         long last = (long)(array.size() - 1);
         int i = 0;

         for(Object element : array) {
            this.toString(element, buffer, seenObjects);
            if ((long)(i++) < last) {
               buffer.append(", ");
            }
         }

         buffer.append("]");
         seenObjects.remove(datum);
      } else if (this.isMap(datum)) {
         if (seenObjects.containsKey(datum)) {
            buffer.append(" \">>> CIRCULAR REFERENCE CANNOT BE PUT IN JSON STRING, ABORTING RECURSION <<<\" ");
            return;
         }

         seenObjects.put(datum, datum);
         buffer.append("{");
         int count = 0;
         Map<Object, Object> map = (Map)datum;

         for(Map.Entry entry : map.entrySet()) {
            buffer.append("\"");
            writeEscapedString(String.valueOf(entry.getKey()), buffer);
            buffer.append("\": ");
            this.toString(entry.getValue(), buffer, seenObjects);
            ++count;
            if (count < map.size()) {
               buffer.append(", ");
            }
         }

         buffer.append("}");
         seenObjects.remove(datum);
      } else if (!this.isString(datum) && !this.isEnum(datum)) {
         if (this.isBytes(datum)) {
            buffer.append("\"");
            ByteBuffer bytes = ((ByteBuffer)datum).duplicate();
            writeEscapedString(StandardCharsets.ISO_8859_1.decode(bytes), buffer);
            buffer.append("\"");
         } else if (!this.isNanOrInfinity(datum) && !this.isTemporal(datum) && !(datum instanceof UUID)) {
            if (datum instanceof GenericData) {
               if (seenObjects.containsKey(datum)) {
                  buffer.append(" \">>> CIRCULAR REFERENCE CANNOT BE PUT IN JSON STRING, ABORTING RECURSION <<<\" ");
                  return;
               }

               seenObjects.put(datum, datum);
               this.toString(datum, buffer, seenObjects);
               seenObjects.remove(datum);
            } else {
               buffer.append(datum);
            }
         } else {
            buffer.append("\"");
            buffer.append(datum);
            buffer.append("\"");
         }
      } else {
         buffer.append("\"");
         writeEscapedString(datum.toString(), buffer);
         buffer.append("\"");
      }

   }

   private boolean isTemporal(Object datum) {
      return datum instanceof Temporal;
   }

   private boolean isNanOrInfinity(Object datum) {
      return datum instanceof Float && (((Float)datum).isInfinite() || ((Float)datum).isNaN()) || datum instanceof Double && (((Double)datum).isInfinite() || ((Double)datum).isNaN());
   }

   private static void writeEscapedString(CharSequence string, StringBuilder builder) {
      for(int i = 0; i < string.length(); ++i) {
         char ch = string.charAt(i);
         switch (ch) {
            case '\b':
               builder.append("\\b");
               continue;
            case '\t':
               builder.append("\\t");
               continue;
            case '\n':
               builder.append("\\n");
               continue;
            case '\f':
               builder.append("\\f");
               continue;
            case '\r':
               builder.append("\\r");
               continue;
            case '"':
               builder.append("\\\"");
               continue;
            case '\\':
               builder.append("\\\\");
               continue;
         }

         if (ch >= 0 && ch <= 31 || ch >= 127 && ch <= 159 || ch >= 8192 && ch <= 8447) {
            String hex = Integer.toHexString(ch);
            builder.append("\\u");

            for(int j = 0; j < 4 - hex.length(); ++j) {
               builder.append('0');
            }

            builder.append(hex.toUpperCase());
         } else {
            builder.append(ch);
         }
      }

   }

   public Schema induce(Object datum) {
      if (this.isRecord(datum)) {
         return this.getRecordSchema(datum);
      } else if (this.isArray(datum)) {
         Schema elementType = null;

         for(Object element : this.getArrayAsCollection(datum)) {
            if (elementType == null) {
               elementType = this.induce(element);
            } else if (!elementType.equals(this.induce(element))) {
               throw new AvroTypeException("No mixed type arrays.");
            }
         }

         if (elementType == null) {
            throw new AvroTypeException("Empty array: " + String.valueOf(datum));
         } else {
            return Schema.createArray(elementType);
         }
      } else if (this.isMap(datum)) {
         Map<Object, Object> map = (Map)datum;
         Schema value = null;

         for(Map.Entry entry : map.entrySet()) {
            if (value == null) {
               value = this.induce(entry.getValue());
            } else if (!value.equals(this.induce(entry.getValue()))) {
               throw new AvroTypeException("No mixed type map values.");
            }
         }

         if (value == null) {
            throw new AvroTypeException("Empty map: " + String.valueOf(datum));
         } else {
            return Schema.createMap(value);
         }
      } else if (datum instanceof GenericFixed) {
         return Schema.createFixed((String)null, (String)null, (String)null, ((GenericFixed)datum).bytes().length);
      } else if (this.isString(datum)) {
         return Schema.create(Schema.Type.STRING);
      } else if (this.isBytes(datum)) {
         return Schema.create(Schema.Type.BYTES);
      } else if (this.isInteger(datum)) {
         return Schema.create(Schema.Type.INT);
      } else if (this.isLong(datum)) {
         return Schema.create(Schema.Type.LONG);
      } else if (this.isFloat(datum)) {
         return Schema.create(Schema.Type.FLOAT);
      } else if (this.isDouble(datum)) {
         return Schema.create(Schema.Type.DOUBLE);
      } else if (this.isBoolean(datum)) {
         return Schema.create(Schema.Type.BOOLEAN);
      } else if (datum == null) {
         return Schema.create(Schema.Type.NULL);
      } else {
         throw new AvroTypeException("Can't create schema for: " + String.valueOf(datum));
      }
   }

   public void setField(Object record, String name, int position, Object value) {
      ((IndexedRecord)record).put(position, value);
   }

   public Object getField(Object record, String name, int position) {
      return ((IndexedRecord)record).get(position);
   }

   protected Object getRecordState(Object record, Schema schema) {
      return null;
   }

   protected void setField(Object record, String name, int position, Object value, Object state) {
      this.setField(record, name, position, value);
   }

   protected Object getField(Object record, String name, int pos, Object state) {
      return this.getField(record, name, pos);
   }

   public int resolveUnion(Schema union, Object datum) {
      if (datum != null) {
         Map<String, Conversion<?>> conversions = (Map)this.conversionsByClass.get(datum.getClass());
         if (conversions != null) {
            List<Schema> candidates = union.getTypes();

            for(int i = 0; i < candidates.size(); ++i) {
               LogicalType candidateType = ((Schema)candidates.get(i)).getLogicalType();
               if (candidateType != null) {
                  Conversion<?> conversion = (Conversion)conversions.get(candidateType.getName());
                  if (conversion != null) {
                     return i;
                  }
               }
            }
         }
      }

      Integer i = union.getIndexNamed(this.getSchemaName(datum));
      if (i != null) {
         return i;
      } else {
         throw new UnresolvedUnionException(union, datum);
      }
   }

   protected String getSchemaName(Object datum) {
      if (datum != null && datum != JsonProperties.NULL_VALUE) {
         String primativeType = (String)this.getPrimitiveTypeCache().get(datum.getClass());
         if (primativeType != null) {
            return primativeType;
         } else if (this.isRecord(datum)) {
            return this.getRecordSchema(datum).getFullName();
         } else if (this.isEnum(datum)) {
            return this.getEnumSchema(datum).getFullName();
         } else if (this.isArray(datum)) {
            return Schema.Type.ARRAY.getName();
         } else if (this.isMap(datum)) {
            return Schema.Type.MAP.getName();
         } else if (this.isFixed(datum)) {
            return this.getFixedSchema(datum).getFullName();
         } else if (this.isString(datum)) {
            return Schema.Type.STRING.getName();
         } else if (this.isBytes(datum)) {
            return Schema.Type.BYTES.getName();
         } else if (this.isInteger(datum)) {
            return Schema.Type.INT.getName();
         } else if (this.isLong(datum)) {
            return Schema.Type.LONG.getName();
         } else if (this.isFloat(datum)) {
            return Schema.Type.FLOAT.getName();
         } else if (this.isDouble(datum)) {
            return Schema.Type.DOUBLE.getName();
         } else if (this.isBoolean(datum)) {
            return Schema.Type.BOOLEAN.getName();
         } else {
            throw new AvroRuntimeException(String.format("Unknown datum type %s: %s", datum.getClass().getName(), datum));
         }
      } else {
         return Schema.Type.NULL.getName();
      }
   }

   protected Map getPrimitiveTypeCache() {
      return PRIMITIVE_DATUM_TYPES;
   }

   protected boolean instanceOf(Schema schema, Object datum) {
      switch (schema.getType()) {
         case RECORD:
            if (!this.isRecord(datum)) {
               return false;
            }

            return schema.getFullName() == null ? this.getRecordSchema(datum).getFullName() == null : schema.getFullName().equals(this.getRecordSchema(datum).getFullName());
         case ENUM:
            if (!this.isEnum(datum)) {
               return false;
            }

            return schema.getFullName().equals(this.getEnumSchema(datum).getFullName());
         case ARRAY:
            return this.isArray(datum);
         case MAP:
            return this.isMap(datum);
         case UNION:
         default:
            throw new AvroRuntimeException("Unexpected type: " + String.valueOf(schema));
         case FIXED:
            if (!this.isFixed(datum)) {
               return false;
            }

            return schema.getFullName().equals(this.getFixedSchema(datum).getFullName());
         case STRING:
            return this.isString(datum);
         case BYTES:
            return this.isBytes(datum);
         case INT:
            return this.isInteger(datum);
         case LONG:
            return this.isLong(datum);
         case FLOAT:
            return this.isFloat(datum);
         case DOUBLE:
            return this.isDouble(datum);
         case BOOLEAN:
            return this.isBoolean(datum);
         case NULL:
            return datum == null;
      }
   }

   protected boolean isArray(Object datum) {
      return datum instanceof Collection;
   }

   protected Collection getArrayAsCollection(Object datum) {
      return (Collection)datum;
   }

   protected boolean isRecord(Object datum) {
      return datum instanceof IndexedRecord;
   }

   protected Schema getRecordSchema(Object record) {
      return ((GenericContainer)record).getSchema();
   }

   protected boolean isEnum(Object datum) {
      return datum instanceof GenericEnumSymbol;
   }

   protected Schema getEnumSchema(Object enu) {
      return ((GenericContainer)enu).getSchema();
   }

   protected boolean isMap(Object datum) {
      return datum instanceof Map;
   }

   protected boolean isFixed(Object datum) {
      return datum instanceof GenericFixed;
   }

   protected Schema getFixedSchema(Object fixed) {
      return ((GenericContainer)fixed).getSchema();
   }

   protected boolean isString(Object datum) {
      return datum instanceof CharSequence;
   }

   protected boolean isBytes(Object datum) {
      return datum instanceof ByteBuffer;
   }

   protected boolean isInteger(Object datum) {
      return datum instanceof Integer;
   }

   protected boolean isLong(Object datum) {
      return datum instanceof Long;
   }

   protected boolean isFloat(Object datum) {
      return datum instanceof Float;
   }

   protected boolean isDouble(Object datum) {
      return datum instanceof Double;
   }

   protected boolean isBoolean(Object datum) {
      return datum instanceof Boolean;
   }

   public int hashCode(Object o, Schema s) {
      HashCodeCalculator calculator = new HashCodeCalculator();
      return calculator.hashCode(o, s);
   }

   public int compare(Object o1, Object o2, Schema s) {
      return this.compare(o1, o2, s, false);
   }

   protected int compareMaps(final Map m1, final Map m2) {
      if (m1 == m2) {
         return 0;
      } else if (m1.isEmpty() && m2.isEmpty()) {
         return 0;
      } else if (m1.size() != m2.size()) {
         return 1;
      } else {
         Object key1 = m1.keySet().iterator().next();
         Object key2 = m2.keySet().iterator().next();
         boolean utf8ToString = false;
         boolean stringToUtf8 = false;
         if (key1 instanceof Utf8 && key2 instanceof String) {
            utf8ToString = true;
         } else if (key1 instanceof String && key2 instanceof Utf8) {
            stringToUtf8 = true;
         }

         try {
            for(Map.Entry e : m1.entrySet()) {
               Object key = e.getKey();
               Object lookupKey = key;
               if (utf8ToString) {
                  lookupKey = key.toString();
               } else if (stringToUtf8) {
                  lookupKey = new Utf8((String)key);
               }

               Object value = e.getValue();
               if (value == null) {
                  if (m2.get(lookupKey) != null || !m2.containsKey(lookupKey)) {
                     return 1;
                  }
               } else {
                  Object value2 = m2.get(lookupKey);
                  if (value instanceof Utf8 && value2 instanceof String) {
                     if (!value.toString().equals(value2)) {
                        return 1;
                     }
                  } else if (value instanceof String && value2 instanceof Utf8) {
                     if (!(new Utf8((String)value)).equals(value2)) {
                        return 1;
                     }
                  } else if (!value.equals(value2)) {
                     return 1;
                  }
               }
            }

            return 0;
         } catch (ClassCastException var13) {
            return 1;
         } catch (NullPointerException var14) {
            return 1;
         }
      }
   }

   protected int compare(Object o1, Object o2, Schema s, boolean equals) {
      if (o1 == o2) {
         return 0;
      } else {
         switch (s.getType()) {
            case RECORD:
               for(Schema.Field f : s.getFields()) {
                  if (f.order() != Schema.Field.Order.IGNORE) {
                     int pos = f.pos();
                     String name = f.name();
                     int compare = this.compare(this.getField(o1, name, pos), this.getField(o2, name, pos), f.schema(), equals);
                     if (compare != 0) {
                        return f.order() == Schema.Field.Order.DESCENDING ? -compare : compare;
                     }
                  }
               }

               return 0;
            case ENUM:
               return s.getEnumOrdinal(o1.toString()) - s.getEnumOrdinal(o2.toString());
            case ARRAY:
               Collection a1 = (Collection)o1;
               Collection a2 = (Collection)o2;
               Iterator e1 = a1.iterator();
               Iterator e2 = a2.iterator();
               Schema elementType = s.getElementType();

               while(e1.hasNext() && e2.hasNext()) {
                  int compare = this.compare(e1.next(), e2.next(), elementType, equals);
                  if (compare != 0) {
                     return compare;
                  }
               }

               return e1.hasNext() ? 1 : (e2.hasNext() ? -1 : 0);
            case MAP:
               if (equals) {
                  return this.compareMaps((Map)o1, (Map)o2);
               }

               throw new AvroRuntimeException("Can't compare maps!");
            case UNION:
               int i1 = this.resolveUnion(s, o1);
               int i2 = this.resolveUnion(s, o2);
               return i1 == i2 ? this.compare(o1, o2, (Schema)s.getTypes().get(i1), equals) : Integer.compare(i1, i2);
            case FIXED:
            case BYTES:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case BOOLEAN:
            default:
               return ((Comparable)o1).compareTo(o2);
            case STRING:
               Utf8 u1 = o1 instanceof Utf8 ? (Utf8)o1 : new Utf8(o1.toString());
               Utf8 u2 = o2 instanceof Utf8 ? (Utf8)o2 : new Utf8(o2.toString());
               return u1.compareTo(u2);
            case NULL:
               return 0;
         }
      }
   }

   public Object getDefaultValue(Schema.Field field) {
      JsonNode json = Accessor.defaultValue(field);
      if (json == null) {
         throw new AvroMissingFieldException("Field " + String.valueOf(field) + " not set and has no default value", field);
      } else {
         return !json.isNull() || field.schema().getType() != Schema.Type.NULL && (field.schema().getType() != Schema.Type.UNION || ((Schema)field.schema().getTypes().get(0)).getType() != Schema.Type.NULL) ? this.defaultValueCache.computeIfAbsent(field, (fieldToGetValueFor) -> {
            try {
               ByteArrayOutputStream baos = new ByteArrayOutputStream();
               BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(baos, (BinaryEncoder)null);
               Accessor.encode(encoder, fieldToGetValueFor.schema(), json);
               encoder.flush();
               BinaryDecoder decoder = DecoderFactory.get().binaryDecoder((byte[])baos.toByteArray(), (BinaryDecoder)null);
               return this.createDatumReader(fieldToGetValueFor.schema()).read((Object)null, decoder);
            } catch (IOException e) {
               throw new AvroRuntimeException(e);
            }
         }) : null;
      }
   }

   public Object deepCopy(Schema schema, Object value) {
      if (value == null) {
         return null;
      } else {
         LogicalType logicalType = schema.getLogicalType();
         if (logicalType == null) {
            return this.deepCopyRaw(schema, value);
         } else {
            Conversion conversion = this.getConversionByClass(value.getClass(), logicalType);
            if (conversion == null) {
               return this.deepCopyRaw(schema, value);
            } else {
               Object raw = Conversions.convertToRawType(value, schema, logicalType, conversion);
               Object copy = this.deepCopyRaw(schema, raw);
               return Conversions.convertToLogicalType(copy, schema, logicalType, conversion);
            }
         }
      }
   }

   private Object deepCopyRaw(Schema schema, Object value) {
      if (value == null) {
         return null;
      } else {
         switch (schema.getType()) {
            case RECORD:
               Object oldState = this.getRecordState(value, schema);
               Object newRecord = this.newRecord((Object)null, schema);
               Object newState = this.getRecordState(newRecord, schema);

               for(Schema.Field f : schema.getFields()) {
                  int pos = f.pos();
                  String name = f.name();
                  Object newValue = this.deepCopy(f.schema(), this.getField(value, name, pos, oldState));
                  this.setField(newRecord, name, pos, newValue, newState);
               }

               return newRecord;
            case ENUM:
               return this.createEnum(value.toString(), schema);
            case ARRAY:
               List<Object> arrayValue = (List)value;
               List<Object> arrayCopy = new Array(arrayValue.size(), schema);

               for(Object obj : arrayValue) {
                  arrayCopy.add(this.deepCopy(schema.getElementType(), obj));
               }

               return arrayCopy;
            case MAP:
               Map<Object, Object> mapValue = (Map)value;
               Map<Object, Object> mapCopy = new HashMap(mapValue.size());

               for(Map.Entry entry : mapValue.entrySet()) {
                  mapCopy.put(this.deepCopy(STRINGS, entry.getKey()), this.deepCopy(schema.getValueType(), entry.getValue()));
               }

               return mapCopy;
            case UNION:
               return this.deepCopy((Schema)schema.getTypes().get(this.resolveUnion(schema, value)), value);
            case FIXED:
               return this.createFixed((Object)null, ((GenericFixed)value).bytes(), schema);
            case STRING:
               return this.createString(value);
            case BYTES:
               ByteBuffer byteBufferValue = (ByteBuffer)value;
               int start = byteBufferValue.position();
               int length = byteBufferValue.limit() - start;
               byte[] bytesCopy = new byte[length];
               byteBufferValue.get(bytesCopy, 0, length);
               ((Buffer)byteBufferValue).position(start);
               return ByteBuffer.wrap(bytesCopy, 0, length);
            case INT:
               return value;
            case LONG:
               return value;
            case FLOAT:
               return value;
            case DOUBLE:
               return value;
            case BOOLEAN:
               return value;
            case NULL:
               return null;
            default:
               String var10002 = String.valueOf(schema);
               throw new AvroRuntimeException("Deep copy failed for schema \"" + var10002 + "\" and value \"" + String.valueOf(value) + "\"");
         }
      }
   }

   public Object createFixed(Object old, Schema schema) {
      return old instanceof GenericFixed && ((GenericFixed)old).bytes().length == schema.getFixedSize() ? old : new Fixed(schema);
   }

   public Object createFixed(Object old, byte[] bytes, Schema schema) {
      GenericFixed fixed = (GenericFixed)this.createFixed(old, schema);
      System.arraycopy(bytes, 0, fixed.bytes(), 0, schema.getFixedSize());
      return fixed;
   }

   public Object createEnum(String symbol, Schema schema) {
      return new EnumSymbol(schema, symbol);
   }

   public Object newRecord(Object old, Schema schema) {
      if (old instanceof IndexedRecord) {
         IndexedRecord record = (IndexedRecord)old;
         if (record.getSchema() == schema) {
            return record;
         }
      }

      return new Record(schema);
   }

   public Object createString(Object value) {
      if (value instanceof String) {
         return value;
      } else {
         return value instanceof Utf8 ? new Utf8((Utf8)value) : new Utf8(value.toString());
      }
   }

   public Object newArray(Object old, int size, Schema schema) {
      if (old instanceof GenericArray) {
         ((GenericArray)old).reset();
         return old;
      } else if (old instanceof Collection) {
         ((Collection)old).clear();
         return old;
      } else if (schema.getElementType().getType() == Schema.Type.INT) {
         return new PrimitivesArrays.IntArray(size, schema);
      } else if (schema.getElementType().getType() == Schema.Type.BOOLEAN) {
         return new PrimitivesArrays.BooleanArray(size, schema);
      } else if (schema.getElementType().getType() == Schema.Type.LONG) {
         return new PrimitivesArrays.LongArray(size, schema);
      } else if (schema.getElementType().getType() == Schema.Type.FLOAT) {
         return new PrimitivesArrays.FloatArray(size, schema);
      } else {
         return schema.getElementType().getType() == Schema.Type.DOUBLE ? new PrimitivesArrays.DoubleArray(size, schema) : new Array(size, schema);
      }
   }

   public Object newMap(Object old, int size) {
      if (old instanceof Map) {
         ((Map)old).clear();
         return old;
      } else {
         return new HashMap(size);
      }
   }

   public InstanceSupplier getNewRecordSupplier(Schema schema) {
      return this::newRecord;
   }

   static {
      PRIMITIVE_DATUM_TYPES.put(Integer.class, Schema.Type.INT.getName());
      PRIMITIVE_DATUM_TYPES.put(Long.class, Schema.Type.LONG.getName());
      PRIMITIVE_DATUM_TYPES.put(Float.class, Schema.Type.FLOAT.getName());
      PRIMITIVE_DATUM_TYPES.put(Double.class, Schema.Type.DOUBLE.getName());
      PRIMITIVE_DATUM_TYPES.put(Boolean.class, Schema.Type.BOOLEAN.getName());
      PRIMITIVE_DATUM_TYPES.put(String.class, Schema.Type.STRING.getName());
      PRIMITIVE_DATUM_TYPES.put(Utf8.class, Schema.Type.STRING.getName());
      STRINGS = Schema.create(Schema.Type.STRING);
   }

   public static enum StringType {
      CharSequence,
      String,
      Utf8;

      // $FF: synthetic method
      private static StringType[] $values() {
         return new StringType[]{CharSequence, String, Utf8};
      }
   }

   public static class Record implements GenericRecord, Comparable {
      private final Schema schema;
      private final Object[] values;

      public Record(Schema schema) {
         if (schema != null && Schema.Type.RECORD.equals(schema.getType())) {
            this.schema = schema;
            this.values = new Object[schema.getFields().size()];
         } else {
            throw new AvroRuntimeException("Not a record schema: " + String.valueOf(schema));
         }
      }

      public Record(Record other, boolean deepCopy) {
         this.schema = other.schema;
         this.values = new Object[this.schema.getFields().size()];
         if (deepCopy) {
            for(int ii = 0; ii < this.values.length; ++ii) {
               this.values[ii] = GenericData.INSTANCE.deepCopy(((Schema.Field)this.schema.getFields().get(ii)).schema(), other.values[ii]);
            }
         } else {
            System.arraycopy(other.values, 0, this.values, 0, other.values.length);
         }

      }

      public Schema getSchema() {
         return this.schema;
      }

      public void put(String key, Object value) {
         Schema.Field field = this.schema.getField(key);
         if (field == null) {
            throw new AvroRuntimeException("Not a valid schema field: " + key);
         } else {
            this.values[field.pos()] = value;
         }
      }

      public void put(int i, Object v) {
         this.values[i] = v;
      }

      public Object get(String key) {
         Schema.Field field = this.schema.getField(key);
         if (field == null) {
            throw new AvroRuntimeException("Not a valid schema field: " + key);
         } else {
            return this.values[field.pos()];
         }
      }

      public Object get(int i) {
         return this.values[i];
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Record)) {
            return false;
         } else {
            Record that = (Record)o;
            if (!this.schema.equals(that.schema)) {
               return false;
            } else {
               return GenericData.get().compare(this, that, this.schema, true) == 0;
            }
         }
      }

      public int hashCode() {
         return GenericData.get().hashCode(this, this.schema);
      }

      public int compareTo(Record that) {
         return GenericData.get().compare(this, that, this.schema);
      }

      public String toString() {
         return GenericData.get().toString(this);
      }
   }

   public abstract static class AbstractArray extends AbstractList implements GenericArray, Comparable {
      private final Schema schema;
      protected int size = 0;

      public AbstractArray(Schema schema) {
         if (schema != null && Schema.Type.ARRAY.equals(schema.getType())) {
            this.schema = schema;
         } else {
            throw new AvroRuntimeException("Not an array schema: " + String.valueOf(schema));
         }
      }

      public Schema getSchema() {
         return this.schema;
      }

      public int size() {
         return this.size;
      }

      public void reset() {
         this.size = 0;
      }

      public int compareTo(GenericArray that) {
         return GenericData.get().compare(this, that, this.getSchema());
      }

      public boolean equals(final Object o) {
         if (!(o instanceof Collection)) {
            return false;
         } else {
            return GenericData.get().compare(this, o, this.getSchema()) == 0;
         }
      }

      public int hashCode() {
         return super.hashCode();
      }

      public Iterator iterator() {
         return new Iterator() {
            private int position = 0;

            public boolean hasNext() {
               return this.position < AbstractArray.this.size;
            }

            public Object next() {
               return AbstractArray.this.get(this.position++);
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
      }

      public void reverse() {
         int left = 0;

         for(int right = this.size - 1; left < right; --right) {
            this.swap(left, right);
            ++left;
         }

      }

      protected abstract void swap(int index1, int index2);
   }

   public static class Array extends AbstractArray {
      private static final Object[] EMPTY = new Object[0];
      private Object[] elements;

      public Array(int capacity, Schema schema) {
         super(schema);
         this.elements = EMPTY;
         if (capacity != 0) {
            this.elements = new Object[capacity];
         }

      }

      public Array(Schema schema, Collection c) {
         super(schema);
         this.elements = EMPTY;
         if (c != null) {
            this.elements = new Object[c.size()];
            this.addAll(c);
         }

      }

      public void clear() {
         Arrays.fill(this.elements, 0, this.size, (Object)null);
         this.size = 0;
      }

      public void prune() {
         if (this.size < this.elements.length) {
            Arrays.fill(this.elements, this.size, this.elements.length, (Object)null);
         }

      }

      public Object get(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            return this.elements[i];
         }
      }

      public void add(int location, Object o) {
         if (location <= this.size && location >= 0) {
            if (this.size == this.elements.length) {
               int newSize = this.size + (this.size >> 1) + 1;
               this.elements = Arrays.copyOf(this.elements, newSize);
            }

            System.arraycopy(this.elements, location, this.elements, location + 1, this.size - location);
            this.elements[location] = o;
            ++this.size;
         } else {
            throw new IndexOutOfBoundsException("Index " + location + " out of bounds.");
         }
      }

      public Object set(int i, Object o) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            T response = (T)this.elements[i];
            this.elements[i] = o;
            return response;
         }
      }

      public Object remove(int i) {
         if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
         } else {
            T result = (T)this.elements[i];
            --this.size;
            System.arraycopy(this.elements, i + 1, this.elements, i, this.size - i);
            this.elements[this.size] = null;
            return result;
         }
      }

      public Object peek() {
         return this.size < this.elements.length ? this.elements[this.size] : null;
      }

      protected void swap(final int index1, final int index2) {
         Object tmp = this.elements[index1];
         this.elements[index1] = this.elements[index2];
         this.elements[index2] = tmp;
      }
   }

   public static class Fixed implements GenericFixed, Comparable {
      private Schema schema;
      private byte[] bytes;

      public Fixed(Schema schema) {
         this.setSchema(schema);
      }

      public Fixed(Schema schema, byte[] bytes) {
         this.schema = schema;
         this.bytes = bytes;
      }

      protected Fixed() {
      }

      protected void setSchema(Schema schema) {
         this.schema = schema;
         this.bytes = new byte[schema.getFixedSize()];
      }

      public Schema getSchema() {
         return this.schema;
      }

      public void bytes(byte[] bytes) {
         this.bytes = bytes;
      }

      public byte[] bytes() {
         return this.bytes;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            return o instanceof GenericFixed && Arrays.equals(this.bytes, ((GenericFixed)o).bytes());
         }
      }

      public int hashCode() {
         return Arrays.hashCode(this.bytes);
      }

      public String toString() {
         return Arrays.toString(this.bytes);
      }

      public int compareTo(Fixed that) {
         return BinaryData.compareBytes(this.bytes, 0, this.bytes.length, that.bytes, 0, that.bytes.length);
      }
   }

   public static class EnumSymbol implements GenericEnumSymbol {
      private Schema schema;
      private String symbol;

      public EnumSymbol(Schema schema, String symbol) {
         this.schema = schema;
         this.symbol = symbol;
      }

      public EnumSymbol(Schema schema, Object symbol) {
         this(schema, symbol.toString());
      }

      public Schema getSchema() {
         return this.schema;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            return o instanceof GenericEnumSymbol && this.symbol.equals(o.toString());
         }
      }

      public int hashCode() {
         return this.symbol.hashCode();
      }

      public String toString() {
         return this.symbol;
      }

      public int compareTo(EnumSymbol that) {
         return GenericData.get().compare(this, that, this.schema);
      }
   }

   class HashCodeCalculator {
      private int counter = 10;
      private int currentHashCode = 1;

      public int hashCode(Object o, Schema s) {
         if (o == null) {
            return 0;
         } else {
            switch (s.getType()) {
               case RECORD:
                  for(Schema.Field f : s.getFields()) {
                     if (this.shouldStop()) {
                        return this.currentHashCode;
                     }

                     if (f.order() != Schema.Field.Order.IGNORE) {
                        Object fieldValue = ((IndexedRecord)o).get(f.pos());
                        this.currentHashCode = this.hashCodeAdd(fieldValue, f.schema());
                     }
                  }

                  return this.currentHashCode;
               case ENUM:
                  return s.getEnumOrdinal(o.toString());
               case ARRAY:
                  Collection<?> a = (Collection)o;
                  Schema elementType = s.getElementType();

                  for(Object e : a) {
                     if (this.shouldStop()) {
                        return this.currentHashCode;
                     }

                     this.currentHashCode = this.hashCodeAdd(e, elementType);
                  }

                  return this.currentHashCode;
               case MAP:
               case FIXED:
               case BYTES:
               case INT:
               case LONG:
               case FLOAT:
               case DOUBLE:
               case BOOLEAN:
               default:
                  return o.hashCode();
               case UNION:
                  return this.hashCode(o, (Schema)s.getTypes().get(GenericData.this.resolveUnion(s, o)));
               case STRING:
                  return (o instanceof Utf8 ? o : new Utf8(o.toString())).hashCode();
               case NULL:
                  return 0;
            }
         }
      }

      protected int hashCodeAdd(Object o, Schema s) {
         return 31 * this.currentHashCode + this.hashCode(o, s);
      }

      private boolean shouldStop() {
         return --this.counter <= 0;
      }
   }

   public interface InstanceSupplier {
      Object newInstance(Object oldInstance, Schema schema);
   }
}

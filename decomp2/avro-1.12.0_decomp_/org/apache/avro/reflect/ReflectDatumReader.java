package org.apache.avro.reflect;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.ResolvingDecoder;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificDatumReader;

public class ReflectDatumReader extends SpecificDatumReader {
   public ReflectDatumReader() {
      this((Schema)null, (Schema)null, ReflectData.get());
   }

   public ReflectDatumReader(Class c) {
      this(new ReflectData(c.getClassLoader()));
      this.setSchema(this.getSpecificData().getSchema(c));
   }

   public ReflectDatumReader(Schema root) {
      this(root, root, ReflectData.get());
   }

   public ReflectDatumReader(Schema writer, Schema reader) {
      this(writer, reader, ReflectData.get());
   }

   public ReflectDatumReader(Schema writer, Schema reader, ReflectData data) {
      super(writer, reader, data);
   }

   public ReflectDatumReader(ReflectData data) {
      super((SpecificData)data);
   }

   protected Object newArray(Object old, int size, Schema schema) {
      Class<?> collectionClass = ReflectData.getClassProp(schema, "java-class");
      Class<?> elementClass = ReflectData.getClassProp(schema, "java-element-class");
      if (elementClass == null) {
         Conversion<?> elementConversion = this.getData().getConversionFor(schema.getElementType().getLogicalType());
         if (elementConversion != null) {
            elementClass = elementConversion.getConvertedType();
         }
      }

      if (collectionClass == null && elementClass == null) {
         return super.newArray(old, size, schema);
      } else if (collectionClass != null && !collectionClass.isArray()) {
         if (old instanceof Collection) {
            ((Collection)old).clear();
            return old;
         } else if (collectionClass.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
         } else if (collectionClass.isAssignableFrom(HashSet.class)) {
            return new HashSet();
         } else {
            return collectionClass.isAssignableFrom(HashMap.class) ? new HashMap() : SpecificData.newInstance(collectionClass, schema);
         }
      } else {
         if (elementClass == null) {
            elementClass = collectionClass.getComponentType();
         }

         if (elementClass == null) {
            ReflectData data = (ReflectData)this.getData();
            elementClass = data.getClass(schema.getElementType());
         }

         return Array.newInstance(elementClass, size);
      }
   }

   protected Object peekArray(Object array) {
      return null;
   }

   protected void addToArray(Object array, long pos, Object e) {
      throw new AvroRuntimeException("reflectDatumReader does not use addToArray");
   }

   protected Object readArray(Object old, Schema expected, ResolvingDecoder in) throws IOException {
      Schema expectedType = expected.getElementType();
      long l = in.readArrayStart();
      if (l <= 0L) {
         return this.newArray(old, 0, expected);
      } else {
         Object array = this.newArray(old, (int)l, expected);
         if (array instanceof Collection) {
            Collection<Object> c = (Collection)array;
            return this.readCollection(c, expectedType, l, in);
         } else if (!(array instanceof Map)) {
            return this.readJavaArray(array, expectedType, l, in);
         } else if (!ReflectData.isNonStringMapSchema(expected)) {
            String msg = "Expected a schema of map with non-string keys but got " + String.valueOf(expected);
            throw new AvroRuntimeException(msg);
         } else {
            Collection<Object> c = new ArrayList();
            this.readCollection(c, expectedType, l, in);
            Map m = (Map)array;

            for(Object ele : c) {
               IndexedRecord rec = (IndexedRecord)ele;
               Object key = rec.get(0);
               Object value = rec.get(1);
               m.put(key, value);
            }

            return array;
         }
      }
   }

   private Object readJavaArray(Object array, Schema expectedType, long l, ResolvingDecoder in) throws IOException {
      Class<?> elementType = array.getClass().getComponentType();
      return elementType.isPrimitive() ? this.readPrimitiveArray(array, elementType, l, in) : this.readObjectArray(array, expectedType, l, in);
   }

   private Object readPrimitiveArray(Object array, Class c, long l, ResolvingDecoder in) throws IOException {
      return ArrayAccessor.readArray(array, c, l, in);
   }

   private Object readObjectArray(Object[] array, Schema expectedType, long l, ResolvingDecoder in) throws IOException {
      LogicalType logicalType = expectedType.getLogicalType();
      Conversion<?> conversion = this.getData().getConversionFor(logicalType);
      int index = 0;
      if (logicalType != null && conversion != null) {
         do {
            for(int limit = index + (int)l; index < limit; ++index) {
               Object element = this.readWithConversion((Object)null, expectedType, logicalType, conversion, in);
               array[index] = element;
            }
         } while((l = in.arrayNext()) > 0L);
      } else {
         do {
            for(int limit = index + (int)l; index < limit; ++index) {
               Object element = this.readWithoutConversion((Object)null, expectedType, in);
               array[index] = element;
            }
         } while((l = in.arrayNext()) > 0L);
      }

      return array;
   }

   private Object readCollection(Collection c, Schema expectedType, long l, ResolvingDecoder in) throws IOException {
      LogicalType logicalType = expectedType.getLogicalType();
      Conversion<?> conversion = this.getData().getConversionFor(logicalType);
      if (logicalType != null && conversion != null) {
         do {
            for(int i = 0; (long)i < l; ++i) {
               Object element = this.readWithConversion((Object)null, expectedType, logicalType, conversion, in);
               c.add(element);
            }
         } while((l = in.arrayNext()) > 0L);
      } else {
         do {
            for(int i = 0; (long)i < l; ++i) {
               Object element = this.readWithoutConversion((Object)null, expectedType, in);
               c.add(element);
            }
         } while((l = in.arrayNext()) > 0L);
      }

      return c;
   }

   protected Object readString(Object old, Decoder in) throws IOException {
      return super.readString((Object)null, in).toString();
   }

   protected Object createString(String value) {
      return value;
   }

   protected Object readBytes(Object old, Schema s, Decoder in) throws IOException {
      ByteBuffer bytes = in.readBytes((ByteBuffer)null);
      Class<?> c = ReflectData.getClassProp(s, "java-class");
      if (c != null && c.isArray()) {
         byte[] result = new byte[bytes.remaining()];
         bytes.get(result);
         return result;
      } else {
         return bytes;
      }
   }

   protected Object readInt(Object old, Schema expected, Decoder in) throws IOException {
      Object value = in.readInt();
      String intClass = expected.getProp("java-class");
      if (Byte.class.getName().equals(intClass)) {
         value = ((Integer)value).byteValue();
      } else if (Short.class.getName().equals(intClass)) {
         value = ((Integer)value).shortValue();
      } else if (Character.class.getName().equals(intClass)) {
         value = (char)(Integer)value;
      }

      return value;
   }

   protected void readField(Object record, Schema.Field field, Object oldDatum, ResolvingDecoder in, Object state) throws IOException {
      if (state != null) {
         FieldAccessor accessor = ((FieldAccessor[])state)[field.pos()];
         if (accessor != null) {
            if (!accessor.supportsIO() || Schema.Type.UNION.equals(field.schema().getType()) && !accessor.isCustomEncoded()) {
               if (accessor.isStringable()) {
                  try {
                     String asString = (String)this.read((Object)null, field.schema(), in);
                     accessor.set(record, asString == null ? null : this.newInstanceFromString(accessor.getField().getType(), asString));
                     return;
                  } catch (Exception e) {
                     throw new AvroRuntimeException("Failed to read Stringable", e);
                  }
               }

               LogicalType logicalType = field.schema().getLogicalType();
               if (logicalType != null) {
                  Conversion<?> conversion = this.getData().getConversionByClass(accessor.getField().getType(), logicalType);
                  if (conversion != null) {
                     try {
                        accessor.set(record, this.convert(this.readWithoutConversion(oldDatum, field.schema(), in), field.schema(), logicalType, conversion));
                        return;
                     } catch (IllegalAccessException var11) {
                        throw new AvroRuntimeException("Failed to set " + String.valueOf(field));
                     }
                  }
               }

               if (Optional.class.isAssignableFrom(accessor.getField().getType())) {
                  try {
                     Object value = this.readWithoutConversion(oldDatum, field.schema(), in);
                     accessor.set(record, Optional.ofNullable(value));
                     return;
                  } catch (IllegalAccessException var12) {
                     throw new AvroRuntimeException("Failed to set " + String.valueOf(field));
                  }
               }

               try {
                  accessor.set(record, this.readWithoutConversion(oldDatum, field.schema(), in));
                  return;
               } catch (IllegalAccessException var13) {
                  throw new AvroRuntimeException("Failed to set " + String.valueOf(field));
               }
            }

            accessor.read(record, in);
            return;
         }
      }

      super.readField(record, field, oldDatum, in, state);
   }
}

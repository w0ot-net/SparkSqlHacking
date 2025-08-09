package org.apache.avro.reflect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.io.Encoder;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificDatumWriter;

public class ReflectDatumWriter extends SpecificDatumWriter {
   public ReflectDatumWriter() {
      this(ReflectData.get());
   }

   public ReflectDatumWriter(Class c) {
      this(c, ReflectData.get());
   }

   public ReflectDatumWriter(Class c, ReflectData data) {
      this(data.getSchema(c), data);
   }

   public ReflectDatumWriter(Schema root) {
      this(root, ReflectData.get());
   }

   public ReflectDatumWriter(Schema root, ReflectData reflectData) {
      super(root, reflectData);
   }

   protected ReflectDatumWriter(ReflectData reflectData) {
      super((SpecificData)reflectData);
   }

   protected void writeArray(Schema schema, Object datum, Encoder out) throws IOException {
      if (datum instanceof Collection) {
         super.writeArray(schema, datum, out);
      } else {
         Class<?> elementClass = datum.getClass().getComponentType();
         if (null == elementClass) {
            throw new AvroRuntimeException("Array data must be a Collection or Array");
         } else {
            Schema element = schema.getElementType();
            if (elementClass.isPrimitive()) {
               Schema.Type type = element.getType();
               out.writeArrayStart();
               switch (type) {
                  case BOOLEAN:
                     ArrayAccessor.writeArray((boolean[])datum, out);
                     break;
                  case DOUBLE:
                     ArrayAccessor.writeArray((double[])datum, out);
                     break;
                  case FLOAT:
                     ArrayAccessor.writeArray((float[])datum, out);
                     break;
                  case INT:
                     if (elementClass.equals(Integer.TYPE)) {
                        ArrayAccessor.writeArray((int[])datum, out);
                     } else if (elementClass.equals(Character.TYPE)) {
                        ArrayAccessor.writeArray((char[])datum, out);
                     } else if (elementClass.equals(Short.TYPE)) {
                        ArrayAccessor.writeArray((short[])datum, out);
                     } else {
                        this.arrayError(elementClass, type);
                     }
                     break;
                  case LONG:
                     ArrayAccessor.writeArray((long[])datum, out);
                     break;
                  default:
                     this.arrayError(elementClass, type);
               }

               out.writeArrayEnd();
            } else {
               out.writeArrayStart();
               this.writeObjectArray(element, datum, out);
               out.writeArrayEnd();
            }

         }
      }
   }

   private void writeObjectArray(Schema element, Object[] data, Encoder out) throws IOException {
      int size = data.length;
      out.setItemCount((long)size);

      for(Object datum : data) {
         this.write(element, datum, out);
      }

   }

   private void arrayError(Class cl, Schema.Type type) {
      String var10002 = String.valueOf(cl);
      throw new AvroRuntimeException("Error writing array with inner type " + var10002 + " and avro type: " + String.valueOf(type));
   }

   protected void writeBytes(Object datum, Encoder out) throws IOException {
      if (datum instanceof byte[]) {
         out.writeBytes((byte[])datum);
      } else {
         super.writeBytes(datum, out);
      }

   }

   protected void write(Schema schema, Object datum, Encoder out) throws IOException {
      if (datum instanceof Byte) {
         datum = ((Byte)datum).intValue();
      } else if (datum instanceof Short) {
         datum = ((Short)datum).intValue();
      } else if (datum instanceof Character) {
         datum = Integer.valueOf((Character)datum);
      } else if (datum instanceof Map && ReflectData.isNonStringMapSchema(schema)) {
         Set entries = ((Map)datum).entrySet();
         List<Map.Entry> entryList = new ArrayList(entries.size());

         for(Object obj : ((Map)datum).entrySet()) {
            Map.Entry e = (Map.Entry)obj;
            entryList.add(new org.apache.avro.util.MapEntry(e.getKey(), e.getValue()));
         }

         datum = entryList;
      } else if (datum instanceof Optional) {
         datum = ((Optional)datum).orElse((Object)null);
      }

      try {
         super.write(schema, datum, out);
      } catch (NullPointerException e) {
         throw this.npe(e, " in " + schema.getFullName());
      }
   }

   protected void writeField(Object record, Schema.Field f, Encoder out, Object state) throws IOException {
      if (state != null) {
         FieldAccessor accessor = ((FieldAccessor[])state)[f.pos()];
         if (accessor != null) {
            if (accessor.supportsIO() && (!Schema.Type.UNION.equals(f.schema().getType()) || accessor.isCustomEncoded())) {
               accessor.write(record, out);
               return;
            }

            if (accessor.isStringable()) {
               try {
                  Object object = accessor.get(record);
                  this.write(f.schema(), object == null ? null : object.toString(), out);
                  return;
               } catch (IllegalAccessException e) {
                  throw new AvroRuntimeException("Failed to write Stringable", e);
               }
            }
         }
      }

      super.writeField(record, f, out, state);
   }
}

package org.apache.avro.reflect;

import java.io.IOException;
import java.lang.reflect.Field;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;

class FieldAccessReflect extends FieldAccess {
   protected FieldAccessor getAccessor(Field field) {
      AvroEncode enc = (AvroEncode)field.getAnnotation(AvroEncode.class);
      if (enc != null) {
         try {
            return new ReflectionBasesAccessorCustomEncoded(field, (CustomEncoding)enc.using().getDeclaredConstructor().newInstance());
         } catch (Exception var4) {
            throw new AvroRuntimeException("Could not instantiate custom Encoding");
         }
      } else {
         return new ReflectionBasedAccessor(field);
      }
   }

   private static class ReflectionBasedAccessor extends FieldAccessor {
      protected final Field field;
      private boolean isStringable;
      private boolean isCustomEncoded;

      public ReflectionBasedAccessor(Field field) {
         this.field = field;
         this.field.setAccessible(true);
         this.isStringable = field.isAnnotationPresent(Stringable.class);
         this.isCustomEncoded = field.isAnnotationPresent(AvroEncode.class);
      }

      public String toString() {
         return this.field.getName();
      }

      public Object get(Object object) throws IllegalAccessException {
         return this.field.get(object);
      }

      public void set(Object object, Object value) throws IllegalAccessException, IOException {
         if (value == null && this.field.getType().isPrimitive()) {
            Object defaultValue = null;
            if (Integer.TYPE.equals(this.field.getType())) {
               defaultValue = 0;
            } else if (Float.TYPE.equals(this.field.getType())) {
               defaultValue = 0.0F;
            } else if (Short.TYPE.equals(this.field.getType())) {
               defaultValue = Short.valueOf((short)0);
            } else if (Byte.TYPE.equals(this.field.getType())) {
               defaultValue = 0;
            } else if (Boolean.TYPE.equals(this.field.getType())) {
               defaultValue = false;
            } else if (Character.TYPE.equals(this.field.getType())) {
               defaultValue = '\u0000';
            } else if (Long.TYPE.equals(this.field.getType())) {
               defaultValue = 0L;
            } else if (Double.TYPE.equals(this.field.getType())) {
               defaultValue = (double)0.0F;
            }

            this.field.set(object, defaultValue);
         } else {
            this.field.set(object, value);
         }

      }

      protected Field getField() {
         return this.field;
      }

      protected boolean isStringable() {
         return this.isStringable;
      }

      protected boolean isCustomEncoded() {
         return this.isCustomEncoded;
      }
   }

   private static final class ReflectionBasesAccessorCustomEncoded extends ReflectionBasedAccessor {
      private CustomEncoding encoding;

      public ReflectionBasesAccessorCustomEncoded(Field f, CustomEncoding encoding) {
         super(f);
         this.encoding = encoding;
      }

      protected void read(Object object, Decoder in) throws IOException {
         try {
            this.field.set(object, this.encoding.read(in));
         } catch (IllegalAccessException e) {
            throw new AvroRuntimeException(e);
         }
      }

      protected void write(Object object, Encoder out) throws IOException {
         try {
            this.encoding.write(this.field.get(object), out);
         } catch (IllegalAccessException e) {
            throw new AvroRuntimeException(e);
         }
      }

      protected boolean isCustomEncoded() {
         return true;
      }

      protected boolean supportsIO() {
         return true;
      }
   }
}

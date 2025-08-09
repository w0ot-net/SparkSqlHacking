package org.apache.avro.reflect;

import java.io.IOException;
import java.lang.reflect.Field;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;

abstract class FieldAccessor {
   protected abstract Object get(Object object) throws IllegalAccessException;

   protected abstract void set(Object object, Object value) throws IllegalAccessException, IOException;

   protected void read(Object object, Decoder in) throws IOException {
   }

   protected void write(Object object, Encoder out) throws IOException {
   }

   protected boolean supportsIO() {
      return false;
   }

   protected abstract Field getField();

   protected boolean isStringable() {
      return false;
   }

   protected boolean isCustomEncoded() {
      return false;
   }
}

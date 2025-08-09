package io.jsonwebtoken.io;

import io.jsonwebtoken.lang.Objects;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public abstract class AbstractSerializer implements Serializer {
   protected AbstractSerializer() {
   }

   public final byte[] serialize(Object t) throws SerializationException {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      this.serialize(t, out);
      return out.toByteArray();
   }

   public final void serialize(Object t, OutputStream out) throws SerializationException {
      try {
         this.doSerialize(t, out);
      } catch (Throwable e) {
         if (e instanceof SerializationException) {
            throw (SerializationException)e;
         } else {
            String msg = "Unable to serialize object of type " + Objects.nullSafeClassName(t) + ": " + e.getMessage();
            throw new SerializationException(msg, e);
         }
      }
   }

   protected abstract void doSerialize(Object var1, OutputStream var2) throws Exception;
}

package io.jsonwebtoken.io;

import io.jsonwebtoken.lang.Assert;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public abstract class AbstractDeserializer implements Deserializer {
   protected static final int EOF = -1;
   private static final byte[] EMPTY_BYTES = new byte[0];

   protected AbstractDeserializer() {
   }

   public final Object deserialize(byte[] bytes) throws DeserializationException {
      bytes = bytes == null ? EMPTY_BYTES : bytes;
      InputStream in = new ByteArrayInputStream(bytes);
      Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
      return this.deserialize(reader);
   }

   public final Object deserialize(Reader reader) throws DeserializationException {
      Assert.notNull(reader, "Reader argument cannot be null.");

      try {
         return this.doDeserialize(reader);
      } catch (Throwable t) {
         if (t instanceof DeserializationException) {
            throw (DeserializationException)t;
         } else {
            String msg = "Unable to deserialize: " + t.getMessage();
            throw new DeserializationException(msg, t);
         }
      }
   }

   protected abstract Object doDeserialize(Reader var1) throws Exception;
}

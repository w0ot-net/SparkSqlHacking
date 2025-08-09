package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.io.AbstractSerializer;
import io.jsonwebtoken.io.SerializationException;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.lang.Assert;
import java.io.OutputStream;
import java.util.Map;

public class NamedSerializer extends AbstractSerializer {
   private final String name;
   private final Serializer DELEGATE;

   public NamedSerializer(String name, Serializer serializer) {
      this.DELEGATE = (Serializer)Assert.notNull(serializer, "JSON Serializer cannot be null.");
      this.name = (String)Assert.hasText(name, "Name cannot be null or empty.");
   }

   protected void doSerialize(Map m, OutputStream out) throws SerializationException {
      try {
         this.DELEGATE.serialize(m, out);
      } catch (Throwable t) {
         String msg = String.format("Cannot serialize %s to JSON. Cause: %s", this.name, t.getMessage());
         throw new SerializationException(msg, t);
      }
   }
}

package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.JsonObjectDeserializer;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.security.MalformedKeySetException;

public class JwkSetDeserializer extends JsonObjectDeserializer {
   public JwkSetDeserializer(Deserializer deserializer) {
      super(deserializer, "JWK Set");
   }

   protected RuntimeException malformed(Throwable t) {
      String msg = "Malformed JWK Set JSON: " + t.getMessage();
      throw new MalformedKeySetException(msg, t);
   }
}

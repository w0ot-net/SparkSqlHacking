package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.JsonObjectDeserializer;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.security.MalformedKeyException;

public class JwkDeserializer extends JsonObjectDeserializer {
   public JwkDeserializer(Deserializer deserializer) {
      super(deserializer, "JWK");
   }

   protected RuntimeException malformed(Throwable t) {
      String msg = "Malformed JWK JSON: " + t.getMessage();
      return new MalformedKeyException(msg);
   }
}

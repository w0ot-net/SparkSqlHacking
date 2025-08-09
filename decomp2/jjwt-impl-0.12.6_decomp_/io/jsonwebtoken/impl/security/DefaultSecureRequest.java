package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.SecureRequest;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;

public class DefaultSecureRequest extends DefaultRequest implements SecureRequest {
   private final Key KEY;

   public DefaultSecureRequest(Object payload, Provider provider, SecureRandom secureRandom, Key key) {
      super(payload, provider, secureRandom);
      this.KEY = (Key)Assert.notNull(key, "key cannot be null.");
   }

   public Key getKey() {
      return this.KEY;
   }
}

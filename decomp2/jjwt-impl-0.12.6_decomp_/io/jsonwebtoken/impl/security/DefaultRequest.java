package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.security.Request;
import java.security.Provider;
import java.security.SecureRandom;

public class DefaultRequest extends DefaultMessage implements Request {
   private final Provider provider;
   private final SecureRandom secureRandom;

   public DefaultRequest(Object payload, Provider provider, SecureRandom secureRandom) {
      super(payload);
      this.provider = provider;
      this.secureRandom = secureRandom;
   }

   public Provider getProvider() {
      return this.provider;
   }

   public SecureRandom getSecureRandom() {
      return this.secureRandom;
   }
}

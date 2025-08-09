package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.security.SecurityBuilder;
import java.security.Provider;
import java.security.SecureRandom;

abstract class AbstractSecurityBuilder implements SecurityBuilder {
   protected Provider provider;
   protected SecureRandom random;

   protected final SecurityBuilder self() {
      return this;
   }

   public SecurityBuilder provider(Provider provider) {
      this.provider = provider;
      return this.self();
   }

   public SecurityBuilder random(SecureRandom random) {
      this.random = random != null ? random : Randoms.secureRandom();
      return this.self();
   }
}

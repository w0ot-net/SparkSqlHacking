package io.jsonwebtoken.impl.security;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class RandomSecretKeyBuilder extends DefaultSecretKeyBuilder {
   public RandomSecretKeyBuilder(String jcaName, int bitLength) {
      super(jcaName, bitLength);
   }

   public SecretKey build() {
      byte[] bytes = new byte[this.BIT_LENGTH / 8];
      this.random.nextBytes(bytes);
      return new SecretKeySpec(bytes, this.JCA_NAME);
   }
}

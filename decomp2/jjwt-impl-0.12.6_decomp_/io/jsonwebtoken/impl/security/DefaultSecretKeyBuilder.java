package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.SecretKeyBuilder;
import javax.crypto.SecretKey;

public class DefaultSecretKeyBuilder extends AbstractSecurityBuilder implements SecretKeyBuilder {
   protected final String JCA_NAME;
   protected final int BIT_LENGTH;

   public DefaultSecretKeyBuilder(String jcaName, int bitLength) {
      this.JCA_NAME = (String)Assert.hasText(jcaName, "jcaName cannot be null or empty.");
      if (bitLength % 8 != 0) {
         String msg = "bitLength must be an even multiple of 8";
         throw new IllegalArgumentException(msg);
      } else {
         this.BIT_LENGTH = (Integer)Assert.gt(bitLength, 0, "bitLength must be > 0");
         this.random(Randoms.secureRandom());
      }
   }

   public SecretKey build() {
      JcaTemplate template = new JcaTemplate(this.JCA_NAME, this.provider, this.random);
      return template.generateSecretKey(this.BIT_LENGTH);
   }
}

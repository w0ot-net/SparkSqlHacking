package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.KeyPairBuilder;
import java.security.KeyPair;
import java.security.spec.AlgorithmParameterSpec;

public class DefaultKeyPairBuilder extends AbstractSecurityBuilder implements KeyPairBuilder {
   private final String jcaName;
   private final int bitLength;
   private final AlgorithmParameterSpec params;

   public DefaultKeyPairBuilder(String jcaName) {
      this.jcaName = (String)Assert.hasText(jcaName, "jcaName cannot be null or empty.");
      this.bitLength = 0;
      this.params = null;
   }

   public DefaultKeyPairBuilder(String jcaName, int bitLength) {
      this.jcaName = (String)Assert.hasText(jcaName, "jcaName cannot be null or empty.");
      this.bitLength = (Integer)Assert.gt(bitLength, 0, "bitLength must be a positive integer greater than 0");
      this.params = null;
   }

   public DefaultKeyPairBuilder(String jcaName, AlgorithmParameterSpec params) {
      this.jcaName = (String)Assert.hasText(jcaName, "jcaName cannot be null or empty.");
      this.params = (AlgorithmParameterSpec)Assert.notNull(params, "AlgorithmParameterSpec params cannot be null.");
      this.bitLength = 0;
   }

   public KeyPair build() {
      JcaTemplate template = new JcaTemplate(this.jcaName, this.provider, this.random);
      if (this.params != null) {
         return template.generateKeyPair(this.params);
      } else {
         return this.bitLength > 0 ? template.generateKeyPair(this.bitLength) : template.generateKeyPair();
      }
   }
}

package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.KeySupplier;
import java.security.PrivateKey;
import java.security.interfaces.ECKey;
import java.security.spec.ECParameterSpec;

public class PrivateECKey implements PrivateKey, ECKey, KeySupplier {
   private final PrivateKey privateKey;
   private final ECParameterSpec params;

   public PrivateECKey(PrivateKey privateKey, ECParameterSpec params) {
      this.privateKey = (PrivateKey)Assert.notNull(privateKey, "PrivateKey cannot be null.");
      this.params = (ECParameterSpec)Assert.notNull(params, "ECParameterSpec cannot be null.");
   }

   public String getAlgorithm() {
      return this.privateKey.getAlgorithm();
   }

   public String getFormat() {
      return this.privateKey.getFormat();
   }

   public byte[] getEncoded() {
      return this.privateKey.getEncoded();
   }

   public ECParameterSpec getParams() {
      return this.params;
   }

   public PrivateKey getKey() {
      return this.privateKey;
   }
}

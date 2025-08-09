package io.jsonwebtoken.impl.security;

public class DefaultKeyUseStrategy implements KeyUseStrategy {
   static final KeyUseStrategy INSTANCE = new DefaultKeyUseStrategy();
   private static final String SIGNATURE = "sig";
   private static final String ENCRYPTION = "enc";

   public String toJwkValue(KeyUsage usage) {
      if (!usage.isKeyEncipherment() && !usage.isDataEncipherment() && !usage.isKeyAgreement()) {
         return !usage.isDigitalSignature() && !usage.isNonRepudiation() && !usage.isKeyCertSign() && !usage.isCRLSign() ? null : "sig";
      } else {
         return "enc";
      }
   }
}

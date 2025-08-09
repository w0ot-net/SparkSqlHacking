package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class DefaultKeyPair implements KeyPair {
   private final PublicKey publicKey;
   private final PrivateKey privateKey;
   private final java.security.KeyPair jdkPair;

   public DefaultKeyPair(PublicKey publicKey, PrivateKey privateKey) {
      this.publicKey = (PublicKey)Assert.notNull(publicKey, "PublicKey argument cannot be null.");
      this.privateKey = (PrivateKey)Assert.notNull(privateKey, "PrivateKey argument cannot be null.");
      this.jdkPair = new java.security.KeyPair(this.publicKey, this.privateKey);
   }

   public PublicKey getPublic() {
      return this.publicKey;
   }

   public PrivateKey getPrivate() {
      return this.privateKey;
   }

   public java.security.KeyPair toJavaKeyPair() {
      return this.jdkPair;
   }
}

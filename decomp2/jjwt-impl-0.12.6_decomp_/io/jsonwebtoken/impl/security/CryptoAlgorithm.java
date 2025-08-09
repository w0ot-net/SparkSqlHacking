package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.Identifiable;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.KeyRequest;
import io.jsonwebtoken.security.Request;
import io.jsonwebtoken.security.SecretKeyBuilder;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.SecretKey;

abstract class CryptoAlgorithm implements Identifiable {
   private final String ID;
   private final String jcaName;

   CryptoAlgorithm(String id, String jcaName) {
      Assert.hasText(id, "id cannot be null or empty.");
      this.ID = id;
      Assert.hasText(jcaName, "jcaName cannot be null or empty.");
      this.jcaName = jcaName;
   }

   public String getId() {
      return this.ID;
   }

   String getJcaName() {
      return this.jcaName;
   }

   static SecureRandom ensureSecureRandom(Request request) {
      SecureRandom random = request != null ? request.getSecureRandom() : null;
      return random != null ? random : Randoms.secureRandom();
   }

   protected JcaTemplate jca() {
      return new JcaTemplate(this.getJcaName());
   }

   protected JcaTemplate jca(Request request) {
      Assert.notNull(request, "request cannot be null.");
      String jcaName = (String)Assert.hasText(this.getJcaName(request), "Request jcaName cannot be null or empty.");
      Provider provider = request.getProvider();
      SecureRandom random = ensureSecureRandom(request);
      return new JcaTemplate(jcaName, provider, random);
   }

   protected String getJcaName(Request request) {
      return this.getJcaName();
   }

   protected SecretKey generateCek(KeyRequest request) {
      AeadAlgorithm enc = (AeadAlgorithm)Assert.notNull(request.getEncryptionAlgorithm(), "Request encryptionAlgorithm cannot be null.");
      SecretKeyBuilder builder = (SecretKeyBuilder)Assert.notNull(enc.key(), "Request encryptionAlgorithm KeyBuilder cannot be null.");
      SecretKey key = (SecretKey)((SecretKeyBuilder)builder.random(request.getSecureRandom())).build();
      return (SecretKey)Assert.notNull(key, "Request encryptionAlgorithm SecretKeyBuilder cannot produce null keys.");
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof CryptoAlgorithm)) {
         return false;
      } else {
         CryptoAlgorithm other = (CryptoAlgorithm)obj;
         return this.ID.equals(other.getId()) && this.jcaName.equals(other.getJcaName());
      }
   }

   public int hashCode() {
      int hash = 7;
      hash = 31 * hash + this.ID.hashCode();
      hash = 31 * hash + this.jcaName.hashCode();
      return hash;
   }

   public String toString() {
      return this.ID;
   }
}

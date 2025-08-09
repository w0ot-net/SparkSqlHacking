package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.VerifySecureDigestRequest;
import java.io.InputStream;
import java.security.Key;

final class NoneSignatureAlgorithm implements SecureDigestAlgorithm {
   private static final String ID = "none";
   static final SecureDigestAlgorithm INSTANCE = new NoneSignatureAlgorithm();

   private NoneSignatureAlgorithm() {
   }

   public String getId() {
      return "none";
   }

   public byte[] digest(SecureRequest request) throws SecurityException {
      throw new SignatureException("The 'none' algorithm cannot be used to create signatures.");
   }

   public boolean verify(VerifySecureDigestRequest request) throws SignatureException {
      throw new SignatureException("The 'none' algorithm cannot be used to verify signatures.");
   }

   public boolean equals(Object obj) {
      return this == obj || obj instanceof SecureDigestAlgorithm && "none".equalsIgnoreCase(((SecureDigestAlgorithm)obj).getId());
   }

   public int hashCode() {
      return this.getId().hashCode();
   }

   public String toString() {
      return "none";
   }
}

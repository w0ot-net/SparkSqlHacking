package com.google.crypto.tink.jwt;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.util.SecretBigInteger;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.RestrictedApi;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Optional;

public final class JwtRsaSsaPssPrivateKey extends JwtSignaturePrivateKey {
   private final JwtRsaSsaPssPublicKey publicKey;
   private final SecretBigInteger d;
   private final SecretBigInteger p;
   private final SecretBigInteger q;
   private final SecretBigInteger dP;
   private final SecretBigInteger dQ;
   private final SecretBigInteger qInv;

   private JwtRsaSsaPssPrivateKey(JwtRsaSsaPssPublicKey publicKey, SecretBigInteger p, SecretBigInteger q, SecretBigInteger d, SecretBigInteger dP, SecretBigInteger dQ, SecretBigInteger qInv) {
      this.publicKey = publicKey;
      this.p = p;
      this.q = q;
      this.d = d;
      this.dP = dP;
      this.dQ = dQ;
      this.qInv = qInv;
   }

   @RestrictedApi(
      explanation = "Accessing parts of keys can produce unexpected incompatibilities, annotate the function with @AccessesPartialKey",
      link = "https://developers.google.com/tink/design/access_control#accessing_partial_keys",
      allowedOnPath = ".*Test\\.java",
      allowlistAnnotations = {AccessesPartialKey.class}
   )
   public static Builder builder() {
      return new Builder();
   }

   public JwtRsaSsaPssParameters getParameters() {
      return this.publicKey.getParameters();
   }

   public JwtRsaSsaPssPublicKey getPublicKey() {
      return this.publicKey;
   }

   @RestrictedApi(
      explanation = "Accessing parts of keys can produce unexpected incompatibilities, annotate the function with @AccessesPartialKey",
      link = "https://developers.google.com/tink/design/access_control#accessing_partial_keys",
      allowedOnPath = ".*Test\\.java",
      allowlistAnnotations = {AccessesPartialKey.class}
   )
   public SecretBigInteger getPrimeP() {
      return this.p;
   }

   @RestrictedApi(
      explanation = "Accessing parts of keys can produce unexpected incompatibilities, annotate the function with @AccessesPartialKey",
      link = "https://developers.google.com/tink/design/access_control#accessing_partial_keys",
      allowedOnPath = ".*Test\\.java",
      allowlistAnnotations = {AccessesPartialKey.class}
   )
   public SecretBigInteger getPrimeQ() {
      return this.q;
   }

   public SecretBigInteger getPrivateExponent() {
      return this.d;
   }

   public SecretBigInteger getPrimeExponentP() {
      return this.dP;
   }

   public SecretBigInteger getPrimeExponentQ() {
      return this.dQ;
   }

   public SecretBigInteger getCrtCoefficient() {
      return this.qInv;
   }

   public boolean equalsKey(Key o) {
      if (!(o instanceof JwtRsaSsaPssPrivateKey)) {
         return false;
      } else {
         JwtRsaSsaPssPrivateKey that = (JwtRsaSsaPssPrivateKey)o;
         return that.publicKey.equalsKey(this.publicKey) && this.p.equalsSecretBigInteger(that.p) && this.q.equalsSecretBigInteger(that.q) && this.d.equalsSecretBigInteger(that.d) && this.dP.equalsSecretBigInteger(that.dP) && this.dQ.equalsSecretBigInteger(that.dQ) && this.qInv.equalsSecretBigInteger(that.qInv);
      }
   }

   public static class Builder {
      private Optional publicKey;
      private Optional d;
      private Optional p;
      private Optional q;
      private Optional dP;
      private Optional dQ;
      private Optional qInv;
      private static final int PRIME_CERTAINTY = 10;

      private Builder() {
         this.publicKey = Optional.empty();
         this.d = Optional.empty();
         this.p = Optional.empty();
         this.q = Optional.empty();
         this.dP = Optional.empty();
         this.dQ = Optional.empty();
         this.qInv = Optional.empty();
      }

      @CanIgnoreReturnValue
      public Builder setPublicKey(JwtRsaSsaPssPublicKey publicKey) {
         this.publicKey = Optional.of(publicKey);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setPrimes(SecretBigInteger p, SecretBigInteger q) {
         this.p = Optional.of(p);
         this.q = Optional.of(q);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setPrivateExponent(SecretBigInteger d) {
         this.d = Optional.of(d);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setPrimeExponents(SecretBigInteger dP, SecretBigInteger dQ) {
         this.dP = Optional.of(dP);
         this.dQ = Optional.of(dQ);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setCrtCoefficient(SecretBigInteger qInv) {
         this.qInv = Optional.of(qInv);
         return this;
      }

      @AccessesPartialKey
      public JwtRsaSsaPssPrivateKey build() throws GeneralSecurityException {
         if (!this.publicKey.isPresent()) {
            throw new GeneralSecurityException("Cannot build without a RSA SSA PSS public key");
         } else if (this.p.isPresent() && this.q.isPresent()) {
            if (!this.d.isPresent()) {
               throw new GeneralSecurityException("Cannot build without private exponent");
            } else if (this.dP.isPresent() && this.dQ.isPresent()) {
               if (!this.qInv.isPresent()) {
                  throw new GeneralSecurityException("Cannot build without CRT coefficient");
               } else {
                  BigInteger e = ((JwtRsaSsaPssPublicKey)this.publicKey.get()).getParameters().getPublicExponent();
                  BigInteger n = ((JwtRsaSsaPssPublicKey)this.publicKey.get()).getModulus();
                  BigInteger ip = ((SecretBigInteger)this.p.get()).getBigInteger(InsecureSecretKeyAccess.get());
                  BigInteger iq = ((SecretBigInteger)this.q.get()).getBigInteger(InsecureSecretKeyAccess.get());
                  BigInteger id = ((SecretBigInteger)this.d.get()).getBigInteger(InsecureSecretKeyAccess.get());
                  BigInteger idP = ((SecretBigInteger)this.dP.get()).getBigInteger(InsecureSecretKeyAccess.get());
                  BigInteger idQ = ((SecretBigInteger)this.dQ.get()).getBigInteger(InsecureSecretKeyAccess.get());
                  BigInteger iqInv = ((SecretBigInteger)this.qInv.get()).getBigInteger(InsecureSecretKeyAccess.get());
                  if (!ip.isProbablePrime(10)) {
                     throw new GeneralSecurityException("p is not a prime");
                  } else if (!iq.isProbablePrime(10)) {
                     throw new GeneralSecurityException("q is not a prime");
                  } else if (!ip.multiply(iq).equals(n)) {
                     throw new GeneralSecurityException("Prime p times prime q is not equal to the public key's modulus");
                  } else {
                     BigInteger pMinusOne = ip.subtract(BigInteger.ONE);
                     BigInteger qMinusOne = iq.subtract(BigInteger.ONE);
                     BigInteger lambda = pMinusOne.divide(pMinusOne.gcd(qMinusOne)).multiply(qMinusOne);
                     if (!e.multiply(id).mod(lambda).equals(BigInteger.ONE)) {
                        throw new GeneralSecurityException("D is invalid.");
                     } else if (!e.multiply(idP).mod(pMinusOne).equals(BigInteger.ONE)) {
                        throw new GeneralSecurityException("dP is invalid.");
                     } else if (!e.multiply(idQ).mod(qMinusOne).equals(BigInteger.ONE)) {
                        throw new GeneralSecurityException("dQ is invalid.");
                     } else if (!iq.multiply(iqInv).mod(ip).equals(BigInteger.ONE)) {
                        throw new GeneralSecurityException("qInv is invalid.");
                     } else {
                        return new JwtRsaSsaPssPrivateKey((JwtRsaSsaPssPublicKey)this.publicKey.get(), (SecretBigInteger)this.p.get(), (SecretBigInteger)this.q.get(), (SecretBigInteger)this.d.get(), (SecretBigInteger)this.dP.get(), (SecretBigInteger)this.dQ.get(), (SecretBigInteger)this.qInv.get());
                     }
                  }
               }
            } else {
               throw new GeneralSecurityException("Cannot build without prime exponents");
            }
         } else {
            throw new GeneralSecurityException("Cannot build without prime factors");
         }
      }
   }
}

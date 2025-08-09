package com.google.crypto.tink.jwt;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.subtle.Base64;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.RestrictedApi;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Optional;
import javax.annotation.Nullable;

public final class JwtRsaSsaPkcs1PublicKey extends JwtSignaturePublicKey {
   private final JwtRsaSsaPkcs1Parameters parameters;
   private final BigInteger modulus;
   private final Optional idRequirement;
   private final Optional kid;

   private JwtRsaSsaPkcs1PublicKey(JwtRsaSsaPkcs1Parameters parameters, BigInteger modulus, Optional idRequirement, Optional kid) {
      this.parameters = parameters;
      this.modulus = modulus;
      this.idRequirement = idRequirement;
      this.kid = kid;
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

   @RestrictedApi(
      explanation = "Accessing parts of keys can produce unexpected incompatibilities, annotate the function with @AccessesPartialKey",
      link = "https://developers.google.com/tink/design/access_control#accessing_partial_keys",
      allowedOnPath = ".*Test\\.java",
      allowlistAnnotations = {AccessesPartialKey.class}
   )
   public BigInteger getModulus() {
      return this.modulus;
   }

   public Optional getKid() {
      return this.kid;
   }

   public JwtRsaSsaPkcs1Parameters getParameters() {
      return this.parameters;
   }

   @Nullable
   public Integer getIdRequirementOrNull() {
      return (Integer)this.idRequirement.orElse((Object)null);
   }

   public boolean equalsKey(Key o) {
      if (!(o instanceof JwtRsaSsaPkcs1PublicKey)) {
         return false;
      } else {
         JwtRsaSsaPkcs1PublicKey that = (JwtRsaSsaPkcs1PublicKey)o;
         return that.parameters.equals(this.parameters) && that.modulus.equals(this.modulus) && that.kid.equals(this.kid) && that.idRequirement.equals(this.idRequirement);
      }
   }

   public static class Builder {
      private Optional parameters;
      private Optional modulus;
      private Optional idRequirement;
      private Optional customKid;

      private Builder() {
         this.parameters = Optional.empty();
         this.modulus = Optional.empty();
         this.idRequirement = Optional.empty();
         this.customKid = Optional.empty();
      }

      @CanIgnoreReturnValue
      public Builder setParameters(JwtRsaSsaPkcs1Parameters parameters) {
         this.parameters = Optional.of(parameters);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setModulus(BigInteger modulus) {
         this.modulus = Optional.of(modulus);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setIdRequirement(Integer idRequirement) {
         this.idRequirement = Optional.of(idRequirement);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setCustomKid(String customKid) {
         this.customKid = Optional.of(customKid);
         return this;
      }

      private Optional computeKid() throws GeneralSecurityException {
         if (((JwtRsaSsaPkcs1Parameters)this.parameters.get()).getKidStrategy().equals(JwtRsaSsaPkcs1Parameters.KidStrategy.BASE64_ENCODED_KEY_ID)) {
            if (this.customKid.isPresent()) {
               throw new GeneralSecurityException("customKid must not be set for KidStrategy BASE64_ENCODED_KEY_ID");
            } else {
               byte[] bigEndianKeyId = ByteBuffer.allocate(4).putInt((Integer)this.idRequirement.get()).array();
               return Optional.of(Base64.urlSafeEncode(bigEndianKeyId));
            }
         } else if (((JwtRsaSsaPkcs1Parameters)this.parameters.get()).getKidStrategy().equals(JwtRsaSsaPkcs1Parameters.KidStrategy.CUSTOM)) {
            if (!this.customKid.isPresent()) {
               throw new GeneralSecurityException("customKid needs to be set for KidStrategy CUSTOM");
            } else {
               return this.customKid;
            }
         } else if (((JwtRsaSsaPkcs1Parameters)this.parameters.get()).getKidStrategy().equals(JwtRsaSsaPkcs1Parameters.KidStrategy.IGNORED)) {
            if (this.customKid.isPresent()) {
               throw new GeneralSecurityException("customKid must not be set for KidStrategy IGNORED");
            } else {
               return Optional.empty();
            }
         } else {
            throw new IllegalStateException("Unknown kid strategy");
         }
      }

      public JwtRsaSsaPkcs1PublicKey build() throws GeneralSecurityException {
         if (!this.parameters.isPresent()) {
            throw new GeneralSecurityException("Cannot build without parameters");
         } else if (!this.modulus.isPresent()) {
            throw new GeneralSecurityException("Cannot build without modulus");
         } else {
            int modulusSize = ((BigInteger)this.modulus.get()).bitLength();
            int paramModulusSize = ((JwtRsaSsaPkcs1Parameters)this.parameters.get()).getModulusSizeBits();
            if (modulusSize != paramModulusSize) {
               throw new GeneralSecurityException("Got modulus size " + modulusSize + ", but parameters requires modulus size " + paramModulusSize);
            } else if (((JwtRsaSsaPkcs1Parameters)this.parameters.get()).hasIdRequirement() && !this.idRequirement.isPresent()) {
               throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (!((JwtRsaSsaPkcs1Parameters)this.parameters.get()).hasIdRequirement() && this.idRequirement.isPresent()) {
               throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            } else {
               return new JwtRsaSsaPkcs1PublicKey((JwtRsaSsaPkcs1Parameters)this.parameters.get(), (BigInteger)this.modulus.get(), this.idRequirement, this.computeKid());
            }
         }
      }
   }
}

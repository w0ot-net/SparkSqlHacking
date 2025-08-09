package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.EllipticCurvesUtil;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.util.Objects;
import java.util.Optional;

public final class JwtEcdsaParameters extends JwtSignatureParameters {
   private final KidStrategy kidStrategy;
   private final Algorithm algorithm;

   public static Builder builder() {
      return new Builder();
   }

   private JwtEcdsaParameters(KidStrategy kidStrategy, Algorithm algorithm) {
      this.kidStrategy = kidStrategy;
      this.algorithm = algorithm;
   }

   public KidStrategy getKidStrategy() {
      return this.kidStrategy;
   }

   public Algorithm getAlgorithm() {
      return this.algorithm;
   }

   public boolean hasIdRequirement() {
      return this.kidStrategy.equals(JwtEcdsaParameters.KidStrategy.BASE64_ENCODED_KEY_ID);
   }

   public boolean allowKidAbsent() {
      return this.kidStrategy.equals(JwtEcdsaParameters.KidStrategy.CUSTOM) || this.kidStrategy.equals(JwtEcdsaParameters.KidStrategy.IGNORED);
   }

   public boolean equals(Object o) {
      if (!(o instanceof JwtEcdsaParameters)) {
         return false;
      } else {
         JwtEcdsaParameters that = (JwtEcdsaParameters)o;
         return that.kidStrategy.equals(this.kidStrategy) && that.algorithm.equals(this.algorithm);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{JwtEcdsaParameters.class, this.kidStrategy, this.algorithm});
   }

   public String toString() {
      return "JWT ECDSA Parameters (kidStrategy: " + this.kidStrategy + ", Algorithm " + this.algorithm + ")";
   }

   @Immutable
   public static final class KidStrategy {
      public static final KidStrategy BASE64_ENCODED_KEY_ID = new KidStrategy("BASE64_ENCODED_KEY_ID");
      public static final KidStrategy IGNORED = new KidStrategy("IGNORED");
      public static final KidStrategy CUSTOM = new KidStrategy("CUSTOM");
      private final String name;

      private KidStrategy(String name) {
         this.name = name;
      }

      public String toString() {
         return this.name;
      }
   }

   @Immutable
   public static final class Algorithm {
      public static final Algorithm ES256;
      public static final Algorithm ES384;
      public static final Algorithm ES512;
      private final String name;
      private final ECParameterSpec ecParameterSpec;

      private Algorithm(String name, ECParameterSpec ecParameterSpec) {
         this.name = name;
         this.ecParameterSpec = ecParameterSpec;
      }

      public String toString() {
         return this.name;
      }

      public String getStandardName() {
         return this.name;
      }

      ECParameterSpec getECParameterSpec() {
         return this.ecParameterSpec;
      }

      static {
         ES256 = new Algorithm("ES256", EllipticCurvesUtil.NIST_P256_PARAMS);
         ES384 = new Algorithm("ES384", EllipticCurvesUtil.NIST_P384_PARAMS);
         ES512 = new Algorithm("ES512", EllipticCurvesUtil.NIST_P521_PARAMS);
      }
   }

   public static final class Builder {
      Optional kidStrategy;
      Optional algorithm;

      @CanIgnoreReturnValue
      public Builder setKidStrategy(KidStrategy kidStrategy) {
         this.kidStrategy = Optional.of(kidStrategy);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setAlgorithm(Algorithm algorithm) {
         this.algorithm = Optional.of(algorithm);
         return this;
      }

      public JwtEcdsaParameters build() throws GeneralSecurityException {
         if (!this.algorithm.isPresent()) {
            throw new GeneralSecurityException("Algorithm must be set");
         } else if (!this.kidStrategy.isPresent()) {
            throw new GeneralSecurityException("KidStrategy must be set");
         } else {
            return new JwtEcdsaParameters((KidStrategy)this.kidStrategy.get(), (Algorithm)this.algorithm.get());
         }
      }

      private Builder() {
         this.kidStrategy = Optional.empty();
         this.algorithm = Optional.empty();
      }
   }
}

package com.google.crypto.tink.signature;

import com.google.crypto.tink.internal.EllipticCurvesUtil;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.util.Objects;

public final class EcdsaParameters extends SignatureParameters {
   private final SignatureEncoding signatureEncoding;
   private final CurveType curveType;
   private final HashType hashType;
   private final Variant variant;

   private EcdsaParameters(SignatureEncoding signatureEncoding, CurveType curveType, HashType hashType, Variant variant) {
      this.signatureEncoding = signatureEncoding;
      this.curveType = curveType;
      this.hashType = hashType;
      this.variant = variant;
   }

   public static Builder builder() {
      return new Builder();
   }

   public SignatureEncoding getSignatureEncoding() {
      return this.signatureEncoding;
   }

   public CurveType getCurveType() {
      return this.curveType;
   }

   public HashType getHashType() {
      return this.hashType;
   }

   public Variant getVariant() {
      return this.variant;
   }

   public boolean equals(Object o) {
      if (!(o instanceof EcdsaParameters)) {
         return false;
      } else {
         EcdsaParameters that = (EcdsaParameters)o;
         return that.getSignatureEncoding() == this.getSignatureEncoding() && that.getCurveType() == this.getCurveType() && that.getHashType() == this.getHashType() && that.getVariant() == this.getVariant();
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{EcdsaParameters.class, this.signatureEncoding, this.curveType, this.hashType, this.variant});
   }

   public boolean hasIdRequirement() {
      return this.variant != EcdsaParameters.Variant.NO_PREFIX;
   }

   public String toString() {
      return "ECDSA Parameters (variant: " + this.variant + ", hashType: " + this.hashType + ", encoding: " + this.signatureEncoding + ", curve: " + this.curveType + ")";
   }

   @Immutable
   public static final class Variant {
      public static final Variant TINK = new Variant("TINK");
      public static final Variant CRUNCHY = new Variant("CRUNCHY");
      public static final Variant LEGACY = new Variant("LEGACY");
      public static final Variant NO_PREFIX = new Variant("NO_PREFIX");
      private final String name;

      private Variant(String name) {
         this.name = name;
      }

      public String toString() {
         return this.name;
      }
   }

   @Immutable
   public static final class SignatureEncoding {
      public static final SignatureEncoding IEEE_P1363 = new SignatureEncoding("IEEE_P1363");
      public static final SignatureEncoding DER = new SignatureEncoding("DER");
      private final String name;

      private SignatureEncoding(String name) {
         this.name = name;
      }

      public String toString() {
         return this.name;
      }
   }

   @Immutable
   public static final class CurveType {
      public static final CurveType NIST_P256;
      public static final CurveType NIST_P384;
      public static final CurveType NIST_P521;
      private final String name;
      private final ECParameterSpec spec;

      private CurveType(String name, ECParameterSpec spec) {
         this.name = name;
         this.spec = spec;
      }

      public String toString() {
         return this.name;
      }

      public ECParameterSpec toParameterSpec() {
         return this.spec;
      }

      public static CurveType fromParameterSpec(ECParameterSpec spec) throws GeneralSecurityException {
         if (EllipticCurvesUtil.isSameEcParameterSpec(spec, NIST_P256.toParameterSpec())) {
            return NIST_P256;
         } else if (EllipticCurvesUtil.isSameEcParameterSpec(spec, NIST_P384.toParameterSpec())) {
            return NIST_P384;
         } else if (EllipticCurvesUtil.isSameEcParameterSpec(spec, NIST_P521.toParameterSpec())) {
            return NIST_P521;
         } else {
            throw new GeneralSecurityException("unknown ECParameterSpec");
         }
      }

      static {
         NIST_P256 = new CurveType("NIST_P256", EllipticCurvesUtil.NIST_P256_PARAMS);
         NIST_P384 = new CurveType("NIST_P384", EllipticCurvesUtil.NIST_P384_PARAMS);
         NIST_P521 = new CurveType("NIST_P521", EllipticCurvesUtil.NIST_P521_PARAMS);
      }
   }

   @Immutable
   public static final class HashType {
      public static final HashType SHA256 = new HashType("SHA256");
      public static final HashType SHA384 = new HashType("SHA384");
      public static final HashType SHA512 = new HashType("SHA512");
      private final String name;

      private HashType(String name) {
         this.name = name;
      }

      public String toString() {
         return this.name;
      }
   }

   public static final class Builder {
      private SignatureEncoding signatureEncoding;
      private CurveType curveType;
      private HashType hashType;
      private Variant variant;

      private Builder() {
         this.signatureEncoding = null;
         this.curveType = null;
         this.hashType = null;
         this.variant = EcdsaParameters.Variant.NO_PREFIX;
      }

      @CanIgnoreReturnValue
      public Builder setSignatureEncoding(SignatureEncoding signatureEncoding) {
         this.signatureEncoding = signatureEncoding;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setCurveType(CurveType curveType) {
         this.curveType = curveType;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setHashType(HashType hashType) {
         this.hashType = hashType;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setVariant(Variant variant) {
         this.variant = variant;
         return this;
      }

      public EcdsaParameters build() throws GeneralSecurityException {
         if (this.signatureEncoding == null) {
            throw new GeneralSecurityException("signature encoding is not set");
         } else if (this.curveType == null) {
            throw new GeneralSecurityException("EC curve type is not set");
         } else if (this.hashType == null) {
            throw new GeneralSecurityException("hash type is not set");
         } else if (this.variant == null) {
            throw new GeneralSecurityException("variant is not set");
         } else if (this.curveType == EcdsaParameters.CurveType.NIST_P256 && this.hashType != EcdsaParameters.HashType.SHA256) {
            throw new GeneralSecurityException("NIST_P256 requires SHA256");
         } else if (this.curveType == EcdsaParameters.CurveType.NIST_P384 && this.hashType != EcdsaParameters.HashType.SHA384 && this.hashType != EcdsaParameters.HashType.SHA512) {
            throw new GeneralSecurityException("NIST_P384 requires SHA384 or SHA512");
         } else if (this.curveType == EcdsaParameters.CurveType.NIST_P521 && this.hashType != EcdsaParameters.HashType.SHA512) {
            throw new GeneralSecurityException("NIST_P521 requires SHA512");
         } else {
            return new EcdsaParameters(this.signatureEncoding, this.curveType, this.hashType, this.variant);
         }
      }
   }
}

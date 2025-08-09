package com.google.crypto.tink.hybrid;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Objects;

public final class HpkeParameters extends HybridParameters {
   private final KemId kem;
   private final KdfId kdf;
   private final AeadId aead;
   private final Variant variant;

   private HpkeParameters(KemId kem, KdfId kdf, AeadId aead, Variant variant) {
      this.kem = kem;
      this.kdf = kdf;
      this.aead = aead;
      this.variant = variant;
   }

   public static Builder builder() {
      return new Builder();
   }

   public KemId getKemId() {
      return this.kem;
   }

   public KdfId getKdfId() {
      return this.kdf;
   }

   public AeadId getAeadId() {
      return this.aead;
   }

   public Variant getVariant() {
      return this.variant;
   }

   public boolean hasIdRequirement() {
      return this.variant != HpkeParameters.Variant.NO_PREFIX;
   }

   public boolean equals(Object o) {
      if (!(o instanceof HpkeParameters)) {
         return false;
      } else {
         HpkeParameters other = (HpkeParameters)o;
         return this.kem == other.kem && this.kdf == other.kdf && this.aead == other.aead && this.variant == other.variant;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{HpkeParameters.class, this.kem, this.kdf, this.aead, this.variant});
   }

   @Immutable
   public static final class Variant {
      public static final Variant TINK = new Variant("TINK");
      public static final Variant CRUNCHY = new Variant("CRUNCHY");
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
   private static class AlgorithmIdentifier {
      protected final String name;
      protected final int value;

      private AlgorithmIdentifier(String name, int value) {
         this.name = name;
         this.value = value;
      }

      public int getValue() {
         return this.value;
      }

      public String toString() {
         return String.format("%s(0x%04x)", this.name, this.value);
      }
   }

   @Immutable
   public static final class KemId extends AlgorithmIdentifier {
      public static final KemId DHKEM_P256_HKDF_SHA256 = new KemId("DHKEM_P256_HKDF_SHA256", 16);
      public static final KemId DHKEM_P384_HKDF_SHA384 = new KemId("DHKEM_P384_HKDF_SHA384", 17);
      public static final KemId DHKEM_P521_HKDF_SHA512 = new KemId("DHKEM_P521_HKDF_SHA512", 18);
      public static final KemId DHKEM_X25519_HKDF_SHA256 = new KemId("DHKEM_X25519_HKDF_SHA256", 32);

      private KemId(String name, int value) {
         super(name, value, null);
      }
   }

   @Immutable
   public static final class KdfId extends AlgorithmIdentifier {
      public static final KdfId HKDF_SHA256 = new KdfId("HKDF_SHA256", 1);
      public static final KdfId HKDF_SHA384 = new KdfId("HKDF_SHA384", 2);
      public static final KdfId HKDF_SHA512 = new KdfId("HKDF_SHA512", 3);

      private KdfId(String name, int value) {
         super(name, value, null);
      }
   }

   @Immutable
   public static final class AeadId extends AlgorithmIdentifier {
      public static final AeadId AES_128_GCM = new AeadId("AES_128_GCM", 1);
      public static final AeadId AES_256_GCM = new AeadId("AES_256_GCM", 2);
      public static final AeadId CHACHA20_POLY1305 = new AeadId("CHACHA20_POLY1305", 3);

      private AeadId(String name, int value) {
         super(name, value, null);
      }
   }

   public static final class Builder {
      private KemId kem;
      private KdfId kdf;
      private AeadId aead;
      private Variant variant;

      private Builder() {
         this.kem = null;
         this.kdf = null;
         this.aead = null;
         this.variant = HpkeParameters.Variant.NO_PREFIX;
      }

      @CanIgnoreReturnValue
      public Builder setKemId(KemId kem) {
         this.kem = kem;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setKdfId(KdfId kdf) {
         this.kdf = kdf;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setAeadId(AeadId aead) {
         this.aead = aead;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setVariant(Variant variant) {
         this.variant = variant;
         return this;
      }

      public HpkeParameters build() throws GeneralSecurityException {
         if (this.kem == null) {
            throw new GeneralSecurityException("HPKE KEM parameter is not set");
         } else if (this.kdf == null) {
            throw new GeneralSecurityException("HPKE KDF parameter is not set");
         } else if (this.aead == null) {
            throw new GeneralSecurityException("HPKE AEAD parameter is not set");
         } else if (this.variant == null) {
            throw new GeneralSecurityException("HPKE variant is not set");
         } else {
            return new HpkeParameters(this.kem, this.kdf, this.aead, this.variant);
         }
      }
   }
}

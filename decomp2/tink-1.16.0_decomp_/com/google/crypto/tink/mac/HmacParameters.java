package com.google.crypto.tink.mac;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class HmacParameters extends MacParameters {
   private final int keySizeBytes;
   private final int tagSizeBytes;
   private final Variant variant;
   private final HashType hashType;

   private HmacParameters(int keySizeBytes, int tagSizeBytes, Variant variant, HashType hashType) {
      this.keySizeBytes = keySizeBytes;
      this.tagSizeBytes = tagSizeBytes;
      this.variant = variant;
      this.hashType = hashType;
   }

   public static Builder builder() {
      return new Builder();
   }

   public int getKeySizeBytes() {
      return this.keySizeBytes;
   }

   public int getCryptographicTagSizeBytes() {
      return this.tagSizeBytes;
   }

   public int getTotalTagSizeBytes() {
      if (this.variant == HmacParameters.Variant.NO_PREFIX) {
         return this.getCryptographicTagSizeBytes();
      } else if (this.variant == HmacParameters.Variant.TINK) {
         return this.getCryptographicTagSizeBytes() + 5;
      } else if (this.variant == HmacParameters.Variant.CRUNCHY) {
         return this.getCryptographicTagSizeBytes() + 5;
      } else if (this.variant == HmacParameters.Variant.LEGACY) {
         return this.getCryptographicTagSizeBytes() + 5;
      } else {
         throw new IllegalStateException("Unknown variant");
      }
   }

   public Variant getVariant() {
      return this.variant;
   }

   public HashType getHashType() {
      return this.hashType;
   }

   public boolean equals(Object o) {
      if (!(o instanceof HmacParameters)) {
         return false;
      } else {
         HmacParameters that = (HmacParameters)o;
         return that.getKeySizeBytes() == this.getKeySizeBytes() && that.getTotalTagSizeBytes() == this.getTotalTagSizeBytes() && that.getVariant() == this.getVariant() && that.getHashType() == this.getHashType();
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{HmacParameters.class, this.keySizeBytes, this.tagSizeBytes, this.variant, this.hashType});
   }

   public boolean hasIdRequirement() {
      return this.variant != HmacParameters.Variant.NO_PREFIX;
   }

   public String toString() {
      return "HMAC Parameters (variant: " + this.variant + ", hashType: " + this.hashType + ", " + this.tagSizeBytes + "-byte tags, and " + this.keySizeBytes + "-byte key)";
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
   public static final class HashType {
      public static final HashType SHA1 = new HashType("SHA1");
      public static final HashType SHA224 = new HashType("SHA224");
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
      @Nullable
      private Integer keySizeBytes;
      @Nullable
      private Integer tagSizeBytes;
      private HashType hashType;
      private Variant variant;

      private Builder() {
         this.keySizeBytes = null;
         this.tagSizeBytes = null;
         this.hashType = null;
         this.variant = HmacParameters.Variant.NO_PREFIX;
      }

      @CanIgnoreReturnValue
      public Builder setKeySizeBytes(int keySizeBytes) throws GeneralSecurityException {
         this.keySizeBytes = keySizeBytes;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setTagSizeBytes(int tagSizeBytes) throws GeneralSecurityException {
         this.tagSizeBytes = tagSizeBytes;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setVariant(Variant variant) {
         this.variant = variant;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setHashType(HashType hashType) {
         this.hashType = hashType;
         return this;
      }

      private static void validateTagSizeBytes(int tagSizeBytes, HashType hashType) throws GeneralSecurityException {
         if (tagSizeBytes < 10) {
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", tagSizeBytes));
         } else if (hashType == HmacParameters.HashType.SHA1) {
            if (tagSizeBytes > 20) {
               throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", tagSizeBytes));
            }
         } else if (hashType == HmacParameters.HashType.SHA224) {
            if (tagSizeBytes > 28) {
               throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", tagSizeBytes));
            }
         } else if (hashType == HmacParameters.HashType.SHA256) {
            if (tagSizeBytes > 32) {
               throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", tagSizeBytes));
            }
         } else if (hashType == HmacParameters.HashType.SHA384) {
            if (tagSizeBytes > 48) {
               throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", tagSizeBytes));
            }
         } else if (hashType == HmacParameters.HashType.SHA512) {
            if (tagSizeBytes > 64) {
               throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", tagSizeBytes));
            }
         } else {
            throw new GeneralSecurityException("unknown hash type; must be SHA256, SHA384 or SHA512");
         }
      }

      public HmacParameters build() throws GeneralSecurityException {
         if (this.keySizeBytes == null) {
            throw new GeneralSecurityException("key size is not set");
         } else if (this.tagSizeBytes == null) {
            throw new GeneralSecurityException("tag size is not set");
         } else if (this.hashType == null) {
            throw new GeneralSecurityException("hash type is not set");
         } else if (this.variant == null) {
            throw new GeneralSecurityException("variant is not set");
         } else if (this.keySizeBytes < 16) {
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 16 bytes", this.keySizeBytes));
         } else {
            validateTagSizeBytes(this.tagSizeBytes, this.hashType);
            return new HmacParameters(this.keySizeBytes, this.tagSizeBytes, this.variant, this.hashType);
         }
      }
   }
}

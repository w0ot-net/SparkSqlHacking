package com.google.crypto.tink.daead;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class AesSivParameters extends DeterministicAeadParameters {
   private final int keySizeBytes;
   private final Variant variant;

   private AesSivParameters(int keySizeBytes, Variant variant) {
      this.keySizeBytes = keySizeBytes;
      this.variant = variant;
   }

   public static Builder builder() {
      return new Builder();
   }

   public int getKeySizeBytes() {
      return this.keySizeBytes;
   }

   public Variant getVariant() {
      return this.variant;
   }

   public boolean equals(Object o) {
      if (!(o instanceof AesSivParameters)) {
         return false;
      } else {
         AesSivParameters that = (AesSivParameters)o;
         return that.getKeySizeBytes() == this.getKeySizeBytes() && that.getVariant() == this.getVariant();
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{AesSivParameters.class, this.keySizeBytes, this.variant});
   }

   public boolean hasIdRequirement() {
      return this.variant != AesSivParameters.Variant.NO_PREFIX;
   }

   public String toString() {
      return "AesSiv Parameters (variant: " + this.variant + ", " + this.keySizeBytes + "-byte key)";
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

   public static final class Builder {
      @Nullable
      private Integer keySizeBytes;
      private Variant variant;

      private Builder() {
         this.keySizeBytes = null;
         this.variant = AesSivParameters.Variant.NO_PREFIX;
      }

      @CanIgnoreReturnValue
      public Builder setKeySizeBytes(int keySizeBytes) throws GeneralSecurityException {
         if (keySizeBytes != 32 && keySizeBytes != 48 && keySizeBytes != 64) {
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 32-byte, 48-byte and 64-byte AES-SIV keys are supported", keySizeBytes));
         } else {
            this.keySizeBytes = keySizeBytes;
            return this;
         }
      }

      @CanIgnoreReturnValue
      public Builder setVariant(Variant variant) {
         this.variant = variant;
         return this;
      }

      public AesSivParameters build() throws GeneralSecurityException {
         if (this.keySizeBytes == null) {
            throw new GeneralSecurityException("Key size is not set");
         } else if (this.variant == null) {
            throw new GeneralSecurityException("Variant is not set");
         } else {
            return new AesSivParameters(this.keySizeBytes, this.variant);
         }
      }
   }
}

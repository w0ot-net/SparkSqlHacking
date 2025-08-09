package com.google.crypto.tink.prf;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class HmacPrfParameters extends PrfParameters {
   private static final int MIN_KEY_SIZE = 16;
   private final int keySizeBytes;
   private final HashType hashType;

   private HmacPrfParameters(int keySizeBytes, HashType hashType) {
      this.keySizeBytes = keySizeBytes;
      this.hashType = hashType;
   }

   public static Builder builder() {
      return new Builder();
   }

   public int getKeySizeBytes() {
      return this.keySizeBytes;
   }

   public HashType getHashType() {
      return this.hashType;
   }

   public boolean equals(Object o) {
      if (!(o instanceof HmacPrfParameters)) {
         return false;
      } else {
         HmacPrfParameters that = (HmacPrfParameters)o;
         return that.getKeySizeBytes() == this.getKeySizeBytes() && that.getHashType() == this.getHashType();
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{HmacPrfParameters.class, this.keySizeBytes, this.hashType});
   }

   public boolean hasIdRequirement() {
      return false;
   }

   public String toString() {
      return "HMAC PRF Parameters (hashType: " + this.hashType + " and " + this.keySizeBytes + "-byte key)";
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
      private HashType hashType;

      private Builder() {
         this.keySizeBytes = null;
         this.hashType = null;
      }

      @CanIgnoreReturnValue
      public Builder setKeySizeBytes(int keySizeBytes) throws GeneralSecurityException {
         if (keySizeBytes < 16) {
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit or larger are supported", keySizeBytes * 8));
         } else {
            this.keySizeBytes = keySizeBytes;
            return this;
         }
      }

      @CanIgnoreReturnValue
      public Builder setHashType(HashType hashType) {
         this.hashType = hashType;
         return this;
      }

      public HmacPrfParameters build() throws GeneralSecurityException {
         if (this.keySizeBytes == null) {
            throw new GeneralSecurityException("key size is not set");
         } else if (this.hashType == null) {
            throw new GeneralSecurityException("hash type is not set");
         } else {
            return new HmacPrfParameters(this.keySizeBytes, this.hashType);
         }
      }
   }
}

package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
public final class LegacyProtoKey extends Key {
   private final ProtoKeySerialization serialization;

   private static void throwIfMissingAccess(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      switch (protoKeySerialization.getKeyMaterialType()) {
         case SYMMETRIC:
         case ASYMMETRIC_PRIVATE:
            SecretKeyAccess.requireAccess(access);
         default:
      }
   }

   public LegacyProtoKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      throwIfMissingAccess(serialization, access);
      this.serialization = serialization;
   }

   public boolean equalsKey(Key key) {
      if (!(key instanceof LegacyProtoKey)) {
         return false;
      } else {
         ProtoKeySerialization other = ((LegacyProtoKey)key).serialization;
         if (!other.getOutputPrefixType().equals(this.serialization.getOutputPrefixType())) {
            return false;
         } else if (!other.getKeyMaterialType().equals(this.serialization.getKeyMaterialType())) {
            return false;
         } else if (!other.getTypeUrl().equals(this.serialization.getTypeUrl())) {
            return false;
         } else {
            return !Objects.equals(other.getIdRequirementOrNull(), this.serialization.getIdRequirementOrNull()) ? false : Bytes.equal(this.serialization.getValue().toByteArray(), other.getValue().toByteArray());
         }
      }
   }

   @Nullable
   public Integer getIdRequirementOrNull() {
      return this.serialization.getIdRequirementOrNull();
   }

   public ProtoKeySerialization getSerialization(@Nullable SecretKeyAccess access) throws GeneralSecurityException {
      throwIfMissingAccess(this.serialization, access);
      return this.serialization;
   }

   public Parameters getParameters() {
      return new LegacyProtoParametersNotForCreation(this.serialization.getTypeUrl(), this.serialization.getOutputPrefixType());
   }

   @Immutable
   private static class LegacyProtoParametersNotForCreation extends Parameters {
      private final String typeUrl;
      private final OutputPrefixType outputPrefixType;

      public boolean hasIdRequirement() {
         return this.outputPrefixType != OutputPrefixType.RAW;
      }

      private static String outputPrefixToString(OutputPrefixType outputPrefixType) {
         switch (outputPrefixType) {
            case TINK:
               return "TINK";
            case LEGACY:
               return "LEGACY";
            case RAW:
               return "RAW";
            case CRUNCHY:
               return "CRUNCHY";
            default:
               return "UNKNOWN";
         }
      }

      public String toString() {
         return String.format("(typeUrl=%s, outputPrefixType=%s)", this.typeUrl, outputPrefixToString(this.outputPrefixType));
      }

      private LegacyProtoParametersNotForCreation(String typeUrl, OutputPrefixType outputPrefixType) {
         this.typeUrl = typeUrl;
         this.outputPrefixType = outputPrefixType;
      }
   }
}

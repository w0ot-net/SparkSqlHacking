package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.OutputPrefixUtil;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Immutable
public final class LegacyFullHybridDecrypt implements HybridDecrypt {
   private final HybridDecrypt rawHybridDecrypt;
   private final byte[] outputPrefix;

   public static HybridDecrypt create(LegacyProtoKey key) throws GeneralSecurityException {
      ProtoKeySerialization protoKeySerialization = key.getSerialization(InsecureSecretKeyAccess.get());
      KeyData keyData = KeyData.newBuilder().setTypeUrl(protoKeySerialization.getTypeUrl()).setValue(protoKeySerialization.getValue()).setKeyMaterialType(protoKeySerialization.getKeyMaterialType()).build();
      HybridDecrypt rawPrimitive = (HybridDecrypt)Registry.getPrimitive(keyData, HybridDecrypt.class);
      OutputPrefixType outputPrefixType = protoKeySerialization.getOutputPrefixType();
      byte[] outputPrefix;
      switch (outputPrefixType) {
         case RAW:
            outputPrefix = OutputPrefixUtil.EMPTY_PREFIX.toByteArray();
            break;
         case LEGACY:
         case CRUNCHY:
            outputPrefix = OutputPrefixUtil.getLegacyOutputPrefix(key.getIdRequirementOrNull()).toByteArray();
            break;
         case TINK:
            outputPrefix = OutputPrefixUtil.getTinkOutputPrefix(key.getIdRequirementOrNull()).toByteArray();
            break;
         default:
            throw new GeneralSecurityException("unknown output prefix type " + outputPrefixType);
      }

      return new LegacyFullHybridDecrypt(rawPrimitive, outputPrefix);
   }

   private LegacyFullHybridDecrypt(HybridDecrypt rawHybridDecrypt, byte[] outputPrefix) {
      this.rawHybridDecrypt = rawHybridDecrypt;
      this.outputPrefix = outputPrefix;
   }

   public byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException {
      if (this.outputPrefix.length == 0) {
         return this.rawHybridDecrypt.decrypt(ciphertext, contextInfo);
      } else if (!Util.isPrefix(this.outputPrefix, ciphertext)) {
         throw new GeneralSecurityException("Invalid ciphertext (output prefix mismatch)");
      } else {
         byte[] ciphertextNoPrefix = Arrays.copyOfRange(ciphertext, this.outputPrefix.length, ciphertext.length);
         return this.rawHybridDecrypt.decrypt(ciphertextNoPrefix, contextInfo);
      }
   }
}

package com.google.crypto.tink.signature.internal;

import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.subtle.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public final class LegacyFullSign implements PublicKeySign {
   private final PublicKeySign rawSigner;
   private final byte[] outputPrefix;
   private final byte[] messageSuffix;

   public static PublicKeySign create(LegacyProtoKey key) throws GeneralSecurityException {
      ProtoKeySerialization protoKeySerialization = key.getSerialization(InsecureSecretKeyAccess.get());
      KeyData keyData = KeyData.newBuilder().setTypeUrl(protoKeySerialization.getTypeUrl()).setValue(protoKeySerialization.getValue()).setKeyMaterialType(protoKeySerialization.getKeyMaterialType()).build();
      PublicKeySign rawSigner = (PublicKeySign)Registry.getPrimitive(keyData, PublicKeySign.class);
      return new LegacyFullSign(rawSigner, LegacyFullVerify.getOutputPrefix(protoKeySerialization), LegacyFullVerify.getMessageSuffix(protoKeySerialization));
   }

   private LegacyFullSign(PublicKeySign rawSigner, byte[] outputPrefix, byte[] messageSuffix) {
      this.rawSigner = rawSigner;
      this.outputPrefix = outputPrefix;
      this.messageSuffix = messageSuffix;
   }

   public byte[] sign(byte[] data) throws GeneralSecurityException {
      byte[] signature;
      if (this.messageSuffix.length == 0) {
         signature = this.rawSigner.sign(data);
      } else {
         signature = this.rawSigner.sign(Bytes.concat(data, this.messageSuffix));
      }

      return this.outputPrefix.length == 0 ? signature : Bytes.concat(this.outputPrefix, signature);
   }
}

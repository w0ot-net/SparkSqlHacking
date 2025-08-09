package com.google.crypto.tink.prf.internal;

import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.prf.Prf;
import com.google.crypto.tink.proto.KeyData;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public class LegacyFullPrf implements Prf {
   private final Prf rawPrf;

   public static Prf create(LegacyProtoKey key) throws GeneralSecurityException {
      ProtoKeySerialization protoKeySerialization = key.getSerialization(InsecureSecretKeyAccess.get());
      KeyData keyData = KeyData.newBuilder().setTypeUrl(protoKeySerialization.getTypeUrl()).setValue(protoKeySerialization.getValue()).setKeyMaterialType(protoKeySerialization.getKeyMaterialType()).build();
      return new LegacyFullPrf((Prf)Registry.getPrimitive(keyData, Prf.class));
   }

   private LegacyFullPrf(Prf rawPrf) {
      this.rawPrf = rawPrf;
   }

   public byte[] compute(byte[] input, int outputLength) throws GeneralSecurityException {
      return this.rawPrf.compute(input, outputLength);
   }
}

package com.google.crypto.tink.mac;

import com.google.crypto.tink.proto.AesCmacKeyFormat;
import com.google.crypto.tink.proto.AesCmacParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;

/** @deprecated */
@Deprecated
public final class MacKeyTemplates {
   public static final KeyTemplate HMAC_SHA256_128BITTAG;
   public static final KeyTemplate HMAC_SHA256_256BITTAG;
   public static final KeyTemplate HMAC_SHA512_256BITTAG;
   public static final KeyTemplate HMAC_SHA512_512BITTAG;
   public static final KeyTemplate AES_CMAC;

   public static KeyTemplate createHmacKeyTemplate(int keySize, int tagSize, HashType hashType) {
      HmacParams params = HmacParams.newBuilder().setHash(hashType).setTagSize(tagSize).build();
      HmacKeyFormat format = HmacKeyFormat.newBuilder().setParams(params).setKeySize(keySize).build();
      return KeyTemplate.newBuilder().setValue(format.toByteString()).setTypeUrl(HmacKeyManager.getKeyType()).setOutputPrefixType(OutputPrefixType.TINK).build();
   }

   private MacKeyTemplates() {
   }

   static {
      HMAC_SHA256_128BITTAG = createHmacKeyTemplate(32, 16, HashType.SHA256);
      HMAC_SHA256_256BITTAG = createHmacKeyTemplate(32, 32, HashType.SHA256);
      HMAC_SHA512_256BITTAG = createHmacKeyTemplate(64, 32, HashType.SHA512);
      HMAC_SHA512_512BITTAG = createHmacKeyTemplate(64, 64, HashType.SHA512);
      AES_CMAC = KeyTemplate.newBuilder().setValue(AesCmacKeyFormat.newBuilder().setKeySize(32).setParams(AesCmacParams.newBuilder().setTagSize(16).build()).build().toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.AesCmacKey").setOutputPrefixType(OutputPrefixType.TINK).build();
   }
}

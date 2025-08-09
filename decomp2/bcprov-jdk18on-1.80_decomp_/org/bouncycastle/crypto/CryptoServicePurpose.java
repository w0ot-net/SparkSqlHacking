package org.bouncycastle.crypto;

public enum CryptoServicePurpose {
   AGREEMENT,
   ENCRYPTION,
   DECRYPTION,
   KEYGEN,
   SIGNING,
   VERIFYING,
   AUTHENTICATION,
   VERIFICATION,
   PRF,
   ANY;

   // $FF: synthetic method
   private static CryptoServicePurpose[] $values() {
      return new CryptoServicePurpose[]{AGREEMENT, ENCRYPTION, DECRYPTION, KEYGEN, SIGNING, VERIFYING, AUTHENTICATION, VERIFICATION, PRF, ANY};
   }
}

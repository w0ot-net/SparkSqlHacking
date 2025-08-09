package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class HSSKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   HSSKeyGenerationParameters param;

   public void init(KeyGenerationParameters var1) {
      this.param = (HSSKeyGenerationParameters)var1;
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      HSSPrivateKeyParameters var1 = HSS.generateHSSKeyPair(this.param);
      return new AsymmetricCipherKeyPair(var1.getPublicKey(), var1);
   }
}

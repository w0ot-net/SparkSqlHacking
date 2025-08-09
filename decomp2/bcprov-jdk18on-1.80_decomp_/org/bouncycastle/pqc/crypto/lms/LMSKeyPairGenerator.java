package org.bouncycastle.pqc.crypto.lms;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class LMSKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   LMSKeyGenerationParameters param;

   public void init(KeyGenerationParameters var1) {
      this.param = (LMSKeyGenerationParameters)var1;
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      SecureRandom var1 = this.param.getRandom();
      byte[] var2 = new byte[16];
      var1.nextBytes(var2);
      LMSigParameters var3 = this.param.getParameters().getLMSigParam();
      byte[] var4 = new byte[var3.getM()];
      var1.nextBytes(var4);
      LMSPrivateKeyParameters var5 = LMS.generateKeys(var3, this.param.getParameters().getLMOTSParam(), 0, var2, var4);
      return new AsymmetricCipherKeyPair(var5.getPublicKey(), var5);
   }
}

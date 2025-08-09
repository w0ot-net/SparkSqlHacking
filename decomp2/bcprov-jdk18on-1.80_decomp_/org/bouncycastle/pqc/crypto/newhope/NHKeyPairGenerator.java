package org.bouncycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class NHKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private SecureRandom random;

   public void init(KeyGenerationParameters var1) {
      this.random = var1.getRandom();
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      byte[] var1 = new byte[1824];
      short[] var2 = new short[1024];
      NewHope.keygen(this.random, var1, var2);
      return new AsymmetricCipherKeyPair(new NHPublicKeyParameters(var1), new NHPrivateKeyParameters(var2));
   }
}

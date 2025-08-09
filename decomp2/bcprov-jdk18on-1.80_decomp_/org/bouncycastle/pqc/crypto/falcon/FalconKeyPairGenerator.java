package org.bouncycastle.pqc.crypto.falcon;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class FalconKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private FalconKeyGenerationParameters params;
   private SecureRandom random;
   private FalconNIST nist;
   private int logn;
   private int noncelen;
   private int pk_size;
   private int sk_size;

   public void init(KeyGenerationParameters var1) {
      this.params = (FalconKeyGenerationParameters)var1;
      this.random = var1.getRandom();
      this.logn = ((FalconKeyGenerationParameters)var1).getParameters().getLogN();
      this.noncelen = ((FalconKeyGenerationParameters)var1).getParameters().getNonceLength();
      this.nist = new FalconNIST(this.logn, this.noncelen, this.random);
      int var2 = 1 << this.logn;
      byte var3 = 8;
      if (var2 == 1024) {
         var3 = 5;
      } else if (var2 != 256 && var2 != 512) {
         if (var2 == 64 || var2 == 128) {
            var3 = 7;
         }
      } else {
         var3 = 6;
      }

      this.pk_size = 1 + 14 * var2 / 8;
      this.sk_size = 1 + 2 * var3 * var2 / 8 + var2;
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      byte[] var1 = new byte[this.pk_size];
      byte[] var2 = new byte[this.sk_size];
      byte[][] var3 = this.nist.crypto_sign_keypair(var1, 0, var2, 0);
      FalconParameters var4 = this.params.getParameters();
      FalconPrivateKeyParameters var5 = new FalconPrivateKeyParameters(var4, var3[1], var3[2], var3[3], var3[0]);
      FalconPublicKeyParameters var6 = new FalconPublicKeyParameters(var4, var3[0]);
      return new AsymmetricCipherKeyPair(var6, var5);
   }
}

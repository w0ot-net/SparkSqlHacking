package org.bouncycastle.pqc.crypto.gemss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;

public class GeMSSSigner implements MessageSigner {
   private GeMSSPrivateKeyParameters privKey;
   private GeMSSPublicKeyParameters pubKey;
   private SecureRandom random;

   public void init(boolean var1, CipherParameters var2) {
      if (var1) {
         if (var2 instanceof ParametersWithRandom) {
            this.privKey = (GeMSSPrivateKeyParameters)((ParametersWithRandom)var2).getParameters();
            this.random = ((ParametersWithRandom)var2).getRandom();
         } else {
            this.privKey = (GeMSSPrivateKeyParameters)var2;
            this.random = CryptoServicesRegistrar.getSecureRandom();
         }
      } else {
         this.pubKey = (GeMSSPublicKeyParameters)var2;
      }

   }

   public byte[] generateSignature(byte[] var1) {
      GeMSSEngine var2 = this.privKey.getParameters().getEngine();
      int var3 = var2.HFEnv + (var2.NB_ITE - 1) * (var2.HFEnv - var2.HFEm) + 7 >>> 3;
      byte[] var4 = new byte[var1.length + var3];
      System.arraycopy(var1, 0, var4, var3, var1.length);
      var2.signHFE_FeistelPatarin(this.random, var4, var1, 0, var1.length, this.privKey.sk);
      return var4;
   }

   public boolean verifySignature(byte[] var1, byte[] var2) {
      GeMSSEngine var3 = this.pubKey.getParameters().getEngine();
      int var4 = var3.crypto_sign_open(this.pubKey.getPK(), var1, var2);
      return var4 != 0;
   }
}

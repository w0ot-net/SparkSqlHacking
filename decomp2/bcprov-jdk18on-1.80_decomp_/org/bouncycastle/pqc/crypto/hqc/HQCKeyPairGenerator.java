package org.bouncycastle.pqc.crypto.hqc;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class HQCKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private int n;
   private int k;
   private int delta;
   private int w;
   private int wr;
   private int we;
   private int N_BYTE;
   private HQCKeyGenerationParameters hqcKeyGenerationParameters;
   private SecureRandom random;

   public void init(KeyGenerationParameters var1) {
      this.hqcKeyGenerationParameters = (HQCKeyGenerationParameters)var1;
      this.random = var1.getRandom();
      this.n = this.hqcKeyGenerationParameters.getParameters().getN();
      this.k = this.hqcKeyGenerationParameters.getParameters().getK();
      this.delta = this.hqcKeyGenerationParameters.getParameters().getDelta();
      this.w = this.hqcKeyGenerationParameters.getParameters().getW();
      this.wr = this.hqcKeyGenerationParameters.getParameters().getWr();
      this.we = this.hqcKeyGenerationParameters.getParameters().getWe();
      this.N_BYTE = (this.n + 7) / 8;
   }

   private AsymmetricCipherKeyPair genKeyPair(byte[] var1) {
      HQCEngine var2 = this.hqcKeyGenerationParameters.getParameters().getEngine();
      byte[] var3 = new byte[40 + this.N_BYTE];
      byte[] var4 = new byte[80 + this.k + this.N_BYTE];
      var2.genKeyPair(var3, var4, var1);
      HQCPublicKeyParameters var5 = new HQCPublicKeyParameters(this.hqcKeyGenerationParameters.getParameters(), var3);
      HQCPrivateKeyParameters var6 = new HQCPrivateKeyParameters(this.hqcKeyGenerationParameters.getParameters(), var4);
      return new AsymmetricCipherKeyPair(var5, var6);
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      byte[] var1 = new byte[48];
      this.random.nextBytes(var1);
      return this.genKeyPair(var1);
   }

   public AsymmetricCipherKeyPair generateKeyPairWithSeed(byte[] var1) {
      return this.genKeyPair(var1);
   }
}

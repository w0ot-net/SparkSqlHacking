package org.bouncycastle.pqc.crypto.mlkem;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class MLKEMKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private MLKEMParameters mlkemParams;
   private SecureRandom random;

   private void initialize(KeyGenerationParameters var1) {
      this.mlkemParams = ((MLKEMKeyGenerationParameters)var1).getParameters();
      this.random = var1.getRandom();
   }

   private AsymmetricCipherKeyPair genKeyPair() {
      MLKEMEngine var1 = this.mlkemParams.getEngine();
      var1.init(this.random);
      byte[][] var2 = var1.generateKemKeyPair();
      MLKEMPublicKeyParameters var3 = new MLKEMPublicKeyParameters(this.mlkemParams, var2[0], var2[1]);
      MLKEMPrivateKeyParameters var4 = new MLKEMPrivateKeyParameters(this.mlkemParams, var2[2], var2[3], var2[4], var2[0], var2[1], var2[5]);
      return new AsymmetricCipherKeyPair(var3, var4);
   }

   public void init(KeyGenerationParameters var1) {
      this.initialize(var1);
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      return this.genKeyPair();
   }

   public AsymmetricCipherKeyPair internalGenerateKeyPair(byte[] var1, byte[] var2) {
      byte[][] var3 = this.mlkemParams.getEngine().generateKemKeyPairInternal(var1, var2);
      MLKEMPublicKeyParameters var4 = new MLKEMPublicKeyParameters(this.mlkemParams, var3[0], var3[1]);
      MLKEMPrivateKeyParameters var5 = new MLKEMPrivateKeyParameters(this.mlkemParams, var3[2], var3[3], var3[4], var3[0], var3[1], var3[5]);
      return new AsymmetricCipherKeyPair(var4, var5);
   }
}

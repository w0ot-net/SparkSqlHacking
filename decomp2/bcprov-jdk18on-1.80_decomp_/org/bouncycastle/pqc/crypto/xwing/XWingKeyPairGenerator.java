package org.bouncycastle.pqc.crypto.xwing;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMKeyPairGenerator;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMParameters;

public class XWingKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private SecureRandom random;

   private void initialize(KeyGenerationParameters var1) {
      this.random = var1.getRandom();
   }

   private AsymmetricCipherKeyPair genKeyPair() {
      MLKEMKeyPairGenerator var1 = new MLKEMKeyPairGenerator();
      var1.init(new MLKEMKeyGenerationParameters(this.random, MLKEMParameters.ml_kem_768));
      X25519KeyPairGenerator var2 = new X25519KeyPairGenerator();
      var2.init(new X25519KeyGenerationParameters(this.random));
      AsymmetricCipherKeyPair var3 = var1.generateKeyPair();
      AsymmetricCipherKeyPair var4 = var2.generateKeyPair();
      return new AsymmetricCipherKeyPair(new XWingPublicKeyParameters(var3.getPublic(), var4.getPublic()), new XWingPrivateKeyParameters(var3.getPrivate(), var4.getPrivate()));
   }

   public void init(KeyGenerationParameters var1) {
      this.initialize(var1);
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      return this.genKeyPair();
   }
}

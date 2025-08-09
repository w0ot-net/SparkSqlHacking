package org.bouncycastle.pqc.jcajce.provider.mceliece;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.pqc.jcajce.spec.McElieceKeyGenParameterSpec;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceKeyGenerationParameters;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceKeyPairGenerator;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McElieceParameters;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McEliecePrivateKeyParameters;
import org.bouncycastle.pqc.legacy.crypto.mceliece.McEliecePublicKeyParameters;

public class McElieceKeyPairGeneratorSpi extends KeyPairGenerator {
   McElieceKeyPairGenerator kpg;

   public McElieceKeyPairGeneratorSpi() {
      super("McEliece");
   }

   public void initialize(AlgorithmParameterSpec var1, SecureRandom var2) throws InvalidAlgorithmParameterException {
      this.kpg = new McElieceKeyPairGenerator();
      McElieceKeyGenParameterSpec var3 = (McElieceKeyGenParameterSpec)var1;
      McElieceKeyGenerationParameters var4 = new McElieceKeyGenerationParameters(var2, new McElieceParameters(var3.getM(), var3.getT()));
      this.kpg.init(var4);
   }

   public void initialize(int var1, SecureRandom var2) {
      McElieceKeyGenParameterSpec var3 = new McElieceKeyGenParameterSpec();

      try {
         this.initialize(var3, var2);
      } catch (InvalidAlgorithmParameterException var5) {
      }

   }

   public KeyPair generateKeyPair() {
      AsymmetricCipherKeyPair var1 = this.kpg.generateKeyPair();
      McEliecePrivateKeyParameters var2 = (McEliecePrivateKeyParameters)var1.getPrivate();
      McEliecePublicKeyParameters var3 = (McEliecePublicKeyParameters)var1.getPublic();
      return new KeyPair(new BCMcEliecePublicKey(var3), new BCMcEliecePrivateKey(var2));
   }
}

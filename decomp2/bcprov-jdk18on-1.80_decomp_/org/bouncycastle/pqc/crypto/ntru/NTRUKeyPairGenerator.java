package org.bouncycastle.pqc.crypto.ntru;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet;
import org.bouncycastle.util.Arrays;

public class NTRUKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private NTRUKeyGenerationParameters params;
   private SecureRandom random;

   public void init(KeyGenerationParameters var1) {
      this.params = (NTRUKeyGenerationParameters)var1;
      this.random = var1.getRandom();
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      NTRUParameters var1 = this.params.getParameters();
      NTRUParameterSet var2 = var1.getParameterSet();
      byte[] var3 = new byte[var2.sampleFgBytes()];
      this.random.nextBytes(var3);
      NTRUOWCPA var4 = new NTRUOWCPA(var2);
      OWCPAKeyPair var5 = var4.keypair(var3);
      byte[] var6 = var5.publicKey;
      byte[] var7 = new byte[var2.prfKeyBytes()];
      this.random.nextBytes(var7);
      byte[] var8 = Arrays.concatenate(var5.privateKey, var7);
      return new AsymmetricCipherKeyPair(new NTRUPublicKeyParameters(var1, var6), new NTRUPrivateKeyParameters(var1, var8));
   }
}

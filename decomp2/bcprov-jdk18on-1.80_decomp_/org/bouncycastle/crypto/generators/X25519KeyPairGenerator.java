package org.bouncycastle.crypto.generators;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;

public class X25519KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private SecureRandom random;

   public void init(KeyGenerationParameters var1) {
      this.random = var1.getRandom();
      CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("X25519KeyGen", 128, (Object)null, CryptoServicePurpose.KEYGEN));
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      X25519PrivateKeyParameters var1 = new X25519PrivateKeyParameters(this.random);
      X25519PublicKeyParameters var2 = var1.generatePublicKey();
      return new AsymmetricCipherKeyPair(var2, var1);
   }
}

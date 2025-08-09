package org.bouncycastle.crypto.generators;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed448PublicKeyParameters;

public class Ed448KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private SecureRandom random;

   public void init(KeyGenerationParameters var1) {
      this.random = var1.getRandom();
      CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("Ed448KeyGen", 224, (Object)null, CryptoServicePurpose.KEYGEN));
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      Ed448PrivateKeyParameters var1 = new Ed448PrivateKeyParameters(this.random);
      Ed448PublicKeyParameters var2 = var1.generatePublicKey();
      return new AsymmetricCipherKeyPair(var2, var1);
   }
}

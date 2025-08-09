package org.bouncycastle.pqc.legacy.crypto.qtesla;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public final class QTESLAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private int securityCategory;
   private SecureRandom secureRandom;

   public void init(KeyGenerationParameters var1) {
      QTESLAKeyGenerationParameters var2 = (QTESLAKeyGenerationParameters)var1;
      this.secureRandom = var2.getRandom();
      this.securityCategory = var2.getSecurityCategory();
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      byte[] var1 = this.allocatePrivate(this.securityCategory);
      byte[] var2 = this.allocatePublic(this.securityCategory);
      switch (this.securityCategory) {
         case 5:
            QTesla1p.generateKeyPair(var2, var1, this.secureRandom);
            break;
         case 6:
            QTesla3p.generateKeyPair(var2, var1, this.secureRandom);
            break;
         default:
            throw new IllegalArgumentException("unknown security category: " + this.securityCategory);
      }

      return new AsymmetricCipherKeyPair(new QTESLAPublicKeyParameters(this.securityCategory, var2), new QTESLAPrivateKeyParameters(this.securityCategory, var1));
   }

   private byte[] allocatePrivate(int var1) {
      return new byte[QTESLASecurityCategory.getPrivateSize(var1)];
   }

   private byte[] allocatePublic(int var1) {
      return new byte[QTESLASecurityCategory.getPublicSize(var1)];
   }
}

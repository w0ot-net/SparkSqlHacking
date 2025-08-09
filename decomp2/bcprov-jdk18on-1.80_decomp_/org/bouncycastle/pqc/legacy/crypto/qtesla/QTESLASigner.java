package org.bouncycastle.pqc.legacy.crypto.qtesla;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;

public class QTESLASigner implements MessageSigner {
   private QTESLAPublicKeyParameters publicKey;
   private QTESLAPrivateKeyParameters privateKey;
   private SecureRandom secureRandom;

   public void init(boolean var1, CipherParameters var2) {
      if (var1) {
         if (var2 instanceof ParametersWithRandom) {
            this.secureRandom = ((ParametersWithRandom)var2).getRandom();
            this.privateKey = (QTESLAPrivateKeyParameters)((ParametersWithRandom)var2).getParameters();
         } else {
            this.secureRandom = CryptoServicesRegistrar.getSecureRandom();
            this.privateKey = (QTESLAPrivateKeyParameters)var2;
         }

         this.publicKey = null;
         QTESLASecurityCategory.validate(this.privateKey.getSecurityCategory());
      } else {
         this.privateKey = null;
         this.publicKey = (QTESLAPublicKeyParameters)var2;
         QTESLASecurityCategory.validate(this.publicKey.getSecurityCategory());
      }

   }

   public byte[] generateSignature(byte[] var1) {
      byte[] var2 = new byte[QTESLASecurityCategory.getSignatureSize(this.privateKey.getSecurityCategory())];
      switch (this.privateKey.getSecurityCategory()) {
         case 5:
            QTesla1p.generateSignature(var2, var1, 0, var1.length, this.privateKey.getSecret(), this.secureRandom);
            break;
         case 6:
            QTesla3p.generateSignature(var2, var1, 0, var1.length, this.privateKey.getSecret(), this.secureRandom);
            break;
         default:
            throw new IllegalArgumentException("unknown security category: " + this.privateKey.getSecurityCategory());
      }

      return var2;
   }

   public boolean verifySignature(byte[] var1, byte[] var2) {
      int var3;
      switch (this.publicKey.getSecurityCategory()) {
         case 5:
            var3 = QTesla1p.verifying(var1, var2, 0, var2.length, this.publicKey.getPublicData());
            break;
         case 6:
            var3 = QTesla3p.verifying(var1, var2, 0, var2.length, this.publicKey.getPublicData());
            break;
         default:
            throw new IllegalArgumentException("unknown security category: " + this.publicKey.getSecurityCategory());
      }

      return 0 == var3;
   }
}

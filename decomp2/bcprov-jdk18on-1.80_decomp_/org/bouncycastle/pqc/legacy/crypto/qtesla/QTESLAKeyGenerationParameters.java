package org.bouncycastle.pqc.legacy.crypto.qtesla;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class QTESLAKeyGenerationParameters extends KeyGenerationParameters {
   private final int securityCategory;

   public QTESLAKeyGenerationParameters(int var1, SecureRandom var2) {
      super(var2, -1);
      QTESLASecurityCategory.getPrivateSize(var1);
      this.securityCategory = var1;
   }

   public int getSecurityCategory() {
      return this.securityCategory;
   }
}

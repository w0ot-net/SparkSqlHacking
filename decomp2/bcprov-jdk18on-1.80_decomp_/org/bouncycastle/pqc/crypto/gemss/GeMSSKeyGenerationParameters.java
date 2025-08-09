package org.bouncycastle.pqc.crypto.gemss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class GeMSSKeyGenerationParameters extends KeyGenerationParameters {
   final GeMSSParameters parameters;

   public GeMSSKeyGenerationParameters(SecureRandom var1, GeMSSParameters var2) {
      super(var1, -1);
      this.parameters = var2;
   }

   public GeMSSParameters getParameters() {
      return this.parameters;
   }
}

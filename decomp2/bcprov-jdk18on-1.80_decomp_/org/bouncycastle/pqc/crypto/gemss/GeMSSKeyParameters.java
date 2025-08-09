package org.bouncycastle.pqc.crypto.gemss;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class GeMSSKeyParameters extends AsymmetricKeyParameter {
   final GeMSSParameters parameters;

   protected GeMSSKeyParameters(boolean var1, GeMSSParameters var2) {
      super(var1);
      this.parameters = var2;
   }

   public GeMSSParameters getParameters() {
      return this.parameters;
   }
}

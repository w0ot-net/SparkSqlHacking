package org.bouncycastle.pqc.legacy.crypto.ntru;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class NTRUEncryptionKeyParameters extends AsymmetricKeyParameter {
   protected final NTRUEncryptionParameters params;

   public NTRUEncryptionKeyParameters(boolean var1, NTRUEncryptionParameters var2) {
      super(var1);
      this.params = var2;
   }

   public NTRUEncryptionParameters getParameters() {
      return this.params;
   }
}

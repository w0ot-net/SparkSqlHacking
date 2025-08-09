package org.bouncycastle.pqc.crypto.gemss;

import org.bouncycastle.util.Arrays;

public class GeMSSPublicKeyParameters extends GeMSSKeyParameters {
   private final byte[] pk;

   public GeMSSPublicKeyParameters(GeMSSParameters var1, byte[] var2) {
      super(false, var1);
      this.pk = new byte[var2.length];
      System.arraycopy(var2, 0, this.pk, 0, this.pk.length);
   }

   public byte[] getPK() {
      return this.pk;
   }

   public byte[] getEncoded() {
      return Arrays.clone(this.pk);
   }
}

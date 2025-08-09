package org.bouncycastle.pqc.crypto.gemss;

import org.bouncycastle.util.Arrays;

public class GeMSSPrivateKeyParameters extends GeMSSKeyParameters {
   final byte[] sk;

   public GeMSSPrivateKeyParameters(GeMSSParameters var1, byte[] var2) {
      super(false, var1);
      this.sk = new byte[var2.length];
      System.arraycopy(var2, 0, this.sk, 0, this.sk.length);
   }

   public byte[] getEncoded() {
      return Arrays.clone(this.sk);
   }
}

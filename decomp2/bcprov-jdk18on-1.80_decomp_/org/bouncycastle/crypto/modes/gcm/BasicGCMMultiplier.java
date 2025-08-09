package org.bouncycastle.crypto.modes.gcm;

public class BasicGCMMultiplier implements GCMMultiplier {
   private long[] H;

   public void init(byte[] var1) {
      this.H = GCMUtil.asLongs(var1);
   }

   public void multiplyH(byte[] var1) {
      GCMUtil.multiply(var1, this.H);
   }
}

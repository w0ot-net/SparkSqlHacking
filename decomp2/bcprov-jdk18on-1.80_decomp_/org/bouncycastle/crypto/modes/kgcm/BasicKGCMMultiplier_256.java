package org.bouncycastle.crypto.modes.kgcm;

public class BasicKGCMMultiplier_256 implements KGCMMultiplier {
   private final long[] H = new long[4];

   public void init(long[] var1) {
      KGCMUtil_256.copy(var1, this.H);
   }

   public void multiplyH(long[] var1) {
      KGCMUtil_256.multiply(var1, this.H, var1);
   }
}

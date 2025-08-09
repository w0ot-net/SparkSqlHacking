package org.bouncycastle.crypto.modes.kgcm;

public class Tables4kKGCMMultiplier_128 implements KGCMMultiplier {
   private long[][] T;

   public void init(long[] var1) {
      if (this.T == null) {
         this.T = new long[256][2];
      } else if (KGCMUtil_128.equal(var1, this.T[1])) {
         return;
      }

      KGCMUtil_128.copy(var1, this.T[1]);

      for(int var2 = 2; var2 < 256; var2 += 2) {
         KGCMUtil_128.multiplyX(this.T[var2 >> 1], this.T[var2]);
         KGCMUtil_128.add(this.T[var2], this.T[1], this.T[var2 + 1]);
      }

   }

   public void multiplyH(long[] var1) {
      long[] var2 = new long[2];
      KGCMUtil_128.copy(this.T[(int)(var1[1] >>> 56) & 255], var2);

      for(int var3 = 14; var3 >= 0; --var3) {
         KGCMUtil_128.multiplyX8(var2, var2);
         KGCMUtil_128.add(this.T[(int)(var1[var3 >>> 3] >>> ((var3 & 7) << 3)) & 255], var2, var2);
      }

      KGCMUtil_128.copy(var2, var1);
   }
}

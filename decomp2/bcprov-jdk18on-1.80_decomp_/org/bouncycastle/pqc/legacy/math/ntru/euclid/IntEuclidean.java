package org.bouncycastle.pqc.legacy.math.ntru.euclid;

public class IntEuclidean {
   public int x;
   public int y;
   public int gcd;

   private IntEuclidean() {
   }

   public static IntEuclidean calculate(int var0, int var1) {
      int var2 = 0;
      int var3 = 1;
      int var4 = 1;

      int var5;
      int var10;
      for(var5 = 0; var1 != 0; var5 = var10) {
         int var6 = var0 / var1;
         var10 = var0;
         var0 = var1;
         var1 = var10 % var1;
         var10 = var2;
         var2 = var3 - var6 * var2;
         var3 = var10;
         var10 = var4;
         var4 = var5 - var6 * var4;
      }

      IntEuclidean var8 = new IntEuclidean();
      var8.x = var3;
      var8.y = var5;
      var8.gcd = var0;
      return var8;
   }
}

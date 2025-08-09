package breeze.linalg.operators;

public final class DenseVectorSupportMethods {
   public static final int MAX_SMALL_DOT_PRODUCT_LENGTH = 8;
   public static final int UNROLL_LENGTH = 8;

   public static double smallDotProduct_Double(double[] var0, double[] var1, int var2) {
      double var3 = (double)0.0F;
      double var5 = (double)0.0F;
      switch (var2) {
         case 7:
            var3 = var0[6] * var1[6];
         case 6:
            var5 = var0[5] * var1[5];
         case 5:
            var3 += var0[4] * var1[4];
         case 4:
            var5 += var0[3] * var1[3];
         case 3:
            var3 += var0[2] * var1[2];
         case 2:
            var5 += var0[1] * var1[1];
         case 1:
            var3 += var0[0] * var1[0];
         case 0:
         default:
            return var3 + var5;
      }
   }

   public static double dotProduct_Double(double[] var0, int var1, double[] var2, int var3, int var4) {
      int var5 = var4 % 8;
      int var6 = var4 / 8;
      double var7 = (double)0.0F;
      double var9 = (double)0.0F;
      double var11 = (double)0.0F;
      double var13 = (double)0.0F;
      double var15 = (double)0.0F;
      double var17 = (double)0.0F;
      double var19 = (double)0.0F;
      double var21 = (double)0.0F;

      for(int var23 = 0; var23 < var5; ++var23) {
         var7 += var0[var1 + var23] * var2[var3 + var23];
      }

      var1 += var5;
      var3 += var5;

      for(int var26 = 0; var26 < var6; var3 += 8) {
         var7 += var0[var1 + 0] * var2[var3 + 0];
         var9 += var0[var1 + 1] * var2[var3 + 1];
         var11 += var0[var1 + 2] * var2[var3 + 2];
         var13 += var0[var1 + 3] * var2[var3 + 3];
         var15 += var0[var1 + 4] * var2[var3 + 4];
         var17 += var0[var1 + 5] * var2[var3 + 5];
         var19 += var0[var1 + 6] * var2[var3 + 6];
         var21 += var0[var1 + 7] * var2[var3 + 7];
         ++var26;
         var1 += 8;
      }

      return var7 + var9 + var11 + var13 + var15 + var17 + var19 + var21;
   }

   public static float smallDotProduct_Float(float[] var0, float[] var1, int var2) {
      float var3 = 0.0F;
      float var4 = 0.0F;
      switch (var2) {
         case 7:
            var3 = var0[6] * var1[6];
         case 6:
            var4 = var0[5] * var1[5];
         case 5:
            var3 += var0[4] * var1[4];
         case 4:
            var4 += var0[3] * var1[3];
         case 3:
            var3 += var0[2] * var1[2];
         case 2:
            var4 += var0[1] * var1[1];
         case 1:
            var3 += var0[0] * var1[0];
         case 0:
         default:
            return var3 + var4;
      }
   }

   public static float dotProduct_Float(float[] var0, int var1, float[] var2, int var3, int var4) {
      int var5 = var4 % 8;
      int var6 = var4 / 8;
      float var7 = 0.0F;
      float var8 = 0.0F;
      float var9 = 0.0F;
      float var10 = 0.0F;
      float var11 = 0.0F;
      float var12 = 0.0F;
      float var13 = 0.0F;
      float var14 = 0.0F;

      for(int var15 = 0; var15 < var5; ++var15) {
         var7 += var0[var1 + var15] * var2[var3 + var15];
      }

      var1 += var5;
      var3 += var5;

      for(int var18 = 0; var18 < var6; var3 += 8) {
         var7 += var0[var1 + 0] * var2[var3 + 0];
         var8 += var0[var1 + 1] * var2[var3 + 1];
         var9 += var0[var1 + 2] * var2[var3 + 2];
         var10 += var0[var1 + 3] * var2[var3 + 3];
         var11 += var0[var1 + 4] * var2[var3 + 4];
         var12 += var0[var1 + 5] * var2[var3 + 5];
         var13 += var0[var1 + 6] * var2[var3 + 6];
         var14 += var0[var1 + 7] * var2[var3 + 7];
         ++var18;
         var1 += 8;
      }

      return var7 + var8 + var9 + var10 + var11 + var12 + var13 + var14;
   }
}

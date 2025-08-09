package org.bouncycastle.pqc.legacy.math.linearalgebra;

public class PolynomialRingGF2m {
   private GF2mField field;
   private PolynomialGF2mSmallM p;
   protected PolynomialGF2mSmallM[] sqMatrix;
   protected PolynomialGF2mSmallM[] sqRootMatrix;

   public PolynomialRingGF2m(GF2mField var1, PolynomialGF2mSmallM var2) {
      this.field = var1;
      this.p = var2;
      this.computeSquaringMatrix();
      this.computeSquareRootMatrix();
   }

   public PolynomialGF2mSmallM[] getSquaringMatrix() {
      return this.sqMatrix;
   }

   public PolynomialGF2mSmallM[] getSquareRootMatrix() {
      return this.sqRootMatrix;
   }

   private void computeSquaringMatrix() {
      int var1 = this.p.getDegree();
      this.sqMatrix = new PolynomialGF2mSmallM[var1];

      for(int var2 = 0; var2 < var1 >> 1; ++var2) {
         int[] var3 = new int[(var2 << 1) + 1];
         var3[var2 << 1] = 1;
         this.sqMatrix[var2] = new PolynomialGF2mSmallM(this.field, var3);
      }

      for(int var5 = var1 >> 1; var5 < var1; ++var5) {
         int[] var6 = new int[(var5 << 1) + 1];
         var6[var5 << 1] = 1;
         PolynomialGF2mSmallM var4 = new PolynomialGF2mSmallM(this.field, var6);
         this.sqMatrix[var5] = var4.mod(this.p);
      }

   }

   private void computeSquareRootMatrix() {
      int var1 = this.p.getDegree();
      PolynomialGF2mSmallM[] var2 = new PolynomialGF2mSmallM[var1];

      for(int var3 = var1 - 1; var3 >= 0; --var3) {
         var2[var3] = new PolynomialGF2mSmallM(this.sqMatrix[var3]);
      }

      this.sqRootMatrix = new PolynomialGF2mSmallM[var1];

      for(int var9 = var1 - 1; var9 >= 0; --var9) {
         this.sqRootMatrix[var9] = new PolynomialGF2mSmallM(this.field, var9);
      }

      for(int var10 = 0; var10 < var1; ++var10) {
         if (var2[var10].getCoefficient(var10) == 0) {
            boolean var4 = false;

            for(int var5 = var10 + 1; var5 < var1; ++var5) {
               if (var2[var5].getCoefficient(var10) != 0) {
                  var4 = true;
                  swapColumns(var2, var10, var5);
                  swapColumns(this.sqRootMatrix, var10, var5);
                  var5 = var1;
               }
            }

            if (!var4) {
               throw new ArithmeticException("Squaring matrix is not invertible.");
            }
         }

         int var11 = var2[var10].getCoefficient(var10);
         int var13 = this.field.inverse(var11);
         var2[var10].multThisWithElement(var13);
         this.sqRootMatrix[var10].multThisWithElement(var13);

         for(int var6 = 0; var6 < var1; ++var6) {
            if (var6 != var10) {
               var11 = var2[var6].getCoefficient(var10);
               if (var11 != 0) {
                  PolynomialGF2mSmallM var7 = var2[var10].multWithElement(var11);
                  PolynomialGF2mSmallM var8 = this.sqRootMatrix[var10].multWithElement(var11);
                  var2[var6].addToThis(var7);
                  this.sqRootMatrix[var6].addToThis(var8);
               }
            }
         }
      }

   }

   private static void swapColumns(PolynomialGF2mSmallM[] var0, int var1, int var2) {
      PolynomialGF2mSmallM var3 = var0[var1];
      var0[var1] = var0[var2];
      var0[var2] = var3;
   }
}

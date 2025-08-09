package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

import org.bouncycastle.util.Arrays;

public class LongPolynomial5 {
   private long[] coeffs;
   private int numCoeffs;

   public LongPolynomial5(IntegerPolynomial var1) {
      this.numCoeffs = var1.coeffs.length;
      this.coeffs = new long[(this.numCoeffs + 4) / 5];
      int var2 = 0;
      int var3 = 0;

      for(int var4 = 0; var4 < this.numCoeffs; ++var4) {
         long[] var10000 = this.coeffs;
         var10000[var2] |= (long)var1.coeffs[var4] << var3;
         var3 += 12;
         if (var3 >= 60) {
            var3 = 0;
            ++var2;
         }
      }

   }

   private LongPolynomial5(long[] var1, int var2) {
      this.coeffs = var1;
      this.numCoeffs = var2;
   }

   public LongPolynomial5 mult(TernaryPolynomial var1) {
      long[][] var2 = new long[5][this.coeffs.length + (var1.size() + 4) / 5 - 1];
      int[] var3 = var1.getOnes();

      for(int var4 = 0; var4 != var3.length; ++var4) {
         int var5 = var3[var4];
         int var6 = var5 / 5;
         int var7 = var5 - var6 * 5;

         for(int var8 = 0; var8 < this.coeffs.length; ++var8) {
            var2[var7][var6] = var2[var7][var6] + this.coeffs[var8] & 576319980446939135L;
            ++var6;
         }
      }

      int[] var18 = var1.getNegOnes();

      for(int var19 = 0; var19 != var18.length; ++var19) {
         int var21 = var18[var19];
         int var24 = var21 / 5;
         int var27 = var21 - var24 * 5;

         for(int var9 = 0; var9 < this.coeffs.length; ++var9) {
            var2[var27][var24] = 576601524159907840L + var2[var27][var24] - this.coeffs[var9] & 576319980446939135L;
            ++var24;
         }
      }

      long[] var20 = Arrays.copyOf(var2[0], var2[0].length + 1);

      for(int var22 = 1; var22 <= 4; ++var22) {
         int var25 = var22 * 12;
         int var28 = 60 - var25;
         long var30 = (1L << var28) - 1L;
         int var11 = var2[var22].length;

         for(int var12 = 0; var12 < var11; ++var12) {
            long var13 = var2[var22][var12] >> var28;
            long var15 = var2[var22][var12] & var30;
            var20[var12] = var20[var12] + (var15 << var25) & 576319980446939135L;
            int var17 = var12 + 1;
            var20[var17] = var20[var17] + var13 & 576319980446939135L;
         }
      }

      int var23 = 12 * (this.numCoeffs % 5);

      for(int var26 = this.coeffs.length - 1; var26 < var20.length; ++var26) {
         int var10;
         long var29;
         if (var26 == this.coeffs.length - 1) {
            var29 = this.numCoeffs == 5 ? 0L : var20[var26] >> var23;
            var10 = 0;
         } else {
            var29 = var20[var26];
            var10 = var26 * 5 - this.numCoeffs;
         }

         int var31 = var10 / 5;
         int var32 = var10 - var31 * 5;
         long var33 = var29 << 12 * var32;
         long var34 = var29 >> 12 * (5 - var32);
         var20[var31] = var20[var31] + var33 & 576319980446939135L;
         int var35 = var31 + 1;
         if (var35 < this.coeffs.length) {
            var20[var35] = var20[var35] + var34 & 576319980446939135L;
         }
      }

      return new LongPolynomial5(var20, this.numCoeffs);
   }

   public IntegerPolynomial toIntegerPolynomial() {
      int[] var1 = new int[this.numCoeffs];
      int var2 = 0;
      int var3 = 0;

      for(int var4 = 0; var4 < this.numCoeffs; ++var4) {
         var1[var4] = (int)(this.coeffs[var2] >> var3 & 2047L);
         var3 += 12;
         if (var3 >= 60) {
            var3 = 0;
            ++var2;
         }
      }

      return new IntegerPolynomial(var1);
   }
}

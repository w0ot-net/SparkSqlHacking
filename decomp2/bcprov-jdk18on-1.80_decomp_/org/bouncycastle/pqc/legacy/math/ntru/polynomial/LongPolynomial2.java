package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

import org.bouncycastle.util.Arrays;

public class LongPolynomial2 {
   private long[] coeffs;
   private int numCoeffs;

   public LongPolynomial2(IntegerPolynomial var1) {
      this.numCoeffs = var1.coeffs.length;
      this.coeffs = new long[(this.numCoeffs + 1) / 2];
      int var2 = 0;

      for(int var3 = 0; var3 < this.numCoeffs; ++var2) {
         int var4;
         for(var4 = var1.coeffs[var3++]; var4 < 0; var4 += 2048) {
         }

         long var5;
         for(var5 = var3 < this.numCoeffs ? (long)var1.coeffs[var3++] : 0L; var5 < 0L; var5 += 2048L) {
         }

         this.coeffs[var2] = (long)var4 + (var5 << 24);
      }

   }

   private LongPolynomial2(long[] var1) {
      this.coeffs = var1;
   }

   private LongPolynomial2(int var1) {
      this.coeffs = new long[var1];
   }

   public LongPolynomial2 mult(LongPolynomial2 var1) {
      int var2 = this.coeffs.length;
      if (var1.coeffs.length == var2 && this.numCoeffs == var1.numCoeffs) {
         LongPolynomial2 var3 = this.multRecursive(var1);
         if (var3.coeffs.length > var2) {
            if (this.numCoeffs % 2 == 0) {
               for(int var4 = var2; var4 < var3.coeffs.length; ++var4) {
                  var3.coeffs[var4 - var2] = var3.coeffs[var4 - var2] + var3.coeffs[var4] & 34342963199L;
               }

               var3.coeffs = Arrays.copyOf(var3.coeffs, var2);
            } else {
               for(int var6 = var2; var6 < var3.coeffs.length; ++var6) {
                  var3.coeffs[var6 - var2] += var3.coeffs[var6 - 1] >> 24;
                  var3.coeffs[var6 - var2] += (var3.coeffs[var6] & 2047L) << 24;
                  long[] var10000 = var3.coeffs;
                  var10000[var6 - var2] &= 34342963199L;
               }

               var3.coeffs = Arrays.copyOf(var3.coeffs, var2);
               long[] var7 = var3.coeffs;
               int var10001 = var3.coeffs.length - 1;
               var7[var10001] &= 2047L;
            }
         }

         var3 = new LongPolynomial2(var3.coeffs);
         var3.numCoeffs = this.numCoeffs;
         return var3;
      } else {
         throw new IllegalArgumentException("Number of coefficients must be the same");
      }
   }

   public IntegerPolynomial toIntegerPolynomial() {
      int[] var1 = new int[this.numCoeffs];
      int var2 = 0;

      for(int var3 = 0; var3 < this.coeffs.length; ++var3) {
         var1[var2++] = (int)(this.coeffs[var3] & 2047L);
         if (var2 < this.numCoeffs) {
            var1[var2++] = (int)(this.coeffs[var3] >> 24 & 2047L);
         }
      }

      return new IntegerPolynomial(var1);
   }

   private LongPolynomial2 multRecursive(LongPolynomial2 var1) {
      long[] var2 = this.coeffs;
      long[] var3 = var1.coeffs;
      int var4 = var1.coeffs.length;
      if (var4 <= 32) {
         int var17 = 2 * var4;
         LongPolynomial2 var18 = new LongPolynomial2(new long[var17]);

         for(int var19 = 0; var19 < var17; ++var19) {
            for(int var20 = Math.max(0, var19 - var4 + 1); var20 <= Math.min(var19, var4 - 1); ++var20) {
               long var21 = var2[var19 - var20] * var3[var20];
               long var22 = var21 & 34342961152L + (var21 & 2047L);
               long var23 = var21 >>> 48 & 2047L;
               var18.coeffs[var19] = var18.coeffs[var19] + var22 & 34342963199L;
               var18.coeffs[var19 + 1] = var18.coeffs[var19 + 1] + var23 & 34342963199L;
            }
         }

         return var18;
      } else {
         int var5 = var4 / 2;
         LongPolynomial2 var6 = new LongPolynomial2(Arrays.copyOf(var2, var5));
         LongPolynomial2 var7 = new LongPolynomial2(Arrays.copyOfRange(var2, var5, var4));
         LongPolynomial2 var8 = new LongPolynomial2(Arrays.copyOf(var3, var5));
         LongPolynomial2 var9 = new LongPolynomial2(Arrays.copyOfRange(var3, var5, var4));
         LongPolynomial2 var10 = (LongPolynomial2)var6.clone();
         var10.add(var7);
         LongPolynomial2 var11 = (LongPolynomial2)var8.clone();
         var11.add(var9);
         LongPolynomial2 var12 = var6.multRecursive(var8);
         LongPolynomial2 var13 = var7.multRecursive(var9);
         LongPolynomial2 var14 = var10.multRecursive(var11);
         var14.sub(var12);
         var14.sub(var13);
         LongPolynomial2 var15 = new LongPolynomial2(2 * var4);

         for(int var16 = 0; var16 < var12.coeffs.length; ++var16) {
            var15.coeffs[var16] = var12.coeffs[var16] & 34342963199L;
         }

         for(int var24 = 0; var24 < var14.coeffs.length; ++var24) {
            var15.coeffs[var5 + var24] = var15.coeffs[var5 + var24] + var14.coeffs[var24] & 34342963199L;
         }

         for(int var25 = 0; var25 < var13.coeffs.length; ++var25) {
            var15.coeffs[2 * var5 + var25] = var15.coeffs[2 * var5 + var25] + var13.coeffs[var25] & 34342963199L;
         }

         return var15;
      }
   }

   private void add(LongPolynomial2 var1) {
      if (var1.coeffs.length > this.coeffs.length) {
         this.coeffs = Arrays.copyOf(this.coeffs, var1.coeffs.length);
      }

      for(int var2 = 0; var2 < var1.coeffs.length; ++var2) {
         this.coeffs[var2] = this.coeffs[var2] + var1.coeffs[var2] & 34342963199L;
      }

   }

   private void sub(LongPolynomial2 var1) {
      if (var1.coeffs.length > this.coeffs.length) {
         this.coeffs = Arrays.copyOf(this.coeffs, var1.coeffs.length);
      }

      for(int var2 = 0; var2 < var1.coeffs.length; ++var2) {
         this.coeffs[var2] = 140737496743936L + this.coeffs[var2] - var1.coeffs[var2] & 34342963199L;
      }

   }

   public void subAnd(LongPolynomial2 var1, int var2) {
      long var3 = ((long)var2 << 24) + (long)var2;

      for(int var5 = 0; var5 < var1.coeffs.length; ++var5) {
         this.coeffs[var5] = 140737496743936L + this.coeffs[var5] - var1.coeffs[var5] & var3;
      }

   }

   public void mult2And(int var1) {
      long var2 = ((long)var1 << 24) + (long)var1;

      for(int var4 = 0; var4 < this.coeffs.length; ++var4) {
         this.coeffs[var4] = this.coeffs[var4] << 1 & var2;
      }

   }

   public Object clone() {
      LongPolynomial2 var1 = new LongPolynomial2((long[])this.coeffs.clone());
      var1.numCoeffs = this.numCoeffs;
      return var1;
   }

   public boolean equals(Object var1) {
      return var1 instanceof LongPolynomial2 ? Arrays.areEqual(this.coeffs, ((LongPolynomial2)var1).coeffs) : false;
   }
}

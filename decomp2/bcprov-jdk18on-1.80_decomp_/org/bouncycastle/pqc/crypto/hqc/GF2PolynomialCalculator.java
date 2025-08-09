package org.bouncycastle.pqc.crypto.hqc;

class GF2PolynomialCalculator {
   private final int VEC_N_SIZE_64;
   private final int PARAM_N;
   private final long RED_MASK;

   GF2PolynomialCalculator(int var1, int var2, long var3) {
      this.VEC_N_SIZE_64 = var1;
      this.PARAM_N = var2;
      this.RED_MASK = var3;
   }

   protected void multLongs(long[] var1, long[] var2, long[] var3) {
      long[] var4 = new long[this.VEC_N_SIZE_64 << 3];
      long[] var5 = new long[(this.VEC_N_SIZE_64 << 1) + 1];
      this.karatsuba(var5, 0, var2, 0, var3, 0, this.VEC_N_SIZE_64, var4, 0);
      this.reduce(var1, var5);
   }

   private void base_mul(long[] var1, int var2, long var3, long var5) {
      long var7 = 0L;
      long var9 = 0L;
      long[] var13 = new long[16];
      long[] var14 = new long[4];
      var13[0] = 0L;
      var13[1] = var5 & 1152921504606846975L;
      var13[2] = var13[1] << 1;
      var13[3] = var13[2] ^ var13[1];
      var13[4] = var13[2] << 1;
      var13[5] = var13[4] ^ var13[1];
      var13[6] = var13[3] << 1;
      var13[7] = var13[6] ^ var13[1];
      var13[8] = var13[4] << 1;
      var13[9] = var13[8] ^ var13[1];
      var13[10] = var13[5] << 1;
      var13[11] = var13[10] ^ var13[1];
      var13[12] = var13[6] << 1;
      var13[13] = var13[12] ^ var13[1];
      var13[14] = var13[7] << 1;
      var13[15] = var13[14] ^ var13[1];
      long var11 = 0L;
      long var15 = var3 & 15L;

      for(int var17 = 0; var17 < 16; ++var17) {
         long var18 = var15 - (long)var17;
         var11 ^= var13[var17] & -(1L - ((var18 | -var18) >>> 63));
      }

      var9 = var11;
      var7 = 0L;

      for(byte var34 = 4; var34 < 64; var34 = (byte)(var34 + 4)) {
         var11 = 0L;
         long var35 = var3 >> var34 & 15L;

         for(int var20 = 0; var20 < 16; ++var20) {
            long var21 = var35 - (long)var20;
            var11 ^= var13[var20] & -(1L - ((var21 | -var21) >>> 63));
         }

         var9 ^= var11 << var34;
         var7 ^= var11 >>> 64 - var34;
      }

      var14[0] = -(var5 >> 60 & 1L);
      var14[1] = -(var5 >> 61 & 1L);
      var14[2] = -(var5 >> 62 & 1L);
      var14[3] = -(var5 >> 63 & 1L);
      var9 ^= var3 << 60 & var14[0];
      var7 ^= var3 >>> 4 & var14[0];
      var9 ^= var3 << 61 & var14[1];
      var7 ^= var3 >>> 3 & var14[1];
      var9 ^= var3 << 62 & var14[2];
      var7 ^= var3 >>> 2 & var14[2];
      var9 ^= var3 << 63 & var14[3];
      var7 ^= var3 >>> 1 & var14[3];
      var1[0 + var2] = var9;
      var1[1 + var2] = var7;
   }

   private void karatsuba_add1(long[] var1, int var2, long[] var3, int var4, long[] var5, int var6, long[] var7, int var8, int var9, int var10) {
      for(int var11 = 0; var11 < var10; ++var11) {
         var1[var11 + var2] = var5[var11 + var6] ^ var5[var11 + var9 + var6];
         var3[var11 + var4] = var7[var11 + var8] ^ var7[var11 + var9 + var8];
      }

      if (var10 < var9) {
         var1[var10 + var2] = var5[var10 + var6];
         var3[var10 + var4] = var7[var10 + var8];
      }

   }

   private void karatsuba_add2(long[] var1, int var2, long[] var3, int var4, long[] var5, int var6, int var7, int var8) {
      for(int var9 = 0; var9 < 2 * var7; ++var9) {
         var3[var9 + var4] ^= var1[var9 + var2];
      }

      for(int var10 = 0; var10 < 2 * var8; ++var10) {
         var3[var10 + var4] ^= var5[var10 + var6];
      }

      for(int var11 = 0; var11 < 2 * var7; ++var11) {
         var1[var11 + var7 + var2] ^= var3[var11 + var4];
      }

   }

   private void karatsuba(long[] var1, int var2, long[] var3, int var4, long[] var5, int var6, int var7, long[] var8, int var9) {
      if (var7 == 1) {
         this.base_mul(var1, var2, var3[0 + var4], var5[0 + var6]);
      } else {
         int var11 = var7 / 2;
         int var10 = (var7 + 1) / 2;
         int var14 = var9;
         int var15 = var9 + var10;
         int var16 = var15 + var10;
         int var17 = var2 + var10 * 2;
         var9 += 4 * var10;
         int var12 = var4 + var10;
         int var13 = var6 + var10;
         this.karatsuba(var1, var2, var3, var4, var5, var6, var10, var8, var9);
         this.karatsuba(var1, var17, var3, var12, var5, var13, var11, var8, var9);
         this.karatsuba_add1(var8, var14, var8, var15, var3, var4, var5, var6, var10, var11);
         this.karatsuba(var8, var16, var8, var14, var8, var15, var10, var8, var9);
         this.karatsuba_add2(var1, var2, var8, var16, var1, var17, var10, var11);
      }
   }

   private void reduce(long[] var1, long[] var2) {
      for(int var3 = 0; var3 < this.VEC_N_SIZE_64; ++var3) {
         long var4 = var2[var3 + this.VEC_N_SIZE_64 - 1] >>> (this.PARAM_N & 63);
         long var6 = var2[var3 + this.VEC_N_SIZE_64] << (int)(64L - ((long)this.PARAM_N & 63L));
         var1[var3] = var2[var3] ^ var4 ^ var6;
      }

      int var10001 = this.VEC_N_SIZE_64 - 1;
      var1[var10001] &= this.RED_MASK;
   }

   static void addLongs(long[] var0, long[] var1, long[] var2) {
      for(int var3 = 0; var3 < var1.length; ++var3) {
         var0[var3] = var1[var3] ^ var2[var3];
      }

   }
}

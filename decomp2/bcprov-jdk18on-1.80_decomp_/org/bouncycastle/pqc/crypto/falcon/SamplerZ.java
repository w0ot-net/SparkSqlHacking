package org.bouncycastle.pqc.crypto.falcon;

class SamplerZ {
   FPREngine fpr = new FPREngine();

   int sample(SamplerCtx var1, FalconFPR var2, FalconFPR var3) {
      return this.sampler(var1, var2, var3);
   }

   int gaussian0_sampler(FalconRNG var1) {
      int[] var2 = new int[]{10745844, 3068844, 3741698, 5559083, 1580863, 8248194, 2260429, 13669192, 2736639, 708981, 4421575, 10046180, 169348, 7122675, 4136815, 30538, 13063405, 7650655, 4132, 14505003, 7826148, 417, 16768101, 11363290, 31, 8444042, 8086568, 1, 12844466, 265321, 0, 1232676, 13644283, 0, 38047, 9111839, 0, 870, 6138264, 0, 14, 12545723, 0, 0, 3104126, 0, 0, 28824, 0, 0, 198, 0, 0, 1};
      long var7 = var1.prng_get_u64();
      int var6 = var1.prng_get_u8() & 255;
      int var3 = (int)var7 & 16777215;
      int var4 = (int)(var7 >>> 24) & 16777215;
      int var5 = (int)(var7 >>> 48) | var6 << 16;
      int var10 = 0;

      for(int var9 = 0; var9 < var2.length; var9 += 3) {
         int var11 = var2[var9 + 2];
         int var12 = var2[var9 + 1];
         int var13 = var2[var9 + 0];
         int var14 = var3 - var11 >>> 31;
         var14 = var4 - var12 - var14 >>> 31;
         var14 = var5 - var13 - var14 >>> 31;
         var10 += var14;
      }

      return var10;
   }

   int BerExp(FalconRNG var1, FalconFPR var2, FalconFPR var3) {
      int var4 = (int)this.fpr.fpr_trunc(this.fpr.fpr_mul(var2, this.fpr.fpr_inv_log2));
      FalconFPR var6 = this.fpr.fpr_sub(var2, this.fpr.fpr_mul(this.fpr.fpr_of((long)var4), this.fpr.fpr_log2));
      int var7 = var4 ^ (var4 ^ 63) & -(63 - var4 >>> 31);
      long var9 = (this.fpr.fpr_expm_p63(var6, var3) << 1) - 1L >>> var7;
      int var5 = 64;

      int var8;
      do {
         var5 -= 8;
         var8 = (var1.prng_get_u8() & 255) - ((int)(var9 >>> var5) & 255);
      } while(var8 == 0 && var5 > 0);

      return var8 >>> 31;
   }

   int sampler(SamplerCtx var1, FalconFPR var2, FalconFPR var3) {
      SamplerCtx var4 = var1;
      int var5 = (int)this.fpr.fpr_floor(var2);
      FalconFPR var6 = this.fpr.fpr_sub(var2, this.fpr.fpr_of((long)var5));
      FalconFPR var7 = this.fpr.fpr_half(this.fpr.fpr_sqr(var3));
      FalconFPR var8 = this.fpr.fpr_mul(var3, var1.sigma_min);

      int var10;
      FalconFPR var13;
      do {
         int var9 = this.gaussian0_sampler(var4.p);
         int var11 = var4.p.prng_get_u8() & 255 & 1;
         var10 = var11 + ((var11 << 1) - 1) * var9;
         var13 = this.fpr.fpr_mul(this.fpr.fpr_sqr(this.fpr.fpr_sub(this.fpr.fpr_of((long)var10), var6)), var7);
         var13 = this.fpr.fpr_sub(var13, this.fpr.fpr_mul(this.fpr.fpr_of((long)(var9 * var9)), this.fpr.fpr_inv_2sqrsigma0));
      } while(this.BerExp(var4.p, var13, var8) == 0);

      return var5 + var10;
   }
}

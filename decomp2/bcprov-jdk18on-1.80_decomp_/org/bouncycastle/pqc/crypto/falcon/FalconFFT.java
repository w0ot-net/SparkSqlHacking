package org.bouncycastle.pqc.crypto.falcon;

class FalconFFT {
   FPREngine fpr = new FPREngine();

   ComplexNumberWrapper FPC_ADD(FalconFPR var1, FalconFPR var2, FalconFPR var3, FalconFPR var4) {
      FalconFPR var5 = this.fpr.fpr_add(var1, var3);
      FalconFPR var6 = this.fpr.fpr_add(var2, var4);
      return new ComplexNumberWrapper(var5, var6);
   }

   ComplexNumberWrapper FPC_SUB(FalconFPR var1, FalconFPR var2, FalconFPR var3, FalconFPR var4) {
      FalconFPR var5 = this.fpr.fpr_sub(var1, var3);
      FalconFPR var6 = this.fpr.fpr_sub(var2, var4);
      return new ComplexNumberWrapper(var5, var6);
   }

   ComplexNumberWrapper FPC_MUL(FalconFPR var1, FalconFPR var2, FalconFPR var3, FalconFPR var4) {
      FalconFPR var9 = this.fpr.fpr_sub(this.fpr.fpr_mul(var1, var3), this.fpr.fpr_mul(var2, var4));
      FalconFPR var10 = this.fpr.fpr_add(this.fpr.fpr_mul(var1, var4), this.fpr.fpr_mul(var2, var3));
      return new ComplexNumberWrapper(var9, var10);
   }

   ComplexNumberWrapper FPC_SQR(FalconFPR var1, FalconFPR var2) {
      FalconFPR var5 = this.fpr.fpr_sub(this.fpr.fpr_sqr(var1), this.fpr.fpr_sqr(var2));
      FalconFPR var6 = this.fpr.fpr_double(this.fpr.fpr_mul(var1, var2));
      return new ComplexNumberWrapper(var5, var6);
   }

   ComplexNumberWrapper FPC_INV(FalconFPR var1, FalconFPR var2) {
      FalconFPR var7 = this.fpr.fpr_add(this.fpr.fpr_sqr(var1), this.fpr.fpr_sqr(var2));
      var7 = this.fpr.fpr_inv(var7);
      FalconFPR var5 = this.fpr.fpr_mul(var1, var7);
      FalconFPR var6 = this.fpr.fpr_mul(this.fpr.fpr_neg(var2), var7);
      return new ComplexNumberWrapper(var5, var6);
   }

   ComplexNumberWrapper FPC_DIV(FalconFPR var1, FalconFPR var2, FalconFPR var3, FalconFPR var4) {
      FalconFPR var11 = this.fpr.fpr_add(this.fpr.fpr_sqr(var3), this.fpr.fpr_sqr(var4));
      var11 = this.fpr.fpr_inv(var11);
      FalconFPR var7 = this.fpr.fpr_mul(var3, var11);
      FalconFPR var8 = this.fpr.fpr_mul(this.fpr.fpr_neg(var4), var11);
      FalconFPR var9 = this.fpr.fpr_sub(this.fpr.fpr_mul(var1, var7), this.fpr.fpr_mul(var2, var8));
      FalconFPR var10 = this.fpr.fpr_add(this.fpr.fpr_mul(var1, var8), this.fpr.fpr_mul(var2, var7));
      return new ComplexNumberWrapper(var9, var10);
   }

   void FFT(FalconFPR[] var1, int var2, int var3) {
      int var6 = 1 << var3;
      int var7 = var6 >> 1;
      int var5 = var7;
      int var4 = 1;

      for(int var8 = 2; var4 < var3; var8 <<= 1) {
         int var9 = var5 >> 1;
         int var10 = var8 >> 1;
         int var11 = 0;

         for(int var12 = 0; var11 < var10; var12 += var5) {
            int var14 = var12 + var9;
            FalconFPR var15 = this.fpr.fpr_gm_tab[(var8 + var11 << 1) + 0];
            FalconFPR var16 = this.fpr.fpr_gm_tab[(var8 + var11 << 1) + 1];

            for(int var13 = var12; var13 < var14; ++var13) {
               FalconFPR var17 = var1[var2 + var13];
               FalconFPR var18 = var1[var2 + var13 + var7];
               FalconFPR var19 = var1[var2 + var13 + var9];
               FalconFPR var20 = var1[var2 + var13 + var9 + var7];
               ComplexNumberWrapper var21 = this.FPC_MUL(var19, var20, var15, var16);
               var19 = var21.re;
               var20 = var21.im;
               var21 = this.FPC_ADD(var17, var18, var19, var20);
               var1[var2 + var13] = var21.re;
               var1[var2 + var13 + var7] = var21.im;
               var21 = this.FPC_SUB(var17, var18, var19, var20);
               var1[var2 + var13 + var9] = var21.re;
               var1[var2 + var13 + var9 + var7] = var21.im;
            }

            ++var11;
         }

         var5 = var9;
         ++var4;
      }

   }

   void iFFT(FalconFPR[] var1, int var2, int var3) {
      int var5 = 1 << var3;
      int var7 = 1;
      int var8 = var5;
      int var6 = var5 >> 1;

      for(int var4 = var3; var4 > 1; --var4) {
         int var9 = var8 >> 1;
         int var10 = var7 << 1;
         int var11 = 0;

         for(int var12 = 0; var12 < var6; var12 += var10) {
            int var14 = var12 + var7;
            FalconFPR var15 = this.fpr.fpr_gm_tab[(var9 + var11 << 1) + 0];
            FalconFPR var16 = this.fpr.fpr_neg(this.fpr.fpr_gm_tab[(var9 + var11 << 1) + 1]);

            for(int var13 = var12; var13 < var14; ++var13) {
               FalconFPR var17 = var1[var2 + var13];
               FalconFPR var18 = var1[var2 + var13 + var6];
               FalconFPR var19 = var1[var2 + var13 + var7];
               FalconFPR var20 = var1[var2 + var13 + var7 + var6];
               ComplexNumberWrapper var21 = this.FPC_ADD(var17, var18, var19, var20);
               var1[var2 + var13] = var21.re;
               var1[var2 + var13 + var6] = var21.im;
               var21 = this.FPC_SUB(var17, var18, var19, var20);
               var17 = var21.re;
               var18 = var21.im;
               var21 = this.FPC_MUL(var17, var18, var15, var16);
               var1[var2 + var13 + var7] = var21.re;
               var1[var2 + var13 + var7 + var6] = var21.im;
            }

            ++var11;
         }

         var7 = var10;
         var8 = var9;
      }

      if (var3 > 0) {
         FalconFPR var23 = this.fpr.fpr_p2_tab[var3];

         for(int var22 = 0; var22 < var5; ++var22) {
            var1[var2 + var22] = this.fpr.fpr_mul(var1[var2 + var22], var23);
         }
      }

   }

   void poly_add(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, int var5) {
      int var6 = 1 << var5;

      for(int var7 = 0; var7 < var6; ++var7) {
         var1[var2 + var7] = this.fpr.fpr_add(var1[var2 + var7], var3[var4 + var7]);
      }

   }

   void poly_sub(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, int var5) {
      int var6 = 1 << var5;

      for(int var7 = 0; var7 < var6; ++var7) {
         var1[var2 + var7] = this.fpr.fpr_sub(var1[var2 + var7], var3[var4 + var7]);
      }

   }

   void poly_neg(FalconFPR[] var1, int var2, int var3) {
      int var4 = 1 << var3;

      for(int var5 = 0; var5 < var4; ++var5) {
         var1[var2 + var5] = this.fpr.fpr_neg(var1[var2 + var5]);
      }

   }

   void poly_adj_fft(FalconFPR[] var1, int var2, int var3) {
      int var4 = 1 << var3;

      for(int var5 = var4 >> 1; var5 < var4; ++var5) {
         var1[var2 + var5] = this.fpr.fpr_neg(var1[var2 + var5]);
      }

   }

   void poly_mul_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, int var5) {
      int var6 = 1 << var5;
      int var7 = var6 >> 1;

      for(int var8 = 0; var8 < var7; ++var8) {
         FalconFPR var9 = var1[var2 + var8];
         FalconFPR var10 = var1[var2 + var8 + var7];
         FalconFPR var11 = var3[var4 + var8];
         FalconFPR var12 = var3[var4 + var8 + var7];
         ComplexNumberWrapper var13 = this.FPC_MUL(var9, var10, var11, var12);
         var1[var2 + var8] = var13.re;
         var1[var2 + var8 + var7] = var13.im;
      }

   }

   void poly_muladj_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, int var5) {
      int var6 = 1 << var5;
      int var7 = var6 >> 1;

      for(int var8 = 0; var8 < var7; ++var8) {
         FalconFPR var9 = var1[var2 + var8];
         FalconFPR var10 = var1[var2 + var8 + var7];
         FalconFPR var11 = var3[var4 + var8];
         FalconFPR var12 = this.fpr.fpr_neg(var3[var4 + var8 + var7]);
         ComplexNumberWrapper var13 = this.FPC_MUL(var9, var10, var11, var12);
         var1[var2 + var8] = var13.re;
         var1[var2 + var8 + var7] = var13.im;
      }

   }

   void poly_mulselfadj_fft(FalconFPR[] var1, int var2, int var3) {
      int var4 = 1 << var3;
      int var5 = var4 >> 1;

      for(int var6 = 0; var6 < var5; ++var6) {
         FalconFPR var7 = var1[var2 + var6];
         FalconFPR var8 = var1[var2 + var6 + var5];
         var1[var2 + var6] = this.fpr.fpr_add(this.fpr.fpr_sqr(var7), this.fpr.fpr_sqr(var8));
         var1[var2 + var6 + var5] = this.fpr.fpr_zero;
      }

   }

   void poly_mulconst(FalconFPR[] var1, int var2, FalconFPR var3, int var4) {
      int var5 = 1 << var4;

      for(int var6 = 0; var6 < var5; ++var6) {
         var1[var2 + var6] = this.fpr.fpr_mul(var1[var2 + var6], var3);
      }

   }

   void poly_div_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, int var5) {
      int var6 = 1 << var5;
      int var7 = var6 >> 1;

      for(int var8 = 0; var8 < var7; ++var8) {
         FalconFPR var9 = var1[var2 + var8];
         FalconFPR var10 = var1[var2 + var8 + var7];
         FalconFPR var11 = var3[var4 + var8];
         FalconFPR var12 = var3[var4 + var8 + var7];
         ComplexNumberWrapper var13 = this.FPC_DIV(var9, var10, var11, var12);
         var1[var2 + var8] = var13.re;
         var1[var2 + var8 + var7] = var13.im;
      }

   }

   void poly_invnorm2_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, int var7) {
      int var8 = 1 << var7;
      int var9 = var8 >> 1;

      for(int var10 = 0; var10 < var9; ++var10) {
         FalconFPR var11 = var3[var4 + var10];
         FalconFPR var12 = var3[var4 + var10 + var9];
         FalconFPR var13 = var5[var6 + var10];
         FalconFPR var14 = var5[var6 + var10 + var9];
         var1[var2 + var10] = this.fpr.fpr_inv(this.fpr.fpr_add(this.fpr.fpr_add(this.fpr.fpr_sqr(var11), this.fpr.fpr_sqr(var12)), this.fpr.fpr_add(this.fpr.fpr_sqr(var13), this.fpr.fpr_sqr(var14))));
      }

   }

   void poly_add_muladj_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, FalconFPR[] var7, int var8, FalconFPR[] var9, int var10, int var11) {
      int var12 = 1 << var11;
      int var13 = var12 >> 1;

      for(int var14 = 0; var14 < var13; ++var14) {
         FalconFPR var15 = var3[var4 + var14];
         FalconFPR var16 = var3[var4 + var14 + var13];
         FalconFPR var17 = var5[var6 + var14];
         FalconFPR var18 = var5[var6 + var14 + var13];
         FalconFPR var19 = var7[var8 + var14];
         FalconFPR var20 = var7[var8 + var14 + var13];
         FalconFPR var21 = var9[var10 + var14];
         FalconFPR var22 = var9[var10 + var14 + var13];
         ComplexNumberWrapper var27 = this.FPC_MUL(var15, var16, var19, this.fpr.fpr_neg(var20));
         FalconFPR var23 = var27.re;
         FalconFPR var24 = var27.im;
         var27 = this.FPC_MUL(var17, var18, var21, this.fpr.fpr_neg(var22));
         FalconFPR var25 = var27.re;
         FalconFPR var26 = var27.im;
         var1[var2 + var14] = this.fpr.fpr_add(var23, var25);
         var1[var2 + var14 + var13] = this.fpr.fpr_add(var24, var26);
      }

   }

   void poly_mul_autoadj_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, int var5) {
      int var6 = 1 << var5;
      int var7 = var6 >> 1;

      for(int var8 = 0; var8 < var7; ++var8) {
         var1[var2 + var8] = this.fpr.fpr_mul(var1[var2 + var8], var3[var4 + var8]);
         var1[var2 + var8 + var7] = this.fpr.fpr_mul(var1[var2 + var8 + var7], var3[var4 + var8]);
      }

   }

   void poly_div_autoadj_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, int var5) {
      int var6 = 1 << var5;
      int var7 = var6 >> 1;

      for(int var8 = 0; var8 < var7; ++var8) {
         FalconFPR var9 = this.fpr.fpr_inv(var3[var4 + var8]);
         var1[var2 + var8] = this.fpr.fpr_mul(var1[var2 + var8], var9);
         var1[var2 + var8 + var7] = this.fpr.fpr_mul(var1[var2 + var8 + var7], var9);
      }

   }

   void poly_LDL_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, int var7) {
      int var8 = 1 << var7;
      int var9 = var8 >> 1;

      for(int var10 = 0; var10 < var9; ++var10) {
         FalconFPR var11 = var1[var2 + var10];
         FalconFPR var12 = var1[var2 + var10 + var9];
         FalconFPR var13 = var3[var4 + var10];
         FalconFPR var14 = var3[var4 + var10 + var9];
         FalconFPR var15 = var5[var6 + var10];
         FalconFPR var16 = var5[var6 + var10 + var9];
         ComplexNumberWrapper var19 = this.FPC_DIV(var13, var14, var11, var12);
         FalconFPR var17 = var19.re;
         FalconFPR var18 = var19.im;
         var19 = this.FPC_MUL(var17, var18, var13, this.fpr.fpr_neg(var14));
         var13 = var19.re;
         var14 = var19.im;
         var19 = this.FPC_SUB(var15, var16, var13, var14);
         var5[var6 + var10] = var19.re;
         var5[var6 + var10 + var9] = var19.im;
         var3[var4 + var10] = var17;
         var3[var4 + var10 + var9] = this.fpr.fpr_neg(var18);
      }

   }

   void poly_LDLmv_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, FalconFPR[] var7, int var8, FalconFPR[] var9, int var10, int var11) {
      int var12 = 1 << var11;
      int var13 = var12 >> 1;

      for(int var14 = 0; var14 < var13; ++var14) {
         FalconFPR var15 = var5[var6 + var14];
         FalconFPR var16 = var5[var6 + var14 + var13];
         FalconFPR var17 = var7[var8 + var14];
         FalconFPR var18 = var7[var8 + var14 + var13];
         FalconFPR var19 = var9[var10 + var14];
         FalconFPR var20 = var9[var10 + var14 + var13];
         ComplexNumberWrapper var23 = this.FPC_DIV(var17, var18, var15, var16);
         FalconFPR var21 = var23.re;
         FalconFPR var22 = var23.im;
         var23 = this.FPC_MUL(var21, var22, var17, this.fpr.fpr_neg(var18));
         var17 = var23.re;
         var18 = var23.im;
         var23 = this.FPC_SUB(var19, var20, var17, var18);
         var1[var2 + var14] = var23.re;
         var1[var2 + var14 + var13] = var23.im;
         var3[var4 + var14] = var21;
         var3[var4 + var14 + var13] = this.fpr.fpr_neg(var22);
      }

   }

   void poly_split_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, int var7) {
      int var8 = 1 << var7;
      int var9 = var8 >> 1;
      int var10 = var9 >> 1;
      var1[var2 + 0] = var5[var6 + 0];
      var3[var4 + 0] = var5[var6 + var9];

      for(int var11 = 0; var11 < var10; ++var11) {
         FalconFPR var12 = var5[var6 + (var11 << 1) + 0];
         FalconFPR var13 = var5[var6 + (var11 << 1) + 0 + var9];
         FalconFPR var14 = var5[var6 + (var11 << 1) + 1];
         FalconFPR var15 = var5[var6 + (var11 << 1) + 1 + var9];
         ComplexNumberWrapper var18 = this.FPC_ADD(var12, var13, var14, var15);
         FalconFPR var16 = var18.re;
         FalconFPR var17 = var18.im;
         var1[var2 + var11] = this.fpr.fpr_half(var16);
         var1[var2 + var11 + var10] = this.fpr.fpr_half(var17);
         var18 = this.FPC_SUB(var12, var13, var14, var15);
         var16 = var18.re;
         var17 = var18.im;
         var18 = this.FPC_MUL(var16, var17, this.fpr.fpr_gm_tab[(var11 + var9 << 1) + 0], this.fpr.fpr_neg(this.fpr.fpr_gm_tab[(var11 + var9 << 1) + 1]));
         var16 = var18.re;
         var17 = var18.im;
         var3[var4 + var11] = this.fpr.fpr_half(var16);
         var3[var4 + var11 + var10] = this.fpr.fpr_half(var17);
      }

   }

   void poly_merge_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, int var7) {
      int var8 = 1 << var7;
      int var9 = var8 >> 1;
      int var10 = var9 >> 1;
      var1[var2 + 0] = var3[var4 + 0];
      var1[var2 + var9] = var5[var6 + 0];

      for(int var11 = 0; var11 < var10; ++var11) {
         FalconFPR var12 = var3[var4 + var11];
         FalconFPR var13 = var3[var4 + var11 + var10];
         ComplexNumberWrapper var18 = this.FPC_MUL(var5[var6 + var11], var5[var6 + var11 + var10], this.fpr.fpr_gm_tab[(var11 + var9 << 1) + 0], this.fpr.fpr_gm_tab[(var11 + var9 << 1) + 1]);
         FalconFPR var14 = var18.re;
         FalconFPR var15 = var18.im;
         var18 = this.FPC_ADD(var12, var13, var14, var15);
         FalconFPR var16 = var18.re;
         FalconFPR var17 = var18.im;
         var1[var2 + (var11 << 1) + 0] = var16;
         var1[var2 + (var11 << 1) + 0 + var9] = var17;
         var18 = this.FPC_SUB(var12, var13, var14, var15);
         var16 = var18.re;
         var17 = var18.im;
         var1[var2 + (var11 << 1) + 1] = var16;
         var1[var2 + (var11 << 1) + 1 + var9] = var17;
      }

   }
}

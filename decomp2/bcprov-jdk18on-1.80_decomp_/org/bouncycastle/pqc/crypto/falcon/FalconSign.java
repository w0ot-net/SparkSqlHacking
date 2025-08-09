package org.bouncycastle.pqc.crypto.falcon;

class FalconSign {
   FPREngine fpr = new FPREngine();
   FalconFFT fft = new FalconFFT();
   FalconCommon common = new FalconCommon();

   private static int MKN(int var0) {
      return 1 << var0;
   }

   int ffLDL_treesize(int var1) {
      return var1 + 1 << var1;
   }

   void ffLDL_fft_inner(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, int var7, FalconFPR[] var8, int var9) {
      int var10 = MKN(var7);
      if (var10 == 1) {
         var1[var2 + 0] = var3[var4 + 0];
      } else {
         int var11 = var10 >> 1;
         this.fft.poly_LDLmv_fft(var8, var9, var1, var2, var3, var4, var5, var6, var3, var4, var7);
         this.fft.poly_split_fft(var5, var6, var5, var6 + var11, var3, var4, var7);
         this.fft.poly_split_fft(var3, var4, var3, var4 + var11, var8, var9, var7);
         this.ffLDL_fft_inner(var1, var2 + var10, var5, var6, var5, var6 + var11, var7 - 1, var8, var9);
         this.ffLDL_fft_inner(var1, var2 + var10 + this.ffLDL_treesize(var7 - 1), var3, var4, var3, var4 + var11, var7 - 1, var8, var9);
      }
   }

   void ffLDL_fft(FalconFPR[] var1, int var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, FalconFPR[] var7, int var8, int var9, FalconFPR[] var10, int var11) {
      int var12 = MKN(var9);
      if (var12 == 1) {
         var1[var2 + 0] = var3[var4 + 0];
      } else {
         int var13 = var12 >> 1;
         int var14 = var11;
         int var15 = var11 + var12;
         var11 += var12 << 1;
         System.arraycopy(var3, var4, var10, var14, var12);
         this.fft.poly_LDLmv_fft(var10, var15, var1, var2, var3, var4, var5, var6, var7, var8, var9);
         this.fft.poly_split_fft(var10, var11, var10, var11 + var13, var10, var14, var9);
         this.fft.poly_split_fft(var10, var14, var10, var14 + var13, var10, var15, var9);
         System.arraycopy(var10, var11, var10, var15, var12);
         this.ffLDL_fft_inner(var1, var2 + var12, var10, var15, var10, var15 + var13, var9 - 1, var10, var11);
         this.ffLDL_fft_inner(var1, var2 + var12 + this.ffLDL_treesize(var9 - 1), var10, var14, var10, var14 + var13, var9 - 1, var10, var11);
      }
   }

   void ffLDL_binary_normalize(FalconFPR[] var1, int var2, int var3, int var4) {
      int var5 = MKN(var4);
      if (var5 == 1) {
         var1[var2 + 0] = this.fpr.fpr_mul(this.fpr.fpr_sqrt(var1[var2 + 0]), this.fpr.fpr_inv_sigma[var3]);
      } else {
         this.ffLDL_binary_normalize(var1, var2 + var5, var3, var4 - 1);
         this.ffLDL_binary_normalize(var1, var2 + var5 + this.ffLDL_treesize(var4 - 1), var3, var4 - 1);
      }

   }

   void smallints_to_fpr(FalconFPR[] var1, int var2, byte[] var3, int var4, int var5) {
      int var6 = MKN(var5);

      for(int var7 = 0; var7 < var6; ++var7) {
         var1[var2 + var7] = this.fpr.fpr_of((long)var3[var4 + var7]);
      }

   }

   int skoff_b00(int var1) {
      return 0;
   }

   int skoff_b01(int var1) {
      return MKN(var1);
   }

   int skoff_b10(int var1) {
      return 2 * MKN(var1);
   }

   int skoff_b11(int var1) {
      return 3 * MKN(var1);
   }

   int skoff_tree(int var1) {
      return 4 * MKN(var1);
   }

   void expand_privkey(FalconFPR[] var1, int var2, byte[] var3, int var4, byte[] var5, int var6, byte[] var7, int var8, byte[] var9, int var10, int var11, FalconFPR[] var12, int var13) {
      int var14 = MKN(var11);
      int var19 = var2 + this.skoff_b00(var11);
      int var20 = var2 + this.skoff_b01(var11);
      int var21 = var2 + this.skoff_b10(var11);
      int var22 = var2 + this.skoff_b11(var11);
      int var27 = var2 + this.skoff_tree(var11);
      this.smallints_to_fpr(var1, var20, var3, var4, var11);
      this.smallints_to_fpr(var1, var19, var5, var6, var11);
      this.smallints_to_fpr(var1, var22, var7, var8, var11);
      this.smallints_to_fpr(var1, var21, var9, var10, var11);
      this.fft.FFT(var1, var20, var11);
      this.fft.FFT(var1, var19, var11);
      this.fft.FFT(var1, var22, var11);
      this.fft.FFT(var1, var21, var11);
      this.fft.poly_neg(var1, var20, var11);
      this.fft.poly_neg(var1, var22, var11);
      int var24 = var13 + var14;
      int var25 = var24 + var14;
      int var26 = var25 + var14;
      System.arraycopy(var1, var19, var12, var13, var14);
      this.fft.poly_mulselfadj_fft(var12, var13, var11);
      System.arraycopy(var1, var20, var12, var26, var14);
      this.fft.poly_mulselfadj_fft(var12, var26, var11);
      this.fft.poly_add(var12, var13, var12, var26, var11);
      System.arraycopy(var1, var19, var12, var24, var14);
      this.fft.poly_muladj_fft(var12, var24, var1, var21, var11);
      System.arraycopy(var1, var20, var12, var26, var14);
      this.fft.poly_muladj_fft(var12, var26, var1, var22, var11);
      this.fft.poly_add(var12, var24, var12, var26, var11);
      System.arraycopy(var1, var21, var12, var25, var14);
      this.fft.poly_mulselfadj_fft(var12, var25, var11);
      System.arraycopy(var1, var22, var12, var26, var14);
      this.fft.poly_mulselfadj_fft(var12, var26, var11);
      this.fft.poly_add(var12, var25, var12, var26, var11);
      this.ffLDL_fft(var1, var27, var12, var13, var12, var24, var12, var25, var11, var12, var26);
      this.ffLDL_binary_normalize(var1, var27, var11, var11);
   }

   void ffSampling_fft_dyntree(SamplerZ var1, SamplerCtx var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, FalconFPR[] var7, int var8, FalconFPR[] var9, int var10, FalconFPR[] var11, int var12, int var13, int var14, FalconFPR[] var15, int var16) {
      if (var14 == 0) {
         FalconFPR var21 = var7[var8 + 0];
         var21 = this.fpr.fpr_mul(this.fpr.fpr_sqrt(var21), this.fpr.fpr_inv_sigma[var13]);
         var3[var4 + 0] = this.fpr.fpr_of((long)var1.sample(var2, var3[var4 + 0], var21));
         var5[var6 + 0] = this.fpr.fpr_of((long)var1.sample(var2, var5[var6 + 0], var21));
      } else {
         int var17 = 1 << var14;
         int var18 = var17 >> 1;
         this.fft.poly_LDL_fft(var7, var8, var9, var10, var11, var12, var14);
         this.fft.poly_split_fft(var15, var16, var15, var16 + var18, var7, var8, var14);
         System.arraycopy(var15, var16, var7, var8, var17);
         this.fft.poly_split_fft(var15, var16, var15, var16 + var18, var11, var12, var14);
         System.arraycopy(var15, var16, var11, var12, var17);
         System.arraycopy(var9, var10, var15, var16, var17);
         System.arraycopy(var7, var8, var9, var10, var18);
         System.arraycopy(var11, var12, var9, var10 + var18, var18);
         int var20 = var16 + var17;
         this.fft.poly_split_fft(var15, var20, var15, var20 + var18, var5, var6, var14);
         this.ffSampling_fft_dyntree(var1, var2, var15, var20, var15, var20 + var18, var11, var12, var11, var12 + var18, var9, var10 + var18, var13, var14 - 1, var15, var20 + var17);
         this.fft.poly_merge_fft(var15, var16 + (var17 << 1), var15, var20, var15, var20 + var18, var14);
         System.arraycopy(var5, var6, var15, var20, var17);
         this.fft.poly_sub(var15, var20, var15, var16 + (var17 << 1), var14);
         System.arraycopy(var15, var16 + (var17 << 1), var5, var6, var17);
         this.fft.poly_mul_fft(var15, var16, var15, var20, var14);
         this.fft.poly_add(var3, var4, var15, var16, var14);
         this.fft.poly_split_fft(var15, var16, var15, var16 + var18, var3, var4, var14);
         this.ffSampling_fft_dyntree(var1, var2, var15, var16, var15, var16 + var18, var7, var8, var7, var8 + var18, var9, var10, var13, var14 - 1, var15, var16 + var17);
         this.fft.poly_merge_fft(var3, var4, var15, var16, var15, var16 + var18, var14);
      }
   }

   void ffSampling_fft(SamplerZ var1, SamplerCtx var2, FalconFPR[] var3, int var4, FalconFPR[] var5, int var6, FalconFPR[] var7, int var8, FalconFPR[] var9, int var10, FalconFPR[] var11, int var12, int var13, FalconFPR[] var14, int var15) {
      if (var13 == 2) {
         int var35 = var8 + 4;
         int var36 = var8 + 8;
         FalconFPR var86 = var11[var12 + 0];
         FalconFPR var93 = var11[var12 + 2];
         FalconFPR var31 = var11[var12 + 1];
         FalconFPR var32 = var11[var12 + 3];
         FalconFPR var33 = this.fpr.fpr_add(var86, var31);
         FalconFPR var34 = this.fpr.fpr_add(var93, var32);
         FalconFPR var50 = this.fpr.fpr_half(var33);
         FalconFPR var58 = this.fpr.fpr_half(var34);
         var33 = this.fpr.fpr_sub(var86, var31);
         var34 = this.fpr.fpr_sub(var93, var32);
         FalconFPR var66 = this.fpr.fpr_mul(this.fpr.fpr_add(var33, var34), this.fpr.fpr_invsqrt8);
         FalconFPR var74 = this.fpr.fpr_mul(this.fpr.fpr_sub(var34, var33), this.fpr.fpr_invsqrt8);
         FalconFPR var38 = var66;
         FalconFPR var43 = var74;
         FalconFPR var82 = var7[var36 + 3];
         var66 = this.fpr.fpr_of((long)var1.sample(var2, var66, var82));
         var74 = this.fpr.fpr_of((long)var1.sample(var2, var74, var82));
         var86 = this.fpr.fpr_sub(var38, var66);
         var93 = this.fpr.fpr_sub(var43, var74);
         var31 = var7[var36 + 0];
         var32 = var7[var36 + 1];
         var33 = this.fpr.fpr_sub(this.fpr.fpr_mul(var86, var31), this.fpr.fpr_mul(var93, var32));
         var34 = this.fpr.fpr_add(this.fpr.fpr_mul(var86, var32), this.fpr.fpr_mul(var93, var31));
         var38 = this.fpr.fpr_add(var33, var50);
         var43 = this.fpr.fpr_add(var34, var58);
         var82 = var7[var36 + 2];
         var50 = this.fpr.fpr_of((long)var1.sample(var2, var38, var82));
         var58 = this.fpr.fpr_of((long)var1.sample(var2, var43, var82));
         var86 = var50;
         var93 = var58;
         var33 = this.fpr.fpr_mul(this.fpr.fpr_sub(var66, var74), this.fpr.fpr_invsqrt2);
         var34 = this.fpr.fpr_mul(this.fpr.fpr_add(var66, var74), this.fpr.fpr_invsqrt2);
         FalconFPR var52;
         var5[var6 + 0] = var52 = this.fpr.fpr_add(var50, var33);
         var5[var6 + 2] = var66 = this.fpr.fpr_add(var58, var34);
         var5[var6 + 1] = var58 = this.fpr.fpr_sub(var86, var33);
         var5[var6 + 3] = var74 = this.fpr.fpr_sub(var93, var34);
         var50 = this.fpr.fpr_sub(var11[var12 + 0], var52);
         var58 = this.fpr.fpr_sub(var11[var12 + 1], var58);
         var66 = this.fpr.fpr_sub(var11[var12 + 2], var66);
         var74 = this.fpr.fpr_sub(var11[var12 + 3], var74);
         var86 = var50;
         var31 = var7[var8 + 0];
         var32 = var7[var8 + 2];
         var50 = this.fpr.fpr_sub(this.fpr.fpr_mul(var50, var31), this.fpr.fpr_mul(var66, var32));
         var66 = this.fpr.fpr_add(this.fpr.fpr_mul(var86, var32), this.fpr.fpr_mul(var66, var31));
         var86 = var58;
         var31 = var7[var8 + 1];
         var32 = var7[var8 + 3];
         var58 = this.fpr.fpr_sub(this.fpr.fpr_mul(var58, var31), this.fpr.fpr_mul(var74, var32));
         var74 = this.fpr.fpr_add(this.fpr.fpr_mul(var86, var32), this.fpr.fpr_mul(var74, var31));
         var50 = this.fpr.fpr_add(var50, var9[var10 + 0]);
         var58 = this.fpr.fpr_add(var58, var9[var10 + 1]);
         var66 = this.fpr.fpr_add(var66, var9[var10 + 2]);
         var74 = this.fpr.fpr_add(var74, var9[var10 + 3]);
         var86 = var50;
         var31 = var58;
         var33 = this.fpr.fpr_add(var50, var58);
         var34 = this.fpr.fpr_add(var66, var74);
         var50 = this.fpr.fpr_half(var33);
         var58 = this.fpr.fpr_half(var34);
         var33 = this.fpr.fpr_sub(var86, var31);
         var34 = this.fpr.fpr_sub(var66, var74);
         var66 = this.fpr.fpr_mul(this.fpr.fpr_add(var33, var34), this.fpr.fpr_invsqrt8);
         var74 = this.fpr.fpr_mul(this.fpr.fpr_sub(var34, var33), this.fpr.fpr_invsqrt8);
         var38 = var66;
         var43 = var74;
         var82 = var7[var35 + 3];
         FalconFPR var47;
         var66 = var47 = this.fpr.fpr_of((long)var1.sample(var2, var66, var82));
         FalconFPR var48;
         var74 = var48 = this.fpr.fpr_of((long)var1.sample(var2, var74, var82));
         var86 = this.fpr.fpr_sub(var38, var47);
         var93 = this.fpr.fpr_sub(var43, var48);
         var31 = var7[var35 + 0];
         var32 = var7[var35 + 1];
         var33 = this.fpr.fpr_sub(this.fpr.fpr_mul(var86, var31), this.fpr.fpr_mul(var93, var32));
         var34 = this.fpr.fpr_add(this.fpr.fpr_mul(var86, var32), this.fpr.fpr_mul(var93, var31));
         var38 = this.fpr.fpr_add(var33, var50);
         var43 = this.fpr.fpr_add(var34, var58);
         var82 = var7[var35 + 2];
         var50 = this.fpr.fpr_of((long)var1.sample(var2, var38, var82));
         var58 = this.fpr.fpr_of((long)var1.sample(var2, var43, var82));
         var33 = this.fpr.fpr_mul(this.fpr.fpr_sub(var66, var74), this.fpr.fpr_invsqrt2);
         var34 = this.fpr.fpr_mul(this.fpr.fpr_add(var66, var74), this.fpr.fpr_invsqrt2);
         var3[var4 + 0] = this.fpr.fpr_add(var50, var33);
         var3[var4 + 2] = this.fpr.fpr_add(var58, var34);
         var3[var4 + 1] = this.fpr.fpr_sub(var50, var33);
         var3[var4 + 3] = this.fpr.fpr_sub(var58, var34);
      } else if (var13 == 1) {
         FalconFPR var20 = var11[var12 + 0];
         FalconFPR var21 = var11[var12 + 1];
         FalconFPR var24 = var7[var8 + 3];
         FalconFPR var22;
         var5[var6 + 0] = var22 = this.fpr.fpr_of((long)var1.sample(var2, var20, var24));
         FalconFPR var23;
         var5[var6 + 1] = var23 = this.fpr.fpr_of((long)var1.sample(var2, var21, var24));
         FalconFPR var25 = this.fpr.fpr_sub(var20, var22);
         FalconFPR var26 = this.fpr.fpr_sub(var21, var23);
         FalconFPR var27 = var7[var8 + 0];
         FalconFPR var28 = var7[var8 + 1];
         FalconFPR var29 = this.fpr.fpr_sub(this.fpr.fpr_mul(var25, var27), this.fpr.fpr_mul(var26, var28));
         FalconFPR var30 = this.fpr.fpr_add(this.fpr.fpr_mul(var25, var28), this.fpr.fpr_mul(var26, var27));
         var20 = this.fpr.fpr_add(var29, var9[var10 + 0]);
         var21 = this.fpr.fpr_add(var30, var9[var10 + 1]);
         var24 = var7[var8 + 2];
         var3[var4 + 0] = this.fpr.fpr_of((long)var1.sample(var2, var20, var24));
         var3[var4 + 1] = this.fpr.fpr_of((long)var1.sample(var2, var21, var24));
      } else {
         int var16 = 1 << var13;
         int var17 = var16 >> 1;
         int var18 = var8 + var16;
         int var19 = var8 + var16 + this.ffLDL_treesize(var13 - 1);
         this.fft.poly_split_fft(var5, var6, var5, var6 + var17, var11, var12, var13);
         this.ffSampling_fft(var1, var2, var14, var15, var14, var15 + var17, var7, var19, var5, var6, var5, var6 + var17, var13 - 1, var14, var15 + var16);
         this.fft.poly_merge_fft(var5, var6, var14, var15, var14, var15 + var17, var13);
         System.arraycopy(var11, var12, var14, var15, var16);
         this.fft.poly_sub(var14, var15, var5, var6, var13);
         this.fft.poly_mul_fft(var14, var15, var7, var8, var13);
         this.fft.poly_add(var14, var15, var9, var10, var13);
         this.fft.poly_split_fft(var3, var4, var3, var4 + var17, var14, var15, var13);
         this.ffSampling_fft(var1, var2, var14, var15, var14, var15 + var17, var7, var18, var3, var4, var3, var4 + var17, var13 - 1, var14, var15 + var16);
         this.fft.poly_merge_fft(var3, var4, var14, var15, var14, var15 + var17, var13);
      }
   }

   int do_sign_tree(SamplerZ var1, SamplerCtx var2, short[] var3, int var4, FalconFPR[] var5, int var6, short[] var7, int var8, int var9, FalconFPR[] var10, int var11) {
      int var12 = MKN(var9);
      int var14 = var11;
      int var15 = var11 + var12;
      int var18 = var6 + this.skoff_b00(var9);
      int var19 = var6 + this.skoff_b01(var9);
      int var20 = var6 + this.skoff_b10(var9);
      int var21 = var6 + this.skoff_b11(var9);
      int var22 = var6 + this.skoff_tree(var9);

      for(int var13 = 0; var13 < var12; ++var13) {
         var10[var14 + var13] = this.fpr.fpr_of((long)var7[var8 + var13]);
      }

      this.fft.FFT(var10, var14, var9);
      FalconFPR var23 = this.fpr.fpr_inverse_of_q;
      System.arraycopy(var10, var14, var10, var15, var12);
      this.fft.poly_mul_fft(var10, var15, var5, var19, var9);
      this.fft.poly_mulconst(var10, var15, this.fpr.fpr_neg(var23), var9);
      this.fft.poly_mul_fft(var10, var14, var5, var21, var9);
      this.fft.poly_mulconst(var10, var14, var23, var9);
      int var16 = var15 + var12;
      int var17 = var16 + var12;
      this.ffSampling_fft(var1, var2, var10, var16, var10, var17, var5, var22, var10, var14, var10, var15, var9, var10, var17 + var12);
      System.arraycopy(var10, var16, var10, var14, var12);
      System.arraycopy(var10, var17, var10, var15, var12);
      this.fft.poly_mul_fft(var10, var16, var5, var18, var9);
      this.fft.poly_mul_fft(var10, var17, var5, var20, var9);
      this.fft.poly_add(var10, var16, var10, var17, var9);
      System.arraycopy(var10, var14, var10, var17, var12);
      this.fft.poly_mul_fft(var10, var17, var5, var19, var9);
      System.arraycopy(var10, var16, var10, var14, var12);
      this.fft.poly_mul_fft(var10, var15, var5, var21, var9);
      this.fft.poly_add(var10, var15, var10, var17, var9);
      this.fft.iFFT(var10, var14, var9);
      this.fft.iFFT(var10, var15, var9);
      short[] var26 = new short[var12];
      int var24 = 0;
      int var25 = 0;

      for(int var29 = 0; var29 < var12; ++var29) {
         int var28 = (var7[var8 + var29] & '\uffff') - (int)this.fpr.fpr_rint(var10[var14 + var29]);
         var24 += var28 * var28;
         var25 |= var24;
         var26[var29] = (short)var28;
      }

      var24 |= -(var25 >>> 31);
      short[] var27 = new short[var12];

      for(int var30 = 0; var30 < var12; ++var30) {
         var27[var30] = (short)((int)(-this.fpr.fpr_rint(var10[var15 + var30])));
      }

      if (this.common.is_short_half(var24, var27, 0, var9) != 0) {
         System.arraycopy(var27, 0, var3, var4, var12);
         System.arraycopy(var26, 0, var10, var11, var12);
         return 1;
      } else {
         return 0;
      }
   }

   int do_sign_dyn(SamplerZ var1, SamplerCtx var2, short[] var3, int var4, byte[] var5, int var6, byte[] var7, int var8, byte[] var9, int var10, byte[] var11, int var12, short[] var13, int var14, int var15, FalconFPR[] var16, int var17) {
      int var18 = MKN(var15);
      int var25 = var17 + var18;
      int var26 = var25 + var18;
      int var27 = var26 + var18;
      this.smallints_to_fpr(var16, var25, var5, var6, var15);
      this.smallints_to_fpr(var16, var17, var7, var8, var15);
      this.smallints_to_fpr(var16, var27, var9, var10, var15);
      this.smallints_to_fpr(var16, var26, var11, var12, var15);
      this.fft.FFT(var16, var25, var15);
      this.fft.FFT(var16, var17, var15);
      this.fft.FFT(var16, var27, var15);
      this.fft.FFT(var16, var26, var15);
      this.fft.poly_neg(var16, var25, var15);
      this.fft.poly_neg(var16, var27, var15);
      int var20 = var27 + var18;
      int var21 = var20 + var18;
      System.arraycopy(var16, var25, var16, var20, var18);
      this.fft.poly_mulselfadj_fft(var16, var20, var15);
      System.arraycopy(var16, var17, var16, var21, var18);
      this.fft.poly_muladj_fft(var16, var21, var16, var26, var15);
      this.fft.poly_mulselfadj_fft(var16, var17, var15);
      this.fft.poly_add(var16, var17, var16, var20, var15);
      System.arraycopy(var16, var25, var16, var20, var18);
      this.fft.poly_muladj_fft(var16, var25, var16, var27, var15);
      this.fft.poly_add(var16, var25, var16, var21, var15);
      this.fft.poly_mulselfadj_fft(var16, var26, var15);
      System.arraycopy(var16, var27, var16, var21, var18);
      this.fft.poly_mulselfadj_fft(var16, var21, var15);
      this.fft.poly_add(var16, var26, var16, var21, var15);
      var20 += var18;
      var21 = var20 + var18;

      for(int var19 = 0; var19 < var18; ++var19) {
         var16[var20 + var19] = this.fpr.fpr_of((long)var13[var14 + var19]);
      }

      this.fft.FFT(var16, var20, var15);
      FalconFPR var31 = this.fpr.fpr_inverse_of_q;
      System.arraycopy(var16, var20, var16, var21, var18);
      this.fft.poly_mul_fft(var16, var21, var16, var20, var15);
      this.fft.poly_mulconst(var16, var21, this.fpr.fpr_neg(var31), var15);
      this.fft.poly_mul_fft(var16, var20, var16, var27, var15);
      this.fft.poly_mulconst(var16, var20, var31, var15);
      System.arraycopy(var16, var20, var16, var27, 2 * var18);
      var20 = var26 + var18;
      var21 = var20 + var18;
      this.ffSampling_fft_dyntree(var1, var2, var16, var20, var16, var21, var16, var17, var16, var25, var16, var26, var15, var15, var16, var21 + var18);
      var25 = var17 + var18;
      var26 = var25 + var18;
      var27 = var26 + var18;
      System.arraycopy(var16, var20, var16, var27 + var18, var18 * 2);
      var20 = var27 + var18;
      var21 = var20 + var18;
      this.smallints_to_fpr(var16, var25, var5, var6, var15);
      this.smallints_to_fpr(var16, var17, var7, var8, var15);
      this.smallints_to_fpr(var16, var27, var9, var10, var15);
      this.smallints_to_fpr(var16, var26, var11, var12, var15);
      this.fft.FFT(var16, var25, var15);
      this.fft.FFT(var16, var17, var15);
      this.fft.FFT(var16, var27, var15);
      this.fft.FFT(var16, var26, var15);
      this.fft.poly_neg(var16, var25, var15);
      this.fft.poly_neg(var16, var27, var15);
      int var22 = var21 + var18;
      int var23 = var22 + var18;
      System.arraycopy(var16, var20, var16, var22, var18);
      System.arraycopy(var16, var21, var16, var23, var18);
      this.fft.poly_mul_fft(var16, var22, var16, var17, var15);
      this.fft.poly_mul_fft(var16, var23, var16, var26, var15);
      this.fft.poly_add(var16, var22, var16, var23, var15);
      System.arraycopy(var16, var20, var16, var23, var18);
      this.fft.poly_mul_fft(var16, var23, var16, var25, var15);
      System.arraycopy(var16, var22, var16, var20, var18);
      this.fft.poly_mul_fft(var16, var21, var16, var27, var15);
      this.fft.poly_add(var16, var21, var16, var23, var15);
      this.fft.iFFT(var16, var20, var15);
      this.fft.iFFT(var16, var21, var15);
      short[] var34 = new short[var18];
      int var32 = 0;
      int var33 = 0;

      for(int var37 = 0; var37 < var18; ++var37) {
         int var36 = (var13[var14 + var37] & '\uffff') - (int)this.fpr.fpr_rint(var16[var20 + var37]);
         var32 += var36 * var36;
         var33 |= var32;
         var34[var37] = (short)var36;
      }

      var32 |= -(var33 >>> 31);
      short[] var35 = new short[var18];

      for(int var38 = 0; var38 < var18; ++var38) {
         var35[var38] = (short)((int)(-this.fpr.fpr_rint(var16[var21 + var38])));
      }

      if (this.common.is_short_half(var32, var35, 0, var15) != 0) {
         System.arraycopy(var35, 0, var3, var4, var18);
         return 1;
      } else {
         return 0;
      }
   }

   void sign_tree(short[] var1, int var2, SHAKE256 var3, FalconFPR[] var4, int var5, short[] var6, int var7, int var8, FalconFPR[] var9, int var10) {
      int var11 = var10;

      SamplerCtx var12;
      SamplerZ var13;
      do {
         var12 = new SamplerCtx();
         var13 = new SamplerZ();
         var12.sigma_min = this.fpr.fpr_sigma_min[var8];
         var12.p.prng_init(var3);
      } while(this.do_sign_tree(var13, var12, var1, var2, var4, var5, var6, var7, var8, var9, var11) == 0);

   }

   void sign_dyn(short[] var1, int var2, SHAKE256 var3, byte[] var4, int var5, byte[] var6, int var7, byte[] var8, int var9, byte[] var10, int var11, short[] var12, int var13, int var14, FalconFPR[] var15, int var16) {
      int var17 = var16;

      SamplerCtx var18;
      SamplerZ var19;
      do {
         var18 = new SamplerCtx();
         var19 = new SamplerZ();
         var18.sigma_min = this.fpr.fpr_sigma_min[var14];
         var18.p.prng_init(var3);
      } while(this.do_sign_dyn(var19, var18, var1, var2, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var17) == 0);

   }
}

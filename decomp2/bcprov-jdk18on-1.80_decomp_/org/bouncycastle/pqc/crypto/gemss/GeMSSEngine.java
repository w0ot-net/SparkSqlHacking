package org.bouncycastle.pqc.crypto.gemss;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Pack;

class GeMSSEngine {
   private SecureRandom random;
   final int HFEn;
   final int HFEv;
   final int HFEDELTA;
   final int NB_ITE;
   final int HFEDeg;
   final int HFEDegI;
   final int HFEDegJ;
   final int HFEnv;
   final int HFEm;
   final int NB_BITS_UINT = 64;
   final int HFEnq;
   final int HFEnr;
   int HFE_odd_degree;
   int NB_WORD_GFqn;
   int NB_WORD_GF2nv;
   int NB_MONOMIAL_VINEGAR;
   int NB_MONOMIAL_PK;
   final int HFEnvq;
   final int HFEnvr;
   int LTRIANGULAR_NV_SIZE;
   final int LTRIANGULAR_N_SIZE;
   final int SIZE_SEED_SK;
   final int NB_WORD_MUL;
   int NB_WORD_MMUL;
   int MQv_GFqn_SIZE;
   final boolean ENABLED_REMOVE_ODD_DEGREE;
   final int MATRIXnv_SIZE;
   final int HFEmq;
   final int HFEmr;
   int NB_WORD_GF2m;
   final int HFEvq;
   final int HFEvr;
   final int NB_WORD_GFqv;
   final int HFEmq8;
   final int HFEmr8;
   final int NB_BYTES_GFqm;
   final int ACCESS_last_equations8;
   final int NB_BYTES_EQUATION;
   final int HFENr8;
   final int NB_WORD_UNCOMP_EQ;
   final int HFENr8c;
   final int LOST_BITS;
   final int NB_WORD_GF2nvm;
   final int SIZE_SIGN_UNCOMPRESSED;
   final int SIZE_DIGEST;
   final int SIZE_DIGEST_UINT;
   final int HFEnvr8;
   final int NB_BYTES_GFqnv;
   final int VAL_BITS_M;
   final long MASK_GF2m;
   final int LEN_UNROLLED_64 = 4;
   int NB_COEFS_HFEPOLY;
   int NB_UINT_HFEVPOLY;
   final int MATRIXn_SIZE;
   final long MASK_GF2n;
   final int NB_BYTES_GFqn;
   private int buffer;
   final int SIZE_ROW;
   final int ShakeBitStrength;
   final int Sha3BitStrength;
   SHA3Digest sha3Digest;
   final int MLv_GFqn_SIZE;
   int II;
   int POW_II;
   int KP;
   int KX;
   int HFEn_1rightmost;
   int HFEn1h_rightmost;
   Mul_GF2x mul;
   Rem_GF2n rem;
   Pointer Buffer_NB_WORD_MUL;
   Pointer Buffer_NB_WORD_GFqn;

   public GeMSSEngine(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.HFEn = var2;
      this.HFEv = var3;
      this.HFEDELTA = var4;
      this.NB_ITE = var5;
      this.HFEDeg = var6;
      this.HFEDegI = var7;
      this.HFEDegJ = var8;
      this.NB_BYTES_GFqn = (var2 >>> 3) + ((var2 & 7) != 0 ? 1 : 0);
      this.SIZE_ROW = var7 + 1;
      this.HFEnv = var2 + var3;
      this.HFEnq = var2 >>> 6;
      this.HFEnr = var2 & 63;
      this.HFEnvq = this.HFEnv >>> 6;
      this.HFEnvr = this.HFEnv & 63;
      this.SIZE_SEED_SK = var1 >>> 3;
      this.NB_WORD_MUL = (var2 - 1 << 1 >>> 6) + 1;
      switch (this.NB_WORD_MUL) {
         case 6:
            this.mul = new Mul_GF2x.Mul6();
         case 7:
         case 8:
         case 10:
         case 11:
         case 14:
         case 15:
         case 16:
         default:
            break;
         case 9:
            this.mul = new Mul_GF2x.Mul9();
            break;
         case 12:
            this.mul = new Mul_GF2x.Mul12();
            break;
         case 13:
            this.mul = new Mul_GF2x.Mul13();
            break;
         case 17:
            this.mul = new Mul_GF2x.Mul17();
      }

      int var9 = var2 & 63;
      int var10 = 64 - var9;
      this.HFEm = var2 - var4;
      this.HFEmq = this.HFEm >>> 6;
      this.HFEmr = this.HFEm & 63;
      this.HFEvq = var3 >>> 6;
      this.HFEvr = var3 & 63;
      this.NB_WORD_GFqv = this.HFEvr != 0 ? this.HFEvq + 1 : this.HFEvq;
      this.HFEmq8 = this.HFEm >>> 3;
      this.HFEmr8 = this.HFEm & 7;
      this.NB_BYTES_GFqm = this.HFEmq8 + (this.HFEmr8 != 0 ? 1 : 0);
      this.NB_WORD_UNCOMP_EQ = (this.HFEnvq * (this.HFEnvq + 1) >>> 1) * 64 + (this.HFEnvq + 1) * this.HFEnvr;
      this.HFEnvr8 = this.HFEnv & 7;
      this.NB_BYTES_GFqnv = (this.HFEnv >>> 3) + (this.HFEnvr8 != 0 ? 1 : 0);
      this.VAL_BITS_M = Math.min(var4 + var3, 8 - this.HFEmr8);
      this.MASK_GF2m = GeMSSUtils.maskUINT(this.HFEmr);
      this.MASK_GF2n = GeMSSUtils.maskUINT(this.HFEnr);
      this.NB_WORD_GFqn = this.HFEnq + (this.HFEnr != 0 ? 1 : 0);
      this.LTRIANGULAR_N_SIZE = (this.HFEnq * (this.HFEnq + 1) >>> 1) * 64 + this.NB_WORD_GFqn * this.HFEnr;
      this.MATRIXn_SIZE = var2 * this.NB_WORD_GFqn;
      this.NB_WORD_GF2nv = this.HFEnvq + (this.HFEnvr != 0 ? 1 : 0);
      this.MATRIXnv_SIZE = this.HFEnv * this.NB_WORD_GF2nv;
      this.LTRIANGULAR_NV_SIZE = (this.HFEnvq * (this.HFEnvq + 1) >>> 1) * 64 + this.NB_WORD_GF2nv * this.HFEnvr;
      this.NB_MONOMIAL_VINEGAR = (var3 * (var3 + 1) >>> 1) + 1;
      this.NB_MONOMIAL_PK = (this.HFEnv * (this.HFEnv + 1) >>> 1) + 1;
      this.MQv_GFqn_SIZE = this.NB_MONOMIAL_VINEGAR * this.NB_WORD_GFqn;
      this.ACCESS_last_equations8 = this.NB_MONOMIAL_PK * this.HFEmq8;
      this.NB_BYTES_EQUATION = this.NB_MONOMIAL_PK + 7 >>> 3;
      this.HFENr8 = this.NB_MONOMIAL_PK & 7;
      this.HFENr8c = 8 - this.HFENr8 & 7;
      this.LOST_BITS = (this.HFEmr8 - 1) * this.HFENr8c;
      this.NB_WORD_MMUL = (var2 - 1 << 1 >>> 6) + 1;
      byte var11 = 0;
      byte var12 = 0;
      int var14 = 0;
      int var15 = 0;
      short var13;
      switch (var2) {
         case 174:
            var13 = 13;
            break;
         case 175:
            var13 = 16;
            break;
         case 177:
            var13 = 8;
            break;
         case 178:
            var13 = 31;
            break;
         case 265:
            var13 = 42;
            break;
         case 266:
            var13 = 47;
            break;
         case 268:
            var13 = 25;
            break;
         case 270:
            var13 = 53;
            break;
         case 271:
            var13 = 58;
            break;
         case 354:
            var13 = 99;
            break;
         case 358:
            var13 = 57;
            break;
         case 364:
            var13 = 9;
            break;
         case 366:
            var13 = 29;
            break;
         case 402:
            var13 = 171;
            break;
         case 537:
            var13 = 10;
            var12 = 2;
            var11 = 1;
            break;
         case 544:
            var13 = 128;
            var12 = 3;
            var11 = 1;
            break;
         default:
            throw new IllegalArgumentException("error: need to add support for HFEn=" + var2);
      }

      if (var12 != 0) {
         var14 = 64 - var11;
         var15 = 64 - var12;
      }

      int var16 = 64 - (var13 & 63);
      if ((var6 & 1) == 0) {
         this.ENABLED_REMOVE_ODD_DEGREE = true;
         this.HFE_odd_degree = (1 << var7) + 1;
         if ((var6 & 1) != 0) {
            throw new IllegalArgumentException("HFEDeg is odd, so to remove the leading term would decrease the degree.");
         }

         if (this.HFE_odd_degree > var6) {
            throw new IllegalArgumentException("It is useless to remove 0 term.");
         }

         if (this.HFE_odd_degree <= 1) {
            throw new IllegalArgumentException("The case where the term X^3 is removing is not implemented.");
         }

         this.NB_COEFS_HFEPOLY = 2 + var8 + (var7 * (var7 - 1) >>> 1) + var7;
      } else {
         this.ENABLED_REMOVE_ODD_DEGREE = false;
         this.NB_COEFS_HFEPOLY = 2 + var8 + (var7 * (var7 + 1) >>> 1);
      }

      this.NB_WORD_GF2m = this.HFEmq + (this.HFEmr != 0 ? 1 : 0);
      this.NB_WORD_GF2nvm = this.NB_WORD_GF2nv - this.NB_WORD_GF2m + (this.HFEmr != 0 ? 1 : 0);
      this.SIZE_SIGN_UNCOMPRESSED = this.NB_WORD_GF2nv + (var5 - 1) * this.NB_WORD_GF2nvm;
      if (var1 <= 128) {
         this.SIZE_DIGEST = 32;
         this.SIZE_DIGEST_UINT = 4;
         this.ShakeBitStrength = 128;
         this.Sha3BitStrength = 256;
      } else if (var1 <= 192) {
         this.SIZE_DIGEST = 48;
         this.SIZE_DIGEST_UINT = 6;
         this.ShakeBitStrength = 256;
         this.Sha3BitStrength = 384;
      } else {
         this.SIZE_DIGEST = 64;
         this.SIZE_DIGEST_UINT = 8;
         this.ShakeBitStrength = 256;
         this.Sha3BitStrength = 512;
      }

      this.sha3Digest = new SHA3Digest(this.Sha3BitStrength);
      this.NB_UINT_HFEVPOLY = (this.NB_COEFS_HFEPOLY + (this.NB_MONOMIAL_VINEGAR - 1) + (var7 + 1) * var3) * this.NB_WORD_GFqn;
      this.MLv_GFqn_SIZE = (var3 + 1) * this.NB_WORD_GFqn;
      if (var6 <= 34 || var2 > 196 && var6 < 256) {
         if (var6 == 17) {
            this.II = 4;
         } else {
            this.II = 6;
         }

         this.POW_II = 1 << this.II;
         this.KP = (var6 >>> this.II) + (var6 % this.POW_II != 0 ? 1 : 0);
         this.KX = var6 - this.KP;
      }

      if (var12 != 0) {
         if (var2 == 544 && var13 == 128) {
            this.rem = new Rem_GF2n.REM544_PENTANOMIAL_K3_IS_128_GF2X(var11, var12, var9, var10, var14, var15, this.MASK_GF2n);
         } else {
            this.rem = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(var11, var12, var13, var9, var10, var14, var15, var16, this.MASK_GF2n);
         }
      } else if (var2 > 256 && var2 < 289 && var13 > 32 && var13 < 64) {
         this.rem = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(var13, var9, var10, var16, this.MASK_GF2n);
      } else if (var2 == 354) {
         this.rem = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(var13, var9, var10, var16, this.MASK_GF2n);
      } else if (var2 == 358) {
         this.rem = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(var13, var9, var10, var16, this.MASK_GF2n);
      } else if (var2 == 402) {
         this.rem = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(var13, var9, var10, var16, this.MASK_GF2n);
      } else {
         switch (this.NB_WORD_MUL) {
            case 6:
               this.rem = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(var13, var9, var10, var16, this.MASK_GF2n);
               break;
            case 9:
               this.rem = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(var13, var9, var10, var16, this.MASK_GF2n);
               break;
            case 12:
               this.rem = new Rem_GF2n.REM384_TRINOMIAL_GF2X(var13, var9, var10, var16, this.MASK_GF2n);
         }
      }

      this.Buffer_NB_WORD_MUL = new Pointer(this.NB_WORD_MUL);
      this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
      this.HFEn_1rightmost = 31;

      for(int var17 = var2 - 1; var17 >>> this.HFEn_1rightmost == 0; --this.HFEn_1rightmost) {
      }

      int var18 = var2 + 1 >>> 1;

      for(this.HFEn1h_rightmost = 31; var18 >>> this.HFEn1h_rightmost == 0; --this.HFEn1h_rightmost) {
      }

      --this.HFEn1h_rightmost;
   }

   void genSecretMQS_gf2_opt(Pointer var1, Pointer var2) {
      Pointer var8 = new Pointer(this.NB_WORD_GFqn);
      Pointer var17 = new Pointer((this.HFEDegI + 1) * (this.HFEv + 1) * this.NB_WORD_GFqn);
      Pointer var7 = new Pointer(var2, this.MQv_GFqn_SIZE);

      for(int var9 = 0; var9 <= this.HFEDegI; ++var9) {
         for(int var11 = 0; var11 <= this.HFEv; ++var11) {
            var17.copyFrom((var11 * (this.HFEDegI + 1) + var9) * this.NB_WORD_GFqn, var7, 0, this.NB_WORD_GFqn);
            var7.move(this.NB_WORD_GFqn);
         }

         var7.move(var9 * this.NB_WORD_GFqn);
      }

      Pointer var18 = new Pointer(this.SIZE_ROW * (this.HFEn - 1) * this.NB_WORD_GFqn);

      for(int var21 = 1; var21 < this.HFEn; ++var21) {
         var18.set(var21 >>> 6, 1L << (var21 & 63));

         for(int var10 = 0; var10 < this.HFEDegI; ++var10) {
            this.sqr_gf2n(var18, this.NB_WORD_GFqn, var18, 0);
            var18.move(this.NB_WORD_GFqn);
         }

         var18.move(this.NB_WORD_GFqn);
      }

      var18.indexReset();
      var1.copyFrom(var2, this.NB_WORD_GFqn);
      var2.move(this.MQv_GFqn_SIZE);
      var1.move(this.NB_WORD_GFqn);
      Pointer var19 = new Pointer(this.HFEDegI * this.HFEn * this.NB_WORD_GFqn);
      this.special_buffer(var19, var2, var18);
      Pointer var5 = new Pointer(var19);
      Pointer var6 = new Pointer(var19);
      var1.copyFrom(var6, this.NB_WORD_GFqn);
      var6.move(this.NB_WORD_GFqn);
      var1.setXorMatrix_NoMove(var6, this.NB_WORD_GFqn, this.HFEDegI - 1);
      var7.changeIndex(var17);
      var1.setXorMatrix(var7, this.NB_WORD_GFqn, this.HFEDegI + 1);
      Pointer var4 = new Pointer(var18, this.NB_WORD_GFqn);

      int var12;
      for(var12 = 1; var12 < this.HFEn; ++var12) {
         this.dotProduct_gf2n(var1, var4, var5, this.HFEDegI);
         var4.move(this.SIZE_ROW * this.NB_WORD_GFqn);
         var1.setXorMatrix(var6, this.NB_WORD_GFqn, this.HFEDegI);
      }

      while(var12 < this.HFEnv) {
         var1.copyFrom(var7, this.NB_WORD_GFqn);
         var7.move(this.NB_WORD_GFqn);
         var1.setXorMatrix(var7, this.NB_WORD_GFqn, this.HFEDegI);
         ++var12;
      }

      Pointer var3 = new Pointer(var18, this.NB_WORD_GFqn);
      Pointer var20 = new Pointer(this.NB_WORD_MUL);

      for(int var23 = 1; var23 < this.HFEn; ++var23) {
         var5.move(this.HFEDegI * this.NB_WORD_GFqn);
         var4.changeIndex(var3);
         var6.changeIndex(var5);
         this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, var17, new Pointer(var4, -this.NB_WORD_GFqn));

         for(int var22 = 1; var22 <= this.HFEDegI; ++var22) {
            var8.setRangeFromXor(0, var6, 0, var17, var22 * this.NB_WORD_GFqn, this.NB_WORD_GFqn);
            this.mul_xorrange(this.Buffer_NB_WORD_MUL, var8, var4);
            var6.move(this.NB_WORD_GFqn);
            var4.move(this.NB_WORD_GFqn);
         }

         var4.move(this.NB_WORD_GFqn);
         this.rem_gf2n(var1, 0, this.Buffer_NB_WORD_MUL);
         var1.move(this.NB_WORD_GFqn);

         for(var12 = var23 + 1; var12 < this.HFEn; ++var12) {
            int var13 = var4.getIndex();
            int var14 = var5.getIndex();
            int var15 = var3.getIndex();
            int var16 = var6.getIndex();
            this.mul_move(var20, var4, var5);
            this.for_mul_xorrange_move(var20, var4, var5, this.HFEDegI - 1);
            this.for_mul_xorrange_move(var20, var3, var6, this.HFEDegI);
            this.rem_gf2n(var1, 0, var20);
            var4.changeIndex(var13 + this.SIZE_ROW * this.NB_WORD_GFqn);
            var5.changeIndex(var14);
            var3.changeIndex(var15);
            var6.changeIndex(var16 + this.HFEDegI * this.NB_WORD_GFqn);
            var1.move(this.NB_WORD_GFqn);
         }

         var7.changeIndex(var17);
         var3.move(-this.NB_WORD_GFqn);

         while(var12 < this.HFEnv) {
            var7.move((this.HFEDegI + 1) * this.NB_WORD_GFqn);
            this.dotProduct_gf2n(var1, var3, var7, this.HFEDegI + 1);
            var1.move(this.NB_WORD_GFqn);
            ++var12;
         }

         var3.move(this.NB_WORD_GFqn + this.SIZE_ROW * this.NB_WORD_GFqn);
      }

      var2.move(this.NB_WORD_GFqn - this.MQv_GFqn_SIZE);
      var1.copyFrom(var2, this.NB_WORD_GFqn * (this.NB_MONOMIAL_VINEGAR - 1));
      var1.indexReset();
      var2.indexReset();
   }

   private void special_buffer(Pointer var1, Pointer var2, Pointer var3) {
      int var6 = var2.getIndex();
      var2.move(this.NB_WORD_GFqn * (this.HFEv + 1) << 1);
      var1.copyFrom(var2, this.NB_WORD_GFqn);
      var1.move(this.NB_WORD_GFqn);
      Pointer var7 = new Pointer(var2, this.NB_WORD_GFqn * (this.HFEv + 2));

      int var4;
      for(var4 = 2; var4 < this.SIZE_ROW - 1; ++var4) {
         this.copy_move_matrix_move(var1, var7, var4 - 1);
      }

      if (this.ENABLED_REMOVE_ODD_DEGREE) {
         while(var4 < this.SIZE_ROW - 1) {
            this.copy_move_matrix_move(var1, var7, var4 - 2);
            ++var4;
         }
      }

      var1.set1_gf2n(0, this.NB_WORD_GFqn);
      var1.setXorMatrix(var7, this.NB_WORD_GFqn, this.HFEDegJ);

      for(int var5 = 0; var5 < this.HFEn - 1; ++var5) {
         this.mul_gf2n(var1, var3, var2);
         var1.move(this.NB_WORD_GFqn);
         var7.changeIndex(var2, this.NB_WORD_GFqn * (this.HFEv + 2));

         for(var4 = 2; var4 < this.HFEDegI; ++var4) {
            this.dotproduct_move_move(var1, var7, var3, var4);
         }

         if (this.ENABLED_REMOVE_ODD_DEGREE) {
            var3.move(this.NB_WORD_GFqn);

            while(var4 < this.SIZE_ROW - 1) {
               this.dotproduct_move_move(var1, var7, var3, var4 - 1);
               ++var4;
            }

            var3.move(-this.NB_WORD_GFqn);
         }

         if (this.HFEDegJ == 0) {
            var1.copyFrom(var3, this.NB_WORD_GFqn);
            var1.move(this.NB_WORD_GFqn);
            var3.move(this.SIZE_ROW * this.NB_WORD_GFqn);
         } else {
            this.dotProduct_gf2n(var1, var3, var7, this.HFEDegJ);
            var3.move(this.HFEDegJ * this.NB_WORD_GFqn);
            var1.setXorRange_SelfMove(var3, this.NB_WORD_GFqn);
            var3.move((this.SIZE_ROW - this.HFEDegJ) * this.NB_WORD_GFqn);
         }
      }

      var1.indexReset();
      var2.changeIndex(var6);
      var3.indexReset();
   }

   private void copy_move_matrix_move(Pointer var1, Pointer var2, int var3) {
      var1.copyFrom(var2, this.NB_WORD_GFqn);
      var2.move(this.NB_WORD_GFqn);
      var1.setXorMatrix(var2, this.NB_WORD_GFqn, var3);
      var2.move(this.NB_WORD_GFqn * (this.HFEv + 1));
   }

   private void dotproduct_move_move(Pointer var1, Pointer var2, Pointer var3, int var4) {
      this.dotProduct_gf2n(var1, var3, var2, var4);
      var1.move(this.NB_WORD_GFqn);
      var2.move((var4 + this.HFEv + 1) * this.NB_WORD_GFqn);
   }

   private void dotProduct_gf2n(Pointer var1, Pointer var2, Pointer var3, int var4) {
      Pointer var5 = new Pointer(this.NB_WORD_MUL);
      int var6 = var2.getIndex();
      int var7 = var3.getIndex();
      this.mul_move(var5, var2, var3);
      this.for_mul_xorrange_move(var5, var2, var3, var4 - 1);
      this.rem_gf2n(var1, 0, var5);
      var2.changeIndex(var6);
      var3.changeIndex(var7);
   }

   void mul_gf2n(Pointer var1, Pointer var2, int var3, Pointer var4) {
      int var5 = var2.getIndex();
      var2.move(var3);
      this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, var2, var4);
      var2.changeIndex(var5);
      this.rem_gf2n(var1, 0, this.Buffer_NB_WORD_MUL);
   }

   void mul_gf2n(Pointer var1, Pointer var2, Pointer var3) {
      this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, var2, var3);
      this.rem_gf2n(var1, 0, this.Buffer_NB_WORD_MUL);
   }

   void for_mul_xorrange_move(Pointer var1, Pointer var2, Pointer var3, int var4) {
      for(int var5 = 0; var5 < var4; ++var5) {
         this.mul.mul_gf2x_xor(var1, var2, var3);
         var2.move(this.NB_WORD_GFqn);
         var3.move(this.NB_WORD_GFqn);
      }

   }

   void mul_move(Pointer var1, Pointer var2, Pointer var3) {
      this.mul.mul_gf2x(var1, var2, var3);
      var2.move(this.NB_WORD_GFqn);
      var3.move(this.NB_WORD_GFqn);
   }

   public void mul_xorrange(Pointer var1, Pointer var2, Pointer var3) {
      this.mul.mul_gf2x_xor(var1, var2, var3);
   }

   public void mul_rem_xorrange(Pointer var1, Pointer var2, Pointer var3) {
      this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, var2, var3);
      this.rem.rem_gf2n_xor(var1.array, var1.cp, this.Buffer_NB_WORD_MUL.array);
   }

   public void mul_rem_xorrange(Pointer var1, Pointer var2, Pointer var3, int var4) {
      int var5 = var3.getIndex();
      var3.move(var4);
      this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, var2, var3);
      this.rem.rem_gf2n_xor(var1.array, var1.cp, this.Buffer_NB_WORD_MUL.array);
      var3.changeIndex(var5);
   }

   private void rem_gf2n(Pointer var1, int var2, Pointer var3) {
      var2 += var1.getIndex();
      this.rem.rem_gf2n(var1.array, var2, var3.array);
   }

   private void sqr_gf2n(Pointer var1, int var2, Pointer var3, int var4) {
      var4 += var3.cp;
      this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, var3.array, var4);
      this.rem_gf2n(var1, var2, this.Buffer_NB_WORD_MUL);
   }

   private void sqr_gf2n(Pointer var1, Pointer var2) {
      this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, var2.array, var2.cp);
      this.rem.rem_gf2n(var1.array, var1.cp, this.Buffer_NB_WORD_MUL.array);
   }

   void cleanLowerMatrix(Pointer var1, FunctionParams var2) {
      int var3;
      int var4;
      switch (var2.ordinal()) {
         case 0:
            var3 = this.HFEnvq;
            var4 = this.HFEnvr;
            break;
         case 2:
            var3 = this.HFEnq;
            var4 = this.HFEnr;
            break;
         default:
            throw new IllegalArgumentException("");
      }

      Pointer var6 = new Pointer(var1);

      int var5;
      for(var5 = 1; var5 <= var3; ++var5) {
         this.for_and_xor_shift_incre_move(var6, var5, 64);
         var6.moveIncremental();
      }

      this.for_and_xor_shift_incre_move(var6, var5, var4);
   }

   private void for_and_xor_shift_incre_move(Pointer var1, int var2, int var3) {
      long var7 = 0L;

      for(int var6 = 0; var6 < var3; ++var6) {
         var1.setAnd(var7);
         var1.setXor(1L << var6);
         var7 <<= 1;
         ++var7;
         var1.move(var2);
      }

   }

   void invMatrixLU_gf2(Pointer var1, Pointer var2, Pointer var3, FunctionParams var4) {
      Pointer var7 = new Pointer(var2);
      Pointer var8 = new Pointer(var2);
      Pointer var9 = new Pointer(var3);
      int var13;
      int var14;
      int var15;
      int var16;
      int var17;
      switch (var4.ordinal()) {
         case 0:
            var13 = this.HFEnvq;
            var14 = this.HFEnv - 1;
            var15 = this.NB_WORD_GF2nv;
            var16 = this.HFEnvr;
            var17 = this.LTRIANGULAR_NV_SIZE;
            break;
         case 2:
            var1.setRangeClear(0, this.MATRIXn_SIZE);
            var13 = this.HFEnq;
            var14 = this.HFEn - 1;
            var15 = this.NB_WORD_GFqn;
            var16 = this.HFEnr;
            var17 = this.LTRIANGULAR_N_SIZE;
            break;
         default:
            throw new IllegalArgumentException("Invalid Input");
      }

      Pointer var5 = new Pointer(var1);
      Pointer var6 = new Pointer(var1);
      int var10 = 0;

      int var11;
      for(var11 = 0; var11 < var13; ++var11) {
         var10 = this.loop_xor_loop_move_xorandmask_move(var5, var6, var7, var8, var10, var11, 64, var14, var15);
         var8.moveIncremental();
      }

      if (var16 > 1) {
         this.loop_xor_loop_move_xorandmask_move(var5, var6, var7, var8, var10, var11, var16 - 1, var14, var15);
         var5.setXor(var11, 1L << var16 - 1);
         var5.move(var15);
      } else if (var16 == 1) {
         var5.set(var11, 1L);
         var5.move(var15);
      }

      var9.move(var17);

      for(int var18 = var14; var18 > 0; --var18) {
         var9.move(-1 - (var18 >>> 6));
         var5.move(-var15);
         var6.changeIndex(var1);

         for(int var12 = 0; var12 < var18; ++var12) {
            var6.setXorRangeAndMask(var5, var15, -(var9.get(var12 >>> 6) >>> (var12 & 63) & 1L));
            var6.move(var15);
         }
      }

   }

   private int loop_xor_loop_move_xorandmask_move(Pointer var1, Pointer var2, Pointer var3, Pointer var4, int var5, int var6, int var7, int var8, int var9) {
      for(int var11 = 0; var11 < var7; ++var5) {
         var1.setXor(var6, 1L << var11);
         var2.changeIndex(var1);
         var3.changeIndex(var4);

         for(int var10 = var5; var10 < var8; ++var10) {
            var2.move(var9);
            var3.move((var10 >>> 6) + 1);
            var2.setXorRangeAndMask(var1, var6 + 1, -(var3.get() >>> var11 & 1L));
         }

         var1.move(var9);
         var4.move(var6 + 1);
         ++var11;
      }

      return var5;
   }

   void vecMatProduct(Pointer var1, Pointer var2, Pointer var3, FunctionParams var4) {
      int var11 = 0;
      int var12 = 0;
      Pointer var13 = new Pointer(var3);
      int var5;
      int var6;
      int var8;
      switch (var4.ordinal()) {
         case 0:
            var1.setRangeClear(0, this.NB_WORD_GF2nv);
            var8 = this.HFEnvq;
            var5 = this.NB_WORD_GF2nv;
            var6 = this.NB_WORD_GF2nv;
            break;
         case 1:
            var1.setRangeClear(0, this.NB_WORD_GFqn);
            var5 = this.NB_WORD_GFqn;
            var6 = this.NB_WORD_GFqn;
            var8 = this.HFEvq;
            break;
         case 2:
            var1.setRangeClear(0, this.NB_WORD_GFqn);
            var5 = this.NB_WORD_GFqn;
            var6 = this.NB_WORD_GFqn;
            var8 = this.HFEnq;
            break;
         case 3:
            var1.setRangeClear(0, this.NB_WORD_GF2m);
            var8 = this.HFEnq;
            var5 = this.NB_WORD_GF2m;
            var6 = this.NB_WORD_GFqn;
            break;
         default:
            throw new IllegalArgumentException("Invalid input for vecMatProduct");
      }

      while(var11 < var8) {
         for(long var9 = var2.get(var11); var12 < 64; ++var12) {
            var1.setXorRangeAndMask(var13, var5, -(var9 & 1L));
            var13.move(var6);
            var9 >>>= 1;
         }

         var12 = 0;
         ++var11;
      }

      int var7;
      long var14;
      switch (var4.ordinal()) {
         case 0:
            if (this.HFEnvr == 0) {
               return;
            }

            var14 = var2.get(this.HFEnvq);
            var7 = this.HFEnvr;
            break;
         case 1:
            if (this.HFEvr == 0) {
               return;
            }

            var14 = var2.get(this.HFEvq);
            var7 = this.HFEvr;
            break;
         case 2:
         case 3:
            var14 = var2.get(this.HFEnq);
            var7 = this.HFEnr;
            break;
         default:
            throw new IllegalArgumentException("Invalid input for vecMatProduct");
      }

      while(var12 < var7) {
         var1.setXorRangeAndMask(var13, var5, -(var14 & 1L));
         var13.move(var6);
         var14 >>>= 1;
         ++var12;
      }

      if (var4 == GeMSSEngine.FunctionParams.M && this.HFEmr != 0) {
         var1.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
      }

   }

   private long convMQ_uncompressL_gf2(Pointer var1, PointerUnion var2) {
      PointerUnion var4 = new PointerUnion(var2);
      int var3 = this.for_setpk2_end_move_plus(var1, var4, this.HFEnvq);
      if (this.HFEnvr != 0) {
         this.setPk2Value(var1, var4, var3, this.HFEnvq, this.HFEnvr + 1);
      }

      return var2.get() & 1L;
   }

   private int setPk2Value(Pointer var1, PointerUnion var2, int var3, int var4, int var5) {
      for(int var6 = 1; var6 < var5; ++var6) {
         if ((var3 & 63) != 0) {
            var1.setRangePointerUnion(var2, var4, var3 & 63);
            var1.set(var4, var2.get(var4) >>> (var3 & 63));
            if ((var3 & 63) + var6 > 64) {
               var1.setXor(var4, var2.get(var4 + 1) << 64 - (var3 & 63));
            }

            if ((var3 & 63) + var6 >= 64) {
               var2.moveIncremental();
            }
         } else {
            var1.setRangePointerUnion(var2, var4 + 1);
         }

         var2.move(var4);
         var1.setAnd(var4, (1L << var6) - 1L);
         var1.move(var4 + 1);
         var3 += (var4 << 6) + var6;
      }

      return var3;
   }

   private void setPk2_endValue(Pointer var1, PointerUnion var2, int var3, int var4) {
      if ((var3 & 63) != 0) {
         var1.setRangePointerUnion(var2, var4 + 1, var3 & 63);
      } else {
         var1.setRangePointerUnion(var2, var4 + 1);
      }

   }

   private long convMQ_last_uncompressL_gf2(Pointer var1, PointerUnion var2) {
      PointerUnion var3 = new PointerUnion(var2);
      int var6 = this.HFEnv - 1;
      int var8 = var6 >>> 6;
      int var9 = var6 & 63;
      int var7 = this.for_setpk2_end_move_plus(var1, var3, var8);
      if (var9 != 0) {
         var7 = this.setPk2Value(var1, var3, var7, var8, var9 + 1);
      }

      var6 = this.HFEnv - this.LOST_BITS;
      int var10 = var6 >>> 6;
      int var11 = var6 & 63;
      if (var11 != 0) {
         if ((var7 & 63) != 0) {
            if ((this.NB_MONOMIAL_PK - this.LOST_BITS + 7 >>> 3 & 7) != 0) {
               int var14 = this.HFEnv - (64 - (this.NB_MONOMIAL_PK - this.LOST_BITS - this.HFEnvr & 63) & 63) >>> 6;
               var1.setRangePointerUnion_Check(var3, var14, var7);
               var1.set(var14, var3.getWithCheck(var14) >>> (var7 & 63));
               if (var14 < var10) {
                  long var12 = var3.getWithCheck(var14 + 1);
                  var1.setXor(var14, var12 << 64 - (var7 & 63));
                  var1.set(var14 + 1, var12 >>> (var7 & 63));
               } else if ((var7 & 63) + var11 > 64) {
                  var1.setXor(var14, var3.getWithCheck(var14 + 1) << 64 - (var7 & 63));
               }
            } else {
               var1.setRangePointerUnion(var3, var10, var7 & 63);
               var1.set(var10, var3.get(var10) >>> (var7 & 63));
               if ((var7 & 63) + var11 > 64) {
                  var1.setXor(var10, var3.get(var10 + 1) << 64 - (var7 & 63));
               }
            }
         } else if ((this.NB_MONOMIAL_PK - this.LOST_BITS + 7 >>> 3 & 7) != 0) {
            var1.setRangePointerUnion(var3, var10);
            var1.set(var10, var3.getWithCheck(var10));
         } else {
            var1.setRangePointerUnion(var3, var10 + 1);
         }
      } else if (var10 != 0) {
         if ((var7 & 63) != 0) {
            if ((this.NB_MONOMIAL_PK - this.LOST_BITS + 7 >>> 3 & 7) != 0) {
               var1.setRangePointerUnion(var3, var10 - 1, var7 & 63);
               var6 = var10 - 1;
               var1.set(var6, var3.get(var6) >>> (var7 & 63));
               var1.setXor(var6, var3.getWithCheck(var6 + 1) << 64 - (var7 & 63));
            } else {
               var1.setRangePointerUnion(var3, var10, var7 & 63);
            }
         } else {
            var1.setRangePointerUnion(var3, var10);
         }
      }

      return var2.get() & 1L;
   }

   private int for_setpk2_end_move_plus(Pointer var1, PointerUnion var2, int var3) {
      int var4 = 1;

      for(int var5 = 0; var5 < var3; ++var5) {
         var4 = this.setPk2Value(var1, var2, var4, var5, 64);
         this.setPk2_endValue(var1, var2, var4, var5);
         var2.move(var5 + 1);
         var1.move(var5 + 1);
         var4 += var5 + 1 << 6;
      }

      return var4;
   }

   public int sign_openHFE_huncomp_pk(byte[] var1, int var2, byte[] var3, PointerUnion var4, PointerUnion var5) {
      Pointer var6 = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
      Pointer var7 = new Pointer(this.NB_WORD_GF2nv);
      Pointer var8 = new Pointer(this.NB_WORD_GF2nv);
      Pointer var9 = new Pointer(var7);
      Pointer var10 = new Pointer(var8);
      byte[] var11 = new byte[64];
      Pointer var12 = new Pointer(this.NB_ITE * this.SIZE_DIGEST_UINT);
      byte var15 = 0;
      long var16 = var5.get();
      var5.move(1);
      this.uncompress_signHFE(var6, var3);
      this.getSHA3Hash(var12, 0, 64, var1, var15, var2, var11);

      int var13;
      for(var13 = 1; var13 < this.NB_ITE; ++var13) {
         this.getSHA3Hash(var12, var13 * this.SIZE_DIGEST_UINT, 64, var11, 0, this.SIZE_DIGEST, var11);
         var12.setAnd(this.SIZE_DIGEST_UINT * (var13 - 1) + this.NB_WORD_GF2m - 1, this.MASK_GF2m);
      }

      var12.setAnd(this.SIZE_DIGEST_UINT * (var13 - 1) + this.NB_WORD_GF2m - 1, this.MASK_GF2m);
      this.evalMQShybrid8_uncomp_nocst_gf2_m(var9, var6, var4, var5);
      var9.setXor(this.HFEmq, var16);

      for(int var18 = this.NB_ITE - 1; var18 > 0; --var18) {
         var9.setXorRange(var12, var18 * this.SIZE_DIGEST_UINT, this.NB_WORD_GF2m);
         int var14 = this.NB_WORD_GF2nv + (this.NB_ITE - 1 - var18) * this.NB_WORD_GF2nvm;
         var9.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
         var9.setXor(this.NB_WORD_GF2m - 1, var6.get(var14));
         if (this.NB_WORD_GF2nvm != 1) {
            ++var14;
            var9.copyFrom(this.NB_WORD_GF2m, var6, var14, this.NB_WORD_GF2nvm - 1);
         }

         this.evalMQShybrid8_uncomp_nocst_gf2_m(var10, var9, var4, var5);
         var10.setXor(this.HFEmq, var16);
         var10.swap(var9);
      }

      return var9.isEqual_nocst_gf2(var12, this.NB_WORD_GF2m);
   }

   private void getSHA3Hash(Pointer var1, int var2, int var3, byte[] var4, int var5, int var6, byte[] var7) {
      this.sha3Digest.update(var4, var5, var6);
      this.sha3Digest.doFinal(var7, 0);
      var1.fill(var2, var7, 0, var3);
   }

   private void evalMQShybrid8_uncomp_nocst_gf2_m(Pointer var1, Pointer var2, PointerUnion var3, PointerUnion var4) {
      PointerUnion var5 = new PointerUnion(var4);
      this.evalMQSnocst8_quo_gf2(var1, var2, var3);
      if (this.HFEmr < 8) {
         var1.set(this.HFEmq, 0L);
      }

      for(int var6 = this.HFEmr - this.HFEmr8; var6 < this.HFEmr; ++var6) {
         var1.setXor(this.HFEmq, this.evalMQnocst_unrolled_no_simd_gf2(var2, var5) << var6);
         var5.move(this.NB_WORD_UNCOMP_EQ);
      }

   }

   private void uncompress_signHFE(Pointer var1, byte[] var2) {
      PointerUnion var3 = new PointerUnion(var1);
      int var4 = (1 << this.HFEnvr8) - 1;
      var3.fillBytes(0, var2, 0, this.NB_BYTES_GFqnv);
      if (this.HFEnvr8 != 0) {
         var3.setAndByte(this.NB_BYTES_GFqnv - 1, (long)var4);
      }

      int var11 = this.HFEnv;
      var3.moveNextBytes((this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7));

      for(int var5 = 1; var5 < this.NB_ITE; ++var5) {
         int var9 = Math.min(this.HFEDELTA + this.HFEv, 8 - (var11 & 7) & 7);
         if ((var11 & 7) != 0) {
            var3.setXorByte((var2[var11 >>> 3] & 255) >>> (var11 & 7) << this.HFEmr8);
            int var10 = var9 - this.VAL_BITS_M;
            if (var10 >= 0) {
               var3.moveNextByte();
            }

            if (var10 > 0) {
               var11 += this.VAL_BITS_M;
               var3.setXorByte((var2[var11 >>> 3] & 255) >>> (var11 & 7));
               var11 += var10;
            } else {
               var11 += var9;
            }
         }

         int var7 = this.HFEDELTA + this.HFEv - var9;
         int var8 = this.HFEm + var9 & 7;
         if (var8 != 0) {
            for(int var12 = 0; var12 < var7 - 1 >>> 3; ++var12) {
               var3.setXorByte((var2[var11 >>> 3] & 255) << var8);
               var3.moveNextByte();
               var3.setXorByte((var2[var11 >>> 3] & 255) >>> 8 - var8);
               var11 += 8;
            }

            var3.setXorByte((var2[var11 >>> 3] & 255) << var8);
            var3.moveNextByte();
            var7 = (var7 + 7 & 7) + 1;
            if (var7 > 8 - var8) {
               var3.setByte((var2[var11 >>> 3] & 255) >>> 8 - var8);
               var3.moveNextByte();
            }

            var11 += var7;
         } else {
            for(int var6 = 0; var6 < var7 + 7 >>> 3; ++var6) {
               var3.setByte(var2[var11 >>> 3]);
               var11 += 8;
               var3.moveNextByte();
            }

            var11 -= 8 - (var7 & 7) & 7;
         }

         if (this.HFEnvr8 != 0) {
            var3.setAndByte(-1, (long)var4);
         }

         var3.moveNextBytes((8 - (this.NB_BYTES_GFqnv & 7) & 7) + (this.HFEmq8 & 7));
      }

   }

   private void evalMQSnocst8_quo_gf2(Pointer var1, Pointer var2, PointerUnion var3) {
      int var10 = this.HFEnv;
      int var12 = this.HFEm >>> 3 != 0 ? this.HFEm >>> 3 << 3 : this.HFEm;
      int var13 = (var12 & 7) != 0 ? (var12 >>> 3) + 1 : var12 >>> 3;
      int var14 = (var13 >>> 3) + ((var13 & 7) != 0 ? 1 : 0);
      PointerUnion var15 = new PointerUnion(var3);
      System.arraycopy(var15.getArray(), 0, var1.getArray(), var1.getIndex(), var14);
      var15.moveNextBytes(var13);

      for(int var8 = 0; var8 < this.HFEnvq; ++var8) {
         long var4 = var2.get(var8);

         for(int var9 = 0; var9 < 64; --var10) {
            if ((var4 & 1L) == 0L) {
               var15.moveNextBytes(var10 * var13);
            } else {
               var1.setXorRange(0, (PointerUnion)var15, 0, var14);
               var15.moveNextBytes(var13);
               long var6 = var4 >>> 1;
               this.LOOPJR_UNROLLED_64(var1, var15, var9 + 1, 64, var6, var13, var14);

               for(int var11 = var8 + 1; var11 < this.HFEnvq; ++var11) {
                  var6 = var2.get(var11);
                  this.LOOPJR_UNROLLED_64(var1, var15, 0, 64, var6, var13, var14);
               }

               if (this.HFEnvr != 0) {
                  this.choose_LOOPJR(var1, var15, 0, var2.get(this.HFEnvq), var13, var14);
               }
            }

            var4 >>>= 1;
            ++var9;
         }
      }

      if (this.HFEnvr != 0) {
         long var16 = var2.get(this.HFEnvq);

         for(int var18 = 0; var18 < this.HFEnvr; --var10) {
            if ((var16 & 1L) != 0L) {
               var1.setXorRange(0, (PointerUnion)var15, 0, var14);
               var15.moveNextBytes(var13);
               this.choose_LOOPJR(var1, var15, var18 + 1, var16 >>> 1, var13, var14);
            } else {
               var15.moveNextBytes(var10 * var13);
            }

            var16 >>>= 1;
            ++var18;
         }
      }

      if ((var12 & 63) != 0) {
         var1.setAnd(var14 - 1, (1L << (var12 & 63)) - 1L);
      }

   }

   private void choose_LOOPJR(Pointer var1, PointerUnion var2, int var3, long var4, int var6, int var7) {
      if (this.HFEnvr < 8) {
         this.LOOPJR_NOCST_64(var1, var2, var3, this.HFEnvr, var4, var6, var7);
      } else {
         this.LOOPJR_UNROLLED_64(var1, var2, var3, this.HFEnvr, var4, var6, var7);
      }

   }

   private void LOOPJR_UNROLLED_64(Pointer var1, PointerUnion var2, int var3, int var4, long var5, int var7, int var8) {
      int var9;
      for(var9 = var3; var9 < var4 - 4 + 1; var9 += 4) {
         var5 = this.LOOPJR_NOCST_64(var1, var2, 0, 4, var5, var7, var8);
      }

      this.LOOPJR_NOCST_64(var1, var2, var9, var4, var5, var7, var8);
   }

   private long LOOPJR_NOCST_64(Pointer var1, PointerUnion var2, int var3, int var4, long var5, int var7, int var8) {
      for(int var9 = var3; var9 < var4; ++var9) {
         if ((var5 & 1L) != 0L) {
            var1.setXorRange(0, (PointerUnion)var2, 0, var8);
         }

         var2.moveNextBytes(var7);
         var5 >>>= 1;
      }

      return var5;
   }

   private long evalMQnocst_unrolled_no_simd_gf2(Pointer var1, PointerUnion var2) {
      long var3 = 0L;
      int var6 = 64;
      PointerUnion var7 = new PointerUnion(var2);
      long var8 = var1.get();

      for(int var5 = 0; var5 < var6; ++var5) {
         if ((var8 >>> var5 & 1L) != 0L) {
            var3 ^= var7.get(var5) & var8;
         }
      }

      var7.move(64);

      for(int var10 = 1; var10 < this.NB_WORD_GF2nv; ++var10) {
         var6 = this.NB_WORD_GF2nv == var10 + 1 && this.HFEnvr != 0 ? this.HFEnvr : 64;
         var8 = var1.get(var10);

         for(int var12 = 0; var12 < var6; ++var12) {
            if ((var8 >>> var12 & 1L) != 0L) {
               var3 ^= var7.getDotProduct(0, var1, 0, var10 + 1);
            }

            var7.move(var10 + 1);
         }
      }

      var3 = GeMSSUtils.XORBITS_UINT(var3);
      return var3;
   }

   public void signHFE_FeistelPatarin(SecureRandom var1, byte[] var2, byte[] var3, int var4, int var5, byte[] var6) {
      this.random = var1;
      Pointer var7 = new Pointer(this.NB_WORD_GFqn);
      Pointer var8 = new Pointer(this.SIZE_DIGEST_UINT);
      Pointer var9 = new Pointer(this.SIZE_DIGEST_UINT);
      Pointer var10 = new Pointer(var9);
      int var11 = this.HFEv & 7;
      int var12 = (this.HFEv >>> 3) + (var11 != 0 ? 1 : 0);
      long var13 = GeMSSUtils.maskUINT(this.HFEvr);
      long var18 = 0L;
      SecretKeyHFE var20 = new SecretKeyHFE(this);
      Pointer var21 = new Pointer(this.NB_WORD_GFqv);
      Pointer[] var22 = new Pointer[this.HFEDegI + 1];
      this.precSignHFE(var20, var22, var6);
      Pointer var23 = new Pointer(var20.F_struct.poly);
      Pointer var24 = new Pointer(var8);
      byte[] var25 = new byte[this.Sha3BitStrength >>> 3];
      this.getSHA3Hash(var24, 0, var25.length, var3, var4, var5, var25);
      Pointer var26 = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
      Pointer var27 = new Pointer(this.NB_WORD_GF2nv);
      PointerUnion var28 = new PointerUnion(var27);

      for(int var16 = 1; var16 <= this.NB_ITE; ++var16) {
         var27.setRangeFromXor(var26, var24, this.NB_WORD_GF2m);
         if (this.HFEmr8 != 0) {
            var27.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
            var18 = (long)var28.getByte(this.HFEmq8);
         }

         do {
            if (this.HFEmr8 != 0) {
               var28.fillRandomBytes(this.HFEmq8, var1, this.NB_BYTES_GFqn - this.NB_BYTES_GFqm + 1);
               var28.setAndThenXorByte(this.HFEmq8, -(1L << this.HFEmr8), var18);
            } else {
               var28.fillRandomBytes(this.NB_BYTES_GFqm, var1, this.NB_BYTES_GFqn - this.NB_BYTES_GFqm);
            }

            if ((this.HFEn & 7) != 0) {
               var27.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);
            }

            this.vecMatProduct(var7, var27, var20.T, GeMSSEngine.FunctionParams.N);
            var21.fillRandom(0, var1, var12);
            if (var11 != 0) {
               var21.setAnd(this.NB_WORD_GFqv - 1, var13);
            }

            this.evalMQSv_unrolled_gf2(var23, var21, var20.F_HFEv);

            for(int var15 = 0; var15 <= this.HFEDegI; ++var15) {
               this.vecMatProduct(this.Buffer_NB_WORD_GFqn, var21, new Pointer(var22[var15], this.NB_WORD_GFqn), GeMSSEngine.FunctionParams.V);
               var23.setRangeFromXor(this.NB_WORD_GFqn * ((var15 * (var15 + 1) >>> 1) + 1), var22[var15], 0, this.Buffer_NB_WORD_GFqn, 0, this.NB_WORD_GFqn);
            }
         } while(this.chooseRootHFE_gf2nx(var27, var20.F_struct, var7) == 0);

         var27.setXor(this.NB_WORD_GFqn - 1, var21.get() << this.HFEnr);
         var27.setRangeRotate(this.NB_WORD_GFqn, var21, 0, this.NB_WORD_GFqv - 1, 64 - this.HFEnr);
         if (this.NB_WORD_GFqn + this.NB_WORD_GFqv == this.NB_WORD_GF2nv) {
            var27.set(this.NB_WORD_GFqn + this.NB_WORD_GFqv - 1, var21.get(this.NB_WORD_GFqv - 1) >>> 64 - this.HFEnr);
         }

         this.vecMatProduct(var26, var27, var20.S, GeMSSEngine.FunctionParams.NV);
         if (var16 != this.NB_ITE) {
            int var17 = this.NB_WORD_GF2nv + (this.NB_ITE - 1 - var16) * this.NB_WORD_GF2nvm;
            var26.copyFrom(var17, var26, this.NB_WORD_GF2nv - this.NB_WORD_GF2nvm, this.NB_WORD_GF2nvm);
            if (this.HFEmr != 0) {
               var26.setAnd(var17, ~this.MASK_GF2m);
            }

            byte[] var29 = var24.toBytes(this.SIZE_DIGEST);
            this.getSHA3Hash(var10, 0, this.SIZE_DIGEST, var29, 0, var29.length, var29);
            var10.swap(var24);
         }
      }

      if (this.NB_ITE == 1) {
         byte[] var30 = var26.toBytes(var26.getLength() << 3);
         System.arraycopy(var30, 0, var2, 0, this.NB_BYTES_GFqnv);
      } else {
         this.compress_signHFE(var2, var26);
      }

   }

   private void precSignHFE(SecretKeyHFE var1, Pointer[] var2, byte[] var3) {
      this.precSignHFESeed(var1, var3);
      this.initListDifferences_gf2nx(var1.F_struct.L);
      Pointer var7 = new Pointer(var1.F_HFEv);
      int var8 = this.NB_COEFS_HFEPOLY * this.NB_WORD_GFqn;
      Pointer var9 = new Pointer(var8);
      var2[0] = new Pointer(var7, this.MQv_GFqn_SIZE);
      var7.changeIndex(var2[0], this.MLv_GFqn_SIZE);
      Pointer var4 = new Pointer(var9, 2 * this.NB_WORD_GFqn);

      int var5;
      for(var5 = 0; var5 < this.HFEDegI; ++var5) {
         int var6 = var5 - ((1 << var5) + 1 > this.HFE_odd_degree && this.ENABLED_REMOVE_ODD_DEGREE ? 1 : 0);
         var4.copyFrom(var7, var6 * this.NB_WORD_GFqn);
         var7.move(var6 * this.NB_WORD_GFqn);
         var4.move(var6 * this.NB_WORD_GFqn);
         var2[var5 + 1] = new Pointer(var7);
         var7.move(this.MLv_GFqn_SIZE);
         var4.move(this.NB_WORD_GFqn);
      }

      if (this.HFEDegJ != 0) {
         int var10 = (1 << var5) + 1 <= this.HFE_odd_degree ? 0 : 1;
         var4.copyFrom(var7, (this.HFEDegJ - var10) * this.NB_WORD_GFqn);
      }

      var1.F_struct.poly = new Pointer(var9);
   }

   private void precSignHFESeed(SecretKeyHFE var1, byte[] var2) {
      int var5 = this.NB_UINT_HFEVPOLY + (this.LTRIANGULAR_NV_SIZE + this.LTRIANGULAR_N_SIZE << 1);
      var1.sk_uncomp = new Pointer(var5 + this.MATRIXnv_SIZE + this.MATRIXn_SIZE);
      SHAKEDigest var6 = new SHAKEDigest(this.ShakeBitStrength);
      var6.update(var2, 0, this.SIZE_SEED_SK);
      byte[] var7 = new byte[var5 << 3];
      var6.doFinal(var7, 0, var7.length);
      var1.sk_uncomp.fill(0, var7, 0, var7.length);
      var1.S = new Pointer(var1.sk_uncomp, var5);
      var1.T = new Pointer(var1.S, this.MATRIXnv_SIZE);
      var1.F_HFEv = new Pointer(var1.sk_uncomp);
      this.cleanMonicHFEv_gf2nx(var1.F_HFEv);
      Pointer var3 = new Pointer(var1.sk_uncomp, this.NB_UINT_HFEVPOLY);
      Pointer var4 = new Pointer(var3, this.LTRIANGULAR_NV_SIZE);
      this.cleanLowerMatrix(var3, GeMSSEngine.FunctionParams.NV);
      this.cleanLowerMatrix(var4, GeMSSEngine.FunctionParams.NV);
      this.mulMatricesLU_gf2(var1.S, var3, var4, GeMSSEngine.FunctionParams.NV);
      var3.move(this.LTRIANGULAR_NV_SIZE << 1);
      var4.changeIndex(var3, this.LTRIANGULAR_N_SIZE);
      this.cleanLowerMatrix(var3, GeMSSEngine.FunctionParams.N);
      this.cleanLowerMatrix(var4, GeMSSEngine.FunctionParams.N);
      this.mulMatricesLU_gf2(var1.T, var3, var4, GeMSSEngine.FunctionParams.N);
   }

   void cleanMonicHFEv_gf2nx(Pointer var1) {
      for(int var2 = this.NB_WORD_GFqn - 1; var2 < this.NB_UINT_HFEVPOLY; var2 += this.NB_WORD_GFqn) {
         var1.setAnd(var2, this.MASK_GF2n);
      }

   }

   private void mulMatricesLU_gf2(Pointer var1, Pointer var2, Pointer var3, FunctionParams var4) {
      int var9 = var1.getIndex();
      int var5;
      int var6;
      boolean var8;
      switch (var4.ordinal()) {
         case 0:
            var5 = this.HFEnvq;
            var6 = this.HFEnvr;
            var8 = this.HFEnvr != 0;
            break;
         case 2:
            var5 = this.HFEnq;
            var6 = this.HFEnr;
            var8 = true;
            break;
         default:
            throw new IllegalArgumentException("Invalid parameter for MULMATRICESLU_GF2");
      }

      Pointer var10 = new Pointer(var2);

      int var7;
      for(var7 = 1; var7 <= var5; ++var7) {
         this.LOOPIR(var1, var10, var3, 64, var5, var6, var7, var8);
      }

      this.LOOPIR(var1, var10, var3, var6, var5, var6, var7, var8);
      var1.changeIndex(var9);
   }

   private void LOOPIR(Pointer var1, Pointer var2, Pointer var3, int var4, int var5, int var6, int var7, boolean var8) {
      for(int var10 = 0; var10 < var4; ++var10) {
         Pointer var11 = new Pointer(var3);

         int var9;
         for(var9 = 1; var9 <= var5; ++var9) {
            this.LOOPJR(var1, var2, var11, 64, var7, var9);
         }

         if (var8) {
            this.LOOPJR(var1, var2, var11, var6, var7, var9);
         }

         var2.move(var7);
      }

   }

   private void LOOPJR(Pointer var1, Pointer var2, Pointer var3, int var4, int var5, int var6) {
      int var7 = Math.min(var5, var6);
      var1.set(0L);

      for(int var10 = 0; var10 < var4; ++var10) {
         long var8 = var2.getDotProduct(0, var3, 0, var7);
         var8 = GeMSSUtils.XORBITS_UINT(var8);
         var1.setXor(var8 << var10);
         var3.move(var6);
      }

      var1.moveIncremental();
   }

   private int setArrayL(int[] var1, int var2, int var3, int var4) {
      for(int var5 = var3; var5 < var4; ++var5) {
         var1[var2++] = this.NB_WORD_GFqn << var5;
      }

      return var2;
   }

   private void initListDifferences_gf2nx(int[] var1) {
      int var3 = 2;
      var1[1] = this.NB_WORD_GFqn;

      int var2;
      for(var2 = 0; var2 < this.HFEDegI; ++var2) {
         if (this.ENABLED_REMOVE_ODD_DEGREE && (1 << var2) + 1 > this.HFE_odd_degree) {
            if (var2 != 0) {
               var1[var3++] = this.NB_WORD_GFqn << 1;
            }

            var3 = this.setArrayL(var1, var3, 1, var2);
         } else {
            var1[var3++] = this.NB_WORD_GFqn;
            var3 = this.setArrayL(var1, var3, 0, var2);
         }
      }

      if (this.HFEDegJ != 0) {
         if (this.ENABLED_REMOVE_ODD_DEGREE && (1 << var2) + 1 > this.HFE_odd_degree) {
            var1[var3++] = this.NB_WORD_GFqn << 1;
            this.setArrayL(var1, var3, 1, this.HFEDegJ - 1);
         } else {
            var1[var3++] = this.NB_WORD_GFqn;
            this.setArrayL(var1, var3, 0, this.HFEDegJ - 1);
         }
      }

   }

   void evalMQSv_unrolled_gf2(Pointer var1, Pointer var2, Pointer var3) {
      Pointer var4 = new Pointer(this.HFEv);
      int var5 = this.HFEv >>> 6;
      int var6 = this.HFEv & 63;
      int var7 = (this.HFEn >>> 6) + ((this.HFEn & 63) != 0 ? 1 : 0);
      int var8 = var3.getIndex();
      Pointer var9 = new Pointer(var7);
      int var10 = 0;

      int var12;
      for(var12 = 0; var10 < var5; ++var10) {
         var12 = var4.setRange_xi(var2.get(var10), var12, 64);
      }

      if (var6 != 0) {
         var4.setRange_xi(var2.get(var10), var12, var6);
      }

      var1.copyFrom(var3, var7);
      var3.move(var7);

      for(int var13 = 0; var13 < this.HFEv; ++var13) {
         var9.copyFrom(var3, var7);
         var3.move(var7);

         int var11;
         for(var11 = var13 + 1; var11 < this.HFEv - 3; var11 += 4) {
            var9.setXorRangeAndMaskMove(var3, var7, var4.get(var11));
            var9.setXorRangeAndMaskMove(var3, var7, var4.get(var11 + 1));
            var9.setXorRangeAndMaskMove(var3, var7, var4.get(var11 + 2));
            var9.setXorRangeAndMaskMove(var3, var7, var4.get(var11 + 3));
         }

         while(var11 < this.HFEv) {
            var9.setXorRangeAndMaskMove(var3, var7, var4.get(var11));
            ++var11;
         }

         var1.setXorRangeAndMask(var9, var7, var4.get(var13));
      }

      var3.changeIndex(var8);
   }

   private int chooseRootHFE_gf2nx(Pointer var1, SecretKeyHFE.complete_sparse_monic_gf2nx var2, Pointer var3) {
      Pointer var4 = new Pointer(this.SIZE_DIGEST_UINT);
      Pointer var5 = new Pointer(((this.HFEDeg << 1) - 1) * this.NB_WORD_GFqn);
      Pointer var6 = new Pointer((this.HFEDeg + 1) * this.NB_WORD_GFqn);
      Pointer var7 = new Pointer(this.NB_WORD_GFqn);
      var7.setRangeFromXor(var2.poly, var3, this.NB_WORD_GFqn);
      if (this.HFEDeg > 34 && (this.HFEn <= 196 || this.HFEDeg >= 256)) {
         int var8 = 2 << this.HFEDegI;
         var5.set(var8 * this.NB_WORD_GFqn, 1L);
         this.divsqr_r_HFE_cstdeg_gf2nx(var5, var8, var8, this.HFEDeg, var2, var7);
         this.for_sqr_divsqr(var5, this.HFEDegI + 1, this.HFEn, var2, var7);
      } else {
         this.frobeniusMap_multisqr_HFE_gf2nx(var5, var2, var7);
      }

      var5.setXor(this.NB_WORD_GFqn, 1L);
      int var10 = var6.getIndex();
      var6.copyFrom(var2.poly, this.NB_WORD_GFqn);
      this.for_copy_move(var6, var2);
      var6.changeIndex(var10);
      var6.set(this.HFEDeg * this.NB_WORD_GFqn, 1L);
      var6.setXorRange(var3, this.NB_WORD_GFqn);
      var10 = var5.getD_for_not0_or_plus(this.NB_WORD_GFqn, this.HFEDeg - 1);
      var10 = this.gcd_gf2nx(var6, this.HFEDeg, var5, var10);
      if (this.buffer != 0) {
         var5.swap(var6);
      }

      if (var5.is0_gf2n(0, this.NB_WORD_GFqn) == 0) {
         return 0;
      } else {
         this.convMonic_gf2nx(var6, var10);
         Pointer var9 = new Pointer(var10 * this.NB_WORD_GFqn);
         this.findRootsSplit_gf2nx(var9, var6, var10);
         if (var10 == 1) {
            var1.copyFrom(var9, this.NB_WORD_GFqn);
         } else {
            this.fast_sort_gf2n(var9, var10);
            this.getSHA3Hash(var4, 0, this.Sha3BitStrength >>> 3, var3.toBytes(this.NB_BYTES_GFqn), 0, this.NB_BYTES_GFqn, new byte[this.Sha3BitStrength >>> 3]);
            var1.copyFrom(0, var9, (int)remainderUnsigned(var4.get(), (long)var10) * this.NB_WORD_GFqn, this.NB_WORD_GFqn);
         }

         return var10;
      }
   }

   private int gcd_gf2nx(Pointer var1, int var2, Pointer var3, int var4) {
      Pointer var5 = new Pointer(this.NB_WORD_GFqn);

      for(this.buffer = 0; var4 != 0; this.buffer = 1 - this.buffer) {
         if (var4 << 1 > var2) {
            var2 = this.div_r_gf2nx(var1, var2, var3, var4);
         } else {
            this.inv_gf2n(var5, var3, var4 * this.NB_WORD_GFqn);
            var3.set1_gf2n(var4 * this.NB_WORD_GFqn, this.NB_WORD_GFqn);
            this.for_mul(var3, var5, var4 - 1);
            var2 = this.div_r_monic_gf2nx(var1, var2, var3, var4);
         }

         Pointer var6 = var1;
         var1 = var3;
         var3 = var6;
         int var7 = var2;
         var2 = var4;
         var4 = var7;
      }

      return var2;
   }

   private void for_mul(Pointer var1, Pointer var2, int var3) {
      Pointer var4 = new Pointer(var1, var3 * this.NB_WORD_GFqn);

      for(int var5 = var3; var5 != -1; --var5) {
         this.mul_gf2n(var4, var4, var2);
         var4.move(-this.NB_WORD_GFqn);
      }

   }

   private void frobeniusMap_multisqr_HFE_gf2nx(Pointer var1, SecretKeyHFE.complete_sparse_monic_gf2nx var2, Pointer var3) {
      Pointer var4 = new Pointer();
      Pointer var5 = new Pointer(this.HFEDeg * this.NB_WORD_GFqn);
      Pointer var6 = new Pointer();
      Pointer var10 = new Pointer((this.KX * this.HFEDeg + this.POW_II) * this.NB_WORD_GFqn);
      int var8 = this.POW_II * this.KP - this.HFEDeg;
      Pointer var11 = new Pointer(var10, this.NB_WORD_GFqn * var8);
      var11.copyFrom(var3, this.NB_WORD_GFqn);
      this.for_copy_move(var11, var2);
      this.divsqr_r_HFE_cstdeg_gf2nx(var10, var8 - 1 + this.HFEDeg, var8 - 1, 0, var2, var3);

      for(int var9 = this.KP + 1; var9 < this.HFEDeg; ++var9) {
         var11.changeIndex(var10, this.HFEDeg * this.NB_WORD_GFqn);
         var11.setRangeClear(0, this.POW_II * this.NB_WORD_GFqn);
         var11.copyFrom(this.POW_II * this.NB_WORD_GFqn, var10, 0, this.HFEDeg * this.NB_WORD_GFqn);
         var10.changeIndex(var11);
         this.divsqr_r_HFE_cstdeg_gf2nx(var10, this.POW_II - 1 + this.HFEDeg, this.POW_II - 1, 0, var2, var3);
      }

      var10.indexReset();
      var1.copyFrom(0, var10, ((1 << this.HFEDegI) - this.KP) * this.HFEDeg * this.NB_WORD_GFqn, this.HFEDeg * this.NB_WORD_GFqn);

      for(int var7 = 0; var7 < (this.HFEn - this.HFEDegI - this.II) / this.II; ++var7) {
         this.loop_sqr(var5, var1);

         for(int var12 = 1; var12 < this.II; ++var12) {
            this.loop_sqr(var5, var5);
         }

         var6.changeIndex(var5, this.KP * this.NB_WORD_GFqn);
         var11.changeIndex(var10);
         var4.changeIndex(var1);

         for(int var15 = 0; var15 < this.HFEDeg; ++var15) {
            this.mul_gf2n(var4, var11, var6);
            var4.move(this.NB_WORD_GFqn);
            var11.move(this.NB_WORD_GFqn);
         }

         for(int var13 = this.KP + 1; var13 < this.HFEDeg; ++var13) {
            var6.move(this.NB_WORD_GFqn);
            var4.changeIndex(var1);

            for(int var16 = 0; var16 < this.HFEDeg; ++var16) {
               this.mul_rem_xorrange(var4, var11, var6);
               var4.move(this.NB_WORD_GFqn);
               var11.move(this.NB_WORD_GFqn);
            }
         }

         for(int var14 = 0; var14 < this.KP; ++var14) {
            var1.setXorRange(var14 * this.POW_II * this.NB_WORD_GFqn, var5, var14 * this.NB_WORD_GFqn, this.NB_WORD_GFqn);
         }
      }

      this.for_sqr_divsqr(var1, 0, (this.HFEn - this.HFEDegI) % this.II, var2, var3);
   }

   private void for_sqr_divsqr(Pointer var1, int var2, int var3, SecretKeyHFE.complete_sparse_monic_gf2nx var4, Pointer var5) {
      for(int var6 = var2; var6 < var3; ++var6) {
         this.sqr_gf2nx(var1, this.HFEDeg - 1);
         this.divsqr_r_HFE_cstdeg_gf2nx(var1, this.HFEDeg - 1 << 1, this.HFEDeg - 1 << 1, this.HFEDeg, var4, var5);
      }

   }

   private void loop_sqr(Pointer var1, Pointer var2) {
      for(int var3 = 0; var3 < this.HFEDeg; ++var3) {
         this.sqr_gf2n(var1, var3 * this.NB_WORD_GFqn, var2, var3 * this.NB_WORD_GFqn);
      }

   }

   private void for_copy_move(Pointer var1, SecretKeyHFE.complete_sparse_monic_gf2nx var2) {
      int var3 = 1;

      for(int var4 = this.NB_WORD_GFqn; var3 < this.NB_COEFS_HFEPOLY; var4 += this.NB_WORD_GFqn) {
         var1.move(var2.L[var3]);
         var1.copyFrom(0, var2.poly, var3 * this.NB_WORD_GFqn, this.NB_WORD_GFqn);
         ++var3;
      }

   }

   private void divsqr_r_HFE_cstdeg_gf2nx(Pointer var1, int var2, int var3, int var4, SecretKeyHFE.complete_sparse_monic_gf2nx var5, Pointer var6) {
      Pointer var7 = new Pointer(var1, var2 * this.NB_WORD_GFqn);
      Pointer var8 = new Pointer();

      for(int var9 = var3; var9 >= var4; --var9) {
         var8.changeIndex(var7, -this.HFEDeg * this.NB_WORD_GFqn);
         this.mul_rem_xorrange(var8, var7, var6);

         for(int var10 = 1; var10 < this.NB_COEFS_HFEPOLY; ++var10) {
            var8.move(var5.L[var10]);
            this.mul_rem_xorrange(var8, var7, var5.poly, var10 * this.NB_WORD_GFqn);
         }

         var7.move(-this.NB_WORD_GFqn);
      }

   }

   private void sqr_gf2nx(Pointer var1, int var2) {
      int var3 = this.NB_WORD_GFqn * var2;
      int var4 = var1.getIndex();
      var1.move(var3);
      Pointer var5 = new Pointer(var1, var3);

      for(int var6 = 0; var6 < var2; ++var6) {
         this.sqr_gf2n(var5, var1);
         var1.move(-this.NB_WORD_GFqn);
         var5.move(-this.NB_WORD_GFqn);
         var5.setRangeClear(0, this.NB_WORD_GFqn);
         var5.move(-this.NB_WORD_GFqn);
      }

      this.sqr_gf2n(var1, var1);
      var1.changeIndex(var4);
   }

   int div_r_gf2nx(Pointer var1, int var2, Pointer var3, int var4) {
      Pointer var5 = new Pointer(this.NB_WORD_GFqn);
      Pointer var6 = new Pointer(this.NB_WORD_GFqn);
      Pointer var7 = new Pointer(var1);
      this.inv_gf2n(var6, var3, var4 * this.NB_WORD_GFqn);

      while(var2 >= var4) {
         var2 = var1.searchDegree(var2, var4, this.NB_WORD_GFqn);
         if (var2 < var4) {
            break;
         }

         var7.changeIndex((var2 - var4) * this.NB_WORD_GFqn);
         this.mul_gf2n(var5, var1, var2 * this.NB_WORD_GFqn, var6);
         this.for_mul_rem_xor_move(var7, var5, var3, 0, var4);
         --var2;
      }

      var2 = var1.searchDegree(var2, 1, this.NB_WORD_GFqn);
      return var2;
   }

   private void div_q_monic_gf2nx(Pointer param1, int param2, Pointer param3, int param4) {
      // $FF: Couldn't be decompiled
   }

   private int div_r_monic_gf2nx(Pointer var1, int var2, Pointer var3, int var4) {
      Pointer var5 = new Pointer();

      for(Pointer var6 = new Pointer(); var2 >= var4; --var2) {
         var2 = var1.searchDegree(var2, var4, this.NB_WORD_GFqn);
         if (var2 < var4) {
            break;
         }

         var5.changeIndex(var1, var2 * this.NB_WORD_GFqn);
         var6.changeIndex(var5, -var4 * this.NB_WORD_GFqn);
         this.for_mul_rem_xor_move(var6, var5, var3, 0, var4);
      }

      if (var2 == -1) {
         ++var2;
      }

      var2 = var1.searchDegree(var2, 1, this.NB_WORD_GFqn);
      return var2;
   }

   private void for_mul_rem_xor_move(Pointer var1, Pointer var2, Pointer var3, int var4, int var5) {
      int var6 = var4;

      for(int var7 = var4 * this.NB_WORD_GFqn; var6 < var5; var7 += this.NB_WORD_GFqn) {
         this.mul_rem_xorrange(var1, var2, var3, var7);
         var1.move(this.NB_WORD_GFqn);
         ++var6;
      }

   }

   private void inv_gf2n(Pointer var1, Pointer var2, int var3) {
      int var4 = var2.getIndex();
      var2.move(var3);
      Pointer var5 = new Pointer(this.NB_WORD_GFqn);
      var1.copyFrom(var2, this.NB_WORD_GFqn);

      for(int var7 = this.HFEn_1rightmost - 1; var7 != -1; --var7) {
         int var6 = this.HFEn - 1 >>> var7 + 1;
         this.sqr_gf2n(var5, var1);

         for(int var8 = 1; var8 < var6; ++var8) {
            this.sqr_gf2n(var5, var5);
         }

         this.mul_gf2n(var1, var1, var5);
         if ((this.HFEn - 1 >>> var7 & 1) != 0) {
            this.sqr_gf2n(var5, var1);
            this.mul_gf2n(var1, var2, var5);
         }
      }

      this.sqr_gf2n(var1, var1);
      var2.changeIndex(var4);
   }

   private void convMonic_gf2nx(Pointer var1, int var2) {
      Pointer var3 = new Pointer(this.NB_WORD_GFqn);
      int var4 = var1.getIndex();
      var1.move(var2 * this.NB_WORD_GFqn);
      this.inv_gf2n(var3, var1, 0);
      var1.set1_gf2n(0, this.NB_WORD_GFqn);

      for(int var5 = var2 - 1; var5 != -1; --var5) {
         var1.move(-this.NB_WORD_GFqn);
         this.mul_gf2n(var1, var1, var3);
      }

      var1.changeIndex(var4);
   }

   private void findRootsSplit_gf2nx(Pointer var1, Pointer var2, int var3) {
      if (var3 == 1) {
         var1.copyFrom(var2, this.NB_WORD_GFqn);
      } else if ((this.HFEn & 1) != 0 && var3 == 2) {
         this.findRootsSplit2_HT_gf2nx(var1, var2);
      } else {
         Pointer var7 = new Pointer(((var3 << 1) - 1) * this.NB_WORD_GFqn);
         Pointer var8 = new Pointer(var3 * this.NB_WORD_GFqn);
         Pointer var9 = new Pointer((var3 + 1) * this.NB_WORD_GFqn);
         Pointer var10 = new Pointer(this.NB_WORD_GFqn);

         int var4;
         int var5;
         do {
            var7.setRangeClear(0, ((var3 << 1) - 1) * this.NB_WORD_GFqn);
            var8.setRangeClear(0, var3 * this.NB_WORD_GFqn);

            do {
               var8.fillRandom(this.NB_WORD_GFqn, this.random, this.NB_BYTES_GFqn);
               var8.setAnd((this.NB_WORD_GFqn << 1) - 1, this.MASK_GF2n);
            } while(var8.is0_gf2n(this.NB_WORD_GFqn, this.NB_WORD_GFqn) != 0);

            var9.copyFrom(var2, (var3 + 1) * this.NB_WORD_GFqn);
            this.traceMap_gf2nx(var8, var7, var9, var3);
            int var6 = var8.searchDegree(var3 - 1, 1, this.NB_WORD_GFqn);
            var5 = this.gcd_gf2nx(var9, var3, var8, var6);
            var4 = this.buffer;
         } while(var5 == 0 || var5 == var3);

         if (var4 != 0) {
            var8.swap(var9);
         }

         this.inv_gf2n(var10, var9, var5 * this.NB_WORD_GFqn);
         var9.set1_gf2n(var5 * this.NB_WORD_GFqn, this.NB_WORD_GFqn);
         this.for_mul(var9, var10, var5 - 1);
         this.div_q_monic_gf2nx(var2, var3, var9, var5);
         this.findRootsSplit_gf2nx(var1, var9, var5);
         this.findRootsSplit_gf2nx(new Pointer(var1, var5 * this.NB_WORD_GFqn), new Pointer(var2, var5 * this.NB_WORD_GFqn), var3 - var5);
      }
   }

   void findRootsSplit2_HT_gf2nx(Pointer var1, Pointer var2) {
      Pointer var3 = new Pointer(this.NB_WORD_GFqn);
      Pointer var4 = new Pointer(this.NB_WORD_GFqn);
      int var5 = var2.getIndex();
      this.sqr_gf2n(var3, 0, var2, this.NB_WORD_GFqn);
      this.inv_gf2n(var1, var3, 0);
      this.mul_gf2n(var3, var2, var1);
      this.findRootsSplit_x2_x_c_HT_gf2nx(var4, var3);
      var2.move(this.NB_WORD_GFqn);
      this.mul_gf2n(var1, var4, var2);
      var1.setRangeFromXor(this.NB_WORD_GFqn, var1, 0, var2, 0, this.NB_WORD_GFqn);
      var2.changeIndex(var5);
   }

   void findRootsSplit_x2_x_c_HT_gf2nx(Pointer var1, Pointer var2) {
      Pointer var3 = new Pointer(this.NB_WORD_GFqn);
      int var4 = this.HFEn + 1 >>> 1;
      var1.copyFrom(var2, this.NB_WORD_GFqn);
      int var5 = this.HFEn1h_rightmost;

      for(int var7 = 1; var5 != -1; --var5) {
         var7 <<= 1;
         this.sqr_gf2n(var3, var1);

         for(int var6 = 1; var6 < var7; ++var6) {
            this.sqr_gf2n(var3, var3);
         }

         var1.setXorRange(var3, this.NB_WORD_GFqn);
         var7 = var4 >>> var5;
         if ((var7 & 1) != 0) {
            this.sqr_gf2n(var3, var1);
            this.sqr_gf2n(var1, var3);
            var1.setXorRange(var2, this.NB_WORD_GFqn);
         }
      }

   }

   private void traceMap_gf2nx(Pointer var1, Pointer var2, Pointer var3, int var4) {
      int var5;
      for(var5 = 1; 1 << var5 < var4; ++var5) {
         this.sqr_gf2n(var1, this.NB_WORD_GFqn << var5, var1, this.NB_WORD_GFqn << var5 - 1);
      }

      if (var5 < this.HFEn) {
         this.sqr_gf2n(var2, this.NB_WORD_GFqn << var5, var1, this.NB_WORD_GFqn << var5 - 1);
         this.div_r_monic_cst_gf2nx(var2, 1 << var5, var3, var4);
         var1.setXorRange(var2, var4 * this.NB_WORD_GFqn);
         ++var5;

         while(var5 < this.HFEn) {
            this.sqr_gf2nx(var2, var4 - 1);
            this.div_r_monic_cst_gf2nx(var2, var4 - 1 << 1, var3, var4);
            var1.setXorRange(var2, var4 * this.NB_WORD_GFqn);
            ++var5;
         }
      }

   }

   private void div_r_monic_cst_gf2nx(Pointer var1, int var2, Pointer var3, int var4) {
      Pointer var5 = new Pointer();
      int var6 = var1.getIndex();
      var1.move(var2 * this.NB_WORD_GFqn);

      while(var2 >= var4) {
         var5.changeIndex(var1, -var4 * this.NB_WORD_GFqn);
         this.for_mul_rem_xor_move(var5, var1, var3, 0, var4);
         var1.move(-this.NB_WORD_GFqn);
         --var2;
      }

      var1.changeIndex(var6);
   }

   void fast_sort_gf2n(Pointer var1, int var2) {
      Pointer var3 = new Pointer(this.NB_WORD_GFqn);
      Pointer var4 = new Pointer(this.NB_WORD_GFqn);
      Pointer var5 = new Pointer();
      Pointer var6 = new Pointer();
      int var10 = GeMSSUtils.Highest_One(var2 - 1);

      for(int var11 = var10; var11 > 1; var11 >>>= 1) {
         int var8 = var2 / (var11 << 1);
         int var9 = Math.max(0, var2 - (var11 << 1) * var8 - var11);
         var5.changeIndex(var1);
         var6.changeIndex(var1, var11 * this.NB_WORD_GFqn);

         for(int var7 = 0; var7 < var8; ++var7) {
            this.for_casct_move(var5, var6, var4, var11, 1);
            var5.move(var11 * this.NB_WORD_GFqn);
            var6.move(var11 * this.NB_WORD_GFqn);
         }

         this.for_casct_move(var5, var6, var4, var9, 1);
         int var12 = var10;

         for(int var13 = 0; var12 > var11; var12 >>>= 1) {
            for(; var13 < var2 - var12; ++var13) {
               if ((var13 & var11) == 0) {
                  var6.changeIndex(var1, (var13 + var11) * this.NB_WORD_GFqn);
                  this.copy_for_casct(var3, var6, var1, var5, var4, var12, var13);
                  var6.copyFrom(var3, this.NB_WORD_GFqn);
               }
            }
         }
      }

      var5.changeIndex(var1);
      var6.changeIndex(var1, this.NB_WORD_GFqn);
      this.for_casct_move(var5, var6, var4, var2 - 1, 2);
      var6.changeIndex(var1, this.NB_WORD_GFqn);
      int var15 = var10;

      for(int var14 = 0; var15 > 1; var15 >>>= 1) {
         while(var14 < var2 - var15) {
            this.copy_for_casct(var3, var6, var1, var5, var4, var15, var14);
            var6.copyFrom(var3, this.NB_WORD_GFqn);
            var6.move(this.NB_WORD_GFqn << 1);
            var14 += 2;
         }
      }

   }

   private void copy_for_casct(Pointer var1, Pointer var2, Pointer var3, Pointer var4, Pointer var5, int var6, int var7) {
      var1.copyFrom(var2, this.NB_WORD_GFqn);

      for(int var8 = var6; var8 > 1; var8 >>>= 1) {
         var4.changeIndex(var3, (var7 + var8) * this.NB_WORD_GFqn);
         this.CMP_AND_SWAP_CST_TIME(var1, var4, var5);
      }

   }

   private void for_casct_move(Pointer var1, Pointer var2, Pointer var3, int var4, int var5) {
      int var6 = this.NB_WORD_GFqn * var5;

      for(int var7 = 0; var7 < var4; var7 += var5) {
         this.CMP_AND_SWAP_CST_TIME(var1, var2, var3);
         var1.move(var6);
         var2.move(var6);
      }

   }

   private void CMP_AND_SWAP_CST_TIME(Pointer var1, Pointer var2, Pointer var3) {
      int var10 = this.NB_WORD_GFqn - 1;
      long var8 = 0L;

      long var4;
      for(var4 = 0L; var10 > 0; --var10) {
         long var6 = var2.get(var10) ^ var1.get(var10);
         var6 = GeMSSUtils.ORBITS_UINT(var6);
         var8 |= var6;
         var4 += var8;
      }

      var10 = 0;

      for(var8 = 0L; var10 < this.NB_WORD_GFqn; ++var10) {
         long var12 = (long)var10 ^ var4;
         var12 = GeMSSUtils.NORBITS_UINT(var12);
         var8 |= -var12 & GeMSSUtils.CMP_LT_UINT(var2.get(var10), var1.get(var10));
      }

      var3.setRangeFromXorAndMask_xor(var1, var2, -var8, this.NB_WORD_GFqn);
   }

   public void compress_signHFE(byte[] var1, Pointer var2) {
      byte[] var3 = var2.toBytes(var2.getLength() << 3);
      System.arraycopy(var3, 0, var1, 0, this.NB_BYTES_GFqnv);
      int var6 = this.HFEnv;
      int var11 = (this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7);

      for(int var4 = 1; var4 < this.NB_ITE; ++var4) {
         int var9 = Math.min(this.HFEDELTA + this.HFEv, 8 - (var6 & 7) & 7);
         if ((var6 & 7) != 0) {
            if (this.HFEmr8 != 0) {
               var1[var6 >>> 3] = (byte)(var1[var6 >>> 3] ^ (var3[var11] & 255) >>> this.HFEmr8 << (var6 & 7));
               int var10 = var9 - this.VAL_BITS_M;
               if (var10 >= 0) {
                  ++var11;
               }

               if (var10 > 0) {
                  var6 += this.VAL_BITS_M;
                  var1[var6 >>> 3] = (byte)(var1[var6 >>> 3] ^ (var3[var11] & 255) << (var6 & 7));
                  var6 += var10;
               } else {
                  var6 += var9;
               }
            } else {
               var1[var6 >>> 3] = (byte)(var1[var6 >>> 3] ^ (var3[var11] & 255) << (var6 & 7));
               var6 += var9;
            }
         }

         int var7 = this.HFEDELTA + this.HFEv - var9;
         int var8 = this.HFEm + var9 & 7;
         if (var8 != 0) {
            for(int var12 = 0; var12 < var7 - 1 >>> 3; ++var12) {
               int var15 = var6 >>> 3;
               int var10002 = (var3[var11] & 255) >>> var8;
               ++var11;
               var1[var15] = (byte)(var10002 ^ (var3[var11] & 255) << 8 - var8);
               var6 += 8;
            }

            var1[var6 >>> 3] = (byte)((var3[var11++] & 255) >>> var8);
            var7 = (var7 + 7 & 7) + 1;
            if (var7 > 8 - var8) {
               var1[var6 >>> 3] ^= (byte)((var3[var11++] & 255) << 8 - var8);
            }

            var6 += var7;
         } else {
            for(int var5 = 0; var5 < var7 + 7 >>> 3; ++var5) {
               var1[var6 >>> 3] = var3[var11++];
               var6 += 8;
            }

            var6 -= 8 - (var7 & 7) & 7;
         }

         var11 += (8 - (this.NB_BYTES_GFqnv & 7) & 7) + (this.HFEmq8 & 7);
      }

   }

   void convMQS_one_to_last_mr8_equations_gf2(byte[] var1, PointerUnion var2) {
      int var7 = 0;
      var2.moveNextBytes(this.HFEmq8);
      PointerUnion var8 = new PointerUnion(var2);
      int var9 = this.NB_MONOMIAL_PK >>> 3;

      for(int var3 = 0; var3 < this.HFEmr8; ++var3) {
         var8.changeIndex(var2);

         for(int var4 = 0; var4 < var9; ++var4) {
            int var6 = var8.getByte() >>> var3 & 1;
            var8.moveNextBytes(this.NB_BYTES_GFqm);

            for(int var5 = 1; var5 < 8; ++var5) {
               var6 ^= (var8.getByte() >>> var3 & 1) << var5;
               var8.moveNextBytes(this.NB_BYTES_GFqm);
            }

            var1[var7++] = (byte)var6;
         }

         if (this.HFENr8 != 0) {
            long var10 = var8.getWithCheck() >>> var3 & 1L;
            var8.moveNextBytes(this.NB_BYTES_GFqm);

            for(int var12 = 1; var12 < this.HFENr8; ++var12) {
               var10 ^= (var8.getWithCheck() >>> var3 & 1L) << var12;
               var8.moveNextBytes(this.NB_BYTES_GFqm);
            }

            var1[var7++] = (byte)((int)var10);
         }
      }

   }

   void convMQ_UL_gf2(byte[] var1, byte[] var2, int var3) {
      for(int var6 = 0; var6 < var3; ++var6) {
         int var4 = this.ACCESS_last_equations8 + var6 * this.NB_BYTES_EQUATION;
         int var5 = var6 * this.NB_BYTES_EQUATION;
         this.for_setPK(var1, var2, var4, var5, this.HFEnv + 1);
      }

   }

   private int for_setPK(byte[] var1, byte[] var2, int var3, int var4, int var5) {
      var1[var3] = (byte)(var2[var4] & 3);
      int var7 = 2;

      for(int var6 = 2; var6 < var5; ++var6) {
         var7 = this.setPK(var1, var2, var6, var3, var4, var7, this.HFEnv - 1, this.HFEnv - var6);
      }

      return var7;
   }

   private int setPK(byte[] var1, byte[] var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      for(int var9 = var7; var9 >= var8; ++var6) {
         var1[var4 + (var6 >>> 3)] = (byte)(var1[var4 + (var6 >>> 3)] ^ (var2[var5 + (var3 >>> 3)] >>> (var3 & 7) & 1) << (var6 & 7));
         var3 += var9;
         --var9;
      }

      this.buffer = var3;
      return var6;
   }

   void convMQS_one_eq_to_hybrid_rep8_comp_gf2(byte[] var1, PointerUnion var2, byte[] var3) {
      int var5 = 0;
      this.convMQ_UL_gf2(var1, var3, this.HFEmr8);

      for(int var4 = 0; var4 < this.NB_MONOMIAL_PK; ++var4) {
         var5 = var2.toBytesMove(var1, var5, this.HFEmq8);
         if (this.HFEmr8 != 0) {
            var2.moveNextByte();
         }
      }

   }

   void convMQS_one_eq_to_hybrid_rep8_uncomp_gf2(byte[] var1, PointerUnion var2, byte[] var3) {
      int var5 = this.HFEmr8 - 1;
      long var8 = 0L;
      this.convMQ_UL_gf2(var1, var3, var5);
      int var10 = this.ACCESS_last_equations8 + var5 * this.NB_BYTES_EQUATION;
      int var11 = var5 * this.NB_BYTES_EQUATION;
      int var6 = this.for_setPK(var1, var3, var10, var11, this.HFEnv);
      int var7 = this.HFEnv;
      var6 = this.setPK(var1, var3, var7, var10, var11, var6, this.HFEnv - 1, this.LOST_BITS);
      var5 = this.LOST_BITS - 1;

      for(int var15 = this.buffer; var5 >= 0; ++var6) {
         var8 ^= (long)(var3[var11 + (var15 >>> 3)] >>> (var15 & 7) & 1) << this.LOST_BITS - 1 - var5;
         var15 += var5;
         --var5;
      }

      var10 = this.ACCESS_last_equations8 - 1;

      for(int var13 = 0; var13 < this.HFEmr8 - 1; ++var13) {
         var10 += this.NB_BYTES_EQUATION;
         var1[var10] = (byte)(var1[var10] ^ (byte)((int)(var8 >>> var13 * this.HFENr8c)) << this.HFENr8);
      }

      var2.indexReset();
      int var4 = 0;

      for(int var17 = 0; var4 < this.NB_MONOMIAL_PK; ++var4) {
         var17 = var2.toBytesMove(var1, var17, this.HFEmq8);
         var2.moveNextByte();
      }

   }

   public int crypto_sign_open(byte[] var1, byte[] var2, byte[] var3) {
      PointerUnion var4 = new PointerUnion(var1);
      long var6 = 0L;
      if (this.HFENr8 != 0 && this.HFEmr8 > 1) {
         PointerUnion var8 = new PointerUnion(var4);
         var8.moveNextBytes(this.ACCESS_last_equations8 - 1);

         for(int var5 = 0; var5 < this.HFEmr8 - 1; ++var5) {
            var8.moveNextBytes(this.NB_BYTES_EQUATION);
            var6 ^= ((long)var8.getByte() & 255L) >>> this.HFENr8 << var5 * this.HFENr8c;
         }
      }

      if (this.HFEmr8 == 0) {
         Pointer var15 = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
         Pointer var17 = new Pointer(this.NB_WORD_GF2nv);
         Pointer var10 = new Pointer(var17);
         Pointer var18 = new Pointer(this.SIZE_DIGEST_UINT);
         var15.fill(0, var3, 0, this.NB_BYTES_GFqnv);
         byte[] var12 = new byte[64];
         this.getSHA3Hash(var18, 0, 64, var2, 0, var2.length, var12);
         this.evalMQSnocst8_quo_gf2(var10, var15, var4);
         return var10.isEqual_nocst_gf2(var18, this.NB_WORD_GF2m);
      } else {
         Pointer var14 = new Pointer(1 + this.NB_WORD_UNCOMP_EQ * this.HFEmr8);
         long var9 = 0L;
         PointerUnion var11 = new PointerUnion(var4);

         int var13;
         for(var13 = 0; var13 < this.HFEmr8 - 1; ++var13) {
            var11.setByteIndex(this.ACCESS_last_equations8 + var13 * this.NB_BYTES_EQUATION);
            var9 ^= this.convMQ_uncompressL_gf2(new Pointer(var14, 1 + var13 * this.NB_WORD_UNCOMP_EQ), var11) << var13;
         }

         var11.setByteIndex(this.ACCESS_last_equations8 + var13 * this.NB_BYTES_EQUATION);
         var9 ^= this.convMQ_last_uncompressL_gf2(new Pointer(var14, 1 + var13 * this.NB_WORD_UNCOMP_EQ), var11) << var13;
         if (this.HFENr8 != 0) {
            if (this.HFEnvr == 0) {
               var14.setXor((var13 + 1) * this.NB_WORD_UNCOMP_EQ, var6 << 64 - this.LOST_BITS);
            } else if (this.HFEnvr > this.LOST_BITS) {
               var14.setXor((var13 + 1) * this.NB_WORD_UNCOMP_EQ, var6 << this.HFEnvr - this.LOST_BITS);
            } else if (this.HFEnvr == this.LOST_BITS) {
               var14.set((var13 + 1) * this.NB_WORD_UNCOMP_EQ, var6);
            } else {
               var14.setXor((var13 + 1) * this.NB_WORD_UNCOMP_EQ - 1, var6 << 64 - (this.LOST_BITS - this.HFEnvr));
               var14.set((var13 + 1) * this.NB_WORD_UNCOMP_EQ, var6 >>> this.LOST_BITS - this.HFEnvr);
            }
         }

         var14.set(var9 << this.HFEmr - this.HFEmr8);
         return this.sign_openHFE_huncomp_pk(var2, var2.length, var3, var4, new PointerUnion(var14));
      }
   }

   void changeVariablesMQS64_gf2(Pointer var1, Pointer var2) {
      Pointer var3 = new Pointer();
      Pointer var9 = new Pointer(this.HFEnv * this.HFEnv * this.NB_WORD_GFqn);
      Pointer var10 = new Pointer(var1, this.NB_WORD_GFqn);
      Pointer var11 = new Pointer(var9);
      Pointer var12 = new Pointer(var2);

      for(int var6 = 0; var6 < this.HFEnv; ++var6) {
         var3.changeIndex(var10);

         for(int var4 = 0; var4 < this.HFEnvq; ++var4) {
            for(int var5 = 0; var5 < 64; ++var5) {
               this.LOOPKR(var3, var11, var12.get() >>> var5, var5, 64);
               this.LOOPK_COMPLETE(var11, var12, var3, 1, this.HFEnvq - var4);
            }

            var12.moveIncremental();
         }

         if (this.HFEnvr != 0) {
            for(int var16 = 0; var16 < this.HFEnvr; ++var16) {
               this.LOOPKR(var3, var11, var12.get() >>> var16, var16, this.HFEnvr);
               var11.move(this.NB_WORD_GFqn);
            }

            var12.moveIncremental();
         }
      }

      var10.changeIndex(var9);
      var11.changeIndex(var1, this.NB_WORD_GFqn);
      Pointer var13 = new Pointer(var2);

      for(int var7 = 0; var7 < this.HFEnvq; ++var7) {
         for(int var8 = 0; var8 < 64; ++var8) {
            var12.changeIndex(var13);
            this.LOOPIR_INIT(var11, var3, var10, var12, var8, 64);

            for(int var14 = var7 + 1; var14 < this.HFEnvq; ++var14) {
               this.LOOPIR_INIT(var11, var3, var10, var12, 0, 64);
            }

            if (this.HFEnvr != 0) {
               this.LOOPIR_INIT(var11, var3, var10, var12, 0, this.HFEnvr);
            }

            var10.changeIndex(var3);
            var13.move(this.NB_WORD_GF2nv);
         }
      }

      if (this.HFEnvr != 0) {
         for(int var18 = 0; var18 < this.HFEnvr; ++var18) {
            var12.changeIndex(var13);
            var3.changeIndex(var10);
            this.LOOPIR_INIT(var11, var3, var10, var12, var18, this.HFEnvr);
            var10.changeIndex(var3);
            var13.move(this.NB_WORD_GF2nv);
         }
      }

      var10.changeIndex(var9);
      var11.changeIndex(var1, this.NB_WORD_GFqn);
      var12.changeIndex(var2);

      for(int var17 = 0; var17 < this.HFEnvq; ++var17) {
         for(int var19 = 0; var19 < 64; ++var19) {
            var11.move(this.NB_WORD_GFqn);
            var10.move(this.HFEnv * this.NB_WORD_GFqn);
            var3.changeIndex(var10);
            this.LOOPIR_LOOPK_COMPLETE(var11, var12, var3, var19 + 1, 64);

            for(int var15 = var17 + 1; var15 < this.HFEnvq; ++var15) {
               this.LOOPIR_LOOPK_COMPLETE(var11, var12, var3, 0, 64);
            }

            if (this.HFEnvr != 0) {
               this.LOOPIR_LOOPK_COMPLETE(var11, var12, var3, 0, this.HFEnvr);
            }

            var12.move(this.NB_WORD_GF2nv);
         }
      }

      if (this.HFEnvr != 0) {
         for(int var20 = 0; var20 < this.HFEnvr - 1; ++var20) {
            var11.move(this.NB_WORD_GFqn);
            var10.move(this.HFEnv * this.NB_WORD_GFqn);
            var3.changeIndex(var10);
            this.LOOPIR_LOOPK_COMPLETE(var11, var12, var3, var20 + 1, this.HFEnvr);
            var12.move(this.NB_WORD_GF2nv);
         }
      }

      var1.indexReset();
      var2.indexReset();
   }

   private void LOOPIR_INIT(Pointer var1, Pointer var2, Pointer var3, Pointer var4, int var5, int var6) {
      for(int var7 = var5; var7 < var6; ++var7) {
         var1.setRangeClear(0, this.NB_WORD_GFqn);
         var2.changeIndex(var3);
         this.LOOPK_COMPLETE(var1, var4, var2, 0, this.HFEnvq);
         var4.move(this.NB_WORD_GF2nv);
      }

   }

   private void LOOPIR_LOOPK_COMPLETE(Pointer var1, Pointer var2, Pointer var3, int var4, int var5) {
      for(int var6 = var4; var6 < var5; ++var6) {
         this.LOOPK_COMPLETE(var1, var2, var3, 0, this.HFEnvq);
      }

   }

   private void LOOPK_COMPLETE(Pointer var1, Pointer var2, Pointer var3, int var4, int var5) {
      for(int var6 = var4; var6 < var5; ++var6) {
         this.LOOPKR(var3, var1, var2.get(var6), 0, 64);
      }

      if (this.HFEnvr != 0) {
         this.LOOPKR(var3, var1, var2.get(var5), 0, this.HFEnvr);
      }

      var1.move(this.NB_WORD_GFqn);
   }

   private void LOOPKR(Pointer var1, Pointer var2, long var3, int var5, int var6) {
      for(int var7 = var5; var7 < var6; ++var7) {
         var2.setXorRangeAndMaskMove(var1, this.NB_WORD_GFqn, -(var3 & 1L));
         var3 >>>= 1;
      }

   }

   int interpolateHFE_FS_ref(Pointer var1, Pointer var2, Pointer var3) {
      Pointer var4 = new Pointer(this.NB_WORD_GF2nv);
      Pointer var5 = new Pointer();
      Pointer var6 = new Pointer();
      Pointer var9 = new Pointer(this.HFEnv * this.NB_WORD_GFqn);
      var1.copyFrom(var2, this.NB_WORD_GFqn);
      Pointer var10 = new Pointer(var3);
      Pointer var11 = new Pointer(var9);

      for(int var7 = 0; var7 < this.HFEnv; ++var7) {
         this.evalHFEv_gf2nx(var11, var2, var10);
         var11.move(this.NB_WORD_GFqn);
         var10.move(this.NB_WORD_GF2nv);
      }

      var10.changeIndex(var3);
      var11.changeIndex(var9);

      for(int var12 = 0; var12 < this.HFEnv; ++var12) {
         var1.move(this.NB_WORD_GFqn);
         var11.setXorRange(var2, this.NB_WORD_GFqn);
         var1.copyFrom(var11, this.NB_WORD_GFqn);
         var5.changeIndex(var11);
         var6.changeIndex(var10);

         for(int var8 = var12 + 1; var8 < this.HFEnv; ++var8) {
            var1.move(this.NB_WORD_GFqn);
            var5.move(this.NB_WORD_GFqn);
            var6.move(this.NB_WORD_GF2nv);
            var4.setRangeFromXor(var10, var6, this.NB_WORD_GF2nv);
            this.evalHFEv_gf2nx(var1, var2, var4);
            var1.setXorRangeXor(0, var11, 0, var5, 0, this.NB_WORD_GFqn);
         }

         var11.move(this.NB_WORD_GFqn);
         var10.move(this.NB_WORD_GF2nv);
      }

      var1.indexReset();
      return 0;
   }

   void evalHFEv_gf2nx(Pointer var1, Pointer var2, Pointer var3) {
      Pointer var4 = new Pointer(this.NB_WORD_MUL);
      Pointer var5 = new Pointer(this.NB_WORD_MUL);
      Pointer var6 = new Pointer((this.HFEDegI + 1) * this.NB_WORD_GFqn);
      Pointer var7 = new Pointer();
      int var9 = var2.getIndex();
      Pointer var10 = new Pointer(this.NB_WORD_GFqv);
      Pointer var11 = new Pointer(var6, this.NB_WORD_GFqn);
      var6.copyFrom(var3, this.NB_WORD_GFqn);
      var6.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);

      for(int var8 = 1; var8 <= this.HFEDegI; ++var8) {
         this.sqr_gf2n(var11, 0, var11, -this.NB_WORD_GFqn);
         var11.move(this.NB_WORD_GFqn);
      }

      int var12 = this.NB_WORD_GFqn + this.NB_WORD_GFqv == this.NB_WORD_GF2nv ? this.NB_WORD_GFqv : this.NB_WORD_GFqv - 1;
      var10.setRangeRotate(0, var3, this.NB_WORD_GFqn - 1, var12, 64 - this.HFEnr);
      if (this.NB_WORD_GFqn + this.NB_WORD_GFqv != this.NB_WORD_GF2nv) {
         var10.set(var12, var3.get(this.NB_WORD_GFqn - 1 + var12) >>> this.HFEnr);
      }

      this.evalMQSv_unrolled_gf2(var4, var10, var2);
      var2.move(this.MQv_GFqn_SIZE);
      this.vmpv_xorrange_move(var5, var10, var2);
      var11.changeIndex(var6);
      this.mul_xorrange(var4, var11, var5);

      for(int var13 = 1; var13 < this.HFEDegI; ++var13) {
         this.vmpv_xorrange_move(var5, var10, var2);
         var5.setRangeClear(this.NB_WORD_GFqn, this.NB_WORD_MMUL - this.NB_WORD_GFqn);
         var7.changeIndex(var11);
         this.for_mul_xorrange_move(var5, var2, var7, var13);
         this.rem_gf2n(var5, 0, var5);
         this.mul_xorrange(var4, var7, var5);
      }

      this.vmpv_xorrange_move(var5, var10, var2);
      var7.changeIndex(var11);
      if (this.HFEDegJ != 0) {
         var5.setRangeClear(this.NB_WORD_GFqn, this.NB_WORD_MMUL - this.NB_WORD_GFqn);
         this.for_mul_xorrange_move(var5, var2, var7, this.HFEDegJ);
         var5.setXorRange(var7, this.NB_WORD_GFqn);
         this.rem_gf2n(var5, 0, var5);
      } else {
         var5.setRangeFromXor(var5, var7, this.NB_WORD_GFqn);
      }

      var11.move(this.HFEDegI * this.NB_WORD_GFqn);
      this.mul_xorrange(var4, var11, var5);
      this.rem_gf2n(var1, 0, var4);
      var2.changeIndex(var9);
   }

   private void vmpv_xorrange_move(Pointer var1, Pointer var2, Pointer var3) {
      this.vecMatProduct(var1, var2, new Pointer(var3, this.NB_WORD_GFqn), GeMSSEngine.FunctionParams.V);
      var1.setXorRange(var3, this.NB_WORD_GFqn);
      var3.move(this.MLv_GFqn_SIZE);
   }

   private static long remainderUnsigned(long var0, long var2) {
      return var0 > 0L && var2 > 0L ? var0 % var2 : (new BigInteger(1, Pack.longToBigEndian(var0))).mod(new BigInteger(1, Pack.longToBigEndian(var2))).longValue();
   }

   static enum FunctionParams {
      NV,
      V,
      N,
      M;

      // $FF: synthetic method
      private static FunctionParams[] $values() {
         return new FunctionParams[]{NV, V, N, M};
      }
   }
}

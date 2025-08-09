package org.bouncycastle.pqc.legacy.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.pqc.legacy.crypto.rainbow.util.GF2Field;
import org.bouncycastle.pqc.legacy.crypto.rainbow.util.RainbowUtil;
import org.bouncycastle.util.Arrays;

public class Layer {
   private int vi;
   private int viNext;
   private int oi;
   private short[][][] coeff_alpha;
   private short[][][] coeff_beta;
   private short[][] coeff_gamma;
   private short[] coeff_eta;

   public Layer(byte var1, byte var2, short[][][] var3, short[][][] var4, short[][] var5, short[] var6) {
      this.vi = var1 & 255;
      this.viNext = var2 & 255;
      this.oi = this.viNext - this.vi;
      this.coeff_alpha = var3;
      this.coeff_beta = var4;
      this.coeff_gamma = var5;
      this.coeff_eta = var6;
   }

   public Layer(int var1, int var2, SecureRandom var3) {
      this.vi = var1;
      this.viNext = var2;
      this.oi = var2 - var1;
      this.coeff_alpha = new short[this.oi][this.oi][this.vi];
      this.coeff_beta = new short[this.oi][this.vi][this.vi];
      this.coeff_gamma = new short[this.oi][this.viNext];
      this.coeff_eta = new short[this.oi];
      int var4 = this.oi;

      for(int var5 = 0; var5 < var4; ++var5) {
         for(int var6 = 0; var6 < this.oi; ++var6) {
            for(int var7 = 0; var7 < this.vi; ++var7) {
               this.coeff_alpha[var5][var6][var7] = (short)(var3.nextInt() & 255);
            }
         }
      }

      for(int var8 = 0; var8 < var4; ++var8) {
         for(int var11 = 0; var11 < this.vi; ++var11) {
            for(int var13 = 0; var13 < this.vi; ++var13) {
               this.coeff_beta[var8][var11][var13] = (short)(var3.nextInt() & 255);
            }
         }
      }

      for(int var9 = 0; var9 < var4; ++var9) {
         for(int var12 = 0; var12 < this.viNext; ++var12) {
            this.coeff_gamma[var9][var12] = (short)(var3.nextInt() & 255);
         }
      }

      for(int var10 = 0; var10 < var4; ++var10) {
         this.coeff_eta[var10] = (short)(var3.nextInt() & 255);
      }

   }

   public short[][] plugInVinegars(short[] var1) {
      short var2 = 0;
      short[][] var3 = new short[this.oi][this.oi + 1];
      short[] var4 = new short[this.oi];

      for(int var5 = 0; var5 < this.oi; ++var5) {
         for(int var6 = 0; var6 < this.vi; ++var6) {
            for(int var7 = 0; var7 < this.vi; ++var7) {
               var2 = GF2Field.multElem(this.coeff_beta[var5][var6][var7], var1[var6]);
               var2 = GF2Field.multElem(var2, var1[var7]);
               var4[var5] = GF2Field.addElem(var4[var5], var2);
            }
         }
      }

      for(int var12 = 0; var12 < this.oi; ++var12) {
         for(int var17 = 0; var17 < this.oi; ++var17) {
            for(int var20 = 0; var20 < this.vi; ++var20) {
               var2 = GF2Field.multElem(this.coeff_alpha[var12][var17][var20], var1[var20]);
               var3[var12][var17] = GF2Field.addElem(var3[var12][var17], var2);
            }
         }
      }

      for(int var13 = 0; var13 < this.oi; ++var13) {
         for(int var18 = 0; var18 < this.vi; ++var18) {
            var2 = GF2Field.multElem(this.coeff_gamma[var13][var18], var1[var18]);
            var4[var13] = GF2Field.addElem(var4[var13], var2);
         }
      }

      for(int var14 = 0; var14 < this.oi; ++var14) {
         for(int var19 = this.vi; var19 < this.viNext; ++var19) {
            var3[var14][var19 - this.vi] = GF2Field.addElem(this.coeff_gamma[var14][var19], var3[var14][var19 - this.vi]);
         }
      }

      for(int var15 = 0; var15 < this.oi; ++var15) {
         var4[var15] = GF2Field.addElem(var4[var15], this.coeff_eta[var15]);
      }

      for(int var16 = 0; var16 < this.oi; ++var16) {
         var3[var16][this.oi] = var4[var16];
      }

      return var3;
   }

   public int getVi() {
      return this.vi;
   }

   public int getViNext() {
      return this.viNext;
   }

   public int getOi() {
      return this.oi;
   }

   public short[][][] getCoeffAlpha() {
      return this.coeff_alpha;
   }

   public short[][][] getCoeffBeta() {
      return this.coeff_beta;
   }

   public short[][] getCoeffGamma() {
      return this.coeff_gamma;
   }

   public short[] getCoeffEta() {
      return this.coeff_eta;
   }

   public boolean equals(Object var1) {
      if (var1 != null && var1 instanceof Layer) {
         Layer var2 = (Layer)var1;
         return this.vi == var2.getVi() && this.viNext == var2.getViNext() && this.oi == var2.getOi() && RainbowUtil.equals(this.coeff_alpha, var2.getCoeffAlpha()) && RainbowUtil.equals(this.coeff_beta, var2.getCoeffBeta()) && RainbowUtil.equals(this.coeff_gamma, var2.getCoeffGamma()) && RainbowUtil.equals(this.coeff_eta, var2.getCoeffEta());
      } else {
         return false;
      }
   }

   public int hashCode() {
      int var1 = this.vi;
      var1 = var1 * 37 + this.viNext;
      var1 = var1 * 37 + this.oi;
      var1 = var1 * 37 + Arrays.hashCode(this.coeff_alpha);
      var1 = var1 * 37 + Arrays.hashCode(this.coeff_beta);
      var1 = var1 * 37 + Arrays.hashCode(this.coeff_gamma);
      var1 = var1 * 37 + Arrays.hashCode(this.coeff_eta);
      return var1;
   }
}

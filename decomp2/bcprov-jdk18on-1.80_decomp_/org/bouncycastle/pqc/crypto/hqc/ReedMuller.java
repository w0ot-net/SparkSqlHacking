package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Arrays;

class ReedMuller {
   static void encodeSub(Codeword var0, int var1) {
      int var2 = Bit0Mask(var1 >> 7);
      var2 ^= Bit0Mask(var1 >> 0) & -1431655766;
      var2 ^= Bit0Mask(var1 >> 1) & -858993460;
      var2 ^= Bit0Mask(var1 >> 2) & -252645136;
      var2 ^= Bit0Mask(var1 >> 3) & -16711936;
      var2 ^= Bit0Mask(var1 >> 4) & -65536;
      var0.type32[0] = var2;
      var2 ^= Bit0Mask(var1 >> 5);
      var0.type32[1] = var2;
      var2 ^= Bit0Mask(var1 >> 6);
      var0.type32[3] = var2;
      var2 ^= Bit0Mask(var1 >> 5);
      var0.type32[2] = var2;
   }

   private static void hadamardTransform(int[] var0, int[] var1) {
      int[] var2 = Arrays.clone(var0);
      int[] var3 = Arrays.clone(var1);

      for(int var4 = 0; var4 < 7; ++var4) {
         for(int var5 = 0; var5 < 64; ++var5) {
            var3[var5] = var2[2 * var5] + var2[2 * var5 + 1];
            var3[var5 + 64] = var2[2 * var5] - var2[2 * var5 + 1];
         }

         int[] var6 = var2;
         var2 = var3;
         var3 = var6;
      }

      System.arraycopy(var3, 0, var0, 0, var0.length);
      System.arraycopy(var2, 0, var1, 0, var1.length);
   }

   private static void expandThenSum(int[] var0, Codeword[] var1, int var2, int var3) {
      for(int var4 = 0; var4 < 4; ++var4) {
         for(int var5 = 0; var5 < 32; ++var5) {
            long var6 = (long)(var1[0 + var2].type32[var4] >> var5 & 1);
            var0[var4 * 32 + var5] = var1[0 + var2].type32[var4] >> var5 & 1;
         }
      }

      for(int var8 = 1; var8 < var3; ++var8) {
         for(int var9 = 0; var9 < 4; ++var9) {
            for(int var10 = 0; var10 < 32; ++var10) {
               var0[var9 * 32 + var10] += var1[var8 + var2].type32[var9] >> var10 & 1;
            }
         }
      }

   }

   private static int findPeaks(int[] var0) {
      int var1 = 0;
      int var2 = 0;
      int var3 = 0;

      for(int var4 = 0; var4 < 128; ++var4) {
         int var5 = var0[var4];
         int var6 = var5 > 0 ? -1 : 0;
         int var7 = var6 & var5 | ~var6 & -var5;
         var2 = var7 > var1 ? var5 : var2;
         var3 = var7 > var1 ? var4 : var3;
         var1 = var7 > var1 ? var7 : var1;
      }

      int var9 = var2 > 0 ? 1 : 0;
      var3 |= 128 * var9;
      return var3;
   }

   private static int Bit0Mask(int var0) {
      return -(var0 & 1) & -1;
   }

   public static void encode(long[] var0, byte[] var1, int var2, int var3) {
      byte[] var4 = Arrays.clone(var1);
      Codeword[] var5 = new Codeword[var2 * var3];

      for(int var6 = 0; var6 < var5.length; ++var6) {
         var5[var6] = new Codeword();
      }

      for(int var9 = 0; var9 < var2; ++var9) {
         int var7 = var9 * var3;
         encodeSub(var5[var7], var4[var9]);

         for(int var8 = 1; var8 < var3; ++var8) {
            var5[var7 + var8] = var5[var7];
         }
      }

      int[] var10 = new int[var5.length * 4];
      int var11 = 0;

      for(int var12 = 0; var12 < var5.length; ++var12) {
         System.arraycopy(var5[var12].type32, 0, var10, var11, var5[var12].type32.length);
         var11 += 4;
      }

      Utils.fromByte32ArrayToLongArray(var0, var10);
   }

   public static void decode(byte[] var0, long[] var1, int var2, int var3) {
      byte[] var4 = Arrays.clone(var0);
      Codeword[] var5 = new Codeword[var1.length / 2];
      int[] var6 = new int[var1.length * 2];
      Utils.fromLongArrayToByte32Array(var6, var1);

      for(int var7 = 0; var7 < var5.length; ++var7) {
         var5[var7] = new Codeword();

         for(int var8 = 0; var8 < 4; ++var8) {
            var5[var7].type32[var8] = var6[var7 * 4 + var8];
         }
      }

      int[] var11 = new int[128];

      for(int var12 = 0; var12 < var2; ++var12) {
         expandThenSum(var11, var5, var12 * var3, var3);
         int[] var9 = new int[128];
         hadamardTransform(var11, var9);
         var9[0] -= 64 * var3;
         var4[var12] = (byte)findPeaks(var9);
      }

      int[] var13 = new int[var5.length * 4];
      int var14 = 0;

      for(int var10 = 0; var10 < var5.length; ++var10) {
         System.arraycopy(var5[var10].type32, 0, var13, var14, var5[var10].type32.length);
         var14 += 4;
      }

      Utils.fromByte32ArrayToLongArray(var1, var13);
      System.arraycopy(var4, 0, var0, 0, var0.length);
   }

   static class Codeword {
      int[] type32 = new int[4];
      int[] type8 = new int[16];

      public Codeword() {
      }
   }
}

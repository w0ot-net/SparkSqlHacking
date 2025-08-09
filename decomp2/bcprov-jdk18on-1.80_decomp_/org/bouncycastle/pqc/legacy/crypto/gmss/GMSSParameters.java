package org.bouncycastle.pqc.legacy.crypto.gmss;

import org.bouncycastle.util.Arrays;

public class GMSSParameters {
   private int numOfLayers;
   private int[] heightOfTrees;
   private int[] winternitzParameter;
   private int[] K;

   public GMSSParameters(int var1, int[] var2, int[] var3, int[] var4) throws IllegalArgumentException {
      this.init(var1, var2, var3, var4);
   }

   private void init(int var1, int[] var2, int[] var3, int[] var4) throws IllegalArgumentException {
      boolean var5 = true;
      String var6 = "";
      this.numOfLayers = var1;
      if (this.numOfLayers != var3.length || this.numOfLayers != var2.length || this.numOfLayers != var4.length) {
         var5 = false;
         var6 = "Unexpected parameterset format";
      }

      for(int var7 = 0; var7 < this.numOfLayers; ++var7) {
         if (var4[var7] < 2 || (var2[var7] - var4[var7]) % 2 != 0) {
            var5 = false;
            var6 = "Wrong parameter K (K >= 2 and H-K even required)!";
         }

         if (var2[var7] < 4 || var3[var7] < 2) {
            var5 = false;
            var6 = "Wrong parameter H or w (H > 3 and w > 1 required)!";
         }
      }

      if (var5) {
         this.heightOfTrees = Arrays.clone(var2);
         this.winternitzParameter = Arrays.clone(var3);
         this.K = Arrays.clone(var4);
      } else {
         throw new IllegalArgumentException(var6);
      }
   }

   public GMSSParameters(int var1) throws IllegalArgumentException {
      if (var1 <= 10) {
         int[] var2 = new int[]{10};
         int[] var3 = new int[]{3};
         int[] var4 = new int[]{2};
         this.init(var2.length, var2, var3, var4);
      } else if (var1 <= 20) {
         int[] var5 = new int[]{10, 10};
         int[] var7 = new int[]{5, 4};
         int[] var9 = new int[]{2, 2};
         this.init(var5.length, var5, var7, var9);
      } else {
         int[] var6 = new int[]{10, 10, 10, 10};
         int[] var8 = new int[]{9, 9, 9, 3};
         int[] var10 = new int[]{2, 2, 2, 2};
         this.init(var6.length, var6, var8, var10);
      }

   }

   public int getNumOfLayers() {
      return this.numOfLayers;
   }

   public int[] getHeightOfTrees() {
      return Arrays.clone(this.heightOfTrees);
   }

   public int[] getWinternitzParameter() {
      return Arrays.clone(this.winternitzParameter);
   }

   public int[] getK() {
      return Arrays.clone(this.K);
   }
}

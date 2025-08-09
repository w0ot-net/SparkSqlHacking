package org.bouncycastle.pqc.crypto.falcon;

class FalconCommon {
   static final int[] l2bound = new int[]{0, 101498, 208714, 428865, 892039, 1852696, 3842630, 7959734, 16468416, 34034726, 70265242};

   void hash_to_point_vartime(SHAKE256 var1, short[] var2, int var3, int var4) {
      int var5 = 1 << var4;

      while(var5 > 0) {
         byte[] var6 = new byte[2];
         var1.inner_shake256_extract(var6, 0, 2);
         int var7 = (var6[0] & 255) << 8 | var6[1] & 255;
         if (var7 < 61445) {
            while(var7 >= 12289) {
               var7 -= 12289;
            }

            var2[var3++] = (short)var7;
            --var5;
         }
      }

   }

   void hash_to_point_ct(SHAKE256 var1, short[] var2, int var3, int var4, short[] var5, int var6) {
      short[] var7 = new short[]{0, 65, 67, 71, 77, 86, 100, 122, 154, 205, 287};
      short[] var15 = new short[63];
      int var8 = 1 << var4;
      int var9 = var8 << 1;
      short var13 = var7[var4];
      int var11 = var8 + var13;
      int var14 = var6;

      for(int var10 = 0; var10 < var11; ++var10) {
         byte[] var16 = new byte[2];
         var1.inner_shake256_extract(var16, 0, var16.length);
         int var17 = (var16[0] & 255) << 8 | var16[1] & 255;
         int var18 = var17 - (24578 & (var17 - 24578 >>> 31) - 1);
         var18 -= 24578 & (var18 - 24578 >>> 31) - 1;
         var18 -= 12289 & (var18 - 12289 >>> 31) - 1;
         var18 |= (var17 - '\uf005' >>> 31) - 1;
         if (var10 < var8) {
            var2[var3 + var10] = (short)var18;
         } else if (var10 < var9) {
            var5[var14 + var10 - var8] = (short)var18;
         } else {
            var15[var10 - var9] = (short)var18;
         }
      }

      for(int var12 = 1; var12 <= var13; var12 <<= 1) {
         int var26 = 0;

         for(int var25 = 0; var25 < var11; ++var25) {
            byte var19;
            short var22;
            int var27;
            if (var25 < var8) {
               var19 = 1;
               var27 = var3 + var25;
               var22 = var2[var27];
            } else if (var25 < var9) {
               var19 = 2;
               var27 = var14 + var25 - var8;
               var22 = var5[var27];
            } else {
               var19 = 3;
               var27 = var25 - var9;
               var22 = var15[var27];
            }

            int var21 = var25 - var26;
            int var24 = (var22 >>> 15) - 1;
            var26 -= var24;
            if (var25 >= var12) {
               byte var20;
               short var23;
               int var31;
               if (var25 - var12 < var8) {
                  var20 = 1;
                  var31 = var3 + var25 - var12;
                  var23 = var2[var31];
               } else if (var25 - var12 < var9) {
                  var20 = 2;
                  var31 = var14 + (var25 - var12) - var8;
                  var23 = var5[var31];
               } else {
                  var20 = 3;
                  var31 = var25 - var12 - var9;
                  var23 = var15[var31];
               }

               var24 &= -((var21 & var12) + 511 >> 9);
               if (var19 == 1) {
                  var2[var27] = (short)(var22 ^ var24 & (var22 ^ var23));
               } else if (var19 == 2) {
                  var5[var27] = (short)(var22 ^ var24 & (var22 ^ var23));
               } else {
                  var15[var27] = (short)(var22 ^ var24 & (var22 ^ var23));
               }

               if (var20 == 1) {
                  var2[var31] = (short)(var23 ^ var24 & (var22 ^ var23));
               } else if (var20 == 2) {
                  var5[var31] = (short)(var23 ^ var24 & (var22 ^ var23));
               } else {
                  var15[var31] = (short)(var23 ^ var24 & (var22 ^ var23));
               }
            }
         }
      }

   }

   int is_short(short[] var1, int var2, short[] var3, int var4, int var5) {
      int var6 = 1 << var5;
      int var8 = 0;
      int var9 = 0;

      for(int var7 = 0; var7 < var6; ++var7) {
         short var10 = var1[var2 + var7];
         int var11 = var8 + var10 * var10;
         var9 |= var11;
         var10 = var3[var4 + var7];
         var8 = var11 + var10 * var10;
         var9 |= var8;
      }

      var8 |= -(var9 >>> 31);
      return ((long)var8 & 4294967295L) <= (long)l2bound[var5] ? 1 : 0;
   }

   int is_short_half(int var1, short[] var2, int var3, int var4) {
      int var5 = 1 << var4;
      int var7 = -(var1 >>> 31);

      for(int var6 = 0; var6 < var5; ++var6) {
         short var8 = var2[var3 + var6];
         var1 += var8 * var8;
         var7 |= var1;
      }

      var1 |= -(var7 >>> 31);
      return ((long)var1 & 4294967295L) <= (long)l2bound[var4] ? 1 : 0;
   }
}

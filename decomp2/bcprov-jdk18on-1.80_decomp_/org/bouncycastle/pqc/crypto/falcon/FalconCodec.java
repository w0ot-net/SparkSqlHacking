package org.bouncycastle.pqc.crypto.falcon;

class FalconCodec {
   final byte[] max_fg_bits = new byte[]{0, 8, 8, 8, 8, 8, 7, 7, 6, 6, 5};
   final byte[] max_FG_bits = new byte[]{0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
   final byte[] max_sig_bits = new byte[]{0, 10, 11, 11, 12, 12, 12, 12, 12, 12, 12};

   int modq_encode(byte[] var1, int var2, int var3, short[] var4, int var5, int var6) {
      int var7 = 1 << var6;

      for(int var9 = 0; var9 < var7; ++var9) {
         if ((var4[var5 + var9] & '\uffff') >= 12289) {
            return 0;
         }
      }

      int var8 = var7 * 14 + 7 >> 3;
      if (var1 == null) {
         return var8;
      } else if (var8 > var3) {
         return 0;
      } else {
         int var10 = var2;
         int var11 = 0;
         int var12 = 0;

         for(int var13 = 0; var13 < var7; ++var13) {
            var11 = var11 << 14 | var4[var5 + var13] & '\uffff';

            for(var12 += 14; var12 >= 8; var1[var10++] = (byte)(var11 >> var12)) {
               var12 -= 8;
            }
         }

         if (var12 > 0) {
            var1[var10] = (byte)(var11 << 8 - var12);
         }

         return var8;
      }
   }

   int modq_decode(short[] var1, int var2, int var3, byte[] var4, int var5, int var6) {
      int var7 = 1 << var3;
      int var8 = var7 * 14 + 7 >> 3;
      if (var8 > var6) {
         return 0;
      } else {
         int var10 = var5;
         int var11 = 0;
         int var12 = 0;
         int var9 = 0;

         while(var9 < var7) {
            var11 = var11 << 8 | var4[var10++] & 255;
            var12 += 8;
            if (var12 >= 14) {
               var12 -= 14;
               int var13 = var11 >>> var12 & 16383;
               if (var13 >= 12289) {
                  return 0;
               }

               var1[var2 + var9] = (short)var13;
               ++var9;
            }
         }

         if ((var11 & (1 << var12) - 1) != 0) {
            return 0;
         } else {
            return var8;
         }
      }
   }

   int trim_i16_encode(byte[] var1, int var2, int var3, short[] var4, int var5, int var6, int var7) {
      int var8 = 1 << var6;
      int var12 = (1 << var7 - 1) - 1;
      int var11 = -var12;

      for(int var9 = 0; var9 < var8; ++var9) {
         if (var4[var5 + var9] < var11 || var4[var5 + var9] > var12) {
            return 0;
         }
      }

      int var10 = var8 * var7 + 7 >> 3;
      if (var1 == null) {
         return var10;
      } else if (var10 > var3) {
         return 0;
      } else {
         int var13 = var2;
         int var14 = 0;
         int var16 = 0;
         int var15 = (1 << var7) - 1;

         for(int var17 = 0; var17 < var8; ++var17) {
            var14 = var14 << var7 | var4[var5 + var17] & 4095 & var15;

            for(var16 += var7; var16 >= 8; var1[var13++] = (byte)(var14 >> var16)) {
               var16 -= 8;
            }
         }

         if (var16 > 0) {
            var1[var13++] = (byte)(var14 << 8 - var16);
         }

         return var10;
      }
   }

   int trim_i16_decode(short[] var1, int var2, int var3, int var4, byte[] var5, int var6, int var7) {
      int var8 = 1 << var3;
      int var9 = var8 * var4 + 7 >> 3;
      if (var9 > var7) {
         return 0;
      } else {
         int var10 = var6;
         int var11 = 0;
         int var12 = 0;
         int var15 = 0;
         int var13 = (1 << var4) - 1;
         int var14 = 1 << var4 - 1;

         while(var11 < var8) {
            var12 = var12 << 8 | var5[var10++] & 255;

            for(var15 += 8; var15 >= var4 && var11 < var8; ++var11) {
               var15 -= var4;
               int var16 = var12 >>> var15 & var13;
               var16 |= -(var16 & var14);
               if (var16 == -var14) {
                  return 0;
               }

               var16 |= -(var16 & var14);
               var1[var2 + var11] = (short)var16;
            }
         }

         if ((var12 & (1 << var15) - 1) != 0) {
            return 0;
         } else {
            return var9;
         }
      }
   }

   int trim_i8_encode(byte[] var1, int var2, int var3, byte[] var4, int var5, int var6, int var7) {
      int var8 = 1 << var6;
      int var12 = (1 << var7 - 1) - 1;
      int var11 = -var12;

      for(int var9 = 0; var9 < var8; ++var9) {
         if (var4[var5 + var9] < var11 || var4[var5 + var9] > var12) {
            return 0;
         }
      }

      int var10 = var8 * var7 + 7 >> 3;
      if (var1 == null) {
         return var10;
      } else if (var10 > var3) {
         return 0;
      } else {
         int var13 = var2;
         int var14 = 0;
         int var16 = 0;
         int var15 = (1 << var7) - 1;

         for(int var17 = 0; var17 < var8; ++var17) {
            var14 = var14 << var7 | var4[var5 + var17] & '\uffff' & var15;

            for(var16 += var7; var16 >= 8; var1[var13++] = (byte)(var14 >>> var16)) {
               var16 -= 8;
            }
         }

         if (var16 > 0) {
            var1[var13++] = (byte)(var14 << 8 - var16);
         }

         return var10;
      }
   }

   int trim_i8_decode(byte[] var1, int var2, int var3, int var4, byte[] var5, int var6, int var7) {
      int var8 = 1 << var3;
      int var9 = var8 * var4 + 7 >> 3;
      if (var9 > var7) {
         return 0;
      } else {
         int var10 = var6;
         int var11 = 0;
         int var12 = 0;
         int var15 = 0;
         int var13 = (1 << var4) - 1;
         int var14 = 1 << var4 - 1;

         while(var11 < var8) {
            var12 = var12 << 8 | var5[var10++] & 255;

            for(var15 += 8; var15 >= var4 && var11 < var8; ++var11) {
               var15 -= var4;
               int var16 = var12 >>> var15 & var13;
               var16 |= -(var16 & var14);
               if (var16 == -var14) {
                  return 0;
               }

               var1[var2 + var11] = (byte)var16;
            }
         }

         if ((var12 & (1 << var15) - 1) != 0) {
            return 0;
         } else {
            return var9;
         }
      }
   }

   int comp_encode(byte[] var1, int var2, int var3, short[] var4, int var5, int var6) {
      int var8 = 1 << var6;
      int var7 = var2;

      for(int var9 = 0; var9 < var8; ++var9) {
         if (var4[var5 + var9] < -2047 || var4[var5 + var9] > 2047) {
            return 0;
         }
      }

      int var11 = 0;
      int var12 = 0;
      int var10 = 0;

      for(int var15 = 0; var15 < var8; ++var15) {
         var11 <<= 1;
         int var13 = var4[var5 + var15];
         if (var13 < 0) {
            var13 = -var13;
            var11 |= 1;
         }

         var11 <<= 7;
         var11 |= var13 & 127;
         int var14 = var13 >>> 7;
         var12 += 8;
         var11 <<= var14 + 1;
         var11 |= 1;

         for(var12 += var14 + 1; var12 >= 8; ++var10) {
            var12 -= 8;
            if (var1 != null) {
               if (var10 >= var3) {
                  return 0;
               }

               var1[var7 + var10] = (byte)(var11 >>> var12);
            }
         }
      }

      if (var12 > 0) {
         if (var1 != null) {
            if (var10 >= var3) {
               return 0;
            }

            var1[var7 + var10] = (byte)(var11 << 8 - var12);
         }

         ++var10;
      }

      return var10;
   }

   int comp_decode(short[] var1, int var2, int var3, byte[] var4, int var5, int var6) {
      int var8 = 1 << var3;
      int var7 = var5;
      int var11 = 0;
      int var12 = 0;
      int var10 = 0;

      label49:
      for(int var9 = 0; var9 < var8; ++var9) {
         if (var10 >= var6) {
            return 0;
         }

         var11 = var11 << 8 | var4[var7 + var10] & 255;
         ++var10;
         int var13 = var11 >>> var12;
         int var14 = var13 & 128;
         int var15 = var13 & 127;

         do {
            if (var12 == 0) {
               if (var10 >= var6) {
                  return 0;
               }

               var11 = var11 << 8 | var4[var7 + var10] & 255;
               ++var10;
               var12 = 8;
            }

            --var12;
            if ((var11 >>> var12 & 1) != 0) {
               if (var14 != 0 && var15 == 0) {
                  return 0;
               }

               var1[var2 + var9] = (short)(var14 != 0 ? -var15 : var15);
               continue label49;
            }

            var15 += 128;
         } while(var15 <= 2047);

         return 0;
      }

      if ((var11 & (1 << var12) - 1) != 0) {
         return 0;
      } else {
         return var10;
      }
   }
}

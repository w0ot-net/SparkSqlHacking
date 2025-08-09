package org.bouncycastle.pqc.crypto.mlkem;

final class CBD {
   public static void mlkemCBD(Poly var0, byte[] var1, int var2) {
      switch (var2) {
         case 3:
            for(int var9 = 0; var9 < 64; ++var9) {
               long var3 = convertByteTo24BitUnsignedInt(var1, 3 * var9);
               long var5 = var3 & 2396745L;
               var5 += var3 >> 1 & 2396745L;
               var5 += var3 >> 2 & 2396745L;

               for(int var10 = 0; var10 < 4; ++var10) {
                  short var7 = (short)((int)(var5 >> 6 * var10 + 0 & 7L));
                  short var8 = (short)((int)(var5 >> 6 * var10 + 3 & 7L));
                  var0.setCoeffIndex(4 * var9 + var10, (short)(var7 - var8));
               }
            }
            break;
         default:
            for(int var18 = 0; var18 < 32; ++var18) {
               long var11 = convertByteTo32BitUnsignedInt(var1, 4 * var18);
               long var14 = var11 & 1431655765L;
               var14 += var11 >> 1 & 1431655765L;

               for(int var19 = 0; var19 < 8; ++var19) {
                  short var16 = (short)((int)(var14 >> 4 * var19 + 0 & 3L));
                  short var17 = (short)((int)(var14 >> 4 * var19 + var2 & 3L));
                  var0.setCoeffIndex(8 * var18 + var19, (short)(var16 - var17));
               }
            }
      }

   }

   private static long convertByteTo32BitUnsignedInt(byte[] var0, int var1) {
      long var2 = (long)(var0[var1] & 255);
      var2 |= (long)(var0[var1 + 1] & 255) << 8;
      var2 |= (long)(var0[var1 + 2] & 255) << 16;
      var2 |= (long)(var0[var1 + 3] & 255) << 24;
      return var2;
   }

   private static long convertByteTo24BitUnsignedInt(byte[] var0, int var1) {
      long var2 = (long)(var0[var1] & 255);
      var2 |= (long)(var0[var1 + 1] & 255) << 8;
      var2 |= (long)(var0[var1 + 2] & 255) << 16;
      return var2;
   }
}

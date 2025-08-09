package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Pack;

class Utils {
   static void resizeArray(long[] var0, int var1, long[] var2, int var3, int var4, int var5) {
      long var6 = Long.MAX_VALUE;
      int var8 = 0;
      if (var1 < var3) {
         if (var1 % 64 != 0) {
            var8 = 64 - var1 % 64;
         }

         System.arraycopy(var2, 0, var0, 0, var4);

         for(int var9 = 0; var9 < var8; ++var9) {
            var0[var5 - 1] &= var6 >> var9;
         }
      } else {
         System.arraycopy(var2, 0, var0, 0, (var3 + 7) / 8);
      }

   }

   static void fromByte16ArrayToLongArray(long[] var0, int[] var1) {
      for(int var2 = 0; var2 != var1.length; var2 += 4) {
         var0[var2 / 4] = (long)var1[var2] & 65535L;
         var0[var2 / 4] |= (long)var1[var2 + 1] << 16;
         var0[var2 / 4] |= (long)var1[var2 + 2] << 32;
         var0[var2 / 4] |= (long)var1[var2 + 3] << 48;
      }

   }

   static void fromByteArrayToByte16Array(int[] var0, byte[] var1) {
      byte[] var2 = var1;
      if (var1.length % 2 != 0) {
         var2 = new byte[(var1.length + 1) / 2 * 2];
         System.arraycopy(var1, 0, var2, 0, var1.length);
      }

      int var3 = 0;

      for(int var4 = 0; var4 < var0.length; ++var4) {
         var0[var4] = Pack.littleEndianToShort(var2, var3) & '\uffff';
         var3 += 2;
      }

   }

   static void fromLongArrayToByteArray(byte[] var0, long[] var1) {
      int var2 = var0.length / 8;

      for(int var3 = 0; var3 != var2; ++var3) {
         Pack.longToLittleEndian(var1[var3], var0, var3 * 8);
      }

      if (var0.length % 8 != 0) {
         int var5 = var2 * 8;

         for(int var4 = 0; var5 < var0.length; var0[var5++] = (byte)((int)(var1[var2] >>> var4++ * 8))) {
         }
      }

   }

   static long bitMask(long var0, long var2) {
      return (1L << (int)(var0 % var2)) - 1L;
   }

   static void fromByteArrayToLongArray(long[] var0, byte[] var1) {
      byte[] var2 = var1;
      if (var1.length % 8 != 0) {
         var2 = new byte[(var1.length + 7) / 8 * 8];
         System.arraycopy(var1, 0, var2, 0, var1.length);
      }

      int var3 = 0;

      for(int var4 = 0; var4 < var0.length; ++var4) {
         var0[var4] = Pack.littleEndianToLong(var2, var3);
         var3 += 8;
      }

   }

   static void fromByte32ArrayToLongArray(long[] var0, int[] var1) {
      for(int var2 = 0; var2 != var1.length; var2 += 2) {
         var0[var2 / 2] = (long)var1[var2] & 4294967295L;
         var0[var2 / 2] |= (long)var1[var2 + 1] << 32;
      }

   }

   static void fromLongArrayToByte32Array(int[] var0, long[] var1) {
      for(int var2 = 0; var2 != var1.length; ++var2) {
         var0[2 * var2] = (int)var1[var2];
         var0[2 * var2 + 1] = (int)(var1[var2] >> 32);
      }

   }

   static void copyBytes(int[] var0, int var1, int[] var2, int var3, int var4) {
      System.arraycopy(var0, var1, var2, var3, var4 / 2);
   }

   static int getByteSizeFromBitSize(int var0) {
      return (var0 + 7) / 8;
   }

   static int getByte64SizeFromBitSize(int var0) {
      return (var0 + 63) / 64;
   }

   static int toUnsigned8bits(int var0) {
      return var0 & 255;
   }

   static int toUnsigned16Bits(int var0) {
      return var0 & '\uffff';
   }

   static void xorLongToByte16Array(int[] var0, long var1, int var3) {
      var0[var3 + 0] ^= (int)var1 & '\uffff';
      var0[var3 + 1] ^= (int)(var1 >>> 16) & '\uffff';
      var0[var3 + 2] ^= (int)(var1 >>> 32) & '\uffff';
      var0[var3 + 3] ^= (int)(var1 >>> 48) & '\uffff';
   }
}

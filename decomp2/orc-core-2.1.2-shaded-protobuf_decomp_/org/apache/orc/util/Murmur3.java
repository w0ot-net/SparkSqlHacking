package org.apache.orc.util;

public class Murmur3 {
   public static final long NULL_HASHCODE = 2862933555777941757L;
   private static final int C1_32 = -862048943;
   private static final int C2_32 = 461845907;
   private static final int R1_32 = 15;
   private static final int R2_32 = 13;
   private static final int M_32 = 5;
   private static final int N_32 = -430675100;
   private static final long C1 = -8663945395140668459L;
   private static final long C2 = 5545529020109919103L;
   private static final int R1 = 31;
   private static final int R2 = 27;
   private static final int R3 = 33;
   private static final int M = 5;
   private static final int N1 = 1390208809;
   private static final int N2 = 944331445;
   private static final int DEFAULT_SEED = 104729;

   public static int hash32(byte[] data) {
      return hash32(data, data.length, 104729);
   }

   public static int hash32(byte[] data, int length, int seed) {
      int hash = seed;
      int nblocks = length >> 2;

      for(int i = 0; i < nblocks; ++i) {
         int i_4 = i << 2;
         int k = data[i_4] & 255 | (data[i_4 + 1] & 255) << 8 | (data[i_4 + 2] & 255) << 16 | (data[i_4 + 3] & 255) << 24;
         k *= -862048943;
         k = Integer.rotateLeft(k, 15);
         k *= 461845907;
         hash ^= k;
         hash = Integer.rotateLeft(hash, 13) * 5 + -430675100;
      }

      int idx = nblocks << 2;
      int k1 = 0;
      switch (length - idx) {
         case 3:
            k1 ^= data[idx + 2] << 16;
         case 2:
            k1 ^= data[idx + 1] << 8;
         case 1:
            k1 ^= data[idx];
            k1 *= -862048943;
            k1 = Integer.rotateLeft(k1, 15);
            k1 *= 461845907;
            hash ^= k1;
         default:
            hash ^= length;
            hash ^= hash >>> 16;
            hash *= -2048144789;
            hash ^= hash >>> 13;
            hash *= -1028477387;
            hash ^= hash >>> 16;
            return hash;
      }
   }

   public static long hash64(byte[] data) {
      return hash64(data, 0, data.length, 104729);
   }

   public static long hash64(byte[] data, int offset, int length) {
      return hash64(data, offset, length, 104729);
   }

   public static long hash64(byte[] data, int offset, int length, int seed) {
      long hash = (long)seed;
      int nblocks = length >> 3;

      for(int i = 0; i < nblocks; ++i) {
         int i8 = i << 3;
         long k = (long)data[offset + i8] & 255L | ((long)data[offset + i8 + 1] & 255L) << 8 | ((long)data[offset + i8 + 2] & 255L) << 16 | ((long)data[offset + i8 + 3] & 255L) << 24 | ((long)data[offset + i8 + 4] & 255L) << 32 | ((long)data[offset + i8 + 5] & 255L) << 40 | ((long)data[offset + i8 + 6] & 255L) << 48 | ((long)data[offset + i8 + 7] & 255L) << 56;
         k *= -8663945395140668459L;
         k = Long.rotateLeft(k, 31);
         k *= 5545529020109919103L;
         hash ^= k;
         hash = Long.rotateLeft(hash, 27) * 5L + 1390208809L;
      }

      long k1 = 0L;
      int tailStart = nblocks << 3;
      switch (length - tailStart) {
         case 7:
            k1 ^= ((long)data[offset + tailStart + 6] & 255L) << 48;
         case 6:
            k1 ^= ((long)data[offset + tailStart + 5] & 255L) << 40;
         case 5:
            k1 ^= ((long)data[offset + tailStart + 4] & 255L) << 32;
         case 4:
            k1 ^= ((long)data[offset + tailStart + 3] & 255L) << 24;
         case 3:
            k1 ^= ((long)data[offset + tailStart + 2] & 255L) << 16;
         case 2:
            k1 ^= ((long)data[offset + tailStart + 1] & 255L) << 8;
         case 1:
            k1 ^= (long)data[offset + tailStart] & 255L;
            k1 *= -8663945395140668459L;
            k1 = Long.rotateLeft(k1, 31);
            k1 *= 5545529020109919103L;
            hash ^= k1;
         default:
            hash ^= (long)length;
            hash = fmix64(hash);
            return hash;
      }
   }

   public static long[] hash128(byte[] data) {
      return hash128(data, 0, data.length, 104729);
   }

   public static long[] hash128(byte[] data, int offset, int length, int seed) {
      long h1 = (long)seed;
      long h2 = (long)seed;
      int nblocks = length >> 4;

      for(int i = 0; i < nblocks; ++i) {
         int i16 = i << 4;
         long k1 = (long)data[offset + i16] & 255L | ((long)data[offset + i16 + 1] & 255L) << 8 | ((long)data[offset + i16 + 2] & 255L) << 16 | ((long)data[offset + i16 + 3] & 255L) << 24 | ((long)data[offset + i16 + 4] & 255L) << 32 | ((long)data[offset + i16 + 5] & 255L) << 40 | ((long)data[offset + i16 + 6] & 255L) << 48 | ((long)data[offset + i16 + 7] & 255L) << 56;
         long k2 = (long)data[offset + i16 + 8] & 255L | ((long)data[offset + i16 + 9] & 255L) << 8 | ((long)data[offset + i16 + 10] & 255L) << 16 | ((long)data[offset + i16 + 11] & 255L) << 24 | ((long)data[offset + i16 + 12] & 255L) << 32 | ((long)data[offset + i16 + 13] & 255L) << 40 | ((long)data[offset + i16 + 14] & 255L) << 48 | ((long)data[offset + i16 + 15] & 255L) << 56;
         k1 *= -8663945395140668459L;
         k1 = Long.rotateLeft(k1, 31);
         k1 *= 5545529020109919103L;
         long var15 = h1 ^ k1;
         long var16 = Long.rotateLeft(var15, 27);
         long var17 = var16 + h2;
         h1 = var17 * 5L + 1390208809L;
         k2 *= 5545529020109919103L;
         k2 = Long.rotateLeft(k2, 33);
         k2 *= -8663945395140668459L;
         h2 ^= k2;
         h2 = Long.rotateLeft(h2, 31);
         h2 += h1;
         h2 = h2 * 5L + 944331445L;
      }

      long k1 = 0L;
      long k2 = 0L;
      int tailStart = nblocks << 4;
      switch (length - tailStart) {
         case 15:
            k2 ^= (long)(data[offset + tailStart + 14] & 255) << 48;
         case 14:
            k2 ^= (long)(data[offset + tailStart + 13] & 255) << 40;
         case 13:
            k2 ^= (long)(data[offset + tailStart + 12] & 255) << 32;
         case 12:
            k2 ^= (long)(data[offset + tailStart + 11] & 255) << 24;
         case 11:
            k2 ^= (long)(data[offset + tailStart + 10] & 255) << 16;
         case 10:
            k2 ^= (long)(data[offset + tailStart + 9] & 255) << 8;
         case 9:
            k2 ^= (long)(data[offset + tailStart + 8] & 255);
            k2 *= 5545529020109919103L;
            k2 = Long.rotateLeft(k2, 33);
            k2 *= -8663945395140668459L;
            h2 ^= k2;
         case 8:
            k1 ^= (long)(data[offset + tailStart + 7] & 255) << 56;
         case 7:
            k1 ^= (long)(data[offset + tailStart + 6] & 255) << 48;
         case 6:
            k1 ^= (long)(data[offset + tailStart + 5] & 255) << 40;
         case 5:
            k1 ^= (long)(data[offset + tailStart + 4] & 255) << 32;
         case 4:
            k1 ^= (long)(data[offset + tailStart + 3] & 255) << 24;
         case 3:
            k1 ^= (long)(data[offset + tailStart + 2] & 255) << 16;
         case 2:
            k1 ^= (long)(data[offset + tailStart + 1] & 255) << 8;
         case 1:
            k1 ^= (long)(data[offset + tailStart] & 255);
            k1 *= -8663945395140668459L;
            k1 = Long.rotateLeft(k1, 31);
            k1 *= 5545529020109919103L;
            h1 ^= k1;
         default:
            h1 ^= (long)length;
            h2 ^= (long)length;
            h1 += h2;
            h2 += h1;
            h1 = fmix64(h1);
            h2 = fmix64(h2);
            h1 += h2;
            h2 += h1;
            return new long[]{h1, h2};
      }
   }

   private static long fmix64(long h) {
      h ^= h >>> 33;
      h *= -49064778989728563L;
      h ^= h >>> 33;
      h *= -4265267296055464877L;
      h ^= h >>> 33;
      return h;
   }
}

package org.apache.spark.util.sketch;

import java.nio.ByteOrder;

final class Murmur3_x86_32 {
   private static final boolean isBigEndian;
   private static final int C1 = -862048943;
   private static final int C2 = 461845907;
   private final int seed;

   Murmur3_x86_32(int seed) {
      this.seed = seed;
   }

   public String toString() {
      return "Murmur3_32(seed=" + this.seed + ")";
   }

   public int hashInt(int input) {
      return hashInt(input, this.seed);
   }

   public static int hashInt(int input, int seed) {
      int k1 = mixK1(input);
      int h1 = mixH1(seed, k1);
      return fmix(h1, 4);
   }

   public int hashUnsafeWords(Object base, long offset, int lengthInBytes) {
      return hashUnsafeWords(base, offset, lengthInBytes, this.seed);
   }

   public static int hashUnsafeWords(Object base, long offset, int lengthInBytes, int seed) {
      assert lengthInBytes % 8 == 0 : "lengthInBytes must be a multiple of 8 (word-aligned)";

      int h1 = hashBytesByInt(base, offset, lengthInBytes, seed);
      return fmix(h1, lengthInBytes);
   }

   public static int hashUnsafeBytes(Object base, long offset, int lengthInBytes, int seed) {
      assert lengthInBytes >= 0 : "lengthInBytes cannot be negative";

      int lengthAligned = lengthInBytes - lengthInBytes % 4;
      int h1 = hashBytesByInt(base, offset, lengthAligned, seed);

      for(int i = lengthAligned; i < lengthInBytes; ++i) {
         int halfWord = Platform.getByte(base, offset + (long)i);
         int k1 = mixK1(halfWord);
         h1 = mixH1(h1, k1);
      }

      return fmix(h1, lengthInBytes);
   }

   public static int hashUnsafeBytes2(Object base, long offset, int lengthInBytes, int seed) {
      assert lengthInBytes >= 0 : "lengthInBytes cannot be negative";

      int lengthAligned = lengthInBytes - lengthInBytes % 4;
      int h1 = hashBytesByInt(base, offset, lengthAligned, seed);
      int k1 = 0;
      int i = lengthAligned;

      for(int shift = 0; i < lengthInBytes; shift += 8) {
         k1 ^= (Platform.getByte(base, offset + (long)i) & 255) << shift;
         ++i;
      }

      h1 ^= mixK1(k1);
      return fmix(h1, lengthInBytes);
   }

   private static int hashBytesByInt(Object base, long offset, int lengthInBytes, int seed) {
      assert lengthInBytes % 4 == 0;

      int h1 = seed;

      for(int i = 0; i < lengthInBytes; i += 4) {
         int halfWord = Platform.getInt(base, offset + (long)i);
         if (isBigEndian) {
            halfWord = Integer.reverseBytes(halfWord);
         }

         h1 = mixH1(h1, mixK1(halfWord));
      }

      return h1;
   }

   public int hashLong(long input) {
      return hashLong(input, this.seed);
   }

   public static int hashLong(long input, int seed) {
      int low = (int)input;
      int high = (int)(input >>> 32);
      int k1 = mixK1(low);
      int h1 = mixH1(seed, k1);
      k1 = mixK1(high);
      h1 = mixH1(h1, k1);
      return fmix(h1, 8);
   }

   private static int mixK1(int k1) {
      k1 *= -862048943;
      k1 = Integer.rotateLeft(k1, 15);
      k1 *= 461845907;
      return k1;
   }

   private static int mixH1(int h1, int k1) {
      h1 ^= k1;
      h1 = Integer.rotateLeft(h1, 13);
      h1 = h1 * 5 + -430675100;
      return h1;
   }

   private static int fmix(int h1, int length) {
      h1 ^= length;
      h1 ^= h1 >>> 16;
      h1 *= -2048144789;
      h1 ^= h1 >>> 13;
      h1 *= -1028477387;
      h1 ^= h1 >>> 16;
      return h1;
   }

   static {
      isBigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
   }
}

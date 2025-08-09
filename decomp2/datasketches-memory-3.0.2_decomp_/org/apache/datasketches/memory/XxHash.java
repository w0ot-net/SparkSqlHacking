package org.apache.datasketches.memory;

import org.apache.datasketches.memory.internal.XxHash64;

public final class XxHash {
   private XxHash() {
   }

   public static long hashByteArr(byte[] arr, long offsetBytes, long lengthBytes, long seed) {
      return XxHash64.hashBytes(arr, offsetBytes, lengthBytes, seed);
   }

   public static long hashShortArr(short[] arr, long offsetShorts, long lengthShorts, long seed) {
      return XxHash64.hashShorts(arr, offsetShorts, lengthShorts, seed);
   }

   public static long hashCharArr(char[] arr, long offsetChars, long lengthChars, long seed) {
      return XxHash64.hashChars(arr, offsetChars, lengthChars, seed);
   }

   public static long hashIntArr(int[] arr, long offsetInts, long lengthInts, long seed) {
      return XxHash64.hashInts(arr, offsetInts, lengthInts, seed);
   }

   public static long hashLongArr(long[] arr, long offsetLongs, long lengthLongs, long seed) {
      return XxHash64.hashLongs(arr, offsetLongs, lengthLongs, seed);
   }

   public static long hashLong(long in, long seed) {
      return XxHash64.hash(in, seed);
   }

   public static long hashFloatArr(float[] arr, long offsetFloats, long lengthFloats, long seed) {
      return XxHash64.hashFloats(arr, offsetFloats, lengthFloats, seed);
   }

   public static long hashDoubleArr(double[] arr, long offsetDoubles, long lengthDoubles, long seed) {
      return XxHash64.hashDoubles(arr, offsetDoubles, lengthDoubles, seed);
   }

   public static long hashString(String str, long offsetChars, long lengthChars, long seed) {
      return XxHash64.hashString(str, offsetChars, lengthChars, seed);
   }
}

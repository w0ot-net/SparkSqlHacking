package org.apache.arrow.vector.util;

public final class DataSizeRoundingUtil {
   public static int ROUND_8_MASK_INT = -8;
   public static long ROUND_8_MASK_LONG = -8L;
   public static int DIVIDE_BY_8_SHIFT_BITS = 3;

   public static int roundUpTo8Multiple(int input) {
      return input + 7 & ROUND_8_MASK_INT;
   }

   public static long roundUpTo8Multiple(long input) {
      return input + 7L & ROUND_8_MASK_LONG;
   }

   public static int roundDownTo8Multiple(int input) {
      return input & ROUND_8_MASK_INT;
   }

   public static long roundDownTo8Multiple(long input) {
      return input & ROUND_8_MASK_LONG;
   }

   public static int divideBy8Ceil(int input) {
      return input + 7 >>> DIVIDE_BY_8_SHIFT_BITS;
   }

   public static long divideBy8Ceil(long input) {
      return input + 7L >>> (int)((long)DIVIDE_BY_8_SHIFT_BITS);
   }

   public static long roundUpToMultipleOf16(long num) {
      return num + 15L & -16L;
   }

   private DataSizeRoundingUtil() {
   }
}

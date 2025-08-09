package org.apache.datasketches.common;

import java.util.Arrays;
import java.util.Comparator;

public final class Util {
   public static final String LS = System.getProperty("line.separator");
   public static final char TAB = '\t';
   public static final double LOG2 = Math.log((double)2.0F);
   public static final long INVERSE_GOLDEN_U64 = -7046029254386353133L;
   public static final double INVERSE_GOLDEN = 0.6180339887498949;
   public static final double LONG_MAX_VALUE_AS_DOUBLE = (double)Long.MAX_VALUE;

   private Util() {
   }

   public static int bytesToInt(byte[] arr) {
      return arr[3] << 24 | (arr[2] & 255) << 16 | (arr[1] & 255) << 8 | arr[0] & 255;
   }

   public static long bytesToLong(byte[] arr) {
      return (long)arr[7] << 56 | ((long)arr[6] & 255L) << 48 | ((long)arr[5] & 255L) << 40 | ((long)arr[4] & 255L) << 32 | ((long)arr[3] & 255L) << 24 | ((long)arr[2] & 255L) << 16 | ((long)arr[1] & 255L) << 8 | (long)arr[0] & 255L;
   }

   public static byte[] intToBytes(int v, byte[] arr) {
      arr[3] = (byte)(v >>> 24);
      arr[2] = (byte)(v >>> 16);
      arr[1] = (byte)(v >>> 8);
      arr[0] = (byte)v;
      return arr;
   }

   public static byte[] longToBytes(long v, byte[] arr) {
      arr[7] = (byte)((int)(v >>> 56));
      arr[6] = (byte)((int)(v >>> 48));
      arr[5] = (byte)((int)(v >>> 40));
      arr[4] = (byte)((int)(v >>> 32));
      arr[3] = (byte)((int)(v >>> 24));
      arr[2] = (byte)((int)(v >>> 16));
      arr[1] = (byte)((int)(v >>> 8));
      arr[0] = (byte)((int)v);
      return arr;
   }

   static long[] convertToLongArray(byte[] byteArr, boolean littleEndian) {
      int len = byteArr.length;
      long[] longArr = new long[len / 8 + (len % 8 != 0 ? 1 : 0)];
      int off = 0;

      long tgt;
      for(int longArrIdx = 0; off < len; longArr[longArrIdx++] = tgt) {
         int rem = Math.min(len - 1 - off, 7);
         tgt = 0L;
         if (littleEndian) {
            int j = off + rem;

            for(int k = 0; j >= off; ++k) {
               tgt |= ((long)byteArr[j] & 255L) << k * 8;
               --j;
            }
         } else {
            int j = off + rem;

            for(int k = rem; j >= off; --k) {
               tgt |= ((long)byteArr[j] & 255L) << k * 8;
               --j;
            }
         }

         off += 8;
      }

      return longArr;
   }

   public static String longToHexBytes(long v) {
      long mask = 255L;
      StringBuilder sb = new StringBuilder();
      int i = 8;

      while(i-- > 0) {
         String s = Long.toHexString(v >>> i * 8 & 255L);
         sb.append(zeroPad(s, 2)).append(" ");
      }

      return sb.toString();
   }

   public static String bytesToString(byte[] arr, boolean signed, boolean littleEndian, String sep) {
      StringBuilder sb = new StringBuilder();
      int mask = signed ? -1 : 255;
      int arrLen = arr.length;
      if (littleEndian) {
         for(int i = 0; i < arrLen - 1; ++i) {
            sb.append(arr[i] & mask).append(sep);
         }

         sb.append(arr[arrLen - 1] & mask);
      } else {
         int i = arrLen;

         while(i-- > 1) {
            sb.append(arr[i] & mask).append(sep);
         }

         sb.append(arr[0] & mask);
      }

      return sb.toString();
   }

   public static String nanoSecToString(long nS) {
      long rem_nS = (long)((double)nS % (double)1000.0F);
      long rem_uS = (long)((double)nS / (double)1000.0F % (double)1000.0F);
      long rem_mS = (long)((double)nS / (double)1000000.0F % (double)1000.0F);
      long sec = (long)((double)nS / (double)1.0E9F);
      String nSstr = zeroPad(Long.toString(rem_nS), 3);
      String uSstr = zeroPad(Long.toString(rem_uS), 3);
      String mSstr = zeroPad(Long.toString(rem_mS), 3);
      return String.format("%d.%3s_%3s_%3s", sec, mSstr, uSstr, nSstr);
   }

   public static String milliSecToString(long mS) {
      long rem_mS = (long)((double)mS % (double)1000.0F);
      long rem_sec = (long)((double)mS / (double)1000.0F % (double)60.0F);
      long rem_min = (long)((double)mS / (double)60000.0F % (double)60.0F);
      long hr = (long)((double)mS / (double)3600000.0F);
      String mSstr = zeroPad(Long.toString(rem_mS), 3);
      String secStr = zeroPad(Long.toString(rem_sec), 2);
      String minStr = zeroPad(Long.toString(rem_min), 2);
      return String.format("%d:%2s:%2s.%3s", hr, minStr, secStr, mSstr);
   }

   public static String zeroPad(String s, int fieldLength) {
      return characterPad(s, fieldLength, '0', false);
   }

   public static String characterPad(String s, int fieldLength, char padChar, boolean postpend) {
      int sLen = s.length();
      if (sLen < fieldLength) {
         char[] cArr = new char[fieldLength - sLen];
         Arrays.fill(cArr, padChar);
         String addstr = String.valueOf(cArr);
         return postpend ? s.concat(addstr) : addstr.concat(s);
      } else {
         return s;
      }
   }

   public static void checkIfMultipleOf8AndGT0(long v, String argName) {
      if ((v & 7L) != 0L || v <= 0L) {
         throw new SketchesArgumentException("The value of the parameter \"" + argName + "\" must be a positive multiple of 8 and greater than zero: " + v);
      }
   }

   public static boolean isMultipleOf8AndGT0(long v) {
      return (v & 7L) == 0L && v > 0L;
   }

   public static boolean isPowerOf2(long n) {
      return n > 0L && (n & n - 1L) == 0L;
   }

   public static void checkIfPowerOf2(long n, String argName) {
      if (!isPowerOf2(n)) {
         argName = argName == null ? "" : argName;
         throw new SketchesArgumentException("The value of the argument \"" + argName + "\" must be a positive integer power of 2: " + n);
      }
   }

   public static int ceilingPowerOf2(int n) {
      if (n <= 1) {
         return 1;
      } else {
         int topIntPwrOf2 = 1073741824;
         return n >= 1073741824 ? 1073741824 : Integer.highestOneBit(n - 1 << 1);
      }
   }

   public static long ceilingPowerOf2(long n) {
      if (n <= 1L) {
         return 1L;
      } else {
         long topIntPwrOf2 = 4611686018427387904L;
         return n >= 4611686018427387904L ? 4611686018427387904L : Long.highestOneBit(n - 1L << 1);
      }
   }

   public static int floorPowerOf2(int n) {
      return n <= 1 ? 1 : Integer.highestOneBit(n);
   }

   public static long floorPowerOf2(long n) {
      return n <= 1L ? 1L : Long.highestOneBit(n);
   }

   public static double invPow2(int e) {
      assert (e | 1024 - e - 1) >= 0 : "e cannot be negative or greater than 1023: " + e;

      return Double.longBitsToDouble(1023L - (long)e << 52);
   }

   public static long pwr2SeriesNext(int ppo, long curPoint) {
      long cur = curPoint < 1L ? 1L : curPoint;
      int gi = (int)Math.round(log2((double)cur) * (double)ppo);

      long next;
      do {
         ++gi;
         next = Math.round(Math.pow((double)2.0F, (double)gi / (double)ppo));
      } while(next <= curPoint);

      return next;
   }

   public static int pwr2SeriesPrev(int ppo, int curPoint) {
      if (curPoint <= 1) {
         return 0;
      } else {
         int gi = (int)Math.round(log2((double)curPoint) * (double)ppo);

         int prev;
         do {
            --gi;
            prev = (int)Math.round(Math.pow((double)2.0F, (double)gi / (double)ppo));
         } while(prev >= curPoint);

         return prev;
      }
   }

   public static double powerSeriesNextDouble(int ppb, double curPoint, boolean roundToLong, double logBase) {
      double cur = curPoint < (double)1.0F ? (double)1.0F : curPoint;
      double gi = (double)Math.round(logBaseOfX(logBase, cur) * (double)ppb);

      double next;
      do {
         double n = Math.pow(logBase, ++gi / (double)ppb);
         next = roundToLong ? (double)Math.round(n) : n;
      } while(next <= cur);

      return next;
   }

   public static double ceilingPowerBaseOfDouble(double base, double n) {
      double x = n < (double)1.0F ? (double)1.0F : n;
      return (double)Math.round(Math.pow(base, Math.ceil(logBaseOfX(base, x))));
   }

   public static double floorPowerBaseOfDouble(double base, double n) {
      double x = n < (double)1.0F ? (double)1.0F : n;
      return (double)Math.round(Math.pow(base, Math.floor(logBaseOfX(base, x))));
   }

   public static double log2(double value) {
      return Math.log(value) / LOG2;
   }

   public static double logBaseOfX(double base, double x) {
      return Math.log(x) / Math.log(base);
   }

   public static int numberOfTrailingOnes(long v) {
      return Long.numberOfTrailingZeros(~v);
   }

   public static int numberOfLeadingOnes(long v) {
      return Long.numberOfLeadingZeros(~v);
   }

   public static int exactLog2OfInt(int powerOf2, String argName) {
      checkIfPowerOf2((long)powerOf2, argName);
      return Integer.numberOfTrailingZeros(powerOf2);
   }

   public static int exactLog2OfLong(long powerOf2, String argName) {
      checkIfPowerOf2(powerOf2, argName);
      return Long.numberOfTrailingZeros(powerOf2);
   }

   public static int exactLog2OfInt(int powerOf2) {
      if (!isPowerOf2((long)powerOf2)) {
         throw new SketchesArgumentException("Argument 'powerOf2' must be a positive power of 2.");
      } else {
         return Long.numberOfTrailingZeros((long)powerOf2);
      }
   }

   public static int exactLog2OfLong(long powerOf2) {
      if (!isPowerOf2(powerOf2)) {
         throw new SketchesArgumentException("Argument 'powerOf2' must be a positive power of 2.");
      } else {
         return Long.numberOfTrailingZeros(powerOf2);
      }
   }

   public static void checkBounds(long reqOff, long reqLen, long allocSize) {
      if ((reqOff | reqLen | reqOff + reqLen | allocSize - (reqOff + reqLen)) < 0L) {
         throw new SketchesArgumentException("Bounds Violation: reqOffset: " + reqOff + ", reqLength: " + reqLen + ", (reqOff + reqLen): " + (reqOff + reqLen) + ", allocSize: " + allocSize);
      }
   }

   public static void checkProbability(double p, String argName) {
      if (!(p >= (double)0.0F) || !(p <= (double)1.0F)) {
         throw new SketchesArgumentException("The value of the parameter \"" + argName + "\" must be between 0.0 inclusive and 1.0 inclusive: " + p);
      }
   }

   public static boolean isLessThanUnsigned(long n1, long n2) {
      return n1 < n2 ^ n1 < 0L != n2 < 0L;
   }

   public static boolean isEven(long n) {
      return (n & 1L) == 0L;
   }

   public static boolean isOdd(long n) {
      return (n & 1L) == 1L;
   }

   public static final int bitAt(long number, int bitPos) {
      return (number & 1L << bitPos) > 0L ? 1 : 0;
   }

   public static int numDigits(long n) {
      if (n % 10L == 0L) {
         ++n;
      }

      return (int)Math.ceil(Math.log((double)n) / Math.log((double)10.0F));
   }

   public static String longToFixedLengthString(long number, int length) {
      String num = Long.toString(number);
      return characterPad(num, length, ' ', false);
   }

   public static Object minT(Object item1, Object item2, Comparator c) {
      return c.compare(item1, item2) <= 0 ? item1 : item2;
   }

   public static Object maxT(Object item1, Object item2, Comparator c) {
      return c.compare(item1, item2) >= 0 ? item1 : item2;
   }

   public static boolean lt(Object item1, Object item2, Comparator c) {
      return c.compare(item1, item2) < 0;
   }

   public static boolean le(Object item1, Object item2, Comparator c) {
      return c.compare(item1, item2) <= 0;
   }
}

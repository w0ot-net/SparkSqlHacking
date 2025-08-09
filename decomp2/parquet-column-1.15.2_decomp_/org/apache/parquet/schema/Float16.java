package org.apache.parquet.schema;

import org.apache.parquet.io.api.Binary;

public class Float16 {
   private static final short POSITIVE_INFINITY = 31744;
   private static final short NaN = 32256;
   private static final int SIGN_MASK = 32768;
   private static final int EXPONENT_SHIFT = 10;
   private static final int SHIFTED_EXPONENT_MASK = 31;
   private static final int SIGNIFICAND_MASK = 1023;
   private static final int EXPONENT_BIAS = 15;
   private static final int SIGN_SHIFT = 15;
   private static final int EXPONENT_SIGNIFICAND_MASK = 32767;
   private static final int FP32_SIGN_SHIFT = 31;
   private static final int FP32_EXPONENT_SHIFT = 23;
   private static final int FP32_SHIFTED_EXPONENT_MASK = 255;
   private static final int FP32_SIGNIFICAND_MASK = 8388607;
   private static final int FP32_EXPONENT_BIAS = 127;
   private static final int FP32_QNAN_MASK = 4194304;
   private static final int FP32_DENORMAL_MAGIC = 1056964608;
   private static final float FP32_DENORMAL_FLOAT = Float.intBitsToFloat(1056964608);

   public static boolean isNaN(short h) {
      return (h & 32767) > 31744;
   }

   public static int compare(short x, short y) {
      boolean xIsNaN = isNaN(x);
      boolean yIsNaN = isNaN(y);
      if (!xIsNaN && !yIsNaN) {
         int first = (x & '耀') != 0 ? '耀' - (x & '\uffff') : x & '\uffff';
         int second = (y & '耀') != 0 ? '耀' - (y & '\uffff') : y & '\uffff';
         if (first < second) {
            return -1;
         }

         if (first > second) {
            return 1;
         }
      }

      short xBits = xIsNaN ? 32256 : x;
      short yBits = yIsNaN ? 32256 : y;
      return xBits == yBits ? 0 : (xBits < yBits ? -1 : 1);
   }

   static float toFloat(Binary b) {
      short h = b.get2BytesLittleEndian();
      int bits = h & '\uffff';
      int s = bits & '耀';
      int e = bits >>> 10 & 31;
      int m = bits & 1023;
      int outE = 0;
      int outM = 0;
      if (e == 0) {
         if (m != 0) {
            float o = Float.intBitsToFloat(1056964608 + m);
            o -= FP32_DENORMAL_FLOAT;
            return s == 0 ? o : -o;
         }
      } else {
         outM = m << 13;
         if (e == 31) {
            outE = 255;
            if (outM != 0) {
               outM |= 4194304;
            }
         } else {
            outE = e - 15 + 127;
         }
      }

      int out = s << 16 | outE << 23 | outM;
      return Float.intBitsToFloat(out);
   }

   static short toFloat16(float f) {
      int bits = Float.floatToRawIntBits(f);
      int s = bits >>> 31;
      int e = bits >>> 23 & 255;
      int m = bits & 8388607;
      int outE = 0;
      int outM = 0;
      if (e == 255) {
         outE = 31;
         outM = m != 0 ? 512 : 0;
      } else {
         e = e - 127 + 15;
         if (e >= 31) {
            outE = 31;
         } else if (e <= 0) {
            if (e >= -10) {
               m |= 8388608;
               int shift = 14 - e;
               outM = m >> shift;
               int lowm = m & (1 << shift) - 1;
               int hway = 1 << shift - 1;
               if (lowm + (outM & 1) > hway) {
                  ++outM;
               }
            }
         } else {
            outE = e;
            outM = m >> 13;
            if ((m & 8191) + (outM & 1) > 4096) {
               ++outM;
            }
         }
      }

      return (short)(s << 15 | (outE << 10) + outM);
   }

   static String toFloatString(Binary h) {
      return Float.toString(toFloat(h));
   }
}

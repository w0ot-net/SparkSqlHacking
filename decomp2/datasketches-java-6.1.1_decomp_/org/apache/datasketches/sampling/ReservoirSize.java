package org.apache.datasketches.sampling;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;

final class ReservoirSize {
   static final int BINS_PER_OCTAVE = 2048;
   private static final double INV_BINS_PER_OCTAVE = (double)4.8828125E-4F;
   private static final double INV_LN_2 = (double)1.0F / Math.log((double)2.0F);
   private static final int EXPONENT_MASK = 31;
   private static final int EXPONENT_SHIFT = 11;
   private static final int INDEX_MASK = 2047;
   private static final int OUTPUT_MASK = 65535;
   private static final int MAX_ABS_VALUE = 2146959360;
   private static final int MAX_ENC_VALUE = 63487;

   private ReservoirSize() {
   }

   public static short computeSize(int k) {
      if (k >= 1 && k <= 2146959360) {
         int p = Util.exactLog2OfInt(Util.floorPowerOf2(k), "computeSize: p");
         if (Util.isPowerOf2((long)k)) {
            return (short)((p & 31) << 11 & '\uffff');
         } else {
            double m = Math.pow((double)2.0F, Math.log((double)k) * INV_LN_2 - (double)p);
            int i = (int)Math.floor(m * (double)2048.0F) - 2048 + 1;
            return i == 2048 ? (short)((p + 1 & 31) << 11 & '\uffff') : (short)((p & 31) << 11 | i & 2047 & '\uffff');
         }
      } else {
         throw new SketchesArgumentException("Can only encode strictly positive sketch sizes less than 2146959360, found: " + k);
      }
   }

   public static int decodeValue(short encodedSize) {
      int value = encodedSize & '\uffff';
      if (value > 63487) {
         throw new SketchesArgumentException("Maximum valid encoded value is " + Integer.toHexString(63487) + ", found: " + value);
      } else {
         int p = value >>> 11 & 31;
         int i = value & 2047;
         return (int)((double)(1 << p) * ((double)i * (double)4.8828125E-4F + (double)1.0F));
      }
   }
}

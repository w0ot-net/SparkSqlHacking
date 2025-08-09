package org.apache.datasketches.cpc;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;

final class CpcUtil {
   static final int minLgK = 4;
   static final int maxLgK = 26;

   static void checkLgK(int lgK) {
      if (lgK < 4 || lgK > 26) {
         throw new SketchesArgumentException("LgK must be >= 4 and <= 26: " + lgK);
      }
   }

   static Flavor determineFlavor(int lgK, long numCoupons) {
      long k = 1L << lgK;
      long c2 = numCoupons << 1;
      long c8 = numCoupons << 3;
      long c32 = numCoupons << 5;
      if (numCoupons == 0L) {
         return Flavor.EMPTY;
      } else if (c32 < 3L * k) {
         return Flavor.SPARSE;
      } else if (c2 < k) {
         return Flavor.HYBRID;
      } else {
         return c8 < 27L * k ? Flavor.PINNED : Flavor.SLIDING;
      }
   }

   static long[] bitMatrixOfSketch(CpcSketch sketch) {
      int k = 1 << sketch.lgK;
      int offset = sketch.windowOffset;

      assert offset >= 0 && offset <= 56;

      long[] matrix = new long[k];
      if (sketch.numCoupons == 0L) {
         return matrix;
      } else {
         long defaultRow = (1L << offset) - 1L;
         Arrays.fill(matrix, defaultRow);
         byte[] window = sketch.slidingWindow;
         if (window != null) {
            for(int i = 0; i < k; ++i) {
               matrix[i] |= ((long)window[i] & 255L) << offset;
            }
         }

         PairTable table = sketch.pairTable;

         assert table != null;

         int[] slots = table.getSlotsArr();
         int numSlots = 1 << table.getLgSizeInts();

         for(int i = 0; i < numSlots; ++i) {
            int rowCol = slots[i];
            if (rowCol != -1) {
               int col = rowCol & 63;
               int row = rowCol >>> 6;
               matrix[row] ^= 1L << col;
            }
         }

         return matrix;
      }
   }

   static long countBitsSetInMatrix(long[] matrix) {
      long count = 0L;
      int len = matrix.length;

      for(int i = 0; i < len; ++i) {
         count += (long)Long.bitCount(matrix[i]);
      }

      return count;
   }

   static int determineCorrectOffset(int lgK, long numCoupons) {
      long k = 1L << lgK;
      long tmp = (numCoupons << 3) - 19L * k;
      return tmp < 0L ? 0 : (int)(tmp >>> lgK + 3);
   }
}

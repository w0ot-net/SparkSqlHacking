package org.apache.datasketches.hll;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesReadOnlyException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;

final class HllUtil {
   static final int KEY_BITS_26 = 26;
   static final int VAL_BITS_6 = 6;
   static final int KEY_MASK_26 = 67108863;
   static final int VAL_MASK_6 = 63;
   static final int EMPTY = 0;
   static final int MIN_LOG_K = 4;
   static final int MAX_LOG_K = 21;
   static final double HLL_HIP_RSE_FACTOR = Math.sqrt(Math.log((double)2.0F));
   static final double HLL_NON_HIP_RSE_FACTOR = Math.sqrt((double)3.0F * Math.log((double)2.0F) - (double)1.0F);
   static final double COUPON_RSE_FACTOR = 0.409;
   static final double COUPON_RSE = 4.99267578125E-5;
   static final int LG_INIT_LIST_SIZE = 3;
   static final int LG_INIT_SET_SIZE = 5;
   static final int RESIZE_NUMER = 3;
   static final int RESIZE_DENOM = 4;
   static final int loNibbleMask = 15;
   static final int hiNibbleMask = 240;
   static final int AUX_TOKEN = 15;
   static final int[] LG_AUX_ARR_INTS = new int[]{0, 2, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};

   static final int checkLgK(int lgK) {
      if (lgK >= 4 && lgK <= 21) {
         return lgK;
      } else {
         throw new SketchesArgumentException("Log K must be between 4 and 21, inclusive: " + lgK);
      }
   }

   static void checkMemSize(long minBytes, long capBytes) {
      if (capBytes < minBytes) {
         throw new SketchesArgumentException("Given WritableMemory is not large enough: " + capBytes);
      }
   }

   static final void checkNumStdDev(int numStdDev) {
      if (numStdDev < 1 || numStdDev > 3) {
         throw new SketchesArgumentException("NumStdDev may not be less than 1 or greater than 3.");
      }
   }

   static CurMode checkPreamble(Memory mem) {
      Util.checkBounds(0L, 8L, mem.getCapacity());
      int preInts = PreambleUtil.extractPreInts(mem);
      Util.checkBounds(0L, (long)preInts * 4L, mem.getCapacity());
      int serVer = PreambleUtil.extractSerVer(mem);
      int famId = PreambleUtil.extractFamilyId(mem);
      CurMode curMode = PreambleUtil.extractCurMode(mem);
      if (famId == Family.HLL.getID() && serVer == 1 && (preInts == 2 || preInts == 3 || preInts == 10) && (curMode != CurMode.LIST || preInts == 2) && (curMode != CurMode.SET || preInts == 3) && (curMode != CurMode.HLL || preInts == 10)) {
         return curMode;
      } else {
         throw new SketchesArgumentException("Possible Corruption, Invalid Preamble:" + PreambleUtil.toString(mem));
      }
   }

   static final void noWriteAccess() {
      throw new SketchesReadOnlyException("This sketch is compact or does not have write access to the underlying resource.");
   }

   static String pairString(int pair) {
      return "SlotNo: " + getPairLow26(pair) + ", Value: " + getPairValue(pair);
   }

   static int pair(int slotNo, int value) {
      return value << 26 | slotNo & 67108863;
   }

   static final int getPairLow26(int coupon) {
      return coupon & 67108863;
   }

   static final int getPairValue(int coupon) {
      return coupon >>> 26;
   }
}

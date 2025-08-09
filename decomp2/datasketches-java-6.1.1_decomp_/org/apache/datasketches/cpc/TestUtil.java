package org.apache.datasketches.cpc;

public class TestUtil {
   static final double pwrLaw10NextDouble(int ppb, double curPoint) {
      double cur = curPoint < (double)1.0F ? (double)1.0F : curPoint;
      double gi = (double)Math.round(Math.log10(cur) * (double)ppb);

      double next;
      do {
         next = (double)Math.round(Math.pow((double)10.0F, ++gi / (double)ppb));
      } while(next <= curPoint);

      return next;
   }

   static boolean specialEquals(CpcSketch sk1, CpcSketch sk2, boolean sk1wasMerged, boolean sk2wasMerged) {
      RuntimeAsserts.rtAssertEquals(sk1.seed, sk2.seed);
      RuntimeAsserts.rtAssertEquals((long)sk1.lgK, (long)sk2.lgK);
      RuntimeAsserts.rtAssertEquals(sk1.numCoupons, sk2.numCoupons);
      RuntimeAsserts.rtAssertEquals((long)sk1.windowOffset, (long)sk2.windowOffset);
      RuntimeAsserts.rtAssertEquals(sk1.slidingWindow, sk2.slidingWindow);
      PairTable.equals(sk1.pairTable, sk2.pairTable);
      int ficolA = sk1.fiCol;
      int ficolB = sk2.fiCol;
      if (!sk1wasMerged && sk2wasMerged) {
         RuntimeAsserts.rtAssert(!sk1.mergeFlag && sk2.mergeFlag);
         int fiCol1 = calculateFirstInterestingColumn(sk1);
         RuntimeAsserts.rtAssertEquals((long)fiCol1, (long)sk2.fiCol);
      } else if (sk1wasMerged && !sk2wasMerged) {
         RuntimeAsserts.rtAssert(sk1.mergeFlag && !sk2.mergeFlag);
         int fiCol2 = calculateFirstInterestingColumn(sk2);
         RuntimeAsserts.rtAssertEquals((long)fiCol2, (long)sk1.fiCol);
      } else {
         RuntimeAsserts.rtAssertEquals(sk1.mergeFlag, sk2.mergeFlag);
         RuntimeAsserts.rtAssertEquals((long)ficolA, (long)ficolB);
         RuntimeAsserts.rtAssertEquals(sk1.kxp, sk2.kxp, 0.01 * sk1.kxp);
         RuntimeAsserts.rtAssertEquals(sk1.hipEstAccum, sk2.hipEstAccum, (double)1.0F * sk1.hipEstAccum);
      }

      return true;
   }

   static int calculateFirstInterestingColumn(CpcSketch sketch) {
      int offset = sketch.windowOffset;
      if (offset == 0) {
         return 0;
      } else {
         PairTable table = sketch.pairTable;

         assert table != null;

         int[] slots = table.getSlotsArr();
         int numSlots = 1 << table.getLgSizeInts();
         int result = offset;

         for(int i = 0; i < numSlots; ++i) {
            int rowCol = slots[i];
            if (rowCol != -1) {
               int col = rowCol & 63;
               if (col < result) {
                  result = col;
               }
            }
         }

         return result;
      }
   }
}

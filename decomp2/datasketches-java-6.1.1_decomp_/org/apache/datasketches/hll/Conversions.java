package org.apache.datasketches.hll;

class Conversions {
   static final Hll4Array convertToHll4(AbstractHllArray srcAbsHllArr) {
      int lgConfigK = srcAbsHllArr.getLgConfigK();
      Hll4Array hll4Array = new Hll4Array(lgConfigK);
      hll4Array.putOutOfOrder(srcAbsHllArr.isOutOfOrder());
      int pair = curMinAndNum(srcAbsHllArr);
      int curMin = HllUtil.getPairValue(pair);
      int numAtCurMin = HllUtil.getPairLow26(pair);
      PairIterator itr = srcAbsHllArr.iterator();
      AuxHashMap auxHashMap = hll4Array.getAuxHashMap();

      while(itr.nextValid()) {
         int slotNo = itr.getIndex();
         int actualValue = itr.getValue();
         AbstractHllArray.hipAndKxQIncrementalUpdate(hll4Array, 0, actualValue);
         if (actualValue >= curMin + 15) {
            hll4Array.putNibble(slotNo, 15);
            if (auxHashMap == null) {
               auxHashMap = new HeapAuxHashMap(HllUtil.LG_AUX_ARR_INTS[lgConfigK], lgConfigK);
               hll4Array.putAuxHashMap(auxHashMap, false);
            }

            auxHashMap.mustAdd(slotNo, actualValue);
         } else {
            hll4Array.putNibble(slotNo, actualValue - curMin);
         }
      }

      hll4Array.putCurMin(curMin);
      hll4Array.putNumAtCurMin(numAtCurMin);
      hll4Array.putHipAccum(srcAbsHllArr.getHipAccum());
      hll4Array.putRebuildCurMinNumKxQFlag(false);
      return hll4Array;
   }

   static final int curMinAndNum(AbstractHllArray absHllArr) {
      int curMin = 64;
      int numAtCurMin = 0;
      PairIterator itr = absHllArr.iterator();

      while(itr.nextAll()) {
         int v = itr.getValue();
         if (v <= curMin) {
            if (v < curMin) {
               curMin = v;
               numAtCurMin = 1;
            } else {
               ++numAtCurMin;
            }
         }
      }

      return HllUtil.pair(numAtCurMin, curMin);
   }

   static final Hll6Array convertToHll6(AbstractHllArray srcAbsHllArr) {
      int lgConfigK = srcAbsHllArr.lgConfigK;
      Hll6Array hll6Array = new Hll6Array(lgConfigK);
      hll6Array.putOutOfOrder(srcAbsHllArr.isOutOfOrder());
      int numZeros = 1 << lgConfigK;
      PairIterator itr = srcAbsHllArr.iterator();

      while(itr.nextAll()) {
         if (itr.getValue() != 0) {
            --numZeros;
            hll6Array.couponUpdate(itr.getPair());
         }
      }

      hll6Array.putNumAtCurMin(numZeros);
      hll6Array.putHipAccum(srcAbsHllArr.getHipAccum());
      hll6Array.putRebuildCurMinNumKxQFlag(false);
      return hll6Array;
   }

   static final Hll8Array convertToHll8(AbstractHllArray srcAbsHllArr) {
      int lgConfigK = srcAbsHllArr.lgConfigK;
      Hll8Array hll8Array = new Hll8Array(lgConfigK);
      hll8Array.putOutOfOrder(srcAbsHllArr.isOutOfOrder());
      int numZeros = 1 << lgConfigK;
      PairIterator itr = srcAbsHllArr.iterator();

      while(itr.nextAll()) {
         if (itr.getValue() != 0) {
            --numZeros;
            hll8Array.couponUpdate(itr.getPair());
         }
      }

      hll8Array.putNumAtCurMin(numZeros);
      hll8Array.putHipAccum(srcAbsHllArr.getHipAccum());
      hll8Array.putRebuildCurMinNumKxQFlag(false);
      return hll8Array;
   }
}

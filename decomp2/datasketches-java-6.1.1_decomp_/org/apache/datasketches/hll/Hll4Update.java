package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;

class Hll4Update {
   static final void internalHll4Update(AbstractHllArray host, int slotNo, int newValue) {
      assert 0 <= slotNo && slotNo < 1 << host.getLgConfigK();

      int curMin = host.getCurMin();
      int rawStoredOldNibble = host.getNibble(slotNo);
      int lbOnOldValue = rawStoredOldNibble + curMin;
      if (newValue > lbOnOldValue) {
         int actualOldValue;
         if (rawStoredOldNibble == 15) {
            AuxHashMap auxHashMap = host.getAuxHashMap();

            assert auxHashMap != null;

            actualOldValue = auxHashMap.mustFindValueFor(slotNo);
            if (newValue <= actualOldValue) {
               return;
            }

            AbstractHllArray.hipAndKxQIncrementalUpdate(host, actualOldValue, newValue);
            int shiftedNewValue = newValue - curMin;

            assert shiftedNewValue >= 0;

            if (shiftedNewValue >= 15) {
               auxHashMap.mustReplace(slotNo, newValue);
            }
         } else {
            actualOldValue = lbOnOldValue;
            AbstractHllArray.hipAndKxQIncrementalUpdate(host, lbOnOldValue, newValue);
            int shiftedNewValue = newValue - curMin;

            assert shiftedNewValue >= 0;

            if (shiftedNewValue >= 15) {
               host.putNibble(slotNo, 15);
               AuxHashMap auxHashMap = host.getAuxHashMap();
               if (auxHashMap == null) {
                  auxHashMap = host.getNewAuxHashMap();
                  host.putAuxHashMap(auxHashMap, false);
               }

               auxHashMap.mustAdd(slotNo, newValue);
            } else {
               host.putNibble(slotNo, shiftedNewValue);
            }
         }

         if (actualOldValue == curMin) {
            assert host.getNumAtCurMin() >= 1;

            host.decNumAtCurMin();

            while(host.getNumAtCurMin() == 0) {
               shiftToBiggerCurMin(host);
            }
         }

      }
   }

   private static final void shiftToBiggerCurMin(AbstractHllArray host) {
      int oldCurMin = host.getCurMin();
      int newCurMin = oldCurMin + 1;
      int lgConfigK = host.getLgConfigK();
      int configK = 1 << lgConfigK;
      int configKmask = configK - 1;
      int numAtNewCurMin = 0;
      int numAuxTokens = 0;

      for(int i = 0; i < configK; ++i) {
         int oldStoredNibble = host.getNibble(i);
         if (oldStoredNibble == 0) {
            throw new SketchesStateException("Array slots cannot be 0 at this point.");
         }

         if (oldStoredNibble < 15) {
            --oldStoredNibble;
            host.putNibble(i, oldStoredNibble);
            if (oldStoredNibble == 0) {
               ++numAtNewCurMin;
            }
         } else {
            ++numAuxTokens;

            assert host.getAuxHashMap() != null : "AuxHashMap cannot be null at this point.";
         }
      }

      AuxHashMap newAuxMap = null;
      AuxHashMap oldAuxMap = host.getAuxHashMap();
      if (oldAuxMap != null) {
         PairIterator itr = oldAuxMap.getIterator();

         while(itr.nextValid()) {
            int slotNum = itr.getKey() & configKmask;
            int oldActualVal = itr.getValue();
            int newShiftedVal = oldActualVal - newCurMin;

            assert newShiftedVal >= 0;

            assert host.getNibble(slotNum) == 15 : "Array slot != AUX_TOKEN: " + host.getNibble(slotNum);

            if (newShiftedVal < 15) {
               assert newShiftedVal == 14;

               host.putNibble(slotNum, newShiftedVal);
               --numAuxTokens;
            } else {
               if (newAuxMap == null) {
                  newAuxMap = new HeapAuxHashMap(HllUtil.LG_AUX_ARR_INTS[lgConfigK], lgConfigK);
               }

               newAuxMap.mustAdd(slotNum, oldActualVal);
            }
         }
      } else {
         assert numAuxTokens == 0 : "auxTokens: " + numAuxTokens;
      }

      assert newAuxMap == null || newAuxMap.getAuxCount() == numAuxTokens : "auxCount: " + newAuxMap.getAuxCount() + ", HLL tokens: " + numAuxTokens;

      host.putAuxHashMap(newAuxMap, false);
      host.putCurMin(newCurMin);
      host.putNumAtCurMin(numAtNewCurMin);
   }
}

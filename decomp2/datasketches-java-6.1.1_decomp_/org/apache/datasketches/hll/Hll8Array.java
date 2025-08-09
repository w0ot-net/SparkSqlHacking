package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;

class Hll8Array extends HllArray {
   Hll8Array(int lgConfigK) {
      super(lgConfigK, TgtHllType.HLL_8);
      this.hllByteArr = new byte[hll8ArrBytes(lgConfigK)];
   }

   Hll8Array(Hll8Array that) {
      super(that);
   }

   static final Hll8Array heapify(Memory mem) {
      int lgConfigK = PreambleUtil.extractLgK(mem);
      Hll8Array hll8Array = new Hll8Array(lgConfigK);
      HllArray.extractCommonHll(mem, hll8Array);
      return hll8Array;
   }

   Hll8Array copy() {
      return new Hll8Array(this);
   }

   HllSketchImpl couponUpdate(int coupon) {
      int newValue = coupon >>> 26;
      int configKmask = (1 << this.lgConfigK) - 1;
      int slotNo = coupon & configKmask;
      this.updateSlotWithKxQ(slotNo, newValue);
      return this;
   }

   int getNibble(int slotNo) {
      throw new SketchesStateException("Improper access.");
   }

   final int getSlotValue(int slotNo) {
      return this.hllByteArr[slotNo] & 63;
   }

   PairIterator iterator() {
      return new HeapHll8Iterator(1 << this.lgConfigK);
   }

   void putNibble(int slotNo, int nibValue) {
      throw new SketchesStateException("Improper access.");
   }

   final void updateSlotNoKxQ(int slotNo, int newValue) {
      int oldValue = this.getSlotValue(slotNo);
      this.hllByteArr[slotNo] = (byte)Math.max(newValue, oldValue);
   }

   final void updateSlotWithKxQ(int slotNo, int newValue) {
      int oldValue = this.getSlotValue(slotNo);
      if (newValue > oldValue) {
         this.hllByteArr[slotNo] = (byte)(newValue & 63);
         hipAndKxQIncrementalUpdate(this, oldValue, newValue);
         if (oldValue == 0) {
            --this.numAtCurMin;

            assert this.getNumAtCurMin() >= 0;
         }
      }

   }

   final class HeapHll8Iterator extends HllPairIterator {
      HeapHll8Iterator(int lengthPairs) {
         super(lengthPairs);
      }

      int value() {
         return Hll8Array.this.hllByteArr[this.index] & 63;
      }

      public boolean nextValid() {
         while(true) {
            if (++this.index < this.lengthPairs) {
               this.value = Hll8Array.this.hllByteArr[this.index] & 63;
               if (this.value == 0) {
                  continue;
               }

               return true;
            }

            return false;
         }
      }
   }
}

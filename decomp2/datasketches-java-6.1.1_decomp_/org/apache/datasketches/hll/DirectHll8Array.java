package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

class DirectHll8Array extends DirectHllArray {
   DirectHll8Array(int lgConfigK, WritableMemory wmem) {
      super(lgConfigK, TgtHllType.HLL_8, wmem);
   }

   DirectHll8Array(int lgConfigK, Memory mem) {
      super(lgConfigK, TgtHllType.HLL_8, mem);
   }

   HllSketchImpl copy() {
      return Hll8Array.heapify(this.mem);
   }

   HllSketchImpl couponUpdate(int coupon) {
      if (this.wmem == null) {
         HllUtil.noWriteAccess();
      }

      int newValue = HllUtil.getPairValue(coupon);
      int configKmask = (1 << this.getLgConfigK()) - 1;
      int slotNo = HllUtil.getPairLow26(coupon) & configKmask;
      this.updateSlotWithKxQ(slotNo, newValue);
      return this;
   }

   int getHllByteArrBytes() {
      return hll8ArrBytes(this.lgConfigK);
   }

   int getNibble(int slotNo) {
      throw new SketchesStateException("Improper access.");
   }

   final int getSlotValue(int slotNo) {
      return this.mem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + slotNo)) & 63;
   }

   PairIterator iterator() {
      return new DirectHll8Iterator(1 << this.lgConfigK);
   }

   void putNibble(int slotNo, int nibValue) {
      throw new SketchesStateException("Improper access.");
   }

   final void updateSlotNoKxQ(int slotNo, int newValue) {
      int oldValue = this.getSlotValue(slotNo);
      if (newValue > oldValue) {
         this.wmem.putByte((long)(PreambleUtil.HLL_BYTE_ARR_START + slotNo), (byte)(newValue & 63));
      }

   }

   final void updateSlotWithKxQ(int slotNo, int newValue) {
      int oldValue = this.getSlotValue(slotNo);
      if (newValue > oldValue) {
         this.wmem.putByte((long)(PreambleUtil.HLL_BYTE_ARR_START + slotNo), (byte)(newValue & 63));
         hipAndKxQIncrementalUpdate(this, oldValue, newValue);
         if (oldValue == 0) {
            this.decNumAtCurMin();

            assert this.getNumAtCurMin() >= 0;
         }
      }

   }

   final class DirectHll8Iterator extends HllPairIterator {
      DirectHll8Iterator(int lengthPairs) {
         super(lengthPairs);
      }

      int value() {
         int tmp = DirectHll8Array.this.mem.getByte((long)(PreambleUtil.HLL_BYTE_ARR_START + this.index));
         return tmp & 63;
      }
   }
}

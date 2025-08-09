package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class DirectHll6Array extends DirectHllArray {
   DirectHll6Array(int lgConfigK, WritableMemory wmem) {
      super(lgConfigK, TgtHllType.HLL_6, wmem);
   }

   DirectHll6Array(int lgConfigK, Memory mem) {
      super(lgConfigK, TgtHllType.HLL_6, mem);
   }

   HllSketchImpl copy() {
      return Hll6Array.heapify(this.mem);
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
      return hll6ArrBytes(this.lgConfigK);
   }

   int getNibble(int slotNo) {
      throw new SketchesStateException("Improper access.");
   }

   final int getSlotValue(int slotNo) {
      return get6Bit(this.mem, PreambleUtil.HLL_BYTE_ARR_START, slotNo);
   }

   PairIterator iterator() {
      return new DirectHll6Iterator(1 << this.lgConfigK);
   }

   void putNibble(int slotNo, int nibValue) {
      throw new SketchesStateException("Improper access.");
   }

   final void updateSlotNoKxQ(int slotNo, int newValue) {
      throw new SketchesStateException("Improper access.");
   }

   final void updateSlotWithKxQ(int slotNo, int newValue) {
      int oldValue = this.getSlotValue(slotNo);
      if (newValue > oldValue) {
         put6Bit(this.wmem, PreambleUtil.HLL_BYTE_ARR_START, slotNo, newValue);
         hipAndKxQIncrementalUpdate(this, oldValue, newValue);
         if (oldValue == 0) {
            this.decNumAtCurMin();

            assert this.getNumAtCurMin() >= 0;
         }
      }

   }

   private static final void put6Bit(WritableMemory wmem, int offsetBytes, int slotNo, int newValue) {
      int startBit = slotNo * 6;
      int shift = startBit & 7;
      int byteIdx = (startBit >>> 3) + offsetBytes;
      int valShifted = (newValue & 63) << shift;
      int curMasked = wmem.getShort((long)byteIdx) & ~(63 << shift);
      short insert = (short)(curMasked | valShifted);
      wmem.putShort((long)byteIdx, insert);
   }

   private static final int get6Bit(Memory mem, int offsetBytes, int slotNo) {
      int startBit = slotNo * 6;
      int shift = startBit & 7;
      int byteIdx = (startBit >>> 3) + offsetBytes;
      return (byte)(mem.getShort((long)byteIdx) >>> shift & 63);
   }

   final class DirectHll6Iterator extends HllPairIterator {
      int bitOffset = -6;

      DirectHll6Iterator(int lengthPairs) {
         super(lengthPairs);
      }

      int value() {
         this.bitOffset += 6;
         int tmp = DirectHll6Array.this.mem.getShort((long)(PreambleUtil.HLL_BYTE_ARR_START + this.bitOffset / 8));
         int shift = this.bitOffset % 8 & 7;
         return tmp >>> shift & 63;
      }
   }
}

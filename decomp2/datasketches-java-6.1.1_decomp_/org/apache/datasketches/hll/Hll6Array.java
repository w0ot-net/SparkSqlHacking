package org.apache.datasketches.hll;

import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;

class Hll6Array extends HllArray {
   Hll6Array(int lgConfigK) {
      super(lgConfigK, TgtHllType.HLL_6);
      this.hllByteArr = new byte[hll6ArrBytes(lgConfigK)];
   }

   Hll6Array(Hll6Array that) {
      super(that);
   }

   static final Hll6Array heapify(Memory mem) {
      int lgConfigK = PreambleUtil.extractLgK(mem);
      Hll6Array hll6Array = new Hll6Array(lgConfigK);
      HllArray.extractCommonHll(mem, hll6Array);
      return hll6Array;
   }

   Hll6Array copy() {
      return new Hll6Array(this);
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
      return get6Bit(this.hllByteArr, 0, slotNo);
   }

   PairIterator iterator() {
      return new HeapHll6Iterator(1 << this.lgConfigK);
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
         put6Bit(this.hllByteArr, 0, slotNo, newValue);
         hipAndKxQIncrementalUpdate(this, oldValue, newValue);
         if (oldValue == 0) {
            --this.numAtCurMin;

            assert this.getNumAtCurMin() >= 0;
         }
      }

   }

   private static final void put6Bit(byte[] arr, int offsetBytes, int slotNo, int newValue) {
      int startBit = slotNo * 6;
      int shift = startBit & 7;
      int byteIdx = (startBit >>> 3) + offsetBytes;
      int valShifted = (newValue & 63) << shift;
      int curMasked = ByteArrayUtil.getShortLE(arr, byteIdx) & ~(63 << shift);
      short insert = (short)(curMasked | valShifted);
      ByteArrayUtil.putShortLE(arr, byteIdx, insert);
   }

   private static final int get6Bit(byte[] arr, int offsetBytes, int slotNo) {
      int startBit = slotNo * 6;
      int shift = startBit & 7;
      int byteIdx = (startBit >>> 3) + offsetBytes;
      return (byte)(ByteArrayUtil.getShortLE(arr, byteIdx) >>> shift & 63);
   }

   private final class HeapHll6Iterator extends HllPairIterator {
      int bitOffset = -6;

      HeapHll6Iterator(int lengthPairs) {
         super(lengthPairs);
      }

      int value() {
         this.bitOffset += 6;
         int tmp = ByteArrayUtil.getShortLE(Hll6Array.this.hllByteArr, this.bitOffset / 8);
         int shift = this.bitOffset % 8 & 7;
         return tmp >>> shift & 63;
      }
   }
}

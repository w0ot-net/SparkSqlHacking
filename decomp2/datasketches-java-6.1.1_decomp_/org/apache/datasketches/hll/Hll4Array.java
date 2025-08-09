package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;

final class Hll4Array extends HllArray {
   Hll4Array(int lgConfigK) {
      super(lgConfigK, TgtHllType.HLL_4);
      this.hllByteArr = new byte[hll4ArrBytes(lgConfigK)];
   }

   Hll4Array(Hll4Array that) {
      super(that);
   }

   static final Hll4Array heapify(Memory mem) {
      int lgConfigK = PreambleUtil.extractLgK(mem);
      Hll4Array hll4Array = new Hll4Array(lgConfigK);
      HllArray.extractCommonHll(mem, hll4Array);
      int auxStart = hll4Array.auxStart;
      int auxCount = PreambleUtil.extractAuxCount(mem);
      boolean compact = PreambleUtil.extractCompactFlag(mem);
      HeapAuxHashMap auxHashMap = null;
      if (auxCount > 0) {
         auxHashMap = HeapAuxHashMap.heapify(mem, (long)auxStart, lgConfigK, auxCount, compact);
      }

      hll4Array.putAuxHashMap(auxHashMap, false);
      return hll4Array;
   }

   Hll4Array copy() {
      return new Hll4Array(this);
   }

   HllSketchImpl couponUpdate(int coupon) {
      int newValue = coupon >>> 26;
      int configKmask = (1 << this.getLgConfigK()) - 1;
      int slotNo = coupon & configKmask;
      this.updateSlotWithKxQ(slotNo, newValue);
      return this;
   }

   int getNibble(int slotNo) {
      int theByte = this.hllByteArr[slotNo >>> 1];
      if ((slotNo & 1) > 0) {
         theByte >>>= 4;
      }

      return theByte & 15;
   }

   int getSlotValue(int slotNo) {
      int nib = this.getNibble(slotNo);
      if (nib == 15) {
         AuxHashMap auxHashMap = this.getAuxHashMap();
         return auxHashMap.mustFindValueFor(slotNo);
      } else {
         return nib + this.getCurMin();
      }
   }

   int getUpdatableSerializationBytes() {
      AuxHashMap auxHashMap = this.getAuxHashMap();
      int auxBytes;
      if (auxHashMap == null) {
         auxBytes = 4 << HllUtil.LG_AUX_ARR_INTS[this.lgConfigK];
      } else {
         auxBytes = 4 << auxHashMap.getLgAuxArrInts();
      }

      return PreambleUtil.HLL_BYTE_ARR_START + this.getHllByteArrBytes() + auxBytes;
   }

   PairIterator iterator() {
      return new HeapHll4Iterator(1 << this.lgConfigK);
   }

   void putNibble(int slotNo, int nibValue) {
      int byteno = slotNo >>> 1;
      int oldValue = this.hllByteArr[byteno];
      if ((slotNo & 1) == 0) {
         this.hllByteArr[byteno] = (byte)(oldValue & 240 | nibValue & 15);
      } else {
         this.hllByteArr[byteno] = (byte)(oldValue & 15 | nibValue << 4 & 240);
      }

   }

   void updateSlotNoKxQ(int slotNo, int newValue) {
      throw new SketchesStateException("Improper access.");
   }

   void updateSlotWithKxQ(int slotNo, int newValue) {
      Hll4Update.internalHll4Update(this, slotNo, newValue);
   }

   byte[] toCompactByteArray() {
      return ToByteArrayImpl.toHllByteArray(this, true);
   }

   final class HeapHll4Iterator extends HllPairIterator {
      HeapHll4Iterator(int lengthPairs) {
         super(lengthPairs);
      }

      int value() {
         return Hll4Array.this.getSlotValue(this.index);
      }
   }
}

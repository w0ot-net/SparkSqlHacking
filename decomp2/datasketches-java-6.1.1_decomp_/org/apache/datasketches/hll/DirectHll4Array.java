package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class DirectHll4Array extends DirectHllArray {
   DirectHll4Array(int lgConfigK, WritableMemory wmem) {
      super(lgConfigK, TgtHllType.HLL_4, wmem);
      if (PreambleUtil.extractAuxCount(this.mem) > 0) {
         this.putAuxHashMap(new DirectAuxHashMap(this, false), false);
      }

   }

   DirectHll4Array(int lgConfigK, Memory mem) {
      super(lgConfigK, TgtHllType.HLL_4, mem);
      int auxCount = PreambleUtil.extractAuxCount(mem);
      if (auxCount > 0) {
         boolean compact = PreambleUtil.extractCompactFlag(mem);
         AuxHashMap auxHashMap;
         if (compact) {
            auxHashMap = HeapAuxHashMap.heapify(mem, (long)this.auxStart, lgConfigK, auxCount, compact);
         } else {
            auxHashMap = new DirectAuxHashMap(this, false);
         }

         this.putAuxHashMap(auxHashMap, compact);
      }

   }

   HllSketchImpl copy() {
      return Hll4Array.heapify(this.mem);
   }

   HllSketchImpl couponUpdate(int coupon) {
      if (this.wmem == null) {
         HllUtil.noWriteAccess();
      }

      int newValue = coupon >>> 26;
      int configKmask = (1 << this.getLgConfigK()) - 1;
      int slotNo = coupon & configKmask;
      this.updateSlotWithKxQ(slotNo, newValue);
      return this;
   }

   int getHllByteArrBytes() {
      return hll4ArrBytes(this.lgConfigK);
   }

   int getNibble(int slotNo) {
      long offset = (long)(PreambleUtil.HLL_BYTE_ARR_START + (slotNo >>> 1));
      int theByte = this.mem.getByte(offset);
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
      return new DirectHll4Iterator(1 << this.lgConfigK);
   }

   void putNibble(int slotNo, int nibValue) {
      long offset = (long)(PreambleUtil.HLL_BYTE_ARR_START + (slotNo >>> 1));
      int oldValue = this.mem.getByte(offset);
      byte value = (slotNo & 1) == 0 ? (byte)(oldValue & 240 | nibValue & 15) : (byte)(oldValue & 15 | nibValue << 4 & 240);
      this.wmem.putByte(offset, value);
   }

   void updateSlotNoKxQ(int slotNo, int newValue) {
      throw new SketchesStateException("Improper access.");
   }

   void updateSlotWithKxQ(int slotNo, int newValue) {
      Hll4Update.internalHll4Update(this, slotNo, newValue);
   }

   byte[] toCompactByteArray() {
      boolean srcMemIsCompact = PreambleUtil.extractCompactFlag(this.mem);
      int totBytes = this.getCompactSerializationBytes();
      byte[] byteArr = new byte[totBytes];
      WritableMemory memOut = WritableMemory.writableWrap(byteArr);
      if (srcMemIsCompact) {
         this.mem.copyTo(0L, memOut, 0L, (long)totBytes);
         return byteArr;
      } else {
         this.mem.copyTo(0L, memOut, 0L, (long)this.auxStart);
         if (this.auxHashMap != null) {
            int auxCount = this.auxHashMap.getAuxCount();
            PreambleUtil.insertAuxCount(memOut, auxCount);
            PreambleUtil.insertLgArr(memOut, this.auxHashMap.getLgAuxArrInts());
            PairIterator itr = this.auxHashMap.getIterator();
            int cnt = 0;

            while(itr.nextValid()) {
               PreambleUtil.insertInt(memOut, (long)(this.auxStart + (cnt++ << 2)), itr.getPair());
            }

            assert cnt == auxCount;
         }

         PreambleUtil.insertCompactFlag(memOut, true);
         return byteArr;
      }
   }

   byte[] toUpdatableByteArray() {
      boolean memIsCompact = PreambleUtil.extractCompactFlag(this.mem);
      int totBytes = this.getUpdatableSerializationBytes();
      byte[] byteArr = new byte[totBytes];
      WritableMemory memOut = WritableMemory.writableWrap(byteArr);
      if (!memIsCompact) {
         this.mem.copyTo(0L, memOut, 0L, (long)totBytes);
         return byteArr;
      } else {
         HllSketch heapSk = HllSketch.heapify(this.mem);
         return heapSk.toUpdatableByteArray();
      }
   }

   final class DirectHll4Iterator extends HllPairIterator {
      DirectHll4Iterator(int lengthPairs) {
         super(lengthPairs);
      }

      int value() {
         return DirectHll4Array.this.getSlotValue(this.index);
      }
   }
}

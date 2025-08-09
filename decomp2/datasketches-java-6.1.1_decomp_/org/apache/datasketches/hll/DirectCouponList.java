package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

class DirectCouponList extends AbstractCoupons {
   WritableMemory wmem;
   Memory mem;
   final boolean compact;

   private static int checkMemCompactFlag(WritableMemory wmem, int lgConfigK) {
      assert !PreambleUtil.extractCompactFlag(wmem);

      return lgConfigK;
   }

   DirectCouponList(int lgConfigK, TgtHllType tgtHllType, CurMode curMode, WritableMemory wmem) {
      super(checkMemCompactFlag(wmem, lgConfigK), tgtHllType, curMode);
      this.wmem = wmem;
      this.mem = wmem;
      this.compact = PreambleUtil.extractCompactFlag(wmem);
   }

   DirectCouponList(int lgConfigK, TgtHllType tgtHllType, CurMode curMode, Memory mem) {
      super(lgConfigK, tgtHllType, curMode);
      this.wmem = null;
      this.mem = mem;
      this.compact = PreambleUtil.extractCompactFlag(mem);
   }

   static DirectCouponList newInstance(int lgConfigK, TgtHllType tgtHllType, WritableMemory dstMem) {
      PreambleUtil.insertPreInts(dstMem, 2);
      PreambleUtil.insertSerVer(dstMem);
      PreambleUtil.insertFamilyId(dstMem);
      PreambleUtil.insertLgK(dstMem, lgConfigK);
      PreambleUtil.insertLgArr(dstMem, 3);
      PreambleUtil.insertFlags(dstMem, 4);
      PreambleUtil.insertListCount(dstMem, 0);
      PreambleUtil.insertModes(dstMem, tgtHllType, CurMode.LIST);
      return new DirectCouponList(lgConfigK, tgtHllType, CurMode.LIST, dstMem);
   }

   CouponList copy() {
      return CouponList.heapifyList(this.mem);
   }

   CouponList copyAs(TgtHllType tgtHllType) {
      CouponList clist = CouponList.heapifyList(this.mem);
      return new CouponList(clist, tgtHllType);
   }

   HllSketchImpl couponUpdate(int coupon) {
      if (this.wmem == null) {
         HllUtil.noWriteAccess();
      }

      int len = 1 << this.getLgCouponArrInts();

      for(int i = 0; i < len; ++i) {
         int couponAtIdx = PreambleUtil.extractInt(this.mem, (long)(PreambleUtil.LIST_INT_ARR_START + (i << 2)));
         if (couponAtIdx == 0) {
            PreambleUtil.insertInt(this.wmem, (long)(PreambleUtil.LIST_INT_ARR_START + (i << 2)), coupon);
            int couponCount = PreambleUtil.extractListCount(this.mem);
            ++couponCount;
            PreambleUtil.insertListCount(this.wmem, couponCount);
            PreambleUtil.insertEmptyFlag(this.wmem, false);
            if (couponCount >= len) {
               if (this.lgConfigK < 8) {
                  return promoteListOrSetToHll(this);
               }

               return promoteListToSet(this);
            }

            return this;
         }

         if (couponAtIdx == coupon) {
            return this;
         }
      }

      throw new SketchesStateException("Invalid State: no empties & no duplicates");
   }

   int getCompactSerializationBytes() {
      return this.getMemDataStart() + (this.getCouponCount() << 2);
   }

   int getCouponCount() {
      return PreambleUtil.extractListCount(this.mem);
   }

   int[] getCouponIntArr() {
      return null;
   }

   int getLgCouponArrInts() {
      int lgArr = PreambleUtil.extractLgArr(this.mem);
      if (lgArr >= 3) {
         return lgArr;
      } else {
         int coupons = this.getCouponCount();
         return PreambleUtil.computeLgArr(this.mem, coupons, this.lgConfigK);
      }
   }

   int getMemDataStart() {
      return PreambleUtil.LIST_INT_ARR_START;
   }

   Memory getMemory() {
      return this.mem;
   }

   int getPreInts() {
      return 2;
   }

   WritableMemory getWritableMemory() {
      return this.wmem;
   }

   boolean isCompact() {
      return this.compact;
   }

   boolean isMemory() {
      return true;
   }

   boolean isOffHeap() {
      return this.mem.isDirect();
   }

   boolean isSameResource(Memory mem) {
      return this.mem.isSameResource(mem);
   }

   PairIterator iterator() {
      long dataStart = (long)this.getMemDataStart();
      int lenInts = this.compact ? this.getCouponCount() : 1 << this.getLgCouponArrInts();
      return new IntMemoryPairIterator(this.mem, dataStart, lenInts, this.lgConfigK);
   }

   void mergeTo(HllSketch that) {
      int lenInts = this.compact ? this.getCouponCount() : 1 << this.getLgCouponArrInts();
      int dataStart = this.getMemDataStart();

      for(int i = 0; i < lenInts; ++i) {
         int pair = this.mem.getInt((long)(dataStart + (i << 2)));
         if (pair != 0) {
            that.couponUpdate(pair);
         }
      }

   }

   DirectCouponList reset() {
      if (this.wmem == null) {
         throw new SketchesArgumentException("Cannot reset a read-only sketch");
      } else {
         PreambleUtil.insertEmptyFlag(this.wmem, true);
         int bytes = HllSketch.getMaxUpdatableSerializationBytes(this.lgConfigK, this.tgtHllType);
         this.wmem.clear(0L, (long)bytes);
         return newInstance(this.lgConfigK, this.tgtHllType, this.wmem);
      }
   }

   static final DirectCouponHashSet promoteListToSet(DirectCouponList src) {
      WritableMemory wmem = src.wmem;
      HllUtil.checkPreamble(wmem);
      int lgConfigK = src.lgConfigK;
      TgtHllType tgtHllType = src.tgtHllType;
      int srcOffset = PreambleUtil.LIST_INT_ARR_START;
      int couponArrInts = 1 << src.getLgCouponArrInts();
      int[] couponArr = new int[couponArrInts];
      wmem.getIntArray((long)srcOffset, couponArr, 0, couponArrInts);
      PreambleUtil.insertPreInts(wmem, 3);
      PreambleUtil.insertLgArr(wmem, 5);
      PreambleUtil.insertCurMin(wmem, 0);
      PreambleUtil.insertCurMode(wmem, CurMode.SET);
      int maxBytes = HllSketch.getMaxUpdatableSerializationBytes(lgConfigK, tgtHllType);
      wmem.clear((long)PreambleUtil.LIST_INT_ARR_START, (long)(maxBytes - PreambleUtil.LIST_INT_ARR_START));
      DirectCouponHashSet dchSet = new DirectCouponHashSet(src.lgConfigK, src.tgtHllType, src.wmem);

      for(int i = 0; i < couponArrInts; ++i) {
         int coupon = couponArr[i];
         if (coupon != 0) {
            dchSet.couponUpdate(coupon);
         }
      }

      return dchSet;
   }

   static final DirectHllArray promoteListOrSetToHll(DirectCouponList src) {
      WritableMemory wmem = src.wmem;
      HllUtil.checkPreamble(wmem);
      int lgConfigK = src.lgConfigK;
      TgtHllType tgtHllType = src.tgtHllType;
      int srcMemDataStart = src.getMemDataStart();
      double est = src.getEstimate();
      int couponArrInts = 1 << src.getLgCouponArrInts();
      int[] couponArr = new int[couponArrInts];
      wmem.getIntArray((long)srcMemDataStart, couponArr, 0, couponArrInts);
      PreambleUtil.insertPreInts(wmem, 10);
      PreambleUtil.insertLgArr(wmem, 0);
      PreambleUtil.insertFlags(wmem, 0);
      PreambleUtil.insertCurMin(wmem, 0);
      PreambleUtil.insertCurMode(wmem, CurMode.HLL);
      int maxBytes = HllSketch.getMaxUpdatableSerializationBytes(lgConfigK, tgtHllType);
      wmem.clear((long)PreambleUtil.LIST_INT_ARR_START, (long)(maxBytes - PreambleUtil.LIST_INT_ARR_START));
      PreambleUtil.insertNumAtCurMin(wmem, 1 << lgConfigK);
      PreambleUtil.insertKxQ0(wmem, (double)(1 << lgConfigK));
      DirectHllArray dirHllArr;
      if (tgtHllType == TgtHllType.HLL_4) {
         dirHllArr = new DirectHll4Array(lgConfigK, wmem);
      } else if (tgtHllType == TgtHllType.HLL_6) {
         dirHllArr = new DirectHll6Array(lgConfigK, wmem);
      } else {
         dirHllArr = new DirectHll8Array(lgConfigK, wmem);
      }

      for(int i = 0; i < couponArrInts; ++i) {
         int coupon = couponArr[i];
         if (coupon != 0) {
            dirHllArr.couponUpdate(coupon);
         }
      }

      dirHllArr.putHipAccum(est);
      return dirHllArr;
   }
}

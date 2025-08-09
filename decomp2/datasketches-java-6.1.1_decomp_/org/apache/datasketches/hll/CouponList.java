package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

class CouponList extends AbstractCoupons {
   int lgCouponArrInts;
   int couponCount;
   int[] couponIntArr;

   private static int checkLgConfigK(CurMode curMode, int lgConfigK) {
      assert curMode != CurMode.SET || lgConfigK > 7;

      return lgConfigK;
   }

   CouponList(int lgConfigK, TgtHllType tgtHllType, CurMode curMode) {
      super(checkLgConfigK(curMode, lgConfigK), tgtHllType, curMode);
      if (curMode == CurMode.LIST) {
         this.lgCouponArrInts = 3;
      } else {
         this.lgCouponArrInts = 5;
      }

      this.couponIntArr = new int[1 << this.lgCouponArrInts];
      this.couponCount = 0;
   }

   CouponList(CouponList that) {
      super(that.lgConfigK, that.tgtHllType, that.curMode);
      this.lgCouponArrInts = that.lgCouponArrInts;
      this.couponCount = that.couponCount;
      this.couponIntArr = (int[])that.couponIntArr.clone();
   }

   CouponList(CouponList that, TgtHllType tgtHllType) {
      super(that.lgConfigK, tgtHllType, that.curMode);
      this.lgCouponArrInts = that.lgCouponArrInts;
      this.couponCount = that.couponCount;
      this.couponIntArr = (int[])that.couponIntArr.clone();
   }

   static final CouponList heapifyList(Memory mem) {
      int lgConfigK = PreambleUtil.extractLgK(mem);
      TgtHllType tgtHllType = PreambleUtil.extractTgtHllType(mem);
      CouponList list = new CouponList(lgConfigK, tgtHllType, CurMode.LIST);
      int couponCount = PreambleUtil.extractListCount(mem);
      mem.getIntArray((long)PreambleUtil.LIST_INT_ARR_START, list.couponIntArr, 0, couponCount);
      list.couponCount = couponCount;
      return list;
   }

   CouponList copy() {
      return new CouponList(this);
   }

   CouponList copyAs(TgtHllType tgtHllType) {
      return new CouponList(this, tgtHllType);
   }

   HllSketchImpl couponUpdate(int coupon) {
      int len = 1 << this.lgCouponArrInts;

      for(int i = 0; i < len; ++i) {
         int couponAtIdx = this.couponIntArr[i];
         if (couponAtIdx == 0) {
            this.couponIntArr[i] = coupon;
            ++this.couponCount;
            if (this.couponCount >= len) {
               if (this.lgConfigK < 8) {
                  return promoteHeapListOrSetToHll(this);
               }

               return promoteHeapListToSet(this);
            }

            return this;
         }

         if (couponAtIdx == coupon) {
            return this;
         }
      }

      throw new SketchesStateException("Array invalid: no empties & no duplicates");
   }

   int getCompactSerializationBytes() {
      return this.getMemDataStart() + (this.couponCount << 2);
   }

   int getCouponCount() {
      return this.couponCount;
   }

   int[] getCouponIntArr() {
      return this.couponIntArr;
   }

   int getLgCouponArrInts() {
      return this.lgCouponArrInts;
   }

   int getMemDataStart() {
      return PreambleUtil.LIST_INT_ARR_START;
   }

   Memory getMemory() {
      return null;
   }

   int getPreInts() {
      return 2;
   }

   WritableMemory getWritableMemory() {
      return null;
   }

   boolean isCompact() {
      return false;
   }

   boolean isMemory() {
      return false;
   }

   boolean isOffHeap() {
      return false;
   }

   boolean isSameResource(Memory mem) {
      return false;
   }

   PairIterator iterator() {
      return new IntArrayPairIterator(this.couponIntArr, this.lgConfigK);
   }

   void mergeTo(HllSketch that) {
      int arrLen = this.couponIntArr.length;

      for(int i = 0; i < arrLen; ++i) {
         int pair = this.couponIntArr[i];
         if (pair != 0) {
            that.couponUpdate(pair);
         }
      }

   }

   CouponList reset() {
      return new CouponList(this.lgConfigK, this.tgtHllType, CurMode.LIST);
   }

   static final HllSketchImpl promoteHeapListToSet(CouponList list) {
      int couponCount = list.couponCount;
      int[] arr = list.couponIntArr;
      CouponHashSet chSet = new CouponHashSet(list.lgConfigK, list.tgtHllType);

      for(int i = 0; i < couponCount; ++i) {
         chSet.couponUpdate(arr[i]);
      }

      return chSet;
   }

   static final HllSketchImpl promoteHeapListOrSetToHll(CouponList src) {
      HllArray tgtHllArr = HllArray.newHeapHll(src.lgConfigK, src.tgtHllType);
      PairIterator srcItr = src.iterator();
      tgtHllArr.putKxQ0((double)(1 << src.lgConfigK));

      while(srcItr.nextValid()) {
         tgtHllArr.couponUpdate(srcItr.getPair());
      }

      tgtHllArr.putHipAccum(src.getEstimate());
      tgtHllArr.putOutOfOrder(false);
      return tgtHllArr;
   }
}

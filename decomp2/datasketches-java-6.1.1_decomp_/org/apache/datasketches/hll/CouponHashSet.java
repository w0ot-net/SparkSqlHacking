package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;

final class CouponHashSet extends CouponList {
   CouponHashSet(int lgConfigK, TgtHllType tgtHllType) {
      super(lgConfigK, tgtHllType, CurMode.SET);

      assert lgConfigK > 7;

   }

   CouponHashSet(CouponHashSet that) {
      super(that);
   }

   CouponHashSet(CouponHashSet that, TgtHllType tgtHllType) {
      super(that, tgtHllType);
   }

   static final CouponHashSet heapifySet(Memory mem) {
      int lgConfigK = PreambleUtil.extractLgK(mem);
      TgtHllType tgtHllType = PreambleUtil.extractTgtHllType(mem);
      CurMode curMode = PreambleUtil.extractCurMode(mem);
      int memArrStart = curMode == CurMode.LIST ? PreambleUtil.LIST_INT_ARR_START : PreambleUtil.HASH_SET_INT_ARR_START;
      CouponHashSet set = new CouponHashSet(lgConfigK, tgtHllType);
      boolean memIsCompact = PreambleUtil.extractCompactFlag(mem);
      int couponCount = PreambleUtil.extractHashSetCount(mem);
      int lgCouponArrInts = PreambleUtil.extractLgArr(mem);
      if (lgCouponArrInts < 5) {
         lgCouponArrInts = PreambleUtil.computeLgArr(mem, couponCount, lgConfigK);
      }

      if (memIsCompact) {
         for(int i = 0; i < couponCount; ++i) {
            set.couponUpdate(PreambleUtil.extractInt(mem, (long)(memArrStart + (i << 2))));
         }
      } else {
         set.couponCount = couponCount;
         set.lgCouponArrInts = lgCouponArrInts;
         int couponArrInts = 1 << lgCouponArrInts;
         set.couponIntArr = new int[couponArrInts];
         mem.getIntArray((long)PreambleUtil.HASH_SET_INT_ARR_START, set.couponIntArr, 0, couponArrInts);
      }

      return set;
   }

   CouponHashSet copy() {
      return new CouponHashSet(this);
   }

   CouponHashSet copyAs(TgtHllType tgtHllType) {
      return new CouponHashSet(this, tgtHllType);
   }

   HllSketchImpl couponUpdate(int coupon) {
      int index = find(this.couponIntArr, this.lgCouponArrInts, coupon);
      if (index >= 0) {
         return this;
      } else {
         this.couponIntArr[~index] = coupon;
         ++this.couponCount;
         return (HllSketchImpl)(this.checkGrowOrPromote() ? promoteHeapListOrSetToHll(this) : this);
      }
   }

   int getMemDataStart() {
      return PreambleUtil.HASH_SET_INT_ARR_START;
   }

   int getPreInts() {
      return 3;
   }

   private boolean checkGrowOrPromote() {
      if (4 * this.couponCount > 3 * (1 << this.lgCouponArrInts)) {
         if (this.lgCouponArrInts == this.lgConfigK - 3) {
            return true;
         }

         this.couponIntArr = growHashSet(this.couponIntArr, ++this.lgCouponArrInts);
      }

      return false;
   }

   private static final int[] growHashSet(int[] coupIntArr, int tgtLgCoupArrSize) {
      int[] tgtCouponIntArr = new int[1 << tgtLgCoupArrSize];

      for(int fetched : coupIntArr) {
         if (fetched != 0) {
            int idx = find(tgtCouponIntArr, tgtLgCoupArrSize, fetched);
            if (idx >= 0) {
               throw new SketchesStateException("Error: found duplicate.");
            }

            tgtCouponIntArr[~idx] = fetched;
         }
      }

      return tgtCouponIntArr;
   }
}

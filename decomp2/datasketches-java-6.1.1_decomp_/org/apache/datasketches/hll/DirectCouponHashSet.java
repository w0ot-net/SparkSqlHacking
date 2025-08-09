package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class DirectCouponHashSet extends DirectCouponList {
   DirectCouponHashSet(int lgConfigK, TgtHllType tgtHllType, WritableMemory wmem) {
      super(lgConfigK, tgtHllType, CurMode.SET, wmem);

      assert wmem.getByte((long)PreambleUtil.LG_K_BYTE) > 7;

   }

   DirectCouponHashSet(int lgConfigK, TgtHllType tgtHllType, Memory mem) {
      super(lgConfigK, tgtHllType, CurMode.SET, mem);

      assert mem.getByte((long)PreambleUtil.LG_K_BYTE) > 7;

   }

   CouponHashSet copy() {
      return CouponHashSet.heapifySet(this.mem);
   }

   CouponHashSet copyAs(TgtHllType tgtHllType) {
      CouponHashSet clist = CouponHashSet.heapifySet(this.mem);
      return new CouponHashSet(clist, tgtHllType);
   }

   HllSketchImpl couponUpdate(int coupon) {
      if (this.wmem == null) {
         HllUtil.noWriteAccess();
      }

      int index = find(this.mem, this.getLgCouponArrInts(), coupon);
      if (index >= 0) {
         return this;
      } else {
         PreambleUtil.insertInt(this.wmem, (long)(PreambleUtil.HASH_SET_INT_ARR_START + (~index << 2)), coupon);
         PreambleUtil.insertHashSetCount(this.wmem, this.getCouponCount() + 1);
         boolean promote = this.checkGrowOrPromote();
         return (HllSketchImpl)(!promote ? this : promoteListOrSetToHll(this));
      }
   }

   int getCouponCount() {
      return PreambleUtil.extractHashSetCount(this.mem);
   }

   int getMemDataStart() {
      return PreambleUtil.HASH_SET_INT_ARR_START;
   }

   int getPreInts() {
      return 3;
   }

   private boolean checkGrowOrPromote() {
      int lgCouponArrInts = this.getLgCouponArrInts();
      if (4 * this.getCouponCount() > 3 * (1 << lgCouponArrInts)) {
         if (lgCouponArrInts == this.getLgConfigK() - 3) {
            return true;
         }

         ++lgCouponArrInts;
         PreambleUtil.insertLgArr(this.wmem, lgCouponArrInts);
         growHashSet(this.wmem, lgCouponArrInts);
      }

      return false;
   }

   private static final void growHashSet(WritableMemory wmem, int tgtLgCouponArrSize) {
      int tgtArrSize = 1 << tgtLgCouponArrSize;
      int[] tgtCouponIntArr = new int[tgtArrSize];
      int oldLen = 1 << PreambleUtil.extractLgArr(wmem);

      for(int i = 0; i < oldLen; ++i) {
         int fetched = PreambleUtil.extractInt(wmem, (long)(PreambleUtil.HASH_SET_INT_ARR_START + (i << 2)));
         if (fetched != 0) {
            int idx = find(tgtCouponIntArr, tgtLgCouponArrSize, fetched);
            if (idx >= 0) {
               throw new SketchesStateException("Error: found duplicate.");
            }

            tgtCouponIntArr[~idx] = fetched;
         }
      }

      wmem.clear((long)PreambleUtil.HASH_SET_INT_ARR_START, (long)(tgtArrSize << 2));
      wmem.putIntArray((long)PreambleUtil.HASH_SET_INT_ARR_START, tgtCouponIntArr, 0, tgtArrSize);
   }

   private static final int find(Memory mem, int lgArr, int coupon) {
      int arrMask = (1 << lgArr) - 1;
      int probe = coupon & arrMask;
      int loopIndex = probe;

      do {
         int couponAtIndex = PreambleUtil.extractInt(mem, (long)(PreambleUtil.HASH_SET_INT_ARR_START + (probe << 2)));
         if (couponAtIndex == 0) {
            return ~probe;
         }

         if (coupon == couponAtIndex) {
            return probe;
         }

         int stride = (coupon & 67108863) >>> lgArr | 1;
         probe = probe + stride & arrMask;
      } while(probe != loopIndex);

      throw new SketchesArgumentException("Key not found and no empty slots!");
   }
}

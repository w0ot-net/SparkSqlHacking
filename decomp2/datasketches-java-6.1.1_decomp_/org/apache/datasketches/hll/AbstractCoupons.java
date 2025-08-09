package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesArgumentException;

abstract class AbstractCoupons extends HllSketchImpl {
   AbstractCoupons(int lgConfigK, TgtHllType tgtHllType, CurMode curMode) {
      super(lgConfigK, tgtHllType, curMode);
   }

   double getCompositeEstimate() {
      return this.getEstimate();
   }

   abstract int getCouponCount();

   abstract int[] getCouponIntArr();

   double getEstimate() {
      int couponCount = this.getCouponCount();
      double est = CubicInterpolation.usingXAndYTables(CouponMapping.xArr, CouponMapping.yArr, (double)couponCount);
      return Math.max(est, (double)couponCount);
   }

   double getHipEstimate() {
      return this.getEstimate();
   }

   abstract int getLgCouponArrInts();

   double getLowerBound(int numStdDev) {
      HllUtil.checkNumStdDev(numStdDev);
      int couponCount = this.getCouponCount();
      double est = CubicInterpolation.usingXAndYTables(CouponMapping.xArr, CouponMapping.yArr, (double)couponCount);
      double tmp = est / ((double)1.0F + (double)numStdDev * 4.99267578125E-5);
      return Math.max(tmp, (double)couponCount);
   }

   double getUpperBound(int numStdDev) {
      HllUtil.checkNumStdDev(numStdDev);
      int couponCount = this.getCouponCount();
      double est = CubicInterpolation.usingXAndYTables(CouponMapping.xArr, CouponMapping.yArr, (double)couponCount);
      double tmp = est / ((double)1.0F - (double)numStdDev * 4.99267578125E-5);
      return Math.max(tmp, (double)couponCount);
   }

   int getUpdatableSerializationBytes() {
      return this.getMemDataStart() + (4 << this.getLgCouponArrInts());
   }

   boolean isEmpty() {
      return this.getCouponCount() == 0;
   }

   boolean isOutOfOrder() {
      return false;
   }

   boolean isRebuildCurMinNumKxQFlag() {
      return false;
   }

   void putEmptyFlag(boolean empty) {
   }

   void putOutOfOrder(boolean outOfOrder) {
   }

   void putRebuildCurMinNumKxQFlag(boolean rebuild) {
   }

   byte[] toCompactByteArray() {
      return ToByteArrayImpl.toCouponByteArray(this, true);
   }

   byte[] toUpdatableByteArray() {
      return ToByteArrayImpl.toCouponByteArray(this, false);
   }

   static final int find(int[] array, int lgArrInts, int coupon) {
      int arrMask = array.length - 1;
      int probe = coupon & arrMask;
      int loopIndex = probe;

      do {
         int couponAtIdx = array[probe];
         if (couponAtIdx == 0) {
            return ~probe;
         }

         if (coupon == couponAtIdx) {
            return probe;
         }

         int stride = (coupon & 67108863) >>> lgArrInts | 1;
         probe = probe + stride & arrMask;
      } while(probe != loopIndex);

      throw new SketchesArgumentException("Key not found and no empty slots!");
   }
}

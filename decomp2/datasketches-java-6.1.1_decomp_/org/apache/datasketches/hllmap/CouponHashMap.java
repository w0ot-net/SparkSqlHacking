package org.apache.datasketches.hllmap;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.hash.MurmurHash3;

final class CouponHashMap extends Map {
   private static final double INNER_LOAD_FACTOR = (double)0.75F;
   private static final byte DELETED_KEY_MARKER = -1;
   private static final int BYTE_MASK = 255;
   private static final int COUPON_K = 1024;
   private static final double RSE = 0.408 / Math.sqrt((double)1024.0F);
   private final int maxCouponsPerKey_;
   private final int capacityCouponsPerKey_;
   private final int entrySizeBytes_;
   private int tableEntries_;
   private int capacityEntries_;
   private int numActiveKeys_;
   private int numDeletedKeys_;
   private byte[] keysArr_;
   private short[] couponsArr_;
   private byte[] curCountsArr_;
   private float[] invPow2SumArr_;
   private float[] hipEstAccumArr_;

   private CouponHashMap(int keySizeBytes, int maxCouponsPerKey) {
      super(keySizeBytes);
      this.maxCouponsPerKey_ = maxCouponsPerKey;
      this.capacityCouponsPerKey_ = (int)((double)maxCouponsPerKey * (double)0.75F);
      this.entrySizeBytes_ = keySizeBytes + maxCouponsPerKey * 2 + 1 + 4 + 4;
   }

   static CouponHashMap getInstance(int keySizeBytes, int maxCouponsPerKey) {
      checkMaxCouponsPerKey(maxCouponsPerKey);
      int tableEntries = 157;
      CouponHashMap map = new CouponHashMap(keySizeBytes, maxCouponsPerKey);
      map.tableEntries_ = 157;
      map.capacityEntries_ = 147;
      map.numActiveKeys_ = 0;
      map.numDeletedKeys_ = 0;
      map.keysArr_ = new byte[157 * keySizeBytes];
      map.couponsArr_ = new short[157 * maxCouponsPerKey];
      map.curCountsArr_ = new byte[157];
      map.invPow2SumArr_ = new float[157];
      map.hipEstAccumArr_ = new float[157];
      return map;
   }

   double update(byte[] key, short coupon) {
      int entryIndex = this.findOrInsertKey(key);
      return this.update(entryIndex, coupon);
   }

   double update(int entryIndex, short coupon) {
      int couponMapArrEntryIndex = entryIndex * this.maxCouponsPerKey_;

      int innerCouponIndex;
      for(innerCouponIndex = (coupon & '\uffff') % this.maxCouponsPerKey_; this.couponsArr_[couponMapArrEntryIndex + innerCouponIndex] != 0; innerCouponIndex = (innerCouponIndex + 1) % this.maxCouponsPerKey_) {
         if (this.couponsArr_[couponMapArrEntryIndex + innerCouponIndex] == coupon) {
            return (double)this.hipEstAccumArr_[entryIndex];
         }
      }

      if ((this.curCountsArr_[entryIndex] + 1 & 255) > this.capacityCouponsPerKey_) {
         return (double)(-this.hipEstAccumArr_[entryIndex]);
      } else {
         this.couponsArr_[couponMapArrEntryIndex + innerCouponIndex] = coupon;
         ++this.curCountsArr_[entryIndex];
         float[] var5 = this.hipEstAccumArr_;
         var5[entryIndex] += 1024.0F / this.invPow2SumArr_[entryIndex];
         var5 = this.invPow2SumArr_;
         var5[entryIndex] = (float)((double)var5[entryIndex] - Util.invPow2(coupon16Value(coupon)));
         return (double)this.hipEstAccumArr_[entryIndex];
      }
   }

   double getEstimate(byte[] key) {
      int index = this.findKey(key);
      return index < 0 ? (double)0.0F : (double)this.hipEstAccumArr_[index];
   }

   double getUpperBound(byte[] key) {
      return this.getEstimate(key) * ((double)1.0F + RSE);
   }

   double getLowerBound(byte[] key) {
      return this.getEstimate(key) * ((double)1.0F - RSE);
   }

   void updateEstimate(int entryIndex, double estimate) {
      if (entryIndex < 0) {
         throw new SketchesArgumentException("Key not found.");
      } else {
         this.hipEstAccumArr_[entryIndex] = (float)estimate;
      }
   }

   int findKey(byte[] key) {
      long[] hash = MurmurHash3.hash(key, 1234567890L);
      int entryIndex = getIndex(hash[0], this.tableEntries_);
      int firstDeletedIndex = -1;
      int loopIndex = entryIndex;

      while(this.curCountsArr_[entryIndex] != 0) {
         if (this.curCountsArr_[entryIndex] == -1) {
            if (firstDeletedIndex == -1) {
               firstDeletedIndex = entryIndex;
            }
         } else if (Map.arraysEqual(this.keysArr_, entryIndex * this.keySizeBytes_, key, 0, this.keySizeBytes_)) {
            return entryIndex;
         }

         entryIndex = (entryIndex + getStride(hash[1], this.tableEntries_)) % this.tableEntries_;
         if (entryIndex == loopIndex) {
            throw new SketchesArgumentException("Key not found and no empty slots!");
         }
      }

      return firstDeletedIndex == -1 ? ~entryIndex : ~firstDeletedIndex;
   }

   int findOrInsertKey(byte[] key) {
      int entryIndex = this.findKey(key);
      if (entryIndex < 0) {
         entryIndex = ~entryIndex;
         if (this.curCountsArr_[entryIndex] == -1) {
            Arrays.fill(this.couponsArr_, entryIndex * this.maxCouponsPerKey_, (entryIndex + 1) * this.maxCouponsPerKey_, (short)0);
            this.curCountsArr_[entryIndex] = 0;
            --this.numDeletedKeys_;
         }

         if (this.numActiveKeys_ + this.numDeletedKeys_ >= this.capacityEntries_) {
            this.resize();
            entryIndex = ~this.findKey(key);

            assert entryIndex >= 0;
         }

         System.arraycopy(key, 0, this.keysArr_, entryIndex * this.keySizeBytes_, this.keySizeBytes_);
         this.invPow2SumArr_[entryIndex] = 1024.0F;
         this.hipEstAccumArr_[entryIndex] = 0.0F;
         ++this.numActiveKeys_;
      }

      return entryIndex;
   }

   void deleteKey(int entryIndex) {
      this.curCountsArr_[entryIndex] = -1;
      --this.numActiveKeys_;
      ++this.numDeletedKeys_;
      if (this.numActiveKeys_ > 157 && (double)this.numActiveKeys_ < (double)this.tableEntries_ * (double)0.5F) {
         this.resize();
      }

   }

   CouponsIterator getCouponsIterator(int entryIndex) {
      return new CouponsIterator(this.couponsArr_, entryIndex * this.maxCouponsPerKey_, this.maxCouponsPerKey_);
   }

   double getEntrySizeBytes() {
      return (double)this.entrySizeBytes_;
   }

   int getTableEntries() {
      return this.tableEntries_;
   }

   int getCapacityEntries() {
      return this.capacityEntries_;
   }

   int getCurrentCountEntries() {
      return this.numActiveKeys_ + this.numDeletedKeys_;
   }

   long getMemoryUsageBytes() {
      long arrays = (long)this.keysArr_.length + (long)this.couponsArr_.length * 2L + (long)this.curCountsArr_.length + (long)this.invPow2SumArr_.length * 4L + (long)this.hipEstAccumArr_.length * 4L;
      long other = 20L;
      return arrays + 20L;
   }

   int getActiveEntries() {
      return this.numActiveKeys_;
   }

   int getDeletedEntries() {
      return this.numDeletedKeys_;
   }

   int getMaxCouponsPerEntry() {
      return this.maxCouponsPerKey_;
   }

   int getCapacityCouponsPerEntry() {
      return this.capacityCouponsPerKey_;
   }

   private static final void checkMaxCouponsPerKey(int maxCouponsPerKey) {
      Util.checkIfPowerOf2((long)maxCouponsPerKey, "maxCouponsPerKey");
      if (maxCouponsPerKey < 16 || maxCouponsPerKey > 256) {
         throw new SketchesArgumentException("Required: 16 <= maxCouponsPerKey <= 256 : " + maxCouponsPerKey);
      }
   }

   private void resize() {
      byte[] oldKeysArr = this.keysArr_;
      short[] oldCouponMapArr = this.couponsArr_;
      byte[] oldCurCountsArr = this.curCountsArr_;
      float[] oldInvPow2SumArr = this.invPow2SumArr_;
      float[] oldHipEstAccumArr = this.hipEstAccumArr_;
      int oldNumEntries = this.tableEntries_;
      this.tableEntries_ = Math.max(nextPrime((int)((double)this.numActiveKeys_ / 0.6666666666666666)), 157);
      this.capacityEntries_ = (int)((double)this.tableEntries_ * (double)0.9375F);
      this.keysArr_ = new byte[this.tableEntries_ * this.keySizeBytes_];
      this.couponsArr_ = new short[this.tableEntries_ * this.maxCouponsPerKey_];
      this.curCountsArr_ = new byte[this.tableEntries_];
      this.invPow2SumArr_ = new float[this.tableEntries_];
      this.hipEstAccumArr_ = new float[this.tableEntries_];
      this.numActiveKeys_ = 0;
      this.numDeletedKeys_ = 0;

      for(int i = 0; i < oldNumEntries; ++i) {
         if (oldCurCountsArr[i] != 0 && oldCurCountsArr[i] != -1) {
            byte[] key = Arrays.copyOfRange(oldKeysArr, i * this.keySizeBytes_, i * this.keySizeBytes_ + this.keySizeBytes_);
            int index = this.insertKey(key);
            System.arraycopy(oldCouponMapArr, i * this.maxCouponsPerKey_, this.couponsArr_, index * this.maxCouponsPerKey_, this.maxCouponsPerKey_);
            this.curCountsArr_[index] = oldCurCountsArr[i];
            this.invPow2SumArr_[index] = oldInvPow2SumArr[i];
            this.hipEstAccumArr_[index] = oldHipEstAccumArr[i];
         }
      }

   }

   private int insertKey(byte[] key) {
      long[] hash = MurmurHash3.hash(key, 1234567890L);
      int entryIndex = getIndex(hash[0], this.tableEntries_);
      int loopIndex = entryIndex;

      while(this.curCountsArr_[entryIndex] != 0) {
         entryIndex = (entryIndex + getStride(hash[1], this.tableEntries_)) % this.tableEntries_;
         if (entryIndex == loopIndex) {
            throw new SketchesArgumentException("Key not found and no empty slots!");
         }
      }

      System.arraycopy(key, 0, this.keysArr_, entryIndex * this.keySizeBytes_, this.keySizeBytes_);
      ++this.numActiveKeys_;
      return entryIndex;
   }
}

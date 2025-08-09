package org.apache.datasketches.hllmap;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.hash.MurmurHash3;

final class CouponTraverseMap extends Map {
   private static final double RSE = 0.408 / Math.sqrt((double)1024.0F);
   private final int maxCouponsPerKey_;
   private int tableEntries_;
   private int capacityEntries_;
   private int numActiveKeys_;
   private int numDeletedKeys_;
   private double entrySizeBytes_;
   private byte[] keysArr_;
   private short[] couponsArr_;
   private byte[] stateArr_;

   private CouponTraverseMap(int keySizeBytes, int maxCouponsPerKey) {
      super(keySizeBytes);
      this.maxCouponsPerKey_ = maxCouponsPerKey;
   }

   static CouponTraverseMap getInstance(int keySizeBytes, int maxCouponsPerKey) {
      CouponTraverseMap map = new CouponTraverseMap(keySizeBytes, maxCouponsPerKey);
      map.tableEntries_ = 157;
      map.capacityEntries_ = (int)((double)map.tableEntries_ * (double)0.9375F);
      map.numActiveKeys_ = 0;
      map.numDeletedKeys_ = 0;
      map.entrySizeBytes_ = updateEntrySizeBytes(map.tableEntries_, keySizeBytes, maxCouponsPerKey);
      map.keysArr_ = new byte[157 * keySizeBytes];
      map.couponsArr_ = new short[157 * maxCouponsPerKey];
      map.stateArr_ = new byte[COUPON_MAP_MIN_NUM_ENTRIES_ARR_SIZE];
      return map;
   }

   double update(byte[] key, short coupon) {
      int entryIndex = this.findOrInsertKey(key);
      return this.update(entryIndex, coupon);
   }

   double update(int entryIndex, short value) {
      int offset = entryIndex * this.maxCouponsPerKey_;
      boolean wasFound = false;

      for(int i = 0; i < this.maxCouponsPerKey_; ++i) {
         if (this.couponsArr_[offset + i] == 0) {
            if (wasFound) {
               return (double)i;
            }

            this.couponsArr_[offset + i] = value;
            return (double)(i + 1);
         }

         if (this.couponsArr_[offset + i] == value) {
            wasFound = true;
         }
      }

      if (wasFound) {
         return (double)this.maxCouponsPerKey_;
      } else {
         return (double)(-this.maxCouponsPerKey_);
      }
   }

   double getEstimate(byte[] key) {
      int entryIndex = this.findKey(key);
      return entryIndex < 0 ? (double)0.0F : (double)this.getCouponCount(entryIndex);
   }

   double getUpperBound(byte[] key) {
      return this.getEstimate(key) * ((double)1.0F + RSE);
   }

   double getLowerBound(byte[] key) {
      return this.getEstimate(key) * ((double)1.0F - RSE);
   }

   int findKey(byte[] key) {
      long[] hash = MurmurHash3.hash(key, 1234567890L);
      int entryIndex = getIndex(hash[0], this.tableEntries_);
      int firstDeletedIndex = -1;
      int loopIndex = entryIndex;

      while(!isBitClear(this.stateArr_, entryIndex)) {
         if (this.couponsArr_[entryIndex * this.maxCouponsPerKey_] == 0) {
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
         if (isBitSet(this.stateArr_, entryIndex)) {
            this.clearCouponArea(entryIndex);
            --this.numDeletedKeys_;
         }

         if (this.numActiveKeys_ + this.numDeletedKeys_ + 1 > this.capacityEntries_) {
            this.resize();
            entryIndex = ~this.findKey(key);

            assert entryIndex >= 0;
         }

         System.arraycopy(key, 0, this.keysArr_, entryIndex * this.keySizeBytes_, this.keySizeBytes_);
         setBit(this.stateArr_, entryIndex);
         ++this.numActiveKeys_;
      }

      return entryIndex;
   }

   void deleteKey(int entryIndex) {
      this.couponsArr_[entryIndex * this.maxCouponsPerKey_] = 0;
      --this.numActiveKeys_;
      ++this.numDeletedKeys_;
      if (this.numActiveKeys_ > 157 && (double)this.numActiveKeys_ < (double)this.tableEntries_ * (double)0.5F) {
         this.resize();
      }

   }

   private int getCouponCount(int entryIndex) {
      int offset = entryIndex * this.maxCouponsPerKey_;

      for(int i = 0; i < this.maxCouponsPerKey_; ++i) {
         if (this.couponsArr_[offset + i] == 0) {
            return i;
         }
      }

      return this.maxCouponsPerKey_;
   }

   CouponsIterator getCouponsIterator(int entryIndex) {
      return new CouponsIterator(this.couponsArr_, entryIndex * this.maxCouponsPerKey_, this.maxCouponsPerKey_);
   }

   double getEntrySizeBytes() {
      return this.entrySizeBytes_;
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
      return (long)this.keysArr_.length + (long)this.couponsArr_.length * 2L + (long)this.stateArr_.length + 16L;
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
      return this.maxCouponsPerKey_;
   }

   private void resize() {
      byte[] oldKeysArr = this.keysArr_;
      short[] oldCouponsArr = this.couponsArr_;
      byte[] oldStateArr = this.stateArr_;
      int oldSizeKeys = this.tableEntries_;
      this.tableEntries_ = Math.max(nextPrime((int)((double)this.numActiveKeys_ / 0.6666666666666666)), 157);
      this.capacityEntries_ = (int)((double)this.tableEntries_ * (double)0.9375F);
      this.numActiveKeys_ = 0;
      this.numDeletedKeys_ = 0;
      this.entrySizeBytes_ = updateEntrySizeBytes(this.tableEntries_, this.keySizeBytes_, this.maxCouponsPerKey_);
      this.keysArr_ = new byte[this.tableEntries_ * this.keySizeBytes_];
      this.couponsArr_ = new short[this.tableEntries_ * this.maxCouponsPerKey_];
      this.stateArr_ = new byte[(int)Math.ceil((double)this.tableEntries_ / (double)8.0F)];

      for(int i = 0; i < oldSizeKeys; ++i) {
         if (isBitSet(oldStateArr, i) && oldCouponsArr[i * this.maxCouponsPerKey_] != 0) {
            byte[] key = Arrays.copyOfRange(oldKeysArr, i * this.keySizeBytes_, i * this.keySizeBytes_ + this.keySizeBytes_);
            int index = this.insertKey(key);
            System.arraycopy(oldCouponsArr, i * this.maxCouponsPerKey_, this.couponsArr_, index * this.maxCouponsPerKey_, this.maxCouponsPerKey_);
         }
      }

   }

   private int insertKey(byte[] key) {
      long[] hash = MurmurHash3.hash(key, 1234567890L);
      int entryIndex = getIndex(hash[0], this.tableEntries_);
      int loopIndex = entryIndex;

      while(!isBitClear(this.stateArr_, entryIndex)) {
         entryIndex = (entryIndex + getStride(hash[1], this.tableEntries_)) % this.tableEntries_;
         if (entryIndex == loopIndex) {
            throw new SketchesArgumentException("Key not found and no empty slots!");
         }
      }

      System.arraycopy(key, 0, this.keysArr_, entryIndex * this.keySizeBytes_, this.keySizeBytes_);
      setBit(this.stateArr_, entryIndex);
      ++this.numActiveKeys_;
      return entryIndex;
   }

   private void clearCouponArea(int entryIndex) {
      int couponAreaIndex = entryIndex * this.maxCouponsPerKey_;

      for(int i = 0; i < this.maxCouponsPerKey_; ++i) {
         this.couponsArr_[couponAreaIndex + i] = 0;
      }

   }

   private static final double updateEntrySizeBytes(int tableEntries, int keySizeBytes, int maxCouponsPerKey) {
      double byteFraction = Math.ceil((double)tableEntries / (double)8.0F) / (double)tableEntries;
      return (double)keySizeBytes + (double)maxCouponsPerKey * (double)2.0F + byteFraction;
   }
}

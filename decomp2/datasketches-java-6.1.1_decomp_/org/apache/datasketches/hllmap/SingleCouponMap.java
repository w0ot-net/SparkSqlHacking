package org.apache.datasketches.hllmap;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.hash.MurmurHash3;

final class SingleCouponMap extends Map {
   private static final double RSE = 0.408 / Math.sqrt((double)1024.0F);
   private int tableEntries_;
   private int capacityEntries_;
   private int curCountEntries_;
   private double entrySizeBytes_;
   private byte[] keysArr_;
   private short[] couponsArr_;
   private byte[] stateArr_;

   private SingleCouponMap(int keySizeBytes) {
      super(keySizeBytes);
   }

   static SingleCouponMap getInstance(int initialNumEntries, int keySizeBytes) {
      int tableEntries = nextPrime(initialNumEntries);
      SingleCouponMap map = new SingleCouponMap(keySizeBytes);
      map.tableEntries_ = tableEntries;
      map.capacityEntries_ = (int)((double)tableEntries * (double)0.9375F);
      map.curCountEntries_ = 0;
      map.entrySizeBytes_ = updateEntrySizeBytes(tableEntries, keySizeBytes);
      map.keysArr_ = new byte[tableEntries * map.keySizeBytes_];
      map.couponsArr_ = new short[tableEntries];
      map.stateArr_ = new byte[(int)Math.ceil((double)tableEntries / (double)8.0F)];
      return map;
   }

   double update(byte[] key, short coupon) {
      int entryIndex = this.findOrInsertKey(key);
      return this.update(entryIndex, coupon);
   }

   double update(int entryIndex, short coupon) {
      if (this.couponsArr_[entryIndex] == 0) {
         this.couponsArr_[entryIndex] = coupon;
         return (double)1.0F;
      } else if (this.isCoupon(entryIndex)) {
         return this.couponsArr_[entryIndex] == coupon ? (double)1.0F : (double)0.0F;
      } else {
         return (double)(-this.couponsArr_[entryIndex]);
      }
   }

   double getEstimate(byte[] key) {
      int entryIndex = this.findKey(key);
      if (entryIndex < 0) {
         return (double)0.0F;
      } else {
         return this.isCoupon(entryIndex) ? (double)1.0F : (double)(-this.getCoupon(entryIndex));
      }
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
      int stride = getStride(hash[1], this.tableEntries_);
      int loopIndex = entryIndex;

      while(this.couponsArr_[entryIndex] != 0) {
         if (Map.arraysEqual(key, 0, this.keysArr_, entryIndex * this.keySizeBytes_, this.keySizeBytes_)) {
            return entryIndex;
         }

         entryIndex = (entryIndex + stride) % this.tableEntries_;
         if (entryIndex == loopIndex) {
            throw new SketchesArgumentException("Key not found and no empty slots!");
         }
      }

      return ~entryIndex;
   }

   int findOrInsertKey(byte[] key) {
      int entryIndex = this.findKey(key);
      if (entryIndex < 0) {
         if (this.curCountEntries_ + 1 > this.capacityEntries_) {
            this.resize();
            entryIndex = this.findKey(key);

            assert entryIndex < 0;
         }

         entryIndex = ~entryIndex;
         System.arraycopy(key, 0, this.keysArr_, entryIndex * this.keySizeBytes_, this.keySizeBytes_);
         ++this.curCountEntries_;
      }

      return entryIndex;
   }

   CouponsIterator getCouponsIterator(int entryIndex) {
      return new CouponsIterator(this.couponsArr_, entryIndex, 1);
   }

   int getMaxCouponsPerEntry() {
      return 1;
   }

   int getCapacityCouponsPerEntry() {
      return 1;
   }

   int getActiveEntries() {
      return this.curCountEntries_;
   }

   int getDeletedEntries() {
      return 0;
   }

   boolean isCoupon(int entryIndex) {
      return !isBitSet(this.stateArr_, entryIndex);
   }

   short getCoupon(int entryIndex) {
      return this.couponsArr_[entryIndex];
   }

   void setCoupon(int entryIndex, short coupon, boolean isLevel) {
      this.couponsArr_[entryIndex] = coupon;
      if (isLevel) {
         setBit(this.stateArr_, entryIndex);
      } else {
         clearBit(this.stateArr_, entryIndex);
      }

   }

   void setLevel(int entryIndex, int level) {
      this.couponsArr_[entryIndex] = (short)level;
      setBit(this.stateArr_, entryIndex);
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
      return this.curCountEntries_;
   }

   long getMemoryUsageBytes() {
      long arrays = (long)this.keysArr_.length + (long)this.couponsArr_.length * 2L + (long)this.stateArr_.length;
      long other = 24L;
      return arrays + 24L;
   }

   private void resize() {
      byte[] oldKeysArr = this.keysArr_;
      short[] oldCouponsArr = this.couponsArr_;
      byte[] oldStateArr = this.stateArr_;
      int oldTableEntries = this.tableEntries_;
      this.tableEntries_ = nextPrime((int)((double)this.curCountEntries_ / 0.6666666666666666));
      this.capacityEntries_ = (int)((double)this.tableEntries_ * (double)0.9375F);
      this.keysArr_ = new byte[this.tableEntries_ * this.keySizeBytes_];
      this.couponsArr_ = new short[this.tableEntries_];
      this.stateArr_ = new byte[(int)Math.ceil((double)this.tableEntries_ / (double)8.0F)];
      this.entrySizeBytes_ = updateEntrySizeBytes(this.tableEntries_, this.keySizeBytes_);

      for(int i = 0; i < oldTableEntries; ++i) {
         if (oldCouponsArr[i] != 0) {
            byte[] key = Arrays.copyOfRange(oldKeysArr, i * this.keySizeBytes_, i * this.keySizeBytes_ + this.keySizeBytes_);
            this.insertEntry(key, oldCouponsArr[i], isBitSet(oldStateArr, i));
         }
      }

   }

   private void insertEntry(byte[] key, int coupon, boolean setStateOne) {
      long[] hash = MurmurHash3.hash(key, 1234567890L);
      int entryIndex = getIndex(hash[0], this.tableEntries_);
      int stride = getStride(hash[1], this.tableEntries_);
      int loopIndex = entryIndex;

      while(this.couponsArr_[entryIndex] != 0) {
         entryIndex = (entryIndex + stride) % this.tableEntries_;
         if (entryIndex == loopIndex) {
            throw new SketchesArgumentException("Key not found and no empty slots!");
         }
      }

      System.arraycopy(key, 0, this.keysArr_, entryIndex * this.keySizeBytes_, this.keySizeBytes_);
      this.setCoupon(entryIndex, (short)coupon, setStateOne);
   }

   private static final double updateEntrySizeBytes(int tableEntries, int keySizeBytes) {
      double byteFraction = Math.ceil((double)tableEntries / (double)8.0F) / (double)tableEntries;
      return (double)(keySizeBytes + 2) + byteFraction;
   }
}

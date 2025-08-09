package org.apache.datasketches.hllmap;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.hash.MurmurHash3;

final class HllMap extends Map {
   private static final double LOAD_FACTOR = (double)0.9375F;
   private static final int HLL_INIT_NUM_ENTRIES = 157;
   private static final int HLL_INIT_NUM_ENTRIES_ARR_SIZE = (int)Math.ceil((double)19.625F);
   private static final float HLL_RESIZE_FACTOR = 2.0F;
   private static final double RSE = Math.sqrt(Math.log((double)2.0F)) / (double)32.0F;
   private final int k_;
   private final int hllArrLongs_;
   private int tableEntries_;
   private int capacityEntries_;
   private int curCountEntries_;
   private float growthFactor_;
   private double entrySizeBytes_;
   private byte[] keysArr_;
   private long[] arrOfHllArr_;
   private double[] invPow2SumHiArr_;
   private double[] invPow2SumLoArr_;
   private double[] hipEstAccumArr_;
   private byte[] stateArr_;

   private HllMap(int keySizeBytes, int k) {
      super(keySizeBytes);
      this.k_ = k;
      this.hllArrLongs_ = k / 10 + 1;
   }

   static HllMap getInstance(int keySizeBytes, int k) {
      HllMap map = new HllMap(keySizeBytes, k);
      map.tableEntries_ = 157;
      map.capacityEntries_ = 147;
      map.curCountEntries_ = 0;
      map.growthFactor_ = 2.0F;
      map.entrySizeBytes_ = updateEntrySizeBytes(map.tableEntries_, keySizeBytes, map.hllArrLongs_);
      map.keysArr_ = new byte[157 * map.keySizeBytes_];
      map.arrOfHllArr_ = new long[157 * map.hllArrLongs_];
      map.invPow2SumHiArr_ = new double[157];
      map.invPow2SumLoArr_ = new double[157];
      map.hipEstAccumArr_ = new double[157];
      map.stateArr_ = new byte[HLL_INIT_NUM_ENTRIES_ARR_SIZE];
      return map;
   }

   double update(byte[] key, short coupon) {
      int entryIndex = this.findOrInsertKey(key);
      return this.update(entryIndex, coupon);
   }

   double update(int entryIndex, short coupon) {
      this.updateHll(entryIndex, coupon);
      return this.hipEstAccumArr_[entryIndex];
   }

   double getEstimate(byte[] key) {
      if (key == null) {
         return Double.NaN;
      } else {
         int entryIndex = this.findKey(key);
         return entryIndex < 0 ? (double)0.0F : this.hipEstAccumArr_[entryIndex];
      }
   }

   double getUpperBound(byte[] key) {
      return this.getEstimate(key) * ((double)1.0F + RSE);
   }

   double getLowerBound(byte[] key) {
      return this.getEstimate(key) * ((double)1.0F - RSE);
   }

   void updateEstimate(int entryIndex, double estimate) {
      this.hipEstAccumArr_[entryIndex] = estimate;
   }

   final int findKey(byte[] key) {
      int keyLen = key.length;
      long[] hash = MurmurHash3.hash(key, 1234567890L);
      int entryIndex = getIndex(hash[0], this.tableEntries_);
      int stride = getStride(hash[1], this.tableEntries_);
      int loopIndex = entryIndex;

      while(!isBitClear(this.stateArr_, entryIndex)) {
         if (arraysEqual(key, 0, this.keysArr_, entryIndex * keyLen, keyLen)) {
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
         entryIndex = ~entryIndex;
         System.arraycopy(key, 0, this.keysArr_, entryIndex * this.keySizeBytes_, this.keySizeBytes_);
         setBit(this.stateArr_, entryIndex);
         this.invPow2SumHiArr_[entryIndex] = (double)this.k_;
         this.invPow2SumLoArr_[entryIndex] = (double)0.0F;
         this.hipEstAccumArr_[entryIndex] = (double)0.0F;
         ++this.curCountEntries_;
         if (this.curCountEntries_ > this.capacityEntries_) {
            this.resize();
            entryIndex = this.findKey(key);

            assert entryIndex >= 0;
         }
      }

      return entryIndex;
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
      long arrays = (long)this.keysArr_.length + (long)this.arrOfHllArr_.length * 8L + (long)this.invPow2SumLoArr_.length * 8L + (long)this.invPow2SumHiArr_.length * 8L + (long)this.hipEstAccumArr_.length * 8L + (long)this.stateArr_.length;
      long other = 32L;
      return arrays + 32L;
   }

   CouponsIterator getCouponsIterator(int index) {
      return null;
   }

   int getMaxCouponsPerEntry() {
      return 0;
   }

   int getCapacityCouponsPerEntry() {
      return 0;
   }

   int getActiveEntries() {
      return this.curCountEntries_;
   }

   int getDeletedEntries() {
      return 0;
   }

   private static final int findEmpty(byte[] key, int tableEntries, byte[] stateArr) {
      long[] hash = MurmurHash3.hash(key, 1234567890L);
      int entryIndex = getIndex(hash[0], tableEntries);
      int stride = getStride(hash[1], tableEntries);
      int loopIndex = entryIndex;

      while(!isBitClear(stateArr, entryIndex)) {
         entryIndex = (entryIndex + stride) % tableEntries;
         if (entryIndex == loopIndex) {
            throw new SketchesArgumentException("No empty slots.");
         }
      }

      return entryIndex;
   }

   @SuppressFBWarnings(
      value = {"IM_MULTIPLYING_RESULT_OF_IREM"},
      justification = "False Positive"
   )
   private final boolean updateHll(int entryIndex, int coupon) {
      int newValue = coupon16Value(coupon);
      int hllIdx = coupon & this.k_ - 1;
      int longIdx = hllIdx / 10;
      int shift = hllIdx % 10 * 6 & 63;
      long hllLong = this.arrOfHllArr_[entryIndex * this.hllArrLongs_ + longIdx];
      int oldValue = (int)(hllLong >>> shift) & 63;
      if (newValue <= oldValue) {
         return false;
      } else {
         double invPow2Sum = this.invPow2SumHiArr_[entryIndex] + this.invPow2SumLoArr_[entryIndex];
         double oneOverQ = (double)this.k_ / invPow2Sum;
         double[] var10000 = this.hipEstAccumArr_;
         var10000[entryIndex] += oneOverQ;
         if (oldValue < 32) {
            var10000 = this.invPow2SumHiArr_;
            var10000[entryIndex] -= Util.invPow2(oldValue);
         } else {
            var10000 = this.invPow2SumLoArr_;
            var10000[entryIndex] -= Util.invPow2(oldValue);
         }

         if (newValue < 32) {
            var10000 = this.invPow2SumHiArr_;
            var10000[entryIndex] += Util.invPow2(newValue);
         } else {
            var10000 = this.invPow2SumLoArr_;
            var10000[entryIndex] += Util.invPow2(newValue);
         }

         hllLong &= ~(63L << shift);
         hllLong |= (long)newValue << shift;
         this.arrOfHllArr_[entryIndex * this.hllArrLongs_ + longIdx] = hllLong;
         return true;
      }
   }

   private final void resize() {
      int newTableEntries = nextPrime((int)((float)this.tableEntries_ * this.growthFactor_));
      int newCapacityEntries = (int)((double)newTableEntries * (double)0.9375F);
      byte[] newKeysArr = new byte[newTableEntries * this.keySizeBytes_];
      long[] newArrOfHllArr = new long[newTableEntries * this.hllArrLongs_];
      double[] newInvPow2Sum1 = new double[newTableEntries];
      double[] newInvPow2Sum2 = new double[newTableEntries];
      double[] newHipEstAccum = new double[newTableEntries];
      byte[] newStateArr = new byte[(int)Math.ceil((double)newTableEntries / (double)8.0F)];

      for(int oldIndex = 0; oldIndex < this.tableEntries_; ++oldIndex) {
         if (!isBitClear(this.stateArr_, oldIndex)) {
            byte[] key = Arrays.copyOfRange(this.keysArr_, oldIndex * this.keySizeBytes_, (oldIndex + 1) * this.keySizeBytes_);
            int newIndex = findEmpty(key, newTableEntries, newStateArr);
            System.arraycopy(key, 0, newKeysArr, newIndex * this.keySizeBytes_, this.keySizeBytes_);
            System.arraycopy(this.arrOfHllArr_, oldIndex * this.hllArrLongs_, newArrOfHllArr, newIndex * this.hllArrLongs_, this.hllArrLongs_);
            newInvPow2Sum1[newIndex] = this.invPow2SumHiArr_[oldIndex];
            newInvPow2Sum2[newIndex] = this.invPow2SumLoArr_[oldIndex];
            newHipEstAccum[newIndex] = this.hipEstAccumArr_[oldIndex];
            setBit(newStateArr, newIndex);
         }
      }

      this.tableEntries_ = newTableEntries;
      this.capacityEntries_ = newCapacityEntries;
      this.entrySizeBytes_ = updateEntrySizeBytes(this.tableEntries_, this.keySizeBytes_, this.hllArrLongs_);
      this.keysArr_ = newKeysArr;
      this.arrOfHllArr_ = newArrOfHllArr;
      this.invPow2SumHiArr_ = newInvPow2Sum1;
      this.invPow2SumLoArr_ = newInvPow2Sum2;
      this.hipEstAccumArr_ = newHipEstAccum;
      this.stateArr_ = newStateArr;
   }

   private static final double updateEntrySizeBytes(int tableEntries, int keySizeBytes, int hllArrLongs) {
      double byteFraction = Math.ceil((double)tableEntries / (double)8.0F) / (double)tableEntries;
      return (double)keySizeBytes + (double)hllArrLongs * (double)8.0F + (double)24.0F + byteFraction;
   }
}

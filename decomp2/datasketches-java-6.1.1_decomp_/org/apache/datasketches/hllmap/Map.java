package org.apache.datasketches.hllmap;

import java.math.BigInteger;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.hash.MurmurHash3;

abstract class Map {
   static final long SEED = 1234567890L;
   static final int SIX_BIT_MASK = 63;
   static final int TEN_BIT_MASK = 1023;
   static final int COUPON_MAP_MIN_NUM_ENTRIES = 157;
   static final double COUPON_MAP_SHRINK_TRIGGER_FACTOR = (double)0.5F;
   static final double COUPON_MAP_GROW_TRIGGER_FACTOR = (double)0.9375F;
   static final double COUPON_MAP_TARGET_FILL_FACTOR = 0.6666666666666666;
   static final int COUPON_MAP_MIN_NUM_ENTRIES_ARR_SIZE = (int)Math.ceil((double)19.625F);
   final int keySizeBytes_;

   Map(int keySizeBytes) {
      this.keySizeBytes_ = keySizeBytes;
   }

   abstract double update(byte[] var1, short var2);

   abstract double update(int var1, short var2);

   abstract double getEstimate(byte[] var1);

   void updateEstimate(int index, double estimate) {
   }

   abstract double getUpperBound(byte[] var1);

   abstract double getLowerBound(byte[] var1);

   abstract int findKey(byte[] var1);

   abstract int findOrInsertKey(byte[] var1);

   abstract CouponsIterator getCouponsIterator(int var1);

   abstract int getMaxCouponsPerEntry();

   abstract int getCapacityCouponsPerEntry();

   abstract int getActiveEntries();

   abstract int getDeletedEntries();

   abstract double getEntrySizeBytes();

   abstract int getTableEntries();

   abstract int getCapacityEntries();

   abstract int getCurrentCountEntries();

   abstract long getMemoryUsageBytes();

   int getKeySizeBytes() {
      return this.keySizeBytes_;
   }

   void deleteKey(int index) {
   }

   static final boolean arraysEqual(byte[] a, int offsetA, byte[] b, int offsetB, int length) {
      for(int i = 0; i < length; ++i) {
         if (a[i + offsetA] != b[i + offsetB]) {
            return false;
         }
      }

      return true;
   }

   static final int coupon16(byte[] identifier) {
      long[] hash = MurmurHash3.hash(identifier, 1234567890L);
      int hllIdx = (int)((hash[0] >>> 1) % 1024L & 1023L);
      int lz = Long.numberOfLeadingZeros(hash[1]);
      int value = (lz > 62 ? 62 : lz) + 1;
      return value << 10 | hllIdx;
   }

   static final int coupon16Value(int coupon) {
      return coupon >>> 10 & 63;
   }

   static final int getIndex(long hash, int tableEntries) {
      return (int)((hash >>> 1) % (long)tableEntries);
   }

   static final int getStride(long hash, int tableEntries) {
      return (int)((hash >>> 1) % ((long)tableEntries - 2L) + 1L);
   }

   static boolean isBitSet(byte[] byteArr, int bitIndex) {
      int byteIndex = bitIndex / 8;
      int mask = 1 << bitIndex % 8;
      return (byteArr[byteIndex] & mask) > 0;
   }

   static boolean isBitClear(byte[] byteArr, int bitIndex) {
      int byteIndex = bitIndex / 8;
      int mask = 1 << bitIndex % 8;
      return (byteArr[byteIndex] & mask) == 0;
   }

   static void clearBit(byte[] byteArr, int index) {
      int byteIndex = index / 8;
      int mask = 1 << index % 8;
      byteArr[byteIndex] = (byte)(byteArr[byteIndex] & ~mask);
   }

   static void setBit(byte[] bits, int index) {
      int byteIndex = index / 8;
      int mask = 1 << index % 8;
      bits[byteIndex] = (byte)(bits[byteIndex] | mask);
   }

   static int nextPrime(int target) {
      return BigInteger.valueOf((long)target).nextProbablePrime().intValueExact();
   }

   static String fmtLong(long value) {
      return String.format("%,d", value);
   }

   static String fmtDouble(double value) {
      return String.format("%,.3f", value);
   }

   public String toString() {
      String mcpe = fmtLong((long)this.getMaxCouponsPerEntry());
      String ccpe = fmtLong((long)this.getCapacityCouponsPerEntry());
      String te = fmtLong((long)this.getTableEntries());
      String ce = fmtLong((long)this.getCapacityEntries());
      String cce = fmtLong((long)this.getCurrentCountEntries());
      String ae = fmtLong((long)this.getActiveEntries());
      String de = fmtLong((long)this.getDeletedEntries());
      String esb = fmtDouble(this.getEntrySizeBytes());
      String mub = fmtLong(this.getMemoryUsageBytes());
      StringBuilder sb = new StringBuilder();
      String thisSimpleName = this.getClass().getSimpleName();
      sb.append("### ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
      sb.append("    Max Coupons Per Entry     : ").append(mcpe).append(Util.LS);
      sb.append("    Capacity Coupons Per Entry: ").append(ccpe).append(Util.LS);
      sb.append("    Table Entries             : ").append(te).append(Util.LS);
      sb.append("    Capacity Entries          : ").append(ce).append(Util.LS);
      sb.append("    Current Count Entries     : ").append(cce).append(Util.LS);
      sb.append("      Active Entries          : ").append(ae).append(Util.LS);
      sb.append("      Deleted Entries         : ").append(de).append(Util.LS);
      sb.append("    Entry Size Bytes          : ").append(esb).append(Util.LS);
      sb.append("    Memory Usage Bytes        : ").append(mub).append(Util.LS);
      sb.append("### END SKETCH SUMMARY").append(Util.LS);
      return sb.toString();
   }
}

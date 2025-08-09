package org.apache.datasketches.hll;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.datasketches.hash.MurmurHash3;
import org.apache.datasketches.memory.Memory;

abstract class BaseHllSketch {
   abstract void couponUpdate(int var1);

   public abstract int getCompactSerializationBytes();

   public abstract double getCompositeEstimate();

   abstract CurMode getCurMode();

   public abstract double getEstimate();

   public abstract TgtHllType getTgtHllType();

   public abstract int getLgConfigK();

   public abstract double getLowerBound(int var1);

   public static final int getSerializationVersion() {
      return 1;
   }

   public static final int getSerializationVersion(Memory mem) {
      return mem.getByte((long)PreambleUtil.SER_VER_BYTE) & 255;
   }

   public static double getRelErr(boolean upperBound, boolean oooFlag, int lgConfigK, int numStdDev) {
      HllUtil.checkLgK(lgConfigK);
      if (lgConfigK > 12) {
         double rseFactor = oooFlag ? HllUtil.HLL_NON_HIP_RSE_FACTOR : HllUtil.HLL_HIP_RSE_FACTOR;
         int configK = 1 << lgConfigK;
         return (double)numStdDev * rseFactor / Math.sqrt((double)configK);
      } else {
         return Math.abs(RelativeErrorTables.getRelErr(upperBound, oooFlag, lgConfigK, numStdDev));
      }
   }

   public abstract int getUpdatableSerializationBytes();

   public abstract double getUpperBound(int var1);

   public abstract boolean isEmpty();

   public abstract boolean isCompact();

   public boolean isEstimationMode() {
      return true;
   }

   public abstract boolean isMemory();

   public abstract boolean isOffHeap();

   abstract boolean isOutOfOrder();

   public abstract boolean isSameResource(Memory var1);

   public abstract void reset();

   public abstract byte[] toCompactByteArray();

   public abstract byte[] toUpdatableByteArray();

   public String toString() {
      return this.toString(true, false, false, false);
   }

   public String toString(boolean summary, boolean detail, boolean auxDetail) {
      return this.toString(summary, detail, auxDetail, false);
   }

   public abstract String toString(boolean var1, boolean var2, boolean var3, boolean var4);

   public void update(long datum) {
      long[] data = new long[]{datum};
      this.couponUpdate(coupon(MurmurHash3.hash(data, 9001L)));
   }

   public void update(double datum) {
      double d = datum == (double)0.0F ? (double)0.0F : datum;
      long[] data = new long[]{Double.doubleToLongBits(d)};
      this.couponUpdate(coupon(MurmurHash3.hash(data, 9001L)));
   }

   public void update(String datum) {
      if (datum != null && !datum.isEmpty()) {
         byte[] data = datum.getBytes(StandardCharsets.UTF_8);
         this.couponUpdate(coupon(MurmurHash3.hash(data, 9001L)));
      }
   }

   public void update(ByteBuffer data) {
      if (data != null && data.remaining() != 0) {
         this.couponUpdate(coupon(MurmurHash3.hash(data, 9001L)));
      }
   }

   public void update(byte[] data) {
      if (data != null && data.length != 0) {
         this.couponUpdate(coupon(MurmurHash3.hash(data, 9001L)));
      }
   }

   public void update(char[] data) {
      if (data != null && data.length != 0) {
         this.couponUpdate(coupon(MurmurHash3.hash(data, 9001L)));
      }
   }

   public void update(int[] data) {
      if (data != null && data.length != 0) {
         this.couponUpdate(coupon(MurmurHash3.hash(data, 9001L)));
      }
   }

   public void update(long[] data) {
      if (data != null && data.length != 0) {
         this.couponUpdate(coupon(MurmurHash3.hash(data, 9001L)));
      }
   }

   private static final int coupon(long[] hash) {
      int addr26 = (int)(hash[0] & 67108863L);
      int lz = Long.numberOfLeadingZeros(hash[1]);
      int value = (lz > 62 ? 62 : lz) + 1;
      return value << 26 | addr26;
   }
}

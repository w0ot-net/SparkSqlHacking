package org.apache.datasketches.cpc;

import java.util.Arrays;
import org.apache.datasketches.hash.MurmurHash3;

class BitMatrix {
   private final int lgK;
   private final long seed;
   private long numCoupons;
   private long[] bitMatrix;
   private boolean numCouponsInvalid;

   BitMatrix(int lgK) {
      this(lgK, 9001L);
   }

   BitMatrix(int lgK, long seed) {
      this.lgK = lgK;
      this.seed = seed;
      this.bitMatrix = new long[1 << lgK];
      this.numCoupons = 0L;
      this.numCouponsInvalid = false;
   }

   void reset() {
      Arrays.fill(this.bitMatrix, 0L);
      this.numCoupons = 0L;
      this.numCouponsInvalid = false;
   }

   long getNumCoupons() {
      if (this.numCouponsInvalid) {
         this.numCoupons = countCoupons(this.bitMatrix);
         this.numCouponsInvalid = false;
      }

      return this.numCoupons;
   }

   long[] getMatrix() {
      return this.bitMatrix;
   }

   public void update(long datum) {
      long[] data = new long[]{datum};
      long[] harr = MurmurHash3.hash(data, this.seed);
      this.hashUpdate(harr[0], harr[1]);
   }

   private void hashUpdate(long hash0, long hash1) {
      int col = Long.numberOfLeadingZeros(hash1);
      if (col > 63) {
         col = 63;
      }

      long kMask = (1L << this.lgK) - 1L;
      int row = (int)(hash0 & kMask);
      int rowCol = row << 6 | col;
      if (rowCol == -1) {
         row ^= 1;
      }

      long oldPattern = this.bitMatrix[row];
      long newPattern = oldPattern | 1L << col;
      if (newPattern != oldPattern) {
         ++this.numCoupons;
         this.bitMatrix[row] = newPattern;
      }

   }

   static long countCoupons(long[] bitMatrix) {
      long count = 0L;
      int len = bitMatrix.length;

      for(int i = 0; i < len; ++i) {
         count += (long)Long.bitCount(bitMatrix[i]);
      }

      return count;
   }
}

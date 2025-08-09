package com.clearspring.analytics.stream.cardinality;

import com.clearspring.analytics.hash.MurmurHash;
import com.clearspring.analytics.util.IBuilder;
import java.util.Arrays;

public class LogLog implements ICardinality {
   protected static final double[] mAlpha = new double[]{(double)0.0F, 0.44567926005415, 1.2480639342271, 2.8391255240079, 6.0165231584809, 12.369319965552, 25.073991603111, 50.482891762408, 101.30047482584, 202.935533381, 406.20559696699, 812.74569744189, 1625.8258850594, 3251.9862536323, 6504.306987448, 13008.948453415, 26018.231384516, 52036.797246302, 104073.92896967, 208148.19241629, 416296.71930949, 832593.77309585, 1665187.8806686, 3330376.095814, 6660752.5261049, 1.3321505386687E7, 2.664301110785E7, 5.3286022550177E7, 1.0657204543483E8, 2.1314409120414E8, 4.2628818274275E8, 8.5257636581999E8};
   protected final int k;
   protected int m;
   protected double Ca;
   protected byte[] M;
   protected int Rsum = 0;

   public LogLog(int k) {
      if (k >= mAlpha.length - 1) {
         throw new IllegalArgumentException(String.format("Max k (%d) exceeded: k=%d", mAlpha.length - 1, k));
      } else {
         this.k = k;
         this.m = 1 << k;
         this.Ca = mAlpha[k];
         this.M = new byte[this.m];
      }
   }

   public LogLog(byte[] M) {
      this.M = M;
      this.m = M.length;
      this.k = Integer.numberOfTrailingZeros(this.m);

      assert this.m == 1 << this.k : "Invalid array size: M.length must be a power of 2";

      this.Ca = mAlpha[this.k];

      for(byte b : M) {
         this.Rsum += b;
      }

   }

   public byte[] getBytes() {
      return this.M;
   }

   public int sizeof() {
      return this.m;
   }

   public long cardinality() {
      double Ravg = (double)this.Rsum / (double)this.m;
      return (long)(this.Ca * Math.pow((double)2.0F, Ravg));
   }

   public boolean offerHashed(long hashedLong) {
      throw new UnsupportedOperationException();
   }

   public boolean offerHashed(int hashedInt) {
      boolean modified = false;
      int j = hashedInt >>> 32 - this.k;
      byte r = (byte)(Integer.numberOfLeadingZeros(hashedInt << this.k | 1 << this.k - 1) + 1);
      if (this.M[j] < r) {
         this.Rsum += r - this.M[j];
         this.M[j] = r;
         modified = true;
      }

      return modified;
   }

   public boolean offer(Object o) {
      int x = MurmurHash.hash(o);
      return this.offerHashed(x);
   }

   protected static int rho(int x, int k) {
      return Integer.numberOfLeadingZeros(x << k | 1 << k - 1) + 1;
   }

   public ICardinality merge(ICardinality... estimators) throws LogLogMergeException {
      if (estimators == null) {
         return new LogLog(this.M);
      } else {
         byte[] mergedBytes = Arrays.copyOf(this.M, this.M.length);

         for(ICardinality estimator : estimators) {
            if (!this.getClass().isInstance(estimator)) {
               throw new LogLogMergeException("Cannot merge estimators of different class");
            }

            if (estimator.sizeof() != this.sizeof()) {
               throw new LogLogMergeException("Cannot merge estimators of different sizes");
            }

            LogLog ll = (LogLog)estimator;

            for(int i = 0; i < mergedBytes.length; ++i) {
               mergedBytes[i] = (byte)Math.max(mergedBytes[i], ll.M[i]);
            }
         }

         return new LogLog(mergedBytes);
      }
   }

   public static LogLog mergeEstimators(LogLog... estimators) throws LogLogMergeException {
      return estimators != null && estimators.length != 0 ? (LogLog)estimators[0].merge((ICardinality[])Arrays.copyOfRange(estimators, 1, estimators.length)) : null;
   }

   protected static class LogLogMergeException extends CardinalityMergeException {
      public LogLogMergeException(String message) {
         super(message);
      }
   }

   public static class Builder implements IBuilder {
      protected final int k;

      public Builder() {
         this(16);
      }

      public Builder(int k) {
         this.k = k;
      }

      public LogLog build() {
         return new LogLog(this.k);
      }

      public int sizeof() {
         return 1 << this.k;
      }
   }
}

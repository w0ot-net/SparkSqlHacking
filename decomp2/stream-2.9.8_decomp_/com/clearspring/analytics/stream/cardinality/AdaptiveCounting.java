package com.clearspring.analytics.stream.cardinality;

import com.clearspring.analytics.hash.Lookup3Hash;
import com.clearspring.analytics.util.IBuilder;
import java.io.Serializable;
import java.util.Arrays;

public class AdaptiveCounting extends LogLog {
   protected int b_e;
   protected final double B_s = 0.051;

   public AdaptiveCounting(int k) {
      super(k);
      this.b_e = this.m;
   }

   public AdaptiveCounting(byte[] M) {
      super(M);

      for(byte b : M) {
         if (b == 0) {
            ++this.b_e;
         }
      }

   }

   public boolean offer(Object o) {
      boolean modified = false;
      long x = Lookup3Hash.lookup3ycs64(o.toString());
      int j = (int)(x >>> 64 - this.k);
      byte r = (byte)(Long.numberOfLeadingZeros(x << this.k | (long)(1 << this.k - 1)) + 1);
      if (this.M[j] < r) {
         this.Rsum += r - this.M[j];
         if (this.M[j] == 0) {
            --this.b_e;
         }

         this.M[j] = r;
         modified = true;
      }

      return modified;
   }

   public long cardinality() {
      double B = (double)this.b_e / (double)this.m;
      return B >= 0.051 ? Math.round((double)(-this.m) * Math.log(B)) : super.cardinality();
   }

   protected static byte rho(long x, int k) {
      return (byte)(Long.numberOfLeadingZeros(x << k | (long)(1 << k - 1)) + 1);
   }

   public ICardinality merge(ICardinality... estimators) throws LogLog.LogLogMergeException {
      LogLog res = (LogLog)super.merge(estimators);
      return new AdaptiveCounting(res.M);
   }

   public static AdaptiveCounting mergeEstimators(LogLog... estimators) throws LogLog.LogLogMergeException {
      return estimators != null && estimators.length != 0 ? (AdaptiveCounting)estimators[0].merge((ICardinality[])Arrays.copyOfRange(estimators, 1, estimators.length)) : null;
   }

   public static class Builder implements IBuilder, Serializable {
      private static final long serialVersionUID = 2205437102378081334L;
      protected final int k;

      public Builder() {
         this(16);
      }

      public Builder(int k) {
         this.k = k;
      }

      public AdaptiveCounting build() {
         return new AdaptiveCounting(this.k);
      }

      public int sizeof() {
         return 1 << this.k;
      }

      public static IBuilder obyCount(long maxCardinality) {
         if (maxCardinality <= 0L) {
            throw new IllegalArgumentException("maxCardinality (" + maxCardinality + ") must be a positive integer");
         } else {
            return (IBuilder)(maxCardinality < 4250000L ? LinearCounting.Builder.onePercentError((int)maxCardinality) : new Builder(16));
         }
      }
   }
}

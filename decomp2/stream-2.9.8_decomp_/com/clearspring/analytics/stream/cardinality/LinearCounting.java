package com.clearspring.analytics.stream.cardinality;

import com.clearspring.analytics.hash.MurmurHash;
import com.clearspring.analytics.util.IBuilder;
import java.io.Serializable;
import java.util.Arrays;

public class LinearCounting implements ICardinality {
   protected byte[] map;
   protected final int length;
   protected int count;

   public LinearCounting(int size) {
      this.length = 8 * size;
      this.count = this.length;
      this.map = new byte[size];
   }

   public LinearCounting(byte[] map) {
      this.map = map;
      this.length = 8 * map.length;
      this.count = this.computeCount();
   }

   public long cardinality() {
      return Math.round((double)this.length * Math.log((double)this.length / (double)this.count));
   }

   public byte[] getBytes() {
      return this.map;
   }

   public boolean offerHashed(long hashedLong) {
      throw new UnsupportedOperationException();
   }

   public boolean offerHashed(int hashedInt) {
      throw new UnsupportedOperationException();
   }

   public boolean offer(Object o) {
      boolean modified = false;
      long hash = (long)MurmurHash.hash(o);
      int bit = (int)((hash & 4294967295L) % (long)this.length);
      int i = bit / 8;
      byte b = this.map[i];
      byte mask = (byte)(1 << bit % 8);
      if ((mask & b) == 0) {
         this.map[i] = (byte)(b | mask);
         --this.count;
         modified = true;
      }

      return modified;
   }

   public int sizeof() {
      return this.map.length;
   }

   protected int computeCount() {
      int c = 0;

      for(byte b : this.map) {
         c += Integer.bitCount(b & 255);
      }

      return this.length - c;
   }

   public double getUtilization() {
      return (double)(this.length - this.count) / (double)this.length;
   }

   public int getCount() {
      return this.count;
   }

   public boolean isSaturated() {
      return this.count == 0;
   }

   protected String mapAsBitString() {
      StringBuilder sb = new StringBuilder();

      for(byte b : this.map) {
         String bits = Integer.toBinaryString(b);

         for(int i = 0; i < 8 - bits.length(); ++i) {
            sb.append('0');
         }

         sb.append(bits);
      }

      return sb.toString();
   }

   public ICardinality merge(ICardinality... estimators) throws LinearCountingMergeException {
      if (estimators == null) {
         return new LinearCounting(this.map);
      } else {
         LinearCounting[] lcs = (LinearCounting[])Arrays.copyOf(estimators, estimators.length + 1, LinearCounting[].class);
         lcs[lcs.length - 1] = this;
         return mergeEstimators(lcs);
      }
   }

   public static LinearCounting mergeEstimators(LinearCounting... estimators) throws LinearCountingMergeException {
      LinearCounting merged = null;
      if (estimators != null && estimators.length > 0) {
         int size = estimators[0].map.length;
         byte[] mergedBytes = new byte[size];

         for(LinearCounting estimator : estimators) {
            if (estimator.map.length != size) {
               throw new LinearCountingMergeException("Cannot merge estimators of different sizes");
            }

            for(int b = 0; b < size; ++b) {
               mergedBytes[b] |= estimator.map[b];
            }
         }

         merged = new LinearCounting(mergedBytes);
      }

      return merged;
   }

   protected static class LinearCountingMergeException extends CardinalityMergeException {
      public LinearCountingMergeException(String message) {
         super(message);
      }
   }

   public static class Builder implements IBuilder, Serializable {
      private static final long serialVersionUID = -4245416224034648428L;
      protected static final int[] onePercentErrorLength = new int[]{5034, 5067, 5100, 5133, 5166, 5199, 5231, 5264, 5296, 5329, 5647, 5957, 6260, 6556, 6847, 7132, 7412, 7688, 7960, 10506, 12839, 15036, 17134, 19156, 21117, 23029, 24897, 26729, 43710, 59264, 73999, 88175, 101932, 115359, 128514, 141441, 154171, 274328, 386798, 494794, 599692, 702246, 802931, 902069, 999894, 1096582};
      protected final int size;

      public Builder() {
         this(65536);
      }

      public Builder(int size) {
         this.size = size;
      }

      public LinearCounting build() {
         return new LinearCounting(this.size);
      }

      public int sizeof() {
         return this.size;
      }

      public static Builder onePercentError(int maxCardinality) {
         if (maxCardinality <= 0) {
            throw new IllegalArgumentException("maxCardinality (" + maxCardinality + ") must be a positive integer");
         } else {
            int length = -1;
            if (maxCardinality < 100) {
               length = onePercentErrorLength[0];
            } else if (maxCardinality < 10000000) {
               int logscale = (int)Math.log10((double)maxCardinality);
               int scaleValue = (int)Math.pow((double)10.0F, (double)logscale);
               int scaleIndex = maxCardinality / scaleValue;
               int index = 9 * (logscale - 2) + (scaleIndex - 1);
               int lowerBound = scaleValue * scaleIndex;
               length = lerp(lowerBound, onePercentErrorLength[index], lowerBound + scaleValue, onePercentErrorLength[index + 1], maxCardinality);
            } else if (maxCardinality < 50000000) {
               length = lerp(10000000, 1096582, 50000000, 4584297, maxCardinality);
            } else if (maxCardinality < 100000000) {
               length = lerp(50000000, 4584297, 100000000, 8571013, maxCardinality);
            } else if (maxCardinality <= 120000000) {
               length = lerp(100000000, 8571013, 120000000, 10112529, maxCardinality);
            } else {
               length = maxCardinality / 12;
            }

            int sz = (int)Math.ceil((double)length / (double)8.0F);
            return new Builder(sz);
         }
      }

      public static Builder withError(double eps, int maxCardinality) {
         int sz = computeRequiredBitMaskLength((double)maxCardinality, eps);
         return new Builder((int)Math.ceil((double)sz / (double)8.0F));
      }

      private static int computeRequiredBitMaskLength(double n, double eps) {
         if (!(eps >= (double)1.0F) && !(eps <= (double)0.0F)) {
            if (n <= (double)0.0F) {
               throw new IllegalArgumentException("Cardinality should be positive");
            } else {
               int fromM = 1;
               int toM = 100000000;

               int m;
               double eq;
               do {
                  m = (toM + fromM) / 2;
                  eq = precisionInequalityRV(n / (double)m, eps);
                  if ((double)m > eq) {
                     toM = m;
                  } else {
                     fromM = m + 1;
                  }
               } while(toM > fromM);

               return (double)m > eq ? m : m + 1;
            }
         } else {
            throw new IllegalArgumentException("Epsilon should be in (0, 1) range");
         }
      }

      private static double precisionInequalityRV(double t, double eps) {
         return Math.max((double)1.0F / Math.pow(eps * t, (double)2.0F), (double)5.0F) * (Math.exp(t) - t - (double)1.0F);
      }

      protected static int lerp(int x0, int y0, int x1, int y1, int x) {
         return (int)Math.ceil((double)y0 + (double)(x - x0) * (double)(y1 - y0) / (double)(x1 - x0));
      }
   }
}

package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.KthSelector;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.MedianOf3PivotingStrategy;
import org.apache.commons.math3.util.PivotingStrategyInterface;
import org.apache.commons.math3.util.Precision;

public class Percentile extends AbstractUnivariateStatistic implements Serializable {
   private static final long serialVersionUID = -8091216485095130416L;
   private static final int MAX_CACHED_LEVELS = 10;
   private static final int PIVOTS_HEAP_LENGTH = 512;
   private final KthSelector kthSelector;
   private final EstimationType estimationType;
   private final NaNStrategy nanStrategy;
   private double quantile;
   private int[] cachedPivots;

   public Percentile() {
      this((double)50.0F);
   }

   public Percentile(double quantile) throws MathIllegalArgumentException {
      this(quantile, Percentile.EstimationType.LEGACY, NaNStrategy.REMOVED, new KthSelector(new MedianOf3PivotingStrategy()));
   }

   public Percentile(Percentile original) throws NullArgumentException {
      MathUtils.checkNotNull(original);
      this.estimationType = original.getEstimationType();
      this.nanStrategy = original.getNaNStrategy();
      this.kthSelector = original.getKthSelector();
      this.setData(original.getDataRef());
      if (original.cachedPivots != null) {
         System.arraycopy(original.cachedPivots, 0, this.cachedPivots, 0, original.cachedPivots.length);
      }

      this.setQuantile(original.quantile);
   }

   protected Percentile(double quantile, EstimationType estimationType, NaNStrategy nanStrategy, KthSelector kthSelector) throws MathIllegalArgumentException {
      this.setQuantile(quantile);
      this.cachedPivots = null;
      MathUtils.checkNotNull(estimationType);
      MathUtils.checkNotNull(nanStrategy);
      MathUtils.checkNotNull(kthSelector);
      this.estimationType = estimationType;
      this.nanStrategy = nanStrategy;
      this.kthSelector = kthSelector;
   }

   public void setData(double[] values) {
      if (values == null) {
         this.cachedPivots = null;
      } else {
         this.cachedPivots = new int[512];
         Arrays.fill(this.cachedPivots, -1);
      }

      super.setData(values);
   }

   public void setData(double[] values, int begin, int length) throws MathIllegalArgumentException {
      if (values == null) {
         this.cachedPivots = null;
      } else {
         this.cachedPivots = new int[512];
         Arrays.fill(this.cachedPivots, -1);
      }

      super.setData(values, begin, length);
   }

   public double evaluate(double p) throws MathIllegalArgumentException {
      return this.evaluate(this.getDataRef(), p);
   }

   public double evaluate(double[] values, double p) throws MathIllegalArgumentException {
      this.test(values, 0, 0);
      return this.evaluate(values, 0, values.length, p);
   }

   public double evaluate(double[] values, int start, int length) throws MathIllegalArgumentException {
      return this.evaluate(values, start, length, this.quantile);
   }

   public double evaluate(double[] values, int begin, int length, double p) throws MathIllegalArgumentException {
      this.test(values, begin, length);
      if (!(p > (double)100.0F) && !(p <= (double)0.0F)) {
         if (length == 0) {
            return Double.NaN;
         } else if (length == 1) {
            return values[begin];
         } else {
            double[] work = this.getWorkArray(values, begin, length);
            int[] pivotsHeap = this.getPivots(values);
            return work.length == 0 ? Double.NaN : this.estimationType.evaluate(work, pivotsHeap, p, this.kthSelector);
         }
      } else {
         throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, p, 0, 100);
      }
   }

   /** @deprecated */
   @Deprecated
   int medianOf3(double[] work, int begin, int end) {
      return (new MedianOf3PivotingStrategy()).pivotIndex(work, begin, end);
   }

   public double getQuantile() {
      return this.quantile;
   }

   public void setQuantile(double p) throws MathIllegalArgumentException {
      if (!(p <= (double)0.0F) && !(p > (double)100.0F)) {
         this.quantile = p;
      } else {
         throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, p, 0, 100);
      }
   }

   public Percentile copy() {
      return new Percentile(this);
   }

   /** @deprecated */
   @Deprecated
   public static void copy(Percentile source, Percentile dest) throws MathUnsupportedOperationException {
      throw new MathUnsupportedOperationException();
   }

   protected double[] getWorkArray(double[] values, int begin, int length) {
      double[] work;
      if (values == this.getDataRef()) {
         work = this.getDataRef();
      } else {
         switch (this.nanStrategy) {
            case MAXIMAL:
               work = replaceAndSlice(values, begin, length, Double.NaN, Double.POSITIVE_INFINITY);
               break;
            case MINIMAL:
               work = replaceAndSlice(values, begin, length, Double.NaN, Double.NEGATIVE_INFINITY);
               break;
            case REMOVED:
               work = removeAndSlice(values, begin, length, Double.NaN);
               break;
            case FAILED:
               work = copyOf(values, begin, length);
               MathArrays.checkNotNaN(work);
               break;
            default:
               work = copyOf(values, begin, length);
         }
      }

      return work;
   }

   private static double[] copyOf(double[] values, int begin, int length) {
      MathArrays.verifyValues(values, begin, length);
      return MathArrays.copyOfRange(values, begin, begin + length);
   }

   private static double[] replaceAndSlice(double[] values, int begin, int length, double original, double replacement) {
      double[] temp = copyOf(values, begin, length);

      for(int i = 0; i < length; ++i) {
         temp[i] = Precision.equalsIncludingNaN(original, temp[i]) ? replacement : temp[i];
      }

      return temp;
   }

   private static double[] removeAndSlice(double[] values, int begin, int length, double removedValue) {
      MathArrays.verifyValues(values, begin, length);
      BitSet bits = new BitSet(length);

      for(int i = begin; i < begin + length; ++i) {
         if (Precision.equalsIncludingNaN(removedValue, values[i])) {
            bits.set(i - begin);
         }
      }

      double[] temp;
      if (bits.isEmpty()) {
         temp = copyOf(values, begin, length);
      } else if (bits.cardinality() == length) {
         temp = new double[0];
      } else {
         temp = new double[length - bits.cardinality()];
         int start = begin;
         int dest = 0;
         int nextOne = -1;

         for(int bitSetPtr = 0; (nextOne = bits.nextSetBit(bitSetPtr)) != -1; start = begin + (bitSetPtr = bits.nextClearBit(nextOne))) {
            int lengthToCopy = nextOne - bitSetPtr;
            System.arraycopy(values, start, temp, dest, lengthToCopy);
            dest += lengthToCopy;
         }

         if (start < begin + length) {
            System.arraycopy(values, start, temp, dest, begin + length - start);
         }
      }

      return temp;
   }

   private int[] getPivots(double[] values) {
      int[] pivotsHeap;
      if (values == this.getDataRef()) {
         pivotsHeap = this.cachedPivots;
      } else {
         pivotsHeap = new int[512];
         Arrays.fill(pivotsHeap, -1);
      }

      return pivotsHeap;
   }

   public EstimationType getEstimationType() {
      return this.estimationType;
   }

   public Percentile withEstimationType(EstimationType newEstimationType) {
      return new Percentile(this.quantile, newEstimationType, this.nanStrategy, this.kthSelector);
   }

   public NaNStrategy getNaNStrategy() {
      return this.nanStrategy;
   }

   public Percentile withNaNStrategy(NaNStrategy newNaNStrategy) {
      return new Percentile(this.quantile, this.estimationType, newNaNStrategy, this.kthSelector);
   }

   public KthSelector getKthSelector() {
      return this.kthSelector;
   }

   public PivotingStrategyInterface getPivotingStrategy() {
      return this.kthSelector.getPivotingStrategy();
   }

   public Percentile withKthSelector(KthSelector newKthSelector) {
      return new Percentile(this.quantile, this.estimationType, this.nanStrategy, newKthSelector);
   }

   public static enum EstimationType {
      LEGACY("Legacy Apache Commons Math") {
         protected double index(double p, int length) {
            double minLimit = (double)0.0F;
            double maxLimit = (double)1.0F;
            return Double.compare(p, (double)0.0F) == 0 ? (double)0.0F : (Double.compare(p, (double)1.0F) == 0 ? (double)length : p * (double)(length + 1));
         }
      },
      R_1("R-1") {
         protected double index(double p, int length) {
            double minLimit = (double)0.0F;
            return Double.compare(p, (double)0.0F) == 0 ? (double)0.0F : (double)length * p + (double)0.5F;
         }

         protected double estimate(double[] values, int[] pivotsHeap, double pos, int length, KthSelector selector) {
            return super.estimate(values, pivotsHeap, FastMath.ceil(pos - (double)0.5F), length, selector);
         }
      },
      R_2("R-2") {
         protected double index(double p, int length) {
            double minLimit = (double)0.0F;
            double maxLimit = (double)1.0F;
            return Double.compare(p, (double)1.0F) == 0 ? (double)length : (Double.compare(p, (double)0.0F) == 0 ? (double)0.0F : (double)length * p + (double)0.5F);
         }

         protected double estimate(double[] values, int[] pivotsHeap, double pos, int length, KthSelector selector) {
            double low = super.estimate(values, pivotsHeap, FastMath.ceil(pos - (double)0.5F), length, selector);
            double high = super.estimate(values, pivotsHeap, FastMath.floor(pos + (double)0.5F), length, selector);
            return (low + high) / (double)2.0F;
         }
      },
      R_3("R-3") {
         protected double index(double p, int length) {
            double minLimit = (double)0.5F / (double)length;
            return Double.compare(p, minLimit) <= 0 ? (double)0.0F : FastMath.rint((double)length * p);
         }
      },
      R_4("R-4") {
         protected double index(double p, int length) {
            double minLimit = (double)1.0F / (double)length;
            double maxLimit = (double)1.0F;
            return Double.compare(p, minLimit) < 0 ? (double)0.0F : (Double.compare(p, (double)1.0F) == 0 ? (double)length : (double)length * p);
         }
      },
      R_5("R-5") {
         protected double index(double p, int length) {
            double minLimit = (double)0.5F / (double)length;
            double maxLimit = ((double)length - (double)0.5F) / (double)length;
            return Double.compare(p, minLimit) < 0 ? (double)0.0F : (Double.compare(p, maxLimit) >= 0 ? (double)length : (double)length * p + (double)0.5F);
         }
      },
      R_6("R-6") {
         protected double index(double p, int length) {
            double minLimit = (double)1.0F / (double)(length + 1);
            double maxLimit = (double)1.0F * (double)length / (double)(length + 1);
            return Double.compare(p, minLimit) < 0 ? (double)0.0F : (Double.compare(p, maxLimit) >= 0 ? (double)length : (double)(length + 1) * p);
         }
      },
      R_7("R-7") {
         protected double index(double p, int length) {
            double minLimit = (double)0.0F;
            double maxLimit = (double)1.0F;
            return Double.compare(p, (double)0.0F) == 0 ? (double)0.0F : (Double.compare(p, (double)1.0F) == 0 ? (double)length : (double)1.0F + (double)(length - 1) * p);
         }
      },
      R_8("R-8") {
         protected double index(double p, int length) {
            double minLimit = 0.6666666666666666 / ((double)length + 0.3333333333333333);
            double maxLimit = ((double)length - 0.3333333333333333) / ((double)length + 0.3333333333333333);
            return Double.compare(p, minLimit) < 0 ? (double)0.0F : (Double.compare(p, maxLimit) >= 0 ? (double)length : ((double)length + 0.3333333333333333) * p + 0.3333333333333333);
         }
      },
      R_9("R-9") {
         protected double index(double p, int length) {
            double minLimit = (double)0.625F / ((double)length + (double)0.25F);
            double maxLimit = ((double)length - (double)0.375F) / ((double)length + (double)0.25F);
            return Double.compare(p, minLimit) < 0 ? (double)0.0F : (Double.compare(p, maxLimit) >= 0 ? (double)length : ((double)length + (double)0.25F) * p + (double)0.375F);
         }
      };

      private final String name;

      private EstimationType(String type) {
         this.name = type;
      }

      protected abstract double index(double var1, int var3);

      protected double estimate(double[] work, int[] pivotsHeap, double pos, int length, KthSelector selector) {
         double fpos = FastMath.floor(pos);
         int intPos = (int)fpos;
         double dif = pos - fpos;
         if (pos < (double)1.0F) {
            return selector.select(work, pivotsHeap, 0);
         } else if (pos >= (double)length) {
            return selector.select(work, pivotsHeap, length - 1);
         } else {
            double lower = selector.select(work, pivotsHeap, intPos - 1);
            double upper = selector.select(work, pivotsHeap, intPos);
            return lower + dif * (upper - lower);
         }
      }

      protected double evaluate(double[] work, int[] pivotsHeap, double p, KthSelector selector) {
         MathUtils.checkNotNull(work);
         if (!(p > (double)100.0F) && !(p <= (double)0.0F)) {
            return this.estimate(work, pivotsHeap, this.index(p / (double)100.0F, work.length), work.length, selector);
         } else {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, p, 0, 100);
         }
      }

      public double evaluate(double[] work, double p, KthSelector selector) {
         return this.evaluate(work, (int[])null, p, selector);
      }

      String getName() {
         return this.name;
      }
   }
}

package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

public class PascalDistribution extends AbstractIntegerDistribution {
   private static final long serialVersionUID = 6751309484392813623L;
   private final int numberOfSuccesses;
   private final double probabilityOfSuccess;
   private final double logProbabilityOfSuccess;
   private final double log1mProbabilityOfSuccess;

   public PascalDistribution(int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
      this(new Well19937c(), r, p);
   }

   public PascalDistribution(RandomGenerator rng, int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
      super(rng);
      if (r <= 0) {
         throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SUCCESSES, r);
      } else if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         this.numberOfSuccesses = r;
         this.probabilityOfSuccess = p;
         this.logProbabilityOfSuccess = FastMath.log(p);
         this.log1mProbabilityOfSuccess = FastMath.log1p(-p);
      } else {
         throw new OutOfRangeException(p, 0, 1);
      }
   }

   public int getNumberOfSuccesses() {
      return this.numberOfSuccesses;
   }

   public double getProbabilityOfSuccess() {
      return this.probabilityOfSuccess;
   }

   public double probability(int x) {
      double ret;
      if (x < 0) {
         ret = (double)0.0F;
      } else {
         ret = CombinatoricsUtils.binomialCoefficientDouble(x + this.numberOfSuccesses - 1, this.numberOfSuccesses - 1) * FastMath.pow(this.probabilityOfSuccess, this.numberOfSuccesses) * FastMath.pow((double)1.0F - this.probabilityOfSuccess, x);
      }

      return ret;
   }

   public double logProbability(int x) {
      double ret;
      if (x < 0) {
         ret = Double.NEGATIVE_INFINITY;
      } else {
         ret = CombinatoricsUtils.binomialCoefficientLog(x + this.numberOfSuccesses - 1, this.numberOfSuccesses - 1) + this.logProbabilityOfSuccess * (double)this.numberOfSuccesses + this.log1mProbabilityOfSuccess * (double)x;
      }

      return ret;
   }

   public double cumulativeProbability(int x) {
      double ret;
      if (x < 0) {
         ret = (double)0.0F;
      } else {
         ret = Beta.regularizedBeta(this.probabilityOfSuccess, (double)this.numberOfSuccesses, (double)x + (double)1.0F);
      }

      return ret;
   }

   public double getNumericalMean() {
      double p = this.getProbabilityOfSuccess();
      double r = (double)this.getNumberOfSuccesses();
      return r * ((double)1.0F - p) / p;
   }

   public double getNumericalVariance() {
      double p = this.getProbabilityOfSuccess();
      double r = (double)this.getNumberOfSuccesses();
      return r * ((double)1.0F - p) / (p * p);
   }

   public int getSupportLowerBound() {
      return 0;
   }

   public int getSupportUpperBound() {
      return Integer.MAX_VALUE;
   }

   public boolean isSupportConnected() {
      return true;
   }
}

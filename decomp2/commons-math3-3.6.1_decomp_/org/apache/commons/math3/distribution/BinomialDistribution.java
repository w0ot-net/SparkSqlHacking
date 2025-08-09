package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.FastMath;

public class BinomialDistribution extends AbstractIntegerDistribution {
   private static final long serialVersionUID = 6751309484392813623L;
   private final int numberOfTrials;
   private final double probabilityOfSuccess;

   public BinomialDistribution(int trials, double p) {
      this(new Well19937c(), trials, p);
   }

   public BinomialDistribution(RandomGenerator rng, int trials, double p) {
      super(rng);
      if (trials < 0) {
         throw new NotPositiveException(LocalizedFormats.NUMBER_OF_TRIALS, trials);
      } else if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         this.probabilityOfSuccess = p;
         this.numberOfTrials = trials;
      } else {
         throw new OutOfRangeException(p, 0, 1);
      }
   }

   public int getNumberOfTrials() {
      return this.numberOfTrials;
   }

   public double getProbabilityOfSuccess() {
      return this.probabilityOfSuccess;
   }

   public double probability(int x) {
      double logProbability = this.logProbability(x);
      return logProbability == Double.NEGATIVE_INFINITY ? (double)0.0F : FastMath.exp(logProbability);
   }

   public double logProbability(int x) {
      if (this.numberOfTrials == 0) {
         return x == 0 ? (double)0.0F : Double.NEGATIVE_INFINITY;
      } else {
         double ret;
         if (x >= 0 && x <= this.numberOfTrials) {
            ret = SaddlePointExpansion.logBinomialProbability(x, this.numberOfTrials, this.probabilityOfSuccess, (double)1.0F - this.probabilityOfSuccess);
         } else {
            ret = Double.NEGATIVE_INFINITY;
         }

         return ret;
      }
   }

   public double cumulativeProbability(int x) {
      double ret;
      if (x < 0) {
         ret = (double)0.0F;
      } else if (x >= this.numberOfTrials) {
         ret = (double)1.0F;
      } else {
         ret = (double)1.0F - Beta.regularizedBeta(this.probabilityOfSuccess, (double)x + (double)1.0F, (double)(this.numberOfTrials - x));
      }

      return ret;
   }

   public double getNumericalMean() {
      return (double)this.numberOfTrials * this.probabilityOfSuccess;
   }

   public double getNumericalVariance() {
      double p = this.probabilityOfSuccess;
      return (double)this.numberOfTrials * p * ((double)1.0F - p);
   }

   public int getSupportLowerBound() {
      return this.probabilityOfSuccess < (double)1.0F ? 0 : this.numberOfTrials;
   }

   public int getSupportUpperBound() {
      return this.probabilityOfSuccess > (double)0.0F ? this.numberOfTrials : 0;
   }

   public boolean isSupportConnected() {
      return true;
   }
}

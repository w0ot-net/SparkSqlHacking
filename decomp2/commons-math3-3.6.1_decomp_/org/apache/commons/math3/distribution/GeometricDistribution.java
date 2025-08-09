package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class GeometricDistribution extends AbstractIntegerDistribution {
   private static final long serialVersionUID = 20130507L;
   private final double probabilityOfSuccess;
   private final double logProbabilityOfSuccess;
   private final double log1mProbabilityOfSuccess;

   public GeometricDistribution(double p) {
      this(new Well19937c(), p);
   }

   public GeometricDistribution(RandomGenerator rng, double p) {
      super(rng);
      if (!(p <= (double)0.0F) && !(p > (double)1.0F)) {
         this.probabilityOfSuccess = p;
         this.logProbabilityOfSuccess = FastMath.log(p);
         this.log1mProbabilityOfSuccess = FastMath.log1p(-p);
      } else {
         throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_LEFT, p, 0, 1);
      }
   }

   public double getProbabilityOfSuccess() {
      return this.probabilityOfSuccess;
   }

   public double probability(int x) {
      return x < 0 ? (double)0.0F : FastMath.exp(this.log1mProbabilityOfSuccess * (double)x) * this.probabilityOfSuccess;
   }

   public double logProbability(int x) {
      return x < 0 ? Double.NEGATIVE_INFINITY : (double)x * this.log1mProbabilityOfSuccess + this.logProbabilityOfSuccess;
   }

   public double cumulativeProbability(int x) {
      return x < 0 ? (double)0.0F : -FastMath.expm1(this.log1mProbabilityOfSuccess * (double)(x + 1));
   }

   public double getNumericalMean() {
      return ((double)1.0F - this.probabilityOfSuccess) / this.probabilityOfSuccess;
   }

   public double getNumericalVariance() {
      return ((double)1.0F - this.probabilityOfSuccess) / (this.probabilityOfSuccess * this.probabilityOfSuccess);
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

   public int inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         if (p == (double)1.0F) {
            return Integer.MAX_VALUE;
         } else {
            return p == (double)0.0F ? 0 : Math.max(0, (int)Math.ceil(FastMath.log1p(-p) / this.log1mProbabilityOfSuccess - (double)1.0F));
         }
      } else {
         throw new OutOfRangeException(p, 0, 1);
      }
   }
}

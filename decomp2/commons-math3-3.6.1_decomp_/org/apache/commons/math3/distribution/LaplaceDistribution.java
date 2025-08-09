package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class LaplaceDistribution extends AbstractRealDistribution {
   private static final long serialVersionUID = 20141003L;
   private final double mu;
   private final double beta;

   public LaplaceDistribution(double mu, double beta) {
      this(new Well19937c(), mu, beta);
   }

   public LaplaceDistribution(RandomGenerator rng, double mu, double beta) {
      super(rng);
      if (beta <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, beta);
      } else {
         this.mu = mu;
         this.beta = beta;
      }
   }

   public double getLocation() {
      return this.mu;
   }

   public double getScale() {
      return this.beta;
   }

   public double density(double x) {
      return FastMath.exp(-FastMath.abs(x - this.mu) / this.beta) / ((double)2.0F * this.beta);
   }

   public double cumulativeProbability(double x) {
      return x <= this.mu ? FastMath.exp((x - this.mu) / this.beta) / (double)2.0F : (double)1.0F - FastMath.exp((this.mu - x) / this.beta) / (double)2.0F;
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         if (p == (double)0.0F) {
            return Double.NEGATIVE_INFINITY;
         } else if (p == (double)1.0F) {
            return Double.POSITIVE_INFINITY;
         } else {
            double x = p > (double)0.5F ? -Math.log((double)2.0F - (double)2.0F * p) : Math.log((double)2.0F * p);
            return this.mu + this.beta * x;
         }
      } else {
         throw new OutOfRangeException(p, (double)0.0F, (double)1.0F);
      }
   }

   public double getNumericalMean() {
      return this.mu;
   }

   public double getNumericalVariance() {
      return (double)2.0F * this.beta * this.beta;
   }

   public double getSupportLowerBound() {
      return Double.NEGATIVE_INFINITY;
   }

   public double getSupportUpperBound() {
      return Double.POSITIVE_INFINITY;
   }

   public boolean isSupportLowerBoundInclusive() {
      return false;
   }

   public boolean isSupportUpperBoundInclusive() {
      return false;
   }

   public boolean isSupportConnected() {
      return true;
   }
}

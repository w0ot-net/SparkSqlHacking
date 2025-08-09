package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class LogisticDistribution extends AbstractRealDistribution {
   private static final long serialVersionUID = 20141003L;
   private final double mu;
   private final double s;

   public LogisticDistribution(double mu, double s) {
      this(new Well19937c(), mu, s);
   }

   public LogisticDistribution(RandomGenerator rng, double mu, double s) {
      super(rng);
      if (s <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, s);
      } else {
         this.mu = mu;
         this.s = s;
      }
   }

   public double getLocation() {
      return this.mu;
   }

   public double getScale() {
      return this.s;
   }

   public double density(double x) {
      double z = (x - this.mu) / this.s;
      double v = FastMath.exp(-z);
      return (double)1.0F / this.s * v / (((double)1.0F + v) * ((double)1.0F + v));
   }

   public double cumulativeProbability(double x) {
      double z = (double)1.0F / this.s * (x - this.mu);
      return (double)1.0F / ((double)1.0F + FastMath.exp(-z));
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         if (p == (double)0.0F) {
            return (double)0.0F;
         } else {
            return p == (double)1.0F ? Double.POSITIVE_INFINITY : this.s * Math.log(p / ((double)1.0F - p)) + this.mu;
         }
      } else {
         throw new OutOfRangeException(p, (double)0.0F, (double)1.0F);
      }
   }

   public double getNumericalMean() {
      return this.mu;
   }

   public double getNumericalVariance() {
      return 3.289868133696453 * ((double)1.0F / (this.s * this.s));
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

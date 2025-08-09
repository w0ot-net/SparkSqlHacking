package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;

public class LevyDistribution extends AbstractRealDistribution {
   private static final long serialVersionUID = 20130314L;
   private final double mu;
   private final double c;
   private final double halfC;

   public LevyDistribution(double mu, double c) {
      this(new Well19937c(), mu, c);
   }

   public LevyDistribution(RandomGenerator rng, double mu, double c) {
      super(rng);
      this.mu = mu;
      this.c = c;
      this.halfC = (double)0.5F * c;
   }

   public double density(double x) {
      if (x < this.mu) {
         return Double.NaN;
      } else {
         double delta = x - this.mu;
         double f = this.halfC / delta;
         return FastMath.sqrt(f / Math.PI) * FastMath.exp(-f) / delta;
      }
   }

   public double logDensity(double x) {
      if (x < this.mu) {
         return Double.NaN;
      } else {
         double delta = x - this.mu;
         double f = this.halfC / delta;
         return (double)0.5F * FastMath.log(f / Math.PI) - f - FastMath.log(delta);
      }
   }

   public double cumulativeProbability(double x) {
      return x < this.mu ? Double.NaN : Erf.erfc(FastMath.sqrt(this.halfC / (x - this.mu)));
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         double t = Erf.erfcInv(p);
         return this.mu + this.halfC / (t * t);
      } else {
         throw new OutOfRangeException(p, 0, 1);
      }
   }

   public double getScale() {
      return this.c;
   }

   public double getLocation() {
      return this.mu;
   }

   public double getNumericalMean() {
      return Double.POSITIVE_INFINITY;
   }

   public double getNumericalVariance() {
      return Double.POSITIVE_INFINITY;
   }

   public double getSupportLowerBound() {
      return this.mu;
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

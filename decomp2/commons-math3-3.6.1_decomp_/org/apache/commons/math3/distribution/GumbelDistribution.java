package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class GumbelDistribution extends AbstractRealDistribution {
   private static final long serialVersionUID = 20141003L;
   private static final double EULER = 0.5778636748954609;
   private final double mu;
   private final double beta;

   public GumbelDistribution(double mu, double beta) {
      this(new Well19937c(), mu, beta);
   }

   public GumbelDistribution(RandomGenerator rng, double mu, double beta) {
      super(rng);
      if (beta <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, beta);
      } else {
         this.beta = beta;
         this.mu = mu;
      }
   }

   public double getLocation() {
      return this.mu;
   }

   public double getScale() {
      return this.beta;
   }

   public double density(double x) {
      double z = (x - this.mu) / this.beta;
      double t = FastMath.exp(-z);
      return FastMath.exp(-z - t) / this.beta;
   }

   public double cumulativeProbability(double x) {
      double z = (x - this.mu) / this.beta;
      return FastMath.exp(-FastMath.exp(-z));
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         if (p == (double)0.0F) {
            return Double.NEGATIVE_INFINITY;
         } else {
            return p == (double)1.0F ? Double.POSITIVE_INFINITY : this.mu - FastMath.log(-FastMath.log(p)) * this.beta;
         }
      } else {
         throw new OutOfRangeException(p, (double)0.0F, (double)1.0F);
      }
   }

   public double getNumericalMean() {
      return this.mu + 0.5778636748954609 * this.beta;
   }

   public double getNumericalVariance() {
      return 1.6449340668482264 * this.beta * this.beta;
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

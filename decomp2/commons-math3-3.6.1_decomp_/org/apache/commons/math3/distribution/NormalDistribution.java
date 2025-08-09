package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;

public class NormalDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = 8589540077390120676L;
   private static final double SQRT2 = FastMath.sqrt((double)2.0F);
   private final double mean;
   private final double standardDeviation;
   private final double logStandardDeviationPlusHalfLog2Pi;
   private final double solverAbsoluteAccuracy;

   public NormalDistribution() {
      this((double)0.0F, (double)1.0F);
   }

   public NormalDistribution(double mean, double sd) throws NotStrictlyPositiveException {
      this(mean, sd, 1.0E-9);
   }

   public NormalDistribution(double mean, double sd, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      this(new Well19937c(), mean, sd, inverseCumAccuracy);
   }

   public NormalDistribution(RandomGenerator rng, double mean, double sd) throws NotStrictlyPositiveException {
      this(rng, mean, sd, 1.0E-9);
   }

   public NormalDistribution(RandomGenerator rng, double mean, double sd, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      super(rng);
      if (sd <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, sd);
      } else {
         this.mean = mean;
         this.standardDeviation = sd;
         this.logStandardDeviationPlusHalfLog2Pi = FastMath.log(sd) + (double)0.5F * FastMath.log((Math.PI * 2D));
         this.solverAbsoluteAccuracy = inverseCumAccuracy;
      }
   }

   public double getMean() {
      return this.mean;
   }

   public double getStandardDeviation() {
      return this.standardDeviation;
   }

   public double density(double x) {
      return FastMath.exp(this.logDensity(x));
   }

   public double logDensity(double x) {
      double x0 = x - this.mean;
      double x1 = x0 / this.standardDeviation;
      return (double)-0.5F * x1 * x1 - this.logStandardDeviationPlusHalfLog2Pi;
   }

   public double cumulativeProbability(double x) {
      double dev = x - this.mean;
      if (FastMath.abs(dev) > (double)40.0F * this.standardDeviation) {
         return dev < (double)0.0F ? (double)0.0F : (double)1.0F;
      } else {
         return (double)0.5F * Erf.erfc(-dev / (this.standardDeviation * SQRT2));
      }
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         return this.mean + this.standardDeviation * SQRT2 * Erf.erfInv((double)2.0F * p - (double)1.0F);
      } else {
         throw new OutOfRangeException(p, 0, 1);
      }
   }

   /** @deprecated */
   @Deprecated
   public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
      return this.probability(x0, x1);
   }

   public double probability(double x0, double x1) throws NumberIsTooLargeException {
      if (x0 > x1) {
         throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, x0, x1, true);
      } else {
         double denom = this.standardDeviation * SQRT2;
         double v0 = (x0 - this.mean) / denom;
         double v1 = (x1 - this.mean) / denom;
         return (double)0.5F * Erf.erf(v0, v1);
      }
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double getNumericalMean() {
      return this.getMean();
   }

   public double getNumericalVariance() {
      double s = this.getStandardDeviation();
      return s * s;
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

   public double sample() {
      return this.standardDeviation * this.random.nextGaussian() + this.mean;
   }
}

package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;

public class LogNormalDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = 20120112L;
   private static final double SQRT2PI = FastMath.sqrt((Math.PI * 2D));
   private static final double SQRT2 = FastMath.sqrt((double)2.0F);
   private final double scale;
   private final double shape;
   private final double logShapePlusHalfLog2Pi;
   private final double solverAbsoluteAccuracy;

   public LogNormalDistribution() {
      this((double)0.0F, (double)1.0F);
   }

   public LogNormalDistribution(double scale, double shape) throws NotStrictlyPositiveException {
      this(scale, shape, 1.0E-9);
   }

   public LogNormalDistribution(double scale, double shape, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      this(new Well19937c(), scale, shape, inverseCumAccuracy);
   }

   public LogNormalDistribution(RandomGenerator rng, double scale, double shape) throws NotStrictlyPositiveException {
      this(rng, scale, shape, 1.0E-9);
   }

   public LogNormalDistribution(RandomGenerator rng, double scale, double shape, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      super(rng);
      if (shape <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, shape);
      } else {
         this.scale = scale;
         this.shape = shape;
         this.logShapePlusHalfLog2Pi = FastMath.log(shape) + (double)0.5F * FastMath.log((Math.PI * 2D));
         this.solverAbsoluteAccuracy = inverseCumAccuracy;
      }
   }

   public double getScale() {
      return this.scale;
   }

   public double getShape() {
      return this.shape;
   }

   public double density(double x) {
      if (x <= (double)0.0F) {
         return (double)0.0F;
      } else {
         double x0 = FastMath.log(x) - this.scale;
         double x1 = x0 / this.shape;
         return FastMath.exp((double)-0.5F * x1 * x1) / (this.shape * SQRT2PI * x);
      }
   }

   public double logDensity(double x) {
      if (x <= (double)0.0F) {
         return Double.NEGATIVE_INFINITY;
      } else {
         double logX = FastMath.log(x);
         double x0 = logX - this.scale;
         double x1 = x0 / this.shape;
         return (double)-0.5F * x1 * x1 - (this.logShapePlusHalfLog2Pi + logX);
      }
   }

   public double cumulativeProbability(double x) {
      if (x <= (double)0.0F) {
         return (double)0.0F;
      } else {
         double dev = FastMath.log(x) - this.scale;
         if (FastMath.abs(dev) > (double)40.0F * this.shape) {
            return dev < (double)0.0F ? (double)0.0F : (double)1.0F;
         } else {
            return (double)0.5F + (double)0.5F * Erf.erf(dev / (this.shape * SQRT2));
         }
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
      } else if (!(x0 <= (double)0.0F) && !(x1 <= (double)0.0F)) {
         double denom = this.shape * SQRT2;
         double v0 = (FastMath.log(x0) - this.scale) / denom;
         double v1 = (FastMath.log(x1) - this.scale) / denom;
         return (double)0.5F * Erf.erf(v0, v1);
      } else {
         return super.probability(x0, x1);
      }
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double getNumericalMean() {
      double s = this.shape;
      return FastMath.exp(this.scale + s * s / (double)2.0F);
   }

   public double getNumericalVariance() {
      double s = this.shape;
      double ss = s * s;
      return FastMath.expm1(ss) * FastMath.exp((double)2.0F * this.scale + ss);
   }

   public double getSupportLowerBound() {
      return (double)0.0F;
   }

   public double getSupportUpperBound() {
      return Double.POSITIVE_INFINITY;
   }

   public boolean isSupportLowerBoundInclusive() {
      return true;
   }

   public boolean isSupportUpperBoundInclusive() {
      return false;
   }

   public boolean isSupportConnected() {
      return true;
   }

   public double sample() {
      double n = this.random.nextGaussian();
      return FastMath.exp(this.scale + this.shape * n);
   }
}

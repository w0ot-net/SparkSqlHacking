package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class WeibullDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = 8589540077390120676L;
   private final double shape;
   private final double scale;
   private final double solverAbsoluteAccuracy;
   private double numericalMean;
   private boolean numericalMeanIsCalculated;
   private double numericalVariance;
   private boolean numericalVarianceIsCalculated;

   public WeibullDistribution(double alpha, double beta) throws NotStrictlyPositiveException {
      this(alpha, beta, 1.0E-9);
   }

   public WeibullDistribution(double alpha, double beta, double inverseCumAccuracy) {
      this(new Well19937c(), alpha, beta, inverseCumAccuracy);
   }

   public WeibullDistribution(RandomGenerator rng, double alpha, double beta) throws NotStrictlyPositiveException {
      this(rng, alpha, beta, 1.0E-9);
   }

   public WeibullDistribution(RandomGenerator rng, double alpha, double beta, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      super(rng);
      this.numericalMean = Double.NaN;
      this.numericalMeanIsCalculated = false;
      this.numericalVariance = Double.NaN;
      this.numericalVarianceIsCalculated = false;
      if (alpha <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, alpha);
      } else if (beta <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, beta);
      } else {
         this.scale = beta;
         this.shape = alpha;
         this.solverAbsoluteAccuracy = inverseCumAccuracy;
      }
   }

   public double getShape() {
      return this.shape;
   }

   public double getScale() {
      return this.scale;
   }

   public double density(double x) {
      if (x < (double)0.0F) {
         return (double)0.0F;
      } else {
         double xscale = x / this.scale;
         double xscalepow = FastMath.pow(xscale, this.shape - (double)1.0F);
         double xscalepowshape = xscalepow * xscale;
         return this.shape / this.scale * xscalepow * FastMath.exp(-xscalepowshape);
      }
   }

   public double logDensity(double x) {
      if (x < (double)0.0F) {
         return Double.NEGATIVE_INFINITY;
      } else {
         double xscale = x / this.scale;
         double logxscalepow = FastMath.log(xscale) * (this.shape - (double)1.0F);
         double xscalepowshape = FastMath.exp(logxscalepow) * xscale;
         return FastMath.log(this.shape / this.scale) + logxscalepow - xscalepowshape;
      }
   }

   public double cumulativeProbability(double x) {
      double ret;
      if (x <= (double)0.0F) {
         ret = (double)0.0F;
      } else {
         ret = (double)1.0F - FastMath.exp(-FastMath.pow(x / this.scale, this.shape));
      }

      return ret;
   }

   public double inverseCumulativeProbability(double p) {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         double ret;
         if (p == (double)0.0F) {
            ret = (double)0.0F;
         } else if (p == (double)1.0F) {
            ret = Double.POSITIVE_INFINITY;
         } else {
            ret = this.scale * FastMath.pow(-FastMath.log1p(-p), (double)1.0F / this.shape);
         }

         return ret;
      } else {
         throw new OutOfRangeException(p, (double)0.0F, (double)1.0F);
      }
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double getNumericalMean() {
      if (!this.numericalMeanIsCalculated) {
         this.numericalMean = this.calculateNumericalMean();
         this.numericalMeanIsCalculated = true;
      }

      return this.numericalMean;
   }

   protected double calculateNumericalMean() {
      double sh = this.getShape();
      double sc = this.getScale();
      return sc * FastMath.exp(Gamma.logGamma((double)1.0F + (double)1.0F / sh));
   }

   public double getNumericalVariance() {
      if (!this.numericalVarianceIsCalculated) {
         this.numericalVariance = this.calculateNumericalVariance();
         this.numericalVarianceIsCalculated = true;
      }

      return this.numericalVariance;
   }

   protected double calculateNumericalVariance() {
      double sh = this.getShape();
      double sc = this.getScale();
      double mn = this.getNumericalMean();
      return sc * sc * FastMath.exp(Gamma.logGamma((double)1.0F + (double)2.0F / sh)) - mn * mn;
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
}

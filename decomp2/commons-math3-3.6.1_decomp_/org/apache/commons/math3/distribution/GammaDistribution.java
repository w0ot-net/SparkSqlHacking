package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class GammaDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = 20120524L;
   private final double shape;
   private final double scale;
   private final double shiftedShape;
   private final double densityPrefactor1;
   private final double logDensityPrefactor1;
   private final double densityPrefactor2;
   private final double logDensityPrefactor2;
   private final double minY;
   private final double maxLogY;
   private final double solverAbsoluteAccuracy;

   public GammaDistribution(double shape, double scale) throws NotStrictlyPositiveException {
      this(shape, scale, 1.0E-9);
   }

   public GammaDistribution(double shape, double scale, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      this(new Well19937c(), shape, scale, inverseCumAccuracy);
   }

   public GammaDistribution(RandomGenerator rng, double shape, double scale) throws NotStrictlyPositiveException {
      this(rng, shape, scale, 1.0E-9);
   }

   public GammaDistribution(RandomGenerator rng, double shape, double scale, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      super(rng);
      if (shape <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, shape);
      } else if (scale <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, scale);
      } else {
         this.shape = shape;
         this.scale = scale;
         this.solverAbsoluteAccuracy = inverseCumAccuracy;
         this.shiftedShape = shape + (double)4.7421875F + (double)0.5F;
         double aux = Math.E / ((Math.PI * 2D) * this.shiftedShape);
         this.densityPrefactor2 = shape * FastMath.sqrt(aux) / Gamma.lanczos(shape);
         this.logDensityPrefactor2 = FastMath.log(shape) + (double)0.5F * FastMath.log(aux) - FastMath.log(Gamma.lanczos(shape));
         this.densityPrefactor1 = this.densityPrefactor2 / scale * FastMath.pow(this.shiftedShape, -shape) * FastMath.exp(shape + (double)4.7421875F);
         this.logDensityPrefactor1 = this.logDensityPrefactor2 - FastMath.log(scale) - FastMath.log(this.shiftedShape) * shape + shape + (double)4.7421875F;
         this.minY = shape + (double)4.7421875F - FastMath.log(Double.MAX_VALUE);
         this.maxLogY = FastMath.log(Double.MAX_VALUE) / (shape - (double)1.0F);
      }
   }

   /** @deprecated */
   @Deprecated
   public double getAlpha() {
      return this.shape;
   }

   public double getShape() {
      return this.shape;
   }

   /** @deprecated */
   @Deprecated
   public double getBeta() {
      return this.scale;
   }

   public double getScale() {
      return this.scale;
   }

   public double density(double x) {
      if (x < (double)0.0F) {
         return (double)0.0F;
      } else {
         double y = x / this.scale;
         if (!(y <= this.minY) && !(FastMath.log(y) >= this.maxLogY)) {
            return this.densityPrefactor1 * FastMath.exp(-y) * FastMath.pow(y, this.shape - (double)1.0F);
         } else {
            double aux1 = (y - this.shiftedShape) / this.shiftedShape;
            double aux2 = this.shape * (FastMath.log1p(aux1) - aux1);
            double aux3 = -y * (double)5.2421875F / this.shiftedShape + (double)4.7421875F + aux2;
            return this.densityPrefactor2 / x * FastMath.exp(aux3);
         }
      }
   }

   public double logDensity(double x) {
      if (x < (double)0.0F) {
         return Double.NEGATIVE_INFINITY;
      } else {
         double y = x / this.scale;
         if (!(y <= this.minY) && !(FastMath.log(y) >= this.maxLogY)) {
            return this.logDensityPrefactor1 - y + FastMath.log(y) * (this.shape - (double)1.0F);
         } else {
            double aux1 = (y - this.shiftedShape) / this.shiftedShape;
            double aux2 = this.shape * (FastMath.log1p(aux1) - aux1);
            double aux3 = -y * (double)5.2421875F / this.shiftedShape + (double)4.7421875F + aux2;
            return this.logDensityPrefactor2 - FastMath.log(x) + aux3;
         }
      }
   }

   public double cumulativeProbability(double x) {
      double ret;
      if (x <= (double)0.0F) {
         ret = (double)0.0F;
      } else {
         ret = Gamma.regularizedGammaP(this.shape, x / this.scale);
      }

      return ret;
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double getNumericalMean() {
      return this.shape * this.scale;
   }

   public double getNumericalVariance() {
      return this.shape * this.scale * this.scale;
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
      if (this.shape < (double)1.0F) {
         while(true) {
            double u = this.random.nextDouble();
            double bGS = (double)1.0F + this.shape / Math.E;
            double p = bGS * u;
            if (p <= (double)1.0F) {
               double x = FastMath.pow(p, (double)1.0F / this.shape);
               double u2 = this.random.nextDouble();
               if (!(u2 > FastMath.exp(-x))) {
                  return this.scale * x;
               }
            } else {
               double x = (double)-1.0F * FastMath.log((bGS - p) / this.shape);
               double u2 = this.random.nextDouble();
               if (!(u2 > FastMath.pow(x, this.shape - (double)1.0F))) {
                  return this.scale * x;
               }
            }
         }
      } else {
         double d = this.shape - 0.3333333333333333;
         double c = (double)1.0F / ((double)3.0F * FastMath.sqrt(d));

         while(true) {
            double x = this.random.nextGaussian();
            double v = ((double)1.0F + c * x) * ((double)1.0F + c * x) * ((double)1.0F + c * x);
            if (!(v <= (double)0.0F)) {
               double x2 = x * x;
               double u = this.random.nextDouble();
               if (u < (double)1.0F - 0.0331 * x2 * x2) {
                  return this.scale * d * v;
               }

               if (FastMath.log(u) < (double)0.5F * x2 + d * ((double)1.0F - v + FastMath.log(v))) {
                  return this.scale * d * v;
               }
            }
         }
      }
   }
}

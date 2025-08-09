package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class ParetoDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = 20130424L;
   private final double scale;
   private final double shape;
   private final double solverAbsoluteAccuracy;

   public ParetoDistribution() {
      this((double)1.0F, (double)1.0F);
   }

   public ParetoDistribution(double scale, double shape) throws NotStrictlyPositiveException {
      this(scale, shape, 1.0E-9);
   }

   public ParetoDistribution(double scale, double shape, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      this(new Well19937c(), scale, shape, inverseCumAccuracy);
   }

   public ParetoDistribution(RandomGenerator rng, double scale, double shape) throws NotStrictlyPositiveException {
      this(rng, scale, shape, 1.0E-9);
   }

   public ParetoDistribution(RandomGenerator rng, double scale, double shape, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      super(rng);
      if (scale <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, scale);
      } else if (shape <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, shape);
      } else {
         this.scale = scale;
         this.shape = shape;
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
      return x < this.scale ? (double)0.0F : FastMath.pow(this.scale, this.shape) / FastMath.pow(x, this.shape + (double)1.0F) * this.shape;
   }

   public double logDensity(double x) {
      return x < this.scale ? Double.NEGATIVE_INFINITY : FastMath.log(this.scale) * this.shape - FastMath.log(x) * (this.shape + (double)1.0F) + FastMath.log(this.shape);
   }

   public double cumulativeProbability(double x) {
      return x <= this.scale ? (double)0.0F : (double)1.0F - FastMath.pow(this.scale / x, this.shape);
   }

   /** @deprecated */
   @Deprecated
   public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
      return this.probability(x0, x1);
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double getNumericalMean() {
      return this.shape <= (double)1.0F ? Double.POSITIVE_INFINITY : this.shape * this.scale / (this.shape - (double)1.0F);
   }

   public double getNumericalVariance() {
      if (this.shape <= (double)2.0F) {
         return Double.POSITIVE_INFINITY;
      } else {
         double s = this.shape - (double)1.0F;
         return this.scale * this.scale * this.shape / (s * s) / (this.shape - (double)2.0F);
      }
   }

   public double getSupportLowerBound() {
      return this.scale;
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
      double n = this.random.nextDouble();
      return this.scale / FastMath.pow(n, (double)1.0F / this.shape);
   }
}

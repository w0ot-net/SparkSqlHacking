package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class TDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = -5852615386664158222L;
   private final double degreesOfFreedom;
   private final double solverAbsoluteAccuracy;
   private final double factor;

   public TDistribution(double degreesOfFreedom) throws NotStrictlyPositiveException {
      this(degreesOfFreedom, 1.0E-9);
   }

   public TDistribution(double degreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      this(new Well19937c(), degreesOfFreedom, inverseCumAccuracy);
   }

   public TDistribution(RandomGenerator rng, double degreesOfFreedom) throws NotStrictlyPositiveException {
      this(rng, degreesOfFreedom, 1.0E-9);
   }

   public TDistribution(RandomGenerator rng, double degreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      super(rng);
      if (degreesOfFreedom <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, degreesOfFreedom);
      } else {
         this.degreesOfFreedom = degreesOfFreedom;
         this.solverAbsoluteAccuracy = inverseCumAccuracy;
         double nPlus1Over2 = (degreesOfFreedom + (double)1.0F) / (double)2.0F;
         this.factor = Gamma.logGamma(nPlus1Over2) - (double)0.5F * (FastMath.log(Math.PI) + FastMath.log(degreesOfFreedom)) - Gamma.logGamma(degreesOfFreedom / (double)2.0F);
      }
   }

   public double getDegreesOfFreedom() {
      return this.degreesOfFreedom;
   }

   public double density(double x) {
      return FastMath.exp(this.logDensity(x));
   }

   public double logDensity(double x) {
      double n = this.degreesOfFreedom;
      double nPlus1Over2 = (n + (double)1.0F) / (double)2.0F;
      return this.factor - nPlus1Over2 * FastMath.log((double)1.0F + x * x / n);
   }

   public double cumulativeProbability(double x) {
      double ret;
      if (x == (double)0.0F) {
         ret = (double)0.5F;
      } else {
         double t = Beta.regularizedBeta(this.degreesOfFreedom / (this.degreesOfFreedom + x * x), (double)0.5F * this.degreesOfFreedom, (double)0.5F);
         if (x < (double)0.0F) {
            ret = (double)0.5F * t;
         } else {
            ret = (double)1.0F - (double)0.5F * t;
         }
      }

      return ret;
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double getNumericalMean() {
      double df = this.getDegreesOfFreedom();
      return df > (double)1.0F ? (double)0.0F : Double.NaN;
   }

   public double getNumericalVariance() {
      double df = this.getDegreesOfFreedom();
      if (df > (double)2.0F) {
         return df / (df - (double)2.0F);
      } else {
         return df > (double)1.0F && df <= (double)2.0F ? Double.POSITIVE_INFINITY : Double.NaN;
      }
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

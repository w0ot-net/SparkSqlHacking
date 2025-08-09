package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class NakagamiDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = 20141003L;
   private final double mu;
   private final double omega;
   private final double inverseAbsoluteAccuracy;

   public NakagamiDistribution(double mu, double omega) {
      this(mu, omega, 1.0E-9);
   }

   public NakagamiDistribution(double mu, double omega, double inverseAbsoluteAccuracy) {
      this(new Well19937c(), mu, omega, inverseAbsoluteAccuracy);
   }

   public NakagamiDistribution(RandomGenerator rng, double mu, double omega, double inverseAbsoluteAccuracy) {
      super(rng);
      if (mu < (double)0.5F) {
         throw new NumberIsTooSmallException(mu, (double)0.5F, true);
      } else if (omega <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, omega);
      } else {
         this.mu = mu;
         this.omega = omega;
         this.inverseAbsoluteAccuracy = inverseAbsoluteAccuracy;
      }
   }

   public double getShape() {
      return this.mu;
   }

   public double getScale() {
      return this.omega;
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.inverseAbsoluteAccuracy;
   }

   public double density(double x) {
      return x <= (double)0.0F ? (double)0.0F : (double)2.0F * FastMath.pow(this.mu, this.mu) / (Gamma.gamma(this.mu) * FastMath.pow(this.omega, this.mu)) * FastMath.pow(x, (double)2.0F * this.mu - (double)1.0F) * FastMath.exp(-this.mu * x * x / this.omega);
   }

   public double cumulativeProbability(double x) {
      return Gamma.regularizedGammaP(this.mu, this.mu * x * x / this.omega);
   }

   public double getNumericalMean() {
      return Gamma.gamma(this.mu + (double)0.5F) / Gamma.gamma(this.mu) * FastMath.sqrt(this.omega / this.mu);
   }

   public double getNumericalVariance() {
      double v = Gamma.gamma(this.mu + (double)0.5F) / Gamma.gamma(this.mu);
      return this.omega * ((double)1.0F - (double)1.0F / this.mu * v * v);
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

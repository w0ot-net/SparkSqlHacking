package org.apache.commons.math3.distribution;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

public class ChiSquaredDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = -8352658048349159782L;
   private final GammaDistribution gamma;
   private final double solverAbsoluteAccuracy;

   public ChiSquaredDistribution(double degreesOfFreedom) {
      this(degreesOfFreedom, 1.0E-9);
   }

   public ChiSquaredDistribution(double degreesOfFreedom, double inverseCumAccuracy) {
      this(new Well19937c(), degreesOfFreedom, inverseCumAccuracy);
   }

   public ChiSquaredDistribution(RandomGenerator rng, double degreesOfFreedom) {
      this(rng, degreesOfFreedom, 1.0E-9);
   }

   public ChiSquaredDistribution(RandomGenerator rng, double degreesOfFreedom, double inverseCumAccuracy) {
      super(rng);
      this.gamma = new GammaDistribution(degreesOfFreedom / (double)2.0F, (double)2.0F);
      this.solverAbsoluteAccuracy = inverseCumAccuracy;
   }

   public double getDegreesOfFreedom() {
      return this.gamma.getShape() * (double)2.0F;
   }

   public double density(double x) {
      return this.gamma.density(x);
   }

   public double logDensity(double x) {
      return this.gamma.logDensity(x);
   }

   public double cumulativeProbability(double x) {
      return this.gamma.cumulativeProbability(x);
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double getNumericalMean() {
      return this.getDegreesOfFreedom();
   }

   public double getNumericalVariance() {
      return (double)2.0F * this.getDegreesOfFreedom();
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

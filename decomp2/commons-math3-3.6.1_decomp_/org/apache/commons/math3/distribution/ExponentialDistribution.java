package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.ResizableDoubleArray;

public class ExponentialDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = 2401296428283614780L;
   private static final double[] EXPONENTIAL_SA_QI;
   private final double mean;
   private final double logMean;
   private final double solverAbsoluteAccuracy;

   public ExponentialDistribution(double mean) {
      this(mean, 1.0E-9);
   }

   public ExponentialDistribution(double mean, double inverseCumAccuracy) {
      this(new Well19937c(), mean, inverseCumAccuracy);
   }

   public ExponentialDistribution(RandomGenerator rng, double mean) throws NotStrictlyPositiveException {
      this(rng, mean, 1.0E-9);
   }

   public ExponentialDistribution(RandomGenerator rng, double mean, double inverseCumAccuracy) throws NotStrictlyPositiveException {
      super(rng);
      if (mean <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, mean);
      } else {
         this.mean = mean;
         this.logMean = FastMath.log(mean);
         this.solverAbsoluteAccuracy = inverseCumAccuracy;
      }
   }

   public double getMean() {
      return this.mean;
   }

   public double density(double x) {
      double logDensity = this.logDensity(x);
      return logDensity == Double.NEGATIVE_INFINITY ? (double)0.0F : FastMath.exp(logDensity);
   }

   public double logDensity(double x) {
      return x < (double)0.0F ? Double.NEGATIVE_INFINITY : -x / this.mean - this.logMean;
   }

   public double cumulativeProbability(double x) {
      double ret;
      if (x <= (double)0.0F) {
         ret = (double)0.0F;
      } else {
         ret = (double)1.0F - FastMath.exp(-x / this.mean);
      }

      return ret;
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         double ret;
         if (p == (double)1.0F) {
            ret = Double.POSITIVE_INFINITY;
         } else {
            ret = -this.mean * FastMath.log((double)1.0F - p);
         }

         return ret;
      } else {
         throw new OutOfRangeException(p, (double)0.0F, (double)1.0F);
      }
   }

   public double sample() {
      double a = (double)0.0F;

      double u;
      for(u = this.random.nextDouble(); u < (double)0.5F; u *= (double)2.0F) {
         a += EXPONENTIAL_SA_QI[0];
      }

      u += u - (double)1.0F;
      if (u <= EXPONENTIAL_SA_QI[0]) {
         return this.mean * (a + u);
      } else {
         int i = 0;
         double u2 = this.random.nextDouble();
         double umin = u2;

         do {
            ++i;
            u2 = this.random.nextDouble();
            if (u2 < umin) {
               umin = u2;
            }
         } while(u > EXPONENTIAL_SA_QI[i]);

         return this.mean * (a + umin * EXPONENTIAL_SA_QI[0]);
      }
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double getNumericalMean() {
      return this.getMean();
   }

   public double getNumericalVariance() {
      double m = this.getMean();
      return m * m;
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

   static {
      double LN2 = FastMath.log((double)2.0F);
      double qi = (double)0.0F;
      int i = 1;

      ResizableDoubleArray ra;
      for(ra = new ResizableDoubleArray(20); qi < (double)1.0F; ++i) {
         qi += FastMath.pow(LN2, i) / (double)CombinatoricsUtils.factorial(i);
         ra.addElement(qi);
      }

      EXPONENTIAL_SA_QI = ra.getElements();
   }
}

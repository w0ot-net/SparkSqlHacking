package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class BetaDistribution extends AbstractRealDistribution {
   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
   private static final long serialVersionUID = -1221965979403477668L;
   private final double alpha;
   private final double beta;
   private double z;
   private final double solverAbsoluteAccuracy;

   public BetaDistribution(double alpha, double beta) {
      this(alpha, beta, 1.0E-9);
   }

   public BetaDistribution(double alpha, double beta, double inverseCumAccuracy) {
      this(new Well19937c(), alpha, beta, inverseCumAccuracy);
   }

   public BetaDistribution(RandomGenerator rng, double alpha, double beta) {
      this(rng, alpha, beta, 1.0E-9);
   }

   public BetaDistribution(RandomGenerator rng, double alpha, double beta, double inverseCumAccuracy) {
      super(rng);
      this.alpha = alpha;
      this.beta = beta;
      this.z = Double.NaN;
      this.solverAbsoluteAccuracy = inverseCumAccuracy;
   }

   public double getAlpha() {
      return this.alpha;
   }

   public double getBeta() {
      return this.beta;
   }

   private void recomputeZ() {
      if (Double.isNaN(this.z)) {
         this.z = Gamma.logGamma(this.alpha) + Gamma.logGamma(this.beta) - Gamma.logGamma(this.alpha + this.beta);
      }

   }

   public double density(double x) {
      double logDensity = this.logDensity(x);
      return logDensity == Double.NEGATIVE_INFINITY ? (double)0.0F : FastMath.exp(logDensity);
   }

   public double logDensity(double x) {
      this.recomputeZ();
      if (!(x < (double)0.0F) && !(x > (double)1.0F)) {
         if (x == (double)0.0F) {
            if (this.alpha < (double)1.0F) {
               throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_0_FOR_SOME_ALPHA, this.alpha, 1, false);
            } else {
               return Double.NEGATIVE_INFINITY;
            }
         } else if (x == (double)1.0F) {
            if (this.beta < (double)1.0F) {
               throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_1_FOR_SOME_BETA, this.beta, 1, false);
            } else {
               return Double.NEGATIVE_INFINITY;
            }
         } else {
            double logX = FastMath.log(x);
            double log1mX = FastMath.log1p(-x);
            return (this.alpha - (double)1.0F) * logX + (this.beta - (double)1.0F) * log1mX - this.z;
         }
      } else {
         return Double.NEGATIVE_INFINITY;
      }
   }

   public double cumulativeProbability(double x) {
      if (x <= (double)0.0F) {
         return (double)0.0F;
      } else {
         return x >= (double)1.0F ? (double)1.0F : Beta.regularizedBeta(x, this.alpha, this.beta);
      }
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double getNumericalMean() {
      double a = this.getAlpha();
      return a / (a + this.getBeta());
   }

   public double getNumericalVariance() {
      double a = this.getAlpha();
      double b = this.getBeta();
      double alphabetasum = a + b;
      return a * b / (alphabetasum * alphabetasum * (alphabetasum + (double)1.0F));
   }

   public double getSupportLowerBound() {
      return (double)0.0F;
   }

   public double getSupportUpperBound() {
      return (double)1.0F;
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
      return BetaDistribution.ChengBetaSampler.sample(this.random, this.alpha, this.beta);
   }

   private static final class ChengBetaSampler {
      static double sample(RandomGenerator random, double alpha, double beta) {
         double a = FastMath.min(alpha, beta);
         double b = FastMath.max(alpha, beta);
         return a > (double)1.0F ? algorithmBB(random, alpha, a, b) : algorithmBC(random, alpha, b, a);
      }

      private static double algorithmBB(RandomGenerator random, double a0, double a, double b) {
         double alpha = a + b;
         double beta = FastMath.sqrt((alpha - (double)2.0F) / ((double)2.0F * a * b - alpha));
         double gamma = a + (double)1.0F / beta;

         double r;
         double w;
         double t;
         double s;
         do {
            double u1 = random.nextDouble();
            double u2 = random.nextDouble();
            double v = beta * (FastMath.log(u1) - FastMath.log1p(-u1));
            w = a * FastMath.exp(v);
            double z = u1 * u1 * u2;
            r = gamma * v - 1.3862944;
            s = a + r - w;
            if (s + 2.609438 >= (double)5.0F * z) {
               break;
            }

            t = FastMath.log(z);
         } while(!(s >= t) && r + alpha * (FastMath.log(alpha) - FastMath.log(b + w)) < t);

         w = FastMath.min(w, Double.MAX_VALUE);
         return Precision.equals(a, a0) ? w / (b + w) : b / (b + w);
      }

      private static double algorithmBC(RandomGenerator random, double a0, double a, double b) {
         double alpha = a + b;
         double beta = (double)1.0F / b;
         double delta = (double)1.0F + a - b;
         double k1 = delta * (0.0138889 + 0.0416667 * b) / (a * beta - 0.777778);
         double k2 = (double)0.25F + ((double)0.5F + (double)0.25F / delta) * b;

         double w;
         while(true) {
            double u1 = random.nextDouble();
            double u2 = random.nextDouble();
            double y = u1 * u2;
            double z = u1 * y;
            if (u1 < (double)0.5F) {
               if ((double)0.25F * u2 + z - y >= k1) {
                  continue;
               }
            } else {
               if (z <= (double)0.25F) {
                  double v = beta * (FastMath.log(u1) - FastMath.log1p(-u1));
                  w = a * FastMath.exp(v);
                  break;
               }

               if (z >= k2) {
                  continue;
               }
            }

            double v = beta * (FastMath.log(u1) - FastMath.log1p(-u1));
            w = a * FastMath.exp(v);
            if (alpha * (FastMath.log(alpha) - FastMath.log(b + w) + v) - 1.3862944 >= FastMath.log(z)) {
               break;
            }
         }

         w = FastMath.min(w, Double.MAX_VALUE);
         return Precision.equals(a, a0) ? w / (b + w) : b / (b + w);
      }
   }
}

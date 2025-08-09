package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

public class PoissonDistribution extends AbstractIntegerDistribution {
   public static final int DEFAULT_MAX_ITERATIONS = 10000000;
   public static final double DEFAULT_EPSILON = 1.0E-12;
   private static final long serialVersionUID = -3349935121172596109L;
   private final NormalDistribution normal;
   private final ExponentialDistribution exponential;
   private final double mean;
   private final int maxIterations;
   private final double epsilon;

   public PoissonDistribution(double p) throws NotStrictlyPositiveException {
      this(p, 1.0E-12, 10000000);
   }

   public PoissonDistribution(double p, double epsilon, int maxIterations) throws NotStrictlyPositiveException {
      this(new Well19937c(), p, epsilon, maxIterations);
   }

   public PoissonDistribution(RandomGenerator rng, double p, double epsilon, int maxIterations) throws NotStrictlyPositiveException {
      super(rng);
      if (p <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, p);
      } else {
         this.mean = p;
         this.epsilon = epsilon;
         this.maxIterations = maxIterations;
         this.normal = new NormalDistribution(rng, p, FastMath.sqrt(p), 1.0E-9);
         this.exponential = new ExponentialDistribution(rng, (double)1.0F, 1.0E-9);
      }
   }

   public PoissonDistribution(double p, double epsilon) throws NotStrictlyPositiveException {
      this(p, epsilon, 10000000);
   }

   public PoissonDistribution(double p, int maxIterations) {
      this(p, 1.0E-12, maxIterations);
   }

   public double getMean() {
      return this.mean;
   }

   public double probability(int x) {
      double logProbability = this.logProbability(x);
      return logProbability == Double.NEGATIVE_INFINITY ? (double)0.0F : FastMath.exp(logProbability);
   }

   public double logProbability(int x) {
      double ret;
      if (x >= 0 && x != Integer.MAX_VALUE) {
         if (x == 0) {
            ret = -this.mean;
         } else {
            ret = -SaddlePointExpansion.getStirlingError((double)x) - SaddlePointExpansion.getDeviancePart((double)x, this.mean) - (double)0.5F * FastMath.log((Math.PI * 2D)) - (double)0.5F * FastMath.log((double)x);
         }
      } else {
         ret = Double.NEGATIVE_INFINITY;
      }

      return ret;
   }

   public double cumulativeProbability(int x) {
      if (x < 0) {
         return (double)0.0F;
      } else {
         return x == Integer.MAX_VALUE ? (double)1.0F : Gamma.regularizedGammaQ((double)x + (double)1.0F, this.mean, this.epsilon, this.maxIterations);
      }
   }

   public double normalApproximateProbability(int x) {
      return this.normal.cumulativeProbability((double)x + (double)0.5F);
   }

   public double getNumericalMean() {
      return this.getMean();
   }

   public double getNumericalVariance() {
      return this.getMean();
   }

   public int getSupportLowerBound() {
      return 0;
   }

   public int getSupportUpperBound() {
      return Integer.MAX_VALUE;
   }

   public boolean isSupportConnected() {
      return true;
   }

   public int sample() {
      return (int)FastMath.min(this.nextPoisson(this.mean), 2147483647L);
   }

   private long nextPoisson(double meanPoisson) {
      double pivot = (double)40.0F;
      if (meanPoisson < (double)40.0F) {
         double p = FastMath.exp(-meanPoisson);
         long n = 0L;
         double r = (double)1.0F;

         for(double rnd = (double)1.0F; (double)n < (double)1000.0F * meanPoisson; ++n) {
            rnd = this.random.nextDouble();
            r *= rnd;
            if (!(r >= p)) {
               return n;
            }
         }

         return n;
      } else {
         double lambda = FastMath.floor(meanPoisson);
         double lambdaFractional = meanPoisson - lambda;
         double logLambda = FastMath.log(lambda);
         double logLambdaFactorial = CombinatoricsUtils.factorialLog((int)lambda);
         long y2 = lambdaFractional < Double.MIN_VALUE ? 0L : this.nextPoisson(lambdaFractional);
         double delta = FastMath.sqrt(lambda * FastMath.log((double)32.0F * lambda / Math.PI + (double)1.0F));
         double halfDelta = delta / (double)2.0F;
         double twolpd = (double)2.0F * lambda + delta;
         double a1 = FastMath.sqrt(Math.PI * twolpd) * FastMath.exp((double)1.0F / ((double)8.0F * lambda));
         double a2 = twolpd / delta * FastMath.exp(-delta * ((double)1.0F + delta) / twolpd);
         double aSum = a1 + a2 + (double)1.0F;
         double p1 = a1 / aSum;
         double p2 = a2 / aSum;
         double c1 = (double)1.0F / ((double)8.0F * lambda);
         double x = (double)0.0F;
         double y = (double)0.0F;
         double v = (double)0.0F;
         int a = 0;
         double t = (double)0.0F;
         double qr = (double)0.0F;
         double qa = (double)0.0F;

         while(true) {
            double u = this.random.nextDouble();
            if (u <= p1) {
               double n = this.random.nextGaussian();
               x = n * FastMath.sqrt(lambda + halfDelta) - (double)0.5F;
               if (x > delta || x < -lambda) {
                  continue;
               }

               y = x < (double)0.0F ? FastMath.floor(x) : FastMath.ceil(x);
               double e = this.exponential.sample();
               v = -e - n * n / (double)2.0F + c1;
            } else {
               if (u > p1 + p2) {
                  y = lambda;
                  break;
               }

               x = delta + twolpd / delta * this.exponential.sample();
               y = FastMath.ceil(x);
               v = -this.exponential.sample() - delta * (x + (double)1.0F) / twolpd;
            }

            a = x < (double)0.0F ? 1 : 0;
            t = y * (y + (double)1.0F) / ((double)2.0F * lambda);
            if (v < -t && a == 0) {
               y = lambda + y;
               break;
            }

            qr = t * (((double)2.0F * y + (double)1.0F) / ((double)6.0F * lambda) - (double)1.0F);
            qa = qr - t * t / ((double)3.0F * (lambda + (double)a * (y + (double)1.0F)));
            if (v < qa) {
               y = lambda + y;
               break;
            }

            if (!(v > qr) && v < y * logLambda - CombinatoricsUtils.factorialLog((int)(y + lambda)) + logLambdaFactorial) {
               y = lambda + y;
               break;
            }
         }

         return y2 + (long)y;
      }
   }
}

package org.apache.commons.math3.special;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.ContinuedFraction;
import org.apache.commons.math3.util.FastMath;

public class Beta {
   private static final double DEFAULT_EPSILON = 1.0E-14;
   private static final double HALF_LOG_TWO_PI = 0.9189385332046727;
   private static final double[] DELTA = new double[]{0.08333333333333333, -2.777777777777778E-5, 7.936507936507937E-8, -5.952380952380953E-10, 8.417508417508329E-12, -1.917526917518546E-13, 6.410256405103255E-15, -2.955065141253382E-16, 1.7964371635940225E-17, -1.3922896466162779E-18, 1.338028550140209E-19, -1.542460098679661E-20, 1.9770199298095743E-21, -2.3406566479399704E-22, 1.713480149663986E-23};

   private Beta() {
   }

   public static double regularizedBeta(double x, double a, double b) {
      return regularizedBeta(x, a, b, 1.0E-14, Integer.MAX_VALUE);
   }

   public static double regularizedBeta(double x, double a, double b, double epsilon) {
      return regularizedBeta(x, a, b, epsilon, Integer.MAX_VALUE);
   }

   public static double regularizedBeta(double x, double a, double b, int maxIterations) {
      return regularizedBeta(x, a, b, 1.0E-14, maxIterations);
   }

   public static double regularizedBeta(double x, final double a, final double b, double epsilon, int maxIterations) {
      double ret;
      if (!Double.isNaN(x) && !Double.isNaN(a) && !Double.isNaN(b) && !(x < (double)0.0F) && !(x > (double)1.0F) && !(a <= (double)0.0F) && !(b <= (double)0.0F)) {
         if (x > (a + (double)1.0F) / ((double)2.0F + b + a) && (double)1.0F - x <= (b + (double)1.0F) / ((double)2.0F + b + a)) {
            ret = (double)1.0F - regularizedBeta((double)1.0F - x, b, a, epsilon, maxIterations);
         } else {
            ContinuedFraction fraction = new ContinuedFraction() {
               protected double getB(int n, double x) {
                  double ret;
                  if (n % 2 == 0) {
                     double m = (double)n / (double)2.0F;
                     ret = m * (b - m) * x / ((a + (double)2.0F * m - (double)1.0F) * (a + (double)2.0F * m));
                  } else {
                     double m = ((double)n - (double)1.0F) / (double)2.0F;
                     ret = -((a + m) * (a + b + m) * x) / ((a + (double)2.0F * m) * (a + (double)2.0F * m + (double)1.0F));
                  }

                  return ret;
               }

               protected double getA(int n, double x) {
                  return (double)1.0F;
               }
            };
            ret = FastMath.exp(a * FastMath.log(x) + b * FastMath.log1p(-x) - FastMath.log(a) - logBeta(a, b)) * (double)1.0F / fraction.evaluate(x, epsilon, maxIterations);
         }
      } else {
         ret = Double.NaN;
      }

      return ret;
   }

   /** @deprecated */
   @Deprecated
   public static double logBeta(double a, double b, double epsilon, int maxIterations) {
      return logBeta(a, b);
   }

   private static double logGammaSum(double a, double b) throws OutOfRangeException {
      if (!(a < (double)1.0F) && !(a > (double)2.0F)) {
         if (!(b < (double)1.0F) && !(b > (double)2.0F)) {
            double x = a - (double)1.0F + (b - (double)1.0F);
            if (x <= (double)0.5F) {
               return Gamma.logGamma1p((double)1.0F + x);
            } else {
               return x <= (double)1.5F ? Gamma.logGamma1p(x) + FastMath.log1p(x) : Gamma.logGamma1p(x - (double)1.0F) + FastMath.log(x * ((double)1.0F + x));
            }
         } else {
            throw new OutOfRangeException(b, (double)1.0F, (double)2.0F);
         }
      } else {
         throw new OutOfRangeException(a, (double)1.0F, (double)2.0F);
      }
   }

   private static double logGammaMinusLogGammaSum(double a, double b) throws NumberIsTooSmallException {
      if (a < (double)0.0F) {
         throw new NumberIsTooSmallException(a, (double)0.0F, true);
      } else if (b < (double)10.0F) {
         throw new NumberIsTooSmallException(b, (double)10.0F, true);
      } else {
         double d;
         double w;
         if (a <= b) {
            d = b + (a - (double)0.5F);
            w = deltaMinusDeltaSum(a, b);
         } else {
            d = a + (b - (double)0.5F);
            w = deltaMinusDeltaSum(b, a);
         }

         double u = d * FastMath.log1p(a / b);
         double v = a * (FastMath.log(b) - (double)1.0F);
         return u <= v ? w - u - v : w - v - u;
      }
   }

   private static double deltaMinusDeltaSum(double a, double b) throws OutOfRangeException, NumberIsTooSmallException {
      if (!(a < (double)0.0F) && !(a > b)) {
         if (b < (double)10.0F) {
            throw new NumberIsTooSmallException(b, 10, true);
         } else {
            double h = a / b;
            double p = h / ((double)1.0F + h);
            double q = (double)1.0F / ((double)1.0F + h);
            double q2 = q * q;
            double[] s = new double[DELTA.length];
            s[0] = (double)1.0F;

            for(int i = 1; i < s.length; ++i) {
               s[i] = (double)1.0F + q + q2 * s[i - 1];
            }

            double sqrtT = (double)10.0F / b;
            double t = sqrtT * sqrtT;
            double w = DELTA[DELTA.length - 1] * s[s.length - 1];

            for(int i = DELTA.length - 2; i >= 0; --i) {
               w = t * w + DELTA[i] * s[i];
            }

            return w * p / b;
         }
      } else {
         throw new OutOfRangeException(a, 0, b);
      }
   }

   private static double sumDeltaMinusDeltaSum(double p, double q) {
      if (p < (double)10.0F) {
         throw new NumberIsTooSmallException(p, (double)10.0F, true);
      } else if (q < (double)10.0F) {
         throw new NumberIsTooSmallException(q, (double)10.0F, true);
      } else {
         double a = FastMath.min(p, q);
         double b = FastMath.max(p, q);
         double sqrtT = (double)10.0F / a;
         double t = sqrtT * sqrtT;
         double z = DELTA[DELTA.length - 1];

         for(int i = DELTA.length - 2; i >= 0; --i) {
            z = t * z + DELTA[i];
         }

         return z / a + deltaMinusDeltaSum(a, b);
      }
   }

   public static double logBeta(double p, double q) {
      if (!Double.isNaN(p) && !Double.isNaN(q) && !(p <= (double)0.0F) && !(q <= (double)0.0F)) {
         double a = FastMath.min(p, q);
         double b = FastMath.max(p, q);
         if (a >= (double)10.0F) {
            double w = sumDeltaMinusDeltaSum(a, b);
            double h = a / b;
            double c = h / ((double)1.0F + h);
            double u = -(a - (double)0.5F) * FastMath.log(c);
            double v = b * FastMath.log1p(h);
            return u <= v ? (double)-0.5F * FastMath.log(b) + 0.9189385332046727 + w - u - v : (double)-0.5F * FastMath.log(b) + 0.9189385332046727 + w - v - u;
         } else if (a > (double)2.0F) {
            if (b > (double)1000.0F) {
               int n = (int)FastMath.floor(a - (double)1.0F);
               double prod = (double)1.0F;
               double ared = a;

               for(int i = 0; i < n; ++i) {
                  --ared;
                  prod *= ared / ((double)1.0F + ared / b);
               }

               return FastMath.log(prod) - (double)n * FastMath.log(b) + Gamma.logGamma(ared) + logGammaMinusLogGammaSum(ared, b);
            } else {
               double prod1 = (double)1.0F;

               double h;
               double ared;
               for(ared = a; ared > (double)2.0F; prod1 *= h / ((double)1.0F + h)) {
                  --ared;
                  h = ared / b;
               }

               if (!(b < (double)10.0F)) {
                  return FastMath.log(prod1) + Gamma.logGamma(ared) + logGammaMinusLogGammaSum(ared, b);
               } else {
                  h = (double)1.0F;

                  double bred;
                  for(bred = b; bred > (double)2.0F; h *= bred / (ared + bred)) {
                     --bred;
                  }

                  return FastMath.log(prod1) + FastMath.log(h) + Gamma.logGamma(ared) + (Gamma.logGamma(bred) - logGammaSum(ared, bred));
               }
            }
         } else if (!(a >= (double)1.0F)) {
            return b >= (double)10.0F ? Gamma.logGamma(a) + logGammaMinusLogGammaSum(a, b) : FastMath.log(Gamma.gamma(a) * Gamma.gamma(b) / Gamma.gamma(a + b));
         } else if (!(b > (double)2.0F)) {
            return Gamma.logGamma(a) + Gamma.logGamma(b) - logGammaSum(a, b);
         } else if (!(b < (double)10.0F)) {
            return Gamma.logGamma(a) + logGammaMinusLogGammaSum(a, b);
         } else {
            double prod = (double)1.0F;

            double bred;
            for(bred = b; bred > (double)2.0F; prod *= bred / (a + bred)) {
               --bred;
            }

            return FastMath.log(prod) + Gamma.logGamma(a) + (Gamma.logGamma(bred) - logGammaSum(a, bred));
         }
      } else {
         return Double.NaN;
      }
   }
}

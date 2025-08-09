package org.apache.commons.math3.special;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.ContinuedFraction;
import org.apache.commons.math3.util.FastMath;

public class Gamma {
   public static final double GAMMA = 0.5772156649015329;
   public static final double LANCZOS_G = (double)4.7421875F;
   private static final double DEFAULT_EPSILON = 1.0E-14;
   private static final double[] LANCZOS = new double[]{0.9999999999999971, 57.15623566586292, -59.59796035547549, 14.136097974741746, -0.4919138160976202, 3.399464998481189E-5, 4.652362892704858E-5, -9.837447530487956E-5, 1.580887032249125E-4, -2.1026444172410488E-4, 2.1743961811521265E-4, -1.643181065367639E-4, 8.441822398385275E-5, -2.6190838401581408E-5, 3.6899182659531625E-6};
   private static final double HALF_LOG_2_PI = (double)0.5F * FastMath.log((Math.PI * 2D));
   private static final double SQRT_TWO_PI = 2.5066282746310007;
   private static final double C_LIMIT = (double)49.0F;
   private static final double S_LIMIT = 1.0E-5;
   private static final double INV_GAMMA1P_M1_A0 = 6.116095104481416E-9;
   private static final double INV_GAMMA1P_M1_A1 = 6.247308301164655E-9;
   private static final double INV_GAMMA1P_M1_B1 = 0.203610414066807;
   private static final double INV_GAMMA1P_M1_B2 = 0.026620534842894922;
   private static final double INV_GAMMA1P_M1_B3 = 4.939449793824468E-4;
   private static final double INV_GAMMA1P_M1_B4 = -8.514194324403149E-6;
   private static final double INV_GAMMA1P_M1_B5 = -6.4304548177935305E-6;
   private static final double INV_GAMMA1P_M1_B6 = 9.926418406727737E-7;
   private static final double INV_GAMMA1P_M1_B7 = -6.077618957228252E-8;
   private static final double INV_GAMMA1P_M1_B8 = 1.9575583661463974E-10;
   private static final double INV_GAMMA1P_M1_P0 = 6.116095104481416E-9;
   private static final double INV_GAMMA1P_M1_P1 = 6.8716741130671986E-9;
   private static final double INV_GAMMA1P_M1_P2 = 6.820161668496171E-10;
   private static final double INV_GAMMA1P_M1_P3 = 4.686843322948848E-11;
   private static final double INV_GAMMA1P_M1_P4 = 1.5728330277104463E-12;
   private static final double INV_GAMMA1P_M1_P5 = -1.2494415722763663E-13;
   private static final double INV_GAMMA1P_M1_P6 = 4.343529937408594E-15;
   private static final double INV_GAMMA1P_M1_Q1 = 0.3056961078365221;
   private static final double INV_GAMMA1P_M1_Q2 = 0.054642130860422966;
   private static final double INV_GAMMA1P_M1_Q3 = 0.004956830093825887;
   private static final double INV_GAMMA1P_M1_Q4 = 2.6923694661863613E-4;
   private static final double INV_GAMMA1P_M1_C = -0.42278433509846713;
   private static final double INV_GAMMA1P_M1_C0 = 0.5772156649015329;
   private static final double INV_GAMMA1P_M1_C1 = -0.6558780715202539;
   private static final double INV_GAMMA1P_M1_C2 = -0.04200263503409524;
   private static final double INV_GAMMA1P_M1_C3 = 0.16653861138229148;
   private static final double INV_GAMMA1P_M1_C4 = -0.04219773455554433;
   private static final double INV_GAMMA1P_M1_C5 = -0.009621971527876973;
   private static final double INV_GAMMA1P_M1_C6 = 0.0072189432466631;
   private static final double INV_GAMMA1P_M1_C7 = -0.0011651675918590652;
   private static final double INV_GAMMA1P_M1_C8 = -2.1524167411495098E-4;
   private static final double INV_GAMMA1P_M1_C9 = 1.280502823881162E-4;
   private static final double INV_GAMMA1P_M1_C10 = -2.013485478078824E-5;
   private static final double INV_GAMMA1P_M1_C11 = -1.2504934821426706E-6;
   private static final double INV_GAMMA1P_M1_C12 = 1.133027231981696E-6;
   private static final double INV_GAMMA1P_M1_C13 = -2.056338416977607E-7;

   private Gamma() {
   }

   public static double logGamma(double x) {
      double ret;
      if (!Double.isNaN(x) && !(x <= (double)0.0F)) {
         if (x < (double)0.5F) {
            return logGamma1p(x) - FastMath.log(x);
         }

         if (x <= (double)2.5F) {
            return logGamma1p(x - (double)0.5F - (double)0.5F);
         }

         if (x <= (double)8.0F) {
            int n = (int)FastMath.floor(x - (double)1.5F);
            double prod = (double)1.0F;

            for(int i = 1; i <= n; ++i) {
               prod *= x - (double)i;
            }

            return logGamma1p(x - (double)(n + 1)) + FastMath.log(prod);
         }

         double sum = lanczos(x);
         double tmp = x + (double)4.7421875F + (double)0.5F;
         ret = (x + (double)0.5F) * FastMath.log(tmp) - tmp + HALF_LOG_2_PI + FastMath.log(sum / x);
      } else {
         ret = Double.NaN;
      }

      return ret;
   }

   public static double regularizedGammaP(double a, double x) {
      return regularizedGammaP(a, x, 1.0E-14, Integer.MAX_VALUE);
   }

   public static double regularizedGammaP(double a, double x, double epsilon, int maxIterations) {
      double ret;
      if (!Double.isNaN(a) && !Double.isNaN(x) && !(a <= (double)0.0F) && !(x < (double)0.0F)) {
         if (x == (double)0.0F) {
            ret = (double)0.0F;
         } else if (x >= a + (double)1.0F) {
            ret = (double)1.0F - regularizedGammaQ(a, x, epsilon, maxIterations);
         } else {
            double n = (double)0.0F;
            double an = (double)1.0F / a;

            double sum;
            for(sum = an; FastMath.abs(an / sum) > epsilon && n < (double)maxIterations && sum < Double.POSITIVE_INFINITY; sum += an) {
               ++n;
               an *= x / (a + n);
            }

            if (n >= (double)maxIterations) {
               throw new MaxCountExceededException(maxIterations);
            }

            if (Double.isInfinite(sum)) {
               ret = (double)1.0F;
            } else {
               ret = FastMath.exp(-x + a * FastMath.log(x) - logGamma(a)) * sum;
            }
         }
      } else {
         ret = Double.NaN;
      }

      return ret;
   }

   public static double regularizedGammaQ(double a, double x) {
      return regularizedGammaQ(a, x, 1.0E-14, Integer.MAX_VALUE);
   }

   public static double regularizedGammaQ(final double a, double x, double epsilon, int maxIterations) {
      double ret;
      if (!Double.isNaN(a) && !Double.isNaN(x) && !(a <= (double)0.0F) && !(x < (double)0.0F)) {
         if (x == (double)0.0F) {
            ret = (double)1.0F;
         } else if (x < a + (double)1.0F) {
            ret = (double)1.0F - regularizedGammaP(a, x, epsilon, maxIterations);
         } else {
            ContinuedFraction cf = new ContinuedFraction() {
               protected double getA(int n, double x) {
                  return (double)2.0F * (double)n + (double)1.0F - a + x;
               }

               protected double getB(int n, double x) {
                  return (double)n * (a - (double)n);
               }
            };
            ret = (double)1.0F / cf.evaluate(x, epsilon, maxIterations);
            ret = FastMath.exp(-x + a * FastMath.log(x) - logGamma(a)) * ret;
         }
      } else {
         ret = Double.NaN;
      }

      return ret;
   }

   public static double digamma(double x) {
      if (!Double.isNaN(x) && !Double.isInfinite(x)) {
         if (x > (double)0.0F && x <= 1.0E-5) {
            return -0.5772156649015329 - (double)1.0F / x;
         } else if (x >= (double)49.0F) {
            double inv = (double)1.0F / (x * x);
            return FastMath.log(x) - (double)0.5F / x - inv * (0.08333333333333333 + inv * (0.008333333333333333 - inv / (double)252.0F));
         } else {
            return digamma(x + (double)1.0F) - (double)1.0F / x;
         }
      } else {
         return x;
      }
   }

   public static double trigamma(double x) {
      if (!Double.isNaN(x) && !Double.isInfinite(x)) {
         if (x > (double)0.0F && x <= 1.0E-5) {
            return (double)1.0F / (x * x);
         } else if (x >= (double)49.0F) {
            double inv = (double)1.0F / (x * x);
            return (double)1.0F / x + inv / (double)2.0F + inv / x * (0.16666666666666666 - inv * (0.03333333333333333 + inv / (double)42.0F));
         } else {
            return trigamma(x + (double)1.0F) + (double)1.0F / (x * x);
         }
      } else {
         return x;
      }
   }

   public static double lanczos(double x) {
      double sum = (double)0.0F;

      for(int i = LANCZOS.length - 1; i > 0; --i) {
         sum += LANCZOS[i] / (x + (double)i);
      }

      return sum + LANCZOS[0];
   }

   public static double invGamma1pm1(double x) {
      if (x < (double)-0.5F) {
         throw new NumberIsTooSmallException(x, (double)-0.5F, true);
      } else if (x > (double)1.5F) {
         throw new NumberIsTooLargeException(x, (double)1.5F, true);
      } else {
         double t = x <= (double)0.5F ? x : x - (double)0.5F - (double)0.5F;
         double ret;
         if (t < (double)0.0F) {
            double a = 6.116095104481416E-9 + t * 6.247308301164655E-9;
            double b = 1.9575583661463974E-10;
            b = -6.077618957228252E-8 + t * b;
            b = 9.926418406727737E-7 + t * b;
            b = -6.4304548177935305E-6 + t * b;
            b = -8.514194324403149E-6 + t * b;
            b = 4.939449793824468E-4 + t * b;
            b = 0.026620534842894922 + t * b;
            b = 0.203610414066807 + t * b;
            b = (double)1.0F + t * b;
            double c = -2.056338416977607E-7 + t * (a / b);
            c = 1.133027231981696E-6 + t * c;
            c = -1.2504934821426706E-6 + t * c;
            c = -2.013485478078824E-5 + t * c;
            c = 1.280502823881162E-4 + t * c;
            c = -2.1524167411495098E-4 + t * c;
            c = -0.0011651675918590652 + t * c;
            c = 0.0072189432466631 + t * c;
            c = -0.009621971527876973 + t * c;
            c = -0.04219773455554433 + t * c;
            c = 0.16653861138229148 + t * c;
            c = -0.04200263503409524 + t * c;
            c = -0.6558780715202539 + t * c;
            c = -0.42278433509846713 + t * c;
            if (x > (double)0.5F) {
               ret = t * c / x;
            } else {
               ret = x * (c + (double)0.5F + (double)0.5F);
            }
         } else {
            double p = 4.343529937408594E-15;
            p = -1.2494415722763663E-13 + t * p;
            p = 1.5728330277104463E-12 + t * p;
            p = 4.686843322948848E-11 + t * p;
            p = 6.820161668496171E-10 + t * p;
            p = 6.8716741130671986E-9 + t * p;
            p = 6.116095104481416E-9 + t * p;
            double q = 2.6923694661863613E-4;
            q = 0.004956830093825887 + t * q;
            q = 0.054642130860422966 + t * q;
            q = 0.3056961078365221 + t * q;
            q = (double)1.0F + t * q;
            double c = -2.056338416977607E-7 + p / q * t;
            c = 1.133027231981696E-6 + t * c;
            c = -1.2504934821426706E-6 + t * c;
            c = -2.013485478078824E-5 + t * c;
            c = 1.280502823881162E-4 + t * c;
            c = -2.1524167411495098E-4 + t * c;
            c = -0.0011651675918590652 + t * c;
            c = 0.0072189432466631 + t * c;
            c = -0.009621971527876973 + t * c;
            c = -0.04219773455554433 + t * c;
            c = 0.16653861138229148 + t * c;
            c = -0.04200263503409524 + t * c;
            c = -0.6558780715202539 + t * c;
            c = 0.5772156649015329 + t * c;
            if (x > (double)0.5F) {
               ret = t / x * (c - (double)0.5F - (double)0.5F);
            } else {
               ret = x * c;
            }
         }

         return ret;
      }
   }

   public static double logGamma1p(double x) throws NumberIsTooSmallException, NumberIsTooLargeException {
      if (x < (double)-0.5F) {
         throw new NumberIsTooSmallException(x, (double)-0.5F, true);
      } else if (x > (double)1.5F) {
         throw new NumberIsTooLargeException(x, (double)1.5F, true);
      } else {
         return -FastMath.log1p(invGamma1pm1(x));
      }
   }

   public static double gamma(double x) {
      if (x == FastMath.rint(x) && x <= (double)0.0F) {
         return Double.NaN;
      } else {
         double absX = FastMath.abs(x);
         double ret;
         if (absX <= (double)20.0F) {
            if (x >= (double)1.0F) {
               double prod = (double)1.0F;

               double t;
               for(t = x; t > (double)2.5F; prod *= t) {
                  --t;
               }

               ret = prod / ((double)1.0F + invGamma1pm1(t - (double)1.0F));
            } else {
               double prod = x;

               double t;
               for(t = x; t < (double)-0.5F; prod *= t) {
                  ++t;
               }

               ret = (double)1.0F / (prod * ((double)1.0F + invGamma1pm1(t)));
            }
         } else {
            double y = absX + (double)4.7421875F + (double)0.5F;
            double gammaAbs = 2.5066282746310007 / absX * FastMath.pow(y, absX + (double)0.5F) * FastMath.exp(-y) * lanczos(absX);
            if (x > (double)0.0F) {
               ret = gammaAbs;
            } else {
               ret = -Math.PI / (x * FastMath.sin(Math.PI * x) * gammaAbs);
            }
         }

         return ret;
      }
   }
}

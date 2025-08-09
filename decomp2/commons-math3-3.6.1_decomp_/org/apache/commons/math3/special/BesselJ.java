package org.apache.commons.math3.special;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class BesselJ implements UnivariateFunction {
   private static final double PI2 = 0.6366197723675814;
   private static final double TOWPI1 = (double)6.28125F;
   private static final double TWOPI2 = 0.001935307179586477;
   private static final double TWOPI = (Math.PI * 2D);
   private static final double ENTEN = 1.0E308;
   private static final double ENSIG = 1.0E16;
   private static final double RTNSIG = 1.0E-4;
   private static final double ENMTEN = 8.9E-308;
   private static final double X_MIN = (double)0.0F;
   private static final double X_MAX = (double)10000.0F;
   private static final double[] FACT = new double[]{(double)1.0F, (double)1.0F, (double)2.0F, (double)6.0F, (double)24.0F, (double)120.0F, (double)720.0F, (double)5040.0F, (double)40320.0F, (double)362880.0F, (double)3628800.0F, (double)3.99168E7F, (double)4.790016E8F, (double)6.2270208E9F, 8.71782912E10, 1.307674368E12, 2.0922789888E13, 3.55687428096E14, 6.402373705728E15, 1.21645100408832E17, 2.43290200817664E18, 5.109094217170944E19, 1.1240007277776077E21, 2.585201673888498E22, 6.204484017332394E23};
   private final double order;

   public BesselJ(double order) {
      this.order = order;
   }

   public double value(double x) throws MathIllegalArgumentException, ConvergenceException {
      return value(this.order, x);
   }

   public static double value(double order, double x) throws MathIllegalArgumentException, ConvergenceException {
      int n = (int)order;
      double alpha = order - (double)n;
      int nb = n + 1;
      BesselJResult res = rjBesl(x, alpha, nb);
      if (res.nVals >= nb) {
         return res.vals[n];
      } else if (res.nVals < 0) {
         throw new MathIllegalArgumentException(LocalizedFormats.BESSEL_FUNCTION_BAD_ARGUMENT, new Object[]{order, x});
      } else if (FastMath.abs(res.vals[res.nVals - 1]) < 1.0E-100) {
         return res.vals[n];
      } else {
         throw new ConvergenceException(LocalizedFormats.BESSEL_FUNCTION_FAILED_CONVERGENCE, new Object[]{order, x});
      }
   }

   public static BesselJResult rjBesl(double x, double alpha, int nb) {
      double[] b = new double[nb];
      int ncalc = 0;
      double alpem = (double)0.0F;
      double alp2em = (double)0.0F;
      int magx = (int)x;
      if (nb > 0 && x >= (double)0.0F && x <= (double)10000.0F && alpha >= (double)0.0F && alpha < (double)1.0F) {
         ncalc = nb;

         for(int i = 0; i < nb; ++i) {
            b[i] = (double)0.0F;
         }

         if (x < 1.0E-4) {
            double tempa = (double)1.0F;
            alpem = (double)1.0F + alpha;
            double halfx = (double)0.0F;
            if (x > 8.9E-308) {
               halfx = (double)0.5F * x;
            }

            if (alpha != (double)0.0F) {
               tempa = FastMath.pow(halfx, alpha) / (alpha * Gamma.gamma(alpha));
            }

            double tempb = (double)0.0F;
            if (x + (double)1.0F > (double)1.0F) {
               tempb = -halfx * halfx;
            }

            b[0] = tempa + tempa * tempb / alpem;
            if (x != (double)0.0F && b[0] == (double)0.0F) {
               ncalc = 0;
            }

            if (nb != 1) {
               if (x <= (double)0.0F) {
                  for(int n = 1; n < nb; ++n) {
                     b[n] = (double)0.0F;
                  }
               } else {
                  double tempc = halfx;
                  double tover = tempb != (double)0.0F ? 8.9E-308 / tempb : 1.78E-307 / x;

                  for(int n = 1; n < nb; ++n) {
                     tempa /= alpem;
                     ++alpem;
                     tempa *= tempc;
                     if (tempa <= tover * alpem) {
                        tempa = (double)0.0F;
                     }

                     b[n] = tempa + tempa * tempb / alpem;
                     if (b[n] == (double)0.0F && ncalc > n) {
                        ncalc = n;
                     }
                  }
               }
            }
         } else if (!(x > (double)25.0F) || nb > magx + 1) {
            int nbmx = nb - magx;
            int n = magx + 1;
            int nstart = 0;
            int nend = 0;
            double en = (double)2.0F * ((double)n + alpha);
            double plast = (double)1.0F;
            double p = en / x;
            double test = 2.0E16;
            boolean readyToInitialize = false;
            if (nbmx >= 3) {
               double tover = 1.0E292;
               nstart = magx + 2;
               nend = nb - 1;
               en = (double)2.0F * ((double)(nstart - 1) + alpha);

               for(int k = nstart; k <= nend; ++k) {
                  n = k;
                  en += (double)2.0F;
                  double pold = plast;
                  plast = p;
                  p = en * p / x - pold;
                  if (p > tover) {
                     tover = 1.0E308;
                     p /= tover;
                     plast /= tover;
                     double psave = p;
                     double psavel = plast;
                     nstart = k + 1;

                     do {
                        ++n;
                        en += (double)2.0F;
                        pold = plast;
                        plast = p;
                        p = en * p / x - pold;
                     } while(p <= (double)1.0F);

                     double tempb = en / x;
                     test = pold * plast * ((double)0.5F - (double)0.5F / (tempb * tempb));
                     test /= 1.0E16;
                     p = plast * tover;
                     --n;
                     en -= (double)2.0F;
                     nend = FastMath.min(nb, n);

                     for(int l = nstart; l <= nend; ++l) {
                        pold = psavel;
                        psavel = psave;
                        psave = en * psave / x - pold;
                        if (psave * psavel > test) {
                           ncalc = l - 1;
                           readyToInitialize = true;
                           break;
                        }
                     }

                     ncalc = nend;
                     readyToInitialize = true;
                     break;
                  }
               }

               if (!readyToInitialize) {
                  n = nend;
                  en = (double)2.0F * ((double)nend + alpha);
                  test = FastMath.max(test, FastMath.sqrt(plast * 1.0E16) * FastMath.sqrt((double)2.0F * p));
               }
            }

            if (!readyToInitialize) {
               do {
                  ++n;
                  en += (double)2.0F;
                  double pold = plast;
                  plast = p;
                  p = en * p / x - pold;
               } while(p < test);
            }

            ++n;
            en += (double)2.0F;
            double tempb = (double)0.0F;
            double tempa = (double)1.0F / p;
            int m = 2 * n - 4 * (n / 2);
            double sum = (double)0.0F;
            double em = (double)(n / 2);
            alpem = em - (double)1.0F + alpha;
            alp2em = (double)2.0F * em + alpha;
            if (m != 0) {
               sum = tempa * alpem * alp2em / em;
            }

            nend = n - nb;
            boolean readyToNormalize = false;
            boolean calculatedB0 = false;

            for(int l = 1; l <= nend; ++l) {
               --n;
               en -= (double)2.0F;
               double tempc = tempb;
               tempb = tempa;
               tempa = en * tempa / x - tempc;
               m = 2 - m;
               if (m != 0) {
                  --em;
                  alp2em = (double)2.0F * em + alpha;
                  if (n == 1) {
                     break;
                  }

                  alpem = em - (double)1.0F + alpha;
                  if (alpem == (double)0.0F) {
                     alpem = (double)1.0F;
                  }

                  sum = (sum + tempa * alp2em) * alpem / em;
               }
            }

            b[n - 1] = tempa;
            if (nend >= 0) {
               if (nb <= 1) {
                  alp2em = alpha;
                  if (alpha + (double)1.0F == (double)1.0F) {
                     alp2em = (double)1.0F;
                  }

                  sum += b[0] * alp2em;
                  readyToNormalize = true;
               } else {
                  --n;
                  en -= (double)2.0F;
                  b[n - 1] = en * tempa / x - tempb;
                  if (n == 1) {
                     calculatedB0 = true;
                  } else {
                     m = 2 - m;
                     if (m != 0) {
                        --em;
                        alp2em = (double)2.0F * em + alpha;
                        alpem = em - (double)1.0F + alpha;
                        if (alpem == (double)0.0F) {
                           alpem = (double)1.0F;
                        }

                        sum = (sum + b[n - 1] * alp2em) * alpem / em;
                     }
                  }
               }
            }

            if (!readyToNormalize && !calculatedB0) {
               nend = n - 2;
               if (nend != 0) {
                  for(int l = 1; l <= nend; ++l) {
                     --n;
                     en -= (double)2.0F;
                     b[n - 1] = en * b[n] / x - b[n + 1];
                     m = 2 - m;
                     if (m != 0) {
                        --em;
                        alp2em = (double)2.0F * em + alpha;
                        alpem = em - (double)1.0F + alpha;
                        if (alpem == (double)0.0F) {
                           alpem = (double)1.0F;
                        }

                        sum = (sum + b[n - 1] * alp2em) * alpem / em;
                     }
                  }
               }
            }

            if (!readyToNormalize) {
               if (!calculatedB0) {
                  b[0] = (double)2.0F * (alpha + (double)1.0F) * b[1] / x - b[2];
               }

               --em;
               alp2em = (double)2.0F * em + alpha;
               if (alp2em == (double)0.0F) {
                  alp2em = (double)1.0F;
               }

               sum += b[0] * alp2em;
            }

            if (FastMath.abs(alpha) > 1.0E-16) {
               sum *= Gamma.gamma(alpha) * FastMath.pow(x * (double)0.5F, -alpha);
            }

            tempa = 8.9E-308;
            if (sum > (double)1.0F) {
               tempa *= sum;
            }

            for(int var77 = 0; var77 < nb; ++var77) {
               if (FastMath.abs(b[var77]) < tempa) {
                  b[var77] = (double)0.0F;
               }

               b[var77] /= sum;
            }
         } else {
            double xc = FastMath.sqrt(0.6366197723675814 / x);
            double mul = (double)0.125F / x;
            double xin = mul * mul;
            int m = 0;
            if (x >= (double)130.0F) {
               m = 4;
            } else if (x >= (double)35.0F) {
               m = 8;
            } else {
               m = 11;
            }

            double xm = (double)4.0F * (double)m;
            double t = (double)((int)(x / (Math.PI * 2D) + (double)0.5F));
            double z = x - t * (double)6.28125F - t * 0.001935307179586477 - (alpha + (double)0.5F) / 0.6366197723675814;
            double vsin = FastMath.sin(z);
            double vcos = FastMath.cos(z);
            double gnu = (double)2.0F * alpha;

            for(int i = 1; i <= 2; ++i) {
               double s = (xm - (double)1.0F - gnu) * (xm - (double)1.0F + gnu) * xin * (double)0.5F;
               t = (gnu - (xm - (double)3.0F)) * (gnu + (xm - (double)3.0F));
               double capp = s * t / FACT[2 * m];
               double t1 = (gnu - (xm + (double)1.0F)) * (gnu + xm + (double)1.0F);
               double capq = s * t1 / FACT[2 * m + 1];
               double xk = xm;
               int k = 2 * m;
               t1 = t;

               for(int j = 2; j <= m; ++j) {
                  xk -= (double)4.0F;
                  s = (xk - (double)1.0F - gnu) * (xk - (double)1.0F + gnu);
                  t = (gnu - (xk - (double)3.0F)) * (gnu + (xk - (double)3.0F));
                  capp = (capp + (double)1.0F / FACT[k - 2]) * s * t * xin;
                  capq = (capq + (double)1.0F / FACT[k - 1]) * s * t1 * xin;
                  k -= 2;
                  t1 = t;
               }

               ++capp;
               capq = (capq + (double)1.0F) * (gnu * gnu - (double)1.0F) * ((double)0.125F / x);
               b[i - 1] = xc * (capp * vcos - capq * vsin);
               if (nb == 1) {
                  return new BesselJResult(MathArrays.copyOf(b, b.length), nb);
               }

               t = vsin;
               vsin = -vcos;
               vcos = t;
               gnu += (double)2.0F;
            }

            if (nb > 2) {
               gnu = (double)2.0F * alpha + (double)2.0F;

               for(int j = 2; j < nb; ++j) {
                  b[j] = gnu * b[j - 1] / x - b[j - 2];
                  gnu += (double)2.0F;
               }
            }
         }
      } else {
         if (b.length > 0) {
            b[0] = (double)0.0F;
         }

         ncalc = FastMath.min(nb, 0) - 1;
      }

      return new BesselJResult(MathArrays.copyOf(b, b.length), ncalc);
   }

   public static class BesselJResult {
      private final double[] vals;
      private final int nVals;

      public BesselJResult(double[] b, int n) {
         this.vals = MathArrays.copyOf(b, b.length);
         this.nVals = n;
      }

      public double[] getVals() {
         return MathArrays.copyOf(this.vals, this.vals.length);
      }

      public int getnVals() {
         return this.nVals;
      }
   }
}

package org.apache.commons.math3.stat.inference;

import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.math3.distribution.EnumeratedRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class KolmogorovSmirnovTest {
   protected static final int MAXIMUM_PARTIAL_SUM_COUNT = 100000;
   protected static final double KS_SUM_CAUCHY_CRITERION = 1.0E-20;
   protected static final double PG_SUM_RELATIVE_ERROR = 1.0E-10;
   /** @deprecated */
   @Deprecated
   protected static final int SMALL_SAMPLE_PRODUCT = 200;
   protected static final int LARGE_SAMPLE_PRODUCT = 10000;
   /** @deprecated */
   @Deprecated
   protected static final int MONTE_CARLO_ITERATIONS = 1000000;
   private final RandomGenerator rng;

   public KolmogorovSmirnovTest() {
      this.rng = new Well19937c();
   }

   /** @deprecated */
   @Deprecated
   public KolmogorovSmirnovTest(RandomGenerator rng) {
      this.rng = rng;
   }

   public double kolmogorovSmirnovTest(RealDistribution distribution, double[] data, boolean exact) {
      return (double)1.0F - this.cdf(this.kolmogorovSmirnovStatistic(distribution, data), data.length, exact);
   }

   public double kolmogorovSmirnovStatistic(RealDistribution distribution, double[] data) {
      this.checkArray(data);
      int n = data.length;
      double nd = (double)n;
      double[] dataCopy = new double[n];
      System.arraycopy(data, 0, dataCopy, 0, n);
      Arrays.sort(dataCopy);
      double d = (double)0.0F;

      for(int i = 1; i <= n; ++i) {
         double yi = distribution.cumulativeProbability(dataCopy[i - 1]);
         double currD = FastMath.max(yi - (double)(i - 1) / nd, (double)i / nd - yi);
         if (currD > d) {
            d = currD;
         }
      }

      return d;
   }

   public double kolmogorovSmirnovTest(double[] x, double[] y, boolean strict) {
      long lengthProduct = (long)x.length * (long)y.length;
      double[] xa = null;
      double[] ya = null;
      if (lengthProduct < 10000L && hasTies(x, y)) {
         xa = MathArrays.copyOf(x);
         ya = MathArrays.copyOf(y);
         fixTies(xa, ya);
      } else {
         xa = x;
         ya = y;
      }

      return lengthProduct < 10000L ? this.exactP(this.kolmogorovSmirnovStatistic(xa, ya), x.length, y.length, strict) : this.approximateP(this.kolmogorovSmirnovStatistic(x, y), x.length, y.length);
   }

   public double kolmogorovSmirnovTest(double[] x, double[] y) {
      return this.kolmogorovSmirnovTest(x, y, true);
   }

   public double kolmogorovSmirnovStatistic(double[] x, double[] y) {
      return (double)this.integralKolmogorovSmirnovStatistic(x, y) / (double)((long)x.length * (long)y.length);
   }

   private long integralKolmogorovSmirnovStatistic(double[] x, double[] y) {
      this.checkArray(x);
      this.checkArray(y);
      double[] sx = MathArrays.copyOf(x);
      double[] sy = MathArrays.copyOf(y);
      Arrays.sort(sx);
      Arrays.sort(sy);
      int n = sx.length;
      int m = sy.length;
      int rankX = 0;
      int rankY = 0;
      long curD = 0L;
      long supD = 0L;

      do {
         double z;
         for(z = Double.compare(sx[rankX], sy[rankY]) <= 0 ? sx[rankX] : sy[rankY]; rankX < n && Double.compare(sx[rankX], z) == 0; curD += (long)m) {
            ++rankX;
         }

         while(rankY < m && Double.compare(sy[rankY], z) == 0) {
            ++rankY;
            curD -= (long)n;
         }

         if (curD > supD) {
            supD = curD;
         } else if (-curD > supD) {
            supD = -curD;
         }
      } while(rankX < n && rankY < m);

      return supD;
   }

   public double kolmogorovSmirnovTest(RealDistribution distribution, double[] data) {
      return this.kolmogorovSmirnovTest(distribution, data, false);
   }

   public boolean kolmogorovSmirnovTest(RealDistribution distribution, double[] data, double alpha) {
      if (!(alpha <= (double)0.0F) && !(alpha > (double)0.5F)) {
         return this.kolmogorovSmirnovTest(distribution, data) < alpha;
      } else {
         throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, alpha, 0, (double)0.5F);
      }
   }

   public double bootstrap(double[] x, double[] y, int iterations, boolean strict) {
      int xLength = x.length;
      int yLength = y.length;
      double[] combined = new double[xLength + yLength];
      System.arraycopy(x, 0, combined, 0, xLength);
      System.arraycopy(y, 0, combined, xLength, yLength);
      EnumeratedRealDistribution dist = new EnumeratedRealDistribution(this.rng, combined);
      long d = this.integralKolmogorovSmirnovStatistic(x, y);
      int greaterCount = 0;
      int equalCount = 0;

      for(int i = 0; i < iterations; ++i) {
         double[] curX = dist.sample(xLength);
         double[] curY = dist.sample(yLength);
         long curD = this.integralKolmogorovSmirnovStatistic(curX, curY);
         if (curD > d) {
            ++greaterCount;
         } else if (curD == d) {
            ++equalCount;
         }
      }

      return strict ? (double)greaterCount / (double)iterations : (double)(greaterCount + equalCount) / (double)iterations;
   }

   public double bootstrap(double[] x, double[] y, int iterations) {
      return this.bootstrap(x, y, iterations, true);
   }

   public double cdf(double d, int n) throws MathArithmeticException {
      return this.cdf(d, n, false);
   }

   public double cdfExact(double d, int n) throws MathArithmeticException {
      return this.cdf(d, n, true);
   }

   public double cdf(double d, int n, boolean exact) throws MathArithmeticException {
      double ninv = (double)1.0F / (double)n;
      double ninvhalf = (double)0.5F * ninv;
      if (d <= ninvhalf) {
         return (double)0.0F;
      } else if (ninvhalf < d && d <= ninv) {
         double res = (double)1.0F;
         double f = (double)2.0F * d - ninv;

         for(int i = 1; i <= n; ++i) {
            res *= (double)i * f;
         }

         return res;
      } else if ((double)1.0F - ninv <= d && d < (double)1.0F) {
         return (double)1.0F - (double)2.0F * Math.pow((double)1.0F - d, (double)n);
      } else if ((double)1.0F <= d) {
         return (double)1.0F;
      } else if (exact) {
         return this.exactK(d, n);
      } else {
         return n <= 140 ? this.roundedK(d, n) : this.pelzGood(d, n);
      }
   }

   private double exactK(double d, int n) throws MathArithmeticException {
      int k = (int)Math.ceil((double)n * d);
      FieldMatrix<BigFraction> H = this.createExactH(d, n);
      FieldMatrix<BigFraction> Hpower = H.power(n);
      BigFraction pFrac = (BigFraction)Hpower.getEntry(k - 1, k - 1);

      for(int i = 1; i <= n; ++i) {
         pFrac = pFrac.multiply(i).divide(n);
      }

      return pFrac.bigDecimalValue(20, 4).doubleValue();
   }

   private double roundedK(double d, int n) {
      int k = (int)Math.ceil((double)n * d);
      RealMatrix H = this.createRoundedH(d, n);
      RealMatrix Hpower = H.power(n);
      double pFrac = Hpower.getEntry(k - 1, k - 1);

      for(int i = 1; i <= n; ++i) {
         pFrac *= (double)i / (double)n;
      }

      return pFrac;
   }

   public double pelzGood(double d, int n) {
      double sqrtN = FastMath.sqrt((double)n);
      double z = d * sqrtN;
      double z2 = d * d * (double)n;
      double z4 = z2 * z2;
      double z6 = z4 * z2;
      double z8 = z4 * z4;
      double ret = (double)0.0F;
      double sum = (double)0.0F;
      double increment = (double)0.0F;
      double kTerm = (double)0.0F;
      double z2Term = 9.869604401089358 / ((double)8.0F * z2);

      int k;
      for(k = 1; k < 100000; ++k) {
         kTerm = (double)(2 * k - 1);
         increment = FastMath.exp(-z2Term * kTerm * kTerm);
         sum += increment;
         if (increment <= 1.0E-10 * sum) {
            break;
         }
      }

      if (k == 100000) {
         throw new TooManyIterationsException(100000);
      } else {
         ret = sum * FastMath.sqrt((Math.PI * 2D)) / z;
         double twoZ2 = (double)2.0F * z2;
         sum = (double)0.0F;
         kTerm = (double)0.0F;
         double kTerm2 = (double)0.0F;

         for(k = 0; k < 100000; ++k) {
            kTerm = (double)k + (double)0.5F;
            kTerm2 = kTerm * kTerm;
            increment = (9.869604401089358 * kTerm2 - z2) * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
            sum += increment;
            if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum)) {
               break;
            }
         }

         if (k == 100000) {
            throw new TooManyIterationsException(100000);
         } else {
            double sqrtHalfPi = FastMath.sqrt((Math.PI / 2D));
            ret += sum * sqrtHalfPi / ((double)3.0F * z4 * sqrtN);
            double z4Term = (double)2.0F * z4;
            double z6Term = (double)6.0F * z6;
            z2Term = (double)5.0F * z2;
            double pi4 = 97.40909103400243;
            sum = (double)0.0F;
            kTerm = (double)0.0F;
            kTerm2 = (double)0.0F;

            for(k = 0; k < 100000; ++k) {
               kTerm = (double)k + (double)0.5F;
               kTerm2 = kTerm * kTerm;
               increment = (z6Term + z4Term + 9.869604401089358 * (z4Term - z2Term) * kTerm2 + 97.40909103400243 * ((double)1.0F - twoZ2) * kTerm2 * kTerm2) * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
               sum += increment;
               if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum)) {
                  break;
               }
            }

            if (k == 100000) {
               throw new TooManyIterationsException(100000);
            } else {
               double sum2 = (double)0.0F;
               kTerm2 = (double)0.0F;

               for(k = 1; k < 100000; ++k) {
                  kTerm2 = (double)(k * k);
                  increment = 9.869604401089358 * kTerm2 * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
                  sum2 += increment;
                  if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum2)) {
                     break;
                  }
               }

               if (k == 100000) {
                  throw new TooManyIterationsException(100000);
               } else {
                  ret += sqrtHalfPi / (double)n * (sum / ((double)36.0F * z2 * z2 * z2 * z) - sum2 / ((double)18.0F * z2 * z));
                  double pi6 = 961.3891935753043;
                  sum = (double)0.0F;
                  double kTerm4 = (double)0.0F;
                  double kTerm6 = (double)0.0F;

                  for(k = 0; k < 100000; ++k) {
                     kTerm = (double)k + (double)0.5F;
                     kTerm2 = kTerm * kTerm;
                     kTerm4 = kTerm2 * kTerm2;
                     kTerm6 = kTerm4 * kTerm2;
                     increment = (961.3891935753043 * kTerm6 * ((double)5.0F - (double)30.0F * z2) + 97.40909103400243 * kTerm4 * ((double)-60.0F * z2 + (double)212.0F * z4) + 9.869604401089358 * kTerm2 * ((double)135.0F * z4 - (double)96.0F * z6) - (double)30.0F * z6 - (double)90.0F * z8) * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
                     sum += increment;
                     if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum)) {
                        break;
                     }
                  }

                  if (k == 100000) {
                     throw new TooManyIterationsException(100000);
                  } else {
                     sum2 = (double)0.0F;

                     for(k = 1; k < 100000; ++k) {
                        kTerm2 = (double)(k * k);
                        kTerm4 = kTerm2 * kTerm2;
                        increment = (-97.40909103400243 * kTerm4 + 29.608813203268074 * kTerm2 * z2) * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
                        sum2 += increment;
                        if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum2)) {
                           break;
                        }
                     }

                     if (k == 100000) {
                        throw new TooManyIterationsException(100000);
                     } else {
                        return ret + sqrtHalfPi / (sqrtN * (double)n) * (sum / ((double)3240.0F * z6 * z4) + sum2 / ((double)108.0F * z6));
                     }
                  }
               }
            }
         }
      }
   }

   private FieldMatrix createExactH(double d, int n) throws NumberIsTooLargeException, FractionConversionException {
      int k = (int)Math.ceil((double)n * d);
      int m = 2 * k - 1;
      double hDouble = (double)k - (double)n * d;
      if (hDouble >= (double)1.0F) {
         throw new NumberIsTooLargeException(hDouble, (double)1.0F, false);
      } else {
         BigFraction h = null;

         try {
            h = new BigFraction(hDouble, 1.0E-20, 10000);
         } catch (FractionConversionException var15) {
            try {
               h = new BigFraction(hDouble, 1.0E-10, 10000);
            } catch (FractionConversionException var14) {
               h = new BigFraction(hDouble, 1.0E-5, 10000);
            }
         }

         BigFraction[][] Hdata = new BigFraction[m][m];

         for(int i = 0; i < m; ++i) {
            for(int j = 0; j < m; ++j) {
               if (i - j + 1 < 0) {
                  Hdata[i][j] = BigFraction.ZERO;
               } else {
                  Hdata[i][j] = BigFraction.ONE;
               }
            }
         }

         BigFraction[] hPowers = new BigFraction[m];
         hPowers[0] = h;

         for(int i = 1; i < m; ++i) {
            hPowers[i] = h.multiply(hPowers[i - 1]);
         }

         for(int i = 0; i < m; ++i) {
            Hdata[i][0] = Hdata[i][0].subtract(hPowers[i]);
            Hdata[m - 1][i] = Hdata[m - 1][i].subtract(hPowers[m - i - 1]);
         }

         if (h.compareTo(BigFraction.ONE_HALF) == 1) {
            Hdata[m - 1][0] = Hdata[m - 1][0].add(h.multiply(2).subtract(1).pow(m));
         }

         for(int i = 0; i < m; ++i) {
            for(int j = 0; j < i + 1; ++j) {
               if (i - j + 1 > 0) {
                  for(int g = 2; g <= i - j + 1; ++g) {
                     Hdata[i][j] = Hdata[i][j].divide(g);
                  }
               }
            }
         }

         return new Array2DRowFieldMatrix(BigFractionField.getInstance(), Hdata);
      }
   }

   private RealMatrix createRoundedH(double d, int n) throws NumberIsTooLargeException {
      int k = (int)Math.ceil((double)n * d);
      int m = 2 * k - 1;
      double h = (double)k - (double)n * d;
      if (h >= (double)1.0F) {
         throw new NumberIsTooLargeException(h, (double)1.0F, false);
      } else {
         double[][] Hdata = new double[m][m];

         for(int i = 0; i < m; ++i) {
            for(int j = 0; j < m; ++j) {
               if (i - j + 1 < 0) {
                  Hdata[i][j] = (double)0.0F;
               } else {
                  Hdata[i][j] = (double)1.0F;
               }
            }
         }

         double[] hPowers = new double[m];
         hPowers[0] = h;

         for(int i = 1; i < m; ++i) {
            hPowers[i] = h * hPowers[i - 1];
         }

         for(int i = 0; i < m; ++i) {
            Hdata[i][0] -= hPowers[i];
            Hdata[m - 1][i] -= hPowers[m - i - 1];
         }

         if (Double.compare(h, (double)0.5F) > 0) {
            Hdata[m - 1][0] += FastMath.pow((double)2.0F * h - (double)1.0F, m);
         }

         for(int i = 0; i < m; ++i) {
            for(int j = 0; j < i + 1; ++j) {
               if (i - j + 1 > 0) {
                  for(int g = 2; g <= i - j + 1; ++g) {
                     Hdata[i][j] /= (double)g;
                  }
               }
            }
         }

         return MatrixUtils.createRealMatrix(Hdata);
      }
   }

   private void checkArray(double[] array) {
      if (array == null) {
         throw new NullArgumentException(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
      } else if (array.length < 2) {
         throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, new Object[]{array.length, 2});
      }
   }

   public double ksSum(double t, double tolerance, int maxIterations) {
      if (t == (double)0.0F) {
         return (double)0.0F;
      } else {
         double x = (double)-2.0F * t * t;
         int sign = -1;
         long i = 1L;
         double partialSum = (double)0.5F;

         for(double delta = (double)1.0F; delta > tolerance && i < (long)maxIterations; ++i) {
            delta = FastMath.exp(x * (double)i * (double)i);
            partialSum += (double)sign * delta;
            sign *= -1;
         }

         if (i == (long)maxIterations) {
            throw new TooManyIterationsException(maxIterations);
         } else {
            return partialSum * (double)2.0F;
         }
      }
   }

   private static long calculateIntegralD(double d, int n, int m, boolean strict) {
      double tol = 1.0E-12;
      long nm = (long)n * (long)m;
      long upperBound = (long)FastMath.ceil((d - 1.0E-12) * (double)nm);
      long lowerBound = (long)FastMath.floor((d + 1.0E-12) * (double)nm);
      return strict && lowerBound == upperBound ? upperBound + 1L : upperBound;
   }

   public double exactP(double d, int n, int m, boolean strict) {
      return (double)1.0F - n(m, n, m, n, calculateIntegralD(d, m, n, strict), strict) / CombinatoricsUtils.binomialCoefficientDouble(n + m, m);
   }

   public double approximateP(double d, int n, int m) {
      double dm = (double)m;
      double dn = (double)n;
      return (double)1.0F - this.ksSum(d * FastMath.sqrt(dm * dn / (dm + dn)), 1.0E-20, 100000);
   }

   static void fillBooleanArrayRandomlyWithFixedNumberTrueValues(boolean[] b, int numberOfTrueValues, RandomGenerator rng) {
      Arrays.fill(b, true);

      for(int k = numberOfTrueValues; k < b.length; ++k) {
         int r = rng.nextInt(k + 1);
         b[b[r] ? r : k] = false;
      }

   }

   public double monteCarloP(double d, int n, int m, boolean strict, int iterations) {
      return this.integralMonteCarloP(calculateIntegralD(d, n, m, strict), n, m, iterations);
   }

   private double integralMonteCarloP(long d, int n, int m, int iterations) {
      int nn = FastMath.max(n, m);
      int mm = FastMath.min(n, m);
      int sum = nn + mm;
      int tail = 0;
      boolean[] b = new boolean[sum];

      for(int i = 0; i < iterations; ++i) {
         fillBooleanArrayRandomlyWithFixedNumberTrueValues(b, nn, this.rng);
         long curD = 0L;

         for(int j = 0; j < b.length; ++j) {
            if (b[j]) {
               curD += (long)mm;
               if (curD >= d) {
                  ++tail;
                  break;
               }
            } else {
               curD -= (long)nn;
               if (curD <= -d) {
                  ++tail;
                  break;
               }
            }
         }
      }

      return (double)tail / (double)iterations;
   }

   private static void fixTies(double[] x, double[] y) {
      double[] values = MathArrays.unique(MathArrays.concatenate(x, y));
      if (values.length != x.length + y.length) {
         double minDelta = (double)1.0F;
         double prev = values[0];
         double delta = (double)1.0F;

         for(int i = 1; i < values.length; ++i) {
            delta = prev - values[i];
            if (delta < minDelta) {
               minDelta = delta;
            }

            prev = values[i];
         }

         minDelta /= (double)2.0F;
         RealDistribution dist = new UniformRealDistribution(new JDKRandomGenerator(100), -minDelta, minDelta);
         int ct = 0;
         boolean ties = true;

         do {
            jitter(x, dist);
            jitter(y, dist);
            ties = hasTies(x, y);
            ++ct;
         } while(ties && ct < 1000);

         if (ties) {
            throw new MathInternalError();
         }
      }
   }

   private static boolean hasTies(double[] x, double[] y) {
      HashSet<Double> values = new HashSet();

      for(int i = 0; i < x.length; ++i) {
         if (!values.add(x[i])) {
            return true;
         }
      }

      for(int i = 0; i < y.length; ++i) {
         if (!values.add(y[i])) {
            return true;
         }
      }

      return false;
   }

   private static void jitter(double[] data, RealDistribution dist) {
      for(int i = 0; i < data.length; ++i) {
         data[i] += dist.sample();
      }

   }

   private static int c(int i, int j, int m, int n, long cmn, boolean strict) {
      if (strict) {
         return FastMath.abs((long)i * (long)n - (long)j * (long)m) <= cmn ? 1 : 0;
      } else {
         return FastMath.abs((long)i * (long)n - (long)j * (long)m) < cmn ? 1 : 0;
      }
   }

   private static double n(int i, int j, int m, int n, long cnm, boolean strict) {
      double[] lag = new double[n];
      double last = (double)0.0F;

      for(int k = 0; k < n; ++k) {
         lag[k] = (double)c(0, k + 1, m, n, cnm, strict);
      }

      for(int k = 1; k <= i; ++k) {
         last = (double)c(k, 0, m, n, cnm, strict);

         for(int l = 1; l <= j; ++l) {
            lag[l - 1] = (double)c(k, l, m, n, cnm, strict) * (last + lag[l - 1]);
            last = lag[l - 1];
         }
      }

      return last;
   }
}

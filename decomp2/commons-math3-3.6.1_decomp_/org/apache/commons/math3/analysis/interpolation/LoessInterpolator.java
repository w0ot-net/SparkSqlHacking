package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class LoessInterpolator implements UnivariateInterpolator, Serializable {
   public static final double DEFAULT_BANDWIDTH = 0.3;
   public static final int DEFAULT_ROBUSTNESS_ITERS = 2;
   public static final double DEFAULT_ACCURACY = 1.0E-12;
   private static final long serialVersionUID = 5204927143605193821L;
   private final double bandwidth;
   private final int robustnessIters;
   private final double accuracy;

   public LoessInterpolator() {
      this.bandwidth = 0.3;
      this.robustnessIters = 2;
      this.accuracy = 1.0E-12;
   }

   public LoessInterpolator(double bandwidth, int robustnessIters) {
      this(bandwidth, robustnessIters, 1.0E-12);
   }

   public LoessInterpolator(double bandwidth, int robustnessIters, double accuracy) throws OutOfRangeException, NotPositiveException {
      if (!(bandwidth < (double)0.0F) && !(bandwidth > (double)1.0F)) {
         this.bandwidth = bandwidth;
         if (robustnessIters < 0) {
            throw new NotPositiveException(LocalizedFormats.ROBUSTNESS_ITERATIONS, robustnessIters);
         } else {
            this.robustnessIters = robustnessIters;
            this.accuracy = accuracy;
         }
      } else {
         throw new OutOfRangeException(LocalizedFormats.BANDWIDTH, bandwidth, 0, 1);
      }
   }

   public final PolynomialSplineFunction interpolate(double[] xval, double[] yval) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
      return (new SplineInterpolator()).interpolate(xval, this.smooth(xval, yval));
   }

   public final double[] smooth(double[] xval, double[] yval, double[] weights) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
      if (xval.length != yval.length) {
         throw new DimensionMismatchException(xval.length, yval.length);
      } else {
         int n = xval.length;
         if (n == 0) {
            throw new NoDataException();
         } else {
            checkAllFiniteReal(xval);
            checkAllFiniteReal(yval);
            checkAllFiniteReal(weights);
            MathArrays.checkOrder(xval);
            if (n == 1) {
               return new double[]{yval[0]};
            } else if (n == 2) {
               return new double[]{yval[0], yval[1]};
            } else {
               int bandwidthInPoints = (int)(this.bandwidth * (double)n);
               if (bandwidthInPoints < 2) {
                  throw new NumberIsTooSmallException(LocalizedFormats.BANDWIDTH, bandwidthInPoints, 2, true);
               } else {
                  double[] res = new double[n];
                  double[] residuals = new double[n];
                  double[] sortedResiduals = new double[n];
                  double[] robustnessWeights = new double[n];
                  Arrays.fill(robustnessWeights, (double)1.0F);

                  for(int iter = 0; iter <= this.robustnessIters; ++iter) {
                     int[] bandwidthInterval = new int[]{0, bandwidthInPoints - 1};

                     for(int i = 0; i < n; ++i) {
                        double x = xval[i];
                        if (i > 0) {
                           updateBandwidthInterval(xval, weights, i, bandwidthInterval);
                        }

                        int ileft = bandwidthInterval[0];
                        int iright = bandwidthInterval[1];
                        int edge;
                        if (xval[i] - xval[ileft] > xval[iright] - xval[i]) {
                           edge = ileft;
                        } else {
                           edge = iright;
                        }

                        double sumWeights = (double)0.0F;
                        double sumX = (double)0.0F;
                        double sumXSquared = (double)0.0F;
                        double sumY = (double)0.0F;
                        double sumXY = (double)0.0F;
                        double denom = FastMath.abs((double)1.0F / (xval[edge] - x));

                        for(int k = ileft; k <= iright; ++k) {
                           double xk = xval[k];
                           double yk = yval[k];
                           double dist = k < i ? x - xk : xk - x;
                           double w = tricube(dist * denom) * robustnessWeights[k] * weights[k];
                           double xkw = xk * w;
                           sumWeights += w;
                           sumX += xkw;
                           sumXSquared += xk * xkw;
                           sumY += yk * w;
                           sumXY += yk * xkw;
                        }

                        double meanX = sumX / sumWeights;
                        double meanY = sumY / sumWeights;
                        double meanXY = sumXY / sumWeights;
                        double meanXSquared = sumXSquared / sumWeights;
                        double beta;
                        if (FastMath.sqrt(FastMath.abs(meanXSquared - meanX * meanX)) < this.accuracy) {
                           beta = (double)0.0F;
                        } else {
                           beta = (meanXY - meanX * meanY) / (meanXSquared - meanX * meanX);
                        }

                        double alpha = meanY - beta * meanX;
                        res[i] = beta * x + alpha;
                        residuals[i] = FastMath.abs(yval[i] - res[i]);
                     }

                     if (iter == this.robustnessIters) {
                        break;
                     }

                     System.arraycopy(residuals, 0, sortedResiduals, 0, n);
                     Arrays.sort(sortedResiduals);
                     double medianResidual = sortedResiduals[n / 2];
                     if (FastMath.abs(medianResidual) < this.accuracy) {
                        break;
                     }

                     for(int i = 0; i < n; ++i) {
                        double arg = residuals[i] / ((double)6.0F * medianResidual);
                        if (arg >= (double)1.0F) {
                           robustnessWeights[i] = (double)0.0F;
                        } else {
                           double w = (double)1.0F - arg * arg;
                           robustnessWeights[i] = w * w;
                        }
                     }
                  }

                  return res;
               }
            }
         }
      }
   }

   public final double[] smooth(double[] xval, double[] yval) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
      if (xval.length != yval.length) {
         throw new DimensionMismatchException(xval.length, yval.length);
      } else {
         double[] unitWeights = new double[xval.length];
         Arrays.fill(unitWeights, (double)1.0F);
         return this.smooth(xval, yval, unitWeights);
      }
   }

   private static void updateBandwidthInterval(double[] xval, double[] weights, int i, int[] bandwidthInterval) {
      int left = bandwidthInterval[0];
      int right = bandwidthInterval[1];
      int nextRight = nextNonzero(weights, right);
      if (nextRight < xval.length && xval[nextRight] - xval[i] < xval[i] - xval[left]) {
         int nextLeft = nextNonzero(weights, bandwidthInterval[0]);
         bandwidthInterval[0] = nextLeft;
         bandwidthInterval[1] = nextRight;
      }

   }

   private static int nextNonzero(double[] weights, int i) {
      int j;
      for(j = i + 1; j < weights.length && weights[j] == (double)0.0F; ++j) {
      }

      return j;
   }

   private static double tricube(double x) {
      double absX = FastMath.abs(x);
      if (absX >= (double)1.0F) {
         return (double)0.0F;
      } else {
         double tmp = (double)1.0F - absX * absX * absX;
         return tmp * tmp * tmp;
      }
   }

   private static void checkAllFiniteReal(double[] values) {
      for(int i = 0; i < values.length; ++i) {
         MathUtils.checkFinite(values[i]);
      }

   }
}

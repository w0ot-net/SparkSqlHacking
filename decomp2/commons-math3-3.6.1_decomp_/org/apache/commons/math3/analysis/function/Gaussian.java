package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class Gaussian implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
   private final double mean;
   private final double is;
   private final double i2s2;
   private final double norm;

   public Gaussian(double norm, double mean, double sigma) throws NotStrictlyPositiveException {
      if (sigma <= (double)0.0F) {
         throw new NotStrictlyPositiveException(sigma);
      } else {
         this.norm = norm;
         this.mean = mean;
         this.is = (double)1.0F / sigma;
         this.i2s2 = (double)0.5F * this.is * this.is;
      }
   }

   public Gaussian(double mean, double sigma) throws NotStrictlyPositiveException {
      this((double)1.0F / (sigma * FastMath.sqrt((Math.PI * 2D))), mean, sigma);
   }

   public Gaussian() {
      this((double)0.0F, (double)1.0F);
   }

   public double value(double x) {
      return value(x - this.mean, this.norm, this.i2s2);
   }

   /** @deprecated */
   @Deprecated
   public UnivariateFunction derivative() {
      return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
   }

   private static double value(double xMinusMean, double norm, double i2s2) {
      return norm * FastMath.exp(-xMinusMean * xMinusMean * i2s2);
   }

   public DerivativeStructure value(DerivativeStructure t) throws DimensionMismatchException {
      double u = this.is * (t.getValue() - this.mean);
      double[] f = new double[t.getOrder() + 1];
      double[] p = new double[f.length];
      p[0] = (double)1.0F;
      double u2 = u * u;
      double coeff = this.norm * FastMath.exp((double)-0.5F * u2);
      if (coeff <= Precision.SAFE_MIN) {
         Arrays.fill(f, (double)0.0F);
      } else {
         f[0] = coeff;

         for(int n = 1; n < f.length; ++n) {
            double v = (double)0.0F;
            p[n] = -p[n - 1];

            for(int k = n; k >= 0; k -= 2) {
               v = v * u2 + p[k];
               if (k > 2) {
                  p[k - 2] = (double)(k - 1) * p[k - 1] - p[k - 3];
               } else if (k == 2) {
                  p[0] = p[1];
               }
            }

            if ((n & 1) == 1) {
               v *= u;
            }

            coeff *= this.is;
            f[n] = coeff * v;
         }
      }

      return t.compose(f);
   }

   public static class Parametric implements ParametricUnivariateFunction {
      public double value(double x, double... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
         this.validateParameters(param);
         double diff = x - param[1];
         double i2s2 = (double)1.0F / ((double)2.0F * param[2] * param[2]);
         return Gaussian.value(diff, param[0], i2s2);
      }

      public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
         this.validateParameters(param);
         double norm = param[0];
         double diff = x - param[1];
         double sigma = param[2];
         double i2s2 = (double)1.0F / ((double)2.0F * sigma * sigma);
         double n = Gaussian.value(diff, (double)1.0F, i2s2);
         double m = norm * n * (double)2.0F * i2s2 * diff;
         double s = m * diff / sigma;
         return new double[]{n, m, s};
      }

      private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
         if (param == null) {
            throw new NullArgumentException();
         } else if (param.length != 3) {
            throw new DimensionMismatchException(param.length, 3);
         } else if (param[2] <= (double)0.0F) {
            throw new NotStrictlyPositiveException(param[2]);
         }
      }
   }
}

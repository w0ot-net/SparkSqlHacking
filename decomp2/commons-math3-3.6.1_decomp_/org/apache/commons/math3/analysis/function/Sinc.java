package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

public class Sinc implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
   private static final double SHORTCUT = 0.006;
   private final boolean normalized;

   public Sinc() {
      this(false);
   }

   public Sinc(boolean normalized) {
      this.normalized = normalized;
   }

   public double value(double x) {
      double scaledX = this.normalized ? Math.PI * x : x;
      if (FastMath.abs(scaledX) <= 0.006) {
         double scaledX2 = scaledX * scaledX;
         return ((scaledX2 - (double)20.0F) * scaledX2 + (double)120.0F) / (double)120.0F;
      } else {
         return FastMath.sin(scaledX) / scaledX;
      }
   }

   /** @deprecated */
   @Deprecated
   public UnivariateFunction derivative() {
      return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
   }

   public DerivativeStructure value(DerivativeStructure t) throws DimensionMismatchException {
      double scaledX = (this.normalized ? Math.PI : (double)1.0F) * t.getValue();
      double scaledX2 = scaledX * scaledX;
      double[] f = new double[t.getOrder() + 1];
      if (FastMath.abs(scaledX) <= 0.006) {
         for(int i = 0; i < f.length; ++i) {
            int k = i / 2;
            if ((i & 1) == 0) {
               f[i] = (double)((k & 1) == 0 ? 1 : -1) * ((double)1.0F / (double)(i + 1) - scaledX2 * ((double)1.0F / (double)(2 * i + 6) - scaledX2 / (double)(24 * i + 120)));
            } else {
               f[i] = ((k & 1) == 0 ? -scaledX : scaledX) * ((double)1.0F / (double)(i + 2) - scaledX2 * ((double)1.0F / (double)(6 * i + 24) - scaledX2 / (double)(120 * i + 720)));
            }
         }
      } else {
         double inv = (double)1.0F / scaledX;
         double cos = FastMath.cos(scaledX);
         double sin = FastMath.sin(scaledX);
         f[0] = inv * sin;
         double[] sc = new double[f.length];
         sc[0] = (double)1.0F;
         double coeff = inv;

         for(int n = 1; n < f.length; ++n) {
            double s = (double)0.0F;
            double c = (double)0.0F;
            int kStart;
            if ((n & 1) == 0) {
               sc[n] = (double)0.0F;
               kStart = n;
            } else {
               sc[n] = sc[n - 1];
               c = sc[n];
               kStart = n - 1;
            }

            for(int k = kStart; k > 1; k -= 2) {
               sc[k] = (double)(k - n) * sc[k] - sc[k - 1];
               s = s * scaledX2 + sc[k];
               sc[k - 1] = (double)(k - 1 - n) * sc[k - 1] + sc[k - 2];
               c = c * scaledX2 + sc[k - 1];
            }

            sc[0] *= (double)(-n);
            s = s * scaledX2 + sc[0];
            coeff *= inv;
            f[n] = coeff * (s * sin + c * scaledX * cos);
         }
      }

      if (this.normalized) {
         double scale = Math.PI;

         for(int i = 1; i < f.length; ++i) {
            f[i] *= scale;
            scale *= Math.PI;
         }
      }

      return t.compose(f);
   }
}

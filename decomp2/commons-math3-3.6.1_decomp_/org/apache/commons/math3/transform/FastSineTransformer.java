package org.apache.commons.math3.transform;

import java.io.Serializable;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

public class FastSineTransformer implements RealTransformer, Serializable {
   static final long serialVersionUID = 20120211L;
   private final DstNormalization normalization;

   public FastSineTransformer(DstNormalization normalization) {
      this.normalization = normalization;
   }

   public double[] transform(double[] f, TransformType type) {
      if (this.normalization == DstNormalization.ORTHOGONAL_DST_I) {
         double s = FastMath.sqrt((double)2.0F / (double)f.length);
         return TransformUtils.scaleArray(this.fst(f), s);
      } else if (type == TransformType.FORWARD) {
         return this.fst(f);
      } else {
         double s = (double)2.0F / (double)f.length;
         return TransformUtils.scaleArray(this.fst(f), s);
      }
   }

   public double[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) {
      double[] data = FunctionUtils.sample(f, min, max, n);
      data[0] = (double)0.0F;
      return this.transform(data, type);
   }

   protected double[] fst(double[] f) throws MathIllegalArgumentException {
      double[] transformed = new double[f.length];
      if (!ArithmeticUtils.isPowerOfTwo((long)f.length)) {
         throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, new Object[]{f.length});
      } else if (f[0] != (double)0.0F) {
         throw new MathIllegalArgumentException(LocalizedFormats.FIRST_ELEMENT_NOT_ZERO, new Object[]{f[0]});
      } else {
         int n = f.length;
         if (n == 1) {
            transformed[0] = (double)0.0F;
            return transformed;
         } else {
            double[] x = new double[n];
            x[0] = (double)0.0F;
            x[n >> 1] = (double)2.0F * f[n >> 1];

            for(int i = 1; i < n >> 1; ++i) {
               double a = FastMath.sin((double)i * Math.PI / (double)n) * (f[i] + f[n - i]);
               double b = (double)0.5F * (f[i] - f[n - i]);
               x[i] = a + b;
               x[n - i] = a - b;
            }

            FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
            Complex[] y = transformer.transform(x, TransformType.FORWARD);
            transformed[0] = (double)0.0F;
            transformed[1] = (double)0.5F * y[0].getReal();

            for(int i = 1; i < n >> 1; ++i) {
               transformed[2 * i] = -y[i].getImaginary();
               transformed[2 * i + 1] = y[i].getReal() + transformed[2 * i - 1];
            }

            return transformed;
         }
      }
   }
}

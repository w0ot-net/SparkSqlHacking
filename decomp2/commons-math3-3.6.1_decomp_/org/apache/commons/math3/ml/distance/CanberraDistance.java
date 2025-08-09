package org.apache.commons.math3.ml.distance;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class CanberraDistance implements DistanceMeasure {
   private static final long serialVersionUID = -6972277381587032228L;

   public double compute(double[] a, double[] b) throws DimensionMismatchException {
      MathArrays.checkEqualLength(a, b);
      double sum = (double)0.0F;

      for(int i = 0; i < a.length; ++i) {
         double num = FastMath.abs(a[i] - b[i]);
         double denom = FastMath.abs(a[i]) + FastMath.abs(b[i]);
         sum += num == (double)0.0F && denom == (double)0.0F ? (double)0.0F : num / denom;
      }

      return sum;
   }
}

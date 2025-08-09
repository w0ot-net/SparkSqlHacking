package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

class BicubicFunction implements BivariateFunction {
   private static final short N = 4;
   private final double[][] a = new double[4][4];

   BicubicFunction(double[] coeff) {
      for(int j = 0; j < 4; ++j) {
         double[] aJ = this.a[j];

         for(int i = 0; i < 4; ++i) {
            aJ[i] = coeff[i * 4 + j];
         }
      }

   }

   public double value(double x, double y) {
      if (!(x < (double)0.0F) && !(x > (double)1.0F)) {
         if (!(y < (double)0.0F) && !(y > (double)1.0F)) {
            double x2 = x * x;
            double x3 = x2 * x;
            double[] pX = new double[]{(double)1.0F, x, x2, x3};
            double y2 = y * y;
            double y3 = y2 * y;
            double[] pY = new double[]{(double)1.0F, y, y2, y3};
            return this.apply(pX, pY, this.a);
         } else {
            throw new OutOfRangeException(y, 0, 1);
         }
      } else {
         throw new OutOfRangeException(x, 0, 1);
      }
   }

   private double apply(double[] pX, double[] pY, double[][] coeff) {
      double result = (double)0.0F;

      for(int i = 0; i < 4; ++i) {
         double r = MathArrays.linearCombination(coeff[i], pY);
         result += r * pX[i];
      }

      return result;
   }
}

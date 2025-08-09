package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.TrivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

class TricubicFunction implements TrivariateFunction {
   private static final short N = 4;
   private final double[][][] a = new double[4][4][4];

   TricubicFunction(double[] aV) {
      for(int i = 0; i < 4; ++i) {
         for(int j = 0; j < 4; ++j) {
            for(int k = 0; k < 4; ++k) {
               this.a[i][j][k] = aV[i + 4 * (j + 4 * k)];
            }
         }
      }

   }

   public double value(double x, double y, double z) throws OutOfRangeException {
      if (!(x < (double)0.0F) && !(x > (double)1.0F)) {
         if (!(y < (double)0.0F) && !(y > (double)1.0F)) {
            if (!(z < (double)0.0F) && !(z > (double)1.0F)) {
               double x2 = x * x;
               double x3 = x2 * x;
               double[] pX = new double[]{(double)1.0F, x, x2, x3};
               double y2 = y * y;
               double y3 = y2 * y;
               double[] pY = new double[]{(double)1.0F, y, y2, y3};
               double z2 = z * z;
               double z3 = z2 * z;
               double[] pZ = new double[]{(double)1.0F, z, z2, z3};
               double result = (double)0.0F;

               for(int i = 0; i < 4; ++i) {
                  for(int j = 0; j < 4; ++j) {
                     for(int k = 0; k < 4; ++k) {
                        result += this.a[i][j][k] * pX[i] * pY[j] * pZ[k];
                     }
                  }
               }

               return result;
            } else {
               throw new OutOfRangeException(z, 0, 1);
            }
         } else {
            throw new OutOfRangeException(y, 0, 1);
         }
      } else {
         throw new OutOfRangeException(x, 0, 1);
      }
   }
}

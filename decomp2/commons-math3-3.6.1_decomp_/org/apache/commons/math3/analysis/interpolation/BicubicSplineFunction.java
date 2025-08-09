package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

class BicubicSplineFunction implements BivariateFunction {
   private static final short N = 4;
   private final double[][] a;
   private final BivariateFunction partialDerivativeX;
   private final BivariateFunction partialDerivativeY;
   private final BivariateFunction partialDerivativeXX;
   private final BivariateFunction partialDerivativeYY;
   private final BivariateFunction partialDerivativeXY;

   BicubicSplineFunction(double[] coeff) {
      this(coeff, false);
   }

   BicubicSplineFunction(double[] coeff, boolean initializeDerivatives) {
      this.a = new double[4][4];

      for(int i = 0; i < 4; ++i) {
         for(int j = 0; j < 4; ++j) {
            this.a[i][j] = coeff[i * 4 + j];
         }
      }

      if (initializeDerivatives) {
         final double[][] aX = new double[4][4];
         final double[][] aY = new double[4][4];
         final double[][] aXX = new double[4][4];
         final double[][] aYY = new double[4][4];
         final double[][] aXY = new double[4][4];

         for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
               double c = this.a[i][j];
               aX[i][j] = (double)i * c;
               aY[i][j] = (double)j * c;
               aXX[i][j] = (double)(i - 1) * aX[i][j];
               aYY[i][j] = (double)(j - 1) * aY[i][j];
               aXY[i][j] = (double)j * aX[i][j];
            }
         }

         this.partialDerivativeX = new BivariateFunction() {
            public double value(double x, double y) {
               double x2 = x * x;
               double[] pX = new double[]{(double)0.0F, (double)1.0F, x, x2};
               double y2 = y * y;
               double y3 = y2 * y;
               double[] pY = new double[]{(double)1.0F, y, y2, y3};
               return BicubicSplineFunction.this.apply(pX, pY, aX);
            }
         };
         this.partialDerivativeY = new BivariateFunction() {
            public double value(double x, double y) {
               double x2 = x * x;
               double x3 = x2 * x;
               double[] pX = new double[]{(double)1.0F, x, x2, x3};
               double y2 = y * y;
               double[] pY = new double[]{(double)0.0F, (double)1.0F, y, y2};
               return BicubicSplineFunction.this.apply(pX, pY, aY);
            }
         };
         this.partialDerivativeXX = new BivariateFunction() {
            public double value(double x, double y) {
               double[] pX = new double[]{(double)0.0F, (double)0.0F, (double)1.0F, x};
               double y2 = y * y;
               double y3 = y2 * y;
               double[] pY = new double[]{(double)1.0F, y, y2, y3};
               return BicubicSplineFunction.this.apply(pX, pY, aXX);
            }
         };
         this.partialDerivativeYY = new BivariateFunction() {
            public double value(double x, double y) {
               double x2 = x * x;
               double x3 = x2 * x;
               double[] pX = new double[]{(double)1.0F, x, x2, x3};
               double[] pY = new double[]{(double)0.0F, (double)0.0F, (double)1.0F, y};
               return BicubicSplineFunction.this.apply(pX, pY, aYY);
            }
         };
         this.partialDerivativeXY = new BivariateFunction() {
            public double value(double x, double y) {
               double x2 = x * x;
               double[] pX = new double[]{(double)0.0F, (double)1.0F, x, x2};
               double y2 = y * y;
               double[] pY = new double[]{(double)0.0F, (double)1.0F, y, y2};
               return BicubicSplineFunction.this.apply(pX, pY, aXY);
            }
         };
      } else {
         this.partialDerivativeX = null;
         this.partialDerivativeY = null;
         this.partialDerivativeXX = null;
         this.partialDerivativeYY = null;
         this.partialDerivativeXY = null;
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
         for(int j = 0; j < 4; ++j) {
            result += coeff[i][j] * pX[i] * pY[j];
         }
      }

      return result;
   }

   public BivariateFunction partialDerivativeX() {
      return this.partialDerivativeX;
   }

   public BivariateFunction partialDerivativeY() {
      return this.partialDerivativeY;
   }

   public BivariateFunction partialDerivativeXX() {
      return this.partialDerivativeXX;
   }

   public BivariateFunction partialDerivativeYY() {
      return this.partialDerivativeYY;
   }

   public BivariateFunction partialDerivativeXY() {
      return this.partialDerivativeXY;
   }
}

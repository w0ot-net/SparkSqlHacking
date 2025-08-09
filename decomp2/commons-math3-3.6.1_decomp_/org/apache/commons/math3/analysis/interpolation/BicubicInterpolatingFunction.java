package org.apache.commons.math3.analysis.interpolation;

import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

public class BicubicInterpolatingFunction implements BivariateFunction {
   private static final int NUM_COEFF = 16;
   private static final double[][] AINV = new double[][]{{(double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F}, {(double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F}, {(double)-3.0F, (double)3.0F, (double)0.0F, (double)0.0F, (double)-2.0F, (double)-1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F}, {(double)2.0F, (double)-2.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F}, {(double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F}, {(double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F}, {(double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)-3.0F, (double)3.0F, (double)0.0F, (double)0.0F, (double)-2.0F, (double)-1.0F, (double)0.0F, (double)0.0F}, {(double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)2.0F, (double)-2.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)1.0F, (double)0.0F, (double)0.0F}, {(double)-3.0F, (double)0.0F, (double)3.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)-2.0F, (double)0.0F, (double)-1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F}, {(double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)-3.0F, (double)0.0F, (double)3.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)-2.0F, (double)0.0F, (double)-1.0F, (double)0.0F}, {(double)9.0F, (double)-9.0F, (double)-9.0F, (double)9.0F, (double)6.0F, (double)3.0F, (double)-6.0F, (double)-3.0F, (double)6.0F, (double)-6.0F, (double)3.0F, (double)-3.0F, (double)4.0F, (double)2.0F, (double)2.0F, (double)1.0F}, {(double)-6.0F, (double)6.0F, (double)6.0F, (double)-6.0F, (double)-3.0F, (double)-3.0F, (double)3.0F, (double)3.0F, (double)-4.0F, (double)4.0F, (double)-2.0F, (double)2.0F, (double)-2.0F, (double)-2.0F, (double)-1.0F, (double)-1.0F}, {(double)2.0F, (double)0.0F, (double)-2.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F}, {(double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)2.0F, (double)0.0F, (double)-2.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)1.0F, (double)0.0F}, {(double)-6.0F, (double)6.0F, (double)6.0F, (double)-6.0F, (double)-4.0F, (double)-2.0F, (double)4.0F, (double)2.0F, (double)-3.0F, (double)3.0F, (double)-3.0F, (double)3.0F, (double)-2.0F, (double)-1.0F, (double)-2.0F, (double)-1.0F}, {(double)4.0F, (double)-4.0F, (double)-4.0F, (double)4.0F, (double)2.0F, (double)2.0F, (double)-2.0F, (double)-2.0F, (double)2.0F, (double)-2.0F, (double)2.0F, (double)-2.0F, (double)1.0F, (double)1.0F, (double)1.0F, (double)1.0F}};
   private final double[] xval;
   private final double[] yval;
   private final BicubicFunction[][] splines;

   public BicubicInterpolatingFunction(double[] x, double[] y, double[][] f, double[][] dFdX, double[][] dFdY, double[][] d2FdXdY) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
      int xLen = x.length;
      int yLen = y.length;
      if (xLen != 0 && yLen != 0 && f.length != 0 && f[0].length != 0) {
         if (xLen != f.length) {
            throw new DimensionMismatchException(xLen, f.length);
         } else if (xLen != dFdX.length) {
            throw new DimensionMismatchException(xLen, dFdX.length);
         } else if (xLen != dFdY.length) {
            throw new DimensionMismatchException(xLen, dFdY.length);
         } else if (xLen != d2FdXdY.length) {
            throw new DimensionMismatchException(xLen, d2FdXdY.length);
         } else {
            MathArrays.checkOrder(x);
            MathArrays.checkOrder(y);
            this.xval = (double[])(([D)x).clone();
            this.yval = (double[])(([D)y).clone();
            int lastI = xLen - 1;
            int lastJ = yLen - 1;
            this.splines = new BicubicFunction[lastI][lastJ];

            for(int i = 0; i < lastI; ++i) {
               if (f[i].length != yLen) {
                  throw new DimensionMismatchException(f[i].length, yLen);
               }

               if (dFdX[i].length != yLen) {
                  throw new DimensionMismatchException(dFdX[i].length, yLen);
               }

               if (dFdY[i].length != yLen) {
                  throw new DimensionMismatchException(dFdY[i].length, yLen);
               }

               if (d2FdXdY[i].length != yLen) {
                  throw new DimensionMismatchException(d2FdXdY[i].length, yLen);
               }

               int ip1 = i + 1;
               double xR = this.xval[ip1] - this.xval[i];

               for(int j = 0; j < lastJ; ++j) {
                  int jp1 = j + 1;
                  double yR = this.yval[jp1] - this.yval[j];
                  double xRyR = xR * yR;
                  double[] beta = new double[]{f[i][j], f[ip1][j], f[i][jp1], f[ip1][jp1], dFdX[i][j] * xR, dFdX[ip1][j] * xR, dFdX[i][jp1] * xR, dFdX[ip1][jp1] * xR, dFdY[i][j] * yR, dFdY[ip1][j] * yR, dFdY[i][jp1] * yR, dFdY[ip1][jp1] * yR, d2FdXdY[i][j] * xRyR, d2FdXdY[ip1][j] * xRyR, d2FdXdY[i][jp1] * xRyR, d2FdXdY[ip1][jp1] * xRyR};
                  this.splines[i][j] = new BicubicFunction(this.computeSplineCoefficients(beta));
               }
            }

         }
      } else {
         throw new NoDataException();
      }
   }

   public double value(double x, double y) throws OutOfRangeException {
      int i = this.searchIndex(x, this.xval);
      int j = this.searchIndex(y, this.yval);
      double xN = (x - this.xval[i]) / (this.xval[i + 1] - this.xval[i]);
      double yN = (y - this.yval[j]) / (this.yval[j + 1] - this.yval[j]);
      return this.splines[i][j].value(xN, yN);
   }

   public boolean isValidPoint(double x, double y) {
      return !(x < this.xval[0]) && !(x > this.xval[this.xval.length - 1]) && !(y < this.yval[0]) && !(y > this.yval[this.yval.length - 1]);
   }

   private int searchIndex(double c, double[] val) {
      int r = Arrays.binarySearch(val, c);
      if (r != -1 && r != -val.length - 1) {
         if (r < 0) {
            return -r - 2;
         } else {
            int last = val.length - 1;
            return r == last ? last - 1 : r;
         }
      } else {
         throw new OutOfRangeException(c, val[0], val[val.length - 1]);
      }
   }

   private double[] computeSplineCoefficients(double[] beta) {
      double[] a = new double[16];

      for(int i = 0; i < 16; ++i) {
         double result = (double)0.0F;
         double[] row = AINV[i];

         for(int j = 0; j < 16; ++j) {
            result += row[j] * beta[j];
         }

         a[i] = result;
      }

      return a;
   }
}

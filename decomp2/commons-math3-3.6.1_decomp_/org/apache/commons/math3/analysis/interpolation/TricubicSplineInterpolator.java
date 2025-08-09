package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

/** @deprecated */
@Deprecated
public class TricubicSplineInterpolator implements TrivariateGridInterpolator {
   public TricubicSplineInterpolatingFunction interpolate(double[] xval, double[] yval, double[] zval, double[][][] fval) throws NoDataException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
      if (xval.length != 0 && yval.length != 0 && zval.length != 0 && fval.length != 0) {
         if (xval.length != fval.length) {
            throw new DimensionMismatchException(xval.length, fval.length);
         } else {
            MathArrays.checkOrder(xval);
            MathArrays.checkOrder(yval);
            MathArrays.checkOrder(zval);
            int xLen = xval.length;
            int yLen = yval.length;
            int zLen = zval.length;
            double[][][] fvalXY = new double[zLen][xLen][yLen];
            double[][][] fvalZX = new double[yLen][zLen][xLen];

            for(int i = 0; i < xLen; ++i) {
               if (fval[i].length != yLen) {
                  throw new DimensionMismatchException(fval[i].length, yLen);
               }

               for(int j = 0; j < yLen; ++j) {
                  if (fval[i][j].length != zLen) {
                     throw new DimensionMismatchException(fval[i][j].length, zLen);
                  }

                  for(int k = 0; k < zLen; ++k) {
                     double v = fval[i][j][k];
                     fvalXY[k][i][j] = v;
                     fvalZX[j][k][i] = v;
                  }
               }
            }

            BicubicSplineInterpolator bsi = new BicubicSplineInterpolator(true);
            BicubicSplineInterpolatingFunction[] xSplineYZ = new BicubicSplineInterpolatingFunction[xLen];

            for(int i = 0; i < xLen; ++i) {
               xSplineYZ[i] = bsi.interpolate(yval, zval, fval[i]);
            }

            BicubicSplineInterpolatingFunction[] ySplineZX = new BicubicSplineInterpolatingFunction[yLen];

            for(int j = 0; j < yLen; ++j) {
               ySplineZX[j] = bsi.interpolate(zval, xval, fvalZX[j]);
            }

            BicubicSplineInterpolatingFunction[] zSplineXY = new BicubicSplineInterpolatingFunction[zLen];

            for(int k = 0; k < zLen; ++k) {
               zSplineXY[k] = bsi.interpolate(xval, yval, fvalXY[k]);
            }

            double[][][] dFdX = new double[xLen][yLen][zLen];
            double[][][] dFdY = new double[xLen][yLen][zLen];
            double[][][] d2FdXdY = new double[xLen][yLen][zLen];

            for(int k = 0; k < zLen; ++k) {
               BicubicSplineInterpolatingFunction f = zSplineXY[k];

               for(int i = 0; i < xLen; ++i) {
                  double x = xval[i];

                  for(int j = 0; j < yLen; ++j) {
                     double y = yval[j];
                     dFdX[i][j][k] = f.partialDerivativeX(x, y);
                     dFdY[i][j][k] = f.partialDerivativeY(x, y);
                     d2FdXdY[i][j][k] = f.partialDerivativeXY(x, y);
                  }
               }
            }

            double[][][] dFdZ = new double[xLen][yLen][zLen];
            double[][][] d2FdYdZ = new double[xLen][yLen][zLen];

            for(int i = 0; i < xLen; ++i) {
               BicubicSplineInterpolatingFunction f = xSplineYZ[i];

               for(int j = 0; j < yLen; ++j) {
                  double y = yval[j];

                  for(int k = 0; k < zLen; ++k) {
                     double z = zval[k];
                     dFdZ[i][j][k] = f.partialDerivativeY(y, z);
                     d2FdYdZ[i][j][k] = f.partialDerivativeXY(y, z);
                  }
               }
            }

            double[][][] d2FdZdX = new double[xLen][yLen][zLen];

            for(int j = 0; j < yLen; ++j) {
               BicubicSplineInterpolatingFunction f = ySplineZX[j];

               for(int k = 0; k < zLen; ++k) {
                  double z = zval[k];

                  for(int i = 0; i < xLen; ++i) {
                     double x = xval[i];
                     d2FdZdX[i][j][k] = f.partialDerivativeXY(z, x);
                  }
               }
            }

            double[][][] d3FdXdYdZ = new double[xLen][yLen][zLen];

            for(int i = 0; i < xLen; ++i) {
               int nI = this.nextIndex(i, xLen);
               int pI = this.previousIndex(i);

               for(int j = 0; j < yLen; ++j) {
                  int nJ = this.nextIndex(j, yLen);
                  int pJ = this.previousIndex(j);

                  for(int k = 0; k < zLen; ++k) {
                     int nK = this.nextIndex(k, zLen);
                     int pK = this.previousIndex(k);
                     d3FdXdYdZ[i][j][k] = (fval[nI][nJ][nK] - fval[nI][pJ][nK] - fval[pI][nJ][nK] + fval[pI][pJ][nK] - fval[nI][nJ][pK] + fval[nI][pJ][pK] + fval[pI][nJ][pK] - fval[pI][pJ][pK]) / ((xval[nI] - xval[pI]) * (yval[nJ] - yval[pJ]) * (zval[nK] - zval[pK]));
                  }
               }
            }

            return new TricubicSplineInterpolatingFunction(xval, yval, zval, fval, dFdX, dFdY, dFdZ, d2FdXdY, d2FdZdX, d2FdYdZ, d3FdXdYdZ);
         }
      } else {
         throw new NoDataException();
      }
   }

   private int nextIndex(int i, int max) {
      int index = i + 1;
      return index < max ? index : index - 1;
   }

   private int previousIndex(int i) {
      int index = i - 1;
      return index >= 0 ? index : 0;
   }
}

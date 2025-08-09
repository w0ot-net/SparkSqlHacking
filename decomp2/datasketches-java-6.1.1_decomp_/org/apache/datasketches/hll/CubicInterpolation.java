package org.apache.datasketches.hll;

import org.apache.datasketches.common.SketchesArgumentException;

final class CubicInterpolation {
   static double usingXAndYTables(double[] xArr, double[] yArr, double x) {
      assert xArr.length >= 4 && xArr.length == yArr.length;

      if (!(x < xArr[0]) && !(x > xArr[xArr.length - 1])) {
         if (x == xArr[xArr.length - 1]) {
            return yArr[yArr.length - 1];
         } else {
            int offset = findStraddle(xArr, x);

            assert offset >= 0 && offset <= xArr.length - 2;

            if (offset == 0) {
               return interpolateUsingXAndYTables(xArr, yArr, offset, x);
            } else {
               return offset == xArr.length - 2 ? interpolateUsingXAndYTables(xArr, yArr, offset - 2, x) : interpolateUsingXAndYTables(xArr, yArr, offset - 1, x);
            }
         }
      } else {
         throw new SketchesArgumentException("X value out of range: " + x);
      }
   }

   private static double interpolateUsingXAndYTables(double[] xArr, double[] yArr, int offset, double x) {
      return cubicInterpolate(xArr[offset], yArr[offset], xArr[offset + 1], yArr[offset + 1], xArr[offset + 2], yArr[offset + 2], xArr[offset + 3], yArr[offset + 3], x);
   }

   static double usingXArrAndYStride(double[] xArr, double yStride, double x) {
      int xArrLen = xArr.length;
      int xArrLenM1 = xArrLen - 1;

      assert xArrLen >= 4 && x >= xArr[0] && x <= xArr[xArrLenM1];

      if (x == xArr[xArrLenM1]) {
         return yStride * (double)xArrLenM1;
      } else {
         int offset = findStraddle(xArr, x);
         int xArrLenM2 = xArrLen - 2;

         assert offset >= 0 && offset <= xArrLenM2;

         if (offset == 0) {
            return interpolateUsingXArrAndYStride(xArr, yStride, offset - 0, x);
         } else {
            return offset == xArrLenM2 ? interpolateUsingXArrAndYStride(xArr, yStride, offset - 2, x) : interpolateUsingXArrAndYStride(xArr, yStride, offset - 1, x);
         }
      }
   }

   private static double interpolateUsingXArrAndYStride(double[] xArr, double yStride, int offset, double x) {
      return cubicInterpolate(xArr[offset + 0], yStride * (double)(offset + 0), xArr[offset + 1], yStride * (double)(offset + 1), xArr[offset + 2], yStride * (double)(offset + 2), xArr[offset + 3], yStride * (double)(offset + 3), x);
   }

   private static double cubicInterpolate(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3, double x) {
      double l0Numer = (x - x1) * (x - x2) * (x - x3);
      double l1Numer = (x - x0) * (x - x2) * (x - x3);
      double l2Numer = (x - x0) * (x - x1) * (x - x3);
      double l3Numer = (x - x0) * (x - x1) * (x - x2);
      double l0Denom = (x0 - x1) * (x0 - x2) * (x0 - x3);
      double l1Denom = (x1 - x0) * (x1 - x2) * (x1 - x3);
      double l2Denom = (x2 - x0) * (x2 - x1) * (x2 - x3);
      double l3Denom = (x3 - x0) * (x3 - x1) * (x3 - x2);
      double term0 = y0 * l0Numer / l0Denom;
      double term1 = y1 * l1Numer / l1Denom;
      double term2 = y2 * l2Numer / l2Denom;
      double term3 = y3 * l3Numer / l3Denom;
      return term0 + term1 + term2 + term3;
   }

   private static int findStraddle(double[] xArr, double x) {
      assert xArr.length >= 2 && x >= xArr[0] && x <= xArr[xArr.length - 1];

      return recursiveFindStraddle(xArr, 0, xArr.length - 1, x);
   }

   private static int recursiveFindStraddle(double[] xArr, int left, int right, double x) {
      assert left < right;

      assert xArr[left] <= x && x < xArr[right];

      if (left + 1 == right) {
         return left;
      } else {
         int middle = left + (right - left) / 2;
         return xArr[middle] <= x ? recursiveFindStraddle(xArr, middle, right, x) : recursiveFindStraddle(xArr, left, middle, x);
      }
   }
}

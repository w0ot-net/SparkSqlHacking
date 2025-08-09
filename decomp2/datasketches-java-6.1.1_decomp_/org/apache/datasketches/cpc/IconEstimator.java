package org.apache.datasketches.cpc;

final class IconEstimator {
   static double evaluatePolynomial(double[] coefficients, int start, int num, double x) {
      int end = start + num - 1;
      double total = coefficients[end];

      for(int j = end - 1; j >= start; --j) {
         total *= x;
         total += coefficients[j];
      }

      return total;
   }

   static double iconExponentialApproximation(double k, double c) {
      return 0.7940236163830469 * k * Math.pow((double)2.0F, c / k);
   }

   static double getIconEstimate(int lgK, long c) {
      assert lgK >= 4;

      assert lgK <= 26;

      if (c < 2L) {
         return c == 0L ? (double)0.0F : (double)1.0F;
      } else {
         int k = 1 << lgK;
         double doubleK = (double)k;
         double doubleC = (double)c;
         double thresholdFactor = lgK < 14 ? 5.7 : 5.6;
         if (doubleC > thresholdFactor * doubleK) {
            return iconExponentialApproximation(doubleK, doubleC);
         } else {
            double factor = evaluatePolynomial(IconPolynomialCoefficients.iconPolynomialCoefficents, 20 * (lgK - 4), 20, doubleC / ((double)2.0F * doubleK));
            double ratio = doubleC / doubleK;
            double term = (double)1.0F + ratio * ratio * ratio / 66.774757;
            double result = doubleC * factor * term;
            return result >= doubleC ? result : doubleC;
         }
      }
   }
}

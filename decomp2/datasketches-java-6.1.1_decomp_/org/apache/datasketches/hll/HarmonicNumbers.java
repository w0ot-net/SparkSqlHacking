package org.apache.datasketches.hll;

final class HarmonicNumbers {
   private static final int NUM_EXACT_HARMONIC_NUMBERS = 25;
   private static double[] tableOfExactHarmonicNumbers = new double[]{(double)0.0F, (double)1.0F, (double)1.5F, 1.8333333333333333, 2.0833333333333335, 2.283333333333333, 2.45, 2.592857142857143, 2.717857142857143, 2.828968253968254, 2.9289682539682538, 3.019877344877345, 3.103210678210678, 3.180133755133755, 3.2515623265623264, 3.3182289932289932, 3.3807289932289932, 3.4395525226407577, 3.4951080781963135, 3.547739657143682, 3.597739657143682, 3.6453587047627294, 3.690813250217275, 3.73429151108684, 3.7759581777535067};
   private static final double EULER_MASCHERONI_CONSTANT = 0.5772156649015329;

   static double getBitMapEstimate(int bitVectorLength, int numBitsSet) {
      return (double)bitVectorLength * (harmonicNumber((long)bitVectorLength) - harmonicNumber((long)(bitVectorLength - numBitsSet)));
   }

   private static double harmonicNumber(long x_i) {
      if (x_i < 25L) {
         return tableOfExactHarmonicNumbers[(int)x_i];
      } else {
         double x = (double)x_i;
         double invSq = (double)1.0F / (x * x);
         double sum = Math.log(x) + 0.5772156649015329 + (double)1.0F / ((double)2.0F * x);
         sum -= invSq * 0.08333333333333333;
         double pow = invSq * invSq;
         sum += pow * 0.008333333333333333;
         pow *= invSq;
         sum -= pow * 0.003968253968253968;
         pow *= invSq;
         sum += pow * 0.004166666666666667;
         return sum;
      }
   }
}

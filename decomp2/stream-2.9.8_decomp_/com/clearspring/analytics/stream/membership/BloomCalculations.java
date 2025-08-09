package com.clearspring.analytics.stream.membership;

public class BloomCalculations {
   private static final int maxBuckets = 15;
   private static final int minBuckets = 2;
   private static final int minK = 1;
   private static final int maxK = 8;
   private static final int[] optKPerBuckets = new int[]{1, 1, 1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 8, 9, 10, 10, 11, 12, 12, 13, 14};
   static final double[][] probs = new double[][]{{(double)1.0F}, {(double)1.0F, (double)1.0F}, {(double)1.0F, 0.393, 0.4}, {(double)1.0F, 0.283, 0.237, 0.253}, {(double)1.0F, 0.221, 0.155, 0.147, 0.16}, {(double)1.0F, 0.181, 0.109, 0.092, 0.092, 0.101}, {(double)1.0F, 0.154, 0.0804, 0.0609, 0.0561, 0.0578, 0.0638}, {(double)1.0F, 0.133, 0.0618, 0.0423, 0.0359, 0.0347, 0.0364}, {(double)1.0F, 0.118, 0.0489, 0.0306, 0.024, 0.0217, 0.0216, 0.0229}, {(double)1.0F, 0.105, 0.0397, 0.0228, 0.0166, 0.0141, 0.0133, 0.0135, 0.0145}, {(double)1.0F, 0.0952, 0.0329, 0.0174, 0.0118, 0.00943, 0.00844, 0.00819, 0.00846}, {(double)1.0F, 0.0869, 0.0276, 0.0136, 0.00864, 0.0065, 0.00552, 0.00513, 0.00509}, {(double)1.0F, 0.08, 0.0236, 0.0108, 0.00646, 0.00459, 0.00371, 0.00329, 0.00314}, {(double)1.0F, 0.074, 0.0203, 0.00875, 0.00492, 0.00332, 0.00255, 0.00217, 0.00199, 0.00194}, {(double)1.0F, 0.0689, 0.0177, 0.00718, 0.00381, 0.00244, 0.00179, 0.00146, 0.00129, 0.00121, 0.0012}, {(double)1.0F, 0.0645, 0.0156, 0.00596, 0.003, 0.00183, 0.00128, 0.001, 8.52E-4, 7.75E-4, 7.44E-4}, {(double)1.0F, 0.0606, 0.0138, 0.005, 0.00239, 0.00139, 9.35E-4, 7.02E-4, 5.74E-4, 5.05E-4, 4.7E-4, 4.59E-4}, {(double)1.0F, 0.0571, 0.0123, 0.00423, 0.00193, 0.00107, 6.92E-4, 4.99E-4, 3.94E-4, 3.35E-4, 3.02E-4, 2.87E-4, 2.84E-4}, {(double)1.0F, 0.054, 0.0111, 0.00362, 0.00158, 8.39E-4, 5.19E-4, 3.6E-4, 2.75E-4, 2.26E-4, 1.98E-4, 1.83E-4, 1.76E-4}, {(double)1.0F, 0.0513, 0.00998, 0.00312, 0.0013, 6.63E-4, 3.94E-4, 2.64E-4, 1.94E-4, 1.55E-4, 1.32E-4, 1.18E-4, 1.11E-4, 1.09E-4}, {(double)1.0F, 0.0488, 0.00906, 0.0027, 0.00108, 5.3E-4, 3.03E-4, 1.96E-4, 1.4E-4, 1.08E-4, 8.89E-5, 7.77E-5, 7.12E-5, 6.79E-5, 6.71E-5}};

   public static int computeBestK(int bucketsPerElement) {
      assert bucketsPerElement >= 0;

      return bucketsPerElement >= optKPerBuckets.length ? optKPerBuckets[optKPerBuckets.length - 1] : optKPerBuckets[bucketsPerElement];
   }

   public static BloomSpecification computeBucketsAndK(double maxFalsePosProb) {
      if (maxFalsePosProb >= probs[2][1]) {
         return new BloomSpecification(2, optKPerBuckets[2]);
      } else if (maxFalsePosProb < probs[15][8]) {
         return new BloomSpecification(8, 15);
      } else {
         int bucketsPerElement = 2;

         int K;
         for(K = optKPerBuckets[2]; probs[bucketsPerElement][K] > maxFalsePosProb; K = optKPerBuckets[bucketsPerElement]) {
            ++bucketsPerElement;
         }

         while(probs[bucketsPerElement][K - 1] <= maxFalsePosProb) {
            --K;
         }

         return new BloomSpecification(K, bucketsPerElement);
      }
   }

   public static double getFalsePositiveProbability(int bucketsPerElement, int hashCount) {
      return Math.pow((double)1.0F - Math.exp((double)(-hashCount) * ((double)1.0F / (double)bucketsPerElement)), (double)hashCount);
   }

   public static final class BloomSpecification {
      final int K;
      final int bucketsPerElement;

      public BloomSpecification(int k, int bucketsPerElement) {
         this.K = k;
         this.bucketsPerElement = bucketsPerElement;
      }
   }
}

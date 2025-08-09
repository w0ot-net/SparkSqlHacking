package org.apache.datasketches.common;

public final class BoundsOnRatiosInSampledSets {
   private static final double NUM_STD_DEVS = (double)2.0F;

   private BoundsOnRatiosInSampledSets() {
   }

   public static double getLowerBoundForBoverA(long a, long b, double f) {
      checkInputs(a, b, f);
      if (a == 0L) {
         return (double)0.0F;
      } else {
         return f == (double)1.0F ? (double)b / (double)a : BoundsOnBinomialProportions.approximateLowerBoundOnP(a, b, (double)2.0F * hackyAdjuster(f));
      }
   }

   public static double getUpperBoundForBoverA(long a, long b, double f) {
      checkInputs(a, b, f);
      if (a == 0L) {
         return (double)1.0F;
      } else {
         return f == (double)1.0F ? (double)b / (double)a : BoundsOnBinomialProportions.approximateUpperBoundOnP(a, b, (double)2.0F * hackyAdjuster(f));
      }
   }

   public static double getEstimateOfBoverA(long a, long b) {
      checkInputs(a, b, 0.3);
      return a == 0L ? (double)0.5F : (double)b / (double)a;
   }

   public static double getEstimateOfA(long a, double f) {
      checkInputs(a, 1L, f);
      return (double)a / f;
   }

   public static double getEstimateOfB(long b, double f) {
      checkInputs(b + 1L, b, f);
      return (double)b / f;
   }

   private static double hackyAdjuster(double f) {
      double tmp = Math.sqrt((double)1.0F - f);
      return f <= (double)0.5F ? tmp : tmp + 0.01 * (f - (double)0.5F);
   }

   static void checkInputs(long a, long b, double f) {
      if ((a - b | a | b) < 0L) {
         throw new SketchesArgumentException("a must be >= b and neither a nor b can be < 0: a = " + a + ", b = " + b);
      } else if (f > (double)1.0F || f <= (double)0.0F) {
         throw new SketchesArgumentException("Required: ((f <= 1.0) && (f > 0.0)): " + f);
      }
   }
}

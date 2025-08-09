package org.apache.datasketches.sampling;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.datasketches.common.BoundsOnBinomialProportions;

final class SamplingUtil {
   private static final double DEFAULT_KAPPA = (double)2.0F;

   private SamplingUtil() {
   }

   static int getAdjustedSize(int maxSize, int resizeTarget) {
      return (long)(maxSize - (resizeTarget << 1)) < 0L ? maxSize : resizeTarget;
   }

   static double nextDoubleExcludeZero() {
      double r;
      for(r = rand().nextDouble(); r == (double)0.0F; r = rand().nextDouble()) {
      }

      return r;
   }

   static int startingSubMultiple(int lgTarget, int lgRf, int lgMin) {
      return lgTarget <= lgMin ? lgMin : (lgRf == 0 ? lgTarget : (lgTarget - lgMin) % lgRf + lgMin);
   }

   static double pseudoHypergeometricUBonP(long n, int k, double samplingRate) {
      double adjustedKappa = (double)2.0F * Math.sqrt((double)1.0F - samplingRate);
      return BoundsOnBinomialProportions.approximateUpperBoundOnP(n, (long)k, adjustedKappa);
   }

   static double pseudoHypergeometricLBonP(long n, int k, double samplingRate) {
      double adjustedKappa = (double)2.0F * Math.sqrt((double)1.0F - samplingRate);
      return BoundsOnBinomialProportions.approximateLowerBoundOnP(n, (long)k, adjustedKappa);
   }

   public static Random rand() {
      return ThreadLocalRandom.current();
   }
}

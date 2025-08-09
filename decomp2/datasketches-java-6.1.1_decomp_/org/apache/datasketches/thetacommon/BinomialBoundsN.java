package org.apache.datasketches.thetacommon;

import org.apache.datasketches.common.SketchesArgumentException;

public final class BinomialBoundsN {
   private static final double[] deltaOfNumSDev = new double[]{(double)0.5F, 0.15865531915860265, 0.02275026189041357, 0.0013498126861731796};

   private BinomialBoundsN() {
   }

   private static double contClassicLB(double numSamplesF, double theta, double numSDev) {
      double nHat = (numSamplesF - (double)0.5F) / theta;
      double b = numSDev * Math.sqrt(((double)1.0F - theta) / theta);
      double d = (double)0.5F * b * Math.sqrt(b * b + (double)4.0F * nHat);
      double center = nHat + (double)0.5F * b * b;
      return center - d;
   }

   private static double contClassicUB(double numSamplesF, double theta, double numSDev) {
      double nHat = (numSamplesF + (double)0.5F) / theta;
      double b = numSDev * Math.sqrt(((double)1.0F - theta) / theta);
      double d = (double)0.5F * b * Math.sqrt(b * b + (double)4.0F * nHat);
      double center = nHat + (double)0.5F * b * b;
      return center + d;
   }

   private static long specialNStar(long numSamplesI, double p, double delta) {
      assertTrue(numSamplesI >= 1L);
      assertTrue((double)0.0F < p && p < (double)1.0F);
      assertTrue((double)0.0F < delta && delta < (double)1.0F);
      double q = (double)1.0F - p;
      double numSamplesF = (double)numSamplesI;
      assertTrue(numSamplesF / p < (double)500.0F);
      double curTerm = Math.pow(p, numSamplesF);
      assertTrue(curTerm > 1.0E-100);
      double tot = curTerm;

      long m;
      for(m = numSamplesI; tot <= delta; ++m) {
         curTerm = curTerm * q * (double)m / (double)(m + 1L - numSamplesI);
         tot += curTerm;
      }

      return m - 1L;
   }

   private static long specialNPrimeB(long numSamplesI, double p, double delta) {
      assertTrue(numSamplesI >= 1L);
      assertTrue((double)0.0F < p && p < (double)1.0F);
      assertTrue((double)0.0F < delta && delta < (double)1.0F);
      double q = (double)1.0F - p;
      double oneMinusDelta = (double)1.0F - delta;
      double numSamplesF = (double)numSamplesI;
      double curTerm = Math.pow(p, numSamplesF);
      assertTrue(curTerm > 1.0E-100);
      double tot = curTerm;

      long m;
      for(m = numSamplesI; tot < oneMinusDelta; ++m) {
         curTerm = curTerm * q * (double)m / (double)(m + 1L - numSamplesI);
         tot += curTerm;
      }

      return m;
   }

   private static long specialNPrimeF(long numSamplesI, double p, double delta) {
      assertTrue((double)numSamplesI / p < (double)500.0F);
      return specialNPrimeB(numSamplesI + 1L, p, delta);
   }

   private static double computeApproxBinoLB(long numSamplesI, double theta, int numSDev) {
      if (theta == (double)1.0F) {
         return (double)numSamplesI;
      } else if (numSamplesI == 0L) {
         return (double)0.0F;
      } else if (numSamplesI == 1L) {
         double delta = deltaOfNumSDev[numSDev];
         double rawLB = Math.log((double)1.0F - delta) / Math.log((double)1.0F - theta);
         return Math.floor(rawLB);
      } else if (numSamplesI > 120L) {
         double rawLB = contClassicLB((double)numSamplesI, theta, (double)numSDev);
         return rawLB - (double)0.5F;
      } else if (theta > 0.99999) {
         return (double)numSamplesI;
      } else if (theta < (double)numSamplesI / (double)360.0F) {
         int index = 3 * (int)numSamplesI + (numSDev - 1);
         double rawLB = contClassicLB((double)numSamplesI, theta, EquivTables.getLB(index));
         return rawLB - (double)0.5F;
      } else {
         double delta = deltaOfNumSDev[numSDev];
         long nstar = specialNStar(numSamplesI, theta, delta);
         return (double)nstar;
      }
   }

   private static double computeApproxBinoUB(long numSamplesI, double theta, int numSDev) {
      if (theta == (double)1.0F) {
         return (double)numSamplesI;
      } else if (numSamplesI == 0L) {
         double delta = deltaOfNumSDev[numSDev];
         double rawUB = Math.log(delta) / Math.log((double)1.0F - theta);
         return Math.ceil(rawUB);
      } else if (numSamplesI > 120L) {
         double rawUB = contClassicUB((double)numSamplesI, theta, (double)numSDev);
         return rawUB + (double)0.5F;
      } else if (theta > 0.99999) {
         return (double)(numSamplesI + 1L);
      } else if (theta < (double)numSamplesI / (double)360.0F) {
         int index = 3 * (int)numSamplesI + (numSDev - 1);
         double rawUB = contClassicUB((double)numSamplesI, theta, EquivTables.getUB(index));
         return rawUB + (double)0.5F;
      } else {
         double delta = deltaOfNumSDev[numSDev];
         long nprimef = specialNPrimeF(numSamplesI, theta, delta);
         return (double)nprimef;
      }
   }

   public static double getLowerBound(long numSamples, double theta, int numSDev, boolean noDataSeen) {
      if (noDataSeen) {
         return (double)0.0F;
      } else {
         checkArgs(numSamples, theta, numSDev);
         double lb = computeApproxBinoLB(numSamples, theta, numSDev);
         double numSamplesF = (double)numSamples;
         double est = numSamplesF / theta;
         return Math.min(est, Math.max(numSamplesF, lb));
      }
   }

   public static double getUpperBound(long numSamples, double theta, int numSDev, boolean noDataSeen) {
      if (noDataSeen) {
         return (double)0.0F;
      } else {
         checkArgs(numSamples, theta, numSDev);
         double ub = computeApproxBinoUB(numSamples, theta, numSDev);
         double numSamplesF = (double)numSamples;
         double est = numSamplesF / theta;
         return Math.max(est, ub);
      }
   }

   static void checkArgs(long numSamples, double theta, int numSDev) {
      if (((long)(numSDev | numSDev - 1 | 3 - numSDev) | numSamples) < 0L) {
         throw new SketchesArgumentException("numSDev must only be 1,2, or 3 and numSamples must >= 0: numSDev=" + numSDev + ", numSamples=" + numSamples);
      } else if (theta < (double)0.0F || theta > (double)1.0F) {
         throw new SketchesArgumentException("0.0 < theta <= 1.0: " + theta);
      }
   }

   private static void assertTrue(boolean truth) {
      assert truth;

   }
}

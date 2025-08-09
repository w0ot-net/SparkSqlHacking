package org.apache.datasketches.common;

public final class BoundsOnBinomialProportions {
   private BoundsOnBinomialProportions() {
   }

   public static double approximateLowerBoundOnP(long n, long k, double numStdDevs) {
      checkInputs(n, k);
      if (n == 0L) {
         return (double)0.0F;
      } else if (k == 0L) {
         return (double)0.0F;
      } else if (k == 1L) {
         return exactLowerBoundOnPForKequalsOne((double)n, deltaOfNumStdevs(numStdDevs));
      } else if (k == n) {
         return exactLowerBoundOnPForKequalsN((double)n, deltaOfNumStdevs(numStdDevs));
      } else {
         double x = abramowitzStegunFormula26p5p22((double)(n - k + 1L), (double)k, (double)-1.0F * numStdDevs);
         return (double)1.0F - x;
      }
   }

   public static double approximateUpperBoundOnP(long n, long k, double numStdDevs) {
      checkInputs(n, k);
      if (n == 0L) {
         return (double)1.0F;
      } else if (k == n) {
         return (double)1.0F;
      } else if (k == n - 1L) {
         return exactUpperBoundOnPForKequalsNminusOne((double)n, deltaOfNumStdevs(numStdDevs));
      } else if (k == 0L) {
         return exactUpperBoundOnPForKequalsZero((double)n, deltaOfNumStdevs(numStdDevs));
      } else {
         double x = abramowitzStegunFormula26p5p22((double)(n - k), (double)(k + 1L), numStdDevs);
         return (double)1.0F - x;
      }
   }

   public static double estimateUnknownP(long n, long k) {
      checkInputs(n, k);
      return n == 0L ? (double)0.5F : (double)k / (double)n;
   }

   private static void checkInputs(long n, long k) {
      if (n < 0L) {
         throw new SketchesArgumentException("N must be non-negative");
      } else if (k < 0L) {
         throw new SketchesArgumentException("K must be non-negative");
      } else if (k > n) {
         throw new SketchesArgumentException("K cannot exceed N");
      }
   }

   public static double erf(double x) {
      return x < (double)0.0F ? (double)-1.0F * erf_of_nonneg((double)-1.0F * x) : erf_of_nonneg(x);
   }

   public static double normalCDF(double x) {
      return (double)0.5F * ((double)1.0F + erf(x / Math.sqrt((double)2.0F)));
   }

   private static double erf_of_nonneg(double x) {
      double a1 = 0.0705230784;
      double a3 = 0.0092705272;
      double a5 = 2.765672E-4;
      double a2 = 0.0422820123;
      double a4 = 1.520143E-4;
      double a6 = 4.30638E-5;
      double x2 = x * x;
      double x3 = x2 * x;
      double x4 = x2 * x2;
      double x5 = x2 * x3;
      double x6 = x3 * x3;
      double sum = (double)1.0F + 0.0705230784 * x + 0.0422820123 * x2 + 0.0092705272 * x3 + 1.520143E-4 * x4 + 2.765672E-4 * x5 + 4.30638E-5 * x6;
      double sum2 = sum * sum;
      double sum4 = sum2 * sum2;
      double sum8 = sum4 * sum4;
      double sum16 = sum8 * sum8;
      return (double)1.0F - (double)1.0F / sum16;
   }

   private static double deltaOfNumStdevs(double kappa) {
      return normalCDF((double)-1.0F * kappa);
   }

   private static double abramowitzStegunFormula26p5p22(double a, double b, double yp) {
      double b2m1 = (double)2.0F * b - (double)1.0F;
      double a2m1 = (double)2.0F * a - (double)1.0F;
      double lambda = (yp * yp - (double)3.0F) / (double)6.0F;
      double htmp = (double)1.0F / a2m1 + (double)1.0F / b2m1;
      double h = (double)2.0F / htmp;
      double term1 = yp * Math.sqrt(h + lambda) / h;
      double term2 = (double)1.0F / b2m1 - (double)1.0F / a2m1;
      double term3 = lambda + 0.8333333333333334 - (double)2.0F / ((double)3.0F * h);
      double w = term1 - term2 * term3;
      double xp = a / (a + b * Math.exp((double)2.0F * w));
      return xp;
   }

   private static double exactUpperBoundOnPForKequalsZero(double n, double delta) {
      return (double)1.0F - Math.pow(delta, (double)1.0F / n);
   }

   private static double exactLowerBoundOnPForKequalsN(double n, double delta) {
      return Math.pow(delta, (double)1.0F / n);
   }

   private static double exactLowerBoundOnPForKequalsOne(double n, double delta) {
      return (double)1.0F - Math.pow((double)1.0F - delta, (double)1.0F / n);
   }

   private static double exactUpperBoundOnPForKequalsNminusOne(double n, double delta) {
      return Math.pow((double)1.0F - delta, (double)1.0F / n);
   }
}

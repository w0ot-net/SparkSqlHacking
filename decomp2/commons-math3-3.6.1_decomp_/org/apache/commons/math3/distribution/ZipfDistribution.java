package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class ZipfDistribution extends AbstractIntegerDistribution {
   private static final long serialVersionUID = -140627372283420404L;
   private final int numberOfElements;
   private final double exponent;
   private double numericalMean;
   private boolean numericalMeanIsCalculated;
   private double numericalVariance;
   private boolean numericalVarianceIsCalculated;
   private transient ZipfRejectionInversionSampler sampler;

   public ZipfDistribution(int numberOfElements, double exponent) {
      this(new Well19937c(), numberOfElements, exponent);
   }

   public ZipfDistribution(RandomGenerator rng, int numberOfElements, double exponent) throws NotStrictlyPositiveException {
      super(rng);
      this.numericalMean = Double.NaN;
      this.numericalMeanIsCalculated = false;
      this.numericalVariance = Double.NaN;
      this.numericalVarianceIsCalculated = false;
      if (numberOfElements <= 0) {
         throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, numberOfElements);
      } else if (exponent <= (double)0.0F) {
         throw new NotStrictlyPositiveException(LocalizedFormats.EXPONENT, exponent);
      } else {
         this.numberOfElements = numberOfElements;
         this.exponent = exponent;
      }
   }

   public int getNumberOfElements() {
      return this.numberOfElements;
   }

   public double getExponent() {
      return this.exponent;
   }

   public double probability(int x) {
      return x > 0 && x <= this.numberOfElements ? (double)1.0F / FastMath.pow((double)x, this.exponent) / this.generalizedHarmonic(this.numberOfElements, this.exponent) : (double)0.0F;
   }

   public double logProbability(int x) {
      return x > 0 && x <= this.numberOfElements ? -FastMath.log((double)x) * this.exponent - FastMath.log(this.generalizedHarmonic(this.numberOfElements, this.exponent)) : Double.NEGATIVE_INFINITY;
   }

   public double cumulativeProbability(int x) {
      if (x <= 0) {
         return (double)0.0F;
      } else {
         return x >= this.numberOfElements ? (double)1.0F : this.generalizedHarmonic(x, this.exponent) / this.generalizedHarmonic(this.numberOfElements, this.exponent);
      }
   }

   public double getNumericalMean() {
      if (!this.numericalMeanIsCalculated) {
         this.numericalMean = this.calculateNumericalMean();
         this.numericalMeanIsCalculated = true;
      }

      return this.numericalMean;
   }

   protected double calculateNumericalMean() {
      int N = this.getNumberOfElements();
      double s = this.getExponent();
      double Hs1 = this.generalizedHarmonic(N, s - (double)1.0F);
      double Hs = this.generalizedHarmonic(N, s);
      return Hs1 / Hs;
   }

   public double getNumericalVariance() {
      if (!this.numericalVarianceIsCalculated) {
         this.numericalVariance = this.calculateNumericalVariance();
         this.numericalVarianceIsCalculated = true;
      }

      return this.numericalVariance;
   }

   protected double calculateNumericalVariance() {
      int N = this.getNumberOfElements();
      double s = this.getExponent();
      double Hs2 = this.generalizedHarmonic(N, s - (double)2.0F);
      double Hs1 = this.generalizedHarmonic(N, s - (double)1.0F);
      double Hs = this.generalizedHarmonic(N, s);
      return Hs2 / Hs - Hs1 * Hs1 / (Hs * Hs);
   }

   private double generalizedHarmonic(int n, double m) {
      double value = (double)0.0F;

      for(int k = n; k > 0; --k) {
         value += (double)1.0F / FastMath.pow((double)k, m);
      }

      return value;
   }

   public int getSupportLowerBound() {
      return 1;
   }

   public int getSupportUpperBound() {
      return this.getNumberOfElements();
   }

   public boolean isSupportConnected() {
      return true;
   }

   public int sample() {
      if (this.sampler == null) {
         this.sampler = new ZipfRejectionInversionSampler(this.numberOfElements, this.exponent);
      }

      return this.sampler.sample(this.random);
   }

   static final class ZipfRejectionInversionSampler {
      private final double exponent;
      private final int numberOfElements;
      private final double hIntegralX1;
      private final double hIntegralNumberOfElements;
      private final double s;

      ZipfRejectionInversionSampler(int numberOfElements, double exponent) {
         this.exponent = exponent;
         this.numberOfElements = numberOfElements;
         this.hIntegralX1 = this.hIntegral((double)1.5F) - (double)1.0F;
         this.hIntegralNumberOfElements = this.hIntegral((double)numberOfElements + (double)0.5F);
         this.s = (double)2.0F - this.hIntegralInverse(this.hIntegral((double)2.5F) - this.h((double)2.0F));
      }

      int sample(RandomGenerator random) {
         double u;
         double x;
         int k;
         do {
            u = this.hIntegralNumberOfElements + random.nextDouble() * (this.hIntegralX1 - this.hIntegralNumberOfElements);
            x = this.hIntegralInverse(u);
            k = (int)(x + (double)0.5F);
            if (k < 1) {
               k = 1;
            } else if (k > this.numberOfElements) {
               k = this.numberOfElements;
            }
         } while(!((double)k - x <= this.s) && !(u >= this.hIntegral((double)k + (double)0.5F) - this.h((double)k)));

         return k;
      }

      private double hIntegral(double x) {
         double logX = FastMath.log(x);
         return helper2(((double)1.0F - this.exponent) * logX) * logX;
      }

      private double h(double x) {
         return FastMath.exp(-this.exponent * FastMath.log(x));
      }

      private double hIntegralInverse(double x) {
         double t = x * ((double)1.0F - this.exponent);
         if (t < (double)-1.0F) {
            t = (double)-1.0F;
         }

         return FastMath.exp(helper1(t) * x);
      }

      static double helper1(double x) {
         return FastMath.abs(x) > 1.0E-8 ? FastMath.log1p(x) / x : (double)1.0F - x * ((double)0.5F - x * (0.3333333333333333 - x * (double)0.25F));
      }

      static double helper2(double x) {
         return FastMath.abs(x) > 1.0E-8 ? FastMath.expm1(x) / x : (double)1.0F + x * (double)0.5F * ((double)1.0F + x * 0.3333333333333333 * ((double)1.0F + x * (double)0.25F));
      }
   }
}

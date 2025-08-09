package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class TriangularDistribution extends AbstractRealDistribution {
   private static final long serialVersionUID = 20120112L;
   private final double a;
   private final double b;
   private final double c;
   private final double solverAbsoluteAccuracy;

   public TriangularDistribution(double a, double c, double b) throws NumberIsTooLargeException, NumberIsTooSmallException {
      this(new Well19937c(), a, c, b);
   }

   public TriangularDistribution(RandomGenerator rng, double a, double c, double b) throws NumberIsTooLargeException, NumberIsTooSmallException {
      super(rng);
      if (a >= b) {
         throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, a, b, false);
      } else if (c < a) {
         throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, c, a, true);
      } else if (c > b) {
         throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_TOO_LARGE, c, b, true);
      } else {
         this.a = a;
         this.c = c;
         this.b = b;
         this.solverAbsoluteAccuracy = FastMath.max(FastMath.ulp(a), FastMath.ulp(b));
      }
   }

   public double getMode() {
      return this.c;
   }

   protected double getSolverAbsoluteAccuracy() {
      return this.solverAbsoluteAccuracy;
   }

   public double density(double x) {
      if (x < this.a) {
         return (double)0.0F;
      } else if (this.a <= x && x < this.c) {
         double divident = (double)2.0F * (x - this.a);
         double divisor = (this.b - this.a) * (this.c - this.a);
         return divident / divisor;
      } else if (x == this.c) {
         return (double)2.0F / (this.b - this.a);
      } else if (this.c < x && x <= this.b) {
         double divident = (double)2.0F * (this.b - x);
         double divisor = (this.b - this.a) * (this.b - this.c);
         return divident / divisor;
      } else {
         return (double)0.0F;
      }
   }

   public double cumulativeProbability(double x) {
      if (x < this.a) {
         return (double)0.0F;
      } else if (this.a <= x && x < this.c) {
         double divident = (x - this.a) * (x - this.a);
         double divisor = (this.b - this.a) * (this.c - this.a);
         return divident / divisor;
      } else if (x == this.c) {
         return (this.c - this.a) / (this.b - this.a);
      } else if (this.c < x && x <= this.b) {
         double divident = (this.b - x) * (this.b - x);
         double divisor = (this.b - this.a) * (this.b - this.c);
         return (double)1.0F - divident / divisor;
      } else {
         return (double)1.0F;
      }
   }

   public double getNumericalMean() {
      return (this.a + this.b + this.c) / (double)3.0F;
   }

   public double getNumericalVariance() {
      return (this.a * this.a + this.b * this.b + this.c * this.c - this.a * this.b - this.a * this.c - this.b * this.c) / (double)18.0F;
   }

   public double getSupportLowerBound() {
      return this.a;
   }

   public double getSupportUpperBound() {
      return this.b;
   }

   public boolean isSupportLowerBoundInclusive() {
      return true;
   }

   public boolean isSupportUpperBoundInclusive() {
      return true;
   }

   public boolean isSupportConnected() {
      return true;
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         if (p == (double)0.0F) {
            return this.a;
         } else if (p == (double)1.0F) {
            return this.b;
         } else {
            return p < (this.c - this.a) / (this.b - this.a) ? this.a + FastMath.sqrt(p * (this.b - this.a) * (this.c - this.a)) : this.b - FastMath.sqrt(((double)1.0F - p) * (this.b - this.a) * (this.b - this.c));
         }
      } else {
         throw new OutOfRangeException(p, 0, 1);
      }
   }
}

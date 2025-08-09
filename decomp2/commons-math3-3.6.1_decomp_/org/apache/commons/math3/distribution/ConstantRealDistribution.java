package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;

public class ConstantRealDistribution extends AbstractRealDistribution {
   private static final long serialVersionUID = -4157745166772046273L;
   private final double value;

   public ConstantRealDistribution(double value) {
      super((RandomGenerator)null);
      this.value = value;
   }

   public double density(double x) {
      return x == this.value ? (double)1.0F : (double)0.0F;
   }

   public double cumulativeProbability(double x) {
      return x < this.value ? (double)0.0F : (double)1.0F;
   }

   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
      if (!(p < (double)0.0F) && !(p > (double)1.0F)) {
         return this.value;
      } else {
         throw new OutOfRangeException(p, 0, 1);
      }
   }

   public double getNumericalMean() {
      return this.value;
   }

   public double getNumericalVariance() {
      return (double)0.0F;
   }

   public double getSupportLowerBound() {
      return this.value;
   }

   public double getSupportUpperBound() {
      return this.value;
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

   public double sample() {
      return this.value;
   }

   public void reseedRandomGenerator(long seed) {
   }
}

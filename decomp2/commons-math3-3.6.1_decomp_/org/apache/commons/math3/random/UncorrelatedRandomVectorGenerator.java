package org.apache.commons.math3.random;

import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;

public class UncorrelatedRandomVectorGenerator implements RandomVectorGenerator {
   private final NormalizedRandomGenerator generator;
   private final double[] mean;
   private final double[] standardDeviation;

   public UncorrelatedRandomVectorGenerator(double[] mean, double[] standardDeviation, NormalizedRandomGenerator generator) {
      if (mean.length != standardDeviation.length) {
         throw new DimensionMismatchException(mean.length, standardDeviation.length);
      } else {
         this.mean = (double[])(([D)mean).clone();
         this.standardDeviation = (double[])(([D)standardDeviation).clone();
         this.generator = generator;
      }
   }

   public UncorrelatedRandomVectorGenerator(int dimension, NormalizedRandomGenerator generator) {
      this.mean = new double[dimension];
      this.standardDeviation = new double[dimension];
      Arrays.fill(this.standardDeviation, (double)1.0F);
      this.generator = generator;
   }

   public double[] nextVector() {
      double[] random = new double[this.mean.length];

      for(int i = 0; i < random.length; ++i) {
         random[i] = this.mean[i] + this.standardDeviation[i] * this.generator.nextNormalizedDouble();
      }

      return random;
   }
}

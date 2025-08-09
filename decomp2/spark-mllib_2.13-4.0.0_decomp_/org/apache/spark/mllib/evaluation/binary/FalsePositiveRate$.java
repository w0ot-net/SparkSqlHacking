package org.apache.spark.mllib.evaluation.binary;

import scala.runtime.ModuleSerializationProxy;

public final class FalsePositiveRate$ implements BinaryClassificationMetricComputer {
   public static final FalsePositiveRate$ MODULE$ = new FalsePositiveRate$();

   public double apply(final BinaryConfusionMatrix c) {
      return c.weightedNegatives() == (double)0.0F ? (double)0.0F : c.weightedFalsePositives() / c.weightedNegatives();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FalsePositiveRate$.class);
   }

   private FalsePositiveRate$() {
   }
}

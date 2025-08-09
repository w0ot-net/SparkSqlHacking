package org.apache.spark.mllib.evaluation.binary;

import scala.runtime.ModuleSerializationProxy;

public final class Precision$ implements BinaryClassificationMetricComputer {
   public static final Precision$ MODULE$ = new Precision$();

   public double apply(final BinaryConfusionMatrix c) {
      double totalPositives = c.weightedTruePositives() + c.weightedFalsePositives();
      return totalPositives == (double)0.0F ? (double)1.0F : c.weightedTruePositives() / totalPositives;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Precision$.class);
   }

   private Precision$() {
   }
}

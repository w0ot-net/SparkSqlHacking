package org.apache.spark.mllib.evaluation.binary;

import scala.runtime.ModuleSerializationProxy;

public final class Recall$ implements BinaryClassificationMetricComputer {
   public static final Recall$ MODULE$ = new Recall$();

   public double apply(final BinaryConfusionMatrix c) {
      return c.weightedPositives() == (double)0.0F ? (double)0.0F : c.weightedTruePositives() / c.weightedPositives();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Recall$.class);
   }

   private Recall$() {
   }
}

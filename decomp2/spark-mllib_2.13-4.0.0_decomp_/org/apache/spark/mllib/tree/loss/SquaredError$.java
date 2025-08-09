package org.apache.spark.mllib.tree.loss;

import org.apache.spark.mllib.tree.model.TreeEnsembleModel;
import org.apache.spark.rdd.RDD;
import scala.runtime.ModuleSerializationProxy;

public final class SquaredError$ implements Loss {
   public static final SquaredError$ MODULE$ = new SquaredError$();

   static {
      Loss.$init$(MODULE$);
   }

   public double computeError(final TreeEnsembleModel model, final RDD data) {
      return Loss.computeError$(this, model, data);
   }

   public double gradient(final double prediction, final double label) {
      return (double)-2.0F * (label - prediction);
   }

   public double computeError(final double prediction, final double label) {
      double err = label - prediction;
      return err * err;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SquaredError$.class);
   }

   private SquaredError$() {
   }
}

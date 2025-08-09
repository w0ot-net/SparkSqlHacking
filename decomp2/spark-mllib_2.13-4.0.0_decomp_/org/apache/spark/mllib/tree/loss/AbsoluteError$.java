package org.apache.spark.mllib.tree.loss;

import org.apache.spark.mllib.tree.model.TreeEnsembleModel;
import org.apache.spark.rdd.RDD;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public final class AbsoluteError$ implements Loss {
   public static final AbsoluteError$ MODULE$ = new AbsoluteError$();

   static {
      Loss.$init$(MODULE$);
   }

   public double computeError(final TreeEnsembleModel model, final RDD data) {
      return Loss.computeError$(this, model, data);
   }

   public double gradient(final double prediction, final double label) {
      return label - prediction < (double)0 ? (double)1.0F : (double)-1.0F;
   }

   public double computeError(final double prediction, final double label) {
      double err = label - prediction;
      return .MODULE$.abs(err);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AbsoluteError$.class);
   }

   private AbsoluteError$() {
   }
}

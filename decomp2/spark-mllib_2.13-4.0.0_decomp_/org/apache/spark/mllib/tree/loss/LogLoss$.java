package org.apache.spark.mllib.tree.loss;

import org.apache.spark.mllib.tree.model.TreeEnsembleModel;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public final class LogLoss$ implements ClassificationLoss {
   public static final LogLoss$ MODULE$ = new LogLoss$();

   static {
      Loss.$init$(MODULE$);
   }

   public double computeError(final TreeEnsembleModel model, final RDD data) {
      return Loss.computeError$(this, model, data);
   }

   public double gradient(final double prediction, final double label) {
      return (double)-4.0F * label / ((double)1.0F + .MODULE$.exp((double)2.0F * label * prediction));
   }

   public double computeError(final double prediction, final double label) {
      double margin = (double)2.0F * label * prediction;
      return (double)2.0F * MLUtils$.MODULE$.log1pExp(-margin);
   }

   public double computeProbability(final double margin) {
      return (double)1.0F / ((double)1.0F + .MODULE$.exp((double)-2.0F * margin));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LogLoss$.class);
   }

   private LogLoss$() {
   }
}

package org.apache.spark.ml.evaluation;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MultilabelClassificationEvaluator$ implements DefaultParamsReadable, Serializable {
   public static final MultilabelClassificationEvaluator$ MODULE$ = new MultilabelClassificationEvaluator$();
   private static final String[] org$apache$spark$ml$evaluation$MultilabelClassificationEvaluator$$supportedMetricNames;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      org$apache$spark$ml$evaluation$MultilabelClassificationEvaluator$$supportedMetricNames = (String[])((Object[])(new String[]{"subsetAccuracy", "accuracy", "hammingLoss", "precision", "recall", "f1Measure", "precisionByLabel", "recallByLabel", "f1MeasureByLabel", "microPrecision", "microRecall", "microF1Measure"}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String[] org$apache$spark$ml$evaluation$MultilabelClassificationEvaluator$$supportedMetricNames() {
      return org$apache$spark$ml$evaluation$MultilabelClassificationEvaluator$$supportedMetricNames;
   }

   public MultilabelClassificationEvaluator load(final String path) {
      return (MultilabelClassificationEvaluator)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MultilabelClassificationEvaluator$.class);
   }

   private MultilabelClassificationEvaluator$() {
   }
}

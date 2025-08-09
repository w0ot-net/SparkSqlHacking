package org.apache.spark.ml.evaluation;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MulticlassClassificationEvaluator$ implements DefaultParamsReadable, Serializable {
   public static final MulticlassClassificationEvaluator$ MODULE$ = new MulticlassClassificationEvaluator$();
   private static final String[] org$apache$spark$ml$evaluation$MulticlassClassificationEvaluator$$supportedMetricNames;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      org$apache$spark$ml$evaluation$MulticlassClassificationEvaluator$$supportedMetricNames = (String[])((Object[])(new String[]{"f1", "accuracy", "weightedPrecision", "weightedRecall", "weightedTruePositiveRate", "weightedFalsePositiveRate", "weightedFMeasure", "truePositiveRateByLabel", "falsePositiveRateByLabel", "precisionByLabel", "recallByLabel", "fMeasureByLabel", "logLoss", "hammingLoss"}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String[] org$apache$spark$ml$evaluation$MulticlassClassificationEvaluator$$supportedMetricNames() {
      return org$apache$spark$ml$evaluation$MulticlassClassificationEvaluator$$supportedMetricNames;
   }

   public MulticlassClassificationEvaluator load(final String path) {
      return (MulticlassClassificationEvaluator)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MulticlassClassificationEvaluator$.class);
   }

   private MulticlassClassificationEvaluator$() {
   }
}

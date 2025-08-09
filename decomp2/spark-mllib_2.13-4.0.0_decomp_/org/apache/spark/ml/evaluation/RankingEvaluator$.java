package org.apache.spark.ml.evaluation;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class RankingEvaluator$ implements DefaultParamsReadable, Serializable {
   public static final RankingEvaluator$ MODULE$ = new RankingEvaluator$();
   private static final String[] org$apache$spark$ml$evaluation$RankingEvaluator$$supportedMetricNames;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      org$apache$spark$ml$evaluation$RankingEvaluator$$supportedMetricNames = (String[])((Object[])(new String[]{"meanAveragePrecision", "meanAveragePrecisionAtK", "precisionAtK", "ndcgAtK", "recallAtK"}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String[] org$apache$spark$ml$evaluation$RankingEvaluator$$supportedMetricNames() {
      return org$apache$spark$ml$evaluation$RankingEvaluator$$supportedMetricNames;
   }

   public RankingEvaluator load(final String path) {
      return (RankingEvaluator)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RankingEvaluator$.class);
   }

   private RankingEvaluator$() {
   }
}

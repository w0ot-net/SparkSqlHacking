package org.apache.spark.ml.r;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.NaiveBayes;
import org.apache.spark.ml.feature.IndexToString;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import scala.MatchError;
import scala.Tuple2;

public final class NaiveBayesWrapper$ implements MLReadable {
   public static final NaiveBayesWrapper$ MODULE$ = new NaiveBayesWrapper$();
   private static final String PREDICTED_LABEL_INDEX_COL;
   private static final String PREDICTED_LABEL_COL;

   static {
      MLReadable.$init$(MODULE$);
      PREDICTED_LABEL_INDEX_COL = "pred_label_idx";
      PREDICTED_LABEL_COL = "prediction";
   }

   public String PREDICTED_LABEL_INDEX_COL() {
      return PREDICTED_LABEL_INDEX_COL;
   }

   public String PREDICTED_LABEL_COL() {
      return PREDICTED_LABEL_COL;
   }

   public NaiveBayesWrapper fit(final String formula, final Dataset data, final double smoothing, final String handleInvalid) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setForceIndexLabel(true).setHandleInvalid(handleInvalid);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      Tuple2 var10 = RWrapperUtils$.MODULE$.getFeaturesAndLabels(rFormulaModel, data);
      if (var10 != null) {
         String[] features = (String[])var10._1();
         String[] labels = (String[])var10._2();
         Tuple2 var9 = new Tuple2(features, labels);
         String[] features = (String[])var9._1();
         String[] labels = (String[])var9._2();
         NaiveBayes naiveBayes = (NaiveBayes)(new NaiveBayes()).setSmoothing(smoothing).setModelType("bernoulli").setFeaturesCol(rFormula.getFeaturesCol()).setLabelCol(rFormula.getLabelCol()).setPredictionCol(this.PREDICTED_LABEL_INDEX_COL());
         IndexToString idxToStr = (new IndexToString()).setInputCol(this.PREDICTED_LABEL_INDEX_COL()).setOutputCol(this.PREDICTED_LABEL_COL()).setLabels(labels);
         PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, naiveBayes, idxToStr})).fit(data);
         return new NaiveBayesWrapper(pipeline, labels, features);
      } else {
         throw new MatchError(var10);
      }
   }

   public MLReader read() {
      return new NaiveBayesWrapper.NaiveBayesWrapperReader();
   }

   public NaiveBayesWrapper load(final String path) {
      return (NaiveBayesWrapper)MLReadable.load$(this, path);
   }

   private NaiveBayesWrapper$() {
   }
}

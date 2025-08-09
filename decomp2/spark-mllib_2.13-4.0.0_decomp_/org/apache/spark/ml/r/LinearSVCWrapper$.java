package org.apache.spark.ml.r;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.LinearSVC;
import org.apache.spark.ml.feature.IndexToString;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxedUnit;

public final class LinearSVCWrapper$ implements MLReadable {
   public static final LinearSVCWrapper$ MODULE$ = new LinearSVCWrapper$();
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

   public LinearSVCWrapper fit(final Dataset data, final String formula, final double regParam, final int maxIter, final double tol, final boolean standardization, final double threshold, final String weightCol, final int aggregationDepth, final String handleInvalid) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setForceIndexLabel(true).setHandleInvalid(handleInvalid);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      boolean fitIntercept = rFormula.hasIntercept();
      Tuple2 var19 = RWrapperUtils$.MODULE$.getFeaturesAndLabels(rFormulaModel, data);
      if (var19 != null) {
         String[] features = (String[])var19._1();
         String[] labels = (String[])var19._2();
         Tuple2 var18 = new Tuple2(features, labels);
         String[] features = (String[])var18._1();
         String[] labels = (String[])var18._2();
         LinearSVC svc = ((LinearSVC)(new LinearSVC()).setRegParam(regParam).setMaxIter(maxIter).setTol(tol).setFitIntercept(fitIntercept).setStandardization(standardization).setFeaturesCol(rFormula.getFeaturesCol()).setLabelCol(rFormula.getLabelCol()).setPredictionCol(this.PREDICTED_LABEL_INDEX_COL())).setThreshold(threshold).setAggregationDepth(aggregationDepth);
         if (weightCol != null) {
            svc.setWeightCol(weightCol);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         IndexToString idxToStr = (new IndexToString()).setInputCol(this.PREDICTED_LABEL_INDEX_COL()).setOutputCol(this.PREDICTED_LABEL_COL()).setLabels(labels);
         PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, svc, idxToStr})).fit(data);
         return new LinearSVCWrapper(pipeline, features, labels);
      } else {
         throw new MatchError(var19);
      }
   }

   public MLReader read() {
      return new LinearSVCWrapper.LinearSVCWrapperReader();
   }

   public LinearSVCWrapper load(final String path) {
      return (LinearSVCWrapper)MLReadable.load$(this, path);
   }

   private LinearSVCWrapper$() {
   }
}

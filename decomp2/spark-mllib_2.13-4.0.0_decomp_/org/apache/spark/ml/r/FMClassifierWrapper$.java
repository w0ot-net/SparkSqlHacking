package org.apache.spark.ml.r;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.FMClassifier;
import org.apache.spark.ml.feature.IndexToString;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.runtime.BoxedUnit;

public final class FMClassifierWrapper$ implements MLReadable {
   public static final FMClassifierWrapper$ MODULE$ = new FMClassifierWrapper$();
   private static final String PREDICTED_LABEL_INDEX_COL;
   private static final String PREDICTED_LABEL_COL;

   static {
      MLReadable.$init$(MODULE$);
      PREDICTED_LABEL_INDEX_COL = "pred_label_idx";
      PREDICTED_LABEL_COL = "prediction";
   }

   public Object load(final String path) {
      return MLReadable.load$(this, path);
   }

   public String PREDICTED_LABEL_INDEX_COL() {
      return PREDICTED_LABEL_INDEX_COL;
   }

   public String PREDICTED_LABEL_COL() {
      return PREDICTED_LABEL_COL;
   }

   public FMClassifierWrapper fit(final Dataset data, final String formula, final int factorSize, final boolean fitLinear, final double regParam, final double miniBatchFraction, final double initStd, final int maxIter, final double stepSize, final double tol, final String solver, final String seed, final double[] thresholds, final String handleInvalid) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setForceIndexLabel(true).setHandleInvalid(handleInvalid);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      boolean fitIntercept = rFormula.hasIntercept();
      Tuple2 var25 = RWrapperUtils$.MODULE$.getFeaturesAndLabels(rFormulaModel, data);
      if (var25 == null) {
         throw new MatchError(var25);
      } else {
         String[] features = (String[])var25._1();
         String[] labels = (String[])var25._2();
         Tuple2 var24 = new Tuple2(features, labels);
         String[] features = (String[])var24._1();
         String[] labels = (String[])var24._2();
         FMClassifier fmc = (FMClassifier)(new FMClassifier()).setFactorSize(factorSize).setFitIntercept(fitIntercept).setFitLinear(fitLinear).setRegParam(regParam).setMiniBatchFraction(miniBatchFraction).setInitStd(initStd).setMaxIter(maxIter).setStepSize(stepSize).setTol(tol).setSolver(solver).setFeaturesCol(rFormula.getFeaturesCol()).setLabelCol(rFormula.getLabelCol()).setPredictionCol(this.PREDICTED_LABEL_INDEX_COL());
         if (seed != null && seed.length() > 0) {
            fmc.setSeed(.MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(seed)));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (thresholds != null) {
            fmc.setThresholds(thresholds);
         } else {
            BoxedUnit var33 = BoxedUnit.UNIT;
         }

         IndexToString idxToStr = (new IndexToString()).setInputCol(this.PREDICTED_LABEL_INDEX_COL()).setOutputCol(this.PREDICTED_LABEL_COL()).setLabels(labels);
         PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, fmc, idxToStr})).fit(data);
         return new FMClassifierWrapper(pipeline, features, labels);
      }
   }

   public MLReader read() {
      return new FMClassifierWrapper.FMClassifierWrapperReader();
   }

   private FMClassifierWrapper$() {
   }
}

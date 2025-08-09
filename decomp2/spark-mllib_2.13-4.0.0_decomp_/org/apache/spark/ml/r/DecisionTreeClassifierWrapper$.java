package org.apache.spark.ml.r;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.DecisionTreeClassifier;
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

public final class DecisionTreeClassifierWrapper$ implements MLReadable {
   public static final DecisionTreeClassifierWrapper$ MODULE$ = new DecisionTreeClassifierWrapper$();
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

   public DecisionTreeClassifierWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final String impurity, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String seed, final int maxMemoryInMB, final boolean cacheNodeIds, final String handleInvalid) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setForceIndexLabel(true).setHandleInvalid(handleInvalid);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      Tuple2 var18 = RWrapperUtils$.MODULE$.getFeaturesAndLabels(rFormulaModel, data);
      if (var18 == null) {
         throw new MatchError(var18);
      } else {
         String[] features = (String[])var18._1();
         String[] labels = (String[])var18._2();
         Tuple2 var17 = new Tuple2(features, labels);
         String[] features = (String[])var17._1();
         String[] labels = (String[])var17._2();
         DecisionTreeClassifier dtc = (DecisionTreeClassifier)(new DecisionTreeClassifier()).setMaxDepth(maxDepth).setMaxBins(maxBins).setImpurity(impurity).setMinInstancesPerNode(minInstancesPerNode).setMinInfoGain(minInfoGain).setCheckpointInterval(checkpointInterval).setMaxMemoryInMB(maxMemoryInMB).setCacheNodeIds(cacheNodeIds).setFeaturesCol(rFormula.getFeaturesCol()).setLabelCol(rFormula.getLabelCol()).setPredictionCol(this.PREDICTED_LABEL_INDEX_COL());
         if (seed != null && seed.length() > 0) {
            dtc.setSeed(.MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(seed)));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         IndexToString idxToStr = (new IndexToString()).setInputCol(this.PREDICTED_LABEL_INDEX_COL()).setOutputCol(this.PREDICTED_LABEL_COL()).setLabels(labels);
         PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, dtc, idxToStr})).fit(data);
         return new DecisionTreeClassifierWrapper(pipeline, formula, features);
      }
   }

   public MLReader read() {
      return new DecisionTreeClassifierWrapper.DecisionTreeClassifierWrapperReader();
   }

   public DecisionTreeClassifierWrapper load(final String path) {
      return (DecisionTreeClassifierWrapper)MLReadable.load$(this, path);
   }

   private DecisionTreeClassifierWrapper$() {
   }
}

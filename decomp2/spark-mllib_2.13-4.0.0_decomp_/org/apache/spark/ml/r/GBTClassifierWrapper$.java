package org.apache.spark.ml.r;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.GBTClassifier;
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

public final class GBTClassifierWrapper$ implements MLReadable {
   public static final GBTClassifierWrapper$ MODULE$ = new GBTClassifierWrapper$();
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

   public GBTClassifierWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final int maxIter, final double stepSize, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String lossType, final String seed, final double subsamplingRate, final int maxMemoryInMB, final boolean cacheNodeIds, final String handleInvalid) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setForceIndexLabel(true).setHandleInvalid(handleInvalid);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      Tuple2 var23 = RWrapperUtils$.MODULE$.getFeaturesAndLabels(rFormulaModel, data);
      if (var23 == null) {
         throw new MatchError(var23);
      } else {
         String[] features = (String[])var23._1();
         String[] labels = (String[])var23._2();
         Tuple2 var22 = new Tuple2(features, labels);
         String[] features = (String[])var22._1();
         String[] labels = (String[])var22._2();
         GBTClassifier rfc = (GBTClassifier)(new GBTClassifier()).setMaxDepth(maxDepth).setMaxBins(maxBins).setMaxIter(maxIter).setStepSize(stepSize).setMinInstancesPerNode(minInstancesPerNode).setMinInfoGain(minInfoGain).setCheckpointInterval(checkpointInterval).setLossType(lossType).setSubsamplingRate(subsamplingRate).setMaxMemoryInMB(maxMemoryInMB).setCacheNodeIds(cacheNodeIds).setFeaturesCol(rFormula.getFeaturesCol()).setLabelCol(rFormula.getLabelCol()).setPredictionCol(this.PREDICTED_LABEL_INDEX_COL());
         if (seed != null && seed.length() > 0) {
            rfc.setSeed(.MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(seed)));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         IndexToString idxToStr = (new IndexToString()).setInputCol(this.PREDICTED_LABEL_INDEX_COL()).setOutputCol(this.PREDICTED_LABEL_COL()).setLabels(labels);
         PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, rfc, idxToStr})).fit(data);
         return new GBTClassifierWrapper(pipeline, formula, features);
      }
   }

   public MLReader read() {
      return new GBTClassifierWrapper.GBTClassifierWrapperReader();
   }

   public GBTClassifierWrapper load(final String path) {
      return (GBTClassifierWrapper)MLReadable.load$(this, path);
   }

   private GBTClassifierWrapper$() {
   }
}

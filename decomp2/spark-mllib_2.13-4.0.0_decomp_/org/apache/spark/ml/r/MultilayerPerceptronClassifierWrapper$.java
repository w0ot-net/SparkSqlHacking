package org.apache.spark.ml.r;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier;
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

public final class MultilayerPerceptronClassifierWrapper$ implements MLReadable {
   public static final MultilayerPerceptronClassifierWrapper$ MODULE$ = new MultilayerPerceptronClassifierWrapper$();
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

   public MultilayerPerceptronClassifierWrapper fit(final Dataset data, final String formula, final int blockSize, final int[] layers, final String solver, final int maxIter, final double tol, final double stepSize, final String seed, final double[] initialWeights, final String handleInvalid) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setForceIndexLabel(true).setHandleInvalid(handleInvalid);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      Tuple2 var18 = RWrapperUtils$.MODULE$.getFeaturesAndLabels(rFormulaModel, data);
      if (var18 == null) {
         throw new MatchError(var18);
      } else {
         String[] labels = (String[])var18._2();
         MultilayerPerceptronClassifier mlp = (MultilayerPerceptronClassifier)(new MultilayerPerceptronClassifier()).setLayers(layers).setBlockSize(blockSize).setSolver(solver).setMaxIter(maxIter).setTol(tol).setStepSize(stepSize).setFeaturesCol(rFormula.getFeaturesCol()).setLabelCol(rFormula.getLabelCol()).setPredictionCol(this.PREDICTED_LABEL_INDEX_COL());
         if (seed != null && seed.length() > 0) {
            mlp.setSeed((long).MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(seed)));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (initialWeights != null) {
            scala.Predef..MODULE$.require(initialWeights.length > 0);
            mlp.setInitialWeights(org.apache.spark.ml.linalg.Vectors..MODULE$.dense(initialWeights));
         } else {
            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         IndexToString idxToStr = (new IndexToString()).setInputCol(this.PREDICTED_LABEL_INDEX_COL()).setOutputCol(this.PREDICTED_LABEL_COL()).setLabels(labels);
         PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, mlp, idxToStr})).fit(data);
         return new MultilayerPerceptronClassifierWrapper(pipeline);
      }
   }

   public MLReader read() {
      return new MultilayerPerceptronClassifierWrapper.MultilayerPerceptronClassifierWrapperReader();
   }

   public MultilayerPerceptronClassifierWrapper load(final String path) {
      return (MultilayerPerceptronClassifierWrapper)MLReadable.load$(this, path);
   }

   private MultilayerPerceptronClassifierWrapper$() {
   }
}

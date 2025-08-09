package org.apache.spark.ml.r;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.feature.IndexToString;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Matrices.;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxedUnit;

public final class LogisticRegressionWrapper$ implements MLReadable {
   public static final LogisticRegressionWrapper$ MODULE$ = new LogisticRegressionWrapper$();
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

   public LogisticRegressionWrapper fit(final Dataset data, final String formula, final double regParam, final double elasticNetParam, final int maxIter, final double tol, final String family, final boolean standardization, final double[] thresholds, final String weightCol, final int aggregationDepth, final int numRowsOfBoundsOnCoefficients, final int numColsOfBoundsOnCoefficients, final double[] lowerBoundsOnCoefficients, final double[] upperBoundsOnCoefficients, final double[] lowerBoundsOnIntercepts, final double[] upperBoundsOnIntercepts, final String handleInvalid) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setForceIndexLabel(true).setHandleInvalid(handleInvalid);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      boolean fitIntercept = rFormula.hasIntercept();
      Tuple2 var27 = RWrapperUtils$.MODULE$.getFeaturesAndLabels(rFormulaModel, data);
      if (var27 != null) {
         String[] features = (String[])var27._1();
         String[] labels = (String[])var27._2();
         Tuple2 var26 = new Tuple2(features, labels);
         String[] features = (String[])var26._1();
         String[] labels = (String[])var26._2();
         LogisticRegression lr = ((LogisticRegression)(new LogisticRegression()).setRegParam(regParam).setElasticNetParam(elasticNetParam).setMaxIter(maxIter).setTol(tol).setFitIntercept(fitIntercept).setFamily(family).setStandardization(standardization).setFeaturesCol(rFormula.getFeaturesCol()).setLabelCol(rFormula.getLabelCol()).setPredictionCol(this.PREDICTED_LABEL_INDEX_COL())).setAggregationDepth(aggregationDepth);
         if (thresholds.length > 1) {
            lr.setThresholds(thresholds);
         } else {
            lr.setThreshold(thresholds[0]);
         }

         if (weightCol != null) {
            lr.setWeightCol(weightCol);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (numRowsOfBoundsOnCoefficients != 0 && numColsOfBoundsOnCoefficients != 0 && lowerBoundsOnCoefficients != null) {
            Matrix coef = .MODULE$.dense(numRowsOfBoundsOnCoefficients, numColsOfBoundsOnCoefficients, lowerBoundsOnCoefficients);
            lr.setLowerBoundsOnCoefficients(coef);
         } else {
            BoxedUnit var39 = BoxedUnit.UNIT;
         }

         if (numRowsOfBoundsOnCoefficients != 0 && numColsOfBoundsOnCoefficients != 0 && upperBoundsOnCoefficients != null) {
            Matrix coef = .MODULE$.dense(numRowsOfBoundsOnCoefficients, numColsOfBoundsOnCoefficients, upperBoundsOnCoefficients);
            lr.setUpperBoundsOnCoefficients(coef);
         } else {
            BoxedUnit var40 = BoxedUnit.UNIT;
         }

         if (lowerBoundsOnIntercepts != null) {
            Vector intercept = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(lowerBoundsOnIntercepts);
            lr.setLowerBoundsOnIntercepts(intercept);
         } else {
            BoxedUnit var41 = BoxedUnit.UNIT;
         }

         if (upperBoundsOnIntercepts != null) {
            Vector intercept = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(upperBoundsOnIntercepts);
            lr.setUpperBoundsOnIntercepts(intercept);
         } else {
            BoxedUnit var42 = BoxedUnit.UNIT;
         }

         IndexToString idxToStr = (new IndexToString()).setInputCol(this.PREDICTED_LABEL_INDEX_COL()).setOutputCol(this.PREDICTED_LABEL_COL()).setLabels(labels);
         PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, lr, idxToStr})).fit(data);
         return new LogisticRegressionWrapper(pipeline, features, labels);
      } else {
         throw new MatchError(var27);
      }
   }

   public MLReader read() {
      return new LogisticRegressionWrapper.LogisticRegressionWrapperReader();
   }

   public LogisticRegressionWrapper load(final String path) {
      return (LogisticRegressionWrapper)MLReadable.load$(this, path);
   }

   private LogisticRegressionWrapper$() {
   }
}

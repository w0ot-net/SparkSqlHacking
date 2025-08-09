package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.regression.GBTRegressor;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;

public final class GBTRegressorWrapper$ implements MLReadable {
   public static final GBTRegressorWrapper$ MODULE$ = new GBTRegressorWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public GBTRegressorWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final int maxIter, final double stepSize, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String lossType, final String seed, final double subsamplingRate, final int maxMemoryInMB, final boolean cacheNodeIds) {
      RFormula rFormula = (new RFormula()).setFormula(formula);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      StructType schema = rFormulaModel.transform(data).schema();
      Attribute[] featureAttrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(schema.apply(rFormulaModel.getFeaturesCol())).attributes().get();
      String[] features = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs), (x$1) -> (String)x$1.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      GBTRegressor rfr = (GBTRegressor)(new GBTRegressor()).setMaxDepth(maxDepth).setMaxBins(maxBins).setMaxIter(maxIter).setStepSize(stepSize).setMinInstancesPerNode(minInstancesPerNode).setMinInfoGain(minInfoGain).setCheckpointInterval(checkpointInterval).setLossType(lossType).setSubsamplingRate(subsamplingRate).setMaxMemoryInMB(maxMemoryInMB).setCacheNodeIds(cacheNodeIds).setFeaturesCol(rFormula.getFeaturesCol());
      if (seed != null && seed.length() > 0) {
         rfr.setSeed(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(seed)));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, rfr})).fit(data);
      return new GBTRegressorWrapper(pipeline, formula, features);
   }

   public MLReader read() {
      return new GBTRegressorWrapper.GBTRegressorWrapperReader();
   }

   public GBTRegressorWrapper load(final String path) {
      return (GBTRegressorWrapper)MLReadable.load$(this, path);
   }

   private GBTRegressorWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

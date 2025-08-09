package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.regression.RandomForestRegressor;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;

public final class RandomForestRegressorWrapper$ implements MLReadable {
   public static final RandomForestRegressorWrapper$ MODULE$ = new RandomForestRegressorWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public RandomForestRegressorWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final int numTrees, final String impurity, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String featureSubsetStrategy, final String seed, final double subsamplingRate, final int maxMemoryInMB, final boolean cacheNodeIds, final boolean bootstrap) {
      RFormula rFormula = (new RFormula()).setFormula(formula);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      StructType schema = rFormulaModel.transform(data).schema();
      Attribute[] featureAttrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(schema.apply(rFormulaModel.getFeaturesCol())).attributes().get();
      String[] features = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs), (x$1) -> (String)x$1.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      RandomForestRegressor rfr = ((RandomForestRegressor)(new RandomForestRegressor()).setMaxDepth(maxDepth).setMaxBins(maxBins).setNumTrees(numTrees).setImpurity(impurity).setMinInstancesPerNode(minInstancesPerNode).setMinInfoGain(minInfoGain).setCheckpointInterval(checkpointInterval).setFeatureSubsetStrategy(featureSubsetStrategy).setSubsamplingRate(subsamplingRate).setMaxMemoryInMB(maxMemoryInMB).setCacheNodeIds(cacheNodeIds).setFeaturesCol(rFormula.getFeaturesCol())).setBootstrap(bootstrap);
      if (seed != null && seed.length() > 0) {
         rfr.setSeed(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(seed)));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, rfr})).fit(data);
      return new RandomForestRegressorWrapper(pipeline, formula, features);
   }

   public MLReader read() {
      return new RandomForestRegressorWrapper.RandomForestRegressorWrapperReader();
   }

   public RandomForestRegressorWrapper load(final String path) {
      return (RandomForestRegressorWrapper)MLReadable.load$(this, path);
   }

   private RandomForestRegressorWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

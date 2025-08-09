package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.regression.DecisionTreeRegressor;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;

public final class DecisionTreeRegressorWrapper$ implements MLReadable {
   public static final DecisionTreeRegressorWrapper$ MODULE$ = new DecisionTreeRegressorWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public DecisionTreeRegressorWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final String impurity, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String seed, final int maxMemoryInMB, final boolean cacheNodeIds) {
      RFormula rFormula = (new RFormula()).setFormula(formula);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      StructType schema = rFormulaModel.transform(data).schema();
      Attribute[] featureAttrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(schema.apply(rFormulaModel.getFeaturesCol())).attributes().get();
      String[] features = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs), (x$1) -> (String)x$1.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      DecisionTreeRegressor dtr = (DecisionTreeRegressor)(new DecisionTreeRegressor()).setMaxDepth(maxDepth).setMaxBins(maxBins).setImpurity(impurity).setMinInstancesPerNode(minInstancesPerNode).setMinInfoGain(minInfoGain).setCheckpointInterval(checkpointInterval).setMaxMemoryInMB(maxMemoryInMB).setCacheNodeIds(cacheNodeIds).setFeaturesCol(rFormula.getFeaturesCol());
      if (seed != null && seed.length() > 0) {
         dtr.setSeed(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(seed)));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, dtr})).fit(data);
      return new DecisionTreeRegressorWrapper(pipeline, formula, features);
   }

   public MLReader read() {
      return new DecisionTreeRegressorWrapper.DecisionTreeRegressorWrapperReader();
   }

   public DecisionTreeRegressorWrapper load(final String path) {
      return (DecisionTreeRegressorWrapper)MLReadable.load$(this, path);
   }

   private DecisionTreeRegressorWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

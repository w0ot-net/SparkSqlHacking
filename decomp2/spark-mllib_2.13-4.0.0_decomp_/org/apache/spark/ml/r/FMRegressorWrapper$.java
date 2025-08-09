package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.regression.FMRegressor;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;

public final class FMRegressorWrapper$ implements MLReadable {
   public static final FMRegressorWrapper$ MODULE$ = new FMRegressorWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public Object load(final String path) {
      return MLReadable.load$(this, path);
   }

   public FMRegressorWrapper fit(final Dataset data, final String formula, final int factorSize, final boolean fitLinear, final double regParam, final double miniBatchFraction, final double initStd, final int maxIter, final double stepSize, final double tol, final String solver, final String seed, final String stringIndexerOrderType) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setStringIndexerOrderType(stringIndexerOrderType);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      boolean fitIntercept = rFormula.hasIntercept();
      StructType schema = rFormulaModel.transform(data).schema();
      Attribute[] featureAttrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(schema.apply(rFormulaModel.getFeaturesCol())).attributes().get();
      String[] features = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs), (x$1) -> (String)x$1.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      FMRegressor fmr = (FMRegressor)(new FMRegressor()).setFactorSize(factorSize).setFitIntercept(fitIntercept).setFitLinear(fitLinear).setRegParam(regParam).setMiniBatchFraction(miniBatchFraction).setInitStd(initStd).setMaxIter(maxIter).setStepSize(stepSize).setTol(tol).setSolver(solver).setFeaturesCol(rFormula.getFeaturesCol());
      if (seed != null && seed.length() > 0) {
         fmr.setSeed(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(seed)));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, fmr})).fit(data);
      return new FMRegressorWrapper(pipeline, features);
   }

   public MLReader read() {
      return new FMRegressorWrapper.FMRegressorWrapperReader();
   }

   private FMRegressorWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

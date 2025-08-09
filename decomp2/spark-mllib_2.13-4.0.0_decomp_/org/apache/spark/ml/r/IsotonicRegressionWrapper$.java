package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.regression.IsotonicRegression;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;

public final class IsotonicRegressionWrapper$ implements MLReadable {
   public static final IsotonicRegressionWrapper$ MODULE$ = new IsotonicRegressionWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public IsotonicRegressionWrapper fit(final Dataset data, final String formula, final boolean isotonic, final int featureIndex, final String weightCol) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setFeaturesCol("features");
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      StructType schema = rFormulaModel.transform(data).schema();
      Attribute[] featureAttrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(schema.apply(rFormulaModel.getFeaturesCol())).attributes().get();
      String[] features = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs), (x$1) -> (String)x$1.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      scala.Predef..MODULE$.require(features.length == 1);
      IsotonicRegression isotonicRegression = (new IsotonicRegression()).setIsotonic(isotonic).setFeatureIndex(featureIndex).setFeaturesCol(rFormula.getFeaturesCol());
      if (weightCol != null) {
         isotonicRegression.setWeightCol(weightCol);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, isotonicRegression})).fit(data);
      return new IsotonicRegressionWrapper(pipeline, features);
   }

   public MLReader read() {
      return new IsotonicRegressionWrapper.IsotonicRegressionWrapperReader();
   }

   public IsotonicRegressionWrapper load(final String path) {
      return (IsotonicRegressionWrapper)MLReadable.load$(this, path);
   }

   private IsotonicRegressionWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

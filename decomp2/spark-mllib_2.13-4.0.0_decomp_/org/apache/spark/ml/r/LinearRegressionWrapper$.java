package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;

public final class LinearRegressionWrapper$ implements MLReadable {
   public static final LinearRegressionWrapper$ MODULE$ = new LinearRegressionWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public Object load(final String path) {
      return MLReadable.load$(this, path);
   }

   public LinearRegressionWrapper fit(final Dataset data, final String formula, final int maxIter, final double regParam, final double elasticNetParam, final double tol, final boolean standardization, final String solver, final String weightCol, final int aggregationDepth, final String loss, final double epsilon, final String stringIndexerOrderType) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setStringIndexerOrderType(stringIndexerOrderType);
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      boolean fitIntercept = rFormula.hasIntercept();
      StructType schema = rFormulaModel.transform(data).schema();
      Attribute[] featureAttrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(schema.apply(rFormulaModel.getFeaturesCol())).attributes().get();
      String[] features = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs), (x$1) -> (String)x$1.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      LinearRegression lm = (LinearRegression)(new LinearRegression()).setMaxIter(maxIter).setRegParam(regParam).setElasticNetParam(elasticNetParam).setTol(tol).setFitIntercept(fitIntercept).setStandardization(standardization).setSolver(solver).setAggregationDepth(aggregationDepth).setLoss(loss).setEpsilon(epsilon).setFeaturesCol(rFormula.getFeaturesCol());
      if (weightCol != null) {
         lm.setWeightCol(weightCol);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, lm})).fit(data);
      return new LinearRegressionWrapper(pipeline, features);
   }

   public MLReader read() {
      return new LinearRegressionWrapper.LinearRegressionWrapperReader();
   }

   private LinearRegressionWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

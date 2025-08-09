package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;

public final class KMeansWrapper$ implements MLReadable {
   public static final KMeansWrapper$ MODULE$ = new KMeansWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   private boolean $lessinit$greater$default$4() {
      return false;
   }

   public KMeansWrapper fit(final Dataset data, final String formula, final int k, final int maxIter, final String initMode, final String seed, final int initSteps, final double tol) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setFeaturesCol("features");
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      StructType schema = rFormulaModel.transform(data).schema();
      Attribute[] featureAttrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(schema.apply(rFormulaModel.getFeaturesCol())).attributes().get();
      String[] features = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs), (x$2) -> (String)x$2.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      KMeans kMeans = (new KMeans()).setK(k).setMaxIter(maxIter).setInitMode(initMode).setFeaturesCol(rFormula.getFeaturesCol()).setInitSteps(initSteps).setTol(tol);
      if (seed != null && seed.length() > 0) {
         kMeans.setSeed((long)scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(seed)));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, kMeans})).fit(data);
      KMeansModel kMeansModel = (KMeansModel)pipeline.stages()[1];
      long[] size = kMeansModel.summary().clusterSizes();
      return new KMeansWrapper(pipeline, features, size, this.$lessinit$greater$default$4());
   }

   public MLReader read() {
      return new KMeansWrapper.KMeansWrapperReader();
   }

   public KMeansWrapper load(final String path) {
      return (KMeansWrapper)MLReadable.load$(this, path);
   }

   private KMeansWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.clustering.GaussianMixture;
import org.apache.spark.ml.clustering.GaussianMixtureModel;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.collection.ArrayOps.;

public final class GaussianMixtureWrapper$ implements MLReadable {
   public static final GaussianMixtureWrapper$ MODULE$ = new GaussianMixtureWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   private boolean $lessinit$greater$default$4() {
      return false;
   }

   public GaussianMixtureWrapper fit(final Dataset data, final String formula, final int k, final int maxIter, final double tol) {
      RFormula rFormula = (new RFormula()).setFormula(formula).setFeaturesCol("features");
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
      RFormulaModel rFormulaModel = rFormula.fit(data);
      StructType schema = rFormulaModel.transform(data).schema();
      Attribute[] featureAttrs = (Attribute[])AttributeGroup$.MODULE$.fromStructField(schema.apply(rFormulaModel.getFeaturesCol())).attributes().get();
      String[] features = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs), (x$3) -> (String)x$3.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      int dim = features.length;
      GaussianMixture gm = (new GaussianMixture()).setK(k).setMaxIter(maxIter).setTol(tol).setFeaturesCol(rFormula.getFeaturesCol());
      PipelineModel pipeline = (new Pipeline()).setStages((PipelineStage[])(new PipelineStage[]{rFormulaModel, gm})).fit(data);
      GaussianMixtureModel gmm = (GaussianMixtureModel)pipeline.stages()[1];
      double logLikelihood = gmm.summary().logLikelihood();
      return new GaussianMixtureWrapper(pipeline, dim, logLikelihood, this.$lessinit$greater$default$4());
   }

   public MLReader read() {
      return new GaussianMixtureWrapper.GaussianMixtureWrapperReader();
   }

   public GaussianMixtureWrapper load(final String path) {
      return (GaussianMixtureWrapper)MLReadable.load$(this, path);
   }

   private GaussianMixtureWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

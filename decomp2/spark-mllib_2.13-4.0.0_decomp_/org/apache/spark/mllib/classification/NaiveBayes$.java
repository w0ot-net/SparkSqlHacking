package org.apache.spark.mllib.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import scala.Predef.;
import scala.collection.immutable.Set;
import scala.runtime.ModuleSerializationProxy;

public final class NaiveBayes$ implements Serializable {
   public static final NaiveBayes$ MODULE$ = new NaiveBayes$();
   private static final String Multinomial = "multinomial";
   private static final String Bernoulli = "bernoulli";
   private static final Set supportedModelTypes;

   static {
      supportedModelTypes = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{MODULE$.Multinomial(), MODULE$.Bernoulli()})));
   }

   public String Multinomial() {
      return Multinomial;
   }

   public String Bernoulli() {
      return Bernoulli;
   }

   public Set supportedModelTypes() {
      return supportedModelTypes;
   }

   public NaiveBayesModel train(final RDD input) {
      return (new NaiveBayes()).run(input);
   }

   public NaiveBayesModel train(final RDD input, final double lambda) {
      return (new NaiveBayes(lambda, this.Multinomial())).run(input);
   }

   public NaiveBayesModel train(final RDD input, final double lambda, final String modelType) {
      .MODULE$.require(this.supportedModelTypes().contains(modelType), () -> "NaiveBayes was created with an unknown modelType: " + modelType + ".");
      return (new NaiveBayes(lambda, modelType)).run(input);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NaiveBayes$.class);
   }

   private NaiveBayes$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

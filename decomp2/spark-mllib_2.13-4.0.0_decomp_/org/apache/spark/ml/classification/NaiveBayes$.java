package org.apache.spark.ml.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Set;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class NaiveBayes$ implements DefaultParamsReadable, Serializable {
   public static final NaiveBayes$ MODULE$ = new NaiveBayes$();
   private static final String Multinomial;
   private static final String Bernoulli;
   private static final String Gaussian;
   private static final String Complement;
   private static final Set supportedModelTypes;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      Multinomial = "multinomial";
      Bernoulli = "bernoulli";
      Gaussian = "gaussian";
      Complement = "complement";
      supportedModelTypes = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{MODULE$.Multinomial(), MODULE$.Bernoulli(), MODULE$.Gaussian(), MODULE$.Complement()})));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String Multinomial() {
      return Multinomial;
   }

   public String Bernoulli() {
      return Bernoulli;
   }

   public String Gaussian() {
      return Gaussian;
   }

   public String Complement() {
      return Complement;
   }

   public Set supportedModelTypes() {
      return supportedModelTypes;
   }

   public void requireNonnegativeValues(final Vector v) {
      .MODULE$.require(v.nonZeroIterator().forall((x$9) -> BoxesRunTime.boxToBoolean($anonfun$requireNonnegativeValues$1(x$9))), () -> "Naive Bayes requires nonnegative feature values but found " + v + ".");
   }

   public void requireZeroOneBernoulliValues(final Vector v) {
      .MODULE$.require(v.nonZeroIterator().forall((x$10) -> BoxesRunTime.boxToBoolean($anonfun$requireZeroOneBernoulliValues$1(x$10))), () -> "Bernoulli naive Bayes requires 0 or 1 feature values but found " + v + ".");
   }

   public NaiveBayes load(final String path) {
      return (NaiveBayes)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NaiveBayes$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$requireNonnegativeValues$1(final Tuple2 x$9) {
      return x$9._2$mcD$sp() > (double)0.0F;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$requireZeroOneBernoulliValues$1(final Tuple2 x$10) {
      return x$10._2$mcD$sp() == (double)1.0F;
   }

   private NaiveBayes$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

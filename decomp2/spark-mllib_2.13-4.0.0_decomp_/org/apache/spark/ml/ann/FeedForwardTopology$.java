package org.apache.spark.ml.ann;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public final class FeedForwardTopology$ implements Serializable {
   public static final FeedForwardTopology$ MODULE$ = new FeedForwardTopology$();

   public FeedForwardTopology apply(final Layer[] layers) {
      return new FeedForwardTopology(layers);
   }

   public FeedForwardTopology multiLayerPerceptron(final int[] layerSizes, final boolean softmaxOnTop) {
      Layer[] layers = new Layer[(layerSizes.length - 1) * 2];
      .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), layerSizes.length - 1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         layers[i * 2] = new AffineLayer(layerSizes[i], layerSizes[i + 1]);
         layers[i * 2 + 1] = (Layer)(i == layerSizes.length - 2 ? (softmaxOnTop ? new SoftmaxLayerWithCrossEntropyLoss() : new SigmoidLayerWithSquaredError()) : new FunctionalLayer(new SigmoidFunction()));
      });
      return this.apply(layers);
   }

   public boolean multiLayerPerceptron$default$2() {
      return true;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FeedForwardTopology$.class);
   }

   private FeedForwardTopology$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

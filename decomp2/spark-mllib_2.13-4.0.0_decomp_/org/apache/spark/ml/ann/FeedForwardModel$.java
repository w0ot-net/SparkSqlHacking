package org.apache.spark.ml.ann;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.util.random.XORShiftRandom;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

public final class FeedForwardModel$ implements Serializable {
   public static final FeedForwardModel$ MODULE$ = new FeedForwardModel$();

   public FeedForwardModel apply(final FeedForwardTopology topology, final Vector weights) {
      int expectedWeightSize = BoxesRunTime.unboxToInt(.MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(topology.layers()), (x$1) -> BoxesRunTime.boxToInteger($anonfun$apply$1(x$1)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      .MODULE$.require(weights.size() == expectedWeightSize, () -> "Expected weight vector of size " + expectedWeightSize + " but got size " + weights.size() + ".");
      return new FeedForwardModel(weights, topology);
   }

   public FeedForwardModel apply(final FeedForwardTopology topology, final long seed) {
      Layer[] layers = topology.layers();
      LayerModel[] layerModels = new LayerModel[layers.length];
      DenseVector weights = breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(BoxesRunTime.unboxToInt(.MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(topology.layers()), (x$2) -> BoxesRunTime.boxToInteger($anonfun$apply$3(x$2)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
      IntRef offset = IntRef.create(0);
      XORShiftRandom random = new XORShiftRandom(seed);
      scala.collection.ArrayOps..MODULE$.indices$extension(.MODULE$.refArrayOps(layers)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         layerModels[i] = layers[i].initModel(new DenseVector.mcD.sp(weights.data$mcD$sp(), offset.elem, 1, layers[i].weightSize()), random);
         offset.elem += layers[i].weightSize();
      });
      return new FeedForwardModel(org.apache.spark.ml.linalg.Vectors..MODULE$.fromBreeze(weights), topology);
   }

   public long apply$default$2() {
      return 11L;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FeedForwardModel$.class);
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$1(final Layer x$1) {
      return x$1.weightSize();
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$3(final Layer x$2) {
      return x$2.weightSize();
   }

   private FeedForwardModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

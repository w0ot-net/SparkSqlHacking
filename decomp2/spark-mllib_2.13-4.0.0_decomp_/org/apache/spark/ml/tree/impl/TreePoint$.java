package org.apache.spark.ml.tree.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.tree.ContinuousSplit;
import org.apache.spark.ml.tree.Split;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.collection.ArrayOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class TreePoint$ implements Serializable {
   public static final TreePoint$ MODULE$ = new TreePoint$();

   public RDD convertToTreeRDD(final RDD input, final Split[][] splits, final DecisionTreeMetadata metadata) {
      int[] featureArity = new int[metadata.numFeatures()];

      for(int featureIndex = 0; featureIndex < metadata.numFeatures(); ++featureIndex) {
         featureArity[featureIndex] = BoxesRunTime.unboxToInt(metadata.featureArity().getOrElse(BoxesRunTime.boxToInteger(featureIndex), (JFunction0.mcI.sp)() -> 0));
      }

      double[][] thresholds = (double[][]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.intArrayOps(featureArity))), (x0$1) -> {
         if (x0$1 != null) {
            int arity = x0$1._1$mcI$sp();
            int idx = x0$1._2$mcI$sp();
            return arity == 0 ? (double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(splits[idx]), (x$1) -> BoxesRunTime.boxToDouble($anonfun$convertToTreeRDD$3(x$1)), scala.reflect.ClassTag..MODULE$.Double()) : scala.Array..MODULE$.emptyDoubleArray();
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      return input.map((x) -> MODULE$.labeledPointToTreePoint(x, thresholds, featureArity), scala.reflect.ClassTag..MODULE$.apply(TreePoint.class));
   }

   private TreePoint labeledPointToTreePoint(final Instance instance, final double[][] thresholds, final int[] featureArity) {
      int numFeatures = instance.features().size();
      int[] arr = new int[numFeatures];

      for(int featureIndex = 0; featureIndex < numFeatures; ++featureIndex) {
         arr[featureIndex] = this.findBin(featureIndex, instance, featureArity[featureIndex], thresholds[featureIndex]);
      }

      return new TreePoint(instance.label(), arr, instance.weight());
   }

   private int findBin(final int featureIndex, final Instance instance, final int featureArity, final double[] thresholds) {
      double featureValue = instance.features().apply(featureIndex);
      if (featureArity == 0) {
         int idx = Arrays.binarySearch(thresholds, featureValue);
         return idx >= 0 ? idx : -idx - 1;
      } else if (!(featureValue < (double)0) && !(featureValue >= (double)featureArity)) {
         return (int)featureValue;
      } else {
         throw new IllegalArgumentException("DecisionTree given invalid data: Feature " + featureIndex + " is categorical with values in {0,...," + (featureArity - 1) + ", but a data point gives it value " + featureValue + ".\n  Bad data point: " + instance);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreePoint$.class);
   }

   // $FF: synthetic method
   public static final double $anonfun$convertToTreeRDD$3(final Split x$1) {
      return ((ContinuousSplit)x$1).threshold();
   }

   private TreePoint$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

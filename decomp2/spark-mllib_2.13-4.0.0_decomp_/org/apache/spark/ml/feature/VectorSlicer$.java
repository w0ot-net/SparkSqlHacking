package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.collection.ArrayOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

public final class VectorSlicer$ implements DefaultParamsReadable, Serializable {
   public static final VectorSlicer$ MODULE$ = new VectorSlicer$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public boolean validIndices(final int[] indices) {
      if (.MODULE$.isEmpty$extension(scala.Predef..MODULE$.intArrayOps(indices))) {
         return true;
      } else {
         return indices.length == ((int[]).MODULE$.distinct$extension(scala.Predef..MODULE$.intArrayOps(indices))).length && .MODULE$.forall$extension(scala.Predef..MODULE$.intArrayOps(indices), (JFunction1.mcZI.sp)(x$3) -> x$3 >= 0);
      }
   }

   public boolean validNames(final String[] names) {
      return .MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])names), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$validNames$1(x$4))) && names.length == ((String[]).MODULE$.distinct$extension(scala.Predef..MODULE$.refArrayOps((Object[])names))).length;
   }

   public VectorSlicer load(final String path) {
      return (VectorSlicer)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorSlicer$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$validNames$1(final String x$4) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   private VectorSlicer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

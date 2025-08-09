package org.apache.spark.ml.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import scala.collection.ArrayOps.;
import scala.runtime.ModuleSerializationProxy;

public final class TreeEnsembleParams$ implements Serializable {
   public static final TreeEnsembleParams$ MODULE$ = new TreeEnsembleParams$();
   private static final String[] supportedFeatureSubsetStrategies;

   static {
      supportedFeatureSubsetStrategies = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"auto", "all", "onethird", "sqrt", "log2"})), (x$3) -> x$3.toLowerCase(Locale.ROOT), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public final String[] supportedFeatureSubsetStrategies() {
      return supportedFeatureSubsetStrategies;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeEnsembleParams$.class);
   }

   private TreeEnsembleParams$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

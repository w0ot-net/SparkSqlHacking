package org.apache.spark.ml.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import scala.collection.ArrayOps.;
import scala.runtime.ModuleSerializationProxy;

public final class TreeClassifierParams$ implements Serializable {
   public static final TreeClassifierParams$ MODULE$ = new TreeClassifierParams$();
   private static final String[] supportedImpurities;

   static {
      supportedImpurities = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"entropy", "gini"})), (x$1) -> x$1.toLowerCase(Locale.ROOT), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public final String[] supportedImpurities() {
      return supportedImpurities;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeClassifierParams$.class);
   }

   private TreeClassifierParams$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

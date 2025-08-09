package org.apache.spark.ml.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import scala.collection.ArrayOps.;
import scala.runtime.ModuleSerializationProxy;

public final class GBTClassifierParams$ implements Serializable {
   public static final GBTClassifierParams$ MODULE$ = new GBTClassifierParams$();
   private static final String[] supportedLossTypes;

   static {
      supportedLossTypes = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"logistic"})), (x$7) -> x$7.toLowerCase(Locale.ROOT), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public final String[] supportedLossTypes() {
      return supportedLossTypes;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GBTClassifierParams$.class);
   }

   private GBTClassifierParams$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

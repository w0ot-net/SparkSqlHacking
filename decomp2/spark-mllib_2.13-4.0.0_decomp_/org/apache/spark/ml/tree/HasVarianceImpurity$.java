package org.apache.spark.ml.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import scala.collection.ArrayOps.;
import scala.runtime.ModuleSerializationProxy;

public final class HasVarianceImpurity$ implements Serializable {
   public static final HasVarianceImpurity$ MODULE$ = new HasVarianceImpurity$();
   private static final String[] supportedImpurities;

   static {
      supportedImpurities = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"variance"})), (x$2) -> x$2.toLowerCase(Locale.ROOT), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public final String[] supportedImpurities() {
      return supportedImpurities;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HasVarianceImpurity$.class);
   }

   private HasVarianceImpurity$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

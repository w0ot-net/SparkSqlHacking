package org.apache.spark.ml.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.collection.ArrayOps.;
import scala.runtime.ModuleSerializationProxy;

public final class LogisticRegression$ implements DefaultParamsReadable, Serializable {
   public static final LogisticRegression$ MODULE$ = new LogisticRegression$();
   private static final String[] supportedFamilyNames;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      supportedFamilyNames = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"auto", "binomial", "multinomial"})), (x$11) -> x$11.toLowerCase(Locale.ROOT), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public LogisticRegression load(final String path) {
      return (LogisticRegression)MLReadable.load$(this, path);
   }

   public String[] supportedFamilyNames() {
      return supportedFamilyNames;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LogisticRegression$.class);
   }

   private LogisticRegression$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

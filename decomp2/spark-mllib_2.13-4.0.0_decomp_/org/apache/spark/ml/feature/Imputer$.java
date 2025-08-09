package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class Imputer$ implements DefaultParamsReadable, Serializable {
   public static final Imputer$ MODULE$ = new Imputer$();
   private static final String mean;
   private static final String median;
   private static final String mode;
   private static final String[] supportedStrategies;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      mean = "mean";
      median = "median";
      mode = "mode";
      supportedStrategies = (String[])((Object[])(new String[]{MODULE$.mean(), MODULE$.median(), MODULE$.mode()}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String mean() {
      return mean;
   }

   public String median() {
      return median;
   }

   public String mode() {
      return mode;
   }

   public String[] supportedStrategies() {
      return supportedStrategies;
   }

   public Imputer load(final String path) {
      return (Imputer)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Imputer$.class);
   }

   private Imputer$() {
   }
}

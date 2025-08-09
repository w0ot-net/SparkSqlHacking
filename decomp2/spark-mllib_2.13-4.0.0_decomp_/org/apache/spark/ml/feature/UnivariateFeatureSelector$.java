package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class UnivariateFeatureSelector$ implements DefaultParamsReadable, Serializable {
   public static final UnivariateFeatureSelector$ MODULE$ = new UnivariateFeatureSelector$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public UnivariateFeatureSelector load(final String path) {
      return (UnivariateFeatureSelector)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnivariateFeatureSelector$.class);
   }

   private UnivariateFeatureSelector$() {
   }
}

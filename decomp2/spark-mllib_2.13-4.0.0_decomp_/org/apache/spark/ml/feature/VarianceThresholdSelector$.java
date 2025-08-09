package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class VarianceThresholdSelector$ implements DefaultParamsReadable, Serializable {
   public static final VarianceThresholdSelector$ MODULE$ = new VarianceThresholdSelector$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public VarianceThresholdSelector load(final String path) {
      return (VarianceThresholdSelector)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VarianceThresholdSelector$.class);
   }

   private VarianceThresholdSelector$() {
   }
}

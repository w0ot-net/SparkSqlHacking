package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MinMaxScalerModel$ implements MLReadable, Serializable {
   public static final MinMaxScalerModel$ MODULE$ = new MinMaxScalerModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new MinMaxScalerModel.MinMaxScalerModelReader();
   }

   public MinMaxScalerModel load(final String path) {
      return (MinMaxScalerModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MinMaxScalerModel$.class);
   }

   private MinMaxScalerModel$() {
   }
}

package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MaxAbsScalerModel$ implements MLReadable, Serializable {
   public static final MaxAbsScalerModel$ MODULE$ = new MaxAbsScalerModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new MaxAbsScalerModel.MaxAbsScalerModelReader();
   }

   public MaxAbsScalerModel load(final String path) {
      return (MaxAbsScalerModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MaxAbsScalerModel$.class);
   }

   private MaxAbsScalerModel$() {
   }
}

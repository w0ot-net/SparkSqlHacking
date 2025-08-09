package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class RobustScalerModel$ implements MLReadable, Serializable {
   public static final RobustScalerModel$ MODULE$ = new RobustScalerModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new RobustScalerModel.RobustScalerModelReader();
   }

   public RobustScalerModel load(final String path) {
      return (RobustScalerModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RobustScalerModel$.class);
   }

   private RobustScalerModel$() {
   }
}

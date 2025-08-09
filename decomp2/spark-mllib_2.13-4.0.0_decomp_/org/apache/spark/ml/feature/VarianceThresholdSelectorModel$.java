package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class VarianceThresholdSelectorModel$ implements MLReadable, Serializable {
   public static final VarianceThresholdSelectorModel$ MODULE$ = new VarianceThresholdSelectorModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new VarianceThresholdSelectorModel.VarianceThresholdSelectorModelReader();
   }

   public VarianceThresholdSelectorModel load(final String path) {
      return (VarianceThresholdSelectorModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VarianceThresholdSelectorModel$.class);
   }

   private VarianceThresholdSelectorModel$() {
   }
}

package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MultilayerPerceptronClassificationModel$ implements MLReadable, Serializable {
   public static final MultilayerPerceptronClassificationModel$ MODULE$ = new MultilayerPerceptronClassificationModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new MultilayerPerceptronClassificationModel.MultilayerPerceptronClassificationModelReader();
   }

   public MultilayerPerceptronClassificationModel load(final String path) {
      return (MultilayerPerceptronClassificationModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MultilayerPerceptronClassificationModel$.class);
   }

   private MultilayerPerceptronClassificationModel$() {
   }
}

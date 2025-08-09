package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class FMClassificationModel$ implements MLReadable, Serializable {
   public static final FMClassificationModel$ MODULE$ = new FMClassificationModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new FMClassificationModel.FMClassificationModelReader();
   }

   public FMClassificationModel load(final String path) {
      return (FMClassificationModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FMClassificationModel$.class);
   }

   private FMClassificationModel$() {
   }
}

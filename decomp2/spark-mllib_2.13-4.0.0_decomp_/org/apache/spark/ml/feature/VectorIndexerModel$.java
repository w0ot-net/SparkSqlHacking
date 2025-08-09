package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class VectorIndexerModel$ implements MLReadable, Serializable {
   public static final VectorIndexerModel$ MODULE$ = new VectorIndexerModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new VectorIndexerModel.VectorIndexerModelReader();
   }

   public VectorIndexerModel load(final String path) {
      return (VectorIndexerModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorIndexerModel$.class);
   }

   private VectorIndexerModel$() {
   }
}

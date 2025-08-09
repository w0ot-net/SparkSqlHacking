package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class LocalLDAModel$ implements MLReadable, Serializable {
   public static final LocalLDAModel$ MODULE$ = new LocalLDAModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new LocalLDAModel.LocalLDAModelReader();
   }

   public LocalLDAModel load(final String path) {
      return (LocalLDAModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LocalLDAModel$.class);
   }

   private LocalLDAModel$() {
   }
}

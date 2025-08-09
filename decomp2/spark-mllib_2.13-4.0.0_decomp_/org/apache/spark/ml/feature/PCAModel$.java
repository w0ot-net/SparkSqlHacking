package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class PCAModel$ implements MLReadable, Serializable {
   public static final PCAModel$ MODULE$ = new PCAModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new PCAModel.PCAModelReader();
   }

   public PCAModel load(final String path) {
      return (PCAModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PCAModel$.class);
   }

   private PCAModel$() {
   }
}

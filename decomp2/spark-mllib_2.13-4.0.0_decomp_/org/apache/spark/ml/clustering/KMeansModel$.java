package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class KMeansModel$ implements MLReadable, Serializable {
   public static final KMeansModel$ MODULE$ = new KMeansModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new KMeansModel.KMeansModelReader();
   }

   public KMeansModel load(final String path) {
      return (KMeansModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KMeansModel$.class);
   }

   private KMeansModel$() {
   }
}

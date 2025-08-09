package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class BisectingKMeansModel$ implements MLReadable, Serializable {
   public static final BisectingKMeansModel$ MODULE$ = new BisectingKMeansModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new BisectingKMeansModel.BisectingKMeansModelReader();
   }

   public BisectingKMeansModel load(final String path) {
      return (BisectingKMeansModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BisectingKMeansModel$.class);
   }

   private BisectingKMeansModel$() {
   }
}

package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingKMeans$ implements Serializable {
   public static final StreamingKMeans$ MODULE$ = new StreamingKMeans$();

   public final String BATCHES() {
      return "batches";
   }

   public final String POINTS() {
      return "points";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingKMeans$.class);
   }

   private StreamingKMeans$() {
   }
}

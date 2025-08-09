package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.rdd.RDD;
import scala.runtime.ModuleSerializationProxy;

public final class KMeans$ implements Serializable {
   public static final KMeans$ MODULE$ = new KMeans$();
   private static final String RANDOM = "random";
   private static final String K_MEANS_PARALLEL = "k-means||";

   public String RANDOM() {
      return RANDOM;
   }

   public String K_MEANS_PARALLEL() {
      return K_MEANS_PARALLEL;
   }

   public KMeansModel train(final RDD data, final int k, final int maxIterations, final String initializationMode, final long seed) {
      return (new KMeans()).setK(k).setMaxIterations(maxIterations).setInitializationMode(initializationMode).setSeed(seed).run(data);
   }

   public KMeansModel train(final RDD data, final int k, final int maxIterations, final String initializationMode) {
      return (new KMeans()).setK(k).setMaxIterations(maxIterations).setInitializationMode(initializationMode).run(data);
   }

   public KMeansModel train(final RDD data, final int k, final int maxIterations) {
      return (new KMeans()).setK(k).setMaxIterations(maxIterations).run(data);
   }

   public boolean validateInitMode(final String initMode) {
      String var10000 = this.RANDOM();
      if (var10000 == null) {
         if (initMode == null) {
            return true;
         }
      } else if (var10000.equals(initMode)) {
         return true;
      }

      var10000 = this.K_MEANS_PARALLEL();
      if (var10000 == null) {
         if (initMode == null) {
            return true;
         }
      } else if (var10000.equals(initMode)) {
         return true;
      }

      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KMeans$.class);
   }

   private KMeans$() {
   }
}

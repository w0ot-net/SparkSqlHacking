package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class DistanceMeasure$ implements Serializable {
   public static final DistanceMeasure$ MODULE$ = new DistanceMeasure$();
   private static final String EUCLIDEAN = "euclidean";
   private static final String COSINE = "cosine";

   public String EUCLIDEAN() {
      return EUCLIDEAN;
   }

   public String COSINE() {
      return COSINE;
   }

   public DistanceMeasure decodeFromString(final String distanceMeasure) {
      String var10000 = this.EUCLIDEAN();
      if (var10000 == null) {
         if (distanceMeasure == null) {
            return new EuclideanDistanceMeasure();
         }
      } else if (var10000.equals(distanceMeasure)) {
         return new EuclideanDistanceMeasure();
      }

      var10000 = this.COSINE();
      if (var10000 == null) {
         if (distanceMeasure == null) {
            return new CosineDistanceMeasure();
         }
      } else if (var10000.equals(distanceMeasure)) {
         return new CosineDistanceMeasure();
      }

      String var10002 = this.EUCLIDEAN();
      throw new IllegalArgumentException("distanceMeasure must be one of: " + var10002 + ", " + this.COSINE() + ". " + distanceMeasure + " provided.");
   }

   public boolean validateDistanceMeasure(final String distanceMeasure) {
      String var10000 = this.EUCLIDEAN();
      if (var10000 == null) {
         if (distanceMeasure == null) {
            return true;
         }
      } else if (var10000.equals(distanceMeasure)) {
         return true;
      }

      var10000 = this.COSINE();
      if (var10000 == null) {
         if (distanceMeasure == null) {
            return true;
         }
      } else if (var10000.equals(distanceMeasure)) {
         return true;
      }

      return false;
   }

   public boolean shouldComputeStatistics(final int k) {
      return k < 1000;
   }

   public boolean shouldComputeStatisticsLocally(final int k, final int numFeatures) {
      return (long)k * (long)k * (long)numFeatures < 1000000L;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DistanceMeasure$.class);
   }

   private DistanceMeasure$() {
   }
}

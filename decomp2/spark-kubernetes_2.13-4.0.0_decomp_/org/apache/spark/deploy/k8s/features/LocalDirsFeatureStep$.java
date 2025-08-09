package org.apache.spark.deploy.k8s.features;

import java.util.UUID;

public final class LocalDirsFeatureStep$ {
   public static final LocalDirsFeatureStep$ MODULE$ = new LocalDirsFeatureStep$();

   public String $lessinit$greater$default$2() {
      return "/var/data/spark-" + UUID.randomUUID();
   }

   private LocalDirsFeatureStep$() {
   }
}

package org.apache.spark.deploy.k8s;

import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;

public final class KubernetesDriverConf$ {
   public static final KubernetesDriverConf$ MODULE$ = new KubernetesDriverConf$();

   public Clock $lessinit$greater$default$7() {
      return new SystemClock();
   }

   private KubernetesDriverConf$() {
   }
}

package org.apache.spark.scheduler.cluster.k8s;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext.;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;

public final class ExecutorPodsSnapshotsStoreImpl$ {
   public static final ExecutorPodsSnapshotsStoreImpl$ MODULE$ = new ExecutorPodsSnapshotsStoreImpl$();

   public Clock $lessinit$greater$default$2() {
      return new SystemClock();
   }

   public SparkConf $lessinit$greater$default$3() {
      return ((SparkContext).MODULE$.getActive().get()).conf();
   }

   private ExecutorPodsSnapshotsStoreImpl$() {
   }
}

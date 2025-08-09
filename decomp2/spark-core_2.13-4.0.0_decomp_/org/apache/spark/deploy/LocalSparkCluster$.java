package org.apache.spark.deploy;

import org.apache.spark.SparkConf;
import scala.Option;
import scala.Some;
import scala.None.;

public final class LocalSparkCluster$ {
   public static final LocalSparkCluster$ MODULE$ = new LocalSparkCluster$();
   private static Option localCluster;

   static {
      localCluster = .MODULE$;
   }

   private Option localCluster() {
      return localCluster;
   }

   private void localCluster_$eq(final Option x$1) {
      localCluster = x$1;
   }

   public Option get() {
      return this.localCluster();
   }

   public void org$apache$spark$deploy$LocalSparkCluster$$clear() {
      this.localCluster_$eq(.MODULE$);
   }

   public LocalSparkCluster apply(final int numWorkers, final int coresPerWorker, final int memoryPerWorker, final SparkConf conf) {
      this.localCluster_$eq(new Some(new LocalSparkCluster(numWorkers, coresPerWorker, memoryPerWorker, conf)));
      return (LocalSparkCluster)this.localCluster().get();
   }

   private LocalSparkCluster$() {
   }
}

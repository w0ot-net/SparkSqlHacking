package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class BarrierJobAllocationFailed$ implements Serializable {
   public static final BarrierJobAllocationFailed$ MODULE$ = new BarrierJobAllocationFailed$();
   private static final String ERROR_MESSAGE_RUN_BARRIER_WITH_UNSUPPORTED_RDD_CHAIN_PATTERN = "[SPARK-24820][SPARK-24821]: Barrier execution mode does not allow the following pattern of RDD chain within a barrier stage:\n1. Ancestor RDDs that have different number of partitions from the resulting RDD (e.g. union()/coalesce()/first()/take()/PartitionPruningRDD). A workaround for first()/take() can be barrierRdd.collect().head (scala) or barrierRdd.collect()[0] (python).\n2. An RDD that depends on multiple barrier RDDs (e.g. barrierRdd1.zip(barrierRdd2)).";
   private static final String ERROR_MESSAGE_RUN_BARRIER_WITH_DYN_ALLOCATION;
   private static final String ERROR_MESSAGE_BARRIER_REQUIRE_MORE_SLOTS_THAN_CURRENT_TOTAL_NUMBER;

   static {
      ERROR_MESSAGE_RUN_BARRIER_WITH_DYN_ALLOCATION = "[SPARK-24942]: Barrier execution mode does not support dynamic resource allocation for now. You can disable dynamic resource allocation by setting Spark conf \"" + org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_ENABLED().key() + "\" to \"false\".";
      ERROR_MESSAGE_BARRIER_REQUIRE_MORE_SLOTS_THAN_CURRENT_TOTAL_NUMBER = "[SPARK-24819]: Barrier execution mode does not allow run a barrier stage that requires more slots than the total number of slots in the cluster currently. Please init a new cluster with more resources(e.g. CPU, GPU) or repartition the input RDD(s) to reduce the number of slots required to run this barrier stage.";
   }

   public String ERROR_MESSAGE_RUN_BARRIER_WITH_UNSUPPORTED_RDD_CHAIN_PATTERN() {
      return ERROR_MESSAGE_RUN_BARRIER_WITH_UNSUPPORTED_RDD_CHAIN_PATTERN;
   }

   public String ERROR_MESSAGE_RUN_BARRIER_WITH_DYN_ALLOCATION() {
      return ERROR_MESSAGE_RUN_BARRIER_WITH_DYN_ALLOCATION;
   }

   public String ERROR_MESSAGE_BARRIER_REQUIRE_MORE_SLOTS_THAN_CURRENT_TOTAL_NUMBER() {
      return ERROR_MESSAGE_BARRIER_REQUIRE_MORE_SLOTS_THAN_CURRENT_TOTAL_NUMBER;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BarrierJobAllocationFailed$.class);
   }

   private BarrierJobAllocationFailed$() {
   }
}

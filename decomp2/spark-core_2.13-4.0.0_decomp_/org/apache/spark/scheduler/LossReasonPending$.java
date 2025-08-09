package org.apache.spark.scheduler;

import scala.runtime.ModuleSerializationProxy;

public final class LossReasonPending$ extends ExecutorLossReason {
   public static final LossReasonPending$ MODULE$ = new LossReasonPending$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(LossReasonPending$.class);
   }

   private LossReasonPending$() {
      super("Pending loss reason.");
   }
}

package org.apache.spark.scheduler;

import scala.runtime.ModuleSerializationProxy;

public final class ExecutorKilled$ extends ExecutorLossReason {
   public static final ExecutorKilled$ MODULE$ = new ExecutorKilled$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorKilled$.class);
   }

   private ExecutorKilled$() {
      super("Executor killed by driver.");
   }
}

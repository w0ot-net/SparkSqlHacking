package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorDecommissionState$ extends AbstractFunction2 implements Serializable {
   public static final ExecutorDecommissionState$ MODULE$ = new ExecutorDecommissionState$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public final String toString() {
      return "ExecutorDecommissionState";
   }

   public ExecutorDecommissionState apply(final long startTime, final Option workerHost) {
      return new ExecutorDecommissionState(startTime, workerHost);
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public Option unapply(final ExecutorDecommissionState x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.startTime()), x$0.workerHost())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorDecommissionState$.class);
   }

   private ExecutorDecommissionState$() {
   }
}

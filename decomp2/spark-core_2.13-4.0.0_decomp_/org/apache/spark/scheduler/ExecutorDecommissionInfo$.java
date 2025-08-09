package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorDecommissionInfo$ extends AbstractFunction2 implements Serializable {
   public static final ExecutorDecommissionInfo$ MODULE$ = new ExecutorDecommissionInfo$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public final String toString() {
      return "ExecutorDecommissionInfo";
   }

   public ExecutorDecommissionInfo apply(final String message, final Option workerHost) {
      return new ExecutorDecommissionInfo(message, workerHost);
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public Option unapply(final ExecutorDecommissionInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.message(), x$0.workerHost())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorDecommissionInfo$.class);
   }

   private ExecutorDecommissionInfo$() {
   }
}

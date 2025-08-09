package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorLost$ extends AbstractFunction2 implements Serializable {
   public static final ExecutorLost$ MODULE$ = new ExecutorLost$();

   public final String toString() {
      return "ExecutorLost";
   }

   public ExecutorLost apply(final String execId, final ExecutorLossReason reason) {
      return new ExecutorLost(execId, reason);
   }

   public Option unapply(final ExecutorLost x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.execId(), x$0.reason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorLost$.class);
   }

   private ExecutorLost$() {
   }
}

package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorAdded$ extends AbstractFunction2 implements Serializable {
   public static final ExecutorAdded$ MODULE$ = new ExecutorAdded$();

   public final String toString() {
      return "ExecutorAdded";
   }

   public ExecutorAdded apply(final String execId, final String host) {
      return new ExecutorAdded(execId, host);
   }

   public Option unapply(final ExecutorAdded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.execId(), x$0.host())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorAdded$.class);
   }

   private ExecutorAdded$() {
   }
}

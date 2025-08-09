package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class WorkerRemoved$ extends AbstractFunction3 implements Serializable {
   public static final WorkerRemoved$ MODULE$ = new WorkerRemoved$();

   public final String toString() {
      return "WorkerRemoved";
   }

   public WorkerRemoved apply(final String workerId, final String host, final String message) {
      return new WorkerRemoved(workerId, host, message);
   }

   public Option unapply(final WorkerRemoved x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.workerId(), x$0.host(), x$0.message())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WorkerRemoved$.class);
   }

   private WorkerRemoved$() {
   }
}

package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorRemoved$ extends AbstractFunction1 implements Serializable {
   public static final ExecutorRemoved$ MODULE$ = new ExecutorRemoved$();

   public final String toString() {
      return "ExecutorRemoved";
   }

   public ExecutorRemoved apply(final String executorId) {
      return new ExecutorRemoved(executorId);
   }

   public Option unapply(final ExecutorRemoved x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.executorId()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorRemoved$.class);
   }

   private ExecutorRemoved$() {
   }
}

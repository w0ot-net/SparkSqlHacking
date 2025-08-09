package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorRegistered$ extends AbstractFunction1 implements Serializable {
   public static final ExecutorRegistered$ MODULE$ = new ExecutorRegistered$();

   public final String toString() {
      return "ExecutorRegistered";
   }

   public ExecutorRegistered apply(final String executorId) {
      return new ExecutorRegistered(executorId);
   }

   public Option unapply(final ExecutorRegistered x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.executorId()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorRegistered$.class);
   }

   private ExecutorRegistered$() {
   }
}

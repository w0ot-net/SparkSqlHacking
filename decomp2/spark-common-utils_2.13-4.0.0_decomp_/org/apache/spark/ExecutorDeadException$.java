package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorDeadException$ extends AbstractFunction1 implements Serializable {
   public static final ExecutorDeadException$ MODULE$ = new ExecutorDeadException$();

   public final String toString() {
      return "ExecutorDeadException";
   }

   public ExecutorDeadException apply(final String message) {
      return new ExecutorDeadException(message);
   }

   public Option unapply(final ExecutorDeadException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.message()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorDeadException$.class);
   }

   private ExecutorDeadException$() {
   }
}

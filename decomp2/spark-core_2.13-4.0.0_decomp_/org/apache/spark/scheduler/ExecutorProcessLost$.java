package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorProcessLost$ extends AbstractFunction3 implements Serializable {
   public static final ExecutorProcessLost$ MODULE$ = new ExecutorProcessLost$();

   public String $lessinit$greater$default$1() {
      return "Executor Process Lost";
   }

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public boolean $lessinit$greater$default$3() {
      return true;
   }

   public final String toString() {
      return "ExecutorProcessLost";
   }

   public ExecutorProcessLost apply(final String _message, final Option workerHost, final boolean causedByApp) {
      return new ExecutorProcessLost(_message, workerHost, causedByApp);
   }

   public String apply$default$1() {
      return "Executor Process Lost";
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public boolean apply$default$3() {
      return true;
   }

   public Option unapply(final ExecutorProcessLost x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0._message(), x$0.workerHost(), BoxesRunTime.boxToBoolean(x$0.causedByApp()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorProcessLost$.class);
   }

   private ExecutorProcessLost$() {
   }
}

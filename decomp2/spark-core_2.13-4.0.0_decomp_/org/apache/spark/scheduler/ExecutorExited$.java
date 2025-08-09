package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.executor.ExecutorExitCode$;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorExited$ implements Serializable {
   public static final ExecutorExited$ MODULE$ = new ExecutorExited$();

   public ExecutorExited apply(final int exitCode, final boolean exitCausedByApp) {
      return new ExecutorExited(exitCode, exitCausedByApp, ExecutorExitCode$.MODULE$.explainExitCode(exitCode));
   }

   public ExecutorExited apply(final int exitCode, final boolean exitCausedByApp, final String reason) {
      return new ExecutorExited(exitCode, exitCausedByApp, reason);
   }

   public Option unapply(final ExecutorExited x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.exitCode()), BoxesRunTime.boxToBoolean(x$0.exitCausedByApp()), x$0.reason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorExited$.class);
   }

   private ExecutorExited$() {
   }
}

package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorLostFailure$ extends AbstractFunction3 implements Serializable {
   public static final ExecutorLostFailure$ MODULE$ = new ExecutorLostFailure$();

   public boolean $lessinit$greater$default$2() {
      return true;
   }

   public final String toString() {
      return "ExecutorLostFailure";
   }

   public ExecutorLostFailure apply(final String execId, final boolean exitCausedByApp, final Option reason) {
      return new ExecutorLostFailure(execId, exitCausedByApp, reason);
   }

   public boolean apply$default$2() {
      return true;
   }

   public Option unapply(final ExecutorLostFailure x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.execId(), BoxesRunTime.boxToBoolean(x$0.exitCausedByApp()), x$0.reason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorLostFailure$.class);
   }

   private ExecutorLostFailure$() {
   }
}

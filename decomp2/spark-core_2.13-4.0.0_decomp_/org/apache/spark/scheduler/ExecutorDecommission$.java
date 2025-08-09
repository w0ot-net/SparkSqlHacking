package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorDecommission$ implements Serializable {
   public static final ExecutorDecommission$ MODULE$ = new ExecutorDecommission$();
   private static final String msgPrefix = "Executor decommission: ";

   public Option $lessinit$greater$default$1() {
      return .MODULE$;
   }

   public String $lessinit$greater$default$2() {
      return "";
   }

   public String msgPrefix() {
      return msgPrefix;
   }

   public ExecutorDecommission apply(final Option workerHost, final String reason) {
      return new ExecutorDecommission(workerHost, reason);
   }

   public Option apply$default$1() {
      return .MODULE$;
   }

   public String apply$default$2() {
      return "";
   }

   public Option unapply(final ExecutorDecommission x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.workerHost(), x$0.reason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorDecommission$.class);
   }

   private ExecutorDecommission$() {
   }
}

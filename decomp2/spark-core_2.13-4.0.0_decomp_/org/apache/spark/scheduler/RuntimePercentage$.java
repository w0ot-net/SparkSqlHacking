package org.apache.spark.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.executor.TaskMetrics;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class RuntimePercentage$ implements Serializable {
   public static final RuntimePercentage$ MODULE$ = new RuntimePercentage$();

   public RuntimePercentage apply(final long totalTime, final TaskMetrics metrics) {
      double denom = (double)totalTime;
      Some fetchTime = new Some(BoxesRunTime.boxToLong(metrics.shuffleReadMetrics().fetchWaitTime()));
      Option fetch = fetchTime.map((JFunction1.mcDJ.sp)(x$13) -> (double)x$13 / denom);
      double exec = (double)(metrics.executorRunTime() - BoxesRunTime.unboxToLong(fetchTime.getOrElse((JFunction0.mcJ.sp)() -> 0L))) / denom;
      double other = (double)1.0F - (exec + BoxesRunTime.unboxToDouble(fetch.getOrElse((JFunction0.mcD.sp)() -> (double)0.0F)));
      return new RuntimePercentage(exec, fetch, other);
   }

   public RuntimePercentage apply(final double executorPct, final Option fetchPct, final double other) {
      return new RuntimePercentage(executorPct, fetchPct, other);
   }

   public Option unapply(final RuntimePercentage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.executorPct()), x$0.fetchPct(), BoxesRunTime.boxToDouble(x$0.other()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RuntimePercentage$.class);
   }

   private RuntimePercentage$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

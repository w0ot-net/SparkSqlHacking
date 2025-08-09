package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerExecutorBlacklisted$ extends AbstractFunction3 implements Serializable {
   public static final SparkListenerExecutorBlacklisted$ MODULE$ = new SparkListenerExecutorBlacklisted$();

   public final String toString() {
      return "SparkListenerExecutorBlacklisted";
   }

   public SparkListenerExecutorBlacklisted apply(final long time, final String executorId, final int taskFailures) {
      return new SparkListenerExecutorBlacklisted(time, executorId, taskFailures);
   }

   public Option unapply(final SparkListenerExecutorBlacklisted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.time()), x$0.executorId(), BoxesRunTime.boxToInteger(x$0.taskFailures()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerExecutorBlacklisted$.class);
   }

   private SparkListenerExecutorBlacklisted$() {
   }
}

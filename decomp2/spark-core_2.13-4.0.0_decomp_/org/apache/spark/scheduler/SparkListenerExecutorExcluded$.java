package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerExecutorExcluded$ extends AbstractFunction3 implements Serializable {
   public static final SparkListenerExecutorExcluded$ MODULE$ = new SparkListenerExecutorExcluded$();

   public final String toString() {
      return "SparkListenerExecutorExcluded";
   }

   public SparkListenerExecutorExcluded apply(final long time, final String executorId, final int taskFailures) {
      return new SparkListenerExecutorExcluded(time, executorId, taskFailures);
   }

   public Option unapply(final SparkListenerExecutorExcluded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.time()), x$0.executorId(), BoxesRunTime.boxToInteger(x$0.taskFailures()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerExecutorExcluded$.class);
   }

   private SparkListenerExecutorExcluded$() {
   }
}

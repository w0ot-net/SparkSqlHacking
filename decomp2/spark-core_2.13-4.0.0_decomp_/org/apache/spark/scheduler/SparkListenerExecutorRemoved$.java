package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerExecutorRemoved$ extends AbstractFunction3 implements Serializable {
   public static final SparkListenerExecutorRemoved$ MODULE$ = new SparkListenerExecutorRemoved$();

   public final String toString() {
      return "SparkListenerExecutorRemoved";
   }

   public SparkListenerExecutorRemoved apply(final long time, final String executorId, final String reason) {
      return new SparkListenerExecutorRemoved(time, executorId, reason);
   }

   public Option unapply(final SparkListenerExecutorRemoved x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.time()), x$0.executorId(), x$0.reason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerExecutorRemoved$.class);
   }

   private SparkListenerExecutorRemoved$() {
   }
}

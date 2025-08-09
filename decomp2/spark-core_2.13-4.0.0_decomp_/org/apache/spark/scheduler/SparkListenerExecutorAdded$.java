package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.scheduler.cluster.ExecutorInfo;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerExecutorAdded$ extends AbstractFunction3 implements Serializable {
   public static final SparkListenerExecutorAdded$ MODULE$ = new SparkListenerExecutorAdded$();

   public final String toString() {
      return "SparkListenerExecutorAdded";
   }

   public SparkListenerExecutorAdded apply(final long time, final String executorId, final ExecutorInfo executorInfo) {
      return new SparkListenerExecutorAdded(time, executorId, executorInfo);
   }

   public Option unapply(final SparkListenerExecutorAdded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.time()), x$0.executorId(), x$0.executorInfo())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerExecutorAdded$.class);
   }

   private SparkListenerExecutorAdded$() {
   }
}

package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerExecutorUnexcluded$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerExecutorUnexcluded$ MODULE$ = new SparkListenerExecutorUnexcluded$();

   public final String toString() {
      return "SparkListenerExecutorUnexcluded";
   }

   public SparkListenerExecutorUnexcluded apply(final long time, final String executorId) {
      return new SparkListenerExecutorUnexcluded(time, executorId);
   }

   public Option unapply(final SparkListenerExecutorUnexcluded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.time()), x$0.executorId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerExecutorUnexcluded$.class);
   }

   private SparkListenerExecutorUnexcluded$() {
   }
}

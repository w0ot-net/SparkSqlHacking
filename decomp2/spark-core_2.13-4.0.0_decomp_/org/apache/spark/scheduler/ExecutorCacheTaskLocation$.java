package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorCacheTaskLocation$ extends AbstractFunction2 implements Serializable {
   public static final ExecutorCacheTaskLocation$ MODULE$ = new ExecutorCacheTaskLocation$();

   public final String toString() {
      return "ExecutorCacheTaskLocation";
   }

   public ExecutorCacheTaskLocation apply(final String host, final String executorId) {
      return new ExecutorCacheTaskLocation(host, executorId);
   }

   public Option unapply(final ExecutorCacheTaskLocation x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.host(), x$0.executorId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorCacheTaskLocation$.class);
   }

   private ExecutorCacheTaskLocation$() {
   }
}

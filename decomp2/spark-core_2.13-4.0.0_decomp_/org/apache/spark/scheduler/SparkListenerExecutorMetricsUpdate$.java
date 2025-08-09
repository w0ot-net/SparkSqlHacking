package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.collection.Map;
import scala.collection.Map.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerExecutorMetricsUpdate$ extends AbstractFunction3 implements Serializable {
   public static final SparkListenerExecutorMetricsUpdate$ MODULE$ = new SparkListenerExecutorMetricsUpdate$();

   public Map $lessinit$greater$default$3() {
      return (Map).MODULE$.empty();
   }

   public final String toString() {
      return "SparkListenerExecutorMetricsUpdate";
   }

   public SparkListenerExecutorMetricsUpdate apply(final String execId, final Seq accumUpdates, final Map executorUpdates) {
      return new SparkListenerExecutorMetricsUpdate(execId, accumUpdates, executorUpdates);
   }

   public Map apply$default$3() {
      return (Map).MODULE$.empty();
   }

   public Option unapply(final SparkListenerExecutorMetricsUpdate x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.execId(), x$0.accumUpdates(), x$0.executorUpdates())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerExecutorMetricsUpdate$.class);
   }

   private SparkListenerExecutorMetricsUpdate$() {
   }
}

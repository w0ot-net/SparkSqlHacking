package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.executor.ExecutorMetrics;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerStageExecutorMetrics$ extends AbstractFunction4 implements Serializable {
   public static final SparkListenerStageExecutorMetrics$ MODULE$ = new SparkListenerStageExecutorMetrics$();

   public final String toString() {
      return "SparkListenerStageExecutorMetrics";
   }

   public SparkListenerStageExecutorMetrics apply(final String execId, final int stageId, final int stageAttemptId, final ExecutorMetrics executorMetrics) {
      return new SparkListenerStageExecutorMetrics(execId, stageId, stageAttemptId, executorMetrics);
   }

   public Option unapply(final SparkListenerStageExecutorMetrics x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.execId(), BoxesRunTime.boxToInteger(x$0.stageId()), BoxesRunTime.boxToInteger(x$0.stageAttemptId()), x$0.executorMetrics())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerStageExecutorMetrics$.class);
   }

   private SparkListenerStageExecutorMetrics$() {
   }
}

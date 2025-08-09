package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.TaskEndReason;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.executor.TaskMetrics;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.runtime.AbstractFunction7;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerTaskEnd$ extends AbstractFunction7 implements Serializable {
   public static final SparkListenerTaskEnd$ MODULE$ = new SparkListenerTaskEnd$();

   public final String toString() {
      return "SparkListenerTaskEnd";
   }

   public SparkListenerTaskEnd apply(final int stageId, final int stageAttemptId, final String taskType, final TaskEndReason reason, final TaskInfo taskInfo, final ExecutorMetrics taskExecutorMetrics, final TaskMetrics taskMetrics) {
      return new SparkListenerTaskEnd(stageId, stageAttemptId, taskType, reason, taskInfo, taskExecutorMetrics, taskMetrics);
   }

   public Option unapply(final SparkListenerTaskEnd x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(BoxesRunTime.boxToInteger(x$0.stageId()), BoxesRunTime.boxToInteger(x$0.stageAttemptId()), x$0.taskType(), x$0.reason(), x$0.taskInfo(), x$0.taskExecutorMetrics(), x$0.taskMetrics())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerTaskEnd$.class);
   }

   private SparkListenerTaskEnd$() {
   }
}

package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerExecutorExcludedForStage$ extends AbstractFunction5 implements Serializable {
   public static final SparkListenerExecutorExcludedForStage$ MODULE$ = new SparkListenerExecutorExcludedForStage$();

   public final String toString() {
      return "SparkListenerExecutorExcludedForStage";
   }

   public SparkListenerExecutorExcludedForStage apply(final long time, final String executorId, final int taskFailures, final int stageId, final int stageAttemptId) {
      return new SparkListenerExecutorExcludedForStage(time, executorId, taskFailures, stageId, stageAttemptId);
   }

   public Option unapply(final SparkListenerExecutorExcludedForStage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.time()), x$0.executorId(), BoxesRunTime.boxToInteger(x$0.taskFailures()), BoxesRunTime.boxToInteger(x$0.stageId()), BoxesRunTime.boxToInteger(x$0.stageAttemptId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerExecutorExcludedForStage$.class);
   }

   private SparkListenerExecutorExcludedForStage$() {
   }
}

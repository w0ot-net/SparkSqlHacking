package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerExecutorBlacklistedForStage$ extends AbstractFunction5 implements Serializable {
   public static final SparkListenerExecutorBlacklistedForStage$ MODULE$ = new SparkListenerExecutorBlacklistedForStage$();

   public final String toString() {
      return "SparkListenerExecutorBlacklistedForStage";
   }

   public SparkListenerExecutorBlacklistedForStage apply(final long time, final String executorId, final int taskFailures, final int stageId, final int stageAttemptId) {
      return new SparkListenerExecutorBlacklistedForStage(time, executorId, taskFailures, stageId, stageAttemptId);
   }

   public Option unapply(final SparkListenerExecutorBlacklistedForStage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.time()), x$0.executorId(), BoxesRunTime.boxToInteger(x$0.taskFailures()), BoxesRunTime.boxToInteger(x$0.stageId()), BoxesRunTime.boxToInteger(x$0.stageAttemptId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerExecutorBlacklistedForStage$.class);
   }

   private SparkListenerExecutorBlacklistedForStage$() {
   }
}

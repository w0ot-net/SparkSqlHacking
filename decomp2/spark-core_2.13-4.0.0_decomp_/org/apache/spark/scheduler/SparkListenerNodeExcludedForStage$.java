package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerNodeExcludedForStage$ extends AbstractFunction5 implements Serializable {
   public static final SparkListenerNodeExcludedForStage$ MODULE$ = new SparkListenerNodeExcludedForStage$();

   public final String toString() {
      return "SparkListenerNodeExcludedForStage";
   }

   public SparkListenerNodeExcludedForStage apply(final long time, final String hostId, final int executorFailures, final int stageId, final int stageAttemptId) {
      return new SparkListenerNodeExcludedForStage(time, hostId, executorFailures, stageId, stageAttemptId);
   }

   public Option unapply(final SparkListenerNodeExcludedForStage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.time()), x$0.hostId(), BoxesRunTime.boxToInteger(x$0.executorFailures()), BoxesRunTime.boxToInteger(x$0.stageId()), BoxesRunTime.boxToInteger(x$0.stageAttemptId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerNodeExcludedForStage$.class);
   }

   private SparkListenerNodeExcludedForStage$() {
   }
}

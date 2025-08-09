package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerTaskStart$ extends AbstractFunction3 implements Serializable {
   public static final SparkListenerTaskStart$ MODULE$ = new SparkListenerTaskStart$();

   public final String toString() {
      return "SparkListenerTaskStart";
   }

   public SparkListenerTaskStart apply(final int stageId, final int stageAttemptId, final TaskInfo taskInfo) {
      return new SparkListenerTaskStart(stageId, stageAttemptId, taskInfo);
   }

   public Option unapply(final SparkListenerTaskStart x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.stageId()), BoxesRunTime.boxToInteger(x$0.stageAttemptId()), x$0.taskInfo())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerTaskStart$.class);
   }

   private SparkListenerTaskStart$() {
   }
}

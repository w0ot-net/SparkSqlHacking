package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerStageCompleted$ extends AbstractFunction1 implements Serializable {
   public static final SparkListenerStageCompleted$ MODULE$ = new SparkListenerStageCompleted$();

   public final String toString() {
      return "SparkListenerStageCompleted";
   }

   public SparkListenerStageCompleted apply(final StageInfo stageInfo) {
      return new SparkListenerStageCompleted(stageInfo);
   }

   public Option unapply(final SparkListenerStageCompleted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.stageInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerStageCompleted$.class);
   }

   private SparkListenerStageCompleted$() {
   }
}

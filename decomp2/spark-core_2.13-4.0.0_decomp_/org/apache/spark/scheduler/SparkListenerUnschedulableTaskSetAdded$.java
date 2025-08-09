package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerUnschedulableTaskSetAdded$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerUnschedulableTaskSetAdded$ MODULE$ = new SparkListenerUnschedulableTaskSetAdded$();

   public final String toString() {
      return "SparkListenerUnschedulableTaskSetAdded";
   }

   public SparkListenerUnschedulableTaskSetAdded apply(final int stageId, final int stageAttemptId) {
      return new SparkListenerUnschedulableTaskSetAdded(stageId, stageAttemptId);
   }

   public Option unapply(final SparkListenerUnschedulableTaskSetAdded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcII.sp(x$0.stageId(), x$0.stageAttemptId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerUnschedulableTaskSetAdded$.class);
   }

   private SparkListenerUnschedulableTaskSetAdded$() {
   }
}

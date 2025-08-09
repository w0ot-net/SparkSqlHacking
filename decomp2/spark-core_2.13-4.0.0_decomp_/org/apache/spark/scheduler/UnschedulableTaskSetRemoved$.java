package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class UnschedulableTaskSetRemoved$ extends AbstractFunction2 implements Serializable {
   public static final UnschedulableTaskSetRemoved$ MODULE$ = new UnschedulableTaskSetRemoved$();

   public final String toString() {
      return "UnschedulableTaskSetRemoved";
   }

   public UnschedulableTaskSetRemoved apply(final int stageId, final int stageAttemptId) {
      return new UnschedulableTaskSetRemoved(stageId, stageAttemptId);
   }

   public Option unapply(final UnschedulableTaskSetRemoved x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcII.sp(x$0.stageId(), x$0.stageAttemptId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnschedulableTaskSetRemoved$.class);
   }

   private UnschedulableTaskSetRemoved$() {
   }
}

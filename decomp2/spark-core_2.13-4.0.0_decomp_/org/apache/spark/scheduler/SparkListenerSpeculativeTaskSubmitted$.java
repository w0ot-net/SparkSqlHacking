package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerSpeculativeTaskSubmitted$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerSpeculativeTaskSubmitted$ MODULE$ = new SparkListenerSpeculativeTaskSubmitted$();

   public int $lessinit$greater$default$2() {
      return 0;
   }

   public final String toString() {
      return "SparkListenerSpeculativeTaskSubmitted";
   }

   public SparkListenerSpeculativeTaskSubmitted apply(final int stageId, final int stageAttemptId) {
      return new SparkListenerSpeculativeTaskSubmitted(stageId, stageAttemptId);
   }

   public int apply$default$2() {
      return 0;
   }

   public Option unapply(final SparkListenerSpeculativeTaskSubmitted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcII.sp(x$0.stageId(), x$0.stageAttemptId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerSpeculativeTaskSubmitted$.class);
   }

   private SparkListenerSpeculativeTaskSubmitted$() {
   }
}

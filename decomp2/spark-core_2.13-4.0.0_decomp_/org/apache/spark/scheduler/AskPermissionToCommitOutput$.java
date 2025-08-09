package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class AskPermissionToCommitOutput$ extends AbstractFunction4 implements Serializable {
   public static final AskPermissionToCommitOutput$ MODULE$ = new AskPermissionToCommitOutput$();

   public final String toString() {
      return "AskPermissionToCommitOutput";
   }

   public AskPermissionToCommitOutput apply(final int stage, final int stageAttempt, final int partition, final int attemptNumber) {
      return new AskPermissionToCommitOutput(stage, stageAttempt, partition, attemptNumber);
   }

   public Option unapply(final AskPermissionToCommitOutput x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.stage()), BoxesRunTime.boxToInteger(x$0.stageAttempt()), BoxesRunTime.boxToInteger(x$0.partition()), BoxesRunTime.boxToInteger(x$0.attemptNumber()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AskPermissionToCommitOutput$.class);
   }

   private AskPermissionToCommitOutput$() {
   }
}

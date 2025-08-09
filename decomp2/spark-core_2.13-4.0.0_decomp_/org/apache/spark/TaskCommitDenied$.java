package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class TaskCommitDenied$ extends AbstractFunction3 implements Serializable {
   public static final TaskCommitDenied$ MODULE$ = new TaskCommitDenied$();

   public final String toString() {
      return "TaskCommitDenied";
   }

   public TaskCommitDenied apply(final int jobID, final int partitionID, final int attemptNumber) {
      return new TaskCommitDenied(jobID, partitionID, attemptNumber);
   }

   public Option unapply(final TaskCommitDenied x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.jobID()), BoxesRunTime.boxToInteger(x$0.partitionID()), BoxesRunTime.boxToInteger(x$0.attemptNumber()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskCommitDenied$.class);
   }

   private TaskCommitDenied$() {
   }
}

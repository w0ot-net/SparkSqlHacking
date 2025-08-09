package org.apache.spark;

import java.io.Serializable;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Tuple8;
import scala.None.;
import scala.runtime.AbstractFunction8;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RequestToSync$ extends AbstractFunction8 implements Serializable {
   public static final RequestToSync$ MODULE$ = new RequestToSync$();

   public final String toString() {
      return "RequestToSync";
   }

   public RequestToSync apply(final int numTasks, final int stageId, final int stageAttemptId, final long taskAttemptId, final int barrierEpoch, final int partitionId, final String message, final Enumeration.Value requestMethod) {
      return new RequestToSync(numTasks, stageId, stageAttemptId, taskAttemptId, barrierEpoch, partitionId, message, requestMethod);
   }

   public Option unapply(final RequestToSync x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple8(BoxesRunTime.boxToInteger(x$0.numTasks()), BoxesRunTime.boxToInteger(x$0.stageId()), BoxesRunTime.boxToInteger(x$0.stageAttemptId()), BoxesRunTime.boxToLong(x$0.taskAttemptId()), BoxesRunTime.boxToInteger(x$0.barrierEpoch()), BoxesRunTime.boxToInteger(x$0.partitionId()), x$0.message(), x$0.requestMethod())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RequestToSync$.class);
   }

   private RequestToSync$() {
   }
}

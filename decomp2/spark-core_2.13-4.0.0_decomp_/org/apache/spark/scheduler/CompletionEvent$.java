package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.TaskEndReason;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction6;
import scala.runtime.ModuleSerializationProxy;

public final class CompletionEvent$ extends AbstractFunction6 implements Serializable {
   public static final CompletionEvent$ MODULE$ = new CompletionEvent$();

   public final String toString() {
      return "CompletionEvent";
   }

   public CompletionEvent apply(final Task task, final TaskEndReason reason, final Object result, final Seq accumUpdates, final long[] metricPeaks, final TaskInfo taskInfo) {
      return new CompletionEvent(task, reason, result, accumUpdates, metricPeaks, taskInfo);
   }

   public Option unapply(final CompletionEvent x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.task(), x$0.reason(), x$0.result(), x$0.accumUpdates(), x$0.metricPeaks(), x$0.taskInfo())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CompletionEvent$.class);
   }

   private CompletionEvent$() {
   }
}

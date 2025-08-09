package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class TaskSetFailed$ extends AbstractFunction3 implements Serializable {
   public static final TaskSetFailed$ MODULE$ = new TaskSetFailed$();

   public final String toString() {
      return "TaskSetFailed";
   }

   public TaskSetFailed apply(final TaskSet taskSet, final String reason, final Option exception) {
      return new TaskSetFailed(taskSet, reason, exception);
   }

   public Option unapply(final TaskSetFailed x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.taskSet(), x$0.reason(), x$0.exception())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskSetFailed$.class);
   }

   private TaskSetFailed$() {
   }
}

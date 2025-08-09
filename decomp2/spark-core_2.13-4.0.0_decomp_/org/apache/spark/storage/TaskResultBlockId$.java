package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class TaskResultBlockId$ extends AbstractFunction1 implements Serializable {
   public static final TaskResultBlockId$ MODULE$ = new TaskResultBlockId$();

   public final String toString() {
      return "TaskResultBlockId";
   }

   public TaskResultBlockId apply(final long taskId) {
      return new TaskResultBlockId(taskId);
   }

   public Option unapply(final TaskResultBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.taskId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskResultBlockId$.class);
   }

   private TaskResultBlockId$() {
   }
}

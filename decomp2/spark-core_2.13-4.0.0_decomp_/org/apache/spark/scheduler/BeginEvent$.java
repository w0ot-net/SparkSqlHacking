package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class BeginEvent$ extends AbstractFunction2 implements Serializable {
   public static final BeginEvent$ MODULE$ = new BeginEvent$();

   public final String toString() {
      return "BeginEvent";
   }

   public BeginEvent apply(final Task task, final TaskInfo taskInfo) {
      return new BeginEvent(task, taskInfo);
   }

   public Option unapply(final BeginEvent x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.task(), x$0.taskInfo())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BeginEvent$.class);
   }

   private BeginEvent$() {
   }
}

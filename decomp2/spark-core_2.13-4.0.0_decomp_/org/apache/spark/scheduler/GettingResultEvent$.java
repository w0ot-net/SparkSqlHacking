package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class GettingResultEvent$ extends AbstractFunction1 implements Serializable {
   public static final GettingResultEvent$ MODULE$ = new GettingResultEvent$();

   public final String toString() {
      return "GettingResultEvent";
   }

   public GettingResultEvent apply(final TaskInfo taskInfo) {
      return new GettingResultEvent(taskInfo);
   }

   public Option unapply(final GettingResultEvent x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.taskInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GettingResultEvent$.class);
   }

   private GettingResultEvent$() {
   }
}

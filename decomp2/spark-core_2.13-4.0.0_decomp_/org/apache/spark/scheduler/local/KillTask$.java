package org.apache.spark.scheduler.local;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class KillTask$ extends AbstractFunction3 implements Serializable {
   public static final KillTask$ MODULE$ = new KillTask$();

   public final String toString() {
      return "KillTask";
   }

   public KillTask apply(final long taskId, final boolean interruptThread, final String reason) {
      return new KillTask(taskId, interruptThread, reason);
   }

   public Option unapply(final KillTask x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.taskId()), BoxesRunTime.boxToBoolean(x$0.interruptThread()), x$0.reason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KillTask$.class);
   }

   private KillTask$() {
   }
}

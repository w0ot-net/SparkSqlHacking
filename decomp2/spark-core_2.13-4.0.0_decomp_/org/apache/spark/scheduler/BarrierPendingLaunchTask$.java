package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.collection.immutable.Map;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BarrierPendingLaunchTask$ extends AbstractFunction5 implements Serializable {
   public static final BarrierPendingLaunchTask$ MODULE$ = new BarrierPendingLaunchTask$();

   public final String toString() {
      return "BarrierPendingLaunchTask";
   }

   public BarrierPendingLaunchTask apply(final String execId, final String host, final int index, final Enumeration.Value taskLocality, final Map assignedResources) {
      return new BarrierPendingLaunchTask(execId, host, index, taskLocality, assignedResources);
   }

   public Option unapply(final BarrierPendingLaunchTask x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.execId(), x$0.host(), BoxesRunTime.boxToInteger(x$0.index()), x$0.taskLocality(), x$0.assignedResources())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BarrierPendingLaunchTask$.class);
   }

   private BarrierPendingLaunchTask$() {
   }
}

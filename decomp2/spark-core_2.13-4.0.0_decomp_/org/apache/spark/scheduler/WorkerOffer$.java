package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.resource.ResourceProfile$;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class WorkerOffer$ extends AbstractFunction6 implements Serializable {
   public static final WorkerOffer$ MODULE$ = new WorkerOffer$();

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public ExecutorResourcesAmounts $lessinit$greater$default$5() {
      return ExecutorResourcesAmounts$.MODULE$.empty();
   }

   public int $lessinit$greater$default$6() {
      return ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
   }

   public final String toString() {
      return "WorkerOffer";
   }

   public WorkerOffer apply(final String executorId, final String host, final int cores, final Option address, final ExecutorResourcesAmounts resources, final int resourceProfileId) {
      return new WorkerOffer(executorId, host, cores, address, resources, resourceProfileId);
   }

   public Option apply$default$4() {
      return .MODULE$;
   }

   public ExecutorResourcesAmounts apply$default$5() {
      return ExecutorResourcesAmounts$.MODULE$.empty();
   }

   public int apply$default$6() {
      return ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
   }

   public Option unapply(final WorkerOffer x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.executorId(), x$0.host(), BoxesRunTime.boxToInteger(x$0.cores()), x$0.address(), x$0.resources(), BoxesRunTime.boxToInteger(x$0.resourceProfileId()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WorkerOffer$.class);
   }

   private WorkerOffer$() {
   }
}

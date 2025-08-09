package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class PodUnknown$ extends AbstractFunction1 implements Serializable {
   public static final PodUnknown$ MODULE$ = new PodUnknown$();

   public final String toString() {
      return "PodUnknown";
   }

   public PodUnknown apply(final Pod pod) {
      return new PodUnknown(pod);
   }

   public Option unapply(final PodUnknown x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.pod()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PodUnknown$.class);
   }

   private PodUnknown$() {
   }
}

package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class PodSucceeded$ extends AbstractFunction1 implements Serializable {
   public static final PodSucceeded$ MODULE$ = new PodSucceeded$();

   public final String toString() {
      return "PodSucceeded";
   }

   public PodSucceeded apply(final Pod pod) {
      return new PodSucceeded(pod);
   }

   public Option unapply(final PodSucceeded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.pod()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PodSucceeded$.class);
   }

   private PodSucceeded$() {
   }
}

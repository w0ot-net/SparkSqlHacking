package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class PodRunning$ extends AbstractFunction1 implements Serializable {
   public static final PodRunning$ MODULE$ = new PodRunning$();

   public final String toString() {
      return "PodRunning";
   }

   public PodRunning apply(final Pod pod) {
      return new PodRunning(pod);
   }

   public Option unapply(final PodRunning x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.pod()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PodRunning$.class);
   }

   private PodRunning$() {
   }
}

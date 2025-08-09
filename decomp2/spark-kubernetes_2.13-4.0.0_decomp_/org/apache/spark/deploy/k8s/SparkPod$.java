package org.apache.spark.deploy.k8s;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class SparkPod$ implements Serializable {
   public static final SparkPod$ MODULE$ = new SparkPod$();

   public SparkPod initialPod() {
      return new SparkPod(((PodBuilder)((PodFluent)(new PodBuilder()).withNewMetadata().endMetadata()).withNewSpec().endSpec()).build(), (new ContainerBuilder()).build());
   }

   public SparkPod apply(final Pod pod, final Container container) {
      return new SparkPod(pod, container);
   }

   public Option unapply(final SparkPod x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.pod(), x$0.container())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkPod$.class);
   }

   private SparkPod$() {
   }
}

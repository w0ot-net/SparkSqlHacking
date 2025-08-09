package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class KubernetesExecutorSpec$ extends AbstractFunction2 implements Serializable {
   public static final KubernetesExecutorSpec$ MODULE$ = new KubernetesExecutorSpec$();

   public final String toString() {
      return "KubernetesExecutorSpec";
   }

   public KubernetesExecutorSpec apply(final SparkPod pod, final Seq executorKubernetesResources) {
      return new KubernetesExecutorSpec(pod, executorKubernetesResources);
   }

   public Option unapply(final KubernetesExecutorSpec x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.pod(), x$0.executorKubernetesResources())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KubernetesExecutorSpec$.class);
   }

   private KubernetesExecutorSpec$() {
   }
}

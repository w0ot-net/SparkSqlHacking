package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class KubernetesDriverSpec$ extends AbstractFunction4 implements Serializable {
   public static final KubernetesDriverSpec$ MODULE$ = new KubernetesDriverSpec$();

   public final String toString() {
      return "KubernetesDriverSpec";
   }

   public KubernetesDriverSpec apply(final SparkPod pod, final Seq driverPreKubernetesResources, final Seq driverKubernetesResources, final Map systemProperties) {
      return new KubernetesDriverSpec(pod, driverPreKubernetesResources, driverKubernetesResources, systemProperties);
   }

   public Option unapply(final KubernetesDriverSpec x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.pod(), x$0.driverPreKubernetesResources(), x$0.driverKubernetesResources(), x$0.systemProperties())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KubernetesDriverSpec$.class);
   }

   private KubernetesDriverSpec$() {
   }
}

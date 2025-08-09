package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class KubernetesHostPathVolumeConf$ extends AbstractFunction2 implements Serializable {
   public static final KubernetesHostPathVolumeConf$ MODULE$ = new KubernetesHostPathVolumeConf$();

   public final String toString() {
      return "KubernetesHostPathVolumeConf";
   }

   public KubernetesHostPathVolumeConf apply(final String hostPath, final String volumeType) {
      return new KubernetesHostPathVolumeConf(hostPath, volumeType);
   }

   public Option unapply(final KubernetesHostPathVolumeConf x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.hostPath(), x$0.volumeType())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KubernetesHostPathVolumeConf$.class);
   }

   private KubernetesHostPathVolumeConf$() {
   }
}

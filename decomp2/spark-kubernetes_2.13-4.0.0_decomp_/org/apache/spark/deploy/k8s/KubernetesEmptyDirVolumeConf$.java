package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class KubernetesEmptyDirVolumeConf$ extends AbstractFunction2 implements Serializable {
   public static final KubernetesEmptyDirVolumeConf$ MODULE$ = new KubernetesEmptyDirVolumeConf$();

   public final String toString() {
      return "KubernetesEmptyDirVolumeConf";
   }

   public KubernetesEmptyDirVolumeConf apply(final Option medium, final Option sizeLimit) {
      return new KubernetesEmptyDirVolumeConf(medium, sizeLimit);
   }

   public Option unapply(final KubernetesEmptyDirVolumeConf x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.medium(), x$0.sizeLimit())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KubernetesEmptyDirVolumeConf$.class);
   }

   private KubernetesEmptyDirVolumeConf$() {
   }
}

package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class KubernetesVolumeSpec$ extends AbstractFunction6 implements Serializable {
   public static final KubernetesVolumeSpec$ MODULE$ = new KubernetesVolumeSpec$();

   public final String toString() {
      return "KubernetesVolumeSpec";
   }

   public KubernetesVolumeSpec apply(final String volumeName, final String mountPath, final String mountSubPath, final String mountSubPathExpr, final boolean mountReadOnly, final KubernetesVolumeSpecificConf volumeConf) {
      return new KubernetesVolumeSpec(volumeName, mountPath, mountSubPath, mountSubPathExpr, mountReadOnly, volumeConf);
   }

   public Option unapply(final KubernetesVolumeSpec x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.volumeName(), x$0.mountPath(), x$0.mountSubPath(), x$0.mountSubPathExpr(), BoxesRunTime.boxToBoolean(x$0.mountReadOnly()), x$0.volumeConf())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KubernetesVolumeSpec$.class);
   }

   private KubernetesVolumeSpec$() {
   }
}

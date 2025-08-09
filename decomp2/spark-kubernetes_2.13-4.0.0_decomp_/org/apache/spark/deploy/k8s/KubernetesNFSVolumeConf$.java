package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class KubernetesNFSVolumeConf$ extends AbstractFunction2 implements Serializable {
   public static final KubernetesNFSVolumeConf$ MODULE$ = new KubernetesNFSVolumeConf$();

   public final String toString() {
      return "KubernetesNFSVolumeConf";
   }

   public KubernetesNFSVolumeConf apply(final String path, final String server) {
      return new KubernetesNFSVolumeConf(path, server);
   }

   public Option unapply(final KubernetesNFSVolumeConf x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.path(), x$0.server())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KubernetesNFSVolumeConf$.class);
   }

   private KubernetesNFSVolumeConf$() {
   }
}

package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.ModuleSerializationProxy;

public final class KubernetesPVCVolumeConf$ extends AbstractFunction5 implements Serializable {
   public static final KubernetesPVCVolumeConf$ MODULE$ = new KubernetesPVCVolumeConf$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$5() {
      return .MODULE$;
   }

   public final String toString() {
      return "KubernetesPVCVolumeConf";
   }

   public KubernetesPVCVolumeConf apply(final String claimName, final Option storageClass, final Option size, final Option labels, final Option annotations) {
      return new KubernetesPVCVolumeConf(claimName, storageClass, size, labels, annotations);
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public Option apply$default$3() {
      return .MODULE$;
   }

   public Option apply$default$4() {
      return .MODULE$;
   }

   public Option apply$default$5() {
      return .MODULE$;
   }

   public Option unapply(final KubernetesPVCVolumeConf x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.claimName(), x$0.storageClass(), x$0.size(), x$0.labels(), x$0.annotations())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KubernetesPVCVolumeConf$.class);
   }

   private KubernetesPVCVolumeConf$() {
   }
}

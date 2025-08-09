package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import org.apache.spark.internal.config.ConfigEntry;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public class SparkKubernetesClientFactory$ClientType$Val$ extends AbstractFunction2 implements Serializable {
   public static final SparkKubernetesClientFactory$ClientType$Val$ MODULE$ = new SparkKubernetesClientFactory$ClientType$Val$();

   public final String toString() {
      return "Val";
   }

   public SparkKubernetesClientFactory$ClientType$Val apply(final ConfigEntry requestTimeoutEntry, final ConfigEntry connectionTimeoutEntry) {
      return new SparkKubernetesClientFactory$ClientType$Val(requestTimeoutEntry, connectionTimeoutEntry);
   }

   public Option unapply(final SparkKubernetesClientFactory$ClientType$Val x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.requestTimeoutEntry(), x$0.connectionTimeoutEntry())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkKubernetesClientFactory$ClientType$Val$.class);
   }
}

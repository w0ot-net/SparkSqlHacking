package org.apache.spark.scheduler.cluster.k8s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class GenerateExecID$ extends AbstractFunction1 implements Serializable {
   public static final GenerateExecID$ MODULE$ = new GenerateExecID$();

   public final String toString() {
      return "GenerateExecID";
   }

   public GenerateExecID apply(final String podName) {
      return new GenerateExecID(podName);
   }

   public Option unapply(final GenerateExecID x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.podName()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GenerateExecID$.class);
   }

   private GenerateExecID$() {
   }
}

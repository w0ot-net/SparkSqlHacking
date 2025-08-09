package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.resource.ResourceProfile;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerResourceProfileAdded$ extends AbstractFunction1 implements Serializable {
   public static final SparkListenerResourceProfileAdded$ MODULE$ = new SparkListenerResourceProfileAdded$();

   public final String toString() {
      return "SparkListenerResourceProfileAdded";
   }

   public SparkListenerResourceProfileAdded apply(final ResourceProfile resourceProfile) {
      return new SparkListenerResourceProfileAdded(resourceProfile);
   }

   public Option unapply(final SparkListenerResourceProfileAdded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.resourceProfile()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerResourceProfileAdded$.class);
   }

   private SparkListenerResourceProfileAdded$() {
   }
}

package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.Map;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerEnvironmentUpdate$ extends AbstractFunction1 implements Serializable {
   public static final SparkListenerEnvironmentUpdate$ MODULE$ = new SparkListenerEnvironmentUpdate$();

   public final String toString() {
      return "SparkListenerEnvironmentUpdate";
   }

   public SparkListenerEnvironmentUpdate apply(final Map environmentDetails) {
      return new SparkListenerEnvironmentUpdate(environmentDetails);
   }

   public Option unapply(final SparkListenerEnvironmentUpdate x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.environmentDetails()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerEnvironmentUpdate$.class);
   }

   private SparkListenerEnvironmentUpdate$() {
   }
}

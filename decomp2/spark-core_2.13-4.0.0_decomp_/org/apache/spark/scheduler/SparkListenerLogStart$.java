package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerLogStart$ extends AbstractFunction1 implements Serializable {
   public static final SparkListenerLogStart$ MODULE$ = new SparkListenerLogStart$();

   public final String toString() {
      return "SparkListenerLogStart";
   }

   public SparkListenerLogStart apply(final String sparkVersion) {
      return new SparkListenerLogStart(sparkVersion);
   }

   public Option unapply(final SparkListenerLogStart x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.sparkVersion()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerLogStart$.class);
   }

   private SparkListenerLogStart$() {
   }
}

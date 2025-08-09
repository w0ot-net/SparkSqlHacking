package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerNodeUnblacklisted$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerNodeUnblacklisted$ MODULE$ = new SparkListenerNodeUnblacklisted$();

   public final String toString() {
      return "SparkListenerNodeUnblacklisted";
   }

   public SparkListenerNodeUnblacklisted apply(final long time, final String hostId) {
      return new SparkListenerNodeUnblacklisted(time, hostId);
   }

   public Option unapply(final SparkListenerNodeUnblacklisted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.time()), x$0.hostId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerNodeUnblacklisted$.class);
   }

   private SparkListenerNodeUnblacklisted$() {
   }
}

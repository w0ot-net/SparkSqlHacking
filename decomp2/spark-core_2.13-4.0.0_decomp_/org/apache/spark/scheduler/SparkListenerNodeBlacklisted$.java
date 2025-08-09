package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerNodeBlacklisted$ extends AbstractFunction3 implements Serializable {
   public static final SparkListenerNodeBlacklisted$ MODULE$ = new SparkListenerNodeBlacklisted$();

   public final String toString() {
      return "SparkListenerNodeBlacklisted";
   }

   public SparkListenerNodeBlacklisted apply(final long time, final String hostId, final int executorFailures) {
      return new SparkListenerNodeBlacklisted(time, hostId, executorFailures);
   }

   public Option unapply(final SparkListenerNodeBlacklisted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.time()), x$0.hostId(), BoxesRunTime.boxToInteger(x$0.executorFailures()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerNodeBlacklisted$.class);
   }

   private SparkListenerNodeBlacklisted$() {
   }
}

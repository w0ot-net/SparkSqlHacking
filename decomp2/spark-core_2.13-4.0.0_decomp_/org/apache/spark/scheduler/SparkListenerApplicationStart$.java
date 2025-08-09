package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.runtime.AbstractFunction7;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerApplicationStart$ extends AbstractFunction7 implements Serializable {
   public static final SparkListenerApplicationStart$ MODULE$ = new SparkListenerApplicationStart$();

   public Option $lessinit$greater$default$6() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$7() {
      return .MODULE$;
   }

   public final String toString() {
      return "SparkListenerApplicationStart";
   }

   public SparkListenerApplicationStart apply(final String appName, final Option appId, final long time, final String sparkUser, final Option appAttemptId, final Option driverLogs, final Option driverAttributes) {
      return new SparkListenerApplicationStart(appName, appId, time, sparkUser, appAttemptId, driverLogs, driverAttributes);
   }

   public Option apply$default$6() {
      return .MODULE$;
   }

   public Option apply$default$7() {
      return .MODULE$;
   }

   public Option unapply(final SparkListenerApplicationStart x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(x$0.appName(), x$0.appId(), BoxesRunTime.boxToLong(x$0.time()), x$0.sparkUser(), x$0.appAttemptId(), x$0.driverLogs(), x$0.driverAttributes())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerApplicationStart$.class);
   }

   private SparkListenerApplicationStart$() {
   }
}

package org.apache.spark.status.api.v1;

import java.io.Serializable;
import java.util.Date;
import scala.Option;
import scala.Some;
import scala.Tuple8;
import scala.None.;
import scala.runtime.AbstractFunction8;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ApplicationAttemptInfo$ extends AbstractFunction8 implements Serializable {
   public static final ApplicationAttemptInfo$ MODULE$ = new ApplicationAttemptInfo$();

   public boolean $lessinit$greater$default$7() {
      return false;
   }

   public final String toString() {
      return "ApplicationAttemptInfo";
   }

   public ApplicationAttemptInfo apply(final Option attemptId, final Date startTime, final Date endTime, final Date lastUpdated, final long duration, final String sparkUser, final boolean completed, final String appSparkVersion) {
      return new ApplicationAttemptInfo(attemptId, startTime, endTime, lastUpdated, duration, sparkUser, completed, appSparkVersion);
   }

   public boolean apply$default$7() {
      return false;
   }

   public Option unapply(final ApplicationAttemptInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple8(x$0.attemptId(), x$0.startTime(), x$0.endTime(), x$0.lastUpdated(), BoxesRunTime.boxToLong(x$0.duration()), x$0.sparkUser(), BoxesRunTime.boxToBoolean(x$0.completed()), x$0.appSparkVersion())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ApplicationAttemptInfo$.class);
   }

   private ApplicationAttemptInfo$() {
   }
}

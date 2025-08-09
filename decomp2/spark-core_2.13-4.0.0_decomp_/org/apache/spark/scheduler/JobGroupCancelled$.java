package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JobGroupCancelled$ extends AbstractFunction3 implements Serializable {
   public static final JobGroupCancelled$ MODULE$ = new JobGroupCancelled$();

   public boolean $lessinit$greater$default$2() {
      return false;
   }

   public final String toString() {
      return "JobGroupCancelled";
   }

   public JobGroupCancelled apply(final String groupId, final boolean cancelFutureJobs, final Option reason) {
      return new JobGroupCancelled(groupId, cancelFutureJobs, reason);
   }

   public boolean apply$default$2() {
      return false;
   }

   public Option unapply(final JobGroupCancelled x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.groupId(), BoxesRunTime.boxToBoolean(x$0.cancelFutureJobs()), x$0.reason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobGroupCancelled$.class);
   }

   private JobGroupCancelled$() {
   }
}

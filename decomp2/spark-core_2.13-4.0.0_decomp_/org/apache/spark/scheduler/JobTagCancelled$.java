package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class JobTagCancelled$ extends AbstractFunction3 implements Serializable {
   public static final JobTagCancelled$ MODULE$ = new JobTagCancelled$();

   public final String toString() {
      return "JobTagCancelled";
   }

   public JobTagCancelled apply(final String tagName, final Option reason, final Option cancelledJobs) {
      return new JobTagCancelled(tagName, reason, cancelledJobs);
   }

   public Option unapply(final JobTagCancelled x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.tagName(), x$0.reason(), x$0.cancelledJobs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobTagCancelled$.class);
   }

   private JobTagCancelled$() {
   }
}

package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JobCancelled$ extends AbstractFunction2 implements Serializable {
   public static final JobCancelled$ MODULE$ = new JobCancelled$();

   public final String toString() {
      return "JobCancelled";
   }

   public JobCancelled apply(final int jobId, final Option reason) {
      return new JobCancelled(jobId, reason);
   }

   public Option unapply(final JobCancelled x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.jobId()), x$0.reason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobCancelled$.class);
   }

   private JobCancelled$() {
   }
}

package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JobStarted$ extends AbstractFunction2 implements Serializable {
   public static final JobStarted$ MODULE$ = new JobStarted$();

   public final String toString() {
      return "JobStarted";
   }

   public JobStarted apply(final Job job, final long startTime) {
      return new JobStarted(job, startTime);
   }

   public Option unapply(final JobStarted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.job(), BoxesRunTime.boxToLong(x$0.startTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobStarted$.class);
   }

   private JobStarted$() {
   }
}

package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JobCompleted$ extends AbstractFunction2 implements Serializable {
   public static final JobCompleted$ MODULE$ = new JobCompleted$();

   public final String toString() {
      return "JobCompleted";
   }

   public JobCompleted apply(final Job job, final long completedTime) {
      return new JobCompleted(job, completedTime);
   }

   public Option unapply(final JobCompleted x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.job(), BoxesRunTime.boxToLong(x$0.completedTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobCompleted$.class);
   }

   private JobCompleted$() {
   }
}

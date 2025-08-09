package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JobFailed$ extends AbstractFunction1 implements Serializable {
   public static final JobFailed$ MODULE$ = new JobFailed$();

   public final String toString() {
      return "JobFailed";
   }

   public JobFailed apply(final Exception exception) {
      return new JobFailed(exception);
   }

   public Option unapply(final JobFailed x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.exception()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JobFailed$.class);
   }

   private JobFailed$() {
   }
}

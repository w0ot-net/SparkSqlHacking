package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class GenerateJobs$ extends AbstractFunction1 implements Serializable {
   public static final GenerateJobs$ MODULE$ = new GenerateJobs$();

   public final String toString() {
      return "GenerateJobs";
   }

   public GenerateJobs apply(final Time time) {
      return new GenerateJobs(time);
   }

   public Option unapply(final GenerateJobs x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.time()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GenerateJobs$.class);
   }

   private GenerateJobs$() {
   }
}

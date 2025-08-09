package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerJobEnd$ extends AbstractFunction3 implements Serializable {
   public static final SparkListenerJobEnd$ MODULE$ = new SparkListenerJobEnd$();

   public final String toString() {
      return "SparkListenerJobEnd";
   }

   public SparkListenerJobEnd apply(final int jobId, final long time, final JobResult jobResult) {
      return new SparkListenerJobEnd(jobId, time, jobResult);
   }

   public Option unapply(final SparkListenerJobEnd x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.jobId()), BoxesRunTime.boxToLong(x$0.time()), x$0.jobResult())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerJobEnd$.class);
   }

   private SparkListenerJobEnd$() {
   }
}

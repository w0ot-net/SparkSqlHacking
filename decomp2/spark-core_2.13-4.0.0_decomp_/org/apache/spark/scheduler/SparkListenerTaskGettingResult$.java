package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerTaskGettingResult$ extends AbstractFunction1 implements Serializable {
   public static final SparkListenerTaskGettingResult$ MODULE$ = new SparkListenerTaskGettingResult$();

   public final String toString() {
      return "SparkListenerTaskGettingResult";
   }

   public SparkListenerTaskGettingResult apply(final TaskInfo taskInfo) {
      return new SparkListenerTaskGettingResult(taskInfo);
   }

   public Option unapply(final SparkListenerTaskGettingResult x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.taskInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerTaskGettingResult$.class);
   }

   private SparkListenerTaskGettingResult$() {
   }
}

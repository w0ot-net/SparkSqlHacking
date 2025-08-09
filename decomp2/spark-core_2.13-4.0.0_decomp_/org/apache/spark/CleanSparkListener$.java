package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.scheduler.SparkListener;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class CleanSparkListener$ extends AbstractFunction1 implements Serializable {
   public static final CleanSparkListener$ MODULE$ = new CleanSparkListener$();

   public final String toString() {
      return "CleanSparkListener";
   }

   public CleanSparkListener apply(final SparkListener listener) {
      return new CleanSparkListener(listener);
   }

   public Option unapply(final CleanSparkListener x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.listener()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CleanSparkListener$.class);
   }

   private CleanSparkListener$() {
   }
}

package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerApplicationEnd$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerApplicationEnd$ MODULE$ = new SparkListenerApplicationEnd$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public final String toString() {
      return "SparkListenerApplicationEnd";
   }

   public SparkListenerApplicationEnd apply(final long time, final Option exitCode) {
      return new SparkListenerApplicationEnd(time, exitCode);
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public Option unapply(final SparkListenerApplicationEnd x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.time()), x$0.exitCode())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerApplicationEnd$.class);
   }

   private SparkListenerApplicationEnd$() {
   }
}

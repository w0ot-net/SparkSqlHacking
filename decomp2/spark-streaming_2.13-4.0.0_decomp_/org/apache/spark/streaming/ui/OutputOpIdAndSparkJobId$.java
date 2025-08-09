package org.apache.spark.streaming.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class OutputOpIdAndSparkJobId$ extends AbstractFunction2 implements Serializable {
   public static final OutputOpIdAndSparkJobId$ MODULE$ = new OutputOpIdAndSparkJobId$();

   public final String toString() {
      return "OutputOpIdAndSparkJobId";
   }

   public OutputOpIdAndSparkJobId apply(final int outputOpId, final int sparkJobId) {
      return new OutputOpIdAndSparkJobId(outputOpId, sparkJobId);
   }

   public Option unapply(final OutputOpIdAndSparkJobId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcII.sp(x$0.outputOpId(), x$0.sparkJobId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OutputOpIdAndSparkJobId$.class);
   }

   private OutputOpIdAndSparkJobId$() {
   }
}

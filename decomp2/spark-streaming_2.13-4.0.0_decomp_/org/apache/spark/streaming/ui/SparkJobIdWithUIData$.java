package org.apache.spark.streaming.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkJobIdWithUIData$ extends AbstractFunction2 implements Serializable {
   public static final SparkJobIdWithUIData$ MODULE$ = new SparkJobIdWithUIData$();

   public final String toString() {
      return "SparkJobIdWithUIData";
   }

   public SparkJobIdWithUIData apply(final int sparkJobId, final Option jobData) {
      return new SparkJobIdWithUIData(sparkJobId, jobData);
   }

   public Option unapply(final SparkJobIdWithUIData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.sparkJobId()), x$0.jobData())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkJobIdWithUIData$.class);
   }

   private SparkJobIdWithUIData$() {
   }
}

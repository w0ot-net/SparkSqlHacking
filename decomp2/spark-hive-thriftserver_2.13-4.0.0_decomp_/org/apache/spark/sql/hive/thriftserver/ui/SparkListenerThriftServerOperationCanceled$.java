package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerThriftServerOperationCanceled$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerThriftServerOperationCanceled$ MODULE$ = new SparkListenerThriftServerOperationCanceled$();

   public final String toString() {
      return "SparkListenerThriftServerOperationCanceled";
   }

   public SparkListenerThriftServerOperationCanceled apply(final String id, final long finishTime) {
      return new SparkListenerThriftServerOperationCanceled(id, finishTime);
   }

   public Option unapply(final SparkListenerThriftServerOperationCanceled x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.id(), BoxesRunTime.boxToLong(x$0.finishTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerThriftServerOperationCanceled$.class);
   }

   private SparkListenerThriftServerOperationCanceled$() {
   }
}

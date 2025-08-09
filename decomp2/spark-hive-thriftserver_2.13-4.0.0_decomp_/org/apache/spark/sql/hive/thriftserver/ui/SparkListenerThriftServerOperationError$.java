package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerThriftServerOperationError$ extends AbstractFunction4 implements Serializable {
   public static final SparkListenerThriftServerOperationError$ MODULE$ = new SparkListenerThriftServerOperationError$();

   public final String toString() {
      return "SparkListenerThriftServerOperationError";
   }

   public SparkListenerThriftServerOperationError apply(final String id, final String errorMsg, final String errorTrace, final long finishTime) {
      return new SparkListenerThriftServerOperationError(id, errorMsg, errorTrace, finishTime);
   }

   public Option unapply(final SparkListenerThriftServerOperationError x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.id(), x$0.errorMsg(), x$0.errorTrace(), BoxesRunTime.boxToLong(x$0.finishTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerThriftServerOperationError$.class);
   }

   private SparkListenerThriftServerOperationError$() {
   }
}

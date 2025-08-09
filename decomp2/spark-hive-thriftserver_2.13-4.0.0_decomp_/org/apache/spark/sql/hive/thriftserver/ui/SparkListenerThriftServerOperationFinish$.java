package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerThriftServerOperationFinish$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerThriftServerOperationFinish$ MODULE$ = new SparkListenerThriftServerOperationFinish$();

   public final String toString() {
      return "SparkListenerThriftServerOperationFinish";
   }

   public SparkListenerThriftServerOperationFinish apply(final String id, final long finishTime) {
      return new SparkListenerThriftServerOperationFinish(id, finishTime);
   }

   public Option unapply(final SparkListenerThriftServerOperationFinish x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.id(), BoxesRunTime.boxToLong(x$0.finishTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerThriftServerOperationFinish$.class);
   }

   private SparkListenerThriftServerOperationFinish$() {
   }
}

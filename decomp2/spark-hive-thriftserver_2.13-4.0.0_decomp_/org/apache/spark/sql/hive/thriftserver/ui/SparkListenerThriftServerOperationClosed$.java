package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerThriftServerOperationClosed$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerThriftServerOperationClosed$ MODULE$ = new SparkListenerThriftServerOperationClosed$();

   public final String toString() {
      return "SparkListenerThriftServerOperationClosed";
   }

   public SparkListenerThriftServerOperationClosed apply(final String id, final long closeTime) {
      return new SparkListenerThriftServerOperationClosed(id, closeTime);
   }

   public Option unapply(final SparkListenerThriftServerOperationClosed x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.id(), BoxesRunTime.boxToLong(x$0.closeTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerThriftServerOperationClosed$.class);
   }

   private SparkListenerThriftServerOperationClosed$() {
   }
}

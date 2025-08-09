package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerThriftServerOperationParsed$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerThriftServerOperationParsed$ MODULE$ = new SparkListenerThriftServerOperationParsed$();

   public final String toString() {
      return "SparkListenerThriftServerOperationParsed";
   }

   public SparkListenerThriftServerOperationParsed apply(final String id, final String executionPlan) {
      return new SparkListenerThriftServerOperationParsed(id, executionPlan);
   }

   public Option unapply(final SparkListenerThriftServerOperationParsed x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.id(), x$0.executionPlan())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerThriftServerOperationParsed$.class);
   }

   private SparkListenerThriftServerOperationParsed$() {
   }
}

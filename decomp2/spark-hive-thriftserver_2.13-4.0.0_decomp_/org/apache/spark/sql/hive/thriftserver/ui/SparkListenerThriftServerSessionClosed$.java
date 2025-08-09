package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerThriftServerSessionClosed$ extends AbstractFunction2 implements Serializable {
   public static final SparkListenerThriftServerSessionClosed$ MODULE$ = new SparkListenerThriftServerSessionClosed$();

   public final String toString() {
      return "SparkListenerThriftServerSessionClosed";
   }

   public SparkListenerThriftServerSessionClosed apply(final String sessionId, final long finishTime) {
      return new SparkListenerThriftServerSessionClosed(sessionId, finishTime);
   }

   public Option unapply(final SparkListenerThriftServerSessionClosed x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.sessionId(), BoxesRunTime.boxToLong(x$0.finishTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerThriftServerSessionClosed$.class);
   }

   private SparkListenerThriftServerSessionClosed$() {
   }
}

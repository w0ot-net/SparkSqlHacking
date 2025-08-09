package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerThriftServerOperationStart$ extends AbstractFunction6 implements Serializable {
   public static final SparkListenerThriftServerOperationStart$ MODULE$ = new SparkListenerThriftServerOperationStart$();

   public String $lessinit$greater$default$6() {
      return "UNKNOWN";
   }

   public final String toString() {
      return "SparkListenerThriftServerOperationStart";
   }

   public SparkListenerThriftServerOperationStart apply(final String id, final String sessionId, final String statement, final String groupId, final long startTime, final String userName) {
      return new SparkListenerThriftServerOperationStart(id, sessionId, statement, groupId, startTime, userName);
   }

   public String apply$default$6() {
      return "UNKNOWN";
   }

   public Option unapply(final SparkListenerThriftServerOperationStart x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.id(), x$0.sessionId(), x$0.statement(), x$0.groupId(), BoxesRunTime.boxToLong(x$0.startTime()), x$0.userName())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerThriftServerOperationStart$.class);
   }

   private SparkListenerThriftServerOperationStart$() {
   }
}

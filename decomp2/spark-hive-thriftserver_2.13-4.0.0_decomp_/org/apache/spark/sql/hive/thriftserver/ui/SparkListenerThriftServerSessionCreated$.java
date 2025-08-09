package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkListenerThriftServerSessionCreated$ extends AbstractFunction4 implements Serializable {
   public static final SparkListenerThriftServerSessionCreated$ MODULE$ = new SparkListenerThriftServerSessionCreated$();

   public final String toString() {
      return "SparkListenerThriftServerSessionCreated";
   }

   public SparkListenerThriftServerSessionCreated apply(final String ip, final String sessionId, final String userName, final long startTime) {
      return new SparkListenerThriftServerSessionCreated(ip, sessionId, userName, startTime);
   }

   public Option unapply(final SparkListenerThriftServerSessionCreated x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.ip(), x$0.sessionId(), x$0.userName(), BoxesRunTime.boxToLong(x$0.startTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkListenerThriftServerSessionCreated$.class);
   }

   private SparkListenerThriftServerSessionCreated$() {
   }
}

package org.apache.spark.deploy.history;

import java.io.Serializable;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Tuple9;
import scala.None.;
import scala.runtime.AbstractFunction9;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class LogInfo$ extends AbstractFunction9 implements Serializable {
   public static final LogInfo$ MODULE$ = new LogInfo$();

   public final String toString() {
      return "LogInfo";
   }

   public LogInfo apply(final String logPath, final long lastProcessed, final Enumeration.Value logType, final Option appId, final Option attemptId, final long fileSize, final Option lastIndex, final Option lastEvaluatedForCompaction, final boolean isComplete) {
      return new LogInfo(logPath, lastProcessed, logType, appId, attemptId, fileSize, lastIndex, lastEvaluatedForCompaction, isComplete);
   }

   public Option unapply(final LogInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple9(x$0.logPath(), BoxesRunTime.boxToLong(x$0.lastProcessed()), x$0.logType(), x$0.appId(), x$0.attemptId(), BoxesRunTime.boxToLong(x$0.fileSize()), x$0.lastIndex(), x$0.lastEvaluatedForCompaction(), BoxesRunTime.boxToBoolean(x$0.isComplete()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LogInfo$.class);
   }

   private LogInfo$() {
   }
}

package org.apache.spark.mapred;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.executor.CommitDeniedException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.scheduler.OutputCommitCoordinator;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;

public final class SparkHadoopMapRedUtil$ implements Logging {
   public static final SparkHadoopMapRedUtil$ MODULE$ = new SparkHadoopMapRedUtil$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public void commitTask(final OutputCommitter committer, final TaskAttemptContext mrTaskContext, final int jobId, final int splitId) {
      TaskAttemptID mrTaskAttemptID = mrTaskContext.getTaskAttemptID();
      if (committer.needsTaskCommit(mrTaskContext)) {
         SparkConf sparkConf = SparkEnv$.MODULE$.get().conf();
         boolean shouldCoordinateWithDriver = sparkConf.getBoolean("spark.hadoop.outputCommitCoordination.enabled", true);
         if (shouldCoordinateWithDriver) {
            OutputCommitCoordinator outputCommitCoordinator = SparkEnv$.MODULE$.get().outputCommitCoordinator();
            TaskContext ctx = TaskContext$.MODULE$.get();
            boolean canCommit = outputCommitCoordinator.canCommit(ctx.stageId(), ctx.stageAttemptNumber(), splitId, ctx.attemptNumber());
            if (canCommit) {
               this.performCommit$1(committer, mrTaskContext, mrTaskAttemptID);
            } else {
               MessageWithContext message = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ": Not committed because"})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, mrTaskAttemptID)}))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{" the driver did not authorize commit"})))).log(scala.collection.immutable.Nil..MODULE$));
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> message));
               committer.abortTask(mrTaskContext);
               throw new CommitDeniedException(message.message(), ctx.stageId(), splitId, ctx.attemptNumber());
            }
         } else {
            this.performCommit$1(committer, mrTaskContext, mrTaskAttemptID);
         }
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"No need to commit output of task because needsTaskCommit=false:"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, mrTaskAttemptID)}))))));
      }
   }

   private final void performCommit$1(final OutputCommitter committer$1, final TaskAttemptContext mrTaskContext$1, final TaskAttemptID mrTaskAttemptID$1) {
      try {
         Tuple2 var8 = Utils$.MODULE$.timeTakenMs((JFunction0.mcV.sp)() -> committer$1.commitTask(mrTaskContext$1));
         if (var8 != null) {
            long timeCost = var8._2$mcJ$sp();
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ": Committed."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, mrTaskAttemptID$1)}))).$plus(MODULE$.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{" Elapsed time: ", " ms."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(timeCost))}))))));
         } else {
            throw new MatchError(var8);
         }
      } catch (IOException var12) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Error committing the output of task: ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, mrTaskAttemptID$1)})))), var12);
         committer$1.abortTask(mrTaskContext$1);
         throw var12;
      }
   }

   private SparkHadoopMapRedUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

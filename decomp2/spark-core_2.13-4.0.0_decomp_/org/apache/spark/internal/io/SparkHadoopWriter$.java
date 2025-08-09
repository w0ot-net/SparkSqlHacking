package org.apache.spark.internal.io;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.TaskContext;
import org.apache.spark.executor.OutputMetrics;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.java8.JFunction0;

public final class SparkHadoopWriter$ implements Logging {
   public static final SparkHadoopWriter$ MODULE$ = new SparkHadoopWriter$();
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

   public void write(final RDD rdd, final HadoopWriteConfigUtil config, final ClassTag evidence$1) {
      SparkContext sparkContext = rdd.context();
      int commitJobId = rdd.id();
      String jobTrackerId = SparkHadoopWriterUtils$.MODULE$.createJobTrackerID(new Date());
      JobContext jobContext = config.createJobContext(jobTrackerId, commitJobId);
      config.initOutputFormat(jobContext);
      config.assertConf(jobContext, rdd.conf());
      jobContext.getConfiguration().set("spark.sql.sources.writeJobUUID", UUID.randomUUID().toString());
      HadoopMapReduceCommitProtocol committer = config.createCommitter(commitJobId);
      committer.setupJob(jobContext);

      try {
         FileCommitProtocol.TaskCommitMessage[] ret = (FileCommitProtocol.TaskCommitMessage[])sparkContext.runJob(rdd, (Function2)((context, iter) -> {
            int attemptId = context.stageAttemptNumber() << 16 | context.attemptNumber();
            return MODULE$.executeTask(context, config, jobTrackerId, commitJobId, context.partitionId(), attemptId, committer, iter, evidence$1);
         }), .MODULE$.apply(FileCommitProtocol.TaskCommitMessage.class));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Start to commit write Job ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, jobContext.getJobID())})))));
         Tuple2 var14 = Utils$.MODULE$.timeTakenMs((JFunction0.mcV.sp)() -> committer.commitJob(jobContext, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(ret).toImmutableArraySeq()));
         if (var14 != null) {
            long duration = var14._2$mcJ$sp();
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Write Job ", " committed."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, jobContext.getJobID())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Elapsed time: ", " ms."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DURATION..MODULE$, BoxesRunTime.boxToLong(duration))}))))));
         } else {
            throw new MatchError(var14);
         }
      } catch (Throwable var18) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Aborting job ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, jobContext.getJobID())})))), var18);
         committer.abortJob(jobContext);
         throw new SparkException("Job aborted.", var18);
      }
   }

   private FileCommitProtocol.TaskCommitMessage executeTask(final TaskContext context, final HadoopWriteConfigUtil config, final String jobTrackerId, final int commitJobId, final int sparkPartitionId, final int sparkAttemptNumber, final FileCommitProtocol committer, final Iterator iterator, final ClassTag evidence$2) {
      TaskAttemptContext taskContext = config.createTaskAttemptContext(jobTrackerId, commitJobId, sparkPartitionId, sparkAttemptNumber);
      committer.setupTask(taskContext);
      config.initWriter(taskContext, sparkPartitionId);
      LongRef recordsWritten = LongRef.create(0L);
      Tuple2 var14 = SparkHadoopWriterUtils$.MODULE$.initHadoopOutputMetrics(context);
      if (var14 != null) {
         OutputMetrics outputMetrics = (OutputMetrics)var14._1();
         Function0 callback = (Function0)var14._2();
         Tuple2 var13 = new Tuple2(outputMetrics, callback);
         OutputMetrics outputMetrics = (OutputMetrics)var13._1();
         Function0 callback = (Function0)var13._2();

         try {
            Function0 x$1 = () -> {
               while(iterator.hasNext()) {
                  Tuple2 pair = (Tuple2)iterator.next();
                  config.write(pair);
                  SparkHadoopWriterUtils$.MODULE$.maybeUpdateOutputMetrics(outputMetrics, callback, recordsWritten.elem);
                  ++recordsWritten.elem;
               }

               config.closeWriter(taskContext);
               return committer.commitTask(taskContext);
            };
            Function0 x$2 = () -> {
               try {
                  config.closeWriter(taskContext);
               } finally {
                  committer.abortTask(taskContext);
                  MODULE$.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task ", " aborted."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, taskContext.getTaskAttemptID())})))));
               }

            };
            Function0 x$3 = () -> Utils$.MODULE$.tryWithSafeFinallyAndFailureCallbacks$default$3(x$1);
            FileCommitProtocol.TaskCommitMessage ret = (FileCommitProtocol.TaskCommitMessage)Utils$.MODULE$.tryWithSafeFinallyAndFailureCallbacks(x$1, x$2, x$3);
            outputMetrics.setBytesWritten(callback.apply$mcJ$sp());
            outputMetrics.setRecordsWritten(recordsWritten.elem);
            return ret;
         } catch (Throwable var24) {
            throw new SparkException("Task failed while writing rows", var24);
         }
      } else {
         throw new MatchError(var14);
      }
   }

   private SparkHadoopWriter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

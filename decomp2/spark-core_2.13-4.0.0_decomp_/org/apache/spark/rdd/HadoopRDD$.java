package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobID;
import org.apache.hadoop.mapred.SplitLocationInfo;
import org.apache.hadoop.mapred.TaskAttemptID;
import org.apache.hadoop.mapred.TaskID;
import org.apache.hadoop.mapreduce.TaskType;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.scheduler.HDFSCacheTaskLocation;
import org.apache.spark.scheduler.HostTaskLocation;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Option.;
import scala.runtime.ModuleSerializationProxy;

public final class HadoopRDD$ implements Logging, Serializable {
   public static final HadoopRDD$ MODULE$ = new HadoopRDD$();
   private static final Object CONFIGURATION_INSTANTIATION_LOCK;
   private static final DateTimeFormatter org$apache$spark$rdd$HadoopRDD$$DATE_TIME_FORMATTER;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      CONFIGURATION_INSTANTIATION_LOCK = new Object();
      org$apache$spark$rdd$HadoopRDD$$DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.US).withZone(ZoneId.systemDefault());
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

   public Object CONFIGURATION_INSTANTIATION_LOCK() {
      return CONFIGURATION_INSTANTIATION_LOCK;
   }

   public DateTimeFormatter org$apache$spark$rdd$HadoopRDD$$DATE_TIME_FORMATTER() {
      return org$apache$spark$rdd$HadoopRDD$$DATE_TIME_FORMATTER;
   }

   public Object getCachedMetadata(final String key) {
      return SparkEnv$.MODULE$.get().hadoopJobMetadata().get(key);
   }

   public void org$apache$spark$rdd$HadoopRDD$$putCachedMetadata(final String key, final Object value) {
      SparkEnv$.MODULE$.get().hadoopJobMetadata().put(key, value);
   }

   public void addLocalConfiguration(final String jobTrackerId, final int jobId, final int splitId, final int attemptId, final JobConf conf) {
      JobID jobID = new JobID(jobTrackerId, jobId);
      TaskAttemptID taId = new TaskAttemptID(new TaskID(jobID, TaskType.MAP, splitId), attemptId);
      conf.set("mapreduce.task.id", taId.getTaskID().toString());
      conf.set("mapreduce.task.attempt.id", taId.toString());
      conf.setBoolean("mapreduce.task.ismap", true);
      conf.setInt("mapreduce.task.partition", splitId);
      conf.set("mapreduce.job.id", jobID.toString());
   }

   public Option convertSplitLocationInfo(final SplitLocationInfo[] infos) {
      return .MODULE$.apply(infos).map((x$4) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$4), (loc) -> {
            String locationStr = loc.getLocation();
            if (locationStr != null) {
               String var2 = "localhost";
               if (locationStr == null) {
                  if (var2 == null) {
                     return scala.None..MODULE$;
                  }
               } else if (locationStr.equals(var2)) {
                  return scala.None..MODULE$;
               }

               if (loc.isInMemory()) {
                  MODULE$.logDebug((Function0)(() -> "Partition " + locationStr + " is cached by Hadoop."));
                  return new Some((new HDFSCacheTaskLocation(locationStr)).toString());
               } else {
                  return new Some((new HostTaskLocation(locationStr)).toString());
               }
            } else {
               return scala.None..MODULE$;
            }
         }, scala.reflect.ClassTag..MODULE$.apply(String.class))).toImmutableArraySeq());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HadoopRDD$.class);
   }

   private HadoopRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.deploy.history;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.io.CompressionCodec;
import org.apache.spark.io.CompressionCodec$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.runtime.BoxesRunTime;

public final class EventLogFileReader$ implements Logging {
   public static final EventLogFileReader$ MODULE$ = new EventLogFileReader$();
   private static final ConcurrentHashMap codecMap;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      codecMap = new ConcurrentHashMap();
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

   private ConcurrentHashMap codecMap() {
      return codecMap;
   }

   public EventLogFileReader apply(final FileSystem fs, final Path path, final Option lastIndex) {
      if (lastIndex instanceof Some) {
         return new RollingEventLogFilesFileReader(fs, path);
      } else if (.MODULE$.equals(lastIndex)) {
         return new SingleFileEventLogFileReader(fs, path);
      } else {
         throw new MatchError(lastIndex);
      }
   }

   public Option apply(final FileSystem fs, final Path path) {
      return this.apply(fs, fs.getFileStatus(path));
   }

   public Option apply(final FileSystem fs, final FileStatus status) {
      if (this.isSingleEventLog(status)) {
         return new Some(new SingleFileEventLogFileReader(fs, status.getPath(), scala.Option..MODULE$.apply(status)));
      } else if (this.isRollingEventLogs(status)) {
         FileStatus[] files = fs.listStatus(status.getPath());
         if (scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps((Object[])files), (statusx) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(statusx))) && scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps((Object[])files), (statusx) -> BoxesRunTime.boxToBoolean($anonfun$apply$2(statusx)))) {
            return new Some(new RollingEventLogFilesFileReader(fs, status.getPath()));
         } else {
            this.logDebug((Function0)(() -> "Rolling event log directory have no event log file at " + status.getPath()));
            return .MODULE$;
         }
      } else {
         return .MODULE$;
      }
   }

   public InputStream openEventLog(final Path log, final FileSystem fs) {
      BufferedInputStream in = new BufferedInputStream(fs.open(log));

      try {
         Option codec = EventLogFileWriter$.MODULE$.codecName(log).map((c) -> (CompressionCodec)MODULE$.codecMap().computeIfAbsent(c, (x$1) -> CompressionCodec$.MODULE$.createCodec(new SparkConf(), x$1)));
         return (InputStream)codec.map((x$2) -> x$2.compressedContinuousInputStream(in)).getOrElse(() -> in);
      } catch (Throwable var6) {
         in.close();
         throw var6;
      }
   }

   private boolean isSingleEventLog(final FileStatus status) {
      return !status.isDirectory() && !status.getPath().getName().startsWith(".");
   }

   private boolean isRollingEventLogs(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isEventLogDir(status);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$1(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isEventLogFile(status);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$2(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isAppStatusFile(status);
   }

   private EventLogFileReader$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

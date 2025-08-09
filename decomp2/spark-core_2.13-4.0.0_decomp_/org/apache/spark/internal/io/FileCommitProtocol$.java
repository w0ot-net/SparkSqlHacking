package org.apache.spark.internal.io;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.runtime.BoxesRunTime;

public final class FileCommitProtocol$ implements Logging {
   public static final FileCommitProtocol$ MODULE$ = new FileCommitProtocol$();
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

   public FileCommitProtocol instantiate(final String className, final String jobId, final String outputPath, final boolean dynamicPartitionOverwrite) {
      this.logDebug((Function0)(() -> "Creating committer " + className + "; job " + jobId + "; output=" + outputPath + "; dynamic=" + dynamicPartitionOverwrite));
      Class clazz = Utils$.MODULE$.classForName(className, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());

      FileCommitProtocol var10000;
      try {
         Constructor ctor = clazz.getDeclaredConstructor(String.class, String.class, Boolean.TYPE);
         this.logDebug((Function0)(() -> "Using (String, String, Boolean) constructor"));
         var10000 = (FileCommitProtocol)ctor.newInstance(jobId, outputPath, BoxesRunTime.boxToBoolean(dynamicPartitionOverwrite));
      } catch (NoSuchMethodException var8) {
         this.logDebug((Function0)(() -> "Falling back to (String, String) constructor"));
         .MODULE$.require(!dynamicPartitionOverwrite, () -> "Dynamic Partition Overwrite is enabled but the committer " + className + " does not have the appropriate constructor");
         Constructor ctor = clazz.getDeclaredConstructor(String.class, String.class);
         var10000 = (FileCommitProtocol)ctor.newInstance(jobId, outputPath);
      }

      return var10000;
   }

   public boolean instantiate$default$4() {
      return false;
   }

   public Path getStagingDir(final String path, final String jobId) {
      return new Path(path, ".spark-staging-" + jobId);
   }

   private FileCommitProtocol$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.util;

import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;

public final class SparkErrorUtils$ implements SparkErrorUtils {
   public static final SparkErrorUtils$ MODULE$ = new SparkErrorUtils$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      SparkErrorUtils.$init$(MODULE$);
   }

   public Object tryOrIOException(final Function0 block) {
      return SparkErrorUtils.tryOrIOException$(this, block);
   }

   public Object tryWithResource(final Function0 createResource, final Function1 f) {
      return SparkErrorUtils.tryWithResource$(this, createResource, f);
   }

   public Object tryInitializeResource(final Function0 createResource, final Function1 initialize) {
      return SparkErrorUtils.tryInitializeResource$(this, createResource, initialize);
   }

   public Object tryWithSafeFinally(final Function0 block, final Function0 finallyBlock) {
      return SparkErrorUtils.tryWithSafeFinally$(this, block, finallyBlock);
   }

   public String stackTraceToString(final Throwable t) {
      return SparkErrorUtils.stackTraceToString$(this, t);
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
      Logging.logInfo$(this, (Function0)msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, (LogEntry)entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, (LogEntry)entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, (Function0)msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, (LogEntry)entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, (LogEntry)entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, (Function0)msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, (LogEntry)entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, (LogEntry)entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, (Function0)msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, (LogEntry)entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, (LogEntry)entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, (Function0)msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, (LogEntry)entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, (LogEntry)entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, (Function0)msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, (Function0)msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, (Function0)msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, (Function0)msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, (Function0)msg, throwable);
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

   private SparkErrorUtils$() {
   }
}

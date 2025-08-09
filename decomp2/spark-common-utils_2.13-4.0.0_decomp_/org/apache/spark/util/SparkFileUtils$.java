package org.apache.spark.util;

import java.io.File;
import java.net.URI;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;

public final class SparkFileUtils$ implements SparkFileUtils {
   public static final SparkFileUtils$ MODULE$ = new SparkFileUtils$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      SparkFileUtils.$init$(MODULE$);
   }

   public URI resolveURI(final String path) {
      return SparkFileUtils.resolveURI$(this, path);
   }

   public File[] recursiveList(final File f) {
      return SparkFileUtils.recursiveList$(this, f);
   }

   public boolean createDirectory(final File dir) {
      return SparkFileUtils.createDirectory$(this, dir);
   }

   public File createDirectory(final String root, final String namePrefix) {
      return SparkFileUtils.createDirectory$(this, root, namePrefix);
   }

   public String createDirectory$default$2() {
      return SparkFileUtils.createDirectory$default$2$(this);
   }

   public File createTempDir() {
      return SparkFileUtils.createTempDir$(this);
   }

   public File createTempDir(final String root, final String namePrefix) {
      return SparkFileUtils.createTempDir$(this, root, namePrefix);
   }

   public String createTempDir$default$1() {
      return SparkFileUtils.createTempDir$default$1$(this);
   }

   public String createTempDir$default$2() {
      return SparkFileUtils.createTempDir$default$2$(this);
   }

   public void deleteRecursively(final File file) {
      SparkFileUtils.deleteRecursively$(this, file);
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

   private SparkFileUtils$() {
   }
}

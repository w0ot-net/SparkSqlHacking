package org.apache.spark.util.logging;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.runtime.BoxesRunTime;

public final class DriverLogger$ implements Logging {
   public static final DriverLogger$ MODULE$ = new DriverLogger$();
   private static final String DRIVER_LOG_DIR;
   private static final String DRIVER_LOG_FILE;
   private static final String DRIVER_LOG_FILE_SUFFIX;
   private static final String APPENDER_NAME;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      DRIVER_LOG_DIR = "__driver_logs__";
      DRIVER_LOG_FILE = "driver.log";
      DRIVER_LOG_FILE_SUFFIX = "_" + MODULE$.DRIVER_LOG_FILE();
      APPENDER_NAME = "_DriverLogAppender";
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

   public String DRIVER_LOG_DIR() {
      return DRIVER_LOG_DIR;
   }

   public String DRIVER_LOG_FILE() {
      return DRIVER_LOG_FILE;
   }

   public String DRIVER_LOG_FILE_SUFFIX() {
      return DRIVER_LOG_FILE_SUFFIX;
   }

   public String APPENDER_NAME() {
      return APPENDER_NAME;
   }

   public Option apply(final SparkConf conf) {
      boolean localDriverLogEnabled = ((Option)conf.get((ConfigEntry)package$.MODULE$.DRIVER_LOG_LOCAL_DIR())).nonEmpty();
      if ((!BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.DRIVER_LOG_PERSISTTODFS())) || !Utils$.MODULE$.isClientMode(conf)) && !localDriverLogEnabled) {
         return .MODULE$;
      } else if (conf.contains((ConfigEntry)package$.MODULE$.DRIVER_LOG_DFS_DIR())) {
         Object var10000;
         try {
            var10000 = new Some(new DriverLogger(conf));
         } catch (Exception var4) {
            this.logError((Function0)(() -> "Could not add driver logger"), var4);
            var10000 = .MODULE$;
         }

         return (Option)var10000;
      } else if (localDriverLogEnabled) {
         new DriverLogger(conf);
         return .MODULE$;
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver logs are not persisted because"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " is not configured"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, package$.MODULE$.DRIVER_LOG_DFS_DIR().key())}))))));
         return .MODULE$;
      }
   }

   private DriverLogger$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

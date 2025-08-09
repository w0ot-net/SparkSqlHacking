package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class HealthTracker$ implements Logging {
   public static final HealthTracker$ MODULE$ = new HealthTracker$();
   private static final String DEFAULT_TIMEOUT;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      DEFAULT_TIMEOUT = "1h";
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

   public Clock $lessinit$greater$default$4() {
      return new SystemClock();
   }

   private String DEFAULT_TIMEOUT() {
      return DEFAULT_TIMEOUT;
   }

   public boolean isExcludeOnFailureEnabled(final SparkConf conf) {
      Option var3 = ((Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_ENABLED_APPLICATION())).orElse(() -> (Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_ENABLED()));
      if (var3 instanceof Some var4) {
         boolean enabled = BoxesRunTime.unboxToBoolean(var4.value());
         return enabled;
      } else if (.MODULE$.equals(var3)) {
         String legacyKey = org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_LEGACY_TIMEOUT_CONF().key();
         return ((Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_LEGACY_TIMEOUT_CONF())).exists((JFunction1.mcZJ.sp)(legacyTimeout) -> {
            if (legacyTimeout == 0L) {
               MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Turning off excludeOnFailure due to legacy configuration: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " == 0"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, legacyKey)}))))));
               return false;
            } else {
               MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Turning on excludeOnFailure due to legacy configuration: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " > 0"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, legacyKey)}))))));
               return true;
            }
         });
      } else {
         throw new MatchError(var3);
      }
   }

   public long getExcludeOnFailureTimeout(final SparkConf conf) {
      return BoxesRunTime.unboxToLong(((Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_TIMEOUT_CONF())).getOrElse((JFunction0.mcJ.sp)() -> BoxesRunTime.unboxToLong(((Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_LEGACY_TIMEOUT_CONF())).getOrElse((JFunction0.mcJ.sp)() -> Utils$.MODULE$.timeStringAsMs(MODULE$.DEFAULT_TIMEOUT())))));
   }

   public void validateExcludeOnFailureConfs(final SparkConf conf) {
      (new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.MAX_TASK_ATTEMPTS_PER_EXECUTOR(), new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.MAX_TASK_ATTEMPTS_PER_NODE(), new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.MAX_FAILURES_PER_EXEC_STAGE(), new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.MAX_FAILED_EXEC_PER_NODE_STAGE(), new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.MAX_FAILURES_PER_EXEC(), new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.MAX_FAILED_EXEC_PER_NODE(), scala.collection.immutable.Nil..MODULE$))))))).foreach((config) -> {
         $anonfun$validateExcludeOnFailureConfs$1(conf, config);
         return BoxedUnit.UNIT;
      });
      long timeout = this.getExcludeOnFailureTimeout(conf);
      if (timeout <= 0L) {
         Option var5 = (Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_TIMEOUT_CONF());
         if (var5 instanceof Some) {
            mustBePos$1(org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_TIMEOUT_CONF().key(), Long.toString(timeout));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!.MODULE$.equals(var5)) {
               throw new MatchError(var5);
            }

            mustBePos$1(org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_LEGACY_TIMEOUT_CONF().key(), Long.toString(timeout));
            BoxedUnit var8 = BoxedUnit.UNIT;
         }
      }

      int maxTaskFailures = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.TASK_MAX_FAILURES()));
      int maxNodeAttempts = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.MAX_TASK_ATTEMPTS_PER_NODE()));
      if (maxNodeAttempts >= maxTaskFailures) {
         String var10002 = org.apache.spark.internal.config.package$.MODULE$.MAX_TASK_ATTEMPTS_PER_NODE().key();
         throw new IllegalArgumentException(var10002 + " ( = " + maxNodeAttempts + ") was >= " + org.apache.spark.internal.config.package$.MODULE$.TASK_MAX_FAILURES().key() + " ( = " + maxTaskFailures + " ). Though excludeOnFailure is enabled, with this configuration, Spark will not be robust to one bad node. Decrease " + org.apache.spark.internal.config.package$.MODULE$.MAX_TASK_ATTEMPTS_PER_NODE().key() + ", increase " + org.apache.spark.internal.config.package$.MODULE$.TASK_MAX_FAILURES().key() + ", or disable excludeOnFailure with " + org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_ENABLED().key());
      }
   }

   private static final void mustBePos$1(final String k, final String v) {
      throw new IllegalArgumentException(k + " was " + v + ", but must be > 0.");
   }

   // $FF: synthetic method
   public static final void $anonfun$validateExcludeOnFailureConfs$1(final SparkConf conf$3, final ConfigEntry config) {
      int v = BoxesRunTime.unboxToInt(conf$3.get(config));
      if (v <= 0) {
         mustBePos$1(config.key(), Integer.toString(v));
      }
   }

   private HealthTracker$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

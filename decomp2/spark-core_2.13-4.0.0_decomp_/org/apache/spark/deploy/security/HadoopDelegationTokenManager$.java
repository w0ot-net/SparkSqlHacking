package org.apache.spark.deploy.security;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class HadoopDelegationTokenManager$ implements Logging {
   public static final HadoopDelegationTokenManager$ MODULE$ = new HadoopDelegationTokenManager$();
   private static final String providerEnabledConfig;
   private static final List deprecatedProviderEnabledConfigs;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      providerEnabledConfig = "spark.security.credentials.%s.enabled";
      deprecatedProviderEnabledConfigs = new .colon.colon("spark.yarn.security.tokens.%s.enabled", new .colon.colon("spark.yarn.security.credentials.%s.enabled", scala.collection.immutable.Nil..MODULE$));
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

   private String providerEnabledConfig() {
      return providerEnabledConfig;
   }

   private List deprecatedProviderEnabledConfigs() {
      return deprecatedProviderEnabledConfigs;
   }

   public boolean isServiceEnabled(final SparkConf sparkConf, final String serviceName) {
      String key = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(this.providerEnabledConfig()), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{serviceName}));
      this.deprecatedProviderEnabledConfigs().foreach((pattern) -> {
         $anonfun$isServiceEnabled$1(serviceName, sparkConf, key, pattern);
         return BoxedUnit.UNIT;
      });
      boolean isEnabledDeprecated = this.deprecatedProviderEnabledConfigs().forall((pattern) -> BoxesRunTime.boxToBoolean($anonfun$isServiceEnabled$3(sparkConf, serviceName, pattern)));
      return BoxesRunTime.unboxToBoolean(sparkConf.getOption(key).map((x$4) -> BoxesRunTime.boxToBoolean($anonfun$isServiceEnabled$6(x$4))).getOrElse((JFunction0.mcZ.sp)() -> isEnabledDeprecated));
   }

   // $FF: synthetic method
   public static final void $anonfun$isServiceEnabled$1(final String serviceName$1, final SparkConf sparkConf$1, final String key$1, final String pattern) {
      String deprecatedKey = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(pattern), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{serviceName$1}));
      if (sparkConf$1.contains(deprecatedKey)) {
         MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is deprecated. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DEPRECATED_KEY..MODULE$, deprecatedKey)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Please use ", " instead."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, key$1)}))))));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isServiceEnabled$4(final String x$3) {
      return scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(x$3));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isServiceEnabled$3(final SparkConf sparkConf$1, final String serviceName$1, final String pattern) {
      return BoxesRunTime.unboxToBoolean(sparkConf$1.getOption(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(pattern), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{serviceName$1}))).map((x$3) -> BoxesRunTime.boxToBoolean($anonfun$isServiceEnabled$4(x$3))).getOrElse((JFunction0.mcZ.sp)() -> true));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isServiceEnabled$6(final String x$4) {
      return scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   private HadoopDelegationTokenManager$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

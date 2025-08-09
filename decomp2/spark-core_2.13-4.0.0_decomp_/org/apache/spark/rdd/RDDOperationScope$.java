package org.apache.spark.rdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.scala.DefaultScalaModule.;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.runtime.BoxesRunTime;

public final class RDDOperationScope$ implements Logging {
   public static final RDDOperationScope$ MODULE$ = new RDDOperationScope$();
   private static final ObjectMapper org$apache$spark$rdd$RDDOperationScope$$jsonMapper;
   private static final AtomicInteger scopeCounter;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      org$apache$spark$rdd$RDDOperationScope$$jsonMapper = (new ObjectMapper()).registerModule(.MODULE$);
      scopeCounter = new AtomicInteger(0);
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

   public Option $lessinit$greater$default$2() {
      return scala.None..MODULE$;
   }

   public String $lessinit$greater$default$3() {
      return Integer.toString(this.nextScopeId());
   }

   public ObjectMapper org$apache$spark$rdd$RDDOperationScope$$jsonMapper() {
      return org$apache$spark$rdd$RDDOperationScope$$jsonMapper;
   }

   private AtomicInteger scopeCounter() {
      return scopeCounter;
   }

   public RDDOperationScope fromJson(final String s) {
      return (RDDOperationScope)this.org$apache$spark$rdd$RDDOperationScope$$jsonMapper().readValue(s, RDDOperationScope.class);
   }

   public int nextScopeId() {
      return this.scopeCounter().getAndIncrement();
   }

   public Object withScope(final SparkContext sc, final boolean allowNesting, final Function0 body) {
      String ourMethodName = "withScope";
      String callerMethodName = (String)scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.dropWhile$extension(scala.Predef..MODULE$.refArrayOps((Object[])Thread.currentThread().getStackTrace()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$withScope$1(ourMethodName, x$2)))), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$withScope$2(ourMethodName, x$3))).map((x$4) -> x$4.getMethodName()).getOrElse(() -> {
         MODULE$.logWarning((Function0)(() -> "No valid method name for this RDD operation scope!"));
         return "N/A";
      });
      return this.withScope(sc, callerMethodName, allowNesting, false, body);
   }

   public Object withScope(final SparkContext sc, final String name, final boolean allowNesting, final boolean ignoreParent, final Function0 body) {
      String scopeKey = SparkContext$.MODULE$.RDD_SCOPE_KEY();
      String noOverrideKey = SparkContext$.MODULE$.RDD_SCOPE_NO_OVERRIDE_KEY();
      String oldScopeJson = sc.getLocalProperty(scopeKey);
      Option oldScope = scala.Option..MODULE$.apply(oldScopeJson).map((s) -> MODULE$.fromJson(s));
      String oldNoOverride = sc.getLocalProperty(noOverrideKey);

      Object var10000;
      try {
         if (ignoreParent) {
            sc.setLocalProperty(scopeKey, (new RDDOperationScope(name, this.$lessinit$greater$default$2(), this.$lessinit$greater$default$3())).toJson());
         } else if (sc.getLocalProperty(noOverrideKey) == null) {
            sc.setLocalProperty(scopeKey, (new RDDOperationScope(name, oldScope, this.$lessinit$greater$default$3())).toJson());
         }

         if (!allowNesting) {
            sc.setLocalProperty(noOverrideKey, "true");
         }

         var10000 = body.apply();
      } finally {
         sc.setLocalProperty(scopeKey, oldScopeJson);
         sc.setLocalProperty(noOverrideKey, oldNoOverride);
      }

      return var10000;
   }

   public boolean withScope$default$2() {
      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$withScope$1(final String ourMethodName$1, final StackTraceElement x$2) {
      boolean var3;
      label23: {
         String var10000 = x$2.getMethodName();
         if (var10000 == null) {
            if (ourMethodName$1 != null) {
               break label23;
            }
         } else if (!var10000.equals(ourMethodName$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$withScope$2(final String ourMethodName$1, final StackTraceElement x$3) {
      boolean var3;
      label23: {
         String var10000 = x$3.getMethodName();
         if (var10000 == null) {
            if (ourMethodName$1 != null) {
               break label23;
            }
         } else if (!var10000.equals(ourMethodName$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private RDDOperationScope$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

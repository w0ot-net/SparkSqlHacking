package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.commons.lang3.SystemUtils;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.immutable.;
import scala.collection.mutable.HashMap;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;
import sun.misc.Signal;

public final class SignalUtils$ implements Logging {
   public static final SignalUtils$ MODULE$ = new SignalUtils$();
   private static boolean loggerRegistered;
   private static final HashMap handlers;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      loggerRegistered = false;
      handlers = new HashMap();
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

   private boolean loggerRegistered() {
      return loggerRegistered;
   }

   private void loggerRegistered_$eq(final boolean x$1) {
      loggerRegistered = x$1;
   }

   public synchronized void registerLogger(final Logger log) {
      if (!this.loggerRegistered()) {
         (new .colon.colon("TERM", new .colon.colon("HUP", new .colon.colon("INT", scala.collection.immutable.Nil..MODULE$)))).foreach((sig) -> {
            $anonfun$registerLogger$1(log, sig);
            return BoxedUnit.UNIT;
         });
         this.loggerRegistered_$eq(true);
      }
   }

   public void register(final String signal, final Function0 action) {
      if (SystemUtils.IS_OS_UNIX) {
         this.register(signal, this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to register signal handler for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIGNAL..MODULE$, signal)}))), true, action);
      }
   }

   public synchronized void register(final String signal, final MessageWithContext failMessage, final boolean logStackTrace, final Function0 action) {
      try {
         SignalUtils.ActionHandler handler = (SignalUtils.ActionHandler)this.handlers().getOrElseUpdate(signal, () -> {
            MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registering signal handler for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIGNAL..MODULE$, signal)})))));
            return new SignalUtils.ActionHandler(new Signal(signal));
         });
         handler.register(action);
      } catch (Exception var7) {
         if (logStackTrace) {
            this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> failMessage), var7);
         } else {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> failMessage));
         }
      }

   }

   public boolean register$default$3() {
      return true;
   }

   private HashMap handlers() {
      return handlers;
   }

   // $FF: synthetic method
   public static final void $anonfun$registerLogger$1(final Logger log$1, final String sig) {
      MODULE$.register(sig, (JFunction0.mcZ.sp)() -> {
         log$1.error("RECEIVED SIGNAL " + sig);
         return false;
      });
   }

   private SignalUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

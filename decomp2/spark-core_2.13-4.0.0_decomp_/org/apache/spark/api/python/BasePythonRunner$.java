package org.apache.spark.api.python;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.file.Path;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;
import scala.util.Success;
import scala.util.Try;

public final class BasePythonRunner$ implements Logging {
   public static final BasePythonRunner$ MODULE$ = new BasePythonRunner$();
   private static File faultHandlerLogDir;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

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

   private File faultHandlerLogDir$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            String x$1 = "faulthandler";
            String x$2 = Utils$.MODULE$.createTempDir$default$1();
            faultHandlerLogDir = Utils$.MODULE$.createTempDir(x$2, "faulthandler");
            bitmap$0 = true;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return faultHandlerLogDir;
   }

   public File faultHandlerLogDir() {
      return !bitmap$0 ? this.faultHandlerLogDir$lzycompute() : faultHandlerLogDir;
   }

   public Path faultHandlerLogPath(final int pid) {
      return (new File(this.faultHandlerLogDir(), Integer.toString(pid))).toPath();
   }

   public MessageWithContext pythonWorkerStatusMessageWithContext(final Option handle, final PythonWorker worker, final boolean hasInputs) {
      MessageWithContext var10000 = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"handle.map(_.isAlive) = "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_IS_ALIVE..MODULE$, handle.map((x$1) -> BoxesRunTime.boxToBoolean($anonfun$pythonWorkerStatusMessageWithContext$1(x$1))))})))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"channel.isConnected = "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_CHANNEL_IS_CONNECTED..MODULE$, BoxesRunTime.boxToBoolean(worker.channel().isConnected()))})))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"channel.isBlocking = "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_CHANNEL_IS_BLOCKING_MODE..MODULE$, BoxesRunTime.boxToBoolean(worker.channel().isBlocking()))}))));
      MessageWithContext var8;
      if (!worker.channel().isBlocking()) {
         var8 = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"selector.isOpen = "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_SELECTOR_IS_OPEN..MODULE$, BoxesRunTime.boxToBoolean(worker.selector().isOpen()))})))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"selectionKey.isValid = "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_SELECTION_KEY_IS_VALID..MODULE$, BoxesRunTime.boxToBoolean(worker.selectionKey().isValid()))}))));
         Try var5 = scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> worker.selectionKey().interestOps());
         MessageWithContext var10002;
         if (var5 instanceof Success) {
            Success var6 = (Success)var5;
            int ops = BoxesRunTime.unboxToInt(var6.value());
            var10002 = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"selectionKey.interestOps = "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_SELECTION_KEY_INTERESTS..MODULE$, BoxesRunTime.boxToInteger(ops))}))));
         } else {
            var10002 = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$);
         }

         var8 = var8.$plus(var10002);
      } else {
         var8 = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$);
      }

      return var10000.$plus(var8).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"hasInputs = ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_HAS_INPUTS..MODULE$, BoxesRunTime.boxToBoolean(hasInputs))}))));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$pythonWorkerStatusMessageWithContext$1(final ProcessHandle x$1) {
      return x$1.isAlive();
   }

   private BasePythonRunner$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

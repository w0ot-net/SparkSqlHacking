package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkContext;
import org.apache.spark.api.plugin.DriverPlugin;
import org.apache.spark.api.plugin.PluginContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.SparkExitCode$;
import org.apache.spark.util.ThreadUtils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005i3AAB\u0004\u0001!!)q\u0005\u0001C\u0001Q!91\u0006\u0001b\u0001\n\u0013a\u0003BB\u001b\u0001A\u0003%Q\u0006C\u00037\u0001\u0011\u0005s\u0007C\u0003U\u0001\u0011\u0005SKA\rEe&4XM\u001d+j[\u0016|W\u000f\u001e#sSZ,'\u000f\u00157vO&t'B\u0001\u0005\n\u0003\u0019!W\r\u001d7ps*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001\t\u0012$\t\t\u0003%]i\u0011a\u0005\u0006\u0003)U\tA\u0001\\1oO*\ta#\u0001\u0003kCZ\f\u0017B\u0001\r\u0014\u0005\u0019y%M[3diB\u0011!dH\u0007\u00027)\u0011A$H\u0001\u0007a2,x-\u001b8\u000b\u0005yI\u0011aA1qS&\u0011\u0001e\u0007\u0002\r\tJLg/\u001a:QYV<\u0017N\u001c\t\u0003E\u0015j\u0011a\t\u0006\u0003I%\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003M\r\u0012q\u0001T8hO&tw-\u0001\u0004=S:LGO\u0010\u000b\u0002SA\u0011!\u0006A\u0007\u0002\u000f\u0005qA/[7f_V$8+\u001a:wS\u000e,W#A\u0017\u0011\u00059\u001aT\"A\u0018\u000b\u0005A\n\u0014AC2p]\u000e,(O]3oi*\u0011!'F\u0001\u0005kRLG.\u0003\u00025_\tA2k\u00195fIVdW\rZ#yK\u000e,Ho\u001c:TKJ4\u0018nY3\u0002\u001fQLW.Z8viN+'O^5dK\u0002\nA!\u001b8jiR\u0019\u0001(S(\u0011\teRD\bP\u0007\u0002c%\u00111(\r\u0002\u0004\u001b\u0006\u0004\bCA\u001fG\u001d\tqD\t\u0005\u0002@\u00056\t\u0001I\u0003\u0002B\u001f\u00051AH]8pizR\u0011aQ\u0001\u0006g\u000e\fG.Y\u0005\u0003\u000b\n\u000ba\u0001\u0015:fI\u00164\u0017BA$I\u0005\u0019\u0019FO]5oO*\u0011QI\u0011\u0005\u0006\u0015\u0012\u0001\raS\u0001\u0003g\u000e\u0004\"\u0001T'\u000e\u0003%I!AT\u0005\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\u000bA#\u0001\u0019A)\u0002\u0007\r$\b\u0010\u0005\u0002\u001b%&\u00111k\u0007\u0002\u000e!2,x-\u001b8D_:$X\r\u001f;\u0002\u0011MDW\u000f\u001e3po:$\u0012A\u0016\t\u0003/bk\u0011AQ\u0005\u00033\n\u0013A!\u00168ji\u0002"
)
public class DriverTimeoutDriverPlugin implements DriverPlugin, Logging {
   private final ScheduledExecutorService timeoutService;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   public void registerMetrics(final String appId, final PluginContext pluginContext) {
      DriverPlugin.super.registerMetrics(appId, pluginContext);
   }

   public Object receive(final Object message) {
      return DriverPlugin.super.receive(message);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private ScheduledExecutorService timeoutService() {
      return this.timeoutService;
   }

   public Map init(final SparkContext sc, final PluginContext ctx) {
      long timeout = BoxesRunTime.unboxToLong(sc.conf().get(package$.MODULE$.DRIVER_TIMEOUT()));
      if (timeout == 0L) {
         this.logWarning((Function0)(() -> "Disabled with the timeout value 0."));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         Runnable task = () -> {
            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Terminate Driver JVM because it runs after "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " minute"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, BoxesRunTime.boxToLong(timeout))})))).$plus(timeout == 1L ? this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$) : this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"s"})))).log(scala.collection.immutable.Nil..MODULE$))));
            System.exit(SparkExitCode$.MODULE$.DRIVER_TIMEOUT());
         };
         this.timeoutService().schedule(task, timeout, TimeUnit.MINUTES);
      }

      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(scala.Predef..MODULE$.Map().empty()).asJava();
   }

   public void shutdown() {
      this.timeoutService().shutdown();
   }

   public DriverTimeoutDriverPlugin() {
      Logging.$init$(this);
      this.timeoutService = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("driver-timeout");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

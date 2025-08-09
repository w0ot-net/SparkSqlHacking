package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.Logging.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.ShutdownHookManager$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.StringContext;
import scala.runtime.java8.JFunction0;

public final class ExternalShuffleService$ implements Logging {
   public static final ExternalShuffleService$ MODULE$ = new ExternalShuffleService$();
   private static volatile ExternalShuffleService server;
   private static final CountDownLatch barrier;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      barrier = new CountDownLatch(1);
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

   private ExternalShuffleService server() {
      return server;
   }

   private void server_$eq(final ExternalShuffleService x$1) {
      server = x$1;
   }

   private CountDownLatch barrier() {
      return barrier;
   }

   public void main(final String[] args) {
      this.main(args, (conf, sm) -> new ExternalShuffleService(conf, sm));
   }

   public void main(final String[] args, final Function2 newShuffleService) {
      org.apache.spark.util.Utils$.MODULE$.resetStructuredLogging();
      org.apache.spark.util.Utils$.MODULE$.initDaemon(this.log());
      SparkConf sparkConf = new SparkConf();
      org.apache.spark.util.Utils$.MODULE$.loadDefaultSparkProperties(sparkConf, org.apache.spark.util.Utils$.MODULE$.loadDefaultSparkProperties$default$2());
      org.apache.spark.util.Utils$.MODULE$.resetStructuredLogging(sparkConf);
      .MODULE$.uninitialize();
      SecurityManager securityManager = new SecurityManager(sparkConf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3());
      sparkConf.set(package$.MODULE$.SHUFFLE_SERVICE_ENABLED().key(), "true");
      this.server_$eq((ExternalShuffleService)newShuffleService.apply(sparkConf, securityManager));
      this.server().start();
      this.logDebug((Function0)(() -> "Adding shutdown hook"));
      ShutdownHookManager$.MODULE$.addShutdownHook((JFunction0.mcV.sp)() -> {
         MODULE$.logInfo((Function0)(() -> "Shutting down shuffle service."));
         MODULE$.server().stop();
         MODULE$.barrier().countDown();
      });
      this.barrier().await();
   }

   private ExternalShuffleService$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.hadoop.hive.common.ServerUtils;
import org.apache.hive.service.server.HiveServer2;
import org.apache.spark.SparkContext;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.hive.client.HiveClientImpl;
import org.apache.spark.sql.hive.thriftserver.ui.HiveThriftServer2AppStatusStore;
import org.apache.spark.sql.hive.thriftserver.ui.HiveThriftServer2EventManager;
import org.apache.spark.sql.hive.thriftserver.ui.HiveThriftServer2Listener;
import org.apache.spark.sql.hive.thriftserver.ui.HiveThriftServer2Listener$;
import org.apache.spark.sql.hive.thriftserver.ui.ThriftServerTab;
import org.apache.spark.sql.hive.thriftserver.ui.ThriftServerTab$;
import org.apache.spark.status.ElementTrackingStore;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class HiveThriftServer2$ implements Logging {
   public static final HiveThriftServer2$ MODULE$ = new HiveThriftServer2$();
   private static Option uiTab;
   private static HiveThriftServer2Listener listener;
   private static HiveThriftServer2EventManager eventManager;
   private static final AtomicBoolean systemExitOnError;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      uiTab = .MODULE$;
      systemExitOnError = new AtomicBoolean(true);
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

   public Option uiTab() {
      return uiTab;
   }

   public void uiTab_$eq(final Option x$1) {
      uiTab = x$1;
   }

   public HiveThriftServer2Listener listener() {
      return listener;
   }

   public void listener_$eq(final HiveThriftServer2Listener x$1) {
      listener = x$1;
   }

   public HiveThriftServer2EventManager eventManager() {
      return eventManager;
   }

   public void eventManager_$eq(final HiveThriftServer2EventManager x$1) {
      eventManager = x$1;
   }

   public AtomicBoolean systemExitOnError() {
      return systemExitOnError;
   }

   @DeveloperApi
   public HiveThriftServer2 startWithSparkSession(final SparkSession sparkSession, final boolean exitOnError) {
      this.systemExitOnError().set(exitOnError);
      HiveClientImpl executionHive = org.apache.spark.sql.hive.HiveUtils..MODULE$.newClientForExecution(sparkSession.sparkContext().conf(), sparkSession.sessionState().newHadoopConf());
      ServerUtils.cleanUpScratchDir(executionHive.conf());
      HiveThriftServer2 server = new HiveThriftServer2(sparkSession);
      server.init(executionHive.conf());
      server.start();
      this.logInfo((Function0)(() -> "HiveThriftServer2 started"));
      this.createListenerAndUI(server, sparkSession.sparkContext());
      return server;
   }

   /** @deprecated */
   @DeveloperApi
   public HiveThriftServer2 startWithContext(final SQLContext sqlContext) {
      return this.startWithSparkSession(sqlContext.sparkSession(), true);
   }

   private void createListenerAndUI(final HiveThriftServer2 server, final SparkContext sc) {
      ElementTrackingStore kvStore = (ElementTrackingStore)sc.statusStore().store();
      this.eventManager_$eq(new HiveThriftServer2EventManager(sc));
      this.listener_$eq(new HiveThriftServer2Listener(kvStore, sc.conf(), new Some(server), HiveThriftServer2Listener$.MODULE$.$lessinit$greater$default$4()));
      sc.listenerBus().addToStatusQueue(this.listener());
      this.uiTab_$eq((Option)(BoxesRunTime.unboxToBoolean(sc.getConf().get(org.apache.spark.internal.config.UI..MODULE$.UI_ENABLED())) ? new Some(new ThriftServerTab(new HiveThriftServer2AppStatusStore(kvStore), ThriftServerTab$.MODULE$.getSparkUI(sc))) : .MODULE$));
   }

   public void main(final String[] args) {
      if (!scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])args), "-h") && !scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])args), "--help")) {
         org.apache.spark.util.Utils..MODULE$.initDaemon(this.log());
         HiveServer2.ServerOptionsProcessor optionsProcessor = new HiveServer2.ServerOptionsProcessor("HiveThriftServer2");
         optionsProcessor.parse(args);
         this.logInfo((Function0)(() -> "Starting SparkContext"));
         SparkSQLEnv$.MODULE$.init();
         org.apache.spark.util.ShutdownHookManager..MODULE$.addShutdownHook((JFunction0.mcV.sp)() -> {
            SparkSQLEnv$.MODULE$.stop(SparkSQLEnv$.MODULE$.stop$default$1());
            MODULE$.uiTab().foreach((x$3) -> {
               $anonfun$main$3(x$3);
               return BoxedUnit.UNIT;
            });
         });

         try {
            this.startWithContext(SparkSQLEnv$.MODULE$.sparkSession().sqlContext());
            if (SparkSQLEnv$.MODULE$.sparkContext().stopped().get()) {
               this.logError((Function0)(() -> "SparkContext has stopped even if HiveServer2 has started, so exit"));
               System.exit(-1);
            }
         } catch (Exception var4) {
            this.logError((Function0)(() -> "Error starting HiveThriftServer2"), var4);
            System.exit(-1);
         }

      } else {
         HiveServer2.main(args);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$main$3(final ThriftServerTab x$3) {
      x$3.detach();
   }

   private HiveThriftServer2$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

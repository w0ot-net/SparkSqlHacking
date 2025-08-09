package org.apache.spark.sql.hive.thriftserver;

import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.hive.HiveExternalCatalog;
import org.apache.spark.sql.hive.client.HiveClient;
import org.apache.spark.util.Utils.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class SparkSQLEnv$ implements Logging {
   public static final SparkSQLEnv$ MODULE$ = new SparkSQLEnv$();
   private static SparkSession sparkSession;
   private static SparkContext sparkContext;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      MODULE$.logDebug((Function0)(() -> "Initializing SparkSQLEnv"));
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

   public SparkSession sparkSession() {
      return sparkSession;
   }

   public void sparkSession_$eq(final SparkSession x$1) {
      sparkSession = x$1;
   }

   public SparkContext sparkContext() {
      return sparkContext;
   }

   public void sparkContext_$eq(final SparkContext x$1) {
      sparkContext = x$1;
   }

   public void init() {
      if (this.sparkSession() == null) {
         SparkConf sparkConf = new SparkConf(true);
         Option maybeAppName = sparkConf.getOption("spark.app.name").filterNot((x$3) -> BoxesRunTime.boxToBoolean($anonfun$init$1(x$3))).filterNot((x$4) -> BoxesRunTime.boxToBoolean($anonfun$init$2(x$4)));
         sparkConf.setAppName((String)maybeAppName.getOrElse(() -> "SparkSQL::" + .MODULE$.localHostName())).set(org.apache.spark.sql.internal.SQLConf..MODULE$.DATETIME_JAVA8API_ENABLED(), BoxesRunTime.boxToBoolean(true));
         boolean shouldUseInMemoryCatalog = sparkConf.getOption(org.apache.spark.sql.internal.StaticSQLConf..MODULE$.CATALOG_IMPLEMENTATION().key()).contains("in-memory");
         SparkSession.Builder builder = org.apache.spark.sql.SparkSession..MODULE$.builder().config(sparkConf).config(org.apache.spark.sql.hive.HiveUtils..MODULE$.BUILTIN_HIVE_VERSION().key(), org.apache.spark.sql.hive.HiveUtils..MODULE$.builtinHiveVersion());
         if (!shouldUseInMemoryCatalog) {
            builder.enableHiveSupport();
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.sparkSession_$eq(builder.getOrCreate());
         this.sparkContext_$eq(this.sparkSession().sparkContext());
         this.sparkSession().sessionState();
         if (!shouldUseInMemoryCatalog) {
            HiveClient metadataHive = ((HiveExternalCatalog)this.sparkSession().sharedState().externalCatalog().unwrapped()).client();
            metadataHive.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
            metadataHive.setInfo(new PrintStream(System.err, true, StandardCharsets.UTF_8.name()));
            metadataHive.setError(new PrintStream(System.err, true, StandardCharsets.UTF_8.name()));
         }
      }
   }

   public void stop(final int exitCode) {
      this.logDebug((Function0)(() -> "Shutting down Spark SQL Environment"));
      if (this.sparkContext() != null) {
         this.sparkContext().stop(exitCode);
         this.sparkContext_$eq((SparkContext)null);
         this.sparkSession_$eq((SparkSession)null);
      }
   }

   public int stop$default$1() {
      return 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$init$1(final String x$3) {
      boolean var10000;
      label23: {
         String var1 = SparkSQLCLIDriver.class.getName();
         if (x$3 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (x$3.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$init$2(final String x$4) {
      boolean var10000;
      label23: {
         String var1 = HiveThriftServer2.class.getName();
         if (x$4 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (x$4.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   private SparkSQLEnv$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

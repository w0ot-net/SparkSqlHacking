package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.spark.network.util.ByteUnit;
import scala.math.Ordering.String.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class History$ {
   public static final History$ MODULE$ = new History$();
   private static final String DEFAULT_LOG_DIR = "file:/tmp/spark-events";
   private static final ConfigEntry HISTORY_LOG_DIR;
   private static final ConfigEntry SAFEMODE_CHECK_INTERVAL_S;
   private static final ConfigEntry UPDATE_INTERVAL_S;
   private static final ConfigEntry UPDATE_BATCHSIZE;
   private static final ConfigEntry CLEANER_ENABLED;
   private static final ConfigEntry CLEANER_INTERVAL_S;
   private static final ConfigEntry MAX_LOG_AGE_S;
   private static final ConfigEntry MAX_LOG_NUM;
   private static final OptionalConfigEntry LOCAL_STORE_DIR;
   private static final ConfigEntry LOCAL_STORE_SERIALIZER;
   private static final ConfigEntry MAX_LOCAL_DISK_USAGE;
   private static final ConfigEntry HISTORY_SERVER_UI_TITLE;
   private static final ConfigEntry HISTORY_SERVER_UI_PORT;
   private static final ConfigEntry FAST_IN_PROGRESS_PARSING;
   private static final ConfigEntry END_EVENT_REPARSE_CHUNK_SIZE;
   private static final ConfigEntry EVENT_LOG_ROLLING_MAX_FILES_TO_RETAIN;
   private static final ConfigEntry EVENT_LOG_COMPACTION_SCORE_THRESHOLD;
   private static final ConfigEntry DRIVER_LOG_CLEANER_ENABLED;
   private static final ConfigEntry MAX_DRIVER_LOG_AGE_S;
   private static final ConfigEntry DRIVER_LOG_CLEANER_INTERVAL;
   private static final ConfigEntry HISTORY_SERVER_UI_ACLS_ENABLE;
   private static final ConfigEntry HISTORY_SERVER_UI_ADMIN_ACLS;
   private static final ConfigEntry HISTORY_SERVER_UI_ADMIN_ACLS_GROUPS;
   private static final ConfigEntry HISTORY_UI_MAX_APPS;
   private static final ConfigEntry NUM_REPLAY_THREADS;
   private static final ConfigEntry RETAINED_APPLICATIONS;
   private static final ConfigEntry PROVIDER;
   private static final ConfigEntry KERBEROS_ENABLED;
   private static final OptionalConfigEntry KERBEROS_PRINCIPAL;
   private static final OptionalConfigEntry KERBEROS_KEYTAB;
   private static final OptionalConfigEntry CUSTOM_EXECUTOR_LOG_URL;
   private static final ConfigEntry APPLY_CUSTOM_EXECUTOR_LOG_URL_TO_INCOMPLETE_APP;
   private static final ConfigEntry HYBRID_STORE_ENABLED;
   private static final ConfigEntry MAX_IN_MEMORY_STORE_USAGE;
   private static final ConfigEntry HYBRID_STORE_DISK_BACKEND;

   static {
      HISTORY_LOG_DIR = (new ConfigBuilder("spark.history.fs.logDirectory")).version("1.1.0").doc("Directory where app logs are stored").stringConf().createWithDefault(MODULE$.DEFAULT_LOG_DIR());
      SAFEMODE_CHECK_INTERVAL_S = (new ConfigBuilder("spark.history.fs.safemodeCheck.interval")).version("1.6.0").doc("Interval between HDFS safemode checks for the event log directory").timeConf(TimeUnit.SECONDS).createWithDefaultString("5s");
      UPDATE_INTERVAL_S = (new ConfigBuilder("spark.history.fs.update.interval")).version("1.4.0").doc("How often(in seconds) to reload log data from storage").timeConf(TimeUnit.SECONDS).createWithDefaultString("10s");
      UPDATE_BATCHSIZE = (new ConfigBuilder("spark.history.fs.update.batchSize")).doc("Specifies the batch size for updating new eventlog files. This controls each scan process to be completed within a reasonable time, and such prevent the initial scan from running too long and blocking new eventlog files to be scanned in time in large environments.").version("3.4.0").intConf().checkValue((JFunction1.mcZI.sp)(v) -> v > 0, "The update batchSize should be a positive integer.").createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      CLEANER_ENABLED = (new ConfigBuilder("spark.history.fs.cleaner.enabled")).version("1.4.0").doc("Whether the History Server should periodically clean up event logs from storage").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      CLEANER_INTERVAL_S = (new ConfigBuilder("spark.history.fs.cleaner.interval")).version("1.4.0").doc("When spark.history.fs.cleaner.enabled=true, specifies how often the filesystem job history cleaner checks for files to delete.").timeConf(TimeUnit.SECONDS).createWithDefaultString("1d");
      MAX_LOG_AGE_S = (new ConfigBuilder("spark.history.fs.cleaner.maxAge")).version("1.4.0").doc("When spark.history.fs.cleaner.enabled=true, history files older than this will be deleted when the filesystem history cleaner runs.").timeConf(TimeUnit.SECONDS).createWithDefaultString("7d");
      MAX_LOG_NUM = (new ConfigBuilder("spark.history.fs.cleaner.maxNum")).doc("The maximum number of log files in the event log directory.").version("3.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      LOCAL_STORE_DIR = (new ConfigBuilder("spark.history.store.path")).doc("Local directory where to cache application history information. By default this is not set, meaning all history information will be kept in memory.").version("2.3.0").stringConf().createOptional();
      LOCAL_STORE_SERIALIZER = (new ConfigBuilder("spark.history.store.serializer")).doc("Serializer for writing/reading in-memory UI objects to/from disk-based KV Store; JSON or PROTOBUF. JSON serializer is the only choice before Spark 3.4.0, thus it is the default value. PROTOBUF serializer is fast and compact, and it is the default serializer for disk-based KV store of live UI.").version("3.4.0").stringConf().transform((x$1) -> x$1.toUpperCase(Locale.ROOT)).checkValues(History.LocalStoreSerializer$.MODULE$.values().map((x$2) -> x$2.toString(), .MODULE$)).createWithDefault(History.LocalStoreSerializer$.MODULE$.JSON().toString());
      MAX_LOCAL_DISK_USAGE = (new ConfigBuilder("spark.history.store.maxDiskUsage")).version("2.3.0").doc("Maximum disk usage for the local directory where the cache application history information are stored.").bytesConf(ByteUnit.BYTE).createWithDefaultString("10g");
      HISTORY_SERVER_UI_TITLE = (new ConfigBuilder("spark.history.ui.title")).version("4.0.0").doc("Specifies the title of the History Server UI page.").stringConf().createWithDefault("History Server");
      HISTORY_SERVER_UI_PORT = (new ConfigBuilder("spark.history.ui.port")).doc("Web UI port to bind Spark History Server").version("1.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(18080));
      FAST_IN_PROGRESS_PARSING = (new ConfigBuilder("spark.history.fs.inProgressOptimization.enabled")).doc("Enable optimized handling of in-progress logs. This option may leave finished applications that fail to rename their event logs listed as in-progress.").version("2.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      END_EVENT_REPARSE_CHUNK_SIZE = (new ConfigBuilder("spark.history.fs.endEventReparseChunkSize")).doc("How many bytes to parse at the end of log files looking for the end event. This is used to speed up generation of application listings by skipping unnecessary parts of event log files. It can be disabled by setting this config to 0.").version("2.4.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("1m");
      EVENT_LOG_ROLLING_MAX_FILES_TO_RETAIN = (new ConfigBuilder("spark.history.fs.eventLog.rolling.maxFilesToRetain")).doc("The maximum number of event log files which will be retained as non-compacted. By default, all event log files will be retained. Please set the configuration and " + package$.MODULE$.EVENT_LOG_ROLLING_MAX_FILE_SIZE().key() + " accordingly if you want to control the overall size of event log files.").version("3.0.0").intConf().checkValue((JFunction1.mcZI.sp)(x$3) -> x$3 > 0, "Max event log files to retain should be higher than 0.").createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      EVENT_LOG_COMPACTION_SCORE_THRESHOLD = (new ConfigBuilder("spark.history.fs.eventLog.rolling.compaction.score.threshold")).doc("The threshold score to determine whether it's good to do the compaction or not. The compaction score is calculated in analyzing, and being compared to this value. Compaction will proceed only when the score is higher than the threshold value.").version("3.0.0").internal().doubleConf().createWithDefault(BoxesRunTime.boxToDouble(0.7));
      DRIVER_LOG_CLEANER_ENABLED = (new ConfigBuilder("spark.history.fs.driverlog.cleaner.enabled")).version("3.0.0").doc("Specifies whether the History Server should periodically clean up driver logs from storage.").fallbackConf(MODULE$.CLEANER_ENABLED());
      MAX_DRIVER_LOG_AGE_S = (new ConfigBuilder("spark.history.fs.driverlog.cleaner.maxAge")).version("3.0.0").doc("When " + MODULE$.DRIVER_LOG_CLEANER_ENABLED().key() + "=true, driver log files older than this will be deleted when the driver log cleaner runs.").fallbackConf(MODULE$.MAX_LOG_AGE_S());
      ConfigBuilder var10000 = (new ConfigBuilder("spark.history.fs.driverlog.cleaner.interval")).version("3.0.0");
      String var10001 = MODULE$.DRIVER_LOG_CLEANER_ENABLED().key();
      DRIVER_LOG_CLEANER_INTERVAL = var10000.doc(" When " + var10001 + "=true, specifies how often the filesystem driver log cleaner checks for files to delete. Files are only deleted if they are older than " + MODULE$.MAX_DRIVER_LOG_AGE_S().key() + ".").fallbackConf(MODULE$.CLEANER_INTERVAL_S());
      HISTORY_SERVER_UI_ACLS_ENABLE = (new ConfigBuilder("spark.history.ui.acls.enable")).version("1.0.1").doc("Specifies whether ACLs should be checked to authorize users viewing the applications in the history server. If enabled, access control checks are performed regardless of what the individual applications had set for spark.ui.acls.enable. The application owner will always have authorization to view their own application and any users specified via spark.ui.view.acls and groups specified via spark.ui.view.acls.groups when the application was run will also have authorization to view that application. If disabled, no access control checks are made for any application UIs available through the history server.").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      HISTORY_SERVER_UI_ADMIN_ACLS = (new ConfigBuilder("spark.history.ui.admin.acls")).version("2.1.1").doc("Comma separated list of users that have view access to all the Spark applications in history server.").stringConf().toSequence().createWithDefault(scala.collection.immutable.Nil..MODULE$);
      HISTORY_SERVER_UI_ADMIN_ACLS_GROUPS = (new ConfigBuilder("spark.history.ui.admin.acls.groups")).version("2.1.1").doc("Comma separated list of groups that have view access to all the Spark applications in history server.").stringConf().toSequence().createWithDefault(scala.collection.immutable.Nil..MODULE$);
      HISTORY_UI_MAX_APPS = (new ConfigBuilder("spark.history.ui.maxApplications")).version("2.0.1").doc("The number of applications to display on the history summary page. Application UIs are still available by accessing their URLs directly even if they are not displayed on the history summary page.").intConf().createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      NUM_REPLAY_THREADS = (new ConfigBuilder("spark.history.fs.numReplayThreads")).version("2.0.0").doc("Number of threads that will be used by history server to process event logs.").intConf().createWithDefaultFunction((JFunction0.mcI.sp)() -> (int)Math.ceil((double)((float)Runtime.getRuntime().availableProcessors() / 4.0F)));
      RETAINED_APPLICATIONS = (new ConfigBuilder("spark.history.retainedApplications")).version("1.0.0").doc("The number of applications to retain UI data for in the cache. If this cap is exceeded, then the oldest applications will be removed from the cache. If an application is not in the cache, it will have to be loaded from disk if it is accessed from the UI.").intConf().createWithDefault(BoxesRunTime.boxToInteger(50));
      PROVIDER = (new ConfigBuilder("spark.history.provider")).version("1.1.0").doc("Name of the class implementing the application history backend.").stringConf().createWithDefault("org.apache.spark.deploy.history.FsHistoryProvider");
      KERBEROS_ENABLED = (new ConfigBuilder("spark.history.kerberos.enabled")).version("1.0.1").doc("Indicates whether the history server should use kerberos to login. This is required if the history server is accessing HDFS files on a secure Hadoop cluster.").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      KERBEROS_PRINCIPAL = (new ConfigBuilder("spark.history.kerberos.principal")).version("1.0.1").doc("When " + MODULE$.KERBEROS_ENABLED().key() + "=true, specifies kerberos principal name for  the History Server.").stringConf().createOptional();
      KERBEROS_KEYTAB = (new ConfigBuilder("spark.history.kerberos.keytab")).version("1.0.1").doc("When " + MODULE$.KERBEROS_ENABLED().key() + "=true, specifies location of the kerberos keytab file for the History Server.").stringConf().createOptional();
      CUSTOM_EXECUTOR_LOG_URL = (new ConfigBuilder("spark.history.custom.executor.log.url")).doc("Specifies custom spark executor log url for supporting external log service instead of using cluster managers' application log urls in the history server. Spark will support some path variables via patterns which can vary on cluster manager. Please check the documentation for your cluster manager to see which patterns are supported, if any. This configuration has no effect on a live application, it only affects the history server.").version("3.0.0").stringConf().createOptional();
      APPLY_CUSTOM_EXECUTOR_LOG_URL_TO_INCOMPLETE_APP = (new ConfigBuilder("spark.history.custom.executor.log.url.applyIncompleteApplication")).doc("Whether to apply custom executor log url, as specified by " + MODULE$.CUSTOM_EXECUTOR_LOG_URL().key() + ", to incomplete application as well. Even if this is true, this still only affects the behavior of the history server, not running spark applications.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      HYBRID_STORE_ENABLED = (new ConfigBuilder("spark.history.store.hybridStore.enabled")).doc("Whether to use HybridStore as the store when parsing event logs. HybridStore will first write data to an in-memory store and having a background thread that dumps data to a disk store after the writing to in-memory store is completed.").version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      MAX_IN_MEMORY_STORE_USAGE = (new ConfigBuilder("spark.history.store.hybridStore.maxMemoryUsage")).doc("Maximum memory space that can be used to create HybridStore. The HybridStore co-uses the heap memory, so the heap memory should be increased through the memory option for SHS if the HybridStore is enabled.").version("3.1.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("2g");
      HYBRID_STORE_DISK_BACKEND = (new ConfigBuilder("spark.history.store.hybridStore.diskBackend")).doc("Specifies a disk-based store used in hybrid store; ROCKSDB or LEVELDB (deprecated).").version("3.3.0").stringConf().transform((x$4) -> x$4.toUpperCase(Locale.ROOT)).checkValues(History.HybridStoreDiskBackend$.MODULE$.values().map((x$5) -> x$5.toString(), .MODULE$)).createWithDefault(History.HybridStoreDiskBackend$.MODULE$.ROCKSDB().toString());
   }

   public String DEFAULT_LOG_DIR() {
      return DEFAULT_LOG_DIR;
   }

   public ConfigEntry HISTORY_LOG_DIR() {
      return HISTORY_LOG_DIR;
   }

   public ConfigEntry SAFEMODE_CHECK_INTERVAL_S() {
      return SAFEMODE_CHECK_INTERVAL_S;
   }

   public ConfigEntry UPDATE_INTERVAL_S() {
      return UPDATE_INTERVAL_S;
   }

   public ConfigEntry UPDATE_BATCHSIZE() {
      return UPDATE_BATCHSIZE;
   }

   public ConfigEntry CLEANER_ENABLED() {
      return CLEANER_ENABLED;
   }

   public ConfigEntry CLEANER_INTERVAL_S() {
      return CLEANER_INTERVAL_S;
   }

   public ConfigEntry MAX_LOG_AGE_S() {
      return MAX_LOG_AGE_S;
   }

   public ConfigEntry MAX_LOG_NUM() {
      return MAX_LOG_NUM;
   }

   public OptionalConfigEntry LOCAL_STORE_DIR() {
      return LOCAL_STORE_DIR;
   }

   public ConfigEntry LOCAL_STORE_SERIALIZER() {
      return LOCAL_STORE_SERIALIZER;
   }

   public ConfigEntry MAX_LOCAL_DISK_USAGE() {
      return MAX_LOCAL_DISK_USAGE;
   }

   public ConfigEntry HISTORY_SERVER_UI_TITLE() {
      return HISTORY_SERVER_UI_TITLE;
   }

   public ConfigEntry HISTORY_SERVER_UI_PORT() {
      return HISTORY_SERVER_UI_PORT;
   }

   public ConfigEntry FAST_IN_PROGRESS_PARSING() {
      return FAST_IN_PROGRESS_PARSING;
   }

   public ConfigEntry END_EVENT_REPARSE_CHUNK_SIZE() {
      return END_EVENT_REPARSE_CHUNK_SIZE;
   }

   public ConfigEntry EVENT_LOG_ROLLING_MAX_FILES_TO_RETAIN() {
      return EVENT_LOG_ROLLING_MAX_FILES_TO_RETAIN;
   }

   public ConfigEntry EVENT_LOG_COMPACTION_SCORE_THRESHOLD() {
      return EVENT_LOG_COMPACTION_SCORE_THRESHOLD;
   }

   public ConfigEntry DRIVER_LOG_CLEANER_ENABLED() {
      return DRIVER_LOG_CLEANER_ENABLED;
   }

   public ConfigEntry MAX_DRIVER_LOG_AGE_S() {
      return MAX_DRIVER_LOG_AGE_S;
   }

   public ConfigEntry DRIVER_LOG_CLEANER_INTERVAL() {
      return DRIVER_LOG_CLEANER_INTERVAL;
   }

   public ConfigEntry HISTORY_SERVER_UI_ACLS_ENABLE() {
      return HISTORY_SERVER_UI_ACLS_ENABLE;
   }

   public ConfigEntry HISTORY_SERVER_UI_ADMIN_ACLS() {
      return HISTORY_SERVER_UI_ADMIN_ACLS;
   }

   public ConfigEntry HISTORY_SERVER_UI_ADMIN_ACLS_GROUPS() {
      return HISTORY_SERVER_UI_ADMIN_ACLS_GROUPS;
   }

   public ConfigEntry HISTORY_UI_MAX_APPS() {
      return HISTORY_UI_MAX_APPS;
   }

   public ConfigEntry NUM_REPLAY_THREADS() {
      return NUM_REPLAY_THREADS;
   }

   public ConfigEntry RETAINED_APPLICATIONS() {
      return RETAINED_APPLICATIONS;
   }

   public ConfigEntry PROVIDER() {
      return PROVIDER;
   }

   public ConfigEntry KERBEROS_ENABLED() {
      return KERBEROS_ENABLED;
   }

   public OptionalConfigEntry KERBEROS_PRINCIPAL() {
      return KERBEROS_PRINCIPAL;
   }

   public OptionalConfigEntry KERBEROS_KEYTAB() {
      return KERBEROS_KEYTAB;
   }

   public OptionalConfigEntry CUSTOM_EXECUTOR_LOG_URL() {
      return CUSTOM_EXECUTOR_LOG_URL;
   }

   public ConfigEntry APPLY_CUSTOM_EXECUTOR_LOG_URL_TO_INCOMPLETE_APP() {
      return APPLY_CUSTOM_EXECUTOR_LOG_URL_TO_INCOMPLETE_APP;
   }

   public ConfigEntry HYBRID_STORE_ENABLED() {
      return HYBRID_STORE_ENABLED;
   }

   public ConfigEntry MAX_IN_MEMORY_STORE_USAGE() {
      return MAX_IN_MEMORY_STORE_USAGE;
   }

   public ConfigEntry HYBRID_STORE_DISK_BACKEND() {
      return HYBRID_STORE_DISK_BACKEND;
   }

   private History$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

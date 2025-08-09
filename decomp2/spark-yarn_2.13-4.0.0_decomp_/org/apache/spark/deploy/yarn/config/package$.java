package org.apache.spark.deploy.yarn.config;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigBuilder;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.network.util.ByteUnit;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.immutable.Nil.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class package$ implements Logging {
   public static final package$ MODULE$ = new package$();
   private static boolean IS_HADOOP_PROVIDED;
   private static final OptionalConfigEntry APPLICATION_TAGS;
   private static final OptionalConfigEntry APPLICATION_PRIORITY;
   private static final OptionalConfigEntry AM_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS;
   private static final ConfigEntry AM_CLIENT_MODE_TREAT_DISCONNECT_AS_FAILED;
   private static final ConfigEntry AM_CLIENT_MODE_EXIT_ON_ERROR;
   private static final OptionalConfigEntry AM_TOKEN_CONF_REGEX;
   private static final OptionalConfigEntry MAX_APP_ATTEMPTS;
   private static final ConfigEntry USER_CLASS_PATH_FIRST;
   private static final ConfigEntry POPULATE_HADOOP_CLASSPATH;
   private static final OptionalConfigEntry GATEWAY_ROOT_PATH;
   private static final OptionalConfigEntry REPLACEMENT_ROOT_PATH;
   private static final ConfigEntry QUEUE_NAME;
   private static final OptionalConfigEntry HISTORY_SERVER_ADDRESS;
   private static final ConfigEntry ALLOW_HISTORY_SERVER_TRACKING_URL;
   private static final ConfigEntry APPLICATION_TYPE;
   private static final OptionalConfigEntry SPARK_ARCHIVE;
   private static final OptionalConfigEntry SPARK_JARS;
   private static final ConfigEntry ARCHIVES_TO_DISTRIBUTE;
   private static final ConfigEntry FILES_TO_DISTRIBUTE;
   private static final ConfigEntry JARS_TO_DISTRIBUTE;
   private static final ConfigEntry PRESERVE_STAGING_FILES;
   private static final OptionalConfigEntry STAGING_FILE_REPLICATION;
   private static final ConfigEntry WAIT_FOR_APP_COMPLETION;
   private static final ConfigEntry REPORT_INTERVAL;
   private static final ConfigEntry REPORT_LOG_FREQUENCY;
   private static final ConfigEntry CLIENT_LAUNCH_MONITOR_INTERVAL;
   private static final ConfigEntry CLIENT_INCLUDE_DRIVER_LOGS_LINK;
   private static final ConfigEntry AM_MAX_WAIT_TIME;
   private static final OptionalConfigEntry YARN_METRICS_NAMESPACE;
   private static final OptionalConfigEntry AM_NODE_LABEL_EXPRESSION;
   private static final ConfigEntry CONTAINER_LAUNCH_MAX_THREADS;
   private static final ConfigEntry MAX_REPORTER_THREAD_FAILURES;
   private static final ConfigEntry RM_HEARTBEAT_INTERVAL;
   private static final ConfigEntry INITIAL_HEARTBEAT_INTERVAL;
   private static final ConfigEntry AM_FINAL_MSG_LIMIT;
   private static final ConfigEntry AM_CORES;
   private static final OptionalConfigEntry AM_JAVA_OPTIONS;
   private static final OptionalConfigEntry AM_LIBRARY_PATH;
   private static final OptionalConfigEntry AM_MEMORY_OVERHEAD;
   private static final ConfigEntry AM_MEMORY;
   private static final OptionalConfigEntry DRIVER_APP_UI_ADDRESS;
   private static final OptionalConfigEntry EXECUTOR_NODE_LABEL_EXPRESSION;
   private static final ConfigEntry YARN_UNMANAGED_AM;
   private static final OptionalConfigEntry ROLLED_LOG_INCLUDE_PATTERN;
   private static final OptionalConfigEntry ROLLED_LOG_EXCLUDE_PATTERN;
   private static final OptionalConfigEntry APP_JAR;
   private static final OptionalConfigEntry SECONDARY_JARS;
   private static final ConfigEntry CACHED_FILES;
   private static final ConfigEntry CACHED_FILES_SIZES;
   private static final ConfigEntry CACHED_FILES_TIMESTAMPS;
   private static final ConfigEntry CACHED_FILES_VISIBILITIES;
   private static final ConfigEntry CACHED_FILES_TYPES;
   private static final OptionalConfigEntry CACHED_CONF_ARCHIVE;
   private static final ConfigEntry YARN_EXECUTOR_LAUNCH_EXCLUDE_ON_FAILURE_ENABLED;
   private static final ConfigEntry YARN_EXCLUDE_NODES;
   private static final ConfigEntry YARN_GPU_DEVICE;
   private static final ConfigEntry YARN_FPGA_DEVICE;
   private static final ConfigEntry YARN_CLIENT_STAT_CACHE_PRELOAD_ENABLED;
   private static final ConfigEntry YARN_CLIENT_STAT_CACHE_PRELOAD_PER_DIRECTORY_THRESHOLD;
   private static final String YARN_EXECUTOR_RESOURCE_TYPES_PREFIX;
   private static final String YARN_DRIVER_RESOURCE_TYPES_PREFIX;
   private static final String YARN_AM_RESOURCE_TYPES_PREFIX;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

   static {
      Logging.$init$(MODULE$);
      APPLICATION_TAGS = (new ConfigBuilder("spark.yarn.tags")).doc("Comma-separated list of strings to pass through as YARN application tags appearing in YARN Application Reports, which can be used for filtering when querying YARN.").version("1.5.0").stringConf().toSequence().createOptional();
      APPLICATION_PRIORITY = (new ConfigBuilder("spark.yarn.priority")).doc("Application priority for YARN to define pending applications ordering policy, those with higher value have a better opportunity to be activated. Currently, YARN only supports application priority when using FIFO ordering policy.").version("3.0.0").intConf().createOptional();
      AM_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS = (new ConfigBuilder("spark.yarn.am.attemptFailuresValidityInterval")).doc("Interval after which AM failures will be considered independent and not accumulate towards the attempt count.").version("1.6.0").timeConf(TimeUnit.MILLISECONDS).createOptional();
      AM_CLIENT_MODE_TREAT_DISCONNECT_AS_FAILED = (new ConfigBuilder("spark.yarn.am.clientModeTreatDisconnectAsFailed")).doc("Treat yarn-client unclean disconnects as failures. In yarn-client mode, normally the application will always finish with a final status of SUCCESS because in some cases, it is not possible to know if the Application was terminated intentionally by the user or if there was a real error. This config changes that behavior such that if the Application Master disconnects from the driver uncleanly (ie without the proper shutdown handshake) the application will terminate with a final status of FAILED. This will allow the caller to decide if it was truly a failure. Note that if this config is set and the user just terminate the client application badly it may show a status of FAILED when it wasn't really FAILED.").version("3.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      AM_CLIENT_MODE_EXIT_ON_ERROR = (new ConfigBuilder("spark.yarn.am.clientModeExitOnError")).doc("In yarn-client mode, when this is true, if driver got application report with final status of KILLED or FAILED, driver will stop corresponding SparkContext and exit program with code 1. Note, if this is true and called from another application, it will terminate the parent application as well.").version("3.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      AM_TOKEN_CONF_REGEX = (new ConfigBuilder("spark.yarn.am.tokenConfRegex")).doc("The value of this config is a regex expression used to grep a list of config entries from the job's configuration file (e.g., hdfs-site.xml) and send to RM, which uses them when renewing delegation tokens. A typical use case of this feature is to support delegation tokens in an environment where a YARN cluster needs to talk to multiple downstream HDFS clusters, where the YARN RM may not have configs (e.g., dfs.nameservices, dfs.ha.namenodes.*, dfs.namenode.rpc-address.*) to connect to these clusters. In this scenario, Spark users can specify the config value to be '^dfs.nameservices$|^dfs.namenode.rpc-address.*$|^dfs.ha.namenodes.*$' to parse these HDFS configs from the job's local configuration files. This config is very similar to 'mapreduce.job.send-token-conf'. Please check YARN-5910 for more details.").version("3.3.0").stringConf().createOptional();
      MAX_APP_ATTEMPTS = (new ConfigBuilder("spark.yarn.maxAppAttempts")).doc("Maximum number of AM attempts before failing the app.").version("1.3.0").intConf().createOptional();
      USER_CLASS_PATH_FIRST = (new ConfigBuilder("spark.yarn.user.classpath.first")).doc("Whether to place user jars in front of Spark's classpath.").version("1.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      POPULATE_HADOOP_CLASSPATH = (new ConfigBuilder("spark.yarn.populateHadoopClasspath")).doc("Whether to populate Hadoop classpath from `yarn.application.classpath` and `mapreduce.application.classpath` Note that if this is set to `false`, it requires a `with-Hadoop` Spark distribution that bundles Hadoop runtime or user has to provide a Hadoop installation separately. By default, for `with-hadoop` Spark distribution, this is set to `false`; for `no-hadoop` distribution, this is set to `true`.").version("2.4.6").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(MODULE$.isHadoopProvided()));
      GATEWAY_ROOT_PATH = (new ConfigBuilder("spark.yarn.config.gatewayPath")).doc("Root of configuration paths that is present on gateway nodes, and will be replaced with the corresponding path in cluster machines.").version("1.5.0").stringConf().createOptional();
      REPLACEMENT_ROOT_PATH = (new ConfigBuilder("spark.yarn.config.replacementPath")).doc("Path to use as a replacement for " + MODULE$.GATEWAY_ROOT_PATH().key() + " when launching processes in the YARN cluster.").version("1.5.0").stringConf().createOptional();
      QUEUE_NAME = (new ConfigBuilder("spark.yarn.queue")).version("1.0.0").stringConf().createWithDefault("default");
      HISTORY_SERVER_ADDRESS = (new ConfigBuilder("spark.yarn.historyServer.address")).version("1.0.0").stringConf().createOptional();
      ALLOW_HISTORY_SERVER_TRACKING_URL = (new ConfigBuilder("spark.yarn.historyServer.allowTracking")).doc("Allow using the History Server URL for the application as the tracking URL for the application when the Web UI is not enabled.").version("2.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      APPLICATION_TYPE = (new ConfigBuilder("spark.yarn.applicationType")).doc("Type of this application,it allows user to specify a more specific type for the application, such as SPARK,SPARK-SQL, SPARK-STREAMING, SPARK-MLLIB and SPARK-GRAPH").version("3.1.0").stringConf().createWithDefault("SPARK");
      SPARK_ARCHIVE = (new ConfigBuilder("spark.yarn.archive")).doc("Location of archive containing jars files with Spark classes.").version("2.0.0").stringConf().createOptional();
      SPARK_JARS = (new ConfigBuilder("spark.yarn.jars")).doc("Location of jars containing Spark classes.").version("2.0.0").stringConf().toSequence().createOptional();
      ARCHIVES_TO_DISTRIBUTE = (new ConfigBuilder("spark.yarn.dist.archives")).version("1.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      FILES_TO_DISTRIBUTE = (new ConfigBuilder("spark.yarn.dist.files")).version("1.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      JARS_TO_DISTRIBUTE = (new ConfigBuilder("spark.yarn.dist.jars")).version("2.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      PRESERVE_STAGING_FILES = (new ConfigBuilder("spark.yarn.preserve.staging.files")).doc("Whether to preserve temporary files created by the job in HDFS.").version("1.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      STAGING_FILE_REPLICATION = (new ConfigBuilder("spark.yarn.submit.file.replication")).doc("Replication factor for files uploaded by Spark to HDFS.").version("0.8.1").intConf().createOptional();
      WAIT_FOR_APP_COMPLETION = (new ConfigBuilder("spark.yarn.submit.waitAppCompletion")).doc("In cluster mode, whether to wait for the application to finish before exiting the launcher process.").version("1.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      REPORT_INTERVAL = (new ConfigBuilder("spark.yarn.report.interval")).doc("Interval between reports of the current app status.").version("0.9.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("1s");
      REPORT_LOG_FREQUENCY = (new ConfigBuilder("spark.yarn.report.loggingFrequency")).doc("Maximum number of application reports processed until the next application status is logged. If there is a change of state, the application status will be logged regardless of the number of application reports processed.").version("3.5.0").intConf().checkValue((JFunction1.mcZI.sp)(x$1) -> x$1 > 0, "logging frequency should be positive").createWithDefault(BoxesRunTime.boxToInteger(30));
      CLIENT_LAUNCH_MONITOR_INTERVAL = (new ConfigBuilder("spark.yarn.clientLaunchMonitorInterval")).doc("Interval between requests for status the client mode AM when starting the app.").version("2.3.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("1s");
      CLIENT_INCLUDE_DRIVER_LOGS_LINK = (new ConfigBuilder("spark.yarn.includeDriverLogsLink")).doc("In cluster mode, whether the client application report includes links to the driver container's logs. This requires polling the ResourceManager's REST API, so it places some additional load on the RM.").version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      AM_MAX_WAIT_TIME = (new ConfigBuilder("spark.yarn.am.waitTime")).version("1.3.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("100s");
      YARN_METRICS_NAMESPACE = (new ConfigBuilder("spark.yarn.metrics.namespace")).doc("The root namespace for AM metrics reporting.").version("2.4.0").stringConf().createOptional();
      AM_NODE_LABEL_EXPRESSION = (new ConfigBuilder("spark.yarn.am.nodeLabelExpression")).doc("Node label expression for the AM.").version("1.6.0").stringConf().createOptional();
      CONTAINER_LAUNCH_MAX_THREADS = (new ConfigBuilder("spark.yarn.containerLauncherMaxThreads")).version("1.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(25));
      MAX_REPORTER_THREAD_FAILURES = (new ConfigBuilder("spark.yarn.scheduler.reporterThread.maxFailures")).version("1.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(5));
      RM_HEARTBEAT_INTERVAL = (new ConfigBuilder("spark.yarn.scheduler.heartbeat.interval-ms")).version("0.8.1").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("3s");
      INITIAL_HEARTBEAT_INTERVAL = (new ConfigBuilder("spark.yarn.scheduler.initial-allocation.interval")).version("1.4.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("200ms");
      AM_FINAL_MSG_LIMIT = (new ConfigBuilder("spark.yarn.am.finalMessageLimit")).doc("The limit size of final diagnostic message for our ApplicationMaster to unregister from the ResourceManager.").version("2.4.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("1m");
      AM_CORES = (new ConfigBuilder("spark.yarn.am.cores")).version("1.3.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1));
      AM_JAVA_OPTIONS = (new ConfigBuilder("spark.yarn.am.extraJavaOptions")).doc("Extra Java options for the client-mode AM.").version("1.3.0").stringConf().createOptional();
      AM_LIBRARY_PATH = (new ConfigBuilder("spark.yarn.am.extraLibraryPath")).doc("Extra native library path for the client-mode AM.").version("1.4.0").stringConf().createOptional();
      AM_MEMORY_OVERHEAD = (new ConfigBuilder("spark.yarn.am.memoryOverhead")).version("1.3.0").bytesConf(ByteUnit.MiB).createOptional();
      AM_MEMORY = (new ConfigBuilder("spark.yarn.am.memory")).version("1.3.0").bytesConf(ByteUnit.MiB).createWithDefaultString("512m");
      DRIVER_APP_UI_ADDRESS = (new ConfigBuilder("spark.driver.appUIAddress")).version("1.1.0").stringConf().createOptional();
      EXECUTOR_NODE_LABEL_EXPRESSION = (new ConfigBuilder("spark.yarn.executor.nodeLabelExpression")).doc("Node label expression for executors.").version("1.4.0").stringConf().createOptional();
      YARN_UNMANAGED_AM = (new ConfigBuilder("spark.yarn.unmanagedAM.enabled")).doc("In client mode, whether to launch the Application Master service as part of the client using unmanaged am.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      ROLLED_LOG_INCLUDE_PATTERN = (new ConfigBuilder("spark.yarn.rolledLog.includePattern")).doc("Java Regex to filter the log files which match the defined include pattern and those log files will be aggregated in a rolling fashion.").version("2.0.0").stringConf().createOptional();
      ROLLED_LOG_EXCLUDE_PATTERN = (new ConfigBuilder("spark.yarn.rolledLog.excludePattern")).doc("Java Regex to filter the log files which match the defined exclude pattern and those log files will not be aggregated in a rolling fashion.").version("2.0.0").stringConf().createOptional();
      APP_JAR = (new ConfigBuilder("spark.yarn.user.jar")).internal().version("1.1.0").stringConf().createOptional();
      SECONDARY_JARS = (new ConfigBuilder("spark.yarn.secondary.jars")).internal().version("0.9.2").stringConf().toSequence().createOptional();
      CACHED_FILES = (new ConfigBuilder("spark.yarn.cache.filenames")).internal().version("2.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      CACHED_FILES_SIZES = (new ConfigBuilder("spark.yarn.cache.sizes")).internal().version("2.0.0").longConf().toSequence().createWithDefault(.MODULE$);
      CACHED_FILES_TIMESTAMPS = (new ConfigBuilder("spark.yarn.cache.timestamps")).internal().version("2.0.0").longConf().toSequence().createWithDefault(.MODULE$);
      CACHED_FILES_VISIBILITIES = (new ConfigBuilder("spark.yarn.cache.visibilities")).internal().version("2.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      CACHED_FILES_TYPES = (new ConfigBuilder("spark.yarn.cache.types")).internal().version("2.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      CACHED_CONF_ARCHIVE = (new ConfigBuilder("spark.yarn.cache.confArchive")).internal().version("2.0.0").stringConf().createOptional();
      YARN_EXECUTOR_LAUNCH_EXCLUDE_ON_FAILURE_ENABLED = (new ConfigBuilder("spark.yarn.executor.launch.excludeOnFailure.enabled")).version("3.1.0").withAlternative("spark.yarn.blacklist.executor.launch.blacklisting.enabled").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      YARN_EXCLUDE_NODES = (new ConfigBuilder("spark.yarn.exclude.nodes")).version("3.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      YARN_GPU_DEVICE = (new ConfigBuilder("spark.yarn.resourceGpuDeviceName")).version("3.2.1").doc("Specify the mapping of the Spark resource type of gpu to the YARN resource representing a GPU. By default YARN uses yarn.io/gpu but if YARN has been configured with a custom resource type, this allows remapping it. Applies when using the <code>spark.{driver/executor}.resource.gpu.*</code> configs.").stringConf().createWithDefault("yarn.io/gpu");
      YARN_FPGA_DEVICE = (new ConfigBuilder("spark.yarn.resourceFpgaDeviceName")).version("3.2.1").doc("Specify the mapping of the Spark resource type of fpga to the YARN resource representing a FPGA. By default YARN uses yarn.io/fpga but if YARN has been configured with a custom resource type, this allows remapping it. Applies when using the <code>spark.{driver/executor}.resource.fpga.*</code> configs.").stringConf().createWithDefault("yarn.io/fpga");
      YARN_CLIENT_STAT_CACHE_PRELOAD_ENABLED = (new ConfigBuilder("spark.yarn.client.statCache.preload.enabled")).doc("Enables statCache to be preloaded at YARN client side. This feature analyzes the pattern of resources paths, and if multiple resources shared the same parent directory, a single <code>listStatus</code> will be invoked on the parent directory instead of multiple <code>getFileStatus</code> on individual resources. If most resources are from a small set of directories, this can improve job submission time. Enabling this feature may potentially increase client memory overhead.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      YARN_CLIENT_STAT_CACHE_PRELOAD_PER_DIRECTORY_THRESHOLD = (new ConfigBuilder("spark.yarn.client.statCache.preload.perDirectoryThreshold")).doc("Minimum resource count in a directory to trigger statCache preloading when submitting an application. If the number of resources in a directory, without any wildcards, equals or exceeds this threshold, the statCache for that directory will be preloaded. This configuration will only take effect when <code>spark.yarn.client.statCache.preloaded.enabled</code> option is enabled.").version("4.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(5));
      YARN_EXECUTOR_RESOURCE_TYPES_PREFIX = "spark.yarn.executor.resource.";
      YARN_DRIVER_RESOURCE_TYPES_PREFIX = "spark.yarn.driver.resource.";
      YARN_AM_RESOURCE_TYPES_PREFIX = "spark.yarn.am.resource.";
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

   public OptionalConfigEntry APPLICATION_TAGS() {
      return APPLICATION_TAGS;
   }

   public OptionalConfigEntry APPLICATION_PRIORITY() {
      return APPLICATION_PRIORITY;
   }

   public OptionalConfigEntry AM_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS() {
      return AM_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS;
   }

   public ConfigEntry AM_CLIENT_MODE_TREAT_DISCONNECT_AS_FAILED() {
      return AM_CLIENT_MODE_TREAT_DISCONNECT_AS_FAILED;
   }

   public ConfigEntry AM_CLIENT_MODE_EXIT_ON_ERROR() {
      return AM_CLIENT_MODE_EXIT_ON_ERROR;
   }

   public OptionalConfigEntry AM_TOKEN_CONF_REGEX() {
      return AM_TOKEN_CONF_REGEX;
   }

   public OptionalConfigEntry MAX_APP_ATTEMPTS() {
      return MAX_APP_ATTEMPTS;
   }

   public ConfigEntry USER_CLASS_PATH_FIRST() {
      return USER_CLASS_PATH_FIRST;
   }

   public ConfigEntry POPULATE_HADOOP_CLASSPATH() {
      return POPULATE_HADOOP_CLASSPATH;
   }

   public OptionalConfigEntry GATEWAY_ROOT_PATH() {
      return GATEWAY_ROOT_PATH;
   }

   public OptionalConfigEntry REPLACEMENT_ROOT_PATH() {
      return REPLACEMENT_ROOT_PATH;
   }

   public ConfigEntry QUEUE_NAME() {
      return QUEUE_NAME;
   }

   public OptionalConfigEntry HISTORY_SERVER_ADDRESS() {
      return HISTORY_SERVER_ADDRESS;
   }

   public ConfigEntry ALLOW_HISTORY_SERVER_TRACKING_URL() {
      return ALLOW_HISTORY_SERVER_TRACKING_URL;
   }

   public ConfigEntry APPLICATION_TYPE() {
      return APPLICATION_TYPE;
   }

   public OptionalConfigEntry SPARK_ARCHIVE() {
      return SPARK_ARCHIVE;
   }

   public OptionalConfigEntry SPARK_JARS() {
      return SPARK_JARS;
   }

   public ConfigEntry ARCHIVES_TO_DISTRIBUTE() {
      return ARCHIVES_TO_DISTRIBUTE;
   }

   public ConfigEntry FILES_TO_DISTRIBUTE() {
      return FILES_TO_DISTRIBUTE;
   }

   public ConfigEntry JARS_TO_DISTRIBUTE() {
      return JARS_TO_DISTRIBUTE;
   }

   public ConfigEntry PRESERVE_STAGING_FILES() {
      return PRESERVE_STAGING_FILES;
   }

   public OptionalConfigEntry STAGING_FILE_REPLICATION() {
      return STAGING_FILE_REPLICATION;
   }

   public ConfigEntry WAIT_FOR_APP_COMPLETION() {
      return WAIT_FOR_APP_COMPLETION;
   }

   public ConfigEntry REPORT_INTERVAL() {
      return REPORT_INTERVAL;
   }

   public ConfigEntry REPORT_LOG_FREQUENCY() {
      return REPORT_LOG_FREQUENCY;
   }

   public ConfigEntry CLIENT_LAUNCH_MONITOR_INTERVAL() {
      return CLIENT_LAUNCH_MONITOR_INTERVAL;
   }

   public ConfigEntry CLIENT_INCLUDE_DRIVER_LOGS_LINK() {
      return CLIENT_INCLUDE_DRIVER_LOGS_LINK;
   }

   public ConfigEntry AM_MAX_WAIT_TIME() {
      return AM_MAX_WAIT_TIME;
   }

   public OptionalConfigEntry YARN_METRICS_NAMESPACE() {
      return YARN_METRICS_NAMESPACE;
   }

   public OptionalConfigEntry AM_NODE_LABEL_EXPRESSION() {
      return AM_NODE_LABEL_EXPRESSION;
   }

   public ConfigEntry CONTAINER_LAUNCH_MAX_THREADS() {
      return CONTAINER_LAUNCH_MAX_THREADS;
   }

   public ConfigEntry MAX_REPORTER_THREAD_FAILURES() {
      return MAX_REPORTER_THREAD_FAILURES;
   }

   public ConfigEntry RM_HEARTBEAT_INTERVAL() {
      return RM_HEARTBEAT_INTERVAL;
   }

   public ConfigEntry INITIAL_HEARTBEAT_INTERVAL() {
      return INITIAL_HEARTBEAT_INTERVAL;
   }

   public ConfigEntry AM_FINAL_MSG_LIMIT() {
      return AM_FINAL_MSG_LIMIT;
   }

   public ConfigEntry AM_CORES() {
      return AM_CORES;
   }

   public OptionalConfigEntry AM_JAVA_OPTIONS() {
      return AM_JAVA_OPTIONS;
   }

   public OptionalConfigEntry AM_LIBRARY_PATH() {
      return AM_LIBRARY_PATH;
   }

   public OptionalConfigEntry AM_MEMORY_OVERHEAD() {
      return AM_MEMORY_OVERHEAD;
   }

   public ConfigEntry AM_MEMORY() {
      return AM_MEMORY;
   }

   public OptionalConfigEntry DRIVER_APP_UI_ADDRESS() {
      return DRIVER_APP_UI_ADDRESS;
   }

   public OptionalConfigEntry EXECUTOR_NODE_LABEL_EXPRESSION() {
      return EXECUTOR_NODE_LABEL_EXPRESSION;
   }

   public ConfigEntry YARN_UNMANAGED_AM() {
      return YARN_UNMANAGED_AM;
   }

   public OptionalConfigEntry ROLLED_LOG_INCLUDE_PATTERN() {
      return ROLLED_LOG_INCLUDE_PATTERN;
   }

   public OptionalConfigEntry ROLLED_LOG_EXCLUDE_PATTERN() {
      return ROLLED_LOG_EXCLUDE_PATTERN;
   }

   public OptionalConfigEntry APP_JAR() {
      return APP_JAR;
   }

   public OptionalConfigEntry SECONDARY_JARS() {
      return SECONDARY_JARS;
   }

   public ConfigEntry CACHED_FILES() {
      return CACHED_FILES;
   }

   public ConfigEntry CACHED_FILES_SIZES() {
      return CACHED_FILES_SIZES;
   }

   public ConfigEntry CACHED_FILES_TIMESTAMPS() {
      return CACHED_FILES_TIMESTAMPS;
   }

   public ConfigEntry CACHED_FILES_VISIBILITIES() {
      return CACHED_FILES_VISIBILITIES;
   }

   public ConfigEntry CACHED_FILES_TYPES() {
      return CACHED_FILES_TYPES;
   }

   public OptionalConfigEntry CACHED_CONF_ARCHIVE() {
      return CACHED_CONF_ARCHIVE;
   }

   public ConfigEntry YARN_EXECUTOR_LAUNCH_EXCLUDE_ON_FAILURE_ENABLED() {
      return YARN_EXECUTOR_LAUNCH_EXCLUDE_ON_FAILURE_ENABLED;
   }

   public ConfigEntry YARN_EXCLUDE_NODES() {
      return YARN_EXCLUDE_NODES;
   }

   public ConfigEntry YARN_GPU_DEVICE() {
      return YARN_GPU_DEVICE;
   }

   public ConfigEntry YARN_FPGA_DEVICE() {
      return YARN_FPGA_DEVICE;
   }

   public ConfigEntry YARN_CLIENT_STAT_CACHE_PRELOAD_ENABLED() {
      return YARN_CLIENT_STAT_CACHE_PRELOAD_ENABLED;
   }

   public ConfigEntry YARN_CLIENT_STAT_CACHE_PRELOAD_PER_DIRECTORY_THRESHOLD() {
      return YARN_CLIENT_STAT_CACHE_PRELOAD_PER_DIRECTORY_THRESHOLD;
   }

   public String YARN_EXECUTOR_RESOURCE_TYPES_PREFIX() {
      return YARN_EXECUTOR_RESOURCE_TYPES_PREFIX;
   }

   public String YARN_DRIVER_RESOURCE_TYPES_PREFIX() {
      return YARN_DRIVER_RESOURCE_TYPES_PREFIX;
   }

   public String YARN_AM_RESOURCE_TYPES_PREFIX() {
      return YARN_AM_RESOURCE_TYPES_PREFIX;
   }

   public boolean isHadoopProvided() {
      return this.IS_HADOOP_PROVIDED();
   }

   private boolean IS_HADOOP_PROVIDED$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            String configPath = "org/apache/spark/deploy/yarn/config.properties";
            String propertyKey = "spark.yarn.isHadoopProvided";
            IS_HADOOP_PROVIDED = this.liftedTree1$1(configPath, propertyKey);
            bitmap$0 = true;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return IS_HADOOP_PROVIDED;
   }

   private boolean IS_HADOOP_PROVIDED() {
      return !bitmap$0 ? this.IS_HADOOP_PROVIDED$lzycompute() : IS_HADOOP_PROVIDED;
   }

   // $FF: synthetic method
   private final boolean liftedTree1$1(final String configPath$1, final String propertyKey$1) {
      boolean var10000;
      try {
         Properties prop = new Properties();
         prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream(configPath$1));
         var10000 = scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(prop.getProperty(propertyKey$1)));
      } catch (Exception var5) {
         this.log().warn("Can not load the default value of `" + propertyKey$1 + "` from `" + configPath$1 + "` with error, " + var5.toString() + ". Using `false` as a default value.");
         var10000 = false;
      }

      return var10000;
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

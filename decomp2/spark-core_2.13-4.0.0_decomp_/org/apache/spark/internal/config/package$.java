package org.apache.spark.internal.config;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkContext$;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.metrics.GarbageCollectionMetrics$;
import org.apache.spark.network.shuffledb.DBBackend;
import org.apache.spark.network.util.ByteUnit;
import org.apache.spark.scheduler.EventLoggingListener$;
import org.apache.spark.scheduler.SchedulingMode$;
import org.apache.spark.shuffle.sort.io.LocalDiskShuffleDataIO;
import org.apache.spark.storage.DefaultTopologyMapper;
import org.apache.spark.storage.RandomBlockReplicationPolicy;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Nil.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final String SPARK_DRIVER_PREFIX = "spark.driver";
   private static final String SPARK_EXECUTOR_PREFIX = "spark.executor";
   private static final String SPARK_TASK_PREFIX = "spark.task";
   private static final String LISTENER_BUS_EVENT_QUEUE_PREFIX = "spark.scheduler.listenerbus.eventqueue";
   private static final OptionalConfigEntry DEFAULT_PARALLELISM = (new ConfigBuilder("spark.default.parallelism")).doc("Default number of partitions in RDDs returned by transformations like join, reduceByKey, and parallelize when not set by user. For distributed shuffle operations like reduceByKey and join, the largest number of partitions in a parent RDD. For operations like parallelize with no parent RDDs, it depends on the cluster manager. For example in Local mode, it defaults to the number of cores on the local machine").version("0.5.0").intConf().createOptional();
   private static final ConfigEntry RESOURCES_DISCOVERY_PLUGIN;
   private static final OptionalConfigEntry DRIVER_RESOURCES_FILE;
   private static final ConfigEntry DRIVER_DEFAULT_EXTRA_CLASS_PATH;
   private static final OptionalConfigEntry DRIVER_CLASS_PATH;
   private static final OptionalConfigEntry DRIVER_JAVA_OPTIONS;
   private static final OptionalConfigEntry DRIVER_LIBRARY_PATH;
   private static final ConfigEntry DRIVER_USER_CLASS_PATH_FIRST;
   private static final ConfigEntry DRIVER_CORES;
   private static final ConfigEntry DRIVER_MEMORY;
   private static final OptionalConfigEntry DRIVER_MEMORY_OVERHEAD;
   private static final ConfigEntry DRIVER_MIN_MEMORY_OVERHEAD;
   private static final ConfigEntry DRIVER_MEMORY_OVERHEAD_FACTOR;
   private static final ConfigEntry STRUCTURED_LOGGING_ENABLED;
   private static final ConfigEntry LEGACY_TASK_NAME_MDC_ENABLED;
   private static final OptionalConfigEntry DRIVER_LOG_LOCAL_DIR;
   private static final OptionalConfigEntry DRIVER_LOG_DFS_DIR;
   private static final OptionalConfigEntry DRIVER_LOG_LAYOUT;
   private static final ConfigEntry DRIVER_LOG_PERSISTTODFS;
   private static final ConfigEntry DRIVER_LOG_ALLOW_EC;
   private static final ConfigEntry EVENT_LOG_ENABLED;
   private static final ConfigEntry EVENT_LOG_DIR;
   private static final ConfigEntry EVENT_LOG_COMPRESS;
   private static final ConfigEntry EVENT_LOG_BLOCK_UPDATES;
   private static final ConfigEntry EVENT_LOG_ALLOW_EC;
   private static final ConfigEntry EVENT_LOG_TESTING;
   private static final ConfigEntry EVENT_LOG_OUTPUT_BUFFER_SIZE;
   private static final ConfigEntry EVENT_LOG_STAGE_EXECUTOR_METRICS;
   private static final ConfigEntry EVENT_LOG_GC_METRICS_YOUNG_GENERATION_GARBAGE_COLLECTORS;
   private static final ConfigEntry EVENT_LOG_GC_METRICS_OLD_GENERATION_GARBAGE_COLLECTORS;
   private static final ConfigEntry EVENT_LOG_INCLUDE_TASK_METRICS_ACCUMULATORS;
   private static final ConfigEntry EVENT_LOG_OVERWRITE;
   private static final ConfigEntry EVENT_LOG_CALLSITE_LONG_FORM;
   private static final ConfigEntry EVENT_LOG_ENABLE_ROLLING;
   private static final ConfigEntry EVENT_LOG_ROLLING_MAX_FILE_SIZE;
   private static final OptionalConfigEntry EXECUTOR_ID;
   private static final ConfigEntry EXECUTOR_DEFAULT_EXTRA_CLASS_PATH;
   private static final OptionalConfigEntry EXECUTOR_CLASS_PATH;
   private static final ConfigEntry EXECUTOR_HEARTBEAT_DROP_ZERO_ACCUMULATOR_UPDATES;
   private static final ConfigEntry EXECUTOR_HEARTBEAT_INTERVAL;
   private static final ConfigEntry EXECUTOR_HEARTBEAT_MAX_FAILURES;
   private static final ConfigEntry EXECUTOR_PROCESS_TREE_METRICS_ENABLED;
   private static final ConfigEntry EXECUTOR_METRICS_POLLING_INTERVAL;
   private static final ConfigEntry EXECUTOR_METRICS_FILESYSTEM_SCHEMES;
   private static final OptionalConfigEntry EXECUTOR_JAVA_OPTIONS;
   private static final OptionalConfigEntry EXECUTOR_LIBRARY_PATH;
   private static final ConfigEntry EXECUTOR_USER_CLASS_PATH_FIRST;
   private static final ConfigEntry EXECUTOR_CORES;
   private static final ConfigEntry EXECUTOR_MEMORY;
   private static final OptionalConfigEntry EXECUTOR_MEMORY_OVERHEAD;
   private static final ConfigEntry EXECUTOR_MIN_MEMORY_OVERHEAD;
   private static final ConfigEntry EXECUTOR_MEMORY_OVERHEAD_FACTOR;
   private static final OptionalConfigEntry CORES_MAX;
   private static final ConfigEntry MEMORY_OFFHEAP_ENABLED;
   private static final ConfigEntry MEMORY_OFFHEAP_SIZE;
   private static final ConfigEntry MEMORY_STORAGE_FRACTION;
   private static final ConfigEntry MEMORY_FRACTION;
   private static final ConfigEntry STORAGE_UNROLL_MEMORY_THRESHOLD;
   private static final ConfigEntry STORAGE_REPLICATION_PROACTIVE;
   private static final ConfigEntry STORAGE_MEMORY_MAP_THRESHOLD;
   private static final ConfigEntry STORAGE_REPLICATION_POLICY;
   private static final ConfigEntry STORAGE_REPLICATION_TOPOLOGY_MAPPER;
   private static final ConfigEntry STORAGE_CACHED_PEERS_TTL;
   private static final ConfigEntry STORAGE_MAX_REPLICATION_FAILURE;
   private static final ConfigEntry STORAGE_DECOMMISSION_ENABLED;
   private static final ConfigEntry STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED;
   private static final ConfigEntry STORAGE_DECOMMISSION_SHUFFLE_MAX_THREADS;
   private static final ConfigEntry STORAGE_DECOMMISSION_RDD_BLOCKS_ENABLED;
   private static final ConfigEntry STORAGE_DECOMMISSION_MAX_REPLICATION_FAILURE_PER_BLOCK;
   private static final ConfigEntry STORAGE_DECOMMISSION_REPLICATION_REATTEMPT_INTERVAL;
   private static final OptionalConfigEntry STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH;
   private static final ConfigEntry STORAGE_DECOMMISSION_FALLBACK_STORAGE_CLEANUP;
   private static final OptionalConfigEntry STORAGE_DECOMMISSION_SHUFFLE_MAX_DISK_SIZE;
   private static final OptionalConfigEntry STORAGE_REPLICATION_TOPOLOGY_FILE;
   private static final ConfigEntry STORAGE_EXCEPTION_PIN_LEAK;
   private static final ConfigEntry STORAGE_BLOCKMANAGER_TIMEOUTINTERVAL;
   private static final ConfigEntry STORAGE_BLOCKMANAGER_MASTER_DRIVER_HEARTBEAT_TIMEOUT;
   private static final OptionalConfigEntry STORAGE_BLOCKMANAGER_HEARTBEAT_TIMEOUT;
   private static final ConfigEntry STORAGE_CLEANUP_FILES_AFTER_EXECUTOR_EXIT;
   private static final ConfigEntry DISKSTORE_SUB_DIRECTORIES;
   private static final ConfigEntry BLOCK_FAILURES_BEFORE_LOCATION_REFRESH;
   private static final ConfigEntry IS_PYTHON_APP;
   private static final ConfigEntry CPUS_PER_TASK;
   private static final ConfigEntry DYN_ALLOCATION_ENABLED;
   private static final ConfigEntry DYN_ALLOCATION_TESTING;
   private static final ConfigEntry DYN_ALLOCATION_MIN_EXECUTORS;
   private static final ConfigEntry DYN_ALLOCATION_INITIAL_EXECUTORS;
   private static final ConfigEntry DYN_ALLOCATION_MAX_EXECUTORS;
   private static final ConfigEntry DYN_ALLOCATION_EXECUTOR_ALLOCATION_RATIO;
   private static final ConfigEntry DYN_ALLOCATION_CACHED_EXECUTOR_IDLE_TIMEOUT;
   private static final ConfigEntry DYN_ALLOCATION_EXECUTOR_IDLE_TIMEOUT;
   private static final ConfigEntry DYN_ALLOCATION_SHUFFLE_TRACKING_ENABLED;
   private static final ConfigEntry DYN_ALLOCATION_SHUFFLE_TRACKING_TIMEOUT;
   private static final ConfigEntry DYN_ALLOCATION_SCHEDULER_BACKLOG_TIMEOUT;
   private static final ConfigEntry DYN_ALLOCATION_SUSTAINED_SCHEDULER_BACKLOG_TIMEOUT;
   private static final ConfigEntry LEGACY_LOCALITY_WAIT_RESET;
   private static final ConfigEntry LOCALITY_WAIT;
   private static final ConfigEntry SHUFFLE_SERVICE_ENABLED;
   private static final ConfigEntry SHUFFLE_SERVICE_REMOVE_SHUFFLE_ENABLED;
   private static final ConfigEntry SHUFFLE_SERVICE_FETCH_RDD_ENABLED;
   private static final ConfigEntry SHUFFLE_SERVICE_DB_ENABLED;
   private static final ConfigEntry SHUFFLE_SERVICE_DB_BACKEND;
   private static final ConfigEntry SHUFFLE_SERVICE_PORT;
   private static final ConfigEntry SHUFFLE_SERVICE_NAME;
   private static final OptionalConfigEntry KEYTAB;
   private static final OptionalConfigEntry PRINCIPAL;
   private static final ConfigEntry KERBEROS_RELOGIN_PERIOD;
   private static final ConfigEntry KERBEROS_RENEWAL_CREDENTIALS;
   private static final ConfigEntry KERBEROS_FILESYSTEMS_TO_ACCESS;
   private static final ConfigEntry YARN_KERBEROS_FILESYSTEM_RENEWAL_EXCLUDE;
   private static final OptionalConfigEntry EXECUTOR_INSTANCES;
   private static final ConfigEntry PY_FILES;
   private static final ConfigEntry TASK_MAX_DIRECT_RESULT_SIZE;
   private static final ConfigEntry TASK_MAX_FAILURES;
   private static final ConfigEntry TASK_REAPER_ENABLED;
   private static final ConfigEntry TASK_REAPER_KILL_TIMEOUT;
   private static final ConfigEntry TASK_REAPER_POLLING_INTERVAL;
   private static final ConfigEntry TASK_REAPER_THREAD_DUMP;
   private static final OptionalConfigEntry EXCLUDE_ON_FAILURE_ENABLED;
   private static final OptionalConfigEntry EXCLUDE_ON_FAILURE_ENABLED_APPLICATION;
   private static final OptionalConfigEntry EXCLUDE_ON_FAILURE_ENABLED_TASK_AND_STAGE;
   private static final ConfigEntry MAX_TASK_ATTEMPTS_PER_EXECUTOR;
   private static final ConfigEntry MAX_TASK_ATTEMPTS_PER_NODE;
   private static final ConfigEntry MAX_FAILURES_PER_EXEC;
   private static final ConfigEntry MAX_FAILURES_PER_EXEC_STAGE;
   private static final ConfigEntry MAX_FAILED_EXEC_PER_NODE;
   private static final ConfigEntry MAX_FAILED_EXEC_PER_NODE_STAGE;
   private static final OptionalConfigEntry EXCLUDE_ON_FAILURE_TIMEOUT_CONF;
   private static final ConfigEntry EXCLUDE_ON_FAILURE_KILL_ENABLED;
   private static final ConfigEntry EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED;
   private static final OptionalConfigEntry EXCLUDE_ON_FAILURE_LEGACY_TIMEOUT_CONF;
   private static final ConfigEntry EXCLUDE_ON_FAILURE_FETCH_FAILURE_ENABLED;
   private static final OptionalConfigEntry MAX_EXECUTOR_FAILURES;
   private static final OptionalConfigEntry EXECUTOR_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS;
   private static final ConfigEntry UNREGISTER_OUTPUT_ON_HOST_ON_FETCH_FAILURE;
   private static final ConfigEntry LISTENER_BUS_EVENT_QUEUE_CAPACITY;
   private static final ConfigEntry LISTENER_BUS_METRICS_MAX_LISTENER_CLASSES_TIMED;
   private static final ConfigEntry LISTENER_BUS_LOG_SLOW_EVENT_ENABLED;
   private static final ConfigEntry LISTENER_BUS_LOG_SLOW_EVENT_TIME_THRESHOLD;
   private static final ConfigEntry LISTENER_BUS_EXIT_TIMEOUT;
   private static final OptionalConfigEntry METRICS_NAMESPACE;
   private static final OptionalConfigEntry METRICS_CONF;
   private static final ConfigEntry METRICS_EXECUTORMETRICS_SOURCE_ENABLED;
   private static final ConfigEntry METRICS_STATIC_SOURCES_ENABLED;
   private static final OptionalConfigEntry PYSPARK_DRIVER_PYTHON;
   private static final OptionalConfigEntry PYSPARK_PYTHON;
   private static final ConfigEntry IO_ENCRYPTION_ENABLED;
   private static final ConfigEntry IO_ENCRYPTION_KEYGEN_ALGORITHM;
   private static final ConfigEntry IO_ENCRYPTION_KEY_SIZE_BITS;
   private static final ConfigEntry IO_CRYPTO_CIPHER_TRANSFORMATION;
   private static final ConfigEntry DRIVER_HOST_ADDRESS;
   private static final ConfigEntry DRIVER_PORT;
   private static final ConfigEntry DRIVER_SUPERVISE;
   private static final ConfigEntry DRIVER_TIMEOUT;
   private static final ConfigEntry DRIVER_BIND_ADDRESS;
   private static final ConfigEntry BLOCK_MANAGER_PORT;
   private static final ConfigEntry DRIVER_BLOCK_MANAGER_PORT;
   private static final ConfigEntry IGNORE_CORRUPT_FILES;
   private static final ConfigEntry IGNORE_MISSING_FILES;
   private static final OptionalConfigEntry APP_CALLER_CONTEXT;
   private static final OptionalConfigEntry SPARK_LOG_LEVEL;
   private static final ConfigEntry FILES_MAX_PARTITION_BYTES;
   private static final ConfigEntry FILES_OPEN_COST_IN_BYTES;
   private static final ConfigEntry HADOOP_RDD_IGNORE_EMPTY_SPLITS;
   private static final ConfigEntry SECRET_REDACTION_PATTERN;
   private static final OptionalConfigEntry STRING_REDACTION_PATTERN;
   private static final OptionalConfigEntry AUTH_SECRET;
   private static final ConfigEntry AUTH_SECRET_BIT_LENGTH;
   private static final ConfigEntry NETWORK_AUTH_ENABLED;
   private static final ConfigEntry SASL_ENCRYPTION_ENABLED;
   private static final OptionalConfigEntry AUTH_SECRET_FILE;
   private static final ConfigEntry AUTH_SECRET_FILE_DRIVER;
   private static final ConfigEntry AUTH_SECRET_FILE_EXECUTOR;
   private static final ConfigEntry BUFFER_WRITE_CHUNK_SIZE;
   private static final OptionalConfigEntry CHECKPOINT_DIR;
   private static final ConfigEntry CHECKPOINT_COMPRESS;
   private static final OptionalConfigEntry CACHE_CHECKPOINT_PREFERRED_LOCS_EXPIRE_TIME;
   private static final ConfigEntry SHUFFLE_ACCURATE_BLOCK_THRESHOLD;
   private static final ConfigEntry SHUFFLE_ACCURATE_BLOCK_SKEWED_FACTOR;
   private static final ConfigEntry SHUFFLE_MAX_ACCURATE_SKEWED_BLOCK_NUMBER;
   private static final ConfigEntry SHUFFLE_REGISTRATION_TIMEOUT;
   private static final ConfigEntry SHUFFLE_REGISTRATION_MAX_ATTEMPTS;
   private static final ConfigEntry SHUFFLE_MAX_ATTEMPTS_ON_NETTY_OOM;
   private static final ConfigEntry REDUCER_MAX_BLOCKS_IN_FLIGHT_PER_ADDRESS;
   private static final ConfigEntry MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM;
   private static final ConfigEntry TASK_METRICS_TRACK_UPDATED_BLOCK_STATUSES;
   private static final ConfigEntry SHUFFLE_IO_PLUGIN_CLASS;
   private static final ConfigEntry SHUFFLE_FILE_BUFFER_SIZE;
   private static final ConfigEntry SHUFFLE_FILE_MERGE_BUFFER_SIZE;
   private static final ConfigEntry SHUFFLE_UNSAFE_FILE_OUTPUT_BUFFER_SIZE;
   private static final ConfigEntry SHUFFLE_LOCAL_DISK_FILE_OUTPUT_BUFFER_SIZE;
   private static final ConfigEntry SHUFFLE_DISK_WRITE_BUFFER_SIZE;
   private static final ConfigEntry UNROLL_MEMORY_CHECK_PERIOD;
   private static final ConfigEntry UNROLL_MEMORY_GROWTH_FACTOR;
   private static final ConfigEntry KUBERNETES_JARS_AVOID_DOWNLOAD_SCHEMES;
   private static final ConfigEntry FORCE_DOWNLOAD_SCHEMES;
   private static final OptionalConfigEntry EXTRA_LISTENERS;
   private static final ConfigEntry SHUFFLE_SPILL_NUM_ELEMENTS_FORCE_SPILL_THRESHOLD;
   private static final ConfigEntry SHUFFLE_MAP_OUTPUT_PARALLEL_AGGREGATION_THRESHOLD;
   private static final ConfigEntry MAX_RESULT_SIZE;
   private static final ConfigEntry CREDENTIALS_RENEWAL_INTERVAL_RATIO;
   private static final ConfigEntry CREDENTIALS_RENEWAL_RETRY_WAIT;
   private static final ConfigEntry SHUFFLE_SORT_INIT_BUFFER_SIZE;
   private static final ConfigEntry SHUFFLE_CHECKSUM_ENABLED;
   private static final ConfigEntry SHUFFLE_CHECKSUM_ALGORITHM;
   private static final ConfigEntry SHUFFLE_COMPRESS;
   private static final ConfigEntry SHUFFLE_SPILL_COMPRESS;
   private static final ConfigEntry MAP_STATUS_COMPRESSION_CODEC;
   private static final ConfigEntry SHUFFLE_SPILL_INITIAL_MEM_THRESHOLD;
   private static final ConfigEntry SHUFFLE_SPILL_BATCH_SIZE;
   private static final ConfigEntry SHUFFLE_MERGE_PREFER_NIO;
   private static final ConfigEntry SHUFFLE_SORT_BYPASS_MERGE_THRESHOLD;
   private static final ConfigEntry SHUFFLE_MANAGER;
   private static final ConfigEntry SHUFFLE_REDUCE_LOCALITY_ENABLE;
   private static final ConfigEntry SHUFFLE_MAPOUTPUT_MIN_SIZE_FOR_BROADCAST;
   private static final ConfigEntry SHUFFLE_MAPOUTPUT_DISPATCHER_NUM_THREADS;
   private static final ConfigEntry SHUFFLE_DETECT_CORRUPT;
   private static final ConfigEntry SHUFFLE_DETECT_CORRUPT_MEMORY;
   private static final ConfigEntry SHUFFLE_SYNC;
   private static final ConfigEntry SHUFFLE_UNSAFE_FAST_MERGE_ENABLE;
   private static final ConfigEntry SHUFFLE_SORT_USE_RADIXSORT;
   private static final ConfigEntry SHUFFLE_MIN_NUM_PARTS_TO_HIGHLY_COMPRESS;
   private static final ConfigEntry SHUFFLE_USE_OLD_FETCH_PROTOCOL;
   private static final ConfigEntry SHUFFLE_HOST_LOCAL_DISK_READING_ENABLED;
   private static final ConfigEntry STORAGE_LOCAL_DISK_BY_EXECUTORS_CACHE_SIZE;
   private static final ConfigEntry MEMORY_MAP_LIMIT_FOR_TESTS;
   private static final ConfigEntry BARRIER_SYNC_TIMEOUT;
   private static final ConfigEntry UNSCHEDULABLE_TASKSET_TIMEOUT;
   private static final ConfigEntry BARRIER_MAX_CONCURRENT_TASKS_CHECK_INTERVAL;
   private static final ConfigEntry BARRIER_MAX_CONCURRENT_TASKS_CHECK_MAX_FAILURES;
   private static final ConfigEntry NUM_CANCELLED_JOB_GROUPS_TO_TRACK;
   private static final ConfigEntry UNSAFE_EXCEPTION_ON_MEMORY_LEAK;
   private static final ConfigEntry UNSAFE_SORTER_SPILL_READ_AHEAD_ENABLED;
   private static final ConfigEntry UNSAFE_SORTER_SPILL_READER_BUFFER_SIZE;
   private static final String DEFAULT_PLUGINS_LIST;
   private static final ConfigEntry PLUGINS;
   private static final ConfigEntry CLEANER_PERIODIC_GC_INTERVAL;
   private static final ConfigEntry CLEANER_REFERENCE_TRACKING;
   private static final ConfigEntry CLEANER_REFERENCE_TRACKING_BLOCKING;
   private static final ConfigEntry CLEANER_REFERENCE_TRACKING_BLOCKING_SHUFFLE;
   private static final ConfigEntry CLEANER_REFERENCE_TRACKING_CLEAN_CHECKPOINTS;
   private static final ConfigEntry EXECUTOR_LOGS_ROLLING_STRATEGY;
   private static final ConfigEntry EXECUTOR_LOGS_ROLLING_TIME_INTERVAL;
   private static final ConfigEntry EXECUTOR_LOGS_ROLLING_MAX_SIZE;
   private static final ConfigEntry EXECUTOR_LOGS_ROLLING_MAX_RETAINED_FILES;
   private static final ConfigEntry EXECUTOR_LOGS_ROLLING_ENABLE_COMPRESSION;
   private static final ConfigEntry MASTER_REST_SERVER_ENABLED;
   private static final OptionalConfigEntry MASTER_REST_SERVER_HOST;
   private static final ConfigEntry MASTER_REST_SERVER_PORT;
   private static final ConfigEntry MASTER_REST_SERVER_MAX_THREADS;
   private static final ConfigEntry MASTER_REST_SERVER_FILTERS;
   private static final ConfigEntry MASTER_REST_SERVER_VIRTUAL_THREADS;
   private static final ConfigEntry MASTER_UI_PORT;
   private static final OptionalConfigEntry MASTER_UI_HISTORY_SERVER_URL;
   private static final ConfigEntry MASTER_USE_APP_NAME_AS_APP_ID;
   private static final ConfigEntry MASTER_USE_DRIVER_ID_AS_APP_NAME;
   private static final ConfigEntry IO_COMPRESSION_SNAPPY_BLOCKSIZE;
   private static final ConfigEntry IO_COMPRESSION_LZ4_BLOCKSIZE;
   private static final ConfigEntry IO_COMPRESSION_CODEC;
   private static final ConfigEntry IO_COMPRESSION_ZSTD_BUFFERSIZE;
   private static final ConfigEntry IO_COMPRESSION_ZSTD_BUFFERPOOL_ENABLED;
   private static final ConfigEntry IO_COMPRESSION_ZSTD_WORKERS;
   private static final ConfigEntry IO_COMPRESSION_ZSTD_LEVEL;
   private static final ConfigEntry IO_COMPRESSION_LZF_PARALLEL;
   private static final ConfigEntry IO_WARNING_LARGEFILETHRESHOLD;
   private static final ConfigEntry EVENT_LOG_COMPRESSION_CODEC;
   private static final ConfigEntry BUFFER_SIZE;
   private static final ConfigEntry LOCALITY_WAIT_PROCESS;
   private static final ConfigEntry LOCALITY_WAIT_NODE;
   private static final ConfigEntry LOCALITY_WAIT_RACK;
   private static final ConfigEntry REDUCER_MAX_SIZE_IN_FLIGHT;
   private static final ConfigEntry REDUCER_MAX_REQS_IN_FLIGHT;
   private static final ConfigEntry BROADCAST_COMPRESS;
   private static final ConfigEntry BROADCAST_BLOCKSIZE;
   private static final ConfigEntry BROADCAST_CHECKSUM;
   private static final ConfigEntry BROADCAST_FOR_UDF_COMPRESSION_THRESHOLD;
   private static final ConfigEntry RDD_COMPRESS;
   private static final ConfigEntry RDD_PARALLEL_LISTING_THRESHOLD;
   private static final ConfigEntry RDD_LIMIT_INITIAL_NUM_PARTITIONS;
   private static final ConfigEntry RDD_LIMIT_SCALE_UP_FACTOR;
   private static final ConfigEntry SERIALIZER;
   private static final ConfigEntry SERIALIZER_OBJECT_STREAM_RESET;
   private static final ConfigEntry SERIALIZER_EXTRA_DEBUG_INFO;
   private static final ConfigEntry JARS;
   private static final ConfigEntry FILES;
   private static final ConfigEntry ARCHIVES;
   private static final ConfigEntry SUBMIT_DEPLOY_MODE;
   private static final ConfigEntry SUBMIT_PYTHON_FILES;
   private static final OptionalConfigEntry SCHEDULER_ALLOCATION_FILE;
   private static final OptionalConfigEntry SCHEDULER_MIN_REGISTERED_RESOURCES_RATIO;
   private static final ConfigEntry SCHEDULER_MAX_REGISTERED_RESOURCE_WAITING_TIME;
   private static final ConfigEntry SCHEDULER_MODE;
   private static final OptionalConfigEntry SCHEDULER_REVIVE_INTERVAL;
   private static final ConfigEntry SPECULATION_ENABLED;
   private static final ConfigEntry SPECULATION_INTERVAL;
   private static final ConfigEntry SPECULATION_MULTIPLIER;
   private static final ConfigEntry SPECULATION_QUANTILE;
   private static final ConfigEntry SPECULATION_MIN_THRESHOLD;
   private static final OptionalConfigEntry SPECULATION_TASK_DURATION_THRESHOLD;
   private static final ConfigEntry SPECULATION_EFFICIENCY_TASK_PROCESS_RATE_MULTIPLIER;
   private static final ConfigEntry SPECULATION_EFFICIENCY_TASK_DURATION_FACTOR;
   private static final ConfigEntry SPECULATION_EFFICIENCY_ENABLE;
   private static final ConfigEntry DECOMMISSION_ENABLED;
   private static final OptionalConfigEntry EXECUTOR_DECOMMISSION_KILL_INTERVAL;
   private static final OptionalConfigEntry EXECUTOR_DECOMMISSION_FORCE_KILL_TIMEOUT;
   private static final ConfigEntry EXECUTOR_DECOMMISSION_SIGNAL;
   private static final OptionalConfigEntry STAGING_DIR;
   private static final OptionalConfigEntry BUFFER_PAGESIZE;
   private static final ConfigEntry RESOURCE_PROFILE_MERGE_CONFLICTS;
   private static final ConfigEntry STANDALONE_SUBMIT_WAIT_APP_COMPLETION;
   private static final ConfigEntry EXECUTOR_ALLOW_SPARK_CONTEXT;
   private static final ConfigEntry EXECUTOR_ALLOW_SYNC_LOG_LEVEL;
   private static final ConfigEntry KILL_ON_FATAL_ERROR_DEPTH;
   private static final ConfigEntry STAGE_MAX_CONSECUTIVE_ATTEMPTS;
   private static final ConfigEntry STAGE_IGNORE_DECOMMISSION_FETCH_FAILURE;
   private static final ConfigEntry SCHEDULER_MAX_RETAINED_REMOVED_EXECUTORS;
   private static final ConfigEntry SCHEDULER_MAX_RETAINED_UNKNOWN_EXECUTORS;
   private static final ConfigEntry PUSH_BASED_SHUFFLE_ENABLED;
   private static final ConfigEntry PUSH_BASED_SHUFFLE_MERGE_RESULTS_TIMEOUT;
   private static final ConfigEntry PUSH_BASED_SHUFFLE_MERGE_FINALIZE_TIMEOUT;
   private static final ConfigEntry SHUFFLE_MERGER_MAX_RETAINED_LOCATIONS;
   private static final ConfigEntry SHUFFLE_MERGER_LOCATIONS_MIN_THRESHOLD_RATIO;
   private static final ConfigEntry SHUFFLE_MERGER_LOCATIONS_MIN_STATIC_THRESHOLD;
   private static final OptionalConfigEntry SHUFFLE_NUM_PUSH_THREADS;
   private static final ConfigEntry SHUFFLE_MAX_BLOCK_SIZE_TO_PUSH;
   private static final ConfigEntry SHUFFLE_MAX_BLOCK_BATCH_SIZE_FOR_PUSH;
   private static final ConfigEntry PUSH_BASED_SHUFFLE_MERGE_FINALIZE_THREADS;
   private static final ConfigEntry PUSH_SHUFFLE_FINALIZE_RPC_THREADS;
   private static final ConfigEntry PUSH_BASED_SHUFFLE_SIZE_MIN_SHUFFLE_SIZE_TO_WAIT;
   private static final ConfigEntry PUSH_BASED_SHUFFLE_MIN_PUSH_RATIO;
   private static final ConfigEntry JAR_IVY_REPO_PATH;
   private static final OptionalConfigEntry JAR_IVY_SETTING_PATH;
   private static final ConfigEntry JAR_PACKAGES;
   private static final ConfigEntry JAR_PACKAGES_EXCLUSIONS;
   private static final ConfigEntry JAR_REPOSITORIES;
   private static final OptionalConfigEntry APP_ATTEMPT_ID;
   private static final ConfigEntry EXECUTOR_STATE_SYNC_MAX_ATTEMPTS;
   private static final ConfigEntry EXECUTOR_REMOVE_DELAY;
   private static final ConfigEntry ALLOW_CUSTOM_CLASSPATH_BY_PROXY_USER_IN_CLUSTER_MODE;
   private static final ConfigEntry RDD_CACHE_VISIBILITY_TRACKING_ENABLED;
   private static final ConfigEntry STAGE_MAX_ATTEMPTS;
   private static final ConfigEntry SHUFFLE_SERVER_RECOVERY_DISABLED;
   private static final ConfigEntry CONNECT_SCALA_UDF_STUB_PREFIXES;
   private static final ConfigEntry LEGACY_ABORT_STAGE_AFTER_KILL_TASKS;
   private static final ConfigEntry DROP_TASK_INFO_ACCUMULABLES_ON_TASK_COMPLETION;
   private static final OptionalConfigEntry SPARK_SHUTDOWN_TIMEOUT_MS;
   private static final ConfigEntry SPARK_API_MODE;

   static {
      RESOURCES_DISCOVERY_PLUGIN = (new ConfigBuilder("spark.resources.discoveryPlugin")).doc("Comma-separated list of class names implementingorg.apache.spark.api.resource.ResourceDiscoveryPlugin to load into the application.This is for advanced users to replace the resource discovery class with a custom implementation. Spark will try each class specified until one of them returns the resource information for that resource. It tries the discovery script last if none of the plugins return information for that resource.").version("3.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      DRIVER_RESOURCES_FILE = (new ConfigBuilder("spark.driver.resourcesFile")).internal().doc("Path to a file containing the resources allocated to the driver. The file should be formatted as a JSON array of ResourceAllocation objects. Only used internally in standalone mode.").version("3.0.0").stringConf().createOptional();
      DRIVER_DEFAULT_EXTRA_CLASS_PATH = (new ConfigBuilder("spark.driver.defaultExtraClassPath")).internal().version("4.0.0").stringConf().createWithDefault("hive-jackson/*");
      DRIVER_CLASS_PATH = (new ConfigBuilder("spark.driver.extraClassPath")).withPrepended(MODULE$.DRIVER_DEFAULT_EXTRA_CLASS_PATH().key(), File.pathSeparator).version("1.0.0").stringConf().createOptional();
      ConfigBuilder qual$2 = new ConfigBuilder("spark.driver.extraJavaOptions");
      String x$3 = "spark.driver.defaultJavaOptions";
      String x$4 = qual$2.withPrepended$default$2();
      DRIVER_JAVA_OPTIONS = qual$2.withPrepended("spark.driver.defaultJavaOptions", x$4).version("1.0.0").stringConf().createOptional();
      DRIVER_LIBRARY_PATH = (new ConfigBuilder("spark.driver.extraLibraryPath")).version("1.0.0").stringConf().createOptional();
      DRIVER_USER_CLASS_PATH_FIRST = (new ConfigBuilder("spark.driver.userClassPathFirst")).version("1.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      DRIVER_CORES = (new ConfigBuilder("spark.driver.cores")).doc("Number of cores to use for the driver process, only in cluster mode.").version("1.3.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1));
      DRIVER_MEMORY = (new ConfigBuilder("spark.driver.memory")).doc("Amount of memory to use for the driver process, in MiB unless otherwise specified.").version("1.1.1").bytesConf(ByteUnit.MiB).createWithDefaultString("1g");
      DRIVER_MEMORY_OVERHEAD = (new ConfigBuilder("spark.driver.memoryOverhead")).doc("The amount of non-heap memory to be allocated per driver in cluster mode, in MiB unless otherwise specified.").version("2.3.0").bytesConf(ByteUnit.MiB).createOptional();
      DRIVER_MIN_MEMORY_OVERHEAD = (new ConfigBuilder("spark.driver.minMemoryOverhead")).doc("The minimum amount of non-heap memory to be allocated per driver in cluster mode, in MiB unless otherwise specified. This value is ignored if spark.driver.memoryOverhead is set directly.").version("4.0.0").bytesConf(ByteUnit.MiB).createWithDefaultString("384m");
      DRIVER_MEMORY_OVERHEAD_FACTOR = (new ConfigBuilder("spark.driver.memoryOverheadFactor")).doc("Fraction of driver memory to be allocated as additional non-heap memory per driver process in cluster mode. This is memory that accounts for things like VM overheads, interned strings, other native overheads, etc. This tends to grow with the container size. This value defaults to 0.10 except for Kubernetes non-JVM jobs, which defaults to 0.40. This is done as non-JVM tasks need more non-JVM heap space and such tasks commonly fail with \"Memory Overhead Exceeded\" errors. This preempts this error with a higher default. This value is ignored if spark.driver.memoryOverhead is set directly.").version("3.3.0").doubleConf().checkValue((JFunction1.mcZD.sp)(factor) -> factor > (double)0, "Ensure that memory overhead is a double greater than 0").createWithDefault(BoxesRunTime.boxToDouble(0.1));
      STRUCTURED_LOGGING_ENABLED = (new ConfigBuilder("spark.log.structuredLogging.enabled")).doc("When true, Spark logs are output as structured JSON lines with added Spark Mapped Diagnostic Context (MDC), facilitating easier integration with log aggregation and analysis tools. When false, logs are plain text without MDC. This configuration does not apply to interactive environments such as spark-shell, spark-sql, and PySpark shell.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      LEGACY_TASK_NAME_MDC_ENABLED = (new ConfigBuilder("spark.log.legacyTaskNameMdc.enabled")).doc("When true, the MDC (Mapped Diagnostic Context) key `mdc.taskName` will be set in the log output, which is the behavior of Spark version 3.1 through Spark 3.5 releases. When false, the logging framework will use `task_name` as the MDC key, aligning it with the naming convention of newer MDC keys introduced in Spark 4.0 release.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      DRIVER_LOG_LOCAL_DIR = (new ConfigBuilder("spark.driver.log.localDir")).doc("Specifies a local directory to write driver logs and enable Driver Log UI Tab.").version("4.0.0").stringConf().createOptional();
      DRIVER_LOG_DFS_DIR = (new ConfigBuilder("spark.driver.log.dfsDir")).version("3.0.0").stringConf().createOptional();
      DRIVER_LOG_LAYOUT = (new ConfigBuilder("spark.driver.log.layout")).version("3.0.0").stringConf().createOptional();
      DRIVER_LOG_PERSISTTODFS = (new ConfigBuilder("spark.driver.log.persistToDfs.enabled")).version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      DRIVER_LOG_ALLOW_EC = (new ConfigBuilder("spark.driver.log.allowErasureCoding")).version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EVENT_LOG_ENABLED = (new ConfigBuilder("spark.eventLog.enabled")).version("1.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EVENT_LOG_DIR = (new ConfigBuilder("spark.eventLog.dir")).version("1.0.0").stringConf().createWithDefault(EventLoggingListener$.MODULE$.DEFAULT_LOG_DIR());
      EVENT_LOG_COMPRESS = (new ConfigBuilder("spark.eventLog.compress")).version("1.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      EVENT_LOG_BLOCK_UPDATES = (new ConfigBuilder("spark.eventLog.logBlockUpdates.enabled")).version("2.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EVENT_LOG_ALLOW_EC = (new ConfigBuilder("spark.eventLog.erasureCoding.enabled")).version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EVENT_LOG_TESTING = (new ConfigBuilder("spark.eventLog.testing")).internal().version("1.0.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EVENT_LOG_OUTPUT_BUFFER_SIZE = (new ConfigBuilder("spark.eventLog.buffer.kb")).doc("Buffer size to use when writing to output streams, in KiB unless otherwise specified.").version("1.0.0").bytesConf(ByteUnit.KiB).createWithDefaultString("100k");
      EVENT_LOG_STAGE_EXECUTOR_METRICS = (new ConfigBuilder("spark.eventLog.logStageExecutorMetrics")).doc("Whether to write per-stage peaks of executor metrics (for each executor) to the event log.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      ConfigBuilder var10000 = new ConfigBuilder("spark.eventLog.gcMetrics.youngGenerationGarbageCollectors");
      Seq var10001 = GarbageCollectionMetrics$.MODULE$.YOUNG_GENERATION_BUILTIN_GARBAGE_COLLECTORS();
      EVENT_LOG_GC_METRICS_YOUNG_GENERATION_GARBAGE_COLLECTORS = var10000.doc("Names of supported young generation garbage collector. A name usually is  the return of GarbageCollectorMXBean.getName. The built-in young generation garbage collectors are " + var10001).version("3.0.0").stringConf().toSequence().createWithDefault(GarbageCollectionMetrics$.MODULE$.YOUNG_GENERATION_BUILTIN_GARBAGE_COLLECTORS());
      var10000 = new ConfigBuilder("spark.eventLog.gcMetrics.oldGenerationGarbageCollectors");
      var10001 = GarbageCollectionMetrics$.MODULE$.OLD_GENERATION_BUILTIN_GARBAGE_COLLECTORS();
      EVENT_LOG_GC_METRICS_OLD_GENERATION_GARBAGE_COLLECTORS = var10000.doc("Names of supported old generation garbage collector. A name usually is the return of GarbageCollectorMXBean.getName. The built-in old generation garbage collectors are " + var10001).version("3.0.0").stringConf().toSequence().createWithDefault(GarbageCollectionMetrics$.MODULE$.OLD_GENERATION_BUILTIN_GARBAGE_COLLECTORS());
      EVENT_LOG_INCLUDE_TASK_METRICS_ACCUMULATORS = (new ConfigBuilder("spark.eventLog.includeTaskMetricsAccumulators")).doc("Whether to include TaskMetrics' underlying accumulator values in the event log (as part of the Task/Stage/Job metrics' 'Accumulables' fields. The TaskMetrics values are already logged in the 'Task Metrics' fields (so the accumulator updates are redundant). This flag defaults to true for behavioral backwards compatibility for applications that might rely on the redundant logging. See SPARK-42204 for details.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      EVENT_LOG_OVERWRITE = (new ConfigBuilder("spark.eventLog.overwrite")).version("1.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EVENT_LOG_CALLSITE_LONG_FORM = (new ConfigBuilder("spark.eventLog.longForm.enabled")).version("2.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EVENT_LOG_ENABLE_ROLLING = (new ConfigBuilder("spark.eventLog.rolling.enabled")).doc("Whether rolling over event log files is enabled. If set to true, it cuts down each event log file to the configured size.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      var10000 = new ConfigBuilder("spark.eventLog.rolling.maxFileSize");
      String var23 = MODULE$.EVENT_LOG_ENABLE_ROLLING().key();
      EVENT_LOG_ROLLING_MAX_FILE_SIZE = var10000.doc("When " + var23 + "=true, specifies the max size of event log file to be rolled over.").version("3.0.0").bytesConf(ByteUnit.BYTE).checkValue((JFunction1.mcZJ.sp)(x$1x) -> x$1x >= ByteUnit.MiB.toBytes(10L), "Max file size of event log should be configured to be at least 10 MiB.").createWithDefaultString("128m");
      EXECUTOR_ID = (new ConfigBuilder("spark.executor.id")).version("1.2.0").stringConf().createOptional();
      EXECUTOR_DEFAULT_EXTRA_CLASS_PATH = (new ConfigBuilder("spark.executor.defaultExtraClassPath")).internal().version("4.0.0").stringConf().createWithDefault("hive-jackson/*");
      EXECUTOR_CLASS_PATH = (new ConfigBuilder("spark.executor.extraClassPath")).withPrepended(MODULE$.EXECUTOR_DEFAULT_EXTRA_CLASS_PATH().key(), File.pathSeparator).version("1.0.0").stringConf().createOptional();
      EXECUTOR_HEARTBEAT_DROP_ZERO_ACCUMULATOR_UPDATES = (new ConfigBuilder("spark.executor.heartbeat.dropZeroAccumulatorUpdates")).internal().version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      EXECUTOR_HEARTBEAT_INTERVAL = (new ConfigBuilder("spark.executor.heartbeatInterval")).version("1.1.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("10s");
      EXECUTOR_HEARTBEAT_MAX_FAILURES = (new ConfigBuilder("spark.executor.heartbeat.maxFailures")).internal().version("1.6.2").intConf().createWithDefault(BoxesRunTime.boxToInteger(60));
      EXECUTOR_PROCESS_TREE_METRICS_ENABLED = (new ConfigBuilder("spark.executor.processTreeMetrics.enabled")).doc("Whether to collect process tree metrics (from the /proc filesystem) when collecting executor metrics.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EXECUTOR_METRICS_POLLING_INTERVAL = (new ConfigBuilder("spark.executor.metrics.pollingInterval")).doc("How often to collect executor metrics (in milliseconds). If 0, the polling is done on executor heartbeats. If positive, the polling is done at this interval.").version("3.0.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("0");
      EXECUTOR_METRICS_FILESYSTEM_SCHEMES = (new ConfigBuilder("spark.executor.metrics.fileSystemSchemes")).doc("The file system schemes to report in executor metrics.").version("3.1.0").stringConf().createWithDefaultString("file,hdfs");
      ConfigBuilder qual$1 = new ConfigBuilder("spark.executor.extraJavaOptions");
      String x$1 = "spark.executor.defaultJavaOptions";
      String x$2 = qual$1.withPrepended$default$2();
      EXECUTOR_JAVA_OPTIONS = qual$1.withPrepended("spark.executor.defaultJavaOptions", x$2).version("1.0.0").stringConf().createOptional();
      EXECUTOR_LIBRARY_PATH = (new ConfigBuilder("spark.executor.extraLibraryPath")).version("1.0.0").stringConf().createOptional();
      EXECUTOR_USER_CLASS_PATH_FIRST = (new ConfigBuilder("spark.executor.userClassPathFirst")).version("1.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EXECUTOR_CORES = (new ConfigBuilder("spark.executor.cores")).version("1.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1));
      EXECUTOR_MEMORY = (new ConfigBuilder("spark.executor.memory")).doc("Amount of memory to use per executor process, in MiB unless otherwise specified.").version("0.7.0").bytesConf(ByteUnit.MiB).createWithDefaultString("1g");
      EXECUTOR_MEMORY_OVERHEAD = (new ConfigBuilder("spark.executor.memoryOverhead")).doc("The amount of non-heap memory to be allocated per executor, in MiB unless otherwise specified.").version("2.3.0").bytesConf(ByteUnit.MiB).createOptional();
      EXECUTOR_MIN_MEMORY_OVERHEAD = (new ConfigBuilder("spark.executor.minMemoryOverhead")).doc("The minimum amount of non-heap memory to be allocated per executor in MiB unless otherwise specified. This value is ignored if spark.executor.memoryOverhead is set directly.").version("4.0.0").bytesConf(ByteUnit.MiB).createWithDefaultString("384m");
      EXECUTOR_MEMORY_OVERHEAD_FACTOR = (new ConfigBuilder("spark.executor.memoryOverheadFactor")).doc("Fraction of executor memory to be allocated as additional non-heap memory per executor process. This is memory that accounts for things like VM overheads, interned strings, other native overheads, etc. This tends to grow with the container size. This value defaults to 0.10 except for Kubernetes non-JVM jobs, which defaults to 0.40. This is done as non-JVM tasks need more non-JVM heap space and such tasks commonly fail with \"Memory Overhead Exceeded\" errors. This preempts this error with a higher default. This value is ignored if spark.executor.memoryOverhead is set directly.").version("3.3.0").doubleConf().checkValue((JFunction1.mcZD.sp)(factor) -> factor > (double)0, "Ensure that memory overhead is a double greater than 0").createWithDefault(BoxesRunTime.boxToDouble(0.1));
      CORES_MAX = (new ConfigBuilder("spark.cores.max")).doc("When running on a standalone deploy cluster, the maximum amount of CPU cores to request for the application from across the cluster (not from each machine). If not set, the default will be `spark.deploy.defaultCores` on Spark's standalone cluster manager").version("0.6.0").intConf().createOptional();
      MEMORY_OFFHEAP_ENABLED = (new ConfigBuilder("spark.memory.offHeap.enabled")).doc("If true, Spark will attempt to use off-heap memory for certain operations. If off-heap memory use is enabled, then spark.memory.offHeap.size must be positive.").version("1.6.0").withAlternative("spark.unsafe.offHeap").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      MEMORY_OFFHEAP_SIZE = (new ConfigBuilder("spark.memory.offHeap.size")).doc("The absolute amount of memory which can be used for off-heap allocation,  in bytes unless otherwise specified. This setting has no impact on heap memory usage, so if your executors' total memory consumption must fit within some hard limit then be sure to shrink your JVM heap size accordingly. This must be set to a positive value when spark.memory.offHeap.enabled=true.").version("1.6.0").bytesConf(ByteUnit.BYTE).checkValue((JFunction1.mcZJ.sp)(x$2x) -> x$2x >= 0L, "The off-heap memory size must not be negative").createWithDefault(BoxesRunTime.boxToLong(0L));
      MEMORY_STORAGE_FRACTION = (new ConfigBuilder("spark.memory.storageFraction")).doc("Amount of storage memory immune to eviction, expressed as a fraction of the size of the region set aside by spark.memory.fraction. The higher this is, the less working memory may be available to execution and tasks may spill to disk more often. Leaving this at the default value is recommended. ").version("1.6.0").doubleConf().checkValue((JFunction1.mcZD.sp)(v) -> v >= (double)0.0F && v < (double)1.0F, "Storage fraction must be in [0,1)").createWithDefault(BoxesRunTime.boxToDouble((double)0.5F));
      MEMORY_FRACTION = (new ConfigBuilder("spark.memory.fraction")).doc("Fraction of (heap space - 300MB) used for execution and storage. The lower this is, the more frequently spills and cached data eviction occur. The purpose of this config is to set aside memory for internal metadata, user data structures, and imprecise size estimation in the case of sparse, unusually large records. Leaving this at the default value is recommended.  ").version("1.6.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble(0.6));
      STORAGE_UNROLL_MEMORY_THRESHOLD = (new ConfigBuilder("spark.storage.unrollMemoryThreshold")).doc("Initial memory to request before unrolling any block").version("1.1.0").longConf().createWithDefault(BoxesRunTime.boxToLong(1048576L));
      STORAGE_REPLICATION_PROACTIVE = (new ConfigBuilder("spark.storage.replication.proactive")).doc("Enables proactive block replication for RDD blocks. Cached RDD block replicas lost due to executor failures are replenished if there are any existing available replicas. This tries to get the replication level of the block to the initial number").version("2.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      STORAGE_MEMORY_MAP_THRESHOLD = (new ConfigBuilder("spark.storage.memoryMapThreshold")).doc("Size in bytes of a block above which Spark memory maps when reading a block from disk. This prevents Spark from memory mapping very small blocks. In general, memory mapping has high overhead for blocks close to or below the page size of the operating system.").version("0.9.2").bytesConf(ByteUnit.BYTE).createWithDefaultString("2m");
      STORAGE_REPLICATION_POLICY = (new ConfigBuilder("spark.storage.replication.policy")).version("2.1.0").stringConf().createWithDefaultString(RandomBlockReplicationPolicy.class.getName());
      STORAGE_REPLICATION_TOPOLOGY_MAPPER = (new ConfigBuilder("spark.storage.replication.topologyMapper")).version("2.1.0").stringConf().createWithDefaultString(DefaultTopologyMapper.class.getName());
      STORAGE_CACHED_PEERS_TTL = (new ConfigBuilder("spark.storage.cachedPeersTtl")).version("1.1.1").intConf().createWithDefault(BoxesRunTime.boxToInteger(60000));
      STORAGE_MAX_REPLICATION_FAILURE = (new ConfigBuilder("spark.storage.maxReplicationFailures")).version("1.1.1").intConf().createWithDefault(BoxesRunTime.boxToInteger(1));
      STORAGE_DECOMMISSION_ENABLED = (new ConfigBuilder("spark.storage.decommission.enabled")).doc("Whether to decommission the block manager when decommissioning executor").version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED = (new ConfigBuilder("spark.storage.decommission.shuffleBlocks.enabled")).doc("Whether to transfer shuffle blocks during block manager decommissioning. Requires a migratable shuffle resolver (like sort based shuffle)").version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      STORAGE_DECOMMISSION_SHUFFLE_MAX_THREADS = (new ConfigBuilder("spark.storage.decommission.shuffleBlocks.maxThreads")).doc("Maximum number of threads to use in migrating shuffle files.").version("3.1.0").intConf().checkValue((JFunction1.mcZI.sp)(x$3x) -> x$3x > 0, "The maximum number of threads should be positive").createWithDefault(BoxesRunTime.boxToInteger(8));
      STORAGE_DECOMMISSION_RDD_BLOCKS_ENABLED = (new ConfigBuilder("spark.storage.decommission.rddBlocks.enabled")).doc("Whether to transfer RDD blocks during block manager decommissioning.").version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      STORAGE_DECOMMISSION_MAX_REPLICATION_FAILURE_PER_BLOCK = (new ConfigBuilder("spark.storage.decommission.maxReplicationFailuresPerBlock")).internal().doc("Maximum number of failures which can be handled for the replication of one RDD block when block manager is decommissioning and trying to move its existing blocks.").version("3.1.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(3));
      STORAGE_DECOMMISSION_REPLICATION_REATTEMPT_INTERVAL = (new ConfigBuilder("spark.storage.decommission.replicationReattemptInterval")).internal().doc("The interval of time between consecutive cache block replication reattempts happening on each decommissioning executor (due to storage decommissioning).").version("3.1.0").timeConf(TimeUnit.MILLISECONDS).checkValue((JFunction1.mcZJ.sp)(x$4x) -> x$4x > 0L, "Time interval between two consecutive attempts of cache block replication should be positive.").createWithDefaultString("30s");
      STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH = (new ConfigBuilder("spark.storage.decommission.fallbackStorage.path")).doc("The location for fallback storage during block manager decommissioning. For example, `s3a://spark-storage/`. In case of empty, fallback storage is disabled. The storage should be managed by TTL because Spark will not clean it up.").version("3.1.0").stringConf().checkValue((x$5) -> BoxesRunTime.boxToBoolean($anonfun$STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH$1(x$5)), "Path should end with separator.").createOptional();
      STORAGE_DECOMMISSION_FALLBACK_STORAGE_CLEANUP = (new ConfigBuilder("spark.storage.decommission.fallbackStorage.cleanUp")).doc("If true, Spark cleans up its fallback storage data during shutting down.").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      var10000 = new ConfigBuilder("spark.storage.decommission.shuffleBlocks.maxDiskSize");
      var23 = MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH().key();
      STORAGE_DECOMMISSION_SHUFFLE_MAX_DISK_SIZE = var10000.doc("Maximum disk space to use to store shuffle blocks before rejecting remote shuffle blocks. Rejecting remote shuffle blocks means that an executor will not receive any shuffle migrations, and if there are no other executors available for migration then shuffle blocks will be lost unless " + var23 + " is configured.").version("3.2.0").bytesConf(ByteUnit.BYTE).createOptional();
      STORAGE_REPLICATION_TOPOLOGY_FILE = (new ConfigBuilder("spark.storage.replication.topologyFile")).version("2.1.0").stringConf().createOptional();
      STORAGE_EXCEPTION_PIN_LEAK = (new ConfigBuilder("spark.storage.exceptionOnPinLeak")).version("1.6.2").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      STORAGE_BLOCKMANAGER_TIMEOUTINTERVAL = (new ConfigBuilder("spark.storage.blockManagerTimeoutIntervalMs")).version("0.7.3").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("60s");
      STORAGE_BLOCKMANAGER_MASTER_DRIVER_HEARTBEAT_TIMEOUT = (new ConfigBuilder("spark.storage.blockManagerMasterDriverHeartbeatTimeoutMs")).doc("A timeout used for block manager master's driver heartbeat endpoint.").version("3.2.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("10m");
      STORAGE_BLOCKMANAGER_HEARTBEAT_TIMEOUT = (new ConfigBuilder("spark.storage.blockManagerHeartbeatTimeoutMs")).version("0.7.0").withAlternative("spark.storage.blockManagerSlaveTimeoutMs").timeConf(TimeUnit.MILLISECONDS).createOptional();
      STORAGE_CLEANUP_FILES_AFTER_EXECUTOR_EXIT = (new ConfigBuilder("spark.storage.cleanupFilesAfterExecutorExit")).doc("Whether or not cleanup the files not served by the external shuffle service on executor exits.").version("2.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      DISKSTORE_SUB_DIRECTORIES = (new ConfigBuilder("spark.diskStore.subDirectories")).doc("Number of subdirectories inside each path listed in spark.local.dir for hashing Block files into.").version("0.6.0").intConf().checkValue((JFunction1.mcZI.sp)(x$6) -> x$6 > 0, "The number of subdirectories must be positive.").createWithDefault(BoxesRunTime.boxToInteger(64));
      BLOCK_FAILURES_BEFORE_LOCATION_REFRESH = (new ConfigBuilder("spark.block.failures.beforeLocationRefresh")).doc("Max number of failures before this block manager refreshes the block locations from the driver.").version("2.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(5));
      IS_PYTHON_APP = (new ConfigBuilder("spark.yarn.isPython")).internal().version("1.5.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      CPUS_PER_TASK = (new ConfigBuilder("spark.task.cpus")).version("0.5.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1));
      DYN_ALLOCATION_ENABLED = (new ConfigBuilder("spark.dynamicAllocation.enabled")).version("1.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      DYN_ALLOCATION_TESTING = (new ConfigBuilder("spark.dynamicAllocation.testing")).version("1.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      DYN_ALLOCATION_MIN_EXECUTORS = (new ConfigBuilder("spark.dynamicAllocation.minExecutors")).version("1.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(0));
      DYN_ALLOCATION_INITIAL_EXECUTORS = (new ConfigBuilder("spark.dynamicAllocation.initialExecutors")).version("1.3.0").fallbackConf(MODULE$.DYN_ALLOCATION_MIN_EXECUTORS());
      DYN_ALLOCATION_MAX_EXECUTORS = (new ConfigBuilder("spark.dynamicAllocation.maxExecutors")).version("1.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      DYN_ALLOCATION_EXECUTOR_ALLOCATION_RATIO = (new ConfigBuilder("spark.dynamicAllocation.executorAllocationRatio")).version("2.4.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble((double)1.0F));
      DYN_ALLOCATION_CACHED_EXECUTOR_IDLE_TIMEOUT = (new ConfigBuilder("spark.dynamicAllocation.cachedExecutorIdleTimeout")).version("1.4.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(x$7) -> x$7 >= 0L, "Timeout must be >= 0.").createWithDefault(BoxesRunTime.boxToLong(2147483647L));
      DYN_ALLOCATION_EXECUTOR_IDLE_TIMEOUT = (new ConfigBuilder("spark.dynamicAllocation.executorIdleTimeout")).version("1.2.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(x$8) -> x$8 >= 0L, "Timeout must be >= 0.").createWithDefault(BoxesRunTime.boxToLong(60L));
      DYN_ALLOCATION_SHUFFLE_TRACKING_ENABLED = (new ConfigBuilder("spark.dynamicAllocation.shuffleTracking.enabled")).version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      DYN_ALLOCATION_SHUFFLE_TRACKING_TIMEOUT = (new ConfigBuilder("spark.dynamicAllocation.shuffleTracking.timeout")).version("3.0.0").timeConf(TimeUnit.MILLISECONDS).checkValue((JFunction1.mcZJ.sp)(x$9) -> x$9 >= 0L, "Timeout must be >= 0.").createWithDefault(BoxesRunTime.boxToLong(Long.MAX_VALUE));
      DYN_ALLOCATION_SCHEDULER_BACKLOG_TIMEOUT = (new ConfigBuilder("spark.dynamicAllocation.schedulerBacklogTimeout")).version("1.2.0").timeConf(TimeUnit.SECONDS).createWithDefault(BoxesRunTime.boxToLong(1L));
      DYN_ALLOCATION_SUSTAINED_SCHEDULER_BACKLOG_TIMEOUT = (new ConfigBuilder("spark.dynamicAllocation.sustainedSchedulerBacklogTimeout")).version("1.2.0").fallbackConf(MODULE$.DYN_ALLOCATION_SCHEDULER_BACKLOG_TIMEOUT());
      LEGACY_LOCALITY_WAIT_RESET = (new ConfigBuilder("spark.locality.wait.legacyResetOnTaskLaunch")).doc("Whether to use the legacy behavior of locality wait, which resets the delay timer anytime a task is scheduled. See Delay Scheduling section of TaskSchedulerImpl's class documentation for more details.").internal().version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      LOCALITY_WAIT = (new ConfigBuilder("spark.locality.wait")).version("0.5.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("3s");
      SHUFFLE_SERVICE_ENABLED = (new ConfigBuilder("spark.shuffle.service.enabled")).version("1.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      SHUFFLE_SERVICE_REMOVE_SHUFFLE_ENABLED = (new ConfigBuilder("spark.shuffle.service.removeShuffle")).doc("Whether to use the ExternalShuffleService for deleting shuffle blocks for deallocated executors when the shuffle is no longer needed. Without this enabled, shuffle data on executors that are deallocated will remain on disk until the application ends.").version("3.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SHUFFLE_SERVICE_FETCH_RDD_ENABLED = (new ConfigBuilder("spark.shuffle.service.fetch.rdd.enabled")).doc("Whether to use the ExternalShuffleService for fetching disk persisted RDD blocks. In case of dynamic allocation if this feature is enabled executors having only disk persisted blocks are considered idle after 'spark.dynamicAllocation.executorIdleTimeout' and will be released accordingly.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      SHUFFLE_SERVICE_DB_ENABLED = (new ConfigBuilder("spark.shuffle.service.db.enabled")).doc("Whether to use db in ExternalShuffleService. Note that this only affects standalone mode.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SHUFFLE_SERVICE_DB_BACKEND = (new ConfigBuilder("spark.shuffle.service.db.backend")).doc("Specifies a disk-based store used in shuffle service local db. ROCKSDB or LEVELDB (deprecated).").version("3.4.0").stringConf().transform((x$10) -> x$10.toUpperCase(Locale.ROOT)).checkValues(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])DBBackend.values()), (x$11) -> x$11.toString(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toSet()).createWithDefault(DBBackend.ROCKSDB.name());
      SHUFFLE_SERVICE_PORT = (new ConfigBuilder("spark.shuffle.service.port")).version("1.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(7337));
      var10000 = new ConfigBuilder("spark.shuffle.service.name");
      ConfigEntry var25 = MODULE$.SHUFFLE_SERVICE_ENABLED();
      SHUFFLE_SERVICE_NAME = var10000.doc("The configured name of the Spark shuffle service the client should communicate with. This must match the name used to configure the Shuffle within the YARN NodeManager configuration (`yarn.nodemanager.aux-services`). Only takes effect when " + var25 + " is set to true.").version("3.2.0").stringConf().createWithDefault("spark_shuffle");
      KEYTAB = (new ConfigBuilder("spark.kerberos.keytab")).doc("Location of user's keytab.").version("3.0.0").stringConf().createOptional();
      PRINCIPAL = (new ConfigBuilder("spark.kerberos.principal")).doc("Name of the Kerberos principal.").version("3.0.0").stringConf().createOptional();
      KERBEROS_RELOGIN_PERIOD = (new ConfigBuilder("spark.kerberos.relogin.period")).version("3.0.0").timeConf(TimeUnit.SECONDS).createWithDefaultString("1m");
      KERBEROS_RENEWAL_CREDENTIALS = (new ConfigBuilder("spark.kerberos.renewal.credentials")).doc("Which credentials to use when renewing delegation tokens for executors. Can be either 'keytab', the default, which requires a keytab to be provided, or 'ccache', which uses the local credentials cache.").version("3.0.0").stringConf().checkValues((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"keytab", "ccache"})))).createWithDefault("keytab");
      KERBEROS_FILESYSTEMS_TO_ACCESS = (new ConfigBuilder("spark.kerberos.access.hadoopFileSystems")).doc("Extra Hadoop filesystem URLs for which to request delegation tokens. The filesystem that hosts fs.defaultFS does not need to be listed here.").version("3.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      YARN_KERBEROS_FILESYSTEM_RENEWAL_EXCLUDE = (new ConfigBuilder("spark.yarn.kerberos.renewal.excludeHadoopFileSystems")).doc("The list of Hadoop filesystem URLs whose hosts will be excluded from delegation token renewal at resource scheduler. Currently this is known to work under YARN, so YARN Resource Manager won't renew tokens for the application. Note that as resource scheduler does not renew token, so any application running longer than the original token expiration that tries to use that token will likely fail.").version("3.2.0").stringConf().toSequence().createWithDefault(.MODULE$);
      EXECUTOR_INSTANCES = (new ConfigBuilder("spark.executor.instances")).version("1.0.0").intConf().createOptional();
      PY_FILES = (new ConfigBuilder("spark.yarn.dist.pyFiles")).internal().version("2.2.1").stringConf().toSequence().createWithDefault(.MODULE$);
      TASK_MAX_DIRECT_RESULT_SIZE = (new ConfigBuilder("spark.task.maxDirectResultSize")).version("2.0.0").bytesConf(ByteUnit.BYTE).checkValue((JFunction1.mcZJ.sp)(x$12) -> x$12 < 2147483632L, "The max direct result size is 2GB").createWithDefault(BoxesRunTime.boxToLong(1048576L));
      TASK_MAX_FAILURES = (new ConfigBuilder("spark.task.maxFailures")).version("0.8.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(4));
      TASK_REAPER_ENABLED = (new ConfigBuilder("spark.task.reaper.enabled")).version("2.0.3").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      TASK_REAPER_KILL_TIMEOUT = (new ConfigBuilder("spark.task.reaper.killTimeout")).version("2.0.3").timeConf(TimeUnit.MILLISECONDS).createWithDefault(BoxesRunTime.boxToLong(-1L));
      TASK_REAPER_POLLING_INTERVAL = (new ConfigBuilder("spark.task.reaper.pollingInterval")).version("2.0.3").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("10s");
      TASK_REAPER_THREAD_DUMP = (new ConfigBuilder("spark.task.reaper.threadDump")).version("2.0.3").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      EXCLUDE_ON_FAILURE_ENABLED = (new ConfigBuilder("spark.excludeOnFailure.enabled")).version("3.1.0").withAlternative("spark.blacklist.enabled").booleanConf().createOptional();
      EXCLUDE_ON_FAILURE_ENABLED_APPLICATION = (new ConfigBuilder("spark.excludeOnFailure.application.enabled")).version("4.0.0").booleanConf().createOptional();
      EXCLUDE_ON_FAILURE_ENABLED_TASK_AND_STAGE = (new ConfigBuilder("spark.excludeOnFailure.taskAndStage.enabled")).version("4.0.0").booleanConf().createOptional();
      MAX_TASK_ATTEMPTS_PER_EXECUTOR = (new ConfigBuilder("spark.excludeOnFailure.task.maxTaskAttemptsPerExecutor")).version("3.1.0").withAlternative("spark.blacklist.task.maxTaskAttemptsPerExecutor").intConf().createWithDefault(BoxesRunTime.boxToInteger(1));
      MAX_TASK_ATTEMPTS_PER_NODE = (new ConfigBuilder("spark.excludeOnFailure.task.maxTaskAttemptsPerNode")).version("3.1.0").withAlternative("spark.blacklist.task.maxTaskAttemptsPerNode").intConf().createWithDefault(BoxesRunTime.boxToInteger(2));
      MAX_FAILURES_PER_EXEC = (new ConfigBuilder("spark.excludeOnFailure.application.maxFailedTasksPerExecutor")).version("3.1.0").withAlternative("spark.blacklist.application.maxFailedTasksPerExecutor").intConf().createWithDefault(BoxesRunTime.boxToInteger(2));
      MAX_FAILURES_PER_EXEC_STAGE = (new ConfigBuilder("spark.excludeOnFailure.stage.maxFailedTasksPerExecutor")).version("3.1.0").withAlternative("spark.blacklist.stage.maxFailedTasksPerExecutor").intConf().createWithDefault(BoxesRunTime.boxToInteger(2));
      MAX_FAILED_EXEC_PER_NODE = (new ConfigBuilder("spark.excludeOnFailure.application.maxFailedExecutorsPerNode")).version("3.1.0").withAlternative("spark.blacklist.application.maxFailedExecutorsPerNode").intConf().createWithDefault(BoxesRunTime.boxToInteger(2));
      MAX_FAILED_EXEC_PER_NODE_STAGE = (new ConfigBuilder("spark.excludeOnFailure.stage.maxFailedExecutorsPerNode")).version("3.1.0").withAlternative("spark.blacklist.stage.maxFailedExecutorsPerNode").intConf().createWithDefault(BoxesRunTime.boxToInteger(2));
      EXCLUDE_ON_FAILURE_TIMEOUT_CONF = (new ConfigBuilder("spark.excludeOnFailure.timeout")).version("3.1.0").withAlternative("spark.blacklist.timeout").timeConf(TimeUnit.MILLISECONDS).createOptional();
      EXCLUDE_ON_FAILURE_KILL_ENABLED = (new ConfigBuilder("spark.excludeOnFailure.killExcludedExecutors")).version("3.1.0").withAlternative("spark.blacklist.killBlacklistedExecutors").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED = (new ConfigBuilder("spark.excludeOnFailure.killExcludedExecutors.decommission")).doc("Attempt decommission of excluded nodes instead of going directly to kill").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EXCLUDE_ON_FAILURE_LEGACY_TIMEOUT_CONF = (new ConfigBuilder("spark.scheduler.executorTaskExcludeOnFailureTime")).internal().version("3.1.0").withAlternative("spark.scheduler.executorTaskBlacklistTime").timeConf(TimeUnit.MILLISECONDS).createOptional();
      EXCLUDE_ON_FAILURE_FETCH_FAILURE_ENABLED = (new ConfigBuilder("spark.excludeOnFailure.application.fetchFailure.enabled")).version("3.1.0").withAlternative("spark.blacklist.application.fetchFailure.enabled").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      MAX_EXECUTOR_FAILURES = (new ConfigBuilder("spark.executor.maxNumFailures")).doc("The maximum number of executor failures before failing the application. This configuration only takes effect on YARN and Kubernetes.").version("3.5.0").intConf().createOptional();
      EXECUTOR_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS = (new ConfigBuilder("spark.executor.failuresValidityInterval")).doc("Interval after which executor failures will be considered independent and not accumulate towards the attempt count. This configuration only takes effect on YARN and Kubernetes.").version("3.5.0").timeConf(TimeUnit.MILLISECONDS).createOptional();
      UNREGISTER_OUTPUT_ON_HOST_ON_FETCH_FAILURE = (new ConfigBuilder("spark.files.fetchFailure.unRegisterOutputOnHost")).doc("Whether to un-register all the outputs on the host in condition that we receive  a FetchFailure. This is set default to false, which means, we only un-register the  outputs related to the exact executor(instead of the host) on a FetchFailure.").version("2.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      LISTENER_BUS_EVENT_QUEUE_CAPACITY = (new ConfigBuilder("spark.scheduler.listenerbus.eventqueue.capacity")).doc("The default capacity for event queues. Spark will try to initialize an event queue using capacity specified by `spark.scheduler.listenerbus.eventqueue.queueName.capacity` first. If it's not configured, Spark will use the default capacity specified by this config.").version("2.3.0").intConf().checkValue((JFunction1.mcZI.sp)(x$13) -> x$13 > 0, "The capacity of listener bus event queue must be positive").createWithDefault(BoxesRunTime.boxToInteger(10000));
      LISTENER_BUS_METRICS_MAX_LISTENER_CLASSES_TIMED = (new ConfigBuilder("spark.scheduler.listenerbus.metrics.maxListenerClassesTimed")).internal().doc("The number of listeners that have timers to track the elapsed time ofprocessing events. If 0 is set, disables this feature. If -1 is set,it sets no limit to the number.").version("2.3.0").intConf().checkValue((JFunction1.mcZI.sp)(x$14) -> x$14 >= -1, "The number of listeners should be larger than -1.").createWithDefault(BoxesRunTime.boxToInteger(128));
      LISTENER_BUS_LOG_SLOW_EVENT_ENABLED = (new ConfigBuilder("spark.scheduler.listenerbus.logSlowEvent")).internal().doc("When enabled, log the event that takes too much time to process. This helps us discover the event types that cause performance bottlenecks. The time threshold is controlled by spark.scheduler.listenerbus.logSlowEvent.threshold.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      var10000 = (new ConfigBuilder("spark.scheduler.listenerbus.logSlowEvent.threshold")).internal();
      String var26 = MODULE$.LISTENER_BUS_LOG_SLOW_EVENT_ENABLED().key();
      LISTENER_BUS_LOG_SLOW_EVENT_TIME_THRESHOLD = var10000.doc("The time threshold of whether a event is considered to be taking too much time to process. Log the event if " + var26 + " is true.").version("3.0.0").timeConf(TimeUnit.NANOSECONDS).createWithDefaultString("1s");
      LISTENER_BUS_EXIT_TIMEOUT = (new ConfigBuilder("spark.scheduler.listenerbus.exitTimeout")).doc("The time that event queue waits until the dispatch thread exits when stop is invoked. This is set to 0 by default for graceful shutdown of the event queue, but allow the user to configure the waiting time.").version("4.0.0").timeConf(TimeUnit.MILLISECONDS).checkValue((JFunction1.mcZJ.sp)(x$15) -> x$15 >= 0L, "Listener bus exit timeout must be non-negative duration").createWithDefault(BoxesRunTime.boxToLong(0L));
      METRICS_NAMESPACE = (new ConfigBuilder("spark.metrics.namespace")).version("2.1.0").stringConf().createOptional();
      METRICS_CONF = (new ConfigBuilder("spark.metrics.conf")).version("0.8.0").stringConf().createOptional();
      METRICS_EXECUTORMETRICS_SOURCE_ENABLED = (new ConfigBuilder("spark.metrics.executorMetricsSource.enabled")).doc("Whether to register the ExecutorMetrics source with the metrics system.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      METRICS_STATIC_SOURCES_ENABLED = (new ConfigBuilder("spark.metrics.staticSources.enabled")).doc("Whether to register static sources with the metrics system.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      PYSPARK_DRIVER_PYTHON = (new ConfigBuilder("spark.pyspark.driver.python")).version("2.1.0").stringConf().createOptional();
      PYSPARK_PYTHON = (new ConfigBuilder("spark.pyspark.python")).version("2.1.0").stringConf().createOptional();
      IO_ENCRYPTION_ENABLED = (new ConfigBuilder("spark.io.encryption.enabled")).version("2.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      IO_ENCRYPTION_KEYGEN_ALGORITHM = (new ConfigBuilder("spark.io.encryption.keygen.algorithm")).version("2.1.0").stringConf().createWithDefault("HmacSHA1");
      IO_ENCRYPTION_KEY_SIZE_BITS = (new ConfigBuilder("spark.io.encryption.keySizeBits")).version("2.1.0").intConf().checkValues((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{128, 192, 256}))).createWithDefault(BoxesRunTime.boxToInteger(128));
      IO_CRYPTO_CIPHER_TRANSFORMATION = (new ConfigBuilder("spark.io.crypto.cipher.transformation")).internal().version("2.1.0").stringConf().createWithDefaultString("AES/CTR/NoPadding");
      DRIVER_HOST_ADDRESS = (new ConfigBuilder("spark.driver.host")).doc("Address of driver endpoints.").version("0.7.0").stringConf().createWithDefault(Utils$.MODULE$.localCanonicalHostName());
      DRIVER_PORT = (new ConfigBuilder("spark.driver.port")).doc("Port of driver endpoints.").version("0.7.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(0));
      DRIVER_SUPERVISE = (new ConfigBuilder("spark.driver.supervise")).doc("If true, restarts the driver automatically if it fails with a non-zero exit status. Only has effect in Spark standalone mode.").version("1.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      DRIVER_TIMEOUT = (new ConfigBuilder("spark.driver.timeout")).doc("A timeout for Spark driver in minutes. 0 means infinite. For the positive time value, terminate the driver with the exit code 124 if it runs after timeout duration. To use, it's required to set `spark.plugins=org.apache.spark.deploy.DriverTimeoutPlugin`.").version("4.0.0").timeConf(TimeUnit.MINUTES).checkValue((JFunction1.mcZJ.sp)(v) -> v >= 0L, "The value should be a non-negative time value.").createWithDefaultString("0min");
      DRIVER_BIND_ADDRESS = (new ConfigBuilder("spark.driver.bindAddress")).doc("Address where to bind network listen sockets on the driver.").version("2.1.0").fallbackConf(MODULE$.DRIVER_HOST_ADDRESS());
      BLOCK_MANAGER_PORT = (new ConfigBuilder("spark.blockManager.port")).doc("Port to use for the block manager when a more specific setting is not provided.").version("1.1.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(0));
      DRIVER_BLOCK_MANAGER_PORT = (new ConfigBuilder("spark.driver.blockManager.port")).doc("Port to use for the block manager on the driver.").version("2.1.0").fallbackConf(MODULE$.BLOCK_MANAGER_PORT());
      IGNORE_CORRUPT_FILES = (new ConfigBuilder("spark.files.ignoreCorruptFiles")).doc("Whether to ignore corrupt files. If true, the Spark jobs will continue to run when encountering corrupted or non-existing files and contents that have been read will still be returned.").version("2.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      IGNORE_MISSING_FILES = (new ConfigBuilder("spark.files.ignoreMissingFiles")).doc("Whether to ignore missing files. If true, the Spark jobs will continue to run when encountering missing files and the contents that have been read will still be returned.").version("2.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      APP_CALLER_CONTEXT = (new ConfigBuilder("spark.log.callerContext")).version("2.2.0").stringConf().createOptional();
      var10000 = new ConfigBuilder("spark.log.level");
      var26 = SparkContext$.MODULE$.VALID_LOG_LEVELS().mkString(",");
      TypedConfigBuilder var12 = var10000.doc("When set, overrides any user-defined log settings as if calling SparkContext.setLogLevel() at Spark startup. Valid log levels include: " + var26).version("3.5.0").stringConf().transform((x$16) -> x$16.toUpperCase(Locale.ROOT));
      Function1 var28 = (logLevel) -> BoxesRunTime.boxToBoolean($anonfun$SPARK_LOG_LEVEL$2(logLevel));
      String var10002 = SparkContext$.MODULE$.VALID_LOG_LEVELS().mkString(",");
      SPARK_LOG_LEVEL = var12.checkValue(var28, "Invalid value for 'spark.log.level'. Valid values are " + var10002).createOptional();
      FILES_MAX_PARTITION_BYTES = (new ConfigBuilder("spark.files.maxPartitionBytes")).doc("The maximum number of bytes to pack into a single partition when reading files.").version("2.1.0").bytesConf(ByteUnit.BYTE).createWithDefault(BoxesRunTime.boxToLong(134217728L));
      FILES_OPEN_COST_IN_BYTES = (new ConfigBuilder("spark.files.openCostInBytes")).doc("The estimated cost to open a file, measured by the number of bytes could be scanned in the same time. This is used when putting multiple files into a partition. It's better to over estimate, then the partitions with small files will be faster than partitions with bigger files.").version("2.1.0").bytesConf(ByteUnit.BYTE).createWithDefault(BoxesRunTime.boxToLong(4194304L));
      HADOOP_RDD_IGNORE_EMPTY_SPLITS = (new ConfigBuilder("spark.hadoopRDD.ignoreEmptySplits")).internal().doc("When true, HadoopRDD/NewHadoopRDD will not create partitions for empty input splits.").version("2.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SECRET_REDACTION_PATTERN = (new ConfigBuilder("spark.redaction.regex")).doc("Regex to decide which Spark configuration properties and environment variables in driver and executor environments contain sensitive information. When this regex matches a property key or value, the value is redacted from the environment UI and various logs like YARN and event logs.").version("2.1.2").regexConf().createWithDefault(scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(?i)secret|password|token|access[.]?key")));
      STRING_REDACTION_PATTERN = (new ConfigBuilder("spark.redaction.string.regex")).doc("Regex to decide which parts of strings produced by Spark contain sensitive information. When this regex matches a string part, that string part is replaced by a dummy value. This is currently used to redact the output of SQL explain commands.").version("2.2.0").regexConf().createOptional();
      AUTH_SECRET = (new ConfigBuilder("spark.authenticate.secret")).version("1.0.0").stringConf().createOptional();
      AUTH_SECRET_BIT_LENGTH = (new ConfigBuilder("spark.authenticate.secretBitLength")).version("1.6.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(256));
      NETWORK_AUTH_ENABLED = (new ConfigBuilder("spark.authenticate")).version("1.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      SASL_ENCRYPTION_ENABLED = (new ConfigBuilder("spark.authenticate.enableSaslEncryption")).version("1.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      AUTH_SECRET_FILE = (new ConfigBuilder("spark.authenticate.secret.file")).doc("Path to a file that contains the authentication secret to use. The secret key is loaded from this path on both the driver and the executors if overrides are not set for either entity (see below). File-based secret keys are only allowed when using Kubernetes.").version("3.0.0").stringConf().createOptional();
      AUTH_SECRET_FILE_DRIVER = (new ConfigBuilder("spark.authenticate.secret.driver.file")).doc("Path to a file that contains the authentication secret to use. Loaded by the driver. In Kubernetes client mode it is often useful to set a different secret path for the driver vs. the executors, since the driver may not be running in a pod unlike the executors. If this is set, an accompanying secret file must be specified for the executors. The fallback configuration allows the same path to be used for both the driver and the executors when running in cluster mode. File-based secret keys are only allowed when using Kubernetes.").version("3.0.0").fallbackConf(MODULE$.AUTH_SECRET_FILE());
      AUTH_SECRET_FILE_EXECUTOR = (new ConfigBuilder("spark.authenticate.secret.executor.file")).doc("Path to a file that contains the authentication secret to use. Loaded by the executors only. In Kubernetes client mode it is often useful to set a different secret path for the driver vs. the executors, since the driver may not be running in a pod unlike the executors. If this is set, an accompanying secret file must be specified for the executors. The fallback configuration allows the same path to be used for both the driver and the executors when running in cluster mode. File-based secret keys are only allowed when using Kubernetes.").version("3.0.0").fallbackConf(MODULE$.AUTH_SECRET_FILE());
      BUFFER_WRITE_CHUNK_SIZE = (new ConfigBuilder("spark.buffer.write.chunkSize")).internal().doc("The chunk size in bytes during writing out the bytes of ChunkedByteBuffer.").version("2.3.0").bytesConf(ByteUnit.BYTE).checkValue((JFunction1.mcZJ.sp)(x$17) -> x$17 <= 2147483632L, "The chunk size during writing out the bytes of ChunkedByteBuffer should be less than or equal to " + 2147483632 + ".").createWithDefault(BoxesRunTime.boxToLong(67108864L));
      CHECKPOINT_DIR = (new ConfigBuilder("spark.checkpoint.dir")).doc("Set the default directory for checkpointing. It can be overwritten by SparkContext.setCheckpointDir.").version("4.0.0").stringConf().createOptional();
      CHECKPOINT_COMPRESS = (new ConfigBuilder("spark.checkpoint.compress")).doc("Whether to compress RDD checkpoints. Generally a good idea. Compression will use spark.io.compression.codec.").version("2.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      CACHE_CHECKPOINT_PREFERRED_LOCS_EXPIRE_TIME = (new ConfigBuilder("spark.rdd.checkpoint.cachePreferredLocsExpireTime")).internal().doc("Expire time in minutes for caching preferred locations of checkpointed RDD.Caching preferred locations can relieve query loading to DFS and save the query time. The drawback is that the cached locations can be possibly outdated and lose data locality. If this config is not specified, it will not cache.").version("3.0.0").timeConf(TimeUnit.MINUTES).checkValue((JFunction1.mcZJ.sp)(x$18) -> x$18 > 0L, "The expire time for caching preferred locations cannot be non-positive.").createOptional();
      SHUFFLE_ACCURATE_BLOCK_THRESHOLD = (new ConfigBuilder("spark.shuffle.accurateBlockThreshold")).doc("Threshold in bytes above which the size of shuffle blocks in HighlyCompressedMapStatus is accurately recorded. This helps to prevent OOM by avoiding underestimating shuffle block size when fetch shuffle blocks.").version("2.2.1").bytesConf(ByteUnit.BYTE).createWithDefault(BoxesRunTime.boxToLong(104857600L));
      SHUFFLE_ACCURATE_BLOCK_SKEWED_FACTOR = (new ConfigBuilder("spark.shuffle.accurateBlockSkewedFactor")).doc("A shuffle block is considered as skewed and will be accurately recorded in HighlyCompressedMapStatus if its size is larger than this factor multiplying the median shuffle block size or SHUFFLE_ACCURATE_BLOCK_THRESHOLD. It is recommended to set this parameter to be the same as SKEW_JOIN_SKEWED_PARTITION_FACTOR.Set to -1.0 to disable this feature by default.").version("3.3.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble((double)-1.0F));
      SHUFFLE_MAX_ACCURATE_SKEWED_BLOCK_NUMBER = (new ConfigBuilder("spark.shuffle.maxAccurateSkewedBlockNumber")).internal().doc("Max skewed shuffle blocks allowed to be accurately recorded in HighlyCompressedMapStatus if its size is larger than SHUFFLE_ACCURATE_BLOCK_SKEWED_FACTOR multiplying the median shuffle block size or SHUFFLE_ACCURATE_BLOCK_THRESHOLD.").version("3.3.0").intConf().checkValue((JFunction1.mcZI.sp)(x$19) -> x$19 > 0, "Allowed max accurate skewed block number must be positive.").createWithDefault(BoxesRunTime.boxToInteger(100));
      SHUFFLE_REGISTRATION_TIMEOUT = (new ConfigBuilder("spark.shuffle.registration.timeout")).doc("Timeout in milliseconds for registration to the external shuffle service.").version("2.3.0").timeConf(TimeUnit.MILLISECONDS).createWithDefault(BoxesRunTime.boxToLong(5000L));
      SHUFFLE_REGISTRATION_MAX_ATTEMPTS = (new ConfigBuilder("spark.shuffle.registration.maxAttempts")).doc("When we fail to register to the external shuffle service, we will retry for maxAttempts times.").version("2.3.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(3));
      SHUFFLE_MAX_ATTEMPTS_ON_NETTY_OOM = (new ConfigBuilder("spark.shuffle.maxAttemptsOnNettyOOM")).doc("The max attempts of a shuffle block would retry on Netty OOM issue before throwing the shuffle fetch failure.").version("3.2.0").internal().intConf().createWithDefault(BoxesRunTime.boxToInteger(10));
      REDUCER_MAX_BLOCKS_IN_FLIGHT_PER_ADDRESS = (new ConfigBuilder("spark.reducer.maxBlocksInFlightPerAddress")).doc("This configuration limits the number of remote blocks being fetched per reduce task from a given host port. When a large number of blocks are being requested from a given address in a single fetch or simultaneously, this could crash the serving executor or Node Manager. This is especially useful to reduce the load on the Node Manager when external shuffle is enabled. You can mitigate the issue by setting it to a lower value.").version("2.2.1").intConf().checkValue((JFunction1.mcZI.sp)(x$20) -> x$20 > 0, "The max no. of blocks in flight cannot be non-positive.").createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM = (new ConfigBuilder("spark.network.maxRemoteBlockSizeFetchToMem")).doc("Remote block will be fetched to disk when size of the block is above this threshold in bytes. This is to avoid a giant request takes too much memory. Note this configuration will affect both shuffle fetch and block manager remote block fetch. For users who enabled external shuffle service, this feature can only work when external shuffle service is at least 2.3.0.").version("3.0.0").bytesConf(ByteUnit.BYTE).checkValue((JFunction1.mcZJ.sp)(x$21) -> x$21 <= 2147483135L, "maxRemoteBlockSizeFetchToMem cannot be larger than (Int.MaxValue - 512) bytes.").createWithDefaultString("200m");
      TASK_METRICS_TRACK_UPDATED_BLOCK_STATUSES = (new ConfigBuilder("spark.taskMetrics.trackUpdatedBlockStatuses")).doc("Enable tracking of updatedBlockStatuses in the TaskMetrics. Off by default since tracking the block statuses can use a lot of memory and its not used anywhere within spark.").version("2.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      SHUFFLE_IO_PLUGIN_CLASS = (new ConfigBuilder("spark.shuffle.sort.io.plugin.class")).doc("Name of the class to use for shuffle IO.").version("3.0.0").stringConf().createWithDefault(LocalDiskShuffleDataIO.class.getName());
      SHUFFLE_FILE_BUFFER_SIZE = (new ConfigBuilder("spark.shuffle.file.buffer")).doc("Size of the in-memory buffer for each shuffle file output stream, in KiB unless otherwise specified. These buffers reduce the number of disk seeks and system calls made in creating intermediate shuffle files.").version("1.4.0").bytesConf(ByteUnit.KiB).checkValue((JFunction1.mcZJ.sp)(v) -> v > 0L && v <= 2097151L, "The file buffer size must be positive and less than or equal to " + 2097151 + ".").createWithDefaultString("32k");
      SHUFFLE_FILE_MERGE_BUFFER_SIZE = (new ConfigBuilder("spark.shuffle.file.merge.buffer")).doc("Size of the in-memory buffer for each shuffle file input stream, in KiB unless otherwise specified. These buffers use off-heap buffers and are related to the number of files in the shuffle file. Too large buffers should be avoided.").version("4.0.0").fallbackConf(MODULE$.SHUFFLE_FILE_BUFFER_SIZE());
      SHUFFLE_UNSAFE_FILE_OUTPUT_BUFFER_SIZE = (new ConfigBuilder("spark.shuffle.unsafe.file.output.buffer")).doc("(Deprecated since Spark 4.0, please use 'spark.shuffle.localDisk.file.output.buffer'.)").version("2.3.0").bytesConf(ByteUnit.KiB).checkValue((JFunction1.mcZJ.sp)(v) -> v > 0L && v <= 2097151L, "The buffer size must be positive and less than or equal to " + 2097151 + ".").createWithDefaultString("32k");
      SHUFFLE_LOCAL_DISK_FILE_OUTPUT_BUFFER_SIZE = (new ConfigBuilder("spark.shuffle.localDisk.file.output.buffer")).doc("The file system for this buffer size after each partition is written in all local disk shuffle writers. In KiB unless otherwise specified.").version("4.0.0").fallbackConf(MODULE$.SHUFFLE_UNSAFE_FILE_OUTPUT_BUFFER_SIZE());
      SHUFFLE_DISK_WRITE_BUFFER_SIZE = (new ConfigBuilder("spark.shuffle.spill.diskWriteBufferSize")).doc("The buffer size, in bytes, to use when writing the sorted records to an on-disk file.").version("2.3.0").bytesConf(ByteUnit.BYTE).checkValue((JFunction1.mcZJ.sp)(v) -> v > 12L && v <= 2147483632L, "The buffer size must be greater than 12 and less than or equal to " + 2147483632 + ".").createWithDefault(BoxesRunTime.boxToLong(1048576L));
      UNROLL_MEMORY_CHECK_PERIOD = (new ConfigBuilder("spark.storage.unrollMemoryCheckPeriod")).internal().doc("The memory check period is used to determine how often we should check whether there is a need to request more memory when we try to unroll the given block in memory.").version("2.3.0").longConf().createWithDefault(BoxesRunTime.boxToLong(16L));
      UNROLL_MEMORY_GROWTH_FACTOR = (new ConfigBuilder("spark.storage.unrollMemoryGrowthFactor")).internal().doc("Memory to request as a multiple of the size that used to unroll the block.").version("2.3.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble((double)1.5F));
      KUBERNETES_JARS_AVOID_DOWNLOAD_SCHEMES = (new ConfigBuilder("spark.kubernetes.jars.avoidDownloadSchemes")).doc("Comma-separated list of schemes for which jars will NOT be downloaded to the driver local disk prior to be distributed to executors, only for kubernetes deployment. For use in cases when the jars are big and executor counts are high, concurrent download causes network saturation and timeouts. Wildcard '*' is denoted to not downloading jars for any the schemes.").version("4.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      FORCE_DOWNLOAD_SCHEMES = (new ConfigBuilder("spark.yarn.dist.forceDownloadSchemes")).doc("Comma-separated list of schemes for which resources will be downloaded to the local disk prior to being added to YARN's distributed cache. For use in cases where the YARN service does not support schemes that are supported by Spark, like http, https and ftp, or jars required to be in the local YARN client's classpath. Wildcard '*' is denoted to download resources for all the schemes.").version("2.3.0").stringConf().toSequence().createWithDefault(.MODULE$);
      EXTRA_LISTENERS = (new ConfigBuilder("spark.extraListeners")).doc("Class names of listeners to add to SparkContext during initialization.").version("1.3.0").stringConf().toSequence().createOptional();
      SHUFFLE_SPILL_NUM_ELEMENTS_FORCE_SPILL_THRESHOLD = (new ConfigBuilder("spark.shuffle.spill.numElementsForceSpillThreshold")).internal().doc("The maximum number of elements in memory before forcing the shuffle sorter to spill. By default it's Integer.MAX_VALUE, which means we never force the sorter to spill, until we reach some limitations, like the max page size limitation for the pointer array in the sorter.").version("1.6.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      SHUFFLE_MAP_OUTPUT_PARALLEL_AGGREGATION_THRESHOLD = (new ConfigBuilder("spark.shuffle.mapOutput.parallelAggregationThreshold")).internal().doc("Multi-thread is used when the number of mappers * shuffle partitions is greater than or equal to this threshold. Note that the actual parallelism is calculated by number of mappers * shuffle partitions / this threshold + 1, so this threshold should be positive.").version("2.3.0").intConf().checkValue((JFunction1.mcZI.sp)(v) -> v > 0, "The threshold should be positive.").createWithDefault(BoxesRunTime.boxToInteger(10000000));
      MAX_RESULT_SIZE = (new ConfigBuilder("spark.driver.maxResultSize")).doc("Size limit for results.").version("1.2.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("1g");
      CREDENTIALS_RENEWAL_INTERVAL_RATIO = (new ConfigBuilder("spark.security.credentials.renewalRatio")).doc("Ratio of the credential's expiration time when Spark should fetch new credentials.").version("2.4.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble((double)0.75F));
      CREDENTIALS_RENEWAL_RETRY_WAIT = (new ConfigBuilder("spark.security.credentials.retryWait")).doc("How long to wait before retrying to fetch new credentials after a failure.").version("2.4.0").timeConf(TimeUnit.SECONDS).createWithDefaultString("1h");
      SHUFFLE_SORT_INIT_BUFFER_SIZE = (new ConfigBuilder("spark.shuffle.sort.initialBufferSize")).internal().version("2.1.0").bytesConf(ByteUnit.BYTE).checkValue((JFunction1.mcZJ.sp)(v) -> v > 0L && v <= 2147483647L, "The buffer size must be greater than 0 and less than or equal to " + Integer.MAX_VALUE + ".").createWithDefault(BoxesRunTime.boxToLong(4096L));
      SHUFFLE_CHECKSUM_ENABLED = (new ConfigBuilder("spark.shuffle.checksum.enabled")).doc("Whether to calculate the checksum of shuffle data. If enabled, Spark will calculate the checksum values for each partition data within the map output file and store the values in a checksum file on the disk. When there's shuffle data corruption detected, Spark will try to diagnose the cause (e.g., network issue, disk issue, etc.) of the corruption by using the checksum file.").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SHUFFLE_CHECKSUM_ALGORITHM = (new ConfigBuilder("spark.shuffle.checksum.algorithm")).doc("The algorithm is used to calculate the shuffle checksum. Currently, it only supports built-in algorithms of JDK.").version("3.2.0").stringConf().transform((x$22) -> x$22.toUpperCase(Locale.ROOT)).checkValues((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ADLER32", "CRC32", "CRC32C"})))).createWithDefault("ADLER32");
      SHUFFLE_COMPRESS = (new ConfigBuilder("spark.shuffle.compress")).doc("Whether to compress shuffle output. Compression will use spark.io.compression.codec.").version("0.6.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SHUFFLE_SPILL_COMPRESS = (new ConfigBuilder("spark.shuffle.spill.compress")).doc("Whether to compress data spilled during shuffles. Compression will use spark.io.compression.codec.").version("0.9.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      MAP_STATUS_COMPRESSION_CODEC = (new ConfigBuilder("spark.shuffle.mapStatus.compression.codec")).internal().doc("The codec used to compress MapStatus, which is generated by ShuffleMapTask. By default, Spark provides four codecs: lz4, lzf, snappy, and zstd. You can also use fully qualified class names to specify the codec.").version("3.0.0").stringConf().createWithDefault(CompressionCodec$.MODULE$.ZSTD());
      SHUFFLE_SPILL_INITIAL_MEM_THRESHOLD = (new ConfigBuilder("spark.shuffle.spill.initialMemoryThreshold")).internal().doc("Initial threshold for the size of a collection before we start tracking its memory usage.").version("1.1.1").bytesConf(ByteUnit.BYTE).createWithDefault(BoxesRunTime.boxToLong(5242880L));
      SHUFFLE_SPILL_BATCH_SIZE = (new ConfigBuilder("spark.shuffle.spill.batchSize")).internal().doc("Size of object batches when reading/writing from serializers.").version("0.9.0").longConf().createWithDefault(BoxesRunTime.boxToLong(10000L));
      SHUFFLE_MERGE_PREFER_NIO = (new ConfigBuilder("spark.file.transferTo")).doc("If true, NIO's `transferTo` API will be preferentially used when merging Spark shuffle spill files").version("1.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SHUFFLE_SORT_BYPASS_MERGE_THRESHOLD = (new ConfigBuilder("spark.shuffle.sort.bypassMergeThreshold")).doc("In the sort-based shuffle manager, avoid merge-sorting data if there is no map-side aggregation and there are at most this many reduce partitions").version("1.1.1").intConf().createWithDefault(BoxesRunTime.boxToInteger(200));
      SHUFFLE_MANAGER = (new ConfigBuilder("spark.shuffle.manager")).version("1.1.0").stringConf().createWithDefault("sort");
      SHUFFLE_REDUCE_LOCALITY_ENABLE = (new ConfigBuilder("spark.shuffle.reduceLocality.enabled")).doc("Whether to compute locality preferences for reduce tasks").version("1.5.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SHUFFLE_MAPOUTPUT_MIN_SIZE_FOR_BROADCAST = (new ConfigBuilder("spark.shuffle.mapOutput.minSizeForBroadcast")).doc("The size at which we use Broadcast to send the map output statuses to the executors.").version("2.0.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("512k");
      SHUFFLE_MAPOUTPUT_DISPATCHER_NUM_THREADS = (new ConfigBuilder("spark.shuffle.mapOutput.dispatcher.numThreads")).version("2.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(8));
      SHUFFLE_DETECT_CORRUPT = (new ConfigBuilder("spark.shuffle.detectCorrupt")).doc("Whether to detect any corruption in fetched blocks.").version("2.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SHUFFLE_DETECT_CORRUPT_MEMORY = (new ConfigBuilder("spark.shuffle.detectCorrupt.useExtraMemory")).doc("If enabled, part of a compressed/encrypted stream will be de-compressed/de-crypted by using extra memory to detect early corruption. Any IOException thrown will cause the task to be retried once and if it fails again with same exception, then FetchFailedException will be thrown to retry previous stage").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      SHUFFLE_SYNC = (new ConfigBuilder("spark.shuffle.sync")).doc("Whether to force outstanding writes to disk.").version("0.8.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      SHUFFLE_UNSAFE_FAST_MERGE_ENABLE = (new ConfigBuilder("spark.shuffle.unsafe.fastMergeEnabled")).doc("Whether to perform a fast spill merge.").version("1.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SHUFFLE_SORT_USE_RADIXSORT = (new ConfigBuilder("spark.shuffle.sort.useRadixSort")).doc("Whether to use radix sort for sorting in-memory partition ids. Radix sort is much faster, but requires additional memory to be reserved memory as pointers are added.").version("2.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SHUFFLE_MIN_NUM_PARTS_TO_HIGHLY_COMPRESS = (new ConfigBuilder("spark.shuffle.minNumPartitionsToHighlyCompress")).internal().doc("Number of partitions to determine if MapStatus should use HighlyCompressedMapStatus").version("2.4.0").intConf().checkValue((JFunction1.mcZI.sp)(v) -> v > 0, "The value should be a positive integer.").createWithDefault(BoxesRunTime.boxToInteger(2000));
      SHUFFLE_USE_OLD_FETCH_PROTOCOL = (new ConfigBuilder("spark.shuffle.useOldFetchProtocol")).doc("Whether to use the old protocol while doing the shuffle block fetching. It is only enabled while we need the compatibility in the scenario of new Spark version job fetching shuffle blocks from old version external shuffle service.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      ConfigBuilder var13 = new ConfigBuilder("spark.shuffle.readHostLocalDisk");
      String var29 = MODULE$.SHUFFLE_USE_OLD_FETCH_PROTOCOL().key();
      SHUFFLE_HOST_LOCAL_DISK_READING_ENABLED = var13.doc("If enabled (and `" + var29 + "` is disabled, shuffle blocks requested from those block managers which are running on the same host are read from the disk directly instead of being fetched as remote blocks over the network.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      var13 = new ConfigBuilder("spark.storage.localDiskByExecutors.cacheSize");
      var29 = MODULE$.SHUFFLE_HOST_LOCAL_DISK_READING_ENABLED().key();
      STORAGE_LOCAL_DISK_BY_EXECUTORS_CACHE_SIZE = var13.doc("The max number of executors for which the local dirs are stored. This size is both applied for the driver and both for the executors side to avoid having an unbounded store. This cache will be used to avoid the network in case of fetching disk persisted RDD blocks or shuffle blocks (when `" + var29 + "` is set) from the same host.").version("3.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1000));
      MEMORY_MAP_LIMIT_FOR_TESTS = (new ConfigBuilder("spark.storage.memoryMapLimitForTests")).internal().doc("For testing only, controls the size of chunks when memory mapping a file").version("2.3.0").bytesConf(ByteUnit.BYTE).createWithDefault(BoxesRunTime.boxToLong(2147483632L));
      BARRIER_SYNC_TIMEOUT = (new ConfigBuilder("spark.barrier.sync.timeout")).doc("The timeout in seconds for each barrier() call from a barrier task. If the coordinator didn't receive all the sync messages from barrier tasks within the configured time, throw a SparkException to fail all the tasks. The default value is set to 31536000(3600 * 24 * 365) so the barrier() call shall wait for one year.").version("2.4.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(v) -> v > 0L, "The value should be a positive time value.").createWithDefaultString("365d");
      UNSCHEDULABLE_TASKSET_TIMEOUT = (new ConfigBuilder("spark.scheduler.excludeOnFailure.unschedulableTaskSetTimeout")).doc("The timeout in seconds to wait to acquire a new executor and schedule a task before aborting a TaskSet which is unschedulable because all executors are excluded due to failures.").version("3.1.0").withAlternative("spark.scheduler.blacklist.unschedulableTaskSetTimeout").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(v) -> v >= 0L, "The value should be a non negative time value.").createWithDefault(BoxesRunTime.boxToLong(120L));
      BARRIER_MAX_CONCURRENT_TASKS_CHECK_INTERVAL = (new ConfigBuilder("spark.scheduler.barrier.maxConcurrentTasksCheck.interval")).doc("Time in seconds to wait between a max concurrent tasks check failure and the next check. A max concurrent tasks check ensures the cluster can launch more concurrent tasks than required by a barrier stage on job submitted. The check can fail in case a cluster has just started and not enough executors have registered, so we wait for a little while and try to perform the check again. If the check fails more than a configured max failure times for a job then fail current job submission. Note this config only applies to jobs that contain one or more barrier stages, we won't perform the check on non-barrier jobs.").version("2.4.0").timeConf(TimeUnit.SECONDS).createWithDefaultString("15s");
      BARRIER_MAX_CONCURRENT_TASKS_CHECK_MAX_FAILURES = (new ConfigBuilder("spark.scheduler.barrier.maxConcurrentTasksCheck.maxFailures")).doc("Number of max concurrent tasks check failures allowed before fail a job submission. A max concurrent tasks check ensures the cluster can launch more concurrent tasks than required by a barrier stage on job submitted. The check can fail in case a cluster has just started and not enough executors have registered, so we wait for a little while and try to perform the check again. If the check fails more than a configured max failure times for a job then fail current job submission. Note this config only applies to jobs that contain one or more barrier stages, we won't perform the check on non-barrier jobs.").version("2.4.0").intConf().checkValue((JFunction1.mcZI.sp)(v) -> v > 0, "The max failures should be a positive value.").createWithDefault(BoxesRunTime.boxToInteger(40));
      NUM_CANCELLED_JOB_GROUPS_TO_TRACK = (new ConfigBuilder("spark.scheduler.numCancelledJobGroupsToTrack")).doc("The maximum number of tracked job groups that are cancelled with `cancelJobGroupAndFutureJobs`. If this maximum number is hit, the oldest job group will no longer be tracked that future jobs belonging to this job group will not be cancelled.").version("4.0.0").intConf().checkValue((JFunction1.mcZI.sp)(v) -> v > 0, "The size of the set should be a positive value.").createWithDefault(BoxesRunTime.boxToInteger(1000));
      UNSAFE_EXCEPTION_ON_MEMORY_LEAK = (new ConfigBuilder("spark.unsafe.exceptionOnMemoryLeak")).internal().version("1.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      UNSAFE_SORTER_SPILL_READ_AHEAD_ENABLED = (new ConfigBuilder("spark.unsafe.sorter.spill.read.ahead.enabled")).internal().version("2.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UNSAFE_SORTER_SPILL_READER_BUFFER_SIZE = (new ConfigBuilder("spark.unsafe.sorter.spill.reader.buffer.size")).internal().version("2.1.0").bytesConf(ByteUnit.BYTE).checkValue((JFunction1.mcZJ.sp)(v) -> 1048576L <= v && v <= 16777216L, "The value must be in allowed range [1,048,576, " + 16777216 + "].").createWithDefault(BoxesRunTime.boxToLong(1048576L));
      DEFAULT_PLUGINS_LIST = "spark.plugins.defaultList";
      PLUGINS = (new ConfigBuilder("spark.plugins")).withPrepended(MODULE$.DEFAULT_PLUGINS_LIST(), ",").doc("Comma-separated list of class names implementing org.apache.spark.api.plugin.SparkPlugin to load into the application.").version("3.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      CLEANER_PERIODIC_GC_INTERVAL = (new ConfigBuilder("spark.cleaner.periodicGC.interval")).version("1.6.0").timeConf(TimeUnit.SECONDS).createWithDefaultString("30min");
      CLEANER_REFERENCE_TRACKING = (new ConfigBuilder("spark.cleaner.referenceTracking")).version("1.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      CLEANER_REFERENCE_TRACKING_BLOCKING = (new ConfigBuilder("spark.cleaner.referenceTracking.blocking")).version("1.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      CLEANER_REFERENCE_TRACKING_BLOCKING_SHUFFLE = (new ConfigBuilder("spark.cleaner.referenceTracking.blocking.shuffle")).version("1.1.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      CLEANER_REFERENCE_TRACKING_CLEAN_CHECKPOINTS = (new ConfigBuilder("spark.cleaner.referenceTracking.cleanCheckpoints")).version("1.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EXECUTOR_LOGS_ROLLING_STRATEGY = (new ConfigBuilder("spark.executor.logs.rolling.strategy")).version("1.1.0").stringConf().createWithDefault("");
      EXECUTOR_LOGS_ROLLING_TIME_INTERVAL = (new ConfigBuilder("spark.executor.logs.rolling.time.interval")).version("1.1.0").stringConf().createWithDefault("daily");
      EXECUTOR_LOGS_ROLLING_MAX_SIZE = (new ConfigBuilder("spark.executor.logs.rolling.maxSize")).version("1.4.0").stringConf().createWithDefault(Integer.toString(1048576));
      EXECUTOR_LOGS_ROLLING_MAX_RETAINED_FILES = (new ConfigBuilder("spark.executor.logs.rolling.maxRetainedFiles")).version("1.1.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(-1));
      EXECUTOR_LOGS_ROLLING_ENABLE_COMPRESSION = (new ConfigBuilder("spark.executor.logs.rolling.enableCompression")).version("2.0.2").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      MASTER_REST_SERVER_ENABLED = (new ConfigBuilder("spark.master.rest.enabled")).version("1.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      MASTER_REST_SERVER_HOST = (new ConfigBuilder("spark.master.rest.host")).doc("Specifies the host of the Master REST API endpoint").version("4.0.0").stringConf().createOptional();
      MASTER_REST_SERVER_PORT = (new ConfigBuilder("spark.master.rest.port")).version("1.3.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(6066));
      MASTER_REST_SERVER_MAX_THREADS = (new ConfigBuilder("spark.master.rest.maxThreads")).doc("Maximum number of threads to use in the Spark Master REST API Server.").version("4.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(200));
      MASTER_REST_SERVER_FILTERS = (new ConfigBuilder("spark.master.rest.filters")).doc("Comma separated list of filter class names to apply to the Spark Master REST API.").version("4.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      MASTER_REST_SERVER_VIRTUAL_THREADS = (new ConfigBuilder("spark.master.rest.virtualThread.enabled")).doc("If true, Spark master tries to use Java 21 virtual thread for REST API.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      MASTER_UI_PORT = (new ConfigBuilder("spark.master.ui.port")).version("1.1.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(8080));
      MASTER_UI_HISTORY_SERVER_URL = (new ConfigBuilder("spark.master.ui.historyServerUrl")).doc("The URL where Spark history server is running. Please note that this assumes that all Spark jobs share the same event log location where the history server accesses.").version("4.0.0").stringConf().createOptional();
      MASTER_USE_APP_NAME_AS_APP_ID = (new ConfigBuilder("spark.master.useAppNameAsAppId.enabled")).internal().doc("(Experimental) If true, Spark master uses the user-provided appName for appId.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      MASTER_USE_DRIVER_ID_AS_APP_NAME = (new ConfigBuilder("spark.master.useDriverIdAsAppName.enabled")).internal().doc("(Experimental) If true, Spark master tries to set driver ID as appName.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      IO_COMPRESSION_SNAPPY_BLOCKSIZE = (new ConfigBuilder("spark.io.compression.snappy.blockSize")).doc("Block size in bytes used in Snappy compression, in the case when Snappy compression codec is used. Lowering this block size will also lower shuffle memory usage when Snappy is used").version("1.4.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("32k");
      IO_COMPRESSION_LZ4_BLOCKSIZE = (new ConfigBuilder("spark.io.compression.lz4.blockSize")).doc("Block size in bytes used in LZ4 compression, in the case when LZ4 compressioncodec is used. Lowering this block size will also lower shuffle memory usage when LZ4 is used.").version("1.4.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("32k");
      IO_COMPRESSION_CODEC = (new ConfigBuilder("spark.io.compression.codec")).doc("The codec used to compress internal data such as RDD partitions, event log, broadcast variables and shuffle outputs. By default, Spark provides four codecs: lz4, lzf, snappy, and zstd. You can also use fully qualified class names to specify the codec").version("0.8.0").stringConf().createWithDefaultString(CompressionCodec$.MODULE$.LZ4());
      IO_COMPRESSION_ZSTD_BUFFERSIZE = (new ConfigBuilder("spark.io.compression.zstd.bufferSize")).doc("Buffer size in bytes used in Zstd compression, in the case when Zstd compression codec is used. Lowering this size will lower the shuffle memory usage when Zstd is used, but it might increase the compression cost because of excessive JNI call overhead").version("2.3.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("32k");
      IO_COMPRESSION_ZSTD_BUFFERPOOL_ENABLED = (new ConfigBuilder("spark.io.compression.zstd.bufferPool.enabled")).doc("If true, enable buffer pool of ZSTD JNI library.").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      IO_COMPRESSION_ZSTD_WORKERS = (new ConfigBuilder("spark.io.compression.zstd.workers")).doc("Thread size spawned to compress in parallel when using Zstd. When the value is 0, no worker is spawned, it works in single-threaded mode. When value > 0, it triggers asynchronous mode, corresponding number of threads are spawned. More workers improve performance, but also increase memory cost.").version("4.0.0").intConf().checkValue((JFunction1.mcZI.sp)(x$23) -> x$23 >= 0, "The number of workers must not be negative.").createWithDefault(BoxesRunTime.boxToInteger(0));
      IO_COMPRESSION_ZSTD_LEVEL = (new ConfigBuilder("spark.io.compression.zstd.level")).doc("Compression level for Zstd compression codec. Increasing the compression level will result in better compression at the expense of more CPU and memory").version("2.3.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1));
      IO_COMPRESSION_LZF_PARALLEL = (new ConfigBuilder("spark.io.compression.lzf.parallel.enabled")).doc("When true, LZF compression will use multiple threads to compress data in parallel.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      IO_WARNING_LARGEFILETHRESHOLD = (new ConfigBuilder("spark.io.warning.largeFileThreshold")).internal().doc("If the size in bytes of a file loaded by Spark exceeds this threshold, a warning is logged with the possible reasons.").version("3.0.0").bytesConf(ByteUnit.BYTE).createWithDefault(BoxesRunTime.boxToLong(1073741824L));
      EVENT_LOG_COMPRESSION_CODEC = (new ConfigBuilder("spark.eventLog.compression.codec")).doc("The codec used to compress event log. By default, Spark provides four codecs: lz4, lzf, snappy, and zstd. You can also use fully qualified class names to specify the codec.").version("3.0.0").stringConf().createWithDefault(CompressionCodec$.MODULE$.ZSTD());
      BUFFER_SIZE = (new ConfigBuilder("spark.buffer.size")).version("0.5.0").intConf().checkValue((JFunction1.mcZI.sp)(x$24) -> x$24 >= 0, "The buffer size must not be negative").createWithDefault(BoxesRunTime.boxToInteger(65536));
      LOCALITY_WAIT_PROCESS = (new ConfigBuilder("spark.locality.wait.process")).version("0.8.0").fallbackConf(MODULE$.LOCALITY_WAIT());
      LOCALITY_WAIT_NODE = (new ConfigBuilder("spark.locality.wait.node")).version("0.8.0").fallbackConf(MODULE$.LOCALITY_WAIT());
      LOCALITY_WAIT_RACK = (new ConfigBuilder("spark.locality.wait.rack")).version("0.8.0").fallbackConf(MODULE$.LOCALITY_WAIT());
      REDUCER_MAX_SIZE_IN_FLIGHT = (new ConfigBuilder("spark.reducer.maxSizeInFlight")).doc("Maximum size of map outputs to fetch simultaneously from each reduce task, in MiB unless otherwise specified. Since each output requires us to create a buffer to receive it, this represents a fixed memory overhead per reduce task, so keep it small unless you have a large amount of memory").version("1.4.0").bytesConf(ByteUnit.MiB).createWithDefaultString("48m");
      REDUCER_MAX_REQS_IN_FLIGHT = (new ConfigBuilder("spark.reducer.maxReqsInFlight")).doc("This configuration limits the number of remote requests to fetch blocks at any given point. When the number of hosts in the cluster increase, it might lead to very large number of inbound connections to one or more nodes, causing the workers to fail under load. By allowing it to limit the number of fetch requests, this scenario can be mitigated").version("2.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      BROADCAST_COMPRESS = (new ConfigBuilder("spark.broadcast.compress")).doc("Whether to compress broadcast variables before sending them. Generally a good idea. Compression will use spark.io.compression.codec").version("0.6.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      BROADCAST_BLOCKSIZE = (new ConfigBuilder("spark.broadcast.blockSize")).doc("Size of each piece of a block for TorrentBroadcastFactory, in KiB unless otherwise specified. Too large a value decreases parallelism during broadcast (makes it slower); however, if it is too small, BlockManager might take a performance hit").version("0.5.0").bytesConf(ByteUnit.KiB).createWithDefaultString("4m");
      BROADCAST_CHECKSUM = (new ConfigBuilder("spark.broadcast.checksum")).doc("Whether to enable checksum for broadcast. If enabled, broadcasts will include a checksum, which can help detect corrupted blocks, at the cost of computing and sending a little more data. It's possible to disable it if the network has other mechanisms to guarantee data won't be corrupted during broadcast").version("2.1.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      BROADCAST_FOR_UDF_COMPRESSION_THRESHOLD = (new ConfigBuilder("spark.broadcast.UDFCompressionThreshold")).doc("The threshold at which user-defined functions (UDFs) and Python RDD commands are compressed by broadcast in bytes unless otherwise specified").version("3.0.0").bytesConf(ByteUnit.BYTE).checkValue((JFunction1.mcZJ.sp)(v) -> v >= 0L, "The threshold should be non-negative.").createWithDefault(BoxesRunTime.boxToLong(1048576L));
      RDD_COMPRESS = (new ConfigBuilder("spark.rdd.compress")).doc("Whether to compress serialized RDD partitions (e.g. for StorageLevel.MEMORY_ONLY_SER in Scala or StorageLevel.MEMORY_ONLY in Python). Can save substantial space at the cost of some extra CPU time. Compression will use spark.io.compression.codec").version("0.6.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      RDD_PARALLEL_LISTING_THRESHOLD = (new ConfigBuilder("spark.rdd.parallelListingThreshold")).version("2.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(10));
      RDD_LIMIT_INITIAL_NUM_PARTITIONS = (new ConfigBuilder("spark.rdd.limit.initialNumPartitions")).version("3.4.0").intConf().checkValue((JFunction1.mcZI.sp)(x$25) -> x$25 > 0, "value should be positive").createWithDefault(BoxesRunTime.boxToInteger(1));
      RDD_LIMIT_SCALE_UP_FACTOR = (new ConfigBuilder("spark.rdd.limit.scaleUpFactor")).version("2.1.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(4));
      SERIALIZER = (new ConfigBuilder("spark.serializer")).version("0.5.0").stringConf().createWithDefault("org.apache.spark.serializer.JavaSerializer");
      SERIALIZER_OBJECT_STREAM_RESET = (new ConfigBuilder("spark.serializer.objectStreamReset")).version("1.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(100));
      SERIALIZER_EXTRA_DEBUG_INFO = (new ConfigBuilder("spark.serializer.extraDebugInfo")).version("1.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      JARS = (new ConfigBuilder("spark.jars")).version("0.9.0").stringConf().toSequence().createWithDefault(.MODULE$);
      FILES = (new ConfigBuilder("spark.files")).version("1.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      ARCHIVES = (new ConfigBuilder("spark.archives")).version("3.1.0").doc("Comma-separated list of archives to be extracted into the working directory of each executor. .jar, .tar.gz, .tgz and .zip are supported. You can specify the directory name to unpack via adding '#' after the file name to unpack, for example, 'file.zip#directory'. This configuration is experimental.").stringConf().toSequence().createWithDefault(.MODULE$);
      SUBMIT_DEPLOY_MODE = (new ConfigBuilder("spark.submit.deployMode")).version("1.5.0").stringConf().createWithDefault("client");
      SUBMIT_PYTHON_FILES = (new ConfigBuilder("spark.submit.pyFiles")).version("1.0.1").stringConf().toSequence().createWithDefault(.MODULE$);
      SCHEDULER_ALLOCATION_FILE = (new ConfigBuilder("spark.scheduler.allocation.file")).version("0.8.1").stringConf().createOptional();
      SCHEDULER_MIN_REGISTERED_RESOURCES_RATIO = (new ConfigBuilder("spark.scheduler.minRegisteredResourcesRatio")).version("1.1.1").doubleConf().createOptional();
      SCHEDULER_MAX_REGISTERED_RESOURCE_WAITING_TIME = (new ConfigBuilder("spark.scheduler.maxRegisteredResourcesWaitingTime")).version("1.1.1").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("30s");
      SCHEDULER_MODE = (new ConfigBuilder("spark.scheduler.mode")).version("0.8.0").stringConf().transform((x$26) -> x$26.toUpperCase(Locale.ROOT)).createWithDefault(SchedulingMode$.MODULE$.FIFO().toString());
      SCHEDULER_REVIVE_INTERVAL = (new ConfigBuilder("spark.scheduler.revive.interval")).version("0.8.1").timeConf(TimeUnit.MILLISECONDS).createOptional();
      SPECULATION_ENABLED = (new ConfigBuilder("spark.speculation")).version("0.6.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      SPECULATION_INTERVAL = (new ConfigBuilder("spark.speculation.interval")).version("0.6.0").timeConf(TimeUnit.MILLISECONDS).createWithDefault(BoxesRunTime.boxToLong(100L));
      SPECULATION_MULTIPLIER = (new ConfigBuilder("spark.speculation.multiplier")).version("0.6.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble((double)3.0F));
      SPECULATION_QUANTILE = (new ConfigBuilder("spark.speculation.quantile")).version("0.6.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble(0.9));
      SPECULATION_MIN_THRESHOLD = (new ConfigBuilder("spark.speculation.minTaskRuntime")).doc("Minimum amount of time a task runs before being considered for speculation. This can be used to avoid launching speculative copies of tasks that are very short.").version("3.2.0").timeConf(TimeUnit.MILLISECONDS).createWithDefault(BoxesRunTime.boxToLong(100L));
      SPECULATION_TASK_DURATION_THRESHOLD = (new ConfigBuilder("spark.speculation.task.duration.threshold")).doc("Task duration after which scheduler would try to speculative run the task. If provided, tasks would be speculatively run if current stage contains less tasks than or equal to the number of slots on a single executor and the task is taking longer time than the threshold. This config helps speculate stage with very few tasks. Regular speculation configs may also apply if the executor slots are large enough. E.g. tasks might be re-launched if there are enough successful runs even though the threshold hasn't been reached. The number of slots is computed based on the conf values of spark.executor.cores and spark.task.cpus minimum 1.").version("3.0.0").timeConf(TimeUnit.MILLISECONDS).createOptional();
      SPECULATION_EFFICIENCY_TASK_PROCESS_RATE_MULTIPLIER = (new ConfigBuilder("spark.speculation.efficiency.processRateMultiplier")).doc("A multiplier that used when evaluating inefficient tasks. The higher the multiplier is, the more tasks will be possibly considered as inefficient.").version("3.4.0").doubleConf().checkValue((JFunction1.mcZD.sp)(v) -> v > (double)0.0F && v <= (double)1.0F, "multiplier must be in (0.0, 1.0]").createWithDefault(BoxesRunTime.boxToDouble((double)0.75F));
      var13 = new ConfigBuilder("spark.speculation.efficiency.longRunTaskFactor");
      var29 = MODULE$.SPECULATION_MULTIPLIER().key();
      SPECULATION_EFFICIENCY_TASK_DURATION_FACTOR = var13.doc("A task will be speculated anyway as long as its duration has exceeded the value of multiplying the factor and the time threshold (either be " + var29 + " * successfulTaskDurations.median or " + MODULE$.SPECULATION_MIN_THRESHOLD().key() + ") regardless of it's data process rate is good or not. This avoids missing the inefficient tasks when task slow isn't related to data process rate.").version("3.4.0").doubleConf().checkValue((JFunction1.mcZD.sp)(x$27) -> x$27 >= (double)1.0F, "Duration factor must be >= 1.0").createWithDefault(BoxesRunTime.boxToDouble((double)2.0F));
      var13 = new ConfigBuilder("spark.speculation.efficiency.enabled");
      var29 = MODULE$.SPECULATION_EFFICIENCY_TASK_DURATION_FACTOR().key();
      SPECULATION_EFFICIENCY_ENABLE = var13.doc("When set to true, spark will evaluate the efficiency of task processing through the stage task metrics or its duration, and only need to speculate the inefficient tasks. A task is inefficient when 1)its data process rate is less than the average data process rate of all successful tasks in the stage multiplied by a multiplier or 2)its duration has exceeded the value of multiplying " + var29 + " and the time threshold (either be " + MODULE$.SPECULATION_MULTIPLIER().key() + " * successfulTaskDurations.median or " + MODULE$.SPECULATION_MIN_THRESHOLD().key() + ").").version("3.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      var13 = new ConfigBuilder("spark.decommission.enabled");
      var29 = MODULE$.STORAGE_DECOMMISSION_RDD_BLOCKS_ENABLED().key();
      DECOMMISSION_ENABLED = var13.doc("When decommission enabled, Spark will try its best to shutdown the executor gracefully. Spark will try to migrate all the RDD blocks (controlled by " + var29 + ") and shuffle blocks (controlled by " + MODULE$.STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED().key() + ") from the decommissioning executor to a remote executor when " + MODULE$.STORAGE_DECOMMISSION_ENABLED().key() + " is enabled. With decommission enabled, Spark will also decommission an executor instead of killing when " + MODULE$.DYN_ALLOCATION_ENABLED().key() + " enabled.").version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EXECUTOR_DECOMMISSION_KILL_INTERVAL = (new ConfigBuilder("spark.executor.decommission.killInterval")).doc("Duration after which a decommissioned executor will be killed forcefully *by an outside* (e.g. non-spark) service. This config is useful for cloud environments where we know in advance when an executor is going to go down after decommissioning signal i.e. around 2 mins in aws spot nodes, 1/2 hrs in spot block nodes etc. This config is currently used to decide what tasks running on decommission executors to speculate.").version("3.1.0").timeConf(TimeUnit.SECONDS).createOptional();
      EXECUTOR_DECOMMISSION_FORCE_KILL_TIMEOUT = (new ConfigBuilder("spark.executor.decommission.forceKillTimeout")).doc("Duration after which a Spark will force a decommissioning executor to exit. this should be set to a high value in most situations as low values will prevent  block migrations from having enough time to complete.").version("3.2.0").timeConf(TimeUnit.SECONDS).createOptional();
      EXECUTOR_DECOMMISSION_SIGNAL = (new ConfigBuilder("spark.executor.decommission.signal")).doc("The signal that used to trigger the executor to start decommission.").version("3.2.0").stringConf().createWithDefaultString("PWR");
      STAGING_DIR = (new ConfigBuilder("spark.yarn.stagingDir")).doc("Staging directory used while submitting applications.").version("2.0.0").stringConf().createOptional();
      BUFFER_PAGESIZE = (new ConfigBuilder("spark.buffer.pageSize")).doc("The amount of memory used per page in bytes").version("1.5.0").bytesConf(ByteUnit.BYTE).createOptional();
      RESOURCE_PROFILE_MERGE_CONFLICTS = (new ConfigBuilder("spark.scheduler.resource.profileMergeConflicts")).doc("If set to true, Spark will merge ResourceProfiles when different profiles are specified in RDDs that get combined into a single stage. When they are merged, Spark chooses the maximum of each resource and creates a new ResourceProfile. The default of false results in Spark throwing an exception if multiple different ResourceProfiles are found in RDDs going into the same stage.").version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      STANDALONE_SUBMIT_WAIT_APP_COMPLETION = (new ConfigBuilder("spark.standalone.submit.waitAppCompletion")).doc("In standalone cluster mode, controls whether the client waits to exit until the application completes. If set to true, the client process will stay alive polling the driver's status. Otherwise, the client process will exit after submission.").version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EXECUTOR_ALLOW_SPARK_CONTEXT = (new ConfigBuilder("spark.executor.allowSparkContext")).doc("If set to true, SparkContext can be created in executors.").version("3.0.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      EXECUTOR_ALLOW_SYNC_LOG_LEVEL = (new ConfigBuilder("spark.executor.syncLogLevel.enabled")).doc("If set to true, log level applied through SparkContext.setLogLevel() method will be propagated to all executors.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      KILL_ON_FATAL_ERROR_DEPTH = (new ConfigBuilder("spark.executor.killOnFatalError.depth")).doc("The max depth of the exception chain in a failed task Spark will search for a fatal error to check whether it should kill the JVM process. 0 means not checking any fatal error, 1 means checking only the exception but not the cause, and so on.").internal().version("3.1.0").intConf().checkValue((JFunction1.mcZI.sp)(x$28) -> x$28 >= 0, "needs to be a non-negative value").createWithDefault(BoxesRunTime.boxToInteger(5));
      STAGE_MAX_CONSECUTIVE_ATTEMPTS = (new ConfigBuilder("spark.stage.maxConsecutiveAttempts")).doc("Number of consecutive stage attempts allowed before a stage is aborted.").version("2.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(4));
      var13 = new ConfigBuilder("spark.stage.ignoreDecommissionFetchFailure");
      var29 = MODULE$.STAGE_MAX_CONSECUTIVE_ATTEMPTS().key();
      STAGE_IGNORE_DECOMMISSION_FETCH_FAILURE = var13.doc("Whether ignore stage fetch failure caused by executor decommission when count " + var29).version("3.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      var13 = (new ConfigBuilder("spark.scheduler.maxRetainedRemovedDecommissionExecutors")).internal();
      var29 = MODULE$.STAGE_IGNORE_DECOMMISSION_FETCH_FAILURE().key();
      SCHEDULER_MAX_RETAINED_REMOVED_EXECUTORS = var13.doc("Max number of removed executors by decommission to retain. This affects whether fetch failure caused by removed decommissioned executors could be ignored when " + var29 + " is enabled.").version("3.4.0").intConf().checkValue((JFunction1.mcZI.sp)(x$29) -> x$29 >= 0, "needs to be a non-negative value").createWithDefault(BoxesRunTime.boxToInteger(0));
      SCHEDULER_MAX_RETAINED_UNKNOWN_EXECUTORS = (new ConfigBuilder("spark.scheduler.maxRetainedUnknownDecommissionExecutors")).internal().doc("Max number of unknown executors by decommission to retain. This affects whether executor could receive decommission request sent before its registration.").version("3.5.0").intConf().checkValue((JFunction1.mcZI.sp)(x$30) -> x$30 >= 0, "needs to be a non-negative value").createWithDefault(BoxesRunTime.boxToInteger(0));
      PUSH_BASED_SHUFFLE_ENABLED = (new ConfigBuilder("spark.shuffle.push.enabled")).doc("Set to true to enable push-based shuffle on the client side and this works in conjunction with the server side flag spark.shuffle.push.server.mergedShuffleFileManagerImpl which needs to be set with the appropriate org.apache.spark.network.shuffle.MergedShuffleFileManager implementation for push-based shuffle to be enabled").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      PUSH_BASED_SHUFFLE_MERGE_RESULTS_TIMEOUT = (new ConfigBuilder("spark.shuffle.push.results.timeout")).internal().doc("The maximum amount of time driver waits in seconds for the merge results to be received from all remote external shuffle services for a given shuffle. Driver submits following stages if not all results are received within the timeout. Setting this too long could potentially lead to performance regression").version("3.2.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(x$31) -> x$31 >= 0L, "Timeout must be >= 0.").createWithDefaultString("10s");
      PUSH_BASED_SHUFFLE_MERGE_FINALIZE_TIMEOUT = (new ConfigBuilder("spark.shuffle.push.finalize.timeout")).doc("The amount of time driver waits, after all mappers have finished for a given shuffle map stage, before it sends merge finalize requests to remote external shuffle services. This gives the external shuffle services extra time to merge blocks. Setting this too long could potentially lead to performance regression").version("3.2.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(x$32) -> x$32 >= 0L, "Timeout must be >= 0.").createWithDefaultString("10s");
      SHUFFLE_MERGER_MAX_RETAINED_LOCATIONS = (new ConfigBuilder("spark.shuffle.push.maxRetainedMergerLocations")).doc("Maximum number of merger locations cached for push-based shuffle. Currently, merger locations are hosts of external shuffle services responsible for handling pushed blocks, merging them and serving merged blocks for later shuffle fetch.").version("3.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(500));
      SHUFFLE_MERGER_LOCATIONS_MIN_THRESHOLD_RATIO = (new ConfigBuilder("spark.shuffle.push.mergersMinThresholdRatio")).doc("Ratio used to compute the minimum number of shuffle merger locations required for a stage based on the number of partitions for the reducer stage. For example, a reduce stage which has 100 partitions and uses the default value 0.05 requires at least 5 unique merger locations to enable push-based shuffle. Merger locations are currently defined as external shuffle services.").version("3.2.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble(0.05));
      var13 = new ConfigBuilder("spark.shuffle.push.mergersMinStaticThreshold");
      var29 = MODULE$.SHUFFLE_MERGER_LOCATIONS_MIN_THRESHOLD_RATIO().key();
      SHUFFLE_MERGER_LOCATIONS_MIN_STATIC_THRESHOLD = var13.doc("The static threshold for number of shuffle push merger locations should be available in order to enable push-based shuffle for a stage. Note this config works in conjunction with " + var29 + ". Maximum of spark.shuffle.push.mergersMinStaticThreshold and " + MODULE$.SHUFFLE_MERGER_LOCATIONS_MIN_THRESHOLD_RATIO().key() + " ratio number of mergers needed to enable push-based shuffle for a stage. For eg: with 1000 partitions for the child stage with spark.shuffle.push.mergersMinStaticThreshold as 5 and " + MODULE$.SHUFFLE_MERGER_LOCATIONS_MIN_THRESHOLD_RATIO().key() + " set to 0.05, we would need at least 50 mergers to enable push-based shuffle for that stage.").version("3.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(5));
      SHUFFLE_NUM_PUSH_THREADS = (new ConfigBuilder("spark.shuffle.push.numPushThreads")).doc("Specify the number of threads in the block pusher pool. These threads assist in creating connections and pushing blocks to remote external shuffle services. By default, the threadpool size is equal to the number of spark executor cores.").version("3.2.0").intConf().createOptional();
      SHUFFLE_MAX_BLOCK_SIZE_TO_PUSH = (new ConfigBuilder("spark.shuffle.push.maxBlockSizeToPush")).doc("The max size of an individual block to push to the remote external shuffle services. Blocks larger than this threshold are not pushed to be merged remotely. These shuffle blocks will be fetched by the executors in the original manner.").version("3.2.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("1m");
      SHUFFLE_MAX_BLOCK_BATCH_SIZE_FOR_PUSH = (new ConfigBuilder("spark.shuffle.push.maxBlockBatchSize")).doc("The max size of a batch of shuffle blocks to be grouped into a single push request.").version("3.2.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("3m");
      PUSH_BASED_SHUFFLE_MERGE_FINALIZE_THREADS = (new ConfigBuilder("spark.shuffle.push.merge.finalizeThreads")).doc("Number of threads used by driver to finalize shuffle merge. Since it could potentially take seconds for a large shuffle to finalize, having multiple threads helps driver to handle concurrent shuffle merge finalize requests when push-based shuffle is enabled.").version("3.3.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(8));
      PUSH_SHUFFLE_FINALIZE_RPC_THREADS = (new ConfigBuilder("spark.shuffle.push.sendFinalizeRPCThreads")).internal().doc("Number of threads used by the driver to send finalize shuffle RPC to mergers location and then get MergeStatus. The thread will run for up to  PUSH_BASED_SHUFFLE_MERGE_RESULTS_TIMEOUT. The merger ESS may open too many files if the finalize rpc is not received.").version("3.4.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(8));
      PUSH_BASED_SHUFFLE_SIZE_MIN_SHUFFLE_SIZE_TO_WAIT = (new ConfigBuilder("spark.shuffle.push.minShuffleSizeToWait")).doc("Driver will wait for merge finalization to complete only if total shuffle size is more than this threshold. If total shuffle size is less, driver will immediately finalize the shuffle output").version("3.3.0").bytesConf(ByteUnit.BYTE).createWithDefaultString("500m");
      PUSH_BASED_SHUFFLE_MIN_PUSH_RATIO = (new ConfigBuilder("spark.shuffle.push.minCompletedPushRatio")).doc("Fraction of map partitions that should be push complete before driver starts shuffle merge finalization during push based shuffle").version("3.3.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble((double)1.0F));
      JAR_IVY_REPO_PATH = (new ConfigBuilder("spark.jars.ivy")).doc("Path to specify the Ivy user directory, used for the local Ivy cache and package files from spark.jars.packages. This will override the Ivy property ivy.default.ivy.user.dir which defaults to ~/.ivy2.5.2").version("1.3.0").stringConf().createWithDefault("~/.ivy2.5.2");
      JAR_IVY_SETTING_PATH = (new ConfigBuilder(org.apache.spark.util.MavenUtils..MODULE$.JAR_IVY_SETTING_PATH_KEY())).doc("Path to an Ivy settings file to customize resolution of jars specified using spark.jars.packages instead of the built-in defaults, such as maven central. Additional repositories given by the command-line option --repositories or spark.jars.repositories will also be included. Useful for allowing Spark to resolve artifacts from behind a firewall e.g. via an in-house artifact server like Artifactory. Details on the settings file format can be found at Settings Files").version("2.2.0").stringConf().createOptional();
      JAR_PACKAGES = (new ConfigBuilder("spark.jars.packages")).doc("Comma-separated list of Maven coordinates of jars to include on the driver and executor classpaths. The coordinates should be groupId:artifactId:version. If spark.jars.ivySettings is given artifacts will be resolved according to the configuration in the file, otherwise artifacts will be searched for in the local maven repo, then maven central and finally any additional remote repositories given by the command-line option --repositories. For more details, see Advanced Dependency Management.").version("1.5.0").stringConf().toSequence().createWithDefault(.MODULE$);
      JAR_PACKAGES_EXCLUSIONS = (new ConfigBuilder("spark.jars.excludes")).doc("Comma-separated list of groupId:artifactId, to exclude while resolving the dependencies provided in spark.jars.packages to avoid dependency conflicts.").version("1.5.0").stringConf().toSequence().createWithDefault(.MODULE$);
      JAR_REPOSITORIES = (new ConfigBuilder("spark.jars.repositories")).doc("Comma-separated list of additional remote repositories to search for the maven coordinates given with --packages or spark.jars.packages.").version("2.3.0").stringConf().toSequence().createWithDefault(.MODULE$);
      APP_ATTEMPT_ID = (new ConfigBuilder("spark.app.attempt.id")).internal().doc("The application attempt Id assigned from Hadoop YARN. When the application runs in cluster mode on YARN, there can be multiple attempts before failing the application").version("3.2.0").stringConf().createOptional();
      EXECUTOR_STATE_SYNC_MAX_ATTEMPTS = (new ConfigBuilder("spark.worker.executorStateSync.maxAttempts")).internal().doc("The max attempts the worker will try to sync the ExecutorState to the Master, if the failed attempts reach the max attempts limit, the worker will give up and exit.").version("3.3.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(5));
      EXECUTOR_REMOVE_DELAY = (new ConfigBuilder("spark.standalone.executorRemoveDelayOnDisconnection")).internal().doc("The timeout duration for a disconnected executor to wait for the specific disconnectreason before it gets removed. This is only used for Standalone yet.").version("3.4.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("5s");
      ALLOW_CUSTOM_CLASSPATH_BY_PROXY_USER_IN_CLUSTER_MODE = (new ConfigBuilder("spark.submit.proxyUser.allowCustomClasspathInClusterMode")).internal().version("3.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      RDD_CACHE_VISIBILITY_TRACKING_ENABLED = (new ConfigBuilder("spark.rdd.cache.visibilityTracking.enabled")).internal().doc("Set to be true to enabled RDD cache block's visibility status. Once it's enabled, a RDD cache block can be used only when it's marked as visible. And a RDD block will be marked as visible only when one of the tasks generating the cache block finished successfully. This is relevant in context of consistent accumulator status.").version("3.5.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      var13 = new ConfigBuilder("spark.stage.maxAttempts");
      var29 = MODULE$.STAGE_MAX_CONSECUTIVE_ATTEMPTS().key();
      STAGE_MAX_ATTEMPTS = var13.doc("Specify the max attempts for a stage - the spark job will be aborted if any of its stages is resubmitted multiple times beyond the max retries limitation. The maximum number of stage retries is the maximum of `spark.stage.maxAttempts` and `" + var29 + "`.").version("3.5.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      SHUFFLE_SERVER_RECOVERY_DISABLED = (new ConfigBuilder("spark.yarn.shuffle.server.recovery.disabled")).internal().doc("Set to true for applications that prefer to disable recovery when the External Shuffle Service restarts. This configuration only takes effect on YARN.").version("3.5.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      CONNECT_SCALA_UDF_STUB_PREFIXES = (new ConfigBuilder("spark.connect.scalaUdf.stubPrefixes")).internal().doc(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n          |Comma-separated list of binary names of classes/packages that should be stubbed during\n          |the Scala UDF serde and execution if not found on the server classpath.\n          |An empty list effectively disables stubbing for all missing classes.\n          |By default, the server stubs classes from the Scala client package.\n          |"))).version("3.5.0").stringConf().toSequence().createWithDefault(.MODULE$.$colon$colon("org.apache.spark.sql.connect.client"));
      LEGACY_ABORT_STAGE_AFTER_KILL_TASKS = (new ConfigBuilder("spark.scheduler.stage.legacyAbortAfterKillTasks")).doc("Whether to abort a stage after TaskScheduler.killAllTaskAttempts(). This is used to restore the original behavior in case there are any regressions after abort stage is removed").version("4.0.0").internal().booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      DROP_TASK_INFO_ACCUMULABLES_ON_TASK_COMPLETION = (new ConfigBuilder("spark.scheduler.dropTaskInfoAccumulablesOnTaskCompletion.enabled")).internal().doc("If true, the task info accumulables will be cleared upon task completion in TaskSetManager. This reduces the heap usage of the driver by only referencing the task info accumulables for the active tasks and not for completed tasks.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      SPARK_SHUTDOWN_TIMEOUT_MS = (new ConfigBuilder("spark.shutdown.timeout")).internal().doc("Defines the timeout period to wait for all shutdown hooks to be executed. This must be passed as a system property argument in the Java options, for example spark.driver.extraJavaOptions=\"-Dspark.shutdown.timeout=60s\".").version("4.0.0").timeConf(TimeUnit.MILLISECONDS).createOptional();
      SPARK_API_MODE = (new ConfigBuilder("spark.api.mode")).doc("For Spark Classic applications, specify whether to automatically use Spark Connect by running a local Spark Connect server dedicated to the application. The server is terminated when the application is terminated. The value can be `classic` or `connect`.").version("4.0.0").stringConf().transform((x$33) -> x$33.toLowerCase(Locale.ROOT)).checkValues((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"connect", "classic"})))).createWithDefault(scala.sys.package..MODULE$.env().get("SPARK_CONNECT_MODE").contains("1") ? "connect" : "classic");
   }

   public String SPARK_DRIVER_PREFIX() {
      return SPARK_DRIVER_PREFIX;
   }

   public String SPARK_EXECUTOR_PREFIX() {
      return SPARK_EXECUTOR_PREFIX;
   }

   public String SPARK_TASK_PREFIX() {
      return SPARK_TASK_PREFIX;
   }

   public String LISTENER_BUS_EVENT_QUEUE_PREFIX() {
      return LISTENER_BUS_EVENT_QUEUE_PREFIX;
   }

   public OptionalConfigEntry DEFAULT_PARALLELISM() {
      return DEFAULT_PARALLELISM;
   }

   public ConfigEntry RESOURCES_DISCOVERY_PLUGIN() {
      return RESOURCES_DISCOVERY_PLUGIN;
   }

   public OptionalConfigEntry DRIVER_RESOURCES_FILE() {
      return DRIVER_RESOURCES_FILE;
   }

   public ConfigEntry DRIVER_DEFAULT_EXTRA_CLASS_PATH() {
      return DRIVER_DEFAULT_EXTRA_CLASS_PATH;
   }

   public OptionalConfigEntry DRIVER_CLASS_PATH() {
      return DRIVER_CLASS_PATH;
   }

   public OptionalConfigEntry DRIVER_JAVA_OPTIONS() {
      return DRIVER_JAVA_OPTIONS;
   }

   public OptionalConfigEntry DRIVER_LIBRARY_PATH() {
      return DRIVER_LIBRARY_PATH;
   }

   public ConfigEntry DRIVER_USER_CLASS_PATH_FIRST() {
      return DRIVER_USER_CLASS_PATH_FIRST;
   }

   public ConfigEntry DRIVER_CORES() {
      return DRIVER_CORES;
   }

   public ConfigEntry DRIVER_MEMORY() {
      return DRIVER_MEMORY;
   }

   public OptionalConfigEntry DRIVER_MEMORY_OVERHEAD() {
      return DRIVER_MEMORY_OVERHEAD;
   }

   public ConfigEntry DRIVER_MIN_MEMORY_OVERHEAD() {
      return DRIVER_MIN_MEMORY_OVERHEAD;
   }

   public ConfigEntry DRIVER_MEMORY_OVERHEAD_FACTOR() {
      return DRIVER_MEMORY_OVERHEAD_FACTOR;
   }

   public ConfigEntry STRUCTURED_LOGGING_ENABLED() {
      return STRUCTURED_LOGGING_ENABLED;
   }

   public ConfigEntry LEGACY_TASK_NAME_MDC_ENABLED() {
      return LEGACY_TASK_NAME_MDC_ENABLED;
   }

   public OptionalConfigEntry DRIVER_LOG_LOCAL_DIR() {
      return DRIVER_LOG_LOCAL_DIR;
   }

   public OptionalConfigEntry DRIVER_LOG_DFS_DIR() {
      return DRIVER_LOG_DFS_DIR;
   }

   public OptionalConfigEntry DRIVER_LOG_LAYOUT() {
      return DRIVER_LOG_LAYOUT;
   }

   public ConfigEntry DRIVER_LOG_PERSISTTODFS() {
      return DRIVER_LOG_PERSISTTODFS;
   }

   public ConfigEntry DRIVER_LOG_ALLOW_EC() {
      return DRIVER_LOG_ALLOW_EC;
   }

   public ConfigEntry EVENT_LOG_ENABLED() {
      return EVENT_LOG_ENABLED;
   }

   public ConfigEntry EVENT_LOG_DIR() {
      return EVENT_LOG_DIR;
   }

   public ConfigEntry EVENT_LOG_COMPRESS() {
      return EVENT_LOG_COMPRESS;
   }

   public ConfigEntry EVENT_LOG_BLOCK_UPDATES() {
      return EVENT_LOG_BLOCK_UPDATES;
   }

   public ConfigEntry EVENT_LOG_ALLOW_EC() {
      return EVENT_LOG_ALLOW_EC;
   }

   public ConfigEntry EVENT_LOG_TESTING() {
      return EVENT_LOG_TESTING;
   }

   public ConfigEntry EVENT_LOG_OUTPUT_BUFFER_SIZE() {
      return EVENT_LOG_OUTPUT_BUFFER_SIZE;
   }

   public ConfigEntry EVENT_LOG_STAGE_EXECUTOR_METRICS() {
      return EVENT_LOG_STAGE_EXECUTOR_METRICS;
   }

   public ConfigEntry EVENT_LOG_GC_METRICS_YOUNG_GENERATION_GARBAGE_COLLECTORS() {
      return EVENT_LOG_GC_METRICS_YOUNG_GENERATION_GARBAGE_COLLECTORS;
   }

   public ConfigEntry EVENT_LOG_GC_METRICS_OLD_GENERATION_GARBAGE_COLLECTORS() {
      return EVENT_LOG_GC_METRICS_OLD_GENERATION_GARBAGE_COLLECTORS;
   }

   public ConfigEntry EVENT_LOG_INCLUDE_TASK_METRICS_ACCUMULATORS() {
      return EVENT_LOG_INCLUDE_TASK_METRICS_ACCUMULATORS;
   }

   public ConfigEntry EVENT_LOG_OVERWRITE() {
      return EVENT_LOG_OVERWRITE;
   }

   public ConfigEntry EVENT_LOG_CALLSITE_LONG_FORM() {
      return EVENT_LOG_CALLSITE_LONG_FORM;
   }

   public ConfigEntry EVENT_LOG_ENABLE_ROLLING() {
      return EVENT_LOG_ENABLE_ROLLING;
   }

   public ConfigEntry EVENT_LOG_ROLLING_MAX_FILE_SIZE() {
      return EVENT_LOG_ROLLING_MAX_FILE_SIZE;
   }

   public OptionalConfigEntry EXECUTOR_ID() {
      return EXECUTOR_ID;
   }

   public ConfigEntry EXECUTOR_DEFAULT_EXTRA_CLASS_PATH() {
      return EXECUTOR_DEFAULT_EXTRA_CLASS_PATH;
   }

   public OptionalConfigEntry EXECUTOR_CLASS_PATH() {
      return EXECUTOR_CLASS_PATH;
   }

   public ConfigEntry EXECUTOR_HEARTBEAT_DROP_ZERO_ACCUMULATOR_UPDATES() {
      return EXECUTOR_HEARTBEAT_DROP_ZERO_ACCUMULATOR_UPDATES;
   }

   public ConfigEntry EXECUTOR_HEARTBEAT_INTERVAL() {
      return EXECUTOR_HEARTBEAT_INTERVAL;
   }

   public ConfigEntry EXECUTOR_HEARTBEAT_MAX_FAILURES() {
      return EXECUTOR_HEARTBEAT_MAX_FAILURES;
   }

   public ConfigEntry EXECUTOR_PROCESS_TREE_METRICS_ENABLED() {
      return EXECUTOR_PROCESS_TREE_METRICS_ENABLED;
   }

   public ConfigEntry EXECUTOR_METRICS_POLLING_INTERVAL() {
      return EXECUTOR_METRICS_POLLING_INTERVAL;
   }

   public ConfigEntry EXECUTOR_METRICS_FILESYSTEM_SCHEMES() {
      return EXECUTOR_METRICS_FILESYSTEM_SCHEMES;
   }

   public OptionalConfigEntry EXECUTOR_JAVA_OPTIONS() {
      return EXECUTOR_JAVA_OPTIONS;
   }

   public OptionalConfigEntry EXECUTOR_LIBRARY_PATH() {
      return EXECUTOR_LIBRARY_PATH;
   }

   public ConfigEntry EXECUTOR_USER_CLASS_PATH_FIRST() {
      return EXECUTOR_USER_CLASS_PATH_FIRST;
   }

   public ConfigEntry EXECUTOR_CORES() {
      return EXECUTOR_CORES;
   }

   public ConfigEntry EXECUTOR_MEMORY() {
      return EXECUTOR_MEMORY;
   }

   public OptionalConfigEntry EXECUTOR_MEMORY_OVERHEAD() {
      return EXECUTOR_MEMORY_OVERHEAD;
   }

   public ConfigEntry EXECUTOR_MIN_MEMORY_OVERHEAD() {
      return EXECUTOR_MIN_MEMORY_OVERHEAD;
   }

   public ConfigEntry EXECUTOR_MEMORY_OVERHEAD_FACTOR() {
      return EXECUTOR_MEMORY_OVERHEAD_FACTOR;
   }

   public OptionalConfigEntry CORES_MAX() {
      return CORES_MAX;
   }

   public ConfigEntry MEMORY_OFFHEAP_ENABLED() {
      return MEMORY_OFFHEAP_ENABLED;
   }

   public ConfigEntry MEMORY_OFFHEAP_SIZE() {
      return MEMORY_OFFHEAP_SIZE;
   }

   public ConfigEntry MEMORY_STORAGE_FRACTION() {
      return MEMORY_STORAGE_FRACTION;
   }

   public ConfigEntry MEMORY_FRACTION() {
      return MEMORY_FRACTION;
   }

   public ConfigEntry STORAGE_UNROLL_MEMORY_THRESHOLD() {
      return STORAGE_UNROLL_MEMORY_THRESHOLD;
   }

   public ConfigEntry STORAGE_REPLICATION_PROACTIVE() {
      return STORAGE_REPLICATION_PROACTIVE;
   }

   public ConfigEntry STORAGE_MEMORY_MAP_THRESHOLD() {
      return STORAGE_MEMORY_MAP_THRESHOLD;
   }

   public ConfigEntry STORAGE_REPLICATION_POLICY() {
      return STORAGE_REPLICATION_POLICY;
   }

   public ConfigEntry STORAGE_REPLICATION_TOPOLOGY_MAPPER() {
      return STORAGE_REPLICATION_TOPOLOGY_MAPPER;
   }

   public ConfigEntry STORAGE_CACHED_PEERS_TTL() {
      return STORAGE_CACHED_PEERS_TTL;
   }

   public ConfigEntry STORAGE_MAX_REPLICATION_FAILURE() {
      return STORAGE_MAX_REPLICATION_FAILURE;
   }

   public ConfigEntry STORAGE_DECOMMISSION_ENABLED() {
      return STORAGE_DECOMMISSION_ENABLED;
   }

   public ConfigEntry STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED() {
      return STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED;
   }

   public ConfigEntry STORAGE_DECOMMISSION_SHUFFLE_MAX_THREADS() {
      return STORAGE_DECOMMISSION_SHUFFLE_MAX_THREADS;
   }

   public ConfigEntry STORAGE_DECOMMISSION_RDD_BLOCKS_ENABLED() {
      return STORAGE_DECOMMISSION_RDD_BLOCKS_ENABLED;
   }

   public ConfigEntry STORAGE_DECOMMISSION_MAX_REPLICATION_FAILURE_PER_BLOCK() {
      return STORAGE_DECOMMISSION_MAX_REPLICATION_FAILURE_PER_BLOCK;
   }

   public ConfigEntry STORAGE_DECOMMISSION_REPLICATION_REATTEMPT_INTERVAL() {
      return STORAGE_DECOMMISSION_REPLICATION_REATTEMPT_INTERVAL;
   }

   public OptionalConfigEntry STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH() {
      return STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH;
   }

   public ConfigEntry STORAGE_DECOMMISSION_FALLBACK_STORAGE_CLEANUP() {
      return STORAGE_DECOMMISSION_FALLBACK_STORAGE_CLEANUP;
   }

   public OptionalConfigEntry STORAGE_DECOMMISSION_SHUFFLE_MAX_DISK_SIZE() {
      return STORAGE_DECOMMISSION_SHUFFLE_MAX_DISK_SIZE;
   }

   public OptionalConfigEntry STORAGE_REPLICATION_TOPOLOGY_FILE() {
      return STORAGE_REPLICATION_TOPOLOGY_FILE;
   }

   public ConfigEntry STORAGE_EXCEPTION_PIN_LEAK() {
      return STORAGE_EXCEPTION_PIN_LEAK;
   }

   public ConfigEntry STORAGE_BLOCKMANAGER_TIMEOUTINTERVAL() {
      return STORAGE_BLOCKMANAGER_TIMEOUTINTERVAL;
   }

   public ConfigEntry STORAGE_BLOCKMANAGER_MASTER_DRIVER_HEARTBEAT_TIMEOUT() {
      return STORAGE_BLOCKMANAGER_MASTER_DRIVER_HEARTBEAT_TIMEOUT;
   }

   public OptionalConfigEntry STORAGE_BLOCKMANAGER_HEARTBEAT_TIMEOUT() {
      return STORAGE_BLOCKMANAGER_HEARTBEAT_TIMEOUT;
   }

   public ConfigEntry STORAGE_CLEANUP_FILES_AFTER_EXECUTOR_EXIT() {
      return STORAGE_CLEANUP_FILES_AFTER_EXECUTOR_EXIT;
   }

   public ConfigEntry DISKSTORE_SUB_DIRECTORIES() {
      return DISKSTORE_SUB_DIRECTORIES;
   }

   public ConfigEntry BLOCK_FAILURES_BEFORE_LOCATION_REFRESH() {
      return BLOCK_FAILURES_BEFORE_LOCATION_REFRESH;
   }

   public ConfigEntry IS_PYTHON_APP() {
      return IS_PYTHON_APP;
   }

   public ConfigEntry CPUS_PER_TASK() {
      return CPUS_PER_TASK;
   }

   public ConfigEntry DYN_ALLOCATION_ENABLED() {
      return DYN_ALLOCATION_ENABLED;
   }

   public ConfigEntry DYN_ALLOCATION_TESTING() {
      return DYN_ALLOCATION_TESTING;
   }

   public ConfigEntry DYN_ALLOCATION_MIN_EXECUTORS() {
      return DYN_ALLOCATION_MIN_EXECUTORS;
   }

   public ConfigEntry DYN_ALLOCATION_INITIAL_EXECUTORS() {
      return DYN_ALLOCATION_INITIAL_EXECUTORS;
   }

   public ConfigEntry DYN_ALLOCATION_MAX_EXECUTORS() {
      return DYN_ALLOCATION_MAX_EXECUTORS;
   }

   public ConfigEntry DYN_ALLOCATION_EXECUTOR_ALLOCATION_RATIO() {
      return DYN_ALLOCATION_EXECUTOR_ALLOCATION_RATIO;
   }

   public ConfigEntry DYN_ALLOCATION_CACHED_EXECUTOR_IDLE_TIMEOUT() {
      return DYN_ALLOCATION_CACHED_EXECUTOR_IDLE_TIMEOUT;
   }

   public ConfigEntry DYN_ALLOCATION_EXECUTOR_IDLE_TIMEOUT() {
      return DYN_ALLOCATION_EXECUTOR_IDLE_TIMEOUT;
   }

   public ConfigEntry DYN_ALLOCATION_SHUFFLE_TRACKING_ENABLED() {
      return DYN_ALLOCATION_SHUFFLE_TRACKING_ENABLED;
   }

   public ConfigEntry DYN_ALLOCATION_SHUFFLE_TRACKING_TIMEOUT() {
      return DYN_ALLOCATION_SHUFFLE_TRACKING_TIMEOUT;
   }

   public ConfigEntry DYN_ALLOCATION_SCHEDULER_BACKLOG_TIMEOUT() {
      return DYN_ALLOCATION_SCHEDULER_BACKLOG_TIMEOUT;
   }

   public ConfigEntry DYN_ALLOCATION_SUSTAINED_SCHEDULER_BACKLOG_TIMEOUT() {
      return DYN_ALLOCATION_SUSTAINED_SCHEDULER_BACKLOG_TIMEOUT;
   }

   public ConfigEntry LEGACY_LOCALITY_WAIT_RESET() {
      return LEGACY_LOCALITY_WAIT_RESET;
   }

   public ConfigEntry LOCALITY_WAIT() {
      return LOCALITY_WAIT;
   }

   public ConfigEntry SHUFFLE_SERVICE_ENABLED() {
      return SHUFFLE_SERVICE_ENABLED;
   }

   public ConfigEntry SHUFFLE_SERVICE_REMOVE_SHUFFLE_ENABLED() {
      return SHUFFLE_SERVICE_REMOVE_SHUFFLE_ENABLED;
   }

   public ConfigEntry SHUFFLE_SERVICE_FETCH_RDD_ENABLED() {
      return SHUFFLE_SERVICE_FETCH_RDD_ENABLED;
   }

   public ConfigEntry SHUFFLE_SERVICE_DB_ENABLED() {
      return SHUFFLE_SERVICE_DB_ENABLED;
   }

   public ConfigEntry SHUFFLE_SERVICE_DB_BACKEND() {
      return SHUFFLE_SERVICE_DB_BACKEND;
   }

   public ConfigEntry SHUFFLE_SERVICE_PORT() {
      return SHUFFLE_SERVICE_PORT;
   }

   public ConfigEntry SHUFFLE_SERVICE_NAME() {
      return SHUFFLE_SERVICE_NAME;
   }

   public OptionalConfigEntry KEYTAB() {
      return KEYTAB;
   }

   public OptionalConfigEntry PRINCIPAL() {
      return PRINCIPAL;
   }

   public ConfigEntry KERBEROS_RELOGIN_PERIOD() {
      return KERBEROS_RELOGIN_PERIOD;
   }

   public ConfigEntry KERBEROS_RENEWAL_CREDENTIALS() {
      return KERBEROS_RENEWAL_CREDENTIALS;
   }

   public ConfigEntry KERBEROS_FILESYSTEMS_TO_ACCESS() {
      return KERBEROS_FILESYSTEMS_TO_ACCESS;
   }

   public ConfigEntry YARN_KERBEROS_FILESYSTEM_RENEWAL_EXCLUDE() {
      return YARN_KERBEROS_FILESYSTEM_RENEWAL_EXCLUDE;
   }

   public OptionalConfigEntry EXECUTOR_INSTANCES() {
      return EXECUTOR_INSTANCES;
   }

   public ConfigEntry PY_FILES() {
      return PY_FILES;
   }

   public ConfigEntry TASK_MAX_DIRECT_RESULT_SIZE() {
      return TASK_MAX_DIRECT_RESULT_SIZE;
   }

   public ConfigEntry TASK_MAX_FAILURES() {
      return TASK_MAX_FAILURES;
   }

   public ConfigEntry TASK_REAPER_ENABLED() {
      return TASK_REAPER_ENABLED;
   }

   public ConfigEntry TASK_REAPER_KILL_TIMEOUT() {
      return TASK_REAPER_KILL_TIMEOUT;
   }

   public ConfigEntry TASK_REAPER_POLLING_INTERVAL() {
      return TASK_REAPER_POLLING_INTERVAL;
   }

   public ConfigEntry TASK_REAPER_THREAD_DUMP() {
      return TASK_REAPER_THREAD_DUMP;
   }

   public OptionalConfigEntry EXCLUDE_ON_FAILURE_ENABLED() {
      return EXCLUDE_ON_FAILURE_ENABLED;
   }

   public OptionalConfigEntry EXCLUDE_ON_FAILURE_ENABLED_APPLICATION() {
      return EXCLUDE_ON_FAILURE_ENABLED_APPLICATION;
   }

   public OptionalConfigEntry EXCLUDE_ON_FAILURE_ENABLED_TASK_AND_STAGE() {
      return EXCLUDE_ON_FAILURE_ENABLED_TASK_AND_STAGE;
   }

   public ConfigEntry MAX_TASK_ATTEMPTS_PER_EXECUTOR() {
      return MAX_TASK_ATTEMPTS_PER_EXECUTOR;
   }

   public ConfigEntry MAX_TASK_ATTEMPTS_PER_NODE() {
      return MAX_TASK_ATTEMPTS_PER_NODE;
   }

   public ConfigEntry MAX_FAILURES_PER_EXEC() {
      return MAX_FAILURES_PER_EXEC;
   }

   public ConfigEntry MAX_FAILURES_PER_EXEC_STAGE() {
      return MAX_FAILURES_PER_EXEC_STAGE;
   }

   public ConfigEntry MAX_FAILED_EXEC_PER_NODE() {
      return MAX_FAILED_EXEC_PER_NODE;
   }

   public ConfigEntry MAX_FAILED_EXEC_PER_NODE_STAGE() {
      return MAX_FAILED_EXEC_PER_NODE_STAGE;
   }

   public OptionalConfigEntry EXCLUDE_ON_FAILURE_TIMEOUT_CONF() {
      return EXCLUDE_ON_FAILURE_TIMEOUT_CONF;
   }

   public ConfigEntry EXCLUDE_ON_FAILURE_KILL_ENABLED() {
      return EXCLUDE_ON_FAILURE_KILL_ENABLED;
   }

   public ConfigEntry EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED() {
      return EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED;
   }

   public OptionalConfigEntry EXCLUDE_ON_FAILURE_LEGACY_TIMEOUT_CONF() {
      return EXCLUDE_ON_FAILURE_LEGACY_TIMEOUT_CONF;
   }

   public ConfigEntry EXCLUDE_ON_FAILURE_FETCH_FAILURE_ENABLED() {
      return EXCLUDE_ON_FAILURE_FETCH_FAILURE_ENABLED;
   }

   public OptionalConfigEntry MAX_EXECUTOR_FAILURES() {
      return MAX_EXECUTOR_FAILURES;
   }

   public OptionalConfigEntry EXECUTOR_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS() {
      return EXECUTOR_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS;
   }

   public ConfigEntry UNREGISTER_OUTPUT_ON_HOST_ON_FETCH_FAILURE() {
      return UNREGISTER_OUTPUT_ON_HOST_ON_FETCH_FAILURE;
   }

   public ConfigEntry LISTENER_BUS_EVENT_QUEUE_CAPACITY() {
      return LISTENER_BUS_EVENT_QUEUE_CAPACITY;
   }

   public ConfigEntry LISTENER_BUS_METRICS_MAX_LISTENER_CLASSES_TIMED() {
      return LISTENER_BUS_METRICS_MAX_LISTENER_CLASSES_TIMED;
   }

   public ConfigEntry LISTENER_BUS_LOG_SLOW_EVENT_ENABLED() {
      return LISTENER_BUS_LOG_SLOW_EVENT_ENABLED;
   }

   public ConfigEntry LISTENER_BUS_LOG_SLOW_EVENT_TIME_THRESHOLD() {
      return LISTENER_BUS_LOG_SLOW_EVENT_TIME_THRESHOLD;
   }

   public ConfigEntry LISTENER_BUS_EXIT_TIMEOUT() {
      return LISTENER_BUS_EXIT_TIMEOUT;
   }

   public OptionalConfigEntry METRICS_NAMESPACE() {
      return METRICS_NAMESPACE;
   }

   public OptionalConfigEntry METRICS_CONF() {
      return METRICS_CONF;
   }

   public ConfigEntry METRICS_EXECUTORMETRICS_SOURCE_ENABLED() {
      return METRICS_EXECUTORMETRICS_SOURCE_ENABLED;
   }

   public ConfigEntry METRICS_STATIC_SOURCES_ENABLED() {
      return METRICS_STATIC_SOURCES_ENABLED;
   }

   public OptionalConfigEntry PYSPARK_DRIVER_PYTHON() {
      return PYSPARK_DRIVER_PYTHON;
   }

   public OptionalConfigEntry PYSPARK_PYTHON() {
      return PYSPARK_PYTHON;
   }

   public ConfigEntry IO_ENCRYPTION_ENABLED() {
      return IO_ENCRYPTION_ENABLED;
   }

   public ConfigEntry IO_ENCRYPTION_KEYGEN_ALGORITHM() {
      return IO_ENCRYPTION_KEYGEN_ALGORITHM;
   }

   public ConfigEntry IO_ENCRYPTION_KEY_SIZE_BITS() {
      return IO_ENCRYPTION_KEY_SIZE_BITS;
   }

   public ConfigEntry IO_CRYPTO_CIPHER_TRANSFORMATION() {
      return IO_CRYPTO_CIPHER_TRANSFORMATION;
   }

   public ConfigEntry DRIVER_HOST_ADDRESS() {
      return DRIVER_HOST_ADDRESS;
   }

   public ConfigEntry DRIVER_PORT() {
      return DRIVER_PORT;
   }

   public ConfigEntry DRIVER_SUPERVISE() {
      return DRIVER_SUPERVISE;
   }

   public ConfigEntry DRIVER_TIMEOUT() {
      return DRIVER_TIMEOUT;
   }

   public ConfigEntry DRIVER_BIND_ADDRESS() {
      return DRIVER_BIND_ADDRESS;
   }

   public ConfigEntry BLOCK_MANAGER_PORT() {
      return BLOCK_MANAGER_PORT;
   }

   public ConfigEntry DRIVER_BLOCK_MANAGER_PORT() {
      return DRIVER_BLOCK_MANAGER_PORT;
   }

   public ConfigEntry IGNORE_CORRUPT_FILES() {
      return IGNORE_CORRUPT_FILES;
   }

   public ConfigEntry IGNORE_MISSING_FILES() {
      return IGNORE_MISSING_FILES;
   }

   public OptionalConfigEntry APP_CALLER_CONTEXT() {
      return APP_CALLER_CONTEXT;
   }

   public OptionalConfigEntry SPARK_LOG_LEVEL() {
      return SPARK_LOG_LEVEL;
   }

   public ConfigEntry FILES_MAX_PARTITION_BYTES() {
      return FILES_MAX_PARTITION_BYTES;
   }

   public ConfigEntry FILES_OPEN_COST_IN_BYTES() {
      return FILES_OPEN_COST_IN_BYTES;
   }

   public ConfigEntry HADOOP_RDD_IGNORE_EMPTY_SPLITS() {
      return HADOOP_RDD_IGNORE_EMPTY_SPLITS;
   }

   public ConfigEntry SECRET_REDACTION_PATTERN() {
      return SECRET_REDACTION_PATTERN;
   }

   public OptionalConfigEntry STRING_REDACTION_PATTERN() {
      return STRING_REDACTION_PATTERN;
   }

   public OptionalConfigEntry AUTH_SECRET() {
      return AUTH_SECRET;
   }

   public ConfigEntry AUTH_SECRET_BIT_LENGTH() {
      return AUTH_SECRET_BIT_LENGTH;
   }

   public ConfigEntry NETWORK_AUTH_ENABLED() {
      return NETWORK_AUTH_ENABLED;
   }

   public ConfigEntry SASL_ENCRYPTION_ENABLED() {
      return SASL_ENCRYPTION_ENABLED;
   }

   public OptionalConfigEntry AUTH_SECRET_FILE() {
      return AUTH_SECRET_FILE;
   }

   public ConfigEntry AUTH_SECRET_FILE_DRIVER() {
      return AUTH_SECRET_FILE_DRIVER;
   }

   public ConfigEntry AUTH_SECRET_FILE_EXECUTOR() {
      return AUTH_SECRET_FILE_EXECUTOR;
   }

   public ConfigEntry BUFFER_WRITE_CHUNK_SIZE() {
      return BUFFER_WRITE_CHUNK_SIZE;
   }

   public OptionalConfigEntry CHECKPOINT_DIR() {
      return CHECKPOINT_DIR;
   }

   public ConfigEntry CHECKPOINT_COMPRESS() {
      return CHECKPOINT_COMPRESS;
   }

   public OptionalConfigEntry CACHE_CHECKPOINT_PREFERRED_LOCS_EXPIRE_TIME() {
      return CACHE_CHECKPOINT_PREFERRED_LOCS_EXPIRE_TIME;
   }

   public ConfigEntry SHUFFLE_ACCURATE_BLOCK_THRESHOLD() {
      return SHUFFLE_ACCURATE_BLOCK_THRESHOLD;
   }

   public ConfigEntry SHUFFLE_ACCURATE_BLOCK_SKEWED_FACTOR() {
      return SHUFFLE_ACCURATE_BLOCK_SKEWED_FACTOR;
   }

   public ConfigEntry SHUFFLE_MAX_ACCURATE_SKEWED_BLOCK_NUMBER() {
      return SHUFFLE_MAX_ACCURATE_SKEWED_BLOCK_NUMBER;
   }

   public ConfigEntry SHUFFLE_REGISTRATION_TIMEOUT() {
      return SHUFFLE_REGISTRATION_TIMEOUT;
   }

   public ConfigEntry SHUFFLE_REGISTRATION_MAX_ATTEMPTS() {
      return SHUFFLE_REGISTRATION_MAX_ATTEMPTS;
   }

   public ConfigEntry SHUFFLE_MAX_ATTEMPTS_ON_NETTY_OOM() {
      return SHUFFLE_MAX_ATTEMPTS_ON_NETTY_OOM;
   }

   public ConfigEntry REDUCER_MAX_BLOCKS_IN_FLIGHT_PER_ADDRESS() {
      return REDUCER_MAX_BLOCKS_IN_FLIGHT_PER_ADDRESS;
   }

   public ConfigEntry MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM() {
      return MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM;
   }

   public ConfigEntry TASK_METRICS_TRACK_UPDATED_BLOCK_STATUSES() {
      return TASK_METRICS_TRACK_UPDATED_BLOCK_STATUSES;
   }

   public ConfigEntry SHUFFLE_IO_PLUGIN_CLASS() {
      return SHUFFLE_IO_PLUGIN_CLASS;
   }

   public ConfigEntry SHUFFLE_FILE_BUFFER_SIZE() {
      return SHUFFLE_FILE_BUFFER_SIZE;
   }

   public ConfigEntry SHUFFLE_FILE_MERGE_BUFFER_SIZE() {
      return SHUFFLE_FILE_MERGE_BUFFER_SIZE;
   }

   public ConfigEntry SHUFFLE_UNSAFE_FILE_OUTPUT_BUFFER_SIZE() {
      return SHUFFLE_UNSAFE_FILE_OUTPUT_BUFFER_SIZE;
   }

   public ConfigEntry SHUFFLE_LOCAL_DISK_FILE_OUTPUT_BUFFER_SIZE() {
      return SHUFFLE_LOCAL_DISK_FILE_OUTPUT_BUFFER_SIZE;
   }

   public ConfigEntry SHUFFLE_DISK_WRITE_BUFFER_SIZE() {
      return SHUFFLE_DISK_WRITE_BUFFER_SIZE;
   }

   public ConfigEntry UNROLL_MEMORY_CHECK_PERIOD() {
      return UNROLL_MEMORY_CHECK_PERIOD;
   }

   public ConfigEntry UNROLL_MEMORY_GROWTH_FACTOR() {
      return UNROLL_MEMORY_GROWTH_FACTOR;
   }

   public ConfigEntry KUBERNETES_JARS_AVOID_DOWNLOAD_SCHEMES() {
      return KUBERNETES_JARS_AVOID_DOWNLOAD_SCHEMES;
   }

   public ConfigEntry FORCE_DOWNLOAD_SCHEMES() {
      return FORCE_DOWNLOAD_SCHEMES;
   }

   public OptionalConfigEntry EXTRA_LISTENERS() {
      return EXTRA_LISTENERS;
   }

   public ConfigEntry SHUFFLE_SPILL_NUM_ELEMENTS_FORCE_SPILL_THRESHOLD() {
      return SHUFFLE_SPILL_NUM_ELEMENTS_FORCE_SPILL_THRESHOLD;
   }

   public ConfigEntry SHUFFLE_MAP_OUTPUT_PARALLEL_AGGREGATION_THRESHOLD() {
      return SHUFFLE_MAP_OUTPUT_PARALLEL_AGGREGATION_THRESHOLD;
   }

   public ConfigEntry MAX_RESULT_SIZE() {
      return MAX_RESULT_SIZE;
   }

   public ConfigEntry CREDENTIALS_RENEWAL_INTERVAL_RATIO() {
      return CREDENTIALS_RENEWAL_INTERVAL_RATIO;
   }

   public ConfigEntry CREDENTIALS_RENEWAL_RETRY_WAIT() {
      return CREDENTIALS_RENEWAL_RETRY_WAIT;
   }

   public ConfigEntry SHUFFLE_SORT_INIT_BUFFER_SIZE() {
      return SHUFFLE_SORT_INIT_BUFFER_SIZE;
   }

   public ConfigEntry SHUFFLE_CHECKSUM_ENABLED() {
      return SHUFFLE_CHECKSUM_ENABLED;
   }

   public ConfigEntry SHUFFLE_CHECKSUM_ALGORITHM() {
      return SHUFFLE_CHECKSUM_ALGORITHM;
   }

   public ConfigEntry SHUFFLE_COMPRESS() {
      return SHUFFLE_COMPRESS;
   }

   public ConfigEntry SHUFFLE_SPILL_COMPRESS() {
      return SHUFFLE_SPILL_COMPRESS;
   }

   public ConfigEntry MAP_STATUS_COMPRESSION_CODEC() {
      return MAP_STATUS_COMPRESSION_CODEC;
   }

   public ConfigEntry SHUFFLE_SPILL_INITIAL_MEM_THRESHOLD() {
      return SHUFFLE_SPILL_INITIAL_MEM_THRESHOLD;
   }

   public ConfigEntry SHUFFLE_SPILL_BATCH_SIZE() {
      return SHUFFLE_SPILL_BATCH_SIZE;
   }

   public ConfigEntry SHUFFLE_MERGE_PREFER_NIO() {
      return SHUFFLE_MERGE_PREFER_NIO;
   }

   public ConfigEntry SHUFFLE_SORT_BYPASS_MERGE_THRESHOLD() {
      return SHUFFLE_SORT_BYPASS_MERGE_THRESHOLD;
   }

   public ConfigEntry SHUFFLE_MANAGER() {
      return SHUFFLE_MANAGER;
   }

   public ConfigEntry SHUFFLE_REDUCE_LOCALITY_ENABLE() {
      return SHUFFLE_REDUCE_LOCALITY_ENABLE;
   }

   public ConfigEntry SHUFFLE_MAPOUTPUT_MIN_SIZE_FOR_BROADCAST() {
      return SHUFFLE_MAPOUTPUT_MIN_SIZE_FOR_BROADCAST;
   }

   public ConfigEntry SHUFFLE_MAPOUTPUT_DISPATCHER_NUM_THREADS() {
      return SHUFFLE_MAPOUTPUT_DISPATCHER_NUM_THREADS;
   }

   public ConfigEntry SHUFFLE_DETECT_CORRUPT() {
      return SHUFFLE_DETECT_CORRUPT;
   }

   public ConfigEntry SHUFFLE_DETECT_CORRUPT_MEMORY() {
      return SHUFFLE_DETECT_CORRUPT_MEMORY;
   }

   public ConfigEntry SHUFFLE_SYNC() {
      return SHUFFLE_SYNC;
   }

   public ConfigEntry SHUFFLE_UNSAFE_FAST_MERGE_ENABLE() {
      return SHUFFLE_UNSAFE_FAST_MERGE_ENABLE;
   }

   public ConfigEntry SHUFFLE_SORT_USE_RADIXSORT() {
      return SHUFFLE_SORT_USE_RADIXSORT;
   }

   public ConfigEntry SHUFFLE_MIN_NUM_PARTS_TO_HIGHLY_COMPRESS() {
      return SHUFFLE_MIN_NUM_PARTS_TO_HIGHLY_COMPRESS;
   }

   public ConfigEntry SHUFFLE_USE_OLD_FETCH_PROTOCOL() {
      return SHUFFLE_USE_OLD_FETCH_PROTOCOL;
   }

   public ConfigEntry SHUFFLE_HOST_LOCAL_DISK_READING_ENABLED() {
      return SHUFFLE_HOST_LOCAL_DISK_READING_ENABLED;
   }

   public ConfigEntry STORAGE_LOCAL_DISK_BY_EXECUTORS_CACHE_SIZE() {
      return STORAGE_LOCAL_DISK_BY_EXECUTORS_CACHE_SIZE;
   }

   public ConfigEntry MEMORY_MAP_LIMIT_FOR_TESTS() {
      return MEMORY_MAP_LIMIT_FOR_TESTS;
   }

   public ConfigEntry BARRIER_SYNC_TIMEOUT() {
      return BARRIER_SYNC_TIMEOUT;
   }

   public ConfigEntry UNSCHEDULABLE_TASKSET_TIMEOUT() {
      return UNSCHEDULABLE_TASKSET_TIMEOUT;
   }

   public ConfigEntry BARRIER_MAX_CONCURRENT_TASKS_CHECK_INTERVAL() {
      return BARRIER_MAX_CONCURRENT_TASKS_CHECK_INTERVAL;
   }

   public ConfigEntry BARRIER_MAX_CONCURRENT_TASKS_CHECK_MAX_FAILURES() {
      return BARRIER_MAX_CONCURRENT_TASKS_CHECK_MAX_FAILURES;
   }

   public ConfigEntry NUM_CANCELLED_JOB_GROUPS_TO_TRACK() {
      return NUM_CANCELLED_JOB_GROUPS_TO_TRACK;
   }

   public ConfigEntry UNSAFE_EXCEPTION_ON_MEMORY_LEAK() {
      return UNSAFE_EXCEPTION_ON_MEMORY_LEAK;
   }

   public ConfigEntry UNSAFE_SORTER_SPILL_READ_AHEAD_ENABLED() {
      return UNSAFE_SORTER_SPILL_READ_AHEAD_ENABLED;
   }

   public ConfigEntry UNSAFE_SORTER_SPILL_READER_BUFFER_SIZE() {
      return UNSAFE_SORTER_SPILL_READER_BUFFER_SIZE;
   }

   public String DEFAULT_PLUGINS_LIST() {
      return DEFAULT_PLUGINS_LIST;
   }

   public ConfigEntry PLUGINS() {
      return PLUGINS;
   }

   public ConfigEntry CLEANER_PERIODIC_GC_INTERVAL() {
      return CLEANER_PERIODIC_GC_INTERVAL;
   }

   public ConfigEntry CLEANER_REFERENCE_TRACKING() {
      return CLEANER_REFERENCE_TRACKING;
   }

   public ConfigEntry CLEANER_REFERENCE_TRACKING_BLOCKING() {
      return CLEANER_REFERENCE_TRACKING_BLOCKING;
   }

   public ConfigEntry CLEANER_REFERENCE_TRACKING_BLOCKING_SHUFFLE() {
      return CLEANER_REFERENCE_TRACKING_BLOCKING_SHUFFLE;
   }

   public ConfigEntry CLEANER_REFERENCE_TRACKING_CLEAN_CHECKPOINTS() {
      return CLEANER_REFERENCE_TRACKING_CLEAN_CHECKPOINTS;
   }

   public ConfigEntry EXECUTOR_LOGS_ROLLING_STRATEGY() {
      return EXECUTOR_LOGS_ROLLING_STRATEGY;
   }

   public ConfigEntry EXECUTOR_LOGS_ROLLING_TIME_INTERVAL() {
      return EXECUTOR_LOGS_ROLLING_TIME_INTERVAL;
   }

   public ConfigEntry EXECUTOR_LOGS_ROLLING_MAX_SIZE() {
      return EXECUTOR_LOGS_ROLLING_MAX_SIZE;
   }

   public ConfigEntry EXECUTOR_LOGS_ROLLING_MAX_RETAINED_FILES() {
      return EXECUTOR_LOGS_ROLLING_MAX_RETAINED_FILES;
   }

   public ConfigEntry EXECUTOR_LOGS_ROLLING_ENABLE_COMPRESSION() {
      return EXECUTOR_LOGS_ROLLING_ENABLE_COMPRESSION;
   }

   public ConfigEntry MASTER_REST_SERVER_ENABLED() {
      return MASTER_REST_SERVER_ENABLED;
   }

   public OptionalConfigEntry MASTER_REST_SERVER_HOST() {
      return MASTER_REST_SERVER_HOST;
   }

   public ConfigEntry MASTER_REST_SERVER_PORT() {
      return MASTER_REST_SERVER_PORT;
   }

   public ConfigEntry MASTER_REST_SERVER_MAX_THREADS() {
      return MASTER_REST_SERVER_MAX_THREADS;
   }

   public ConfigEntry MASTER_REST_SERVER_FILTERS() {
      return MASTER_REST_SERVER_FILTERS;
   }

   public ConfigEntry MASTER_REST_SERVER_VIRTUAL_THREADS() {
      return MASTER_REST_SERVER_VIRTUAL_THREADS;
   }

   public ConfigEntry MASTER_UI_PORT() {
      return MASTER_UI_PORT;
   }

   public OptionalConfigEntry MASTER_UI_HISTORY_SERVER_URL() {
      return MASTER_UI_HISTORY_SERVER_URL;
   }

   public ConfigEntry MASTER_USE_APP_NAME_AS_APP_ID() {
      return MASTER_USE_APP_NAME_AS_APP_ID;
   }

   public ConfigEntry MASTER_USE_DRIVER_ID_AS_APP_NAME() {
      return MASTER_USE_DRIVER_ID_AS_APP_NAME;
   }

   public ConfigEntry IO_COMPRESSION_SNAPPY_BLOCKSIZE() {
      return IO_COMPRESSION_SNAPPY_BLOCKSIZE;
   }

   public ConfigEntry IO_COMPRESSION_LZ4_BLOCKSIZE() {
      return IO_COMPRESSION_LZ4_BLOCKSIZE;
   }

   public ConfigEntry IO_COMPRESSION_CODEC() {
      return IO_COMPRESSION_CODEC;
   }

   public ConfigEntry IO_COMPRESSION_ZSTD_BUFFERSIZE() {
      return IO_COMPRESSION_ZSTD_BUFFERSIZE;
   }

   public ConfigEntry IO_COMPRESSION_ZSTD_BUFFERPOOL_ENABLED() {
      return IO_COMPRESSION_ZSTD_BUFFERPOOL_ENABLED;
   }

   public ConfigEntry IO_COMPRESSION_ZSTD_WORKERS() {
      return IO_COMPRESSION_ZSTD_WORKERS;
   }

   public ConfigEntry IO_COMPRESSION_ZSTD_LEVEL() {
      return IO_COMPRESSION_ZSTD_LEVEL;
   }

   public ConfigEntry IO_COMPRESSION_LZF_PARALLEL() {
      return IO_COMPRESSION_LZF_PARALLEL;
   }

   public ConfigEntry IO_WARNING_LARGEFILETHRESHOLD() {
      return IO_WARNING_LARGEFILETHRESHOLD;
   }

   public ConfigEntry EVENT_LOG_COMPRESSION_CODEC() {
      return EVENT_LOG_COMPRESSION_CODEC;
   }

   public ConfigEntry BUFFER_SIZE() {
      return BUFFER_SIZE;
   }

   public ConfigEntry LOCALITY_WAIT_PROCESS() {
      return LOCALITY_WAIT_PROCESS;
   }

   public ConfigEntry LOCALITY_WAIT_NODE() {
      return LOCALITY_WAIT_NODE;
   }

   public ConfigEntry LOCALITY_WAIT_RACK() {
      return LOCALITY_WAIT_RACK;
   }

   public ConfigEntry REDUCER_MAX_SIZE_IN_FLIGHT() {
      return REDUCER_MAX_SIZE_IN_FLIGHT;
   }

   public ConfigEntry REDUCER_MAX_REQS_IN_FLIGHT() {
      return REDUCER_MAX_REQS_IN_FLIGHT;
   }

   public ConfigEntry BROADCAST_COMPRESS() {
      return BROADCAST_COMPRESS;
   }

   public ConfigEntry BROADCAST_BLOCKSIZE() {
      return BROADCAST_BLOCKSIZE;
   }

   public ConfigEntry BROADCAST_CHECKSUM() {
      return BROADCAST_CHECKSUM;
   }

   public ConfigEntry BROADCAST_FOR_UDF_COMPRESSION_THRESHOLD() {
      return BROADCAST_FOR_UDF_COMPRESSION_THRESHOLD;
   }

   public ConfigEntry RDD_COMPRESS() {
      return RDD_COMPRESS;
   }

   public ConfigEntry RDD_PARALLEL_LISTING_THRESHOLD() {
      return RDD_PARALLEL_LISTING_THRESHOLD;
   }

   public ConfigEntry RDD_LIMIT_INITIAL_NUM_PARTITIONS() {
      return RDD_LIMIT_INITIAL_NUM_PARTITIONS;
   }

   public ConfigEntry RDD_LIMIT_SCALE_UP_FACTOR() {
      return RDD_LIMIT_SCALE_UP_FACTOR;
   }

   public ConfigEntry SERIALIZER() {
      return SERIALIZER;
   }

   public ConfigEntry SERIALIZER_OBJECT_STREAM_RESET() {
      return SERIALIZER_OBJECT_STREAM_RESET;
   }

   public ConfigEntry SERIALIZER_EXTRA_DEBUG_INFO() {
      return SERIALIZER_EXTRA_DEBUG_INFO;
   }

   public ConfigEntry JARS() {
      return JARS;
   }

   public ConfigEntry FILES() {
      return FILES;
   }

   public ConfigEntry ARCHIVES() {
      return ARCHIVES;
   }

   public ConfigEntry SUBMIT_DEPLOY_MODE() {
      return SUBMIT_DEPLOY_MODE;
   }

   public ConfigEntry SUBMIT_PYTHON_FILES() {
      return SUBMIT_PYTHON_FILES;
   }

   public OptionalConfigEntry SCHEDULER_ALLOCATION_FILE() {
      return SCHEDULER_ALLOCATION_FILE;
   }

   public OptionalConfigEntry SCHEDULER_MIN_REGISTERED_RESOURCES_RATIO() {
      return SCHEDULER_MIN_REGISTERED_RESOURCES_RATIO;
   }

   public ConfigEntry SCHEDULER_MAX_REGISTERED_RESOURCE_WAITING_TIME() {
      return SCHEDULER_MAX_REGISTERED_RESOURCE_WAITING_TIME;
   }

   public ConfigEntry SCHEDULER_MODE() {
      return SCHEDULER_MODE;
   }

   public OptionalConfigEntry SCHEDULER_REVIVE_INTERVAL() {
      return SCHEDULER_REVIVE_INTERVAL;
   }

   public ConfigEntry SPECULATION_ENABLED() {
      return SPECULATION_ENABLED;
   }

   public ConfigEntry SPECULATION_INTERVAL() {
      return SPECULATION_INTERVAL;
   }

   public ConfigEntry SPECULATION_MULTIPLIER() {
      return SPECULATION_MULTIPLIER;
   }

   public ConfigEntry SPECULATION_QUANTILE() {
      return SPECULATION_QUANTILE;
   }

   public ConfigEntry SPECULATION_MIN_THRESHOLD() {
      return SPECULATION_MIN_THRESHOLD;
   }

   public OptionalConfigEntry SPECULATION_TASK_DURATION_THRESHOLD() {
      return SPECULATION_TASK_DURATION_THRESHOLD;
   }

   public ConfigEntry SPECULATION_EFFICIENCY_TASK_PROCESS_RATE_MULTIPLIER() {
      return SPECULATION_EFFICIENCY_TASK_PROCESS_RATE_MULTIPLIER;
   }

   public ConfigEntry SPECULATION_EFFICIENCY_TASK_DURATION_FACTOR() {
      return SPECULATION_EFFICIENCY_TASK_DURATION_FACTOR;
   }

   public ConfigEntry SPECULATION_EFFICIENCY_ENABLE() {
      return SPECULATION_EFFICIENCY_ENABLE;
   }

   public ConfigEntry DECOMMISSION_ENABLED() {
      return DECOMMISSION_ENABLED;
   }

   public OptionalConfigEntry EXECUTOR_DECOMMISSION_KILL_INTERVAL() {
      return EXECUTOR_DECOMMISSION_KILL_INTERVAL;
   }

   public OptionalConfigEntry EXECUTOR_DECOMMISSION_FORCE_KILL_TIMEOUT() {
      return EXECUTOR_DECOMMISSION_FORCE_KILL_TIMEOUT;
   }

   public ConfigEntry EXECUTOR_DECOMMISSION_SIGNAL() {
      return EXECUTOR_DECOMMISSION_SIGNAL;
   }

   public OptionalConfigEntry STAGING_DIR() {
      return STAGING_DIR;
   }

   public OptionalConfigEntry BUFFER_PAGESIZE() {
      return BUFFER_PAGESIZE;
   }

   public ConfigEntry RESOURCE_PROFILE_MERGE_CONFLICTS() {
      return RESOURCE_PROFILE_MERGE_CONFLICTS;
   }

   public ConfigEntry STANDALONE_SUBMIT_WAIT_APP_COMPLETION() {
      return STANDALONE_SUBMIT_WAIT_APP_COMPLETION;
   }

   public ConfigEntry EXECUTOR_ALLOW_SPARK_CONTEXT() {
      return EXECUTOR_ALLOW_SPARK_CONTEXT;
   }

   public ConfigEntry EXECUTOR_ALLOW_SYNC_LOG_LEVEL() {
      return EXECUTOR_ALLOW_SYNC_LOG_LEVEL;
   }

   public ConfigEntry KILL_ON_FATAL_ERROR_DEPTH() {
      return KILL_ON_FATAL_ERROR_DEPTH;
   }

   public ConfigEntry STAGE_MAX_CONSECUTIVE_ATTEMPTS() {
      return STAGE_MAX_CONSECUTIVE_ATTEMPTS;
   }

   public ConfigEntry STAGE_IGNORE_DECOMMISSION_FETCH_FAILURE() {
      return STAGE_IGNORE_DECOMMISSION_FETCH_FAILURE;
   }

   public ConfigEntry SCHEDULER_MAX_RETAINED_REMOVED_EXECUTORS() {
      return SCHEDULER_MAX_RETAINED_REMOVED_EXECUTORS;
   }

   public ConfigEntry SCHEDULER_MAX_RETAINED_UNKNOWN_EXECUTORS() {
      return SCHEDULER_MAX_RETAINED_UNKNOWN_EXECUTORS;
   }

   public ConfigEntry PUSH_BASED_SHUFFLE_ENABLED() {
      return PUSH_BASED_SHUFFLE_ENABLED;
   }

   public ConfigEntry PUSH_BASED_SHUFFLE_MERGE_RESULTS_TIMEOUT() {
      return PUSH_BASED_SHUFFLE_MERGE_RESULTS_TIMEOUT;
   }

   public ConfigEntry PUSH_BASED_SHUFFLE_MERGE_FINALIZE_TIMEOUT() {
      return PUSH_BASED_SHUFFLE_MERGE_FINALIZE_TIMEOUT;
   }

   public ConfigEntry SHUFFLE_MERGER_MAX_RETAINED_LOCATIONS() {
      return SHUFFLE_MERGER_MAX_RETAINED_LOCATIONS;
   }

   public ConfigEntry SHUFFLE_MERGER_LOCATIONS_MIN_THRESHOLD_RATIO() {
      return SHUFFLE_MERGER_LOCATIONS_MIN_THRESHOLD_RATIO;
   }

   public ConfigEntry SHUFFLE_MERGER_LOCATIONS_MIN_STATIC_THRESHOLD() {
      return SHUFFLE_MERGER_LOCATIONS_MIN_STATIC_THRESHOLD;
   }

   public OptionalConfigEntry SHUFFLE_NUM_PUSH_THREADS() {
      return SHUFFLE_NUM_PUSH_THREADS;
   }

   public ConfigEntry SHUFFLE_MAX_BLOCK_SIZE_TO_PUSH() {
      return SHUFFLE_MAX_BLOCK_SIZE_TO_PUSH;
   }

   public ConfigEntry SHUFFLE_MAX_BLOCK_BATCH_SIZE_FOR_PUSH() {
      return SHUFFLE_MAX_BLOCK_BATCH_SIZE_FOR_PUSH;
   }

   public ConfigEntry PUSH_BASED_SHUFFLE_MERGE_FINALIZE_THREADS() {
      return PUSH_BASED_SHUFFLE_MERGE_FINALIZE_THREADS;
   }

   public ConfigEntry PUSH_SHUFFLE_FINALIZE_RPC_THREADS() {
      return PUSH_SHUFFLE_FINALIZE_RPC_THREADS;
   }

   public ConfigEntry PUSH_BASED_SHUFFLE_SIZE_MIN_SHUFFLE_SIZE_TO_WAIT() {
      return PUSH_BASED_SHUFFLE_SIZE_MIN_SHUFFLE_SIZE_TO_WAIT;
   }

   public ConfigEntry PUSH_BASED_SHUFFLE_MIN_PUSH_RATIO() {
      return PUSH_BASED_SHUFFLE_MIN_PUSH_RATIO;
   }

   public ConfigEntry JAR_IVY_REPO_PATH() {
      return JAR_IVY_REPO_PATH;
   }

   public OptionalConfigEntry JAR_IVY_SETTING_PATH() {
      return JAR_IVY_SETTING_PATH;
   }

   public ConfigEntry JAR_PACKAGES() {
      return JAR_PACKAGES;
   }

   public ConfigEntry JAR_PACKAGES_EXCLUSIONS() {
      return JAR_PACKAGES_EXCLUSIONS;
   }

   public ConfigEntry JAR_REPOSITORIES() {
      return JAR_REPOSITORIES;
   }

   public OptionalConfigEntry APP_ATTEMPT_ID() {
      return APP_ATTEMPT_ID;
   }

   public ConfigEntry EXECUTOR_STATE_SYNC_MAX_ATTEMPTS() {
      return EXECUTOR_STATE_SYNC_MAX_ATTEMPTS;
   }

   public ConfigEntry EXECUTOR_REMOVE_DELAY() {
      return EXECUTOR_REMOVE_DELAY;
   }

   public ConfigEntry ALLOW_CUSTOM_CLASSPATH_BY_PROXY_USER_IN_CLUSTER_MODE() {
      return ALLOW_CUSTOM_CLASSPATH_BY_PROXY_USER_IN_CLUSTER_MODE;
   }

   public ConfigEntry RDD_CACHE_VISIBILITY_TRACKING_ENABLED() {
      return RDD_CACHE_VISIBILITY_TRACKING_ENABLED;
   }

   public ConfigEntry STAGE_MAX_ATTEMPTS() {
      return STAGE_MAX_ATTEMPTS;
   }

   public ConfigEntry SHUFFLE_SERVER_RECOVERY_DISABLED() {
      return SHUFFLE_SERVER_RECOVERY_DISABLED;
   }

   public ConfigEntry CONNECT_SCALA_UDF_STUB_PREFIXES() {
      return CONNECT_SCALA_UDF_STUB_PREFIXES;
   }

   public ConfigEntry LEGACY_ABORT_STAGE_AFTER_KILL_TASKS() {
      return LEGACY_ABORT_STAGE_AFTER_KILL_TASKS;
   }

   public ConfigEntry DROP_TASK_INFO_ACCUMULABLES_ON_TASK_COMPLETION() {
      return DROP_TASK_INFO_ACCUMULABLES_ON_TASK_COMPLETION;
   }

   public OptionalConfigEntry SPARK_SHUTDOWN_TIMEOUT_MS() {
      return SPARK_SHUTDOWN_TIMEOUT_MS;
   }

   public ConfigEntry SPARK_API_MODE() {
      return SPARK_API_MODE;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH$1(final String x$5) {
      return x$5.endsWith(File.separator);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$SPARK_LOG_LEVEL$2(final String logLevel) {
      return SparkContext$.MODULE$.VALID_LOG_LEVELS().contains(logLevel);
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

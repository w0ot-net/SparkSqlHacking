package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar.;
import scala.runtime.java8.JFunction1;

public final class Worker$ {
   public static final Worker$ MODULE$ = new Worker$();
   private static final String SPARK_WORKER_PREFIX = "spark.worker";
   private static final OptionalConfigEntry SPARK_WORKER_RESOURCE_FILE = (new ConfigBuilder("spark.worker.resourcesFile")).internal().doc("Path to a file containing the resources allocated to the worker. The file should be formatted as a JSON array of ResourceAllocation objects. Only used internally in standalone mode.").version("3.0.0").stringConf().createOptional();
   private static final ConfigEntry WORKER_TIMEOUT = (new ConfigBuilder("spark.worker.timeout")).version("0.6.2").longConf().createWithDefault(BoxesRunTime.boxToLong(60L));
   private static final ConfigEntry WORKER_INITIAL_REGISTRATION_RETRIES = (new ConfigBuilder("spark.worker.initialRegistrationRetries")).version("4.0.0").internal().doc("The number of retries to reconnect in short intervals (between 5 and 15 seconds).").intConf().checkValue((JFunction1.mcZI.sp)(x$1) -> x$1 > 0, "The number of initial registration retries should be positive").createWithDefault(BoxesRunTime.boxToInteger(6));
   private static final ConfigEntry WORKER_MAX_REGISTRATION_RETRIES = (new ConfigBuilder("spark.worker.maxRegistrationRetries")).version("4.0.0").internal().doc("The max number of retries to reconnect. After spark.worker.initialRegistrationRetries attempts, the interval is between 30 and 90 seconds.").intConf().checkValue((JFunction1.mcZI.sp)(x$2) -> x$2 > 0, "The max number of registration retries should be positive").createWithDefault(BoxesRunTime.boxToInteger(16));
   private static final ConfigEntry WORKER_DRIVER_TERMINATE_TIMEOUT;
   private static final ConfigEntry WORKER_CLEANUP_ENABLED;
   private static final ConfigEntry WORKER_CLEANUP_INTERVAL;
   private static final ConfigEntry APP_DATA_RETENTION;
   private static final ConfigEntry PREFER_CONFIGURED_MASTER_ADDRESS;
   private static final OptionalConfigEntry WORKER_UI_PORT;
   private static final ConfigEntry WORKER_UI_RETAINED_EXECUTORS;
   private static final ConfigEntry WORKER_UI_RETAINED_DRIVERS;
   private static final ConfigEntry UNCOMPRESSED_LOG_FILE_LENGTH_CACHE_SIZE_CONF;
   private static final ConfigEntry WORKER_DECOMMISSION_SIGNAL;
   private static final ConfigEntry WORKER_ID_PATTERN;

   static {
      WORKER_DRIVER_TERMINATE_TIMEOUT = (new ConfigBuilder("spark.worker.driverTerminateTimeout")).version("2.1.2").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("10s");
      WORKER_CLEANUP_ENABLED = (new ConfigBuilder("spark.worker.cleanup.enabled")).version("1.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      WORKER_CLEANUP_INTERVAL = (new ConfigBuilder("spark.worker.cleanup.interval")).version("1.0.0").longConf().createWithDefault(BoxesRunTime.boxToLong(1800L));
      APP_DATA_RETENTION = (new ConfigBuilder("spark.worker.cleanup.appDataTtl")).version("1.0.0").longConf().createWithDefault(BoxesRunTime.boxToLong(604800L));
      PREFER_CONFIGURED_MASTER_ADDRESS = (new ConfigBuilder("spark.worker.preferConfiguredMasterAddress")).version("2.2.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      WORKER_UI_PORT = (new ConfigBuilder("spark.worker.ui.port")).version("1.1.0").intConf().createOptional();
      WORKER_UI_RETAINED_EXECUTORS = (new ConfigBuilder("spark.worker.ui.retainedExecutors")).version("1.5.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1000));
      WORKER_UI_RETAINED_DRIVERS = (new ConfigBuilder("spark.worker.ui.retainedDrivers")).version("1.5.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1000));
      UNCOMPRESSED_LOG_FILE_LENGTH_CACHE_SIZE_CONF = (new ConfigBuilder("spark.worker.ui.compressedLogFileLengthCacheSize")).version("2.0.2").intConf().createWithDefault(BoxesRunTime.boxToInteger(100));
      WORKER_DECOMMISSION_SIGNAL = (new ConfigBuilder("spark.worker.decommission.signal")).doc("The signal that used to trigger the worker to start decommission.").version("3.2.0").stringConf().createWithDefaultString("PWR");
      WORKER_ID_PATTERN = (new ConfigBuilder("spark.worker.idPattern")).internal().doc("The pattern for worker ID generation based on Java `String.format` method. The default value is `worker-%s-%s-%d` which represents the existing worker id string, e.g., `worker-20231109183042-[fe80::1%lo0]-39729`. Please be careful to generate unique IDs").version("4.0.0").stringConf().checkValue((x$3) -> BoxesRunTime.boxToBoolean($anonfun$WORKER_ID_PATTERN$1(x$3)), "Whitespace is not allowed.").createWithDefaultString("worker-%s-%s-%d");
   }

   public String SPARK_WORKER_PREFIX() {
      return SPARK_WORKER_PREFIX;
   }

   public OptionalConfigEntry SPARK_WORKER_RESOURCE_FILE() {
      return SPARK_WORKER_RESOURCE_FILE;
   }

   public ConfigEntry WORKER_TIMEOUT() {
      return WORKER_TIMEOUT;
   }

   public ConfigEntry WORKER_INITIAL_REGISTRATION_RETRIES() {
      return WORKER_INITIAL_REGISTRATION_RETRIES;
   }

   public ConfigEntry WORKER_MAX_REGISTRATION_RETRIES() {
      return WORKER_MAX_REGISTRATION_RETRIES;
   }

   public ConfigEntry WORKER_DRIVER_TERMINATE_TIMEOUT() {
      return WORKER_DRIVER_TERMINATE_TIMEOUT;
   }

   public ConfigEntry WORKER_CLEANUP_ENABLED() {
      return WORKER_CLEANUP_ENABLED;
   }

   public ConfigEntry WORKER_CLEANUP_INTERVAL() {
      return WORKER_CLEANUP_INTERVAL;
   }

   public ConfigEntry APP_DATA_RETENTION() {
      return APP_DATA_RETENTION;
   }

   public ConfigEntry PREFER_CONFIGURED_MASTER_ADDRESS() {
      return PREFER_CONFIGURED_MASTER_ADDRESS;
   }

   public OptionalConfigEntry WORKER_UI_PORT() {
      return WORKER_UI_PORT;
   }

   public ConfigEntry WORKER_UI_RETAINED_EXECUTORS() {
      return WORKER_UI_RETAINED_EXECUTORS;
   }

   public ConfigEntry WORKER_UI_RETAINED_DRIVERS() {
      return WORKER_UI_RETAINED_DRIVERS;
   }

   public ConfigEntry UNCOMPRESSED_LOG_FILE_LENGTH_CACHE_SIZE_CONF() {
      return UNCOMPRESSED_LOG_FILE_LENGTH_CACHE_SIZE_CONF;
   }

   public ConfigEntry WORKER_DECOMMISSION_SIGNAL() {
      return WORKER_DECOMMISSION_SIGNAL;
   }

   public ConfigEntry WORKER_ID_PATTERN() {
      return WORKER_ID_PATTERN;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$WORKER_ID_PATTERN$2(final char x$4) {
      return .MODULE$.isWhitespace$extension(scala.Predef..MODULE$.charWrapper(x$4));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$WORKER_ID_PATTERN$1(final String x$3) {
      return !scala.collection.StringOps..MODULE$.exists$extension(scala.Predef..MODULE$.augmentString(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(x$3), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"20231109000000", "host", BoxesRunTime.boxToInteger(0)}))), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$WORKER_ID_PATTERN$2(BoxesRunTime.unboxToChar(x$4))));
   }

   private Worker$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

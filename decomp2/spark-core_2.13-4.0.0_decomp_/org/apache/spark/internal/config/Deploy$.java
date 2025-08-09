package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import scala.math.Ordering.String.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class Deploy$ {
   public static final Deploy$ MODULE$ = new Deploy$();
   private static final ConfigEntry RECOVERY_MODE = (new ConfigBuilder("spark.deploy.recoveryMode")).version("0.8.1").stringConf().createWithDefault("NONE");
   private static final OptionalConfigEntry RECOVERY_COMPRESSION_CODEC = (new ConfigBuilder("spark.deploy.recoveryCompressionCodec")).doc("A compression codec for persistence engines. none (default), lz4, lzf, snappy, and zstd. Currently, only FILESYSTEM mode supports this configuration.").version("4.0.0").stringConf().createOptional();
   private static final ConfigEntry RECOVERY_MODE_FACTORY = (new ConfigBuilder("spark.deploy.recoveryMode.factory")).version("1.2.0").stringConf().createWithDefault("");
   private static final ConfigEntry RECOVERY_DIRECTORY = (new ConfigBuilder("spark.deploy.recoveryDirectory")).version("0.8.1").stringConf().createWithDefault("");
   private static final OptionalConfigEntry RECOVERY_TIMEOUT;
   private static final OptionalConfigEntry ZOOKEEPER_URL;
   private static final OptionalConfigEntry ZOOKEEPER_DIRECTORY;
   private static final ConfigEntry RETAINED_APPLICATIONS;
   private static final ConfigEntry RETAINED_DRIVERS;
   private static final ConfigEntry REAPER_ITERATIONS;
   private static final ConfigEntry MAX_EXECUTOR_RETRIES;
   private static final ConfigEntry SPREAD_OUT_DRIVERS;
   private static final ConfigEntry SPREAD_OUT_APPS;
   private static final ConfigEntry WORKER_SELECTION_POLICY;
   private static final ConfigEntry DEFAULT_CORES;
   private static final ConfigEntry MAX_DRIVERS;
   private static final OptionalConfigEntry APP_NUMBER_MODULO;
   private static final ConfigEntry DRIVER_ID_PATTERN;
   private static final ConfigEntry APP_ID_PATTERN;

   static {
      RECOVERY_TIMEOUT = (new ConfigBuilder("spark.deploy.recoveryTimeout")).doc("Configures the timeout for recovery process. The default value is the same with " + Worker$.MODULE$.WORKER_TIMEOUT().key() + ".").version("4.0.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(x$1) -> x$1 > 0L, "spark.deploy.recoveryTimeout must be positive.").createOptional();
      ZOOKEEPER_URL = (new ConfigBuilder("spark.deploy.zookeeper.url")).doc("When `" + MODULE$.RECOVERY_MODE().key() + "` is set to ZOOKEEPER, this configuration is used to set the zookeeper URL to connect to.").version("0.8.1").stringConf().createOptional();
      ZOOKEEPER_DIRECTORY = (new ConfigBuilder("spark.deploy.zookeeper.dir")).version("0.8.1").stringConf().createOptional();
      RETAINED_APPLICATIONS = (new ConfigBuilder("spark.deploy.retainedApplications")).version("0.8.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(200));
      RETAINED_DRIVERS = (new ConfigBuilder("spark.deploy.retainedDrivers")).version("1.1.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(200));
      REAPER_ITERATIONS = (new ConfigBuilder("spark.dead.worker.persistence")).version("0.8.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(15));
      MAX_EXECUTOR_RETRIES = (new ConfigBuilder("spark.deploy.maxExecutorRetries")).version("1.6.3").intConf().createWithDefault(BoxesRunTime.boxToInteger(10));
      SPREAD_OUT_DRIVERS = (new ConfigBuilder("spark.deploy.spreadOutDrivers")).version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      SPREAD_OUT_APPS = (new ConfigBuilder("spark.deploy.spreadOutApps")).version("0.6.1").withAlternative("spark.deploy.spreadOut").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      WORKER_SELECTION_POLICY = (new ConfigBuilder("spark.deploy.workerSelectionPolicy")).doc("A policy to assign executors on one of the assignable workers; CORES_FREE_ASC to choose a worker with the least free cores, CORES_FREE_DESC to choose a worker with the most free cores, MEMORY_FREE_ASC to choose a worker with the least free memory, MEMORY_FREE_DESC to choose a worker with the most free memory, WORKER_ID to choose a worker with the smallest worker id. CORES_FREE_DESC is the default behavior.").version("4.0.0").stringConf().transform((x$2) -> x$2.toUpperCase(Locale.ROOT)).checkValues(Deploy.WorkerSelectionPolicy$.MODULE$.values().map((x$3) -> x$3.toString(), .MODULE$)).createWithDefault(Deploy.WorkerSelectionPolicy$.MODULE$.CORES_FREE_DESC().toString());
      DEFAULT_CORES = (new ConfigBuilder("spark.deploy.defaultCores")).version("0.9.0").intConf().checkValue((JFunction1.mcZI.sp)(x$4) -> x$4 > 0, "spark.deploy.defaultCores must be positive.").createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      MAX_DRIVERS = (new ConfigBuilder("spark.deploy.maxDrivers")).doc("The maximum number of running drivers.").version("4.0.0").intConf().checkValue((JFunction1.mcZI.sp)(x$5) -> x$5 > 0, "The maximum number of running drivers should be positive.").createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      APP_NUMBER_MODULO = (new ConfigBuilder("spark.deploy.appNumberModulo")).doc("The modulo for app number. By default, the next of `app-yyyyMMddHHmmss-9999` is `app-yyyyMMddHHmmss-10000`. If we have 10000 as modulo, it will be `app-yyyyMMddHHmmss-0000`. In most cases, the prefix `app-yyyyMMddHHmmss` is increased already during creating 10000 applications.").version("4.0.0").intConf().checkValue((JFunction1.mcZI.sp)(x$6) -> x$6 >= 1000, "The modulo for app number should be greater than or equal to 1000.").createOptional();
      DRIVER_ID_PATTERN = (new ConfigBuilder("spark.deploy.driverIdPattern")).doc("The pattern for driver ID generation based on Java `String.format` method. The default value is `driver-%s-%04d` which represents the existing driver id string , e.g., `driver-20231031224459-0019`. Please be careful to generate unique IDs").version("4.0.0").stringConf().checkValue((x$7) -> BoxesRunTime.boxToBoolean($anonfun$DRIVER_ID_PATTERN$1(x$7)), "Whitespace is not allowed.").createWithDefault("driver-%s-%04d");
      APP_ID_PATTERN = (new ConfigBuilder("spark.deploy.appIdPattern")).doc("The pattern for app ID generation based on Java `String.format` method.. The default value is `app-%s-%04d` which represents the existing app id string, e.g., `app-20231031224509-0008`. Plesae be careful to generate unique IDs.").version("4.0.0").stringConf().checkValue((x$9) -> BoxesRunTime.boxToBoolean($anonfun$APP_ID_PATTERN$1(x$9)), "Whitespace is not allowed.").createWithDefault("app-%s-%04d");
   }

   public ConfigEntry RECOVERY_MODE() {
      return RECOVERY_MODE;
   }

   public OptionalConfigEntry RECOVERY_COMPRESSION_CODEC() {
      return RECOVERY_COMPRESSION_CODEC;
   }

   public ConfigEntry RECOVERY_MODE_FACTORY() {
      return RECOVERY_MODE_FACTORY;
   }

   public ConfigEntry RECOVERY_DIRECTORY() {
      return RECOVERY_DIRECTORY;
   }

   public OptionalConfigEntry RECOVERY_TIMEOUT() {
      return RECOVERY_TIMEOUT;
   }

   public OptionalConfigEntry ZOOKEEPER_URL() {
      return ZOOKEEPER_URL;
   }

   public OptionalConfigEntry ZOOKEEPER_DIRECTORY() {
      return ZOOKEEPER_DIRECTORY;
   }

   public ConfigEntry RETAINED_APPLICATIONS() {
      return RETAINED_APPLICATIONS;
   }

   public ConfigEntry RETAINED_DRIVERS() {
      return RETAINED_DRIVERS;
   }

   public ConfigEntry REAPER_ITERATIONS() {
      return REAPER_ITERATIONS;
   }

   public ConfigEntry MAX_EXECUTOR_RETRIES() {
      return MAX_EXECUTOR_RETRIES;
   }

   public ConfigEntry SPREAD_OUT_DRIVERS() {
      return SPREAD_OUT_DRIVERS;
   }

   public ConfigEntry SPREAD_OUT_APPS() {
      return SPREAD_OUT_APPS;
   }

   public ConfigEntry WORKER_SELECTION_POLICY() {
      return WORKER_SELECTION_POLICY;
   }

   public ConfigEntry DEFAULT_CORES() {
      return DEFAULT_CORES;
   }

   public ConfigEntry MAX_DRIVERS() {
      return MAX_DRIVERS;
   }

   public OptionalConfigEntry APP_NUMBER_MODULO() {
      return APP_NUMBER_MODULO;
   }

   public ConfigEntry DRIVER_ID_PATTERN() {
      return DRIVER_ID_PATTERN;
   }

   public ConfigEntry APP_ID_PATTERN() {
      return APP_ID_PATTERN;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$DRIVER_ID_PATTERN$2(final char x$8) {
      return scala.runtime.RichChar..MODULE$.isWhitespace$extension(scala.Predef..MODULE$.charWrapper(x$8));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$DRIVER_ID_PATTERN$1(final String x$7) {
      return !scala.collection.StringOps..MODULE$.exists$extension(scala.Predef..MODULE$.augmentString(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(x$7), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"20231101000000", BoxesRunTime.boxToInteger(0)}))), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$DRIVER_ID_PATTERN$2(BoxesRunTime.unboxToChar(x$8))));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$APP_ID_PATTERN$2(final char x$10) {
      return scala.runtime.RichChar..MODULE$.isWhitespace$extension(scala.Predef..MODULE$.charWrapper(x$10));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$APP_ID_PATTERN$1(final String x$9) {
      return !scala.collection.StringOps..MODULE$.exists$extension(scala.Predef..MODULE$.augmentString(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(x$9), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"20231101000000", BoxesRunTime.boxToInteger(0)}))), (x$10) -> BoxesRunTime.boxToBoolean($anonfun$APP_ID_PATTERN$2(BoxesRunTime.unboxToChar(x$10))));
   }

   private Deploy$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

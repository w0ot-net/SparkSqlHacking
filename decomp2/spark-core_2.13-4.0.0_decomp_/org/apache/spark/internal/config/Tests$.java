package org.apache.spark.internal.config;

import scala.runtime.BoxesRunTime;

public final class Tests$ {
   public static final Tests$ MODULE$ = new Tests$();
   private static final String TEST_USE_COMPRESSED_OOPS_KEY = "spark.test.useCompressedOops";
   private static final ConfigEntry TEST_MEMORY = (new ConfigBuilder("spark.testing.memory")).version("1.6.0").longConf().createWithDefault(BoxesRunTime.boxToLong(Runtime.getRuntime().maxMemory()));
   private static final ConfigEntry TEST_DYNAMIC_ALLOCATION_SCHEDULE_ENABLED = (new ConfigBuilder("spark.testing.dynamicAllocation.schedule.enabled")).version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
   private static final OptionalConfigEntry IS_TESTING = (new ConfigBuilder("spark.testing")).version("1.0.1").booleanConf().createOptional();
   private static final ConfigEntry TEST_NO_STAGE_RETRY = (new ConfigBuilder("spark.test.noStageRetry")).version("1.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final OptionalConfigEntry TEST_RESERVED_MEMORY = (new ConfigBuilder("spark.testing.reservedMemory")).version("1.6.0").longConf().createOptional();
   private static final ConfigEntry TEST_N_HOSTS = (new ConfigBuilder("spark.testing.nHosts")).version("3.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(5));
   private static final ConfigEntry TEST_N_EXECUTORS_HOST = (new ConfigBuilder("spark.testing.nExecutorsPerHost")).version("3.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(4));
   private static final ConfigEntry TEST_N_CORES_EXECUTOR = (new ConfigBuilder("spark.testing.nCoresPerExecutor")).version("3.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(2));
   private static final ConfigEntry RESOURCES_WARNING_TESTING = (new ConfigBuilder("spark.resources.warnings.testing")).version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final ConfigEntry RESOURCE_PROFILE_MANAGER_TESTING = (new ConfigBuilder("spark.testing.resourceProfileManager")).version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final ConfigEntry SKIP_VALIDATE_CORES_TESTING = (new ConfigBuilder("spark.testing.skipValidateCores")).version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final ConfigEntry TEST_SKIP_ESS_REGISTER;

   static {
      ConfigBuilder var10000 = (new ConfigBuilder("spark.testing.skipESSRegister")).version("4.0.0");
      String var10001 = package$.MODULE$.SHUFFLE_SERVICE_ENABLED().key();
      TEST_SKIP_ESS_REGISTER = var10000.doc("None of Spark testing modes (local, local-cluster) enables shuffle service. So it is hard to test " + var10001 + " when you only want to test this flag but without the real server. This config provides a way to allow tests run with " + package$.MODULE$.SHUFFLE_SERVICE_ENABLED().key() + " enabled without registration failures.").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   }

   public String TEST_USE_COMPRESSED_OOPS_KEY() {
      return TEST_USE_COMPRESSED_OOPS_KEY;
   }

   public ConfigEntry TEST_MEMORY() {
      return TEST_MEMORY;
   }

   public ConfigEntry TEST_DYNAMIC_ALLOCATION_SCHEDULE_ENABLED() {
      return TEST_DYNAMIC_ALLOCATION_SCHEDULE_ENABLED;
   }

   public OptionalConfigEntry IS_TESTING() {
      return IS_TESTING;
   }

   public ConfigEntry TEST_NO_STAGE_RETRY() {
      return TEST_NO_STAGE_RETRY;
   }

   public OptionalConfigEntry TEST_RESERVED_MEMORY() {
      return TEST_RESERVED_MEMORY;
   }

   public ConfigEntry TEST_N_HOSTS() {
      return TEST_N_HOSTS;
   }

   public ConfigEntry TEST_N_EXECUTORS_HOST() {
      return TEST_N_EXECUTORS_HOST;
   }

   public ConfigEntry TEST_N_CORES_EXECUTOR() {
      return TEST_N_CORES_EXECUTOR;
   }

   public ConfigEntry RESOURCES_WARNING_TESTING() {
      return RESOURCES_WARNING_TESTING;
   }

   public ConfigEntry RESOURCE_PROFILE_MANAGER_TESTING() {
      return RESOURCE_PROFILE_MANAGER_TESTING;
   }

   public ConfigEntry SKIP_VALIDATE_CORES_TESTING() {
      return SKIP_VALIDATE_CORES_TESTING;
   }

   public ConfigEntry TEST_SKIP_ESS_REGISTER() {
      return TEST_SKIP_ESS_REGISTER;
   }

   private Tests$() {
   }
}

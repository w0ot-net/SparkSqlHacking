package org.apache.spark.internal.config;

import java.util.concurrent.TimeUnit;
import scala.runtime.BoxesRunTime;

public final class Status$ {
   public static final Status$ MODULE$ = new Status$();
   private static final ConfigEntry ASYNC_TRACKING_ENABLED = (new ConfigBuilder("spark.appStateStore.asyncTracking.enable")).version("2.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
   private static final ConfigEntry LIVE_ENTITY_UPDATE_PERIOD;
   private static final ConfigEntry LIVE_ENTITY_UPDATE_MIN_FLUSH_PERIOD;
   private static final ConfigEntry MAX_RETAINED_JOBS;
   private static final ConfigEntry MAX_RETAINED_STAGES;
   private static final ConfigEntry MAX_RETAINED_TASKS_PER_STAGE;
   private static final ConfigEntry MAX_RETAINED_DEAD_EXECUTORS;
   private static final ConfigEntry MAX_RETAINED_ROOT_NODES;
   private static final ConfigEntry METRICS_APP_STATUS_SOURCE_ENABLED;
   private static final OptionalConfigEntry LIVE_UI_LOCAL_STORE_DIR;

   static {
      LIVE_ENTITY_UPDATE_PERIOD = (new ConfigBuilder("spark.ui.liveUpdate.period")).version("2.3.0").timeConf(TimeUnit.NANOSECONDS).createWithDefaultString("100ms");
      LIVE_ENTITY_UPDATE_MIN_FLUSH_PERIOD = (new ConfigBuilder("spark.ui.liveUpdate.minFlushPeriod")).doc("Minimum time elapsed before stale UI data is flushed. This avoids UI staleness when incoming task events are not fired frequently.").version("2.4.2").timeConf(TimeUnit.NANOSECONDS).createWithDefaultString("1s");
      MAX_RETAINED_JOBS = (new ConfigBuilder("spark.ui.retainedJobs")).version("1.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1000));
      MAX_RETAINED_STAGES = (new ConfigBuilder("spark.ui.retainedStages")).version("0.9.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1000));
      MAX_RETAINED_TASKS_PER_STAGE = (new ConfigBuilder("spark.ui.retainedTasks")).version("2.0.1").intConf().createWithDefault(BoxesRunTime.boxToInteger(100000));
      MAX_RETAINED_DEAD_EXECUTORS = (new ConfigBuilder("spark.ui.retainedDeadExecutors")).version("2.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(100));
      MAX_RETAINED_ROOT_NODES = (new ConfigBuilder("spark.ui.dagGraph.retainedRootRDDs")).version("2.1.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      METRICS_APP_STATUS_SOURCE_ENABLED = (new ConfigBuilder("spark.metrics.appStatusSource.enabled")).doc("Whether Dropwizard/Codahale metrics will be reported for the status of the running spark app.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      LIVE_UI_LOCAL_STORE_DIR = (new ConfigBuilder("spark.ui.store.path")).doc("Local directory where to cache application information for live UI. By default this is not set, meaning all application information will be kept in memory.").version("3.4.0").stringConf().createOptional();
   }

   public ConfigEntry ASYNC_TRACKING_ENABLED() {
      return ASYNC_TRACKING_ENABLED;
   }

   public ConfigEntry LIVE_ENTITY_UPDATE_PERIOD() {
      return LIVE_ENTITY_UPDATE_PERIOD;
   }

   public ConfigEntry LIVE_ENTITY_UPDATE_MIN_FLUSH_PERIOD() {
      return LIVE_ENTITY_UPDATE_MIN_FLUSH_PERIOD;
   }

   public ConfigEntry MAX_RETAINED_JOBS() {
      return MAX_RETAINED_JOBS;
   }

   public ConfigEntry MAX_RETAINED_STAGES() {
      return MAX_RETAINED_STAGES;
   }

   public ConfigEntry MAX_RETAINED_TASKS_PER_STAGE() {
      return MAX_RETAINED_TASKS_PER_STAGE;
   }

   public ConfigEntry MAX_RETAINED_DEAD_EXECUTORS() {
      return MAX_RETAINED_DEAD_EXECUTORS;
   }

   public ConfigEntry MAX_RETAINED_ROOT_NODES() {
      return MAX_RETAINED_ROOT_NODES;
   }

   public ConfigEntry METRICS_APP_STATUS_SOURCE_ENABLED() {
      return METRICS_APP_STATUS_SOURCE_ENABLED;
   }

   public OptionalConfigEntry LIVE_UI_LOCAL_STORE_DIR() {
      return LIVE_UI_LOCAL_STORE_DIR;
   }

   private Status$() {
   }
}

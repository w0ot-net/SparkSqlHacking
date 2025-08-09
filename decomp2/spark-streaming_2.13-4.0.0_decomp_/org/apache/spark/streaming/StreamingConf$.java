package org.apache.spark.streaming;

import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.config.ConfigBuilder;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.streaming.util.OpenHashMapBasedStateMap$;
import scala.runtime.BoxesRunTime;

public final class StreamingConf$ {
   public static final StreamingConf$ MODULE$ = new StreamingConf$();
   private static final ConfigEntry BACKPRESSURE_ENABLED = (new ConfigBuilder("spark.streaming.backpressure.enabled")).version("1.5.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final ConfigEntry RECEIVER_MAX_RATE = (new ConfigBuilder("spark.streaming.receiver.maxRate")).version("1.0.2").longConf().createWithDefault(BoxesRunTime.boxToLong(Long.MAX_VALUE));
   private static final ConfigEntry BACKPRESSURE_INITIAL_RATE;
   private static final ConfigEntry BLOCK_INTERVAL;
   private static final ConfigEntry RECEIVER_WAL_ENABLE_CONF_KEY;
   private static final OptionalConfigEntry RECEIVER_WAL_CLASS_CONF_KEY;
   private static final ConfigEntry RECEIVER_WAL_ROLLING_INTERVAL_CONF_KEY;
   private static final ConfigEntry RECEIVER_WAL_MAX_FAILURES_CONF_KEY;
   private static final ConfigEntry RECEIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY;
   private static final OptionalConfigEntry DRIVER_WAL_CLASS_CONF_KEY;
   private static final ConfigEntry DRIVER_WAL_ROLLING_INTERVAL_CONF_KEY;
   private static final ConfigEntry DRIVER_WAL_MAX_FAILURES_CONF_KEY;
   private static final ConfigEntry DRIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY;
   private static final ConfigEntry DRIVER_WAL_BATCHING_CONF_KEY;
   private static final ConfigEntry DRIVER_WAL_BATCHING_TIMEOUT_CONF_KEY;
   private static final ConfigEntry STREAMING_UNPERSIST;
   private static final ConfigEntry STOP_GRACEFULLY_ON_SHUTDOWN;
   private static final ConfigEntry UI_RETAINED_BATCHES;
   private static final ConfigEntry SESSION_BY_KEY_DELTA_CHAIN_THRESHOLD;
   private static final ConfigEntry BACKPRESSURE_RATE_ESTIMATOR;
   private static final ConfigEntry BACKPRESSURE_PID_PROPORTIONAL;
   private static final ConfigEntry BACKPRESSURE_PID_INTEGRAL;
   private static final ConfigEntry BACKPRESSURE_PID_DERIVED;
   private static final ConfigEntry BACKPRESSURE_PID_MIN_RATE;
   private static final ConfigEntry CONCURRENT_JOBS;
   private static final OptionalConfigEntry GRACEFUL_STOP_TIMEOUT;
   private static final ConfigEntry MANUAL_CLOCK_JUMP;

   static {
      BACKPRESSURE_INITIAL_RATE = (new ConfigBuilder("spark.streaming.backpressure.initialRate")).version("2.0.0").fallbackConf(MODULE$.RECEIVER_MAX_RATE());
      BLOCK_INTERVAL = (new ConfigBuilder("spark.streaming.blockInterval")).version("0.8.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("200ms");
      RECEIVER_WAL_ENABLE_CONF_KEY = (new ConfigBuilder("spark.streaming.receiver.writeAheadLog.enable")).version("1.2.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      RECEIVER_WAL_CLASS_CONF_KEY = (new ConfigBuilder("spark.streaming.receiver.writeAheadLog.class")).version("1.4.0").stringConf().createOptional();
      RECEIVER_WAL_ROLLING_INTERVAL_CONF_KEY = (new ConfigBuilder("spark.streaming.receiver.writeAheadLog.rollingIntervalSecs")).version("1.4.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(60));
      RECEIVER_WAL_MAX_FAILURES_CONF_KEY = (new ConfigBuilder("spark.streaming.receiver.writeAheadLog.maxFailures")).version("1.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(3));
      RECEIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY = (new ConfigBuilder("spark.streaming.receiver.writeAheadLog.closeFileAfterWrite")).version("1.6.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      DRIVER_WAL_CLASS_CONF_KEY = (new ConfigBuilder("spark.streaming.driver.writeAheadLog.class")).version("1.4.0").stringConf().createOptional();
      DRIVER_WAL_ROLLING_INTERVAL_CONF_KEY = (new ConfigBuilder("spark.streaming.driver.writeAheadLog.rollingIntervalSecs")).version("1.4.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(60));
      DRIVER_WAL_MAX_FAILURES_CONF_KEY = (new ConfigBuilder("spark.streaming.driver.writeAheadLog.maxFailures")).version("1.4.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(3));
      DRIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY = (new ConfigBuilder("spark.streaming.driver.writeAheadLog.closeFileAfterWrite")).version("1.6.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      DRIVER_WAL_BATCHING_CONF_KEY = (new ConfigBuilder("spark.streaming.driver.writeAheadLog.allowBatching")).version("1.6.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      DRIVER_WAL_BATCHING_TIMEOUT_CONF_KEY = (new ConfigBuilder("spark.streaming.driver.writeAheadLog.batchingTimeout")).version("1.6.0").longConf().createWithDefault(BoxesRunTime.boxToLong(5000L));
      STREAMING_UNPERSIST = (new ConfigBuilder("spark.streaming.unpersist")).version("0.9.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      STOP_GRACEFULLY_ON_SHUTDOWN = (new ConfigBuilder("spark.streaming.stopGracefullyOnShutdown")).version("1.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      UI_RETAINED_BATCHES = (new ConfigBuilder("spark.streaming.ui.retainedBatches")).version("1.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1000));
      SESSION_BY_KEY_DELTA_CHAIN_THRESHOLD = (new ConfigBuilder("spark.streaming.sessionByKey.deltaChainThreshold")).version("1.6.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(OpenHashMapBasedStateMap$.MODULE$.DELTA_CHAIN_LENGTH_THRESHOLD()));
      BACKPRESSURE_RATE_ESTIMATOR = (new ConfigBuilder("spark.streaming.backpressure.rateEstimator")).version("1.5.0").stringConf().createWithDefault("pid");
      BACKPRESSURE_PID_PROPORTIONAL = (new ConfigBuilder("spark.streaming.backpressure.pid.proportional")).version("1.5.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble((double)1.0F));
      BACKPRESSURE_PID_INTEGRAL = (new ConfigBuilder("spark.streaming.backpressure.pid.integral")).version("1.5.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble(0.2));
      BACKPRESSURE_PID_DERIVED = (new ConfigBuilder("spark.streaming.backpressure.pid.derived")).version("1.5.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble((double)0.0F));
      BACKPRESSURE_PID_MIN_RATE = (new ConfigBuilder("spark.streaming.backpressure.pid.minRate")).version("1.5.0").doubleConf().createWithDefault(BoxesRunTime.boxToDouble((double)100.0F));
      CONCURRENT_JOBS = (new ConfigBuilder("spark.streaming.concurrentJobs")).version("0.7.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1));
      GRACEFUL_STOP_TIMEOUT = (new ConfigBuilder("spark.streaming.gracefulStopTimeout")).version("1.0.0").timeConf(TimeUnit.MILLISECONDS).createOptional();
      MANUAL_CLOCK_JUMP = (new ConfigBuilder("spark.streaming.manualClock.jump")).version("0.7.0").longConf().createWithDefault(BoxesRunTime.boxToLong(0L));
   }

   public ConfigEntry BACKPRESSURE_ENABLED() {
      return BACKPRESSURE_ENABLED;
   }

   public ConfigEntry RECEIVER_MAX_RATE() {
      return RECEIVER_MAX_RATE;
   }

   public ConfigEntry BACKPRESSURE_INITIAL_RATE() {
      return BACKPRESSURE_INITIAL_RATE;
   }

   public ConfigEntry BLOCK_INTERVAL() {
      return BLOCK_INTERVAL;
   }

   public ConfigEntry RECEIVER_WAL_ENABLE_CONF_KEY() {
      return RECEIVER_WAL_ENABLE_CONF_KEY;
   }

   public OptionalConfigEntry RECEIVER_WAL_CLASS_CONF_KEY() {
      return RECEIVER_WAL_CLASS_CONF_KEY;
   }

   public ConfigEntry RECEIVER_WAL_ROLLING_INTERVAL_CONF_KEY() {
      return RECEIVER_WAL_ROLLING_INTERVAL_CONF_KEY;
   }

   public ConfigEntry RECEIVER_WAL_MAX_FAILURES_CONF_KEY() {
      return RECEIVER_WAL_MAX_FAILURES_CONF_KEY;
   }

   public ConfigEntry RECEIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY() {
      return RECEIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY;
   }

   public OptionalConfigEntry DRIVER_WAL_CLASS_CONF_KEY() {
      return DRIVER_WAL_CLASS_CONF_KEY;
   }

   public ConfigEntry DRIVER_WAL_ROLLING_INTERVAL_CONF_KEY() {
      return DRIVER_WAL_ROLLING_INTERVAL_CONF_KEY;
   }

   public ConfigEntry DRIVER_WAL_MAX_FAILURES_CONF_KEY() {
      return DRIVER_WAL_MAX_FAILURES_CONF_KEY;
   }

   public ConfigEntry DRIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY() {
      return DRIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY;
   }

   public ConfigEntry DRIVER_WAL_BATCHING_CONF_KEY() {
      return DRIVER_WAL_BATCHING_CONF_KEY;
   }

   public ConfigEntry DRIVER_WAL_BATCHING_TIMEOUT_CONF_KEY() {
      return DRIVER_WAL_BATCHING_TIMEOUT_CONF_KEY;
   }

   public ConfigEntry STREAMING_UNPERSIST() {
      return STREAMING_UNPERSIST;
   }

   public ConfigEntry STOP_GRACEFULLY_ON_SHUTDOWN() {
      return STOP_GRACEFULLY_ON_SHUTDOWN;
   }

   public ConfigEntry UI_RETAINED_BATCHES() {
      return UI_RETAINED_BATCHES;
   }

   public ConfigEntry SESSION_BY_KEY_DELTA_CHAIN_THRESHOLD() {
      return SESSION_BY_KEY_DELTA_CHAIN_THRESHOLD;
   }

   public ConfigEntry BACKPRESSURE_RATE_ESTIMATOR() {
      return BACKPRESSURE_RATE_ESTIMATOR;
   }

   public ConfigEntry BACKPRESSURE_PID_PROPORTIONAL() {
      return BACKPRESSURE_PID_PROPORTIONAL;
   }

   public ConfigEntry BACKPRESSURE_PID_INTEGRAL() {
      return BACKPRESSURE_PID_INTEGRAL;
   }

   public ConfigEntry BACKPRESSURE_PID_DERIVED() {
      return BACKPRESSURE_PID_DERIVED;
   }

   public ConfigEntry BACKPRESSURE_PID_MIN_RATE() {
      return BACKPRESSURE_PID_MIN_RATE;
   }

   public ConfigEntry CONCURRENT_JOBS() {
      return CONCURRENT_JOBS;
   }

   public OptionalConfigEntry GRACEFUL_STOP_TIMEOUT() {
      return GRACEFUL_STOP_TIMEOUT;
   }

   public ConfigEntry MANUAL_CLOCK_JUMP() {
      return MANUAL_CLOCK_JUMP;
   }

   private StreamingConf$() {
   }
}

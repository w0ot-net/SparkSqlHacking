package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeUnit;
import org.apache.spark.network.util.ByteUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class Python$ {
   public static final Python$ MODULE$ = new Python$();
   private static final ConfigEntry PYTHON_WORKER_REUSE = (new ConfigBuilder("spark.python.worker.reuse")).version("1.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
   private static final ConfigEntry PYTHON_TASK_KILL_TIMEOUT;
   private static final ConfigEntry PYTHON_USE_DAEMON;
   private static final ConfigEntry PYTHON_LOG_INFO;
   private static final OptionalConfigEntry PYTHON_DAEMON_MODULE;
   private static final OptionalConfigEntry PYTHON_WORKER_MODULE;
   private static final OptionalConfigEntry PYSPARK_EXECUTOR_MEMORY;
   private static final ConfigEntry PYTHON_AUTH_SOCKET_TIMEOUT;
   private static final ConfigEntry PYTHON_WORKER_FAULTHANLDER_ENABLED;
   private static final String PYTHON_WORKER_IDLE_TIMEOUT_SECONDS_KEY;
   private static final ConfigEntry PYTHON_WORKER_IDLE_TIMEOUT_SECONDS;

   static {
      PYTHON_TASK_KILL_TIMEOUT = (new ConfigBuilder("spark.python.task.killTimeout")).version("2.2.2").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("2s");
      PYTHON_USE_DAEMON = (new ConfigBuilder("spark.python.use.daemon")).version("2.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      PYTHON_LOG_INFO = (new ConfigBuilder("spark.executor.python.worker.log.details")).version("3.5.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      PYTHON_DAEMON_MODULE = (new ConfigBuilder("spark.python.daemon.module")).version("2.4.0").stringConf().createOptional();
      PYTHON_WORKER_MODULE = (new ConfigBuilder("spark.python.worker.module")).version("2.4.0").stringConf().createOptional();
      PYSPARK_EXECUTOR_MEMORY = (new ConfigBuilder("spark.executor.pyspark.memory")).version("2.4.0").bytesConf(ByteUnit.MiB).createOptional();
      PYTHON_AUTH_SOCKET_TIMEOUT = (new ConfigBuilder("spark.python.authenticate.socketTimeout")).internal().version("3.1.0").timeConf(TimeUnit.SECONDS).createWithDefaultString("15s");
      PYTHON_WORKER_FAULTHANLDER_ENABLED = (new ConfigBuilder("spark.python.worker.faulthandler.enabled")).doc("When true, Python workers set up the faulthandler for the case when the Python worker exits unexpectedly (crashes), and shows the stack trace of the moment the Python worker crashes in the error message if captured successfully.").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      PYTHON_WORKER_IDLE_TIMEOUT_SECONDS_KEY = "spark.python.worker.idleTimeoutSeconds";
      PYTHON_WORKER_IDLE_TIMEOUT_SECONDS = (new ConfigBuilder(MODULE$.PYTHON_WORKER_IDLE_TIMEOUT_SECONDS_KEY())).doc("The time (in seconds) Spark will wait for activity (e.g., data transfer or communication) from a Python worker before considering it potentially idle or unresponsive. When the timeout is triggered, Spark will log the network-related status for debugging purposes. However, the Python worker will remain active and continue waiting for communication. The default is `0` that means no timeout.").version("4.0.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(x$1) -> x$1 >= 0L, "The idle timeout should be 0 or positive.").createWithDefault(BoxesRunTime.boxToLong(0L));
   }

   public ConfigEntry PYTHON_WORKER_REUSE() {
      return PYTHON_WORKER_REUSE;
   }

   public ConfigEntry PYTHON_TASK_KILL_TIMEOUT() {
      return PYTHON_TASK_KILL_TIMEOUT;
   }

   public ConfigEntry PYTHON_USE_DAEMON() {
      return PYTHON_USE_DAEMON;
   }

   public ConfigEntry PYTHON_LOG_INFO() {
      return PYTHON_LOG_INFO;
   }

   public OptionalConfigEntry PYTHON_DAEMON_MODULE() {
      return PYTHON_DAEMON_MODULE;
   }

   public OptionalConfigEntry PYTHON_WORKER_MODULE() {
      return PYTHON_WORKER_MODULE;
   }

   public OptionalConfigEntry PYSPARK_EXECUTOR_MEMORY() {
      return PYSPARK_EXECUTOR_MEMORY;
   }

   public ConfigEntry PYTHON_AUTH_SOCKET_TIMEOUT() {
      return PYTHON_AUTH_SOCKET_TIMEOUT;
   }

   public ConfigEntry PYTHON_WORKER_FAULTHANLDER_ENABLED() {
      return PYTHON_WORKER_FAULTHANLDER_ENABLED;
   }

   private String PYTHON_WORKER_IDLE_TIMEOUT_SECONDS_KEY() {
      return PYTHON_WORKER_IDLE_TIMEOUT_SECONDS_KEY;
   }

   public ConfigEntry PYTHON_WORKER_IDLE_TIMEOUT_SECONDS() {
      return PYTHON_WORKER_IDLE_TIMEOUT_SECONDS;
   }

   private Python$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

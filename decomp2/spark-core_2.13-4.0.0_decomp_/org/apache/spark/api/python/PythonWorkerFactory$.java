package org.apache.spark.api.python;

import java.util.concurrent.TimeUnit;

public final class PythonWorkerFactory$ {
   public static final PythonWorkerFactory$ MODULE$ = new PythonWorkerFactory$();
   private static final int PROCESS_WAIT_TIMEOUT_MS = 10000;
   private static final long IDLE_WORKER_TIMEOUT_NS;
   private static final String defaultDaemonModule;

   static {
      IDLE_WORKER_TIMEOUT_NS = TimeUnit.MINUTES.toNanos(1L);
      defaultDaemonModule = "pyspark.daemon";
   }

   public int PROCESS_WAIT_TIMEOUT_MS() {
      return PROCESS_WAIT_TIMEOUT_MS;
   }

   public long IDLE_WORKER_TIMEOUT_NS() {
      return IDLE_WORKER_TIMEOUT_NS;
   }

   public String defaultDaemonModule() {
      return defaultDaemonModule;
   }

   private PythonWorkerFactory$() {
   }
}

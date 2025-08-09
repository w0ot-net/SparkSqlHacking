package org.apache.spark.api.plugin;

import org.apache.spark.annotation.DeveloperApi;

@DeveloperApi
public interface SparkPlugin {
   DriverPlugin driverPlugin();

   ExecutorPlugin executorPlugin();
}

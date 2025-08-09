package org.apache.spark.api.plugin;

import java.util.Collections;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.annotation.DeveloperApi;

@DeveloperApi
public interface DriverPlugin {
   default Map init(SparkContext sc, PluginContext pluginContext) {
      return Collections.emptyMap();
   }

   default void registerMetrics(String appId, PluginContext pluginContext) {
   }

   default Object receive(Object message) throws Exception {
      throw new UnsupportedOperationException();
   }

   default void shutdown() {
   }
}

package org.apache.spark.api.plugin;

import com.codahale.metrics.MetricRegistry;
import java.io.IOException;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;

@DeveloperApi
public interface PluginContext {
   MetricRegistry metricRegistry();

   SparkConf conf();

   String executorID();

   String hostname();

   Map resources();

   void send(Object var1) throws IOException;

   Object ask(Object var1) throws Exception;
}

package org.apache.spark.api.plugin;

import java.util.Map;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.annotation.DeveloperApi;

@DeveloperApi
public interface ExecutorPlugin {
   default void init(PluginContext ctx, Map extraConf) {
   }

   default void shutdown() {
   }

   default void onTaskStart() {
   }

   default void onTaskSucceeded() {
   }

   default void onTaskFailed(TaskFailedReason failureReason) {
   }
}

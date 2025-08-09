package io.vertx.core.spi;

import io.vertx.core.VertxOptions;
import io.vertx.core.impl.VertxBuilder;
import io.vertx.core.impl.launcher.commands.BareCommand;
import io.vertx.core.json.JsonObject;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.metrics.VertxMetrics;

public interface VertxMetricsFactory extends VertxServiceProvider {
   default void init(VertxBuilder builder) {
      if (builder.metrics() == null) {
         JsonObject config = builder.config();
         VertxOptions options = builder.options();
         MetricsOptions metricsOptions;
         if (config != null && config.containsKey("metricsOptions")) {
            metricsOptions = this.newOptions(config.getJsonObject("metricsOptions"));
         } else {
            metricsOptions = options.getMetricsOptions();
            if (metricsOptions == null) {
               metricsOptions = this.newOptions();
            } else {
               metricsOptions = this.newOptions(metricsOptions);
            }
         }

         BareCommand.configureFromSystemProperties(metricsOptions, "vertx.metrics.options.");
         builder.options().setMetricsOptions(metricsOptions);
         if (options.getMetricsOptions().isEnabled()) {
            builder.metrics(this.metrics(options));
         }
      }

   }

   VertxMetrics metrics(VertxOptions var1);

   default MetricsOptions newOptions() {
      return new MetricsOptions();
   }

   default MetricsOptions newOptions(MetricsOptions options) {
      return this.newOptions(options.toJson());
   }

   default MetricsOptions newOptions(JsonObject jsonObject) {
      return new MetricsOptions(jsonObject);
   }
}

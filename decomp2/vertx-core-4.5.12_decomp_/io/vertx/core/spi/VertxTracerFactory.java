package io.vertx.core.spi;

import io.vertx.core.impl.VertxBuilder;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.tracing.TracingOptions;

public interface VertxTracerFactory extends VertxServiceProvider {
   VertxTracerFactory NOOP = (options) -> VertxTracer.NOOP;

   default void init(VertxBuilder builder) {
      TracingOptions options = builder.options().getTracingOptions();
      if (options != null && builder.tracer() == null) {
         builder.tracer(this.tracer(options));
      }

   }

   VertxTracer tracer(TracingOptions var1);

   default TracingOptions newOptions() {
      return new TracingOptions();
   }

   default TracingOptions newOptions(JsonObject jsonObject) {
      return new TracingOptions(jsonObject);
   }
}

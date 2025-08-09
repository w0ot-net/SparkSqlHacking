package io.vertx.core;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.spi.VertxMetricsFactory;
import io.vertx.core.spi.VertxTracerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import java.util.Objects;

@VertxGen
public interface VertxBuilder {
   @Fluent
   VertxBuilder with(VertxOptions var1);

   @GenIgnore({"permitted-type"})
   @Fluent
   VertxBuilder withMetrics(VertxMetricsFactory var1);

   @GenIgnore({"permitted-type"})
   @Fluent
   VertxBuilder withTracer(VertxTracerFactory var1);

   @GenIgnore({"permitted-type"})
   @Fluent
   VertxBuilder withClusterManager(ClusterManager var1);

   Vertx build();

   Future buildClustered();

   default void buildClustered(Handler handler) {
      Objects.requireNonNull(handler);
      Future<Vertx> fut = this.buildClustered();
      fut.onComplete(handler);
   }
}

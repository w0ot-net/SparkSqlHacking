package io.vertx.core.impl.launcher;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.impl.VertxBuilder;
import io.vertx.core.json.JsonObject;

public interface VertxLifecycleHooks {
   void afterConfigParsed(JsonObject var1);

   default VertxBuilder createVertxBuilder(JsonObject config) {
      return config == null ? new VertxBuilder() : new VertxBuilder(config);
   }

   void beforeStartingVertx(VertxOptions var1);

   void afterStartingVertx(Vertx var1);

   void beforeDeployingVerticle(DeploymentOptions var1);

   void beforeStoppingVertx(Vertx var1);

   void afterStoppingVertx();

   void handleDeployFailed(Vertx var1, String var2, DeploymentOptions var3, Throwable var4);
}

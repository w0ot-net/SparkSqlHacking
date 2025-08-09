package io.vertx.core.impl;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import java.util.Set;

public interface Deployment {
   boolean addChild(Deployment var1);

   void removeChild(Deployment var1);

   Future doUndeploy(ContextInternal var1);

   JsonObject config();

   String deploymentID();

   String verticleIdentifier();

   DeploymentOptions deploymentOptions();

   Set getContexts();

   Set getVerticles();

   void undeployHandler(Handler var1);

   boolean isChild();
}

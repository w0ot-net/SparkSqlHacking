package io.vertx.core.impl.launcher.commands;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxException;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class VertxIsolatedDeployer {
   private static final Logger log = LoggerFactory.getLogger(VertxIsolatedDeployer.class);
   private String deploymentId;
   private Vertx vertx;

   public void deploy(String verticle, Vertx vertx, DeploymentOptions options, Handler completionHandler) {
      this.vertx = vertx;
      String message = options.isWorker() ? "deploying worker verticle" : "deploying verticle";
      vertx.deployVerticle(verticle, options, this.createHandler(message, completionHandler));
   }

   public void undeploy(Handler completionHandler) {
      this.vertx.undeploy(this.deploymentId, (res) -> {
         if (res.failed()) {
            log.error("Failed in undeploying " + this.deploymentId, res.cause());
         } else {
            log.info("Succeeded in undeploying " + this.deploymentId);
         }

         this.deploymentId = null;
         completionHandler.handle(res);
      });
   }

   private Handler createHandler(String message, Handler completionHandler) {
      return (res) -> {
         if (res.failed()) {
            Throwable cause = res.cause();
            if (cause instanceof VertxException) {
               VertxException ve = (VertxException)cause;
               if (ve.getCause() == null) {
                  log.error(ve.getMessage());
               } else {
                  log.error(ve.getMessage(), ve.getCause());
               }
            } else {
               log.error("Failed in " + message, cause);
            }
         } else {
            this.deploymentId = (String)res.result();
            log.info("Succeeded in " + message);
         }

         if (completionHandler != null) {
            completionHandler.handle(res);
         }

      };
   }
}

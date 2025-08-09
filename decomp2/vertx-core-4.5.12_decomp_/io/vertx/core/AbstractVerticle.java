package io.vertx.core;

import io.vertx.core.json.JsonObject;
import java.util.List;

public abstract class AbstractVerticle implements Verticle {
   protected Vertx vertx;
   protected Context context;

   public Vertx getVertx() {
      return this.vertx;
   }

   public void init(Vertx vertx, Context context) {
      this.vertx = vertx;
      this.context = context;
   }

   public String deploymentID() {
      return this.context.deploymentID();
   }

   public JsonObject config() {
      return this.context.config();
   }

   public List processArgs() {
      return this.context.processArgs();
   }

   public void start(Promise startPromise) throws Exception {
      this.start();
      startPromise.complete();
   }

   public void stop(Promise stopPromise) throws Exception {
      this.stop();
      stopPromise.complete();
   }

   public void start() throws Exception {
   }

   public void stop() throws Exception {
   }
}

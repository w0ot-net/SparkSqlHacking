package io.vertx.core.tracing;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.VertxTracerFactory;

@DataObject
@JsonGen(
   publicConverter = false
)
public class TracingOptions {
   private JsonObject json;
   private VertxTracerFactory factory;

   public TracingOptions() {
   }

   public TracingOptions(TracingOptions other) {
      this.factory = other.factory;
   }

   public TracingOptions(JsonObject json) {
      this();
      TracingOptionsConverter.fromJson(json, this);
      this.json = json.copy();
   }

   public VertxTracerFactory getFactory() {
      return this.factory;
   }

   /** @deprecated */
   @Deprecated
   public TracingOptions setFactory(VertxTracerFactory factory) {
      this.factory = factory;
      return this;
   }

   public TracingOptions copy() {
      return new TracingOptions(this);
   }

   public JsonObject toJson() {
      return this.json != null ? this.json.copy() : new JsonObject();
   }

   public String toString() {
      return "TracingOptions{json=" + this.json + '}';
   }
}

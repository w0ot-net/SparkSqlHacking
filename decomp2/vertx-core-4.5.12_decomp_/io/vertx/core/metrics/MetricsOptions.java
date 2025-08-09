package io.vertx.core.metrics;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.VertxMetricsFactory;

@DataObject
@JsonGen(
   publicConverter = false
)
public class MetricsOptions {
   public static final boolean DEFAULT_METRICS_ENABLED = false;
   private boolean enabled;
   private JsonObject json;
   private VertxMetricsFactory factory;

   public MetricsOptions() {
      this.enabled = false;
   }

   public MetricsOptions(MetricsOptions other) {
      this.enabled = other.isEnabled();
      this.factory = other.factory;
   }

   public MetricsOptions(JsonObject json) {
      this();
      MetricsOptionsConverter.fromJson(json, this);
      this.json = json.copy();
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public MetricsOptions setEnabled(boolean enable) {
      this.enabled = enable;
      return this;
   }

   public VertxMetricsFactory getFactory() {
      return this.factory;
   }

   /** @deprecated */
   @Deprecated
   public MetricsOptions setFactory(VertxMetricsFactory factory) {
      this.factory = factory;
      return this;
   }

   public JsonObject toJson() {
      JsonObject json = this.json;
      if (json == null) {
         json = new JsonObject();
         MetricsOptionsConverter.toJson(this, json);
      }

      return json;
   }

   public String toString() {
      return "MetricsOptions{enabled=" + this.enabled + ", json=" + this.json + '}';
   }
}

package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.MultiMap;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import io.vertx.core.tracing.TracingPolicy;
import java.util.Map;
import java.util.Objects;

@DataObject
public class DeliveryOptions {
   public static final long DEFAULT_TIMEOUT = 30000L;
   public static final boolean DEFAULT_LOCAL_ONLY = false;
   public static final TracingPolicy DEFAULT_TRACING_POLICY;
   private long timeout = 30000L;
   private String codecName;
   private MultiMap headers;
   private boolean localOnly = false;
   private TracingPolicy tracingPolicy;

   public DeliveryOptions() {
      this.tracingPolicy = DEFAULT_TRACING_POLICY;
   }

   public DeliveryOptions(DeliveryOptions other) {
      this.tracingPolicy = DEFAULT_TRACING_POLICY;
      this.timeout = other.getSendTimeout();
      this.codecName = other.getCodecName();
      if (other.getHeaders() != null) {
         this.headers = MultiMap.caseInsensitiveMultiMap().addAll(other.getHeaders());
      }

      this.localOnly = other.localOnly;
      this.tracingPolicy = other.tracingPolicy;
   }

   public DeliveryOptions(JsonObject json) {
      this.tracingPolicy = DEFAULT_TRACING_POLICY;
      this.timeout = json.getLong("timeout", 30000L);
      this.codecName = json.getString("codecName", (String)null);
      JsonObject hdrs = json.getJsonObject("headers", (JsonObject)null);
      if (hdrs != null) {
         this.headers = MultiMap.caseInsensitiveMultiMap();

         for(Map.Entry entry : hdrs) {
            if (!(entry.getValue() instanceof String)) {
               throw new IllegalStateException("Invalid type for message header value " + entry.getValue().getClass());
            }

            this.headers.set((String)entry.getKey(), (String)entry.getValue());
         }
      }

      this.localOnly = json.getBoolean("localOnly", false);
      String tracingPolicyStr = json.getString("tracingPolicy");
      this.tracingPolicy = tracingPolicyStr != null ? TracingPolicy.valueOf(tracingPolicyStr) : DEFAULT_TRACING_POLICY;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      json.put("timeout", this.timeout);
      if (this.codecName != null) {
         json.put("codecName", this.codecName);
      }

      if (this.headers != null) {
         JsonObject hJson = new JsonObject();
         this.headers.entries().forEach((entry) -> hJson.put((String)entry.getKey(), entry.getValue()));
         json.put("headers", hJson);
      }

      json.put("localOnly", this.localOnly);
      if (this.tracingPolicy != null) {
         json.put("tracingPolicy", this.tracingPolicy.name());
      }

      return json;
   }

   public long getSendTimeout() {
      return this.timeout;
   }

   public DeliveryOptions setSendTimeout(long timeout) {
      Arguments.require(timeout >= 1L, "sendTimeout must be >= 1");
      this.timeout = timeout;
      return this;
   }

   public String getCodecName() {
      return this.codecName;
   }

   public DeliveryOptions setCodecName(String codecName) {
      this.codecName = codecName;
      return this;
   }

   public DeliveryOptions addHeader(String key, String value) {
      this.checkHeaders();
      Objects.requireNonNull(key, "no null key accepted");
      Objects.requireNonNull(value, "no null value accepted");
      this.headers.add(key, value);
      return this;
   }

   @GenIgnore
   public DeliveryOptions setHeaders(MultiMap headers) {
      this.headers = headers;
      return this;
   }

   @GenIgnore
   public MultiMap getHeaders() {
      return this.headers;
   }

   private void checkHeaders() {
      if (this.headers == null) {
         this.headers = MultiMap.caseInsensitiveMultiMap();
      }

   }

   public boolean isLocalOnly() {
      return this.localOnly;
   }

   public DeliveryOptions setLocalOnly(boolean localOnly) {
      this.localOnly = localOnly;
      return this;
   }

   public TracingPolicy getTracingPolicy() {
      return this.tracingPolicy;
   }

   public DeliveryOptions setTracingPolicy(TracingPolicy tracingPolicy) {
      this.tracingPolicy = tracingPolicy;
      return this;
   }

   static {
      DEFAULT_TRACING_POLICY = TracingPolicy.PROPAGATE;
   }
}

package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class PoolOptions {
   private int http1MaxSize;
   private int http2MaxSize;
   private int cleanerPeriod;
   private int eventLoopSize;
   private int maxWaitQueueSize;

   public PoolOptions() {
      this.http1MaxSize = 5;
      this.http2MaxSize = 1;
      this.cleanerPeriod = 1000;
      this.eventLoopSize = 0;
      this.maxWaitQueueSize = -1;
   }

   public PoolOptions(PoolOptions other) {
      this.http1MaxSize = other.http1MaxSize;
      this.http2MaxSize = other.http2MaxSize;
      this.cleanerPeriod = other.cleanerPeriod;
      this.eventLoopSize = other.eventLoopSize;
      this.maxWaitQueueSize = other.maxWaitQueueSize;
   }

   public PoolOptions(JsonObject json) {
      PoolOptionsConverter.fromJson(json, this);
   }

   public int getHttp1MaxSize() {
      return this.http1MaxSize;
   }

   public PoolOptions setHttp1MaxSize(int http1MaxSize) {
      if (http1MaxSize < 1) {
         throw new IllegalArgumentException("maxPoolSize must be > 0");
      } else {
         this.http1MaxSize = http1MaxSize;
         return this;
      }
   }

   public int getHttp2MaxSize() {
      return this.http2MaxSize;
   }

   public PoolOptions setHttp2MaxSize(int max) {
      if (max < 1) {
         throw new IllegalArgumentException("http2MaxPoolSize must be > 0");
      } else {
         this.http2MaxSize = max;
         return this;
      }
   }

   public int getCleanerPeriod() {
      return this.cleanerPeriod;
   }

   public PoolOptions setCleanerPeriod(int cleanerPeriod) {
      this.cleanerPeriod = cleanerPeriod;
      return this;
   }

   public int getEventLoopSize() {
      return this.eventLoopSize;
   }

   public PoolOptions setEventLoopSize(int eventLoopSize) {
      Arguments.require(eventLoopSize >= 0, "poolEventLoopSize must be >= 0");
      this.eventLoopSize = eventLoopSize;
      return this;
   }

   public PoolOptions setMaxWaitQueueSize(int maxWaitQueueSize) {
      this.maxWaitQueueSize = maxWaitQueueSize;
      return this;
   }

   public int getMaxWaitQueueSize() {
      return this.maxWaitQueueSize;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      PoolOptionsConverter.toJson(this, json);
      return json;
   }
}

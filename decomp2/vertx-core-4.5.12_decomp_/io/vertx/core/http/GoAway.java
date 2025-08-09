package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class GoAway {
   private long errorCode;
   private int lastStreamId;
   private Buffer debugData;

   public GoAway() {
   }

   public GoAway(JsonObject json) {
      GoAwayConverter.fromJson(json, this);
   }

   public GoAway(GoAway that) {
      this.errorCode = that.errorCode;
      this.lastStreamId = that.lastStreamId;
      this.debugData = that.debugData != null ? that.debugData.copy() : null;
   }

   public long getErrorCode() {
      return this.errorCode;
   }

   public GoAway setErrorCode(long errorCode) {
      this.errorCode = errorCode;
      return this;
   }

   public int getLastStreamId() {
      return this.lastStreamId;
   }

   public GoAway setLastStreamId(int lastStreamId) {
      this.lastStreamId = lastStreamId;
      return this;
   }

   public Buffer getDebugData() {
      return this.debugData;
   }

   public GoAway setDebugData(Buffer debugData) {
      this.debugData = debugData;
      return this;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      GoAwayConverter.toJson(this, json);
      return json;
   }
}

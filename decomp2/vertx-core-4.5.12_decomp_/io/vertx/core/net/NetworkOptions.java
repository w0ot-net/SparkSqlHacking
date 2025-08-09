package io.vertx.core.net;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public abstract class NetworkOptions {
   public static final int DEFAULT_SEND_BUFFER_SIZE = -1;
   public static final int DEFAULT_RECEIVE_BUFFER_SIZE = -1;
   public static final int DEFAULT_TRAFFIC_CLASS = -1;
   public static final boolean DEFAULT_REUSE_ADDRESS = true;
   public static final boolean DEFAULT_REUSE_PORT = false;
   public static final boolean DEFAULT_LOG_ENABLED = false;
   public static final ByteBufFormat DEFAULT_LOG_ACTIVITY_FORMAT;
   private int sendBufferSize;
   private int receiveBufferSize;
   private int trafficClass;
   private boolean reuseAddress;
   private boolean logActivity;
   private ByteBufFormat activityLogDataFormat;
   private boolean reusePort;

   public NetworkOptions() {
      this.sendBufferSize = -1;
      this.receiveBufferSize = -1;
      this.reuseAddress = true;
      this.trafficClass = -1;
      this.logActivity = false;
      this.activityLogDataFormat = DEFAULT_LOG_ACTIVITY_FORMAT;
      this.reusePort = false;
   }

   public NetworkOptions(NetworkOptions other) {
      this.sendBufferSize = other.getSendBufferSize();
      this.receiveBufferSize = other.getReceiveBufferSize();
      this.reuseAddress = other.isReuseAddress();
      this.reusePort = other.isReusePort();
      this.trafficClass = other.getTrafficClass();
      this.logActivity = other.logActivity;
      this.activityLogDataFormat = other.activityLogDataFormat;
   }

   public NetworkOptions(JsonObject json) {
      this();
      NetworkOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      NetworkOptionsConverter.toJson(this, json);
      return json;
   }

   public int getSendBufferSize() {
      return this.sendBufferSize;
   }

   public NetworkOptions setSendBufferSize(int sendBufferSize) {
      Arguments.require(sendBufferSize > 0 || sendBufferSize == -1, "sendBufferSize must be > 0");
      this.sendBufferSize = sendBufferSize;
      return this;
   }

   public int getReceiveBufferSize() {
      return this.receiveBufferSize;
   }

   public NetworkOptions setReceiveBufferSize(int receiveBufferSize) {
      Arguments.require(receiveBufferSize > 0 || receiveBufferSize == -1, "receiveBufferSize must be > 0");
      this.receiveBufferSize = receiveBufferSize;
      return this;
   }

   public boolean isReuseAddress() {
      return this.reuseAddress;
   }

   public NetworkOptions setReuseAddress(boolean reuseAddress) {
      this.reuseAddress = reuseAddress;
      return this;
   }

   public int getTrafficClass() {
      return this.trafficClass;
   }

   public NetworkOptions setTrafficClass(int trafficClass) {
      Arguments.requireInRange(trafficClass, -1, 255, "trafficClass tc must be 0 <= tc <= 255");
      this.trafficClass = trafficClass;
      return this;
   }

   public boolean getLogActivity() {
      return this.logActivity;
   }

   public ByteBufFormat getActivityLogDataFormat() {
      return this.activityLogDataFormat;
   }

   public NetworkOptions setLogActivity(boolean logActivity) {
      this.logActivity = logActivity;
      return this;
   }

   public NetworkOptions setActivityLogDataFormat(ByteBufFormat activityLogDataFormat) {
      this.activityLogDataFormat = activityLogDataFormat;
      return this;
   }

   public boolean isReusePort() {
      return this.reusePort;
   }

   public NetworkOptions setReusePort(boolean reusePort) {
      this.reusePort = reusePort;
      return this;
   }

   static {
      DEFAULT_LOG_ACTIVITY_FORMAT = ByteBufFormat.HEX_DUMP;
   }
}

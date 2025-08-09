package io.vertx.core.dns;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class DnsClientOptions {
   public static final int DEFAULT_PORT = -1;
   public static final String DEFAULT_HOST = null;
   public static final long DEFAULT_QUERY_TIMEOUT = 5000L;
   public static final boolean DEFAULT_LOG_ENABLED = false;
   public static final ByteBufFormat DEFAULT_LOG_ACTIVITY_FORMAT;
   public static final boolean DEFAULT_RECURSION_DESIRED = true;
   private int port = -1;
   private String host;
   private long queryTimeout;
   private boolean logActivity;
   private ByteBufFormat activityLogFormat;
   private boolean recursionDesired;

   public DnsClientOptions() {
      this.host = DEFAULT_HOST;
      this.queryTimeout = 5000L;
      this.logActivity = false;
      this.activityLogFormat = DEFAULT_LOG_ACTIVITY_FORMAT;
      this.recursionDesired = true;
   }

   public DnsClientOptions(JsonObject json) {
      this.host = DEFAULT_HOST;
      this.queryTimeout = 5000L;
      this.logActivity = false;
      this.activityLogFormat = DEFAULT_LOG_ACTIVITY_FORMAT;
      this.recursionDesired = true;
      DnsClientOptionsConverter.fromJson(json, this);
   }

   public DnsClientOptions(DnsClientOptions other) {
      this.host = DEFAULT_HOST;
      this.queryTimeout = 5000L;
      this.logActivity = false;
      this.activityLogFormat = DEFAULT_LOG_ACTIVITY_FORMAT;
      this.recursionDesired = true;
      this.port = other.port;
      this.host = other.host;
      this.queryTimeout = other.queryTimeout;
      this.logActivity = other.logActivity;
      this.activityLogFormat = other.activityLogFormat;
      this.recursionDesired = other.recursionDesired;
   }

   public int getPort() {
      return this.port;
   }

   public DnsClientOptions setPort(int port) {
      if (port < 1 && port != -1) {
         throw new IllegalArgumentException("DNS client port " + port + " must be > 0 or equal to DEFAULT_PORT");
      } else {
         this.port = port;
         return this;
      }
   }

   public String getHost() {
      return this.host;
   }

   public DnsClientOptions setHost(String host) {
      this.host = host;
      return this;
   }

   public long getQueryTimeout() {
      return this.queryTimeout;
   }

   public DnsClientOptions setQueryTimeout(long queryTimeout) {
      if (queryTimeout < 1L) {
         throw new IllegalArgumentException("queryTimeout must be > 0");
      } else {
         this.queryTimeout = queryTimeout;
         return this;
      }
   }

   public boolean getLogActivity() {
      return this.logActivity;
   }

   public ByteBufFormat getActivityLogFormat() {
      return this.activityLogFormat;
   }

   public DnsClientOptions setLogActivity(boolean logActivity) {
      this.logActivity = logActivity;
      return this;
   }

   public DnsClientOptions setActivityLogFormat(ByteBufFormat activityLogFormat) {
      this.activityLogFormat = activityLogFormat;
      return this;
   }

   public boolean isRecursionDesired() {
      return this.recursionDesired;
   }

   public DnsClientOptions setRecursionDesired(boolean recursionDesired) {
      this.recursionDesired = recursionDesired;
      return this;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      DnsClientOptionsConverter.toJson(this, json);
      return json;
   }

   static {
      DEFAULT_LOG_ACTIVITY_FORMAT = ByteBufFormat.SIMPLE;
   }
}

package io.vertx.core.net;

import io.netty.util.internal.ObjectUtil;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class TrafficShapingOptions {
   public static final long DEFAULT_INBOUND_GLOBAL_BANDWIDTH_LIMIT = 0L;
   public static final long DEFAULT_OUTBOUND_GLOBAL_BANDWIDTH_LIMIT = 0L;
   public static final long DEFAULT_PEAK_OUTBOUND_GLOBAL_BANDWIDTH = 419430400L;
   public static final long DEFAULT_CHECK_INTERVAL;
   public static final long DEFAULT_MAX_TIME;
   private long inboundGlobalBandwidth;
   private long outboundGlobalBandwidth;
   private long peakOutboundGlobalBandwidth;
   private long maxDelayToWait;
   private TimeUnit maxDelayToWaitTimeUnit;
   private long checkIntervalForStats;
   private TimeUnit checkIntervalForStatsTimeUnit;

   public TrafficShapingOptions() {
      this.inboundGlobalBandwidth = 0L;
      this.outboundGlobalBandwidth = 0L;
      this.peakOutboundGlobalBandwidth = 419430400L;
      this.maxDelayToWait = DEFAULT_MAX_TIME;
      this.maxDelayToWaitTimeUnit = TimeUnit.MILLISECONDS;
      this.checkIntervalForStats = DEFAULT_CHECK_INTERVAL;
      this.checkIntervalForStatsTimeUnit = TimeUnit.MILLISECONDS;
   }

   public TrafficShapingOptions(TrafficShapingOptions other) {
      this.inboundGlobalBandwidth = other.getInboundGlobalBandwidth();
      this.outboundGlobalBandwidth = other.getOutboundGlobalBandwidth();
      this.peakOutboundGlobalBandwidth = other.getPeakOutboundGlobalBandwidth();
      this.maxDelayToWait = other.getMaxDelayToWait();
      this.checkIntervalForStats = other.getCheckIntervalForStats();
      this.maxDelayToWaitTimeUnit = other.getMaxDelayToWaitTimeUnit();
      this.checkIntervalForStatsTimeUnit = other.getCheckIntervalForStatsTimeUnit();
   }

   public TrafficShapingOptions(JsonObject json) {
      TrafficShapingOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      TrafficShapingOptionsConverter.toJson(this, json);
      return json;
   }

   public TrafficShapingOptions setInboundGlobalBandwidth(long inboundGlobalBandwidth) {
      this.inboundGlobalBandwidth = inboundGlobalBandwidth;
      return this;
   }

   public TrafficShapingOptions setOutboundGlobalBandwidth(long outboundGlobalBandwidth) {
      this.outboundGlobalBandwidth = outboundGlobalBandwidth;
      return this;
   }

   public TrafficShapingOptions setMaxDelayToWait(long maxDelayToWaitTime) {
      this.maxDelayToWait = maxDelayToWaitTime;
      ObjectUtil.checkPositive(this.maxDelayToWait, "maxDelayToWaitTime");
      return this;
   }

   public TrafficShapingOptions setMaxDelayToWaitUnit(TimeUnit maxDelayToWaitTimeUnit) {
      this.maxDelayToWaitTimeUnit = (TimeUnit)Objects.requireNonNull(maxDelayToWaitTimeUnit, "maxDelayToWaitTimeUnit");
      return this;
   }

   public TrafficShapingOptions setCheckIntervalForStats(long checkIntervalForStats) {
      this.checkIntervalForStats = checkIntervalForStats;
      ObjectUtil.checkPositiveOrZero(this.checkIntervalForStats, "checkIntervalForStats");
      return this;
   }

   public TrafficShapingOptions setCheckIntervalForStatsTimeUnit(TimeUnit checkIntervalForStatsTimeUnit) {
      this.checkIntervalForStatsTimeUnit = (TimeUnit)Objects.requireNonNull(checkIntervalForStatsTimeUnit, "checkIntervalForStatsTimeUnit");
      return this;
   }

   public TrafficShapingOptions setPeakOutboundGlobalBandwidth(long peakOutboundGlobalBandwidth) {
      this.peakOutboundGlobalBandwidth = peakOutboundGlobalBandwidth;
      ObjectUtil.checkPositive(this.peakOutboundGlobalBandwidth, "peakOutboundGlobalBandwidth");
      return this;
   }

   public long getInboundGlobalBandwidth() {
      return this.inboundGlobalBandwidth;
   }

   public long getOutboundGlobalBandwidth() {
      return this.outboundGlobalBandwidth;
   }

   public long getPeakOutboundGlobalBandwidth() {
      return this.peakOutboundGlobalBandwidth;
   }

   public long getMaxDelayToWait() {
      return this.maxDelayToWait;
   }

   public TimeUnit getMaxDelayToWaitTimeUnit() {
      return this.maxDelayToWaitTimeUnit;
   }

   public long getCheckIntervalForStats() {
      return this.checkIntervalForStats;
   }

   public TimeUnit getCheckIntervalForStatsTimeUnit() {
      return this.checkIntervalForStatsTimeUnit;
   }

   public boolean equals(Object obj) {
      TrafficShapingOptions that = (TrafficShapingOptions)obj;
      return this.inboundGlobalBandwidth == that.inboundGlobalBandwidth && this.outboundGlobalBandwidth == that.outboundGlobalBandwidth && this.peakOutboundGlobalBandwidth == that.peakOutboundGlobalBandwidth && this.maxDelayToWait == that.maxDelayToWait && this.maxDelayToWaitTimeUnit == that.maxDelayToWaitTimeUnit && this.checkIntervalForStats == that.checkIntervalForStats && this.checkIntervalForStatsTimeUnit == that.checkIntervalForStatsTimeUnit;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.inboundGlobalBandwidth, this.outboundGlobalBandwidth, this.peakOutboundGlobalBandwidth, this.maxDelayToWait, this.maxDelayToWaitTimeUnit, this.checkIntervalForStats, this.checkIntervalForStatsTimeUnit});
   }

   static {
      DEFAULT_CHECK_INTERVAL = TimeUnit.SECONDS.toMillis(1L);
      DEFAULT_MAX_TIME = TimeUnit.SECONDS.toMillis(15L);
   }
}

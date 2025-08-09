package io.vertx.core.datagram;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetworkOptions;

@DataObject
@JsonGen(
   publicConverter = false
)
public class DatagramSocketOptions extends NetworkOptions {
   public static final boolean DEFAULT_BROADCAST = false;
   public static final boolean DEFAULT_LOOPBACK_MODE_DISABLED = true;
   public static final int DEFAULT_MULTICAST_TIME_TO_LIVE = -1;
   public static final String DEFAULT_MULTICAST_NETWORK_INTERFACE = null;
   public static final boolean DEFAULT_REUSE_ADDRESS = false;
   public static final boolean DEFAULT_IPV6 = false;
   private boolean broadcast;
   private boolean loopbackModeDisabled;
   private int multicastTimeToLive;
   private String multicastNetworkInterface;
   private boolean ipV6;

   public DatagramSocketOptions() {
      this.init();
      this.setReuseAddress(false);
   }

   public DatagramSocketOptions(DatagramSocketOptions other) {
      super((NetworkOptions)other);
      this.broadcast = other.isBroadcast();
      this.loopbackModeDisabled = other.isLoopbackModeDisabled();
      this.multicastTimeToLive = other.getMulticastTimeToLive();
      this.multicastNetworkInterface = other.getMulticastNetworkInterface();
      this.ipV6 = other.isIpV6();
   }

   public DatagramSocketOptions(JsonObject json) {
      super(json);
      this.init();
      DatagramSocketOptionsConverter.fromJson(json, this);
   }

   private void init() {
      this.broadcast = false;
      this.loopbackModeDisabled = true;
      this.multicastTimeToLive = -1;
      this.multicastNetworkInterface = DEFAULT_MULTICAST_NETWORK_INTERFACE;
      this.ipV6 = false;
   }

   public int getSendBufferSize() {
      return super.getSendBufferSize();
   }

   public DatagramSocketOptions setSendBufferSize(int sendBufferSize) {
      super.setSendBufferSize(sendBufferSize);
      return this;
   }

   public int getReceiveBufferSize() {
      return super.getReceiveBufferSize();
   }

   public DatagramSocketOptions setReceiveBufferSize(int receiveBufferSize) {
      super.setReceiveBufferSize(receiveBufferSize);
      return this;
   }

   public DatagramSocketOptions setReuseAddress(boolean reuseAddress) {
      super.setReuseAddress(reuseAddress);
      return this;
   }

   public DatagramSocketOptions setReusePort(boolean reusePort) {
      return (DatagramSocketOptions)super.setReusePort(reusePort);
   }

   public int getTrafficClass() {
      return super.getTrafficClass();
   }

   public DatagramSocketOptions setTrafficClass(int trafficClass) {
      super.setTrafficClass(trafficClass);
      return this;
   }

   public boolean isBroadcast() {
      return this.broadcast;
   }

   public DatagramSocketOptions setBroadcast(boolean broadcast) {
      this.broadcast = broadcast;
      return this;
   }

   public boolean isLoopbackModeDisabled() {
      return this.loopbackModeDisabled;
   }

   public DatagramSocketOptions setLoopbackModeDisabled(boolean loopbackModeDisabled) {
      this.loopbackModeDisabled = loopbackModeDisabled;
      return this;
   }

   public int getMulticastTimeToLive() {
      return this.multicastTimeToLive;
   }

   public DatagramSocketOptions setMulticastTimeToLive(int multicastTimeToLive) {
      Arguments.require(multicastTimeToLive >= 0, "multicastTimeToLive must be >= 0");
      this.multicastTimeToLive = multicastTimeToLive;
      return this;
   }

   public String getMulticastNetworkInterface() {
      return this.multicastNetworkInterface;
   }

   public DatagramSocketOptions setMulticastNetworkInterface(String multicastNetworkInterface) {
      this.multicastNetworkInterface = multicastNetworkInterface;
      return this;
   }

   public boolean isIpV6() {
      return this.ipV6;
   }

   public DatagramSocketOptions setIpV6(boolean ipV6) {
      this.ipV6 = ipV6;
      return this;
   }

   public DatagramSocketOptions setLogActivity(boolean logEnabled) {
      return (DatagramSocketOptions)super.setLogActivity(logEnabled);
   }

   public DatagramSocketOptions setActivityLogDataFormat(ByteBufFormat activityLogDataFormat) {
      return (DatagramSocketOptions)super.setActivityLogDataFormat(activityLogDataFormat);
   }
}

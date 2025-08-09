package org.apache.zookeeper.server;

import java.util.Date;
import org.apache.jute.BinaryInputArchive;
import org.apache.zookeeper.Version;
import org.apache.zookeeper.jmx.ZKMBeanInfo;
import org.apache.zookeeper.server.quorum.CommitProcessor;

public class ZooKeeperServerBean implements ZooKeeperServerMXBean, ZKMBeanInfo {
   private final Date startTime = new Date();
   private final String name;
   protected final ZooKeeperServer zks;

   public ZooKeeperServerBean(ZooKeeperServer zks) {
      this.zks = zks;
      this.name = "StandaloneServer_port" + zks.getClientPort();
   }

   public String getClientPort() {
      return Integer.toString(this.zks.getClientPort());
   }

   public String getName() {
      return this.name;
   }

   public boolean isHidden() {
      return false;
   }

   public String getStartTime() {
      return this.startTime.toString();
   }

   public String getVersion() {
      return Version.getFullVersion();
   }

   public double getAvgRequestLatency() {
      return this.zks.serverStats().getAvgLatency();
   }

   public long getMaxRequestLatency() {
      return this.zks.serverStats().getMaxLatency();
   }

   public long getMinRequestLatency() {
      return this.zks.serverStats().getMinLatency();
   }

   public long getOutstandingRequests() {
      return this.zks.serverStats().getOutstandingRequests();
   }

   public int getTickTime() {
      return this.zks.getTickTime();
   }

   public void setTickTime(int tickTime) {
      this.zks.setTickTime(tickTime);
   }

   public int getMaxClientCnxnsPerHost() {
      return this.zks.getMaxClientCnxnsPerHost();
   }

   public void setMaxClientCnxnsPerHost(int max) {
      if (this.zks.serverCnxnFactory != null) {
         this.zks.serverCnxnFactory.setMaxClientCnxnsPerHost(max);
      }

      if (this.zks.secureServerCnxnFactory != null) {
         this.zks.secureServerCnxnFactory.setMaxClientCnxnsPerHost(max);
      }

   }

   public int getMinSessionTimeout() {
      return this.zks.getMinSessionTimeout();
   }

   public void setMinSessionTimeout(int min) {
      this.zks.setMinSessionTimeout(min);
   }

   public int getMaxSessionTimeout() {
      return this.zks.getMaxSessionTimeout();
   }

   public void setMaxSessionTimeout(int max) {
      this.zks.setMaxSessionTimeout(max);
   }

   public long getDataDirSize() {
      return this.zks.getDataDirSize();
   }

   public long getLogDirSize() {
      return this.zks.getLogDirSize();
   }

   public long getPacketsReceived() {
      return this.zks.serverStats().getPacketsReceived();
   }

   public long getPacketsSent() {
      return this.zks.serverStats().getPacketsSent();
   }

   public long getFsyncThresholdExceedCount() {
      return this.zks.serverStats().getFsyncThresholdExceedCount();
   }

   public long getAuthFailedCount() {
      return this.zks.serverStats().getAuthFailedCount();
   }

   public long getNonMTLSRemoteConnCount() {
      return this.zks.serverStats().getNonMTLSRemoteConnCount();
   }

   public long getNonMTLSLocalConnCount() {
      return this.zks.serverStats().getNonMTLSLocalConnCount();
   }

   public void resetLatency() {
      this.zks.serverStats().resetLatency();
   }

   public void resetMaxLatency() {
      this.zks.serverStats().resetMaxLatency();
   }

   public void resetFsyncThresholdExceedCount() {
      this.zks.serverStats().resetFsyncThresholdExceedCount();
   }

   public void resetAuthFailedCount() {
      this.zks.serverStats().resetAuthFailedCount();
   }

   public void resetNonMTLSConnCount() {
      this.zks.serverStats().resetNonMTLSRemoteConnCount();
      this.zks.serverStats().resetNonMTLSLocalConnCount();
   }

   public void resetStatistics() {
      ServerStats serverStats = this.zks.serverStats();
      serverStats.resetRequestCounters();
      serverStats.resetLatency();
      serverStats.resetFsyncThresholdExceedCount();
      serverStats.resetAuthFailedCount();
      serverStats.resetNonMTLSRemoteConnCount();
      serverStats.resetNonMTLSLocalConnCount();
   }

   public long getNumAliveConnections() {
      return (long)this.zks.getNumAliveConnections();
   }

   public String getSecureClientPort() {
      return this.zks.secureServerCnxnFactory != null ? Integer.toString(this.zks.secureServerCnxnFactory.getLocalPort()) : "";
   }

   public String getSecureClientAddress() {
      return this.zks.secureServerCnxnFactory != null ? String.format("%s:%d", this.zks.secureServerCnxnFactory.getLocalAddress().getHostString(), this.zks.secureServerCnxnFactory.getLocalPort()) : "";
   }

   public long getTxnLogElapsedSyncTime() {
      return this.zks.getTxnLogElapsedSyncTime();
   }

   public int getJuteMaxBufferSize() {
      return BinaryInputArchive.maxBuffer;
   }

   public int getLastClientResponseSize() {
      return this.zks.serverStats().getClientResponseStats().getLastBufferSize();
   }

   public int getMinClientResponseSize() {
      return this.zks.serverStats().getClientResponseStats().getMinBufferSize();
   }

   public int getMaxClientResponseSize() {
      return this.zks.serverStats().getClientResponseStats().getMaxBufferSize();
   }

   public boolean getResponseCachingEnabled() {
      return this.zks.isResponseCachingEnabled();
   }

   public void setResponseCachingEnabled(boolean isEnabled) {
      this.zks.setResponseCachingEnabled(isEnabled);
   }

   public int getConnectionMaxTokens() {
      return this.zks.connThrottle().getMaxTokens();
   }

   public void setConnectionMaxTokens(int val) {
      this.zks.connThrottle().setMaxTokens(val);
   }

   public int getConnectionTokenFillTime() {
      return this.zks.connThrottle().getFillTime();
   }

   public void setConnectionTokenFillTime(int val) {
      this.zks.connThrottle().setFillTime(val);
   }

   public int getConnectionTokenFillCount() {
      return this.zks.connThrottle().getFillCount();
   }

   public void setConnectionTokenFillCount(int val) {
      this.zks.connThrottle().setFillCount(val);
   }

   public int getConnectionFreezeTime() {
      return this.zks.connThrottle().getFreezeTime();
   }

   public void setConnectionFreezeTime(int val) {
      this.zks.connThrottle().setFreezeTime(val);
   }

   public double getConnectionDropIncrease() {
      return this.zks.connThrottle().getDropIncrease();
   }

   public void setConnectionDropIncrease(double val) {
      this.zks.connThrottle().setDropIncrease(val);
   }

   public double getConnectionDropDecrease() {
      return this.zks.connThrottle().getDropDecrease();
   }

   public void setConnectionDropDecrease(double val) {
      this.zks.connThrottle().setDropDecrease(val);
   }

   public double getConnectionDecreaseRatio() {
      return this.zks.connThrottle().getDecreasePoint();
   }

   public void setConnectionDecreaseRatio(double val) {
      this.zks.connThrottle().setDecreasePoint(val);
   }

   public int getCommitProcMaxReadBatchSize() {
      return CommitProcessor.getMaxReadBatchSize();
   }

   public void setCommitProcMaxReadBatchSize(int size) {
      CommitProcessor.setMaxReadBatchSize(size);
   }

   public int getCommitProcMaxCommitBatchSize() {
      return CommitProcessor.getMaxCommitBatchSize();
   }

   public void setCommitProcMaxCommitBatchSize(int size) {
      CommitProcessor.setMaxCommitBatchSize(size);
   }

   public long getFlushDelay() {
      return this.zks.getFlushDelay();
   }

   public void setFlushDelay(long delay) {
      ZooKeeperServer.setFlushDelay(delay);
   }

   public int getThrottledOpWaitTime() {
      return ZooKeeperServer.getThrottledOpWaitTime();
   }

   public void setThrottledOpWaitTime(int val) {
      ZooKeeperServer.setThrottledOpWaitTime(val);
   }

   public int getRequestThrottleLimit() {
      return RequestThrottler.getMaxRequests();
   }

   public void setRequestThrottleLimit(int requests) {
      RequestThrottler.setMaxRequests(requests);
   }

   public int getRequestThrottleStallTime() {
      return RequestThrottler.getStallTime();
   }

   public void setRequestThrottleStallTime(int time) {
      RequestThrottler.setStallTime(time);
   }

   public boolean getRequestThrottleDropStale() {
      return RequestThrottler.getDropStaleRequests();
   }

   public void setRequestThrottleDropStale(boolean drop) {
      RequestThrottler.setDropStaleRequests(drop);
   }

   public long getMaxWriteQueuePollTime() {
      return this.zks.getMaxWriteQueuePollTime();
   }

   public void setMaxWriteQueuePollTime(long delay) {
      ZooKeeperServer.setMaxWriteQueuePollTime(delay);
   }

   public boolean getRequestStaleLatencyCheck() {
      return Request.getStaleLatencyCheck();
   }

   public void setRequestStaleLatencyCheck(boolean check) {
      Request.setStaleLatencyCheck(check);
   }

   public int getMaxBatchSize() {
      return this.zks.getMaxBatchSize();
   }

   public void setMaxBatchSize(int size) {
      ZooKeeperServer.setMaxBatchSize(size);
   }

   public boolean getRequestStaleConnectionCheck() {
      return Request.getStaleConnectionCheck();
   }

   public void setRequestStaleConnectionCheck(boolean check) {
      Request.setStaleConnectionCheck(check);
   }

   public int getLargeRequestMaxBytes() {
      return this.zks.getLargeRequestMaxBytes();
   }

   public void setLargeRequestMaxBytes(int bytes) {
      this.zks.setLargeRequestMaxBytes(bytes);
   }

   public int getLargeRequestThreshold() {
      return this.zks.getLargeRequestThreshold();
   }

   public void setLargeRequestThreshold(int threshold) {
      this.zks.setLargeRequestThreshold(threshold);
   }

   public int getMaxCnxns() {
      return ServerCnxnHelper.getMaxCnxns(this.zks.secureServerCnxnFactory, this.zks.serverCnxnFactory);
   }
}

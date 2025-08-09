package org.apache.zookeeper.server;

import java.util.concurrent.atomic.AtomicLong;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.server.metric.AvgMinMaxCounter;
import org.apache.zookeeper.server.quorum.BufferStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerStats {
   private static final Logger LOG = LoggerFactory.getLogger(ServerStats.class);
   private final AtomicLong packetsSent = new AtomicLong();
   private final AtomicLong packetsReceived = new AtomicLong();
   private final AvgMinMaxCounter requestLatency = new AvgMinMaxCounter("request_latency");
   private final AtomicLong fsyncThresholdExceedCount = new AtomicLong(0L);
   private final BufferStats clientResponseStats = new BufferStats();
   private AtomicLong nonMTLSRemoteConnCntr = new AtomicLong(0L);
   private AtomicLong nonMTLSLocalConnCntr = new AtomicLong(0L);
   private AtomicLong authFailedCntr = new AtomicLong(0L);
   private final Provider provider;
   private final long startTime = Time.currentElapsedTime();

   public ServerStats(Provider provider) {
      this.provider = provider;
   }

   public long getMinLatency() {
      return this.requestLatency.getMin();
   }

   public double getAvgLatency() {
      return this.requestLatency.getAvg();
   }

   public long getMaxLatency() {
      return this.requestLatency.getMax();
   }

   public long getOutstandingRequests() {
      return this.provider.getOutstandingRequests();
   }

   public long getLastProcessedZxid() {
      return this.provider.getLastProcessedZxid();
   }

   public long getDataDirSize() {
      return this.provider.getDataDirSize();
   }

   public long getLogDirSize() {
      return this.provider.getLogDirSize();
   }

   public long getPacketsReceived() {
      return this.packetsReceived.get();
   }

   public long getPacketsSent() {
      return this.packetsSent.get();
   }

   public String getServerState() {
      return this.provider.getState();
   }

   public int getNumAliveClientConnections() {
      return this.provider.getNumAliveConnections();
   }

   public long getUptime() {
      return Time.currentElapsedTime() - this.startTime;
   }

   public boolean isProviderNull() {
      return this.provider == null;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Latency min/avg/max: " + this.getMinLatency() + "/" + this.getAvgLatency() + "/" + this.getMaxLatency() + "\n");
      sb.append("Received: " + this.getPacketsReceived() + "\n");
      sb.append("Sent: " + this.getPacketsSent() + "\n");
      sb.append("Connections: " + this.getNumAliveClientConnections() + "\n");
      if (this.provider != null) {
         sb.append("Outstanding: " + this.getOutstandingRequests() + "\n");
         sb.append("Zxid: 0x" + Long.toHexString(this.getLastProcessedZxid()) + "\n");
      }

      sb.append("Mode: " + this.getServerState() + "\n");
      return sb.toString();
   }

   public void updateLatency(Request request, long currentTime) {
      long latency = currentTime - request.createTime;
      if (latency >= 0L) {
         this.requestLatency.addDataPoint(latency);
         if (request.getHdr() != null) {
            ServerMetrics.getMetrics().UPDATE_LATENCY.add(latency);
         } else {
            ServerMetrics.getMetrics().READ_LATENCY.add(latency);
         }

      }
   }

   public void resetLatency() {
      this.requestLatency.reset();
   }

   public void resetMaxLatency() {
      this.requestLatency.resetMax();
   }

   public void incrementPacketsReceived() {
      this.packetsReceived.incrementAndGet();
   }

   public void incrementPacketsSent() {
      this.packetsSent.incrementAndGet();
   }

   public void resetRequestCounters() {
      this.packetsReceived.set(0L);
      this.packetsSent.set(0L);
   }

   public long getFsyncThresholdExceedCount() {
      return this.fsyncThresholdExceedCount.get();
   }

   public void incrementFsyncThresholdExceedCount() {
      this.fsyncThresholdExceedCount.incrementAndGet();
   }

   public void resetFsyncThresholdExceedCount() {
      this.fsyncThresholdExceedCount.set(0L);
   }

   public long getNonMTLSLocalConnCount() {
      return this.nonMTLSLocalConnCntr.get();
   }

   public void incrementNonMTLSLocalConnCount() {
      this.nonMTLSLocalConnCntr.incrementAndGet();
   }

   public void resetNonMTLSLocalConnCount() {
      this.nonMTLSLocalConnCntr.set(0L);
   }

   public long getNonMTLSRemoteConnCount() {
      return this.nonMTLSRemoteConnCntr.get();
   }

   public void incrementNonMTLSRemoteConnCount() {
      this.nonMTLSRemoteConnCntr.incrementAndGet();
   }

   public void resetNonMTLSRemoteConnCount() {
      this.nonMTLSRemoteConnCntr.set(0L);
   }

   public long getAuthFailedCount() {
      return this.authFailedCntr.get();
   }

   public void incrementAuthFailedCount() {
      this.authFailedCntr.incrementAndGet();
   }

   public void resetAuthFailedCount() {
      this.authFailedCntr.set(0L);
   }

   public void reset() {
      this.resetLatency();
      this.resetRequestCounters();
      this.clientResponseStats.reset();
      ServerMetrics.getMetrics().resetAll();
   }

   public void updateClientResponseSize(int size) {
      this.clientResponseStats.setLastBufferSize(size);
   }

   public BufferStats getClientResponseStats() {
      return this.clientResponseStats;
   }

   public interface Provider {
      long getOutstandingRequests();

      long getLastProcessedZxid();

      String getState();

      int getNumAliveConnections();

      long getDataDirSize();

      long getLogDirSize();
   }
}

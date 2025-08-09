package org.apache.zookeeper.server;

public interface ZooKeeperServerMXBean {
   String getClientPort();

   String getVersion();

   String getStartTime();

   long getMinRequestLatency();

   double getAvgRequestLatency();

   long getMaxRequestLatency();

   long getPacketsReceived();

   long getPacketsSent();

   long getFsyncThresholdExceedCount();

   long getAuthFailedCount();

   long getNonMTLSLocalConnCount();

   long getNonMTLSRemoteConnCount();

   long getOutstandingRequests();

   int getTickTime();

   void setTickTime(int var1);

   int getMaxClientCnxnsPerHost();

   void setMaxClientCnxnsPerHost(int var1);

   int getMinSessionTimeout();

   void setMinSessionTimeout(int var1);

   int getMaxSessionTimeout();

   void setMaxSessionTimeout(int var1);

   boolean getResponseCachingEnabled();

   void setResponseCachingEnabled(boolean var1);

   int getConnectionMaxTokens();

   void setConnectionMaxTokens(int var1);

   int getConnectionTokenFillTime();

   void setConnectionTokenFillTime(int var1);

   int getConnectionTokenFillCount();

   void setConnectionTokenFillCount(int var1);

   int getConnectionFreezeTime();

   void setConnectionFreezeTime(int var1);

   double getConnectionDropIncrease();

   void setConnectionDropIncrease(double var1);

   double getConnectionDropDecrease();

   void setConnectionDropDecrease(double var1);

   double getConnectionDecreaseRatio();

   void setConnectionDecreaseRatio(double var1);

   int getCommitProcMaxReadBatchSize();

   void setCommitProcMaxReadBatchSize(int var1);

   int getCommitProcMaxCommitBatchSize();

   void setCommitProcMaxCommitBatchSize(int var1);

   int getRequestThrottleLimit();

   void setRequestThrottleLimit(int var1);

   int getRequestThrottleStallTime();

   void setRequestThrottleStallTime(int var1);

   boolean getRequestThrottleDropStale();

   void setRequestThrottleDropStale(boolean var1);

   int getThrottledOpWaitTime();

   void setThrottledOpWaitTime(int var1);

   boolean getRequestStaleLatencyCheck();

   void setRequestStaleLatencyCheck(boolean var1);

   boolean getRequestStaleConnectionCheck();

   void setRequestStaleConnectionCheck(boolean var1);

   int getLargeRequestMaxBytes();

   void setLargeRequestMaxBytes(int var1);

   int getLargeRequestThreshold();

   void setLargeRequestThreshold(int var1);

   void resetStatistics();

   void resetLatency();

   void resetMaxLatency();

   void resetFsyncThresholdExceedCount();

   void resetNonMTLSConnCount();

   void resetAuthFailedCount();

   long getNumAliveConnections();

   long getDataDirSize();

   long getLogDirSize();

   String getSecureClientPort();

   String getSecureClientAddress();

   long getTxnLogElapsedSyncTime();

   int getJuteMaxBufferSize();

   int getLastClientResponseSize();

   int getMinClientResponseSize();

   int getMaxClientResponseSize();

   long getFlushDelay();

   void setFlushDelay(long var1);

   long getMaxWriteQueuePollTime();

   void setMaxWriteQueuePollTime(long var1);

   int getMaxBatchSize();

   void setMaxBatchSize(int var1);

   int getMaxCnxns();
}

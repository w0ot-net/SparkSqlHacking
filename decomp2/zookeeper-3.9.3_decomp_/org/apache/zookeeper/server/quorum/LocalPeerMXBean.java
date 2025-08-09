package org.apache.zookeeper.server.quorum;

public interface LocalPeerMXBean extends ServerMXBean {
   int getTickTime();

   int getMaxClientCnxnsPerHost();

   int getMinSessionTimeout();

   int getMaxSessionTimeout();

   int getInitLimit();

   int getSyncLimit();

   void setInitLimit(int var1);

   void setSyncLimit(int var1);

   int getTick();

   String getState();

   String getQuorumAddress();

   int getElectionType();

   String getElectionAddress();

   String getClientAddress();

   String getLearnerType();

   long getConfigVersion();

   String getQuorumSystemInfo();

   boolean isPartOfEnsemble();

   boolean isLeader();

   int getMaxCnxns();
}

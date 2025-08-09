package org.apache.zookeeper.server.quorum;

import org.apache.zookeeper.server.ZooKeeperServerMXBean;

public interface FollowerMXBean extends ZooKeeperServerMXBean {
   String getQuorumAddress();

   String getLastQueuedZxid();

   int getPendingRevalidationCount();

   long getElectionTimeTaken();

   int getObserverMasterPacketSizeLimit();

   void setObserverMasterPacketSizeLimit(int var1);

   int getMaxConcurrentSnapSyncs();

   void setMaxConcurrentSnapSyncs(int var1);

   int getMaxConcurrentDiffSyncs();

   void setMaxConcurrentDiffSyncs(int var1);
}

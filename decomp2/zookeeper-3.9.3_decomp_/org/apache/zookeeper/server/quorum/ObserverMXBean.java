package org.apache.zookeeper.server.quorum;

import org.apache.zookeeper.server.ZooKeeperServerMXBean;

public interface ObserverMXBean extends ZooKeeperServerMXBean {
   int getPendingRevalidationCount();

   String getQuorumAddress();

   String getLearnerMaster();

   void setLearnerMaster(String var1);
}

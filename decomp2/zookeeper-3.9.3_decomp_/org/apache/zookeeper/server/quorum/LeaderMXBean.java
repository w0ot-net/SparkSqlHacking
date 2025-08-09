package org.apache.zookeeper.server.quorum;

import org.apache.zookeeper.server.ZooKeeperServerMXBean;

public interface LeaderMXBean extends ZooKeeperServerMXBean {
   String getCurrentZxid();

   String followerInfo();

   String nonVotingFollowerInfo();

   long getElectionTimeTaken();

   int getLastProposalSize();

   int getMinProposalSize();

   int getMaxProposalSize();

   void resetProposalStatistics();

   int getMaxConcurrentSnapSyncs();

   void setMaxConcurrentSnapSyncs(int var1);

   int getMaxConcurrentDiffSyncs();

   void setMaxConcurrentDiffSyncs(int var1);
}

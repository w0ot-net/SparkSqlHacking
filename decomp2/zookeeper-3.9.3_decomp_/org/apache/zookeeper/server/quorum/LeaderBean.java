package org.apache.zookeeper.server.quorum;

import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooKeeperServerBean;

public class LeaderBean extends ZooKeeperServerBean implements LeaderMXBean {
   private final Leader leader;

   public LeaderBean(Leader leader, ZooKeeperServer zks) {
      super(zks);
      this.leader = leader;
   }

   public String getName() {
      return "Leader";
   }

   public String getCurrentZxid() {
      return "0x" + Long.toHexString(this.zks.getZxid());
   }

   public String followerInfo() {
      StringBuilder sb = new StringBuilder();

      for(LearnerHandler handler : this.leader.getLearners()) {
         if (handler.getLearnerType() == QuorumPeer.LearnerType.PARTICIPANT) {
            sb.append(handler.toString()).append("\n");
         }
      }

      return sb.toString();
   }

   public String nonVotingFollowerInfo() {
      StringBuilder sb = new StringBuilder();

      for(LearnerHandler handler : this.leader.getNonVotingFollowers()) {
         sb.append(handler.toString()).append("\n");
      }

      return sb.toString();
   }

   public long getElectionTimeTaken() {
      return this.leader.self.getElectionTimeTaken();
   }

   public int getLastProposalSize() {
      return this.leader.getProposalStats().getLastBufferSize();
   }

   public int getMinProposalSize() {
      return this.leader.getProposalStats().getMinBufferSize();
   }

   public int getMaxProposalSize() {
      return this.leader.getProposalStats().getMaxBufferSize();
   }

   public void resetProposalStatistics() {
      this.leader.getProposalStats().reset();
   }

   public int getMaxConcurrentSnapSyncs() {
      return this.leader.getMaxConcurrentSnapSyncs();
   }

   public void setMaxConcurrentSnapSyncs(int maxConcurrentSnapshots) {
      this.leader.setMaxConcurrentSnapSyncs(maxConcurrentSnapshots);
   }

   public int getMaxConcurrentDiffSyncs() {
      return this.leader.getMaxConcurrentDiffSyncs();
   }

   public void setMaxConcurrentDiffSyncs(int maxConcurrentDiffSyncs) {
      this.leader.setMaxConcurrentDiffSyncs(maxConcurrentDiffSyncs);
   }
}

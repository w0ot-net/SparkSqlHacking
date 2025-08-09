package org.apache.zookeeper.server.quorum;

import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooKeeperServerBean;

public class FollowerBean extends ZooKeeperServerBean implements FollowerMXBean {
   private final Follower follower;

   public FollowerBean(Follower follower, ZooKeeperServer zks) {
      super(zks);
      this.follower = follower;
   }

   public String getName() {
      return "Follower";
   }

   public String getQuorumAddress() {
      return this.follower.sock.toString();
   }

   public String getLastQueuedZxid() {
      return "0x" + Long.toHexString(this.follower.getLastQueued());
   }

   public int getPendingRevalidationCount() {
      return this.follower.getPendingRevalidationsCount();
   }

   public long getElectionTimeTaken() {
      return this.follower.self.getElectionTimeTaken();
   }

   public int getObserverMasterPacketSizeLimit() {
      return this.follower.om == null ? -1 : this.follower.om.getPktsSizeLimit();
   }

   public void setObserverMasterPacketSizeLimit(int sizeLimit) {
      ObserverMaster.setPktsSizeLimit(sizeLimit);
   }

   public int getMaxConcurrentSnapSyncs() {
      ObserverMaster om = this.follower.om;
      return om == null ? -1 : om.getMaxConcurrentSnapSyncs();
   }

   public void setMaxConcurrentSnapSyncs(int maxConcurrentSnapshots) {
      ObserverMaster om = this.follower.om;
      if (om != null) {
         om.setMaxConcurrentSnapSyncs(maxConcurrentSnapshots);
      }

   }

   public int getMaxConcurrentDiffSyncs() {
      ObserverMaster om = this.follower.om;
      return om == null ? -1 : om.getMaxConcurrentDiffSyncs();
   }

   public void setMaxConcurrentDiffSyncs(int maxConcurrentDiffSyncs) {
      ObserverMaster om = this.follower.om;
      if (om != null) {
         om.setMaxConcurrentDiffSyncs(maxConcurrentDiffSyncs);
      }

   }
}

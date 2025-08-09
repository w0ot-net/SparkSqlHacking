package org.apache.zookeeper.server.quorum;

import org.apache.zookeeper.jmx.ZKMBeanInfo;
import org.apache.zookeeper.server.ZooKeeperServer;

public class QuorumBean implements QuorumMXBean, ZKMBeanInfo {
   private final QuorumPeer peer;
   private final String name;

   public QuorumBean(QuorumPeer peer) {
      this.peer = peer;
      this.name = "ReplicatedServer_id" + peer.getMyId();
   }

   public String getName() {
      return this.name;
   }

   public boolean isHidden() {
      return false;
   }

   public int getQuorumSize() {
      return this.peer.getQuorumSize();
   }

   public int getSyncLimit() {
      return this.peer.getSyncLimit();
   }

   public int getInitLimit() {
      return this.peer.getInitLimit();
   }

   public void setInitLimit(int initLimit) {
      this.peer.setInitLimit(initLimit);
   }

   public void setSyncLimit(int syncLimit) {
      this.peer.setSyncLimit(syncLimit);
   }

   public boolean isSslQuorum() {
      return this.peer.isSslQuorum();
   }

   public boolean isPortUnification() {
      return this.peer.shouldUsePortUnification();
   }

   public long getObserverElectionDelayMS() {
      return Observer.getObserverElectionDelayMs();
   }

   public void setObserverElectionDelayMS(long delayMS) {
      Observer.setObserverElectionDelayMs(delayMS);
   }

   public boolean getDigestEnabled() {
      return ZooKeeperServer.isDigestEnabled();
   }

   public void disableDigest() {
      ZooKeeperServer.setDigestEnabled(false);
   }
}

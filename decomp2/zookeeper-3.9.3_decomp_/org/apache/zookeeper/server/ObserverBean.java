package org.apache.zookeeper.server;

import java.net.InetSocketAddress;
import org.apache.zookeeper.server.quorum.Observer;
import org.apache.zookeeper.server.quorum.ObserverMXBean;
import org.apache.zookeeper.server.quorum.QuorumPeer;

public class ObserverBean extends ZooKeeperServerBean implements ObserverMXBean {
   private Observer observer;

   public ObserverBean(Observer observer, ZooKeeperServer zks) {
      super(zks);
      this.observer = observer;
   }

   public String getName() {
      return "Observer";
   }

   public int getPendingRevalidationCount() {
      return this.observer.getPendingRevalidationsCount();
   }

   public String getQuorumAddress() {
      return this.observer.getSocket().toString();
   }

   public String getLearnerMaster() {
      QuorumPeer.QuorumServer learnerMaster = this.observer.getCurrentLearnerMaster();
      if (learnerMaster != null && !learnerMaster.addr.isEmpty()) {
         InetSocketAddress address = learnerMaster.addr.getReachableOrOne();
         return address.getAddress().getHostAddress() + ":" + address.getPort();
      } else {
         return "Unknown";
      }
   }

   public void setLearnerMaster(String learnerMaster) {
      if (!this.observer.setLearnerMaster(learnerMaster)) {
         throw new IllegalArgumentException("Not a valid learner master");
      }
   }
}

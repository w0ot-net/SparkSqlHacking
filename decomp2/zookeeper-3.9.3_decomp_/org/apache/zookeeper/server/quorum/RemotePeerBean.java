package org.apache.zookeeper.server.quorum;

import java.util.stream.Collectors;
import org.apache.zookeeper.common.NetUtils;
import org.apache.zookeeper.jmx.ZKMBeanInfo;

public class RemotePeerBean implements RemotePeerMXBean, ZKMBeanInfo {
   private QuorumPeer.QuorumServer peer;
   private final QuorumPeer localPeer;

   public RemotePeerBean(QuorumPeer localPeer, QuorumPeer.QuorumServer peer) {
      this.peer = peer;
      this.localPeer = localPeer;
   }

   public void setQuorumServer(QuorumPeer.QuorumServer peer) {
      this.peer = peer;
   }

   public String getName() {
      return "replica." + this.peer.id;
   }

   public boolean isHidden() {
      return false;
   }

   public String getQuorumAddress() {
      return (String)this.peer.addr.getAllAddresses().stream().map(NetUtils::formatInetAddr).collect(Collectors.joining("|"));
   }

   public String getElectionAddress() {
      return (String)this.peer.electionAddr.getAllAddresses().stream().map(NetUtils::formatInetAddr).collect(Collectors.joining("|"));
   }

   public String getClientAddress() {
      return null == this.peer.clientAddr ? "" : NetUtils.formatInetAddr(this.peer.clientAddr);
   }

   public String getLearnerType() {
      return this.peer.type.toString();
   }

   public boolean isLeader() {
      return this.localPeer.isLeader(this.peer.getId());
   }
}

package org.apache.zookeeper.server.quorum;

import java.util.stream.Collectors;
import org.apache.zookeeper.common.NetUtils;
import org.apache.zookeeper.server.ServerCnxnHelper;

public class LocalPeerBean extends ServerBean implements LocalPeerMXBean {
   private final QuorumPeer peer;

   public LocalPeerBean(QuorumPeer peer) {
      this.peer = peer;
   }

   public String getName() {
      return "replica." + this.peer.getMyId();
   }

   public boolean isHidden() {
      return false;
   }

   public int getTickTime() {
      return this.peer.getTickTime();
   }

   public int getMaxClientCnxnsPerHost() {
      return this.peer.getMaxClientCnxnsPerHost();
   }

   public int getMinSessionTimeout() {
      return this.peer.getMinSessionTimeout();
   }

   public int getMaxSessionTimeout() {
      return this.peer.getMaxSessionTimeout();
   }

   public int getInitLimit() {
      return this.peer.getInitLimit();
   }

   public int getSyncLimit() {
      return this.peer.getSyncLimit();
   }

   public void setInitLimit(int initLimit) {
      this.peer.setInitLimit(initLimit);
   }

   public void setSyncLimit(int syncLimit) {
      this.peer.setSyncLimit(syncLimit);
   }

   public int getTick() {
      return this.peer.getTick();
   }

   public String getState() {
      return this.peer.getServerState();
   }

   public String getQuorumAddress() {
      return (String)this.peer.getQuorumAddress().getAllAddresses().stream().map(NetUtils::formatInetAddr).collect(Collectors.joining("|"));
   }

   public int getElectionType() {
      return this.peer.getElectionType();
   }

   public String getElectionAddress() {
      return (String)this.peer.getElectionAddress().getAllAddresses().stream().map(NetUtils::formatInetAddr).collect(Collectors.joining("|"));
   }

   public String getClientAddress() {
      return null != this.peer.cnxnFactory ? NetUtils.formatInetAddr(this.peer.cnxnFactory.getLocalAddress()) : "";
   }

   public String getLearnerType() {
      return this.peer.getLearnerType().toString();
   }

   public long getConfigVersion() {
      return this.peer.getQuorumVerifier().getVersion();
   }

   public String getQuorumSystemInfo() {
      return this.peer.getQuorumVerifier().toString();
   }

   public boolean isPartOfEnsemble() {
      return this.peer.getView().containsKey(this.peer.getMyId());
   }

   public boolean isLeader() {
      return this.peer.isLeader(this.peer.getMyId());
   }

   public int getMaxCnxns() {
      return ServerCnxnHelper.getMaxCnxns(this.peer.secureCnxnFactory, this.peer.cnxnFactory);
   }
}

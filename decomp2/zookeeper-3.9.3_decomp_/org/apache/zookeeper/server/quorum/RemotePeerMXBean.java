package org.apache.zookeeper.server.quorum;

public interface RemotePeerMXBean {
   String getName();

   String getQuorumAddress();

   String getElectionAddress();

   String getClientAddress();

   String getLearnerType();

   boolean isLeader();
}

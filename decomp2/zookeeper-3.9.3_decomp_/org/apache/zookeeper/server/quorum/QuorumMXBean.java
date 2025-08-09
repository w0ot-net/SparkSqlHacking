package org.apache.zookeeper.server.quorum;

public interface QuorumMXBean {
   String getName();

   int getQuorumSize();

   int getInitLimit();

   int getSyncLimit();

   void setInitLimit(int var1);

   void setSyncLimit(int var1);

   boolean isSslQuorum();

   boolean isPortUnification();

   long getObserverElectionDelayMS();

   void setObserverElectionDelayMS(long var1);

   boolean getDigestEnabled();

   void disableDigest();
}

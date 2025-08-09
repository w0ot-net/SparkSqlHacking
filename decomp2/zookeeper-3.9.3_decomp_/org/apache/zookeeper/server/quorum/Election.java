package org.apache.zookeeper.server.quorum;

public interface Election {
   Vote lookForLeader() throws InterruptedException;

   void shutdown();
}

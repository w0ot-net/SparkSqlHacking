package org.apache.zookeeper.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ZooKeeperServerListenerImpl implements ZooKeeperServerListener {
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperServerListenerImpl.class);
   private final ZooKeeperServer zkServer;

   ZooKeeperServerListenerImpl(ZooKeeperServer zkServer) {
      this.zkServer = zkServer;
   }

   public void notifyStopping(String threadName, int exitCode) {
      LOG.info("Thread {} exits, error code {}", threadName, exitCode);
      this.zkServer.setState(ZooKeeperServer.State.ERROR);
   }
}

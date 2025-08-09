package org.apache.zookeeper;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ZooKeeperTestable implements Testable {
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperTestable.class);
   private final ClientCnxn clientCnxn;

   ZooKeeperTestable(ClientCnxn clientCnxn) {
      this.clientCnxn = clientCnxn;
   }

   public void injectSessionExpiration() {
      LOG.info("injectSessionExpiration() called");
      this.clientCnxn.eventThread.queueEvent(new WatchedEvent(Watcher.Event.EventType.None, Watcher.Event.KeeperState.Expired, (String)null));
      this.clientCnxn.eventThread.queueEventOfDeath();
      this.clientCnxn.state = ZooKeeper.States.CLOSED;
      this.clientCnxn.sendThread.getClientCnxnSocket().onClosing();
   }

   public void queueEvent(WatchedEvent event) {
      LOG.info("queueEvent() called: {}", event);
      this.clientCnxn.eventThread.queueEvent(event);
   }

   public void closeSocket() throws IOException {
      LOG.info("closeSocket() called");
      this.clientCnxn.sendThread.testableCloseSocket();
   }
}

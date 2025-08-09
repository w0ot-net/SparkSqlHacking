package org.apache.zookeeper.server;

import java.util.concurrent.CountDownLatch;

public final class ZooKeeperServerShutdownHandler {
   private final CountDownLatch shutdownLatch;

   ZooKeeperServerShutdownHandler(CountDownLatch shutdownLatch) {
      this.shutdownLatch = shutdownLatch;
   }

   public void handle(ZooKeeperServer.State state) {
      if (state == ZooKeeperServer.State.ERROR || state == ZooKeeperServer.State.SHUTDOWN) {
         this.shutdownLatch.countDown();
      }

   }
}

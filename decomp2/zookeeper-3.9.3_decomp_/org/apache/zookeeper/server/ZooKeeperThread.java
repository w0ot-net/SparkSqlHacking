package org.apache.zookeeper.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZooKeeperThread extends Thread {
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperThread.class);
   private Thread.UncaughtExceptionHandler uncaughtExceptionalHandler = (t, e) -> this.handleException(t.getName(), e);

   public ZooKeeperThread(String threadName) {
      super(threadName);
      this.setUncaughtExceptionHandler(this.uncaughtExceptionalHandler);
   }

   protected void handleException(String thName, Throwable e) {
      LOG.warn("Exception occurred from thread {}", thName, e);
   }
}

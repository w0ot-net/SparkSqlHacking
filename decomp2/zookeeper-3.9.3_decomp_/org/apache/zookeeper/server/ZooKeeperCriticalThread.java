package org.apache.zookeeper.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZooKeeperCriticalThread extends ZooKeeperThread {
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperCriticalThread.class);
   private final ZooKeeperServerListener listener;

   public ZooKeeperCriticalThread(String threadName, ZooKeeperServerListener listener) {
      super(threadName);
      this.listener = listener;
   }

   protected void handleException(String threadName, Throwable e) {
      LOG.error("Severe unrecoverable error, from thread : {}", threadName, e);
      this.listener.notifyStopping(threadName, ExitCode.UNEXPECTED_ERROR.getValue());
      ServerMetrics.getMetrics().UNRECOVERABLE_ERROR_COUNT.add(1L);
   }
}

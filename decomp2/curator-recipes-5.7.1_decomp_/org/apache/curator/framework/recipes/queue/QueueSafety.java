package org.apache.curator.framework.recipes.queue;

import java.util.concurrent.BlockingQueue;
import org.apache.curator.utils.PathUtils;

public class QueueSafety {
   private final String lockPath;
   private final QueueConsumer consumer;
   private final BlockingQueue queue;

   public QueueSafety(String lockPath, QueueConsumer consumer) {
      this.lockPath = PathUtils.validatePath(lockPath);
      this.consumer = consumer;
      this.queue = null;
   }

   QueueSafety(String lockPath, BlockingQueue queue) {
      this.lockPath = PathUtils.validatePath(lockPath);
      this.consumer = null;
      this.queue = queue;
   }

   String getLockPath() {
      return this.lockPath;
   }

   QueueConsumer getConsumer() {
      return this.consumer;
   }

   BlockingQueue getQueue() {
      return this.queue;
   }
}

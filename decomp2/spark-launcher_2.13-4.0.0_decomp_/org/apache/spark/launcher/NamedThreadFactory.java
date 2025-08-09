package org.apache.spark.launcher;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

class NamedThreadFactory implements ThreadFactory {
   private final String nameFormat;
   private final AtomicLong threadIds;

   NamedThreadFactory(String nameFormat) {
      this.nameFormat = nameFormat;
      this.threadIds = new AtomicLong();
   }

   public Thread newThread(Runnable r) {
      Thread t = new Thread(r, String.format(this.nameFormat, this.threadIds.incrementAndGet()));
      t.setDaemon(true);
      return t;
   }
}

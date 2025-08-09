package org.apache.curator.framework.recipes.queue;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

public class DistributedPriorityQueue implements Closeable, QueueBase {
   private final DistributedQueue queue;

   DistributedPriorityQueue(CuratorFramework client, QueueConsumer consumer, QueueSerializer serializer, String queuePath, ThreadFactory threadFactory, Executor executor, int minItemsBeforeRefresh, String lockPath, int maxItems, boolean putInBackground, int finalFlushMs) {
      Preconditions.checkArgument(minItemsBeforeRefresh >= 0, "minItemsBeforeRefresh cannot be negative");
      this.queue = new DistributedQueue(client, consumer, serializer, queuePath, threadFactory, executor, minItemsBeforeRefresh, true, lockPath, maxItems, putInBackground, finalFlushMs);
   }

   public void start() throws Exception {
      this.queue.start();
   }

   public void close() throws IOException {
      this.queue.close();
   }

   public void put(Object item, int priority) throws Exception {
      this.put(item, priority, 0, (TimeUnit)null);
   }

   public boolean put(Object item, int priority, int maxWait, TimeUnit unit) throws Exception {
      this.queue.checkState();
      String priorityHex = priorityToString(priority);
      return this.queue.internalPut(item, (MultiItem)null, this.queue.makeItemPath() + priorityHex, maxWait, unit);
   }

   public void putMulti(MultiItem items, int priority) throws Exception {
      this.putMulti(items, priority, 0, (TimeUnit)null);
   }

   public boolean putMulti(MultiItem items, int priority, int maxWait, TimeUnit unit) throws Exception {
      this.queue.checkState();
      String priorityHex = priorityToString(priority);
      return this.queue.internalPut((Object)null, items, this.queue.makeItemPath() + priorityHex, maxWait, unit);
   }

   public void setErrorMode(ErrorMode newErrorMode) {
      this.queue.setErrorMode(newErrorMode);
   }

   public boolean flushPuts(long waitTime, TimeUnit timeUnit) throws InterruptedException {
      return this.queue.flushPuts(waitTime, timeUnit);
   }

   public Listenable getPutListenerContainer() {
      return this.queue.getPutListenerContainer();
   }

   public int getLastMessageCount() {
      return this.queue.getLastMessageCount();
   }

   @VisibleForTesting
   ChildrenCache getCache() {
      return this.queue.getCache();
   }

   @VisibleForTesting
   static String priorityToString(int priority) {
      long l = (long)priority & 4294967295L;
      return String.format("%s%08X", priority >= 0 ? "1" : "0", l);
   }
}

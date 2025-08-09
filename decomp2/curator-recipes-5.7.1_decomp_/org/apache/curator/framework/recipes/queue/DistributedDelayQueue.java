package org.apache.curator.framework.recipes.queue;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

public class DistributedDelayQueue implements Closeable, QueueBase {
   private final DistributedQueue queue;
   private static final char SEPARATOR = '|';

   DistributedDelayQueue(CuratorFramework client, QueueConsumer consumer, QueueSerializer serializer, String queuePath, ThreadFactory threadFactory, Executor executor, int minItemsBeforeRefresh, String lockPath, int maxItems, boolean putInBackground, int finalFlushMs) {
      Preconditions.checkArgument(minItemsBeforeRefresh >= 0, "minItemsBeforeRefresh cannot be negative");
      this.queue = new DistributedQueue(client, consumer, serializer, queuePath, threadFactory, executor, minItemsBeforeRefresh, true, lockPath, maxItems, putInBackground, finalFlushMs) {
         protected long getDelay(String itemNode) {
            return this.getDelay(itemNode, System.currentTimeMillis());
         }

         private long getDelay(String itemNode, long sortTime) {
            long epoch = DistributedDelayQueue.getEpoch(itemNode);
            return epoch - sortTime;
         }

         protected void sortChildren(List children) {
            final long sortTime = System.currentTimeMillis();
            Collections.sort(children, new Comparator() {
               public int compare(String o1, String o2) {
                  long diff = getDelay(o1, sortTime) - getDelay(o2, sortTime);
                  return diff < 0L ? -1 : (diff > 0L ? 1 : 0);
               }
            });
         }
      };
   }

   public void start() throws Exception {
      this.queue.start();
   }

   public void close() throws IOException {
      this.queue.close();
   }

   public void put(Object item, long delayUntilEpoch) throws Exception {
      this.put(item, delayUntilEpoch, 0, (TimeUnit)null);
   }

   public boolean put(Object item, long delayUntilEpoch, int maxWait, TimeUnit unit) throws Exception {
      Preconditions.checkArgument(delayUntilEpoch > 0L, "delayUntilEpoch cannot be negative");
      this.queue.checkState();
      return this.queue.internalPut(item, (MultiItem)null, this.queue.makeItemPath() + epochToString(delayUntilEpoch), maxWait, unit);
   }

   public void putMulti(MultiItem items, long delayUntilEpoch) throws Exception {
      this.putMulti(items, delayUntilEpoch, 0, (TimeUnit)null);
   }

   public boolean putMulti(MultiItem items, long delayUntilEpoch, int maxWait, TimeUnit unit) throws Exception {
      Preconditions.checkArgument(delayUntilEpoch > 0L, "delayUntilEpoch cannot be negative");
      this.queue.checkState();
      return this.queue.internalPut((Object)null, items, this.queue.makeItemPath() + epochToString(delayUntilEpoch), maxWait, unit);
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
   static String epochToString(long epoch) {
      return '|' + String.format("%08X", epoch) + '|';
   }

   private static long getEpoch(String itemNode) {
      int index2 = itemNode.lastIndexOf(124);
      int index1 = index2 > 0 ? itemNode.lastIndexOf(124, index2 - 1) : -1;
      if (index1 > 0 && index2 > index1 + 1) {
         try {
            String epochStr = itemNode.substring(index1 + 1, index2);
            return Long.parseLong(epochStr, 16);
         } catch (NumberFormatException var4) {
         }
      }

      return 0L;
   }
}

package org.apache.curator.framework.recipes.queue;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedIdQueue implements QueueBase {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final DistributedQueue queue;
   private static final char SEPARATOR = '|';

   DistributedIdQueue(CuratorFramework client, QueueConsumer consumer, QueueSerializer serializer, String queuePath, ThreadFactory threadFactory, Executor executor, int minItemsBeforeRefresh, boolean refreshOnWatch, String lockPath, int maxItems, boolean putInBackground, int finalFlushMs) {
      this.queue = new DistributedQueue(client, consumer, serializer, queuePath, threadFactory, executor, minItemsBeforeRefresh, refreshOnWatch, lockPath, maxItems, putInBackground, finalFlushMs) {
         protected void sortChildren(List children) {
            DistributedIdQueue.this.internalSortChildren(children);
         }

         protected String makeRequeueItemPath(String itemPath) {
            return DistributedIdQueue.this.makeIdPath(DistributedIdQueue.this.parseId(itemPath).id);
         }
      };
      if (this.queue.makeItemPath().contains(Character.toString('|'))) {
         throw new IllegalStateException("DistributedQueue can't use |");
      }
   }

   public void start() throws Exception {
      this.queue.start();
   }

   public void close() throws IOException {
      this.queue.close();
   }

   public Listenable getPutListenerContainer() {
      return this.queue.getPutListenerContainer();
   }

   public void setErrorMode(ErrorMode newErrorMode) {
      this.queue.setErrorMode(newErrorMode);
   }

   public boolean flushPuts(long waitTime, TimeUnit timeUnit) throws InterruptedException {
      return this.queue.flushPuts(waitTime, timeUnit);
   }

   public int getLastMessageCount() {
      return this.queue.getLastMessageCount();
   }

   public void put(Object item, String itemId) throws Exception {
      this.put(item, itemId, 0, (TimeUnit)null);
   }

   public boolean put(Object item, String itemId, int maxWait, TimeUnit unit) throws Exception {
      Preconditions.checkArgument(this.isValidId(itemId), "Invalid id: " + itemId);
      this.queue.checkState();
      return this.queue.internalPut(item, (MultiItem)null, this.makeIdPath(itemId), maxWait, unit);
   }

   public int remove(String id) throws Exception {
      id = (String)Preconditions.checkNotNull(id, "id cannot be null");
      this.queue.checkState();
      int count = 0;

      for(String name : this.queue.getChildren()) {
         if (this.parseId(name).id.equals(id) && this.queue.tryRemove(name)) {
            ++count;
         }
      }

      return count;
   }

   @VisibleForTesting
   boolean debugIsQueued(String id) throws Exception {
      for(String name : this.queue.getChildren()) {
         if (this.parseId(name).id.equals(id)) {
            return true;
         }
      }

      return false;
   }

   private String makeIdPath(String itemId) {
      return this.queue.makeItemPath() + '|' + fixId(itemId) + '|';
   }

   private void internalSortChildren(List children) {
      Collections.sort(children, new Comparator() {
         public int compare(String o1, String o2) {
            return DistributedIdQueue.this.parseId(o1).cleaned.compareTo(DistributedIdQueue.this.parseId(o2).cleaned);
         }
      });
   }

   private boolean isValidId(String id) {
      return id != null && id.length() > 0;
   }

   private static String fixId(String id) {
      String fixed = id.replace('/', '_');
      return fixed.replace('|', '_');
   }

   private Parts parseId(String name) {
      int firstIndex = name.indexOf(124);
      int secondIndex = name.indexOf(124, firstIndex + 1);
      if (firstIndex >= 0 && secondIndex >= 0) {
         return new Parts(name.substring(firstIndex + 1, secondIndex), name.substring(0, firstIndex) + name.substring(secondIndex + 1));
      } else {
         this.log.error("Bad node in queue: " + name);
         return new Parts(name, name);
      }
   }

   private static class Parts {
      final String id;
      final String cleaned;

      private Parts(String id, String cleaned) {
         this.id = id;
         this.cleaned = cleaned;
      }
   }
}

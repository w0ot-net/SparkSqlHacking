package org.apache.curator.framework.recipes.queue;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSet;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.collect.Sets;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueSharder implements Closeable {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final CuratorFramework client;
   private final QueueAllocator queueAllocator;
   private final String queuePath;
   private final QueueSharderPolicies policies;
   private final ConcurrentMap queues = Maps.newConcurrentMap();
   private final Set preferredQueues = Sets.newSetFromMap(Maps.newConcurrentMap());
   private final AtomicReference state;
   private final LeaderLatch leaderLatch;
   private final ExecutorService service;
   private static final String QUEUE_PREFIX = "queue-";

   public QueueSharder(CuratorFramework client, QueueAllocator queueAllocator, String queuePath, String leaderPath, QueueSharderPolicies policies) {
      this.state = new AtomicReference(QueueSharder.State.LATENT);
      this.client = client;
      this.queueAllocator = queueAllocator;
      this.queuePath = queuePath;
      this.policies = policies;
      this.leaderLatch = new LeaderLatch(client, leaderPath);
      this.service = Executors.newSingleThreadExecutor(policies.getThreadFactory());
   }

   public void start() throws Exception {
      Preconditions.checkState(this.state.compareAndSet(QueueSharder.State.LATENT, QueueSharder.State.STARTED), "Cannot be started more than once");
      this.client.createContainers(this.queuePath);
      this.getInitialQueues();
      this.leaderLatch.start();
      this.service.submit(new Callable() {
         public Void call() throws Exception {
            while(QueueSharder.this.state.get() == QueueSharder.State.STARTED) {
               try {
                  Thread.sleep((long)QueueSharder.this.policies.getThresholdCheckMs());
                  QueueSharder.this.checkThreshold();
               } catch (InterruptedException var2) {
               }
            }

            return null;
         }
      });
   }

   public void close() {
      if (this.state.compareAndSet(QueueSharder.State.STARTED, QueueSharder.State.CLOSED)) {
         this.service.shutdownNow();
         CloseableUtils.closeQuietly(this.leaderLatch);

         for(QueueBase queue : this.queues.values()) {
            try {
               queue.close();
            } catch (IOException e) {
               this.log.error("Closing a queue", e);
            }
         }
      }

   }

   public QueueBase getQueue() {
      Preconditions.checkState(this.state.get() == QueueSharder.State.STARTED, "Not started");
      List<String> localPreferredQueues = Lists.newArrayList(this.preferredQueues);
      if (localPreferredQueues.size() > 0) {
         String key = (String)localPreferredQueues.get(ThreadLocalRandom.current().nextInt(localPreferredQueues.size()));
         return (QueueBase)this.queues.get(key);
      } else {
         List<String> keys = Lists.newArrayList(this.queues.keySet());
         String key = (String)keys.get(ThreadLocalRandom.current().nextInt(keys.size()));
         return (QueueBase)this.queues.get(key);
      }
   }

   public int getShardQty() {
      return this.queues.size();
   }

   public Collection getQueuePaths() {
      return ImmutableSet.copyOf(this.queues.keySet());
   }

   private void getInitialQueues() throws Exception {
      List<String> children = (List)this.client.getChildren().forPath(this.queuePath);

      for(String child : children) {
         String queuePath = ZKPaths.makePath(this.queuePath, child);
         this.addNewQueueIfNeeded(queuePath);
      }

      if (children.size() == 0) {
         this.addNewQueueIfNeeded((String)null);
      }

   }

   private void addNewQueueIfNeeded(String newQueuePath) throws Exception {
      if (newQueuePath == null) {
         newQueuePath = ZKPaths.makePath(this.queuePath, "queue-" + UUID.randomUUID().toString());
      }

      if (!this.queues.containsKey(newQueuePath)) {
         T queue = (T)this.queueAllocator.allocateQueue(this.client, newQueuePath);
         if (this.queues.putIfAbsent(newQueuePath, queue) == null) {
            queue.start();
            this.preferredQueues.add(newQueuePath);
         }
      }

   }

   private void checkThreshold() {
      try {
         boolean addAQueueIfLeader = false;
         int size = 0;

         for(String child : (List)this.client.getChildren().forPath(this.queuePath)) {
            String queuePath = ZKPaths.makePath(this.queuePath, child);
            this.addNewQueueIfNeeded(queuePath);
            Stat stat = (Stat)this.client.checkExists().forPath(queuePath);
            if (stat.getNumChildren() >= this.policies.getNewQueueThreshold()) {
               size = stat.getNumChildren();
               addAQueueIfLeader = true;
               this.preferredQueues.remove(queuePath);
            } else if (stat.getNumChildren() <= this.policies.getNewQueueThreshold() / 2) {
               this.preferredQueues.add(queuePath);
            }
         }

         if (addAQueueIfLeader && this.leaderLatch.hasLeadership()) {
            if (this.queues.size() < this.policies.getMaxQueues()) {
               this.log.info(String.format("Adding a queue due to exceeded threshold. Queue Size: %d - Threshold: %d", size, this.policies.getNewQueueThreshold()));
               this.addNewQueueIfNeeded((String)null);
            } else {
               this.log.warn(String.format("Max number of queues (%d) reached. Consider increasing the max.", this.policies.getMaxQueues()));
            }
         }
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         this.log.error("Checking queue counts against threshold", e);
      }

   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }
}

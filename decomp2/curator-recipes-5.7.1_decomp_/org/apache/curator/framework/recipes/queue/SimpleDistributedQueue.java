package org.apache.curator.framework.recipes.queue;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.EnsureContainers;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleDistributedQueue {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final CuratorFramework client;
   private final String path;
   private final EnsureContainers ensureContainers;
   private final String PREFIX = "qn-";

   public SimpleDistributedQueue(CuratorFramework client, String path) {
      this.client = client;
      this.path = PathUtils.validatePath(path);
      this.ensureContainers = new EnsureContainers(client, path);
   }

   public byte[] element() throws Exception {
      byte[] bytes = this.internalElement(false, (Watcher)null);
      if (bytes == null) {
         throw new NoSuchElementException();
      } else {
         return bytes;
      }
   }

   public byte[] remove() throws Exception {
      byte[] bytes = this.internalElement(true, (Watcher)null);
      if (bytes == null) {
         throw new NoSuchElementException();
      } else {
         return bytes;
      }
   }

   public byte[] take() throws Exception {
      return this.internalPoll(0L, (TimeUnit)null);
   }

   public boolean offer(byte[] data) throws Exception {
      String thisPath = ZKPaths.makePath(this.path, "qn-");
      ((ACLBackgroundPathAndBytesable)this.client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL)).forPath(thisPath, data);
      return true;
   }

   public byte[] peek() throws Exception {
      try {
         return this.element();
      } catch (NoSuchElementException var2) {
         return null;
      }
   }

   public byte[] poll(long timeout, TimeUnit unit) throws Exception {
      return this.internalPoll(timeout, unit);
   }

   public byte[] poll() throws Exception {
      try {
         return this.remove();
      } catch (NoSuchElementException var2) {
         return null;
      }
   }

   protected void ensurePath() throws Exception {
      this.ensureContainers.ensure();
   }

   private byte[] internalPoll(long timeout, TimeUnit unit) throws Exception {
      this.ensurePath();
      long startMs = System.currentTimeMillis();
      boolean hasTimeout = unit != null;
      long maxWaitMs = hasTimeout ? TimeUnit.MILLISECONDS.convert(timeout, unit) : Long.MAX_VALUE;

      while(true) {
         final CountDownLatch latch = new CountDownLatch(1);
         Watcher watcher = new Watcher() {
            public void process(WatchedEvent event) {
               latch.countDown();
            }
         };

         byte[] bytes;
         try {
            bytes = this.internalElement(true, watcher);
         } catch (NoSuchElementException var16) {
            this.log.debug("Parent containers appear to have lapsed - recreate and retry");
            this.ensureContainers.reset();
            continue;
         }

         if (bytes != null) {
            return bytes;
         }

         if (hasTimeout) {
            long elapsedMs = System.currentTimeMillis() - startMs;
            long thisWaitMs = maxWaitMs - elapsedMs;
            if (thisWaitMs <= 0L) {
               return null;
            }

            latch.await(thisWaitMs, TimeUnit.MILLISECONDS);
         } else {
            latch.await();
         }
      }
   }

   private byte[] internalElement(boolean removeIt, Watcher watcher) throws Exception {
      this.ensurePath();

      List<String> nodes;
      try {
         nodes = watcher != null ? (List)((BackgroundPathable)this.client.getChildren().usingWatcher(watcher)).forPath(this.path) : (List)this.client.getChildren().forPath(this.path);
      } catch (KeeperException.NoNodeException var8) {
         throw new NoSuchElementException();
      }

      Collections.sort(nodes);

      for(String node : nodes) {
         if (node.startsWith("qn-")) {
            String thisPath = ZKPaths.makePath(this.path, node);

            try {
               byte[] bytes = (byte[])this.client.getData().forPath(thisPath);
               if (removeIt) {
                  this.client.delete().forPath(thisPath);
               }

               return bytes;
            } catch (KeeperException.NoNodeException var9) {
            }
         } else {
            this.log.warn("Foreign node in queue path: " + node);
         }
      }

      return null;
   }
}

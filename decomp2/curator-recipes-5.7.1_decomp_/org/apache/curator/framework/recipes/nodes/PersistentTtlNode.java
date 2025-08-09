package org.apache.curator.framework.recipes.nodes;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistentTtlNode implements Closeable {
   public static final String DEFAULT_CHILD_NODE_NAME = "touch";
   public static final int DEFAULT_TOUCH_SCHEDULE_FACTOR = 2;
   public static final boolean DEFAULT_USE_PARENT_CREATION = true;
   private final Logger log;
   private final PersistentNode node;
   private final CuratorFramework client;
   private final long ttlMs;
   private final int touchScheduleFactor;
   private final ScheduledExecutorService executorService;
   private final AtomicReference futureRef;
   private final String childPath;

   public PersistentTtlNode(CuratorFramework client, String path, long ttlMs, byte[] initData) {
      this(client, Executors.newSingleThreadScheduledExecutor(ThreadUtils.newThreadFactory("PersistentTtlNode")), path, ttlMs, initData, "touch", 2, true);
   }

   public PersistentTtlNode(CuratorFramework client, String path, long ttlMs, byte[] initData, boolean useParentCreation) {
      this(client, Executors.newSingleThreadScheduledExecutor(ThreadUtils.newThreadFactory("PersistentTtlNode")), path, ttlMs, initData, "touch", 2, useParentCreation);
   }

   public PersistentTtlNode(CuratorFramework client, ScheduledExecutorService executorService, String path, long ttlMs, byte[] initData, String childNodeName, int touchScheduleFactor) {
      this(client, executorService, path, ttlMs, initData, childNodeName, touchScheduleFactor, true);
   }

   public PersistentTtlNode(CuratorFramework client, ScheduledExecutorService executorService, String path, long ttlMs, byte[] initData, String childNodeName, int touchScheduleFactor, boolean useParentCreation) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.futureRef = new AtomicReference();
      this.client = (CuratorFramework)Objects.requireNonNull(client, "client cannot be null");
      this.ttlMs = ttlMs;
      this.touchScheduleFactor = touchScheduleFactor;
      this.node = new PersistentNode(client, CreateMode.CONTAINER, false, path, initData, useParentCreation) {
         protected void deleteNode() {
         }
      };
      this.executorService = (ScheduledExecutorService)Objects.requireNonNull(executorService, "executorService cannot be null");
      this.childPath = ZKPaths.makePath((String)Objects.requireNonNull(path, "path cannot be null"), childNodeName);
   }

   public void start() {
      this.node.start();
      Runnable touchTask = new Runnable() {
         public void run() {
            try {
               try {
                  PersistentTtlNode.this.client.setData().forPath(PersistentTtlNode.this.childPath);
               } catch (KeeperException.NoNodeException var2) {
                  ((ACLBackgroundPathAndBytesable)PersistentTtlNode.this.client.create().orSetData().withTtl(PersistentTtlNode.this.ttlMs).withMode(CreateMode.PERSISTENT_WITH_TTL)).forPath(PersistentTtlNode.this.childPath);
               }
            } catch (KeeperException.NoNodeException var3) {
            } catch (Exception e) {
               if (!ThreadUtils.checkInterrupted(e)) {
                  PersistentTtlNode.this.log.debug("Could not touch child node", e);
               }
            }

         }
      };
      Future<?> future = this.executorService.scheduleAtFixedRate(touchTask, this.ttlMs / (long)this.touchScheduleFactor, this.ttlMs / (long)this.touchScheduleFactor, TimeUnit.MILLISECONDS);
      this.futureRef.set(future);
   }

   public boolean waitForInitialCreate(long timeout, TimeUnit unit) throws InterruptedException {
      return this.node.waitForInitialCreate(timeout, unit);
   }

   public void setData(byte[] data) throws Exception {
      this.node.setData(data);
   }

   public byte[] getData() {
      return this.node.getData();
   }

   public void close() {
      Future<?> future = (Future)this.futureRef.getAndSet((Object)null);
      if (future != null) {
         future.cancel(true);
      }

      try {
         this.node.close();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}

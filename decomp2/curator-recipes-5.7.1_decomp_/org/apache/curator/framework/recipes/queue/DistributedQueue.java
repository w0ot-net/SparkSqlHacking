package org.apache.curator.framework.recipes.queue;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.api.ACLPathAndBytesable;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.ErrorListenerPathAndBytesable;
import org.apache.curator.framework.api.WatchPathable;
import org.apache.curator.framework.api.transaction.CuratorTransactionBridge;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedQueue implements QueueBase {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final CuratorFramework client;
   private final QueueSerializer serializer;
   private final String queuePath;
   private final Executor executor;
   private final ExecutorService service;
   private final AtomicReference state;
   private final QueueConsumer consumer;
   private final int minItemsBeforeRefresh;
   private final boolean refreshOnWatch;
   private final boolean isProducerOnly;
   private final String lockPath;
   private final AtomicReference errorMode;
   private final StandardListenerManager putListenerContainer;
   private final AtomicInteger lastChildCount;
   private final int maxItems;
   private final int finalFlushMs;
   private final boolean putInBackground;
   private final ChildrenCache childrenCache;
   private final AtomicInteger putCount;
   private static final String QUEUE_ITEM_NAME = "queue-";

   DistributedQueue(CuratorFramework client, QueueConsumer consumer, QueueSerializer serializer, String queuePath, ThreadFactory threadFactory, Executor executor, int minItemsBeforeRefresh, boolean refreshOnWatch, String lockPath, int maxItems, boolean putInBackground, int finalFlushMs) {
      this.state = new AtomicReference(DistributedQueue.State.LATENT);
      this.errorMode = new AtomicReference(ErrorMode.REQUEUE);
      this.putListenerContainer = StandardListenerManager.standard();
      this.lastChildCount = new AtomicInteger(0);
      this.putCount = new AtomicInteger(0);
      Preconditions.checkNotNull(client, "client cannot be null");
      Preconditions.checkNotNull(serializer, "serializer cannot be null");
      Preconditions.checkNotNull(threadFactory, "threadFactory cannot be null");
      Preconditions.checkNotNull(executor, "executor cannot be null");
      Preconditions.checkArgument(maxItems > 0, "maxItems must be a positive number");
      this.isProducerOnly = consumer == null;
      this.lockPath = lockPath == null ? null : PathUtils.validatePath(lockPath);
      this.putInBackground = putInBackground;
      this.consumer = consumer;
      this.minItemsBeforeRefresh = minItemsBeforeRefresh;
      this.refreshOnWatch = refreshOnWatch;
      this.client = client;
      this.serializer = serializer;
      this.queuePath = PathUtils.validatePath(queuePath);
      this.executor = executor;
      this.maxItems = maxItems;
      this.finalFlushMs = finalFlushMs;
      this.service = Executors.newFixedThreadPool(2, threadFactory);
      this.childrenCache = new ChildrenCache(client, queuePath);
      if (maxItems != Integer.MAX_VALUE && putInBackground) {
         this.log.warn("Bounded queues should set putInBackground(false) in the builder. Putting in the background will result in spotty maxItem consistency.");
      }

   }

   public void start() throws Exception {
      if (!this.state.compareAndSet(DistributedQueue.State.LATENT, DistributedQueue.State.STARTED)) {
         throw new IllegalStateException();
      } else {
         try {
            this.client.create().creatingParentContainersIfNeeded().forPath(this.queuePath);
         } catch (KeeperException.NodeExistsException var3) {
         }

         if (this.lockPath != null) {
            try {
               this.client.create().creatingParentContainersIfNeeded().forPath(this.lockPath);
            } catch (KeeperException.NodeExistsException var2) {
            }
         }

         if (!this.isProducerOnly || this.maxItems != Integer.MAX_VALUE) {
            this.childrenCache.start();
         }

         if (!this.isProducerOnly) {
            this.service.submit(new Callable() {
               public Object call() {
                  DistributedQueue.this.runLoop();
                  return null;
               }
            });
         }

      }
   }

   public void close() throws IOException {
      if (this.state.compareAndSet(DistributedQueue.State.STARTED, DistributedQueue.State.STOPPED)) {
         if (this.finalFlushMs > 0) {
            try {
               this.flushPuts((long)this.finalFlushMs, TimeUnit.MILLISECONDS);
            } catch (InterruptedException var2) {
               Thread.currentThread().interrupt();
            }
         }

         CloseableUtils.closeQuietly(this.childrenCache);
         this.putListenerContainer.clear();
         this.service.shutdownNow();
      }

   }

   public Listenable getPutListenerContainer() {
      return this.putListenerContainer;
   }

   public void setErrorMode(ErrorMode newErrorMode) {
      Preconditions.checkNotNull(this.lockPath, "lockPath cannot be null");
      if (newErrorMode == ErrorMode.REQUEUE) {
         this.log.warn("ErrorMode.REQUEUE requires ZooKeeper version 3.4.x+ - make sure you are not using a prior version");
      }

      this.errorMode.set(newErrorMode);
   }

   public boolean flushPuts(long waitTime, TimeUnit timeUnit) throws InterruptedException {
      long msWaitRemaining = TimeUnit.MILLISECONDS.convert(waitTime, timeUnit);
      synchronized(this.putCount) {
         while(this.putCount.get() > 0) {
            if (msWaitRemaining <= 0L) {
               return false;
            }

            long startMs = System.currentTimeMillis();
            this.putCount.wait(msWaitRemaining);
            long elapsedMs = System.currentTimeMillis() - startMs;
            msWaitRemaining -= elapsedMs;
         }

         return true;
      }
   }

   public void put(Object item) throws Exception {
      this.put(item, 0, (TimeUnit)null);
   }

   public boolean put(Object item, int maxWait, TimeUnit unit) throws Exception {
      this.checkState();
      String path = this.makeItemPath();
      return this.internalPut(item, (MultiItem)null, path, maxWait, unit);
   }

   public void putMulti(MultiItem items) throws Exception {
      this.putMulti(items, 0, (TimeUnit)null);
   }

   public boolean putMulti(MultiItem items, int maxWait, TimeUnit unit) throws Exception {
      this.checkState();
      String path = this.makeItemPath();
      return this.internalPut((Object)null, items, path, maxWait, unit);
   }

   public int getLastMessageCount() {
      return this.lastChildCount.get();
   }

   boolean internalPut(Object item, MultiItem multiItem, String path, int maxWait, TimeUnit unit) throws Exception {
      if (!this.blockIfMaxed(maxWait, unit)) {
         return false;
      } else {
         MultiItem<T> givenMultiItem = multiItem;
         if (item != null) {
            final AtomicReference<T> ref = new AtomicReference(item);
            multiItem = new MultiItem() {
               public Object nextItem() throws Exception {
                  return ref.getAndSet((Object)null);
               }
            };
         }

         this.putCount.incrementAndGet();
         byte[] bytes = ItemSerializer.serialize(multiItem, this.serializer);
         if (this.putInBackground) {
            this.doPutInBackground(item, path, givenMultiItem, bytes);
         } else {
            this.doPutInForeground(item, path, givenMultiItem, bytes);
         }

         return true;
      }
   }

   private void doPutInForeground(Object item, String path, MultiItem givenMultiItem, byte[] bytes) throws Exception {
      ((ACLBackgroundPathAndBytesable)this.client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL)).forPath(path, bytes);
      synchronized(this.putCount) {
         this.putCount.decrementAndGet();
         this.putCount.notifyAll();
      }

      this.putListenerContainer.forEach((listener) -> {
         if (item != null) {
            listener.putCompleted(item);
         } else {
            listener.putMultiCompleted(givenMultiItem);
         }

      });
   }

   private void doPutInBackground(final Object item, String path, final MultiItem givenMultiItem, byte[] bytes) throws Exception {
      BackgroundCallback callback = new BackgroundCallback() {
         public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            if (event.getResultCode() == Code.OK.intValue()) {
               if (event.getType() == CuratorEventType.CREATE) {
                  synchronized(DistributedQueue.this.putCount) {
                     DistributedQueue.this.putCount.decrementAndGet();
                     DistributedQueue.this.putCount.notifyAll();
                  }
               }

               DistributedQueue.this.putListenerContainer.forEach((listener) -> {
                  if (item != null) {
                     listener.putCompleted(item);
                  } else {
                     listener.putMultiCompleted(givenMultiItem);
                  }

               });
            }
         }
      };
      this.internalCreateNode(path, bytes, callback);
   }

   @VisibleForTesting
   void internalCreateNode(String path, byte[] bytes, BackgroundCallback callback) throws Exception {
      ((ErrorListenerPathAndBytesable)((ACLBackgroundPathAndBytesable)this.client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL)).inBackground(callback)).forPath(path, bytes);
   }

   void checkState() throws Exception {
      if (this.state.get() != DistributedQueue.State.STARTED) {
         throw new IllegalStateException();
      }
   }

   String makeItemPath() {
      return ZKPaths.makePath(this.queuePath, "queue-");
   }

   @VisibleForTesting
   ChildrenCache getCache() {
      return this.childrenCache;
   }

   protected void sortChildren(List children) {
      Collections.sort(children);
   }

   protected List getChildren() throws Exception {
      return (List)this.client.getChildren().forPath(this.queuePath);
   }

   protected long getDelay(String itemNode) {
      return 0L;
   }

   protected boolean tryRemove(String itemNode) throws Exception {
      boolean isUsingLockSafety = this.lockPath != null;
      return isUsingLockSafety ? this.processWithLockSafety(itemNode, DistributedQueue.ProcessType.REMOVE) : this.processNormally(itemNode, DistributedQueue.ProcessType.REMOVE);
   }

   private boolean blockIfMaxed(int maxWait, TimeUnit unit) throws Exception {
      ChildrenCache.Data data = this.childrenCache.getData();

      while(data.children.size() >= this.maxItems) {
         long previousVersion = data.version;
         data = this.childrenCache.blockingNextGetData(data.version, (long)maxWait, unit);
         if (data.version == previousVersion) {
            return false;
         }
      }

      return true;
   }

   private void runLoop() {
      long currentVersion = -1L;
      long maxWaitMs = -1L;

      try {
         while(this.state.get() == DistributedQueue.State.STARTED) {
            try {
               ChildrenCache.Data data = maxWaitMs > 0L ? this.childrenCache.blockingNextGetData(currentVersion, maxWaitMs, TimeUnit.MILLISECONDS) : this.childrenCache.blockingNextGetData(currentVersion);
               currentVersion = data.version;
               List<String> children = Lists.newArrayList(data.children);
               this.sortChildren(children);
               if (children.size() > 0) {
                  maxWaitMs = this.getDelay((String)children.get(0));
                  if (maxWaitMs <= 0L) {
                     this.processChildren(children, currentVersion);
                  }
               }
            } catch (InterruptedException var7) {
            }
         }
      } catch (Exception e) {
         this.log.error("Exception caught in background handler", e);
      }

   }

   private void processChildren(List children, long currentVersion) throws Exception {
      final Semaphore processedLatch = new Semaphore(0);
      final boolean isUsingLockSafety = this.lockPath != null;
      int min = this.minItemsBeforeRefresh;

      for(final String itemNode : children) {
         if (Thread.currentThread().isInterrupted()) {
            processedLatch.release(children.size());
            break;
         }

         if (!itemNode.startsWith("queue-")) {
            this.log.warn("Foreign node in queue path: " + itemNode);
            processedLatch.release();
         } else {
            if (min-- <= 0 && this.refreshOnWatch && currentVersion != this.childrenCache.getData().version) {
               processedLatch.release(children.size());
               break;
            }

            if (this.getDelay(itemNode) > 0L) {
               processedLatch.release();
            } else {
               this.executor.execute(new Runnable() {
                  public void run() {
                     try {
                        if (isUsingLockSafety) {
                           DistributedQueue.this.processWithLockSafety(itemNode, DistributedQueue.ProcessType.NORMAL);
                        } else {
                           DistributedQueue.this.processNormally(itemNode, DistributedQueue.ProcessType.NORMAL);
                        }
                     } catch (Exception e) {
                        ThreadUtils.checkInterrupted(e);
                        DistributedQueue.this.log.error("Error processing message at " + itemNode, e);
                     } finally {
                        processedLatch.release();
                     }

                  }
               });
            }
         }
      }

      processedLatch.acquire(children.size());
   }

   private ProcessMessageBytesCode processMessageBytes(String itemNode, byte[] bytes) throws Exception {
      ProcessMessageBytesCode resultCode = DistributedQueue.ProcessMessageBytesCode.NORMAL;

      MultiItem<T> items;
      try {
         items = ItemSerializer.deserialize(bytes, this.serializer);
      } catch (Throwable e) {
         ThreadUtils.checkInterrupted(e);
         this.log.error("Corrupted queue item: " + itemNode, e);
         return resultCode;
      }

      while(true) {
         T item = (T)items.nextItem();
         if (item == null) {
            break;
         }

         try {
            this.consumer.consumeMessage(item);
         } catch (Throwable e) {
            ThreadUtils.checkInterrupted(e);
            this.log.error("Exception processing queue item: " + itemNode, e);
            if (this.errorMode.get() == ErrorMode.REQUEUE) {
               resultCode = DistributedQueue.ProcessMessageBytesCode.REQUEUE;
               break;
            }
         }
      }

      return resultCode;
   }

   private boolean processNormally(String itemNode, ProcessType type) throws Exception {
      try {
         String itemPath = ZKPaths.makePath(this.queuePath, itemNode);
         Stat stat = new Stat();
         byte[] bytes = null;
         if (type == DistributedQueue.ProcessType.NORMAL) {
            bytes = (byte[])((WatchPathable)this.client.getData().storingStatIn(stat)).forPath(itemPath);
         }

         if (this.client.getState() == CuratorFrameworkState.STARTED) {
            ((BackgroundPathable)this.client.delete().withVersion(stat.getVersion())).forPath(itemPath);
         }

         if (type == DistributedQueue.ProcessType.NORMAL) {
            this.processMessageBytes(itemNode, bytes);
         }

         return true;
      } catch (KeeperException.NodeExistsException var6) {
      } catch (KeeperException.NoNodeException var7) {
      } catch (KeeperException.BadVersionException var8) {
      }

      return false;
   }

   @VisibleForTesting
   protected boolean processWithLockSafety(String itemNode, ProcessType type) throws Exception {
      String lockNodePath = ZKPaths.makePath(this.lockPath, itemNode);
      boolean lockCreated = false;

      try {
         ((ACLBackgroundPathAndBytesable)this.client.create().withMode(CreateMode.EPHEMERAL)).forPath(lockNodePath);
         lockCreated = true;
         String itemPath = ZKPaths.makePath(this.queuePath, itemNode);
         boolean requeue = false;
         byte[] bytes = null;
         if (type == DistributedQueue.ProcessType.NORMAL) {
            bytes = (byte[])this.client.getData().forPath(itemPath);
            requeue = this.processMessageBytes(itemNode, bytes) == DistributedQueue.ProcessMessageBytesCode.REQUEUE;
         }

         if (requeue) {
            ((CuratorTransactionBridge)((ACLPathAndBytesable)((CuratorTransactionBridge)this.client.inTransaction().delete().forPath(itemPath)).and().create().withMode(CreateMode.PERSISTENT_SEQUENTIAL)).forPath(this.makeRequeueItemPath(itemPath), bytes)).and().commit();
         } else {
            this.client.delete().forPath(itemPath);
         }

         boolean var8 = true;
         return var8;
      } catch (KeeperException.NodeExistsException var14) {
      } catch (KeeperException.NoNodeException var15) {
      } catch (KeeperException.BadVersionException var16) {
      } finally {
         if (lockCreated) {
            ((ChildrenDeletable)this.client.delete().guaranteed()).forPath(lockNodePath);
         }

      }

      return false;
   }

   protected String makeRequeueItemPath(String itemPath) {
      return this.makeItemPath();
   }

   private static enum State {
      LATENT,
      STARTED,
      STOPPED;
   }

   @VisibleForTesting
   protected static enum ProcessType {
      NORMAL,
      REMOVE;
   }

   private static enum ProcessMessageBytesCode {
      NORMAL,
      REQUEUE;
   }
}

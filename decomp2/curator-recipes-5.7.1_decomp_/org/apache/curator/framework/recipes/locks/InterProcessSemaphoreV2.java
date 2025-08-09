package org.apache.curator.framework.recipes.locks;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.curator.RetryLoop;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.PathAndBytesable;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Sets;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterProcessSemaphoreV2 {
   private final Logger log;
   private final InterProcessMutex lock;
   private final WatcherRemoveCuratorFramework client;
   private final String leasesPath;
   private final Watcher watcher;
   private volatile byte[] nodeData;
   private volatile int maxLeases;
   private static final String LOCK_PARENT = "locks";
   private static final String LEASE_PARENT = "leases";
   private static final String LEASE_BASE_NAME = "lease-";
   public static final Set LOCK_SCHEMA = Sets.newHashSet(new String[]{"locks", "leases"});
   static volatile CountDownLatch debugAcquireLatch = null;
   static volatile CountDownLatch debugFailedGetChildrenLatch = null;
   volatile CountDownLatch debugWaitLatch;

   public InterProcessSemaphoreV2(CuratorFramework client, String path, int maxLeases) {
      this(client, path, maxLeases, (SharedCountReader)null);
   }

   public InterProcessSemaphoreV2(CuratorFramework client, String path, SharedCountReader count) {
      this(client, path, 0, count);
   }

   private InterProcessSemaphoreV2(final CuratorFramework client, String path, int maxLeases, SharedCountReader count) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.watcher = new Watcher() {
         public void process(WatchedEvent event) {
            InterProcessSemaphoreV2.this.client.postSafeNotify(InterProcessSemaphoreV2.this);
         }
      };
      this.debugWaitLatch = null;
      this.client = client.newWatcherRemoveCuratorFramework();
      path = PathUtils.validatePath(path);
      this.lock = new InterProcessMutex(client, ZKPaths.makePath(path, "locks"));
      this.maxLeases = count != null ? count.getCount() : maxLeases;
      this.leasesPath = ZKPaths.makePath(path, "leases");
      if (count != null) {
         count.addListener(new SharedCountListener() {
            public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
               InterProcessSemaphoreV2.this.maxLeases = newCount;
               client.postSafeNotify(InterProcessSemaphoreV2.this);
            }

            public void stateChanged(CuratorFramework clientx, ConnectionState newState) {
            }
         });
      }

   }

   public void setNodeData(byte[] nodeData) {
      this.nodeData = nodeData != null ? Arrays.copyOf(nodeData, nodeData.length) : null;
   }

   public Collection getParticipantNodes() throws Exception {
      return (Collection)this.client.getChildren().forPath(this.leasesPath);
   }

   public void returnAll(Collection leases) {
      for(Lease l : leases) {
         CloseableUtils.closeQuietly(l);
      }

   }

   public void returnLease(Lease lease) {
      CloseableUtils.closeQuietly(lease);
   }

   public Lease acquire() throws Exception {
      Collection<Lease> leases = this.acquire(1, 0L, (TimeUnit)null);
      return (Lease)leases.iterator().next();
   }

   public Collection acquire(int qty) throws Exception {
      return this.acquire(qty, 0L, (TimeUnit)null);
   }

   public Lease acquire(long time, TimeUnit unit) throws Exception {
      Collection<Lease> leases = this.acquire(1, time, unit);
      return leases != null ? (Lease)leases.iterator().next() : null;
   }

   public Collection acquire(int qty, long time, TimeUnit unit) throws Exception {
      long startMs = System.currentTimeMillis();
      boolean hasWait = unit != null;
      long waitMs = hasWait ? TimeUnit.MILLISECONDS.convert(time, unit) : 0L;
      Preconditions.checkArgument(qty > 0, "qty cannot be 0");
      ImmutableList.Builder<Lease> builder = ImmutableList.builder();
      boolean success = false;

      try {
         while(qty-- > 0) {
            int retryCount = 0;
            long startMillis = System.currentTimeMillis();
            boolean isDone = false;

            while(!isDone) {
               switch (this.internalAcquire1Lease(builder, startMs, hasWait, waitMs)) {
                  case CONTINUE:
                     isDone = true;
                     break;
                  case RETURN_NULL:
                     Object var16 = null;
                     return (Collection)var16;
                  case RETRY_DUE_TO_MISSING_NODE:
                     if (!this.client.getZookeeperClient().getRetryPolicy().allowRetry(retryCount++, System.currentTimeMillis() - startMillis, RetryLoop.getDefaultRetrySleeper())) {
                        throw new KeeperException.NoNodeException("Sequential path not found - possible session loss");
                     }
               }
            }
         }

         success = true;
         return builder.build();
      } finally {
         if (!success) {
            this.returnAll(builder.build());
         }

      }
   }

   private InternalAcquireResult internalAcquire1Lease(ImmutableList.Builder builder, long startMs, boolean hasWait, long waitMs) throws Exception {
      if (this.client.getState() != CuratorFrameworkState.STARTED) {
         return InterProcessSemaphoreV2.InternalAcquireResult.RETURN_NULL;
      } else {
         if (hasWait) {
            long thisWaitMs = this.getThisWaitMs(startMs, waitMs);
            if (!this.lock.acquire(thisWaitMs, TimeUnit.MILLISECONDS)) {
               return InterProcessSemaphoreV2.InternalAcquireResult.RETURN_NULL;
            }
         } else {
            this.lock.acquire();
         }

         Lease lease = null;
         boolean success = false;

         try {
            PathAndBytesable<String> createBuilder = (PathAndBytesable)this.client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL);
            String path = this.nodeData != null ? (String)createBuilder.forPath(ZKPaths.makePath(this.leasesPath, "lease-"), this.nodeData) : (String)createBuilder.forPath(ZKPaths.makePath(this.leasesPath, "lease-"));
            String nodeName = ZKPaths.getNodeFromPath(path);
            lease = this.makeLease(path);
            if (debugAcquireLatch != null) {
               debugAcquireLatch.await();
            }

            try {
               synchronized(this) {
                  while(true) {
                     List<String> children;
                     Exception e;
                     try {
                        children = (List)((BackgroundPathable)this.client.getChildren().usingWatcher(this.watcher)).forPath(this.leasesPath);
                     } catch (Exception var28) {
                        e = var28;
                        if (debugFailedGetChildrenLatch != null) {
                           debugFailedGetChildrenLatch.countDown();
                        }

                        throw var28;
                     }

                     if (!children.contains(nodeName)) {
                        this.log.error("Sequential path not found: " + path);
                        e = InterProcessSemaphoreV2.InternalAcquireResult.RETRY_DUE_TO_MISSING_NODE;
                        return e;
                     }

                     if (children.size() <= this.maxLeases) {
                        success = true;
                        break;
                     }

                     if (hasWait) {
                        e = this.getThisWaitMs(startMs, waitMs);
                        if (e <= 0L) {
                           InternalAcquireResult var16 = InterProcessSemaphoreV2.InternalAcquireResult.RETURN_NULL;
                           return var16;
                        }

                        if (this.debugWaitLatch != null) {
                           this.debugWaitLatch.countDown();
                        }

                        this.wait((long)e);
                     } else {
                        if (this.debugWaitLatch != null) {
                           this.debugWaitLatch.countDown();
                        }

                        this.wait();
                     }
                  }
               }
            } finally {
               if (!success) {
                  this.returnLease(lease);
               }

               this.client.removeWatchers();
            }
         } finally {
            this.lock.release();
         }

         builder.add(Preconditions.checkNotNull(lease));
         return InterProcessSemaphoreV2.InternalAcquireResult.CONTINUE;
      }
   }

   private long getThisWaitMs(long startMs, long waitMs) {
      long elapsedMs = System.currentTimeMillis() - startMs;
      return waitMs - elapsedMs;
   }

   private Lease makeLease(final String path) {
      return new Lease() {
         public void close() throws IOException {
            try {
               ((ChildrenDeletable)InterProcessSemaphoreV2.this.client.delete().guaranteed()).forPath(path);
            } catch (KeeperException.NoNodeException e) {
               InterProcessSemaphoreV2.this.log.warn("Lease already released", e);
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               throw new IOException(e);
            }

         }

         public byte[] getData() throws Exception {
            return (byte[])InterProcessSemaphoreV2.this.client.getData().forPath(path);
         }

         public String getNodeName() {
            return ZKPaths.getNodeFromPath(path);
         }
      };
   }

   private static enum InternalAcquireResult {
      CONTINUE,
      RETURN_NULL,
      RETRY_DUE_TO_MISSING_NODE;
   }
}

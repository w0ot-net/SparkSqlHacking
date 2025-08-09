package org.apache.curator.framework.recipes.barriers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.common.collect.Iterables;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

public class DistributedDoubleBarrier {
   private final CuratorFramework client;
   private final String barrierPath;
   private final int memberQty;
   private final String ourPath;
   private final String readyPath;
   private final AtomicBoolean hasBeenNotified = new AtomicBoolean(false);
   private final AtomicBoolean connectionLost = new AtomicBoolean(false);
   private final Watcher watcher = new Watcher() {
      public void process(WatchedEvent event) {
         DistributedDoubleBarrier.this.connectionLost.set(event.getState() != KeeperState.SyncConnected);
         DistributedDoubleBarrier.this.client.runSafe(() -> {
            synchronized(DistributedDoubleBarrier.this) {
               DistributedDoubleBarrier.this.hasBeenNotified.set(true);
               DistributedDoubleBarrier.this.notifyAll();
            }
         });
      }
   };
   private static final String READY_NODE = "ready";

   public DistributedDoubleBarrier(CuratorFramework client, String barrierPath, int memberQty) {
      Preconditions.checkState(memberQty > 0, "memberQty cannot be 0");
      this.client = client;
      this.barrierPath = PathUtils.validatePath(barrierPath);
      this.memberQty = memberQty;
      this.ourPath = ZKPaths.makePath(barrierPath, UUID.randomUUID().toString());
      this.readyPath = ZKPaths.makePath(barrierPath, "ready");
   }

   public void enter() throws Exception {
      this.enter(-1L, (TimeUnit)null);
   }

   public boolean enter(long maxWait, TimeUnit unit) throws Exception {
      long startMs = System.currentTimeMillis();
      boolean hasMaxWait = unit != null;
      long maxWaitMs = hasMaxWait ? TimeUnit.MILLISECONDS.convert(maxWait, unit) : Long.MAX_VALUE;
      boolean readyPathExists = ((BackgroundPathable)this.client.checkExists().usingWatcher(this.watcher)).forPath(this.readyPath) != null;
      ((ACLBackgroundPathAndBytesable)this.client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL)).forPath(this.ourPath);
      boolean result = readyPathExists || this.internalEnter(startMs, hasMaxWait, maxWaitMs);
      if (this.connectionLost.get()) {
         throw new KeeperException.ConnectionLossException();
      } else {
         return result;
      }
   }

   public synchronized void leave() throws Exception {
      this.leave(-1L, (TimeUnit)null);
   }

   public synchronized boolean leave(long maxWait, TimeUnit unit) throws Exception {
      long startMs = System.currentTimeMillis();
      boolean hasMaxWait = unit != null;
      long maxWaitMs = hasMaxWait ? TimeUnit.MILLISECONDS.convert(maxWait, unit) : Long.MAX_VALUE;
      return this.internalLeave(startMs, hasMaxWait, maxWaitMs);
   }

   @VisibleForTesting
   protected List getChildrenForEntering() throws Exception {
      return (List)this.client.getChildren().forPath(this.barrierPath);
   }

   private List filterAndSortChildren(List children) {
      Iterable<String> filtered = Iterables.filter(children, new Predicate() {
         public boolean apply(String name) {
            return !name.equals("ready");
         }
      });
      ArrayList<String> filteredList = Lists.newArrayList(filtered);
      Collections.sort(filteredList);
      return filteredList;
   }

   private boolean internalLeave(long startMs, boolean hasMaxWait, long maxWaitMs) throws Exception {
      String ourPathName = ZKPaths.getNodeFromPath(this.ourPath);
      boolean ourNodeShouldExist = true;
      boolean result = true;

      while(!this.connectionLost.get()) {
         List<String> children;
         try {
            children = (List)this.client.getChildren().forPath(this.barrierPath);
         } catch (KeeperException.NoNodeException var18) {
            children = Lists.newArrayList();
         }

         children = this.filterAndSortChildren(children);
         if (children != null && children.size() != 0) {
            int ourIndex = children.indexOf(ourPathName);
            if (ourIndex < 0 && ourNodeShouldExist) {
               if (!this.connectionLost.get()) {
                  throw new IllegalStateException(String.format("Our path (%s) is missing", ourPathName));
               }
            } else if (children.size() == 1) {
               if (ourNodeShouldExist && !((String)children.get(0)).equals(ourPathName)) {
                  throw new IllegalStateException(String.format("Last path (%s) is not ours (%s)", children.get(0), ourPathName));
               }

               this.checkDeleteOurPath(ourNodeShouldExist);
            } else {
               boolean IsLowestNode = ourIndex == 0;
               Stat stat;
               if (IsLowestNode) {
                  String highestNodePath = ZKPaths.makePath(this.barrierPath, (String)children.get(children.size() - 1));
                  stat = (Stat)((BackgroundPathable)this.client.checkExists().usingWatcher(this.watcher)).forPath(highestNodePath);
               } else {
                  String lowestNodePath = ZKPaths.makePath(this.barrierPath, (String)children.get(0));
                  stat = (Stat)((BackgroundPathable)this.client.checkExists().usingWatcher(this.watcher)).forPath(lowestNodePath);
                  this.checkDeleteOurPath(ourNodeShouldExist);
                  ourNodeShouldExist = false;
               }

               if (stat == null) {
                  continue;
               }

               if (!hasMaxWait) {
                  this.wait();
                  continue;
               }

               long elapsed = System.currentTimeMillis() - startMs;
               long thisWaitMs = maxWaitMs - elapsed;
               if (thisWaitMs > 0L) {
                  this.wait(thisWaitMs);
                  continue;
               }

               result = false;
            }
         }

         try {
            this.client.delete().forPath(this.readyPath);
         } catch (KeeperException.NoNodeException var17) {
         }

         return result;
      }

      throw new KeeperException.ConnectionLossException();
   }

   private void checkDeleteOurPath(boolean shouldExist) throws Exception {
      if (shouldExist) {
         this.client.delete().forPath(this.ourPath);
      }

   }

   private synchronized boolean internalEnter(long startMs, boolean hasMaxWait, long maxWaitMs) throws Exception {
      boolean result = true;
      List<String> children = this.getChildrenForEntering();
      int count = children != null ? children.size() : 0;
      if (count >= this.memberQty) {
         try {
            this.client.create().forPath(this.readyPath);
         } catch (KeeperException.NodeExistsException var13) {
         }
      } else if (hasMaxWait && !this.hasBeenNotified.get()) {
         long elapsed = System.currentTimeMillis() - startMs;
         long thisWaitMs = maxWaitMs - elapsed;
         if (thisWaitMs <= 0L) {
            result = false;
         } else {
            this.wait(thisWaitMs);
         }

         if (!this.hasBeenNotified.get()) {
            result = false;
         }
      } else {
         this.wait();
      }

      return result;
   }
}

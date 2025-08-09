package org.apache.curator.framework.recipes.barriers;

import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.utils.PathUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class DistributedBarrier {
   private final CuratorFramework client;
   private final String barrierPath;
   private final Watcher watcher = new Watcher() {
      public void process(WatchedEvent event) {
         DistributedBarrier.this.client.postSafeNotify(DistributedBarrier.this);
      }
   };

   public DistributedBarrier(CuratorFramework client, String barrierPath) {
      this.client = client;
      this.barrierPath = PathUtils.validatePath(barrierPath);
   }

   public synchronized void setBarrier() throws Exception {
      try {
         this.client.create().creatingParentContainersIfNeeded().forPath(this.barrierPath);
      } catch (KeeperException.NodeExistsException var2) {
      }

   }

   public synchronized void removeBarrier() throws Exception {
      try {
         this.client.delete().forPath(this.barrierPath);
      } catch (KeeperException.NoNodeException var2) {
      }

   }

   public synchronized void waitOnBarrier() throws Exception {
      this.waitOnBarrier(-1L, (TimeUnit)null);
   }

   public synchronized boolean waitOnBarrier(long maxWait, TimeUnit unit) throws Exception {
      long startMs = System.currentTimeMillis();
      boolean hasMaxWait = unit != null;
      long maxWaitMs = hasMaxWait ? TimeUnit.MILLISECONDS.convert(maxWait, unit) : Long.MAX_VALUE;

      boolean result;
      while(true) {
         result = ((BackgroundPathable)this.client.checkExists().usingWatcher(this.watcher)).forPath(this.barrierPath) == null;
         if (result) {
            break;
         }

         if (hasMaxWait) {
            long elapsed = System.currentTimeMillis() - startMs;
            long thisWaitMs = maxWaitMs - elapsed;
            if (thisWaitMs <= 0L) {
               break;
            }

            this.wait(thisWaitMs);
         } else {
            this.wait();
         }
      }

      return result;
   }
}

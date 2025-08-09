package org.apache.curator.framework.recipes.locks;

import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

public class InterProcessSemaphoreMutex implements InterProcessLock {
   private final InterProcessSemaphoreV2 semaphore;
   private final WatcherRemoveCuratorFramework watcherRemoveClient;
   private volatile Lease lease;

   public InterProcessSemaphoreMutex(CuratorFramework client, String path) {
      this.watcherRemoveClient = client.newWatcherRemoveCuratorFramework();
      this.semaphore = new InterProcessSemaphoreV2(this.watcherRemoveClient, path, 1);
   }

   public void acquire() throws Exception {
      this.lease = this.semaphore.acquire();
   }

   public boolean acquire(long time, TimeUnit unit) throws Exception {
      Lease acquiredLease = this.semaphore.acquire(time, unit);
      if (acquiredLease == null) {
         return false;
      } else {
         this.lease = acquiredLease;
         return true;
      }
   }

   public void release() throws Exception {
      Lease lease = this.lease;
      Preconditions.checkState(lease != null, "Not acquired");
      this.lease = null;
      lease.close();
      this.watcherRemoveClient.removeWatchers();
   }

   public boolean isAcquiredInThisProcess() {
      return this.lease != null;
   }
}

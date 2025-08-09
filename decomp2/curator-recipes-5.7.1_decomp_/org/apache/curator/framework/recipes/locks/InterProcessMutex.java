package org.apache.curator.framework.recipes.locks;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.util.concurrent.MoreExecutors;
import org.apache.curator.utils.PathUtils;

public class InterProcessMutex implements InterProcessLock, Revocable {
   private final LockInternals internals;
   private final String basePath;
   private final ConcurrentMap threadData;
   private static final String LOCK_NAME = "lock-";

   public InterProcessMutex(CuratorFramework client, String path) {
      this(client, path, new StandardLockInternalsDriver());
   }

   public InterProcessMutex(CuratorFramework client, String path, LockInternalsDriver driver) {
      this(client, path, "lock-", 1, driver);
   }

   public void acquire() throws Exception {
      if (!this.internalLock(-1L, (TimeUnit)null)) {
         throw new IOException("Lost connection while trying to acquire lock: " + this.basePath);
      }
   }

   public boolean acquire(long time, TimeUnit unit) throws Exception {
      return this.internalLock(time, unit);
   }

   public boolean isAcquiredInThisProcess() {
      return this.threadData.size() > 0;
   }

   public void release() throws Exception {
      Thread currentThread = Thread.currentThread();
      LockData lockData = (LockData)this.threadData.get(currentThread);
      if (lockData == null) {
         throw new IllegalMonitorStateException("You do not own the lock: " + this.basePath);
      } else {
         int newLockCount = lockData.lockCount.decrementAndGet();
         if (newLockCount <= 0) {
            if (newLockCount < 0) {
               throw new IllegalMonitorStateException("Lock count has gone negative for lock: " + this.basePath);
            } else {
               try {
                  this.internals.releaseLock(lockData.lockPath);
               } finally {
                  this.threadData.remove(currentThread);
               }

            }
         }
      }
   }

   public Collection getParticipantNodes() throws Exception {
      return LockInternals.getParticipantNodes(this.internals.getClient(), this.basePath, this.internals.getLockName(), this.internals.getDriver());
   }

   public void makeRevocable(RevocationListener listener) {
      this.makeRevocable(listener, MoreExecutors.directExecutor());
   }

   public void makeRevocable(final RevocationListener listener, Executor executor) {
      this.internals.makeRevocable(new RevocationSpec(executor, new Runnable() {
         public void run() {
            listener.revocationRequested(InterProcessMutex.this);
         }
      }));
   }

   InterProcessMutex(CuratorFramework client, String path, String lockName, int maxLeases, LockInternalsDriver driver) {
      this.threadData = Maps.newConcurrentMap();
      this.basePath = PathUtils.validatePath(path);
      this.internals = new LockInternals(client, driver, path, lockName, maxLeases);
   }

   public boolean isOwnedByCurrentThread() {
      LockData lockData = (LockData)this.threadData.get(Thread.currentThread());
      return lockData != null && lockData.lockCount.get() > 0;
   }

   protected byte[] getLockNodeBytes() {
      return null;
   }

   protected String getLockPath() {
      LockData lockData = (LockData)this.threadData.get(Thread.currentThread());
      return lockData != null ? lockData.lockPath : null;
   }

   private boolean internalLock(long time, TimeUnit unit) throws Exception {
      Thread currentThread = Thread.currentThread();
      LockData lockData = (LockData)this.threadData.get(currentThread);
      if (lockData != null) {
         lockData.lockCount.incrementAndGet();
         return true;
      } else {
         String lockPath = this.internals.attemptLock(time, unit, this.getLockNodeBytes());
         if (lockPath != null) {
            LockData newLockData = new LockData(currentThread, lockPath);
            this.threadData.put(currentThread, newLockData);
            return true;
         } else {
            return false;
         }
      }
   }

   private static class LockData {
      final Thread owningThread;
      final String lockPath;
      final AtomicInteger lockCount;

      private LockData(Thread owningThread, String lockPath) {
         this.lockCount = new AtomicInteger(1);
         this.owningThread = owningThread;
         this.lockPath = lockPath;
      }
   }
}

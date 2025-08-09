package org.apache.curator.framework.recipes.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Locker implements AutoCloseable {
   private final InterProcessLock lock;
   private final AtomicBoolean acquired = new AtomicBoolean(false);

   public Locker(InterProcessLock lock, long timeout, TimeUnit unit) throws Exception {
      this.lock = lock;
      this.acquired.set(this.acquireLock(lock, timeout, unit));
      if (!this.acquired.get()) {
         throw new TimeoutException("Could not acquire lock within timeout of " + unit.toMillis(timeout) + "ms");
      }
   }

   public Locker(InterProcessLock lock) throws Exception {
      this.lock = lock;
      this.acquireLock(lock);
      this.acquired.set(true);
   }

   public void close() throws Exception {
      if (this.acquired.compareAndSet(true, false)) {
         this.releaseLock();
      }

   }

   protected void releaseLock() throws Exception {
      this.lock.release();
   }

   protected void acquireLock(InterProcessLock lock) throws Exception {
      lock.acquire();
   }

   protected boolean acquireLock(InterProcessLock lock, long timeout, TimeUnit unit) throws Exception {
      return lock.acquire(timeout, unit);
   }
}

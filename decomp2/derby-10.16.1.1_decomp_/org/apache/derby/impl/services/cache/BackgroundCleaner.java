package org.apache.derby.impl.services.cache;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.shared.common.error.StandardException;

final class BackgroundCleaner implements Serviceable {
   private final DaemonService daemonService;
   private final int clientNumber;
   private final AtomicBoolean scheduled = new AtomicBoolean();
   private final ArrayBlockingQueue queue;
   private volatile boolean shrink;
   private final ConcurrentCache cacheManager;

   BackgroundCleaner(ConcurrentCache var1, DaemonService var2, int var3) {
      this.queue = new ArrayBlockingQueue(var3);
      this.daemonService = var2;
      this.cacheManager = var1;
      this.clientNumber = var2.subscribe(this, true);
   }

   boolean scheduleClean(CacheEntry var1) {
      boolean var2 = this.queue.offer(var1);
      if (var2) {
         this.requestService();
      }

      return var2;
   }

   void scheduleShrink() {
      this.shrink = true;
      this.requestService();
   }

   private void requestService() {
      if (this.scheduled.compareAndSet(false, true)) {
         this.daemonService.serviceNow(this.clientNumber);
      }

   }

   void unsubscribe() {
      this.daemonService.unsubscribe(this.clientNumber);
   }

   public int performWork(ContextManager var1) throws StandardException {
      this.scheduled.set(false);
      if (this.shrink) {
         this.shrink = false;
         this.cacheManager.getReplacementPolicy().doShrink();
      }

      CacheEntry var2 = (CacheEntry)this.queue.poll();
      if (var2 != null) {
         try {
            this.cacheManager.cleanEntry(var2);
         } finally {
            if (!this.queue.isEmpty() || this.shrink) {
               this.requestService();
            }

         }
      }

      return 1;
   }

   public boolean serviceASAP() {
      return true;
   }

   public boolean serviceImmediately() {
      return false;
   }
}

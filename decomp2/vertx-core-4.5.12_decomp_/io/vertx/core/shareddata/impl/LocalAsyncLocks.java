package io.vertx.core.shareddata.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.shareddata.Lock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class LocalAsyncLocks {
   private final ConcurrentMap waitersMap = new ConcurrentHashMap();

   public Future acquire(ContextInternal context, String name, long timeout) {
      Promise<Lock> promise = context.promise();
      LockWaiter lockWaiter = new LockWaiter(context, name, timeout, promise);
      List<LockWaiter> waiters = (List)this.waitersMap.compute(name, (s, list) -> {
         List<LockWaiter> result;
         if (list != null) {
            result = new ArrayList(list.size() + 1);
            result.addAll(list);
         } else {
            result = new ArrayList(1);
         }

         result.add(lockWaiter);
         return result;
      });
      if (waiters.size() == 1) {
         ((LockWaiter)waiters.get(0)).acquireLock();
      }

      return promise.future();
   }

   private void nextWaiter(String lockName) {
      List<LockWaiter> waiters = (List)this.waitersMap.compute(lockName, (s, list) -> list != null && list.size() != 1 ? new ArrayList(list.subList(1, list.size())) : null);
      if (waiters != null) {
         ((LockWaiter)waiters.get(0)).acquireLock();
      }

   }

   private class LockWaiter {
      final ContextInternal context;
      final String lockName;
      final Promise promise;
      final Long timerId;

      LockWaiter(ContextInternal context, String lockName, long timeout, Promise promise) {
         this.lockName = lockName;
         this.promise = promise;
         this.context = context;
         this.timerId = timeout != Long.MAX_VALUE ? context.setTimer(timeout, (tid) -> this.timeout()) : null;
      }

      void timeout() {
         LocalAsyncLocks.this.waitersMap.compute(this.lockName, (s, list) -> {
            int idx;
            if (list != null && (idx = list.indexOf(this)) != -1) {
               if (list.size() == 1) {
                  return null;
               } else {
                  int size = list.size();
                  List<LockWaiter> n = new ArrayList(size - 1);
                  if (idx > 0) {
                     n.addAll(list.subList(0, idx));
                  }

                  if (idx + 1 < size) {
                     n.addAll(list.subList(idx + 1, size));
                  }

                  return n;
               }
            } else {
               return list;
            }
         });
         this.promise.fail("Timed out waiting to get lock");
      }

      void acquireLock() {
         if (this.timerId != null && !this.context.owner().cancelTimer(this.timerId)) {
            LocalAsyncLocks.this.nextWaiter(this.lockName);
         } else {
            this.promise.complete(LocalAsyncLocks.this.new AsyncLock(this.lockName));
         }

      }
   }

   private class AsyncLock implements LockInternal {
      final String lockName;
      final AtomicBoolean invoked = new AtomicBoolean();

      AsyncLock(String lockName) {
         this.lockName = lockName;
      }

      public void release() {
         if (this.invoked.compareAndSet(false, true)) {
            LocalAsyncLocks.this.nextWaiter(this.lockName);
         }

      }

      public int waiters() {
         List<LockWaiter> waiters = (List)LocalAsyncLocks.this.waitersMap.get(this.lockName);
         return waiters == null ? 0 : waiters.size() - 1;
      }
   }
}

package org.apache.http.pool;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Contract(
   threading = ThreadingBehavior.SAFE_CONDITIONAL
)
public abstract class AbstractConnPool implements ConnPool, ConnPoolControl {
   private final Lock lock;
   private final Condition condition;
   private final ConnFactory connFactory;
   private final Map routeToPool;
   private final Set leased;
   private final LinkedList available;
   private final LinkedList pending;
   private final Map maxPerRoute;
   private volatile boolean isShutDown;
   private volatile int defaultMaxPerRoute;
   private volatile int maxTotal;
   private volatile int validateAfterInactivity;

   public AbstractConnPool(ConnFactory connFactory, int defaultMaxPerRoute, int maxTotal) {
      this.connFactory = (ConnFactory)Args.notNull(connFactory, "Connection factory");
      this.defaultMaxPerRoute = Args.positive(defaultMaxPerRoute, "Max per route value");
      this.maxTotal = Args.positive(maxTotal, "Max total value");
      this.lock = new ReentrantLock();
      this.condition = this.lock.newCondition();
      this.routeToPool = new HashMap();
      this.leased = new HashSet();
      this.available = new LinkedList();
      this.pending = new LinkedList();
      this.maxPerRoute = new HashMap();
   }

   protected abstract PoolEntry createEntry(Object var1, Object var2);

   protected void onLease(PoolEntry entry) {
   }

   protected void onRelease(PoolEntry entry) {
   }

   protected void onReuse(PoolEntry entry) {
   }

   protected boolean validate(PoolEntry entry) {
      return true;
   }

   public boolean isShutdown() {
      return this.isShutDown;
   }

   public void shutdown() throws IOException {
      if (!this.isShutDown) {
         this.isShutDown = true;
         this.lock.lock();

         try {
            for(PoolEntry entry : this.available) {
               entry.close();
            }

            for(PoolEntry entry : this.leased) {
               entry.close();
            }

            for(RouteSpecificPool pool : this.routeToPool.values()) {
               pool.shutdown();
            }

            this.routeToPool.clear();
            this.leased.clear();
            this.available.clear();
         } finally {
            this.lock.unlock();
         }

      }
   }

   private RouteSpecificPool getPool(Object route) {
      // $FF: Couldn't be decompiled
   }

   private static Exception operationAborted() {
      return new CancellationException("Operation aborted");
   }

   public Future lease(Object route, Object state, FutureCallback callback) {
      // $FF: Couldn't be decompiled
   }

   public Future lease(Object route, Object state) {
      return this.lease(route, state, (FutureCallback)null);
   }

   private PoolEntry getPoolEntryBlocking(Object route, Object state, long timeout, TimeUnit timeUnit, Future future) throws IOException, InterruptedException, ExecutionException, TimeoutException {
      Date deadline = null;
      if (timeout > 0L) {
         deadline = new Date(System.currentTimeMillis() + timeUnit.toMillis(timeout));
      }

      this.lock.lock();

      try {
         while(true) {
            Asserts.check(!this.isShutDown, "Connection pool shut down");
            if (future.isCancelled()) {
               throw new ExecutionException(operationAborted());
            }

            RouteSpecificPool<T, C, E> pool = this.getPool(route);

            E entry;
            while(true) {
               entry = (E)pool.getFree(state);
               if (entry == null) {
                  break;
               }

               if (entry.isExpired(System.currentTimeMillis())) {
                  entry.close();
               }

               if (!entry.isClosed()) {
                  break;
               }

               this.available.remove(entry);
               pool.free(entry, false);
            }

            if (entry != null) {
               this.available.remove(entry);
               this.leased.add(entry);
               this.onReuse(entry);
               PoolEntry var26 = entry;
               return var26;
            } else {
               int maxPerRoute = this.getMax(route);
               int excess = Math.max(0, pool.getAllocatedCount() + 1 - maxPerRoute);
               if (excess > 0) {
                  for(int i = 0; i < excess; ++i) {
                     E lastUsed = (E)pool.getLastUsed();
                     if (lastUsed == null) {
                        break;
                     }

                     lastUsed.close();
                     this.available.remove(lastUsed);
                     pool.remove(lastUsed);
                  }
               }

               if (pool.getAllocatedCount() < maxPerRoute) {
                  int totalUsed = this.leased.size();
                  int freeCapacity = Math.max(this.maxTotal - totalUsed, 0);
                  if (freeCapacity > 0) {
                     int totalAvailable = this.available.size();
                     if (totalAvailable > freeCapacity - 1) {
                        E lastUsed = (E)((PoolEntry)this.available.removeLast());
                        lastUsed.close();
                        RouteSpecificPool<T, C, E> otherpool = this.getPool(lastUsed.getRoute());
                        otherpool.remove(lastUsed);
                     }

                     C conn = (C)this.connFactory.create(route);
                     entry = (E)pool.add(conn);
                     this.leased.add(entry);
                     PoolEntry var32 = entry;
                     return var32;
                  }
               }

               boolean success = false;

               try {
                  pool.queue(future);
                  this.pending.add(future);
                  if (deadline != null) {
                     success = this.condition.awaitUntil(deadline);
                  } else {
                     this.condition.await();
                     success = true;
                  }

                  if (future.isCancelled()) {
                     throw new ExecutionException(operationAborted());
                  }
               } finally {
                  pool.unqueue(future);
                  this.pending.remove(future);
               }

               if (!success && deadline != null && deadline.getTime() <= System.currentTimeMillis()) {
                  throw new TimeoutException("Timeout waiting for connection");
               }
            }
         }
      } finally {
         this.lock.unlock();
      }
   }

   public void release(PoolEntry entry, boolean reusable) {
      this.lock.lock();

      try {
         if (this.leased.remove(entry)) {
            RouteSpecificPool<T, C, E> pool = this.getPool(entry.getRoute());
            pool.free(entry, reusable);
            if (reusable && !this.isShutDown) {
               this.available.addFirst(entry);
            } else {
               entry.close();
            }

            this.onRelease(entry);
            Future<E> future = pool.nextPending();
            if (future != null) {
               this.pending.remove(future);
            } else {
               future = (Future)this.pending.poll();
            }

            if (future != null) {
               this.condition.signalAll();
            }
         }
      } finally {
         this.lock.unlock();
      }

   }

   private int getMax(Object route) {
      Integer v = (Integer)this.maxPerRoute.get(route);
      return v != null ? v : this.defaultMaxPerRoute;
   }

   public void setMaxTotal(int max) {
      Args.positive(max, "Max value");
      this.lock.lock();

      try {
         this.maxTotal = max;
      } finally {
         this.lock.unlock();
      }

   }

   public int getMaxTotal() {
      this.lock.lock();

      int var1;
      try {
         var1 = this.maxTotal;
      } finally {
         this.lock.unlock();
      }

      return var1;
   }

   public void setDefaultMaxPerRoute(int max) {
      Args.positive(max, "Max per route value");
      this.lock.lock();

      try {
         this.defaultMaxPerRoute = max;
      } finally {
         this.lock.unlock();
      }

   }

   public int getDefaultMaxPerRoute() {
      this.lock.lock();

      int var1;
      try {
         var1 = this.defaultMaxPerRoute;
      } finally {
         this.lock.unlock();
      }

      return var1;
   }

   public void setMaxPerRoute(Object route, int max) {
      Args.notNull(route, "Route");
      this.lock.lock();

      try {
         if (max > -1) {
            this.maxPerRoute.put(route, max);
         } else {
            this.maxPerRoute.remove(route);
         }
      } finally {
         this.lock.unlock();
      }

   }

   public int getMaxPerRoute(Object route) {
      Args.notNull(route, "Route");
      this.lock.lock();

      int var2;
      try {
         var2 = this.getMax(route);
      } finally {
         this.lock.unlock();
      }

      return var2;
   }

   public PoolStats getTotalStats() {
      this.lock.lock();

      PoolStats var1;
      try {
         var1 = new PoolStats(this.leased.size(), this.pending.size(), this.available.size(), this.maxTotal);
      } finally {
         this.lock.unlock();
      }

      return var1;
   }

   public PoolStats getStats(Object route) {
      Args.notNull(route, "Route");
      this.lock.lock();

      PoolStats var3;
      try {
         RouteSpecificPool<T, C, E> pool = this.getPool(route);
         var3 = new PoolStats(pool.getLeasedCount(), pool.getPendingCount(), pool.getAvailableCount(), this.getMax(route));
      } finally {
         this.lock.unlock();
      }

      return var3;
   }

   public Set getRoutes() {
      this.lock.lock();

      HashSet var1;
      try {
         var1 = new HashSet(this.routeToPool.keySet());
      } finally {
         this.lock.unlock();
      }

      return var1;
   }

   protected void enumAvailable(PoolEntryCallback callback) {
      this.lock.lock();

      try {
         Iterator<E> it = this.available.iterator();

         while(it.hasNext()) {
            E entry = (E)((PoolEntry)it.next());
            callback.process(entry);
            if (entry.isClosed()) {
               RouteSpecificPool<T, C, E> pool = this.getPool(entry.getRoute());
               pool.remove(entry);
               it.remove();
            }
         }

         this.purgePoolMap();
      } finally {
         this.lock.unlock();
      }

   }

   protected void enumLeased(PoolEntryCallback callback) {
      this.lock.lock();

      try {
         for(PoolEntry entry : this.leased) {
            callback.process(entry);
         }
      } finally {
         this.lock.unlock();
      }

   }

   private void purgePoolMap() {
      Iterator<Map.Entry<T, RouteSpecificPool<T, C, E>>> it = this.routeToPool.entrySet().iterator();

      while(it.hasNext()) {
         Map.Entry<T, RouteSpecificPool<T, C, E>> entry = (Map.Entry)it.next();
         RouteSpecificPool<T, C, E> pool = (RouteSpecificPool)entry.getValue();
         if (pool.getPendingCount() + pool.getAllocatedCount() == 0) {
            it.remove();
         }
      }

   }

   public void closeIdle(long idletime, TimeUnit timeUnit) {
      // $FF: Couldn't be decompiled
   }

   public void closeExpired() {
      // $FF: Couldn't be decompiled
   }

   public int getValidateAfterInactivity() {
      return this.validateAfterInactivity;
   }

   public void setValidateAfterInactivity(int ms) {
      this.validateAfterInactivity = ms;
   }

   public String toString() {
      this.lock.lock();

      String var2;
      try {
         StringBuilder buffer = new StringBuilder();
         buffer.append("[leased: ");
         buffer.append(this.leased);
         buffer.append("][available: ");
         buffer.append(this.available);
         buffer.append("][pending: ");
         buffer.append(this.pending);
         buffer.append("]");
         var2 = buffer.toString();
      } finally {
         this.lock.unlock();
      }

      return var2;
   }

   // $FF: synthetic method
   static Lock access$000(AbstractConnPool x0) {
      return x0.lock;
   }

   // $FF: synthetic method
   static Condition access$100(AbstractConnPool x0) {
      return x0.condition;
   }

   // $FF: synthetic method
   static Exception access$200() {
      return operationAborted();
   }

   // $FF: synthetic method
   static PoolEntry access$300(AbstractConnPool x0, Object x1, Object x2, long x3, TimeUnit x4, Future x5) throws IOException, InterruptedException, ExecutionException, TimeoutException {
      return x0.getPoolEntryBlocking(x1, x2, x3, x4, x5);
   }

   // $FF: synthetic method
   static int access$400(AbstractConnPool x0) {
      return x0.validateAfterInactivity;
   }
}

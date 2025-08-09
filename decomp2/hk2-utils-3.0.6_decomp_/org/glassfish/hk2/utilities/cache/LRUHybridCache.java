package org.glassfish.hk2.utilities.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LRUHybridCache implements Computable {
   private final CycleHandler cycleHandler;
   private static final CycleHandler EMPTY_CYCLE_HANDLER = new CycleHandler() {
      public void handleCycle(Object key) {
      }
   };
   private final ConcurrentHashMap cache;
   private final Computable computable;
   private final Object prunningLock;
   private final int maxCacheSize;
   private static final Comparator COMPARATOR = new CacheEntryImplComparator();

   public LRUHybridCache(int maxCacheSize, Computable computable) {
      this(maxCacheSize, computable, EMPTY_CYCLE_HANDLER);
   }

   public LRUHybridCache(int maxCacheSize, Computable computable, CycleHandler cycleHandler) {
      this.cache = new ConcurrentHashMap();
      this.prunningLock = new Object();
      this.maxCacheSize = maxCacheSize;
      this.computable = computable;
      this.cycleHandler = cycleHandler;
   }

   public HybridCacheEntry createCacheEntry(Object k, Object v, boolean dropMe) {
      return new HybridCacheEntryImpl(k, v, dropMe);
   }

   public HybridCacheEntry compute(Object key) {
      LRUHybridCache<K, V>.OriginThreadAwareFuture f = (OriginThreadAwareFuture)this.cache.get(key);
      if (f == null) {
         LRUHybridCache<K, V>.OriginThreadAwareFuture ft = new OriginThreadAwareFuture(this, key);
         synchronized(this.prunningLock) {
            if (this.cache.size() + 1 > this.maxCacheSize) {
               this.removeLRUItem();
            }

            f = (OriginThreadAwareFuture)this.cache.putIfAbsent(key, ft);
         }

         if (f == null) {
            f = ft;
            ft.run();
         }
      } else {
         long tid = f.threadId;
         if (tid != -1L && Thread.currentThread().getId() == f.threadId) {
            this.cycleHandler.handleCycle(key);
         }

         f.lastHit = System.nanoTime();
      }

      try {
         HybridCacheEntry result = f.get();
         if (result.dropMe()) {
            this.cache.remove(key);
         }

         return result;
      } catch (InterruptedException ex) {
         throw new RuntimeException(ex);
      } catch (ExecutionException ex) {
         this.cache.remove(key);
         if (ex.getCause() instanceof RuntimeException) {
            throw (RuntimeException)ex.getCause();
         } else {
            throw new RuntimeException(ex);
         }
      }
   }

   public void clear() {
      this.cache.clear();
   }

   public int size() {
      return this.cache.size();
   }

   public int getMaximumCacheSize() {
      return this.maxCacheSize;
   }

   public boolean containsKey(Object key) {
      return this.cache.containsKey(key);
   }

   public void remove(Object key) {
      this.cache.remove(key);
   }

   private void removeLRUItem() {
      Collection<LRUHybridCache<K, V>.OriginThreadAwareFuture> values = this.cache.values();
      this.cache.remove(((OriginThreadAwareFuture)Collections.min(values, COMPARATOR)).key);
   }

   public void releaseMatching(CacheKeyFilter filter) {
      if (filter != null) {
         for(Object key : this.cache.keySet()) {
            if (filter.matches(key)) {
               this.cache.remove(key);
            }
         }

      }
   }

   private class OriginThreadAwareFuture implements Future {
      private final Object key;
      private final FutureTask future;
      private volatile long threadId;
      private volatile long lastHit;

      OriginThreadAwareFuture(LRUHybridCache cache, final Object key) {
         this.key = key;
         this.threadId = Thread.currentThread().getId();
         Callable<HybridCacheEntry<V>> eval = new Callable() {
            public HybridCacheEntry call() throws Exception {
               HybridCacheEntry var2;
               try {
                  HybridCacheEntry<V> result = (HybridCacheEntry)LRUHybridCache.this.computable.compute(key);
                  var2 = result;
               } finally {
                  OriginThreadAwareFuture.this.threadId = -1L;
               }

               return var2;
            }
         };
         this.future = new FutureTask(eval);
         this.lastHit = System.nanoTime();
      }

      public int hashCode() {
         return this.future.hashCode();
      }

      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            LRUHybridCache<K, V>.OriginThreadAwareFuture other = (OriginThreadAwareFuture)obj;
            return this.future == other.future || this.future != null && this.future.equals(other.future);
         }
      }

      public boolean cancel(boolean mayInterruptIfRunning) {
         return this.future.cancel(mayInterruptIfRunning);
      }

      public boolean isCancelled() {
         return this.future.isCancelled();
      }

      public boolean isDone() {
         return this.future.isDone();
      }

      public HybridCacheEntry get() throws InterruptedException, ExecutionException {
         return (HybridCacheEntry)this.future.get();
      }

      public HybridCacheEntry get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
         return (HybridCacheEntry)this.future.get(timeout, unit);
      }

      public void run() {
         this.future.run();
      }
   }

   private final class HybridCacheEntryImpl implements HybridCacheEntry {
      private final Object key;
      private final Object value;
      private final boolean dropMe;

      public HybridCacheEntryImpl(Object key, Object value, boolean dropMe) {
         this.key = key;
         this.value = value;
         this.dropMe = dropMe;
      }

      public Object getValue() {
         return this.value;
      }

      public boolean dropMe() {
         return this.dropMe;
      }

      public void removeFromCache() {
         LRUHybridCache.this.remove(this.key);
      }

      public int hashCode() {
         int hash = 5;
         hash = 23 * hash + (this.key != null ? this.key.hashCode() : 0);
         return hash;
      }

      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            LRUHybridCache<K, V>.HybridCacheEntryImpl<V1> other = (HybridCacheEntryImpl)obj;
            return this.key == other.key || this.key != null && this.key.equals(other.key);
         }
      }
   }

   private static class CacheEntryImplComparator implements Comparator {
      public int compare(OriginThreadAwareFuture first, OriginThreadAwareFuture second) {
         long diff = first.lastHit - second.lastHit;
         return diff > 0L ? 1 : (diff == 0L ? 0 : -1);
      }
   }

   public interface CycleHandler {
      void handleCycle(Object var1);
   }
}

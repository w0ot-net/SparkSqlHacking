package org.glassfish.jersey.internal.util.collection;

import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class Cache implements Function {
   private static final CycleHandler EMPTY_HANDLER = (key) -> {
   };
   private final CycleHandler cycleHandler;
   private final ConcurrentHashMap cache;
   private final Function computable;

   public Cache(Function computable) {
      this(computable, EMPTY_HANDLER);
   }

   public Cache(Function computable, CycleHandler cycleHandler) {
      this.cache = new ConcurrentHashMap();
      this.computable = computable;
      this.cycleHandler = cycleHandler;
   }

   public Object apply(Object key) {
      Cache<K, V>.OriginThreadAwareFuture f = (OriginThreadAwareFuture)this.cache.get(key);
      if (f == null) {
         Cache<K, V>.OriginThreadAwareFuture ft = new OriginThreadAwareFuture(key);
         f = (OriginThreadAwareFuture)this.cache.putIfAbsent(key, ft);
         if (f == null) {
            f = ft;
            ft.run();
         }
      } else {
         long tid = f.threadId;
         if (tid != -1L && Thread.currentThread().getId() == f.threadId) {
            this.cycleHandler.handleCycle(key);
         }
      }

      try {
         return f.get();
      } catch (InterruptedException ex) {
         throw new RuntimeException(ex);
      } catch (ExecutionException ex) {
         this.cache.remove(key);
         Throwable cause = ex.getCause();
         if (cause == null) {
            throw new RuntimeException(ex);
         } else if (cause instanceof RuntimeException) {
            throw (RuntimeException)cause;
         } else {
            throw new RuntimeException(cause);
         }
      }
   }

   public void clear() {
      this.cache.clear();
   }

   public Enumeration keys() {
      return this.cache.keys();
   }

   public boolean containsKey(Object key) {
      return this.cache.containsKey(key);
   }

   public void remove(Object key) {
      this.cache.remove(key);
   }

   public int size() {
      return this.cache.size();
   }

   private class OriginThreadAwareFuture implements Future {
      private final FutureTask future;
      private volatile long threadId = Thread.currentThread().getId();

      OriginThreadAwareFuture(Object key) {
         Callable<V> eval = () -> {
            Object var2;
            try {
               var2 = Cache.this.computable.apply(key);
            } finally {
               this.threadId = -1L;
            }

            return var2;
         };
         this.future = new FutureTask(eval);
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
            Cache<K, V>.OriginThreadAwareFuture other = (OriginThreadAwareFuture)obj;
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

      public Object get() throws InterruptedException, ExecutionException {
         return this.future.get();
      }

      public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
         return this.future.get(timeout, unit);
      }

      public void run() {
         this.future.run();
      }
   }

   public interface CycleHandler {
      void handleCycle(Object var1);
   }
}

package io.vertx.core.impl;

import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CloseFuture implements Closeable {
   private final Logger log;
   private final Promise promise;
   private boolean closed;
   private Map weakHooks;
   private Map hooks;

   public CloseFuture() {
      this((Logger)null);
   }

   public CloseFuture(Logger log) {
      this.promise = Promise.promise();
      this.log = log;
   }

   public synchronized boolean add(Closeable hook) {
      if (this.closed) {
         return false;
      } else {
         if (hook instanceof CloseFuture) {
            CloseFuture fut = (CloseFuture)hook;
            fut.future().onComplete((ar) -> this.remove(fut));
            if (this.weakHooks == null) {
               this.weakHooks = new WeakHashMap();
            }

            this.weakHooks.put(hook, this);
         } else {
            if (this.hooks == null) {
               this.hooks = new HashMap();
            }

            this.hooks.put(hook, this);
         }

         return true;
      }
   }

   public synchronized boolean remove(Closeable hook) {
      if (hook instanceof CloseFuture) {
         if (this.weakHooks != null) {
            return this.weakHooks.remove(hook) != null;
         }
      } else if (this.hooks != null) {
         return this.hooks.remove(hook) != null;
      }

      return false;
   }

   public synchronized boolean isClosed() {
      return this.closed;
   }

   public Future future() {
      return this.promise.future();
   }

   public Future close() {
      List<List<Closeable>> toClose = new ArrayList();
      boolean close;
      synchronized(this) {
         close = !this.closed;
         if (this.weakHooks != null) {
            toClose.add(new ArrayList(this.weakHooks.keySet()));
         }

         if (this.hooks != null) {
            toClose.add(new ArrayList(this.hooks.keySet()));
         }

         this.closed = true;
         this.weakHooks = null;
         this.hooks = null;
      }

      if (close) {
         int num = toClose.stream().mapToInt(List::size).sum();
         if (num > 0) {
            AtomicInteger count = new AtomicInteger();

            for(List l : toClose) {
               for(Closeable hook : l) {
                  Promise<Void> closePromise = Promise.promise();
                  closePromise.future().onComplete((ar) -> {
                     if (count.incrementAndGet() == num) {
                        this.promise.complete();
                     }

                  });

                  try {
                     hook.close(closePromise);
                  } catch (Throwable var11) {
                     if (this.log != null) {
                        this.log.warn("Failed to run close hook", var11);
                     }

                     closePromise.tryFail(var11);
                  }
               }
            }
         } else {
            this.promise.complete();
         }
      }

      return this.promise.future();
   }

   public void close(Promise completionHandler) {
      this.close().onComplete(completionHandler);
   }

   protected void finalize() {
      this.close();
   }
}

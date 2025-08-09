package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import java.util.concurrent.TimeUnit;

public abstract class CompleteFuture extends AbstractFuture {
   private final EventExecutor executor;

   protected CompleteFuture(EventExecutor executor) {
      this.executor = executor;
   }

   protected EventExecutor executor() {
      return this.executor;
   }

   public Future addListener(GenericFutureListener listener) {
      DefaultPromise.notifyListener(this.executor(), this, (GenericFutureListener)ObjectUtil.checkNotNull(listener, "listener"));
      return this;
   }

   public Future addListeners(GenericFutureListener... listeners) {
      for(GenericFutureListener l : (GenericFutureListener[])ObjectUtil.checkNotNull(listeners, "listeners")) {
         if (l == null) {
            break;
         }

         DefaultPromise.notifyListener(this.executor(), this, l);
      }

      return this;
   }

   public Future removeListener(GenericFutureListener listener) {
      return this;
   }

   public Future removeListeners(GenericFutureListener... listeners) {
      return this;
   }

   public Future await() throws InterruptedException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         return this;
      }
   }

   public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         return true;
      }
   }

   public Future sync() throws InterruptedException {
      return this;
   }

   public Future syncUninterruptibly() {
      return this;
   }

   public boolean await(long timeoutMillis) throws InterruptedException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         return true;
      }
   }

   public Future awaitUninterruptibly() {
      return this;
   }

   public boolean awaitUninterruptibly(long timeout, TimeUnit unit) {
      return true;
   }

   public boolean awaitUninterruptibly(long timeoutMillis) {
      return true;
   }

   public boolean isDone() {
      return true;
   }

   public boolean isCancellable() {
      return false;
   }

   public boolean isCancelled() {
      return false;
   }

   public boolean cancel(boolean mayInterruptIfRunning) {
      return false;
   }
}

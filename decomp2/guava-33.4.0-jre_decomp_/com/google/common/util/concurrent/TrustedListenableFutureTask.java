package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
class TrustedListenableFutureTask extends FluentFuture.TrustedFuture implements RunnableFuture {
   @CheckForNull
   private volatile InterruptibleTask task;

   static TrustedListenableFutureTask create(AsyncCallable callable) {
      return new TrustedListenableFutureTask(callable);
   }

   static TrustedListenableFutureTask create(Callable callable) {
      return new TrustedListenableFutureTask(callable);
   }

   static TrustedListenableFutureTask create(Runnable runnable, @ParametricNullness Object result) {
      return new TrustedListenableFutureTask(Executors.callable(runnable, result));
   }

   TrustedListenableFutureTask(Callable callable) {
      this.task = new TrustedFutureInterruptibleTask(callable);
   }

   TrustedListenableFutureTask(AsyncCallable callable) {
      this.task = new TrustedFutureInterruptibleAsyncTask(callable);
   }

   public void run() {
      InterruptibleTask<?> localTask = this.task;
      if (localTask != null) {
         localTask.run();
      }

      this.task = null;
   }

   protected void afterDone() {
      super.afterDone();
      if (this.wasInterrupted()) {
         InterruptibleTask<?> localTask = this.task;
         if (localTask != null) {
            localTask.interruptTask();
         }
      }

      this.task = null;
   }

   @CheckForNull
   protected String pendingToString() {
      InterruptibleTask<?> localTask = this.task;
      return localTask != null ? "task=[" + localTask + "]" : super.pendingToString();
   }

   private final class TrustedFutureInterruptibleTask extends InterruptibleTask {
      private final Callable callable;

      TrustedFutureInterruptibleTask(Callable callable) {
         this.callable = (Callable)Preconditions.checkNotNull(callable);
      }

      final boolean isDone() {
         return TrustedListenableFutureTask.this.isDone();
      }

      @ParametricNullness
      Object runInterruptibly() throws Exception {
         return this.callable.call();
      }

      void afterRanInterruptiblySuccess(@ParametricNullness Object result) {
         TrustedListenableFutureTask.this.set(result);
      }

      void afterRanInterruptiblyFailure(Throwable error) {
         TrustedListenableFutureTask.this.setException(error);
      }

      String toPendingString() {
         return this.callable.toString();
      }
   }

   private final class TrustedFutureInterruptibleAsyncTask extends InterruptibleTask {
      private final AsyncCallable callable;

      TrustedFutureInterruptibleAsyncTask(AsyncCallable callable) {
         this.callable = (AsyncCallable)Preconditions.checkNotNull(callable);
      }

      final boolean isDone() {
         return TrustedListenableFutureTask.this.isDone();
      }

      ListenableFuture runInterruptibly() throws Exception {
         return (ListenableFuture)Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object)this.callable);
      }

      void afterRanInterruptiblySuccess(ListenableFuture result) {
         TrustedListenableFutureTask.this.setFuture(result);
      }

      void afterRanInterruptiblyFailure(Throwable error) {
         TrustedListenableFutureTask.this.setException(error);
      }

      String toPendingString() {
         return this.callable.toString();
      }
   }
}

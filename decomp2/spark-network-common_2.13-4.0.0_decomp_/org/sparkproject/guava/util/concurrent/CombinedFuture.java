package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ImmutableCollection;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class CombinedFuture extends AggregateFuture {
   @CheckForNull
   @LazyInit
   private CombinedFutureInterruptibleTask task;

   CombinedFuture(ImmutableCollection futures, boolean allMustSucceed, Executor listenerExecutor, AsyncCallable callable) {
      super(futures, allMustSucceed, false);
      this.task = new AsyncCallableInterruptibleTask(callable, listenerExecutor);
      this.init();
   }

   CombinedFuture(ImmutableCollection futures, boolean allMustSucceed, Executor listenerExecutor, Callable callable) {
      super(futures, allMustSucceed, false);
      this.task = new CallableInterruptibleTask(callable, listenerExecutor);
      this.init();
   }

   void collectOneValue(int index, @CheckForNull Object returnValue) {
   }

   void handleAllCompleted() {
      CombinedFuture<V>.CombinedFutureInterruptibleTask<?> localTask = this.task;
      if (localTask != null) {
         localTask.execute();
      }

   }

   void releaseResources(AggregateFuture.ReleaseResourcesReason reason) {
      super.releaseResources(reason);
      if (reason == AggregateFuture.ReleaseResourcesReason.OUTPUT_FUTURE_DONE) {
         this.task = null;
      }

   }

   protected void interruptTask() {
      CombinedFuture<V>.CombinedFutureInterruptibleTask<?> localTask = this.task;
      if (localTask != null) {
         localTask.interruptTask();
      }

   }

   private abstract class CombinedFutureInterruptibleTask extends InterruptibleTask {
      private final Executor listenerExecutor;

      CombinedFutureInterruptibleTask(Executor listenerExecutor) {
         this.listenerExecutor = (Executor)Preconditions.checkNotNull(listenerExecutor);
      }

      final boolean isDone() {
         return CombinedFuture.this.isDone();
      }

      final void execute() {
         try {
            this.listenerExecutor.execute(this);
         } catch (RejectedExecutionException e) {
            CombinedFuture.this.setException(e);
         }

      }

      final void afterRanInterruptiblySuccess(@ParametricNullness Object result) {
         CombinedFuture.this.task = null;
         this.setValue(result);
      }

      final void afterRanInterruptiblyFailure(Throwable error) {
         CombinedFuture.this.task = null;
         if (error instanceof ExecutionException) {
            CombinedFuture.this.setException(((ExecutionException)error).getCause());
         } else if (error instanceof CancellationException) {
            CombinedFuture.this.cancel(false);
         } else {
            CombinedFuture.this.setException(error);
         }

      }

      abstract void setValue(@ParametricNullness Object value);
   }

   private final class AsyncCallableInterruptibleTask extends CombinedFutureInterruptibleTask {
      private final AsyncCallable callable;

      AsyncCallableInterruptibleTask(AsyncCallable callable, Executor listenerExecutor) {
         super(listenerExecutor);
         this.callable = (AsyncCallable)Preconditions.checkNotNull(callable);
      }

      ListenableFuture runInterruptibly() throws Exception {
         ListenableFuture<V> result = this.callable.call();
         return (ListenableFuture)Preconditions.checkNotNull(result, "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object)this.callable);
      }

      void setValue(ListenableFuture value) {
         CombinedFuture.this.setFuture(value);
      }

      String toPendingString() {
         return this.callable.toString();
      }
   }

   private final class CallableInterruptibleTask extends CombinedFutureInterruptibleTask {
      private final Callable callable;

      CallableInterruptibleTask(Callable callable, Executor listenerExecutor) {
         super(listenerExecutor);
         this.callable = (Callable)Preconditions.checkNotNull(callable);
      }

      @ParametricNullness
      Object runInterruptibly() throws Exception {
         return this.callable.call();
      }

      void setValue(@ParametricNullness Object value) {
         CombinedFuture.this.set(value);
      }

      String toPendingString() {
         return this.callable.toString();
      }
   }
}

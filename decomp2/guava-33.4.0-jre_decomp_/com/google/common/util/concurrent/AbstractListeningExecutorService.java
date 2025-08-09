package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@CheckReturnValue
@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public abstract class AbstractListeningExecutorService extends AbstractExecutorService implements ListeningExecutorService {
   @CanIgnoreReturnValue
   protected final RunnableFuture newTaskFor(Runnable runnable, @ParametricNullness Object value) {
      return TrustedListenableFutureTask.create(runnable, value);
   }

   @CanIgnoreReturnValue
   protected final RunnableFuture newTaskFor(Callable callable) {
      return TrustedListenableFutureTask.create(callable);
   }

   @CanIgnoreReturnValue
   public ListenableFuture submit(Runnable task) {
      return (ListenableFuture)super.submit(task);
   }

   @CanIgnoreReturnValue
   public ListenableFuture submit(Runnable task, @ParametricNullness Object result) {
      return (ListenableFuture)super.submit(task, result);
   }

   @CanIgnoreReturnValue
   public ListenableFuture submit(Callable task) {
      return (ListenableFuture)super.submit(task);
   }
}

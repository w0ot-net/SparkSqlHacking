package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Throwables;
import org.sparkproject.guava.collect.ImmutableList;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
abstract class WrappingExecutorService implements ExecutorService {
   private final ExecutorService delegate;

   protected WrappingExecutorService(ExecutorService delegate) {
      this.delegate = (ExecutorService)Preconditions.checkNotNull(delegate);
   }

   protected abstract Callable wrapTask(Callable callable);

   protected Runnable wrapTask(Runnable command) {
      Callable<Object> wrapped = this.wrapTask(Executors.callable(command, (Object)null));
      return () -> {
         try {
            wrapped.call();
         } catch (Exception e) {
            Platform.restoreInterruptIfIsInterruptedException(e);
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
         }
      };
   }

   private ImmutableList wrapTasks(Collection tasks) {
      ImmutableList.Builder<Callable<T>> builder = ImmutableList.builder();

      for(Callable task : tasks) {
         builder.add((Object)this.wrapTask(task));
      }

      return builder.build();
   }

   public final void execute(Runnable command) {
      this.delegate.execute(this.wrapTask(command));
   }

   public final Future submit(Callable task) {
      return this.delegate.submit(this.wrapTask((Callable)Preconditions.checkNotNull(task)));
   }

   public final Future submit(Runnable task) {
      return this.delegate.submit(this.wrapTask(task));
   }

   public final Future submit(Runnable task, @ParametricNullness Object result) {
      return this.delegate.submit(this.wrapTask(task), result);
   }

   public final List invokeAll(Collection tasks) throws InterruptedException {
      return this.delegate.invokeAll(this.wrapTasks(tasks));
   }

   public final List invokeAll(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException {
      return this.delegate.invokeAll(this.wrapTasks(tasks), timeout, unit);
   }

   public final Object invokeAny(Collection tasks) throws InterruptedException, ExecutionException {
      return this.delegate.invokeAny(this.wrapTasks(tasks));
   }

   public final Object invokeAny(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      return this.delegate.invokeAny(this.wrapTasks(tasks), timeout, unit);
   }

   public final void shutdown() {
      this.delegate.shutdown();
   }

   @CanIgnoreReturnValue
   public final List shutdownNow() {
      return this.delegate.shutdownNow();
   }

   public final boolean isShutdown() {
      return this.delegate.isShutdown();
   }

   public final boolean isTerminated() {
      return this.delegate.isTerminated();
   }

   public final boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
      return this.delegate.awaitTermination(timeout, unit);
   }
}

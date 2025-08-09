package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.collect.ForwardingObject;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public abstract class ForwardingExecutorService extends ForwardingObject implements ExecutorService {
   protected ForwardingExecutorService() {
   }

   protected abstract ExecutorService delegate();

   @CheckReturnValue
   public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
      return this.delegate().awaitTermination(timeout, unit);
   }

   public List invokeAll(Collection tasks) throws InterruptedException {
      return this.delegate().invokeAll(tasks);
   }

   public List invokeAll(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException {
      return this.delegate().invokeAll(tasks, timeout, unit);
   }

   public Object invokeAny(Collection tasks) throws InterruptedException, ExecutionException {
      return this.delegate().invokeAny(tasks);
   }

   public Object invokeAny(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      return this.delegate().invokeAny(tasks, timeout, unit);
   }

   public boolean isShutdown() {
      return this.delegate().isShutdown();
   }

   public boolean isTerminated() {
      return this.delegate().isTerminated();
   }

   public void shutdown() {
      this.delegate().shutdown();
   }

   @CanIgnoreReturnValue
   public List shutdownNow() {
      return this.delegate().shutdownNow();
   }

   public void execute(Runnable command) {
      this.delegate().execute(command);
   }

   public Future submit(Callable task) {
      return this.delegate().submit(task);
   }

   public Future submit(Runnable task) {
      return this.delegate().submit(task);
   }

   public Future submit(Runnable task, @ParametricNullness Object result) {
      return this.delegate().submit(task, result);
   }
}

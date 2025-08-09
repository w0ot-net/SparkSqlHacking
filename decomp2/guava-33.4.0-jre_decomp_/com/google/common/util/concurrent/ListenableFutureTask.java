package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public class ListenableFutureTask extends FutureTask implements ListenableFuture {
   private final ExecutionList executionList = new ExecutionList();

   public static ListenableFutureTask create(Callable callable) {
      return new ListenableFutureTask(callable);
   }

   public static ListenableFutureTask create(Runnable runnable, @ParametricNullness Object result) {
      return new ListenableFutureTask(runnable, result);
   }

   ListenableFutureTask(Callable callable) {
      super(callable);
   }

   ListenableFutureTask(Runnable runnable, @ParametricNullness Object result) {
      super(runnable, result);
   }

   public void addListener(Runnable listener, Executor exec) {
      this.executionList.add(listener, exec);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object get(long timeout, TimeUnit unit) throws TimeoutException, InterruptedException, ExecutionException {
      long timeoutNanos = unit.toNanos(timeout);
      return timeoutNanos <= 2147483647999999999L ? super.get(timeout, unit) : super.get(Math.min(timeoutNanos, 2147483647999999999L), TimeUnit.NANOSECONDS);
   }

   protected void done() {
      this.executionList.execute();
   }
}

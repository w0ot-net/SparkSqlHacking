package org.apache.commons.lang3.concurrent;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class AbstractFutureProxy implements Future {
   private final Future future;

   public AbstractFutureProxy(Future future) {
      this.future = (Future)Objects.requireNonNull(future, "future");
   }

   public boolean cancel(boolean mayInterruptIfRunning) {
      return this.future.cancel(mayInterruptIfRunning);
   }

   public Object get() throws InterruptedException, ExecutionException {
      return this.future.get();
   }

   public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      return this.future.get(timeout, unit);
   }

   public Future getFuture() {
      return this.future;
   }

   public boolean isCancelled() {
      return this.future.isCancelled();
   }

   public boolean isDone() {
      return this.future.isDone();
   }
}

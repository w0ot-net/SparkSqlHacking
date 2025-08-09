package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ForwardingObject;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingFuture extends ForwardingObject implements Future {
   protected ForwardingFuture() {
   }

   protected abstract Future delegate();

   @CanIgnoreReturnValue
   public boolean cancel(boolean mayInterruptIfRunning) {
      return this.delegate().cancel(mayInterruptIfRunning);
   }

   public boolean isCancelled() {
      return this.delegate().isCancelled();
   }

   public boolean isDone() {
      return this.delegate().isDone();
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object get() throws InterruptedException, ExecutionException {
      return this.delegate().get();
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      return this.delegate().get(timeout, unit);
   }

   public abstract static class SimpleForwardingFuture extends ForwardingFuture {
      private final Future delegate;

      protected SimpleForwardingFuture(Future delegate) {
         this.delegate = (Future)Preconditions.checkNotNull(delegate);
      }

      protected final Future delegate() {
         return this.delegate;
      }
   }
}

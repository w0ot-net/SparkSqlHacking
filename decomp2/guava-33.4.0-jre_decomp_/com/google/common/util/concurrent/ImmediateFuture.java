package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
class ImmediateFuture implements ListenableFuture {
   static final ListenableFuture NULL = new ImmediateFuture((Object)null);
   private static final LazyLogger log = new LazyLogger(ImmediateFuture.class);
   @ParametricNullness
   private final Object value;

   ImmediateFuture(@ParametricNullness Object value) {
      this.value = value;
   }

   public void addListener(Runnable listener, Executor executor) {
      Preconditions.checkNotNull(listener, "Runnable was null.");
      Preconditions.checkNotNull(executor, "Executor was null.");

      try {
         executor.execute(listener);
      } catch (Exception e) {
         log.get().log(Level.SEVERE, "RuntimeException while executing runnable " + listener + " with executor " + executor, e);
      }

   }

   public boolean cancel(boolean mayInterruptIfRunning) {
      return false;
   }

   @ParametricNullness
   public Object get() {
      return this.value;
   }

   @ParametricNullness
   public Object get(long timeout, TimeUnit unit) throws ExecutionException {
      Preconditions.checkNotNull(unit);
      return this.get();
   }

   public boolean isCancelled() {
      return false;
   }

   public boolean isDone() {
      return true;
   }

   public String toString() {
      return super.toString() + "[status=SUCCESS, result=[" + this.value + "]]";
   }

   static final class ImmediateFailedFuture extends AbstractFuture.TrustedFuture {
      ImmediateFailedFuture(Throwable thrown) {
         this.setException(thrown);
      }
   }

   static final class ImmediateCancelledFuture extends AbstractFuture.TrustedFuture {
      @CheckForNull
      static final ImmediateCancelledFuture INSTANCE;

      ImmediateCancelledFuture() {
         this.cancel(false);
      }

      static {
         INSTANCE = AbstractFuture.GENERATE_CANCELLATION_CAUSES ? null : new ImmediateCancelledFuture();
      }
   }
}

package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingListenableFuture extends ForwardingFuture implements ListenableFuture {
   protected ForwardingListenableFuture() {
   }

   protected abstract ListenableFuture delegate();

   public void addListener(Runnable listener, Executor exec) {
      this.delegate().addListener(listener, exec);
   }

   public abstract static class SimpleForwardingListenableFuture extends ForwardingListenableFuture {
      private final ListenableFuture delegate;

      protected SimpleForwardingListenableFuture(ListenableFuture delegate) {
         this.delegate = (ListenableFuture)Preconditions.checkNotNull(delegate);
      }

      protected final ListenableFuture delegate() {
         return this.delegate;
      }
   }
}

package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public abstract class ForwardingListeningExecutorService extends ForwardingExecutorService implements ListeningExecutorService {
   protected ForwardingListeningExecutorService() {
   }

   protected abstract ListeningExecutorService delegate();

   public ListenableFuture submit(Callable task) {
      return this.delegate().submit(task);
   }

   public ListenableFuture submit(Runnable task) {
      return this.delegate().submit(task);
   }

   public ListenableFuture submit(Runnable task, @ParametricNullness Object result) {
      return this.delegate().submit(task, result);
   }
}

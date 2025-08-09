package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.concurrent.Callable;

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

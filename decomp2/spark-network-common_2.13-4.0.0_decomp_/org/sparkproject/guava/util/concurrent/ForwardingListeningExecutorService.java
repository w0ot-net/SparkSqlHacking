package org.sparkproject.guava.util.concurrent;

import java.util.concurrent.Callable;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

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

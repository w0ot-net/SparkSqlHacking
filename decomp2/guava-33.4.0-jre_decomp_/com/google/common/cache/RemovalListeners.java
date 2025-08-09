package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class RemovalListeners {
   private RemovalListeners() {
   }

   public static RemovalListener asynchronous(RemovalListener listener, Executor executor) {
      Preconditions.checkNotNull(listener);
      Preconditions.checkNotNull(executor);
      return (notification) -> executor.execute(() -> listener.onRemoval(notification));
   }
}

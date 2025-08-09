package org.sparkproject.guava.cache;

import java.util.concurrent.Executor;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.base.Preconditions;

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

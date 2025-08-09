package org.apache.curator.shaded.com.google.common.cache;

import java.util.concurrent.Executor;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

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

package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface ListeningScheduledExecutorService extends ScheduledExecutorService, ListeningExecutorService {
   ListenableScheduledFuture schedule(Runnable command, long delay, TimeUnit unit);

   default ListenableScheduledFuture schedule(Runnable command, Duration delay) {
      return this.schedule(command, Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
   }

   ListenableScheduledFuture schedule(Callable callable, long delay, TimeUnit unit);

   default ListenableScheduledFuture schedule(Callable callable, Duration delay) {
      return this.schedule(callable, Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
   }

   ListenableScheduledFuture scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);

   default ListenableScheduledFuture scheduleAtFixedRate(Runnable command, Duration initialDelay, Duration period) {
      return this.scheduleAtFixedRate(command, Internal.toNanosSaturated(initialDelay), Internal.toNanosSaturated(period), TimeUnit.NANOSECONDS);
   }

   ListenableScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit);

   default ListenableScheduledFuture scheduleWithFixedDelay(Runnable command, Duration initialDelay, Duration delay) {
      return this.scheduleWithFixedDelay(command, Internal.toNanosSaturated(initialDelay), Internal.toNanosSaturated(delay), TimeUnit.NANOSECONDS);
   }
}

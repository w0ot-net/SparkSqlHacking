package org.apache.curator.utils;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

public class CloseableScheduledExecutorService extends CloseableExecutorService {
   private final ScheduledExecutorService scheduledExecutorService;

   public CloseableScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
      super(scheduledExecutorService, false);
      this.scheduledExecutorService = scheduledExecutorService;
   }

   public CloseableScheduledExecutorService(ScheduledExecutorService scheduledExecutorService, boolean shutdownOnClose) {
      super(scheduledExecutorService, shutdownOnClose);
      this.scheduledExecutorService = scheduledExecutorService;
   }

   public Future schedule(Runnable task, long delay, TimeUnit unit) {
      Preconditions.checkState(this.isOpen.get(), "CloseableExecutorService is closed");
      CloseableExecutorService.InternalFutureTask<Void> futureTask = new CloseableExecutorService.InternalFutureTask(new FutureTask(task, (Object)null));
      this.scheduledExecutorService.schedule(futureTask, delay, unit);
      return futureTask;
   }

   public Future scheduleWithFixedDelay(Runnable task, long initialDelay, long delay, TimeUnit unit) {
      Preconditions.checkState(this.isOpen.get(), "CloseableExecutorService is closed");
      ScheduledFuture<?> scheduledFuture = this.scheduledExecutorService.scheduleWithFixedDelay(task, initialDelay, delay, unit);
      return new CloseableExecutorService.InternalScheduledFutureTask(scheduledFuture);
   }
}

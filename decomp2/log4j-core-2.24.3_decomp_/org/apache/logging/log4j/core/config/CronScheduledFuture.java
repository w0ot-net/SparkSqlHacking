package org.apache.logging.log4j.core.config;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CronScheduledFuture implements ScheduledFuture {
   private volatile FutureData futureData;

   public CronScheduledFuture(final ScheduledFuture future, final Date runDate) {
      this.futureData = new FutureData(future, runDate);
   }

   public Date getFireTime() {
      return this.futureData.runDate;
   }

   void reset(final ScheduledFuture future, final Date runDate) {
      this.futureData = new FutureData(future, runDate);
   }

   public long getDelay(final TimeUnit unit) {
      return this.futureData.scheduledFuture.getDelay(unit);
   }

   public int compareTo(final Delayed delayed) {
      return this.futureData.scheduledFuture.compareTo(delayed);
   }

   public boolean cancel(final boolean mayInterruptIfRunning) {
      return this.futureData.scheduledFuture.cancel(mayInterruptIfRunning);
   }

   public boolean isCancelled() {
      return this.futureData.scheduledFuture.isCancelled();
   }

   public boolean isDone() {
      return this.futureData.scheduledFuture.isDone();
   }

   public Object get() throws InterruptedException, ExecutionException {
      return this.futureData.scheduledFuture.get();
   }

   public Object get(final long timeout, final TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      return this.futureData.scheduledFuture.get(timeout, unit);
   }

   private class FutureData {
      private final ScheduledFuture scheduledFuture;
      private final Date runDate;

      FutureData(final ScheduledFuture future, final Date runDate) {
         this.scheduledFuture = future;
         this.runDate = runDate;
      }
   }
}

package io.fabric8.kubernetes.client.utils;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CachedSingleThreadScheduler {
   public static final long DEFAULT_TTL_MILLIS;
   private final long ttlMillis;
   private ScheduledThreadPoolExecutor executor;

   public CachedSingleThreadScheduler() {
      this(DEFAULT_TTL_MILLIS);
   }

   public CachedSingleThreadScheduler(long ttlMillis) {
      this.ttlMillis = ttlMillis;
   }

   public synchronized ScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
      this.startExecutor();
      return this.executor.scheduleWithFixedDelay(command, initialDelay, delay, unit);
   }

   public synchronized ScheduledFuture schedule(Runnable command, long delay, TimeUnit unit) {
      this.startExecutor();
      return this.executor.schedule(command, delay, unit);
   }

   private void startExecutor() {
      if (this.executor == null) {
         this.executor = new ScheduledThreadPoolExecutor(1, Utils.daemonThreadFactory((Object)this));
         this.executor.setRemoveOnCancelPolicy(true);
         this.executor.scheduleWithFixedDelay(this::shutdownCheck, this.ttlMillis, this.ttlMillis, TimeUnit.MILLISECONDS);
      }

   }

   private synchronized void shutdownCheck() {
      if (this.executor.getQueue().isEmpty()) {
         this.executor.shutdownNow();
         this.executor = null;
      }

   }

   synchronized boolean hasExecutor() {
      return this.executor != null;
   }

   static {
      DEFAULT_TTL_MILLIS = TimeUnit.SECONDS.toMillis(10L);
   }
}

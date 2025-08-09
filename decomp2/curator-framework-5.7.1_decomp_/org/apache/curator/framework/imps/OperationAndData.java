package org.apache.curator.framework.imps;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;

class OperationAndData implements Delayed, RetrySleeper {
   private static final AtomicLong nextOrdinal = new AtomicLong();
   private final BackgroundOperation operation;
   private final Object data;
   private final BackgroundCallback callback;
   private final long startTimeMs;
   private final ErrorCallback errorCallback;
   private final AtomicInteger retryCount;
   private final AtomicLong sleepUntilTimeMs;
   private final AtomicLong ordinal;
   private final Object context;
   private final boolean connectionRequired;

   OperationAndData(BackgroundOperation operation, OperationAndData main) {
      this.startTimeMs = System.currentTimeMillis();
      this.sleepUntilTimeMs = new AtomicLong(0L);
      this.ordinal = new AtomicLong();
      this.operation = operation;
      this.data = main.data;
      this.callback = main.callback;
      this.errorCallback = main.errorCallback;
      this.context = main.context;
      this.connectionRequired = main.connectionRequired;
      this.retryCount = main.retryCount;
   }

   OperationAndData(BackgroundOperation operation, Object data, BackgroundCallback callback, ErrorCallback errorCallback, Object context, boolean connectionRequired) {
      this.startTimeMs = System.currentTimeMillis();
      this.sleepUntilTimeMs = new AtomicLong(0L);
      this.ordinal = new AtomicLong();
      this.operation = operation;
      this.data = data;
      this.callback = callback;
      this.errorCallback = errorCallback;
      this.context = context;
      this.connectionRequired = connectionRequired;
      this.retryCount = new AtomicInteger(0);
      this.reset();
   }

   void reset() {
      this.retryCount.set(0);
      this.ordinal.set(nextOrdinal.getAndIncrement());
   }

   OperationAndData(BackgroundOperation operation, Object data, BackgroundCallback callback, ErrorCallback errorCallback, Object context, Watching watching) {
      this(operation, data, callback, errorCallback, context, true);
   }

   Object getContext() {
      return this.context;
   }

   boolean isConnectionRequired() {
      return this.connectionRequired;
   }

   void callPerformBackgroundOperation() throws Exception {
      this.operation.performBackgroundOperation(this);
   }

   Object getData() {
      return this.data;
   }

   long getElapsedTimeMs() {
      return System.currentTimeMillis() - this.startTimeMs;
   }

   int getThenIncrementRetryCount() {
      return this.retryCount.getAndIncrement();
   }

   BackgroundCallback getCallback() {
      return this.callback;
   }

   ErrorCallback getErrorCallback() {
      return this.errorCallback;
   }

   @VisibleForTesting
   BackgroundOperation getOperation() {
      return this.operation;
   }

   CuratorEventType getEventType() {
      return this.operation.getBackgroundEventType();
   }

   void clearSleep() {
      this.sleepUntilTimeMs.set(0L);
   }

   public void sleepFor(long time, TimeUnit unit) throws InterruptedException {
      this.sleepUntilTimeMs.set(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(time, unit));
   }

   public long getDelay(TimeUnit unit) {
      return unit.convert(this.sleepUntilTimeMs.get() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
   }

   public int compareTo(Delayed o) {
      if (o == this) {
         return 0;
      } else {
         long diff = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
         if (diff == 0L && o instanceof OperationAndData) {
            diff = this.ordinal.get() - ((OperationAndData)o).ordinal.get();
         }

         return diff < 0L ? -1 : (diff > 0L ? 1 : 0);
      }
   }

   interface ErrorCallback {
      void retriesExhausted(OperationAndData var1);
   }
}

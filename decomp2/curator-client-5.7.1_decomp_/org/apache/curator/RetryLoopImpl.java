package org.apache.curator;

import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.drivers.EventTrace;
import org.apache.curator.drivers.TracerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RetryLoopImpl extends RetryLoop {
   private boolean isDone = false;
   private int retryCount = 0;
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final long startTimeMs = System.currentTimeMillis();
   private final RetryPolicy retryPolicy;
   private final AtomicReference tracer;
   private static final RetrySleeper sleeper = (time, unit) -> unit.sleep(time);

   RetryLoopImpl(RetryPolicy retryPolicy, AtomicReference tracer) {
      this.retryPolicy = retryPolicy;
      this.tracer = tracer;
   }

   static RetrySleeper getRetrySleeper() {
      return sleeper;
   }

   public boolean shouldContinue() {
      return !this.isDone;
   }

   public void markComplete() {
      this.isDone = true;
   }

   public void takeException(Exception exception) throws Exception {
      boolean rethrow = true;
      if (this.retryPolicy.allowRetry(exception)) {
         if (!Boolean.getBoolean("curator-dont-log-connection-problems")) {
            this.log.debug("Retry-able exception received", exception);
         }

         if (this.retryPolicy.allowRetry(this.retryCount++, System.currentTimeMillis() - this.startTimeMs, sleeper)) {
            (new EventTrace("retries-allowed", (TracerDriver)this.tracer.get())).commit();
            if (!Boolean.getBoolean("curator-dont-log-connection-problems")) {
               this.log.debug("Retrying operation");
            }

            rethrow = false;
         } else {
            (new EventTrace("retries-disallowed", (TracerDriver)this.tracer.get())).commit();
            if (!Boolean.getBoolean("curator-dont-log-connection-problems")) {
               this.log.debug("Retry policy not allowing retry");
            }
         }
      }

      if (rethrow) {
         throw exception;
      }
   }
}

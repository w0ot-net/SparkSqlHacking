package org.sparkproject.jetty.client;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.io.CyclicTimeout;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.thread.Scheduler;

/** @deprecated */
@Deprecated
public class TimeoutCompleteListener extends CyclicTimeout implements Response.CompleteListener {
   private static final Logger LOG = LoggerFactory.getLogger(TimeoutCompleteListener.class);
   private final AtomicReference requestTimeout = new AtomicReference();

   public TimeoutCompleteListener(Scheduler scheduler) {
      super(scheduler);
   }

   public void onTimeoutExpired() {
      Request request = (Request)this.requestTimeout.getAndSet((Object)null);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Total timeout {} ms elapsed for {} on {}", new Object[]{request.getTimeout(), request, this});
      }

      if (request != null) {
         request.abort(new TimeoutException("Total timeout " + request.getTimeout() + " ms elapsed"));
      }

   }

   public void onComplete(Result result) {
      Request request = (Request)this.requestTimeout.getAndSet((Object)null);
      if (request != null) {
         boolean cancelled = this.cancel();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Cancelled ({}) timeout for {} on {}", new Object[]{cancelled, request, this});
         }
      }

   }

   void schedule(HttpRequest request, long timeoutAt) {
      if (this.requestTimeout.compareAndSet((Object)null, request)) {
         long delay = Math.max(0L, NanoTime.until(timeoutAt));
         if (LOG.isDebugEnabled()) {
            LOG.debug("Scheduling timeout in {} ms for {} on {}", new Object[]{TimeUnit.NANOSECONDS.toMillis(delay), request, this});
         }

         this.schedule(delay, TimeUnit.NANOSECONDS);
      }

   }
}

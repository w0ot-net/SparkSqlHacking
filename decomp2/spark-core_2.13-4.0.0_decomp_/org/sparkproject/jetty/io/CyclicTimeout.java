package org.sparkproject.jetty.io;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.component.Destroyable;
import org.sparkproject.jetty.util.thread.Scheduler;

public abstract class CyclicTimeout implements Destroyable {
   private static final Logger LOG = LoggerFactory.getLogger(CyclicTimeout.class);
   private static final Timeout NOT_SET = new Timeout(Long.MAX_VALUE, (Wakeup)null);
   private static final Scheduler.Task DESTROYED = () -> false;
   private final Scheduler _scheduler;
   private final AtomicReference _timeout;

   public CyclicTimeout(Scheduler scheduler) {
      this._timeout = new AtomicReference(NOT_SET);
      this._scheduler = scheduler;
   }

   public Scheduler getScheduler() {
      return this._scheduler;
   }

   public boolean schedule(long delay, TimeUnit units) {
      long now = NanoTime.now();
      long newTimeoutAt = now + units.toNanos(delay);
      Wakeup newWakeup = null;

      boolean result;
      Timeout timeout;
      Wakeup wakeup;
      do {
         timeout = (Timeout)this._timeout.get();
         result = timeout._at != Long.MAX_VALUE;
         wakeup = timeout._wakeup;
         if (wakeup == null || NanoTime.isBefore(newTimeoutAt, wakeup._at)) {
            wakeup = newWakeup = new Wakeup(newTimeoutAt, wakeup);
         }
      } while(!this._timeout.compareAndSet(timeout, new Timeout(newTimeoutAt, wakeup)));

      if (LOG.isDebugEnabled()) {
         LOG.debug("Installed timeout in {} ms, {} wake up in {} ms", new Object[]{units.toMillis(delay), newWakeup != null ? "new" : "existing", NanoTime.millisElapsed(now, wakeup._at)});
      }

      if (newWakeup != null) {
         newWakeup.schedule(now);
      }

      return result;
   }

   public boolean cancel() {
      boolean result;
      Timeout timeout;
      Timeout newTimeout;
      do {
         timeout = (Timeout)this._timeout.get();
         result = timeout._at != Long.MAX_VALUE;
         Wakeup wakeup = timeout._wakeup;
         newTimeout = wakeup == null ? NOT_SET : new Timeout(Long.MAX_VALUE, wakeup);
      } while(!this._timeout.compareAndSet(timeout, newTimeout));

      return result;
   }

   public abstract void onTimeoutExpired();

   public void destroy() {
      Timeout timeout = (Timeout)this._timeout.getAndSet(NOT_SET);

      for(Wakeup wakeup = timeout == null ? null : timeout._wakeup; wakeup != null; wakeup = wakeup._next) {
         wakeup.destroy();
      }

   }

   private static class Timeout {
      private final long _at;
      private final Wakeup _wakeup;

      private Timeout(long timeoutAt, Wakeup wakeup) {
         this._at = timeoutAt;
         this._wakeup = wakeup;
      }

      public String toString() {
         return String.format("%s@%x:%dms,%s", this.getClass().getSimpleName(), this.hashCode(), NanoTime.millisUntil(this._at), this._wakeup);
      }
   }

   private class Wakeup implements Runnable {
      private final AtomicReference _task = new AtomicReference();
      private final long _at;
      private final Wakeup _next;

      private Wakeup(long wakeupAt, Wakeup next) {
         this._at = wakeupAt;
         this._next = next;
      }

      private void schedule(long now) {
         this._task.compareAndSet((Object)null, CyclicTimeout.this._scheduler.schedule(this, NanoTime.elapsed(now, this._at), TimeUnit.NANOSECONDS));
      }

      private void destroy() {
         Scheduler.Task task = (Scheduler.Task)this._task.getAndSet(CyclicTimeout.DESTROYED);
         if (task != null) {
            task.cancel();
         }

      }

      public void run() {
         long now = NanoTime.now();
         Wakeup newWakeup = null;
         boolean hasExpired = false;

         Timeout timeout;
         Timeout newTimeout;
         do {
            timeout = (Timeout)CyclicTimeout.this._timeout.get();

            Wakeup wakeup;
            for(wakeup = timeout._wakeup; wakeup != null && wakeup != this; wakeup = wakeup._next) {
            }

            if (wakeup == null) {
               return;
            }

            wakeup = wakeup._next;
            if (NanoTime.isBeforeOrSame(timeout._at, now)) {
               hasExpired = true;
               newTimeout = wakeup == null ? CyclicTimeout.NOT_SET : new Timeout(Long.MAX_VALUE, wakeup);
            } else if (timeout._at != Long.MAX_VALUE) {
               if (wakeup == null || NanoTime.isBefore(timeout._at, wakeup._at)) {
                  wakeup = newWakeup = CyclicTimeout.this.new Wakeup(timeout._at, wakeup);
               }

               newTimeout = new Timeout(timeout._at, wakeup);
            } else {
               newTimeout = wakeup == null ? CyclicTimeout.NOT_SET : new Timeout(Long.MAX_VALUE, wakeup);
            }
         } while(!CyclicTimeout.this._timeout.compareAndSet(timeout, newTimeout));

         if (newWakeup != null) {
            newWakeup.schedule(now);
         }

         if (hasExpired) {
            CyclicTimeout.this.onTimeoutExpired();
         }

      }

      public String toString() {
         return String.format("%s@%x:%dms->%s", this.getClass().getSimpleName(), this.hashCode(), this._at == Long.MAX_VALUE ? this._at : NanoTime.millisUntil(this._at), this._next);
      }
   }
}

package com.codahale.metrics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public abstract class CachedGauge implements Gauge {
   private final Clock clock;
   private final AtomicLong reloadAt;
   private final long timeoutNS;
   private final AtomicReference value;

   protected CachedGauge(long timeout, TimeUnit timeoutUnit) {
      this(Clock.defaultClock(), timeout, timeoutUnit);
   }

   protected CachedGauge(Clock clock, long timeout, TimeUnit timeoutUnit) {
      this.clock = clock;
      this.reloadAt = new AtomicLong(clock.getTick());
      this.timeoutNS = timeoutUnit.toNanos(timeout);
      this.value = new AtomicReference();
   }

   protected abstract Object loadValue();

   public Object getValue() {
      T currentValue = (T)this.value.get();
      if (!this.shouldLoad() && currentValue != null) {
         return currentValue;
      } else {
         T newValue = (T)this.loadValue();
         return !this.value.compareAndSet(currentValue, newValue) ? this.value.get() : newValue;
      }
   }

   private boolean shouldLoad() {
      long time;
      long current;
      do {
         time = this.clock.getTick();
         current = this.reloadAt.get();
         if (current > time) {
            return false;
         }
      } while(!this.reloadAt.compareAndSet(current, time + this.timeoutNS));

      return true;
   }
}

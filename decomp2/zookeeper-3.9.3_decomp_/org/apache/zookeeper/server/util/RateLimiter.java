package org.apache.zookeeper.server.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.zookeeper.common.Time;

public class RateLimiter {
   private final int rate;
   private final long intervalInMs;
   private long lastTimeReset;
   private final AtomicInteger remained;

   public RateLimiter(int rate, long interval, TimeUnit unit) {
      this.rate = rate;
      this.intervalInMs = unit.toMillis(interval);
      this.lastTimeReset = Time.currentElapsedTime();
      this.remained = new AtomicInteger(rate);
   }

   public boolean allow() {
      long now = Time.currentElapsedTime();
      if (now > this.lastTimeReset + this.intervalInMs) {
         this.remained.set(this.rate);
         this.lastTimeReset = now;
      }

      int value = this.remained.get();

      boolean allowed;
      for(allowed = false; !allowed && value > 0; value = this.remained.get()) {
         allowed = this.remained.compareAndSet(value, value - 1);
      }

      return allowed;
   }
}

package com.codahale.metrics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ExponentialMovingAverages implements MovingAverages {
   private static final double maxTickZeroTarget = 1.0E-4;
   private static final int maxTicks;
   private static final long TICK_INTERVAL;
   private final EWMA m1Rate;
   private final EWMA m5Rate;
   private final EWMA m15Rate;
   private final AtomicLong lastTick;
   private final Clock clock;

   public ExponentialMovingAverages() {
      this(Clock.defaultClock());
   }

   public ExponentialMovingAverages(Clock clock) {
      this.m1Rate = EWMA.oneMinuteEWMA();
      this.m5Rate = EWMA.fiveMinuteEWMA();
      this.m15Rate = EWMA.fifteenMinuteEWMA();
      this.clock = clock;
      this.lastTick = new AtomicLong(this.clock.getTick());
   }

   public void update(long n) {
      this.m1Rate.update(n);
      this.m5Rate.update(n);
      this.m15Rate.update(n);
   }

   public void tickIfNecessary() {
      long oldTick = this.lastTick.get();
      long newTick = this.clock.getTick();
      long age = newTick - oldTick;
      if (age > TICK_INTERVAL) {
         long newIntervalStartTick = newTick - age % TICK_INTERVAL;
         if (this.lastTick.compareAndSet(oldTick, newIntervalStartTick)) {
            long requiredTicks = age / TICK_INTERVAL;
            if (requiredTicks >= (long)maxTicks) {
               this.m1Rate.reset();
               this.m5Rate.reset();
               this.m15Rate.reset();
            } else {
               for(long i = 0L; i < requiredTicks; ++i) {
                  this.m1Rate.tick();
                  this.m5Rate.tick();
                  this.m15Rate.tick();
               }
            }
         }
      }

   }

   public double getM1Rate() {
      return this.m1Rate.getRate(TimeUnit.SECONDS);
   }

   public double getM5Rate() {
      return this.m5Rate.getRate(TimeUnit.SECONDS);
   }

   public double getM15Rate() {
      return this.m15Rate.getRate(TimeUnit.SECONDS);
   }

   static {
      TICK_INTERVAL = TimeUnit.SECONDS.toNanos(5L);
      int m3Ticks = 1;
      EWMA m3 = EWMA.fifteenMinuteEWMA();
      m3.update(Long.MAX_VALUE);

      do {
         m3.tick();
         ++m3Ticks;
      } while(m3.getRate(TimeUnit.SECONDS) > 1.0E-4);

      maxTicks = m3Ticks;
   }
}

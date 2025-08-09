package com.codahale.metrics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class Meter implements Metered {
   private final MovingAverages movingAverages;
   private final LongAdder count;
   private final long startTime;
   private final Clock clock;

   public Meter(MovingAverages movingAverages) {
      this(movingAverages, Clock.defaultClock());
   }

   public Meter() {
      this(Clock.defaultClock());
   }

   public Meter(Clock clock) {
      this(new ExponentialMovingAverages(clock), clock);
   }

   public Meter(MovingAverages movingAverages, Clock clock) {
      this.count = new LongAdder();
      this.movingAverages = movingAverages;
      this.clock = clock;
      this.startTime = this.clock.getTick();
   }

   public void mark() {
      this.mark(1L);
   }

   public void mark(long n) {
      this.movingAverages.tickIfNecessary();
      this.count.add(n);
      this.movingAverages.update(n);
   }

   public long getCount() {
      return this.count.sum();
   }

   public double getFifteenMinuteRate() {
      this.movingAverages.tickIfNecessary();
      return this.movingAverages.getM15Rate();
   }

   public double getFiveMinuteRate() {
      this.movingAverages.tickIfNecessary();
      return this.movingAverages.getM5Rate();
   }

   public double getMeanRate() {
      if (this.getCount() == 0L) {
         return (double)0.0F;
      } else {
         double elapsed = (double)(this.clock.getTick() - this.startTime);
         return (double)this.getCount() / elapsed * (double)TimeUnit.SECONDS.toNanos(1L);
      }
   }

   public double getOneMinuteRate() {
      this.movingAverages.tickIfNecessary();
      return this.movingAverages.getM1Rate();
   }
}

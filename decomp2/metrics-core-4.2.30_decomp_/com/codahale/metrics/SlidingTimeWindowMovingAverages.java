package com.codahale.metrics;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.LongStream;

public class SlidingTimeWindowMovingAverages implements MovingAverages {
   private static final long TIME_WINDOW_DURATION_MINUTES = 15L;
   private static final long TICK_INTERVAL;
   private static final Duration TIME_WINDOW_DURATION;
   static final int NUMBER_OF_BUCKETS;
   private final AtomicLong lastTick;
   private final Clock clock;
   private ArrayList buckets;
   private int oldestBucketIndex;
   private int currentBucketIndex;
   private final Instant bucketBaseTime;
   Instant oldestBucketTime;

   public SlidingTimeWindowMovingAverages() {
      this(Clock.defaultClock());
   }

   public SlidingTimeWindowMovingAverages(Clock clock) {
      this.clock = clock;
      long startTime = clock.getTick();
      this.lastTick = new AtomicLong(startTime);
      this.buckets = new ArrayList(NUMBER_OF_BUCKETS);

      for(int i = 0; i < NUMBER_OF_BUCKETS; ++i) {
         this.buckets.add(new LongAdder());
      }

      this.bucketBaseTime = Instant.ofEpochSecond(0L, startTime);
      this.oldestBucketTime = this.bucketBaseTime;
      this.oldestBucketIndex = 0;
      this.currentBucketIndex = 0;
   }

   public void update(long n) {
      ((LongAdder)this.buckets.get(this.currentBucketIndex)).add(n);
   }

   public void tickIfNecessary() {
      long oldTick = this.lastTick.get();
      long newTick = this.clock.getTick();
      long age = newTick - oldTick;
      if (age >= TICK_INTERVAL) {
         long newLastTick = newTick - age % TICK_INTERVAL;
         if (this.lastTick.compareAndSet(oldTick, newLastTick)) {
            Instant currentInstant = Instant.ofEpochSecond(0L, newLastTick);
            this.currentBucketIndex = this.normalizeIndex(this.calculateIndexOfTick(currentInstant));
            this.cleanOldBuckets(currentInstant);
         }
      }

   }

   public double getM15Rate() {
      return this.getMinuteRate(15);
   }

   public double getM5Rate() {
      return this.getMinuteRate(5);
   }

   public double getM1Rate() {
      return this.getMinuteRate(1);
   }

   private double getMinuteRate(int minutes) {
      Instant now = Instant.ofEpochSecond(0L, this.lastTick.get());
      return (double)this.sumBuckets(now, (int)(TimeUnit.MINUTES.toNanos((long)minutes) / TICK_INTERVAL));
   }

   int calculateIndexOfTick(Instant tickTime) {
      return (int)(Duration.between(this.bucketBaseTime, tickTime).toNanos() / TICK_INTERVAL);
   }

   int normalizeIndex(int index) {
      int mod = index % NUMBER_OF_BUCKETS;
      return mod >= 0 ? mod : mod + NUMBER_OF_BUCKETS;
   }

   private void cleanOldBuckets(Instant currentTick) {
      Instant oldestStillNeededTime = currentTick.minus(TIME_WINDOW_DURATION).plusNanos(TICK_INTERVAL);
      Instant youngestNotInWindow = this.oldestBucketTime.plus(TIME_WINDOW_DURATION);
      int newOldestIndex;
      if (oldestStillNeededTime.isAfter(youngestNotInWindow)) {
         newOldestIndex = this.oldestBucketIndex;
         this.oldestBucketTime = currentTick;
      } else {
         if (!oldestStillNeededTime.isAfter(this.oldestBucketTime)) {
            return;
         }

         newOldestIndex = this.normalizeIndex(this.calculateIndexOfTick(oldestStillNeededTime));
         this.oldestBucketTime = oldestStillNeededTime;
      }

      this.cleanBucketRange(this.oldestBucketIndex, newOldestIndex);
      this.oldestBucketIndex = newOldestIndex;
   }

   private void cleanBucketRange(int fromIndex, int toIndex) {
      if (fromIndex < toIndex) {
         for(int i = fromIndex; i < toIndex; ++i) {
            ((LongAdder)this.buckets.get(i)).reset();
         }
      } else {
         for(int i = fromIndex; i < NUMBER_OF_BUCKETS; ++i) {
            ((LongAdder)this.buckets.get(i)).reset();
         }

         for(int i = 0; i < toIndex; ++i) {
            ((LongAdder)this.buckets.get(i)).reset();
         }
      }

   }

   private long sumBuckets(Instant toTime, int numberOfBuckets) {
      int toIndex = this.normalizeIndex(this.calculateIndexOfTick(toTime) + 1);
      int fromIndex = this.normalizeIndex(toIndex - numberOfBuckets);
      LongAdder adder = new LongAdder();
      if (fromIndex < toIndex) {
         LongStream var10000 = this.buckets.stream().skip((long)fromIndex).limit((long)(toIndex - fromIndex)).mapToLong(LongAdder::longValue);
         Objects.requireNonNull(adder);
         var10000.forEach(adder::add);
      } else {
         LongStream var8 = this.buckets.stream().limit((long)toIndex).mapToLong(LongAdder::longValue);
         Objects.requireNonNull(adder);
         var8.forEach(adder::add);
         var8 = this.buckets.stream().skip((long)fromIndex).mapToLong(LongAdder::longValue);
         Objects.requireNonNull(adder);
         var8.forEach(adder::add);
      }

      long retval = adder.longValue();
      return retval;
   }

   static {
      TICK_INTERVAL = TimeUnit.SECONDS.toNanos(1L);
      TIME_WINDOW_DURATION = Duration.ofMinutes(15L);
      NUMBER_OF_BUCKETS = (int)(TIME_WINDOW_DURATION.toNanos() / TICK_INTERVAL);
   }
}

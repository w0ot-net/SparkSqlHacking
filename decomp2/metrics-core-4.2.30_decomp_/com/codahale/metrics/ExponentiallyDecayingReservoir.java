package com.codahale.metrics;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ExponentiallyDecayingReservoir implements Reservoir {
   private static final int DEFAULT_SIZE = 1028;
   private static final double DEFAULT_ALPHA = 0.015;
   private static final long RESCALE_THRESHOLD;
   private final ConcurrentSkipListMap values;
   private final ReentrantReadWriteLock lock;
   private final double alpha;
   private final int size;
   private final AtomicLong count;
   private volatile long startTime;
   private final AtomicLong lastScaleTick;
   private final Clock clock;

   public ExponentiallyDecayingReservoir() {
      this(1028, 0.015);
   }

   public ExponentiallyDecayingReservoir(int size, double alpha) {
      this(size, alpha, Clock.defaultClock());
   }

   public ExponentiallyDecayingReservoir(int size, double alpha, Clock clock) {
      this.values = new ConcurrentSkipListMap();
      this.lock = new ReentrantReadWriteLock();
      this.alpha = alpha;
      this.size = size;
      this.clock = clock;
      this.count = new AtomicLong(0L);
      this.startTime = this.currentTimeInSeconds();
      this.lastScaleTick = new AtomicLong(clock.getTick());
   }

   public int size() {
      return (int)Math.min((long)this.size, this.count.get());
   }

   public void update(long value) {
      this.update(value, this.currentTimeInSeconds());
   }

   public void update(long value, long timestamp) {
      this.rescaleIfNeeded();
      this.lockForRegularUsage();

      try {
         double itemWeight = this.weight(timestamp - this.startTime);
         WeightedSnapshot.WeightedSample sample = new WeightedSnapshot.WeightedSample(value, itemWeight);
         double priority = itemWeight / ThreadLocalRandom.current().nextDouble();
         long newCount = this.count.incrementAndGet();
         if (newCount > (long)this.size && !this.values.isEmpty()) {
            Double first = (Double)this.values.firstKey();
            if (first < priority && this.values.putIfAbsent(priority, sample) == null) {
               while(this.values.remove(first) == null) {
                  first = (Double)this.values.firstKey();
               }
            }
         } else {
            this.values.put(priority, sample);
         }
      } finally {
         this.unlockForRegularUsage();
      }

   }

   private void rescaleIfNeeded() {
      long now = this.clock.getTick();
      long lastScaleTickSnapshot = this.lastScaleTick.get();
      if (now - lastScaleTickSnapshot >= RESCALE_THRESHOLD) {
         this.rescale(now, lastScaleTickSnapshot);
      }

   }

   public Snapshot getSnapshot() {
      this.rescaleIfNeeded();
      this.lockForRegularUsage();

      WeightedSnapshot var1;
      try {
         var1 = new WeightedSnapshot(this.values.values());
      } finally {
         this.unlockForRegularUsage();
      }

      return var1;
   }

   private long currentTimeInSeconds() {
      return TimeUnit.MILLISECONDS.toSeconds(this.clock.getTime());
   }

   private double weight(long t) {
      return Math.exp(this.alpha * (double)t);
   }

   private void rescale(long now, long lastTick) {
      this.lockForRescale();

      try {
         if (this.lastScaleTick.compareAndSet(lastTick, now)) {
            long oldStartTime = this.startTime;
            this.startTime = this.currentTimeInSeconds();
            double scalingFactor = Math.exp(-this.alpha * (double)(this.startTime - oldStartTime));
            if (Double.compare(scalingFactor, (double)0.0F) == 0) {
               this.values.clear();
            } else {
               for(Double key : new ArrayList(this.values.keySet())) {
                  WeightedSnapshot.WeightedSample sample = (WeightedSnapshot.WeightedSample)this.values.remove(key);
                  WeightedSnapshot.WeightedSample newSample = new WeightedSnapshot.WeightedSample(sample.value, sample.weight * scalingFactor);
                  if (Double.compare(newSample.weight, (double)0.0F) != 0) {
                     this.values.put(key * scalingFactor, newSample);
                  }
               }
            }

            this.count.set((long)this.values.size());
         }
      } finally {
         this.unlockForRescale();
      }

   }

   private void unlockForRescale() {
      this.lock.writeLock().unlock();
   }

   private void lockForRescale() {
      this.lock.writeLock().lock();
   }

   private void lockForRegularUsage() {
      this.lock.readLock().lock();
   }

   private void unlockForRegularUsage() {
      this.lock.readLock().unlock();
   }

   static {
      RESCALE_THRESHOLD = TimeUnit.HOURS.toNanos(1L);
   }
}

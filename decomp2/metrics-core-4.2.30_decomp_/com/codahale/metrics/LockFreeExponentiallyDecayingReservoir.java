package com.codahale.metrics;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.BiConsumer;

public final class LockFreeExponentiallyDecayingReservoir implements Reservoir {
   private static final double SECONDS_PER_NANO = 1.0E-9;
   private static final AtomicReferenceFieldUpdater stateUpdater = AtomicReferenceFieldUpdater.newUpdater(LockFreeExponentiallyDecayingReservoir.class, State.class, "state");
   private final int size;
   private final long rescaleThresholdNanos;
   private final Clock clock;
   private volatile State state;

   private LockFreeExponentiallyDecayingReservoir(int size, double alpha, Duration rescaleThreshold, Clock clock) {
      double alphaNanos = alpha * 1.0E-9;
      this.size = size;
      this.clock = clock;
      this.rescaleThresholdNanos = rescaleThreshold.toNanos();
      this.state = new State(alphaNanos, size, clock.getTick(), 0, new ConcurrentSkipListMap());
   }

   public int size() {
      return Math.min(this.size, this.state.count);
   }

   public void update(long value) {
      long now = this.clock.getTick();
      this.rescaleIfNeeded(now).update(value, now);
   }

   private State rescaleIfNeeded(long currentTick) {
      State stateSnapshot = this.state;
      return currentTick - stateSnapshot.startTick >= this.rescaleThresholdNanos ? this.doRescale(currentTick, stateSnapshot) : stateSnapshot;
   }

   private State doRescale(long currentTick, State stateSnapshot) {
      State newState = stateSnapshot.rescale(currentTick);
      return stateUpdater.compareAndSet(this, stateSnapshot, newState) ? newState : this.state;
   }

   public Snapshot getSnapshot() {
      State stateSnapshot = this.rescaleIfNeeded(this.clock.getTick());
      return new WeightedSnapshot(stateSnapshot.values.values());
   }

   public static Builder builder() {
      return new Builder();
   }

   private static final class State {
      private static final AtomicIntegerFieldUpdater countUpdater = AtomicIntegerFieldUpdater.newUpdater(State.class, "count");
      private final double alphaNanos;
      private final int size;
      private final long startTick;
      private final ConcurrentSkipListMap values;
      private volatile int count;

      State(double alphaNanos, int size, long startTick, int count, ConcurrentSkipListMap values) {
         this.alphaNanos = alphaNanos;
         this.size = size;
         this.startTick = startTick;
         this.values = values;
         this.count = count;
      }

      private void update(long value, long timestampNanos) {
         double itemWeight = this.weight(timestampNanos - this.startTick);
         double priority = itemWeight / ThreadLocalRandom.current().nextDouble();
         boolean mapIsFull = this.count >= this.size;
         if (!mapIsFull || (Double)this.values.firstKey() < priority) {
            this.addSample(priority, value, itemWeight, mapIsFull);
         }

      }

      private void addSample(double priority, long value, double itemWeight, boolean bypassIncrement) {
         if (this.values.putIfAbsent(priority, new WeightedSnapshot.WeightedSample(value, itemWeight)) == null && (bypassIncrement || countUpdater.incrementAndGet(this) > this.size)) {
            this.values.pollFirstEntry();
         }

      }

      State rescale(long newTick) {
         long durationNanos = newTick - this.startTick;
         double scalingFactor = Math.exp(-this.alphaNanos * (double)durationNanos);
         int newCount = 0;
         ConcurrentSkipListMap<Double, WeightedSnapshot.WeightedSample> newValues = new ConcurrentSkipListMap();
         if (Double.compare(scalingFactor, (double)0.0F) != 0) {
            RescalingConsumer consumer = new RescalingConsumer(scalingFactor, newValues);
            this.values.forEach(consumer);
            newCount = consumer.count;
         }

         while(newCount > this.size) {
            Objects.requireNonNull(newValues.pollFirstEntry(), "Expected an entry");
            --newCount;
         }

         return new State(this.alphaNanos, this.size, newTick, newCount, newValues);
      }

      private double weight(long durationNanos) {
         return Math.exp(this.alphaNanos * (double)durationNanos);
      }
   }

   private static final class RescalingConsumer implements BiConsumer {
      private final double scalingFactor;
      private final ConcurrentSkipListMap values;
      private int count;

      RescalingConsumer(double scalingFactor, ConcurrentSkipListMap values) {
         this.scalingFactor = scalingFactor;
         this.values = values;
      }

      public void accept(Double priority, WeightedSnapshot.WeightedSample sample) {
         double newWeight = sample.weight * this.scalingFactor;
         if (Double.compare(newWeight, (double)0.0F) != 0) {
            WeightedSnapshot.WeightedSample newSample = new WeightedSnapshot.WeightedSample(sample.value, newWeight);
            if (this.values.put(priority * this.scalingFactor, newSample) == null) {
               ++this.count;
            }

         }
      }
   }

   public static final class Builder {
      private static final int DEFAULT_SIZE = 1028;
      private static final double DEFAULT_ALPHA = 0.015;
      private static final Duration DEFAULT_RESCALE_THRESHOLD = Duration.ofHours(1L);
      private int size;
      private double alpha;
      private Duration rescaleThreshold;
      private Clock clock;

      private Builder() {
         this.size = 1028;
         this.alpha = 0.015;
         this.rescaleThreshold = DEFAULT_RESCALE_THRESHOLD;
         this.clock = Clock.defaultClock();
      }

      public Builder size(int value) {
         if (value <= 0) {
            throw new IllegalArgumentException("LockFreeExponentiallyDecayingReservoir size must be positive: " + value);
         } else {
            this.size = value;
            return this;
         }
      }

      public Builder alpha(double value) {
         this.alpha = value;
         return this;
      }

      public Builder rescaleThreshold(Duration value) {
         this.rescaleThreshold = (Duration)Objects.requireNonNull(value, "rescaleThreshold is required");
         return this;
      }

      public Builder clock(Clock value) {
         this.clock = (Clock)Objects.requireNonNull(value, "clock is required");
         return this;
      }

      public Reservoir build() {
         return new LockFreeExponentiallyDecayingReservoir(this.size, this.alpha, this.rescaleThreshold, this.clock);
      }
   }
}

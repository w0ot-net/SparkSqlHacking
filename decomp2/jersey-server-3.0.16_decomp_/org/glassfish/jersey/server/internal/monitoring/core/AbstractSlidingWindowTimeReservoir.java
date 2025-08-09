package org.glassfish.jersey.server.internal.monitoring.core;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractSlidingWindowTimeReservoir implements TimeReservoir {
   private final ConcurrentNavigableMap measurements;
   private final long window;
   private final AtomicLong greatestTick;
   private final AtomicLong updateCount;
   private final AtomicLong startTick;
   private final AtomicInteger trimOff;
   private final SlidingWindowTrimmer trimmer;
   private final long interval;
   private final TimeUnit intervalUnit;

   public AbstractSlidingWindowTimeReservoir(long window, TimeUnit windowUnit, long startTime, TimeUnit startTimeUnit) {
      this(window, windowUnit, startTime, startTimeUnit, (SlidingWindowTrimmer)null);
   }

   public AbstractSlidingWindowTimeReservoir(long window, TimeUnit windowUnit, long startTime, TimeUnit startTimeUnit, SlidingWindowTrimmer trimmer) {
      this.trimmer = trimmer != null ? trimmer : AbstractSlidingWindowTimeReservoir.DefaultSlidingWindowTrimmerHolder.INSTANCE;
      this.measurements = new ConcurrentSkipListMap();
      this.interval = window;
      this.intervalUnit = windowUnit;
      this.window = windowUnit.toNanos(window) << ReservoirConstants.COLLISION_BUFFER_POWER;
      this.startTick = new AtomicLong(this.tick(startTime, startTimeUnit));
      this.greatestTick = new AtomicLong(this.startTick.get());
      this.updateCount = new AtomicLong(0L);
      this.trimOff = new AtomicInteger(0);
      this.trimmer.setTimeReservoir(this);
   }

   public int size(long time, TimeUnit timeUnit) {
      this.conditionallyUpdateGreatestTick(this.tick(time, timeUnit));
      this.trim();
      return this.measurements.size();
   }

   public void update(Object value, long time, TimeUnit timeUnit) {
      if (this.updateCount.incrementAndGet() % 256L == 0L) {
         this.trim();
      }

      long tick = this.tick(time, timeUnit);

      for(int i = 0; i < ReservoirConstants.COLLISION_BUFFER; ++i) {
         if (this.measurements.putIfAbsent(tick, value) == null) {
            this.conditionallyUpdateGreatestTick(tick);
            return;
         }

         ++tick;
      }

   }

   public long interval(TimeUnit timeUnit) {
      return timeUnit.convert(this.interval, this.intervalUnit);
   }

   private long conditionallyUpdateGreatestTick(long tick) {
      long currentGreatestTick;
      do {
         currentGreatestTick = this.greatestTick.get();
         if (tick <= currentGreatestTick) {
            return currentGreatestTick;
         }
      } while(!this.greatestTick.compareAndSet(currentGreatestTick, tick));

      return tick;
   }

   private void conditionallyUpdateStartTick(Map.Entry firstEntry) {
      Long firstEntryKey = firstEntry != null ? (Long)firstEntry.getKey() : null;
      if (firstEntryKey != null && firstEntryKey < this.startTick.get()) {
         long expectedStartTick;
         do {
            expectedStartTick = this.startTick.get();
         } while(!this.startTick.compareAndSet(expectedStartTick, firstEntryKey));

      }
   }

   protected abstract UniformTimeSnapshot snapshot(Collection var1, long var2, TimeUnit var4, long var5, TimeUnit var7);

   public UniformTimeSnapshot getSnapshot(long time, TimeUnit timeUnit) {
      this.trimOff.incrementAndGet();
      long baselineTick = this.conditionallyUpdateGreatestTick(this.tick(time, timeUnit));

      UniformTimeSnapshot var9;
      try {
         ConcurrentNavigableMap<Long, V> windowMap = this.measurements.subMap(this.roundTick(baselineTick) - this.window, true, baselineTick, true);
         this.conditionallyUpdateStartTick(windowMap.firstEntry());
         long measuredTickInterval = Math.min(baselineTick - this.startTick.get(), this.window);
         var9 = this.snapshot(windowMap.values(), measuredTickInterval >> ReservoirConstants.COLLISION_BUFFER_POWER, TimeUnit.NANOSECONDS, time, timeUnit);
      } finally {
         this.trimOff.decrementAndGet();
         this.trim(baselineTick);
      }

      return var9;
   }

   private long tick(long time, TimeUnit timeUnit) {
      return timeUnit.toNanos(time) << ReservoirConstants.COLLISION_BUFFER_POWER;
   }

   private void trim() {
      this.trim(this.greatestTick.get());
   }

   private void trim(long baselineTick) {
      if (this.trimEnabled()) {
         long key = this.roundTick(baselineTick) - this.window;
         this.trimmer.trim(this.measurements, key);
      }

   }

   private boolean trimEnabled() {
      return this.trimOff.get() == 0;
   }

   private long roundTick(long tick) {
      return tick >> ReservoirConstants.COLLISION_BUFFER_POWER << ReservoirConstants.COLLISION_BUFFER_POWER;
   }

   private static final class DefaultSlidingWindowTrimmerHolder {
      static final SlidingWindowTrimmer INSTANCE = new SlidingWindowTrimmer() {
         public void trim(ConcurrentNavigableMap map, long key) {
            map.headMap(key).clear();
         }

         public void setTimeReservoir(TimeReservoir reservoir) {
         }
      };
   }
}

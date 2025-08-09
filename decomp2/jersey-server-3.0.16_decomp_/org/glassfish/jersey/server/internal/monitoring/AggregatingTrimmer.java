package org.glassfish.jersey.server.internal.monitoring;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.glassfish.jersey.internal.guava.TreeMultimap;
import org.glassfish.jersey.server.internal.monitoring.core.ReservoirConstants;
import org.glassfish.jersey.server.internal.monitoring.core.SlidingWindowTrimmer;
import org.glassfish.jersey.server.internal.monitoring.core.TimeReservoir;

class AggregatingTrimmer implements SlidingWindowTrimmer {
   private final List aggregatedReservoirListeners = new CopyOnWriteArrayList();
   private TimeReservoir timeReservoirNotifier;
   private final long startTime;
   private final TimeUnit startUnitTime;
   private final long chunkSize;
   private final AtomicBoolean locked = new AtomicBoolean(false);

   public AggregatingTrimmer(long startTime, TimeUnit startUnitTime, long chunkTimeSize, TimeUnit chunkTimeSizeUnit) {
      this.startTime = startTime;
      this.startUnitTime = startUnitTime;
      this.chunkSize = TimeUnit.NANOSECONDS.convert(chunkTimeSize, chunkTimeSizeUnit) << ReservoirConstants.COLLISION_BUFFER_POWER;
   }

   public void trim(ConcurrentNavigableMap map, long key) {
      if (this.locked.compareAndSet(false, true)) {
         TreeMultimap<Long, Long> trimMultiMap = TreeMultimap.create();
         NavigableMap<Long, Collection<Long>> trimMap = trimMultiMap.asMap();

         try {
            ConcurrentNavigableMap<Long, Long> headMap = map.headMap(key);

            while(!headMap.isEmpty()) {
               Map.Entry<Long, Long> entry = headMap.pollFirstEntry();
               trimMultiMap.put(entry.getKey(), entry.getValue());
            }
         } finally {
            this.locked.set(false);
         }

         for(Map.Entry firstEntry = trimMap.firstEntry(); firstEntry != null; firstEntry = trimMap.firstEntry()) {
            long chunkLowerBound = this.lowerBound((Long)firstEntry.getKey());
            long chunkUpperBound = this.upperBound(chunkLowerBound, key);
            SortedMap<Long, Collection<Long>> chunkMap = trimMap.headMap(chunkUpperBound);
            AggregatedValueObject aggregatedValueObject = AggregatedValueObject.createFromMultiValues(chunkMap.values());

            for(TimeReservoir aggregatedReservoir : this.aggregatedReservoirListeners) {
               aggregatedReservoir.update(aggregatedValueObject, chunkLowerBound >> ReservoirConstants.COLLISION_BUFFER_POWER, TimeUnit.NANOSECONDS);
            }

            chunkMap.clear();
         }

      }
   }

   private long upperBound(long chunkLowerBound, long key) {
      long chunkUpperBoundCandidate = chunkLowerBound + this.chunkSize;
      return chunkUpperBoundCandidate < key ? chunkUpperBoundCandidate : key;
   }

   private long lowerBound(Long key) {
      return lowerBound(key, TimeUnit.NANOSECONDS.convert(this.startTime, this.startUnitTime), this.chunkSize, ReservoirConstants.COLLISION_BUFFER_POWER);
   }

   static long lowerBound(long key, long startTime, long chunkSize, int power) {
      long offset = startTime % chunkSize << power;
      return key - offset >= 0L ? (key - offset) / chunkSize * chunkSize + offset : (key - offset - chunkSize + 1L) / chunkSize * chunkSize + offset;
   }

   public void register(TimeReservoir timeReservoirListener) {
      this.aggregatedReservoirListeners.add(timeReservoirListener);
   }

   public void setTimeReservoir(TimeReservoir timeReservoirNotifier) {
      this.timeReservoirNotifier = timeReservoirNotifier;
   }

   public TimeReservoir getTimeReservoirNotifier() {
      return this.timeReservoirNotifier;
   }
}

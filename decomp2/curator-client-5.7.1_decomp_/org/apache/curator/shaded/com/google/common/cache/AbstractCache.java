package org.apache.curator.shaded.com.google.common.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.shaded.com.google.common.collect.Maps;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class AbstractCache implements Cache {
   protected AbstractCache() {
   }

   public Object get(Object key, Callable valueLoader) throws ExecutionException {
      throw new UnsupportedOperationException();
   }

   public ImmutableMap getAllPresent(Iterable keys) {
      Map<K, V> result = Maps.newLinkedHashMap();

      for(Object key : keys) {
         if (!result.containsKey(key)) {
            V value = (V)this.getIfPresent(key);
            if (value != null) {
               result.put(key, value);
            }
         }
      }

      return ImmutableMap.copyOf(result);
   }

   public void put(Object key, Object value) {
      throw new UnsupportedOperationException();
   }

   public void putAll(Map m) {
      for(Map.Entry entry : m.entrySet()) {
         this.put(entry.getKey(), entry.getValue());
      }

   }

   public void cleanUp() {
   }

   public long size() {
      throw new UnsupportedOperationException();
   }

   public void invalidate(Object key) {
      throw new UnsupportedOperationException();
   }

   public void invalidateAll(Iterable keys) {
      for(Object key : keys) {
         this.invalidate(key);
      }

   }

   public void invalidateAll() {
      throw new UnsupportedOperationException();
   }

   public CacheStats stats() {
      throw new UnsupportedOperationException();
   }

   public ConcurrentMap asMap() {
      throw new UnsupportedOperationException();
   }

   public static final class SimpleStatsCounter implements StatsCounter {
      private final LongAddable hitCount = LongAddables.create();
      private final LongAddable missCount = LongAddables.create();
      private final LongAddable loadSuccessCount = LongAddables.create();
      private final LongAddable loadExceptionCount = LongAddables.create();
      private final LongAddable totalLoadTime = LongAddables.create();
      private final LongAddable evictionCount = LongAddables.create();

      public void recordHits(int count) {
         this.hitCount.add((long)count);
      }

      public void recordMisses(int count) {
         this.missCount.add((long)count);
      }

      public void recordLoadSuccess(long loadTime) {
         this.loadSuccessCount.increment();
         this.totalLoadTime.add(loadTime);
      }

      public void recordLoadException(long loadTime) {
         this.loadExceptionCount.increment();
         this.totalLoadTime.add(loadTime);
      }

      public void recordEviction() {
         this.evictionCount.increment();
      }

      public CacheStats snapshot() {
         return new CacheStats(negativeToMaxValue(this.hitCount.sum()), negativeToMaxValue(this.missCount.sum()), negativeToMaxValue(this.loadSuccessCount.sum()), negativeToMaxValue(this.loadExceptionCount.sum()), negativeToMaxValue(this.totalLoadTime.sum()), negativeToMaxValue(this.evictionCount.sum()));
      }

      private static long negativeToMaxValue(long value) {
         return value >= 0L ? value : Long.MAX_VALUE;
      }

      public void incrementBy(StatsCounter other) {
         CacheStats otherStats = other.snapshot();
         this.hitCount.add(otherStats.hitCount());
         this.missCount.add(otherStats.missCount());
         this.loadSuccessCount.add(otherStats.loadSuccessCount());
         this.loadExceptionCount.add(otherStats.loadExceptionCount());
         this.totalLoadTime.add(otherStats.totalLoadTime());
         this.evictionCount.add(otherStats.evictionCount());
      }
   }

   public interface StatsCounter {
      void recordHits(int count);

      void recordMisses(int count);

      void recordLoadSuccess(long loadTime);

      void recordLoadException(long loadTime);

      void recordEviction();

      CacheStats snapshot();
   }
}

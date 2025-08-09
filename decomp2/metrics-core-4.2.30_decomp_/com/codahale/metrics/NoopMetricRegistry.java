package com.codahale.metrics;

import java.io.OutputStream;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public final class NoopMetricRegistry extends MetricRegistry {
   private static final EmptyConcurrentMap EMPTY_CONCURRENT_MAP = new EmptyConcurrentMap();

   protected ConcurrentMap buildMap() {
      return EMPTY_CONCURRENT_MAP;
   }

   public Metric register(String name, Metric metric) throws IllegalArgumentException {
      if (metric == null) {
         throw new NullPointerException("metric == null");
      } else {
         return metric;
      }
   }

   public void registerAll(MetricSet metrics) throws IllegalArgumentException {
   }

   public Counter counter(String name) {
      return NoopMetricRegistry.NoopCounter.INSTANCE;
   }

   public Counter counter(String name, MetricRegistry.MetricSupplier supplier) {
      return NoopMetricRegistry.NoopCounter.INSTANCE;
   }

   public Histogram histogram(String name) {
      return NoopMetricRegistry.NoopHistogram.INSTANCE;
   }

   public Histogram histogram(String name, MetricRegistry.MetricSupplier supplier) {
      return NoopMetricRegistry.NoopHistogram.INSTANCE;
   }

   public Meter meter(String name) {
      return NoopMetricRegistry.NoopMeter.INSTANCE;
   }

   public Meter meter(String name, MetricRegistry.MetricSupplier supplier) {
      return NoopMetricRegistry.NoopMeter.INSTANCE;
   }

   public Timer timer(String name) {
      return NoopMetricRegistry.NoopTimer.INSTANCE;
   }

   public Timer timer(String name, MetricRegistry.MetricSupplier supplier) {
      return NoopMetricRegistry.NoopTimer.INSTANCE;
   }

   public Gauge gauge(String name) {
      return NoopMetricRegistry.NoopGauge.INSTANCE;
   }

   public Gauge gauge(String name, MetricRegistry.MetricSupplier supplier) {
      return NoopMetricRegistry.NoopGauge.INSTANCE;
   }

   public boolean remove(String name) {
      return false;
   }

   public void removeMatching(MetricFilter filter) {
   }

   public void addListener(MetricRegistryListener listener) {
   }

   public void removeListener(MetricRegistryListener listener) {
   }

   public SortedSet getNames() {
      return Collections.emptySortedSet();
   }

   public SortedMap getGauges() {
      return Collections.emptySortedMap();
   }

   public SortedMap getGauges(MetricFilter filter) {
      return Collections.emptySortedMap();
   }

   public SortedMap getCounters() {
      return Collections.emptySortedMap();
   }

   public SortedMap getCounters(MetricFilter filter) {
      return Collections.emptySortedMap();
   }

   public SortedMap getHistograms() {
      return Collections.emptySortedMap();
   }

   public SortedMap getHistograms(MetricFilter filter) {
      return Collections.emptySortedMap();
   }

   public SortedMap getMeters() {
      return Collections.emptySortedMap();
   }

   public SortedMap getMeters(MetricFilter filter) {
      return Collections.emptySortedMap();
   }

   public SortedMap getTimers() {
      return Collections.emptySortedMap();
   }

   public SortedMap getTimers(MetricFilter filter) {
      return Collections.emptySortedMap();
   }

   public void registerAll(String prefix, MetricSet metrics) throws IllegalArgumentException {
   }

   public Map getMetrics() {
      return Collections.emptyMap();
   }

   static final class NoopGauge implements Gauge {
      private static final NoopGauge INSTANCE = new NoopGauge();

      public Object getValue() {
         return null;
      }
   }

   private static final class EmptySnapshot extends Snapshot {
      private static final EmptySnapshot INSTANCE = new EmptySnapshot();
      private static final long[] EMPTY_LONG_ARRAY = new long[0];

      public double getValue(double quantile) {
         return (double)0.0F;
      }

      public long[] getValues() {
         return EMPTY_LONG_ARRAY;
      }

      public int size() {
         return 0;
      }

      public long getMax() {
         return 0L;
      }

      public double getMean() {
         return (double)0.0F;
      }

      public long getMin() {
         return 0L;
      }

      public double getStdDev() {
         return (double)0.0F;
      }

      public void dump(OutputStream output) {
      }
   }

   static final class NoopTimer extends Timer {
      private static final NoopTimer INSTANCE = new NoopTimer();
      private static final Timer.Context CONTEXT = new Context();

      public void update(long duration, TimeUnit unit) {
      }

      public void update(Duration duration) {
      }

      public Object time(Callable event) throws Exception {
         return event.call();
      }

      public Object timeSupplier(Supplier event) {
         return event.get();
      }

      public void time(Runnable event) {
         event.run();
      }

      public Timer.Context time() {
         return CONTEXT;
      }

      public long getCount() {
         return 0L;
      }

      public double getFifteenMinuteRate() {
         return (double)0.0F;
      }

      public double getFiveMinuteRate() {
         return (double)0.0F;
      }

      public double getMeanRate() {
         return (double)0.0F;
      }

      public double getOneMinuteRate() {
         return (double)0.0F;
      }

      public Snapshot getSnapshot() {
         return NoopMetricRegistry.EmptySnapshot.INSTANCE;
      }

      private static class Context extends Timer.Context {
         private static final Clock CLOCK = new Clock() {
            public long getTick() {
               return 0L;
            }

            public long getTime() {
               return 0L;
            }
         };

         private Context() {
            super(NoopMetricRegistry.NoopTimer.INSTANCE, CLOCK);
         }

         public long stop() {
            return 0L;
         }

         public void close() {
         }
      }
   }

   static final class NoopHistogram extends Histogram {
      private static final NoopHistogram INSTANCE = new NoopHistogram();
      private static final Reservoir EMPTY_RESERVOIR = new Reservoir() {
         public int size() {
            return 0;
         }

         public void update(long value) {
         }

         public Snapshot getSnapshot() {
            return NoopMetricRegistry.EmptySnapshot.INSTANCE;
         }
      };

      private NoopHistogram() {
         super(EMPTY_RESERVOIR);
      }

      public void update(int value) {
      }

      public void update(long value) {
      }

      public long getCount() {
         return 0L;
      }

      public Snapshot getSnapshot() {
         return NoopMetricRegistry.EmptySnapshot.INSTANCE;
      }
   }

   static final class NoopCounter extends Counter {
      private static final NoopCounter INSTANCE = new NoopCounter();

      public void inc() {
      }

      public void inc(long n) {
      }

      public void dec() {
      }

      public void dec(long n) {
      }

      public long getCount() {
         return 0L;
      }
   }

   static final class NoopMeter extends Meter {
      private static final NoopMeter INSTANCE = new NoopMeter();

      public void mark() {
      }

      public void mark(long n) {
      }

      public long getCount() {
         return 0L;
      }

      public double getFifteenMinuteRate() {
         return (double)0.0F;
      }

      public double getFiveMinuteRate() {
         return (double)0.0F;
      }

      public double getMeanRate() {
         return (double)0.0F;
      }

      public double getOneMinuteRate() {
         return (double)0.0F;
      }
   }

   private static final class EmptyConcurrentMap implements ConcurrentMap {
      private EmptyConcurrentMap() {
      }

      public Object putIfAbsent(Object key, Object value) {
         return null;
      }

      public boolean remove(Object key, Object value) {
         return false;
      }

      public boolean replace(Object key, Object oldValue, Object newValue) {
         return false;
      }

      public Object replace(Object key, Object value) {
         return null;
      }

      public int size() {
         return 0;
      }

      public boolean isEmpty() {
         return true;
      }

      public boolean containsKey(Object key) {
         return false;
      }

      public boolean containsValue(Object value) {
         return false;
      }

      public Object get(Object key) {
         return null;
      }

      public Object put(Object key, Object value) {
         return null;
      }

      public Object remove(Object key) {
         return null;
      }

      public void putAll(Map m) {
      }

      public void clear() {
      }

      public Set keySet() {
         return Collections.emptySet();
      }

      public Collection values() {
         return Collections.emptySet();
      }

      public Set entrySet() {
         return Collections.emptySet();
      }
   }
}

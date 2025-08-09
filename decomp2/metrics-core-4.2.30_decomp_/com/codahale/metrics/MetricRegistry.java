package com.codahale.metrics;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MetricRegistry implements MetricSet {
   private final ConcurrentMap metrics = this.buildMap();
   private final List listeners = new CopyOnWriteArrayList();

   public static String name(String name, String... names) {
      StringBuilder builder = new StringBuilder();
      append(builder, name);
      if (names != null) {
         for(String s : names) {
            append(builder, s);
         }
      }

      return builder.toString();
   }

   public static String name(Class klass, String... names) {
      return name(klass.getName(), names);
   }

   private static void append(StringBuilder builder, String part) {
      if (part != null && !part.isEmpty()) {
         if (builder.length() > 0) {
            builder.append('.');
         }

         builder.append(part);
      }

   }

   protected ConcurrentMap buildMap() {
      return new ConcurrentHashMap();
   }

   public Gauge registerGauge(String name, Gauge metric) throws IllegalArgumentException {
      return (Gauge)this.register(name, metric);
   }

   public Metric register(final String name, Metric metric) throws IllegalArgumentException {
      if (metric == null) {
         throw new NullPointerException("metric == null");
      } else {
         if (metric instanceof MetricRegistry) {
            MetricRegistry childRegistry = (MetricRegistry)metric;
            childRegistry.addListener(new MetricRegistryListener() {
               public void onGaugeAdded(String namex, Gauge gauge) {
                  MetricRegistry.this.register(MetricRegistry.name(name, name), gauge);
               }

               public void onGaugeRemoved(String namex) {
                  MetricRegistry.this.remove(MetricRegistry.name(name, name));
               }

               public void onCounterAdded(String namex, Counter counter) {
                  MetricRegistry.this.register(MetricRegistry.name(name, name), counter);
               }

               public void onCounterRemoved(String namex) {
                  MetricRegistry.this.remove(MetricRegistry.name(name, name));
               }

               public void onHistogramAdded(String namex, Histogram histogram) {
                  MetricRegistry.this.register(MetricRegistry.name(name, name), histogram);
               }

               public void onHistogramRemoved(String namex) {
                  MetricRegistry.this.remove(MetricRegistry.name(name, name));
               }

               public void onMeterAdded(String namex, Meter meter) {
                  MetricRegistry.this.register(MetricRegistry.name(name, name), meter);
               }

               public void onMeterRemoved(String namex) {
                  MetricRegistry.this.remove(MetricRegistry.name(name, name));
               }

               public void onTimerAdded(String namex, Timer timer) {
                  MetricRegistry.this.register(MetricRegistry.name(name, name), timer);
               }

               public void onTimerRemoved(String namex) {
                  MetricRegistry.this.remove(MetricRegistry.name(name, name));
               }
            });
         } else if (metric instanceof MetricSet) {
            this.registerAll(name, (MetricSet)metric);
         } else {
            Metric existing = (Metric)this.metrics.putIfAbsent(name, metric);
            if (existing != null) {
               throw new IllegalArgumentException("A metric named " + name + " already exists");
            }

            this.onMetricAdded(name, metric);
         }

         return metric;
      }
   }

   public void registerAll(MetricSet metrics) throws IllegalArgumentException {
      this.registerAll((String)null, metrics);
   }

   public Counter counter(String name) {
      return (Counter)this.getOrAdd(name, MetricRegistry.MetricBuilder.COUNTERS);
   }

   public Counter counter(String name, final MetricSupplier supplier) {
      return (Counter)this.getOrAdd(name, new MetricBuilder() {
         public Counter newMetric() {
            return (Counter)supplier.newMetric();
         }

         public boolean isInstance(Metric metric) {
            return Counter.class.isInstance(metric);
         }
      });
   }

   public Histogram histogram(String name) {
      return (Histogram)this.getOrAdd(name, MetricRegistry.MetricBuilder.HISTOGRAMS);
   }

   public Histogram histogram(String name, final MetricSupplier supplier) {
      return (Histogram)this.getOrAdd(name, new MetricBuilder() {
         public Histogram newMetric() {
            return (Histogram)supplier.newMetric();
         }

         public boolean isInstance(Metric metric) {
            return Histogram.class.isInstance(metric);
         }
      });
   }

   public Meter meter(String name) {
      return (Meter)this.getOrAdd(name, MetricRegistry.MetricBuilder.METERS);
   }

   public Meter meter(String name, final MetricSupplier supplier) {
      return (Meter)this.getOrAdd(name, new MetricBuilder() {
         public Meter newMetric() {
            return (Meter)supplier.newMetric();
         }

         public boolean isInstance(Metric metric) {
            return Meter.class.isInstance(metric);
         }
      });
   }

   public Timer timer(String name) {
      return (Timer)this.getOrAdd(name, MetricRegistry.MetricBuilder.TIMERS);
   }

   public Timer timer(String name, final MetricSupplier supplier) {
      return (Timer)this.getOrAdd(name, new MetricBuilder() {
         public Timer newMetric() {
            return (Timer)supplier.newMetric();
         }

         public boolean isInstance(Metric metric) {
            return Timer.class.isInstance(metric);
         }
      });
   }

   public Gauge gauge(String name) {
      return (Gauge)this.getOrAdd(name, MetricRegistry.MetricBuilder.GAUGES);
   }

   public Gauge gauge(String name, final MetricSupplier supplier) {
      return (Gauge)this.getOrAdd(name, new MetricBuilder() {
         public Gauge newMetric() {
            return (Gauge)supplier.newMetric();
         }

         public boolean isInstance(Metric metric) {
            return Gauge.class.isInstance(metric);
         }
      });
   }

   public boolean remove(String name) {
      Metric metric = (Metric)this.metrics.remove(name);
      if (metric != null) {
         this.onMetricRemoved(name, metric);
         return true;
      } else {
         return false;
      }
   }

   public void removeMatching(MetricFilter filter) {
      for(Map.Entry entry : this.metrics.entrySet()) {
         if (filter.matches((String)entry.getKey(), (Metric)entry.getValue())) {
            this.remove((String)entry.getKey());
         }
      }

   }

   public void addListener(MetricRegistryListener listener) {
      this.listeners.add(listener);

      for(Map.Entry entry : this.metrics.entrySet()) {
         this.notifyListenerOfAddedMetric(listener, (Metric)entry.getValue(), (String)entry.getKey());
      }

   }

   public void removeListener(MetricRegistryListener listener) {
      this.listeners.remove(listener);
   }

   public SortedSet getNames() {
      return Collections.unmodifiableSortedSet(new TreeSet(this.metrics.keySet()));
   }

   public SortedMap getGauges() {
      return this.getGauges(MetricFilter.ALL);
   }

   public SortedMap getGauges(MetricFilter filter) {
      return this.getMetrics(Gauge.class, filter);
   }

   public SortedMap getCounters() {
      return this.getCounters(MetricFilter.ALL);
   }

   public SortedMap getCounters(MetricFilter filter) {
      return this.getMetrics(Counter.class, filter);
   }

   public SortedMap getHistograms() {
      return this.getHistograms(MetricFilter.ALL);
   }

   public SortedMap getHistograms(MetricFilter filter) {
      return this.getMetrics(Histogram.class, filter);
   }

   public SortedMap getMeters() {
      return this.getMeters(MetricFilter.ALL);
   }

   public SortedMap getMeters(MetricFilter filter) {
      return this.getMetrics(Meter.class, filter);
   }

   public SortedMap getTimers() {
      return this.getTimers(MetricFilter.ALL);
   }

   public SortedMap getTimers(MetricFilter filter) {
      return this.getMetrics(Timer.class, filter);
   }

   private Metric getOrAdd(String name, MetricBuilder builder) {
      Metric metric = (Metric)this.metrics.get(name);
      if (builder.isInstance(metric)) {
         return metric;
      } else {
         if (metric == null) {
            try {
               return this.register(name, builder.newMetric());
            } catch (IllegalArgumentException var6) {
               Metric added = (Metric)this.metrics.get(name);
               if (builder.isInstance(added)) {
                  return added;
               }
            }
         }

         throw new IllegalArgumentException(name + " is already used for a different type of metric");
      }
   }

   private SortedMap getMetrics(Class klass, MetricFilter filter) {
      TreeMap<String, T> timers = new TreeMap();

      for(Map.Entry entry : this.metrics.entrySet()) {
         if (klass.isInstance(entry.getValue()) && filter.matches((String)entry.getKey(), (Metric)entry.getValue())) {
            timers.put((String)entry.getKey(), (Metric)entry.getValue());
         }
      }

      return Collections.unmodifiableSortedMap(timers);
   }

   private void onMetricAdded(String name, Metric metric) {
      for(MetricRegistryListener listener : this.listeners) {
         this.notifyListenerOfAddedMetric(listener, metric, name);
      }

   }

   private void notifyListenerOfAddedMetric(MetricRegistryListener listener, Metric metric, String name) {
      if (metric instanceof Gauge) {
         listener.onGaugeAdded(name, (Gauge)metric);
      } else if (metric instanceof Counter) {
         listener.onCounterAdded(name, (Counter)metric);
      } else if (metric instanceof Histogram) {
         listener.onHistogramAdded(name, (Histogram)metric);
      } else if (metric instanceof Meter) {
         listener.onMeterAdded(name, (Meter)metric);
      } else {
         if (!(metric instanceof Timer)) {
            throw new IllegalArgumentException("Unknown metric type: " + metric.getClass());
         }

         listener.onTimerAdded(name, (Timer)metric);
      }

   }

   private void onMetricRemoved(String name, Metric metric) {
      for(MetricRegistryListener listener : this.listeners) {
         this.notifyListenerOfRemovedMetric(name, metric, listener);
      }

   }

   private void notifyListenerOfRemovedMetric(String name, Metric metric, MetricRegistryListener listener) {
      if (metric instanceof Gauge) {
         listener.onGaugeRemoved(name);
      } else if (metric instanceof Counter) {
         listener.onCounterRemoved(name);
      } else if (metric instanceof Histogram) {
         listener.onHistogramRemoved(name);
      } else if (metric instanceof Meter) {
         listener.onMeterRemoved(name);
      } else {
         if (!(metric instanceof Timer)) {
            throw new IllegalArgumentException("Unknown metric type: " + metric.getClass());
         }

         listener.onTimerRemoved(name);
      }

   }

   public void registerAll(String prefix, MetricSet metrics) throws IllegalArgumentException {
      for(Map.Entry entry : metrics.getMetrics().entrySet()) {
         if (entry.getValue() instanceof MetricSet) {
            this.registerAll(name(prefix, (String)entry.getKey()), (MetricSet)entry.getValue());
         } else {
            this.register(name(prefix, (String)entry.getKey()), (Metric)entry.getValue());
         }
      }

   }

   public Map getMetrics() {
      return Collections.unmodifiableMap(this.metrics);
   }

   private interface MetricBuilder {
      MetricBuilder COUNTERS = new MetricBuilder() {
         public Counter newMetric() {
            return new Counter();
         }

         public boolean isInstance(Metric metric) {
            return Counter.class.isInstance(metric);
         }
      };
      MetricBuilder HISTOGRAMS = new MetricBuilder() {
         public Histogram newMetric() {
            return new Histogram(new ExponentiallyDecayingReservoir());
         }

         public boolean isInstance(Metric metric) {
            return Histogram.class.isInstance(metric);
         }
      };
      MetricBuilder METERS = new MetricBuilder() {
         public Meter newMetric() {
            return new Meter();
         }

         public boolean isInstance(Metric metric) {
            return Meter.class.isInstance(metric);
         }
      };
      MetricBuilder TIMERS = new MetricBuilder() {
         public Timer newMetric() {
            return new Timer();
         }

         public boolean isInstance(Metric metric) {
            return Timer.class.isInstance(metric);
         }
      };
      MetricBuilder GAUGES = new MetricBuilder() {
         public Gauge newMetric() {
            return new DefaultSettableGauge();
         }

         public boolean isInstance(Metric metric) {
            return Gauge.class.isInstance(metric);
         }
      };

      Metric newMetric();

      boolean isInstance(Metric metric);
   }

   @FunctionalInterface
   public interface MetricSupplier {
      Metric newMetric();
   }
}

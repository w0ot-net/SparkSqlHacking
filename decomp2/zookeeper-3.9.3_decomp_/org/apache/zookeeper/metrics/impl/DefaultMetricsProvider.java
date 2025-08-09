package org.apache.zookeeper.metrics.impl;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import org.apache.zookeeper.metrics.Counter;
import org.apache.zookeeper.metrics.CounterSet;
import org.apache.zookeeper.metrics.Gauge;
import org.apache.zookeeper.metrics.GaugeSet;
import org.apache.zookeeper.metrics.MetricsContext;
import org.apache.zookeeper.metrics.MetricsProvider;
import org.apache.zookeeper.metrics.MetricsProviderLifeCycleException;
import org.apache.zookeeper.metrics.Summary;
import org.apache.zookeeper.metrics.SummarySet;
import org.apache.zookeeper.server.metric.AvgMinMaxCounter;
import org.apache.zookeeper.server.metric.AvgMinMaxCounterSet;
import org.apache.zookeeper.server.metric.AvgMinMaxPercentileCounter;
import org.apache.zookeeper.server.metric.AvgMinMaxPercentileCounterSet;
import org.apache.zookeeper.server.metric.SimpleCounter;
import org.apache.zookeeper.server.metric.SimpleCounterSet;

public class DefaultMetricsProvider implements MetricsProvider {
   private final DefaultMetricsContext rootMetricsContext = new DefaultMetricsContext();

   public void configure(Properties configuration) throws MetricsProviderLifeCycleException {
   }

   public void start() throws MetricsProviderLifeCycleException {
   }

   public MetricsContext getRootContext() {
      return this.rootMetricsContext;
   }

   public void stop() {
      this.rootMetricsContext.gauges.clear();
      this.rootMetricsContext.gaugeSets.clear();
   }

   public void dump(BiConsumer sink) {
      this.rootMetricsContext.dump(sink);
   }

   public void resetAllValues() {
      this.rootMetricsContext.reset();
   }

   private static final class DefaultMetricsContext implements MetricsContext {
      private final ConcurrentMap gauges;
      private final ConcurrentMap gaugeSets;
      private final ConcurrentMap counters;
      private final ConcurrentMap counterSets;
      private final ConcurrentMap basicSummaries;
      private final ConcurrentMap summaries;
      private final ConcurrentMap basicSummarySets;
      private final ConcurrentMap summarySets;

      private DefaultMetricsContext() {
         this.gauges = new ConcurrentHashMap();
         this.gaugeSets = new ConcurrentHashMap();
         this.counters = new ConcurrentHashMap();
         this.counterSets = new ConcurrentHashMap();
         this.basicSummaries = new ConcurrentHashMap();
         this.summaries = new ConcurrentHashMap();
         this.basicSummarySets = new ConcurrentHashMap();
         this.summarySets = new ConcurrentHashMap();
      }

      public MetricsContext getContext(String name) {
         return this;
      }

      public Counter getCounter(String name) {
         return (Counter)this.counters.computeIfAbsent(name, (n) -> new SimpleCounter(n));
      }

      public CounterSet getCounterSet(String name) {
         Objects.requireNonNull(name, "Cannot register a CounterSet with null name");
         return (CounterSet)this.counterSets.computeIfAbsent(name, SimpleCounterSet::new);
      }

      public void registerGauge(String name, Gauge gauge) {
         Objects.requireNonNull(gauge, "Cannot register a null Gauge for " + name);
         this.gauges.put(name, gauge);
      }

      public void unregisterGauge(String name) {
         this.gauges.remove(name);
      }

      public void registerGaugeSet(String name, GaugeSet gaugeSet) {
         Objects.requireNonNull(name, "Cannot register a GaugeSet with null name");
         Objects.requireNonNull(gaugeSet, "Cannot register a null GaugeSet for " + name);
         this.gaugeSets.put(name, gaugeSet);
      }

      public void unregisterGaugeSet(String name) {
         Objects.requireNonNull(name, "Cannot unregister GaugeSet with null name");
         this.gaugeSets.remove(name);
      }

      public Summary getSummary(String name, MetricsContext.DetailLevel detailLevel) {
         return detailLevel == MetricsContext.DetailLevel.BASIC ? (Summary)this.basicSummaries.computeIfAbsent(name, (n) -> {
            if (this.summaries.containsKey(n)) {
               throw new IllegalArgumentException("Already registered a non basic summary as " + n);
            } else {
               return new AvgMinMaxCounter(name);
            }
         }) : (Summary)this.summaries.computeIfAbsent(name, (n) -> {
            if (this.basicSummaries.containsKey(n)) {
               throw new IllegalArgumentException("Already registered a basic summary as " + n);
            } else {
               return new AvgMinMaxPercentileCounter(name);
            }
         });
      }

      public SummarySet getSummarySet(String name, MetricsContext.DetailLevel detailLevel) {
         return detailLevel == MetricsContext.DetailLevel.BASIC ? (SummarySet)this.basicSummarySets.computeIfAbsent(name, (n) -> {
            if (this.summarySets.containsKey(n)) {
               throw new IllegalArgumentException("Already registered a non basic summary set as " + n);
            } else {
               return new AvgMinMaxCounterSet(name);
            }
         }) : (SummarySet)this.summarySets.computeIfAbsent(name, (n) -> {
            if (this.basicSummarySets.containsKey(n)) {
               throw new IllegalArgumentException("Already registered a basic summary set as " + n);
            } else {
               return new AvgMinMaxPercentileCounterSet(name);
            }
         });
      }

      void dump(BiConsumer sink) {
         this.gauges.forEach((name, metric) -> {
            Number value = metric.get();
            if (value != null) {
               sink.accept(name, value);
            }

         });
         this.gaugeSets.forEach((name, gaugeSet) -> gaugeSet.values().forEach((key, value) -> {
               if (key != null) {
                  sink.accept(key + "_" + name, value != null ? value : 0);
               }

            }));
         this.counters.values().forEach((metric) -> metric.values().forEach(sink));
         this.counterSets.values().forEach((metric) -> metric.values().forEach(sink));
         this.basicSummaries.values().forEach((metric) -> metric.values().forEach(sink));
         this.summaries.values().forEach((metric) -> metric.values().forEach(sink));
         this.basicSummarySets.values().forEach((metric) -> metric.values().forEach(sink));
         this.summarySets.values().forEach((metric) -> metric.values().forEach(sink));
      }

      void reset() {
         this.counters.values().forEach((metric) -> metric.reset());
         this.counterSets.values().forEach((metric) -> metric.reset());
         this.basicSummaries.values().forEach((metric) -> metric.reset());
         this.summaries.values().forEach((metric) -> metric.reset());
         this.basicSummarySets.values().forEach((metric) -> metric.reset());
         this.summarySets.values().forEach((metric) -> metric.reset());
      }
   }
}

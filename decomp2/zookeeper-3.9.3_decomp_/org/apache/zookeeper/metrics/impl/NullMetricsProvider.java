package org.apache.zookeeper.metrics.impl;

import java.util.Properties;
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

public class NullMetricsProvider implements MetricsProvider {
   public static final MetricsProvider INSTANCE = new NullMetricsProvider();

   public void configure(Properties configuration) throws MetricsProviderLifeCycleException {
   }

   public void start() throws MetricsProviderLifeCycleException {
   }

   public MetricsContext getRootContext() {
      return NullMetricsProvider.NullMetricsContext.INSTANCE;
   }

   public void dump(BiConsumer sink) {
   }

   public void resetAllValues() {
   }

   public void stop() {
   }

   public static final class NullMetricsContext implements MetricsContext {
      public static final NullMetricsContext INSTANCE = new NullMetricsContext();

      public MetricsContext getContext(String name) {
         return INSTANCE;
      }

      public Counter getCounter(String name) {
         return NullMetricsProvider.NullCounter.INSTANCE;
      }

      public CounterSet getCounterSet(String name) {
         return NullMetricsProvider.NullCounterSet.INSTANCE;
      }

      public void registerGauge(String name, Gauge gauge) {
      }

      public void unregisterGauge(String name) {
      }

      public void registerGaugeSet(String name, GaugeSet gaugeSet) {
      }

      public void unregisterGaugeSet(String name) {
      }

      public Summary getSummary(String name, MetricsContext.DetailLevel detailLevel) {
         return NullMetricsProvider.NullSummary.INSTANCE;
      }

      public SummarySet getSummarySet(String name, MetricsContext.DetailLevel detailLevel) {
         return NullMetricsProvider.NullSummarySet.INSTANCE;
      }
   }

   private static final class NullCounter implements Counter {
      private static final NullCounter INSTANCE = new NullCounter();

      public void add(long delta) {
      }

      public long get() {
         return 0L;
      }
   }

   private static final class NullCounterSet implements CounterSet {
      private static final NullCounterSet INSTANCE = new NullCounterSet();

      public void add(String key, long delta) {
      }
   }

   private static final class NullSummary implements Summary {
      private static final NullSummary INSTANCE = new NullSummary();

      public void add(long value) {
      }
   }

   private static final class NullSummarySet implements SummarySet {
      private static final NullSummarySet INSTANCE = new NullSummarySet();

      public void add(String key, long value) {
      }
   }
}

package com.codahale.metrics.graphite;

import com.codahale.metrics.Clock;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Metered;
import com.codahale.metrics.MetricAttribute;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;
import java.io.IOException;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphiteReporter extends ScheduledReporter {
   private static final Logger LOGGER = LoggerFactory.getLogger(GraphiteReporter.class);
   private static final DoubleFunction DEFAULT_FP_FORMATTER = (fp) -> String.format(Locale.US, "%2.2f", fp);
   private final GraphiteSender graphite;
   private final Clock clock;
   private final String prefix;
   private final boolean addMetricAttributesAsTags;
   private final DoubleFunction floatingPointFormatter;

   public static Builder forRegistry(MetricRegistry registry) {
      return new Builder(registry);
   }

   protected GraphiteReporter(MetricRegistry registry, GraphiteSender graphite, Clock clock, String prefix, TimeUnit rateUnit, TimeUnit durationUnit, MetricFilter filter, ScheduledExecutorService executor, boolean shutdownExecutorOnStop, Set disabledMetricAttributes) {
      this(registry, graphite, clock, prefix, rateUnit, durationUnit, filter, executor, shutdownExecutorOnStop, disabledMetricAttributes, false);
   }

   protected GraphiteReporter(MetricRegistry registry, GraphiteSender graphite, Clock clock, String prefix, TimeUnit rateUnit, TimeUnit durationUnit, MetricFilter filter, ScheduledExecutorService executor, boolean shutdownExecutorOnStop, Set disabledMetricAttributes, boolean addMetricAttributesAsTags) {
      this(registry, graphite, clock, prefix, rateUnit, durationUnit, filter, executor, shutdownExecutorOnStop, disabledMetricAttributes, addMetricAttributesAsTags, DEFAULT_FP_FORMATTER);
   }

   protected GraphiteReporter(MetricRegistry registry, GraphiteSender graphite, Clock clock, String prefix, TimeUnit rateUnit, TimeUnit durationUnit, MetricFilter filter, ScheduledExecutorService executor, boolean shutdownExecutorOnStop, Set disabledMetricAttributes, boolean addMetricAttributesAsTags, DoubleFunction floatingPointFormatter) {
      super(registry, "graphite-reporter", filter, rateUnit, durationUnit, executor, shutdownExecutorOnStop, disabledMetricAttributes);
      this.graphite = graphite;
      this.clock = clock;
      this.prefix = prefix;
      this.addMetricAttributesAsTags = addMetricAttributesAsTags;
      this.floatingPointFormatter = floatingPointFormatter;
   }

   public void report(SortedMap gauges, SortedMap counters, SortedMap histograms, SortedMap meters, SortedMap timers) {
      long timestamp = this.clock.getTime() / 1000L;

      try {
         this.graphite.connect();

         for(Map.Entry entry : gauges.entrySet()) {
            this.reportGauge((String)entry.getKey(), (Gauge)entry.getValue(), timestamp);
         }

         for(Map.Entry entry : counters.entrySet()) {
            this.reportCounter((String)entry.getKey(), (Counter)entry.getValue(), timestamp);
         }

         for(Map.Entry entry : histograms.entrySet()) {
            this.reportHistogram((String)entry.getKey(), (Histogram)entry.getValue(), timestamp);
         }

         for(Map.Entry entry : meters.entrySet()) {
            this.reportMetered((String)entry.getKey(), (Metered)entry.getValue(), timestamp);
         }

         for(Map.Entry entry : timers.entrySet()) {
            this.reportTimer((String)entry.getKey(), (Timer)entry.getValue(), timestamp);
         }

         this.graphite.flush();
      } catch (IOException e) {
         LOGGER.warn("Unable to report to Graphite", this.graphite, e);
      } finally {
         try {
            this.graphite.close();
         } catch (IOException e1) {
            LOGGER.warn("Error closing Graphite", this.graphite, e1);
         }

      }

   }

   public void stop() {
      try {
         super.stop();
      } finally {
         try {
            this.graphite.close();
         } catch (IOException e) {
            LOGGER.debug("Error disconnecting from Graphite", this.graphite, e);
         }

      }

   }

   private void reportTimer(String name, Timer timer, long timestamp) throws IOException {
      Snapshot snapshot = timer.getSnapshot();
      this.sendIfEnabled(MetricAttribute.MAX, name, this.convertDuration((double)snapshot.getMax()), timestamp);
      this.sendIfEnabled(MetricAttribute.MEAN, name, this.convertDuration(snapshot.getMean()), timestamp);
      this.sendIfEnabled(MetricAttribute.MIN, name, this.convertDuration((double)snapshot.getMin()), timestamp);
      this.sendIfEnabled(MetricAttribute.STDDEV, name, this.convertDuration(snapshot.getStdDev()), timestamp);
      this.sendIfEnabled(MetricAttribute.P50, name, this.convertDuration(snapshot.getMedian()), timestamp);
      this.sendIfEnabled(MetricAttribute.P75, name, this.convertDuration(snapshot.get75thPercentile()), timestamp);
      this.sendIfEnabled(MetricAttribute.P95, name, this.convertDuration(snapshot.get95thPercentile()), timestamp);
      this.sendIfEnabled(MetricAttribute.P98, name, this.convertDuration(snapshot.get98thPercentile()), timestamp);
      this.sendIfEnabled(MetricAttribute.P99, name, this.convertDuration(snapshot.get99thPercentile()), timestamp);
      this.sendIfEnabled(MetricAttribute.P999, name, this.convertDuration(snapshot.get999thPercentile()), timestamp);
      this.reportMetered(name, timer, timestamp);
   }

   private void reportMetered(String name, Metered meter, long timestamp) throws IOException {
      this.sendIfEnabled(MetricAttribute.COUNT, name, meter.getCount(), timestamp);
      this.sendIfEnabled(MetricAttribute.M1_RATE, name, this.convertRate(meter.getOneMinuteRate()), timestamp);
      this.sendIfEnabled(MetricAttribute.M5_RATE, name, this.convertRate(meter.getFiveMinuteRate()), timestamp);
      this.sendIfEnabled(MetricAttribute.M15_RATE, name, this.convertRate(meter.getFifteenMinuteRate()), timestamp);
      this.sendIfEnabled(MetricAttribute.MEAN_RATE, name, this.convertRate(meter.getMeanRate()), timestamp);
   }

   private void reportHistogram(String name, Histogram histogram, long timestamp) throws IOException {
      Snapshot snapshot = histogram.getSnapshot();
      this.sendIfEnabled(MetricAttribute.COUNT, name, histogram.getCount(), timestamp);
      this.sendIfEnabled(MetricAttribute.MAX, name, snapshot.getMax(), timestamp);
      this.sendIfEnabled(MetricAttribute.MEAN, name, snapshot.getMean(), timestamp);
      this.sendIfEnabled(MetricAttribute.MIN, name, snapshot.getMin(), timestamp);
      this.sendIfEnabled(MetricAttribute.STDDEV, name, snapshot.getStdDev(), timestamp);
      this.sendIfEnabled(MetricAttribute.P50, name, snapshot.getMedian(), timestamp);
      this.sendIfEnabled(MetricAttribute.P75, name, snapshot.get75thPercentile(), timestamp);
      this.sendIfEnabled(MetricAttribute.P95, name, snapshot.get95thPercentile(), timestamp);
      this.sendIfEnabled(MetricAttribute.P98, name, snapshot.get98thPercentile(), timestamp);
      this.sendIfEnabled(MetricAttribute.P99, name, snapshot.get99thPercentile(), timestamp);
      this.sendIfEnabled(MetricAttribute.P999, name, snapshot.get999thPercentile(), timestamp);
   }

   private void sendIfEnabled(MetricAttribute type, String name, double value, long timestamp) throws IOException {
      if (!this.getDisabledMetricAttributes().contains(type)) {
         this.graphite.send(this.prefix(this.appendMetricAttribute(name, type.getCode())), this.format(value), timestamp);
      }
   }

   private void sendIfEnabled(MetricAttribute type, String name, long value, long timestamp) throws IOException {
      if (!this.getDisabledMetricAttributes().contains(type)) {
         this.graphite.send(this.prefix(this.appendMetricAttribute(name, type.getCode())), this.format(value), timestamp);
      }
   }

   private void reportCounter(String name, Counter counter, long timestamp) throws IOException {
      this.graphite.send(this.prefix(this.appendMetricAttribute(name, MetricAttribute.COUNT.getCode())), this.format(counter.getCount()), timestamp);
   }

   private void reportGauge(String name, Gauge gauge, long timestamp) throws IOException {
      String value = this.format(gauge.getValue());
      if (value != null) {
         this.graphite.send(this.prefix(name), value, timestamp);
      }

   }

   private String format(Object o) {
      if (o instanceof Float) {
         return this.format(((Float)o).doubleValue());
      } else if (o instanceof Double) {
         return this.format((Double)o);
      } else if (o instanceof Byte) {
         return this.format(((Byte)o).longValue());
      } else if (o instanceof Short) {
         return this.format(((Short)o).longValue());
      } else if (o instanceof Integer) {
         return this.format(((Integer)o).longValue());
      } else if (o instanceof Long) {
         return this.format((Long)o);
      } else if (o instanceof Number) {
         return this.format(((Number)o).doubleValue());
      } else {
         return o instanceof Boolean ? this.format((Boolean)o ? 1L : 0L) : null;
      }
   }

   private String prefix(String name) {
      return MetricRegistry.name(this.prefix, new String[]{name});
   }

   private String appendMetricAttribute(String name, String metricAttribute) {
      return this.addMetricAttributesAsTags ? name + ";metricattribute=" + metricAttribute : name + "." + metricAttribute;
   }

   private String format(long n) {
      return Long.toString(n);
   }

   protected String format(double v) {
      return (String)this.floatingPointFormatter.apply(v);
   }

   public static class Builder {
      private final MetricRegistry registry;
      private Clock clock;
      private String prefix;
      private TimeUnit rateUnit;
      private TimeUnit durationUnit;
      private MetricFilter filter;
      private ScheduledExecutorService executor;
      private boolean shutdownExecutorOnStop;
      private Set disabledMetricAttributes;
      private boolean addMetricAttributesAsTags;
      private DoubleFunction floatingPointFormatter;

      private Builder(MetricRegistry registry) {
         this.registry = registry;
         this.clock = Clock.defaultClock();
         this.prefix = null;
         this.rateUnit = TimeUnit.SECONDS;
         this.durationUnit = TimeUnit.MILLISECONDS;
         this.filter = MetricFilter.ALL;
         this.executor = null;
         this.shutdownExecutorOnStop = true;
         this.disabledMetricAttributes = Collections.emptySet();
         this.addMetricAttributesAsTags = false;
         this.floatingPointFormatter = GraphiteReporter.DEFAULT_FP_FORMATTER;
      }

      public Builder shutdownExecutorOnStop(boolean shutdownExecutorOnStop) {
         this.shutdownExecutorOnStop = shutdownExecutorOnStop;
         return this;
      }

      public Builder scheduleOn(ScheduledExecutorService executor) {
         this.executor = executor;
         return this;
      }

      public Builder withClock(Clock clock) {
         this.clock = clock;
         return this;
      }

      public Builder prefixedWith(String prefix) {
         this.prefix = prefix;
         return this;
      }

      public Builder convertRatesTo(TimeUnit rateUnit) {
         this.rateUnit = rateUnit;
         return this;
      }

      public Builder convertDurationsTo(TimeUnit durationUnit) {
         this.durationUnit = durationUnit;
         return this;
      }

      public Builder filter(MetricFilter filter) {
         this.filter = filter;
         return this;
      }

      public Builder disabledMetricAttributes(Set disabledMetricAttributes) {
         this.disabledMetricAttributes = disabledMetricAttributes;
         return this;
      }

      public Builder addMetricAttributesAsTags(boolean addMetricAttributesAsTags) {
         this.addMetricAttributesAsTags = addMetricAttributesAsTags;
         return this;
      }

      public Builder withFloatingPointFormatter(DoubleFunction floatingPointFormatter) {
         this.floatingPointFormatter = floatingPointFormatter;
         return this;
      }

      public GraphiteReporter build(Graphite graphite) {
         return this.build((GraphiteSender)graphite);
      }

      public GraphiteReporter build(GraphiteSender graphite) {
         return new GraphiteReporter(this.registry, graphite, this.clock, this.prefix, this.rateUnit, this.durationUnit, this.filter, this.executor, this.shutdownExecutorOnStop, this.disabledMetricAttributes, this.addMetricAttributesAsTags, this.floatingPointFormatter);
      }
   }
}

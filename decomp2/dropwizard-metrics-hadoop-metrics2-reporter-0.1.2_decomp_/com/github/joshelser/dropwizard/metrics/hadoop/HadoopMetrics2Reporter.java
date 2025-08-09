package com.github.joshelser.dropwizard.metrics.hadoop;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.metrics2.MetricsCollector;
import org.apache.hadoop.metrics2.MetricsInfo;
import org.apache.hadoop.metrics2.MetricsRecordBuilder;
import org.apache.hadoop.metrics2.MetricsSource;
import org.apache.hadoop.metrics2.MetricsSystem;
import org.apache.hadoop.metrics2.lib.Interns;
import org.apache.hadoop.metrics2.lib.MetricsRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HadoopMetrics2Reporter extends ScheduledReporter implements MetricsSource {
   private static final Logger LOG = LoggerFactory.getLogger(HadoopMetrics2Reporter.class);
   private static final String EMPTY_STRING = "";
   private static final SortedMap EMPTY_GAUGE_MAP = Collections.unmodifiableSortedMap(new TreeMap());
   private static final SortedMap EMPTY_METER_MAP = Collections.unmodifiableSortedMap(new TreeMap());
   private static final SortedMap EMPTY_TIMER_MAP = Collections.unmodifiableSortedMap(new TreeMap());
   private static final SortedMap EMPTY_COUNTER_MAP = Collections.unmodifiableSortedMap(new TreeMap());
   private static final SortedMap EMPTY_HISTOGRAM_MAP = Collections.unmodifiableSortedMap(new TreeMap());
   public static final MetricsInfo RATE_UNIT_LABEL = Interns.info("rate_unit", "The unit of measure for rate metrics");
   public static final MetricsInfo DURATION_UNIT_LABEL = Interns.info("duration_unit", "The unit of measure of duration metrics");
   private final MetricsRegistry metrics2Registry;
   private final MetricsSystem metrics2System;
   private final String recordName;
   private final String context;
   private SortedMap dropwizardGauges;
   private SortedMap dropwizardCounters;
   private SortedMap dropwizardHistograms;
   private SortedMap dropwizardMeters;
   private SortedMap dropwizardTimers;

   public static Builder forRegistry(MetricRegistry registry) {
      return new Builder(registry);
   }

   private HadoopMetrics2Reporter(MetricRegistry registry, TimeUnit rateUnit, TimeUnit durationUnit, MetricFilter filter, MetricsSystem metrics2System, String jmxContext, String description, String recordName, String context) {
      super(registry, "hadoop-metrics2-reporter", filter, rateUnit, durationUnit);
      this.metrics2Registry = new MetricsRegistry(Interns.info(jmxContext, description));
      this.metrics2System = metrics2System;
      this.recordName = recordName;
      this.context = context;
      this.dropwizardGauges = EMPTY_GAUGE_MAP;
      this.dropwizardCounters = EMPTY_COUNTER_MAP;
      this.dropwizardHistograms = EMPTY_HISTOGRAM_MAP;
      this.dropwizardMeters = EMPTY_METER_MAP;
      this.dropwizardTimers = EMPTY_TIMER_MAP;
      this.metrics2System.register((String)Objects.requireNonNull(jmxContext), (String)Objects.requireNonNull(description), this);
   }

   public void getMetrics(MetricsCollector collector, boolean all) {
      MetricsRecordBuilder builder = collector.addRecord(this.recordName);
      if (null != this.context) {
         builder.setContext(this.context);
      }

      synchronized(this) {
         this.snapshotAllMetrics(builder);
      }

      this.metrics2Registry.snapshot(builder, all);
   }

   void snapshotAllMetrics(MetricsRecordBuilder builder) {
      try {
         for(Map.Entry gauge : this.dropwizardGauges.entrySet()) {
            MetricsInfo info = Interns.info((String)gauge.getKey(), "");
            Object o = ((Gauge)gauge.getValue()).getValue();
            if (o instanceof Integer) {
               builder.addGauge(info, (Integer)o);
            } else if (o instanceof Long) {
               builder.addGauge(info, (Long)o);
            } else if (o instanceof Float) {
               builder.addGauge(info, (Float)o);
            } else if (o instanceof Double) {
               builder.addGauge(info, (Double)o);
            } else {
               LOG.trace("Ignoring Gauge ({}) with unhandled type: {}", gauge.getKey(), o.getClass());
            }
         }
      } finally {
         this.dropwizardGauges = EMPTY_GAUGE_MAP;
      }

      try {
         for(Map.Entry counter : this.dropwizardCounters.entrySet()) {
            MetricsInfo info = Interns.info((String)counter.getKey(), "");
            builder.addCounter(info, ((Counter)counter.getValue()).getCount());
         }
      } finally {
         this.dropwizardCounters = EMPTY_COUNTER_MAP;
      }

      try {
         for(Map.Entry entry : this.dropwizardHistograms.entrySet()) {
            String name = (String)entry.getKey();
            Histogram histogram = (Histogram)entry.getValue();
            this.addSnapshot(builder, name, "", histogram.getSnapshot(), histogram.getCount());
         }
      } finally {
         this.dropwizardHistograms = EMPTY_HISTOGRAM_MAP;
      }

      try {
         for(Map.Entry meterEntry : this.dropwizardMeters.entrySet()) {
            String name = (String)meterEntry.getKey();
            Meter meter = (Meter)meterEntry.getValue();
            this.addMeter(builder, name, "", meter.getCount(), meter.getMeanRate(), meter.getOneMinuteRate(), meter.getFiveMinuteRate(), meter.getFifteenMinuteRate());
         }
      } finally {
         this.dropwizardMeters = EMPTY_METER_MAP;
      }

      try {
         for(Map.Entry timerEntry : this.dropwizardTimers.entrySet()) {
            String name = (String)timerEntry.getKey();
            Timer timer = (Timer)timerEntry.getValue();
            Snapshot snapshot = timer.getSnapshot();
            this.addMeter(builder, name, "", timer.getCount(), timer.getMeanRate(), timer.getOneMinuteRate(), timer.getFiveMinuteRate(), timer.getFifteenMinuteRate());
            this.addSnapshot(builder, name, "", snapshot);
         }
      } finally {
         this.dropwizardTimers = EMPTY_TIMER_MAP;
      }

      builder.tag(RATE_UNIT_LABEL, this.getRateUnit());
      builder.tag(DURATION_UNIT_LABEL, this.getDurationUnit());
   }

   private void addMeter(MetricsRecordBuilder builder, String name, String desc, long count, double meanRate, double oneMinuteRate, double fiveMinuteRate, double fifteenMinuteRate) {
      builder.addGauge(Interns.info(name + "_count", ""), count);
      builder.addGauge(Interns.info(name + "_mean_rate", ""), this.convertRate(meanRate));
      builder.addGauge(Interns.info(name + "_1min_rate", ""), this.convertRate(oneMinuteRate));
      builder.addGauge(Interns.info(name + "_5min_rate", ""), this.convertRate(fiveMinuteRate));
      builder.addGauge(Interns.info(name + "_15min_rate", ""), this.convertRate(fifteenMinuteRate));
   }

   private void addSnapshot(MetricsRecordBuilder builder, String name, String desc, Snapshot snapshot, long count) {
      builder.addGauge(Interns.info(name + "_count", desc), count);
      this.addSnapshot(builder, name, desc, snapshot);
   }

   private void addSnapshot(MetricsRecordBuilder builder, String name, String desc, Snapshot snapshot) {
      builder.addGauge(Interns.info(name + "_mean", desc), this.convertDuration(snapshot.getMean()));
      builder.addGauge(Interns.info(name + "_min", desc), this.convertDuration((double)snapshot.getMin()));
      builder.addGauge(Interns.info(name + "_max", desc), this.convertDuration((double)snapshot.getMax()));
      builder.addGauge(Interns.info(name + "_median", desc), this.convertDuration(snapshot.getMedian()));
      builder.addGauge(Interns.info(name + "_stddev", desc), this.convertDuration(snapshot.getStdDev()));
      builder.addGauge(Interns.info(name + "_75thpercentile", desc), this.convertDuration(snapshot.get75thPercentile()));
      builder.addGauge(Interns.info(name + "_95thpercentile", desc), this.convertDuration(snapshot.get95thPercentile()));
      builder.addGauge(Interns.info(name + "_98thpercentile", desc), this.convertDuration(snapshot.get98thPercentile()));
      builder.addGauge(Interns.info(name + "_99thpercentile", desc), this.convertDuration(snapshot.get99thPercentile()));
      builder.addGauge(Interns.info(name + "_999thpercentile", desc), this.convertDuration(snapshot.get999thPercentile()));
   }

   public void report(SortedMap gauges, SortedMap counters, SortedMap histograms, SortedMap meters, SortedMap timers) {
      this.dropwizardGauges = gauges;
      this.dropwizardCounters = counters;
      this.dropwizardHistograms = histograms;
      this.dropwizardMeters = meters;
      this.dropwizardTimers = timers;
   }

   protected String getRateUnit() {
      return "events/" + super.getRateUnit();
   }

   protected String getDurationUnit() {
      return super.getDurationUnit();
   }

   protected double convertDuration(double duration) {
      return super.convertDuration(duration);
   }

   protected double convertRate(double rate) {
      return super.convertRate(rate);
   }

   MetricsRegistry getMetrics2Registry() {
      return this.metrics2Registry;
   }

   MetricsSystem getMetrics2System() {
      return this.metrics2System;
   }

   String getRecordName() {
      return this.recordName;
   }

   String getContext() {
      return this.context;
   }

   SortedMap getDropwizardGauges() {
      return this.dropwizardGauges;
   }

   void setDropwizardGauges(SortedMap gauges) {
      this.dropwizardGauges = (SortedMap)Objects.requireNonNull(gauges);
   }

   SortedMap getDropwizardCounters() {
      return this.dropwizardCounters;
   }

   void setDropwizardCounters(SortedMap counters) {
      this.dropwizardCounters = counters;
   }

   SortedMap getDropwizardHistograms() {
      return this.dropwizardHistograms;
   }

   void setDropwizardHistograms(SortedMap histograms) {
      this.dropwizardHistograms = histograms;
   }

   SortedMap getDropwizardMeters() {
      return this.dropwizardMeters;
   }

   void setDropwizardMeters(SortedMap meters) {
      this.dropwizardMeters = meters;
   }

   SortedMap getDropwizardTimers() {
      return this.dropwizardTimers;
   }

   void setDropwizardTimers(SortedMap timers) {
      this.dropwizardTimers = timers;
   }

   protected void printQueueDebugMessage() {
      StringBuilder sb = new StringBuilder(64);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
      sb.append(sdf.format(new Date())).append(" ================================\n");
      sb.append("\n  Dropwizard gauges size: ").append(this.getDropwizardGauges().size());
      sb.append("\n  Dropwizard counters size: ").append(this.getDropwizardCounters().size());
      sb.append("\n  Dropwizard histograms size: ").append(this.getDropwizardHistograms().size());
      sb.append("\n  Dropwizard meters size: ").append(this.getDropwizardMeters().size());
      sb.append("\n  Dropwizard timers size: ").append(this.getDropwizardTimers().size()).append("\n");
      System.out.println(sb.toString());
   }

   public static class Builder {
      private final MetricRegistry registry;
      private MetricFilter filter;
      private TimeUnit rateUnit;
      private TimeUnit durationUnit;
      private String recordContext;

      private Builder(MetricRegistry registry) {
         this.registry = registry;
         this.filter = MetricFilter.ALL;
         this.rateUnit = TimeUnit.SECONDS;
         this.durationUnit = TimeUnit.MILLISECONDS;
      }

      public Builder convertRatesTo(TimeUnit rateUnit) {
         this.rateUnit = (TimeUnit)Objects.requireNonNull(rateUnit);
         return this;
      }

      public Builder convertDurationsTo(TimeUnit durationUnit) {
         this.durationUnit = (TimeUnit)Objects.requireNonNull(durationUnit);
         return this;
      }

      public Builder filter(MetricFilter filter) {
         this.filter = (MetricFilter)Objects.requireNonNull(filter);
         return this;
      }

      public Builder recordContext(String recordContext) {
         this.recordContext = (String)Objects.requireNonNull(recordContext);
         return this;
      }

      public HadoopMetrics2Reporter build(MetricsSystem metrics2System, String jmxContext, String description, String recordName) {
         return new HadoopMetrics2Reporter(this.registry, this.rateUnit, this.durationUnit, this.filter, metrics2System, (String)Objects.requireNonNull(jmxContext), description, recordName, this.recordContext);
      }
   }
}

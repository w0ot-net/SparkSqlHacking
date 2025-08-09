package com.codahale.metrics;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class Slf4jReporter extends ScheduledReporter {
   private final LoggerProxy loggerProxy;
   private final Marker marker;
   private final String prefix;

   public static Builder forRegistry(MetricRegistry registry) {
      return new Builder(registry);
   }

   private Slf4jReporter(MetricRegistry registry, LoggerProxy loggerProxy, Marker marker, String prefix, TimeUnit rateUnit, TimeUnit durationUnit, MetricFilter filter, ScheduledExecutorService executor, boolean shutdownExecutorOnStop, Set disabledMetricAttributes) {
      super(registry, "logger-reporter", filter, rateUnit, durationUnit, executor, shutdownExecutorOnStop, disabledMetricAttributes);
      this.loggerProxy = loggerProxy;
      this.marker = marker;
      this.prefix = prefix;
   }

   public void report(SortedMap gauges, SortedMap counters, SortedMap histograms, SortedMap meters, SortedMap timers) {
      if (this.loggerProxy.isEnabled(this.marker)) {
         StringBuilder b = new StringBuilder();

         for(Map.Entry entry : gauges.entrySet()) {
            this.logGauge(b, (String)entry.getKey(), (Gauge)entry.getValue());
         }

         for(Map.Entry entry : counters.entrySet()) {
            this.logCounter(b, (String)entry.getKey(), (Counter)entry.getValue());
         }

         for(Map.Entry entry : histograms.entrySet()) {
            this.logHistogram(b, (String)entry.getKey(), (Histogram)entry.getValue());
         }

         for(Map.Entry entry : meters.entrySet()) {
            this.logMeter(b, (String)entry.getKey(), (Meter)entry.getValue());
         }

         for(Map.Entry entry : timers.entrySet()) {
            this.logTimer(b, (String)entry.getKey(), (Timer)entry.getValue());
         }
      }

   }

   private void logTimer(StringBuilder b, String name, Timer timer) {
      Snapshot snapshot = timer.getSnapshot();
      b.setLength(0);
      b.append("type=TIMER");
      this.append(b, "name", this.prefix(name));
      this.appendCountIfEnabled(b, timer);
      MetricAttribute var10002 = MetricAttribute.MIN;
      Objects.requireNonNull(snapshot);
      this.appendLongDurationIfEnabled(b, var10002, snapshot::getMin);
      var10002 = MetricAttribute.MAX;
      Objects.requireNonNull(snapshot);
      this.appendLongDurationIfEnabled(b, var10002, snapshot::getMax);
      var10002 = MetricAttribute.MEAN;
      Objects.requireNonNull(snapshot);
      this.appendDoubleDurationIfEnabled(b, var10002, snapshot::getMean);
      var10002 = MetricAttribute.STDDEV;
      Objects.requireNonNull(snapshot);
      this.appendDoubleDurationIfEnabled(b, var10002, snapshot::getStdDev);
      var10002 = MetricAttribute.P50;
      Objects.requireNonNull(snapshot);
      this.appendDoubleDurationIfEnabled(b, var10002, snapshot::getMedian);
      var10002 = MetricAttribute.P75;
      Objects.requireNonNull(snapshot);
      this.appendDoubleDurationIfEnabled(b, var10002, snapshot::get75thPercentile);
      var10002 = MetricAttribute.P95;
      Objects.requireNonNull(snapshot);
      this.appendDoubleDurationIfEnabled(b, var10002, snapshot::get95thPercentile);
      var10002 = MetricAttribute.P98;
      Objects.requireNonNull(snapshot);
      this.appendDoubleDurationIfEnabled(b, var10002, snapshot::get98thPercentile);
      var10002 = MetricAttribute.P99;
      Objects.requireNonNull(snapshot);
      this.appendDoubleDurationIfEnabled(b, var10002, snapshot::get99thPercentile);
      var10002 = MetricAttribute.P999;
      Objects.requireNonNull(snapshot);
      this.appendDoubleDurationIfEnabled(b, var10002, snapshot::get999thPercentile);
      this.appendMetered(b, timer);
      this.append(b, "rate_unit", this.getRateUnit());
      this.append(b, "duration_unit", this.getDurationUnit());
      this.loggerProxy.log(this.marker, b.toString());
   }

   private void logMeter(StringBuilder b, String name, Meter meter) {
      b.setLength(0);
      b.append("type=METER");
      this.append(b, "name", this.prefix(name));
      this.appendCountIfEnabled(b, meter);
      this.appendMetered(b, meter);
      this.append(b, "rate_unit", this.getRateUnit());
      this.loggerProxy.log(this.marker, b.toString());
   }

   private void logHistogram(StringBuilder b, String name, Histogram histogram) {
      Snapshot snapshot = histogram.getSnapshot();
      b.setLength(0);
      b.append("type=HISTOGRAM");
      this.append(b, "name", this.prefix(name));
      this.appendCountIfEnabled(b, histogram);
      MetricAttribute var10002 = MetricAttribute.MIN;
      Objects.requireNonNull(snapshot);
      this.appendLongIfEnabled(b, var10002, snapshot::getMin);
      var10002 = MetricAttribute.MAX;
      Objects.requireNonNull(snapshot);
      this.appendLongIfEnabled(b, var10002, snapshot::getMax);
      var10002 = MetricAttribute.MEAN;
      Objects.requireNonNull(snapshot);
      this.appendDoubleIfEnabled(b, var10002, snapshot::getMean);
      var10002 = MetricAttribute.STDDEV;
      Objects.requireNonNull(snapshot);
      this.appendDoubleIfEnabled(b, var10002, snapshot::getStdDev);
      var10002 = MetricAttribute.P50;
      Objects.requireNonNull(snapshot);
      this.appendDoubleIfEnabled(b, var10002, snapshot::getMedian);
      var10002 = MetricAttribute.P75;
      Objects.requireNonNull(snapshot);
      this.appendDoubleIfEnabled(b, var10002, snapshot::get75thPercentile);
      var10002 = MetricAttribute.P95;
      Objects.requireNonNull(snapshot);
      this.appendDoubleIfEnabled(b, var10002, snapshot::get95thPercentile);
      var10002 = MetricAttribute.P98;
      Objects.requireNonNull(snapshot);
      this.appendDoubleIfEnabled(b, var10002, snapshot::get98thPercentile);
      var10002 = MetricAttribute.P99;
      Objects.requireNonNull(snapshot);
      this.appendDoubleIfEnabled(b, var10002, snapshot::get99thPercentile);
      var10002 = MetricAttribute.P999;
      Objects.requireNonNull(snapshot);
      this.appendDoubleIfEnabled(b, var10002, snapshot::get999thPercentile);
      this.loggerProxy.log(this.marker, b.toString());
   }

   private void logCounter(StringBuilder b, String name, Counter counter) {
      b.setLength(0);
      b.append("type=COUNTER");
      this.append(b, "name", this.prefix(name));
      this.append(b, MetricAttribute.COUNT.getCode(), counter.getCount());
      this.loggerProxy.log(this.marker, b.toString());
   }

   private void logGauge(StringBuilder b, String name, Gauge gauge) {
      b.setLength(0);
      b.append("type=GAUGE");
      this.append(b, "name", this.prefix(name));
      this.append(b, "value", gauge.getValue());
      this.loggerProxy.log(this.marker, b.toString());
   }

   private void appendLongDurationIfEnabled(StringBuilder b, MetricAttribute metricAttribute, Supplier durationSupplier) {
      if (!this.getDisabledMetricAttributes().contains(metricAttribute)) {
         this.append(b, metricAttribute.getCode(), this.convertDuration((double)(Long)durationSupplier.get()));
      }

   }

   private void appendDoubleDurationIfEnabled(StringBuilder b, MetricAttribute metricAttribute, Supplier durationSupplier) {
      if (!this.getDisabledMetricAttributes().contains(metricAttribute)) {
         this.append(b, metricAttribute.getCode(), this.convertDuration((Double)durationSupplier.get()));
      }

   }

   private void appendLongIfEnabled(StringBuilder b, MetricAttribute metricAttribute, Supplier valueSupplier) {
      if (!this.getDisabledMetricAttributes().contains(metricAttribute)) {
         this.append(b, metricAttribute.getCode(), valueSupplier.get());
      }

   }

   private void appendDoubleIfEnabled(StringBuilder b, MetricAttribute metricAttribute, Supplier valueSupplier) {
      if (!this.getDisabledMetricAttributes().contains(metricAttribute)) {
         this.append(b, metricAttribute.getCode(), valueSupplier.get());
      }

   }

   private void appendCountIfEnabled(StringBuilder b, Counting counting) {
      if (!this.getDisabledMetricAttributes().contains(MetricAttribute.COUNT)) {
         this.append(b, MetricAttribute.COUNT.getCode(), counting.getCount());
      }

   }

   private void appendMetered(StringBuilder b, Metered meter) {
      MetricAttribute var10002 = MetricAttribute.M1_RATE;
      Objects.requireNonNull(meter);
      this.appendRateIfEnabled(b, var10002, meter::getOneMinuteRate);
      var10002 = MetricAttribute.M5_RATE;
      Objects.requireNonNull(meter);
      this.appendRateIfEnabled(b, var10002, meter::getFiveMinuteRate);
      var10002 = MetricAttribute.M15_RATE;
      Objects.requireNonNull(meter);
      this.appendRateIfEnabled(b, var10002, meter::getFifteenMinuteRate);
      var10002 = MetricAttribute.MEAN_RATE;
      Objects.requireNonNull(meter);
      this.appendRateIfEnabled(b, var10002, meter::getMeanRate);
   }

   private void appendRateIfEnabled(StringBuilder b, MetricAttribute metricAttribute, Supplier rateSupplier) {
      if (!this.getDisabledMetricAttributes().contains(metricAttribute)) {
         this.append(b, metricAttribute.getCode(), this.convertRate((Double)rateSupplier.get()));
      }

   }

   private void append(StringBuilder b, String key, long value) {
      b.append(", ").append(key).append('=').append(value);
   }

   private void append(StringBuilder b, String key, double value) {
      b.append(", ").append(key).append('=').append(value);
   }

   private void append(StringBuilder b, String key, String value) {
      b.append(", ").append(key).append('=').append(value);
   }

   private void append(StringBuilder b, String key, Object value) {
      b.append(", ").append(key).append('=').append(value);
   }

   protected String getRateUnit() {
      return "events/" + super.getRateUnit();
   }

   private String prefix(String... components) {
      return MetricRegistry.name(this.prefix, components);
   }

   public static enum LoggingLevel {
      TRACE,
      DEBUG,
      INFO,
      WARN,
      ERROR;

      // $FF: synthetic method
      private static LoggingLevel[] $values() {
         return new LoggingLevel[]{TRACE, DEBUG, INFO, WARN, ERROR};
      }
   }

   public static class Builder {
      private final MetricRegistry registry;
      private Logger logger;
      private LoggingLevel loggingLevel;
      private Marker marker;
      private String prefix;
      private TimeUnit rateUnit;
      private TimeUnit durationUnit;
      private MetricFilter filter;
      private ScheduledExecutorService executor;
      private boolean shutdownExecutorOnStop;
      private Set disabledMetricAttributes;

      private Builder(MetricRegistry registry) {
         this.registry = registry;
         this.logger = LoggerFactory.getLogger("metrics");
         this.marker = null;
         this.prefix = "";
         this.rateUnit = TimeUnit.SECONDS;
         this.durationUnit = TimeUnit.MILLISECONDS;
         this.filter = MetricFilter.ALL;
         this.loggingLevel = Slf4jReporter.LoggingLevel.INFO;
         this.executor = null;
         this.shutdownExecutorOnStop = true;
         this.disabledMetricAttributes = Collections.emptySet();
      }

      public Builder shutdownExecutorOnStop(boolean shutdownExecutorOnStop) {
         this.shutdownExecutorOnStop = shutdownExecutorOnStop;
         return this;
      }

      public Builder scheduleOn(ScheduledExecutorService executor) {
         this.executor = executor;
         return this;
      }

      public Builder outputTo(Logger logger) {
         this.logger = logger;
         return this;
      }

      public Builder markWith(Marker marker) {
         this.marker = marker;
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

      public Builder withLoggingLevel(LoggingLevel loggingLevel) {
         this.loggingLevel = loggingLevel;
         return this;
      }

      public Builder disabledMetricAttributes(Set disabledMetricAttributes) {
         this.disabledMetricAttributes = disabledMetricAttributes;
         return this;
      }

      public Slf4jReporter build() {
         LoggerProxy loggerProxy;
         switch (this.loggingLevel) {
            case TRACE:
               loggerProxy = new TraceLoggerProxy(this.logger);
               break;
            case INFO:
               loggerProxy = new InfoLoggerProxy(this.logger);
               break;
            case WARN:
               loggerProxy = new WarnLoggerProxy(this.logger);
               break;
            case ERROR:
               loggerProxy = new ErrorLoggerProxy(this.logger);
               break;
            case DEBUG:
            default:
               loggerProxy = new DebugLoggerProxy(this.logger);
         }

         return new Slf4jReporter(this.registry, loggerProxy, this.marker, this.prefix, this.rateUnit, this.durationUnit, this.filter, this.executor, this.shutdownExecutorOnStop, this.disabledMetricAttributes);
      }
   }

   abstract static class LoggerProxy {
      protected final Logger logger;

      public LoggerProxy(Logger logger) {
         this.logger = logger;
      }

      abstract void log(Marker marker, String format);

      abstract boolean isEnabled(Marker marker);
   }

   private static class DebugLoggerProxy extends LoggerProxy {
      public DebugLoggerProxy(Logger logger) {
         super(logger);
      }

      public void log(Marker marker, String format) {
         this.logger.debug(marker, format);
      }

      public boolean isEnabled(Marker marker) {
         return this.logger.isDebugEnabled(marker);
      }
   }

   private static class TraceLoggerProxy extends LoggerProxy {
      public TraceLoggerProxy(Logger logger) {
         super(logger);
      }

      public void log(Marker marker, String format) {
         this.logger.trace(marker, format);
      }

      public boolean isEnabled(Marker marker) {
         return this.logger.isTraceEnabled(marker);
      }
   }

   private static class InfoLoggerProxy extends LoggerProxy {
      public InfoLoggerProxy(Logger logger) {
         super(logger);
      }

      public void log(Marker marker, String format) {
         this.logger.info(marker, format);
      }

      public boolean isEnabled(Marker marker) {
         return this.logger.isInfoEnabled(marker);
      }
   }

   private static class WarnLoggerProxy extends LoggerProxy {
      public WarnLoggerProxy(Logger logger) {
         super(logger);
      }

      public void log(Marker marker, String format) {
         this.logger.warn(marker, format);
      }

      public boolean isEnabled(Marker marker) {
         return this.logger.isWarnEnabled(marker);
      }
   }

   private static class ErrorLoggerProxy extends LoggerProxy {
      public ErrorLoggerProxy(Logger logger) {
         super(logger);
      }

      public void log(Marker marker, String format) {
         this.logger.error(marker, format);
      }

      public boolean isEnabled(Marker marker) {
         return this.logger.isErrorEnabled(marker);
      }
   }
}

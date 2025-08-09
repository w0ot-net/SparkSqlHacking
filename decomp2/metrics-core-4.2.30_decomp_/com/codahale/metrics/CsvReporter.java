package com.codahale.metrics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvReporter extends ScheduledReporter {
   private static final String DEFAULT_SEPARATOR = ",";
   private static final Logger LOGGER = LoggerFactory.getLogger(CsvReporter.class);
   private final File directory;
   private final Locale locale;
   private final String separator;
   private final Clock clock;
   private final CsvFileProvider csvFileProvider;
   private final String histogramFormat;
   private final String meterFormat;
   private final String timerFormat;
   private final String timerHeader;
   private final String meterHeader;
   private final String histogramHeader;

   public static Builder forRegistry(MetricRegistry registry) {
      return new Builder(registry);
   }

   private CsvReporter(MetricRegistry registry, File directory, Locale locale, String separator, TimeUnit rateUnit, TimeUnit durationUnit, Clock clock, MetricFilter filter, ScheduledExecutorService executor, boolean shutdownExecutorOnStop, CsvFileProvider csvFileProvider) {
      super(registry, "csv-reporter", filter, rateUnit, durationUnit, executor, shutdownExecutorOnStop);
      this.directory = directory;
      this.locale = locale;
      this.separator = separator;
      this.clock = clock;
      this.csvFileProvider = csvFileProvider;
      this.histogramFormat = String.join(separator, "%d", "%d", "%f", "%d", "%f", "%f", "%f", "%f", "%f", "%f", "%f");
      this.meterFormat = String.join(separator, "%d", "%f", "%f", "%f", "%f", "events/%s");
      this.timerFormat = String.join(separator, "%d", "%f", "%f", "%f", "%f", "%f", "%f", "%f", "%f", "%f", "%f", "%f", "%f", "%f", "%f", "calls/%s", "%s");
      this.timerHeader = String.join(separator, "count", "max", "mean", "min", "stddev", "p50", "p75", "p95", "p98", "p99", "p999", "mean_rate", "m1_rate", "m5_rate", "m15_rate", "rate_unit", "duration_unit");
      this.meterHeader = String.join(separator, "count", "mean_rate", "m1_rate", "m5_rate", "m15_rate", "rate_unit");
      this.histogramHeader = String.join(separator, "count", "max", "mean", "min", "stddev", "p50", "p75", "p95", "p98", "p99", "p999");
   }

   public void report(SortedMap gauges, SortedMap counters, SortedMap histograms, SortedMap meters, SortedMap timers) {
      long timestamp = TimeUnit.MILLISECONDS.toSeconds(this.clock.getTime());

      for(Map.Entry entry : gauges.entrySet()) {
         this.reportGauge(timestamp, (String)entry.getKey(), (Gauge)entry.getValue());
      }

      for(Map.Entry entry : counters.entrySet()) {
         this.reportCounter(timestamp, (String)entry.getKey(), (Counter)entry.getValue());
      }

      for(Map.Entry entry : histograms.entrySet()) {
         this.reportHistogram(timestamp, (String)entry.getKey(), (Histogram)entry.getValue());
      }

      for(Map.Entry entry : meters.entrySet()) {
         this.reportMeter(timestamp, (String)entry.getKey(), (Meter)entry.getValue());
      }

      for(Map.Entry entry : timers.entrySet()) {
         this.reportTimer(timestamp, (String)entry.getKey(), (Timer)entry.getValue());
      }

   }

   private void reportTimer(long timestamp, String name, Timer timer) {
      Snapshot snapshot = timer.getSnapshot();
      this.report(timestamp, name, this.timerHeader, this.timerFormat, timer.getCount(), this.convertDuration((double)snapshot.getMax()), this.convertDuration(snapshot.getMean()), this.convertDuration((double)snapshot.getMin()), this.convertDuration(snapshot.getStdDev()), this.convertDuration(snapshot.getMedian()), this.convertDuration(snapshot.get75thPercentile()), this.convertDuration(snapshot.get95thPercentile()), this.convertDuration(snapshot.get98thPercentile()), this.convertDuration(snapshot.get99thPercentile()), this.convertDuration(snapshot.get999thPercentile()), this.convertRate(timer.getMeanRate()), this.convertRate(timer.getOneMinuteRate()), this.convertRate(timer.getFiveMinuteRate()), this.convertRate(timer.getFifteenMinuteRate()), this.getRateUnit(), this.getDurationUnit());
   }

   private void reportMeter(long timestamp, String name, Meter meter) {
      this.report(timestamp, name, this.meterHeader, this.meterFormat, meter.getCount(), this.convertRate(meter.getMeanRate()), this.convertRate(meter.getOneMinuteRate()), this.convertRate(meter.getFiveMinuteRate()), this.convertRate(meter.getFifteenMinuteRate()), this.getRateUnit());
   }

   private void reportHistogram(long timestamp, String name, Histogram histogram) {
      Snapshot snapshot = histogram.getSnapshot();
      this.report(timestamp, name, this.histogramHeader, this.histogramFormat, histogram.getCount(), snapshot.getMax(), snapshot.getMean(), snapshot.getMin(), snapshot.getStdDev(), snapshot.getMedian(), snapshot.get75thPercentile(), snapshot.get95thPercentile(), snapshot.get98thPercentile(), snapshot.get99thPercentile(), snapshot.get999thPercentile());
   }

   private void reportCounter(long timestamp, String name, Counter counter) {
      this.report(timestamp, name, "count", "%d", counter.getCount());
   }

   private void reportGauge(long timestamp, String name, Gauge gauge) {
      this.report(timestamp, name, "value", "%s", gauge.getValue());
   }

   private void report(long timestamp, String name, String header, String line, Object... values) {
      try {
         File file = this.csvFileProvider.getFile(this.directory, name);
         boolean fileAlreadyExists = file.exists();
         if (fileAlreadyExists || file.createNewFile()) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8));

            try {
               if (!fileAlreadyExists) {
                  out.println("t" + this.separator + header);
               }

               out.printf(this.locale, String.format(this.locale, "%d" + this.separator + "%s%n", timestamp, line), values);
            } catch (Throwable var13) {
               try {
                  out.close();
               } catch (Throwable var12) {
                  var13.addSuppressed(var12);
               }

               throw var13;
            }

            out.close();
         }
      } catch (IOException e) {
         LOGGER.warn("Error writing to {}", name, e);
      }

   }

   protected String sanitize(String name) {
      return name;
   }

   public static class Builder {
      private final MetricRegistry registry;
      private Locale locale;
      private String separator;
      private TimeUnit rateUnit;
      private TimeUnit durationUnit;
      private Clock clock;
      private MetricFilter filter;
      private ScheduledExecutorService executor;
      private boolean shutdownExecutorOnStop;
      private CsvFileProvider csvFileProvider;

      private Builder(MetricRegistry registry) {
         this.registry = registry;
         this.locale = Locale.getDefault();
         this.separator = ",";
         this.rateUnit = TimeUnit.SECONDS;
         this.durationUnit = TimeUnit.MILLISECONDS;
         this.clock = Clock.defaultClock();
         this.filter = MetricFilter.ALL;
         this.executor = null;
         this.shutdownExecutorOnStop = true;
         this.csvFileProvider = new FixedNameCsvFileProvider();
      }

      public Builder shutdownExecutorOnStop(boolean shutdownExecutorOnStop) {
         this.shutdownExecutorOnStop = shutdownExecutorOnStop;
         return this;
      }

      public Builder scheduleOn(ScheduledExecutorService executor) {
         this.executor = executor;
         return this;
      }

      public Builder formatFor(Locale locale) {
         this.locale = locale;
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

      public Builder withSeparator(String separator) {
         this.separator = separator;
         return this;
      }

      public Builder withClock(Clock clock) {
         this.clock = clock;
         return this;
      }

      public Builder filter(MetricFilter filter) {
         this.filter = filter;
         return this;
      }

      public Builder withCsvFileProvider(CsvFileProvider csvFileProvider) {
         this.csvFileProvider = csvFileProvider;
         return this;
      }

      public CsvReporter build(File directory) {
         return new CsvReporter(this.registry, directory, this.locale, this.separator, this.rateUnit, this.durationUnit, this.clock, this.filter, this.executor, this.shutdownExecutorOnStop, this.csvFileProvider);
      }
   }
}

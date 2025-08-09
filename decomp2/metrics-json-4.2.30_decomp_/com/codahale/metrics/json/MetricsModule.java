package com.codahale.metrics.json;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MetricsModule extends Module {
   static final Version VERSION = new Version(4, 0, 0, "", "io.dropwizard.metrics", "metrics-json");
   protected final TimeUnit rateUnit;
   protected final TimeUnit durationUnit;
   protected final boolean showSamples;
   protected final MetricFilter filter;

   public MetricsModule(TimeUnit rateUnit, TimeUnit durationUnit, boolean showSamples) {
      this(rateUnit, durationUnit, showSamples, MetricFilter.ALL);
   }

   public MetricsModule(TimeUnit rateUnit, TimeUnit durationUnit, boolean showSamples, MetricFilter filter) {
      this.rateUnit = rateUnit;
      this.durationUnit = durationUnit;
      this.showSamples = showSamples;
      this.filter = filter;
   }

   public String getModuleName() {
      return "metrics";
   }

   public Version version() {
      return VERSION;
   }

   public void setupModule(Module.SetupContext context) {
      context.addSerializers(new SimpleSerializers(Arrays.asList(new GaugeSerializer(), new CounterSerializer(), new HistogramSerializer(this.showSamples), new MeterSerializer(this.rateUnit), new TimerSerializer(this.rateUnit, this.durationUnit, this.showSamples), new MetricRegistrySerializer(this.filter))));
   }

   private static String calculateRateUnit(TimeUnit unit, String name) {
      String s = unit.toString().toLowerCase(Locale.US);
      return name + '/' + s.substring(0, s.length() - 1);
   }

   private static class GaugeSerializer extends StdSerializer {
      private static final long serialVersionUID = 1L;

      private GaugeSerializer() {
         super(Gauge.class);
      }

      public void serialize(Gauge gauge, JsonGenerator json, SerializerProvider provider) throws IOException {
         json.writeStartObject();

         try {
            Object value = gauge.getValue();
            json.writeObjectField("value", value);
         } catch (RuntimeException e) {
            json.writeObjectField("error", e.toString());
         }

         json.writeEndObject();
      }
   }

   private static class CounterSerializer extends StdSerializer {
      private static final long serialVersionUID = 1L;

      private CounterSerializer() {
         super(Counter.class);
      }

      public void serialize(Counter counter, JsonGenerator json, SerializerProvider provider) throws IOException {
         json.writeStartObject();
         json.writeNumberField("count", counter.getCount());
         json.writeEndObject();
      }
   }

   private static class HistogramSerializer extends StdSerializer {
      private static final long serialVersionUID = 1L;
      private final boolean showSamples;

      private HistogramSerializer(boolean showSamples) {
         super(Histogram.class);
         this.showSamples = showSamples;
      }

      public void serialize(Histogram histogram, JsonGenerator json, SerializerProvider provider) throws IOException {
         json.writeStartObject();
         Snapshot snapshot = histogram.getSnapshot();
         json.writeNumberField("count", histogram.getCount());
         json.writeNumberField("max", snapshot.getMax());
         json.writeNumberField("mean", snapshot.getMean());
         json.writeNumberField("min", snapshot.getMin());
         json.writeNumberField("p50", snapshot.getMedian());
         json.writeNumberField("p75", snapshot.get75thPercentile());
         json.writeNumberField("p95", snapshot.get95thPercentile());
         json.writeNumberField("p98", snapshot.get98thPercentile());
         json.writeNumberField("p99", snapshot.get99thPercentile());
         json.writeNumberField("p999", snapshot.get999thPercentile());
         if (this.showSamples) {
            json.writeObjectField("values", snapshot.getValues());
         }

         json.writeNumberField("stddev", snapshot.getStdDev());
         json.writeEndObject();
      }
   }

   private static class MeterSerializer extends StdSerializer {
      private static final long serialVersionUID = 1L;
      private final String rateUnit;
      private final double rateFactor;

      public MeterSerializer(TimeUnit rateUnit) {
         super(Meter.class);
         this.rateFactor = (double)rateUnit.toSeconds(1L);
         this.rateUnit = MetricsModule.calculateRateUnit(rateUnit, "events");
      }

      public void serialize(Meter meter, JsonGenerator json, SerializerProvider provider) throws IOException {
         json.writeStartObject();
         json.writeNumberField("count", meter.getCount());
         json.writeNumberField("m15_rate", meter.getFifteenMinuteRate() * this.rateFactor);
         json.writeNumberField("m1_rate", meter.getOneMinuteRate() * this.rateFactor);
         json.writeNumberField("m5_rate", meter.getFiveMinuteRate() * this.rateFactor);
         json.writeNumberField("mean_rate", meter.getMeanRate() * this.rateFactor);
         json.writeStringField("units", this.rateUnit);
         json.writeEndObject();
      }
   }

   private static class TimerSerializer extends StdSerializer {
      private static final long serialVersionUID = 1L;
      private final String rateUnit;
      private final double rateFactor;
      private final String durationUnit;
      private final double durationFactor;
      private final boolean showSamples;

      private TimerSerializer(TimeUnit rateUnit, TimeUnit durationUnit, boolean showSamples) {
         super(Timer.class);
         this.rateUnit = MetricsModule.calculateRateUnit(rateUnit, "calls");
         this.rateFactor = (double)rateUnit.toSeconds(1L);
         this.durationUnit = durationUnit.toString().toLowerCase(Locale.US);
         this.durationFactor = (double)1.0F / (double)durationUnit.toNanos(1L);
         this.showSamples = showSamples;
      }

      public void serialize(Timer timer, JsonGenerator json, SerializerProvider provider) throws IOException {
         json.writeStartObject();
         Snapshot snapshot = timer.getSnapshot();
         json.writeNumberField("count", timer.getCount());
         json.writeNumberField("max", (double)snapshot.getMax() * this.durationFactor);
         json.writeNumberField("mean", snapshot.getMean() * this.durationFactor);
         json.writeNumberField("min", (double)snapshot.getMin() * this.durationFactor);
         json.writeNumberField("p50", snapshot.getMedian() * this.durationFactor);
         json.writeNumberField("p75", snapshot.get75thPercentile() * this.durationFactor);
         json.writeNumberField("p95", snapshot.get95thPercentile() * this.durationFactor);
         json.writeNumberField("p98", snapshot.get98thPercentile() * this.durationFactor);
         json.writeNumberField("p99", snapshot.get99thPercentile() * this.durationFactor);
         json.writeNumberField("p999", snapshot.get999thPercentile() * this.durationFactor);
         if (this.showSamples) {
            long[] values = snapshot.getValues();
            double[] scaledValues = new double[values.length];

            for(int i = 0; i < values.length; ++i) {
               scaledValues[i] = (double)values[i] * this.durationFactor;
            }

            json.writeObjectField("values", scaledValues);
         }

         json.writeNumberField("stddev", snapshot.getStdDev() * this.durationFactor);
         json.writeNumberField("m15_rate", timer.getFifteenMinuteRate() * this.rateFactor);
         json.writeNumberField("m1_rate", timer.getOneMinuteRate() * this.rateFactor);
         json.writeNumberField("m5_rate", timer.getFiveMinuteRate() * this.rateFactor);
         json.writeNumberField("mean_rate", timer.getMeanRate() * this.rateFactor);
         json.writeStringField("duration_units", this.durationUnit);
         json.writeStringField("rate_units", this.rateUnit);
         json.writeEndObject();
      }
   }

   private static class MetricRegistrySerializer extends StdSerializer {
      private static final long serialVersionUID = 1L;
      private final MetricFilter filter;

      private MetricRegistrySerializer(MetricFilter filter) {
         super(MetricRegistry.class);
         this.filter = filter;
      }

      public void serialize(MetricRegistry registry, JsonGenerator json, SerializerProvider provider) throws IOException {
         json.writeStartObject();
         json.writeStringField("version", MetricsModule.VERSION.toString());
         json.writeObjectField("gauges", registry.getGauges(this.filter));
         json.writeObjectField("counters", registry.getCounters(this.filter));
         json.writeObjectField("histograms", registry.getHistograms(this.filter));
         json.writeObjectField("meters", registry.getMeters(this.filter));
         json.writeObjectField("timers", registry.getTimers(this.filter));
         json.writeEndObject();
      }
   }
}

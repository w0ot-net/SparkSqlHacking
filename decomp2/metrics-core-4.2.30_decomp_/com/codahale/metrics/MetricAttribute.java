package com.codahale.metrics;

public enum MetricAttribute {
   MAX("max"),
   MEAN("mean"),
   MIN("min"),
   STDDEV("stddev"),
   P50("p50"),
   P75("p75"),
   P95("p95"),
   P98("p98"),
   P99("p99"),
   P999("p999"),
   COUNT("count"),
   M1_RATE("m1_rate"),
   M5_RATE("m5_rate"),
   M15_RATE("m15_rate"),
   MEAN_RATE("mean_rate");

   private final String code;

   private MetricAttribute(String code) {
      this.code = code;
   }

   public String getCode() {
      return this.code;
   }

   // $FF: synthetic method
   private static MetricAttribute[] $values() {
      return new MetricAttribute[]{MAX, MEAN, MIN, STDDEV, P50, P75, P95, P98, P99, P999, COUNT, M1_RATE, M5_RATE, M15_RATE, MEAN_RATE};
   }
}

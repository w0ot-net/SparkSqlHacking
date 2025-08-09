package com.codahale.metrics;

public interface MetricFilter {
   MetricFilter ALL = (name, metric) -> true;

   static MetricFilter startsWith(String prefix) {
      return (name, metric) -> name.startsWith(prefix);
   }

   static MetricFilter endsWith(String suffix) {
      return (name, metric) -> name.endsWith(suffix);
   }

   static MetricFilter contains(String substring) {
      return (name, metric) -> name.contains(substring);
   }

   boolean matches(String name, Metric metric);
}

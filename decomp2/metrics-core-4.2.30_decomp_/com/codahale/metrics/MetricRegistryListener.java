package com.codahale.metrics;

import java.util.EventListener;

public interface MetricRegistryListener extends EventListener {
   void onGaugeAdded(String name, Gauge gauge);

   void onGaugeRemoved(String name);

   void onCounterAdded(String name, Counter counter);

   void onCounterRemoved(String name);

   void onHistogramAdded(String name, Histogram histogram);

   void onHistogramRemoved(String name);

   void onMeterAdded(String name, Meter meter);

   void onMeterRemoved(String name);

   void onTimerAdded(String name, Timer timer);

   void onTimerRemoved(String name);

   public abstract static class Base implements MetricRegistryListener {
      public void onGaugeAdded(String name, Gauge gauge) {
      }

      public void onGaugeRemoved(String name) {
      }

      public void onCounterAdded(String name, Counter counter) {
      }

      public void onCounterRemoved(String name) {
      }

      public void onHistogramAdded(String name, Histogram histogram) {
      }

      public void onHistogramRemoved(String name) {
      }

      public void onMeterAdded(String name, Meter meter) {
      }

      public void onMeterRemoved(String name) {
      }

      public void onTimerAdded(String name, Timer timer) {
      }

      public void onTimerRemoved(String name) {
      }
   }
}

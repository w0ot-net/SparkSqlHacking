package org.apache.zookeeper.metrics;

public interface MetricsContext {
   MetricsContext getContext(String var1);

   Counter getCounter(String var1);

   CounterSet getCounterSet(String var1);

   void registerGauge(String var1, Gauge var2);

   void unregisterGauge(String var1);

   void registerGaugeSet(String var1, GaugeSet var2);

   void unregisterGaugeSet(String var1);

   Summary getSummary(String var1, DetailLevel var2);

   SummarySet getSummarySet(String var1, DetailLevel var2);

   public static enum DetailLevel {
      BASIC,
      ADVANCED;
   }
}

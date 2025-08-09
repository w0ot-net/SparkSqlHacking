package org.apache.hadoop.hive.common.metrics.common;

public interface Metrics {
   void close() throws Exception;

   void startStoredScope(String var1);

   void endStoredScope(String var1);

   MetricsScope createScope(String var1);

   void endScope(MetricsScope var1);

   Long incrementCounter(String var1);

   Long incrementCounter(String var1, long var2);

   Long decrementCounter(String var1);

   Long decrementCounter(String var1, long var2);

   void addGauge(String var1, MetricsVariable var2);

   void addRatio(String var1, MetricsVariable var2, MetricsVariable var3);

   void markMeter(String var1);
}

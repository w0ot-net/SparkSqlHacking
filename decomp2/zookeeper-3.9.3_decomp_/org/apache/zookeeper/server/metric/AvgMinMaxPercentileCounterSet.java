package org.apache.zookeeper.server.metric;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.zookeeper.metrics.SummarySet;

public class AvgMinMaxPercentileCounterSet extends Metric implements SummarySet {
   private final String name;
   private ConcurrentHashMap counters = new ConcurrentHashMap();

   public AvgMinMaxPercentileCounterSet(String name) {
      this.name = name;
   }

   private AvgMinMaxPercentileCounter getCounterForKey(String key) {
      AvgMinMaxPercentileCounter counter = (AvgMinMaxPercentileCounter)this.counters.get(key);
      if (counter == null) {
         this.counters.putIfAbsent(key, new AvgMinMaxPercentileCounter(key + "_" + this.name));
         counter = (AvgMinMaxPercentileCounter)this.counters.get(key);
      }

      return counter;
   }

   public void addDataPoint(String key, long value) {
      this.getCounterForKey(key).addDataPoint(value);
   }

   public void resetMax() {
      for(Map.Entry entry : this.counters.entrySet()) {
         ((AvgMinMaxPercentileCounter)entry.getValue()).resetMax();
      }

   }

   public void reset() {
      for(Map.Entry entry : this.counters.entrySet()) {
         ((AvgMinMaxPercentileCounter)entry.getValue()).reset();
      }

   }

   public void add(String key, long value) {
      this.addDataPoint(key, value);
   }

   public Map values() {
      Map<String, Object> m = new LinkedHashMap();

      for(Map.Entry entry : this.counters.entrySet()) {
         m.putAll(((AvgMinMaxPercentileCounter)entry.getValue()).values());
      }

      return m;
   }
}

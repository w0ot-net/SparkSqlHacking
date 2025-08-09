package org.apache.zookeeper.server.metric;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.zookeeper.metrics.SummarySet;

public class AvgMinMaxCounterSet extends Metric implements SummarySet {
   private final String name;
   private ConcurrentHashMap counters = new ConcurrentHashMap();

   public AvgMinMaxCounterSet(String name) {
      this.name = name;
   }

   private AvgMinMaxCounter getCounterForKey(String key) {
      return (AvgMinMaxCounter)this.counters.computeIfAbsent(key, (k) -> new AvgMinMaxCounter(k + "_" + this.name));
   }

   public void addDataPoint(String key, long value) {
      this.getCounterForKey(key).addDataPoint(value);
   }

   public void resetMax() {
      for(Map.Entry entry : this.counters.entrySet()) {
         ((AvgMinMaxCounter)entry.getValue()).resetMax();
      }

   }

   public void reset() {
      for(Map.Entry entry : this.counters.entrySet()) {
         ((AvgMinMaxCounter)entry.getValue()).reset();
      }

   }

   public void add(String key, long value) {
      this.addDataPoint(key, value);
   }

   public Map values() {
      Map<String, Object> m = new LinkedHashMap();

      for(Map.Entry entry : this.counters.entrySet()) {
         m.putAll(((AvgMinMaxCounter)entry.getValue()).values());
      }

      return m;
   }
}

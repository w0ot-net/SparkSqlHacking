package org.apache.zookeeper.server.metric;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.zookeeper.metrics.CounterSet;

public class SimpleCounterSet extends Metric implements CounterSet {
   private final String name;
   private final ConcurrentHashMap counters = new ConcurrentHashMap();

   public SimpleCounterSet(String name) {
      this.name = name;
   }

   public void add(String key, long delta) {
      SimpleCounter counter = (SimpleCounter)this.counters.computeIfAbsent(key, (k) -> new SimpleCounter(k + "_" + this.name));
      counter.add(delta);
   }

   public void reset() {
      this.counters.values().forEach(SimpleCounter::reset);
   }

   public Map values() {
      Map<String, Object> m = new LinkedHashMap();
      this.counters.values().forEach((counter) -> m.putAll(counter.values()));
      return m;
   }
}

package org.apache.zookeeper.server.metric;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.zookeeper.metrics.Counter;

public class SimpleCounter extends Metric implements Counter {
   private final String name;
   private final AtomicLong counter = new AtomicLong();

   public SimpleCounter(String name) {
      this.name = name;
   }

   public void add(long value) {
      this.counter.addAndGet(value);
   }

   public void reset() {
      this.counter.set(0L);
   }

   public long get() {
      return this.counter.get();
   }

   public Map values() {
      Map<String, Object> m = new LinkedHashMap();
      m.put(this.name, this.get());
      return m;
   }
}

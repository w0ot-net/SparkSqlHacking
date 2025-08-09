package org.apache.zookeeper.server.metric;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.zookeeper.metrics.Summary;

public class AvgMinMaxCounter extends Metric implements Summary {
   private final String name;
   private final AtomicLong total = new AtomicLong();
   private final AtomicLong min = new AtomicLong(Long.MAX_VALUE);
   private final AtomicLong max = new AtomicLong(Long.MIN_VALUE);
   private final AtomicLong count = new AtomicLong();

   public AvgMinMaxCounter(String name) {
      this.name = name;
   }

   public void addDataPoint(long value) {
      this.total.addAndGet(value);
      this.count.incrementAndGet();
      this.setMin(value);
      this.setMax(value);
   }

   private void setMax(long value) {
      long current;
      while(value > (current = this.max.get()) && !this.max.compareAndSet(current, value)) {
      }

   }

   private void setMin(long value) {
      long current;
      while(value < (current = this.min.get()) && !this.min.compareAndSet(current, value)) {
      }

   }

   public double getAvg() {
      long currentCount = this.count.get();
      long currentTotal = this.total.get();
      if (currentCount > 0L) {
         double avgLatency = (double)currentTotal / (double)currentCount;
         BigDecimal bg = new BigDecimal(avgLatency);
         return bg.setScale(4, RoundingMode.HALF_UP).doubleValue();
      } else {
         return (double)0.0F;
      }
   }

   public long getCount() {
      return this.count.get();
   }

   public long getMax() {
      long current = this.max.get();
      return current == Long.MIN_VALUE ? 0L : current;
   }

   public long getMin() {
      long current = this.min.get();
      return current == Long.MAX_VALUE ? 0L : current;
   }

   public long getTotal() {
      return this.total.get();
   }

   public void resetMax() {
      this.max.set(this.getMin());
   }

   public void reset() {
      this.count.set(0L);
      this.total.set(0L);
      this.min.set(Long.MAX_VALUE);
      this.max.set(Long.MIN_VALUE);
   }

   public void add(long value) {
      this.addDataPoint(value);
   }

   public Map values() {
      Map<String, Object> m = new LinkedHashMap();
      m.put("avg_" + this.name, this.getAvg());
      m.put("min_" + this.name, this.getMin());
      m.put("max_" + this.name, this.getMax());
      m.put("cnt_" + this.name, this.getCount());
      m.put("sum_" + this.name, this.getTotal());
      return m;
   }
}

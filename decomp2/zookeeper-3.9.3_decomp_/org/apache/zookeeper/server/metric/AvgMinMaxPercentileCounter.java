package org.apache.zookeeper.server.metric;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.Reservoir;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.UniformSnapshot;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import org.apache.zookeeper.metrics.Summary;

public class AvgMinMaxPercentileCounter extends Metric implements Summary {
   private final String name;
   private final AvgMinMaxCounter counter;
   private final ResettableUniformReservoir reservoir;
   private final Histogram histogram;

   public AvgMinMaxPercentileCounter(String name) {
      this.name = name;
      this.counter = new AvgMinMaxCounter(this.name);
      this.reservoir = new ResettableUniformReservoir();
      this.histogram = new Histogram(this.reservoir);
   }

   public void addDataPoint(long value) {
      this.counter.add(value);
      this.histogram.update(value);
   }

   public void resetMax() {
      this.counter.resetMax();
   }

   public void reset() {
      this.counter.reset();
      this.reservoir.reset();
   }

   public void add(long value) {
      this.addDataPoint(value);
   }

   public Map values() {
      Map<String, Object> m = new LinkedHashMap();
      m.putAll(this.counter.values());
      m.put("p50_" + this.name, Math.round(this.histogram.getSnapshot().getMedian()));
      m.put("p95_" + this.name, Math.round(this.histogram.getSnapshot().get95thPercentile()));
      m.put("p99_" + this.name, Math.round(this.histogram.getSnapshot().get99thPercentile()));
      m.put("p999_" + this.name, Math.round(this.histogram.getSnapshot().get999thPercentile()));
      return m;
   }

   static class ResettableUniformReservoir implements Reservoir {
      private static final int DEFAULT_SIZE = 4096;
      private static final int BITS_PER_LONG = 63;
      private final AtomicLong count = new AtomicLong();
      private volatile AtomicLongArray values = new AtomicLongArray(4096);

      public int size() {
         long c = this.count.get();
         return c > (long)this.values.length() ? this.values.length() : (int)c;
      }

      public void update(long value) {
         long c = this.count.incrementAndGet();
         if (c <= (long)this.values.length()) {
            this.values.set((int)c - 1, value);
         } else {
            long r = nextLong(c);
            if (r < (long)this.values.length()) {
               this.values.set((int)r, value);
            }
         }

      }

      private static long nextLong(long n) {
         long bits;
         long val;
         do {
            bits = ThreadLocalRandom.current().nextLong() & Long.MAX_VALUE;
            val = bits % n;
         } while(bits - val + (n - 1L) < 0L);

         return val;
      }

      public Snapshot getSnapshot() {
         int s = this.size();
         List<Long> copy = new ArrayList(s);

         for(int i = 0; i < s; ++i) {
            copy.add(this.values.get(i));
         }

         return new UniformSnapshot(copy);
      }

      public void reset() {
         this.count.set(0L);
         this.values = new AtomicLongArray(4096);
      }
   }
}

package com.codahale.metrics.jvm;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.RatioGauge;
import com.codahale.metrics.RatioGauge.Ratio;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MemoryUsageGaugeSet implements MetricSet {
   private static final Pattern WHITESPACE = Pattern.compile("[\\s]+");
   private final MemoryMXBean mxBean;
   private final List memoryPools;

   public MemoryUsageGaugeSet() {
      this(ManagementFactory.getMemoryMXBean(), ManagementFactory.getMemoryPoolMXBeans());
   }

   public MemoryUsageGaugeSet(MemoryMXBean mxBean, Collection memoryPools) {
      this.mxBean = mxBean;
      this.memoryPools = new ArrayList(memoryPools);
   }

   public Map getMetrics() {
      Map<String, Metric> gauges = new HashMap();
      gauges.put("total.init", (Gauge)() -> this.mxBean.getHeapMemoryUsage().getInit() + this.mxBean.getNonHeapMemoryUsage().getInit());
      gauges.put("total.used", (Gauge)() -> this.mxBean.getHeapMemoryUsage().getUsed() + this.mxBean.getNonHeapMemoryUsage().getUsed());
      gauges.put("total.max", (Gauge)() -> this.mxBean.getNonHeapMemoryUsage().getMax() == -1L ? -1L : this.mxBean.getHeapMemoryUsage().getMax() + this.mxBean.getNonHeapMemoryUsage().getMax());
      gauges.put("total.committed", (Gauge)() -> this.mxBean.getHeapMemoryUsage().getCommitted() + this.mxBean.getNonHeapMemoryUsage().getCommitted());
      gauges.put("heap.init", (Gauge)() -> this.mxBean.getHeapMemoryUsage().getInit());
      gauges.put("heap.used", (Gauge)() -> this.mxBean.getHeapMemoryUsage().getUsed());
      gauges.put("heap.max", (Gauge)() -> this.mxBean.getHeapMemoryUsage().getMax());
      gauges.put("heap.committed", (Gauge)() -> this.mxBean.getHeapMemoryUsage().getCommitted());
      gauges.put("heap.usage", new RatioGauge() {
         protected RatioGauge.Ratio getRatio() {
            MemoryUsage usage = MemoryUsageGaugeSet.this.mxBean.getHeapMemoryUsage();
            return Ratio.of((double)usage.getUsed(), (double)usage.getMax());
         }
      });
      gauges.put("non-heap.init", (Gauge)() -> this.mxBean.getNonHeapMemoryUsage().getInit());
      gauges.put("non-heap.used", (Gauge)() -> this.mxBean.getNonHeapMemoryUsage().getUsed());
      gauges.put("non-heap.max", (Gauge)() -> this.mxBean.getNonHeapMemoryUsage().getMax());
      gauges.put("non-heap.committed", (Gauge)() -> this.mxBean.getNonHeapMemoryUsage().getCommitted());
      gauges.put("non-heap.usage", new RatioGauge() {
         protected RatioGauge.Ratio getRatio() {
            MemoryUsage usage = MemoryUsageGaugeSet.this.mxBean.getNonHeapMemoryUsage();
            return Ratio.of((double)usage.getUsed(), usage.getMax() == -1L ? (double)usage.getCommitted() : (double)usage.getMax());
         }
      });

      for(final MemoryPoolMXBean pool : this.memoryPools) {
         String poolName = MetricRegistry.name("pools", new String[]{WHITESPACE.matcher(pool.getName()).replaceAll("-")});
         gauges.put(MetricRegistry.name(poolName, new String[]{"usage"}), new RatioGauge() {
            protected RatioGauge.Ratio getRatio() {
               MemoryUsage usage = pool.getUsage();
               return Ratio.of((double)usage.getUsed(), usage.getMax() == -1L ? (double)usage.getCommitted() : (double)usage.getMax());
            }
         });
         gauges.put(MetricRegistry.name(poolName, new String[]{"max"}), (Gauge)() -> pool.getUsage().getMax());
         gauges.put(MetricRegistry.name(poolName, new String[]{"used"}), (Gauge)() -> pool.getUsage().getUsed());
         gauges.put(MetricRegistry.name(poolName, new String[]{"committed"}), (Gauge)() -> pool.getUsage().getCommitted());
         if (pool.getCollectionUsage() != null) {
            gauges.put(MetricRegistry.name(poolName, new String[]{"used-after-gc"}), (Gauge)() -> pool.getCollectionUsage().getUsed());
         }

         gauges.put(MetricRegistry.name(poolName, new String[]{"init"}), (Gauge)() -> pool.getUsage().getInit());
      }

      return Collections.unmodifiableMap(gauges);
   }
}

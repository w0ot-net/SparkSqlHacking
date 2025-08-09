package com.codahale.metrics.jvm;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class GarbageCollectorMetricSet implements MetricSet {
   private static final Pattern WHITESPACE = Pattern.compile("[\\s]+");
   private final List garbageCollectors;

   public GarbageCollectorMetricSet() {
      this(ManagementFactory.getGarbageCollectorMXBeans());
   }

   public GarbageCollectorMetricSet(Collection garbageCollectors) {
      this.garbageCollectors = new ArrayList(garbageCollectors);
   }

   public Map getMetrics() {
      Map<String, Metric> gauges = new HashMap();

      for(GarbageCollectorMXBean gc : this.garbageCollectors) {
         String name = WHITESPACE.matcher(gc.getName()).replaceAll("-");
         String var10001 = MetricRegistry.name(name, new String[]{"count"});
         Objects.requireNonNull(gc);
         gauges.put(var10001, gc::getCollectionCount);
         var10001 = MetricRegistry.name(name, new String[]{"time"});
         Objects.requireNonNull(gc);
         gauges.put(var10001, gc::getCollectionTime);
      }

      return Collections.unmodifiableMap(gauges);
   }
}

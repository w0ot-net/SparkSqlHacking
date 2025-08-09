package com.codahale.metrics.jvm;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BufferPoolMetricSet implements MetricSet {
   private static final Logger LOGGER = LoggerFactory.getLogger(BufferPoolMetricSet.class);
   private static final String[] ATTRIBUTES = new String[]{"Count", "MemoryUsed", "TotalCapacity"};
   private static final String[] NAMES = new String[]{"count", "used", "capacity"};
   private static final String[] POOLS = new String[]{"direct", "mapped"};
   private final MBeanServer mBeanServer;

   public BufferPoolMetricSet(MBeanServer mBeanServer) {
      this.mBeanServer = mBeanServer;
   }

   public Map getMetrics() {
      Map<String, Metric> gauges = new HashMap();

      for(String pool : POOLS) {
         for(int i = 0; i < ATTRIBUTES.length; ++i) {
            String attribute = ATTRIBUTES[i];
            String name = NAMES[i];

            try {
               ObjectName on = new ObjectName("java.nio:type=BufferPool,name=" + pool);
               this.mBeanServer.getMBeanInfo(on);
               gauges.put(MetricRegistry.name(pool, new String[]{name}), new JmxAttributeGauge(this.mBeanServer, on, attribute));
            } catch (JMException var10) {
               LOGGER.debug("Unable to load buffer pool MBeans, possibly running on Java 6");
            }
         }
      }

      return Collections.unmodifiableMap(gauges);
   }
}

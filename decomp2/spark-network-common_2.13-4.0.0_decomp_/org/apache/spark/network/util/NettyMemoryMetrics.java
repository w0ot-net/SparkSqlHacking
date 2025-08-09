package org.apache.spark.network.util;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import io.netty.buffer.PoolArenaMetric;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocatorMetric;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.sparkproject.guava.annotations.VisibleForTesting;

public class NettyMemoryMetrics implements MetricSet {
   private final PooledByteBufAllocator pooledAllocator;
   private final boolean verboseMetricsEnabled;
   private final Map allMetrics;
   private final String metricPrefix;
   @VisibleForTesting
   static final Set VERBOSE_METRICS = new HashSet();

   public NettyMemoryMetrics(PooledByteBufAllocator pooledAllocator, String metricPrefix, TransportConf conf) {
      this.pooledAllocator = pooledAllocator;
      this.allMetrics = new HashMap();
      this.metricPrefix = metricPrefix;
      this.verboseMetricsEnabled = conf.verboseMetrics();
      this.registerMetrics(this.pooledAllocator);
   }

   private void registerMetrics(PooledByteBufAllocator allocator) {
      PooledByteBufAllocatorMetric pooledAllocatorMetric = allocator.metric();
      this.allMetrics.put(MetricRegistry.name(this.metricPrefix, new String[]{"usedHeapMemory"}), (Gauge)() -> pooledAllocatorMetric.usedHeapMemory());
      this.allMetrics.put(MetricRegistry.name(this.metricPrefix, new String[]{"usedDirectMemory"}), (Gauge)() -> pooledAllocatorMetric.usedDirectMemory());
      if (this.verboseMetricsEnabled) {
         int directArenaIndex = 0;

         for(PoolArenaMetric metric : pooledAllocatorMetric.directArenas()) {
            this.registerArenaMetric(metric, "directArena" + directArenaIndex);
            ++directArenaIndex;
         }

         int heapArenaIndex = 0;

         for(PoolArenaMetric metric : pooledAllocatorMetric.heapArenas()) {
            this.registerArenaMetric(metric, "heapArena" + heapArenaIndex);
            ++heapArenaIndex;
         }
      }

   }

   private void registerArenaMetric(PoolArenaMetric arenaMetric, String arenaName) {
      for(String methodName : VERBOSE_METRICS) {
         Method m;
         try {
            m = PoolArenaMetric.class.getMethod(methodName);
         } catch (Exception var8) {
            continue;
         }

         if (Modifier.isPublic(m.getModifiers())) {
            Class<?> returnType = m.getReturnType();
            String metricName = MetricRegistry.name(this.metricPrefix, new String[]{arenaName, m.getName()});
            if (returnType.equals(Integer.TYPE)) {
               this.allMetrics.put(metricName, (Gauge)() -> {
                  try {
                     return (Integer)m.invoke(arenaMetric);
                  } catch (Exception var3) {
                     return -1;
                  }
               });
            } else if (returnType.equals(Long.TYPE)) {
               this.allMetrics.put(metricName, (Gauge)() -> {
                  try {
                     return (Long)m.invoke(arenaMetric);
                  } catch (Exception var3) {
                     return -1L;
                  }
               });
            }
         }
      }

   }

   public Map getMetrics() {
      return Collections.unmodifiableMap(this.allMetrics);
   }

   static {
      VERBOSE_METRICS.addAll(Arrays.asList("numAllocations", "numTinyAllocations", "numSmallAllocations", "numNormalAllocations", "numHugeAllocations", "numDeallocations", "numTinyDeallocations", "numSmallDeallocations", "numNormalDeallocations", "numHugeDeallocations", "numActiveAllocations", "numActiveTinyAllocations", "numActiveSmallAllocations", "numActiveNormalAllocations", "numActiveHugeAllocations", "numActiveBytes"));
   }
}

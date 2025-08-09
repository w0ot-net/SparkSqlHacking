package com.zaxxer.hikari.metrics.prometheus;

import com.zaxxer.hikari.metrics.PoolStats;
import io.prometheus.client.Collector;
import io.prometheus.client.Collector.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class HikariCPCollector extends Collector {
   private final PoolStats poolStats;
   private final List labelNames;
   private final List labelValues;

   HikariCPCollector(String poolName, PoolStats poolStats) {
      this.poolStats = poolStats;
      this.labelNames = Collections.singletonList("pool");
      this.labelValues = Collections.singletonList(poolName);
   }

   public List collect() {
      return Arrays.asList(this.createSample("hikaricp_active_connections", "Active connections", (double)this.poolStats.getActiveConnections()), this.createSample("hikaricp_idle_connections", "Idle connections", (double)this.poolStats.getIdleConnections()), this.createSample("hikaricp_pending_threads", "Pending threads", (double)this.poolStats.getPendingThreads()), this.createSample("hikaricp_connections", "The number of current connections", (double)this.poolStats.getTotalConnections()));
   }

   private Collector.MetricFamilySamples createSample(String name, String helpMessage, double value) {
      List<Collector.MetricFamilySamples.Sample> samples = Collections.singletonList(new Collector.MetricFamilySamples.Sample(name, this.labelNames, this.labelValues, value));
      return new Collector.MetricFamilySamples(name, Type.GAUGE, helpMessage, samples);
   }
}

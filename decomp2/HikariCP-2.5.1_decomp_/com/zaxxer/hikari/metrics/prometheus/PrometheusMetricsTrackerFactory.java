package com.zaxxer.hikari.metrics.prometheus;

import com.zaxxer.hikari.metrics.MetricsTracker;
import com.zaxxer.hikari.metrics.MetricsTrackerFactory;
import com.zaxxer.hikari.metrics.PoolStats;

public class PrometheusMetricsTrackerFactory implements MetricsTrackerFactory {
   public MetricsTracker create(String poolName, PoolStats poolStats) {
      (new HikariCPCollector(poolName, poolStats)).register();
      return new PrometheusMetricsTracker(poolName);
   }
}

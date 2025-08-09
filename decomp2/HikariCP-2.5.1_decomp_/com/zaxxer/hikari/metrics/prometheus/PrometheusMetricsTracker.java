package com.zaxxer.hikari.metrics.prometheus;

import com.zaxxer.hikari.metrics.MetricsTracker;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;

class PrometheusMetricsTracker extends MetricsTracker {
   private final Counter.Child connectionTimeoutCounter;
   private final Summary.Child elapsedAcquiredSummary;
   private final Summary.Child elapsedBorrowedSummary;

   PrometheusMetricsTracker(String poolName) {
      Counter counter = (Counter)((Counter.Builder)((Counter.Builder)((Counter.Builder)Counter.build().name("hikaricp_connection_timeout_count")).labelNames(new String[]{"pool"})).help("Connection timeout count")).register();
      this.connectionTimeoutCounter = (Counter.Child)counter.labels(new String[]{poolName});
      Summary elapsedAcquiredSummary = (Summary)((Summary.Builder)((Summary.Builder)((Summary.Builder)Summary.build().name("hikaricp_connection_acquired_nanos")).labelNames(new String[]{"pool"})).help("Connection acquired time")).register();
      this.elapsedAcquiredSummary = (Summary.Child)elapsedAcquiredSummary.labels(new String[]{poolName});
      Summary elapsedBorrowedSummary = (Summary)((Summary.Builder)((Summary.Builder)((Summary.Builder)Summary.build().name("hikaricp_connection_usage_millis")).labelNames(new String[]{"pool"})).help("Connection usage")).register();
      this.elapsedBorrowedSummary = (Summary.Child)elapsedBorrowedSummary.labels(new String[]{poolName});
   }

   public void recordConnectionAcquiredNanos(long elapsedAcquiredNanos) {
      this.elapsedAcquiredSummary.observe((double)elapsedAcquiredNanos);
   }

   public void recordConnectionUsageMillis(long elapsedBorrowedMillis) {
      this.elapsedBorrowedSummary.observe((double)elapsedBorrowedMillis);
   }

   public void recordConnectionTimeout() {
      this.connectionTimeoutCounter.inc();
   }
}

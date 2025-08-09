package org.apache.spark.metrics.source;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

public final class HiveCatalogMetrics$ implements Source {
   public static final HiveCatalogMetrics$ MODULE$ = new HiveCatalogMetrics$();
   private static final String sourceName = "HiveExternalCatalog";
   private static final MetricRegistry metricRegistry = new MetricRegistry();
   private static final Counter METRIC_PARTITIONS_FETCHED;
   private static final Counter METRIC_FILES_DISCOVERED;
   private static final Counter METRIC_FILE_CACHE_HITS;
   private static final Counter METRIC_HIVE_CLIENT_CALLS;
   private static final Counter METRIC_PARALLEL_LISTING_JOB_COUNT;

   static {
      METRIC_PARTITIONS_FETCHED = MODULE$.metricRegistry().counter(MetricRegistry.name("partitionsFetched", new String[0]));
      METRIC_FILES_DISCOVERED = MODULE$.metricRegistry().counter(MetricRegistry.name("filesDiscovered", new String[0]));
      METRIC_FILE_CACHE_HITS = MODULE$.metricRegistry().counter(MetricRegistry.name("fileCacheHits", new String[0]));
      METRIC_HIVE_CLIENT_CALLS = MODULE$.metricRegistry().counter(MetricRegistry.name("hiveClientCalls", new String[0]));
      METRIC_PARALLEL_LISTING_JOB_COUNT = MODULE$.metricRegistry().counter(MetricRegistry.name("parallelListingJobCount", new String[0]));
   }

   public String sourceName() {
      return sourceName;
   }

   public MetricRegistry metricRegistry() {
      return metricRegistry;
   }

   public Counter METRIC_PARTITIONS_FETCHED() {
      return METRIC_PARTITIONS_FETCHED;
   }

   public Counter METRIC_FILES_DISCOVERED() {
      return METRIC_FILES_DISCOVERED;
   }

   public Counter METRIC_FILE_CACHE_HITS() {
      return METRIC_FILE_CACHE_HITS;
   }

   public Counter METRIC_HIVE_CLIENT_CALLS() {
      return METRIC_HIVE_CLIENT_CALLS;
   }

   public Counter METRIC_PARALLEL_LISTING_JOB_COUNT() {
      return METRIC_PARALLEL_LISTING_JOB_COUNT;
   }

   public void reset() {
      this.METRIC_PARTITIONS_FETCHED().dec(this.METRIC_PARTITIONS_FETCHED().getCount());
      this.METRIC_FILES_DISCOVERED().dec(this.METRIC_FILES_DISCOVERED().getCount());
      this.METRIC_FILE_CACHE_HITS().dec(this.METRIC_FILE_CACHE_HITS().getCount());
      this.METRIC_HIVE_CLIENT_CALLS().dec(this.METRIC_HIVE_CLIENT_CALLS().getCount());
      this.METRIC_PARALLEL_LISTING_JOB_COUNT().dec(this.METRIC_PARALLEL_LISTING_JOB_COUNT().getCount());
   }

   public void incrementFetchedPartitions(final int n) {
      this.METRIC_PARTITIONS_FETCHED().inc((long)n);
   }

   public void incrementFilesDiscovered(final int n) {
      this.METRIC_FILES_DISCOVERED().inc((long)n);
   }

   public void incrementFileCacheHits(final int n) {
      this.METRIC_FILE_CACHE_HITS().inc((long)n);
   }

   public void incrementHiveClientCalls(final int n) {
      this.METRIC_HIVE_CLIENT_CALLS().inc((long)n);
   }

   public void incrementParallelListingJobCount(final int n) {
      this.METRIC_PARALLEL_LISTING_JOB_COUNT().inc((long)n);
   }

   private HiveCatalogMetrics$() {
   }
}

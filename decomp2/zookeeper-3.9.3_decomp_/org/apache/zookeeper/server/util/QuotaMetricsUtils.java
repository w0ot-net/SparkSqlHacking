package org.apache.zookeeper.server.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.zookeeper.Quotas;
import org.apache.zookeeper.StatsTrack;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.server.DataNode;
import org.apache.zookeeper.server.DataTree;

public final class QuotaMetricsUtils {
   public static final String QUOTA_COUNT_LIMIT_PER_NAMESPACE = "quota_count_limit_per_namespace";
   public static final String QUOTA_BYTES_LIMIT_PER_NAMESPACE = "quota_bytes_limit_per_namespace";
   public static final String QUOTA_COUNT_USAGE_PER_NAMESPACE = "quota_count_usage_per_namespace";
   public static final String QUOTA_BYTES_USAGE_PER_NAMESPACE = "quota_bytes_usage_per_namespace";
   public static final String QUOTA_EXCEEDED_ERROR_PER_NAMESPACE = "quota_exceeded_error_per_namespace";
   static final String LIMIT_END_STRING = "/zookeeper_limits";
   static final String STATS_END_STRING = "/zookeeper_stats";

   private QuotaMetricsUtils() {
   }

   public static Map getQuotaCountLimit(DataTree dataTree) {
      return getQuotaLimitOrUsage(dataTree, QuotaMetricsUtils.QUOTA_LIMIT_USAGE_METRIC_TYPE.QUOTA_COUNT_LIMIT);
   }

   public static Map getQuotaBytesLimit(DataTree dataTree) {
      return getQuotaLimitOrUsage(dataTree, QuotaMetricsUtils.QUOTA_LIMIT_USAGE_METRIC_TYPE.QUOTA_BYTES_LIMIT);
   }

   public static Map getQuotaCountUsage(DataTree dataTree) {
      return getQuotaLimitOrUsage(dataTree, QuotaMetricsUtils.QUOTA_LIMIT_USAGE_METRIC_TYPE.QUOTA_COUNT_USAGE);
   }

   public static Map getQuotaBytesUsage(DataTree dataTree) {
      return getQuotaLimitOrUsage(dataTree, QuotaMetricsUtils.QUOTA_LIMIT_USAGE_METRIC_TYPE.QUOTA_BYTES_USAGE);
   }

   private static Map getQuotaLimitOrUsage(DataTree dataTree, QUOTA_LIMIT_USAGE_METRIC_TYPE type) {
      Map<String, Number> metricsMap = new ConcurrentHashMap();
      if (dataTree != null) {
         getQuotaLimitOrUsage("/zookeeper/quota", metricsMap, type, dataTree);
      }

      return metricsMap;
   }

   private static void getQuotaLimitOrUsage(String path, Map metricsMap, QUOTA_LIMIT_USAGE_METRIC_TYPE type, DataTree dataTree) {
      DataNode node = dataTree.getNode(path);
      if (node != null) {
         String[] children;
         synchronized(node) {
            children = (String[])node.getChildren().toArray(new String[0]);
         }

         if (children.length == 0) {
            if (shouldCollect(path, type)) {
               collectQuotaLimitOrUsage(path, node, metricsMap, type);
            }

         } else {
            for(String child : children) {
               getQuotaLimitOrUsage(path + "/" + child, metricsMap, type, dataTree);
            }

         }
      }
   }

   static boolean shouldCollect(String path, QUOTA_LIMIT_USAGE_METRIC_TYPE type) {
      return path.endsWith("/zookeeper_limits") && (QuotaMetricsUtils.QUOTA_LIMIT_USAGE_METRIC_TYPE.QUOTA_COUNT_LIMIT == type || QuotaMetricsUtils.QUOTA_LIMIT_USAGE_METRIC_TYPE.QUOTA_BYTES_LIMIT == type) || path.endsWith("/zookeeper_stats") && (QuotaMetricsUtils.QUOTA_LIMIT_USAGE_METRIC_TYPE.QUOTA_COUNT_USAGE == type || QuotaMetricsUtils.QUOTA_LIMIT_USAGE_METRIC_TYPE.QUOTA_BYTES_USAGE == type);
   }

   static void collectQuotaLimitOrUsage(String path, DataNode node, Map metricsMap, QUOTA_LIMIT_USAGE_METRIC_TYPE type) {
      String namespace = PathUtils.getTopNamespace(Quotas.trimQuotaPath(path));
      if (namespace != null) {
         byte[] data = node.getData();
         if (data != null) {
            StatsTrack statsTrack = new StatsTrack(data);
            switch (type) {
               case QUOTA_COUNT_LIMIT:
                  aggregateQuotaLimitOrUsage(namespace, metricsMap, getQuotaLimit(statsTrack.getCountHardLimit(), statsTrack.getCount()));
                  break;
               case QUOTA_BYTES_LIMIT:
                  aggregateQuotaLimitOrUsage(namespace, metricsMap, getQuotaLimit(statsTrack.getByteHardLimit(), statsTrack.getBytes()));
                  break;
               case QUOTA_COUNT_USAGE:
                  aggregateQuotaLimitOrUsage(namespace, metricsMap, statsTrack.getCount());
                  break;
               case QUOTA_BYTES_USAGE:
                  aggregateQuotaLimitOrUsage(namespace, metricsMap, statsTrack.getBytes());
            }

         }
      }
   }

   static long getQuotaLimit(long hardLimit, long limit) {
      return hardLimit > -1L ? hardLimit : limit;
   }

   private static void aggregateQuotaLimitOrUsage(String namespace, Map metricsMap, long limitOrUsage) {
      metricsMap.put(namespace, ((Number)metricsMap.getOrDefault(namespace, 0)).longValue() + limitOrUsage);
   }

   static enum QUOTA_LIMIT_USAGE_METRIC_TYPE {
      QUOTA_COUNT_LIMIT,
      QUOTA_BYTES_LIMIT,
      QUOTA_COUNT_USAGE,
      QUOTA_BYTES_USAGE;
   }
}

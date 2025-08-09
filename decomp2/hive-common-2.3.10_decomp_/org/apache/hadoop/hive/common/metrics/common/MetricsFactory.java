package org.apache.hadoop.hive.common.metrics.common;

import java.lang.reflect.Constructor;
import org.apache.hadoop.hive.conf.HiveConf;

public class MetricsFactory {
   private static volatile Metrics metrics;

   public static synchronized void init(HiveConf conf) throws Exception {
      if (metrics == null) {
         Class metricsClass = conf.getClassByName(conf.getVar(HiveConf.ConfVars.HIVE_METRICS_CLASS));
         Constructor constructor = metricsClass.getConstructor(HiveConf.class);
         metrics = (Metrics)constructor.newInstance(conf);
      }

   }

   public static Metrics getInstance() {
      return metrics;
   }

   public static synchronized void close() throws Exception {
      if (metrics != null) {
         metrics.close();
         metrics = null;
      }

   }
}

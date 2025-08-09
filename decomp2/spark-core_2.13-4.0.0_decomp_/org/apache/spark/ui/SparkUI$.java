package org.apache.spark.ui;

import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.package$;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.status.AppStatusStore;
import scala.Option;
import scala.runtime.BoxesRunTime;

public final class SparkUI$ {
   public static final SparkUI$ MODULE$ = new SparkUI$();
   private static final String STATIC_RESOURCE_DIR = "org/apache/spark/ui/static";
   private static final String DEFAULT_POOL_NAME = "default";

   public String STATIC_RESOURCE_DIR() {
      return STATIC_RESOURCE_DIR;
   }

   public String DEFAULT_POOL_NAME() {
      return DEFAULT_POOL_NAME;
   }

   public int getUIPort(final SparkConf conf) {
      return BoxesRunTime.unboxToInt(conf.get(UI$.MODULE$.UI_PORT()));
   }

   public SparkUI create(final Option sc, final AppStatusStore store, final SparkConf conf, final SecurityManager securityManager, final String appName, final String basePath, final long startTime, final String appSparkVersion) {
      return new SparkUI(store, sc, conf, securityManager, appName, basePath, startTime, appSparkVersion);
   }

   public String create$default$8() {
      return package$.MODULE$.SPARK_VERSION();
   }

   private SparkUI$() {
   }
}

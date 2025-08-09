package org.apache.spark.util;

public final class SparkEnvUtils$ implements SparkEnvUtils {
   public static final SparkEnvUtils$ MODULE$ = new SparkEnvUtils$();

   static {
      SparkEnvUtils.$init$(MODULE$);
   }

   public boolean isTesting() {
      return SparkEnvUtils.isTesting$(this);
   }

   private SparkEnvUtils$() {
   }
}

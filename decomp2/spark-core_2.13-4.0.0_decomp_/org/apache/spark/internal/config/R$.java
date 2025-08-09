package org.apache.spark.internal.config;

import scala.runtime.BoxesRunTime;

public final class R$ {
   public static final R$ MODULE$ = new R$();
   private static final ConfigEntry R_BACKEND_CONNECTION_TIMEOUT = (new ConfigBuilder("spark.r.backendConnectionTimeout")).version("2.1.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(6000));
   private static final ConfigEntry R_NUM_BACKEND_THREADS = (new ConfigBuilder("spark.r.numRBackendThreads")).version("1.4.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(2));
   private static final ConfigEntry R_HEARTBEAT_INTERVAL = (new ConfigBuilder("spark.r.heartBeatInterval")).version("2.1.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(100));
   private static final ConfigEntry SPARKR_COMMAND = (new ConfigBuilder("spark.sparkr.r.command")).version("1.5.3").stringConf().createWithDefault("Rscript");
   private static final OptionalConfigEntry R_COMMAND = (new ConfigBuilder("spark.r.command")).version("1.5.3").stringConf().createOptional();

   public ConfigEntry R_BACKEND_CONNECTION_TIMEOUT() {
      return R_BACKEND_CONNECTION_TIMEOUT;
   }

   public ConfigEntry R_NUM_BACKEND_THREADS() {
      return R_NUM_BACKEND_THREADS;
   }

   public ConfigEntry R_HEARTBEAT_INTERVAL() {
      return R_HEARTBEAT_INTERVAL;
   }

   public ConfigEntry SPARKR_COMMAND() {
      return SPARKR_COMMAND;
   }

   public OptionalConfigEntry R_COMMAND() {
      return R_COMMAND;
   }

   private R$() {
   }
}

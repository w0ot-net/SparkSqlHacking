package org.apache.spark.deploy.history;

public final class FsHistoryProvider$ {
   public static final FsHistoryProvider$ MODULE$ = new FsHistoryProvider$();
   private static final String org$apache$spark$deploy$history$FsHistoryProvider$$APPL_START_EVENT_PREFIX = "{\"Event\":\"SparkListenerApplicationStart\"";
   private static final String org$apache$spark$deploy$history$FsHistoryProvider$$APPL_END_EVENT_PREFIX = "{\"Event\":\"SparkListenerApplicationEnd\"";
   private static final String org$apache$spark$deploy$history$FsHistoryProvider$$LOG_START_EVENT_PREFIX = "{\"Event\":\"SparkListenerLogStart\"";
   private static final String org$apache$spark$deploy$history$FsHistoryProvider$$ENV_UPDATE_EVENT_PREFIX = "{\"Event\":\"SparkListenerEnvironmentUpdate\",";
   private static final long CURRENT_LISTING_VERSION = 1L;

   public String org$apache$spark$deploy$history$FsHistoryProvider$$APPL_START_EVENT_PREFIX() {
      return org$apache$spark$deploy$history$FsHistoryProvider$$APPL_START_EVENT_PREFIX;
   }

   public String org$apache$spark$deploy$history$FsHistoryProvider$$APPL_END_EVENT_PREFIX() {
      return org$apache$spark$deploy$history$FsHistoryProvider$$APPL_END_EVENT_PREFIX;
   }

   public String org$apache$spark$deploy$history$FsHistoryProvider$$LOG_START_EVENT_PREFIX() {
      return org$apache$spark$deploy$history$FsHistoryProvider$$LOG_START_EVENT_PREFIX;
   }

   public String org$apache$spark$deploy$history$FsHistoryProvider$$ENV_UPDATE_EVENT_PREFIX() {
      return org$apache$spark$deploy$history$FsHistoryProvider$$ENV_UPDATE_EVENT_PREFIX;
   }

   public long CURRENT_LISTING_VERSION() {
      return CURRENT_LISTING_VERSION;
   }

   private FsHistoryProvider$() {
   }
}

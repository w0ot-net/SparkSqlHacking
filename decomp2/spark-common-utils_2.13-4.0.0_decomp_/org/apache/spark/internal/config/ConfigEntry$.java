package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import scala.Predef.;

public final class ConfigEntry$ {
   public static final ConfigEntry$ MODULE$ = new ConfigEntry$();
   private static final String UNDEFINED = "<undefined>";
   private static final ConcurrentHashMap knownConfigs = new ConcurrentHashMap();

   public String UNDEFINED() {
      return UNDEFINED;
   }

   public ConcurrentHashMap knownConfigs() {
      return knownConfigs;
   }

   public void registerEntry(final ConfigEntry entry) {
      ConfigEntry existing = (ConfigEntry)this.knownConfigs().putIfAbsent(entry.key(), entry);
      .MODULE$.require(existing == null, () -> "Config entry " + entry.key() + " already registered!");
   }

   public ConfigEntry findEntry(final String key) {
      return (ConfigEntry)this.knownConfigs().get(key);
   }

   private ConfigEntry$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

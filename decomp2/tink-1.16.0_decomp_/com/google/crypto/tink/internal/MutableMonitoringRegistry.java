package com.google.crypto.tink.internal;

import java.util.concurrent.atomic.AtomicReference;

public final class MutableMonitoringRegistry {
   private static final MutableMonitoringRegistry GLOBAL_INSTANCE = new MutableMonitoringRegistry();
   private static final DoNothingClient DO_NOTHING_CLIENT = new DoNothingClient();
   private final AtomicReference monitoringClient = new AtomicReference();

   public static MutableMonitoringRegistry globalInstance() {
      return GLOBAL_INSTANCE;
   }

   public synchronized void clear() {
      this.monitoringClient.set((Object)null);
   }

   public synchronized void registerMonitoringClient(MonitoringClient client) {
      if (this.monitoringClient.get() != null) {
         throw new IllegalStateException("a monitoring client has already been registered");
      } else {
         this.monitoringClient.set(client);
      }
   }

   public MonitoringClient getMonitoringClient() {
      MonitoringClient client = (MonitoringClient)this.monitoringClient.get();
      return (MonitoringClient)(client == null ? DO_NOTHING_CLIENT : client);
   }

   private static class DoNothingClient implements MonitoringClient {
      private DoNothingClient() {
      }

      public MonitoringClient.Logger createLogger(MonitoringKeysetInfo keysetInfo, String primitive, String api) {
         return MonitoringUtil.DO_NOTHING_LOGGER;
      }
   }
}

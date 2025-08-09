package io.fabric8.kubernetes.client.informers;

import io.fabric8.kubernetes.client.informers.cache.Indexer;
import io.fabric8.kubernetes.client.informers.cache.ItemStore;
import io.fabric8.kubernetes.client.informers.cache.Store;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface SharedIndexInformer extends AutoCloseable {
   SharedIndexInformer addIndexers(Map var1);

   default SharedIndexInformer removeNamespaceIndex() {
      return this.removeIndexer("namespace");
   }

   SharedIndexInformer removeIndexer(String var1);

   Indexer getIndexer();

   SharedIndexInformer addEventHandler(ResourceEventHandler var1);

   SharedIndexInformer removeEventHandler(ResourceEventHandler var1);

   SharedIndexInformer addEventHandlerWithResyncPeriod(ResourceEventHandler var1, long var2);

   SharedIndexInformer run();

   void stop();

   default void close() {
      this.stop();
   }

   default boolean hasSynced() {
      return this.lastSyncResourceVersion() != null;
   }

   String lastSyncResourceVersion();

   boolean isRunning();

   Class getApiTypeClass();

   boolean isWatching();

   Store getStore();

   SharedIndexInformer initialState(Stream var1);

   SharedIndexInformer itemStore(ItemStore var1);

   CompletionStage start();

   SharedIndexInformer exceptionHandler(ExceptionHandler var1);

   CompletionStage stopped();
}

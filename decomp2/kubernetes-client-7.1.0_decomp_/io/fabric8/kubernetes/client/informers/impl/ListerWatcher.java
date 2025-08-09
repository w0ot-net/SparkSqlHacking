package io.fabric8.kubernetes.client.informers.impl;

import io.fabric8.kubernetes.api.model.ListOptions;
import io.fabric8.kubernetes.client.Watcher;
import java.util.concurrent.CompletableFuture;

public interface ListerWatcher {
   CompletableFuture submitWatch(ListOptions var1, Watcher var2);

   CompletableFuture submitList(ListOptions var1);

   Long getLimit();

   int getWatchReconnectInterval();

   String getApiEndpointPath();
}

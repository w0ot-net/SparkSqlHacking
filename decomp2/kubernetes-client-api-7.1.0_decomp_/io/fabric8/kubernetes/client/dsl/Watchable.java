package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.ListOptions;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;

public interface Watchable {
   Watch watch(Watcher var1);

   Watch watch(ListOptions var1, Watcher var2);

   /** @deprecated */
   @Deprecated
   Watch watch(String var1, Watcher var2);
}

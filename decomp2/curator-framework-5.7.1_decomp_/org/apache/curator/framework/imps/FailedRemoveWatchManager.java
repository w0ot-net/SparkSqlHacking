package org.apache.curator.framework.imps;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundPathableQuietlyable;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.zookeeper.Watcher;

class FailedRemoveWatchManager extends FailedOperationManager {
   FailedRemoveWatchManager(CuratorFramework client) {
      super(client);
   }

   protected void executeGuaranteedOperationInBackground(FailedRemoveWatchDetails details) throws Exception {
      if (details.watcher == null) {
         ((ErrorListenerPathable)((BackgroundPathableQuietlyable)this.client.watches().removeAll().guaranteed()).inBackground()).forPath(details.path);
      } else {
         ((ErrorListenerPathable)((BackgroundPathableQuietlyable)this.client.watches().remove(details.watcher).guaranteed()).inBackground()).forPath(details.path);
      }

   }

   static class FailedRemoveWatchDetails {
      public final String path;
      public final Watcher watcher;

      public FailedRemoveWatchDetails(String path, Watcher watcher) {
         this.path = path;
         this.watcher = watcher;
      }
   }
}

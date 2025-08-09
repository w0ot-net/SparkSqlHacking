package org.apache.zookeeper;

import java.util.Map;

public class WatchDeregistration {
   private final String clientPath;
   private final Watcher watcher;
   private final Watcher.WatcherType watcherType;
   private final boolean local;
   private final ZKWatchManager zkManager;

   public WatchDeregistration(String clientPath, Watcher watcher, Watcher.WatcherType watcherType, boolean local, ZKWatchManager zkManager) {
      this.clientPath = clientPath;
      this.watcher = watcher;
      this.watcherType = watcherType;
      this.local = local;
      this.zkManager = zkManager;
   }

   public Map unregister(int rc) throws KeeperException {
      return this.zkManager.removeWatcher(this.clientPath, this.watcher, this.watcherType, this.local, rc);
   }

   public String getClientPath() {
      return this.clientPath;
   }
}

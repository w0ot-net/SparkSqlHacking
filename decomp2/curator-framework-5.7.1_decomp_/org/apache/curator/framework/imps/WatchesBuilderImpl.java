package org.apache.curator.framework.imps;

import org.apache.curator.framework.api.AddWatchBuilder;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.WatchesBuilder;
import org.apache.zookeeper.Watcher;

public class WatchesBuilderImpl extends RemoveWatchesBuilderImpl implements WatchesBuilder {
   public WatchesBuilderImpl(CuratorFrameworkImpl client) {
      super(client);
   }

   public WatchesBuilderImpl(CuratorFrameworkImpl client, Watcher watcher, CuratorWatcher curatorWatcher, Watcher.WatcherType watcherType, boolean guaranteed, boolean local, boolean quietly, Backgrounding backgrounding) {
      super(client, watcher, curatorWatcher, watcherType, guaranteed, local, quietly, backgrounding);
   }

   public AddWatchBuilder add() {
      return new AddWatchBuilderImpl(this.getClient());
   }
}

package org.apache.curator.framework.api;

import org.apache.zookeeper.Watcher;

public interface RemoveWatchesBuilder {
   RemoveWatchesType remove(Watcher var1);

   RemoveWatchesType remove(CuratorWatcher var1);

   RemoveWatchesType removeAll();
}

package org.apache.curator.framework.api;

import org.apache.zookeeper.Watcher;

public interface WatchableBase {
   Object usingWatcher(Watcher var1);

   Object usingWatcher(CuratorWatcher var1);
}

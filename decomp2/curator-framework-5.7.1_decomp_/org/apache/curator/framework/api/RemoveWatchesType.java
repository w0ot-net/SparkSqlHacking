package org.apache.curator.framework.api;

import org.apache.zookeeper.Watcher;

public interface RemoveWatchesType extends RemoveWatchesLocal, Guaranteeable {
   RemoveWatchesLocal ofType(Watcher.WatcherType var1);
}

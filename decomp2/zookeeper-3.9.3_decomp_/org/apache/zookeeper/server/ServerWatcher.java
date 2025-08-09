package org.apache.zookeeper.server;

import java.util.List;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public interface ServerWatcher extends Watcher {
   void process(WatchedEvent var1, List var2);
}

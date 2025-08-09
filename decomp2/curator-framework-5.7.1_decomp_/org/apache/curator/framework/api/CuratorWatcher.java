package org.apache.curator.framework.api;

import org.apache.zookeeper.WatchedEvent;

public interface CuratorWatcher {
   void process(WatchedEvent var1) throws Exception;
}

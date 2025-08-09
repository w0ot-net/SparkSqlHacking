package org.apache.curator.framework.imps;

import org.apache.zookeeper.WatchedEvent;

class NamespaceWatchedEvent extends WatchedEvent {
   NamespaceWatchedEvent(CuratorFrameworkImpl client, WatchedEvent event) {
      super(event.getType(), event.getState(), client.unfixForNamespace(event.getPath()));
   }
}

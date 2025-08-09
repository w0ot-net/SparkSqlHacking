package org.apache.zookeeper;

import java.util.Set;

public interface ClientWatchManager {
   Set materialize(Watcher.Event.KeeperState var1, Watcher.Event.EventType var2, String var3);
}

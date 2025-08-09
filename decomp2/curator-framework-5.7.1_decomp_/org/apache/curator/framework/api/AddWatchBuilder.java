package org.apache.curator.framework.api;

import org.apache.zookeeper.AddWatchMode;

public interface AddWatchBuilder extends AddWatchBuilder2 {
   AddWatchBuilder2 withMode(AddWatchMode var1);
}

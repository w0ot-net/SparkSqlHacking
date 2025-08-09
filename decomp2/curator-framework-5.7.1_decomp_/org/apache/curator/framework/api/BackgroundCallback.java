package org.apache.curator.framework.api;

import org.apache.curator.framework.CuratorFramework;

public interface BackgroundCallback {
   void processResult(CuratorFramework var1, CuratorEvent var2) throws Exception;
}

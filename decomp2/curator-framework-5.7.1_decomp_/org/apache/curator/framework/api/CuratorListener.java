package org.apache.curator.framework.api;

import org.apache.curator.framework.CuratorFramework;

public interface CuratorListener {
   void eventReceived(CuratorFramework var1, CuratorEvent var2) throws Exception;
}

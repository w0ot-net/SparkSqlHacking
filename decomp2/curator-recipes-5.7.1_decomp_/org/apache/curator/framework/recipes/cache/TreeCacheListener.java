package org.apache.curator.framework.recipes.cache;

import org.apache.curator.framework.CuratorFramework;

public interface TreeCacheListener {
   void childEvent(CuratorFramework var1, TreeCacheEvent var2) throws Exception;
}

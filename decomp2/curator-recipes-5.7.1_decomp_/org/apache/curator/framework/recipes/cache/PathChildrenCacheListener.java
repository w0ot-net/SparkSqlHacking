package org.apache.curator.framework.recipes.cache;

import org.apache.curator.framework.CuratorFramework;

public interface PathChildrenCacheListener {
   void childEvent(CuratorFramework var1, PathChildrenCacheEvent var2) throws Exception;
}

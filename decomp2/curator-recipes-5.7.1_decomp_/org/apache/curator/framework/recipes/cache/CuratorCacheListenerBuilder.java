package org.apache.curator.framework.recipes.cache;

import java.util.function.Consumer;
import org.apache.curator.framework.CuratorFramework;

public interface CuratorCacheListenerBuilder {
   CuratorCacheListenerBuilder forAll(CuratorCacheListener var1);

   CuratorCacheListenerBuilder forCreates(Consumer var1);

   CuratorCacheListenerBuilder forChanges(ChangeListener var1);

   CuratorCacheListenerBuilder forCreatesAndChanges(ChangeListener var1);

   CuratorCacheListenerBuilder forDeletes(Consumer var1);

   CuratorCacheListenerBuilder forInitialized(Runnable var1);

   CuratorCacheListenerBuilder forPathChildrenCache(String var1, CuratorFramework var2, PathChildrenCacheListener var3);

   CuratorCacheListenerBuilder forTreeCache(CuratorFramework var1, TreeCacheListener var2);

   CuratorCacheListenerBuilder forNodeCache(NodeCacheListener var1);

   CuratorCacheListenerBuilder afterInitialized();

   CuratorCacheListener build();

   @FunctionalInterface
   public interface ChangeListener {
      void event(ChildData var1, ChildData var2);
   }
}

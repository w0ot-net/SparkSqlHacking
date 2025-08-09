package org.apache.curator.framework.recipes.cache;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.LoggerFactory;

class CuratorCacheBridgeBuilderImpl implements CuratorCacheBridgeBuilder {
   private final CuratorFramework client;
   private final String path;
   private CuratorCache.Options[] options;
   private boolean cacheData = true;
   private ExecutorService executorService = null;
   private final boolean forceTreeCache = Boolean.getBoolean("curator-cache-bridge-force-tree-cache");

   CuratorCacheBridgeBuilderImpl(CuratorFramework client, String path) {
      this.client = client;
      this.path = path;
   }

   public CuratorCacheBridgeBuilder withOptions(CuratorCache.Options... options) {
      this.options = options;
      return this;
   }

   public CuratorCacheBridgeBuilder withDataNotCached() {
      this.cacheData = false;
      return this;
   }

   public CuratorCacheBridgeBuilder withExecutorService(ExecutorService executorService) {
      this.executorService = executorService;
      return this;
   }

   public CuratorCacheBridge build() {
      if (!this.forceTreeCache && this.client.getZookeeperCompatibility().hasPersistentWatchers()) {
         if (this.executorService != null) {
            LoggerFactory.getLogger(this.getClass()).warn("CuratorCache does not support custom ExecutorService");
         }

         CuratorCacheStorage storage = this.cacheData ? CuratorCacheStorage.standard() : CuratorCacheStorage.dataNotCached();
         return new CuratorCacheImpl(this.client, storage, this.path, this.options, (Consumer)null);
      } else {
         return new CompatibleCuratorCacheBridge(this.client, this.path, this.options, this.executorService, this.cacheData);
      }
   }
}

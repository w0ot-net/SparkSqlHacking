package org.apache.curator.framework.recipes.cache;

import java.util.concurrent.ExecutorService;

public interface CuratorCacheBridgeBuilder {
   CuratorCacheBridgeBuilder withOptions(CuratorCache.Options... var1);

   CuratorCacheBridgeBuilder withDataNotCached();

   CuratorCacheBridgeBuilder withExecutorService(ExecutorService var1);

   CuratorCacheBridge build();
}

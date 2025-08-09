package org.apache.curator.framework.recipes.cache;

import java.util.function.Consumer;

public interface CuratorCacheBuilder {
   CuratorCacheBuilder withOptions(CuratorCache.Options... var1);

   CuratorCacheBuilder withStorage(CuratorCacheStorage var1);

   CuratorCacheBuilder withExceptionHandler(Consumer var1);

   CuratorCache build();
}

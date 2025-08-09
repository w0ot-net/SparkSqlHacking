package org.apache.curator.framework.recipes.cache;

import java.util.function.Consumer;
import org.apache.curator.framework.CuratorFramework;

class CuratorCacheBuilderImpl implements CuratorCacheBuilder {
   private final CuratorFramework client;
   private final String path;
   private CuratorCacheStorage storage;
   private Consumer exceptionHandler;
   private CuratorCache.Options[] options;

   CuratorCacheBuilderImpl(CuratorFramework client, String path) {
      this.client = client;
      this.path = path;
   }

   public CuratorCacheBuilder withOptions(CuratorCache.Options... options) {
      this.options = options;
      return this;
   }

   public CuratorCacheBuilder withStorage(CuratorCacheStorage storage) {
      this.storage = storage;
      return this;
   }

   public CuratorCacheBuilder withExceptionHandler(Consumer exceptionHandler) {
      this.exceptionHandler = exceptionHandler;
      return this;
   }

   public CuratorCache build() {
      return new CuratorCacheImpl(this.client, this.storage, this.path, this.options, this.exceptionHandler);
   }
}

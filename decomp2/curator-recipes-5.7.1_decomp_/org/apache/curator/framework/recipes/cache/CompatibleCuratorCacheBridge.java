package org.apache.curator.framework.recipes.cache;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.shaded.com.google.common.collect.Sets;

class CompatibleCuratorCacheBridge implements CuratorCacheBridge, TreeCacheListener {
   private final TreeCache cache;
   private final StandardListenerManager listenerManager = StandardListenerManager.standard();

   CompatibleCuratorCacheBridge(CuratorFramework client, String path, CuratorCache.Options[] optionsArg, ExecutorService executorService, boolean cacheData) {
      Set<CuratorCache.Options> options = (Set<CuratorCache.Options>)(optionsArg != null ? Sets.newHashSet(optionsArg) : Collections.emptySet());
      TreeCache.Builder builder = TreeCache.newBuilder(client, path).setCacheData(cacheData);
      if (options.contains(CuratorCache.Options.SINGLE_NODE_CACHE)) {
         builder.setMaxDepth(0);
      }

      if (options.contains(CuratorCache.Options.COMPRESSED_DATA)) {
         builder.setDataIsCompressed(true);
      }

      if (executorService != null) {
         builder.setExecutor(executorService);
      }

      this.cache = builder.build();
   }

   public void start() {
      try {
         this.cache.getListenable().addListener(this);
         this.cache.start();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public void close() {
      this.cache.close();
   }

   public boolean isCuratorCache() {
      return false;
   }

   public Listenable listenable() {
      return this.listenerManager;
   }

   public Optional get(String path) {
      return Optional.ofNullable(this.cache.getCurrentData(path));
   }

   public int size() {
      return this.cache.size();
   }

   public Stream stream() {
      TreeCache var10000 = this.cache;
      Iterable<ChildData> iterable = var10000::iterator;
      return StreamSupport.stream(iterable.spliterator(), false);
   }

   public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
      switch (event.getType()) {
         case NODE_ADDED:
            this.listenerManager.forEach((listener) -> listener.event(CuratorCacheListener.Type.NODE_CREATED, (ChildData)null, event.getData()));
            break;
         case NODE_REMOVED:
            this.listenerManager.forEach((listener) -> listener.event(CuratorCacheListener.Type.NODE_DELETED, event.getData(), (ChildData)null));
            break;
         case NODE_UPDATED:
            this.listenerManager.forEach((listener) -> listener.event(CuratorCacheListener.Type.NODE_CHANGED, event.getOldData(), event.getData()));
            break;
         case INITIALIZED:
            this.listenerManager.forEach(CuratorCacheListener::initialized);
      }

   }
}

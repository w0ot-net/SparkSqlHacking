package org.apache.curator.framework.recipes.cache;

import java.io.Closeable;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.Listenable;

public interface CuratorCache extends Closeable, CuratorCacheAccessor {
   static CuratorCache build(CuratorFramework client, String path, Options... options) {
      return builder(client, path).withOptions(options).build();
   }

   static CuratorCacheBuilder builder(CuratorFramework client, String path) {
      return new CuratorCacheBuilderImpl(client, path);
   }

   static CuratorCacheBridgeBuilder bridgeBuilder(CuratorFramework client, String path) {
      return new CuratorCacheBridgeBuilderImpl(client, path);
   }

   void start();

   void close();

   Listenable listenable();

   Optional get(String var1);

   int size();

   Stream stream();

   public static enum Options {
      SINGLE_NODE_CACHE,
      COMPRESSED_DATA,
      DO_NOT_CLEAR_ON_CLOSE;
   }
}

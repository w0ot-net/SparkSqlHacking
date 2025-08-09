package org.apache.curator.framework.recipes.cache;

import java.util.Optional;
import java.util.stream.Stream;

public interface CuratorCacheStorage extends CuratorCacheAccessor {
   static CuratorCacheStorage standard() {
      return new StandardCuratorCacheStorage(true);
   }

   static CuratorCacheStorage dataNotCached() {
      return new StandardCuratorCacheStorage(false);
   }

   Optional put(ChildData var1);

   Optional remove(String var1);

   void clear();

   Optional get(String var1);

   int size();

   Stream stream();
}

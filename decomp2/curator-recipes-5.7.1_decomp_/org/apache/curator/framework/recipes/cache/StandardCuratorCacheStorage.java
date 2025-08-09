package org.apache.curator.framework.recipes.cache;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

class StandardCuratorCacheStorage implements CuratorCacheStorage {
   private final Map dataMap = new ConcurrentHashMap();
   private final boolean cacheBytes;

   StandardCuratorCacheStorage(boolean cacheBytes) {
      this.cacheBytes = cacheBytes;
   }

   public Optional put(ChildData data) {
      ChildData localData = this.cacheBytes ? data : new ChildData(data.getPath(), data.getStat(), (byte[])null);
      return Optional.ofNullable(this.dataMap.put(data.getPath(), localData));
   }

   public Optional remove(String path) {
      return Optional.ofNullable(this.dataMap.remove(path));
   }

   public Optional get(String path) {
      return Optional.ofNullable(this.dataMap.get(path));
   }

   public int size() {
      return this.dataMap.size();
   }

   public Stream stream() {
      return this.dataMap.values().stream();
   }

   public void clear() {
      this.dataMap.clear();
   }
}

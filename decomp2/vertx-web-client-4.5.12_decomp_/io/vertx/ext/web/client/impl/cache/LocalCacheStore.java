package io.vertx.ext.web.client.impl.cache;

import io.vertx.core.Future;
import io.vertx.ext.web.client.spi.CacheStore;
import java.util.HashMap;
import java.util.Map;

public class LocalCacheStore implements CacheStore {
   private final Map localMap = new HashMap();

   public Future get(CacheKey key) {
      return Future.succeededFuture(this.localMap.get(key));
   }

   public Future set(CacheKey key, CachedHttpResponse response) {
      return Future.succeededFuture(this.localMap.put(key, response));
   }

   public Future delete(CacheKey key) {
      this.localMap.remove(key);
      return Future.succeededFuture();
   }

   public Future flush() {
      this.localMap.clear();
      return Future.succeededFuture();
   }
}

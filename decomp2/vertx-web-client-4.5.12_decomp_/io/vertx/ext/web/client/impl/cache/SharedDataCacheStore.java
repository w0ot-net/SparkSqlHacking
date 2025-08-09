package io.vertx.ext.web.client.impl.cache;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.client.spi.CacheStore;

public class SharedDataCacheStore implements CacheStore {
   private static final String ASYNC_MAP_NAME = "HttpCacheStore";
   private final SharedData sharedData;

   public SharedDataCacheStore(Vertx vertx) {
      this.sharedData = vertx.sharedData();
   }

   public Future get(CacheKey key) {
      return this.asyncMap().compose((map) -> map.get(key));
   }

   public Future set(CacheKey key, CachedHttpResponse response) {
      return this.asyncMap().compose((map) -> map.put(key, response)).map(response);
   }

   public Future delete(CacheKey key) {
      return this.asyncMap().compose((map) -> map.remove(key)).mapEmpty();
   }

   public Future flush() {
      return this.asyncMap().compose(AsyncMap::clear);
   }

   private Future asyncMap() {
      return this.sharedData.getAsyncMap("HttpCacheStore");
   }
}

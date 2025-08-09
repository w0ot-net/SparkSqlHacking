package io.vertx.ext.web.client.spi;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.impl.cache.CacheKey;
import io.vertx.ext.web.client.impl.cache.CachedHttpResponse;
import io.vertx.ext.web.client.impl.cache.LocalCacheStore;
import io.vertx.ext.web.client.impl.cache.NoOpCacheStore;
import io.vertx.ext.web.client.impl.cache.SharedDataCacheStore;

public interface CacheStore {
   static CacheStore build() {
      return new NoOpCacheStore();
   }

   static CacheStore sharedDataStore(Vertx vertx) {
      return new SharedDataCacheStore(vertx);
   }

   static CacheStore localStore() {
      return new LocalCacheStore();
   }

   Future get(CacheKey var1);

   Future set(CacheKey var1, CachedHttpResponse var2);

   Future delete(CacheKey var1);

   Future flush();

   default void get(CacheKey key, Handler handler) {
      this.get(key).onComplete(handler);
   }

   default void set(CacheKey key, CachedHttpResponse response, Handler handler) {
      this.set(key, response).onComplete(handler);
   }

   default void delete(CacheKey key, Handler handler) {
      this.delete(key).onComplete(handler);
   }

   default void flush(Handler handler) {
      this.flush().onComplete(handler);
   }
}

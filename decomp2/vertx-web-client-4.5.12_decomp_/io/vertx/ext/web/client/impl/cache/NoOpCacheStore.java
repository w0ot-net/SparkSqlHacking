package io.vertx.ext.web.client.impl.cache;

import io.vertx.core.Future;
import io.vertx.ext.web.client.spi.CacheStore;

public final class NoOpCacheStore implements CacheStore {
   public Future get(CacheKey key) {
      return Future.failedFuture("The no-op cache store cannot return results");
   }

   public Future set(CacheKey key, CachedHttpResponse response) {
      return Future.succeededFuture(response);
   }

   public Future delete(CacheKey key) {
      return Future.succeededFuture();
   }

   public Future flush() {
      return Future.succeededFuture();
   }
}

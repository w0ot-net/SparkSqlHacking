package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.client.impl.CachingWebClientImpl;
import io.vertx.ext.web.client.impl.cache.LocalCacheStore;
import io.vertx.ext.web.client.spi.CacheStore;

@VertxGen
public interface CachingWebClient {
   @GenIgnore({"permitted-type"})
   static WebClient create(WebClient webClient) {
      return create(webClient, (CacheStore)(new LocalCacheStore()));
   }

   @GenIgnore({"permitted-type"})
   static WebClient create(WebClient webClient, CacheStore cacheStore) {
      return create(webClient, cacheStore, new CachingWebClientOptions());
   }

   @GenIgnore({"permitted-type"})
   static WebClient create(WebClient webClient, CachingWebClientOptions options) {
      return create(webClient, new LocalCacheStore(), options);
   }

   @GenIgnore({"permitted-type"})
   static WebClient create(WebClient webClient, CacheStore cacheStore, CachingWebClientOptions options) {
      return CachingWebClientImpl.wrap(webClient, cacheStore, options);
   }
}

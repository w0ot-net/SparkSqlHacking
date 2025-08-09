package io.vertx.ext.web.client.impl;

import io.vertx.ext.web.client.CachingWebClientOptions;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.impl.cache.CacheInterceptor;
import io.vertx.ext.web.client.spi.CacheStore;

public interface CachingWebClientImpl {
   static WebClient wrap(WebClient webClient, CacheStore cacheStore, CachingWebClientOptions options) {
      WebClientInternal internal = new WebClientBase((WebClientBase)webClient);
      internal.addInterceptor(new CacheInterceptor(cacheStore, options));
      return internal;
   }
}

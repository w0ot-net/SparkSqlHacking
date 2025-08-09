package io.vertx.ext.web.client.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientSession;
import io.vertx.ext.web.client.spi.CacheStore;
import io.vertx.ext.web.client.spi.CookieStore;

public class WebClientSessionAware extends WebClientBase implements WebClientSession {
   private final CookieStore cookieStore;
   private final CacheStore cacheStore;
   private MultiMap headers;

   public WebClientSessionAware(WebClient webClient, CookieStore cookieStore) {
      super((WebClientBase)webClient);
      this.cookieStore = cookieStore;
      this.cacheStore = CacheStore.localStore();
      this.addInterceptor(new SessionAwareInterceptor(this));
   }

   public CookieStore cookieStore() {
      return this.cookieStore;
   }

   protected MultiMap headers() {
      if (this.headers == null) {
         this.headers = HttpHeaders.headers();
      }

      return this.headers;
   }

   public WebClientSession addHeader(CharSequence name, CharSequence value) {
      this.headers().add(name, value);
      return this;
   }

   public WebClientSession addHeader(String name, String value) {
      this.headers().add(name, value);
      return this;
   }

   public WebClientSession addHeader(CharSequence name, Iterable values) {
      this.headers().add(name, values);
      return this;
   }

   public WebClientSession addHeader(String name, Iterable values) {
      this.headers().add(name, values);
      return this;
   }

   public WebClientSession removeHeader(CharSequence name) {
      this.headers().remove(name);
      return this;
   }

   public WebClientSession removeHeader(String name) {
      this.headers().remove(name);
      return this;
   }

   public HttpContext createContext(Handler handler) {
      return super.createContext(handler).privateCacheStore(this.cacheStore);
   }
}

package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.client.impl.WebClientSessionAware;
import io.vertx.ext.web.client.spi.CookieStore;

@VertxGen
public interface WebClientSession extends WebClient {
   static WebClientSession create(WebClient webClient) {
      return create(webClient, CookieStore.build());
   }

   @GenIgnore({"permitted-type"})
   static WebClientSession create(WebClient webClient, CookieStore cookieStore) {
      return new WebClientSessionAware(webClient, cookieStore);
   }

   @Fluent
   @GenIgnore({"permitted-type"})
   WebClientSession addHeader(CharSequence var1, CharSequence var2);

   @Fluent
   WebClientSession addHeader(String var1, String var2);

   @Fluent
   @GenIgnore({"permitted-type"})
   WebClientSession addHeader(CharSequence var1, Iterable var2);

   @Fluent
   @GenIgnore({"permitted-type"})
   WebClientSession addHeader(String var1, Iterable var2);

   @Fluent
   @GenIgnore({"permitted-type"})
   WebClientSession removeHeader(CharSequence var1);

   @Fluent
   WebClientSession removeHeader(String var1);

   @GenIgnore({"permitted-type"})
   CookieStore cookieStore();
}

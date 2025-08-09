package io.vertx.ext.web.client.spi;

import io.netty.handler.codec.http.cookie.Cookie;
import io.vertx.ext.web.client.impl.CookieStoreImpl;

public interface CookieStore {
   static CookieStore build() {
      return new CookieStoreImpl();
   }

   Iterable get(Boolean var1, String var2, String var3);

   CookieStore put(Cookie var1);

   CookieStore remove(Cookie var1);
}

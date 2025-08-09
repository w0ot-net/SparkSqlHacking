package io.vertx.core.http;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.http.impl.CookieImpl;

@VertxGen
public interface Cookie {
   static Cookie cookie(String name, String value) {
      return new CookieImpl(name, value);
   }

   String getName();

   String getValue();

   @Fluent
   Cookie setValue(String var1);

   @Fluent
   Cookie setDomain(@Nullable String var1);

   @Nullable String getDomain();

   @Fluent
   Cookie setPath(@Nullable String var1);

   @Nullable String getPath();

   @Fluent
   Cookie setMaxAge(long var1);

   long getMaxAge();

   @Fluent
   Cookie setSecure(boolean var1);

   boolean isSecure();

   @Fluent
   Cookie setHttpOnly(boolean var1);

   boolean isHttpOnly();

   @Fluent
   Cookie setSameSite(CookieSameSite var1);

   @Nullable CookieSameSite getSameSite();

   String encode();
}

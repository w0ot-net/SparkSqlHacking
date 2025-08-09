package io.vertx.core.http.impl;

import io.vertx.core.http.Cookie;

public interface ServerCookie extends Cookie {
   boolean isChanged();

   void setChanged(boolean var1);

   boolean isFromUserAgent();
}

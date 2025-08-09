package io.vertx.ext.web.client.impl;

import io.vertx.core.Handler;
import io.vertx.ext.web.client.WebClient;

public interface WebClientInternal extends WebClient {
   HttpContext createContext(Handler var1);

   WebClientInternal addInterceptor(Handler var1);
}

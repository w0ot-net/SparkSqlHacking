package io.vertx.core.http.impl;

import io.vertx.core.Future;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.SocketAddress;

public interface HttpClientInternal extends HttpClient {
   VertxInternal vertx();

   HttpClientOptions options();

   default Future connect(SocketAddress server) {
      return this.connect(server, (SocketAddress)null);
   }

   Future connect(SocketAddress var1, SocketAddress var2);
}

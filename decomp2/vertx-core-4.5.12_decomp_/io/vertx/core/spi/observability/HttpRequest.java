package io.vertx.core.spi.observability;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.net.SocketAddress;

public interface HttpRequest {
   int id();

   String uri();

   String absoluteURI();

   HttpMethod method();

   MultiMap headers();

   SocketAddress remoteAddress();
}

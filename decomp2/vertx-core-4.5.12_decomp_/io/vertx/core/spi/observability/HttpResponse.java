package io.vertx.core.spi.observability;

import io.vertx.core.MultiMap;

public interface HttpResponse {
   int statusCode();

   MultiMap headers();
}

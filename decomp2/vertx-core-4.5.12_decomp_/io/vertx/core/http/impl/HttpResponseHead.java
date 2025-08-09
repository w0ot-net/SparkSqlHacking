package io.vertx.core.http.impl;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.spi.observability.HttpResponse;

public class HttpResponseHead implements HttpResponse {
   public final HttpVersion version;
   public final int statusCode;
   public final String statusMessage;
   public final MultiMap headers;

   public HttpResponseHead(HttpVersion version, int statusCode, String statusMessage, MultiMap headers) {
      this.version = version;
      this.statusCode = statusCode;
      this.statusMessage = statusMessage;
      this.headers = headers;
   }

   public int statusCode() {
      return this.statusCode;
   }

   public MultiMap headers() {
      return this.headers;
   }
}

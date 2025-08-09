package io.vertx.core.http.impl;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.observability.HttpRequest;

public class HttpRequestHead implements HttpRequest {
   int id;
   SocketAddress remoteAddress;
   public final HttpMethod method;
   public final String uri;
   public final MultiMap headers;
   public final String authority;
   public final String absoluteURI;
   public final String traceOperation;

   public HttpRequestHead(HttpMethod method, String uri, MultiMap headers, String authority, String absoluteURI, String traceOperation) {
      if (uri != null && !uri.isEmpty()) {
         this.method = method;
         this.uri = uri;
         this.headers = headers;
         this.authority = authority;
         this.absoluteURI = absoluteURI;
         this.traceOperation = traceOperation;
      } else {
         throw new IllegalArgumentException("Invalid request URI");
      }
   }

   public MultiMap headers() {
      return this.headers;
   }

   public SocketAddress remoteAddress() {
      return this.remoteAddress;
   }

   public String absoluteURI() {
      return this.absoluteURI;
   }

   public int id() {
      return this.id;
   }

   public String uri() {
      return this.uri;
   }

   public HttpMethod method() {
      return this.method;
   }
}

package io.vertx.core.http.impl;

import io.netty.handler.codec.http2.Http2Headers;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.impl.headers.Http2HeadersAdaptor;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.observability.HttpRequest;

public class HttpClientPush implements HttpRequest {
   final int port;
   final String uri;
   final HttpMethod method;
   final String host;
   final HttpClientStream stream;
   final MultiMap headers;

   public HttpClientPush(Http2Headers headers, HttpClientStream stream) {
      String rawMethod = headers.method().toString();
      String authority = headers.authority() != null ? headers.authority().toString() : null;
      MultiMap headersMap = new Http2HeadersAdaptor(headers);
      int pos = authority == null ? -1 : authority.indexOf(58);
      if (pos == -1) {
         this.host = authority;
         this.port = 80;
      } else {
         this.host = authority.substring(0, pos);
         this.port = Integer.parseInt(authority.substring(pos + 1));
      }

      this.method = HttpMethod.valueOf(rawMethod);
      this.uri = headers.path().toString();
      this.stream = stream;
      this.headers = headersMap;
   }

   public int id() {
      return this.stream.id();
   }

   public MultiMap headers() {
      return this.headers;
   }

   public String absoluteURI() {
      return null;
   }

   public SocketAddress remoteAddress() {
      return this.stream.connection().remoteAddress();
   }

   public String uri() {
      return this.uri;
   }

   public HttpMethod method() {
      return this.method;
   }
}

package io.vertx.core.http.impl;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpVersion;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.impl.headers.HeadersMultiMap;

public class VertxHttpRequestDecoder extends HttpRequestDecoder {
   public VertxHttpRequestDecoder(HttpServerOptions options) {
      super(options.getMaxInitialLineLength(), options.getMaxHeaderSize(), options.getMaxChunkSize(), !HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION, options.getDecoderInitialBufferSize());
   }

   protected HttpMessage createMessage(String[] initialLine) {
      return new DefaultHttpRequest(HttpVersion.valueOf(initialLine[2]), HttpMethod.valueOf(initialLine[0]), initialLine[1], HeadersMultiMap.httpHeaders());
   }

   protected boolean isContentAlwaysEmpty(HttpMessage msg) {
      if (msg == null) {
         return false;
      } else {
         return msg.getClass() == DefaultHttpRequest.class ? false : super.isContentAlwaysEmpty(msg);
      }
   }
}

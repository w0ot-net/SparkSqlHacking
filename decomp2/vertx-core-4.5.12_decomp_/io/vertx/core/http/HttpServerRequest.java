package io.vertx.core.http;

import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.TooLongHttpHeaderException;
import io.netty.handler.codec.http.TooLongHttpLineException;
import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

@VertxGen
public interface HttpServerRequest extends ReadStream {
   @GenIgnore
   Handler DEFAULT_INVALID_REQUEST_HANDLER = (request) -> {
      DecoderResult result = request.decoderResult();
      Throwable cause = result.cause();
      HttpResponseStatus status = null;
      if (cause instanceof TooLongHttpLineException) {
         status = HttpResponseStatus.REQUEST_URI_TOO_LONG;
      } else if (cause instanceof TooLongHttpHeaderException) {
         status = HttpResponseStatus.REQUEST_HEADER_FIELDS_TOO_LARGE;
      }

      if (status == null) {
         status = HttpResponseStatus.BAD_REQUEST;
      }

      HttpServerResponse response = request.response();
      response.setStatusCode(status.code()).end();
      response.close();
   };

   HttpServerRequest exceptionHandler(Handler var1);

   HttpServerRequest handler(Handler var1);

   HttpServerRequest pause();

   HttpServerRequest resume();

   HttpServerRequest fetch(long var1);

   HttpServerRequest endHandler(Handler var1);

   HttpVersion version();

   HttpMethod method();

   default boolean isSSL() {
      return this.connection().isSsl();
   }

   @Nullable String scheme();

   String uri();

   @Nullable String path();

   @Nullable String query();

   @Nullable HostAndPort authority();

   /** @deprecated */
   @Deprecated
   @Nullable String host();

   long bytesRead();

   @CacheReturn
   HttpServerResponse response();

   @CacheReturn
   MultiMap headers();

   default @Nullable String getHeader(String headerName) {
      return this.headers().get(headerName);
   }

   @GenIgnore({"permitted-type"})
   default String getHeader(CharSequence headerName) {
      return this.headers().get(headerName);
   }

   @Fluent
   HttpServerRequest setParamsCharset(String var1);

   String getParamsCharset();

   @CacheReturn
   default MultiMap params() {
      return this.params(false);
   }

   MultiMap params(boolean var1);

   default @Nullable String getParam(String paramName) {
      return this.params().get(paramName);
   }

   default String getParam(String paramName, String defaultValue) {
      Objects.requireNonNull(defaultValue, "defaultValue");
      String paramValue = this.params().get(paramName);
      return paramValue != null ? paramValue : defaultValue;
   }

   @CacheReturn
   default SocketAddress remoteAddress() {
      return this.connection().remoteAddress();
   }

   @CacheReturn
   default SocketAddress localAddress() {
      return this.connection().localAddress();
   }

   @GenIgnore({"permitted-type"})
   default SSLSession sslSession() {
      return this.connection().sslSession();
   }

   @GenIgnore
   X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;

   String absoluteURI();

   @Fluent
   default HttpServerRequest bodyHandler(@Nullable Handler bodyHandler) {
      this.body().onSuccess(bodyHandler);
      return this;
   }

   default HttpServerRequest body(Handler handler) {
      this.body().onComplete(handler);
      return this;
   }

   Future body();

   default void end(Handler handler) {
      this.end().onComplete(handler);
   }

   Future end();

   default void toNetSocket(Handler handler) {
      Future<NetSocket> fut = this.toNetSocket();
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   Future toNetSocket();

   @Fluent
   HttpServerRequest setExpectMultipart(boolean var1);

   boolean isExpectMultipart();

   @Fluent
   HttpServerRequest uploadHandler(@Nullable Handler var1);

   @CacheReturn
   MultiMap formAttributes();

   @Nullable String getFormAttribute(String var1);

   @CacheReturn
   default int streamId() {
      return -1;
   }

   default void toWebSocket(Handler handler) {
      Future<ServerWebSocket> fut = this.toWebSocket();
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   Future toWebSocket();

   boolean isEnded();

   @Fluent
   HttpServerRequest customFrameHandler(Handler var1);

   @CacheReturn
   HttpConnection connection();

   default StreamPriority streamPriority() {
      return null;
   }

   @Fluent
   HttpServerRequest streamPriorityHandler(Handler var1);

   @GenIgnore
   DecoderResult decoderResult();

   @Nullable Cookie getCookie(String var1);

   @Nullable Cookie getCookie(String var1, String var2, String var3);

   default int cookieCount() {
      return this.cookies().size();
   }

   /** @deprecated */
   @Deprecated
   default Map cookieMap() {
      return (Map)this.cookies().stream().collect(Collectors.toMap(Cookie::getName, (cookie) -> cookie));
   }

   Set cookies(String var1);

   Set cookies();

   @Fluent
   default HttpServerRequest routed(String route) {
      return this;
   }
}

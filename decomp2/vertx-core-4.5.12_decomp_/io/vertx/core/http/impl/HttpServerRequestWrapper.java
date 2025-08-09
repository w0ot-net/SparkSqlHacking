package io.vertx.core.http.impl;

import io.netty.handler.codec.DecoderResult;
import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.net.HostAndPort;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.X509Certificate;

public class HttpServerRequestWrapper extends HttpServerRequestInternal {
   protected final HttpServerRequestInternal delegate;

   public HttpServerRequestWrapper(HttpServerRequestInternal delegate) {
      if (delegate == null) {
         throw new NullPointerException("Null delegate not allowed");
      } else {
         this.delegate = delegate;
      }
   }

   public HttpServerRequest exceptionHandler(Handler handler) {
      return this.delegate.exceptionHandler(handler);
   }

   public HttpServerRequest handler(Handler handler) {
      return this.delegate.handler(handler);
   }

   public HttpServerRequest pause() {
      return this.delegate.pause();
   }

   public HttpServerRequest resume() {
      return this.delegate.resume();
   }

   public HttpServerRequest fetch(long amount) {
      return this.delegate.fetch(amount);
   }

   public HttpServerRequest endHandler(Handler endHandler) {
      return this.delegate.endHandler(endHandler);
   }

   public HttpVersion version() {
      return this.delegate.version();
   }

   public HttpMethod method() {
      return this.delegate.method();
   }

   public String scheme() {
      return this.delegate.scheme();
   }

   public String uri() {
      return this.delegate.uri();
   }

   public String path() {
      return this.delegate.path();
   }

   public String query() {
      return this.delegate.query();
   }

   public HostAndPort authority() {
      return this.delegate.authority();
   }

   public boolean isValidAuthority() {
      return this.delegate.isValidAuthority();
   }

   public String host() {
      return this.delegate.host();
   }

   public long bytesRead() {
      return this.delegate.bytesRead();
   }

   public HttpServerResponse response() {
      return this.delegate.response();
   }

   public MultiMap headers() {
      return this.delegate.headers();
   }

   public HttpServerRequest setParamsCharset(String charset) {
      return this.delegate.setParamsCharset(charset);
   }

   public String getParamsCharset() {
      return this.delegate.getParamsCharset();
   }

   public MultiMap params(boolean semicolonIsNormalChar) {
      return this.delegate.params(semicolonIsNormalChar);
   }

   @GenIgnore
   public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
      return this.delegate.peerCertificateChain();
   }

   public String absoluteURI() {
      return this.delegate.absoluteURI();
   }

   public Future body() {
      return this.delegate.body();
   }

   public Future end() {
      return this.delegate.end();
   }

   public Future toNetSocket() {
      return this.delegate.toNetSocket();
   }

   @Fluent
   public HttpServerRequest setExpectMultipart(boolean expect) {
      return this.delegate.setExpectMultipart(expect);
   }

   public boolean isExpectMultipart() {
      return this.delegate.isExpectMultipart();
   }

   @Fluent
   public HttpServerRequest uploadHandler(@Nullable Handler uploadHandler) {
      return this.delegate.uploadHandler(uploadHandler);
   }

   @CacheReturn
   public MultiMap formAttributes() {
      return this.delegate.formAttributes();
   }

   public @Nullable String getFormAttribute(String attributeName) {
      return this.delegate.getFormAttribute(attributeName);
   }

   @CacheReturn
   public int streamId() {
      return this.delegate.streamId();
   }

   public Future toWebSocket() {
      return this.delegate.toWebSocket();
   }

   public boolean isEnded() {
      return this.delegate.isEnded();
   }

   @Fluent
   public HttpServerRequest customFrameHandler(Handler handler) {
      return this.delegate.customFrameHandler(handler);
   }

   @CacheReturn
   public HttpConnection connection() {
      return this.delegate.connection();
   }

   public StreamPriority streamPriority() {
      return this.delegate.streamPriority();
   }

   @Fluent
   public HttpServerRequest streamPriorityHandler(Handler handler) {
      return this.delegate.streamPriorityHandler(handler);
   }

   @GenIgnore
   public DecoderResult decoderResult() {
      return this.delegate.decoderResult();
   }

   public @Nullable Cookie getCookie(String name) {
      return this.delegate.getCookie(name);
   }

   public @Nullable Cookie getCookie(String name, String domain, String path) {
      return this.delegate.getCookie(name, domain, path);
   }

   public Set cookies(String name) {
      return this.delegate.cookies(name);
   }

   public Set cookies() {
      return this.delegate.cookies();
   }

   @Fluent
   public HttpServerRequest routed(String route) {
      return this.delegate.routed(route);
   }

   public Context context() {
      return this.delegate.context();
   }

   public Object metric() {
      return this.delegate.metric();
   }
}

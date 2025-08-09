package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.http.WebsocketVersion;
import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.SSLOptions;
import io.vertx.core.net.SocketAddress;
import java.util.List;
import java.util.function.Function;

public class SharedHttpClient implements HttpClientInternal {
   public static final String SHARED_MAP_NAME = "__vertx.shared.httpClients";
   private final VertxInternal vertx;
   private final CloseFuture closeFuture;
   private final HttpClientInternal delegate;

   public SharedHttpClient(VertxInternal vertx, CloseFuture closeFuture, HttpClient delegate) {
      this.vertx = vertx;
      this.closeFuture = closeFuture;
      this.delegate = (HttpClientInternal)delegate;
   }

   public void close(Handler handler) {
      ContextInternal closingCtx = this.vertx.getOrCreateContext();
      this.closeFuture.close(handler != null ? closingCtx.promise(handler) : null);
   }

   public Future close() {
      ContextInternal closingCtx = this.vertx.getOrCreateContext();
      PromiseInternal<Void> promise = closingCtx.promise();
      this.closeFuture.close(promise);
      return promise.future();
   }

   public void request(RequestOptions options, Handler handler) {
      this.delegate.request(options, handler);
   }

   public Future request(RequestOptions options) {
      return this.delegate.request(options);
   }

   public void request(HttpMethod method, int port, String host, String requestURI, Handler handler) {
      this.delegate.request(method, port, host, requestURI, handler);
   }

   public Future request(HttpMethod method, int port, String host, String requestURI) {
      return this.delegate.request(method, port, host, requestURI);
   }

   public void request(HttpMethod method, String host, String requestURI, Handler handler) {
      this.delegate.request(method, host, requestURI, handler);
   }

   public Future request(HttpMethod method, String host, String requestURI) {
      return this.delegate.request(method, host, requestURI);
   }

   public void request(HttpMethod method, String requestURI, Handler handler) {
      this.delegate.request(method, requestURI, handler);
   }

   public Future request(HttpMethod method, String requestURI) {
      return this.delegate.request(method, requestURI);
   }

   public void webSocket(int port, String host, String requestURI, Handler handler) {
      this.delegate.webSocket(port, host, requestURI, handler);
   }

   public Future webSocket(int port, String host, String requestURI) {
      return this.delegate.webSocket(port, host, requestURI);
   }

   public void webSocket(String host, String requestURI, Handler handler) {
      this.delegate.webSocket(host, requestURI, handler);
   }

   public Future webSocket(String host, String requestURI) {
      return this.delegate.webSocket(host, requestURI);
   }

   public void webSocket(String requestURI, Handler handler) {
      this.delegate.webSocket(requestURI, handler);
   }

   public Future webSocket(String requestURI) {
      return this.delegate.webSocket(requestURI);
   }

   public void webSocket(WebSocketConnectOptions options, Handler handler) {
      this.delegate.webSocket(options, handler);
   }

   public Future webSocket(WebSocketConnectOptions options) {
      return this.delegate.webSocket(options);
   }

   public void webSocketAbs(String url, MultiMap headers, WebsocketVersion version, List subProtocols, Handler handler) {
      this.delegate.webSocketAbs(url, headers, version, subProtocols, handler);
   }

   public Future webSocketAbs(String url, MultiMap headers, WebsocketVersion version, List subProtocols) {
      return this.delegate.webSocketAbs(url, headers, version, subProtocols);
   }

   public Future updateSSLOptions(SSLOptions options, boolean force) {
      return this.delegate.updateSSLOptions(options, force);
   }

   public HttpClient connectionHandler(Handler handler) {
      return this.delegate.connectionHandler(handler);
   }

   public HttpClient redirectHandler(Function handler) {
      return this.delegate.redirectHandler(handler);
   }

   public Function redirectHandler() {
      return this.delegate.redirectHandler();
   }

   public VertxInternal vertx() {
      return this.delegate.vertx();
   }

   public HttpClientOptions options() {
      return this.delegate.options();
   }

   public Future connect(SocketAddress server, SocketAddress peer) {
      return this.delegate.connect(server, peer);
   }
}
